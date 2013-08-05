/**
 * @author
 * @createtime
 * @class EntrantForm
 * @extends Ext.Window
 * @description Entrant表单
 */
EntrantForm = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		EntrantForm.superclass.constructor.call(this, {
					id : 'EntrantFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					width:650,
					height:465,
					maximizable : true,
					title : '员工入职详细信息',
					buttonAlign : 'center',
					buttons : this.buttons
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel({
			layout:"absolute",
			trackResetOnLoad:true,
			autoScroll : true,
			bodyStyle: 'padding:10px 10px 10px 10px;overflow-x:hidden;overflow-y:scroll',
			width:617,
			height:465,
			border : false,
			url : __ctxPath + '/statistics/saveEntrant.do',
			id : 'EntrantForm',
			defaultType : 'textfield',
			items : [
			{
				xtype:"label",
				name:"MyLabel",
				text:"员工入职新进审批表",
				x:170,
				y:10,
				width:140,
				style:"font-size:14px;font-weight:bold"
			},
			{
				xtype:"label",
				name:"MyLabel1",
				text:"招聘渠道：",
				x:10,
				y:30,
				width:70
			},
			{
				xtype:"label",
				name:"MyLabel2",
				text:"公开招聘，途径",
				x:30,
				y:53,
				width:130
			},
			{
				xtype:"label",
				name:"MyLabel3",
				text:"推荐，理由：（请附推荐表）",
				x:30,
				y:75,
				width:160
			},{
			    xtype:'hidden',
			    value:this.entrantId,
			    name:"entrant.entrantId",
			    id:'entrantId'
			},
			{
				xtype:"textfield",
				maxLength:20,
				name:"entrant.inviteReason",
				id:"inviteReason",
				disabled:true,
				x:190,
				y:75,
				width:415
			},
			{
				 xtype: 'radiogroup',
				 id   : 'entrantType',
				 name:'entrant.entrantType',
				 columns:1,
				 width:12,
				 items:[{
					name:"entrant.entrantType",
					inputValue :'公开招聘',
					checked : true,
					listeners:{
						check:function(cb,checked){
							//var contractOptions = Ext.query("*[name=contractOption]");
								if(checked){
								    Ext.getCmp("inviteReason").reset();
									Ext.getCmp("inviteReason").disable();
								    Ext.getCmp("inviteWay").enable();
								}else{
									Ext.getCmp("inviteWay").reset();
									Ext.getCmp("inviteWay").disable();
								    Ext.getCmp("inviteReason").enable();
								}
							}
						}
				 },{					
					name:"entrant.entrantType",
					inputValue :'推荐',					
					style:'margin-top:-12px;'
				}],
				x:10,
				y:54
			},
			{
				xtype:"label",
				name:"MyLabel4",
				text:"推荐人签名：",
				x:280,
				y:102,
				style:"font-size:12px"
			},
			{
				xtype:"datefield",
				name:"entrant.signTime",
				id:"signTime",
				format:"Y-m-d",
				fieldLabel:"Label",
				anchor:"100%",
				editable:false,
				x:440,
				y:100
			},
			{
				xtype:"label",
				name:"MyLabel5",
				id:'signName',
				text:__currentUser,
				x:345,
				y:102,
				style:"margin-left:10px;color: #036; text-decoration: none; border-bottom: 1px solid #F90;",
				width:80
			},
			{
				xtype:"label",
				name:"MyLabel6",
				text:"用人部门意见：",
				x:10,
				y:132
			},
			{
				xtype:"label",
				name:"MyLabel7",
				text:"拟定担任",
				x:370,
				y:132
			},
			{
				xtype:"textfield",
				name:"entrant.position",
				id:"position",
				fieldLabel:"Label",
				maxLength:20,
				anchor:"",
				allowBlank : false,
				x:420,
				y:130,
				width:156
			},
			{
				xtype:"label",
				name:"MyLabel8",
				text:"职位",
				x:580,
				y:132
			},
			{
				xtype:"textfield",
				name:"entrant.inviteWay",
				id:"inviteWay",
				fieldLabel:"Label",
				maxLength:20,
				anchor:"100%",
				x:120,
				y:50,
				width:360
			},
			{
				xtype:"label",
				name:"MyLabel10",
				text:"专业知识",
				x:10,
				y:168
			},
			{
				 xtype: 'radiogroup',
				 id   : 'professional',
				 name:'entrant.professional',
				 width:300,
				 items:[{
					name:"entrant.professional",
					boxLabel:"优秀",
					inputValue :'优秀'
				 },{					
					name:"entrant.professional",					
					boxLabel:"良好",
					inputValue :'良好',
					checked : true
				},
				{					
					name:"entrant.professional",					
					boxLabel:"一般",
					inputValue :'一般'
				},
				{					
					name:"entrant.professional",
					boxLabel:"不能接受",
					inputValue :'不能接受'
				}],
				x:60,
				y:165
			},
			{
				xtype:"label",
				name:"MyLabel10",
				text:"工作经历",
				x:10,
				y:193
			},{
				 xtype: 'radiogroup',
				 id   : 'experience',
				 name:'entrant.experience',
				 width:300,
				 items:[{
					name:"entrant.experience",
					boxLabel:"优秀",
					inputValue :'优秀'
				 },{					
					name:"entrant.experience",					
					boxLabel:"良好",
					inputValue :'良好',
					checked : true
				},
				{					
					name:"entrant.experience",					
					boxLabel:"一般",
					inputValue :'一般'
				},
				{					
					name:"entrant.experience",
					boxLabel:"不能接受",
					inputValue :'不能接受'
				}],
				x:60,
				y:190
			},
			{
				xtype:"label",
				name:"MyLabel11",
				text:"自信度",
				x:10,
				y:218
			},{
				 xtype: 'radiogroup',
				 id   : 'confident',
				 name:'entrant.confident',
				 width:300,
				 items:[{
					name:"entrant.confident",
					boxLabel:"优秀",
					inputValue :'优秀'
				 },{					
					name:"entrant.confident",					
					boxLabel:"良好",
					inputValue :'良好',
					checked : true
				},
				{					
					name:"entrant.confident",					
					boxLabel:"一般",
					inputValue :'一般'
				},
				{					
					name:"entrant.confident",
					boxLabel:"不能接受",
					inputValue :'不能接受'
				}],
				x:60,
				y:215
			},				
			{
				xtype:"label",
				name:"MyLabel12",
				text:"思维能力",
				x:10,
				y:243
			},
			{
				 xtype: 'radiogroup',
				 id   : 'thinkability',
				 name:'entrant.thinkability',
				 width:300,
				 items:[{
					name:"entrant.thinkability",
					boxLabel:"优秀",
					inputValue :'优秀'
				 },{					
					name:"entrant.thinkability",					
					boxLabel:"良好",
					inputValue :'良好',
					checked : true
				},
				{					
					name:"entrant.thinkability",					
					boxLabel:"一般",
					inputValue :'一般'
				},
				{					
					name:"entrant.thinkability",
					boxLabel:"不能接受",
					inputValue :'不能接受'
				}],
				x:60,
				y:240
			},	
			{
				xtype:"label",
				name:"MyLabel13",
				text:"表达能力",
				x:10,
				y:268
			},
			{
				 xtype: 'radiogroup',
				 id   : 'expressability',
				 name:'entrant.expressability',
				 width:300,
				 items:[{
					name:"entrant.expressability",
					boxLabel:"优秀",
					inputValue :'优秀'
				 },{					
					name:"entrant.expressability",					
					boxLabel:"良好",
					inputValue :'良好',
					checked : true
				},
				{					
					name:"entrant.expressability",					
					boxLabel:"一般",
					inputValue :'一般'
				},
				{					
					name:"entrant.expressability",
					boxLabel:"不能接受",
					inputValue :'不能接受'
				}],
				x:60,
				y:265
			},	
			{
				xtype:"label",
				name:"MyLabel14",
				text:"仪表",
				x:10,
				y:293
			},
			{
				 xtype: 'radiogroup',
				 id   : 'looks',
				 name:'entrant.looks',
				 width:300,
				 items:[{
					name:"entrant.looks",
					boxLabel:"优秀",
					inputValue :'优秀'
				 },{					
					name:"entrant.looks",					
					boxLabel:"良好",
					inputValue :'良好',
					checked : true
				},
				{					
					name:"entrant.looks",					
					boxLabel:"一般",
					inputValue :'一般'
				},
				{					
					name:"entrant.looks",
					boxLabel:"不能接受",
					inputValue :'不能接受'
				}],
				x:60,
				y:290
			},
			{
				xtype:"fieldset",
				name:"MyFieldSet",
				title:"同意新进（行政部填B项）",
				layout:"absolute",
				x:370,
				y:168,
				width:240,
				height:140,
				items:[
					{
						xtype:"label",
						name:"MyLabel15",
						text:"月",
						x:200,
						y:0
					},
					{
						xtype:"label",
						name:"MyLabel16",
						text:"拟定考察期",
						x:0,
						y:2
					},
					{
						xtype:"label",
						name:"MyLabel17",
						text:"考察期月薪",
						x:0,
						y:29
					},
					{
						xtype:"label",
						name:"MyLabel18",
						text:"拟定试用期",
						x:0,
						y:56
					},
					{
						xtype:"label",
						name:"MyLabel19",
						text:"试用期月薪",
						x:0,
						y:83
					},
					{
						xtype:"label",
						name:"MyLabel20",
						text:"元/月",
						x:180,
						y:29
					},
					{
						xtype:"label",
						name:"MyLabel21",
						text:"月",
						x:200,
						y:56
					},
					{
						xtype:"label",
						name:"MyLabel22",
						text:"元/月",
						x:180,
						y:83
					},
					{
						xtype:"numberfield",
						regex:/^(\d{1,2}|0)?$/,
						name:"entrant.inspect",
						id:"inspect",
						fieldLabel:"Label",
						width:130,
						anchor:"",
						x:65,
						y:0
					},
					{
						xtype:"numberfield",
						name:"entrant.inspectSalary",
						id:"inspectSalary",
						fieldLabel:"Label",
						regex:/^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/,
						anchor:"",
						x:65,
						y:27,
						width:110
					},
					{
						xtype:"numberfield",
						name:"entrant.probation",
						id:"probation",
						regex:/^(\d{1,2}|0)?$/,
						fieldLabel:"Label",
						width:130,
						anchor:"",
						x:65,
						y:54
					},
					{
						xtype:"numberfield",
						name:"entrant.probationSalary",
						id:"probationSalary",
						regex:/^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/,
						fieldLabel:"Label",
						anchor:"",
						x:65,
						y:81,
						width:110
					}
				]
			},{
				xtype : 'panel',
				layout : 'column',
				defaultType : 'label',
				style:'padding-top:3px;margin-buttom:5px',
				x:10,
				y:313,
				height: 70,
				border:false,
				items : [{
							text : '推荐表:',
							width : 90
						},{
							xtype : 'hidden',
							name : 'entrantAttachIDs',
							id : 'entrantAttachIDs'
						},{
							xtype : 'hidden',
							name : 'entrantAttachFile',
							id : 'entrantAttachFile'
						},{
							xtype : 'panel',
							id:'displayAttach',
							width : 400,
							height: 60,
							frame:true,
							autoScroll:true,
							html : ''
						},{
							xtype : 'button',
							iconCls : 'btn-upload',
							text : '上传',
							handler : function() {
							        Ext.getCmp('displayAttach').body.update('');
								Ext.getCmp("entrantAttachFile").setValue('');
								Ext.getCmp('entrantAttachIDs').setValue('');
						
								var dialog = App.createUploadDialog({
														file_cat : 'flow/entrant',
														callback : function(data) {
															var entrantFile = Ext.getCmp("entrantAttachFile");
															var fileIds = Ext.getCmp('entrantAttachIDs');
															var display = Ext.getCmp('displayAttach');
															for (var i = 0; i < data.length; i++) {
																if (fileIds.getValue() != '') {
																	fileIds.setValue(fileIds.getValue()+ ',');
																	entrantFile.setValue(entrantFile.getValue()+ ',');
																}
																entrantFile.setValue(entrantFile.getValue()+"<a href=javascript:window.location.href=\""+__ctxPath+"/file-Download?filePath=attachFiles/"+data[i].filepath+"&fileName="+data[i].filename.replace(/\s+/g,"")+"\">"+data[i].filename.replace(/\s+/g,"")+"</a>");
																fileIds.setValue(fileIds.getValue()+data[i].fileId);
																Ext.DomHelper.append(display.body,'<span><a href="#" onclick="FileAttachDetail.show('+data[i].fileId+')">'+data[i].filename.replace(/\s+/g,"")+'</a><img class="img-delete" src="'+__ctxPath+'/images/system/delete.gif" onclick="removeFile(this,'+data[i].fileId+')"/>&nbsp;|&nbsp;</span>');
																		
															}
														},
														permitted_max_size :1024*1024*20
													});
									dialog.show(this);
							}
						}
						]
				}
		]
		});
		// 加载表单对应的数据
		if (this.entrantId != null && this.entrantId != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getEntrant.do?entrantId='
						+ this.entrantId,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					 	var jsonData = Ext.util.JSON.decode(action.response.responseText);
						var res = jsonData.data;
						var signName = res.signName;
						var af = res.entrantFiles;
						Ext.getCmp("signName").setText(signName);
						Ext.getCmp("signName").originalValue = signName;
						var entrantType=res.entrantType;
						var looks=res.looks;
						var experience=res.experience;
						var confident=res.confident;
						var thinkability=res.thinkability;
						var expressability=res.expressability;
						var professional=res.professional
						Ext.getCmp("expressability").setValue(expressability)
						Ext.getCmp("expressability").originalValue =expressability;
						Ext.getCmp("thinkability").setValue(thinkability)
						Ext.getCmp("thinkability").originalValue =thinkability;
						Ext.getCmp("confident").setValue(confident)
						Ext.getCmp("confident").originalValue =confident;
					    Ext.getCmp("experience").setValue(experience)
						Ext.getCmp("experience").originalValue =experience;
						Ext.getCmp("looks").setValue(looks)
						Ext.getCmp("looks").originalValue =looks;
						Ext.getCmp("professional").setValue(professional)
						Ext.getCmp("professional").originalValue =professional;
						Ext.getCmp("entrantType").setValue(entrantType)
						Ext.getCmp("entrantType").originalValue =entrantType;
						var filePanel = Ext.getCmp('displayAttach');
						var fileIds = Ext.getCmp("entrantAttachIDs");
						for (var i = 0; i < af.length; i++) {
							if (fileIds.getValue() != '') {
								fileIds.setValue(fileIds.getValue() + ',');
							}
							fileIds.setValue(fileIds.getValue() + af[i].fileId);
							Ext.DomHelper
									.append(
											filePanel.body,
											'<span><a href="#" onclick="FileAttachDetail.show('
													+ af[i].fileId
													+ ')">'
													+ af[i].fileName.replace(/\s+/g,"")+'</a><img class="img-delete" src="'+__ctxPath+'/images/system/delete.gif" onclick="removeFile(this,'+af[i].fileId+')"/>&nbsp;|&nbsp;</span>');
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
				 handler : this.save.createCallback(this.formPanel,
				 this)
		    }, {
				 text : '重置',
				 iconCls : 'btn-reset',
				 handler : this.reset.createCallback(this.formPanel)
			   },
			{
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
		var signName=Ext.getCmp("signName").text;
		if (formPanel.getForm().isValid()) {
			formPanel.getForm().submit({
				        params:{signName:signName},
						method : 'POST',
						waitMsg : '正在提交数据...',
						success : function(fp, action) {
							Ext.ux.Toast.msg('操作信息', '成功保存信息！');
							var gridPanel = Ext.getCmp('EntrantGrid');
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
function removeFile(obj, fileId) {
	var fileIds = Ext.getCmp("entrantAttachIDs");
	var value = fileIds.getValue();
	if (value.indexOf(',') < 0) {// 仅有一个附件
		fileIds.setValue('');
	} else {
		value = value.replace(',' + fileId, '').replace(fileId + ',', '');
		fileIds.setValue(value);
	}

	var el = Ext.get(obj.parentNode);
	el.remove();
};