/**
 * @author:
 * @class OtherProjectRepayView
 * @extends Ext.Panel
 * @description [ProjectRepay]管理
 * @company
 * @createtime:2010-07-19
 */
OtherProjectRepayView = Ext.extend(Ext.Panel, {
	// 条件搜索Panel
	searchPanel : null,
	mainPanel : null,
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
		OtherProjectRepayView.superclass.constructor.call(this, {
					id : 'OtherProjectRepayView',
					title : '[其他项目报销]管理',
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
								text : '项目名称:'
							}, {
								name : 'Q_otherProject.proName_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '报销人:'
							}, {
								name : 'Q_repayUser.fullname_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '报销开始时间:'
							}, {
								name : 'Q_repayTime_D_GE',
								xtype : 'datefield',
								format : 'Y-m-d'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '报销截止时间:'
							}, {
								name : 'Q_repayTime_D_LE',
								format : 'Y-m-d',
								xtype : 'datefield'
							}, {
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								handler : this.search.createCallback(this)
							}]
				});// end of the searchPanel

		// 加载数据至store
		this.store = new Ext.data.JsonStore({
					url : __ctxPath + "/statistics/listProjectRepay.do",
					root : 'result',
					totalProperty : 'totalCounts',
					baseParams : {
						'Q_project.id_L_EQ' : this.proId,
						'Q_repayType_N_EQ' : 4
					},
					remoteSort : true,
					fields : [{
								name : 'id',
								type : 'int'
							}, 'amountBig', 'amount', 'proName','otherProject','proId', 'createtime',
							'createUser', 'repayTime', 'repayUser']
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
					width : 80,
					actions : [{
								iconCls : 'btn-del',
								qtip : '删除',
								style : 'margin:0 3px 0 3px',
								hide : !isGranted("_OtherProjectRepayDel")
							}, {
								iconCls : 'btn-edit',
								qtip : '编辑',
								style : 'margin:0 3px 0 3px',
								hide : !isGranted("_OtherProjectRepayEdit")
							}]
				});

		// 初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
					columns : [sm, new Ext.grid.RowNumberer(), {
								header : 'id',
								dataIndex : 'id',
								hidden : true
							}
							, {
								header : '项目名称',
								dataIndex : 'otherProject',
								renderer : function(value) {
									if (!Ext.isEmpty(value)) {
										return value.proName;
									}
								}
							}
							, {
								header : '报销人',
								dataIndex : 'repayUser',
								renderer : function(value) {
									if (!Ext.isEmpty(value)) {
										return value.fullname;
									}
								}
							}, {
								header : '报销时间',
								dataIndex : 'repayTime'
							}, {
								header : '报销金额',
								dataIndex : 'amount'
							}, {
								header : '报销金额大写',
								dataIndex : 'amountBig'
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
								text : '添加[报销]',
								xtype : 'button',
								handler : this.createRecord,
								scope : this,
								hidden : !isGranted("_OtherProjectRepayAdd")
							}, {
								iconCls : 'btn-del',
								text : '删除[报销]',
								xtype : 'button',
								handler : this.delRecords,
								scope : this,
								hidden : !isGranted("_OtherProjectRepayDel")
							}, {
								iconCls : 'btn-xls',
								text : '<div style="COLOR: #C3841D; FILTER: wave(add=0,lightstrength=50,strength=4,freq=2,phrase=30); FONT-FAMILY: 华文行楷; FONT-SIZE: 13pt; LINE-HEIGHT: 110%; WIDTH: 100%">报销导入</div>',
								xtype : 'button',
								hidden : !isGranted("_OtherProjectRepayImport"),
								handler : this.importRepay,
								scope : this
							}, {
								iconCls : 'btn-download',
								text : '<div style="COLOR: #C3841D; FILTER: wave(add=0,lightstrength=50,strength=4,freq=2,phrase=30); FONT-FAMILY: 华文行楷; FONT-SIZE: 13pt; LINE-HEIGHT: 110%; WIDTH: 100%">下载导入模板</div>',
								xtype : 'button',
								handler : this.downloadTemplate,
								scope : this
							}]
				});

		this.gridPanel = new Ext.grid.GridPanel({
					id : 'OtherProjectRepayGrid',
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
								if (isGranted("_OtherProjectRepayEdit")) {
									new OtherProjectRepayForm({
											id : rec.data.id
										}).show();											
								}
							});
				});
		this.rowActions.on('action', this.onRowAction, this);
		this.initData();
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
	initData : function() {
		// if (this.proId != null && this.proId != 'undefined') {
		// this.mainPanel.getForm().load({
		// deferredRender : false,
		// url : __ctxPath + '/statistics/getProjectNew.do?id='+ this.proId,
		// waitMsg : '正在载入数据...',
		// success : function(form, action) {
		// var jsonData = Ext.util.JSON.decode(action.response.responseText);
		// var res = jsonData.data;
		// Ext.getCmp("proNameLabel").setText("<font
		// color='green'>"+res.proName+"</font>",false);
		// Ext.getCmp("proNoLabel").setText("<font
		// color='green'>"+res.proNo+"</font>",false);
		// Ext.getCmp("areaLabel").setText("<font
		// color='green'>"+res.area+"</font>",false);
		// var proCharger = "";
		// if(!Ext.isEmpty(res.proChargerUser)){
		// proCharger = res.proChargerUser.fullname;
		// }
		// Ext.getCmp("proChargerLabel").setText("<font
		// color='green'>"+proCharger+"</font>",false);
		// // Ext.getCmp("proChargerLabel").setText("<font
		// color='green'>"+AmountInWords(res.contractAmount)+"</font>",false);
		// var url = __ctxPath +
		// '/statistics/getRepayByProNoProjectRepay.do?proNo='+ res.proNo;
		// var repayTotal = ajaxSyncCall(url).data;
		// Ext.getCmp("repayTotal").setText("<font
		// color='green'>"+repayTotal+"</font>",false);
		// Ext.getCmp("repayTotalBig").setText("<font
		// color='green'>"+AmountInWords(repayTotal)+"</font>",false);
		// },
		// failure : function(form, action) {
		// }
		// });
		// }
	},
	importRepay : function() {
		new ProjectRepayImport().show();
	},
	downloadTemplate : function() {
		location = __ctxPath+'/system/downloadTemplateFileAttach.do?fileName=报销导入模板.xls';
	},
	/**
	 * 添加记录
	 */
	createRecord : function() {
		new OtherProjectRepayForm().show();
	},
	/**
	 * 按IDS删除记录
	 * 
	 * @param {}
	 *            ids
	 */
	delByIds : function(ids, obj) {
		Ext.Msg.confirm('信息确认', '您确认要删除所选记录吗？', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
									url : __ctxPath
											+ '/statistics/multiDelProjectRepay.do',
									params : {
										ids : ids
									},
									method : 'POST',
									success : function(response, options) {
										Ext.ux.Toast.msg('操作信息', '成功删除该[报销]！');
										obj.initData();
										Ext.getCmp('OtherProjectRepayGrid')
												.getStore().reload();
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
		var gridPanel = Ext.getCmp('OtherProjectRepayGrid');
		var selectRecords = gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var ids = Array();
		for (var i = 0; i < selectRecords.length; i++) {
			ids.push(selectRecords[i].data.id);
		}
		this.delByIds(ids, this);
	},

	/**
	 * 编辑记录
	 * 
	 * @param {}
	 *            record
	 */
	editRecord : function(record) {
		new OtherProjectRepayForm({
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
				this.delByIds(record.data.id, this);
				break;
			case 'btn-edit' :
				this.editRecord(record);
				break;
			default :
				break;
		}
	}
});
