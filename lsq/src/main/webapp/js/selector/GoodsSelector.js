var GoodsSelector = {
	getView : function(i, c) {
		var f = new Ext.tree.TreePanel({
					title : "商品显示",
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
												f.root.reload();
											}
										}, {
											xtype : "button",
											text : "展开",
											iconCls : "btn-expand",
											handler : function() {
												f.expandAll();
											}
										}, {
											xtype : "button",
											text : "收起",
											iconCls : "btn-collapse",
											handler : function() {
												f.collapseAll();
											}
										}]
							}),
					loader : new Ext.tree.TreeLoader({
								url : __ctxPath
										+ "/admin/treeOfficeGoodsType.do"
							}),
					root : new Ext.tree.AsyncTreeNode({
								expanded : true
							}),
					rootVisible : false,
					listeners : {
						"click" : function(l) {
							if (l != null) {
								var j = Ext.getCmp("GoodsSelectorGrid");
								var k = j.getStore();
								k.proxy.conn.url = __ctxPath
										+ "/admin/listOfficeGoods.do";
								k.baseParams = {
									"Q_officeGoodsType.typeId_L_EQ" : l.id == 0
											? null
											: l.id
								};
								k.load({
											params : {
												start : 0,
												limit : 12
											}
										});
							}
						}
					}
				});
		var b = null;
		if (c) {
			var b = new Ext.grid.CheckboxSelectionModel({
						singleSelect : true
					});
		} else {
			b = new Ext.grid.CheckboxSelectionModel();
		}
		var h = new Ext.grid.ColumnModel({
			columns : [b, new Ext.grid.RowNumberer(), {
						header : "typeId",
						dataIndex : "typeId",
						hidden : true
					}, {
						header : "商品名称",
						dataIndex : "goodsName",
						width : 60
					},{
						header : '单价',
						dataIndex : 'price'
					}
//					, {
//						header : "库存数",
//						dataIndex : "stockCounts",
//						width : 60,
//						renderer : function(n, m, j, p, k) {
//							var l = j.data.warnCounts;
//							var o = j.data.isWarning;
//							if (n <= l && o == "1") {
//								return '<a style="color:red;" title="已少于警报库存！">'
//										+ n + "</a>";
//							} else {
//								return n;
//							}
//						}
//					}
					]
		});
		var g = new Ext.data.Store({
					proxy : new Ext.data.HttpProxy({
								url : __ctxPath + "/admin/listOfficeGoods.do"
							}),
					reader : new Ext.data.JsonReader({
								root : "result",
								totalProperty : "totalCounts",
								id : "goodsId",
								fields : [{
											name : "goodsId",
											type : "int"
										}, "goodsName",'brand','specifications','unit', {
											name : "stockCounts",
											type : "int"
										}, {
											name : "warnCounts",
											type : "int"
										}, "isWarning","price"]
							}),
					remoteSort : true
				});
		var d = new Ext.grid.GridPanel({
					id : "GoodsSelectorGrid",
					width : 400,
					height : 300,
					region : "center",
					title : "办公用品列表",
					store : g,
					shim : true,
					trackMouseOver : true,
					disableSelection : false,
					loadMask : true,
					cm : h,
					sm : b,
					viewConfig : {
						forceFit : true,
						enableRowBody : false,
						showPreview : false
					},
					bbar : new Ext.PagingToolbar({
								pageSize : 25,
								store : g,
								displayInfo : true,
								displayMsg : "当前显示从{0}至{1}， 共{2}条记录",
								emptyMsg : "当前没有记录"
							})
				});
		g.load({
					params : {
						start : 0,
						limit : 25
					}
				});
		var a = new Ext.FormPanel({
			width : 400,
			region : "north",
			id : "GoodsForm",
			height : 40,
			frame : false,
			border : false,
			layout : "hbox",
			layoutConfig : {
				padding : "5",
				align : "middle"
			},
			defaults : {
				xtype : "label",
				margins : {
					top : 0,
					right : 4,
					bottom : 4,
					left : 4
				}
			},
			items : [{
						text : "请输入查询条件:"
					}, {
						text : "商品名称"
					}, {
						xtype : "textfield",
						name : "Q_goodsName_S_LK"
					}, {
						xtype : "button",
						text : "查询",
						iconCls : "search",
						handler : function() {
							var j = Ext.getCmp("GoodsForm");
							var k = Ext.getCmp("GoodsSelectorGrid");
							if (j.getForm().isValid()) {
								j.getForm().submit({
									waitMsg : "正在提交查询",
									url : __ctxPath
											+ "/admin/listOfficeGoods.do",
									success : function(m, n) {
										var l = Ext.util.JSON
												.decode(n.response.responseText);
										k.getStore().loadData(l);
									}
								});
							}
						}
					}]
		});
		
		var tab1GroupMgr = new Ext.WindowGroup();
		//前置窗口
		tab1GroupMgr.zseed=99999;
		var e = new Ext.Window({
					title : "办公用品选择",
					iconCls : "menu-goods",
					width : 630,
					height : 380,
					layout : "border",
					manager: tab1GroupMgr,
					border : false,
					items : [f, a, d],
					modal : true,
					buttonAlign : "center",
					buttons : [{
								iconCls : "btn-ok",
								text : "确定",
								handler : function() {
									var l = Ext.getCmp("GoodsSelectorGrid");
									var m = l.getSelectionModel()
											.getSelections();
									var j = "";
									var n = "";
									var brands = "";
									var specifications = "";
									var stockCounts = "";
									var units = "";
									var prices = "";
									for (var k = 0; k < m.length; k++) {
										if (k > 0) {
											j += ",";
											n += ",";
											brands += ",";
											specifications += ",";
											stockCounts += ",";
											units += ",";
											prices += ",";
										}
										j += m[k].data.goodsId;
										n += m[k].data.goodsName;
										brands += m[k].data.brand;
										specifications += m[k].data.specifications;
										stockCounts += m[k].data.stockCounts;
										units += m[k].data.unit;
										prices += m[k].data.price;
										
									}
									if (i != null) {
										i.call(this, j, n,brands,specifications,stockCounts,units,prices);
									}
									e.close();
								}
							}, {
								text : "取消",
								iconCls : "btn-cancel",
								handler : function() {
									e.close();
								}
							}]
				});
		return e;
	}
};