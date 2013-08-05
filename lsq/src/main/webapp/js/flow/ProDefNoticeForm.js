var ProDefNoticeForm = function(defId,name) {
	this.defId = defId;
	this.name = name;
};
ProDefNoticeForm.prototype.getView = function() {
	var b = this.defId;
	var a = new Ext.FormPanel({
		id : "ProDefNoticeForm",
		bodyStyle : "padding:4px 10px 4px 10px",
		items : [{
					xtype : "hidden",
					name : "proDefNotice.defId",
					id : "defId"
				}, {
					name : 'proDefNotice.proDefinition.defId',
					id : 'proDefNotice.proDefinition.defId',
					xtype : 'hidden',
					value : this.defId == null ? '' : this.defId
				}, {
					xtype : "fieldset",
					border : false,
					layout : "column",
					items : [{
								xtype : "label",
								text : "通知人员",
								width : 100
							}, {
								xtype : "hidden",
								name : "proDefNotice.noticeUserIds",
								id : "noticeUserIds"
							}, {
								xtype : "textarea",
								name : "proDefNotice.noticeUserNames",
								id : "noticeUserNames",
								readOnly:true,
								width : 300
							}, {
								xtype : "button",
								text : "选择",
								iconCls : "btn-select",
								width : 80,
								handler : function() {
									UserSelector.getView(function(e, g) {
										var d = Ext
												.getCmp("noticeUserIds");
										var h = Ext
												.getCmp("noticeUserNames");
										if (d.getValue() == "") {
											d.setValue("," + e + ",");
											h.setValue(g);
											return;
										} else {
											var i = d.getValue().split(",");
											var f = h.getValue().split(",");
											d.setValue(uniqueArray(i.concat(e
													.split(",")))
													+ ",");
											h.setValue(uniqueArray(f.concat(g
													.split(","))));
										}
									}).show();
								}
							}, {
								xtype : "button",
								iconCls : "btn-clear",
								text : "清空",
								handler : function() {
									var d = Ext
											.getCmp("noticeUserIds");
									var e = Ext
											.getCmp("noticeUserNames");
									d.setValue("");
									e.setValue("");
								},
								width : 80
							}, {
								xtype : "button",
								text : "选择全部",
								iconCls : "btn-select",
								width : 80,
								handler : function() {
									var url = __ctxPath + "/system/listAppUser.do";
									var params = "start=0&limit=9999&Q_userId_L_NEQ=1";
									var data = ajaxSyncCall(url,params).result;
									if(!Ext.isEmpty(data)){
										var userId = '';
										var fullname = '';
										Ext.each(data,function(item,i){
											if(userId!=''){
												userId+=',';
												fullname+=',';
											}
											userId +=item.userId;
											fullname +=item.fullname;
										});
										Ext.getCmp('noticeUserIds').setValue(userId);
										Ext.getCmp('noticeUserNames').setValue(fullname);
									}
								}
							}]
				}, {
					xtype : "fieldset",
					border : false,
					layout : "column",
					items : [{
								xtype : "label",
								text : "通知部门",
								width : 100
							}, {
								name : "proDefNotice.noticeDepIds",
								id : "noticeDepIds",
								xtype : "hidden"
							}, {
								name : "proDefNotice.noticeDepNames",
								id : "noticeDepNames",
								xtype : "textarea",
								readOnly:true,
								width : 300
							}, {
								xtype : "button",
								text : "选择",
								iconCls : "btn-select",
								width : 80,
								handler : function() {
									DepSelector.getView(function(f, h) {
										var e = Ext
												.getCmp("noticeDepIds");
										var g = Ext
												.getCmp("noticeDepNames");
										if (e.getValue() == "") {
											e.setValue("," + f + ",");
											g.setValue(h);
											return;
										}
										var i = e.getValue().split(",");
										var d = g.getValue().split(",");
										e.setValue(uniqueArray(i.concat(f
												.split(",")))
												+ ",");
										g.setValue(uniqueArray(d.concat(h
												.split(","))));
									}).show();
								}
							}, {
								xtype : "button",
								text : "清空",
								iconCls : "btn-clear",
								handler : function() {
									var d = Ext
											.getCmp("noticeDepIds");
									var e = Ext
											.getCmp("noticeDepNames");
									d.setValue("");
									e.setValue("");
								},
								width : 80
							}, {
								xtype : "button",
								text : "选择全部",
								iconCls : "btn-select",
								width : 80,
								handler : function() {
									var url = __ctxPath + "/system/selectDepartment.do";
									var params = "start=0&limit=9999";
									var data = ajaxSyncCall(url,params).result;
									if(!Ext.isEmpty(data)){
										var depId = '';
										var depName = '';
										Ext.each(data,function(item,i){
											if(depId!=''){
												depId+=',';
												depName+=',';
											}
											depId +=item.depId;
											depName +=item.depName;
										});
										Ext.getCmp('noticeDepIds').setValue(depId);
										Ext.getCmp('noticeDepNames').setValue(depName);
									}
								}
							}]
				}, {
					xtype : "fieldset",
					border : false,
					layout : "column",
					items : [{
								xtype : "label",
								text : "通知角色",
								width : 100
							}, {
								xtype : "hidden",
								id : "noticeRoleIds",
								name : "proDefNotice.noticeRoleIds"
							}, {
								name : "proDefNotice.noticeRoleNames",
								id : "noticeRoleNames",
								xtype : "textarea",
								readOnly:true,
								width : 300
							}, {
								xtype : "button",
								text : "选择",
								iconCls : "btn-select",
								width : 80,
								handler : function() {
									RoleSelector.getView(function(g, h) {
										var d = Ext
												.getCmp("noticeRoleIds");
										var e = Ext
												.getCmp("noticeRoleNames");
										if (d.getValue() == "") {
											d.setValue("," + g + ",");
											e.setValue(h);
											return;
										}
										var i = d.getValue().split(",");
										var f = e.getValue().split(",");
										d.setValue(uniqueArray(i.concat(g
												.split(",")))
												+ ",");
										e.setValue(uniqueArray(f.concat(h
												.split(","))));
									}).show();
								}
							}, {
								xtype : "button",
								text : "清空",
								iconCls : "btn-clear",
								handler : function() {
									var d = Ext
											.getCmp("noticeRoleIds");
									var e = Ext
											.getCmp("noticeRoleNames");
									d.setValue("");
									e.setValue("");
								},
								width : 80
							}, {
								xtype : "button",
								text : "选择全部",
								iconCls : "btn-select",
								width : 80,
								handler : function() {
									var url = __ctxPath + "/system/listAppRole.do";
									var params = "start=0&limit=9999&Q_roleId_L_NEQ=-1";
									var data = ajaxSyncCall(url,params).result;
									if(!Ext.isEmpty(data)){
										var roleId = '';
										var roleName = '';
										Ext.each(data,function(item,i){
											if(roleId!=''){
												roleId+=',';
												roleName+=',';
											}
											roleId +=item.roleId;
											roleName +=item.roleName;
										});
										Ext.getCmp('noticeRoleIds').setValue(roleId);
										Ext.getCmp('noticeRoleNames').setValue(roleName);
									}
								}
							}]
				}]
	});
	if (b != null && b != "" && b != "undefined") {
		a.getForm().load({
					deferredRender : false,
					url : __ctxPath + "/flow/getProDefNotice.do?defId=" + b,
					waitMsg : "正在载入数据...",
					success : function(d, e) {
					},
					failure : function(d, e) {
					}
				});
	}
	var c = new Ext.Window({
				title:"流程通知人员设置－" + this.name,
				width : 620,
				iconCls : "menu-mail_folder",
				height : 380,
				modal : true,
				layout : "anchor",
				plain : true,
				bodyStyle : "padding:5px;",
				scope : this,
				buttonAlign : "center",
				items : a,
				buttons : [{
					xtype : "button",
					text : "保存",
					iconCls : "btn-ok",
					handler : function() {
						a.getForm().submit({
									url : __ctxPath
											+ "/flow/saveProDefNotice.do",
									method : "post",
									waitMsg : "正在提交...",
									success : function(d, e) {
										c.close();
									}
								});
					}
				}, {
					xtype : "button",
					iconCls : "btn-cancel",
					text : "关闭",
					handler : function() {
						c.close();
					}
				}]
			});
	return c;
};