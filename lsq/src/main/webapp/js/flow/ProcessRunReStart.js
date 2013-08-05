ProcessRunReStart = Ext
		.extend(
				Ext.Panel,
				{
					formPanel : null,
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.buttonsArr = [ {
							text : "提交并启动流程",
							iconCls : "btn-ok",
							scope : this,
							id:'startFlowBtn',
							handler : this.saveAndStart
						}, {
							text : "重置表单",
							scope : this,
							iconCls : "btn-reset",
							handler : this.reset
						} ];
						ProcessRunReStart.superclass.constructor.call(this, {
							autoScroll : true,
							layout : "hbox",
							tbar : new Ext.Toolbar({
								height : 26,
								items : this.buttonsArr
							}),
							layoutConfig : {
								padding : "5",
								pack : "center",
								align : "middle"
							},
							defaults : {
								margins : "0 5 10 0"
							},
							title : "流程启动-" + this.flowName,
							iconCls : "btn-flow-start"
						});
					},
					saveAndStart : function() {
						var b = this;
						var a = b.formPanel;
						if (a.getForm().isValid()) {
							a
									.getForm()
									.submit(
											{
												url : __ctxPath
														+ "/flow/saveProcessActivity.do",
												waitMsg : "正在提交流程表单信息...",
												params : {
													defId : this.defId,
													runId : this.runId,
													activityName : this.activityName,
													startFlow : true,
													reStart:true
												},
												success : function(c, d) {
													Ext.ux.Toast.msg("操作信息",
															"成功保存信息！");
													AppUtil.removeTab(b.getId());
													var e = Ext.getCmp("MyProcessRunGrid");
													var processRunGrid = Ext.getCmp("ProcessRunGrid");
													setTimeout(function(){
														if (e != null) {
															e.getStore().reload();
														}
														
														if (processRunGrid != null) {
															processRunGrid.getStore().reload();
														}
													},1000);
												}
											});
						}
					},
					reset : function() {
						this.formPanel.getForm().reset();
					},
					initComponent : function() {
						ProcessRunReStart.superclass.initComponent.call(this);
						var topPanel = this;
						var activityName = this.activityName;
						var defId = this.defId;
						var runId = this.runId;;
						$request({
							url : __ctxPath + "/flow/getProcessActivity.do",
							params : {
								activityName : activityName,
								defId : this.defId,
								runId : this.runId
							},
							success : function(response, options) {
								var isFormPanel = true;
								if (response.responseText.trim().indexOf("[") == 0) {
									if (activityName == ""
											|| activityName == "undefined"
											|| activityName == null) {
										activityName = "开始";
									}
									eval('topPanel.formPanel = new Ext.FormPanel({title:"任务表单-'
											+ activityName
											+ '",defaults:{border:false},bodyStyle:"padding:8px 8px 8px 8px;",layout:"form",width:750,autoHeight:true,autoScroll:true,items:'
											+ response.responseText + "});");
								} else {
									if (response.responseText
											.indexOf("Ext.extend(Ext.Panel") != -1) {
										isFormPanel = false;
									}
									eval("topPanel.formPanel= new ("
											+ response.responseText + ")();");
								}
								if (!isFormPanel) {
									topPanel.formPanel.setDefId(defId);
									topPanel.getTopToolbar().removeAll();
									topPanel.getTopToolbar().setHeight(0);
								}
								topPanel.add(topPanel.formPanel);
								topPanel.doLayout();
								
								//加载表单数据
								Ext.Ajax.request({
									url : __ctxPath + "/flow/getByRunIdProcessForm.do",
									params : {
										runId : runId
									},
									success : function(response, options) {
										//alert(response.responseText);
										var result = Ext.util.JSON.decode(response.responseText);
										
										var prefix = "";
										var attachId;
										var attachFile;
										for(var item in result){
											var cmp=Ext.getCmp('flow_'+item);
											var bigItem = Ext.util.Format.lowercase(item);
											//alert(item+'--result:'+result[item]);
//											if(item.indexOf('.')!=-1){
//												prefix = 'flow_'+item.substring(0,item.indexOf('.')+1);
//											}
											if(cmp!= null && cmp != 'undefined'){
												
												if(cmp.getXType()=='datefield' || cmp.getXType()=='timefield'|| cmp.getXType()=='datetimefield'){
													
													if(!Ext.isEmpty(result[item])&& result[item]!= 'null'){
														cmp.setValue(new Date(getDateFromFormat(result[item].substring(0,19), "yyyy-MM-dd HH:mm:ss")));
													}
												}else if(cmp.getXType()=='radio'){
												    if(result[item]=='是'||result[item]=='有'||result[item]=='良好'||result[item]=='公开招聘'){
												    	cmp.setValue(true);
												    }else{
												    	Ext.getCmp(item+'No').setValue(true);
												    }
												}else if(item.indexOf('attachFiles')!=-1||bigItem.indexOf('attachfile')!=-1){
													attachFile=result[item];
													cmp.setValue(result[item]);
												}else if(item.indexOf('attachIds')!=-1||bigItem.indexOf('attachid')!=-1){
													attachId=result[item];
													cmp.setValue(result[item]);
												}else if(item.indexOf('GridData')!=-1){
													var rows=result[item].replace(/;,/g,";").trim().split(';');
													var arrrows= new Array();
													
													if(rows!=null&&rows!=''){
														for(i=0;i<rows.length;i++){
															var arrcolumns= new Array();
															var columns=rows[i].split(',');
															
															if(columns!=null&&columns!=''){
																for(j=0;j<columns.length;j++){
																	//alert(columns[j].substring(columns[j].indexOf(':')+1));
																	arrcolumns.push(columns[j].substring(columns[j].indexOf(':')+1));
																}
															}
															arrrows.push(arrcolumns);														
														}
													}
													
													//去除空元素
													for(k in arrrows)
    													if(arrrows[k] == "") arrrows.splice(k,k+1);
    													
    												//alert(arrrows);
    												//载入数据
													Ext.getCmp('flow_'+item+'Grid').getStore().loadData(arrrows);
													Ext.getCmp('flow_'+item+'Grid').getStore().fireEvent('update');
												}else{
													cmp.setValue(result[item]==null||result[item]==''||result[item]=='null'?'':result[item]);
												}
												
												
												if(cmp.getValue()!=null && cmp.getValue()!=''){
													cmp.enable();
												}
											}
										}
//										alert(Ext.get(Ext.query("*[id$=displayAttach]")[0]).first().first());
										if(attachId!= null && attachId != 'undefined' && attachFile!= null && attachFile != 'undefined'){
											var ids = attachId.split(',');
											var files= attachFile.split(',');
											for(var i=0;i<ids.length;i++){
												if(files[i].lastIndexOf(':')!=-1){
													  var fg = files[i].split(':');
//													  Ext.get(Ext.query("*[id$=displayAttach]")[0]).child()
//													  Ext.DomHelper.append(Ext.getCmp(prefix+'displayAttach').body,'<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+fg[1].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
													  Ext.DomHelper.append(Ext.get(Ext.query("*[id$=displayAttach]")[0]).first().first(),'<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+fg[1].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
//													  Ext.DomHelper.insertFirst(Ext.query("*[id$=displayAttach]")[0],'<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+fg[1].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
//													  Ext.get(Ext.query("*[id$=displayAttach]")[0]).first().first().innerHTML = '<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+fg[1].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>';
												}
											}
										}
										
										
										
									}
								});
							}
						});
						
					}
				});