/**
 * @author:
 * @class SmsmessageView
 * @extends Ext.Panel
 * @description [Smsmessage]管理
 * @company
 * @createtime:2010-07-19
 */
SmsmessageView = Ext.extend(Ext.Panel, {
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
		SmsmessageView.superclass.constructor.call(this, {
			id : 'SmsmessageView',
			title : '短信管理',
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
			height : 60,
			bodyStyle : 'padding:6px 10px 6px 10px',
			border : false,
			frame : true,
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
						text : '姓名:'
					}, {
						name : 'Q_name_S_LK',
						xtype : 'textfield'
					}, {
						style : 'margin-top:5px;',
						xtype : 'label',
						text : '发送人:'
					}, {
						name : 'Q_sender_S_LK',
						xtype : 'textfield'
					}, {
						style : 'margin-top:5px;',
						xtype : 'label',
						text : '发送时间:'
					}, {
						name : 'Q_sendTime_S_LK',
						format : 'Y-m-d',
						xtype : 'datefield',
						width : '120'
					}, {
						xtype : 'button',
						text : '查询',
						iconCls : 'search',
						handler : this.search.createCallback(this)
					}]
		});// end of the searchPanel

		// 加载数据至store
		this.store = new Ext.data.JsonStore({
			url : __ctxPath + "/system/listSmsmessage.do",
			root : 'result',
			totalProperty : 'totalCounts',
			remoteSort : true,
			fields : [{
						name : 'smsId',
						type : 'int'
					}, 'name', 'phoneNo', 'massage', 'sendTime', 'sender']
		});
		this.store.setDefaultSort('smsId', 'desc');
		// 加载数据
		this.store.load({
			params : {
				start : 0,
				limit : 25
			}
		});

		this.rowActions = new Ext.ux.grid.RowActions({
			header : '管理',
			width : 80,
			actions : [
					//								   {
					//										iconCls : 'btn-del',
					//										qtip : '删除',
					//										style : 'margin:0 3px 0 3px'
					//									}, 
					{
				iconCls : 'btn-edit',
				qtip : '编辑',
				style : 'margin:0 3px 0 3px'
			}]
		});

		// 初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
			columns : [sm, new Ext.grid.RowNumberer(), {
						header : 'smsId',
						dataIndex : 'smsId',
						hidden : true
					}, {
						header : '姓名',
						dataIndex : 'name'
					}, {
						header : '手机号码',
						dataIndex : 'phoneNo'
					}, {
						header : '短信内容',
						dataIndex : 'massage'
					}, {
						header : '发送人',
						dataIndex : 'sender'
					}, {
						header : '发送时间',
						dataIndex : 'sendTime'
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
						text : '发送短信',
						xtype : 'button',
						handler : this.createRecord
					}
					//									{
					//										iconCls : 'btn-del',
					//										text : '删除短信',
					//										xtype : 'button',
					//										handler : this.delRecords,
					//										scope : this
					//									}
					, {
						iconCls : 'btn-edit',
						text : '短信组管理',
						xtype : 'button',
						hidden:!isGranted("_SmsGroupManage"),
						handler : this.manageGroup
					}]
		});

		this.gridPanel = new Ext.grid.GridPanel({
			id : 'SmsmessageGrid',
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
				new SmsmessageForm({
					smsId : rec.data.smsId
				}).show();
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
				url : __ctxPath + '/system/listSmsmessage.do',
				success : function(formPanel, action) {
					var result = Ext.util.JSON.decode(action.response.responseText);
					self.gridPanel.getStore().loadData(result);
				}
			});
		}
	},

	/**
	 * 添加记录
	 */
	createRecord : function() {
		new SmsmessageForm().show();
	},
	/**
	 * 短信组管理
	 */
	manageGroup : function(){
		AppUtil.addTab('SmsGroupView');
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
					url : __ctxPath + '/system/multiDelSmsmessage.do',
					params : {
						ids : ids
					},
					method : 'POST',
					success : function(response, options) {
						Ext.ux.Toast.msg('操作信息', '成功删除该短信信息！');
						Ext.getCmp('SmsmessageGrid').getStore().reload();
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
		var gridPanel = Ext.getCmp('SmsmessageGrid');
		var selectRecords = gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var ids = Array();
		for (var i = 0; i < selectRecords.length; i++) {
			ids.push(selectRecords[i].data.smsId);
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
		new SmsmessageForm({
			smsId : record.data.smsId
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
			//					case 'btn-del' :
			//						this.delByIds(record.data.smsId);
			//						break;
			case 'btn-edit' :
				this.editRecord(record);
				break;
			default :
				break;
		}
	}
});
