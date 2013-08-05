/**
 * @author 
 * @createtime 
 * @class StaffActiveapplyForm
 * @extends Ext.Window
 * @description StaffActiveapply表单
 */
StaffActiveapplyForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		StaffActiveapplyForm.superclass.constructor.call(this,{
			id:'StaffActiveapplyFormWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			width : 680,
			height : 550,
			maximizable:false,
			title:'员工转正详细信息',
			buttonAlign : 'center',
			buttons:this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents:function(){
		this.formPanel=new Ext.FormPanel({
			    autoScroll : true,
				layout : 'form',
				trackResetOnLoad:true,
				autoScroll : true,
			    bodyStyle: 'padding:10px 10px 10px 10px;overflow-x:hidden;overflow-y:scroll',
				border:false,
				url : __ctxPath + '/statistics/saveStaffActiveapply.do',
				id : 'StaffActiveapplyForm',
				defaults : {
					anchor : '95%,95%'
				},
				defaultType : 'textfield',
				items : [{
					xtype : "label",
					name : "MyLabel",
					text : "转正审批表",
					width : "",
					style : "padding-left:250px;font-weight:bold;font-size:20px;"
				}, {
					xtype : "panel",
					layout : "column",
					border : false,
					unstyled : false,
					height : 30,
					items : [{
								xtype : "label",
								name : "MyLabel1",
								text : "姓名:",
								height : 30,
								width : 60
							},{
							   xtype:'hidden',
							   name:'staffActiveapply.id',
							   value:this.id,
							   id:'id'
							 },{
							   xtype:'hidden',
							   name:'staffActiveapply.processRunId',
							   value:-1
							 },{
							   xtype:'hidden',
							   name:'staffActiveapply.applyUser.userId',
							   id:"applyUserId"
							 },{
								xtype : "textfield",
								name : "staffActiveapply.applyName",
								id:'applyName',
								emptyText:'请选择人员',
								allowBlank:false,
								readOnly:true,
								listeners:{
									focus:function(){
										UserSelector.getView(function(ids, names) {
												Ext.getCmp("applyName").setValue(names);
												Ext.Ajax.request({
												url : __ctxPath
														+ "/system/getAppUser.do",
												params : {
													userId : ids
												},
												method : "post",
												success : function(response) {
													var e = Ext.util.JSON
															.decode(response.responseText).data[0];
													Ext.getCmp("workPosition").setValue(e.position);
													Ext.getCmp("deptName").setValue(e.department.depName);
													Ext.getCmp("accessionTime").setValue(e.accessionTime);
													Ext.getCmp("applyUserId").setValue(e.userId);
												}
											});
											}, true).show();
									}
								}
							}, {
								xtype : "label",
								name : "MyLabel2",
								text : "公司/部门:",
								style : "padding-left:5px;",
								height : 30
							}, {
								xtype : "textfield",
								name : "staffActiveapply.deptName",
								id:'deptName',
								emptyText:'请选择人员',
								allowBlank:false,
								readOnly:true,
								style:"background:#F0F0F0;",
								listeners:{
									focus:function(){
										UserSelector.getView(function(ids, names) {
												Ext.getCmp("applyName").setValue(names);
												Ext.Ajax.request({
												url : __ctxPath
														+ "/system/getAppUser.do",
												params : {
													userId : ids
												},
												method : "post",
												success : function(response) {
													var e = Ext.util.JSON
															.decode(response.responseText).data[0];
													Ext.getCmp("workPosition").setValue(e.position);
													Ext.getCmp("deptName").setValue(e.department.depName);
													Ext.getCmp("accessionTime").setValue(e.accessionTime);
													Ext.getCmp("applyUserId").setValue(e.userId);
												}
											});
											}, true).show();
									}
								}
							}, {
								xtype : "label",
								name : "MyLabel3",
								text : "职位:",
								style : "padding-left:5px;",
								height : 30
							}, {
								xtype : "textfield",
								name : "staffActiveapply.workPosition",
								id:'workPosition',
								emptyText:'请选择人员',
								allowBlank:false,
								readOnly:true,
								style:"background:#F0F0F0;",
								listeners:{
									focus:function(){
										UserSelector.getView(function(ids, names) {
												Ext.getCmp("applyName").setValue(names);
												Ext.Ajax.request({
												url : __ctxPath
														+ "/system/getAppUser.do",
												params : {
													userId : ids
												},
												method : "post",
												success : function(response) {
													var e = Ext.util.JSON
															.decode(response.responseText).data[0];
													Ext.getCmp("workPosition").setValue(e.position);
													Ext.getCmp("deptName").setValue(e.department.depName);
													Ext.getCmp("accessionTime").setValue(e.accessionTime);
													Ext.getCmp("applyUserId").setValue(e.userId);
												}
											});
											}, true).show();
									}
								}
							}]
				},{
					xtype : "panel",
					layout : "column",
					border : false,
					unstyled : false,
					height : 30,
					items:[{
							xtype : "label",
							text : "入职时间:",
							//style : "padding-left:5px;",
							height : 30,
							width : 60
							}, {
								xtype : "textfield",
								name : "staffActiveapply.accessionTime",
								id:'accessionTime',
								emptyText:'请选择人员',
								allowBlank:false,
								readOnly:true,
								listeners:{
									focus:function(){
										UserSelector.getView(function(ids, names) {
												Ext.getCmp("applyName").setValue(names);
												Ext.Ajax.request({
												url : __ctxPath
														+ "/system/getAppUser.do",
												params : {
													userId : ids
												},
												method : "post",
												success : function(response) {
													var e = Ext.util.JSON
															.decode(response.responseText).data[0];
													Ext.getCmp("workPosition").setValue(e.position);
													Ext.getCmp("deptName").setValue(e.department.depName);
													Ext.getCmp("accessionTime").setValue(e.accessionTime);
													Ext.getCmp("applyUserId").setValue(e.userId);
													//				Date.parseDate(e.accessionAttachmenTime,'Y-m-d').format('Y-m-d')
													//			)
													//			new Date(e.accessionAttachmenTime).format('Y-m-d'));
													//			Ext.util.Format.date(e.accessionAttachmenTime, 'Y-m-d'))
													//new Date(
													//getDateFromFormat(e.accessionAttachmenTime,
													//	"yyyy-MM-dd")));
												}
											});
									}, true).show();
							}
						}
					},{
						xtype : "label",
						text : "试用期:",
						height : 30,
						width : 60
					}, {
						xtype : "textfield",
						name : "staffActiveapply.probation",
						id:'probation',
						allowBlank:false,
						readOnly:false,
						maxLength:20,
					},{
					xtype : "panel",
					border : false,
					unstyled : false,
					height : 30,
					items:[
						{
							xtype : "label",
							text : "考试分数:",
							height : 30,
							width : 60,
						}, {
							xtype : "textfield",
							name : "staffActiveapply.score",
							id:'score',
							allowBlank:false,
							readOnly:false,
							maxLength:20,
						}
					]
				}
					]
				},{
							xtype : 'panel',
							layout : 'column',
							defaultType : 'label',
							style:'padding-top:3px;margin-buttom:5px',
							height: 70,
							border:false,
							items : [{
										text : '附件:',
										width : 90
									},{
										xtype : 'hidden',
										name : 'staffActiveapply.firstAttachIds',
										id : 'firstAttachIds'
									},{
										xtype : 'hidden',
										name : 'staffActiveapply.firstAttachFiles',
										id : 'firstAttachFiles'
									},{
										xtype : 'panel',
										id:'displayAttachmentAttachStaff',
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
											var dialog = App.createUploadDialog({
																	file_cat : 'flow/staffActive/attachment',
																	callback : function(data) {
																		Ext.getCmp('displayAttachmentAttachStaff').body.update('');
																		Ext.getCmp("firstAttachFiles").setValue('');
																		Ext.getCmp('firstAttachIds').setValue('');
																		var fileIds = Ext.getCmp('firstAttachIds');
																		var display = Ext.getCmp('displayAttachmentAttachStaff');
																		var staffActiveFile = Ext.getCmp("firstAttachFiles");
																		
																		for (var i = 0; i < data.length; i++) {
																			if (fileIds.getValue() != '') {
																				fileIds.setValue(fileIds.getValue()+ ',');
																				staffActiveFile.setValue(staffActiveFile.getValue()+ ',');
																			}
																			staffActiveFile.setValue(staffActiveFile.getValue()+data[i].filepath+":"+data[i].filename.replace(/\s+/g,""));
//																			staffActiveFile.setValue(staffActiveFile.getValue()+"<a href=javascript:window.location.href=\""+__ctxPath+"/file-Download?filePath=attachFiles/"+data[i].filepath+"&fileName="+data[i].filename.replace(/\s+/g,"")+"\">"+data[i].filename.replace(/\s+/g,"")+"</a>");
																			fileIds.setValue(fileIds.getValue()+data[i].fileId);
																			Ext.DomHelper.append(display.body,'<span><a href="#" onclick="FileAttachDetail.show('+data[i].fileId+')">'+data[i].filename.replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
																		}
																	},
																	permitted_max_size :1024*1024*20
																});
												dialog.show(this);
										}
									}
									]
							}
//				, {
//					xtype : "panel",
//					layout : "column",
//					style:'padding-top:5px;',
//					border : false,
//					unstyled : false,
//					height : 60,
//					items : [{
//								xtype : "label",
//								name : "MyLabel1",
//								text : "考核项目:",
//								height : 50,
//								width : 60
//							}, {
//								xtype : "textarea",
//								maxLength:200,
//								name : "staffActiveapply.examProject",
//								id:'examProject',
//								width: 510,
//								height:50,
//								allowBlank:false
//							}]
//				}
				, {
					xtype : "panel",
					layout : "column",
					border : false,
					unstyled : false,
					style:'padding-top:5px;',
					height : 60,
					items : [{
								xtype : "label",
								name : "MyLabel1",
								text : "工作成果:",
								height : 50,
								width : 60
							}, {
								xtype : "textarea",
								maxLength:200,
								name : "staffActiveapply.workAchieve",
								id:'workAchieve',
								allowBlank:false,
								width: 510,
								height:50
							}]
				}, {
					xtype : "panel",
					layout : "column",
					border : false,
					unstyled : false,
					style:'padding-top:5px;',
					height : 40,
					items : [{
								xtype : "label",
								text : "工作效率:",
								width : 60
							}, {
								xtype : "textfield",
								name : "staffActiveapply.workEfficiency",
								id:'workEfficiency',
								maxLength:20,
								allowBlank:false,
								width:215
							}, {
								xtype : "label",
								text : "团队精神:",
								width : 90,
								style : "padding-left:30px;"
							}, {
								xtype : "textfield",
								name : "staffActiveapply.teamSpirit",
								id:'teamSpirit',
								maxLength:20,
								allowBlank:false,
								width:215
							}]
				}, {
					xtype : "panel",
					layout : "column",
					border : false,
					unstyled : false,
					style:'padding-top:5px;',
					height : 40,
					items : [{
								xtype : "label",
								text : "业务水平:",
								width : 60
							}, {
								xtype : "textfield",
								maxLength:20,
								name : "staffActiveapply.businessLevel",
								id:'businessLevel',
								allowBlank:false,
								width:215
							}, {
								xtype : "label",
								text : "成本意识:",
								width : 90,
								style : "padding-left:30px;"
							}, {
								xtype : "textfield",
								maxLength:20,
								name : "staffActiveapply.consciousness",
								id:'consciousness',
								allowBlank:false,
								width:215
							}]
				}, {
					xtype : "panel",
					layout : "column",
					border : false,
					unstyled : false,
					style:'padding-top:5px;',
					height : 40,
					items : [{
								xtype : "label",
								text : "创新能力:",
								width : 60
							}, {
								xtype : "textfield",
								maxLength:20,
								name : "staffActiveapply.innovationAbility",
								id:'innovationAbility',
								allowBlank:false,
								width:215
							}, {
								xtype : "label",
								text : "发展潜力:",
								width : 90,
								style : "padding-left:30px;"
							}, {
								xtype : "textfield",
								maxLength:20,
								name : "staffActiveapply.developAbility",
								id:'developAbility',
								allowBlank:false,
								width:215
							}]
				},{
					xtype : "panel",
					layout : "column",
					border : false,
					unstyled : false,
					style:'padding-top:5px;',
					height : 40,
					items : [{
								xtype : "label",
								text : "工作态度:",
								width : 60
							}, {
								xtype : "textfield",
								maxLength:20,
								name : "staffActiveapply.workAttitude",
								id:'workAttitude',
								allowBlank:false,
								width:215
							}, {
								xtype : "label",
								text : "品德言行:",
								width : 90,
								style : "padding-left:30px;"
							}, {
								xtype : "textfield",
								maxLength:20,
								name : "staffActiveapply.character",
								id:'character',
								allowBlank:false,
								width:215
							}]
				},{
					xtype : "label",
					text : "用人部门负责人意见（以工作任务为期限填写A项）:"
				},{
					xtype : "panel",
					layout : "form",
					border : false,
					unstyled : false,
					style:'padding-top:5px;',
					items : [{
				           xtype:'panel',
				           layout:'column',
				           border:false,
				           items:[{
						 xtype: 'radiogroup',
						 id   : 'salaryOption',
						 name: 'staffActiveapply.salaryOption',
						 width:40,
						 columns:1,
						 items:[{
				           		boxLabel: 'A:',
							name:'staffActiveapply.salaryOption',
							checked : true,
							inputValue: 'A',
							listeners:{
									check:function(cb,checked){
										//var contractOptions = Ext.query("*[name=contractOption]");
											if(checked){
											     Ext.getCmp("contractOption").reset();
												 Ext.getCmp("contractTime").reset();
												 Ext.getCmp("contractOption").disable();
												 Ext.getCmp("contractTime").disable();
												 Ext.getCmp("salaryMonthB").disable();
												 Ext.getCmp("salaryMonthB").reset();
												 Ext.getCmp("salaryBusinessB").disable();
												 Ext.getCmp("salaryBusinessB").reset();
												 Ext.getCmp("salaryMonthA").enable();
												 Ext.getCmp("salaryBusinessA").enable();
											}else{
												 Ext.getCmp("salaryMonthA").disable();
												 Ext.getCmp("salaryMonthA").reset();
												 Ext.getCmp("salaryBusinessA").disable();
												 Ext.getCmp("salaryBusinessA").reset();
												 Ext.getCmp("salaryMonthB").enable();
												 Ext.getCmp("salaryBusinessB").enable();
												 Ext.getCmp("contractOption").enable();
												 if(Ext.getCmp("contractOption").getValue().inputValue=='合同制员工'){
												  	 Ext.getCmp("contractTime").enable();
												 }
//												Ext.each(contractOptions,function(item,i){
//													//alert(typeof(Ext.get(item)))
//													//alert(item.innerHTML)
//													//alert(Ext.get(item).dom().value);
//												})
											}
										}
									}
				           	},{

						           		boxLabel: 'B:',
									name:'staffActiveapply.salaryOption',
									inputValue: 'B',
									listeners:{
										check:function(cb,checked){
										  //var contractOptions = Ext.query("*[name=contractOption]");
											if(checked){
												Ext.getCmp("salaryMonthA").disable();
												 Ext.getCmp("salaryMonthA").reset();
												 Ext.getCmp("salaryBusinessA").disable();
												 Ext.getCmp("salaryBusinessA").reset();
												 Ext.getCmp("salaryMonthB").enable();
												 Ext.getCmp("salaryBusinessB").enable();
												 Ext.getCmp("contractOption").enable();
												 if(Ext.getCmp("contractOption").getValue().inputValue=='合同制员工'){
												  	 Ext.getCmp("contractTime").enable();
												 }
//												Ext.each(contractOptions,function(item,i){
//													//alert(typeof(Ext.get(item)))
//													//alert(item.innerHTML)
//													//alert(Ext.get(item).dom().value);
//												})
											}else{
											   	 Ext.getCmp("contractOption").reset();
												 Ext.getCmp("contractTime").reset();
												 Ext.getCmp("contractOption").disable();
												 Ext.getCmp("contractTime").disable();
												 Ext.getCmp("salaryMonthB").disable();
												 Ext.getCmp("salaryMonthB").reset();
												 Ext.getCmp("salaryBusinessB").disable();
												 Ext.getCmp("salaryBusinessB").reset();
												 Ext.getCmp("salaryMonthA").enable();
												 Ext.getCmp("salaryBusinessA").enable();
											}
										}
									}
						     }]
					  },{
							xtype:'panel',
							border:false,
						        width:550,
							items:[
								{
									xtype:'container',
									layout:'column',
									items:[
										{
											xtype:'label',
											text:' 考核期满后月薪',
											width:90
										},{
											xtype:'numberfield',
											name:'staffActiveapply.salaryMonthA',
											maxLength:10,
											id:'salaryMonthA',
											width:80
										},{
											xtype:'label',
											text:'元/月;',
											width:50
										},{
											xtype:'label',
											text:' 按业绩考核定:',
											width:90
										},{
											xtype:'numberfield',
											maxLength:10,
											name:'staffActiveapply.salaryBusinessA',
											id:'salaryBusinessA',
											width:80
										},{
											xtype:'label',
											text:'元;',
											width:50
										}
									]
								},{
										xtype:'panel',
										border:false,
										layout:'column',
										items:[{
												xtype:'label',
												text:' 试用期满同意转正为：',
												width:120
											}
										]
									}
									,{
										xtype:'panel',
										layout:'form',
										border:false,
										items:[
											{
												xtype:'panel',
												border:false,
												layout:'column',
												items:[{
													 xtype: 'radiogroup',
													 id   : 'contractOption',
													 name: 'staffActiveapply.contractOption',
													 disabled:true,
													 style:'margin-left:50px;',
													 width:200,
													 items:[{
													 	checked : true,
													 	name:'staffActiveapply.contractOption',
														boxLabel:'聘用制员工',
														inputValue:'聘用制员工'
													},{
														name:'staffActiveapply.contractOption',
														boxLabel:'合同制员工',
														inputValue:'合同制员工'
													}],
													listeners:{
														change:function(field,newValue,oldValue){
															if(newValue.inputValue=='合同制员工'){
																Ext.getCmp("contractTime").enable();
															}else{
																Ext.getCmp("contractTime").disable();
															}
														}
													}
												 },{
														xtype:'label',
													 	style:'margin-top:5px;',
														text:'（合同期限为 '
												},{
													 xtype: 'radiogroup',
													 id   : 'contractTime',
													 name: 'staffActiveapply.contractTime',
													 disabled:true,
													 style:'margin-left:5px;',
													 width:100,
													 items:[{
													 	checked : true,
													 	name: 'staffActiveapply.contractTime',
														boxLabel:'2年',
														inputValue:'2年'
													},{
														name: 'staffActiveapply.contractTime',
														boxLabel:'3年',
														inputValue:'3年'
													}]
												 },{
														xtype:'label',
														 style:'margin-top:5px;',
														text:' )'
													}
												]
											},{
												xtype:'panel',
												border:false,
												layout:'column',
												style:'padding-top:5px;',
												items:[
													{
														xtype:'label',
														style:'margin-left:50px;',
														text:'转正后月薪：'
													},{
														xtype:'numberfield',
														name:'staffActiveapply.salaryMonthB',
														maxLength:10,
														id:'salaryMonthB',
														disabled:true,
														width:80
													},{
														xtype:'label',
														text:'元/月;',
														width:50
													},{
														xtype:'label',
														text:' 基本年薪（按业绩考核定）:'
													},{
														xtype:'numberfield',
														name:'staffActiveapply.salaryBusinessB',
														maxLength:10,
														id:'salaryBusinessB',
														disabled:true,
														width:80
													},{
														xtype:'label',
														text:'元;',
														width:50
													}
												]
											}
										]
									}
							]
						}

				           ]
				        }]
				}
//				,{
//							xtype : 'panel',
//							layout : 'column',
//							defaultType : 'label',
//							style:'padding-top:10px;',
//							border:false,
//							items:[
//								{
//									text : '部门领导:',
//									width : 90
//								},
//								{
//									xtype:'textfield',
//									fieldLabel:'部门领导',
//									name:'staffActiveapply.deptLeaderName',
//									readOnly:true,
//									style:"background:#F0F0F0;",
//									value:__currentUser,
//									width:450
//								}
//							]
//							
//				}
				,{
							xtype : 'panel',
							layout : 'column',
							defaultType : 'label',
							style:'padding-top:3px;margin-buttom:5px',
							height: 70,
							border:false,
							items : [{
										text : '转正评估表:',
										width : 90
									},{
										xtype : 'hidden',
										name : 'staffActiveapply.attachIds',
										id : 'attachIds'
									},{
										xtype : 'hidden',
										name : 'staffActiveapply.attachFiles',
										id : 'attachFiles'
									},{
										xtype : 'panel',
										id:'staffActiveDisplayAttach',
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
											var dialog = App.createUploadDialog({
																	file_cat : 'flow/staffActive',
																	callback : function(data) {
																		Ext.getCmp('staffActiveDisplayAttach').body.update('');
																		Ext.getCmp("attachFiles").setValue('');
																		Ext.getCmp('attachIds').setValue('');
																		var fileIds = Ext.getCmp('attachIds');
																		var display = Ext.getCmp('staffActiveDisplayAttach');
																		var staffActiveFile = Ext.getCmp("attachFiles");
																		
																		for (var i = 0; i < data.length; i++) {
																			if (fileIds.getValue() != '') {
																				fileIds.setValue(fileIds.getValue()+ ',');
																				staffActiveFile.setValue(staffActiveFile.getValue()+ ',');
																			}
																			staffActiveFile.setValue(staffActiveFile.getValue()+data[i].filepath+":"+data[i].filename.replace(/\s+/g,""));
//																			staffActiveFile.setValue(staffActiveFile.getValue()+"<a href=javascript:window.location.href=\""+__ctxPath+"/file-Download?filePath=attachFiles/"+data[i].filepath+"&fileName="+data[i].filename.replace(/\s+/g,"")+"\">"+data[i].filename.replace(/\s+/g,"")+"</a>");
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
		//加载表单对应的数据	
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getStaffActiveapply.do?id='+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
						var jsonData = Ext.util.JSON.decode(action.response.responseText);
						var res = jsonData.data;
						var attachId = res.attachIds;
						var attachFile = res.attachFiles;
						if(attachId!= null && attachId != 'undefined' && attachFile!= null && attachFile != 'undefined'){
							var ids = attachId.split(',');
							var files= attachFile.split(',');
							for(var i=0;i<ids.length;i++){
								if(files[i].lastIndexOf('<a href')!=-1){
									  Ext.DomHelper.append(Ext.getCmp('staffActiveDisplayAttach').body,'<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+files[i].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
								}else if(files[i].lastIndexOf(':')!=-1){
									  var fg = files[i].split(':');
									  Ext.DomHelper.append(Ext.getCmp('staffActiveDisplayAttach').body,'<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+fg[1].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
								}
							}
						}
						var firstAttachId = res.firstAttachIds;
						var firstAttachFile = res.firstAttachFiles;
						if(firstAttachId!= null && firstAttachId != 'undefined' && firstAttachFile!= null && firstAttachFile != 'undefined'){
							var ids = firstAttachId.split(',');
							var files= firstAttachFile.split(',');
							for(var i=0;i<ids.length;i++){
								if(files[i].lastIndexOf('<a href')!=-1){
									  Ext.DomHelper.append(Ext.getCmp('displayAttachmentAttachStaff').body,'<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+files[i].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
								}else if(files[i].lastIndexOf(':')!=-1){
									  var fg = files[i].split(':');
									  Ext.DomHelper.append(Ext.getCmp('displayAttachmentAttachStaff').body,'<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+fg[1].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
								}
							}
						}
						Ext.getCmp("salaryOption").originalValue = res.salaryOption;
						Ext.getCmp("contractOption").originalValue = res.contractOption;
						Ext.getCmp("contractTime").originalValue = res.contractTime;
						Ext.getCmp('accessionTime').originalValue = res.applyUser.accessionTime;
						Ext.getCmp('applyUserId').setValue(res.applyUser.userId);
						Ext.getCmp('accessionTime').setValue(res.applyUser.accessionTime);
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
			}
			];
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
					var gridPanel=Ext.getCmp('StaffActiveapplyGrid');
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
function removeFile(obj, fileId) {
	var fileIds = Ext.getCmp("staffActiveAttachIDs");
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