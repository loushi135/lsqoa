var DepSelector = {
	getView : function(h, f) {
		var e = new Ext.tree.TreePanel({
					title : "部门信息显示",
					region : "west",
					width : 180,
					height : 300,
					split : true,
					collapsible : true,
					autoScroll : true,
					bbar : new Ext.Toolbar({
								items : [{
											xtype : "button",
											iconCls : "btn-refresh",
											text : "刷新",
											handler : function() {
												e.root.reload();
											}
										}, {
											xtype : "button",
											text : "展开",
											iconCls : "btn-expand",
											handler : function() {
												e.expandAll();
											}
										}, {
											xtype : "button",
											text : "收起",
											iconCls : "btn-collapse",
											handler : function() {
												e.collapseAll();
											}
										}]
							}),
					loader : new Ext.tree.TreeLoader({
								url : __ctxPath + "/system/listDepartment.do"
							}),
					root : new Ext.tree.AsyncTreeNode({
								expanded : true
							}),
					rootVisible : false,
					listeners : {
						"click" : function(j) {
							if (j != null) {
								var k = Ext.getCmp("DepSelectorGrid");
								var i = k.getStore();
								i.proxy.conn.url = __ctxPath
										+ "/system/selectDepartment.do";
								i.baseParams = {
									depId : j.id
								};
								i.load({
											params : {
												start : 0,
												limit : 12
											}
										});
							}
						}
					}
				});
		var g = null;
		if (f) {
			var g = new Ext.grid.CheckboxSelectionModel({
						singleSelect : true
					});
		} else {
			g = new Ext.grid.CheckboxSelectionModel();
		}
		var a = new Ext.grid.ColumnModel({
					columns : [g, new Ext.grid.RowNumberer(), {
								header : "depId",
								dataIndex : "depId",
								hidden : true
							}, {
								header : "部门名称",
								dataIndex : "depName",
								renderer : function(m, l, j) {
									var n = "";
									var o = j.data.depLevel;
									if (o != null && !isNaN(o)) {
										for (var k = 2; k <= o; k++) {
											n += '<img src="'
													+ __ctxPath
													+ '/images/system/down.gif"/>';
										}
									}
									n += m;
									return n;
								},
								width : 60
							}]
				});
		var b = new Ext.data.Store({
					proxy : new Ext.data.HttpProxy({
								url : __ctxPath + "/system/selectDepartment.do"
							}),
					reader : new Ext.data.JsonReader({
								root : "result",
								totalProperty : "totalCounts",
								id : "depId",
								fields : [{
											name : "depId",
											type : "int"
										}, "depName", {
											name : "depLevel",
											type : "int"
										}]
							}),
					remoteSort : true
				});
		var c = new Ext.grid.GridPanel({
					id : "DepSelectorGrid",
					width : 530,
					height : 300,
					region : "center",
					title : "部门列表",
					store : b,
					shim : true,
					trackMouseOver : true,
					disableSelection : false,
					loadMask : true,
					cm : a,
					sm : g,
					viewConfig : {
						forceFit : true,
						enableRowBody : false,
						showPreview : false
					},
					bbar : new Ext.PagingToolbar({
								pageSize : 25,
								store : b,
								plugins : [new Ext.ux.PageSizePlugin()],
								displayInfo : true,
								displayMsg : "当前显示从{0}至{1}， 共{2}条记录",
								emptyMsg : "当前没有记录"
							})
				});
		b.load({
					params : {
						start : 0,
						limit : 25
					}
				});
		var d = new Ext.Window({
					title : "部门选择器",
					iconCls : "menu-department",
					width : 730,
					height : 380,
					layout : "border",
					border : false,
					items : [e, c],
					modal : true,
					buttonAlign : "center",
					buttons : [{
								iconCls : "btn-ok",
								text : "确定",
								handler : function() {
									var k = Ext.getCmp("DepSelectorGrid");
									var l = k.getSelectionModel()
											.getSelections();
									var m = "";
									var n = "";
									for (var j = 0; j < l.length; j++) {
										if (j > 0) {
											m += ",";
											n += ",";
										}
										m += l[j].data.depId;
										n += l[j].data.depName;
									}
									if (h != null) {
										h.call(this, m, n);
									}
									d.close();
								}
							}, {
								text : "取消",
								iconCls : "btn-cancel",
								handler : function() {
									d.close();
								}
							}]
				});
		return d;
	}
};