package com.xpsoft.core;

import java.util.HashMap;
import java.util.Map;

import com.xpsoft.core.model.ProcessProperty;

public class Constants {
	public static final Short FLAG_DISABLE = 0;

	public static final Short FLAG_ACTIVATION = 1;
	
	public static final Short FLAG_GOLDMANTIS = 3;//集团用户

	public static final Short FLAG_DELETED = 1;

	public static final Short FLAG_UNDELETED = 0;
	public static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
	public static final String FLOW_START_USER = "flowStartUser";
	public static final String FLOW_ASSIGN_ID = "flowAssignId";
	public static final String FLOW_SIGN_USERIDS = "signUserIds";
	public static final String FLOW_START_ID = "__start";
	public static final String FLOW_SUPER_ID = "__super";
	public static final String FLOW_LEAVE_KEY = "-requestHoliday";
	public static final String FLOW_NEXT_TRANS = "nextTrans";
	
	/**
	 * 流程发短信，指定某个部门
	 */
	public static final String FLOW_DEPT="newDept";
	public static final String ARCHIES_ARCHIVESID = "archives.archivesId";
	public static final String COMPANY_LOGO = "app.logoPath";
	public static final String DEFAULT_LOGO = "/images/yh-logo.png";
	public static final String COMPANY_NAME = "app.companyName";
	public static final String DEFAULT_COMPANYNAME = "苏州朗捷通智能科技有限公司";

	public static final Short FLAG_PASS = 1;
	public static final Short FLAG_NOTPASS = 2;

	public static final Short ENABLED = 1;

	public static final Short UNENABLED = 0;
	
	
	/**
	 * 短信方式操作（该方式亦可在OA系统中操作） 
	 */
	public static final Short PRO_BYMOBILEMSG=1;
	/**
	 * 代办操作（指定的代办人进行操作，但是原办理人无法进行操作）
	 */
	public static final Short PRO_SUBSTITUTE=2;
	
	/**
	 * 流程类型包含属性：银行付款申请(工程)
	 */
	public static final String PROCESS_BANKPAYAPPLY="银行付款申请(工程)_processNameCH,项目名称_bpaProjectName,项目编号_bpaProjectNo,付款类别_bpaPayType,收款单位_bpaReceiptDept,收款事由_bpaReceiptReason,合同/结算金额_bpaContract,累计已付款_bpaSumMoney,已付款比例(%)_bpaSumMoneyRatio,资金结余_bpaFundBalance,垫资审批额度_bpaPayRatio,发票结余_bpaInvoiceBalance,本次申请用款(小写)_bpaApplyMoneyXX,本次申请用款(大写)_bpaApplyMoneyDX,经办人员（备注）_bpaRemark";

	/**
	 * 通用报告属性
	 */
	public static final String PROCESS_COMMONREPORT="通用报告_processNameCH,报告人_reporter,所属部门_deptName,类型_state,所属公司_company,抄送部门_sendDept,报告内容_content";
	/**
	 * 离职申请
	 */
	public static final String PROCESS_STAFFLEAVEAPPLY="离职申请_processNameCH,所属公司_companyName,姓名_applyName,性别_sex,部门_deptName,职位_workPosition,进公司日期_comeInDate,离职原因_leaveReason,离职申请日期_applyDate,正式离职日期_leaveDate,员工层级_staffType";
	/**
	 * 流程类型包含属性：银行付款申请(其他)
	 */
	public static final String PROCESS_BANKPAYAPPLYOTHER="银行付款申请(其他)_processNameCH,项目名称_bpaProjectNameOT,项目编号_bpaProjectNoOT,付款类别_bpaPayTypeOT,收款单位_bpaReceiptDeptOT,收款事由_bpaReceiptReasonOT,合同/结算金额_bpaContractOT,累计已付款_bpaSumMoneyOT,已付款比例(%)_bpaSumMoneyRatioOT,发票结余_bpaInvoiceBalanceOT,本次申请用款(小写)_bpaApplyMoneyOTXX,本次申请用款(大写)_bpaApplyMoneyOTDX,经办人员_bpaRemarkOT";
	/**
	 * 流程类型包含属性：材料分包合同
	 */
	public static final String PROCESS_CONTRACTLETTINGAPPLY="材料发包合同申请_processNameCH,合同编号_contractNo,项目编号_proNo,项目名称_proName,申请人/经办人_applyer,申请部门_dept,合同总价(数字)_contractAmount,合同总价(中文)_chValue,数量/面积_areanum,合同类型_mainItem,系统类别_sysItem,签约单位_xydw,联系人及电话_tel,预付款_yfk,进度款_jdk,完工款_wgk,结算款_jsk,质保金_zbj,进度款收取时间_jdkReceive,合同陈述_usefull";
	/**
	 * 流程类型包含属性：开工备案书
	 */
	public static final String PROCESS_PROJECT="开工备案书_processNameCH,项目名称_proName,项目编号_proNo,工程地点_proAddr,所属区域_area,项目负责人_proCharger,项目负责人电话_proChargerTel,跟踪预算员_proFollower,预算员电话_proFollowerTel,设计单位_designUnit,设计单位负责人_designCharger,设计单位负责人电话_designChargerTel,建设单位_buildUnit,现场负责人_buildCharger,现场负责人电话_buildChargerTel,监理单位_watchUnit,监理单位负责人_watchCharger,监理单位负责人电话_watchChargerTel,总包单位_totalUnit,总包单位负责人_totalCharger,总包单位负责人电话_totalChargerTel,开工日期_startDate,竣工日期_endDate,业务主办_businessMain,进场日期_enterDate,项目经理_manager";
	/**
	 * 流程类型包含属性：设计合同评审表
	 */
	public static final String PROCESS_DESIGNCONTRACT="设计合同评审表_processNameCH,合同编号_contractNo,甲方单位名称_companyName,设计部门_designDept,甲方单位性质_companyProperty,有无合作_cooperation,合作次数_cooperationNum,甲方资信评价_companyCredit,项目名称_projectName,项目地点_projectAddr,合同总价_contractAmount,设计面积_designArea,单价（元/㎡）_singlePrice,工程造价_projectPrice,取费率_chargeRate,是否承接后期施工_isEndWork,施工跟进区域_workArea,有无因承接后期施工而让利设计费情况_isLetDesignFee";
	/**
	 * 员工转正
	 */
	public static final String PROCESS_STAFFACTIVE="员工转正_processNameCH,姓名_applyName,公司/部门_deptName,职位_workPosition,考核项目_examProject,工作成果_workAchieve,工作效率_workEfficiency,团队精神_teamSpirit,业务水平_businessLevel,成本意识_consciousness,创新能力_innovationAbility,发展潜力_developAbility,工作态度_workAttitude,品德言行_character,工资方式选择_salaryOption,考核期满后月薪_salaryMonthA,按业绩考核定_salaryBusinessA,合同方式选择_contractOption,合同年限选择_contractTime,转正后月薪_salaryMonthB,基本年薪_salaryBusinessB,转正评估表_staffActiveAttachFiles";
	/**
	 * 暂支单
	 */
	public static final String PROCESS_PAYMENTLIST="暂支单_processNameCH,项目名称_proName,项目编号_proNo,部门名称_deptName,暂支类别_paymentType,目前欠款金额_owedSum,本次借款金额大写_paymentSumBig,本次借款金额小写_paymentSumSmall,预计归还时间_preNowReturnTime,借款人_borrower";
	

	/**
	 * 流程类型包含属性：施工合同评审
	 */
	public static final String PROCESS_CONSTRUCTIONCONTRACTS="施工合同评审_processNameCH,合同编号_contractNo,发包人_contractor,项目名称_projectName,项目简称_projectAbbreviation,发包联系人及电话_linkmanAndTel,施工区域_projectRegional,项目实际负责人_projectCharger,业务主办_businessCharger,数量/面积_numOrArea,合同总价_sumPrice,付款方式_payWay,工期要求/能否满足/奖罚_projectTime,质量/奖罚_quality,是否双包_isFullContract,是否承担设计费_isDesignCost,设计费金额_designCost,是否评文明工地_isModelCommunity,奖罚_rewardOrPunish,保修期/金额_guarantee,是否办理施工备案_constructionBackUp,备案办理责任人_constructionBackUpPerson,备案具体完成时间_constructionBackUpFinishTime,是否办理施工许可证_constructionLicense,许可办理责任人_constructionLicensePerson,许可具体完成时间_constructionLicenseFinishTime,是否存在（无）超资质施工的情况_isoverBudget,报价_quote,预估亏损额_quoteloss,其他应在签定前商谈的事项_remark,应在合同履行过程中注意的事项_meno";
	
	
	/**
	 * 流程类型包含属性：请假申请
	 */
	public static final String PROCESS_QINGJIA="请假申请_processNameCH,申请日期_applyTime,用户名称_userName,部门名称_deptName,假期类型_type,开始日期_startTime,开始日期上下午_startAmOrPm,结束日期_endTime,结束日期上下午_endAmOrPm,请假天数_totalDays,申请理由_other";
	/**
	 * 流程类型包含属性：车辆补贴申请
	 */
	public static final String PROCESS_CARSUBSIDY="车辆补贴申请_processNameCH,报告人_carReporter,所属部门_carDeptName,所在公司_companyName,车牌号_carNo,品牌型号_brand,排量_displacement,购置年份_boughtYear,申请原因_subsidyReason";
	/**
	 * 流程类型包含属性：资产请购申请
	 */
	public static final String PROCESS_ASSETSAPPLY="资产请购申请_processNameCH,姓名_name,申请部门_dept,预估价格_prePrice,请购类型_applyType,申请日期_applyDate,请购说明_applyDescription,资产列表_resultGridData";
	/**
	 * 流程类型包含属性:办公用品申请
	 */
	public static final String PROCESS_OFFICEGOODSAPPLY="办公用品申请_processNameCH,申请人_name,申请部门_dept,申请日期_applyDate,请购类型_applyGoodType,总价格_totalPrice,申请说明_applyDescription,领用物品列表_goodresultGridData,使用类型_applyGoodUseType,项目名称_applyGoodProName";
	/**
	 * 流程类型包含属性:会议室申请
	 */
	public static final String PROCESS_MEETING="会议室申请_processNameCH,申请部门_name,申请部门_department,申请人_applyUser,开始时间_startTime,结束时间_endTime,会议主题_meetingTitle,人数_personNum,会议类型_meetingType,涉会需求_meetingRequire";
	/**
	 * 流程类型包含属性:票务申请
	 */
	public static final String PROCESS_TICKETAPPLY="票务申请_processNameCH,报告人_reporter,所属部门_deptName,所在公司_company,出行人员_bookUsers,需要申请票数_ticketNum,出差类型_businessType,项目_projectName,出发地_departure,目的地_destination,票务类型_ticketType,出发时间_departureTime,预订返程票_backOrNot,返程时间_backTime,申请原因_applyReason";
	/**
	* 流程类型包含属性:租房申请
	*/
	public static final String PROCESS_LEASEHOUSE="租房申请_processNameCH,报告人_reporter,所属部门_reporterDepatment,类别_sort,所在公司_reporterCompanyName,项目名称_itemName,租期开始日期_leaseStart,租期结束日期_leaseEnd,租期时长_leaseLen,月租金_monthlyRent,租金总额_yearRent,申请原因_cause";
	/**
	* 流程类型包含属性:名片申请
	*/
	public static final String PROCESS_CARDAPPLY="名片申请_processNameCH,姓名_cardProposer,电话_phone,公司_companyName,传真_fax,手机_mobile,职位_position,邮编_postalCode,邮箱_Email,其他_other,数量_amount,类型_type,高印类型_isType";	
	/**
	* 流程类型包含属性:档案借阅申请
	*/
	public static final String PROCESS_BOOKBORROW="档案借阅申请_processNameCH,申请人_borrower,申请部门_borrowerDept,档案编号_isbn,档案名称_bookName,档案类别_bookType,存放位置_location,在库数量_leftAmount,借阅数量_amount,归还时间_returnTime,备注_remark";	
	/**
	* 流程类型包含属性:快递申请
	*/
	public static final String PROCESS_EXPRESSAPPLY="快递申请_processNameCH,申请人_applyer,申请日期_applyDate,省份_province,城市_city,快递属性_expressTypeText,目的城市_cityAprovince,目的单位_toWhereName,分摊对象_dispatchType,部门_expressApplyDeptName,项目_expressApplyProName,快递公司_expressName,快递单号_expressNo";	
	
	
	/**
	* 流程类型包含属性:转岗申请
	*/
	public static final String PROCESS_STAFF_CHANGEJOB_APPLY="转岗申请_processNameCH,公司_staffChangejobapply@companyName,员工姓名_staffChangejobapply@changeJobUserName,原部门_staffChangejobapply@oldDepTreeSelector,原任职位_staffChangejobapply@oldJobName,新部门_staffChangejobapply@newDepTreeSelector,新任职位_staffChangejobapply@newJobName,转岗日期_staffChangejobapply@changeJobDate,转岗原因_staffChangejobapply@changeJobReason";	
	/**
	* 流程类型包含属性:入职申请
	*/
	public static final String PROCESS_STAFF_ENTRY_APPLY="入职申请_processNameCH,入职公司_staffEntryapply@companyName,入职部门_staffEntryapply@depTreeSelector,姓名_staffEntryapply@entryUserName,担任岗位_staffEntryapply@jobName,联系方式_staffEntryapply@contact,拟报到日期_staffEntryapply@entryDate";	
	/**
	* 流程类型包含属性:录用申请
	*/
	public static final String PROCESS_STAFF_EMPLOY_APPLY="录用申请_processNameCH,员工姓名_staffEmployapply@staffName,部门_innerFlowDeptName,岗位_staffEmployapply@jobName,招聘渠道_staffEmployapply@inviteWay,途径_staffEmployapply@publicWay,推荐人_inviteUserName";
	/* 流程类型包含属性:招聘申请
	*/
	public static final String PROCESS_STAFF_RECRUIT_APPLY="招聘申请_processNameCH,申请公司_staffRecruitapply@companyName,部门_staffRecruitapply@depTreeSelector,空缺职位名称_staffRecruitapply@jobName,该职位目前人数_staffRecruitapply@currentNum,此次申请人数_staffRecruitapply@applyNum,申请理由_staffRecruitapply@applyReason,年龄_staffRecruitapply@age,性别_staffRecruitapply@sex,专业_staffRecruitapply@majorDic@itemValue,学历_staffRecruitapply@educationDic@itemValue,工作年限_staffRecruitapply@workYear,该职位的主要职责_staffRecruitapply@positionDuty,技能要求_staffRecruitapply@skillRequirement,工作经历_staffRecruitapply@workExperience,个性_staffRecruitapply@personality,主要工作地点_staffRecruitapply@mainPositionNames,其他要求_staffRecruitapply@otherRequirement,到岗期限_staffRecruitapply@deadline,人员性质_staffRecruitapply@personalCharacter";	
	/**
	* 流程类型包含属性:晋升申请
	*/
	public static final String PROCESS_STAFF_PROMOTE_APPLY="晋升申请_processNameCH,员工姓名_staffPromoteapply@promoteUserName,原部门_staffPromoteapply@oldDepTreeSelector,原职位_staffPromoteapply@oldJobName,拟晋升部门_staffPromoteapply@newDepTreeSelector,拟晋升职位_staffPromoteapply@newJobName,生效日期_staffPromoteapply@activeDate,晋升理由_staffPromoteapply@promoteReason,个人优点和需改进的方面_staffPromoteapply@advantageOrChange,专业能力_staffPromoteapply@professional,综合能力_staffPromoteapply@mixedAbility";	
	/**
	 * 流程类型包含属性：零星用工申请
	 */
	public static final String PROCESS_ODDEMPLOY_APPLY="零星用工申请_processNameCH,项目名称_oddEmployapply.proName,合同编号_oddEmployapply.contract.contractNo,类别_oddEmployapply.employType,施工单位_oddEmployapply.workUnit,人数_oddEmployapply.num,姓名/联系方式_oddEmployapply.contact,用工时间_oddEmployapply.employTime,用工费用_oddEmployapply.employFee,用工费用大写_employFeeBig,用工原因_oddEmployapply.employReason,用工内容_oddEmployapply.employContent";
	/**
	 * 流程类型包含属性：签证变更申请
	 */
	public static final String PROCESS_SIGNAPPLY="签证变更申请_processNameCH,项目名称_signApply.proName,签证编号_signApply.signNo,施工单位_signApply.workUnit,合同编号_signApply.contract.contractNo,承担费用单位_signApply.feeSupplierName,签证原因_signApply.signReason,签证内容_signApply.signContent,备注说明_signApply.remark";
	
	/**
	 * 在ERP中的OA系统标识，表明该请求是由朗捷通OA发出
	 */
	public static final String INTERGRATION_LJTOA_NAME = "LJTOA";
	/**
	 * ERP接口URL
	 */
	//正式环境
	public static final String INTERGRATION_WEBSERVICE_URL = "http://172.16.0.190:9052/WaitHandleWCFService";
	//测试环境
	//public static final String INTERGRATION_WEBSERVICE_URL = "http://172.16.55.82:9052/WaitHandleWCFService";
	
	public static Map<String,ProcessProperty> getProcessType(){
		Map<String,ProcessProperty> map=new HashMap<String, ProcessProperty>();
		
		map.put("bankPayApply", new ProcessProperty(PROCESS_BANKPAYAPPLY, "0"));//银行（工程）
		map.put("commonReport", new ProcessProperty(PROCESS_COMMONREPORT, "1"));//通用报告
		map.put("contractLettingApply", new ProcessProperty(PROCESS_CONTRACTLETTINGAPPLY, "0"));//材料发包
		map.put("bankPayApplyOther", new ProcessProperty(PROCESS_BANKPAYAPPLYOTHER, "1"));//银行付款申请(其他)
		map.put("project", new ProcessProperty(PROCESS_PROJECT, "1"));//开工备案书
		map.put("designContract", new ProcessProperty(PROCESS_DESIGNCONTRACT, "1"));//设计合同评审表
		map.put("constructionContracts", new ProcessProperty(PROCESS_CONSTRUCTIONCONTRACTS, "0"));//施工合同评审
		map.put("staffEntryApply", new ProcessProperty(PROCESS_STAFF_ENTRY_APPLY, "1"));//员工入职
		map.put("staffChangeJobApply", new ProcessProperty(PROCESS_STAFF_CHANGEJOB_APPLY, "1"));//转岗申请
		map.put("staffEmployApply", new ProcessProperty(PROCESS_STAFF_EMPLOY_APPLY, "1"));//录用申请
		map.put("staffPromoteApply", new ProcessProperty(PROCESS_STAFF_PROMOTE_APPLY, "1"));//晋升申请
		map.put("staffRecruitApply", new ProcessProperty(PROCESS_STAFF_RECRUIT_APPLY, "1"));//招聘申请
		map.put("staffLeaveApply", new ProcessProperty(PROCESS_STAFFLEAVEAPPLY, "1"));//离职申请
		map.put("staffActive", new ProcessProperty(PROCESS_STAFFACTIVE, "1"));//员工转正
		map.put("payment", new ProcessProperty(PROCESS_PAYMENTLIST, "1"));//暂支单
		map.put("qingjia", new ProcessProperty(PROCESS_QINGJIA, "1"));//请假申请
		map.put("CarSubsidy", new ProcessProperty(PROCESS_CARSUBSIDY, "1"));//车辆补贴申请
		map.put("assetsApply", new ProcessProperty(PROCESS_ASSETSAPPLY, "1"));//资产请购申请
		map.put("officeGoodsApply", new ProcessProperty(PROCESS_OFFICEGOODSAPPLY, "1"));//办公用品申请
		map.put("meeting", new ProcessProperty(PROCESS_MEETING, "0"));//会议室申请
		map.put("ticketApply", new ProcessProperty(PROCESS_TICKETAPPLY, "1"));//票务
		map.put("leaseHouse", new ProcessProperty(PROCESS_LEASEHOUSE, "1"));//租房
		map.put("CardApply", new ProcessProperty(PROCESS_CARDAPPLY, "1"));//名片申请
		map.put("bookBorrow", new ProcessProperty(PROCESS_BOOKBORROW, "1"));//档案申请
		map.put("expressApply", new ProcessProperty(PROCESS_EXPRESSAPPLY, "1"));//快递申请
		map.put("oddEmployapply", new ProcessProperty(PROCESS_ODDEMPLOY_APPLY, "1"));//零星用工申请
		map.put("signApply", new ProcessProperty(PROCESS_SIGNAPPLY, "1"));//签证变更申请
		return map;
	}
}
