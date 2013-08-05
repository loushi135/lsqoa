/**
 * @author:
 * @class SmsGroupView
 * @extends Ext.Panel
 * @description [SmsGroup]管理
 * @company
 * @createtime:2010-07-19
 */
SmsGroupView = Ext.extend(Ext.Panel, {
	//条件搜索Panel
	searchPanel : null,
	treePanel : null,
	mainPanel : null,
	//数据展示Panel
	gridPanel : null,
	curNodeId : null,
	selectedNode : null,
	//GridPanel的数据Store
	store : null,
	//头部工具栏
	topbar : null,
	//构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		//初始化组件
		this.initUIComponents();
		//调用父类构造
		SmsGroupView.superclass.constructor.call(this, {
			id : 'SmsGroupView',
			title : '[短信组]管理',
			region : 'center',
			layout : 'border',
			items : [this.treePanel, this.mainPanel]
		});
	},//end of constructor

	//初始化组件
	initUIComponents : function() {
		//初始化treePanel
		var thiz = this;
		this.treePanel = new Ext.tree.TreePanel({
			region : 'west',
			id : 'smsGroupPanel',
			title : '短信组显示',
			collapsible : true,
			useArrows : true,
			animate : true,
			containerScroll : true,
			split : true,
			height : 800,
			width : 200,
			tbar : new Ext.Toolbar({
				items : [{
							xtype : 'button',
							iconCls : 'btn-refresh',
							text : '刷新',
							handler : function() {
								thiz.treePanel.root.reload();
							}
						}, {
							xtype : 'button',
							text : '展开',
							iconCls : 'btn-expand',
							handler : function() {
								thiz.treePanel.expandAll();
							}
						}, {
							xtype : 'button',
							text : '收起',
							iconCls : 'btn-collapse',
							handler : function() {
								thiz.treePanel.collapseAll();
							}
						}]
			}),
			loader : new Ext.tree.TreeLoader({
				url : __ctxPath + '/system/treeSmsGroup.do'
			}),
			root : {},
			rootVisible : false,
			listeners : {
				'click' : function(node) {
					var nodeId = node.attributes.groupId;
					var smsGroupForm = Ext.getCmp('SmsGroupForm');
					if (typeof(smsGroupForm) != 'undefined' && smsGroupForm != null) {
						if (thiz.curNodeId != nodeId && nodeId != '-1') {
							smsGroupForm.destroy();
							new SmsGroupForm({
								id : nodeId
							}).show();
						}
					}

					if (thiz.curNodeId != nodeId) {
						if (nodeId != '-1') {
							thiz.store.baseParams={id:nodeId};
							thiz.store.reload({
								params : {
									start : 0,
									limit : 25
								}
							});
						}
					}
					thiz.curNodeId = nodeId;
					thiz.selectedNode = node;
				}
			}
		});
		// 树的右键菜单的
		this.treePanel.on('contextmenu', contextmenu, this.treePanel);
		// 创建右键菜单
		var treeMenu = new Ext.menu.Menu({
			id : 'SmsGroupTreeMenu',
			items : []
		});
		treeMenu.add({
			text : '新建短信组',
			iconCls : 'btn-add',
			scope : this,
			hidden:!isGranted("_SmsGroupAdd"),
			handler : this.createNode
		});
		treeMenu.add({
			text : '修改短信组',
			iconCls : 'btn-edit',
			id : 'updateFolderBtn',
			scope : this,
			hidden:!isGranted("_SmsGroupEdit"),
			handler : this.editNode
		});
		treeMenu.add({
			text : '删除短信组',
			iconCls : 'btn-delete',
			id : 'deleteFolderBtn',
			scope : this,
			hidden:!isGranted("_SmsGroupDel"),
			handler : this.deteleNode
		});

		function contextmenu(node, e) {
			thiz.selectedNode = node;
			treeMenu.showAt(e.getXY());
		}

		//加载数据至store
		this.store = new Ext.data.JsonStore({
			url : __ctxPath + "/system/getUserByGroupIdSmsGroup.do",
			root : 'result',
			totalProperty : 'totalCounts',
			baseParams:{id:-1},
			remoteSort : true,
			fields : ['userId', 'fullname']
		});
		this.store.setDefaultSort('id', 'desc');

		this.rowActions = new Ext.ux.grid.RowActions({
			header : '管理',
			width : 80,
			actions : [{
						iconCls : 'btn-del',
						qtip : '删除',
						style : 'margin:0 3px 0 3px',
						hide : !isGranted("_SmsGroupUserDel")
					}]
		});

		//初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
			columns : [sm, new Ext.grid.RowNumberer(), {
						header : 'userId',
						dataIndex : 'userId',
						hidden : true
					}, {
						header : '姓名',
						dataIndex : 'fullname'
					}, this.rowActions],
			defaults : {
				sortable : true,
				menuDisabled : false,
				width : 100
			}
		});
		//初始化工具栏
		this.topbar = new Ext.Toolbar({
			height : 30,
			bodyStyle : 'text-align:left',
			items : [{
						iconCls : 'btn-add',
						text : '添加[组成员]',
						xtype : 'button',
						handler : this.createRecord,
						scope : this,
						hidden : !isGranted("_SmsGroupUserAdd")
					}, {
						iconCls : 'btn-del',
						text : '删除[组成员]',
						xtype : 'button',
						handler : this.delRecords,
						scope : this,
						hidden : !isGranted("_SmsGroupUserDel")
					}]
		});

		this.gridPanel = new Ext.grid.GridPanel({
			id : 'SmsGroupGrid',
			region : 'center',
			stripeRows : true,
			tbar : this.topbar,
			store : this.store,
			trackMouseOver : true,
			disableSelection : false,
			loadMask : true,
			autoHeight : true,
			cm : cm,
			sm : sm,
			plugins : this.rowActions,
			viewConfig : {
				forceFit : true,
				autoFill : true, //自动填充
				forceFit : true
				//showPreview : false
			},
			bbar : new Ext.PagingToolbar({
				pageSize : 25,
				store : this.store,
				plugins : [new Ext.ux.PageSizePlugin()],
				displayInfo : true,
				displayMsg : '当前页记录索引{0}-{1}， 共{2}条记录',
				emptyMsg : "当前没有记录"
			})
		});

		this.rowActions.on('action', this.onRowAction, this);

		this.mainPanel = new Ext.Panel({
			frame : true,
			region : 'center',
			collapsible : true,
			layout : 'border',
			items : [this.gridPanel]
		});
	},//end of the initComponents()

	/**
	 * 
	 * @param {}
	 *            self 当前窗体对象
	 */
	search : function(self) {
		if (self.searchPanel.getForm().isValid()) {// 如果合法
			var params = self.gridPanel.getStore().baseParams;
			Ext.apply(params, self.searchPanel.getForm().getValues());
			self.gridPanel.getStore().load(params);
		}
	},

	/**
	 * 添加记录
	 */
	createRecord : function() {
		var groupId ='';
		if(!Ext.isEmpty(this.selectedNode)){
			var groupId = this.selectedNode.attributes.groupId;
		}
		new SmsGroupUserForm({id:groupId}).show();
	},
	/**
	 * 按IDS删除记录
	 * 
	 * @param {}
	 *            ids
	 */
	delByIds : function(ids) {
		if(Ext.isEmpty(this.selectedNode)||this.selectedNode.attributes.groupId==-1){
			Ext.ux.Toast.msg("信息", "请选择短信组！");
			return;
		}
		var groupId = this.selectedNode.attributes.groupId;
		Ext.Msg.confirm('信息确认', '您确认要删除所选记录吗？', function(btn) {
			if (btn == 'yes') {
				Ext.Ajax.request({
					url : __ctxPath + '/system/deleteGroupUserSmsGroup.do',
					params : {
						userIds : ids,
						id:groupId
					},
					method : 'POST',
					success : function(response, options) {
						Ext.ux.Toast.msg('操作信息', '成功删除该[SmsGroup]！');
						Ext.getCmp('SmsGroupGrid').getStore().reload();
					},
					failure : function(response, options) {
						Ext.ux.Toast.msg('操作信息', '操作出错，请联系管理员！');
					}
				});
			}
		});//end of comfirm
	},
	/**
	 * 删除多条记录
	 */
	delRecords : function() {
		var gridPanel = Ext.getCmp('SmsGroupGrid');
		var selectRecords = gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var ids = Array();
		for (var i = 0; i < selectRecords.length; i++) {
			ids.push(selectRecords[i].data.userId);
		}
		this.delByIds(ids);
	},

	/**
	 * 编辑记录
	 * 
	 * @param {}
	 *            record
	 */
	editRecord : function(record) {

	},
	/**
	 * 管理列中的事件处理
	 * 
	 * @param {}
	 *            grid
	 * @param {}
	 *            record
	 * @param {}
	 *            action
	 * @param {}
	 *            row
	 * @param {}
	 *            col
	 */
	onRowAction : function(gridPanel, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.delByIds(record.data.userId);
				break;
			case 'btn-edit' :
				this.editRecord(record);
				break;
			default :
				break;
		}
	},
	createNode : function() {
		var smsGroupForm = Ext.getCmp('SmsGroupForm');
		if (typeof(smsGroupForm) == 'undefined' || smsGroupForm == null) {
			new SmsGroupForm().show();
		}
	},
	editNode : function() {
		var groupId = this.selectedNode.attributes.groupId;
		var smsGroupForm = Ext.getCmp('SmsGroupForm');
		if (typeof(smsGroupForm) == 'undefined' || smsGroupForm == null) {
			new SmsGroupForm({
				id : groupId
			}).show();
		}
	},
	deteleNode : function() {
		var groupId = this.selectedNode.attributes.groupId;
		var smsGroupPanel = Ext.getCmp("smsGroupPanel");
		var ids = Array();
		if(groupId==-1){
			Ext.ux.Toast.msg("信息", "根分组不能删除！");
			return;
		}
		ids.push(groupId);
		if (Ext.MessageBox.confirm("提示", "确定删除此组吗?", function(btn) {
			if (btn == "yes") {
				Ext.Ajax.request({
					url : __ctxPath + '/system/multiDelSmsGroup.do',
					params : {
						ids : ids
					},
					success : function(response, options) {
						var respText = Ext.util.JSON.decode(response.responseText);
						Ext.MessageBox.alert('成功', '删除成功!')
						smsGroupPanel.root.reload();
						smsGroupPanel.fireEvent('click', smsGroupPanel.getRootNode());
					},
					failure : function(response, options) {
						var respText = Ext.util.JSON.decode(response.responseText);
						Ext.Msg.alert('错误', response.error);
					}
				})
			}
		}))
			;
	}
});
