/**
 * @author juql
 * @class MeetingRoomView
 * @extends Ext.Panel
 * @description [MeetingRoom]管理
 */
MeetingRoomView = Ext.extend(Ext.Panel, {
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
		MeetingRoomView.superclass.constructor.call(this, {
					id : 'MeetingRoomView',
					title : '会议室管理',
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
					height:35,
					frame:false,
					defaults : {
						xtype : 'label',
						border : false,
						anchor : '98%,98%'
					},
					items : [{
								text : '请输入查询条件:'
							}, {
								text : '会议室名称:'
							}, {
								name : 'Q_name_S_LK',
								xtype : 'textfield'
							}, {
								text : '容纳人数:'
							}, {
								name : 'Q_maxPerson_S_LK',
								xtype : 'textfield'
							}, {
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								handler : this.search.createCallback(this)
							}]
				});// end of the searchPanel

		// 加载数据至store
		this.store = new Ext.data.JsonStore({
					url : __ctxPath + "/admin/listMeetingRoom.do",
					root : 'result',
					totalProperty : 'totalCounts',
					remoteSort : true,
					fields : [{
								name : 'id',
								type : 'int'
							}, 'name', 'maxPerson', 'microphone', 'speaker',
							'projector', 'znhyt', 'notebook', 'wordPad',
							'status', 'remark']
				});
		this.store.setDefaultSort('id', 'desc');
		// 加载数据
		this.store.load({
					params : {
						start : 0,
						limit : 25
					}
				});

		// 初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
					columns : [sm, new Ext.grid.RowNumberer(), {
								header : 'id',
								dataIndex : 'id',
								hidden : true
							}, {
								header : '会议室名称',
								dataIndex : 'name'
							}, {
								header : '容纳人数',
								dataIndex : 'maxPerson'
							}, {
								header : '麦克风',
								dataIndex : 'microphone',
								renderer : function(value) {
									if (value == '0') {
										return '有';
									}
									if (value == '1') {
										return '无';
									}
								}
							}, {
								header : '音箱',
								dataIndex : 'speaker',
								renderer : function(value) {
									if (value == '0') {
										return '有';
									}
									if (value == '1') {
										return '无';
									}
								}
							}, {
								header : '投影仪',
								dataIndex : 'projector',
								renderer : function(value) {
									if (value == '0') {
										return '有';
									}
									if (value == '1') {
										return '无';
									}
								}
							}, {
								header : '智能会议通',
								dataIndex : 'znhyt',
								renderer : function(value) {
									if (value == '0') {
										return '有';
									}
									if (value == '1') {
										return '无';
									}
								}
							}, {
								header : '笔记本电脑',
								dataIndex : 'notebook',
								renderer : function(value) {
									if (value == '0') {
										return '有';
									}
									if (value == '1') {
										return '无';
									}
								}
							}, {
								header : '写字板',
								dataIndex : 'wordPad',
								renderer : function(value) {
									if (value == '0') {
										return '有';
									}
									if (value == '1') {
										return '无';
									}
								}
							}, {
								header : '状态',
								dataIndex : 'status',
								renderer : function(value) {
									if (value == '0') {
										return '可用';
									}
									if (value == '1') {
										return '占用';
									}
									return value;
								}
							}, {
								header : '备注',
								dataIndex : 'remark'
							}, {
								header : '管理',
								dataIndex : 'id',
								sortable : false,
								width : 50,
								renderer : function(value, metadata, record, rowIndex,
										colIndex) {
									var id = record.data.id;
									var str = '';
									if (isGranted('_MeetingRoomDel')) {
										str = '<button title="删除" value=" " class="btn-del" onclick="MeetingRoomView.delByIds('
												+ id + ')">&nbsp;</button>';
									}
//									if (isGranted('_MeetingRoomEdit')) {
										str += '&nbsp;<button title="详细" value=" " class="btn-edit" onclick="MeetingRoomView.editRecord('
												+ id + ')">&nbsp;</button>';
//									}
									return str;
								}
							}],
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
								id : 'addBtn',
								iconCls : 'btn-add',
								text : '添加会议室',
								xtype : 'button',
								handler : this.createRecord,
								listeners : {
									'beforerender' : function() {
										if (!isGranted('_MeetingRoomAdd')) {
											Ext.getCmp('addBtn').hide();
										}
									}
								}
							}, {
								id : 'delBtn',
								iconCls : 'btn-del',
								text : '删除会议室',
								xtype : 'button',
								handler : this.delRecords,
								scope : this,
								listeners : {
									'beforerender' : function() {
										if (!isGranted('_MeetingRoomDel')) {
											Ext.getCmp('delBtn').hide();
										}
									}
								}
							}, {
								id : 'editBtn',
								iconCls : 'btn-edit',
								text : '修改会议室状态',
								xtype : 'button',
								handler : this.editRecords,
								scope : this,
								listeners : {
									'beforerender' : function() {
										if (!isGranted('_MeetingRoomDel')) {
											Ext.getCmp('editBtn').hide();
										}
									}
								}
							}]
				});

		this.gridPanel = new Ext.grid.GridPanel({
					id : 'MeetingRoomGrid',
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
								new MeetingRoomShow({
											id : rec.data.id
										}).show();
							});
				});
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
				url : __ctxPath + '/admin/listMeetingRoom.do',
				success : function(formPanel, action) {
					var result = Ext.util.JSON.decode(action.response.responseText);
					var params = self.gridPanel.getStore().baseParams; 
			        Ext.apply(params,self.searchPanel.getForm().getValues());
					self.gridPanel.getStore().loadData(result);
				}
			});
		}
	},

	/**
	 * 添加记录
	 */
	createRecord : function() {
		new MeetingRoomForm().show();
	},
	/**
	 * 删除多条记录
	 */
	delRecords : function() {
		var gridPanel = Ext.getCmp('MeetingRoomGrid');
		var selectRecords = gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var ids = Array();
		for (var i = 0; i < selectRecords.length; i++) {
			ids.push(selectRecords[i].data.id);
		}
		MeetingRoomView.delByIds(ids);
	},
	editRecords : function() {
		var gridPanel = Ext.getCmp('MeetingRoomGrid');
		var selectRecords = gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要修改的记录！");
			return;
		}
		var ids = Array();
		for (var i = 0; i < selectRecords.length; i++) {
			ids.push(selectRecords[i].data.id);
		}
		Ext.Msg.confirm('信息确认', '您确认要修改所选记录吗？', function(btn) {
		if (btn == 'yes') {
			Ext.Ajax.request({
						url : __ctxPath + '/admin/saveMeetingRoom.do',
						params : {
							ids : ids
						},
						method : 'POST',
						success : function(response, options) {
							Ext.ux.Toast.msg('操作信息', '成功修改会议室状态！');
							Ext.getCmp('MeetingRoomGrid').getStore()
									.reload();
						},
						failure : function(response, options) {
							Ext.ux.Toast.msg('操作信息', '操作出错，请联系管理员！');
						}
					});
			}
		});// end of comfirm
	}
});
/**
 * 按IDS删除记录
 */
MeetingRoomView.delByIds = function(ids) {
	Ext.Msg.confirm('信息确认', '您确认要删除所选记录吗？', function(btn) {
		if (btn == 'yes') {
			Ext.Ajax.request({
						url : __ctxPath + '/admin/multiDelMeetingRoom.do',
						params : {
							ids : ids
						},
						method : 'POST',
						success : function(response, options) {
							Ext.ux.Toast.msg('操作信息', '成功删除该[MeetingRoom]！');
							Ext.getCmp('MeetingRoomGrid').getStore()
									.reload();
						},
						failure : function(response, options) {
							Ext.ux.Toast.msg('操作信息', '操作出错，请联系管理员！');
						}
					});
		}
	});// end of comfirm
};

/**
 * 编辑记录
 */
MeetingRoomView.editRecord = function(id) {
	new MeetingRoomForm({
				id : id
			}).show();
};
