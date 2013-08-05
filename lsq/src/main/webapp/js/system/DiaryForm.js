var DiaryForm = function(a) {
	this.diaryId = a;
	var b = this.setup();
	var c = new Ext.Window({
		id : "DiaryFormWin",
		title : "日志详细信息",
		iconCls : "menu-diary",
		maximizable : true,
		width : 650,
		height : 500,
		modal : true,
		layout : "fit",
		buttonAlign : "center",
		items : [this.setup()],
		buttons : [{
			text : "保存",
			iconCls : "btn-save",
			handler : function() {
				var d = Ext.getCmp("DiaryForm");
				if (d.getForm().isValid()) {
					d.getForm().submit({
								method : "post",
								waitMsg : "正在提交数据...",
								success : function(e, f) {
									Ext.ux.Toast.msg("操作信息", "成功信息保存！");
									Ext.getCmp("DiaryGrid").getStore().reload();
									c.close();
								},
								failure : function(e, f) {
									Ext.MessageBox.show({
												title : "操作信息",
												msg : "信息保存出错，请联系管理员！",
												buttons : Ext.MessageBox.OK,
												icon : "ext-mb-error"
											});
									c.close();
								}
							});
				}
			}
		}, {
			text : "取消",
			iconCls : "btn-cancel",
			handler : function() {
				c.close();
			}
		}]
	});
	c.show();
};
DiaryForm.prototype.setup = function() {
	var a = new Ext.FormPanel({
				url : __ctxPath + "/system/saveDiary.do",
				layout : "form",
				id : "DiaryForm",
				frame : false,
				border : false,
				bodyStyle : "padding:5px;",
				defaults : {
					anchor : "100%,100%"
				},
				formId : "DiaryFormId",
				defaultType : "textfield",
				labelWidth : 65,
				items : [{
							name : "diary.diaryId",
							id : "diaryId",
							xtype : "hidden",
							value : this.diaryId == null ? "" : this.diaryId
						}, {
							xtype : "hidden",
							name : "diary.appUser.userId",
							id : "userId"
						}, {
							fieldLabel : "日期",
							xtype : "datefield",
							name : "diary.dayTime",
							id : "dayTime",
							editable : false,
							format : "Y-m-d"
						}, {
							fieldLabel : "日志类型",
							xtype : "combo",
							hiddenName : "diary.diaryType",
							id : "diaryType",
							mode : "local",
							editable : false,
							value : "0",
							triggerAction : "all",
							store : [["0", "个人日志"], ["1", "工作日志"]]
						}, {
							fieldLabel : "内容",
							xtype : "htmleditor",
							name : "diary.content",
							id : "content"
						},{
							xtype : 'container',
							autoHeight : true,
							layout : 'column',
							autoWidth : true,
							defaultType : 'label',
							style : 'padding-left:0px;padding-bottom:5px;',
							items : [{
										text : '附件:',
										width : 61,
										style : 'padding-left:0px;padding-top:3px;'
									},{
										xtype : 'hidden',
										name : 'diaryAttachIDs',
										id : 'diary_AttachIDs'
									},{
										xtype : 'hidden',
										name : 'diaryAttachFile',
										id : 'diary_AttachFile'
									},{
										xtype : 'panel',
										id:'diary_displayAttach',
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
													file_cat : 'diary/attach',
													callback : function(data) {
														Ext.getCmp('diary_displayAttach').body.update('');
														Ext.getCmp("diary_AttachFile").setValue('');
														Ext.getCmp('diary_AttachIDs').setValue('');
														var diaryFile = Ext.getCmp("diary_AttachFile");
														var fileIds = Ext.getCmp('diary_AttachIDs');
														var display = Ext.getCmp('diary_displayAttach');
														for (var i = 0; i < data.length; i++) {
															if (fileIds.getValue() != '') {
																fileIds.setValue(fileIds.getValue()+ ',');
																diaryFile.setValue(diaryFile.getValue()+ ',');
															}
															diaryFile.setValue(diaryFile.getValue()+"<a href=javascript:window.location.href=\""+__ctxPath+"/file-Download?filePath=attachFiles/"+data[i].filepath+"&fileName="+data[i].filename.replace(/\s+/g,"")+"\">"+data[i].filename.replace(/\s+/g,"")+"</a>");
															fileIds.setValue(fileIds.getValue()+data[i].fileId);
															Ext.DomHelper.append(display.body,'<span><a href="#" onclick="FileAttachDetail.show('+data[i].fileId+')">'+data[i].filename.replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
														}
													},
													permitted_max_size :1024*1024*20
												});
												dialog.show(this);
										}
									}]
						}]
			});
	if (this.diaryId != null && this.diaryId != "undefined") {
		a.getForm().load({
					deferredRender : false,
					url : __ctxPath + "/system/getDiary.do?diaryId="
							+ this.diaryId,
					waitMsg : "正在载入数据...",
					success : function(d, e) {
						var b = e.result.data;
						var c = getDateFromFormat(b.dayTime, "yyyy-MM-dd");
						Ext.getCmp("dayTime").setValue(new Date(c));
						var m = b.diaryFiles;
						var l = Ext.getCmp("diary_displayAttach");
						var o = Ext.getCmp("diary_AttachIDs");
						for (var j = 0; j < m.length; j++) {
						if (o.getValue() != "") {
							o.setValue(o.getValue() + ",");
						}
						o.setValue(o.getValue() + m[j].fileId);
						Ext.DomHelper
								.append(
										l.body,
										'<span><a href="#" onclick="FileAttachDetail.show('
												+ m[j].fileId
												+ ')">'
												+ m[j].fileName
												+ '</a><img class="img-delete" src="'
												+ __ctxPath
												+ '/images/system/delete.gif" onclick="removeContractAttach(this,'
												+ m[j].fileId
												+ ')"/>&nbsp;|&nbsp;</span>');
						}
					},
					failure : function(b, c) {
						Ext.ux.Toast.msg("编辑", "载入失败");
					}
				});
	}
	return a;
};