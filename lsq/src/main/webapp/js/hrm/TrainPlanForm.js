/**
 * @author
 * @createtime
 * @class TrainPlanForm
 * @extends Ext.Window
 * @description TrainPlan表单
 */
TrainPlanForm = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		TrainPlanForm.superclass.constructor.call(this, {
			id : 'TrainPlanFormWin',
			layout : 'fit',
			items : this.formPanel,
			modal : true,
			height : 370,
			width : 400,
			maximizable : true,
			title : '[培训计划]详细信息',
			buttonAlign : 'center',
			buttons : this.buttons
		});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			trackResetOnLoad : true,
			bodyStyle : 'padding:10px 10px 10px 10px',
			border : false,
			url : __ctxPath + '/hrm/saveTrainPlan.do',
			id : 'TrainPlanForm',
			defaults : {
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items : [{
						name : 'trainPlan.id',
						id : 'id',
						xtype : 'hidden',
						value : this.id == null ? '' : this.id
					}, {
						xtype : 'container',
						layout : 'form',
						style : 'margin-top:4px',
						items : [{
									xtype : 'hidden',
									name : 'trainPlan.trainCourse.courseId',
									id : 'trainCourseId'
								}, {
									xtype : 'hidden',
									// name : 'trainPlan.trainCourse.courseNo',
									id : 'trainCourseNo'
								}, {
									fieldLabel : '课程名称',
									xtype : 'textfield',
									width : 240,
									allowBlank : false,
									// name :
									// 'trainPlan.trainCourse.courseName',
									id : 'trainCourseName',
									listeners : {
										focus : function() {
											CourseBoxSelector.getView(function(courseId, courseNo, courseName) {
												Ext.getCmp("trainCourseName").setValue(courseName);
												Ext.getCmp("trainCourseId").setValue(courseId);
											}, true).show();
										}
									}
								}]
					}, {
						xtype : 'container',
						layout : 'form',
						style : 'margin-top:4px',
						defaults : {
						anchor : '98%,98%'
					},
						items : [{
									fieldLabel : '计划编号',
									name : 'trainPlan.sn',
									xtype : 'hidden',
									width : 240,
									id : 'trainPlanSn'
								},{
									fieldLabel : '培训地点',
									name : 'trainPlan.trainPlanPlace',
									xtype : 'textfield',
									allowBlank : false,
									width : 240,
									id : 'trainPlanPlace'
								
								}, {
									fieldLabel : '培训时间',
									name : 'trainPlan.trainTime',
									xtype : 'datetimefield',
									format : 'Y-m-d H:i:s',
									width : 240,
									id : 'trainTime'
								}, {
									fieldLabel : '培训状态',
									// width:240,
									xtype : 'hidden',
									id : 'trainStatus',
									name : 'trainPlan.trainStatus'
								}, {
									fieldLabel : '培训类型',
									width : 240,
									xtype : 'combo',
									hiddenName : 'trainPlan.trainType',
		                        	editable: false,
									style : 'padding-right:0px',
									triggerAction : 'all',
									allowBlank : false,
									emptyText : '---请选择---',
									store : [['年度计划内', '年度计划内'], ['年度计划外', '年度计划外']],
									id : 'trainType'
								},{
									fieldLabel : '培训对象类型',
									width : 240,
									xtype : 'combo',
									hiddenName : 'trainPlan.trainTarget',
									readOnly : true,
									allowBlank : false,
									style : 'padding-right:0px',
									triggerAction : 'all',
									store : [['部门培训','部门培训'],['公司培训','公司培训']],
									id : 'trainTarget',
									value : '公司培训',
									listeners : {
										'select' : function(obj,record,index){
											if(obj.value == '部门培训'){
												Ext.getCmp('trainType').setDisabled(true);
												Ext.getCmp('trainType').setVisible(false);
											}else if(obj.value == '公司培训'){
												Ext.getCmp('trainType').setDisabled(false);
												Ext.getCmp('trainType').setVisible(true);
											}				
										}
									}
								}, {
									fieldLabel : '总人数',
									width : 240,
									maxLength : 9,
									xtype : 'numberfield',
									name : 'trainPlan.sumNum',
									id : 'sumNum'
								},{
									fieldLabel : '参与人员（非必选）',
									hidden : !Ext.isEmpty(this.id),
									xtype : 'textarea',
									id : 'userName',
									listeners : {
										focus : function(){
											UserSelector.getView(function(userIds, fullnames){
												Ext.getCmp("userName").setValue(fullnames);
												Ext.getCmp("userId").setValue(userIds);
											},false).show();
										}
									}
								},{
									fieldLabel : '参与人员（非必选）',
									hidden : true,
									name : 'userId',
									xtype : 'textarea',
									id : 'userId'
								},{
									fieldLabel : '是否发送消息',
									xtype : 'checkbox',
									name : 'sendMsgFlag'
								}]
					}, {
						xtype : 'container',
						layout : 'form',
						hidden : true,
						id : 'cancelReasonForm',
						style : 'margin-top:4px',
						items : [{
									width : 240,
									fieldLabel : '取消原因',
									maxLength : 9,
									xtype : 'textarea',
									name : 'trainPlan.cancelReason',
									id : 'cancelReason'
								}]
					},{
						xtype : 'hidden',
						name : 'trainApplyId',
						value : this.trainApplyId
					}

			]
		});
		// 加载表单对应的数据
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/hrm/getTrainPlan.do?id=' + this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var trainPlan = action.result.data
					var trainCourse = action.result.data.trainCourse;
					Ext.getCmp('trainCourseId').setValue(trainCourse.courseId);
					Ext.getCmp('trainCourseNo').setValue(trainCourse.courseNo);
					Ext.getCmp('trainCourseName').setValue(trainCourse.courseName);
					Ext.getCmp('trainPlanSn').setValue(trainPlan.sn);
					if (trainPlan.trainStatus == '已取消') {
						Ext.getCmp('cancelReasonForm').show();
					} else if (trainPlan.trainStatus == '已结束') {
						Ext.getCmp('saveBtn1').hide();
					} else {
						Ext.getCmp('cancelReasonForm').hide();
						Ext.getCmp('saveBtn1').show();
					};

				},
				failure : function(form, action) {
				}
			});
		}
		if(!Ext.isEmpty(this.sign) && this.sign == 'trainApplyCreatePlan'){
			Ext.getCmp('trainCourseId').setValue(this.courseId);
			Ext.getCmp('trainCourseNo').setValue(this.courseNo);
			Ext.getCmp('trainCourseName').setValue(this.courseName);
			Ext.getCmp('trainType').setValue(this.trainType);
			Ext.getCmp('trainTarget').setValue(this.trainTarget);
			if(this.trainTarget == '部门培训'){
				Ext.getCmp('trainType').setDisabled(true);
				Ext.getCmp('trainType').setVisible(false);
			}
		}
		// 初始化功能按钮
		this.buttons = [{
					text : '保存',
					iconCls : 'btn-save',
					id : 'saveBtn1',
					handler : this.save.createCallback(this.formPanel, this)
				}, {
					text : '取消',
					iconCls : 'btn-cancel',
					handler : this.cancel.createCallback(this)
				}];
	},// end of the initcomponents

	/**
	 * 取消
	 * 
	 * @param {}
	 *            window
	 */
	cancel : function(window) {
		window.close();
	},
	/**
	 * 保存记录
	 */
	save : function(formPanel, window) {
		if (formPanel.getForm().isValid()) {
			formPanel.getForm().submit({
				method : 'POST',
				waitMsg : '正在提交数据...',
				success : function(fp, action) {
					Ext.ux.Toast.msg('操作信息', '成功保存信息！');
					var gridPanel = Ext.getCmp('TrainPlanGrid');
					if (gridPanel != null) {
						gridPanel.getStore().reload();
					}
					window.close();
				},
				failure : function(fp, action) {
					Ext.MessageBox.show({
						title : '操作信息',
						msg : '信息保存出错，请联系管理员！',
						buttons : Ext.MessageBox.OK,
						icon : Ext.MessageBox.ERROR
					});
					window.close();
				}
			});
		}
	}// end of save

});