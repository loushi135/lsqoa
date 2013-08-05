Ext.ns('AppUserRoleView');

var AppUserRoleView = function() {
	return this.setup();
};

AppUserRoleView.prototype.setup = function() {
	var selected;
	var store = this.initData();
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel({
		columns : [sm, new Ext.grid.RowNumberer(), {
					header : "userId",
					dataIndex : 'userId',
					hidden : true
				}, {
					header : "用户名",
					dataIndex : 'fullname',
					width:60
				}, {// 先不显示
					header : "所属部门",
					dataIndex : 'department',
					renderer : function(value) {
						if(value==null){
						   return '';
						}else{
						   return value;
						}
					}
				},{
					header : "拥有角色",
					dataIndex : 'haveRole',
					width:400,
					renderer : function(value, metadata, record, rowIndex,
							colIndex) {
						metadata.attr = 'ext:qtitle="拥有角色"' + ' ext:qtip="' + value + '"';    
						return value;
					}
				}, {
					header : '管理',
					dataIndex : 'userId',
					width:60,
					sortable : false,
					renderer : function(value, metadata, record, rowIndex,
							colIndex) {
						var str = '';
						if (isGranted('_AppUserRoleEdit')) {
							str += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="AppUserRoleView.edit('
									+ '\''+record.data.userId+'\',\''+record.data.fullname+'\',\''+record.data.department+'\',\''+record.data.haveRole
									+ '\')"></button>';
						}
						
						return str;
					}
				}],
		defaults : {
			sortable : true,
			menuDisabled : true,
			width : 100
		},
		listeners : {
			hiddenchange : function(cm, colIndex, hidden) {
				saveConfig(colIndex, hidden);
			}
		}
	});

	var grid = new Ext.grid.GridPanel({
				id : 'appUserRoleView',
				autoHeight : true,
				title : '员工基本信息',
				store : store,
				shim : true,
				trackMouseOver : true,
				disableSelection : false,
				loadMask : true,
				cm : cm,
				sm : sm,
				viewConfig : {
					forceFit : false,
					autoFill : true //自动填充
				},
				// paging bar on the bottom
				bbar : new Ext.PagingToolbar({
							pageSize : 25,
							store : store,
							plugins : [new Ext.ux.PageSizePlugin()], 
							displayInfo : true,
							displayMsg : '当前显示从{0}至{1}， 共{2}条记录',
							emptyMsg : "当前没有记录"
						})
			});
	grid.addListener('rowdblclick', rowdblclickFn);
	function rowdblclickFn(grid, rowindex, e) {
		grid.getSelectionModel().each(function(rec) {
					if (isGranted('_AppUserRoleEdit')) {
						AppUserRoleView.edit(rec.data.userId,rec.data.fullname,rec.data.department,rec.data.haveRole);
					}
				});
	}
	store.load({
				params : {
					start : 0,
					limit : 25
				}
			});

	var treePanel = new Ext.tree.TreePanel({
				region : 'west',
				id : 'treePanelappUserRole',
				title : '部门信息显示',
				collapsible : true,
				split : true,
				height : 800,
				width : 200,
				tbar : new Ext.Toolbar({
							items : [{
										xtype : 'button',
										iconCls : 'btn-refresh',
										text : '刷新',
										handler : function() {
											treePanel.root.reload();
										}
									}, {
										xtype : 'button',
										text : '展开',
										iconCls : 'btn-expand',
										handler : function() {
											treePanel.expandAll();
										}
									}, {
										xtype : 'button',
										text : '收起',
										iconCls : 'btn-collapse',
										handler : function() {
											treePanel.collapseAll();
										}
									}]
						}),
				loader : new Ext.tree.TreeLoader({
							url : __ctxPath + '/system/listDepartment.do'
						}),
				root : new Ext.tree.AsyncTreeNode({
							expanded : true
						}),
				rootVisible : false,
				listeners : {
					'click' : AppUserRoleView.clickNode
				}
			});
	var searchPanel = new Ext.FormPanel({
				id : 'appUserRoleViewSearch',
				height : 35,
				frame : false,
				border : false,
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
							text : "用户姓名"
						}, {
							xtype : "textfield",
							name : "fullname"
						}, {
							text : "角色名"
						}, {
							xtype : "textfield",
							name : "rolename"
						}, {
							xtype : "button",
							text : "查询",
							iconCls : "search",
							handler : function(){
								var a = Ext.getCmp("appUserRoleViewSearch");
								var b = Ext.getCmp("appUserRoleView");
								if (a.getForm().isValid()) {
									var params = b.getStore().baseParams; 
						            Ext.apply(params,a.getForm().getValues());
						            b.getStore().load(params);
								}
							}
						}]
			});
	var panel = new Ext.Panel({
				id : 'AppUserRoleView',
				title : '人员角色信息',
				closable : true,
				iconCls : 'menu-department',
				layout : 'border',
				height : 800,
				items : [treePanel, {
							region : "center",
							layout : "anchor",
							autoScroll : true,
							items : [searchPanel,grid]
						}]
			});
	return panel;

};

AppUserRoleView.prototype.initData = function() {
	var store = new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
							url : __ctxPath + '/system/selectUserRoleAppUser.do'
						}),
				// create reader that reads the Topic records
				reader : new Ext.data.JsonReader({
							root : 'result',
							totalProperty : 'totalCounts',
							fields : [{
										name : 'userId',
										type : 'int'
									}, 'username', 'fullname',
									'department','haveRole']
						}),
				remoteSort : true
			});
	store.setDefaultSort('id', 'desc');
	return store;
};

/**
 * 初始化
 * 
 * @return {}
 */
AppUserRoleView.clickNode = function(node) {
	if (node != null) {
		var users = Ext.getCmp('appUserRoleView');
		var store = users.getStore();
		store.url = __ctxPath + '/system/selectUserRoleAppUser.do';
		store.baseParams = {
			depId : node.id
		};
		store.params = {
			start : 0,
			limit : 25
		};
		var a = Ext.getCmp("appUserRoleViewSearch");
		var params = store.baseParams; 
		Ext.apply(params,a.getForm().getValues());
		store.reload({
					params : {
						start : 0,
						limit : 25
					}
				});
	}

};
AppUserRoleView.edit = function(userId,fullname,department,haveRole){
	new AppUserRoleForm({userId:userId,fullname:fullname,department:department,haveRole:haveRole}).show()
}