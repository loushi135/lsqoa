package com.xpsoft.webservice.service.inner.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.model.SyncUserInfo;

import com.thoughtworks.xstream.XStream;
import com.xpsoft.core.Constants;
import com.xpsoft.core.model.BaseModel;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.BeanUtil;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.core.util.NullConverter;
import com.xpsoft.core.util.XmlUtil;
import com.xpsoft.oa.model.hrm.EmpProfile;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.AppUserUpdate;
import com.xpsoft.oa.model.system.Department;
import com.xpsoft.oa.service.hrm.JobService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.DepartmentService;
import com.xpsoft.webservice.service.inner.SyncUserInfoService;

public class SyncUserInfoServiceImpl implements SyncUserInfoService {
	private final Log logger = LogFactory.getLog(SyncUserInfoServiceImpl.class);
	@Override
	public String getUser(String updateDate) {
		AppUserService appUserService = (AppUserService)AppUtil.getBean("appUserService");
		List list = appUserService.findByUpdateTime(updateDate);
		List<SyncUserInfo> userList = this.assembleData(list);
		XStream xstream = new XStream();
		xstream.setMode(XStream.NO_REFERENCES);
		NullConverter nullConverter = new NullConverter();
		xstream.registerConverter(nullConverter);
//		xstream.alias("userInfo", Object[].class);
		xstream.alias("syncUserInfo", SyncUserInfo.class);
//		xstream.alias("department", Department.class);
//		xstream.alias("empProfile", EmpProfile.class);
//		xstream.alias("appUserUpdate", AppUserUpdate.class);
//		xstream.aliasField("部门名称", Department.class, "depName");
//		xstream.aliasAttribute(Department.class, "depName", "dept");
//		xstream.omitField(AppUser.class, "roles");
//		xstream.omitField(AppUser.class, "userId");
//		xstream.omitField(AppUser.class, "rights");
		xstream.omitField(BaseModel.class, "logger");//去掉父类里的logger
		xstream.omitField(BaseModel.class, "version");//去掉父类里的logger
//		xstream.omitField(Department.class, "depId");
//		xstream.omitField(AppUser.class, "password");
//		xstream.omitField(AppUserUpdate.class, "appUser");
//		xstream.omitField(AppUserUpdate.class, "id");
//		xstream.aliasType("accessionTime", Date.class);
		String str = xstream.toXML(userList);
		return XmlUtil.reponseXml(str);
	}
	
	
	
	@Override
	public String getDepartmentAndJob() {
		DepartmentService departmentService = (DepartmentService)AppUtil.getBean("departmentService");
		JobService jobService = (JobService)AppUtil.getBean("jobService");
		
		List<Department> deptList = departmentService.getAll();
		List<String> jobNameList = jobService.getAllJobName();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("deptList", deptList);
		dataMap.put("jobNameList", jobNameList);
		XStream deptxstream = new XStream();
		deptxstream.setMode(XStream.NO_REFERENCES);
		deptxstream.omitField(BaseModel.class, "logger");//去掉父类里的logger
		deptxstream.omitField(BaseModel.class, "version");//去掉父类里的logger
		deptxstream.omitField(Department.class, "depDesc");
		deptxstream.omitField(Department.class, "depLevel");
//		deptxstream.omitField(Department.class, "parentId");
		deptxstream.omitField(Department.class, "path");
		deptxstream.alias("department", Department.class);
		deptxstream.alias("deptList", List.class);
		String deptStr = deptxstream.toXML(deptList)+"\n";
		XStream jobxstream = new XStream();
		jobxstream.alias("jobList", List.class);
		jobxstream.alias("jobName", String.class);
		String jobStr = jobxstream.toXML(jobNameList);
		return XmlUtil.reponseXml(deptStr+jobStr);
	}


	@Override
	public String getDeptInfoById(String id) {
		String status = "";
		String deptStr = "";
		DepartmentService departmentService = (DepartmentService)AppUtil.getBean("departmentService");
		if(StringUtils.isNotBlank(id)){
			Department department = departmentService.get(Long.valueOf(id));
			if(department!=null){
				XStream deptxstream = new XStream();
				deptxstream.omitField(BaseModel.class, "logger");//去掉父类里的logger
				deptxstream.omitField(BaseModel.class, "version");//去掉父类里的logger
				deptxstream.omitField(Department.class, "depDesc");
				deptxstream.omitField(Department.class, "depLevel");
				deptxstream.omitField(Department.class, "path");
				deptxstream.alias("department", Department.class);
				deptStr = deptxstream.toXML(department)+"\n";
				status = "<status>正常</status>";
			}else{
				status = "<status>异常：没有查到id为"+id+"的部门</status>";
			}
		}else{
			status = "<status>异常：请传入部门id</status>";
		}
		return XmlUtil.reponseXml(deptStr+status);
	}



	/**
	 * 组装员工同步数据
	 * @param list
	 * @return
	 */
	private List<SyncUserInfo> assembleData(List list){
		List<SyncUserInfo> userList = new ArrayList<SyncUserInfo>();
		for(Object obj : list){
			Object o[] = (Object[])obj;
			AppUser appUser = (AppUser)o[0];
			Department department = (Department)o[1];
			EmpProfile empProfile = (EmpProfile)o[2];
			AppUserUpdate appUserUpdate = (AppUserUpdate)o[3];
			SyncUserInfo syncUserInfo = new SyncUserInfo();
			syncUserInfo.setDepId(department.getDepId().toString());
			syncUserInfo.setDepName(department.getDepName());
			syncUserInfo.setUpdateTime(DateUtil.format(appUserUpdate.getUpdateTime(),Constants.DATE_FORMAT_YMD));
			try {
				BeanUtil.copyNotNullProperties(syncUserInfo, appUser);
				int status = appUser.getStatus().intValue();
				switch (status) {//1=激活  0=禁用  2=离职  3=集团用户
					case 0:
						syncUserInfo.setStatus("禁用");
						break;
					case 1:
						syncUserInfo.setStatus("激活");
						break;
					case 2:
						syncUserInfo.setStatus("离职");
						break;
					case 3:
						syncUserInfo.setStatus("集团用户");
						break;
					default:
						break;
				}
				int sex = appUser.getTitle().intValue();
				switch (sex) {
					case 0:
						syncUserInfo.setSex("女");
						break;
					case 1:
						syncUserInfo.setSex("男");
						break;
					default:
						break;
				}
				syncUserInfo.setAssessionTime(DateUtil.format(appUser.getAccessionTime(),Constants.DATE_FORMAT_YMD));
				if(appUser.getDepartureTime()!=null){
					syncUserInfo.setDepartureTime(DateUtil.format(appUser.getDepartureTime(),Constants.DATE_FORMAT_YMD));
				}else{
					syncUserInfo.setDepartureTime("");
				}
				if(empProfile==null){
					empProfile = new EmpProfile();
				}
				syncUserInfo.setProfileNo(empProfile.getProfileNo()==null?"":empProfile.getProfileNo());
				syncUserInfo.setBirthday(empProfile.getBirthday()==null?"":DateUtil.format(empProfile.getBirthday(),Constants.DATE_FORMAT_YMD));
				syncUserInfo.setStartWorkDate(empProfile.getStartWorkDate()==null?"":DateUtil.format(empProfile.getStartWorkDate(),Constants.DATE_FORMAT_YMD));
				syncUserInfo.setWorkContractEndDate(empProfile.getWorkContractEndDate()==null?"":DateUtil.format(empProfile.getWorkContractEndDate(),Constants.DATE_FORMAT_YMD));
				syncUserInfo.setRosterJobName(empProfile.getRosterJobName()==null?"":empProfile.getRosterJobName());
				syncUserInfo.setEduMajor(empProfile.getEduMajor()==null?"":empProfile.getEduMajor());
				syncUserInfo.setEduCollege(empProfile.getEduCollege()==null?"":empProfile.getEduCollege());
				syncUserInfo.setIdCard(empProfile.getIdCard()==null?"":empProfile.getIdCard());
				syncUserInfo.setInnerCommentTitle(empProfile.getInnerCommentTitle()==null?"":empProfile.getInnerCommentTitle());
				syncUserInfo.setRePayWageTime(empProfile.getRePayWageTime()==null?"":DateUtil.format(empProfile.getRePayWageTime(),Constants.DATE_FORMAT_YMD));
				syncUserInfo.setProfilePlace(empProfile.getProfilePlace()==null?"":empProfile.getProfilePlace());
				syncUserInfo.setGraduateDate(empProfile.getGraduateDate()==null?"":DateUtil.format(empProfile.getGraduateDate(),Constants.DATE_FORMAT_YMD));
				syncUserInfo.setHrmTitleDesc(empProfile.getHrmTitleDesc()==null?"":empProfile.getHrmTitleDesc());
				syncUserInfo.setHealthStatus(empProfile.getHealthStatus()==null?"":empProfile.getHealthStatus());
				syncUserInfo.setNewStaffTrain(empProfile.getNewStaffTrain()==null?"":empProfile.getNewStaffTrain());
				syncUserInfo.setApprovalTime(empProfile.getApprovalTime()==null?"":empProfile.getApprovalTime());
				syncUserInfo.setStatisticsTime(empProfile.getStatisticsTime()==null?"":empProfile.getStatisticsTime());
				syncUserInfo.setMasterCeremony(empProfile.getMasterCeremony()==null?"":empProfile.getMasterCeremony());
				syncUserInfo.setDicEduDegree(empProfile.getDicEduDegree()==null?"":empProfile.getDicEduDegree().getGlodMantisId());
				syncUserInfo.setDicWorkContractType(empProfile.getDicWorkContractType()==null?"":empProfile.getDicWorkContractType().getGlodMantisId());
				syncUserInfo.setDicMarriage(empProfile.getDicMarriage()==null?"":empProfile.getDicMarriage().getGlodMantisId());
				syncUserInfo.setDicParty(empProfile.getDicParty()==null?"":empProfile.getDicParty().getGlodMantisId());
				syncUserInfo.setDicNationality(empProfile.getDicNationality()==null?"":empProfile.getDicNationality().getGlodMantisId());
				syncUserInfo.setDicRace(empProfile.getDicRace()==null?"":empProfile.getDicRace().getGlodMantisId());
				syncUserInfo.setDicBloodType(empProfile.getDicBloodType()==null?"":empProfile.getDicBloodType().getGlodMantisId());
				syncUserInfo.setDicPlait(empProfile.getDicPlait()==null?"":empProfile.getDicPlait().getGlodMantisId());
				syncUserInfo.setDicProfessionType(empProfile.getDicProfessionType()==null?"":empProfile.getDicProfessionType().getGlodMantisId());
				syncUserInfo.setDicProfessionLevel(empProfile.getDicProfessionLevel()==null?"":empProfile.getDicProfessionLevel().getGlodMantisId());
				syncUserInfo.setDicHrmTitle(empProfile.getDicHrmTitle()==null?"":empProfile.getDicHrmTitle().getGlodMantisId());
				syncUserInfo.setDicHrmTitleLevel(empProfile.getDicHrmTitleLevel()==null?"":empProfile.getDicHrmTitleLevel().getGlodMantisId());
				syncUserInfo.setDicNewTypeCommitment(empProfile.getDicNewTypeCommitment()==null?"":empProfile.getDicNewTypeCommitment().getGlodMantisId());
				syncUserInfo.setDicProManagerLevel(empProfile.getDicProManagerLevel()==null?"":empProfile.getDicProManagerLevel().getGlodMantisId());
				syncUserInfo.setDicSocialSecurityPlace(empProfile.getDicSocialSecurityPlace()==null?"":empProfile.getDicSocialSecurityPlace().getGlodMantisId());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (Exception e ){
				logger.error("同步接口异常："+e.getMessage());
			}
			userList.add(syncUserInfo);
		}
		return userList;
	}
}
