/**
 * @author
 * @createtime
 * @class CommonReportForm
 * @extends Ext.Window
 * @description CommonReport表单
 */
CommonReportForm = Ext.extend(Ext.Window, {
			// 内嵌FormPanel
			formPanel : null,
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				CommonReportForm.superclass.constructor.call(this, {
							id : 'CommonReportFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							width : 600,
			                height : 550,
			                autoScroll : true,
							//maximizable : true,
							title : '通用报告详细信息',
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
							//url : __ctxPath + '/statistics/saveCommonReport.do',
							id : 'CommonReportForm',
							defaults : {
								anchor : '98%,98%'
							},
							defaultType : 'textfield',
							items : [{
										name : 'commonReport.id',
										id : 'id',
										xtype : 'hidden',
										value : this.id == null ? '' : this.id
									}, {
										fieldLabel : '报告人',
										name : 'commonReport.reporter',
										id : 'reporter'
									}, {
										fieldLabel : '所在部门',
										name : 'commonReport.deptName',
										id : 'deptName'
									}, {
										fieldLabel : '类型',
										name : 'commonReport.state',
										id : 'state'
									}, {
										fieldLabel : '所在公司',
										name : 'commonReport.company',
										id : 'company'
									}, {
										fieldLabel : '抄送部门',
										name : 'commonReport.sendDept',
										id : 'sendDept'
									}, {
										xtype : 'textarea',
										fieldLabel : '报告内容：',
										width : 443,
										height : 300,
										allowBlank : false,
										name : 'commonReport.content',
										id : 'content'
									}

							]
						});
				// 加载表单对应的数据
				if (this.id != null && this.id != 'undefined') {
					this.formPanel.getForm().load({
						deferredRender : false,
						url : __ctxPath + '/statistics/getCommonReport.do?id='
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
			},

			cancel : function(window) {
				window.close();
			}

		});