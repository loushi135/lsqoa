/**
 * no cache使用说明： 该文件用于测试环境下，所有的js引入均加上了时间戳， 用于避免浏览器缓存这些文件而导致客户端浏览器显示出现问题
 * 使用时请修改文件名，去掉 _nocache，并将原App.impot.js文件改名或删除
 * 
 * PS：如果修改了其他资源文件，比如CSS、其他的JS 在引用的地方同样添加时间戳或者手工添加一个数字
 */
Ext.ns("App");
App.importJs = {
	AppRoleView : [__ctxPath + "/js/system/AppRoleView.js?" + __timestamp,
			__ctxPath + "/ext3/ux/CheckTreePanel.js?" + __timestamp,
			__ctxPath + "/js/system/RoleGrantRightView.js?" + __timestamp,
			__ctxPath + "/js/system/AppRoleForm.js?" + __timestamp],
	UserSubView : [__ctxPath + "/js/system/UserSubView.js?" + __timestamp,
			__ctxPath + "/js/system/UserSubForm.js?" + __timestamp],
	PersonalDocumentView : [
			__ctxPath + "/js/document/PersonalDocumentView.js?" + __timestamp,
			__ctxPath + "/js/document/DocumentView.js?" + __timestamp,
			__ctxPath + "/js/document/DocumentForm.js?" + __timestamp,
			__ctxPath + "/js/document/DocumentSharedForm.js?" + __timestamp,
			__ctxPath + "/js/document/DocFolderForm.js?" + __timestamp],
	DocumentSharedView : [
			__ctxPath + "/js/document/DocumentSharedView.js?" + __timestamp,
			__ctxPath + "/js/document/DocumentSharedDetail.js?" + __timestamp],
	DocFolderSharedView : [
			__ctxPath + "/js/document/FindPublicDocumentView.js?" + __timestamp,
			__ctxPath + "/js/document/DocFolderView.js?" + __timestamp,
			__ctxPath + "/js/document/DocFolderForm.js?" + __timestamp,
			__ctxPath + "/js/document/DocFolderSharedView.js?" + __timestamp,
			__ctxPath + "/js/document/DocFolderSharedForm.js?" + __timestamp,
			__ctxPath + "/js/document/DocPrivilegeForm.js?" + __timestamp,
			__ctxPath + "/js/document/DocPrivilegeView.js?" + __timestamp],
	FindPublicDocumentView : [
			__ctxPath + "/js/document/FindPublicDocumentView.js?" + __timestamp,
			__ctxPath + "/js/document/PublicDocumentView.js?" + __timestamp,
			__ctxPath + "/js/document/PublicDocumentDetail.js?" + __timestamp,
			__ctxPath + "/js/document/NewPublicDocumentForm.js?" + __timestamp,
			__ctxPath + "/js/document/DocFolderSelector.js?" + __timestamp],
	NewPublicDocumentForm : [
			__ctxPath + "/js/document/NewPublicDocumentForm.js?" + __timestamp,
			__ctxPath + "/js/document/DocFolderSelector.js?" + __timestamp],
	DocFolderMoveForm : [
			__ctxPath + "/js/document/DocFolderMoveForm.js?" + __timestamp,
			__ctxPath + "/js/document/PersonalDocFolderSelector.js?"
					+ __timestamp],
	NoticeView : [__ctxPath + "/js/info/NoticeView.js?" + __timestamp,
			__ctxPath + "/js/info/NoticeForm.js?" + __timestamp],
	ArticleSendView : [
			__ctxPath + "/js/info/ArticleSendView.js?" + __timestamp,
			__ctxPath + "/js/system/TreeTypePanel.js?" + __timestamp,
			__ctxPath + "/js/system/TreeTypeForm.js?" + __timestamp,
			__ctxPath + "/js/info/ArticleSendForm.js?" + __timestamp],
	ReportTemplateView : [
			__ctxPath + "/js/system/ReportTemplateView.js?" + __timestamp,
			__ctxPath + "/js/system/ReportTemplateForm.js?" + __timestamp,
			__ctxPath + "/js/system/ReportParamForm.js?" + __timestamp,
			__ctxPath + "/js/system/ReportParamView.js?" + __timestamp],
	MessageView : [__ctxPath + "/js/info/MessageView.js?" + __timestamp,
			__ctxPath + "/js/info/MessageForm.js?" + __timestamp,
			__ctxPath + "/js/info/MessageWin.js?" + __timestamp],
	MessageManageView : [
			__ctxPath + "/js/info/MessageManageView.js?" + __timestamp,
			__ctxPath + "/js/info/MessageForm.js?" + __timestamp],
	PhoneBookView : [
			__ctxPath + "/js/communicate/PhoneBookView.js?" + __timestamp,
			__ctxPath + "/js/communicate/PhoneGroupForm.js?" + __timestamp,
			__ctxPath + "/js/communicate/PhoneBookForm.js?" + __timestamp],
	DepartmentView : [
			__ctxPath + "/js/system/DepartmentView.js?" + __timestamp,
			__ctxPath + "/js/system/DepartmentForm.js?" + __timestamp,
			__ctxPath + "/js/system/AppUserView.js?" + __timestamp,
			__ctxPath + "/ext3/ux/ItemSelector.js?" + __timestamp,
			__ctxPath + "/ext3/ux/MultiSelect.js?" + __timestamp,
			__ctxPath + "/js/system/AppUserForm.js?" + __timestamp],
	AppUserView : [__ctxPath + "/js/system/AppUserView.js?" + __timestamp,
			__ctxPath + "/js/system/AppUserForm.js?" + __timestamp,
			__ctxPath + "/ext3/ux/ItemSelector.js?" + __timestamp,
			__ctxPath + "/ext3/ux/MultiSelect.js?" + __timestamp,
			__ctxPath + "/js/system/ResetPasswordForm.js?" + __timestamp],
	// AppShoppingGuideView : [ __ctxPath +
	// "/js/system/AppShoppingGuideView.js?"+__timestamp,
	// __ctxPath + "/js/system/AppShoppingGuideForm.js?"+__timestamp,
	// __ctxPath + "/ext3/ux/ItemSelector.js?"+__timestamp,
	// __ctxPath + "/ext3/ux/MultiSelect.js?"+__timestamp,
	// __ctxPath + "/js/system/ResetPasswordForm.js?"+__timestamp ],
	ProfileForm : [__ctxPath + "/js/system/ProfileForm.js?" + __timestamp,
			__ctxPath + "/js/system/ResetPasswordForm.js?" + __timestamp],
	NewsView : [__ctxPath + "/js/info/NewsView.js?" + __timestamp,
			__ctxPath + "/js/info/NewsForm.js?" + __timestamp,
			__ctxPath + "/js/info/NewsTypeTree.js?" + __timestamp,
			__ctxPath + "/js/info/NewsTypeForm.js?" + __timestamp],
	NewsTypeView : [__ctxPath + "/js/info/NewsTypeView.js?" + __timestamp,
			__ctxPath + "/js/info/NewsTypeForm.js?" + __timestamp],
	CompanyView : [__ctxPath + "/js/system/CompanyView.js?" + __timestamp],
	FileAttachView : [
			__ctxPath + "/js/system/FileAttachView.js?" + __timestamp,
			__ctxPath + "/js/system/FileAttachDetail.js?" + __timestamp],
	DiaryView : [__ctxPath + "/js/system/DiaryView.js?" + __timestamp,
			__ctxPath + "/js/system/DiaryForm.js?" + __timestamp],
	MySubUserDiaryView : [
			__ctxPath + "/js/system/MySubUserDiaryView.js?" + __timestamp,
			__ctxPath + "/js/system/DiaryDetail.js?" + __timestamp],
	// PersonalMailBoxView : [
	// __ctxPath + "/js/communicate/PersonalMailBoxView.js?"+__timestamp,
	// __ctxPath + "/js/communicate/MailView.js?"+__timestamp,
	// __ctxPath + "/js/communicate/MailForm.js?"+__timestamp,
	// __ctxPath + "/js/communicate/MailFolderForm.js?"+__timestamp,
	// __ctxPath + "/ext3/ux/RowExpander.js?"+__timestamp ],
	// MailForm : [ __ctxPath + "/js/communicate/MailForm.js?"+__timestamp ],
	PersonalPhoneBookView : [
			__ctxPath + "/js/communicate/PersonalPhoneBookView.js?"
					+ __timestamp,
			__ctxPath + "/js/communicate/PhoneBookView.js?" + __timestamp,
			__ctxPath + "/js/communicate/PhoneGroupForm.js?" + __timestamp,
			__ctxPath + "/js/communicate/PhoneBookForm.js?" + __timestamp],
	SharedPhoneBookView : [
			__ctxPath + "/js/communicate/SharedPhoneBookView.js?" + __timestamp,
			__ctxPath + "/js/communicate/SharedPhoneBookWin.js?" + __timestamp],
	FlowManagerView : [__ctxPath + "/js/flow/ProTypeForm.js?" + __timestamp,
			__ctxPath + "/js/flow/ProDefinitionForm.js?" + __timestamp,
			__ctxPath + "/js/flow/ProDefinitionView.js?" + __timestamp,
			__ctxPath + "/js/flow/FlowManagerView.js?" + __timestamp,
			__ctxPath + "/js/flow/ProDefinitionDetail.js?" + __timestamp,
			__ctxPath + "/js/flow/ProcessRunStart.js?" + __timestamp,
			__ctxPath + "/js/flow/ProDefinitionSetting.js?" + __timestamp,
			__ctxPath + "/js/flow/ProDefNoticeForm.js?"+__timestamp,
			__ctxPath + "/js/flow/MyTaskView.js?" + __timestamp,
			__ctxPath + "/js/flow/ProcessNextForm.js?" + __timestamp,
			__ctxPath + "/js/flow/FormDesignWindow.js?" + __timestamp,
			__ctxPath + "/js/flow/FormEditorWindow.js?" + __timestamp,
			__ctxPath + "/js/flowDesign/FlowDesignerWindow.js?" + __timestamp,
			__ctxPath + '/js/flow/TaskSignForm.js?' + __timestamp],
	NewProcess : [__ctxPath + "/js/flow/NewProcess.js?" + __timestamp,
			__ctxPath + "/js/flow/ProDefinitionDetail.js?" + __timestamp,
			__ctxPath + "/js/flow/ProDefinitionView.js?" + __timestamp,
			__ctxPath + "/js/flow/ProcessRunStart.js?" + __timestamp],
	ProcessRunView : [__ctxPath + "/js/flow/ProcessRunView.js?" + __timestamp,
			__ctxPath + "/js/flow/ProcessRunDetail.js?" + __timestamp,
			__ctxPath + "/js/flow/ProcessRunStart.js?" + __timestamp],
	ProcessAllView : [
			__ctxPath + '/js/flow/ProcessRunDetail.js?' + __timestamp,
			__ctxPath + '/js/flow/ProcessAllView.js?' + __timestamp],
	ProcessDelReasonView : [
			__ctxPath + '/js/flow/ProcessDelReasonForm.js?' + __timestamp,
			__ctxPath + '/js/flow/ProcessDelReasonView.js?' + __timestamp],
	MyTaskView : [__ctxPath + "/js/flow/MyTaskView.js?" + __timestamp,
			__ctxPath + "/js/flow/ChangeTaskView.js?" + __timestamp,
			__ctxPath + "/js/flow/ProcessNextForm.js?" + __timestamp,
			__ctxPath + "/js/flow/ProcessNextFormForYhoa.js?" + __timestamp,
			__ctxPath + "/js/flow/DoTaskModeForm.js?" + __timestamp],
	ProcessRunFinishView : [
			__ctxPath + "/js/flow/ProcessRunFinishView.js?" + __timestamp,
			__ctxPath + "/js/flow/ProcessRunDetail.js?" + __timestamp],
	StaffActiveapplyView : [
			__ctxPath + "/js/statistics/StaffActiveapplyView.js?" + __timestamp,
			__ctxPath + "/js/statistics/StaffActiveapplyForm.js?" + __timestamp],
	StaffLeaveapplyView : [
			__ctxPath + "/js/statistics/StaffLeaveapplyView.js?" + __timestamp,
			__ctxPath + "/js/statistics/StaffLeaveapplyForm.js?" + __timestamp],
	StaffRecruitapplyView : [
			__ctxPath + '/js/statistics/StaffRecruitapplyForm.js?'
					+ __timestamp,
			__ctxPath + '/js/statistics/StaffRecruitapplyView.js?'
					+ __timestamp],
	StaffEmployapplyView : [
			__ctxPath + '/js/statistics/StaffEmployapplyForm.js?' + __timestamp,
			__ctxPath + '/js/statistics/StaffEmployapplyView.js?' + __timestamp],
	StaffPromoteapplyView : [
			__ctxPath + '/js/statistics/StaffPromoteapplyForm.js?'
					+ __timestamp,
			__ctxPath + '/js/statistics/StaffPromoteapplyView.js?'
					+ __timestamp],
	StaffChangejobapplyView : [
			__ctxPath + '/js/statistics/StaffChangejobapplyForm.js?'
					+ __timestamp,
			__ctxPath + '/js/statistics/StaffChangejobapplyView.js?'
					+ __timestamp],
	OddEmployapplyView : [
			__ctxPath + '/js/statistics/OddEmployapplyForm.js?' + __timestamp,
			__ctxPath + '/js/statistics/OddEmployapplyView.js?' + __timestamp],
	SignApplyView : [
			__ctxPath + '/js/statistics/SignApplyForm.js?' + __timestamp,
			__ctxPath + '/js/statistics/SignApplyView.js?' + __timestamp],
	BankpayReportView : [
			__ctxPath + '/js/statistics/BankpayReportForm.js?' + __timestamp,
			__ctxPath + '/js/statistics/BankpayReportView.js?' + __timestamp],
	ProjectRelatedDataView : [
			__ctxPath + '/js/statistics/ProjectRelatedDataView.js?'+ __timestamp,
			__ctxPath + '/js/statistics/ProjectBankPayDetail.js?'+ __timestamp,
			__ctxPath + '/js/statistics/ProjectReceiveDetail.js?'+ __timestamp,
			__ctxPath + '/js/statistics/ProjectRePayDetail.js?'+ __timestamp,
			__ctxPath + '/js/statistics/BankpayReportForm.js?' + __timestamp],
	BookManageView : [__ctxPath + "/js/admin/BookManageView.js?" + __timestamp,
			__ctxPath + "/js/admin/BookView.js?" + __timestamp,
			__ctxPath + "/js/admin/BookForm.js?" + __timestamp,
			__ctxPath + "/js/admin/BookDelForm.js?" + __timestamp,
			__ctxPath + "/js/admin/BookTypeForm.js?" + __timestamp,
			__ctxPath + "/js/admin/BookBorrowForm.js?" + __timestamp,
			__ctxPath + "/js/admin/BookAmountForm.js?" + __timestamp],
	GoodApplyTotal : [__ctxPath + "/js/admin/GoodApplyTotal.js?" + __timestamp],
	BookTypeView : [__ctxPath + "/js/admin/BookTypeView.js?" + __timestamp,
			__ctxPath + "/js/admin/BookTypeForm.js?" + __timestamp],
	BookBorrowView : [__ctxPath + "/js/admin/BookBorrowView.js?" + __timestamp,
			__ctxPath + "/js/admin/BookBorrowForm.js?" + __timestamp,
			__ctxPath + "/js/admin/BookReturnForm.js?" + __timestamp],
	BookReturnView : [__ctxPath + "/js/admin/BookReturnView.js?" + __timestamp,
			__ctxPath + "/js/admin/BookReturnForm.js?" + __timestamp],
	OfficeGoodsManageView : [
			__ctxPath + "/js/admin/OfficeGoodsManageView.js?" + __timestamp,
			__ctxPath + "/js/admin/OfficeGoodsTypeForm.js?" + __timestamp,
			__ctxPath + "/js/admin/OfficeGoodsView.js?" + __timestamp,
			__ctxPath + "/js/admin/OfficeGoodsForm.js?" + __timestamp],
	// InStockView : [ __ctxPath + "/js/admin/InStockView.js?"+__timestamp,
	// __ctxPath + "/js/admin/InStockForm.js?"+__timestamp ],
	GoodsApplyView : [__ctxPath + "/js/admin/GoodsApplyView.js?" + __timestamp,
			__ctxPath + "/js/admin/GoodsApplyForm.js?" + __timestamp],
	// CarView : [ __ctxPath + "/js/admin/CarView.js?"+__timestamp,
	// __ctxPath + "/js/admin/CarForm.js?"+__timestamp ],
	// CartRepairView : [ __ctxPath +
	// "/js/admin/CartRepairView.js?"+__timestamp,
	// __ctxPath + "/js/admin/CartRepairForm.js?"+__timestamp ],
	// CarApplyView : [ __ctxPath + "/js/admin/CarApplyView.js?"+__timestamp,
	// __ctxPath + "/js/admin/CarApplyForm.js?"+__timestamp ],
	// AppointmentView : [ __ctxPath +
	// "/js/task/AppointmentView.js?"+__timestamp,
	// __ctxPath + "/js/task/AppointmentForm.js?"+__timestamp ],
	CalendarPlanView : [
			__ctxPath + "/js/task/CalendarPlanView.js?" + __timestamp,
			__ctxPath + "/js/task/CalendarPlanForm.js?" + __timestamp,
			__ctxPath + "/js/task/CalendarPlanFinishForm.js?" + __timestamp],
	// MyPlanTaskView : [ __ctxPath +
	// "/js/task/CalendarPlanView.js?"+__timestamp,
	// __ctxPath + "/js/task/CalendarPlanForm.js?"+__timestamp,
	// __ctxPath + "/js/task/CalendarPlanFinishForm.js?"+__timestamp,
	// __ctxPath + "/ext3/ux/caltask/e2cs_zh_CN.js?"+__timestamp,
	// __ctxPath + "/ext3/ux/caltask/calendar.gzjs",
	// __ctxPath + "/ext3/ux/caltask/scheduler.gzjs",
	// __ctxPath + "/ext3/ux/caltask/monthview.gzjs",
	// __ctxPath + "/ext3/ux/caltask/weekview.gzjs",
	// __ctxPath + "/ext3/ux/caltask/dayview.gzjs",
	// __ctxPath + "/ext3/ux/caltask/task.gzjs",
	// __ctxPath + "/js/task/MyPlanTaskView.gzjs",
	// __ctxPath + "/js/task/CalendarPlanDetailView.js?"+__timestamp ],
	// PlanTypeView : [ __ctxPath + "/js/task/PlanTypeView.js?"+__timestamp,
	// __ctxPath + "/js/task/PlanTypeForm.js?"+__timestamp ],
	// WorkPlanView : [ __ctxPath + "/js/task/WorkPlanView.js?"+__timestamp,
	// __ctxPath + "/js/task/NewWorkPlanForm.js?"+__timestamp ],
	// PersonalWorkPlanView : [ __ctxPath +
	// "/js/task/PersonalWorkPlanView.js?"+__timestamp,
	// __ctxPath + "/js/task/WorkPlanDetail.js?"+__timestamp ],
	// NewWorkPlanForm : [ __ctxPath +
	// "/js/task/NewWorkPlanForm.js?"+__timestamp ],
	// DepWorkPlanView : [ __ctxPath +
	// "/js/task/DepWorkPlanView.js?"+__timestamp,
	// __ctxPath + "/js/task/WorkPlanDetail.js?"+__timestamp ],
	CustomerNewView : [
			__ctxPath + "/js/customer/CustomerNewView.js?" + __timestamp,
			__ctxPath + "/js/customer/CustomerNewForm.js?" + __timestamp,
			__ctxPath + "/js/system/ProvinceForm.js?" + __timestamp,
			__ctxPath + "/js/system/CityForm.js?" + __timestamp],
	// CusLinkmanView : [ __ctxPath +
	// "/js/customer/CusLinkmanView.js?"+__timestamp,
	// __ctxPath + "/js/customer/CusLinkmanForm.js?"+__timestamp ],
	FixedAssetsManageView : [
			__ctxPath + "/js/admin/FixedAssetsManageView.js?" + __timestamp,
			__ctxPath + "/js/admin/FixedAssetsView.js?" + __timestamp,
			__ctxPath + "/js/admin/FixedAssetsForm.js?" + __timestamp,
			__ctxPath + "/js/admin/AssetsTypeForm.js?" + __timestamp,
			__ctxPath + "/js/admin/DepreWin.js?" + __timestamp,
			__ctxPath + "/js/admin/WorkGrossWin.js?" + __timestamp],
	AssetsApplyView : [
			__ctxPath + '/js/admin/AssetsApplyForm.js?' + __timestamp,
			__ctxPath + '/js/admin/AssetsApplyView.js?' + __timestamp],
	DepreTypeView : [__ctxPath + "/js/admin/DepreTypeForm.js?" + __timestamp,
			__ctxPath + "/js/admin/DepreTypeView.js?" + __timestamp],
	DepreRecordView : [
			__ctxPath + "/js/admin/DepreRecordForm.js?" + __timestamp,
			__ctxPath + "/js/admin/DepreRecordView.js?" + __timestamp],
	// CusConnectionView : [ __ctxPath +
	// "/js/customer/CusConnectionView.js?"+__timestamp,
	// __ctxPath + "/js/customer/CusConnectionForm.js?"+__timestamp ],
	// ProjectView : [ __ctxPath + "/js/customer/ProjectView.js?"+__timestamp,
	// __ctxPath + "/js/customer/ProjectForm.js?"+__timestamp,
	// __ctxPath + "/js/customer/ContractForm.js?"+__timestamp,
	// __ctxPath + "/js/customer/ContractConfigView.js?"+__timestamp ],
	// ContractView : [ __ctxPath + "/js/customer/ContractView.js?"+__timestamp,
	// __ctxPath + "/js/customer/ContractForm.js?"+__timestamp,
	// __ctxPath + "/js/customer/ContractConfigView.js?"+__timestamp ],
	// ProductView : [ __ctxPath + "/js/customer/ProductView.js?"+__timestamp,
	// __ctxPath + "/js/customer/ProductForm.js?"+__timestamp ],
	ProviderView : [__ctxPath + "/js/customer/ProviderView.js?" + __timestamp,
			__ctxPath + "/js/customer/ProviderForm.js?" + __timestamp,
			__ctxPath + "/js/customer/SendMailForm.js?" + __timestamp],
	// HolidayRecordView : [ __ctxPath +
	// "/js/personal/HolidayRecordView.js?"+__timestamp,
	// __ctxPath + "/js/personal/HolidayRecordForm.js?"+__timestamp ],
	// DutySectionView : [ __ctxPath +
	// "/js/personal/DutySectionView.js?"+__timestamp,
	// __ctxPath + "/js/personal/DutySectionForm.js?"+__timestamp ],
	// DutySystemView : [ __ctxPath +
	// "/js/personal/DutySystemView.js?"+__timestamp,
	// __ctxPath + "/js/personal/DutySystemForm.js?"+__timestamp,
	// __ctxPath + "/js/selector/DutySectionSelector.js?"+__timestamp ],
	// SignInOffView : [ __ctxPath +
	// "/js/personal/SignInOffView.js?"+__timestamp ],
	DutyRegisterPersonView : [__ctxPath
			+ "/js/personal/DutyRegisterPersonView.js?" + __timestamp],
	// DutyRegisterView : [ __ctxPath +
	// "/js/personal/DutyRegisterView.js?"+__timestamp,
	// __ctxPath + "/js/personal/DutyRegisterForm.js?"+__timestamp ],
	// ErrandsRegisterView : [ __ctxPath +
	// "/js/personal/ErrandsRegisterView.js?"+__timestamp,
	// __ctxPath + "/js/personal/ErrandsRegisterDetail.js?"+__timestamp,
	// __ctxPath + "/js/personal/ErrandsRegisterForm.js?"+__timestamp ],
	// ErrandsRegisterOutView : [
	// __ctxPath + "/js/personal/ErrandsRegisterOutView.js?"+__timestamp,
	// __ctxPath + "/js/personal/ErrandsRegisterOutForm.js?"+__timestamp ],
	SysConfigView : [__ctxPath + "/js/system/SysConfigView.js?" + __timestamp],
	NoticeDetail : [__ctxPath + "/js/info/NoticeDetail.js?" + __timestamp],
	ArticleSendDetail : [__ctxPath + "/js/info/ArticleSendDetail.js?"
			+ __timestamp],
	NewsDetail : [__ctxPath + "/js/info/NewsDetail.js?" + __timestamp],
	PublicDocumentDetail : [__ctxPath + "/js/document/PublicDocumentDetail.js?"
			+ __timestamp],
	MailDetail : [__ctxPath + "/js/communicate/MailDetail.js?" + __timestamp,
			__ctxPath + "/js/communicate/MailForm.js?" + __timestamp],
	CalendarPlanDetail : [__ctxPath + "/js/task/CalendarPlanDetail.js?"
			+ __timestamp],
	AppointmentDetail : [__ctxPath + "/js/task/AppointmentDetail.js?"
			+ __timestamp],
	SearchNews : [__ctxPath + "/js/search/SearchNews.js?" + __timestamp,
			__ctxPath + "/js/info/NewsDetail.js?" + __timestamp],
	SearchMail : [__ctxPath + "/js/search/SearchMail.js?" + __timestamp,
			__ctxPath + "/ext3/ux/RowExpander.js?" + __timestamp,
			__ctxPath + "/js/communicate/MailView.js?" + __timestamp,
			__ctxPath + "/js/communicate/MailForm.js?" + __timestamp],
	SearchNotice : [__ctxPath + "/js/search/SearchNotice.js?" + __timestamp],
	SearchArticleSend : [__ctxPath + "/js/search/SearchArticleSend.js?"
			+ __timestamp],
	SearchDocument : [
			__ctxPath + "/js/search/SearchDocument.js?" + __timestamp,
			__ctxPath + "/js/document/PublicDocumentDetail.js?" + __timestamp],
	// HireIssueView : [ __ctxPath + "/js/hrm/HireIssueView.js?"+__timestamp,
	// __ctxPath + "/js/hrm/HireIssueForm.js?"+__timestamp,
	// __ctxPath + "/js/hrm/HireIssueCheckWin.js?"+__timestamp ],
	// ResumeView : [ __ctxPath + "/js/hrm/ResumeView.js?"+__timestamp,
	// __ctxPath + "/js/hrm/ResumeForm.js?"+__timestamp ],
	NewsCommentView : [
			__ctxPath + "/js/info/NewsCommentView.js?" + __timestamp,
			__ctxPath + "/ext3/ux/RowExpander.js?" + __timestamp],
	DictionaryView : [
			__ctxPath + "/js/system/DictionaryView.js?" + __timestamp,
			__ctxPath + "/js/system/DictionaryForm.js?" + __timestamp],
	// SalaryItemView : [ __ctxPath + "/js/hrm/SalaryItemForm.js?"+__timestamp,
	// __ctxPath + "/js/hrm/SalaryItemView.js?"+__timestamp ],
	// StandSalaryForm : [ __ctxPath +
	// "/js/hrm/StandSalaryForm.js?"+__timestamp,
	// __ctxPath + "/js/hrm/StandSalaryItemView.js?"+__timestamp,
	// __ctxPath + "/js/selector/SalaryItemSelector.js?"+__timestamp ],
	// StandSalaryView : [ __ctxPath +
	// "/js/hrm/StandSalaryView.js?"+__timestamp,
	// __ctxPath + "/js/hrm/StandSalaryForm.js?"+__timestamp,
	// __ctxPath + "/js/hrm/StandSalaryItemView.js?"+__timestamp,
	// __ctxPath + "/js/hrm/CheckStandSalaryForm.js?"+__timestamp,
	// __ctxPath + "/js/hrm/CheckStandSalaryItemView.js?"+__timestamp,
	// __ctxPath + "/js/selector/SalaryItemSelector.js?"+__timestamp ],
	// JobChangeForm : [ __ctxPath + "/js/hrm/JobChangeForm.js?"+__timestamp,
	// __ctxPath + "/js/selector/EmpProfileSelector.js?"+__timestamp ],
	// JobChangeView : [ __ctxPath + "/js/hrm/JobChangeView.js?"+__timestamp,
	// __ctxPath + "/js/hrm/JobChangeForm.js?"+__timestamp,
	// __ctxPath + "/js/selector/EmpProfileSelector.js?"+__timestamp,
	// __ctxPath + "/js/hrm/CheckJobChangeWin.js?"+__timestamp ],
	JobView : [__ctxPath + "/js/hrm/JobView.js?" + __timestamp,
			__ctxPath + "/js/hrm/JobForm.js?" + __timestamp,
			__ctxPath + "/js/hrm/RecoveryJobWin.js?" + __timestamp],
	EmpProfileForm : [__ctxPath + "/js/hrm/EmpProfileForm.js?" + __timestamp],
	EmpProfileView : [__ctxPath + "/js/hrm/EmpProfileView.js?" + __timestamp,
			__ctxPath + "/js/hrm/EmpProfileForm.js?" + __timestamp,
			__ctxPath + "/js/hrm/CheckEmpProfileForm.js?" + __timestamp,
			__ctxPath + "/js/hrm/RecoveryProfileWin.js?" + __timestamp],
	// SalaryPayoffForm : [ __ctxPath +
	// "/js/hrm/SalaryPayoffForm.js?"+__timestamp,
	// __ctxPath + "/js/selector/EmpProfileSelector.js?"+__timestamp ],
	// SalaryPayoffView : [ __ctxPath +
	// "/js/hrm/SalaryPayoffForm.js?"+__timestamp,
	// __ctxPath + "/js/selector/EmpProfileSelector.js?"+__timestamp,
	// __ctxPath + "/js/hrm/CheckSalaryPayoffForm.js?"+__timestamp,
	// __ctxPath + "/js/hrm/SalaryPayoffView.js?"+__timestamp ],
	// ArchiveTypeTempView : [ __ctxPath +
	// "/js/archive/ArchiveTypeTempView.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesTypeForm.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchTemplateView.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchTemplateForm.js?"+__timestamp,
	// __ctxPath + "/js/archive/OfficeTemplateView.js?"+__timestamp ],
	// ArchivesDraftView : [ __ctxPath +
	// "/js/archive/ArchiveTypeTempView.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDraftView.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDocForm.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchTemplateView.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchTemplateSelector.js?"+__timestamp,
	// __ctxPath + "/js/archive/OfficeTemplateView.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDocHistoryWin.js?"+__timestamp ],
	// PersonalSalaryView : [ __ctxPath +
	// "/js/personal/PersonalSalaryView.js?"+__timestamp,
	// __ctxPath + "/ext3/ux/RowExpander.js?"+__timestamp ],
	// ArchRecTypeView : [ __ctxPath +
	// "/js/archive/ArchRecTypeView.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchRecTypeForm.js?"+__timestamp ],
	// ArchivesRecView : [ __ctxPath +
	// "/js/archive/ArchivesRecView.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesRecForm.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDetail.js?"+__timestamp ],
	// ArchivesHandleView : [ __ctxPath +
	// "/js/archive/ArchivesHandleView.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesHandleForm.js?"+__timestamp ],
	// LeaderReadView : [ __ctxPath +
	// "/js/archive/LeaderReadView.js?"+__timestamp,
	// __ctxPath + "/js/archive/LeaderReadForm.js?"+__timestamp ],
	// ArchDispatchView : [ __ctxPath +
	// "/js/archive/ArchDispatchView.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchDistributeForm.js?"+__timestamp ],
	// ArchUndertakeView : [ __ctxPath +
	// "/js/archive/ArchUndertakeView.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchUndertakeForm.js?"+__timestamp ],
	// ArchReadView : [ __ctxPath + "/js/archive/ArchReadView.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchReadForm.js?"+__timestamp ],
	// ArchivesRecCtrlView : [ __ctxPath +
	// "/js/archive/ArchivesRecCtrlView.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDetail.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchHastenForm.js?"+__timestamp ],
	// ArchivesDraftManage : [ __ctxPath +
	// "/js/archive/ArchivesDraftManage.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDraftView.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDraftWin.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchTemplateView.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchTemplateSelector.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDocForm.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDetailWin.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDocHistoryWin.js?"+__timestamp ],
	// ArchivesIssueSearch : [ __ctxPath +
	// "/js/archive/ArchivesIssueSearch.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDetailWin.js?"+__timestamp ],
	// ArchivesIssueProof : [ __ctxPath +
	// "/js/archive/ArchivesIssueProof.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDocWin.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDetailWin.js?"+__timestamp ],
	// ArchivesIssueAudit : [ __ctxPath +
	// "/js/archive/ArchivesIssueAudit.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDraftWin.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDetailWin.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDocHistoryWin.js?"+__timestamp ],
	// ArchivesIssueLead : [ __ctxPath +
	// "/js/archive/ArchivesIssueLead.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDraftWin.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDetailWin.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDocHistoryWin.js?"+__timestamp ],
	// ArchivesIssueCharge : [ __ctxPath +
	// "/js/archive/ArchivesIssueCharge.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDraftWin.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDetailWin.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDocHistoryWin.js?"+__timestamp ],
	// ArchivesDocument : [ __ctxPath +
	// "/js/archive/ArchivesDocument.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDetailWin.js?"+__timestamp ],
	// ArchivesIssueMonitor : [ __ctxPath +
	// "/js/archive/ArchivesIssueMonitor.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDetailWin.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchHastenForm.js?"+__timestamp ],
	// ArchivesIssueManage : [ __ctxPath +
	// "/js/archive/ArchivesIssueManage.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDetailWin.js?"+__timestamp ],
	// DocHistoryView : [ __ctxPath +
	// "/js/archive/DocHistoryView.js?"+__timestamp ],
	// ArchFlowConfView : [ __ctxPath +
	// "/js/archive/ArchFlowConfView.js?"+__timestamp,
	// __ctxPath + "/js/selector/FlowSelector.js?"+__timestamp ],
	// ArchivesSignView : [ __ctxPath +
	// "/js/archive/ArchivesSignView.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesRecForm.js?"+__timestamp,
	// __ctxPath + "/js/archive/ArchivesDetailWin.js?"+__timestamp ],
	// SystemLogView : [ __ctxPath + "/js/system/SystemLogView.js?"+__timestamp
	// ],
	MyProcessRunView : [
			__ctxPath + "/js/flow/MyProcessRunView.js?" + __timestamp,
			__ctxPath + "/js/flow/ProcessRunDetail.js?" + __timestamp,
			__ctxPath + "/js/flow/ProcessRunReStart.js?" + __timestamp],
	PersonalTipsView : [__ctxPath + "/js/info/PersonalTipsView.js?"
			+ __timestamp],
	// NameCardAddView : [ __ctxPath +
	// "/js/nameCard/NameCardAddView.js?"+__timestamp,
	// __ctxPath + "/js/nameCard/NameCardAddForm.js?"+__timestamp ],
	// NameCardDelView : [ __ctxPath +
	// "/js/nameCard/NameCardDelView.js?"+__timestamp,
	// __ctxPath + "/js/nameCard/NameCardDelForm.js?"+__timestamp ],
	MaterialContractView : [
			__ctxPath + "/js/statistics/MaterialContractView.js?" + __timestamp,
			__ctxPath + "/js/statistics/MaterialContractForm.js?" + __timestamp],
	// CommonReportView:[ __ctxPath +
	// "/js/statistics/CommonReportView.js?"+__timestamp,
	// __ctxPath + "/js/statistics/CommonReportForm.js?"+__timestamp
	// ],
	PaymentListView : [
			__ctxPath + "/js/statistics/PaymentListView.js?" + __timestamp,
			__ctxPath + "/js/statistics/PaymentListForm.js?" + __timestamp,
			__ctxPath + "/js/statistics/RepaymentForm.js?" + __timestamp,
			__ctxPath + "/js/statistics/RepaymentView.js?" + __timestamp],
	StaffEntryapplyView : [
			__ctxPath + "/js/statistics/StaffEntryapplyView.js?" + __timestamp,
			__ctxPath + "/js/statistics/StaffEntryapplyForm.js?" + __timestamp],
	ConstructioncontractView : [
			__ctxPath + "/js/statistics/ConstructioncontractView.js?"
					+ __timestamp,
			__ctxPath + "/js/statistics/ConstructioncontractForm.js?"
					+ __timestamp],
	DesignContractView : [
			__ctxPath + "/js/statistics/DesignContractView.js?" + __timestamp,
			__ctxPath + "/js/statistics/DesignContractForm.js?" + __timestamp],
	ProjectNewView : [
			__ctxPath + "/js/statistics/ProjectNewView.js?" + __timestamp,
			__ctxPath + "/js/statistics/ProjectNewImportUpdate.js?" + __timestamp,
			__ctxPath + "/js/statistics/ProjectNewForm.js?" + __timestamp],
	CompanyPhoneBookView : [
			__ctxPath + "/js/communicate/CompanyPhoneBookView.js?"
					+ __timestamp,
			__ctxPath + "/js/communicate/CompanyPhoneBookForm.js?"
					+ __timestamp],
	// PaybaseView:[
	// __ctxPath + "/js/statistics/PaybaseView.js?"+__timestamp,
	// __ctxPath + "/js/statistics/PaybaseForm.js?"+__timestamp
	// ],
	BankpayapplyotherView : [
			__ctxPath + "/js/statistics/BankpayapplyotherView.js?"
					+ __timestamp,
			__ctxPath + "/js/statistics/BankpayapplyotherForm.js?"
					+ __timestamp],
	ProjectBaseDataView : [
			__ctxPath + "/js/statistics/ProjectBaseDataView.js?" + __timestamp,
			__ctxPath + "/js/statistics/ProjectReceiveForm.js?" + __timestamp,
			__ctxPath + "/js/statistics/ProjectReceiveView.js?" + __timestamp,
			__ctxPath + "/js/statistics/ProjectPercentageForm.js?" + __timestamp,
			__ctxPath + "/js/statistics/ProjectPercentageView.js?" + __timestamp,
			__ctxPath + "/js/statistics/ProjectRepayForm.js?" + __timestamp,
			__ctxPath + "/js/statistics/ProjectRepayView.js?" + __timestamp,
			__ctxPath + "/js/statistics/ProjectQuotaForm.js?" + __timestamp,
			__ctxPath + "/js/statistics/ProjectQuotaView.js?" + __timestamp,
			__ctxPath + "/js/statistics/ProjectAuditForm.js?" + __timestamp,
			__ctxPath + "/js/statistics/BillForm.js?" + __timestamp,
			__ctxPath + "/js/statistics/BillView.js?" + __timestamp,
			__ctxPath + "/js/statistics/BillAdjustForm.js?" + __timestamp,
			__ctxPath + "/js/statistics/ProjectReceiveImport.js?" + __timestamp,
			__ctxPath + "/js/statistics/ProjectRepayImport.js?" + __timestamp,
			__ctxPath + "/js/statistics/PaybaseForm.js?" + __timestamp,
			__ctxPath + "/js/statistics/PaybaseView.js?" + __timestamp,
			__ctxPath + "/js/statistics/BankpayapplyView.js?" + __timestamp,
			__ctxPath + "/js/statistics/BankpayapplyUpdate.js?" + __timestamp,
			__ctxPath + "/js/statistics/BankpayapplyForm.js?" + __timestamp
			],
	MaterialBaseDataView : [
			__ctxPath + "/js/statistics/MaterialBaseDataView.js?" + __timestamp,
			__ctxPath + "/js/statistics/BillForm.js?" + __timestamp,
			__ctxPath + "/js/statistics/BillView.js?" + __timestamp],
	SuppliersAssessView : [
			__ctxPath + "/js/customer/SuppliersAssessForm.js?" + __timestamp,
			__ctxPath + "/js/customer/SuppliersAssessView.js?" + __timestamp,
			__ctxPath + "/js/system/ProvinceForm.js?" + __timestamp,
			__ctxPath + "/js/system/CityForm.js?" + __timestamp],
	PhotoDetail : [__ctxPath + '/js/hrm/PhotoDetail.js?' + __timestamp],
	PhotoFolderView : [__ctxPath + "/js/hrm/PhotoFolderView.js?" + __timestamp,
			__ctxPath + "/js/hrm/PhotoFolderForm.js?" + __timestamp,
			__ctxPath + "/js/hrm/PhotoUploadWin.js?" + __timestamp],
	BbsTopicView : [__ctxPath + "/js/bbs/BbsTopicView.js?" + __timestamp,
			__ctxPath + "/js/bbs/BbsTopicForm.js?" + __timestamp,
			__ctxPath + "/js/bbs/BbsGroupForm.js?" + __timestamp,
			__ctxPath + "/js/bbs/BbsReplyView.js?" + __timestamp,
			__ctxPath + "/js/bbs/BbsReplyForm.js?" + __timestamp,
			__ctxPath + "/js/bbs/BbsUserPropertyForm.js?" + __timestamp],

	BbsReplyView : [__ctxPath + "/js/bbs/BbsReplyView.js?" + __timestamp,
			__ctxPath + "/js/bbs/BbsReplyForm.js?" + __timestamp],
	MeetingRoomView : [
			__ctxPath + '/js/admin/MeetingRoomView.js?' + __timestamp,
			__ctxPath + '/js/admin/MeetingRoomForm.js?' + __timestamp,
			__ctxPath + '/js/admin/MeetingRoomShow.js?' + __timestamp],
	MeetingView : [__ctxPath + '/js/admin/MeetingView.js?' + __timestamp,
			__ctxPath + '/js/admin/MeetingForm.js?' + __timestamp],
	LeaveapplyView : [
			__ctxPath + '/js/personal/LeaveapplyForm.js?' + __timestamp,
			__ctxPath + '/js/personal/LeaveapplyView.js?' + __timestamp],
	LeaseHouseView : [
			__ctxPath + '/js/statistics/LeaseHouseForm.js?' + __timestamp,
			__ctxPath + '/js/statistics/LeaseHouseView.js?' + __timestamp],
	// PersonLoanView:[
	// __ctxPath+'/js/statistics/PersonLoanForm.js?'+__timestamp,
	// __ctxPath+'/js/statistics/PersonLoanView.js?'+__timestamp
	//    
	// ],
	CarSubsidyView : [
			__ctxPath + '/js/statistics/CarSubsidyForm.js?' + __timestamp,
			__ctxPath + '/js/statistics/CarSubsidyView.js?' + __timestamp],
	TicketApplyView : [
			__ctxPath + '/js/statistics/TicketApplyForm.js?' + __timestamp,
			__ctxPath + '/js/statistics/TicketApplyChangeForm.js?' + __timestamp,
			__ctxPath + '/js/statistics/TicketApplyView.js?' + __timestamp],
	AppUserRoleView : [
			__ctxPath + "/ext3/ux/ItemSelector.js?" + __timestamp,
			__ctxPath + "/ext3/ux/MultiSelect.js?" + __timestamp,
			__ctxPath + '/js/system/AppUserRoleView.js?' + __timestamp,
			__ctxPath + '/js/system/AppUserRoleForm.js?' + __timestamp],
	UserFlowconfigView : [
			__ctxPath + '/js/system/UserFlowconfigForm.js?' + __timestamp,
			__ctxPath + '/js/system/UserFlowconfigAddForm.js?' + __timestamp,
			__ctxPath + '/js/system/UserFlowconfigView.js?' + __timestamp],
	CardApplyView : [
			__ctxPath + '/js/statistics/CardApplyForm.js?' + __timestamp,
			__ctxPath + '/js/statistics/CardApplyView.js?' + __timestamp

	],
	SmsmessageView : [
			__ctxPath + '/js/system/SmsmessageView.js?' + __timestamp,
			__ctxPath + '/js/system/SmsmessageForm.js?' + __timestamp,
			__ctxPath + '/js/system/SmsGroupUserForm.js?' + __timestamp,
			__ctxPath + '/js/system/SmsGroupView.js?' + __timestamp,
			__ctxPath + '/js/system/SmsGroupForm.js?' + __timestamp],
	BookManageView : [__ctxPath + '/js/admin/BookAdd.js?' + __timestamp,
			__ctxPath + '/js/admin/BookManageView.js?' + __timestamp,
			__ctxPath + '/js/admin/BookView.js?' + __timestamp,
			__ctxPath + '/js/admin/BookForm.js?' + __timestamp,
			__ctxPath + '/js/admin/BookDelForm.js?' + __timestamp,
			__ctxPath + '/js/admin/BookTypeForm.js?' + __timestamp,
			__ctxPath + '/js/admin/BookBorrowForm.js?' + __timestamp,
			__ctxPath + '/js/admin/BookAmountForm.js?' + __timestamp,
			__ctxPath + "/js/flow/ProcessRunStart.js?" + __timestamp],
	BookTypeView : [__ctxPath + '/js/admin/BookTypeView.js?' + __timestamp,
			__ctxPath + '/js/admin/BookTypeForm.js?' + __timestamp],
	BookBorrowView : [__ctxPath + '/js/admin/BookBorrowView.js?' + __timestamp,
			__ctxPath + '/js/admin/BookBorrowForm.js?' + __timestamp,
			__ctxPath + '/js/admin/BookReturnForm.js?' + __timestamp,
			__ctxPath + '/js/admin/BookBorrowExport.js?' + __timestamp,
			__ctxPath + '/js/admin/BookBorrowConfirmWin.js?' + __timestamp],
	BookReturnView : [__ctxPath + '/js/admin/BookReturnView.js?' + __timestamp,
			__ctxPath + '/js/admin/BookReturnForm.js?' + __timestamp],
	BookBorrowForPublicView : [
			__ctxPath + '/js/admin/BookBorrowForPublicView.js?' + __timestamp,
			__ctxPath + '/js/admin/BookBorrowConfirmWin.js?' + __timestamp],
	ExpressApplyView : [
			__ctxPath + '/js/admin/ExpressApplyForm.js?' + __timestamp,
			__ctxPath + '/js/admin/ExpressApplyView.js?' + __timestamp],
	ExpressApplyExportView : [__ctxPath
			+ '/js/admin/ExpressApplyExportView.js?' + __timestamp],
	AnnounceView : [__ctxPath + '/js/system/AnnounceForm.js?' + __timestamp,
			__ctxPath + '/js/system/AnnounceView.js?' + __timestamp],
	UserLogView : [__ctxPath + '/js/system/UserLogForm.js?' + __timestamp,
			__ctxPath + '/js/system/UserLogView.js?' + __timestamp],
	TrainSurveyView : [__ctxPath + '/js/hrm/TrainSurveyView.js?' + __timestamp,
			__ctxPath + '/js/hrm/TrainSurveyVoteForm.js?' + __timestamp,
			__ctxPath + '/js/selector/CourseBoxSelector.js?' + __timestamp,
			__ctxPath + '/js/hrm/TrainSurveyForm.js?' + __timestamp],
	TrainApplyView : [__ctxPath + '/js/hrm/TrainApplyForm.js?' + __timestamp,
			__ctxPath + '/js/hrm/TrainPlanForm.js?' + __timestamp,
			__ctxPath + '/js/hrm/TrainApplyView.js?' + __timestamp],
	TrainCourseView : [__ctxPath + '/js/hrm/TrainCourseView.js?' + __timestamp,
			__ctxPath + '/js/hrm/TrainCourseForm.js?' + __timestamp,
			__ctxPath + '/js/hrm/TrainCourseImport.js?' + __timestamp],
	TrainPlanView : [__ctxPath + '/js/hrm/TrainPlanView.js?' + __timestamp,
			__ctxPath + '/js/hrm/TrainPlanSendMsgForm.js?' + __timestamp,
			__ctxPath + '/js/hrm/TrainReportForm.js?' + __timestamp,
			__ctxPath + '/js/hrm/TrainPlanForm.js?' + __timestamp,
			__ctxPath + '/js/selector/CourseBoxSelector.js?' + __timestamp,
			__ctxPath + '/js/hrm/TrainReportUserEditForm.js?' + __timestamp],
	TrainPlanShow : [__ctxPath + '/js/hrm/TrainPlanShow.js?' + __timestamp],
	TrainReportView : [__ctxPath + '/js/hrm/TrainReportForm.js?' + __timestamp,
			__ctxPath + '/js/hrm/TrainReportView.js?' + __timestamp,
			__ctxPath + '/js/hrm/TrainReportUserEditForm.js?' + __timestamp],
	FlowApplyView : [__ctxPath + '/js/statistics/FlowApplyView.js?' + __timestamp,
			__ctxPath + '/js/statistics/FlowApplyForm.js?' + __timestamp],
	TaskAgentView :	[__ctxPath+'/js/flow/TaskAgentView.js?' + __timestamp,
    		__ctxPath+'/js/flow/TaskAgentForm.js?' + __timestamp],
    TeamInStockView : [__ctxPath+'/js/statistics/TeamInStockForm.js?' + __timestamp,
    		__ctxPath+'/js/statistics/TeamInStockView.js?' + __timestamp],
    EndInnerCheckView : [__ctxPath+'/js/statistics/EndInnerCheckForm.js?' + __timestamp,
    		__ctxPath+'/js/statistics/EndInnerCheckView.js?' + __timestamp],
    ProjectEndCheckView : [__ctxPath+'/js/statistics/ProjectEndCheckForm.js?' + __timestamp,
    		__ctxPath+'/js/statistics/ProjectEndCheckView.js?' + __timestamp],
    HelmetsoverallsapplyView : [__ctxPath+'/js/statistics/HelmetsoverallsapplyForm.js?' + __timestamp,
    		__ctxPath+'/js/statistics/HelmetsoverallsapplyView.js?' + __timestamp],
    ProjectSealView : 	[__ctxPath+'/js/statistics/ProjectSealForm.js?' + __timestamp,
    		__ctxPath+'/js/statistics/ProjectSealView.js?' + __timestamp],
    ProjectSealRecycleView	: 	[__ctxPath+'/js/statistics/ProjectSealRecycleForm.js?' + __timestamp,
    		__ctxPath+'/js/statistics/ProjectSealRecycleView.js?' + __timestamp],
    PrequalificareportView	: 	[__ctxPath+'/js/statistics/PrequalificareportForm.js?' + __timestamp,
    		__ctxPath+'/js/statistics/PrequalificareportView.js?' + __timestamp],
    DesignProjectView	: 	[__ctxPath+'/js/statistics/DesignProjectForm.js?' + __timestamp,
    		__ctxPath+'/js/statistics/DesignProjectView.js?' + __timestamp],
    SalesProjectView	: 	[__ctxPath+'/js/statistics/SalesProjectForm.js?' + __timestamp,
    		__ctxPath+'/js/statistics/SalesProjectView.js?' + __timestamp],
    OtherProjectView	: 	[__ctxPath+'/js/statistics/OtherProjectForm.js?' + __timestamp,
    		__ctxPath+'/js/statistics/OtherProjectView.js?' + __timestamp],
    DesignProjectFinancialData	: 	[__ctxPath+'/js/statistics/DesignProjectFinancialData.js?' + __timestamp,
    								 __ctxPath+'/js/statistics/ProjectRepayImport.js?' + __timestamp,
    								 __ctxPath+'/js/statistics/DesignProjectRepayView.js?' + __timestamp,
    								 __ctxPath+'/js/statistics/DesignProjectRepayForm.js?' + __timestamp
    								],
    SalesProjectFinancialData	: 	[__ctxPath+'/js/statistics/SalesProjectFinancialData.js?' + __timestamp,
    								 __ctxPath+'/js/statistics/ProjectRepayImport.js?' + __timestamp,
    								 __ctxPath+'/js/statistics/SalesProjectRepayView.js?' + __timestamp,
    								 __ctxPath+'/js/statistics/SalesProjectRepayForm.js?' + __timestamp
    								],
    OtherFinancialData	: 	[__ctxPath+'/js/statistics/OtherFinancialData.js?' + __timestamp,
    						 __ctxPath+'/js/statistics/ProjectRepayImport.js?' + __timestamp,
							 __ctxPath+'/js/statistics/OtherProjectRepayView.js?' + __timestamp,
							 __ctxPath+'/js/statistics/OtherProjectRepayForm.js?' + __timestamp
    						],
    DruidMonitorView	: 	[__ctxPath+'/js/system/DruidMonitorView.js?' + __timestamp],
    ProjectReceiveExportView : [
    						 __ctxPath+'/js/admin/ProjectReceiveExportView.js?' + __timestamp
  							],
    BankPayApplyExportView : [
  							__ctxPath+'/js/statistics/BankPayApplyExportView.js?' + __timestamp
    						],
    ResetPwdView : [
  							__ctxPath+'/js/system/ResetPwdView.js?' + __timestamp,
  							__ctxPath+'/js/system/ResetPwdForm.js?' + __timestamp
   							],
    RosterView : [
  							__ctxPath+'/js/hrm/RosterView.js?' + __timestamp
   							],
   	PayAndBillDetailView : [
  							__ctxPath+'/js/statistics/PayAndBillDetailView.js?' + __timestamp
    						],						
   	FixedAssetsApplyeData	: [
  							__ctxPath+'/js/admin/FixedAssetsApplyeData.js?' + __timestamp
    						],	
   	LocalProductApplyView	: 	[__ctxPath+'/js/statistics/LocalProductApplyView.js?' + __timestamp,
    								 __ctxPath+'/js/statistics/LocalProductApplyForm.js?' + __timestamp
    								],
    OutTaxApplyView 	:	[__ctxPath+'/js/statistics/OutTaxApplyView.js?' + __timestamp,
    								 __ctxPath+'/js/statistics/OutTaxApplyForm.js?' + __timestamp
    								]						
};

