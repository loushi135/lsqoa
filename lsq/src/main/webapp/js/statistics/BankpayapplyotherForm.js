/**
 * @author
 * @createtime
 * @class BankpayapplyotherForm
 * @extends Ext.Window
 * @description Bankpayapplyother表单
 */
BankpayapplyotherForm = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		BankpayapplyotherForm.superclass.constructor.call(this, {
					id : 'BankpayapplyotherFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					width : 700,
					height : 400,
					frame : true,
					title : '银行付款(其他)详细信息',
					buttonAlign : 'center',
					buttons : this.buttons
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel({
					layout : 'form',
					autoScroll : true,
					trackResetOnLoad:true,
			        bodyStyle: 'padding:10px 10px 10px 10px;overflow-x:hidden;overflow-y:scroll',
					border : false,
					url : __ctxPath + '/statistics/saveBankpayapplyother.do',
					id : 'BankpayapplyotherForm',
					defaults : {
						anchor : '95%,95%'
					},
					defaultType : 'textfield',
					items : [{
						name : 'bankpayapplyother.bankPayApplyId',
						id : 'bankPayApplyId',
						xtype : 'hidden',
						value : this.bankPayApplyId == null
								? ''
								: this.bankPayApplyId
					},{
						xtype:'hidden',
						name:'bankpayapplyother.processRunId',
						value:-1
					},  {
					xtype : "panel",
					layout : "column",
					border : false,
					unstyled : false,
					style:'margin-top:20px;',
					items : [{
								xtype : "label",
								name : "MyLabel1",
								text : "项目名称:",
								width : 90
							}, {
								xtype : "textfield",
								name : "bankpayapplyother.bpaProjectName",
								id:'bpaProjectName',
								width:140,
								maxLength:64,
								allowBlank:true
							}, {
								xtype : "label",
								name : "MyLabel2",
								text : "项目编号:",
								style : "padding-left:5px;"
							}, {
								xtype : "textfield",
							    maxLength:64,
								name : "bankpayapplyother.bpaProjectNo",
								id:'bpaProjectNo',
								width:160,
								allowBlank:true
							}, {
								xtype : "label",
								name : "MyLabel3",
								text : "付款类别:",
								style : "padding-left:5px;"
							}, {
								xtype : "combo",
								id:"bpaPayType",
								hiddenName:"bankpayapplyother.bpaPayType",
								allowBlank:false,
								triggerAction :'all',
								width:120,
								editable :false,
								store:[['投标保证金','投标保证金'],['施工保证金','施工保证金'],['保函保证金','保函保证金'],['税金','税金'],['其他','其他']]
							}]
				}
				, {
					xtype : "panel",
					layout : "column",
					style:'padding-top:5px;',
					border : false,
					unstyled : false,
					anchor:"100%",
					items : [{
								xtype : "label",
								text : "收款单位:",
								width : 90
							}, {
								xtype : "textfield",
							    maxLength:64,
								name : "bankpayapplyother.bpaReceiptDept",
								id:"bpaReceiptDept",
								width: 530,
								allowBlank:false
							}]
				}, {
					xtype : "panel",
					layout : "column",
					border : false,
					unstyled : false,
					style:'padding-top:5px;',
					items : [{
								xtype : "label",
								name : "MyLabel1",
								text : "付款事由:",
								height : 50,
								width : 90
							}, {
								xtype : "textarea",
								maxLength:190,
								name : "bankpayapplyother.bpaReceiptReason",
								id:"bpaReceiptReason",
								allowBlank:false,
								width: 526,
								height:50
							}]
				}, {
					xtype : "panel",
					layout : "column",
					border : false,
					unstyled : false,
					style:'padding-top:5px;',
					items : [{
								xtype : "label",
								text : "合同金额:",
								width : 90
							}, {
								xtype : "numberfield",
								regex:/(^$)|^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/,  //加上（^$）表示可为空
								name : "bankpayapplyother.bpaContract",
								id:'bpaContract',
//								allowBlank:false,
								width:80,
								enableKeyEvents:true,
								listeners : { 
									keyup: function(field) { 
										var bpaSumMoney=Ext.getCmp('bpaSumMoney').getValue();
										var bpaContract=Ext.getCmp('bpaContract').getValue();
										Ext.getCmp('bpaSumMoneyRatio').setValue(Math.round(bpaSumMoney/bpaContract*1000)/10);
									}
								}
							}, {
								xtype : "label",
								text : "累计已付款:",
								width :80,
								style : "padding-left:5px;"
							}, {
								xtype : "numberfield",
								id:'bpaSumMoney',
								regex:/(^$)|^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/,  //加上（^$）表示可为空
								name : "bankpayapplyother.bpaSumMoney",
//								allowBlank:false,
								width:80,
								enableKeyEvents:true,
								listeners : { 
									keyup: function(field) { 
										var bpaSumMoney=Ext.getCmp('bpaSumMoney').getValue();
										var bpaContract=Ext.getCmp('bpaContract').getValue();
										Ext.getCmp('bpaSumMoneyRatio').setValue(Math.round(bpaSumMoney/bpaContract*1000)/10);
									}
								}
							}, {
								xtype : "label",
								text : "已付款比例(%):",
								width : 100,
								style : "padding-left:5px;"
							}, {
								xtype : "numberfield",
								id:'bpaSumMoneyRatio',
								name : "bankpayapplyother.bpaSumMoneyRatio",
								style:"background:#F0F0F0;",
//								allowBlank:false,
								editable:false,
								readOnly:true,
								width:60,
								listeners : { 
									focus: function(field) { 
										var bpaSumMoney=Ext.getCmp('bpaSumMoney').getValue();
										var bpaContract=Ext.getCmp('bpaContract').getValue();
										Ext.getCmp('bpaSumMoneyRatio').setValue(Math.round(bpaSumMoney/bpaContract*1000)/10);
									}
								}
							},{
								xtype : "label",
								text : "发票结余:",
								width : 70,
								style : "padding-left:5px;"
							},{
								xtype : "numberfield",
								regex:/(^$)|^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/,  //加上（^$）表示可为空
								name : "bankpayapplyother.bpaInvoiceBalance",
								id:"bpaInvoiceBalance",
//								allowBlank:false,
								width:80
							}]
				},  {
					xtype : "panel",
					layout : "column",
					border : false,
					unstyled : false,
					style:'padding-top:5px;',
					items : [{
								xtype : "label",
								text : "本次申请用款:",
								width : 90
							}, {
								xtype : 'container',
								style : 'padding-left:0px;padding-bottom:5px;',
								items : [{
									xtype : 'container',
									layout : 'column',
									style : 'padding-left:0px;padding-bottom:5px;',
									items : [{
										xtype : "label",
										text : "小写:",
										width : 40
									}, {
										xtype : "numberfield",
										cls:'textfieldBG',
										id:'bpaApplyMoneyXX',
										name : "bankpayapplyother.bpaApplyMoneyXX",
										allowBlank:false,
										width:490,
										enableKeyEvents:true,
										regex:/^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/,
										regexText:'请输入正确格式的金额',
										listeners : { 
											keyup: function(field) { 	
												var paymentSumBig = AmountInWords(field.getValue());
												Ext.getCmp('bpaApplyMoneyDX').setValue(paymentSumBig); 
											} 
										} 
									}]
								},{
									xtype : 'container',
									layout : 'column',
									style : 'padding-left:0px;padding-bottom:5px;',
									items : [{
										xtype : "label",
										text : "大写:",
										width : 40
									}, {
										xtype : "textfield",
										id:'bpaApplyMoneyDX',
										name : "bankpayapplyother.bpaApplyMoneyDX",
										width: 490,
										allowBlank:false,
										readOnly:true,
										editable:false,
										style:"background:#F0F0F0;"
									}]
								}]
							}]
				},{
						xtype : 'container',
						autoHeight : true,
						layout : 'column',
						autoWidth : true,
						defaultType : 'label',
						style : 'padding-left:0px;padding-bottom:5px;',
						items : [{
									text : '附件:',
									width : 80,
									style : "margin-top:15px;"
								},{
									xtype : 'hidden',
									name : 'bankpayapplyother.attachIds',
									id : 'bankpayapplyother.attachIds'
								},{
									xtype : 'hidden',
									name : 'bankpayapplyother.attachFiles',
									id : 'bankpayapplyother.attachFiles'
								},{
									xtype : 'panel',
									id:'bankpayapplyother.displayAttachOT',
									width : 480,
									height: 65,
									frame:false,
									autoScroll:true,
									style : 'padding-left:10px;padding-top:0px;',
									html : ''
								},{
									xtype : 'button',
									iconCls : 'btn-upload',
									text : '上传',
									handler : function() {
										var dialog = App.createUploadDialog({
																file_cat : 'flow/bankPayApplyOther',
																callback : function(data) {
																	Ext.getCmp('bankpayapplyother.displayAttachOT').body.update('');
																	Ext.getCmp("bankpayapplyother.attachFiles").setValue('');
																	Ext.getCmp('bankpayapplyother.attachIds').setValue('');
																	var contractFile = Ext.getCmp("bankpayapplyother.attachFiles");
																	var fileIds = Ext.getCmp('bankpayapplyother.attachIds');
																	var display = Ext.getCmp('bankpayapplyother.displayAttachOT');
																	for (var i = 0; i < data.length; i++) {
																		if (fileIds.getValue() != '') {
																			fileIds.setValue(fileIds.getValue()+ ',');
																			contractFile.setValue(contractFile.getValue()+ ',');
																		}
																		contractFile.setValue(contractFile.getValue()+data[i].filepath+":"+data[i].filename.replace(/\s+/g,""));
//																		contractFile.setValue(contractFile.getValue()+"<a href=javascript:window.location.href=\""+__ctxPath+"/file-Download?filePath=attachFiles/"+data[i].filepath+"&fileName="+data[i].filename.replace(/\s+/g,"")+"\">"+data[i].filename.replace(/\s+/g,"")+"</a>");
																		fileIds.setValue(fileIds.getValue()+data[i].fileId);
																		Ext.DomHelper.append(display.body,'<span><a href="#" onclick="FileAttachDetail.show('+data[i].fileId+')">'+data[i].filename.replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
																	}
																},
																permitted_max_size :1024*1024*20
															});
											dialog.show(this);
									}
								}]
				},{
							xtype : 'panel',
							layout : 'column',
							defaultType : 'label',
							border:false,
							items:[
								{
									text : '经办人员:',
									width : 90
								},
								{
									xtype:'textfield',
									name:'bankpayapplyother.bpaRemark',
									id:'bpaRemark',
									allowBlank:false,
									readOnly:true,
									value:__currentUser,
									width: 530,
									style:"background:#F0F0F0;"
								}
							]
							
				}
					]
				});
		// 加载表单对应的数据
		if (this.bankPayApplyId != null && this.bankPayApplyId != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath
						+ '/statistics/getBankpayapplyother.do?bankPayApplyId='
						+ this.bankPayApplyId,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON.decode(action.response.responseText);
					var res = jsonData.data;
					var attachId = res.attachIds;
					var attachFile = res.attachFiles;
					if(attachId!= null && attachId != 'undefined' && attachFile!= null && attachFile != 'undefined'){
						Ext.getCmp("bankpayapplyother.attachIds").setValue(res.attachIds);
						Ext.getCmp("bankpayapplyother.attachIds").originalValue = res.attachIds;
						Ext.getCmp("bankpayapplyother.attachFiles").setValue(res.attachFiles);
						Ext.getCmp("bankpayapplyother.attachFiles").originalValue = res.attachFiles;
							var ids = attachId.split(',');
							var files= attachFile.split(',');
							for(var i=0;i<ids.length;i++){
								if(files[i].lastIndexOf('<a href')!=-1){
									  Ext.DomHelper.append(Ext.getCmp('bankpayapplyother.displayAttachOT').body,'<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+files[i].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
								}else if(files[i].lastIndexOf(':')!=-1){
									  var fg = files[i].split(':');
									  Ext.DomHelper.append(Ext.getCmp('bankpayapplyother.displayAttachOT').body,'<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+fg[1].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
								}
							}
					}
				},
				failure : function(form, action) {
				}
			});
		}
		// 初始化功能按钮
		this.buttons = [
				 {
					 text : '保存',
					 iconCls : 'btn-save',
					 handler :this.save.createCallback(this.formPanel,this)
				 }, {
					 text : '重置',
					 iconCls : 'btn-reset',
					 handler :this.reset.createCallback(this.formPanel)
				 },{
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
							var gridPanel = Ext.getCmp('BankpayapplyotherGrid');
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