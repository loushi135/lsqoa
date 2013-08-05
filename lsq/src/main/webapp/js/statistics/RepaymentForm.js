/**
 * @author 
 * @createtime 
 * @class RepaymentForm
 * @extends Ext.Window
 * @description RepaymentForm表单
 */
RepaymentForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		RepaymentForm.superclass.constructor.call(this,{
			id:'RepaymentFormWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			autoScroll : true,
			width : 700,
			height : 550,
			maximizable:true,
			title:'还款详细信息',
			buttonAlign : 'center',
			buttons:this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents:function(){
		var thiz = this;
		this.formPanel=new Ext.FormPanel({
				layout : 'form',
				bodyStyle: 'padding:10px 10px 10px 10px;overflow-x:hidden;overflow-y:scroll',
				border:false,
				labelWidth : 30,
				url : __ctxPath + '/statistics/savePaymentList.do',
				id : 'RepaymentForm',
				defaults : {
					anchor : '98%,98%'
				},
				defaultType : 'textfield',
				items : [{
								xtype : "container",
								style : "padding-left:0px;margin-bottom:4px;",
								layout : "column",
							    items:[{
							           xtype:'label',
							           text:'类别选择：'
							        },
							        {
							        	xtype : "combo",
										hiddenName : "payOption",
										id : "payOptionId",
										allowBlank : false,
										mode : 'local',
										store : ['工程', '其他'],
										value:'工程',
										triggerAction : 'all',
										readOnly:true,
										width : 125,
										editable : false,
							        	listeners:{
							        		select : function(combo,record,index){
							        			if(combo.getValue()=='工程'){
							        				Ext.getCmp("proNameLabel").show();
							        				Ext.getCmp("proName").show();
							        			}else{
							        				Ext.getCmp("proId").setValue("");
							        				Ext.getCmp("proName").setValue("");
							        				Ext.getCmp("proName").hide();
							        				Ext.getCmp("proNameLabel").hide();
							        			}
							        		}
							        		
							        	}
							        	
							        },{
										xtype : 'label',
										id:'proNameLabel',
										style:'padding-left:3px;padding-right:12px;',
										text : '项目名称：'
									},{
										xtype:'hidden',
										name:'proId',
										id:'proId'
									}, {
										xtype : 'textfield',
										allowBlank : false,
										readOnly:true,
										id:'proName',
										name:'proName',
										emptyText :'请选择项目',
										width : 390
//									    listeners:{
//											   'focus':function(){
//													ProjectNewSelector.getView(function(proId,proName,proNo,contractId,contractNo,contractAmount){
//														Ext.getCmp("proName").setValue(proName);
//														Ext.getCmp("proId").setValue(proId);
//													},true).show();
//												}
//												
//											}
									}]
				        },{
				        	xtype : "container",
							style : "padding-left:0px;margin-bottom:4px;",
							layout : "column",
				        	items:[
									 {
										xtype : 'label',
										text : '借款人：',
										style:'padding-right:12px;'
									},{
										xtype:'hidden',
										id:'borrowerId'
									}, {
										xtype : 'textfield',
										readOnly:true,
										id:'borrower',
										name:'borrower',
										width : 130
//										listeners:{
//											   'focus':function(){
//											   		UserSelector.getView(function(userId, username) {
//													Ext.getCmp("borrower").setValue(username);
//													Ext.getCmp("borrowerId").setValue(userId);
//												}, true).show();
//												}
//											}
									},{
										xtype : 'label',
										style:'padding-left:3px;padding-right:12px;',
										text : '暂支类别：'
									},{
										xtype : 'textfield',
										allowBlank : false,
										readOnly:true,
										id:'paymentType',
										name:'paymentType',
										width : 130
									}
				        	]
				        }
				        ,{
					        xtype:'fieldset',
					        title: '还款',
					        autoHeight:true,
					        border:true,
					        defaults: {
					            anchor: '-10' // leave room for error icon
					        },
					        viewConfig : {
								forceFit : true
							},
							items:[{
								xtype : "container",
								style : "padding-left:0px;margin-bottom:4px;",
								layout : "column",
						  		items:[
						  			{
										xtype : 'label',
										text : '还款金额：'
									},{
										xtype : 'hidden',
										id:'returnAmountBig',
										name:'returnAmountBig'
									}, {
										xtype : 'numberfield',
										id:'returnAmount',
										name:'returnAmount',
										maxLength:8,
										width : 130,
									    enableKeyEvents :true,
										listeners:{
										  	keyup : function(field){
										  		var value = Ext.getCmp("returnAmount").getValue();
										  	    var returnAmountBig = AmountInWords(value);
										  	    Ext.getCmp("returnAmountBig").setValue(returnAmountBig);
										  	    Ext.getCmp("returnAmountBigLabel").setText("("+returnAmountBig+")");
										  	}
										}
									},{
										xtype:'label',
										id:'returnAmountBigLabel',
										style:'color:red'
									}, {
										xtype : 'button',
										text : '保存',
										iconCls : 'btn-save',
								        style:'padding-left:3px;',
								        handler : this.returnMoney.createCallback(this)
									}
						  		]
						  }]
						},
				        {	       
					        xtype:'fieldset',
					        title: '暂支信息',
					        autoHeight:true,
					        border:true,
					        defaults: {
					            anchor: '-10' // leave room for error icon
					        },
					        viewConfig : {
								forceFit : true
							},
							items:[
								{
									xtype:'container',
									style:'padding-left:0px;margin-top:5px;',
									layout:'column',
									items:[
											{
													xtype:'label',
													text:'此暂支单借款:',
													width:100
												},
												{
													xtype:'numberfield',
													id : 'totalAmountBorr',
													disabled :true,
													width:130
												},{
													xtype:'label',
													text:'元',
													width:30
												},{
													xtype:'label',
													style:'color:red',
													id:'totalAmountBorrBig'
												}
									]
								},{
									xtype:'container',
									style:'padding-left:0px;margin-top:5px;',
									layout:'column',
									items:[
											{
													xtype:'label',
													text:'总计还款:',
													width:100
												},
												{
													xtype:'numberfield',
													id : 'totalAmountReturn',
													disabled :true,
													width:130
												},{
													xtype:'label',
													text:'元',
													width:30
												},{
													xtype:'label',
													style:'color:red',
													id:'totalAmountReturnBig'
												}
									]
								},{
									xtype:'container',
									style:'padding-left:0px;margin-top:5px;',
									layout:'column',
									items:[
											{
													xtype:'label',
													text:'目前欠款金额:',
													width:100
												},
												{
													xtype:'numberfield',
													id : 'owedSum',
													disabled :true,
													width:130
												},{
													xtype:'label',
													text:'元',
													width:30
												},{
													xtype:'label',
													style:'color:red',
													id:'owedSumBig'
												}
									]
								}
							]
				        },{
					        xtype:'fieldset',
					        title: '还款记录',
					        autoHeight:true,
					        border:true,
					        defaults: {
					            anchor: '-10' // leave room for error icon
					        },
					        viewConfig : {
								forceFit : true
							},
							items:[
								{
									xtype:'grid',
									id : 'repayGrid',
									height:170,
									width:600,
									trackMouseOver : true,
									disableSelection : false,
									autoScroll : true,
									loadMask : true,
									viewConfig : {
										forceFit : false
									},
									store:new Ext.data.JsonStore({
												url : __ctxPath + "/statistics/listRepayment.do",
												root : 'result',
												totalProperty : 'totalCounts',
												remoteSort : true,
												autoLoad:false,
												fields : [{
															name : 'id',
															type : 'int'
														},
														 'payment',
														 'returnAmount', 'returnDate','operator','returnAmountBig','returnType']
											}),
									cm: new Ext.grid.ColumnModel({
												columns : [new Ext.grid.RowNumberer(), {
																header : 'id',
																dataIndex : 'id',
																hidden : true
															}, {
																header : '还款类别',
																dataIndex : 'returnType'
															}, {
																header : '还款项目',
																dataIndex : 'payment',
																renderer:function(value){
																	if(!Ext.isEmpty(value.project)){
																		return value.project.proName;
																	}
																}
															}, {
																header : '还款人',
																dataIndex : 'payment',
																renderer:function(value){
																	if(!Ext.isEmpty(value.user)){
																		return value.user.fullname;
																	}
																}
															}, {
																header : '还款金额',
																dataIndex : 'returnAmount'
															}, {
																header : '还款金额大写',
																dataIndex : 'returnAmountBig'
															}, {
																header : '还款日期',
																dataIndex : 'returnDate'
															}, {
																header : '操作员',
																dataIndex : 'operator'
															}],
												defaults : {
													sortable : true,
													menuDisabled : false,
													width : 100
												}
											})
								}
							]
				        }
						]
			});
			
		//加载表单对应的数据	
		this.initData();
		
		//初始化功能按钮
		this.buttons=[
			{
				text : '关闭',
				iconCls : 'btn-cancel',
				handler : this.cancel.createCallback(this)
			}];
	},//end of the initcomponents
	/**
	 * 初始化数据
	 * @param {} formPanel
	 */
	initData:function(){
		var thiz = this;
		if (this.paymentId != null && this.paymentId != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getPaymentList.do?id='+ this.paymentId,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON.decode(action.response.responseText);
						var res = jsonData.data;
						var project = res.project;
						Ext.getCmp("proName").setValue(project.proName);
						Ext.getCmp("totalAmountBorr").setValue(res.paymentSumSmall);
						Ext.getCmp("totalAmountBorrBig").setText("("+res.paymentSumBig+")");
						thiz.reloadData(thiz.paymentId,res.paymentSumSmall)
				},
				failure : function(form, action) {
				}
			});
		}
	},
	/**
	 * 取消
	 * @param {} window
	 */
	cancel:function(window){
		var gridPanel=Ext.getCmp('PaymentListGrid');
		if(gridPanel!=null){
			gridPanel.getStore().reload();
		}
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
					var gridPanel=Ext.getCmp('PaymentListGrid');
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
	,
	returnMoney:function(RepaymentForm){
			var returnAmount = Ext.getCmp("returnAmount").getValue();
			if(returnAmount==''){
				Ext.ux.Toast.msg('操作信息','请输入还款金额！');
				return;
			}
			var owedSum = Ext.getCmp('owedSum').getValue();
			if(parseFloat(returnAmount) > parseFloat(owedSum)){
				Ext.ux.Toast.msg('操作信息','还款金额比目前欠款金额大！');
				return;
			}
			var returnAmountBig = Ext.getCmp("returnAmountBig").getValue();
			var payOption = Ext.getCmp("payOptionId").getValue();
			var url=__ctxPath+'/statistics/saveRepayment.do';
			Ext.Ajax.request({
								url:url,
								params:{
										'repayment.payment.id':RepaymentForm.paymentId,
										'repayment.returnAmount':returnAmount,
										'repayment.returnAmountBig':returnAmountBig,
										'repayment.returnType':payOption
										},
								method:'POST',
								success:function(response,options){
									   var data = Ext.util.JSON.decode(response.responseText);
									   if(data.success){
									   		Ext.MessageBox.show({
												title : '操作成功',
												msg : '保存成功',
												buttons : Ext.MessageBox.OK,
												icon : Ext.MessageBox.OK
											});
											RepaymentForm.initData();
											Ext.getCmp("returnAmount").reset();
											Ext.getCmp("returnAmountBigLabel").setText("");
									   }
								},
								failure:function(response,options){
									Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
								}
							});
	},
	reloadData:function(paymentId,totalAmountBorr){
		var	repayParams = {
						start : 0,
						"Q_payment.id_L_EQ" : paymentId,
						limit : 9999
					}
		Ext.getCmp("repayGrid").store.reload({
								params :repayParams,
								callback:function(r,option,success){
									var recordArr1=Ext.getCmp("repayGrid").store.getRange();
									var totalAmountReturn = 0;
									if(recordArr1.length>0){
										for(var i =0;i<recordArr1.length;i++){
											var record = recordArr1[i];
											totalAmountReturn+=parseFloat(record.get("returnAmount"));
										}
									}
									var totalAmountReturnBig = AmountInWords(totalAmountReturn);
									Ext.getCmp("totalAmountReturn").setValue(totalAmountReturn);
									Ext.getCmp("totalAmountReturnBig").setText("("+totalAmountReturnBig+")");
									var owedSum = parseFloat(totalAmountBorr)-parseFloat(totalAmountReturn);
									var owedSumBig = AmountInWords(owedSum);
									Ext.getCmp("owedSum").setValue(owedSum);
									Ext.getCmp("owedSumBig").setText("("+owedSumBig+")");
								}
							});
	}
});