NoticeForm = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		NoticeForm.superclass.constructor.call(this, {
			layout : "fit",
			id : "NoticeFormWin",
			iconCls : "menu-notice",
			title : "公告详细信息",
			width : 500,
			height : 530,
			minWidth : 499,
			minHeight : 409,
			items : this.formPanel,
			maximizable : true,
			border : false,
			modal : true,
			plain : true,
			buttonAlign : "center",
			buttons : this.buttons
		});
	},
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel({
			url : __ctxPath + "/info/saveNotice.do",
			layout : "form",
			id : "NoticeForm",
			bodyStyle : "padding:5px;",
			frame : false,
			width : 480,
			formId : "NoticeFormId",
			defaultType : "textfield",
			items : [{
						name : "notice.noticeId",
						id : "noticeId",
						xtype : "hidden",
						value : this.noticeId == null ? "" : this.noticeId
					}, {
						fieldLabel : "发布者",
						name : "notice.postName",
						id : "postName",
						allowBlank : false,
						blankText : "发布者不能为空",
						anchor : "98%",
						value : curUserInfo.fullname
					}, {
						fieldLabel : "公告标题",
						name : "notice.noticeTitle",
						id : "noticeTitle",
						allowBlank : false,
						blankText : "公告标题不能为空",
						anchor : "98%"
					}, {
						fieldLabel : "公告内容",
						name : "notice.noticeContent",
						id : "noticeContent",
						xtype : "htmleditor",
						height : 200,
						allowBlank : false,
						blankText : "公告内容不能为空",
						anchor : "98%"
					}, {
						fieldLabel : "生效日期",
						name : "notice.effectiveDate",
						id : "effectiveDate",
						xtype : "datefield",
						format : "Y-m-d",
						anchor : "98%"
					}, {
						fieldLabel : "失效日期",
						name : "notice.expirationDate",
						id : "expirationDate",
						xtype : "datefield",
						format : "Y-m-d",
						anchor : "98%"
					}, {
						xtype : "combo",
						fieldLabel : "发布状态",
						allowBlank : false,
						hiddenName : "notice.state",
						id : "state",
						emptyText : "请选择发布状态",
						mode : "local",
						editable : false,
						triggerAction : "all",
						store : [["0", "草稿"], ["1", "立即发布"]],
						anchor : "98%",
						value : "1"
					},{
						xtype : 'container',
						autoHeight : true,
						layout : 'column',
						autoWidth : true,
						defaultType : 'label',
						style : 'padding-left:0px;padding-bottom:5px;',
						items : [{
									text : '附件:',
									width : 95,
									style : "margin-top:15px;"
								},{
									xtype : 'hidden',
									name : 'attachIds',
									id : 'notice.attachIds'
								},{
									xtype : 'panel',
									id:'notice.displayAttach',
									height: 65,
									width:315,
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
																file_cat : 'flow/notice',
																callback : function(data) {
																	var fileIds = Ext.getCmp('notice.attachIds');
																	var display = Ext.getCmp('notice.displayAttach');
																	display.body.update('');
																	fileIds.setValue('');
																	for (var i = 0; i < data.length; i++) {
																		if (fileIds.getValue() != '') {
																			fileIds.setValue(fileIds.getValue()+ ',');
																		}
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
		if (this.noticeId != null && this.noticeId != "undefined") {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + "/info/getNotice.do?noticeId=" + this.noticeId,
				waitMsg : "正在载入数据...",
				success : function(b, c) {
					var d = c.result.data.effectiveDate;
					var e = Ext.getCmp("effectiveDate");
					var a = c.result.data.expirationDate;
					var f = Ext.getCmp("expirationDate");
					e.setValue(new Date(getDateFromFormat(d, "yyyy-MM-dd HH:mm:ss")));
					if (a != null) {
						f.setValue(new Date(getDateFromFormat(a, "yyyy-MM-dd HH:mm:ss")));
					}
					var res = c.result.data;
					var af = res.attachFiles;
					var filePanel = Ext.getCmp('notice.displayAttach');
					var fileIds = Ext.getCmp("notice.attachIds");
					for (var i = 0; i < af.length; i++) {
						if (fileIds.getValue() != '') {
							fileIds.setValue(fileIds.getValue() + ',');
						}
						fileIds.setValue(fileIds.getValue() + af[i].fileId);
						Ext.DomHelper.append(filePanel.body,'<span><a href="#" onclick="FileAttachDetail.show('
												+ af[i].fileId
												+ ')">'
												+ af[i].fileName.replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
					}
					fileIds.originalValue = fileIds.getValue();
				},
				failure : function(a, b) {
					Ext.ux.Toast.msg("编辑", "载入失败");
				}
			});
		}
		this.buttons = [{
					text : "保存",
					iconCls : "btn-save",
					handler : function() {
						var a = Ext.getCmp("NoticeForm");
						if (a.getForm().isValid()) {
							a.getForm().submit({
								method : "post",
								waitMsg : "正在提交数据...",
								success : function(b, c) {
									Ext.ux.Toast.msg("操作信息", "成功保存信息！");
									Ext.getCmp("NoticeGrid").getStore().reload();
									Ext.getCmp("NoticeFormWin").close();
								},
								failure : function(b, c) {
									Ext.MessageBox.show({
										title : "操作信息",
										msg : "信息保存出错，请联系管理员！",
										buttons : Ext.MessageBox.OK,
										icon : "ext-mb-error"
									});
									Ext.getCmp("NoticeFormWin").close();
								}
							});
						}
					}
				}, {
					text : "取消",
					iconCls : "btn-cancel",
					handler : function() {
						Ext.getCmp("NoticeFormWin").close();
					}
				}];
	}
});