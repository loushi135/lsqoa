/**
 * @author
 * @createtime
 * @class PhotoFolderForm
 * @extends Ext.Window
 * @description PhotoFolder表单
 */
PhotoFolderForm = Ext.extend(Ext.Window, {
			// 内嵌FormPanel
			formPanel : null,
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				PhotoFolderForm.superclass.constructor.call(this, {
							id : 'PhotoFolderFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : false,
							height : 200,
							width : 400,
							title : '图片文件夹详细信息',
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
							url : __ctxPath + '/hrm/savePhotoFolder.do',
							id : 'photoFolderForm',
							defaults : {
								anchor : '98%,98%'
							},
							defaultType : 'textfield',
							items : [{
										name : 'photoFolder.id',
										id : 'id',
										xtype : 'hidden',
										value : this.id == null ? '' : this.id
									}, {
										fieldLabel : '文件夹名称',
										name : 'photoFolder.folderName',
										id : 'folderName'
									},{
										name:'photoFolder.parent.id',
										value:this.parentId,
										id:'photoFolder.parent.id',
										xtype : 'hidden'
									},{
										name:'photoFolder.user.userId',
										id:'photoFolder.user.userId',
										xtype : 'hidden',
										value:__currentUserId
									}
							]
						});
				// 加载表单对应的数据
				if (this.id != null && this.id != 'undefined') {
					this.formPanel.getForm().load({
						deferredRender : false,
						url : __ctxPath + '/hrm/getPhotoFolder.do?id='
								+ this.id,
						waitMsg : '正在载入数据...',
						success : function(form, action) {
							var res = Ext.util.JSON.decode(action.response.responseText).data;
							Ext.getCmp("photoFolder.parent.id").setValue(res.parent.id);
							Ext.getCmp("photoFolder.user.userId").setValue(res.user.userId);
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
						}
//						, {
//							text : '重置',
//							iconCls : 'btn-reset',
//							handler : this.reset.createCallback(this.formPanel)
//						}
						, {
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
			save : function(formPanel1, window) {
				if (window.formPanel.getForm().isValid()) {
					window.formPanel.getForm().submit({
								method : 'POST',
								waitMsg : '正在提交数据...',
								success : function(fp, action) {
									Ext.ux.Toast.msg('操作信息', '成功保存信息！');
									var photoTreePanel = Ext
											.getCmp('photoTreePanel');
									if (photoTreePanel != null) {
										photoTreePanel.root.reload();
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