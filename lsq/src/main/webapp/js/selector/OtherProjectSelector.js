var OtherProjectSelector = {
	getView : function(h, f) {
		var g = null;
		if (f) {
			var g = new Ext.grid.CheckboxSelectionModel({
						singleSelect : true
					});
		} else {
			g = new Ext.grid.CheckboxSelectionModel();
		}
		var b = new Ext.grid.ColumnModel({
					columns : [g, new Ext.grid.RowNumberer(), {
								dataIndex : "id",
								hidden : true
							}, {
								header : "项目编号",
								dataIndex : "proNo"
							}, {
								header : "项目名称",
								dataIndex : "proName"
							}]
				});
		var baseParams = {};
		var c = new Ext.data.Store({
					proxy : new Ext.data.HttpProxy({
								url : __ctxPath
										+ "/statistics/listOtherProject.do"
							}),
					baseParams : baseParams,
					reader : new Ext.data.JsonReader({
								root : 'result',
								totalProperty : 'totalCounts',
								remoteSort : true,
								fields : [{
											name : 'id',
											type : 'int'
										}, 'proNo', 'proName', 'teamDep',
										'businessUser', 'remark']
							}),
					remoteSort : true
				});
		var d = new Ext.grid.GridPanel({
					id : "OtherProjectSelectorGrid",
					width : 600,
					height : 300,
					region : "center",
					title : "项目列表",
					store : c,
					shim : true,
					trackMouseOver : true,
					disableSelection : false,
					loadMask : true,
					cm : b,
					sm : g,
					viewConfig : {
						forceFit : true,
						enableRowBody : false,
						showPreview : false
					},
					bbar : new Ext.PagingToolbar({
								pageSize : 25,
								store : c,
								displayInfo : true,
								displayMsg : "当前显示从{0}至{1}， 共{2}条记录",
								emptyMsg : "当前没有记录"
							})
				});
		c.load({
					params : {
						start : 0,
						limit : 25
					}
				});
		var a = new Ext.FormPanel({
					width : 600,
					region : "north",
					id : "OtherProjectSelectorForm",
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
								text : "查询条件:"
							}, {
								text : "项目编号"
							}, {
								xtype : "textfield",
								width : 80,
								name : "Q_proNo_S_LK"
							}, {
								text : "项目名称"
							}, {
								xtype : "textfield",
								width : 80,
								name : "Q_proName_S_LK"
							}, {
								xtype : "button",
								text : "查询",
								iconCls : "search",
								handler : function() {
									var i = Ext.getCmp("OtherProjectSelectorForm");
									var j = Ext.getCmp("OtherProjectSelectorGrid");
									if (i.getForm().isValid()) {
										var params = c.baseParams;
										Ext.apply(params, a.getForm().getValues());
										c.load(params);
									}
								}
							}]
				});
		var e = new Ext.Window({
			title : "其他项目选择器",
			iconCls : "menu-project",
			width : 630,
			height : 380,
			layout : "border",
			border : false,
			items : [a, d],
			modal : true,
			buttonAlign : "center",
			buttons : [{
				iconCls : "btn-ok",
				text : "确定",
				handler : function() {
					var n = Ext.getCmp("OtherProjectSelectorGrid");
					var o = n.getSelectionModel().getSelections();
					var l = "";
					var k = "";
					var j = "";
					for (var m = 0; m < o.length; m++) {
						if (m > 0) {
							l += ",";
							k += ",";
							j += ",";
						}
						l += o[m].data.id;
						k += o[m].data.proName;
						j += o[m].data.proNo;
					}
					if (h != null) {
						h.call(this, l, k, j);
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