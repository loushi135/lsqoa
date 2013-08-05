/**
 * @author juql
 * @createtime
 * @class MeetingRoomForm
 * @extends Ext.Window
 * @description MeetingRoom表单
 */
MeetingRoomForm = Ext.extend(Ext.Window, {
			// 内嵌FormPanel
			formPanel : null,
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				MeetingRoomForm.superclass.constructor.call(this, {
							id : 'MeetingRoomFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							height : 400,
							width : 400,
							title : '会议室详细信息',
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
							url : __ctxPath + '/admin/saveMeetingRoom.do',
							id : 'MeetingRoomForm',
							defaults : {
								anchor : '98%,98%'
							},
							defaultType : 'textfield',
							items : [{
										name : 'meetingRoom.id',
										id : 'id',
										xtype : 'hidden',
										value : this.id == null ? '' : this.id
									}, {
										fieldLabel : '会议室名称',
										name : 'meetingRoom.name',
										id : 'name'
									}, {
										fieldLabel : '容纳人数',
										name : 'meetingRoom.maxPerson',
										id : 'maxPerson'
									}, {
										fieldLabel : '麦克风',
										hiddenName : 'meetingRoom.microphone',
										allowBlank : false,
										id : 'microphone',
										xtype : 'combo',
										mode : 'local',
										editable : false,
										readOnly : true,
										triggerAction : 'all',
										store : [['0', '有'], ['1', '无']]
									}, {
										fieldLabel : '音箱',
										hiddenName : 'meetingRoom.speaker',
										allowBlank : false,
										id : 'speaker',
										xtype : 'combo',
										mode : 'local',
										editable : false,
										readOnly : true,
										triggerAction : 'all',
										store : [['0', '有'], ['1', '无']]
									}, {
										fieldLabel : '投影仪',
										hiddenName : 'meetingRoom.projector',
										allowBlank : false,
										id : 'projector',
										xtype : 'combo',
										mode : 'local',
										editable : false,
										readOnly : true,
										triggerAction : 'all',
										store : [['0', '有'], ['1', '无']]
									}, {
										fieldLabel : '智能会议通',
										hiddenName : 'meetingRoom.znhyt',
										allowBlank : false,
										id : 'znhyt',
										xtype : 'combo',
										mode : 'local',
										editable : false,
										readOnly : true,
										triggerAction : 'all',
										store : [['0', '有'], ['1', '无']]
									}, {
										fieldLabel : '笔记本电脑',
										hiddenName : 'meetingRoom.notebook',
										allowBlank : false,
										id : 'notebook',
										xtype : 'combo',
										mode : 'local',
										editable : false,
										readOnly : true,
										triggerAction : 'all',
										store : [['0', '有'], ['1', '无']]
									}, {
										fieldLabel : '写字板',
										hiddenName : 'meetingRoom.wordPad',
										allowBlank : false,
										id : 'wordPad',
										xtype : 'combo',
										mode : 'local',
										editable : false,
										readOnly : true,
										triggerAction : 'all',
										store : [['0', '有'], ['1', '无']]
									}, {
										fieldLabel : '状态',
										hiddenName : 'meetingRoom.status',
										allowBlank : false,
										id : 'status',
										xtype : 'combo',
										mode : 'local',
										editable : false,
										readOnly : true,
										triggerAction : 'all',
										store : [['0', '可用'], ['1', '占用']]
									}, {
										fieldLabel : '备注',
										xtype : 'textarea',
										name : 'meetingRoom.remark',
										id : 'remark'
									}

							]
						});
				// 加载表单对应的数据
				if (this.id != null && this.id != 'undefined') {
					this.formPanel.getForm().load({
						deferredRender : false,
						url : __ctxPath + '/admin/getMeetingRoom.do?id='
								+ this.id,
						waitMsg : '正在载入数据...',
						success : function(form, action) {
							Ext.getCmp('name').setValue(action.result.data.name);
							Ext.getCmp('maxPerson').setValue(action.result.data.maxPerson);
							Ext.getCmp('microphone').setValue(action.result.data.microphone);
							Ext.getCmp('speaker').setValue(action.result.data.speaker);
							Ext.getCmp('projector').setValue(action.result.data.projector);
							Ext.getCmp('znhyt').setValue(action.result.data.znhyt);
							Ext.getCmp('notebook').setValue(action.result.data.notebook);
							Ext.getCmp('wordPad').setValue(action.result.data.wordPad);
							Ext.getCmp('status').setValue(action.result.data.status);
							Ext.getCmp('remark').setValue(action.result.data.remark);
						},
						failure : function(form, action) {
							Ext.ux.Toast.msg('编辑', '载入失败');
						}
					});
				}
				if(isGranted('_MeetingRoomAdd')||isGranted('_MeetingRoomEdit')){
					// 初始化功能按钮
					this.buttons = [{
								text : '保存',
								iconCls : 'btn-save',
								handler : this.save.createCallback(this.formPanel,
										this)
							}, {
								text : '重置',
								iconCls : 'btn-reset',
								handler : this.reset.createCallback(this.formPanel)
							}, {
								text : '取消',
								iconCls : 'btn-cancel',
								handler : this.cancel.createCallback(this)
							}];
				}
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
									var gridPanel = Ext
											.getCmp('MeetingRoomGrid');
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