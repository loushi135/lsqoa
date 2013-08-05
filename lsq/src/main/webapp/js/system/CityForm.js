/**
 * @author
 * @createtime
 * @class CityForm
 * @extends Ext.Window
 * @description City表单
 */
CityForm = Ext.extend(Ext.Window, {
			// 内嵌FormPanel
			formPanel : null,
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				CityForm.superclass.constructor.call(this, {
							id : 'CityFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : false,
							height : 140,
							width : 400,
							title : '城市详细信息',
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
							url : __ctxPath + '/system/saveCity.do',
							id : 'CityForm',
							defaults : {
								anchor : '98%,98%'
							},
							defaultType : 'textfield',
							items : [{
										name : 'city.cityId',
										id : 'cityId',
										xtype : 'hidden',
										value : this.cityId == null ? '' : this.cityId
									}, {
										fieldLabel : '城市名称',
										name : 'city.cityName',
										id : 'cityName'
									}, {
										fieldLabel : '省主键',
										xtype:'hidden',
										name : 'city.province.provinceId',
										id : 'provinceId',
										value:this.provinceId
									}, {
										fieldLabel : '排序',
										xtype:'hidden',
										name : 'city.sort',
										id : 'sort'
									}

							]
						});
				// 加载表单对应的数据
				if (this.cityId != null && this.cityId != 'undefined') {
					this.formPanel.getForm().load({
						deferredRender : false,
						url : __ctxPath + '/system/getCity.do?cityId='
								+ this.cityId,
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