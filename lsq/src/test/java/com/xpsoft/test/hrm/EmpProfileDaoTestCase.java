 package com.xpsoft.test.hrm;
 
 import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.ExcelUtils;
import com.xpsoft.oa.dao.hrm.EmpProfileDao;
import com.xpsoft.oa.model.hrm.EmpProfile;
import com.xpsoft.oa.model.hrm.Job;
import com.xpsoft.oa.service.hrm.JobService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.AppUserUpdateService;
import com.xpsoft.test.BaseTestCase;
 
 public class EmpProfileDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private EmpProfileDao empProfileDao;
   @Resource
   private AppUserService appUserService;
   @Resource
   private JobService jobService;
   @Resource
   private AppUserUpdateService appUserUpdateService;
   @Test
   @Rollback(false)
   public void add()
   {
//     EmpProfile empProfile = new EmpProfile();
// 
//     this.empProfileDao.save(empProfile);
   }
   
   @Test
	public void importAdd() {

		String filePath = "员工档案导入模板——.xls";// 文件路径
		
		String fileName = "";// 文件名
		
		int count = 0;
		Map<String,Object> map = ExcelUtils.readExcel(this.getClass()
				.getResource("").getPath().replace("%20", " ")
				+ filePath,2,3);

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
				EmpProfile empProfile = empProfileDao.getByUserId(Long.valueOf(a.get(0).toString().trim()));
				for(Object col:a){
//					if(j==5){//出生日期
//						if(StringUtils.isNotBlank(col.toString())){
//							try {
//								Date date = DateUtils.parseDate(col.toString().trim(), new String[] { "yyyy-MM-dd","yyyy/mm/dd","yyyy/m/d" });
//								if(empProfile!=null){
////									empProfile.setBirthday(date);
//								}
//							} catch (ParseException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//					}
					
//					if(j==28){//出生日期
//						if(StringUtils.isNotBlank(col.toString())&&!"#N/A".equals(col.toString().trim())){
//							try {
//								Date date = DateUtils.parseDate(col.toString().trim(), new String[] { "yyyy-MM-dd","yyyy/mm/dd","yyyy/m/d" });
//								System.out.println(col.toString());
//								if(empProfile!=null){
//									empProfile.setStartWorkDate(date);
//								}
//							} catch (ParseException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//					}
					
//					System.out.print(col+",");
					j++;
				}
				if(empProfile!=null){
//					empProfile.setDept(empProfile.getAppUser().getDepartment());
//					empProfile.setDepName(empProfile.getAppUser().getDepartment().getDepName());
//					Job job = jobService.findByDepAndJobName(Long.valueOf(empProfile.getAppUser().getDepartment().getDepId()),empProfile.getPosition());
//					empProfile.setJob(job);
//					empProfile.setCreatetime(new Date());
//					empProfile.setCreator("超级管理员");
					appUserUpdateService.saveAppUserUpdate(empProfile.getAppUser());
					empProfileDao.save(empProfile);
				}
				System.out.println("");
			}
		}

		System.out.println("{success:true,data:'共上传" + count + "条数据'}");
	}
 }

