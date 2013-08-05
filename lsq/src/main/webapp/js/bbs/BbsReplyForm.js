/**
 * @author
 * @createtime
 * @class BbsReplyForm
 * @extends Ext.Window
 * @description BbsReply表单
 */
BbsReplyForm = Ext.extend(Ext.Window, {
			// 内嵌FormPanel
			formPanel : null,
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				BbsReplyForm.superclass.constructor.call(this, {
							id : 'BbsReplyFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							height : 350,
							width : 750,
							frame : true,
							title : '回复信息',
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
							url : __ctxPath + '/bbs/saveBbsReply.do',
							id : 'BbsReplyForm',
							defaults : {
								anchor : '98%,98%'
							},
							defaultType : 'textfield',
							items : [{
										name : 'bbsReply.id',
										xtype : 'hidden'
									},{
										fieldLabel : '回复内容',
										xtype:'fckeditor',
										allowBlank:false,
										height:250,
										name : 'bbsReply.replyContent',
										id : 'replyContent'
										
									},{
										  xtype:'container' ,
									      id:'containerReplyId',
									      hidden:true,
									      layout : 'form',
									      defaults : {
								               anchor : '100%,100%'
							                   },
							        	  items:[
											        {
														fieldLabel : '回复人',
													    xtype:'textfield',
														name : 'bbsReply.replyUser.userId',
														id : 'userId',
														value:__currentUserId
												    },{
														fieldLabel : '主题',
														xtype:'textfield',
														name : 'bbsReply.topic.id',
														id : 'topicId',
														value:this.topicId
													}
											]
									}
							]
						});
				// 加载表单对应的数据
				if (this.replyId != null && this.replyId!= 'undefined') {
					this.formPanel.getForm().load({
								deferredRender : false,
								url : __ctxPath + '/bbs/getBbsReply.do?id='
										+ this.replyId,
								waitMsg : '正在载入数据...',
								success : function(form, action) {
									var bbsReply = action.result.data;
									var replyContent=bbsReply.replyContent
									var topicId=bbsReply.topic.id
									Ext.getCmp("topicId").setValue(topicId);
									Ext.getCmp("replyContent").setValue(replyContent+"<br/>")
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
						},
//						{
//							text : '重置',
//							iconCls : 'btn-reset',
//							handler : this.reset.createCallback(this.formPanel)
//						}, 
						{
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
									var gridPanel = Ext.getCmp('BbsReplyGrid');
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