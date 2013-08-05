/**
 * @author
 * @createtime
 * @class ConstructioncontractForm
 * @extends Ext.Window
 * @description Constructioncontract表单
 */
ConstructioncontractForm = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		ConstructioncontractForm.superclass.constructor.call(this, {
					id : 'ConstructioncontractFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					width:782,
					autoScroll : true,
					height:550,
					maximizable : true,
					title : '施工合同详细信息',
					buttonAlign : 'center',
					buttons : this.buttons
					
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		var thiz = this;
		var _url = __ctxPath + '/system/listWorkDepartment.do?opt=appUser';
		var depSelector = new TreeSelector('projectRegional', _url, '施工区域',
				'constructioncontract.projectRegional', false, 147,'constructioncontract.projectRegional');
						
		Ext.getCmp("projectRegionalTree").on("click", function(g) {
			//Ext.getCmp("projectRegional").setValue(Ext.getCmp("flow_projectRegional").getValue());
			Ext.getCmp("deptRegionalId").setValue(Ext.getCmp("projectRegional").id);
		});
		
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			autoScroll : true,
			bodyStyle: 'padding:10px 10px 10px 10px;overflow-x:hidden;overflow-y:scroll',
			border : false,
			url : __ctxPath + '/statistics/saveConstructioncontract.do',
			id : 'ConstructioncontractForm',
			defaults : {
				anchor : '95%,95%'
			},
			padding:"10px",
			labelPad:10,
			defaultType : 'textfield',
			items : [{ 
						xtype:'hidden',	
						name:'constructioncontract.processRunId',
						value:-1
					 },{
						xtype : "container",
						name : "MyContainer",
						autoEl : "div",
						layout : "hbox",
						style : "text-align:center;margin:0px auto;",
						items : [{
									xtype : "label",
									name : "MyLabel",
									text : "施工合同审评表",
									style : "font-size:14px;font-weight:bold",
									region : "west"
								}]
					}, {
						xtype : "container",
						name : "MyContainer8",
						autoEl : "div",
						layout : "column",
						items : [{
									xtype : "container",
									name : "MyContainer9",
									autoEl : "div",
									layout : "vbox",
									width : 400,
									height : 40,
									hideBorders : true,
									items : [{
												xtype : "label",
												name : "MyLabel12",
												text : "a：本表由业务人员在合同签定前，对合同进行评审时用；"
											}, {
												xtype : "label",
												name : "MyLabel13",
												text : "b：本表由法务部、核算部归档；"
											}]
								}, {
									xtype : "label",
									name : "MyLabel14",
									text : "合同编号：",
									style : "margin:3px 0 0 10px",
									width : 110
								}, {
									xtype : "hidden",
									name : "constructioncontract.contractId",
									id:'contractId'
								}, {
									xtype : "hidden",
									name : "constructioncontract.deptRegional.depId",
									id:'deptRegionalId'
								}, {
									xtype : "hidden",
									name : "constructioncontract.projectChargerUser.userId",
									id:'projectChargerUserId'
								}, {
									xtype : "hidden",
									name : "constructioncontract.status",
									id:'status'
								}, {
									xtype : "hidden",
									name : "constructioncontract.contractVersion",
									id:'contractVersion',
									value:0
								}, {
									xtype : "textfield",
									name : "constructioncontract.contractNo",
									id:'contractNo',
									emptyText:'系统自动生成',
									readOnly:true,
									width : 130
								}]
					}, {
						xtype : "container",
						name : "MyContainer1",
						autoEl : "div",
						layout : "column",
						anchor : "96%",
						items : [{
									xtype : "container",
									name : "MyContainer2",
									autoEl : "div",
									layout : "form",
									labelPad : 10,
									labelWidth : "",
									columnWidth : "",
									width : 400,
									items : [{
												xtype : "textfield",
												name : "constructioncontract.contractor",
												id:'contractor',
												fieldLabel : "发包人",
												anchor : "96%"
											}, {
												xtype : "textfield",
												name : "constructioncontract.projectName",
												id:'projectName',
												fieldLabel : "项目名称",
												allowBlank:false,
												anchor : "96%",
												listeners:{
													blur:function(field){
														var proName = field.getValue();
														if(Ext.isEmpty(thiz.contractId)){
															if(!Ext.isEmpty(proName)){
																var url = __ctxPath + '/statistics/checkProNameProjectNew.do';
																var params = "proName="+encodeURI(proName);
																var data = ajaxSyncCall(url,params);
																if(!data.success){
																	Ext.ux.Toast.msg('操作信息', '信息修改，项目名称为:<font color="red">'+field.getValue()+'</font>已经存在，请修改项目名称！');
																	field.setValue('');
																}
															}
														}
													}
												}
											}, {
												xtype : "textfield",
												name : "constructioncontract.projectAbbreviation",
												id:'projectAbbreviation',
												fieldLabel : "项目简称",
												anchor : "96%"
											}, {
												xtype : "textfield",
												name : "constructioncontract.linkmanAndTel",
												id:'linkmanAndTel',
												fieldLabel : "发包联系人及电话",
												anchor : "96%"
											}]
								}, {
									xtype : "container",
									name : "MyContainer3",
									autoEl : "div",
									layout : "form",
									labelPad : 10,
									style : "margin-left:10px",
									items : [depSelector, {
												xtype : "textfield",
												name : "constructioncontract.projectCharger",
												id:'projectCharger',
												fieldLabel : "项目实际负责人",
												anchor : "96%",
												listeners : {
													'focus' : function() {
														UserSelector.getView(function(n,l,mobile) {
																			var projectChargerUserId = Ext.getCmp("projectChargerUserId");
																			var projectCharger = Ext.getCmp("projectCharger");
																			projectChargerUserId.setValue(n);
																			projectCharger.setValue(l);
																			
																		},true)
																.show()
													}
												}
											}, {
												xtype : "textfield",
												name : "constructioncontract.businessCharger",
												id:'businessCharger',
												fieldLabel : "业务主办",
												anchor : "96%"
											}, {
												xtype : "textfield",
												name : "constructioncontract.numOrArea",
												id:'numOrArea',
												fieldLabel : "数量/面积",
												anchor : "96%"
											}]
								}]
					}, {
						xtype : "numberfield",
						name : "constructioncontract.sumPrice",
						id:'sumPrice',
						fieldLabel : "合同总价",
						anchor : "96%"
					}, {
						xtype : "textarea",
						name : "constructioncontract.contractRemark",
						id:'contractRemark',
						fieldLabel : "合同说明",
						anchor : "96%"
					}, {
						xtype : "textarea",
						name : "constructioncontract.payWay",
						id:'payWay',
						fieldLabel : "付款方式",
						anchor : "96%"
					}, {
						xtype : "textfield",
						name : "constructioncontract.projectTime",
						id:'projectTime',
						fieldLabel : "工期要求/能否满足/奖罚",
						anchor : "96%"
					}, {
						xtype : "textfield",
						name : "constructioncontract.quality",
						id:'quality',
						fieldLabel : "质量/奖罚",
						anchor : "96%"
					}, {
						xtype : "combo",
						hiddenName : "constructioncontract.isFullContract",
						id:'isFullContract',
						fieldLabel : "是否双包",
						triggerAction : 'all',
						editable : false,
						store : [['拟整体双包单位/班组', '拟整体双包单位/班组'], ['否', '否']],
						anchor : "96%"
					}, {
						xtype : "container",
						name : "MyContainer4",
						autoEl : "div",
						layout : "column",
						style : "margin:0 0 5px 0;",
						items : [{
									xtype : "label",
									name : "MyLabel1",
									style : "margin-right:8px;",
									text : "是否承担设计费用:"
								}, {
									xtype : "combo",
									hiddenName : "constructioncontract.isDesignCost",
									id:'isDesignCost',
									fieldLabel : "是否承担设计费",
									store : [['是', '是'], ['否', '否']],
									triggerAction : 'all',
									editable : false,
									width : 200,
									listeners : {
										select : function(combo, record, index) {
											if (combo.getValue() == '是') {
												Ext.getCmp("designCost")
														.enable();
											} else {
												Ext.getCmp("designCost")
														.disable();
											}
										}
									}
								}, {
									xtype : "label",
									name : "MyLabel2",
									text : "金额",
									style : "margin: 3px 10px 0 50px;"
								}, {
									xtype : "numberfield",
									name : "constructioncontract.designCost",
									id : 'designCost',
									fieldLabel : "Label"
								}, {
									xtype : "label",
									name : "MyLabel3",
									text : "元",
									style : "margin: 3px 10px 0 2px"
								}]
					},{
				xtype:'container',
				layout:'column',
				style:'margin-bottom:3px',
				items:[
					{
						xtype:'label',
						text:'是否评文明工地:',
						style:'margin-right:20px'
					},
					{
						xtype:"combo",
						hiddenName:"constructioncontract.isModelCommunity",
						id:'isModelCommunity',
						fieldLabel:"是否评文明工地",
						store :[['是','是'],['否','否']],
						triggerAction :'all',
						width:200,
						editable :false,
						listeners:{
							select:function(combo,record,index){
								if(combo.getValue()=='是'){
									Ext.getCmp("rewardOrPunish").enable();
								}else{
									Ext.getCmp("rewardOrPunish").disable();
								}
							}
						}
					},
					{
						xtype:'label',
						text:'奖罚:',
						style:"margin: 3px 10px 0 50px;"
					},
					{
						xtype:'textfield',
						name:'constructioncontract.rewardOrPunish',
						id:'rewardOrPunish',
						width:270
					}
				]
				}, {
					xtype:"textfield",
					name:'constructioncontract.guarantee',
					id:'guarantee',
	//				hiddenName:"guarantee",
					fieldLabel:"保修期/金额",
	//				store :[['1年/5%','1年/5%'],['2年/5%','2年/5%'],['其他','其他']],
	//				triggerAction :'all',
	//				editable :false,
					anchor:"96%"
				}, {
						xtype : "container",
						name : "MyContainer5",
						autoEl : "div",
						layout : "column",
						style : "margin:0 0 5px 0;",
						items : [{
									xtype : "label",
									name : "MyLabel4",
									text : "是否办理施工备案：",
									style : "margin: 3px 2px 0 0"
								}, {
									xtype : "combo",
									hiddenName : "constructioncontract.constructionBackUp",
									id : 'constructionBackUp',
									fieldLabel : "Label",
									store : [['是', '是'], ['否', '否']],
									columnWidth : "",
									triggerAction : 'all',
									editable : false,
									width : 166,
									listeners : {
										select : function(combo, record, index) {
											if (combo.getValue() == '是') {
												Ext
														.getCmp("constructionBackUpPerson")
														.enable();
												Ext
														.getCmp("constructionBackUpFinishTime")
														.enable();
											} else {
												Ext
														.getCmp("constructionBackUpPerson")
														.disable();
												Ext
														.getCmp("constructionBackUpFinishTime")
														.disable();
											}
										}
									}
								}, {
									xtype : "label",
									name : "MyLabel5",
									text : "办理责任人：",
									style : "margin: 3px 0 0 9px"
								}, {
									xtype : "textfield",
									name : "constructioncontract.constructionBackUpPerson",
									id : 'constructionBackUpPerson'
								}, {
									xtype : "label",
									name : "MyLabel6",
									text : "具体完成时间：",
									style : "margin: 3px 0 0 9px"
								}, {
									xtype : "datefield",
									name : "constructioncontract.constructionBackUpFinishTime",
									id : "constructionBackUpFinishTime",
									format : "Y-m-d",
									fieldLabel : "Label"
								}]
					}, {
						xtype : "container",
						name : "MyContainer6",
						autoEl : "div",
						layout : "column",
						style : "margin:0 0 5px 0;",
						items : [{
									xtype : "label",
									name : "MyLabel7",
									text : "是否办理施工许可证：",
									width : 110
								}, {
									xtype : "combo",
									hiddenName : "constructioncontract.constructionLicense",
									id : 'constructionLicense',
									store : [['是', '是'], ['否', '否']],
									triggerAction : 'all',
									editable : false,
									listeners : {
										select : function(combo, record, index) {
											if (combo.getValue() == '是') {
												Ext
														.getCmp("constructionLicensePerson")
														.enable();
												Ext
														.getCmp("constructionLicenseFinishTime")
														.enable();
											} else {
												Ext
														.getCmp("constructionLicensePerson")
														.disable();
												Ext
														.getCmp("constructionLicenseFinishTime")
														.disable();
											}
										}
									}
								}, {
									xtype : "label",
									name : "MyLabel8",
									text : "办理责任人：",
									style : "margin: 3px 0 0 9px"
								}, {
									xtype : "textfield",
									name : "constructioncontract.constructionLicensePerson",
									id : "constructionLicensePerson",
									fieldLabel : "Label"
								}, {
									xtype : "label",
									name : "MyLabel9",
									text : "具体完成时间：",
									style : "margin: 3px 0 0 9px"
								}, {
									xtype : "datefield",
									name : "constructioncontract.constructionLicenseFinishTime",
									id : "constructionLicenseFinishTime",
									format : "Y-m-d",
									fieldLabel : "Label"
								}]
					}, {
						xtype : "combo",
						hiddenName : "constructioncontract.isoverBudget",
						id : 'isoverBudget',
						fieldLabel : "是否存在（无）超资质施工的情况",
						store : [['是', '是'], ['否', '否']],
						triggerAction : 'all',
						editable : false,
						anchor : "96%"
					}, {
						xtype : "container",
						name : "MyContainer7",
						autoEl : "div",
						style : "margin:0 0 5px 0;",
						layout : "column",
						items : [{
									xtype : "label",
									name : "MyLabel10",
									text : "报价:",
									width : 110,
									style : "margin: 3px 0 0 0"
								}, {
									xtype : "combo",
									hiddenName : "constructioncontract.quote",
									id : 'quote',
									store : [['有利润', '有利润'],
											['连管理费持平', '连管理费持平'],
											['不含管理费亏损', '不含管理费亏损'],
											['含管理费亏损', '含管理费亏损'],
											['免管理费', '免管理费']],
									fieldLabel : "Label",
									triggerAction : 'all',
									editable : false,
									listeners : {
										select : function(combo, record, index) {
											if(combo.getValue()=='含管理费亏损'||combo.getValue()=='不含管理费亏损'||combo.getValue()=='免管理费'){
												Ext.getCmp("quoteloss").enable();
											}else{
												Ext.getCmp("quoteloss").disable();
											}
										}
									}
								}, {
									xtype : "label",
									name : "MyLabel11",
									text : "预估亏损额：",
									style : "margin: 3px 0 0 9px"
								}, {
									xtype : "numberfield",
									name : "constructioncontract.quoteloss",
									id : "quoteloss",
									fieldLabel : "Label"
								}]
					}, {
						xtype : "textfield",
						name : "constructioncontract.remark",
						id : 'remark',
						fieldLabel : "其他应在签定前商谈的事项",
						anchor : "96%"
					}, {
						xtype : "textfield",
						name : "constructioncontract.meno",
						id : 'meno',
						fieldLabel : "应在合同履行过程中注意的事项",
						anchor : "96%"
					}, {
						xtype : 'container',
						layout : 'column',
						height : 15,
						defaults : {
							xtype : 'label',
							style : {
								margin : '0px 0px 3px 0px'
							}
						},
						items : [{
									style : 'color:red;margin-left:110px;',
									id : 'constructioncontract.errorLabel',
									text : '(*必须上传合同附件)'
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
									width : 100,
									style : "margin-top:15px;"
								},{
									xtype : 'hidden',
									name : 'constructioncontract.attachIds',
									id : 'attachIds'
								},{
									xtype : 'hidden',
									name : 'constructioncontract.attachFiles',
									id : 'attachFiles'
								},{
									xtype : 'panel',
									id:'constructioncontract.displayAttach',
									width : 500,
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
																file_cat : 'flow/Constructioncontract',
																callback : function(data) {
																	Ext.getCmp('constructioncontract.displayAttach').body.update('');
																	Ext.getCmp("attachFiles").setValue('');
																	Ext.getCmp('attachIds').setValue('');
																	var contractFile = Ext.getCmp("attachFiles");
																	var fileIds = Ext.getCmp('attachIds');
																	var display = Ext.getCmp('constructioncontract.displayAttach');
																	for (var i = 0; i < data.length; i++) {
																		if (fileIds.getValue() != '') {
																			fileIds.setValue(fileIds.getValue()+ ',');
																			contractFile.setValue(contractFile.getValue()+ ',');
																		}
																		contractFile.setValue(contractFile.getValue()+data[i].filepath+":"+data[i].filename.replace(/\s+/g,""));
																		fileIds.setValue(fileIds.getValue()+data[i].fileId);
																		Ext.DomHelper.append(display.body,'<span><a href="#" onclick="FileAttachDetail.show('+data[i].fileId+')">'+data[i].filename.replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
																	}
																},
																permitted_max_size :1024*1024*20,
																permitted_extensions_size : [{'type':['jpg','png','gif','bmp','jpeg'],'MaxSize':1024*800}]
															});
											dialog.show(this);
									}
								}]
					}, {
							xtype : 'textfield',
							hidden : true,
							validator : function() {
								var attachIDs = Ext.getCmp('attachIds').getValue();
								var errorLabel = Ext.getCmp("constructioncontract.errorLabel");
								if(Ext.isEmpty(thiz.contractId)){
									if (Ext.isEmpty(attachIDs)) {//为空显示提示
										return false;
									}
								}
								return true
							}
					}]
		});
		// 加载表单对应的数据
		if (this.contractId != null && this.contractId != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath
						+ '/statistics/getConstructioncontract.do?contractId='
						+ this.contractId,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
						var jsonData = Ext.util.JSON.decode(action.response.responseText);
						var res = jsonData.data;
						Ext.getCmp("deptRegionalId").setValue(res.deptRegional.depId);
						Ext.getCmp("projectChargerUserId").setValue(res.projectChargerUser.userId);
						
						var attachId = res.attachIds;
						var attachFile = res.attachFiles;
						if(attachId!= null && attachId != 'undefined' && attachFile!= null && attachFile != 'undefined'){
							var ids = attachId.split(',');
							var files= attachFile.split(',');
							for(var i=0;i<ids.length;i++){
								if(files[i].lastIndexOf('<a href')!=-1){
									  Ext.DomHelper.append(Ext.getCmp('constructioncontract.displayAttach').body,'<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+files[i].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
								}else if(files[i].lastIndexOf(':')!=-1){
									  var fg = files[i].split(':');
									  Ext.DomHelper.append(Ext.getCmp('constructioncontract.displayAttach').body,'<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+fg[1].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
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
					hidden:!(isGranted("_ConstructioncontractAdd")&&isGranted("_ConstructioncontractEdit")) ,
					handler : this.save.createCallback(this.formPanel, this)
				}, 
//					{
//					text : '重置',
//					iconCls : 'btn-reset',
//					handler : this.reset.createCallback(this.formPanel)
//				},
				{
					text : '关闭',
					iconCls : 'btn-cancel',
					handler : this.cancel.createCallback(this)
				}
				];
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
									.getCmp('ConstructioncontractGrid');
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