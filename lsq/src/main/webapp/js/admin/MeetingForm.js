/**
 * @author juql
 * @createtime
 * @class MeetingForm
 * @extends Ext.Window
 * @description Meeting表单
 */
MeetingForm = Ext.extend(Ext.Window, {
			// 内嵌FormPanel
			formPanel : null,
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				MeetingForm.superclass.constructor.call(this, {
							id : 'MeetingFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							height : 600,
							width : 600,
							title : '会议详细信息',
							buttonAlign : 'center',
							buttons : this.buttons
						});
			},// end of the constructor
			// 初始化组件
			initUIComponents : function() {
				this.formPanel = new Ext.FormPanel({
							layout : 'form',
							bodyStyle : 'padding:10px 10px 10px 10px',
							border : false,
							frame:true,
							url : __ctxPath + '/admin/saveMeeting.do',
							id : 'MeetingForm',
							defaults : {
								anchor : '98%,98%'
							},
							defaultType : 'textfield',
							items : [{
										name : 'meeting.id',
										id : 'id',
										xtype : 'hidden',
										value : this.id == null ? '' : this.id
									},{
										xtype:'hidden',
										name:'meeting.processRunId',
										value:-1
									}, {
										name : 'meeting.userId',
										id : 'userId',
										xtype : 'hidden',
										readyOnly : true
									}, {
										fieldLabel : '会议编号',
										name : 'meeting.meetingNo',
										id : 'meetingNo',
										editable : false,
										readOnly : true
									}, {
										fieldLabel : '申请部门',
										name : 'meeting.department',
										id : 'department',
										editable : false,
										readOnly : true
									}, {
										fieldLabel : '申请人',
										name : 'meeting.applyUser',
										id : 'applyUser',
										editable : false,
										readOnly : true
									}, {
										fieldLabel : '开始时间',
										name : 'meeting.startTime',
										id : 'startTime',
										editable : false,
										readOnly : true
									}, {
										fieldLabel : '结束时间',
										name : 'meeting.entTime',
										id : 'entTime',
										editable : false,
										readOnly : true
									}, {
										fieldLabel : '会议主题',
										name : 'meeting.meetingTitle',
										id : 'meetingTitle',
										editable : false,
										readOnly : true
									}, {
										fieldLabel : '人数',
										name : 'meeting.personNum',
										id : 'personNum'
									}, {
										fieldLabel : '会议类型',
										name : 'meeting.meetingType',
										id : 'meetingType',
										xtype : 'combo',
										mode : 'local',
										editable : false,
										readOnly : true,
										store : [['1', '部门会议'], ['2', '公司会议'], ['3', '股东会'], ['4', '董事会'], ['5', '外部接待'], ['6', '专项会议'], ['7', '员工培训']]
									}, {
										fieldLabel : '会议室',
										name : 'meeting.meetingRoom',
										id : 'meetingRoom',
										editable : false,
										readOnly : true
									}, {
										fieldLabel : '涉会需求',
										xtype : 'textarea',
										name : 'meeting.meetingRequire',
										id : 'meetingRequire',
										height : 200,
										editable : false,
										readOnly : true
									}
							]
						});
				// 加载表单对应的数据
				if (this.id != null && this.id != 'undefined') {
					this.formPanel.getForm().load({
								deferredRender : false,
								url : __ctxPath + '/admin/getMeeting.do?id='
										+ this.id,
								waitMsg : '正在载入数据...',
								success : function(form, action) {
								},
								failure : function(form, action) {
								}
							});
				}
				// 初始化功能按钮
				this.buttons = [{
							text : '关闭',
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
									var gridPanel = Ext.getCmp('MeetingGrid');
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