/**
 * @author
 * @createtime
 * @class TrainCourseForm
 * @extends Ext.Window
 * @description TrainCourse表单
 */
TrainCourseForm = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		TrainCourseForm.superclass.constructor.call(this, {
					id : 'TrainCourseFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					height : 400,
					width : 650,
					maximizable : true,
					title : '详细信息',
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
					url : __ctxPath + '/hrm/saveTrainCourse.do',
					id : 'TrainCourseForm',
					defaults : {
						anchor : '98%,98%'
					},
					defaultType : 'textfield',
					items : [{
								name : 'trainCourse.courseId',
								id : 'courseId',
								xtype : 'hidden',
								value : this.courseId == null
										? ''
										: this.courseId
							}, {
								xtype : 'container',
								layout : 'column',
								defaults: {anchor : "100%"},
								items : [{
											xtype : 'label',
											text : '课程编号:',
											columnWidth : 0.12
										}, {
											xtype : 'textfield',
											allowBlank : false,
											name : 'trainCourse.courseNo',
											id : 'courseNo',
											style : 'margin-right : 5px;',
											columnWidth : 0.2
										}, {
											xtype : 'label',
											text : '课程名称:',
											columnWidth:0.12
										}, {
											xtype : 'textfield',
											allowBlank : false,
											name : 'trainCourse.courseName',
											id : 'courseName',
											style : 'margin-right : 5px;',
											columnWidth:0.2
										}, {
											xtype : 'label',
											text : '部门名称:',
											columnWidth:0.12
										}, {
											xtype : 'textfield',
											id : 'deptName',
											style : 'margin-right : 5px;',
											columnWidth:0.2,
											name : 'trainCourse.deptName',
											allowBlank : false,
											listeners:{
												'focus' : function() {
													DepSelector.getView(function(id, name) {
														Ext.getCmp('deptName').setValue(name);
													}, false).show();
												}
		                                     }
										}
										]
							}, {
							    xtype : 'container',
								layout : 'column',
								style : 'margin-top:4px;padding-left:0px',
								items:[
								        {
											xtype : 'label',
											columnWidth:0.12,
											text : '课程类型:'
										}, {
											xtype : 'combo',
											hiddenName :'trainCourse.courseType',
				                        	editable: false,
											columnWidth:0.2,
											triggerAction : 'all',
											allowBlank : false,
											id:'courseType',
										    emptyText : '---请选择---',
										    store : [['选修', '选修'],['必修','必修']]
										},{
											xtype : 'label',
											columnWidth:0.12,
											text : '培训对象:'
										}, {
											xtype : 'textfield',
											allowBlank : false,
											style : 'margin-right : 5px;',
											columnWidth:0.2,
											name : 'trainCourse.trainTarget',
											id : 'trainTarget'
										}, {
											xtype : 'label',
											columnWidth:0.12,
											text : '课程优先级:'
											
										}, {
											xtype : 'combo',
											columnWidth:0.2,
											id : 'coursePriority',
											hiddenName :'trainCourse.coursePriority',
				                        	editable: false,
											triggerAction : 'all',
											allowBlank : false,
										    emptyText : '---请选择---',
										    store : [['高级', '高级'],['中级','中级'],['低级','低级']]
										}
								  ]
							 },{
								xtype : 'container',
								layout : 'column',
								style:'padding-left:0px;margin-top:4px',
								items : [{
											xtype : 'label',
											text : '培训目的:',
											columnWidth:0.12
										},{
											xtype : 'textarea',
											height:80,
											columnWidth:0.83,
											name : 'trainCourse.trainCause',
											id : 'trainCause'
										}]
							},{
								xtype : 'container',
								layout : 'column',
								style:'padding-left:0px;margin-top:4px',
								items : [{
											xtype : 'label',
											text : '备注说明:',
											columnWidth:0.12
										}, {
											xtype : 'textarea',
											height:80,
											columnWidth:0.83,
											name : 'trainCourse.remarks',
											id : 'remarks'
										}]
							
							 }, {
								xtype : 'container',
								layout : 'column',
								style:'padding-left:0px;margin-top:4px',
								items : [{
											xtype : 'label',
											text : '培训类型:',
											columnWidth:0.12
										}, {
											id : 'trainType',
											xtype : 'combo',
											hiddenName :'trainCourse.trainType',
				                        	editable: false,
											columnWidth:0.2,
											triggerAction : 'all',
											allowBlank : false,
										    emptyText : '---请选择---',
										    store : [['内训内聘', '内训内聘'],['内训外聘','内训外聘'],['外训','外训'],['其他','其他']]
										},{
											xtype : 'label',
											text : '培训方式:',
											columnWidth:0.12
										}, {
											id : 'trainWay',
											xtype : 'combo',
											hiddenName :'trainCourse.trainWay',
				                        	editable: false,
											columnWidth:0.2,
											triggerAction : 'all',
											allowBlank : false,
										    emptyText : '---请选择---',
										    store : [['授课', '授课'],['座谈交流','座谈交流'],['参观展览','参观展览'],['授课/实验','授课/实验'],['授课/演练','授课/演练'],['分发资料','分发资料'],['自学','自学'],['实际操作','实际操作'],['其他','其他']]
										}, {
											xtype : 'label',
											text : '总课时:',
											columnWidth:0.12
										}, {
											xtype : 'textfield',
											allowBlank : false,
											columnWidth:0.2,
											style : 'margin-right : 5px;',
											name : 'trainCourse.courseTotal',
											id : 'courseTotal'
										}]

							}, {
								xtype : 'container',
								layout : 'column',
								style:'padding-left:0px;margin-top:4px',
								items : [{
											xtype : 'label',
											text : '培训课时:',
											width:55,
											columnWidth:0.12
										}, {
											xtype : 'textfield',
											name : 'trainCourse.courseTime',
											allowBlank : false,
											columnWidth:0.2,
											style : 'margin-right : 5px;',
											id : 'courseTime'
										}, {
											xtype : 'label',
											text : '培训讲师:',
											columnWidth:0.12
										}, {
											id : 'trainTeacher',
											xtype : 'textfield',
											columnWidth:0.2,
											style : 'margin-right : 5px;',
											name :'trainCourse.trainTeacher',
											allowBlank : false
										},{
											xtype : 'label',
											text : '考核方式:',
											columnWidth:0.12
										}, {
											id : 'checkType',
											xtype : 'combo',
											hiddenName :'trainCourse.checkType',
				                        	editable: false,
											columnWidth:0.2,
											triggerAction : 'all',
											allowBlank : false,
										    emptyText : '---请选择---',
										    store : [['闭卷考试', '闭卷考试'],['心得体会','心得体会'],['课后作业','课后作业'],['取得证书','取得证书'],['组织讨论','组织讨论'],['开卷考试','开卷考试'],['评估','评估'],['其他','其他']]
										}]
							}, {
								xtype : 'container',
								layout : 'column',
								style:'padding-left:0px;margin-top:4px',
								items : [{
											xtype : 'label',
											text : '成本预算:',
											columnWidth:0.12
										}, {
											xtype : 'textfield',
											columnWidth:0.2,
											style : 'margin-right : 5px;',
											name : 'trainCourse.trainBudget',
											id : 'trainBudget'
										}, {
											xtype : 'label',
											text : '培训学分:',
											columnWidth:0.12
										}, {
											xtype : 'textfield',
											columnWidth:0.2,
											style : 'margin-right : 5px;',
											name : 'trainCourse.credit',
											id : 'credit'
										}]

							}]
				});
		// 加载表单对应的数据
		if (this.courseId != null && this.courseId != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/hrm/getTrainCourse.do?courseId='
						+ this.courseId,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var result=Ext.util.JSON.decode(action.response.responseText);
					Ext.getCmp('deptId').setValue(result.data.dept.depId);
					Ext.getCmp('deptName').setValue(result.data.dept.depName);
				},
				failure : function(form, action) {
				}
			});
		}
		// 初始化功能按钮
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
	},// end of the initcomponents

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
							var gridPanel = Ext.getCmp('TrainCourseGrid');
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