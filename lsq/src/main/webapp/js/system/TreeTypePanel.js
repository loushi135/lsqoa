Ext.ns("TreeTypePanel")

TreeTypePanel = function(title,className,store) {
	this.title = title;
	this.className = className;
	this.store = store;
};
TreeTypePanel.prototype.getTree = function() {
	var thiz = this;
	var treeLoader = new Ext.tree.TreeLoader({
		url : __ctxPath + '/system/getTreeOfTreeType.do?className='+this.className
	});
	var treePanel = new Ext.tree.TreePanel({
		region : 'west',
		title : this.title,
		collapsible : true,
		autoScroll : true,
		useArrows : true,
		animate : true,
		//			    enableDD: true,
		//			    dropConfig: {appendOnly:true},//appendOnlY 只向后加
		containerScroll : true,
		border : false,
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
		loader : treeLoader,
		root : {},
		rootVisible : false,
		listeners : {
			'click' : function(node) {
				var nodeId = node.attributes.typeId;
				var treeTypeForm = Ext.getCmp('TreeTypeFormWin');
				if (!Ext.isEmpty(treeTypeForm)) {
					if (thiz.curNodeId != nodeId && nodeId != '-1') {
						treeTypeForm.destroy();
						new TreeTypeForm({className : thiz.className,title:thiz.title,id:nodeId,treePanel:thiz.treePanel}).show();
					}
				}

				if (thiz.curNodeId != nodeId) {
					if (nodeId != '-1') {
						thiz.store.baseParams = {
							'Q_treeType.id_L_EQ' : nodeId
						};
					}else{
						thiz.store.baseParams = {
						};
					}
					thiz.store.reload({
							params : {
								start : 0,
								limit : 25
							}
					});
				}
				thiz.curNodeId = nodeId;
				thiz.selectedNode = node;
			}
		}
	});
	// 树的右键菜单的
	treePanel.on('contextmenu', contextmenu, treePanel);
	var treeMenu = new Ext.menu.Menu({
		items : []
	});
	function contextmenu(node, e) {
		thiz.selectedNode = node;
		// 创建右键菜单
		treeMenu.removeAll();
		treeMenu.add({
			text : '新建',
			iconCls : 'btn-add',
			scope : thiz,
			hidden:!isGranted("_TreeTypeAdd"),
			handler : thiz.createTreeType
		});
		if(node.attributes.typeId!='-1'){
			treeMenu.add({
				text : '修改',
				iconCls : 'btn-edit',
				scope : thiz,
				hidden:!isGranted("_TreeTypeEdit"),
				handler : thiz.editTreeType
			});
			treeMenu.add({
				text : '删除',
				iconCls : 'btn-delete',
				scope : thiz,
				hidden:!isGranted("_TreeTypeDel"),
				handler : thiz.deleteTreeType
			});
		}
		treeMenu.showAt(e.getXY());
	}
	this.treePanel = treePanel;
	return this.treePanel;
}
TreeTypePanel.prototype.selectedNode = null;//当前选中节点

TreeTypePanel.prototype.curNodeId = null; //上次点击的节点 id，可能为provinceId或cityId

TreeTypePanel.prototype.treePanel = null;

TreeTypePanel.prototype.type = null;

TreeTypePanel.prototype.createTreeType = function() {
	var treeTypeForm = Ext.getCmp('TreeTypeFormWin');
	if (Ext.isEmpty(treeTypeForm)) {
		new TreeTypeForm({className : this.className,title:this.title,parentId:this.selectedNode.attributes.typeId,treePanel:this.treePanel}).show();
	}
}

TreeTypePanel.prototype.editTreeType = function() {
	var typeId = this.selectedNode.attributes.typeId;
	var treeTypeForm = Ext.getCmp('TreeTypeFormWin');
	if (Ext.isEmpty(treeTypeForm)&&typeId!='-1') {
		new TreeTypeForm({className : this.className,title:this.title,id:typeId,treePanel:this.treePanel}).show();
	}
}

TreeTypePanel.prototype.deleteTreeType = function() {
	var thiz = this;
	var typeId = this.selectedNode.attributes.typeId;
	var ids = new Array();
	ids.push(typeId);
	Ext.Msg.confirm('信息确认', '您确认要删除此'+thiz.title+'吗？', function(btn) {
		if (btn == 'yes') {
			Ext.Ajax.request({
				url : __ctxPath + '/system/multiDelTreeType.do',
				params : {
					ids : ids
				},
				method : 'POST',
				success : function(response, options) {
					Ext.ux.Toast.msg('操作信息', '成功删除该'+thiz.title+'！');
					thiz.treePanel.root.reload();
				},
				failure : function(response, options) {
					Ext.ux.Toast.msg('操作信息', '操作出错，请联系管理员！');
				}
			});
		}
	});//end of comfirm
}
