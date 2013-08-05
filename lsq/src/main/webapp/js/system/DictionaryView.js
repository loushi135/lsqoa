DictionaryView = Ext.extend(Ext.Panel, {
	searchPanel : null,
	gridPanel : null,
	store : null,
	topbar : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		DictionaryView.superclass.constructor.call(this, {
					id : "DictionaryView",
					iconCls : "menu-dictionary",
					title : "字典列表",
					region : "center",
					layout : "border",
					items : [this.searchPanel, this.gridPanel]
				});
	},
	initUIComponents : function() {
		this.searchPanel = new Ext.FormPanel({
			height : 35,
			region : "north",
			frame : false,
			border : false,
			layout : "hbox",
			layoutConfig : {
				padding : "5",
				align : "middle"
			},
			id : "DictionarySearchForm",
			defaults : {
				xtype : "label",
				style : "padding:0px 5px 0px 5px;"
			},
			items : [{
						text : "请输入查询条件:"
					}, {
						text : "条目"
					}, {
						id : "DicSearchFormItemName",
						width : 120,
						name : "Q_itemName_S_LK",
						maxHeight : 200,
						xtype : "combo",
						mode : "local",
						editable : true,
						triggerAction : "all",
						store : [],
						listeners : {
							focus : function(e) {
								var d = Ext.getCmp("DicSearchFormItemName")
										.getStore();
								if (d.getCount() <= 0) {
									Ext.Ajax.request({
												url : __ctxPath
														+ "/system/itemsDictionary.do",
												method : "post",
												success : function(g) {
													var f = Ext.util.JSON
															.decode(g.responseText);
													d.loadData(f);
												}
											});
								}
							},
							select : function(e, f, g) {
								var d = Ext.getCmp("DicSearchFormItemValue")
										.getStore();
								Ext.Ajax.request({
											url : __ctxPath
													+ "/system/loadDictionary.do",
											method : "post",
											params : {
												itemName : e.value
											},
											success : function(i) {
												var h = Ext.util.JSON
														.decode(i.responseText);
												d.loadData(h);
											}
										});
							}
						}
					}, {
						text : "数值"
					}, {
						id : "DicSearchFormItemValue",
						width : 120,
						name : "Q_itemValue_S_LK",
						maxHeight : 200,
						xtype : "combo",
						mode : "local",
						editable : true,
						triggerAction : "all",
						store : []
					}, {
						xtype : "button",
						text : "查询",
						iconCls : "search",
						handler : this.search.createCallback(this)
					}]
		});
		this.store = new Ext.data.Store({
					proxy : new Ext.data.HttpProxy({
								url : __ctxPath + "/system/listDictionary.do"
							}),
					reader : new Ext.data.JsonReader({
								root : "result",
								totalProperty : "totalCounts",
								id : "id",
								fields : [{
											name : "dicId",
											type : "int"
										}, "itemName", "itemValue",'glodMantisId', "descp"]
							}),
					remoteSort : true
				});
		this.store.setDefaultSort("dicId", "desc");
		this.store.load({
					params : {
						start : 0,
						limit : 25
					}
				});
		var b = [];
		if (isGranted("_DictionaryDel")) {
			b.push({
						iconCls : "btn-del",
						qtip : "删除",
						style : "margin:0 3px 0 3px"
					});
		}
		if (isGranted("_DictionaryEdit")) {
			b.push({
						iconCls : "btn-edit",
						qtip : "编辑",
						style : "margin:0 3px 0 3px"
					});
		}
		this.rowActions = new Ext.ux.grid.RowActions({
					header : "管理",
					width : 80,
					actions : b
				});
		var c = new Ext.grid.CheckboxSelectionModel();
		var a = new Ext.grid.ColumnModel({
					columns : [c, new Ext.grid.RowNumberer(), {
								header : "dicId",
								dataIndex : "dicId",
								hidden : true
							}, {
								header : "条目",
								dataIndex : "itemName"
							}, {
								header : "数值",
								dataIndex : "itemValue"
							}, {
								header : "金螳螂主键",
								dataIndex : "glodMantisId"
							}, {
								header : "备注",
								dataIndex : "descp",
								renderer : function(d) {
									if (d == null || d == ""
											|| d == "undefined") {
										return "无";
									} else {
										return d;
									}
								}
							}, this.rowActions],
					defaults : {
						sortable : true,
						menuDisabled : false,
						width : 100
					}
				});
		this.topbar = new Ext.Toolbar({
					id : "DictionaryFootBar",
					height : 30,
					bodyStyle : "text-align:left",
					items : []
				});
		if (isGranted("_DictionaryAdd")) {
			this.topbar.add(new Ext.Button({
						iconCls : "btn-add",
						text : "添加字典",
						handler : this.createRecord,
						scope : this
					}));
		}
		if (isGranted("_DictionaryDel")) {
			this.topbar.add(new Ext.Button({
						iconCls : "btn-del",
						text : "删除字典",
						handler : this.delRecords,
						scope : this
					}));
		}
		this.gridPanel = new Ext.grid.GridPanel({
					id : "DictionaryGrid",
					region : "center",
					tbar : this.topbar,
					store : this.store,
					trackMouseOver : true,
					disableSelection : false,
					loadMask : true,
					autoHeight : true,
					cm : a,
					sm : c,
					plugins : this.rowActions,
					viewConfig : {
						forceFit : true,
						autoFill : true,
						forceFit : true
					},
					bbar : new Ext.PagingToolbar({
								pageSize : 25,
								store : this.store,
								displayInfo : true,
								displayMsg : "当前显示{0}至{1}， 共{2}条记录",
								emptyMsg : "当前没有记录"
							})
				});
		this.gridPanel.addListener("rowdblclick", function(f, d, g) {
					f.getSelectionModel().each(function(e) {
								if (isGranted("_DictionaryEdit")) {
									new DictionaryForm({
											dicId : e.data.dicId
										}).show();
								}
							});
				});
		this.rowActions.on("action", this.onRowAction, this);
	},
	search : function(a) {
		if (a.searchPanel.getForm().isValid()) {
			a.searchPanel.getForm().submit({
						waitMsg : "正在提交查询",
						url : __ctxPath + "/system/listDictionary.do",
						success : function(c, d) {
							var b = Ext.util.JSON
									.decode(d.response.responseText);
							a.gridPanel.getStore().loadData(b);
						}
					});
		}
	},
	createRecord : function() {
		new DictionaryForm().show();
	},
	delByIds : function(a) {
		Ext.Msg.confirm("信息确认", "您确认要删除所选记录吗？", function(b) {
					if (b == "yes") {
						Ext.Ajax.request({
									url : __ctxPath
											+ "/system/multiDelDictionary.do",
									params : {
										ids : a
									},
									method : "POST",
									success : function(c, d) {
										Ext.ux.Toast.msg("信息提示", "成功删除所选记录！");
										Ext.getCmp("DictionaryGrid").getStore()
												.reload();
									},
									failure : function(c, d) {
										Ext.ux.Toast
												.msg("操作信息", "操作出错，请联系管理员！");
									}
								});
					}
				});
	},
	delRecords : function() {
		var c = Ext.getCmp("DictionaryGrid");
		var a = c.getSelectionModel().getSelections();
		if (a.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var d = Array();
		for (var b = 0; b < a.length; b++) {
			d.push(a[b].data.dicId);
		}
		this.delByIds(d);
	},
	editRecord : function(a) {
		new DictionaryForm({
					dicId : a.data.dicId
				}).show();
	},
	onRowAction : function(c, a, d, e, b) {
		switch (d) {
			case "btn-del" :
				this.delByIds(a.data.dicId);
				break;
			case "btn-edit" :
				this.editRecord(a);
				break;
			default :
				break;
		}
	}
});