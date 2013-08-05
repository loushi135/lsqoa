RosterView = Ext.extend(Ext.Panel, {
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		RosterView.superclass.constructor.call(this, {
			id : "RosterView",
			title : "在职花名册",
			iconCls : "menu-appuser",
			autoScroll : true
		});
	},
	initUIComponents : function() {
		this.initSearchPanel();
		this.initGridPanel();
		this.items = [this.searchPanel, this.gridPanel];
	},
	onSearch : function(c) {
		var a = Ext.getCmp("RosterSearchForm");
		var b = Ext.getCmp("RosterGrid");
		if (a.getForm().isValid()) {
			a.getForm().submit({
				waitMsg : "正在提交查询",
				url : __ctxPath + "/system/listRosterAppUser.do",
				success : function(e, f) {
					var d = Ext.util.JSON.decode(f.response.responseText);
					b.getStore().loadData(d);
				}
			});
		}
	}
});
RosterView.prototype.initSearchPanel = function() {
	this.searchPanel = new Ext.FormPanel({
		height : 35,
		frame : false,
		border : false,
		id : "RosterSearchForm",
		layout : "hbox",
		layoutConfig : {
			padding : "5",
			align : "middle"
		},
		defaults : {
			xtype : "label",
			border : false,
			margins : {
				top : 0,
				right : 4,
				bottom : 4,
				left : 4
			}
		},
		items : [{
				text : "员工工号"
			}, {
				xtype : "textfield",
				id : "Q_u.staffNo_S_LK",
				name : "Q_u.staffNo_S_LK"
			}, {
				text : "用户姓名"
			}, {
				xtype : "textfield",
				id : "Q_u.fullname_S_LK",
				name : "Q_u.fullname_S_LK"
			}, {
				text : "部门"
			}, {
				xtype : "textfield",
				id : "Q_u.department.depName_S_LK",
				name : "Q_u.department.depName_S_LK"
			}, {
				text : "职位"
			}, {
				xtype : "textfield",
				id : "Q_u.position_S_LK",
				name : "Q_u.position_S_LK"
			}, {
				xtype : "button",
				text : "查询",
				iconCls : "search",
				scope : this,
				handler : this.onSearch.createCallback(this)
			}]
	});
};
RosterView.prototype.initGridPanel = function() {
	this.toolbar = new Ext.Toolbar({
		height : 30,
		items : [{
				iconCls : 'btn-xls',
				text : '导出[在职花名册]',
				hidden : !isGranted("_RosterExport"),
				xtype : 'button',
				handler : function() {

					var url = __ctxPath + '/system/exportRosterAppUser.do?format=xls&jasperName=Roster&limit=99999999' +
							'&staffNo=' + Ext.getCmp('Q_u.staffNo_S_LK').getValue() 
							+ '&fullname=' + Ext.getCmp('Q_u.fullname_S_LK').getValue()
							+ '&depName=' + Ext.getCmp('Q_u.department.depName_S_LK').getValue()
							+ '&position=' + Ext.getCmp('Q_u.position_S_LK').getValue()
					location = encodeURI(encodeURI(url));
				},
				scope : this
			},{
 				text:'打印[在职花名册]',
 				iconCls:'btn-print',
 				handler:function(){
 					Ext.ux.Printer.print(Ext.getCmp("RosterGrid"));
 				}}]
	});
	var b = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : __ctxPath + "/system/listRosterAppUser.do"
		}),
		reader : new Ext.data.JsonReader({
			root : "result",
			totalProperty : "totalCounts",
			fields : ['staffNO', "fullname", "sex", "departmentName", "formation", "position", "accessionTime", "workType", "workContractEndDate", "mobile"]
		}),
		remoteSort : true
	});
	b.setDefaultSort("u.userId", "desc");
	b.load({
		params : {
			start : 0,
			limit : 25
		}
	});
	var a = new Ext.grid.ColumnModel({
		columns : [{
				header : "工号",
				dataIndex : "staffNO",
				width : 60
			}, {
				header : "姓名",
				dataIndex : "fullname",
				sortable : false,
				exprint : true,
				width : 60
			}, {
				header : "性别",
				dataIndex : "sex",
				width : 30
			}, {
				header : "公司",
				width : 150,
				renderer : function(value) {

					return __companyName;
				}
			}, {
				header : "部门",
				dataIndex : "departmentName",
				sortable : false,
				width : 60
			}, {
				header : "编制",
				sortable : false,
				dataIndex : "formation",
				width : 60
			}, {
				header : "职位",
				sortable : false,
				dataIndex : "position",
				width : 60
			}, {
				header : "进司时间",
				dataIndex : "accessionTime",
				width : 100
			}, {
				header : "劳动关系",
				sortable : false,
				dataIndex : "workType",
				width : 100
			}, {
				header : "劳动时期时间",
				dataIndex : "workContractEndDate",
				width : 100
			}, {
				header : "手机",
				dataIndex : "mobile",
				width : 100
			}],
		defaults : {
			sortable : true,
			menuDisabled : true,
			width : 100
		}
	});
	this.gridPanel = new Ext.grid.GridPanel({
		id : "RosterGrid",
		tbar : this.toolbar,
		store : b,
		autoHeight : true,
		shim : true,
		trackMouseOver : true,
		disableSelection : false,
		loadMask : true,
		cm : a,
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
};
