/**
 * @author
 * @createtime
 * @class PersonLoanForm
 * @extends Ext.Window
 * @description PersonLoan表单
 */
PersonLoanForm = Ext.extend(Ext.Window, {
			// 内嵌FormPanel
			formPanel : null,
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				PersonLoanForm.superclass.constructor.call(this, {
							id : 'PersonLoanFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							height : 400,
							width : 500,
							frame : true,
							title : '[PersonLoan]详细信息',
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
							url : __ctxPath + '/statistics/savePersonLoan.do',
							id : 'PersonLoanForm',
							defaults : {
								anchor : '98%,98%'
							},
							defaultType : 'textfield',
							items : [{
										name : 'personLoan.id',
										id : 'id',
										xtype : 'hidden',
										value : this.id == null ? '' : this.id
									}, {
										fieldLabel : '报告人',
										name : 'personLoan.loanReport',
										id : 'loanReport'
									}, {
										fieldLabel : '所属部门',
										name : 'personLoan.loanDepatment',
										id : 'loanDepatment'
									}, {
										fieldLabel : '公司名称',
										name : 'personLoan.personCompanyName',
										id : 'personCompanyName'
									}, {
										fieldLabel : '借款金额',
										name : 'personLoan.loanMoney',
										id : 'loanMoney'
									}, {
										fieldLabel : '',
										name : 'personLoan.loanYear',
										id : 'loanYear'
									}, {
										fieldLabel : '借款时长',
										name : 'personLoan.loanMonth',
										id : 'loanMonth'
									}, {
										fieldLabel : '借款原因',
										name : 'personLoan.loanCase',
										id : 'loanCase'
									}, {
										fieldLabel : '审批通过日期',
										name : 'personLoan.createDate',
										id : 'createDate'
									}, {
										fieldLabel : '金额大写',
										name : 'personLoan.loanMoneyBig',
										id : 'loanMoneyBig'
									}

							]
						});
				// 加载表单对应的数据
				if (this.id != null && this.id != 'undefined') {
					this.formPanel.getForm().load({
						deferredRender : false,
						url : __ctxPath + '/statistics/getPersonLoan.do?id='
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
									var gridPanel = Ext
											.getCmp('PersonLoanGrid');
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