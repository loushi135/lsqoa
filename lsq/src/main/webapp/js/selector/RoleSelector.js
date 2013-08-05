var RoleSelector = {
	getView : function(d, c) {
		var a = this.initGridPanel(c);
		var hp = new Ext.grid.CheckboxSelectionModel();
		var mp = new Ext.grid.GridPanel({
			id : "selectedRoleGrid",
			title : "已选角色",
			width :200,
			height : 330,
			autoScroll : true,
			store : new Ext.data.ArrayStore({
				fields : ["roleId", "roleName"]
			}),
			trackMouseOver : true,
			sm : hp,
			columns : [hp, new Ext.grid.RowNumberer(),  {
					header : "角色名称",
					dataIndex : "roleName",
					width : 150
				},{
					dataIndex : "roleId",
					hidden:true
				}]
		});
		var ap = new Ext.Panel({
			width : 200,
			region : "center",
			layout : "column",
			border : false,
			items : [new Ext.Panel({
					frame : true,
					width : 35,
					height : 330,
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
								var t = Ext.getCmp("RoleSelectorGrid");
								var n = Ext.getCmp("selectedRoleGrid");
								var u = n.getStore();
								var x = t.getSelectionModel().getSelections();
								for (var p = 0; p < x.length; p++) {
									//"proId", "proNo",'proName'
									var roleId = x[p].data.roleId;
									var roleName = x[p].data.roleName;

									var s = false;
									for (var o = 0; o < u.getCount(); o++) {
										if (u.getAt(o).data.roleId == roleId) {
											s = true;
											break;
										}
									}
									if(u.getCount()>0&&c){
										s=true;
									}
									if (!s) {
										var w = {
											roleId : roleId,
											roleName : roleName
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
								var p = Ext.getCmp("selectedRoleGrid");
								var q = p.getSelectionModel().getSelections();
								var n = p.getStore();
								for (var o = 0; o < q.length; o++) {
									p.stopEditing();
									n.remove(q[o]);
								}
							}
						}]
				}), mp]
		});
		var tab1GroupMgr = new Ext.WindowGroup();
		//前置窗口
		tab1GroupMgr.zseed=99999;
		var b = new Ext.Window({
			title : "角色选择",
			width : 650,
			height : 380,
			layout : "border",
			manager: tab1GroupMgr,
			border : false,
			items : [a,ap],
			modal : true,
			buttonAlign : "center",
			buttons : [{
					iconCls : "btn-ok",
					text : "确定",
					handler : function() {
						var g = Ext.getCmp("selectedRoleGrid");
						var o = g.getStore();;
						var j = "";
						var e = "";
						for (var m = 0; m < o.getCount(); m++) {
							if (m > 0) {
								j += ",";
								e += ",";
							}
							j +=  o.getAt(m).data.roleId;
							e +=  o.getAt(m).data.roleName;
						}
						if (d != null) {
							d.call(this, j, e);
						}
						b.close();
					}
				}, {
					text : "取消",
					iconCls : "btn-cancel",
					handler : function() {
						b.close();
					}
				}]
		});
		return b;
	},
	initGridPanel : function(e) {
		var f = null;
		if (e) {
			var f = new Ext.grid.CheckboxSelectionModel({
				singleSelect : true
			});
		} else {
			f = new Ext.grid.CheckboxSelectionModel();
		}
		var a = new Ext.grid.ColumnModel({
			columns : [f, new Ext.grid.RowNumberer(), {
					header : "roleId",
					dataIndex : "roleId",
					hidden : true
				}, {
					header : "角色名称",
					dataIndex : "roleName",
					width : 60
				}, {
					header : "角色描述",
					dataIndex : "roleDesc",
					width : 60
				}]
		});
		var b = new Ext.data.Store({
			proxy : new Ext.data.HttpProxy({
				url : __ctxPath + "/system/listAppRole.do"
			}),
			reader : new Ext.data.JsonReader({
				root : "result",
				totalProperty : "totalCounts",
				id : "id",
				fields : [{
						name : "roleId",
						type : "int"
					}, "roleName", "roleDesc"]
			})
		});
		b.load({
			params : {
				start : 0,
				limit : 25
			}
		});
		var d = new Ext.Toolbar({
			id : "AppRoleFootBar",
			height : 30,
			items : ["角色名称：", {
					name : "Q_roleName_S_LK",
					xtype : "textfield",
					id : "Q_roleName_S_LK",
					width : 200
				}, " ", {
					xtype : "button",
					iconCls : "btn-search",
					text : "查询",
					handler : function() {
						var g = Ext.getCmp("Q_roleName_S_LK").getValue();
						Ext.Ajax.request({
							url : __ctxPath + "/system/listAppRole.do",
							params : {
								Q_roleName_S_LK : g
							},
							method : "post",
							success : function(h, j) {
								var k = Ext.util.JSON.decode(h.responseText);
								var i = Ext.getCmp("RoleSelectorGrid");
								i.getStore().loadData(k);
							},
							failure : function(h, i) {
							}
						});
					}
				}]
		});
		var c = new Ext.grid.GridPanel({
			id : "RoleSelectorGrid",
			region : "west",
			tbar : d,
			store : b,
			trackMouseOver : true,
			disableSelection : false,
			loadMask : true,
			height : 360,
			width: 400,
			cm : a,
			sm : f,
			viewConfig : {
				forceFit : true,
				enableRowBody : false,
				showPreview : false
			},
			bbar : new Ext.PagingToolbar({
				pageSize : 25,
				store : b,
				plugins : [new Ext.ux.PageSizePlugin()], 
				displayInfo : false,
				displayMsg : "当前显示从{0}至{1}， 共{2}条记录",
				emptyMsg : "当前没有记录"
			})
		});
		return c;
	}
};