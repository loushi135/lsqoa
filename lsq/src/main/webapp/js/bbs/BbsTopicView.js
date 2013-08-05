/**
 * @author:
 * @class BbsTopicView
 * @extends Ext.Panel
 * @description [BbsTopic]管理
 * @company
 * @createtime:2010-07-19
 */
BbsTopicView = Ext.extend(Ext.Panel, {
	// 条件搜索Panelf
	searchPanel : null,
	// 数据展示Panel
	gridPanel : null,
	// GridPanel的数据Store
	store : null,
	// 头部工具栏
	topbar : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		BbsTopicView.superclass.constructor.call(this, {
					id : 'BbsTopicView',
					title : '论坛管理',
					region : 'center',
					layout : 'border',
					items : [this.treePanel, this.gridPanel]
				});
	},// end of constructor

	// 初始化组件
	initUIComponents : function() {
		var thiz = this;
		this.treePanel = new Ext.tree.TreePanel({
					region : 'west',
					id : 'BbsTreePanel',
					title : '主题分类列表',
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
								url : __ctxPath + '/bbs/treeMenuBbsGroup.do'
							}),
					root : new Ext.tree.AsyncTreeNode({
								expanded : true
							}),
					rootVisible : false,
					listeners : {
						'click' : function(node) {
								Ext.getCmp("ToppicGroupId").setValue(node.id);
								var store = Ext.getCmp("BbsTopicView").gridPanel.getStore();
								if(node.id!=0){
									store.baseParams = {
									'Q_group.id_L_EQ' : node.id
									}
								}else{
									store.baseParams = {}
									
								}
								
								store.reload({
											params : {
												start : 0,
												limit : 25
											}
								});
								if(node.id!=0){
									var parentId = node.parentNode.id;
									var bbsGroupForm = Ext.getCmp('BbsGroupFormWin');
									if (typeof(bbsGroupForm)!='undefined'&&bbsGroupForm != null) {
											bbsGroupForm.destroy();
											new BbsGroupForm({
													id : node.id,
													parentId : parentId
												}).show();
									}
								}
		
						}
					}
				});
				
				
		// 树的右键菜单的
	if(isGranted('_BbsTopicManage)')){
		this.treePanel.on('contextmenu', contextmenu, this.treePanel);
	}
		// 创建右键菜单
		var treeMenu = new Ext.menu.Menu({
					id : 'bbsTreeMenu',
					items : []
				});
		treeMenu.add({
					text : '新建目录',
					iconCls : 'btn-add',
					scope : this,
					handler : this.createNode
				});
		treeMenu.add({
					text : '修改目录',
					iconCls : 'btn-edit',
					id : 'updateBbsBtn',
					scope : this,
					handler : this.editNode
				});
		treeMenu.add({
					text : '删除目录',
					iconCls : 'btn-delete',
					id : 'deleteBbsBtn',
					scope : this,
					handler : this.deteleNode
				});
		function contextmenu(node, e) {
			thiz.selectedNode = node;
			Ext.getCmp("updateBbsBtn").show();
			Ext.getCmp("deleteBbsBtn").show();
			treeMenu.showAt(e.getXY());
		}
		// 加载数据至store
		this.store = new Ext.data.JsonStore({
					url : __ctxPath + "/bbs/listBbsTopic.do",
					root : 'result',
					totalProperty : 'totalCounts',
					remoteSort : true,
					fields : [{
								name : 'id',
								type : 'int'
							}, 'title', 'publishTime', 'lastUpdateTime',
							'viewCount', 'isTop', 'isPrime', 'isLock',
							'isReply', 'user', 'content', 'group']
				});
//		this.store.sortInfo={field:'publishTime', direction: 'DESC'}
		this.store.load({
					params : {
						start : 0,
						limit : 25
					}
				});
		// alert(publishTime)

				 
  if(isGranted('_BbsTopicEdit')&&isGranted('_BbsTopicDel')){
		this.rowActions = new Ext.ux.grid.RowActions({
					header : '管理',
					width : 80,
					actions : [{
								iconCls : 'btn-del',
								qtip : '删除',
								style : 'margin:0 3px 0 3px'
							}, {
								iconCls : 'btn-edit',
								qtip : '编辑',
								style : 'margin:0 3px 0 3px'
							}]
				});
				     }else if(isGranted('_BbsTopicDel')){
							     this.rowActions = new Ext.ux.grid.RowActions({
												header : '管理',
												width : 80,
												actions : [{
															iconCls : 'btn-del',
															qtip : '删除',
															style : 'margin:0 3px 0 3px'
											               }]
											});
				     
				     
				     }else if(isGranted('_BbsTopicEdit')){
				        this.rowActions = new Ext.ux.grid.RowActions({
												header : '管理',
												width : 80,
												actions : [{
															iconCls : 'btn-edit',
															qtip : '编辑',
															style : 'margin:0 3px 0 3px'
														}]
											});
				     
				     
				     }else{
				         this.rowActions = new Ext.ux.grid.RowActions({
												header : '管理',
												width : 80,
												actions : []
											});
				     }
		// 初始化ColumnModel
	    var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
					columns : [sm,new Ext.grid.RowNumberer(), {
								header : 'id',
								dataIndex : 'id',
								hidden : true
							}, {
								header : '标题',
								width:300,
								dataIndex : 'title',
								renderer : this.renderTopicTitle
							}, {
								header : '发布时间',
								dataIndex : 'publishTime'
							},
							{
								header : '发布人',
								renderer : function(v, meta, record) {
									if (record.data.user != '') {
										return record.data.user.fullname;
									}
								}

							},{
								header : '管理',
								renderer : function(v, meta, record) {
									var id = record.data.id;
									var userId = record.data.user.userId;
									var str = '';
									if (userId == __currentUserId ||(__currentUserId==1) ) {
									 str += '&nbsp;<button title="修改" value=" " class="btn-edit" onclick="BbsTopicView.editRecord('
												+ id + ')">&nbsp;</button>';
									 str += '&nbsp;<button title="删除" value=" " class="btn-del" onclick="BbsTopicView.delByIds('
												+ id + ')">&nbsp;</button>';
									}
									return str;
								}
							}
							],
					defaults : {
						sortable : true,
						menuDisabled : false,
						width : 100
					}
				});
		// 初始化工具栏
		this.topbar = new Ext.Toolbar({
					height : 30,
					bodyStyle : 'text-align:left',
					items : [{
						text : '发布新主题',
						iconCls : 'btn-topic',
						// hidden:!isGranted('_BbsTopicAdd'),
						xtype : 'button',
						handler : this.createRecord
					}, {
						xtype : 'hidden',
						id : 'ToppicGroupId'
					}, {
						text : '置顶',
						iconCls : 'btn-bbsTop',
						xtype : 'button',
						hidden : !isGranted('_BbsTopicManage'),
						handler : function() {
							var gridPanel = Ext.getCmp('BbsTopicGrid');
							var selectRecords = gridPanel.getSelectionModel().getSelections();
							if (selectRecords.length == 0) {
								Ext.ux.Toast.msg("信息", "请选择要置顶的主题！");
								return;
							}
							var ids = Array();
							for (var i = 0; i < selectRecords.length; i++) {
								ids.push(selectRecords[i].data.id);
							}
							Ext.Ajax.request({
								url : __ctxPath + "/bbs/topBbsTopic.do",
								success : function() {
									thiz.store.reload();
								},
								failure : function() {
								},
								method : 'POST',
								params : {
									ids : ids
								}
							});
						}
					}, {
						iconCls : 'btn-sign',
						text : '签名管理',
						xtype : 'button',
						handler : function() {
							new BbsUserPropertyForm().show()
						}
					}, {
						xtype : "combo",
						hiddenName : "state",
						emptyText : '请选择主题分类',
						triggerAction : "all",
						editable : false,
						width : 100,
						value : 0,
						store : [["0", "所有主题"], ["1", "精华主题"]],
						listeners:{
						     select : function(combo ,record,index) {
								if (index == 0) {
									thiz.store.load({
										params : {}
									});
								} else if (index == 1) {
									thiz.store.load({
										params : {
											Q_isPrime_N_EQ : 1
										}
									});
								}
						}
					 }
					}
			]
				});

		this.gridPanel = new Ext.grid.GridPanel({
					id : 'BbsTopicGrid',
					region : 'center',
					stripeRows : true,
					tbar : this.topbar,
					store : this.store,
					trackMouseOver : true,
					disableSelection : false,
					loadMask : true,
					height : 800,
					cm : cm,
				    sm : sm,
					plugins : this.rowActions,
					viewConfig : {
						forceFit : true,
						autoFill : true, // 自动填充
						forceFit : true
						// showPreview : false
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

		this.gridPanel.addListener('rowdblclick', function(grid, rowindex, e) {
					grid.getSelectionModel().each(function(rec) {
						var tabs = Ext.getCmp('centerTabPanel');
						var topicId=rec.data.id;
						var title = rec.data.title;
		                var tabItem = new BbsReplyView({topicId:topicId,title:title});
		                tabs.add(tabItem);
		                tabs.setActiveTab(tabItem);
		                if(rec.data.id!=''||rec.data.id!=null){
		                	Ext.Ajax.request({
									  url:__ctxPath + "/bbs/viewCountBbsTopic.do",
									   success:function(){thiz.store.reload()},
									   failure:function(){},
									   method : 'POST',
									   params : {
										id : rec.data.id
									 }
									   });
		                }
		               
					});
				});
		this.rowActions.on('action', this.onRowAction, this);
	},// end of the initComponents()

	/**
	 * 
	 * @param {}
	 *            self 当前窗体对象
	 */
	search : function(self) {
		if (self.searchPanel.getForm().isValid()) {// 如果合法
			self.searchPanel.getForm().submit({
				waitMsg : '正在提交查询',
				url : __ctxPath + '/bbs/listBbsTopic.do',
				success : function(formPanel, action) {
					var result = Ext.util.JSON
							.decode(action.response.responseText);
					self.gridPanel.getStore().loadData(result);
				}
			});
		}
	},
	createNode : function() {
		var nodeId = this.selectedNode.id;
		var bbsGroupForm = Ext.getCmp('BbsGroupFormWin');
		if (typeof(bbsGroupForm) == 'undefined' || bbsGroupForm == null) {
			if (nodeId > 0) {
				new BbsGroupForm({
							parentId : nodeId
						}).show();
			} else {
				new BbsGroupForm({
							parentId : 0
						}).show();
			}
		}
	},
	editNode : function() {
		var nodeId = this.selectedNode.id;
		if(nodeId!=0){
			var parentId = this.selectedNode.parentNode.id;
			var bbsGroupForm = Ext.getCmp('BbsGroupFormWin');
			if (typeof(bbsTreePanel) == 'undefined' || bbsGroupForm == null) {
				new BbsGroupForm({
							id : nodeId,
							parentId : parentId
						}).show();
			}
		}
	},
	deteleNode : function() {
		var nodeId = this.selectedNode.id;
		var BbsTreePanel = Ext.getCmp("BbsTreePanel");
		if (Ext.MessageBox.confirm("提示", "删除此文件夹,会删除子文件夹，确定删除此文件夹吗?",
				function(btn) {
					if (btn == "yes") {
						Ext.Ajax.request({
									url : __ctxPath
											+ '/bbs/singleDelBbsGroup.do?id='
											+ nodeId,
									success : function(response, options) {
										var respText = Ext.util.JSON
												.decode(response.responseText);
										Ext.MessageBox.alert('成功', '删除成功!')
										BbsTreePanel.root.reload();
										BbsTreePanel.fireEvent('click',
												BbsTreePanel.getRootNode());
									},
									failure : function(response, options) {
										var respText = Ext.util.JSON
												.decode(response.responseText);
										Ext.Msg.alert('错误', response.error);
									}
								})
					}
				}))
			;

	},
	/**
	 * 添加记录
	 */
	createRecord : function() {
		var ToppicGroupId =Ext.getCmp("ToppicGroupId").getValue();
		new BbsTopicForm({ToppicGroupId:ToppicGroupId}).show();
	},
	/**
	 * 按IDS删除记录
	 * 
	 * @param {}
	 *            ids
	 */
	delByIds : function(ids) {
		Ext.Msg.confirm('信息确认', '您确认要删除所选记录吗？', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
									url : __ctxPath
											+ '/bbs/multiDelBbsTopic.do',
									params : {
										ids : ids
									},
									method : 'POST',
									success : function(response, options) {
										Ext.ux.Toast.msg('操作信息',
												'成功删除该主题！');
										Ext.getCmp('BbsTopicGrid').getStore()
												.reload();
									},
									failure : function(response, options) {
										Ext.ux.Toast
												.msg('操作信息', '操作出错，请联系管理员！');
									}
								});
					}
				});// end of comfirm
	},
	/**
	 * 删除多条记录
	 */
	delRecords : function() {
		var gridPanel = Ext.getCmp('BbsTopicGrid');
		var selectRecords = gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var ids = Array();
		for (var i = 0; i < selectRecords.length; i++) {
			ids.push(selectRecords[i].data.id);
		}
		this.delByIds(ids);
	},
	renderTopicTitle : function(value,p,record) {
		var temp = String
				.format(
						'<div class="rowBox"><p>{0}</p><span>回复:{2}</span><span>浏览:{3}</span><span>最后更新:{4}</span></div>',
						value, record.data.title,
						record.data.isReply, record.data.viewCount,
						record.data.lastUpdateTime);
		if (record.data.isTop == 1) {
			temp = "[置顶]" + temp;
		}
		if (record.data.isPrime == 1) {
			temp = "[精华]" + temp;
		}
		if (record.data.isLock == 1) {
			temp = "[锁定]" + temp;
		}
      	return temp;
	},
	editRecord : function(record) {
		new BbsTopicForm({
					id : record.data.id
				}).show();
	},

	onRowAction : function(gridPanel, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.delByIds(record.data.id);
				break;
			case 'btn-edit' :
				this.editRecord(record);
				break;
			default :
				break;
		}
	}

});

BbsTopicView.editRecord = function(id){
    new BbsTopicForm({
			id : id
		}).show();
}

BbsTopicView.delByIds=function(ids){
		Ext.Msg.confirm('信息确认', '您确认要删除所选记录吗？', function(btn) {
			if (btn == 'yes') {
				Ext.Ajax.request({
					url : __ctxPath + '/bbs/multiDelBbsTopic.do',
					params : {
						ids : ids
					},
					method : 'POST',
					success : function(response, options) {
						Ext.ux.Toast.msg('操作信息', '成功删除该主题！');
						Ext.getCmp('BbsTopicGrid').getStore().reload();
					},
					failure : function(response, options) {
						Ext.ux.Toast.msg('操作信息', '操作出错，请联系管理员！');
					}
				});
			}
		});// end of comfirm
}
