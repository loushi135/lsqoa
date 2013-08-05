/**
 * @author:
 * @class ProjectSealRecycleView
 * @extends Ext.Panel
 * @description 项目印章回收管理
 * @company 
 * @createtime:2010-07-19
 */
ProjectSealRecycleView = Ext.extend(Ext.Panel, {
	//条件搜索Panel
	searchPanel : null,
	//数据展示Panel
	gridPanel : null,
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
		ProjectSealRecycleView.superclass.constructor.call(this, {
					id : 'ProjectSealRecycleView',
					title : '项目印章回收管理',
					region : 'center',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	},//end of constructor

	//初始化组件
	initUIComponents : function() {
		//初始化搜索条件Panel
		this.searchPanel = new Ext.FormPanel({
					layout : 'column',
					region : 'north',
					bodyStyle : 'padding:6px 10px 6px 10px',
					border : false,
					frame : true,
					height : 50,
					defaults : {
						border : false,
						anchor : '98%,98%'
					},
					items : [{
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '请输入查询条件:'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '项目名称:'
							}, {
								name : 'Q_projectSeal.proName_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '项目印章内容:'
							}, {
								name : 'Q_projectSeal.content_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '项目完工内检时间:'
							}, {
								name : 'Q_innerCheckTime_D_EQ',
								xtype : 'datefield',
								format : 'Y-m-d'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '最晚归还时间:'
							}, {
								name : 'Q_lastReturnTime_D_EQ',
								format : 'Y-m-d',
								xtype : 'datefield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '经办人:'
							}, {
								name : 'Q_applyUser.fullname_S_LK',
								xtype : 'textfield'
							}, {
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								handler : this.search.createCallback(this)
							}]
				});//end of the searchPanel

		//加载数据至store
		this.store = new Ext.data.JsonStore({
					url : __ctxPath + "/statistics/listProjectSealRecycle.do",
					root : 'result',
					totalProperty : 'totalCounts',
					remoteSort : true,
					fields : [{
								name : 'id',
								type : 'int'
							}, 'projectSeal', 'innerCheckTime', 'lastReturnTime',
							'applyUser', 'processRunId']
				});
		this.store.setDefaultSort('id', 'desc');
		//加载数据
		this.store.load({
					params : {
						start : 0,
						limit : 25
					}
				});

		this.rowActions = new Ext.ux.grid.RowActions({
					header : '管理',
					width : 80,
					actions : [{
								iconCls : 'btn-del',
								qtip : '删除',
								style : 'margin:0 3px 0 3px',
								hide : !isGranted("_ProjectSealRecycleDel")
							}, {
								iconCls : 'btn-edit',
								qtip : '编辑',
								style : 'margin:0 3px 0 3px',
								hide : !isGranted("_ProjectSealRecycleEdit")
							},{
								 iconCls:'btn-flowView',
								 qtip:'查看审批表单',
								 style:'margin:0 3px 0 3px'
							}]
				});

		//初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
					columns : [sm, new Ext.grid.RowNumberer(), {
								header : 'id',
								dataIndex : 'id',
								hidden : true
							}, {
								header : '项目名称',
								dataIndex : 'projectSeal',
								renderer : function(value, metadata, record, rowIndex,colIndex) {
												var str='';
												if(!Ext.isEmpty(value)){
													str = value.proName;
												}
												return str;
										}
							}, {
								header : '项目印章',
								dataIndex : 'projectSeal',
								renderer : function(value, metadata, record, rowIndex,colIndex) {
												var str='';
												if(!Ext.isEmpty(value)){
													str = value.content;
												}
												return str;
										}
							}, {
								header : '项目完工内检时间',
								dataIndex : 'innerCheckTime'
							}, {
								header : '最晚归还时间',
								dataIndex : 'lastReturnTime'
							}, {
								header : '经办人',
								dataIndex : 'applyUser',
								renderer : function(value, metadata, record, rowIndex,colIndex) {
												var str='';
												if(!Ext.isEmpty(value)){
													str = value.fullname;
												}
												return str;
										}
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
								text : '添加[项目印章回收]',
								xtype : 'button',
								handler : this.createRecord,
								hidden : !isGranted("_ProjectSealRecycleAdd")
							}, {
								iconCls : 'btn-del',
								text : '删除[项目印章回收]',
								xtype : 'button',
								handler : this.delRecords,
								scope : this,
								hidden : !isGranted("_ProjectSealRecycleDel")
							}]
				});

		this.gridPanel = new Ext.grid.GridPanel({
					id : 'ProjectSealRecycleGrid',
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

		this.gridPanel.addListener('rowdblclick', function(grid, rowindex, e) {
					grid.getSelectionModel().each(function(rec) {
								if (isGranted("_ProjectSealRecycleEdit")) {
									new ProjectSealRecycleForm({
												id : rec.data.id
											}).show();
								}
							});
				});
		this.rowActions.on('action', this.onRowAction, this);
	},//end of the initComponents()

	/**
	 * 
	 * @param {} self 当前窗体对象
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
		new ProjectSealRecycleForm().show();
	},
	/**
	 * 按IDS删除记录
	 * @param {} ids
	 */
	delByIds : function(ids) {
		Ext.Msg.confirm('信息确认', '您确认要删除所选记录吗？', function(btn) {
			if (btn == 'yes') {
				Ext.Ajax.request({
							url : __ctxPath
									+ '/statistics/multiDelProjectSealRecycle.do',
							params : {
								ids : ids
							},
							method : 'POST',
							success : function(response, options) {
								Ext.ux.Toast.msg('操作信息', '成功删除该项目印章回收！');
								Ext.getCmp('ProjectSealRecycleGrid').getStore()
										.reload();
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
		var gridPanel = Ext.getCmp('ProjectSealRecycleGrid');
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

	/**
	 * 编辑记录
	 * @param {} record
	 */
	editRecord : function(record) {
		new ProjectSealRecycleForm({
					id : record.data.id
				}).show();
	},
	/**
	 * 管理列中的事件处理
	 * @param {} grid
	 * @param {} record
	 * @param {} action
	 * @param {} row
	 * @param {} col
	 */
	onRowAction : function(gridPanel, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.delByIds(record.data.id);
				break;
			case 'btn-edit' :
				this.editRecord(record);
				break;
			case 'btn-flowView':
			AppUtil.selectProcessForm(record.data.processRunId);	
			default :
				break;
		}
	}
});
