package com.xpsoft.test.statistics;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.xpsoft.core.util.ExcelUtils;
import com.xpsoft.oa.dao.statistics.DesignProjectDao;
import com.xpsoft.oa.dao.system.DepartmentDao;
import com.xpsoft.oa.model.statistics.DesignProject;
import com.xpsoft.oa.model.system.Department;
import com.xpsoft.test.BaseTestCase;

public class DesignProjectDaoTestCase extends BaseTestCase {
	private Logger log = Logger.getLogger(DesignProjectDaoTestCase.class);
	@Resource
	private DesignProjectDao designProjectDao;
	@Resource
	private DepartmentDao departmentDao;
	@Test
	@Rollback(false)
	public void add(){		
//		DesignProject designProject=new DesignProject();
////		TODO
//
//		designProjectDao.save(designProject);
	}
	
	@Test
	public void importAdd() {

		String filePath = "副本台帐模版.xls";// 文件路径
		
		String fileName = "";// 文件名
		
		int count = 0;
		Map<String,Object> map = ExcelUtils.readExcel(this.getClass()
				.getResource("").getPath().replace("%20", " ")
				+ filePath,4,5);

		if (null == map) {
			log.error("数据异常3");
			throw new RuntimeException("{success:false,data:'数据异常'}");
		}
		List sheetNameList = (List)map.get("sheetNameList");
		List sheetList = (List)map.get("sheetList");
		int length = sheetList.size();
		for (int i=0;i<length;i++) {
			List<ArrayList> rowList = (List<ArrayList>)sheetList.get(i);
			String sheetName = (String)sheetNameList.get(i);
			// excle的所有行
			for (ArrayList a : rowList) {
				int j = 0;
				DesignProject designProject = new DesignProject();
				for(Object col:a){
					if(j==0){
						if(StringUtils.isBlank(col.toString())){
							j++;
							break;
						}
						designProject.setProNo(col.toString());
					}
					if(j==1){
						designProject.setProName(col.toString());
					}
					if(j==2){
						if(StringUtils.isNotBlank(col.toString())){
							if(col.toString().trim().contains("技术一部")){
								Department department = departmentDao.get(30L);
//								designProject.setDept(department);
							}else if(col.toString().trim().contains("技术二部")){
								Department department = departmentDao.get(31L);
//								designProject.setDept(department);
							}
						}
					}
					System.out.print(col+",");
					j++;
				}
				if(StringUtils.isNotBlank(designProject.getProNo())){
					designProjectDao.save(designProject);
				}
				System.out.println("");
			}
		}

		System.out.println("{success:true,data:'共上传" + count + "条数据'}");
	}
}