var UserGroupSelector = {
	getView : function(e, c, d) {
		var a = this.initPanel(c);
		var b = new Ext.Window({
			title : "选择短信组用户",
			iconCls : "menu-appuser",
			width : 640,
			height : 480,
			layout : "fit",
			border : false,
			items : [a],
			resizable : false,
			modal : true,
			buttonAlign : "center",
			buttons : [{
						text : "确认",
						iconCls : "btn-ok",
						scope : "true",
						handler : function() {
							var l = "";
							var j = "";
							var mo = "";
							var email = "";
							if (c) {
								var h = Ext.getCmp("userGroupGrid");
								var k = h.getSelectionModel().getSelections();
								for (var g = 0; g < k.length; g++) {
									if (g > 0) {
										l += ",";
										j += ",";
										mo += ",";
										email += ","
									}
									l += k[g].data.userId;
									j += k[g].data.fullname;
									mo += k[g].data.mobile;
									email += k[g].data.email;
								}
							} else {
								var f = Ext.getCmp("selectedUserGroupGrid").getStore();
								for (var g = 0; g < f.getCount(); g++) {
									if (g > 0) {
										l += ",";
										j += ",";
										mo += ",";
										email += ","
									}
									l += f.getAt(g).data.userId;
									j += f.getAt(g).data.fullname;
									mo += f.getAt(g).data.mobile;
									email += f.getAt(g).data.email;
								}
							}
							if (e != null) {
								e.call(this, l, j, mo, email);
							}
							b.close();
						}
					}, {
						text : "关闭",
						iconCls : "btn-cancel",
						handler : function() {
							b.close();
						}
					}]
		});
		return b;
	},
	initPanel : function(e) {
		var i = new Ext.data.Store({
			proxy : new Ext.data.HttpProxy({
				url : __ctxPath + "/system/getUserByGroupIdSmsGroup.do"
			}),
			reader : new Ext.data.JsonReader({
				root : "result",
				totalProperty : "totalCounts",
				id : "id",
				fields : [{
							name : "userId",
							type : "int"
						}, "fullname", "title", "mobile", "email"]
			}),
			remoteSort : true
		});
		i.setDefaultSort("id", "desc");
		i.load({
			params : {
				start : 0,
				limit : 12,
				id:-1
			}
		});
		var c = null;
		if (e) {
			c = new Ext.grid.CheckboxSelectionModel({
				singleSelect : true
			});
		} else {
			c = new Ext.grid.CheckboxSelectionModel();
		}
		var j = new Ext.grid.ColumnModel({
			columns : [c, new Ext.grid.RowNumberer({width:30}), {
						header : "用户名",
						dataIndex : "fullname",
						renderer : function(o, p, n) {
							var q = n.data.title;
							if (q == 1) {
								return '<img src="' + __ctxPath + '/images/flag/man.png"/>&nbsp;' + o;
							} else {
								return '<img src="' + __ctxPath + '/images/flag/women.png"/>&nbsp;' + o;
							}
						},
						width : 60
					}],
			defaults : {
				sortable : true,
				menuDisabled : true,
				width : 120
			},
			listeners : {
				hiddenchange : function(n, o, p) {
					saveConfig(o, p);
				}
			}
		});
		var d = new Ext.tree.TreePanel({
			title : "短信组 ",
			width:120,
			iconCls : "dep-user",
			loader : new Ext.tree.TreeLoader({
				url : __ctxPath + "/system/treeSmsGroup.do"
			}),
			root : {},
			rootVisible : false,
			listeners : {
				"click" : this.clickNode
			}
		});
		var k = new Ext.grid.GridPanel({
			title : "用户列表",
			id : "userGroupGrid",
			region : "center",
			height : 400,
			autoWidth : true,
			store : i,
			shim : true,
			trackMouseOver : true,
			disableSelection : false,
			loadMask : true,
			cm : j,
			sm : c,
			viewConfig : {
				forceFit : true,
				enableRowBody : false,
				showPreview : false
			},
			bbar : new Ext.PagingToolbar({
				pageSize : 12,
				store : i,
				displayInfo : true,
				displayMsg : "当前显示从{0}至{1}， 共{2}条记录",
				emptyMsg : "当前没有记录"
			})
		});
		var h = new Ext.grid.CheckboxSelectionModel();
		var a = new Ext.grid.GridPanel({
			id : "selectedUserGroupGrid",
			title : "已选用户",
			height : 400,
			autoScroll : true,
			store : new Ext.data.ArrayStore({
				fields : ["userId", "fullname", 'mobile', 'email']
			}),
			trackMouseOver : true,
			sm : h,
			columns : [h, new Ext.grid.RowNumberer(), {
						header : "用户名",
						dataIndex : "fullname"
					}]
		});
		var m = new Ext.Panel({
			width : 200,
			region : "east",
			layout : "column",
			border : false,
			items : [new Ext.Panel({
						frame : true,
						width : 35,
						height : 430,
						layout : {
							type : "vbox",
							pack : "center",
							align : "stretch"
						},
						defaults : {
							margins : "0 0 5 0"
						},
						items : [{
									xtype : "button",
									text : "&gt;&gt;",
									handler : function() {
										var t = Ext.getCmp("userGroupGrid");
										var n = Ext.getCmp("selectedUserGroupGrid");
										var u = n.getStore();
										var x = t.getSelectionModel().getSelections();
										for (var p = 0; p < x.length; p++) {
											var q = x[p].data.userId;
											var v = x[p].data.fullname;
											var mobile = x[p].data.mobile;
											var email = x[p].data.email;
											var s = false;
											for (var o = 0; o < u.getCount(); o++) {
												if (u.getAt(o).data.userId == q) {
													s = true;
													break;
												}
											}
											if (!s) {
												var w = {
													userId : q,
													fullname : v,
													mobile : mobile,
													email : email
												};
												var r = new u.recordType(w);
												n.stopEditing();
												u.add(r);
											}
										}
									}
								}, {
									xtype : "button",
									text : "&lt&lt;",
									handler : function() {
										var p = Ext.getCmp("selectedUserGroupGrid");
										var q = p.getSelectionModel().getSelections();
										var n = p.getStore();
										for (var o = 0; o < q.length; o++) {
											p.stopEditing();
											n.remove(q[o]);
										}
									}
								}]
					}), a]
		});
		var g = new Ext.Panel({
			width : 540,
			height : 410,
			layout : "border",
			border : false,
			items : [{
						region : "west",
						split : true,
						header : false,
						collapsible : true,
						width : 140,
						layout : "accordion",
						items : [d]
					}, k]
		});
		if (!e) {
			g.add(m);
		}
		return g;
	},
	clickNode : function(b) {
		if (b != null) {
			var c = Ext.getCmp("userGroupGrid");
			var a = c.getStore();
			a.proxy.conn.url = __ctxPath + "/system/getUserByGroupIdSmsGroup.do";
			a.baseParams = {
				id : b.attributes.groupId
			};
			a.load({
				params : {
					start : 0,
					limit : 12
				}
			});
		}
	}
};