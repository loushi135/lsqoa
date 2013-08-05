/**
 * @author
 * @createtime
 * @class ProvinceForm
 * @extends Ext.Window
 * @description Province表单
 */
ProvinceForm = Ext.extend(Ext.Window, {
			// 内嵌FormPanel
			formPanel : null,
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				ProvinceForm.superclass.constructor.call(this, {
							id : 'ProvinceFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : false,
							height : 140,
							width : 400,
							title : '省详细信息',
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
							url : __ctxPath + '/system/saveProvince.do',
							id : 'ProvinceForm',
							defaults : {
								anchor : '98%,98%'
							},
							defaultType : 'textfield',
							items : [{
								name : 'province.provinceId',
								id:'provinceId',
								xtype : 'hidden',
								value : this.provinceId == null ? '': this.provinceId
							}, {
								fieldLabel : '名称',
								name : 'province.provinceName',
								id : 'provinceName'
							}, {
								fieldLabel : '',
								xtype:'hidden',
								name : 'province.sort',
								id : 'sort'
							}, {
								fieldLabel : '',
								xtype:'hidden',
								name : 'province.remark',
								id : 'remark'
							}
							]
						});
				// 加载表单对应的数据
				if (this.provinceId != null && this.provinceId != 'undefined') {
					this.formPanel.getForm().load({
						deferredRender : false,
						url : __ctxPath + '/system/getProvince.do?provinceId='
								+ this.provinceId,
						waitMsg : '正在载入数据...',
						success : function(form, action) {
							
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
									window.provinceTreePanel.treePanel.root.reload();
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