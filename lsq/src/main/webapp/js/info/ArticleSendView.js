Ext.ns("ArticleSendView");
ArticleSendView = Ext.extend(Ext.Panel, {
	searchPanel : null,
	gridPanel : null,
	store : null,
	topbar : null,
	treePanel:null,
	mainPanel:null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		ArticleSendView.superclass.constructor.call(this, {
			id : "ArticleSendView",
			title : "发文列表",
			iconCls : "menu-notice",
			region : "center",
			layout : "border",
			items : [this.treePanel, this.mainPanel]
		});
	},
	initUIComponents : function() {
		this.searchPanel = new Ext.FormPanel({
			id : "ArticleSendSearchForm",
			height : 40,
			frame : false,
			region : "north",
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
						text : "发布者"
					}, {
						xtype : "textfield",
						width : 80,
						name : "Q_postName_S_LK"
					}, {
						text : "标题"
					}, {
						xtype : "textfield",
						width : 80,
						name : "Q_noticeTitle_S_LK"
					}, {
						xtype : "button",
						text : "查询",
						iconCls : "search",
						handler : function() {
							var c = Ext.getCmp("ArticleSendSearchForm");
							var d = Ext.getCmp("ArticleSendGrid");
							if (c.getForm().isValid()) {
								c.getForm().submit({
									waitMsg : "正在提交查询",
									url : __ctxPath + "/info/listArticleSend.do",
									success : function(f, g) {
										var e = Ext.util.JSON.decode(g.response.responseText);
										d.getStore().loadData(e);
									}
								});
							}
						}
					}, {
						xtype : "button",
						text : "重置",
						iconCls : "reset",
						handler : function() {
							var c = Ext.getCmp("ArticleSendSearchForm");
							c.getForm().reset();
						}
					}]
		});
		this.store = new Ext.data.Store({
			proxy : new Ext.data.HttpProxy({
				url : __ctxPath + "/info/listArticleSend.do"
			}),
			reader : new Ext.data.JsonReader({
				root : "result",
				totalProperty : "totalCounts",
				id : "id",
				fields : [{
							name : "noticeId",
							type : "int"
						}, "postName", "noticeTitle", "effectiveDate", "expirationDate", "state","attachFiles"]
			}),
			remoteSort : true
		});
		this.store.setDefaultSort("noticeId", "desc");
		this.store.load({
			param : {
				start : 0,
				limit : 25
			}
		});
		var b = new Ext.grid.CheckboxSelectionModel();
		var a = new Ext.grid.ColumnModel({
			columns : [b, new Ext.grid.RowNumberer(), {
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
						renderer : function(c) {
							return c.substring(0, 10);
						}
					}, {
						header : "失效日期",
						dataIndex : "expirationDate",
						renderer : function(c) {
							return c.substring(0, 10);
						}
					}, {
						header : "状态",
						dataIndex : "state",
						renderer : function(c) {
							return c == "1" ? "正式发布" : "草稿";
						}
					}, {
						header : "附件",
						dataIndex : "attachFiles",
						renderer : function(value, meta, record) {
							var str= "";
							if(!Ext.isEmpty(value)){
								for (var i = 0; i < value.length; i++) {
									str += '<a href="#" onclick="FileAttachDetail.show(' + value[i].fileId + ');" class="attachment">' + value[i].fileName + "</a>";
									str += "&nbsp;";
								}
							}else{
								str = "无附件";
							}
							return str;
						}
					}, {
						header : "管理",
						dataIndex : "noticeId",
						sortable : false,
						width : 50,
						renderer : function(f, e, c, i, d) {
							var h = c.data.noticeId;
							var g = "";
							if (isGranted("_ArticleSendDel")) {
								g = '<button title="删除" value=" " class="btn-del" onclick="ArticleSendView.remove(' + h + ')"></button>';
							}
							if (isGranted("_ArticleSendEdit")) {
								g += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="ArticleSendView.edit(' + h + ')"></button>';
							}
							return g;
						}
					}],
			defaults : {
				sortable : true,
				menuDisabled : false,
				width : 100
			}
		});
		this.topbar = new Ext.Toolbar({
			height : 30,
			bodyStyle : "text-align:left",
			items : []
		});
		if (isGranted("_ArticleSendAdd")) {
			this.topbar.add(new Ext.Button({
				iconCls : "btn-add",
				text : "添加发文",
				handler : function() {
					new ArticleSendForm().show();
				}
			}));
		}
		if (isGranted("_ArticleSendDel")) {
			this.topbar.add(new Ext.Button({
				iconCls : "btn-del",
				text : "删除发文",
				handler : function() {
					var e = Ext.getCmp("ArticleSendGrid");
					var c = e.getSelectionModel().getSelections();
					if (c.length == 0) {
						Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
						return;
					}
					var f = Array();
					for (var d = 0; d < c.length; d++) {
						f.push(c[d].data.noticeId);
					}
					ArticleSendView.remove(f);
				}
			}));
		}
		this.gridPanel = new Ext.grid.GridPanel({
			id : "ArticleSendGrid",
			tbar : this.topbar,
			region : "center",
			store : this.store,
			trackMouseOver : true,
			disableSelection : false,
			loadMask : true,
			autoHeight : true,
			cm : a,
			sm : b,
			viewConfig : {
				forceFit : true,
				enableRowBody : false,
				showPreview : false
			},
			bbar : new Ext.PagingToolbar({
				pageSize : 25,
				store : this.store,
				displayInfo : true,
				displayMsg : "当前显示从{0}至{1}， 共{2}条记录",
				emptyMsg : "当前没有记录"
			})
		});
		this.gridPanel.addListener("rowdblclick", function(d, c, f) {
			d.getSelectionModel().each(function(e) {
				if (isGranted("_ArticleSendEdit")) {
					ArticleSendView.edit(e.data.noticeId);
				}
			});
		});
		
		var treeTypePanel = new TreeTypePanel('发文分类','ArticleSend',this.store);
		this.treePanel = treeTypePanel.getTree();
		this.mainPanel = new Ext.Panel({
		            region:'center',
		            title:'发文列表',
		            layout:'border',
		            items: [this.searchPanel,this.gridPanel]
        	});
	}
});
ArticleSendView.remove = function(b) {
	var a = Ext.getCmp("ArticleSendGrid");
	Ext.Msg.confirm("信息确认", "您确认要删除该记录吗？", function(c) {
		if (c == "yes") {
			Ext.Ajax.request({
				url : __ctxPath + "/info/multiDelArticleSend.do",
				params : {
					ids : b
				},
				method : "post",
				success : function() {
					Ext.ux.Toast.msg("操作信息", "成功删除所选记录！");
					a.getStore().reload({
						params : {
							start : 0,
							limit : 25
						}
					});
				}
			});
		}
	});
};
ArticleSendView.edit = function(a) {
	new ArticleSendForm({
		noticeId : a
	}).show();
};