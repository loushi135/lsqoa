package com.xpsoft.test.statistics;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.xpsoft.core.util.DateUtil;
import com.xpsoft.core.util.ExcelUtils;
import com.xpsoft.core.util.Trans2RMB;
import com.xpsoft.oa.dao.statistics.SalesProjectDao;
import com.xpsoft.oa.model.statistics.DesignProject;
import com.xpsoft.oa.model.statistics.OtherProject;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.statistics.SalesProject;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.Department;
import com.xpsoft.oa.service.statistics.DesignProjectService;
import com.xpsoft.oa.service.statistics.OtherProjectService;
import com.xpsoft.oa.service.statistics.ProjectNewService;
import com.xpsoft.oa.service.statistics.SalesProjectService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.DepartmentService;
import com.xpsoft.test.BaseTestCase;

public class SalesProjectDaoTestCase extends BaseTestCase {
	private final Logger logger = Logger.getLogger(SalesProjectDaoTestCase.class);
	@Resource
	private SalesProjectDao salesProjectDao;
	@Resource
	private AppUserService appUserService;
	@Resource
	private DepartmentService departmentService;
	@Resource
	private ProjectNewService projectNewService;
	@Resource
	private DesignProjectService designProjectService;
	@Resource
	private OtherProjectService otherProjectService;
	@Resource
	private SalesProjectService salesProjectService;
	@Test
	@Rollback(false)
	public void add(){		
//		SalesProject salesProject=new SalesProject();
//		TODO

//		salesProjectDao.save(salesProject);
	}
	
//	@Test
	public void importAdd() {

		String filePath = "副本台帐模版.xls";// 文件路径
		
		String fileName = "";// 文件名
		
		Map<String,Object> map = ExcelUtils.readExcel(this.getClass()
				.getResource("").getPath().replace("%20", " ")
				+ filePath,5,12);

		if (null == map) {
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
				SalesProject salesProject = new SalesProject();
				for(Object col:a){
					if(j==0){
						salesProject.setProNo(col.toString().trim());
					}
					if(j==1){
						if(StringUtils.isBlank(col.toString())){
							j++;
							break;
						}
						salesProject.setProName(col.toString().trim());
					}
					if(j==2){
						if(StringUtils.isNotBlank(col.toString())){
							String deptName = sheetNameList.get(i).toString().trim()+"团队";
							List<Department> departments = departmentService.findByDepName(deptName);
							if(departments!=null&&departments.size()>0){
								salesProject.setTeamDep(departments.get(0));
							}
						}
					}
					if(j==3){
						if(StringUtils.isNotBlank(col.toString())){
							List<AppUser> userList = appUserService.findByFullnames("'"+col.toString().trim()+"'");
							if(userList!=null&&userList.size()>0){
								salesProject.setBusinessUser(userList.get(0));
							}
						}
					}
					System.out.print(col+",");
					j++;
				}
				if(StringUtils.isNotBlank(salesProject.getProName())){
					salesProjectDao.save(salesProject);
				}
				System.out.println("");
			}
		}
	}
	
	@Test
	public void importAllRepay() throws IOException {

		String filePath = "1306费用.xls";// 文件路径
		
		String fileName = "";// 文件名
		
		Map<String,Object> map = ExcelUtils.readExcel(this.getClass()
				.getResource("").getPath().replace("%20", " ")
				+ filePath,0,1);

		if (null == map) {
			throw new RuntimeException("{success:false,data:'数据异常'}");
		}
		List sheetNameList = (List)map.get("sheetNameList");
		List sheetList = (List)map.get("sheetList");
		int length = sheetList.size();
		File file = new File("c:/file.txt");
		File file_error = new File("c:/file_error.txt");
		FileWriter fileWriter=null;
		FileWriter fileWriter_E=null;
		try {
			fileWriter = new FileWriter(file);
			fileWriter_E = new FileWriter(file_error);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int i=0;i<length;i++) {
			List<ArrayList> rowList = (List<ArrayList>)sheetList.get(i);
			String sheetName = (String)sheetNameList.get(i);
			// excle的所有行
			for (ArrayList a : rowList) {
				if(a.get(0)==null) break;
				String repayTime = DateUtil.format(DateUtil.parse(a.get(0).toString().trim(), "d/M/yyyy"),"yyyy-MM-dd");
				String proNo = a.get(1).toString().trim();
				String proName = a.get(2).toString().trim();
				String repayUserName = a.get(3).toString().trim();
				String amount = a.get(4).toString().trim().replace(",", "");
				if (amount.startsWith("(")) {
					amount = "-" + amount.substring(1, amount.length() - 1);
				}
				String amountBig = new Trans2RMB().toRMB(amount);
				String proId = "";
				String createTime = DateUtil.format(new Date(), "yyyy-MM-dd");
				String createUser = "1";
				String repayType = "";
				String repayUser = "1";
				List<AppUser> appUserList = appUserService.findByFullnames("'"+repayUserName+"'");
				if(appUserList!=null&&appUserList.size()>0){
					repayUser = appUserList.get(0).getUserId().toString();
					if(appUserList.size()>1){
						fileWriter_E.write("userId:"+repayUser+"此姓名有多个用户,项目："+proName+",项目编号"+proNo+"金额"+amount+"日期"+repayTime+"报销人"+repayUserName+"\n");
					}
				}else{
					fileWriter_E.write("没有找到报销人,项目："+proName+",项目编号"+proNo+"金额"+amount+"日期"+repayTime+"报销人"+repayUserName+"\n");
				}
				if(proNo.startsWith("Z.")){//其他项目
					repayType = "4";
					OtherProject otherProject = otherProjectService.getByProName(proName);
					if(otherProject!=null){
						proId = otherProject.getId().toString();
					}else{
						fileWriter_E.write(i+"其他项目："+proName+",项目编号"+proNo+"金额"+amount+"日期"+repayTime+"\n");
					}
				}else if(proNo.startsWith("SJ.")){//设计项目
					repayType = "2";
					DesignProject designProject = designProjectService.getByProName(proName);
					if(designProject!=null){
						proId = designProject.getId().toString();
					}else{
						fileWriter_E.write("设计项目："+proName+",项目编号"+proNo+"\n");
					}
				}else if(proNo.split("\\.")[0].length() == 1){//工程项目
					repayType = "1";
					ProjectNew projectNews = projectNewService.getByProName(proName);
					if(projectNews==null)projectNews = projectNewService.getByProNo(proNo);
					if(projectNews!=null){
						proId = projectNews.getId().toString();
					}else{
						fileWriter_E.write("工程项目："+proName+",项目编号"+proNo+"\n");
					}
				}else{//营销项目
					repayType = "3";
					SalesProject salesProject = salesProjectService.getByProName(proName);
					if(salesProject!=null){
						proId = salesProject.getId().toString();
					}else{
						fileWriter_E.write("营销项目："+proName+",项目编号"+proNo+"\n");
					}
				}
				String insertStr = "INSERT INTO project_repay(amountBig,amount,proId,createtime,createUser,repayTime,repayUserId,repayType) " +
						"VALUES('"+amountBig+"','"+amount+"','"+proId+"','"+createTime+"','"+createUser+"','"+repayTime+"','"+repayUser+"','"+repayType+"');";
				fileWriter.write(insertStr+"\n");
//				logger.info("INSERT INTO project_repay(amountBig,amount,proId,createtime,createUser,repayTime,repayUserId,repayType) " +
//						"VALUES('"+amountBig+"','"+amount+"','"+proId+"','"+createTime+"','"+createUser+"','"+repayTime+"','"+repayUser+"','"+repayType+"');");
//				System.out.println("INSERT INTO project_repay(amountBig,amount,proId,createtime,createUser,repayTime,repayUserId,repayType) " +
//						"VALUES('"+amountBig+"','"+amount+"','"+proId+"','"+createTime+"','"+createUser+"','"+repayTime+"','"+repayUser+"','"+repayType+"');");
			}
		}
		fileWriter_E.close();
		fileWriter.close();
	}
}