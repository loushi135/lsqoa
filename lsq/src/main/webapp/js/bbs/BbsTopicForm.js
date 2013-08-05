/**
 * @author
 * @createtime
 * @class BbsTopicForm
 * @extends Ext.Window
 * @description BbsTopic表单
 */
BbsTopicForm = Ext.extend(Ext.Window, {
			// 内嵌FormPanel
			formPanel : null,
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				BbsTopicForm.superclass.constructor.call(this, {
							id : 'BbsTopicFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							autoScroll : true,
							height : 530,
							width : 750,
							frame : true,
							title : '主题详细信息',
							buttonAlign : 'center',
							buttons : this.buttons
						});
			},// end of the constructor
			// 初始化组件
			initUIComponents : function() {
				var thiz=this;
				this.formPanel = new Ext.FormPanel({
					        autoScroll : false,
							layout : 'form',
							bodyStyle : 'padding:10px 10px 10px 10px',
							border : false,
							url : __ctxPath + '/bbs/saveBbsTopic.do',
							id : 'BbsTopicForm',
							defaults : {
								anchor : '100%,100%'
							},
							items : [{
										name : 'bbsTopic.id',
										id : 'id',
										xtype : 'hidden',
										value : this.id == null ? '' : this.id
									},{
										name : 'bbsTopic.topId',
										id : 'topId',
										xtype : 'hidden'
									},{
										name : 'bbsTopic.isReply',
										id : 'isReply',
										xtype : 'hidden'
									},{
										name : 'bbsTopic.publishTime',
										id : 'publishTime',
										xtype : 'hidden'
									},{
										name : 'bbsTopic.lastUpdateTime',
										id : 'lastUpdateTime',
										xtype : 'hidden'
									},{
										name : 'bbsTopic.viewCount',
										id : 'viewCount',
										xtype : 'hidden'
									}, {
										fieldLabel : '标题',
										xtype:'textfield',
										allowBlank:false,
										maxLength:20,
										name : 'bbsTopic.title',
										id : 'title'
									}, 
									  {
										  xtype:'container' ,
									      id:'containerId',
									      hidden:!isGranted('_BbsTopicManage'),
									      layout : 'form',
									      defaults : {
								               anchor : '100%,100%'
							                   },
							items:[
									{   
										xtype: "combo",
										fieldLabel : '是否加精',
										value:0,
										hiddenName : 'bbsTopic.isPrime',
										allowBlank:false,
										triggerAction: "all",
				                        editable: false,
				                        store: [["0", "否"], ["1", "是"]]
									 },
									  {   
									    xtype: "combo",
									    value:0,
										fieldLabel : '是否被锁定',
										hiddenName : 'bbsTopic.isLock',
										allowBlank:false,
										id:'isLock',
									    triggerAction: "all",
				                        editable: false,
				                        store: [["0", "否"], ["1", "是"]]
									}, 
									{
									    xtype:'hidden',
									    name:'bbsTopic.user.userId',
									    allowBlank:false,
									    id:'bbsTopic.user.userId',
									    value:__currentUserId
									},{
									    xtype: "combo",
									    value:0,
										fieldLabel : '是否置顶',
										hiddenName : 'bbsTopic.isTop',
										allowBlank:false,
										id:'isTop',
									    triggerAction: "all",
				                        editable: false,
				                        store: [["0", "否"], ["1", "是"]]
									 },
									{   
									    fieldLabel : '发布人',
										xtype:'textfield',
										allowBlank:false,
										id : 'bbsTopicUsername',
										readOnly:true,
										value:__currentUser
									}
									]},{
										xtype:'combo',
										fieldLabel : '分组名称',
										triggerAction: "all",
										editable: false,
										allowBlank:false,
										id:'bbsGroupId',
										valueField : 'id',
										displayField : 'groupName',
										hiddenName : 'bbsTopic.group.id',
										store: new Ext.data.JsonStore({
														autoLoad:true,
														url:__ctxPath + "/bbs/listBbsGroup.do",
													    baseParams:{start:0,limit:100},
														root : 'result',
														totalProperty : 'totalCounts',
														remoteSort : true,
														fields : [{name:'id',type:'int'},'groupName']
													})
									},
									{
										fieldLabel : '内容',
										xtype:'fckeditor',
										allowBlank:false,
										name : 'bbsTopic.content',
										id : 'content',
										height:300
									}
							]
						});
					
				// 加载表单对应的数据
				if (this.id != null && this.id != 'undefined') {
					this.formPanel.getForm().load({
								deferredRender : false,
								url : __ctxPath + '/bbs/getBbsTopic.do?id='
										+ this.id,
								waitMsg : '正在载入数据...',
								success : function(form, action) {
									var bbsTopic = action.result.data;
									var bbsGroupId=bbsTopic.group.id;
									var bbsTopicUsername=bbsTopic.user.fullname;
									var userId = bbsTopic.user.userId;
									Ext.getCmp("bbsTopic.user.userId").setValue(userId);
									Ext.getCmp('bbsGroupId').setValue(bbsGroupId);
									Ext.getCmp('bbsTopicUsername').setValue(bbsTopicUsername);
									
								},
								failure : function(form, action) {
								}
							});
				}
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
									var gridPanel = Ext.getCmp('BbsTopicGrid');
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