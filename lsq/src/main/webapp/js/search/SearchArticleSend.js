Ext.ns("SearchArticleSend");
var SearchArticleSend = function(a) {
	return this.getView(a);
};
SearchArticleSend.prototype.getView = function(a) {
	return new Ext.Panel({
		id : "SearchArticleSend",
		title : "搜索发文",
		iconCls : "menu-notice",
		border : false,
		style : "padding-bottom:10px;",
		autoScroll : true,
		items : [{
					region : "center",
					anchor : "100%",
					items : [new Ext.FormPanel({
								id : "ALLSearchArticleSearchForm",
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
											text : "请输入条件:"
										}, {
											xtype : "textfield",
											name : "searchContent",
											width : 150
										}, {
											xtype : "button",
											text : "查询",
											iconCls : "search",
											handler : function() {
												var b = Ext.getCmp("ALLSearchArticleSearchForm");
												var c = Ext.getCmp("SearchArticleSendGrid");
												if (b.getForm().isValid()) {
													b.getForm().submit({
														waitMsg : "正在提交查询",
														url : __ctxPath + "/info/searchArticleSend.do",
														success : function(e, f) {
															var d = Ext.util.JSON.decode(f.response.responseText);
															c.getStore().loadData(d);
														}
													});
												}
											}
										}, {
											xtype : "button",
											text : "重置",
											iconCls : "reset",
											handler : function() {
												var b = Ext.getCmp("ALLSearchArticleSearchForm");
												b.getForm().reset();
											}
										}]
							}), this.setup(a)]
				}]
	});
};
SearchArticleSend.prototype.setup = function(a) {
	return this.grid(a);
};
SearchArticleSend.prototype.grid = function(d) {
	var a = new Ext.grid.ColumnModel({
		columns : [new Ext.grid.RowNumberer(), {
					header : "noticeId",
					dataIndex : "noticeId",
					hidden : true
				}, {
					header : "发布者",
					dataIndex : "postName"
				}, {
					header : "公告标题",
					dataIndex : "noticeTitle"
				}, {
					header : "生效日期",
					dataIndex : "effectiveDate",
					renderer : function(e) {
						return e.substring(0, 10);
					}
				}, {
					header : "失效日期",
					dataIndex : "expirationDate",
					renderer : function(e) {
						return e.substring(0, 10);
					}
				}, {
					header : "状态",
					dataIndex : "state",
					renderer : function(e) {
						return e == "1" ? "正式发布" : "草稿";
					}
				}],
		defaults : {
			sortable : true,
			menuDisabled : false,
			width : 100
		}
	});
	var b = this.store();
	b.load({
		params : {
			start : 0,
			limit : 7,
			searchContent : d
		}
	});
	var c = new Ext.grid.GridPanel({
		id : "SearchArticleSendGrid",
		store : b,
		trackMouseOver : true,
		disableSelection : false,
		autoScroll : true,
		loadMask : true,
		autoHeight : true,
		sortable : false,
		cm : a,
		viewConfig : {
			forceFit : true,
			enableRowBody : false,
			showPreview : false
		},
		bbar : new Ext.PagingToolbar({
			pageSize : 7,
			store : b,
			displayInfo : true,
			displayMsg : "当前显示从{0}至{1}， 共{2}条记录",
			emptyMsg : "当前没有记录"
		})
	});
	c.addListener("rowdblclick", function(g, f, h) {
		g.getSelectionModel().each(function(e) {
			App.clickTopTab("ArticleSendDetail", e.data.noticeId, function() {
				AppUtil.removeTab("ArticleSendDetail");
			});
		});
	});
	return c;
};
SearchArticleSend.prototype.store = function() {
	var a = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : __ctxPath + "/info/searchArticleSend.do"
		}),
		reader : new Ext.data.JsonReader({
			root : "result",
			totalProperty : "totalCounts",
			id : "id",
			fields : [{
						name : "noticeId",
						type : "int"
					}, "postName", "noticeTitle", "effectiveDate", "expirationDate", "state"]
		}),
		remoteSort : true
	});
	a.setDefaultSort("noticeId", "desc");
	return a;
};