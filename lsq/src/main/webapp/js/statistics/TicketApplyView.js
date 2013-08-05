/**
 * @author:
 * @class TicketApplyView
 * @extends Ext.Panel
 * @description [TicketApply]管理
 * @company
 * @createtime:2010-07-19
 */
TicketApplyView = Ext.extend(Ext.Panel, {
	// 条件搜索Panel
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
		TicketApplyView.superclass.constructor.call(this, {
					id : 'TicketApplyView',
					title : '票务申请管理',
					region : 'center',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	},// end of constructor

	// 初始化组件
	initUIComponents : function() {
		// 初始化搜索条件Panel
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
								text : '报告人:'
							}, {
								name : 'Q_reporter_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '出行人员:'
							}, {
								name : 'Q_bookUsers_S_LK',
								xtype : 'textfield'
							}
//							, {
//								style : 'margin-top:5px;',
//								xtype : 'label',
//								text : '出差类型:'
//							}, {
//								name : 'Q_businessType_S_LK',
//								xtype : 'textfield'
//							}, {
//								style : 'margin-top:5px;',
//								xtype : 'label',
//								text : '项目主键:'
//							}, {
//								name : 'Q_projectId_S_LK',
//								xtype : 'textfield'
//							}, {
//								style : 'margin-top:5px;',
//								xtype : 'label',
//								text : '出发地:'
//							}, {
//								name : 'Q_departure_S_LK',
//								xtype : 'textfield'
//							}, {
//								style : 'margin-top:5px;',
//								xtype : 'label',
//								text : '目的地:'
//							}, {
//								name : 'Q_destination_S_LK',
//								xtype : 'textfield'
//							}, {
//								style : 'margin-top:5px;',
//								xtype : 'label',
//								text : '票务类型:'
//							}, {
//								name : 'Q_ticketType_S_LK',
//								xtype : 'textfield'
//							}, {
//								style : 'margin-top:5px;',
//								xtype : 'label',
//								text : '出发时间:'
//							}, {
//								name : 'Q_departureTime_S_LK',
//								xtype : 'textfield'
//							}, {
//								style : 'margin-top:5px;',
//								xtype : 'label',
//								text : '返程时间:'
//							}, {
//								name : 'Q_backTime_S_LK',
//								xtype : 'textfield'
//							}, {
//								style : 'margin-top:5px;',
//								xtype : 'label',
//								text : '是否需要预订返程票:'
//							}, {
//								name : 'Q_backOrNot_S_LK',
//								xtype : 'textfield'
//							}, {
//								style : 'margin-top:5px;',
//								xtype : 'label',
//								text : '申请原因:'
//							}, {
//								name : 'Q_applyReason_S_LK',
//								xtype : 'textfield'
//							}
							, {
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								handler : this.search.createCallback(this)
							}]
				});// end of the searchPanel

		// 加载数据至store
		this.store = new Ext.data.JsonStore({
					url : __ctxPath + "/statistics/listTicketApply.do",
					root : 'result',
					totalProperty : 'totalCounts',
					remoteSort : true,
					fields : [{
								name : 'id',
								type : 'int'
							}, 'reporter', 'dept', 'bookUsers',
							'bookUserIds', 'ticketNum', 'businessType',
							'project', 'departure', 'destination',
							'ticketType', 'departureTime', 'backTime',
							'backOrNot', 'applyReason','processRunId','departureDetail','backDetail','amount','amountBig','status']
				});
		this.store.setDefaultSort('id', 'desc');
		// 加载数据
		this.store.load({
					params : {
						start : 0,
						limit : 25
					}
				});

		this.rowActions = new Ext.ux.grid.RowActions({
					header : '管理',
					width : 120,
					actions : [{
								iconCls : 'btn-del',
								qtip : '删除',
								hide:!isGranted("_TicketApplyDel"),
//								hide:true,
								style : 'margin:0 3px 0 3px'
							}, {
								iconCls : 'btn-edit',
								qtip : '编辑',
//								hide:true,
								hide:!isGranted("_TicketApplyEdit"),
								style : 'margin:0 3px 0 3px'
							},{
								 iconCls:'btn-flowView',
								 qtip:'查看审批表单',
								 style:'margin:0 3px 0 3px'
							}, {
								iconCls : 'menu-flowWait',
								qtip : '退改签',
//								hide:true,
								hide:!isGranted("_TicketApplyChange"),
								style : 'margin:0 3px 0 3px'
							}
							]
				});

		// 初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
					columns : [sm, new Ext.grid.RowNumberer(), {
								header : 'id',
								dataIndex : 'id',
								hidden : true
							}, {
								header : '报告人',
								dataIndex : 'reporter'
							}, {
								header : '所属部门',
								dataIndex:'dept',
								renderer :function(value,metadata,record,rowIndex,colIndex,store){
									if(value!=null){
										return value.depName;
									}
								}
							},{
								header : '需要申请票数',
								dataIndex : 'ticketNum'
							}, {
								header : '出差类型',
								dataIndex : 'businessType'
							}, {
								header : '项目名称',
								dataIndex:'project',
								width:150,
								renderer :function(value,metadata,record,rowIndex,colIndex,store){
									if(value!=null){
										return value.proName;
									}
								}
							}, {
								header : '出发地',
								dataIndex : 'departure'
							}, {
								header : '目的地',
								dataIndex : 'destination'
							}, {
								header : '票务类型',
								dataIndex : 'ticketType'
							}, {
								header : '出发时间',
								width:120,
								dataIndex : 'departureTime'
							}, {
								header : '具体',
								dataIndex : 'departureDetail'
							}, {
								header : '返程时间',
								width:120,
								dataIndex : 'backTime'
							}, {
								header : '具体',
								dataIndex : 'backDetail'
							}, {
								header : '是否需要预订返程票',
								dataIndex : 'backOrNot'
							}, {
								header : '申请原因',
								dataIndex : 'applyReason',
								width:150
							}, {
								header : '总费用小写(元)',
								dataIndex : 'amount',
								width:110
							}, {
								header : '总费用大写',
								dataIndex : 'amountBig',
								width:150
							}, {
								header : '状态',
								dataIndex : 'status',
								renderer:function(value,meta,record){
									if(!Ext.isEmpty(value)){
										if(value==0){
											return "正常";
										}else{
											return '已退签';
										}
									}
								}
							}, this.rowActions],
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
								iconCls : 'btn-add',
								text : '添加票务信息',
								xtype : 'button',
//								hidden:true,
								hidden:!isGranted("_TicketApplyAdd"),
								handler : this.createRecord
							}, {
								iconCls : 'btn-del',
								text : '删除票务信息',
								xtype : 'button',
//								hidden:true,
								hidden:!isGranted("_TicketApplyDel"),
								handler : this.delRecords,
								scope : this
							},{
								iconCls : 'menu-flowWait',
								text : '退改签',
								xtype : 'button',
//								hidden:true,
								hidden:!isGranted("_TicketApplyChange"),
								handler : this.changeRecords,
								scope : this
							}]
				});

		this.gridPanel = new Ext.grid.GridPanel({
					id : 'TicketApplyGrid',
					region : 'center',
					stripeRows : true,
					tbar : this.topbar,
					store : this.store,
					trackMouseOver : true,
					disableSelection : false,
					autoScroll:true,
					loadMask : true,
					cm : cm,
					sm : sm,
					plugins : this.rowActions,
					viewConfig : {
//						forceFit : true
//						autoFill : true // 自动填充
						// showPreview : false
					},
					bbar : new Ext.PagingToolbar({
								pageSize : 25,
								store : this.store,
								displayInfo : true,
								displayMsg : '当前页记录索引{0}-{1}， 共{2}条记录',
								emptyMsg : "当前没有记录"
							})
				});

		this.gridPanel.addListener('rowdblclick', function(grid, rowindex, e) {
					grid.getSelectionModel().each(function(rec) {
						if(isGranted("_TicketApplyEdit")){
										   new TicketApplyForm({
															id : rec.data.id
														}).show();}		
								
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
			var params = self.gridPanel.getStore().baseParams;
			Ext.apply(params, self.searchPanel.getForm().getValues());
			self.gridPanel.getStore().load(params);
		}
	},

	/**
	 * 添加记录
	 */
	createRecord : function() {
		new TicketApplyForm().show();
	},
	/**
	 *  退改签
	 */
	changeRecords :　function(){
		var gridPanel = Ext.getCmp('TicketApplyGrid');
		var selectRecords = gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		if (selectRecords.length > 1) {
			Ext.ux.Toast.msg("信息", "只能选择一条记录进行退改签操作！");
			return;
		}
		this.changeById(selectRecords[0].data.id);
	},
	changeById : function(id){
		new TicketApplyChangeForm({id : id}).show();
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
									+ '/statistics/multiDelTicketApply.do',
							params : {
								ids : ids
							},
							method : 'POST',
							success : function(response, options) {
								Ext.ux.Toast.msg('操作信息', '成功删除该票务信息！');
								Ext.getCmp('TicketApplyGrid').getStore()
										.reload();
							},
							failure : function(response, options) {
								Ext.ux.Toast.msg('操作信息', '操作出错，请联系管理员！');
							}
						});
			}
		});// end of comfirm
	},
	/**
	 * 删除多条记录
	 */
	delRecords : function() {
		var gridPanel = Ext.getCmp('TicketApplyGrid');
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
	 * 
	 * @param {}
	 *            record
	 */
	editRecord : function(record) {
		new TicketApplyForm({
					id : record.data.id
				}).show();
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
				this.delByIds(record.data.id);
				break;
			case 'btn-edit' :
				this.editRecord(record);
				break;
			case 'btn-flowView':
				AppUtil.selectProcessForm(record.data.processRunId);
				break;
			case 'menu-flowWait':
				this.changeById(record.data.id);
				break;
			default :
				break;
		}
	}
});
