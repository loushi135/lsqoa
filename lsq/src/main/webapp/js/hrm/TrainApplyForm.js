/**
 * @author
 * @createtime
 * @class TrainApplyForm
 * @extends Ext.Window
 * @description TrainApply表单
 */
TrainApplyForm = Ext.extend(Ext.Window, {
	//内嵌FormPanel
	formPanel : null,
	//构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		//必须先初始化组件
		this.initUIComponents();
		TrainApplyForm.superclass.constructor.call(this, {
			id : 'TrainApplyFormWin',
			layout : 'fit',
			items : this.formPanel,
			modal : true,
			height : 240,
			width : 400,
			maximizable : true,
			title : '培训申请信息',
			buttonAlign : 'center',
			buttons : this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			trackResetOnLoad : true,
			bodyStyle : 'padding:10px 10px 10px 10px',
			border : false,
			url : __ctxPath + '/hrm/saveTrainApply.do',
			id : 'TrainApplyForm',
			defaults : {
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items : [{
					name : 'trainApply.id',
					id : 'id',
					xtype : 'hidden',
					value : this.id == null ? '' : this.id
				}, {
					fieldLabel : '申请人',
					name : 'trainApply.applyUser.fullname',
					id : 'applyUser',
					emptyText:'请选择申请人',
					allowBlank:false,
					listeners : {
								focus : function() {
										UserSelector.getView(
												function(userId, fullname,departments,positions,accessionTime,departmentIds,sexs,posts) {
													Ext.getCmp('applyUser').setValue(fullname);
													Ext.getCmp('applyUserId').setValue(userId);
													Ext.getCmp('departmentId').setValue(departmentIds);
													Ext.getCmp('department').setValue(departments);
												},true).show();
									}
						}
				},{
					xtype : 'hidden',
					name : 'trainApply.applyUser.userId',
					id : 'applyUserId'
				}, {
					fieldLabel : '申请时间',
					xtype : 'datefield',
					name : 'trainApply.createTime',
					id : 'createTime',
					format : 'Y-m-d',
					value:new Date()
				}, {
					fieldLabel : '申请部门',
					readOnly : true,
					cls : 'x-item-disabled',
					name : 'trainApply.department.depName',
					id : 'department'
				}, {
					xtype : 'hidden',
					name : 'trainApply.department.depId',
					id : 'departmentId'
				}, {
					fieldLabel : '培训对象类型',
					name : 'trainApply.trainTarget',
					xtype : 'combo',
					triggerAction: 'all',
					readOnly : true,
					editable : false,
					store : [['部门培训','部门培训'],['公司培训','公司培训']],
					id : 'trainTarget',
					value : '公司培训',
					listeners : {
						'select' : function(obj,record,index){
							if(obj.value == '部门培训'){
								Ext.getCmp('trainType').setDisabled(true);
								Ext.getCmp('trainType').getEl().up('.x-form-item').setDisplayed(false);
							}else if(obj.value == '公司培训'){
								Ext.getCmp('trainType').setDisabled(false);
								Ext.getCmp('trainType').getEl().up('.x-form-item').setDisplayed(true);
							}				
						}
					}
				}, {
					fieldLabel : '培训类型',
					name : 'trainApply.trainType',
					xtype : 'combo',
					triggerAction: 'all',
					readOnly : true,
					editable : false,
					store : [['年度计划','年度计划'],['半年度计划','半年度计划'],['季度计划','季度计划'],['月度计划','月度计划']],
					id : 'trainType'
				}, {
					fieldLabel : '培训课程',
					name : 'trainApply.trainCourse.courseName',
					id : 'courseName',
					emptyText:'请选择培训课程',
					allowBlank:false,
					listeners : {
								focus : function() {
										CourseBoxSelector.getView(
												function(courseId, courseNo,courseName) {
													Ext.getCmp('courseName').setValue(courseName);
													Ext.getCmp('courseId').setValue(courseId);
												},true).show();
									}
						}
				}, {
					xtype : 'hidden',
					name : 'trainApply.trainCourse.courseId',
					id : 'courseId'
				}

			]
		});
		//加载表单对应的数据	
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/hrm/getTrainApply.do?id=' + this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var data = action.result.data;
					
					if(!Ext.isEmpty(data.applyUser)){
						Ext.getCmp('applyUser').setValue(data.applyUser.fullname);
						Ext.getCmp('applyUser').originalValue = data.applyUser.fullname;
						Ext.getCmp('applyUserId').setValue(data.applyUser.userId);
						Ext.getCmp('applyUserId').originalValue = data.applyUser.userId;
					}
					
					if(!Ext.isEmpty(data.department)){
						Ext.getCmp('department').setValue(data.department.depName);
						Ext.getCmp('department').originalValue = data.department.depName;
						Ext.getCmp('departmentId').setValue(data.department.depId);
						Ext.getCmp('departmentId').originalValue = data.department.depId;
					}
					
					if(!Ext.isEmpty(data.trainCourse)){
						Ext.getCmp('courseName').setValue(data.trainCourse.courseName);
						Ext.getCmp('courseName').originalValue = data.trainCourse.courseName;
						Ext.getCmp('courseId').setValue(data.trainCourse.courseId);
						Ext.getCmp('courseId').originalValue = data.trainCourse.courseId;
					}
				},
				failure : function(form, action) {
				}
			});
		}
		//初始化功能按钮
		this.buttons = [{
				text : '保存',
				iconCls : 'btn-save',
				handler : this.save.createCallback(this.formPanel, this)
			}, {
				text : '重置',
				iconCls : 'btn-reset',
				handler : this.reset.createCallback(this.formPanel)
			}, {
				text : '取消',
				iconCls : 'btn-cancel',
				handler : this.cancel.createCallback(this)
			}];
	},//end of the initcomponents

	/**
	 * 重置
	 * 
	 * @param {}
	 *            formPanel
	 */
	reset : function(formPanel) {
		formPanel.getForm().reset();
	},
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
					var gridPanel = Ext.getCmp('TrainApplyGrid');
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
	}//end of save

});