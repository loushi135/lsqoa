/**
 * @author 
 * @createtime 
 * @class BankpayapplyForm
 * @extends Ext.Window
 * @description Bankpayapply表单
 */
BankpayapplyForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		BankpayapplyForm.superclass.constructor.call(this,{
			id:'BankpayapplyFormWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			width : 700,
			height : 550,
			maximizable:true,
			title:'工程项目银行付款详细信息',
			buttonAlign : 'center',
			buttons:this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents:function(){
		var _url = __ctxPath + '/system/listWorkDepartment.do?opt=appUser';
		var depSelector = new TreeSelector('bankpayapply.payprojectRegional', _url, '',
				'payprojectRegional', true, 523,'payprojectRegional');
		Ext.getCmp("bankpayapply.payprojectRegionalTree").on("click", function(g) {
			//Ext.getCmp("bankpayapply.projectRegional").setValue(Ext.getCmp("bankPayDepTreeSelector").getValue());
			Ext.getCmp("bankpayapply.bpaDeptId").setValue(Ext.getCmp("bankpayapply.payprojectRegional").id);
		});
		this.formPanel=new Ext.FormPanel({
				layout : 'form',
				autoScroll : true,
				trackResetOnLoad:true,
//			    bodyStyle: 'padding:10px 10px 10px 10px;overflow-x:hidden;overflow-y:scroll',
			    bodyStyle: 'padding:10px 10px 10px 10px;',
				border:false,
				url : __ctxPath + '/statistics/saveBankpayapply.do',
				id : 'BankpayapplyForm',
				defaults : {
					anchor : '95%,95%'
				},
				defaultType : 'textfield',
				items : [{
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
							},{
								xtype:'hidden',
								name:'bankpayapply.bankPayApplyId',
								value:this.bankPayApplyId
							},{
								xtype:'hidden',
								name:'bankpayapply.materialContract.id',
								id:'bankpayapply.materialContract.id'
							},{
								xtype:'hidden',
								name:'bankpayapply.payMoneyDate',
								id:'payMoneyDate'
							},{
								xtype:'hidden',
								name:'bankpayapply.bpaApplyMoneyXX',
								id:'bpaApplyMoneyXX'
							},{
								xtype:'hidden',
								name:'bankpayapply.bpaApplyMoneyDX',
								id:'bpaApplyMoneyDX'
							},{
								xtype:'hidden',
								name:'bankpayapply.processRunId',
								value:-1
							},{
								xtype : "textfield",
								name : "bankpayapply.bpaProjectName",
								id:'bpaProjectName',
								emptyText:'请选择项目',
								width:140,
								allowBlank:false,
								readOnly:true,
								style:"background:#F0F0F0;",
								listeners:{
									focus:function(){
										new MaterialContractSelector(function(id,contractNo,contractAmount,proName,proNo,suppliersName,suppliersId,projectId){
												Ext.getCmp("bpaProjectNo").setValue(proNo);
												Ext.getCmp("bpaProjectName").setValue(proName);
												Ext.getCmp("bankpayapply.materialContract.id").setValue(id);
												if(suppliersName!=null&&suppliersName!=''&&typeof(suppliersName)!='undefined'){
													Ext.getCmp("bpaReceiptDept").setValue(suppliersName);
												}else{
													Ext.getCmp("bpaReceiptDept").setValue('');
												}
												if(contractAmount!=null&&contractAmount!=''){
													Ext.getCmp("bpaContract").setValue(contractAmount);
												}else{
													Ext.getCmp("bpaContract").setValue('');
												}
												if(suppliersId!=null&&suppliersId!=''&&typeof(suppliersId)!='undefined'){
													Ext.getCmp("bankpayapply.suppliersAssess.suppliersId").setValue(suppliersId);
													var url=__ctxPath+'/statistics/getPayedByProNoBankpayapply.do';//获取累计已付款金额  = 同一材料付款下所有的工程银行付款和付款基数之和
													var params='Q_materialContract.id_L_EQ='+id+"&proId="+projectId+"&suppId="+suppliersId; ;
													var data = ajaxSyncCall(url,params).data;
													if(data!=null){
														Ext.getCmp("bpaSumMoney").setValue(data);
													}else{
														Ext.getCmp("bpaSumMoney").setValue('');
													}
													Ext.getCmp("bpaSumMoney").fireEvent('keyup');
												}else{
													Ext.getCmp("bankpayapply.suppliersAssess.suppliersId").setValue('');
													Ext.getCmp("bpaSumMoney").setValue('');
													Ext.getCmp("bpaSumMoney").fireEvent('keyup');
												}
												var url=__ctxPath+'/statistics/getProjectByNoProjectNew.do';
												var params='Q_proNo_S_EQ='+proNo;
												var data = ajaxSyncCall(url,params).data;
												if(data!=null&&data.contract!=null&&data.contract.deptRegional!=null){
													Ext.getCmp("bankpayapply.bpaDeptId").setValue(data.contract.deptRegional.depId);
													Ext.getCmp("bankpayapply.payprojectRegional").setValue(data.contract.deptRegional.depName);
												}else{
													Ext.getCmp("bankpayapply.bpaDeptId").setValue('');
													Ext.getCmp("bankpayapply.payprojectRegional").setValue('');
												}
												//得到 资金结余等
												var url2 = __ctxPath+'/statistics/getApplyDataBankpayapply.do';
												var params2 = 'proNo='+proNo+"&materialId="+id+"&projectId="+projectId+"&suppliersId="+suppliersId;
												var data2 = ajaxSyncCall(url2,params2).data;
												if(!Ext.isEmpty(data2.leftMoney)){
													Ext.getCmp('bpaFundBalance').setValue(data2.leftMoney);
												}
												if(!Ext.isEmpty(data2.quota)){
													Ext.getCmp('bpaPayRatio').setValue(data2.quota);
												}
												if(!Ext.isEmpty(data2.leftBill)){
													Ext.getCmp('bpaInvoiceBalance').setValue(data2.leftBill);
												}else{
													Ext.getCmp('bpaInvoiceBalance').setValue("");
												}
											},true).show();
									}
								}
							}, {
								xtype : "label",
								name : "MyLabel2",
								text : "项目编号:",
								style : "padding-left:5px;"
							}, {
								xtype : "textfield",
								name : "bankpayapply.bpaProjectNo",
								id:'bpaProjectNo',
								width:160,
								emptyText:'请选择项目',
								allowBlank:false,
								readOnly:true,
								style:"background:#F0F0F0;",
								listeners:{
									focus:function(){
										new MaterialContractSelector(function(id,contractNo,contractAmount,proName,proNo,suppliersName,suppliersId,projectId){
												Ext.getCmp("bpaProjectNo").setValue(proNo);
												Ext.getCmp("bpaProjectName").setValue(proName);
												Ext.getCmp("bankpayapply.materialContract.id").setValue(id);
												if(suppliersName!=null&&suppliersName!=''&&typeof(suppliersName)!='undefined'){
													Ext.getCmp("bpaReceiptDept").setValue(suppliersName);
												}else{
													Ext.getCmp("bpaReceiptDept").setValue('');
												}
												if(contractAmount!=null&&contractAmount!=''){
													Ext.getCmp("bpaContract").setValue(contractAmount);
												}else{
													Ext.getCmp("bpaContract").setValue('');
												}
												if(suppliersId!=null&&suppliersId!=''&&typeof(suppliersId)!='undefined'){
													Ext.getCmp("bankpayapply.suppliersAssess.suppliersId").setValue(suppliersId);
													var url=__ctxPath+'/statistics/getPayedByProNoBankpayapply.do';//获取累计已付款金额  = 同一材料付款下所有的工程银行付款和付款基数之和
													var params='Q_materialContract.id_L_EQ='+id+"&proId="+projectId+"&suppId="+suppliersId; ;
													var data = ajaxSyncCall(url,params).data;
													if(data!=null){
														Ext.getCmp("bpaSumMoney").setValue(data);
													}else{
														Ext.getCmp("bpaSumMoney").setValue('');
													}
													Ext.getCmp("bpaSumMoney").fireEvent('keyup');
												}else{
													Ext.getCmp("bankpayapply.suppliersAssess.suppliersId").setValue('');
													Ext.getCmp("bpaSumMoney").setValue('');
													Ext.getCmp("bpaSumMoney").fireEvent('keyup');
												}
												var url=__ctxPath+'/statistics/getProjectByNoProjectNew.do';
												var params='Q_proNo_S_EQ='+proNo;
												var data = ajaxSyncCall(url,params).data;
												if(data!=null&&data.contract!=null&&data.contract.deptRegional!=null){
													Ext.getCmp("bankpayapply.bpaDeptId").setValue(data.contract.deptRegional.depId);
													Ext.getCmp("bankpayapply.payprojectRegional").setValue(data.contract.deptRegional.depName);
												}else{
													Ext.getCmp("bankpayapply.bpaDeptId").setValue('');
													Ext.getCmp("bankpayapply.payprojectRegional").setValue('');
												}
												//得到 资金结余等
												var url2 = __ctxPath+'/statistics/getApplyDataBankpayapply.do';
												var params2 = 'proNo='+proNo+"&materialId="+id+"&projectId="+projectId+"&suppliersId="+suppliersId;
												var data2 = ajaxSyncCall(url2,params2).data;
												if(!Ext.isEmpty(data2.leftMoney)){
													Ext.getCmp('bpaFundBalance').setValue(data2.leftMoney);
												}
												if(!Ext.isEmpty(data2.quota)){
													Ext.getCmp('bpaPayRatio').setValue(data2.quota);
												}
												if(!Ext.isEmpty(data2.leftBill)){
													Ext.getCmp('bpaInvoiceBalance').setValue(data2.leftBill);
												}else{
													Ext.getCmp('bpaInvoiceBalance').setValue("");
												}
											},true).show();
									}
								}
							}, {
								xtype : "label",
								name : "MyLabel3",
								text : "付款类别:",
								style : "padding-left:5px;"
							}, {
								xtype : "combo",
								hiddenName : "bankpayapply.bpaPayType",
								id:'bpaPayType',
								allowBlank:false,
								triggerAction :'all',
								width:115,
								editable :false,
								store:[['主材（战略）','主材（战略）'],['主材（无战略）','主材（无战略）'],['辅材','辅材'],['零星材料','零星材料'],['尾款结算','尾款结算'],['单包','单包'],['小双包','小双包'],['大双包','大双包']]
							}]
				},
				{
					xtype : "panel",
					layout : "column",
					border : false,
					unstyled : false,
					style:'margin-top:5px;',
					anchor:"100%",
					items : [{
								xtype : "label",
								text : "收款单位:",
								width : 90
							}, {
								xtype : "textfield",
								name : "bankpayapply.bpaReceiptDept",
								id:'bpaReceiptDept',
								width: 528,
								allowBlank:false,
								readOnly:true,
								listeners:{
									focus:function(){
										new SuppliersAssessSelector(function(suppliersId,supplierName,supplierNo){
												Ext.getCmp("bpaReceiptDept").setValue(supplierName);
												Ext.getCmp("bankpayapply.suppliersAssess.suppliersId").setValue(suppliersId);
												var proNo = Ext.getCmp("bpaProjectNo").getValue();
												if(proNo!=null&&proNo!=''&&typeof(proNo)!='undefined'){
													var url=__ctxPath+'/statistics/getPayedByProNoBankpayapply.do';
													var params='Q_bpaProjectNo_S_EQ='+proNo+"&Q_suppliersAssess.suppliersId_L_EQ="+suppliersId;
													var data = ajaxSyncCall(url,params).data;
													if(data!=null){
														Ext.getCmp("bpaSumMoney").setValue(data);
													}else{
														Ext.getCmp("bpaSumMoney").setValue('');
													}
													Ext.getCmp("bpaSumMoney").fireEvent('keyup');
												}
											},true).show();
									}
								}
							},{
								xtype:'hidden',
								name:'bankpayapply.suppliersAssess.suppliersId',
								id:'bankpayapply.suppliersAssess.suppliersId'
							}
							]
				}, {
					xtype : "panel",
					layout : "column",
					border : false,
					unstyled : false,
					style:'margin-top:5px;',
					anchor:"100%",
					items : [{
								xtype : "label",
								text : "预决算员:",
								width : 90
							}, {
								xtype:'combo',
								mode : 'remote',
								editable : false,
//								allowBlank:false,
								width: 523,
								valueField : 'userId',
								displayField : 'fullname',
								triggerAction : 'all',
								store : new Ext.data.JsonStore({
										autoLoad:true,
										url : __ctxPath
												+ '/system/listAppUser.do',
										baseParams:{'Q_roles.roleName_S_EQ':'流程-预决算员'},
										root : 'result',
										totalProperty : 'totalCounts',
										fields : [{name:'userId',type:'int'},'fullname']
									}),
								hiddenName:'bpaPreCalcUserId',
								id:'bankpayapply.bpaPreCalcUserId',
								anchor:'90%',
								listeners:{
										select : function(combo, record,index) {
														var preCalcUsername = record.data.fullname;
														Ext.getCmp("bankpayapply.bpaPreCalcUsername").setValue(preCalcUsername);
												}
								}
							},
							{
								xtype:'hidden',
								name:'bpaPreCalcUsername',
								id:'bankpayapply.bpaPreCalcUsername'
							}
							]
				}, {
					xtype : "panel",
					layout : "column",
					border : false,
					unstyled : false,
					style:'margin-top:5px;',
					anchor:"100%",
					items : [{
								xtype : "label",
								text : "类型:",
								width : 90
							}, {
								xtype:'combo',
								mode : 'local',
								editable : false,
								allowBlank:false,
								width: 523,
								triggerAction : 'all',
								store : ['区域','经理'],
								hiddenName:'bpaDeptType',
								id:'bankpayapply.bpaDeptType',
								allowBlank:false,
								value:'区域',
								anchor:'90%',
								listeners:{
										select : function(combo, record,index) {
														if(combo.getValue()=='经理'){
															Ext.getCmp("bankpayapply.payprojectRegional").hide();
															Ext.getCmp("bankpayapply.payprojectRegionalLabel").hide();
															Ext.getCmp("bankpayapply.bpaDeptContainer").show();
														}else if(combo.getValue()=='区域'){
															Ext.getCmp("bankpayapply.payprojectRegional").show();
															Ext.getCmp("bankpayapply.payprojectRegionalLabel").show();
															Ext.getCmp("bankpayapply.bpaDeptContainer").hide();
														}
												 }
								}
							}
							]
				}, {
					xtype : "panel",
					layout : "column",
					border : false,
					unstyled : false,
					style:'margin-top:5px;',
					anchor:"100%",
					items : [{
								xtype : "label",
								text : "施工区域:",
								id:'bankpayapply.payprojectRegionalLabel',
								width : 90
							}
							,depSelector
							, {
								xtype : 'hidden',
								name : 'bpaDeptId',
								id : 'bankpayapply.bpaDeptId'
							}
							]
				},{
					xtype:'container',
					id:'bankpayapply.bpaDeptContainer',
					layout : "column",
					border : false,
					unstyled : false,
					style:'margin-top:5px;',
					anchor:"100%",
					items:[
						{
							xtype:'label',
							id:'bankpayapply.bpaDeptChargerLabel',
							width : 90,
							text:'区域经理:'
						},
						{
							xtype:"textfield",
							name:"bankpayapply.bpaDeptChargerName",
							id:'bpaDeptChargerName',
							width: 528,
							listeners : {
								'focus' : function() {
									UserSelector.getView(function(n,l,mobile) {
														var bpaDeptChargerId = Ext.getCmp("bankpayapply.bpaDeptCharger.userId");
														var bpaDeptCharger = Ext.getCmp("bpaDeptChargerName");
														bpaDeptChargerId.setValue(n);
														bpaDeptCharger.setValue(l);
													},true)
											.show()
								}
							}
						},{
							xtype:'hidden',
							name:'bankpayapply.bpaDeptCharger.userId',
							id:'bankpayapply.bpaDeptCharger.userId',
							listeners :{
										afterrender:function(container){
												Ext.getCmp("bankpayapply.bpaDeptContainer").hide();
										}
									}
						}
					]
				}, {
					xtype : "panel",
					layout : "column",
					border : false,
					unstyled : false,
					style:'margin-top:5px;',
					items : [{
								xtype : "label",
								name : "MyLabel1",
								text : "付款事由:",
								height : 50,
								width : 90
							}, {
								xtype : "textarea",
								name : "bankpayapply.bpaReceiptReason",
								id:'bpaReceiptReason',
								maxLength:190,
								allowBlank:false,
								width: 522,
								height:50
							}]
				}, {
					xtype : "panel",
					layout : "column",
					border : false,
					unstyled : false,
					style:'margin-top:5px;',
					items : [{
								xtype : "label",
								text : "合同/结算金额:",
								width : 90
							}, {
								xtype : "numberfield",
								regex:/^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/,
								name : "bankpayapply.bpaContract",
								id:'bpaContract',
								allowBlank:false,
								width:120,
								decimalPrecision:2,
								enableKeyEvents:true,
								listeners : {
									keyup: function(field) {
										var bpaSumMoney=Ext.getCmp('bpaSumMoney').getValue();
										var bpaContract=Ext.getCmp('bpaContract').getValue();
										Ext.getCmp('bpaSumMoneyRatio').setValue(((bpaSumMoney*100)/bpaContract).toFixed(2));
									}
								}
							}, {
								xtype : "label",
								text : "累计已付款:",
								width : 90,
								style : "padding-left:5px;"
							}, {
								xtype : "numberfield",
								regex:/^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/,
								name : "bankpayapply.bpaSumMoney",
								id:'bpaSumMoney',
								allowBlank:false,
								width:120,
								decimalPrecision:2,
								enableKeyEvents:true,
								listeners : { 
									keyup: function(field) {
										var bpaSumMoney=Ext.getCmp('bpaSumMoney').getValue();
										var bpaContract=Ext.getCmp('bpaContract').getValue();
										Ext.getCmp('bpaSumMoneyRatio').setValue(((bpaSumMoney*100)/bpaContract).toFixed(2));
									}
								}
							}, {
								xtype : "label",
								text : "已付款比例(%):",
								width : 90,
								style : "padding-left:5px;"
							}, {
								xtype : "numberfield",
								name : "bankpayapply.bpaSumMoneyRatio",
								id:'bpaSumMoneyRatio',
								style:"background:#F0F0F0;",
								allowBlank:false,
								editable:false,
								readOnly:true,
								width:120,
								decimalPrecision:2,
								listeners : { 
									focus: function(field) {
										var bpaSumMoney=Ext.getCmp('bpaSumMoney').getValue();
										var bpaContract=Ext.getCmp('bpaContract').getValue();
										Ext.getCmp('bpaSumMoneyRatio').setValue(((bpaSumMoney*100)/bpaContract).toFixed(2));
									}
								}
							}]
				}, {
					xtype : "panel",
					layout : "column",
					border : false,
					unstyled : false,
					style:'margin-top:5px;',
					items : [{
								xtype : "label",
								text : "资金结余:",
								width : 90
							}, {
								xtype : "numberfield",
								regex:/(^$)|^\-?(([1-9]\d{0,9})|0)(\.\d{1,2})?$/,  //加上（^$）表示可为空  加\-?表示可以为负数
								name : "bankpayapply.bpaFundBalance",
								id : "bpaFundBalance",
								allowBlank:true,
								width:120
							}, {
								xtype : "label",
								text : "垫资审批额度:",
								width : 90,
								style : "padding-left:5px;"
							}, {
								xtype : "numberfield",
								regex:/(^$)|^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/,  //加上（^$）表示可为空
								name : "bankpayapply.bpaPayRatio",
								id : "bpaPayRatio",
								allowBlank:true,
								width:120
							}, {
								xtype : "label",
								text : "发票结余:",
								width : 90,
								style : "padding-left:5px;"
							}, {
								xtype : "numberfield",
//							    regex:/(^$)|^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/,  //加上（^$）表示可为空
								name : "bankpayapply.bpaInvoiceBalance",
								id : "bpaInvoiceBalance",
								allowBlank:true,
								width:120
							}]
				}, {
					xtype : "panel",
					layout : "column",
					border : false,
					unstyled : false,
					style:'padding-top:5px;',
					items : [{
								xtype : "label",
								text : "本次申请用款:",
								width : 90,
								style : "margin-top:15px;"
							}, {
								xtype : 'container',
								id : 'thisApplyContainer',
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
										id:'bpaApplyFirstMoneyXX',
										name : "bankpayapply.bpaApplyFirstMoneyXX",
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
										id:'bpaApplyFirstMoneyDX',
										name : "bankpayapply.bpaApplyFirstMoneyDX",
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
												text : '合同附件:',
												width : 80,
												style : "margin-top:15px;"
											},{
												xtype : 'hidden',
												name : 'bankpayapply.attachIds',
												id : 'attachIds'
											},{
												xtype : 'hidden',
												name : 'bankpayapply.attachFiles',
												id : 'attachFiles'
											},{
												xtype : 'panel',
												id:'bankpayapply.bpaDisplayAttach',
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
																			file_cat : 'flow/bankPayApply',
																			callback : function(data) {
																				Ext.getCmp('bankpayapply.bpaDisplayAttach').body.update('');
																				Ext.getCmp("attachFiles").setValue('');
																				Ext.getCmp('attachIds').setValue('');
																				var contractFile = Ext.getCmp("attachFiles");
																				var fileIds = Ext.getCmp('attachIds');
																				var display = Ext.getCmp('bankpayapply.bpaDisplayAttach');
																				for (var i = 0; i < data.length; i++) {
																					if (fileIds.getValue() != '') {
																						fileIds.setValue(fileIds.getValue()+ ',');
																						contractFile.setValue(contractFile.getValue()+ ',');
																					}
																					contractFile.setValue(contractFile.getValue()+data[i].filepath+":"+data[i].filename.replace(/\s+/g,""));
//																					contractFile.setValue(contractFile.getValue()+"<a href=javascript:window.location.href=\""+__ctxPath+"/file-Download?filePath=attachFiles/"+data[i].filepath+"&fileName="+data[i].filename.replace(/\s+/g,"")+"\">"+data[i].filename.replace(/\s+/g,"")+"</a>");
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
									name:'bankpayapply.bpaRemark',
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
		//加载表单对应的数据	
		if (this.bankPayApplyId != null && this.bankPayApplyId != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getBankpayapply.do?bankPayApplyId='+ this.bankPayApplyId,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON.decode(action.response.responseText);
					var res = jsonData.data;
					Ext.getCmp("bankpayapply.suppliersAssess.suppliersId").setValue(res.suppliersAssess.suppliersId);
					Ext.getCmp("bankpayapply.suppliersAssess.suppliersId").originalValue = res.suppliersAssess.suppliersId;
					var attachId = res.attachIds;
					var attachFile = res.attachFiles;
					if(attachId!= null && attachId != 'undefined' && attachFile!= null && attachFile != 'undefined'){
							Ext.getCmp("attachIds").setValue(res.attachIds);
							Ext.getCmp("attachIds").originalValue = res.attachIds;
							Ext.getCmp("attachFiles").setValue(res.attachFiles);
							Ext.getCmp("attachFiles").originalValue = res.attachFiles;
							var ids = attachId.split(',');
							var files= attachFile.split(',');
							for(var i=0;i<ids.length;i++){
								if(files[i].lastIndexOf('<a href')!=-1){
									  Ext.DomHelper.append(Ext.getCmp('bankpayapply.bpaDisplayAttach').body,'<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+files[i].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
								}else if(files[i].lastIndexOf(':')!=-1){
									  var fg = files[i].split(':');
									  Ext.DomHelper.append(Ext.getCmp('bankpayapply.bpaDisplayAttach').body,'<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+fg[1].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
								}
							}
					}
					if(!Ext.isEmpty(res.bpaDeptCharger)){
						Ext.getCmp("bankpayapply.bpaDeptCharger.userId").setValue(res.bpaDeptCharger.userId);
						Ext.getCmp("bankpayapply.bpaDeptCharger.userId").originalValue = res.bpaDeptCharger.userId;
						Ext.getCmp("bankpayapply.payprojectRegional").hide();
						Ext.getCmp("bankpayapply.payprojectRegionalLabel").hide();
						Ext.getCmp("bankpayapply.bpaDeptContainer").show();
						Ext.getCmp("bankpayapply.bpaDeptType").setValue("经理");
						Ext.getCmp("bankpayapply.bpaDeptType").originalValue = "经理";
					}else{
						Ext.getCmp("bankpayapply.payprojectRegional").show();
						Ext.getCmp("bankpayapply.payprojectRegionalLabel").show();
						Ext.getCmp("bankpayapply.bpaDeptContainer").hide();
						Ext.getCmp("bankpayapply.bpaDeptType").setValue("区域");
						Ext.getCmp("bankpayapply.bpaDeptType").originalValue = "区域";
					}
					if(!Ext.isEmpty(res.materialContract)){
						Ext.getCmp("bankpayapply.materialContract.id").setValue(res.materialContract.id);
						Ext.getCmp("bankpayapply.materialContract.id").originalValue = res.materialContract.id;
					}
				},
				failure : function(form, action) {
				}
			});
		}
		//初始化功能按钮
		this.buttons=[
				{
					text : '保存',
					iconCls : 'btn-save',
					handler :this.save.createCallback(this.formPanel,this)
				}, {
					text : '重置',
					iconCls : 'btn-reset',
					handler :this.reset.createCallback(this.formPanel)
				},
				{
					text : '关闭',
					iconCls : 'btn-cancel',
					handler : this.cancel.createCallback(this)
			}];
	},//end of the initcomponents
	
	/**
	 * 重置
	 * @param {} formPanel
	 */
	reset:function(formPanel){
		formPanel.getForm().reset();
	},
	/**
	 * 取消
	 * @param {} window
	 */
	cancel:function(window){
		window.close();
	},
	/**
	 * 保存记录
	 */
	save:function(formPanel,window){
		if (formPanel.getForm().isValid()) {
			formPanel.getForm().submit({
				method : 'POST',
				waitMsg : '正在提交数据...',
				success : function(fp, action) {
					Ext.ux.Toast.msg('操作信息', '成功保存信息！');
					var gridPanel=Ext.getCmp('BankpayapplyGrid');
					if(gridPanel!=null){
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
	}//end of save
	
});