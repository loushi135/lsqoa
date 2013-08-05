/**
 * @author:
 * @class ProjectBaseDataView
 * @extends Ext.Panel
 * @description [ProjectNew]管理
 * @company
 * @createtime:2010-07-19
 */
ProjectBaseDataView = Ext.extend(Ext.Panel, {
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
		ProjectBaseDataView.superclass.constructor.call(this, {
					id : 'ProjectBaseDataView',
					title : '工程项目财务数据',
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
								name : 'Q_proName_S_LK',
								xtype : 'textfield',
								width : 90
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '项目编号:'
							}, {
								name : 'Q_proNo_S_LK',
								xtype : 'textfield',
								width : 90
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '项目负责人:'
							}, {
								name : 'Q_proCharger_S_LK',
								xtype : 'textfield',
								width : 90
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '经办人:'
							}, {
								name : 'Q_manager_S_LK',
								xtype : 'textfield',
								width : 90
							}, {
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								handler : this.search.createCallback(this)
							}]
				});// end of the searchPanel

		var baseParams = {};
		// if(!isGranted("_ProjectNewQueryAll")){
		// baseParams = {'Q_area_S_EQ':__currentUserDept};
		// }
		// 加载数据至store
		this.store = new Ext.data.JsonStore({
					url : __ctxPath + "/statistics/listProjectNew.do",
					baseParams : baseParams,
					root : 'result',
					totalProperty : 'totalCounts',
					remoteSort : true,
					fields : [{
								name : 'id',
								type : 'int'
							}, 'proName', 'proNo', 'area', 'proCharger',
							'proChargerTel', 'proFollower', 'proFollowerTel',
							'designUnit', 'designCharger', 'designChargerTel',
							'buildUnit', 'buildCharger', 'buildChargerTel',
							'watchUnit', 'watchCharger', 'watchChargerTel',
							'totalUnit', 'totalCharger', 'totalChargerTel',
							'contract', 'startDate', 'endDate', 'businessMain',
							'enterDate', 'manager', 'status', 'processRunId']
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
						hide : !(isGranted("_ProjectNewDel"))
					}, {
						iconCls : 'btn-edit',
						qtip : '编辑',
						style : 'margin:0 3px 0 3px',
						hide : !(isGranted("_ProjectNewQuery") || isGranted("_ProjectNewEdit"))
					}, {
						iconCls : 'btn-flowView',
						qtip : '查看审批表单',
						style : 'margin:0 3px 0 3px'
					}]
		})

		// 初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
					columns : [sm, new Ext.grid.RowNumberer(), {
								header : 'id',
								dataIndex : 'id',
								hidden : true
							}, {
								header : '项目名称',
								dataIndex : 'proName'
							}, {
								header : '项目编号',
								dataIndex : 'proNo'
							}, {
								header : '所属区域',
								dataIndex : 'area'
							}, {
								header : '项目负责人',
								dataIndex : 'proCharger'
							}, {
								header : '联系电话',
								dataIndex : 'proChargerTel'
							}, {
								header : '跟踪预算员',
								dataIndex : 'proFollower'
							}, {
								header : '预算员电话',
								dataIndex : 'proFollowerTel'
							}, {
								header : '设计单位',
								dataIndex : 'designUnit'
							}, {
								header : '设计负责人',
								dataIndex : 'designCharger'
							}, {
								header : '设计负责人电话',
								dataIndex : 'designChargerTel'
							}, {
								header : '建设单位',
								dataIndex : 'buildUnit'
							}, {
								header : '建设负责人',
								dataIndex : 'buildCharger'
							}, {
								header : '建设负责人电话',
								dataIndex : 'buildChargerTel'
							}, {
								header : '监理单位',
								dataIndex : 'watchUnit'
							}, {
								header : '监理负责人',
								dataIndex : 'watchCharger'
							}, {
								header : '监理负责人电话',
								dataIndex : 'watchChargerTel'
							}, {
								header : '总包单位',
								dataIndex : 'totalUnit'
							}, {
								header : '总包负责人',
								dataIndex : 'totalCharger'
							}, {
								header : '总包负责人电话',
								dataIndex : 'totalChargerTel'
							}, {
								header : '合同编号',
								renderer : function(value, metadata, record,
										rowIndex, colIndex) {
									var contract = record.data.contract;
									var str = '';
									if (contract != null) {
										str = contract.contractNo;
									}
									return str;
								}
							}, {
								header : '合同金额',
								renderer : function(value, metadata, record,
										rowIndex, colIndex) {
									var contract = record.data.contract;
									var str = '';
									if (contract != null) {
										str = contract.sumPrice;
									}
									return str;
								}
							}, {
								header : '开工日期',
								dataIndex : 'startDate'
							}, {
								header : '竣工日期',
								dataIndex : 'endDate'
							}, {
								header : '业务主办',
								dataIndex : 'businessMain'
							}, {
								header : '进场日期',
								dataIndex : 'enterDate'
							}, {
								header : '经办人',
								dataIndex : 'manager'
							}, {
								header : '状态',
								dataIndex : 'status',
								renderer : function(value) {
									if (value != '') {
										if (value == '0') {
											return '可用';
										} else if (value == '1') {
											return '不可用';
										}
									} else {
										return '可用';
									}
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
				iconCls : 'btn-xls',
				text : '<div style="COLOR: #C3841D; FILTER: wave(add=0,lightstrength=50,strength=4,freq=2,phrase=30); FONT-FAMILY: 华文行楷; FONT-SIZE: 13pt; LINE-HEIGHT: 110%; WIDTH: 100%">收款导入</div>',
				xtype : 'button',
				hidden : !(isGranted("_ProjectReceiveQuery")
						|| isGranted("_ProjectReceiveAdd")
						|| isGranted("_ProjectReceiveEdit")
						|| isGranted("_ProjectReceiveDel") || isGranted("_ProjectReceiveImport")),
				handler : this.importReceive,
				scope : this
			}, {
				iconCls : 'btn-xls',
				text : '<div style="COLOR: #C3841D; FILTER: wave(add=0,lightstrength=50,strength=4,freq=2,phrase=30); FONT-FAMILY: 华文行楷; FONT-SIZE: 13pt; LINE-HEIGHT: 110%; WIDTH: 100%">报销导入</div>',
				xtype : 'button',
				hidden : !(isGranted("_ProjectRepayQuery")
						|| isGranted("_ProjectRepayAdd")
						|| isGranted("_ProjectRepayEdit")
						|| isGranted("_ProjectRepayDel") || isGranted("_ProjectRepayImport")),
				handler : this.importRepay,
				scope : this
			}, {
				iconCls : 'btn-download',
				text : '<div style="COLOR: #C3841D; FILTER: wave(add=0,lightstrength=50,strength=4,freq=2,phrase=30); FONT-FAMILY: 华文行楷; FONT-SIZE: 13pt; LINE-HEIGHT: 110%; WIDTH: 100%">下载报销导入模板</div>',
				xtype : 'button',
				handler : this.downloadTemplate,
				scope : this
			}]
		});

		var topbar2 = new Ext.Toolbar({
			height : 30,
			renderTo : this.gridPanel,
			bodyStyle : 'text-align:left',
			items : [{
				iconCls : 'assets-type',
				text : '<B>发票管理</B>',
				xtype : 'button',
				hidden : !(isGranted("_BillQuery") || isGranted("_BillAdd")
						|| isGranted("_BillEdit") || isGranted("_BillDel")),
				handler : this.manageBill,
				scope : this
			}, {
				iconCls : 'assets-type',
				text : '<B>收款管理</B>',
				xtype : 'button',
				hidden : !(isGranted("_ProjectReceiveQuery")
						|| isGranted("_ProjectReceiveAdd")
						|| isGranted("_ProjectReceiveEdit") || isGranted("_ProjectReceiveDel")),
				handler : this.manageReceive,
				scope : this
			}, {
				iconCls : 'assets-type',
				text : '<B>管理费率</B>',
				xtype : 'button',
				hidden : !(isGranted("_ProjectPercentageQuery") || isGranted("_ProjectPercentageEdit")),
				handler : this.managePercentage,
				scope : this
			}, {
				iconCls : 'assets-type',
				text : '<B>报销管理</B>',
				xtype : 'button',
				hidden : !(isGranted("_ProjectRepayQuery")
						|| isGranted("_ProjectRepayAdd")
						|| isGranted("_ProjectRepayEdit") || isGranted("_ProjectRepayDel")),
				handler : this.manageRepay,
				scope : this
			}, {
				iconCls : 'assets-type',
				text : '<B>垫资审批额度</B>',
				xtype : 'button',
				hidden : !(isGranted("_ProjectQuotaQuery") || isGranted("_ProjectQuotaEdit")),
				handler : this.manageQuota,
				scope : this
			}, {
				iconCls : 'assets-type',
				text : '<B>审定金额</B>',
				xtype : 'button',
				hidden : !(isGranted("_ProjectAuditQuery") || isGranted("_ProjectAuditEdit")),
				handler : this.manageAudit,
				scope : this
			}, {
				iconCls : 'assets-type',
				text : '<B>付款基数</B>',
				xtype : 'button',
				hidden : !(isGranted("_PaybaseQuery")
						|| isGranted("_PaybaseAdd")
						|| isGranted("_PaybaseEdit") || isGranted("_PaybaseDel")),
				handler : this.payBase,
				scope : this
			}, {
				iconCls : 'assets-type',
				text : '<B>付款管理</B>',
				xtype : 'button',
				hidden : !(isGranted("_BankPayApplyQuery")
						|| isGranted("_BankPayApplyAdd")
						|| isGranted("_BankPayApplyEdit") || isGranted("_BankPayApplyDel")),
				handler : this.bankPay,
				scope : this
			}]
		});

		this.gridPanel = new Ext.grid.GridPanel({
					listeners : { // 將第二個bar渲染到tbar裏面，通过listeners事件
						'render' : function() {
							topbar2.render(this.tbar);
						}
					},
					id : 'ProjectNewGrid',
					region : 'center',
					stripeRows : true,
					tbar : this.topbar,
					store : this.store,
					trackMouseOver : true,
					disableSelection : false,
					autoScroll : true,
					loadMask : true,
					height : 100,
					cm : cm,
					sm : sm,
					plugins : this.rowActions,
					viewConfig : {
						forceFit : false
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
	 * 收款导入
	 */
	importReceive : function() {
		new ProjectReceiveImport().show();
	},
	importRepay : function() {
		new ProjectRepayImport().show();
	},
	downloadTemplate : function() {
		location = __ctxPath+'/system/downloadTemplateFileAttach.do?fileName=报销导入模板.xls';
	},
	manageBill : function() {
//		var selectRecords = this.gridPanel.getSelectionModel().getSelections();
//		if (selectRecords.length == 0) {
//			Ext.ux.Toast.msg("信息", "请选择要进行发票管理的记录！");
//			return;
//		}
//		if (selectRecords.length > 1) {
//			Ext.ux.Toast.msg("信息", "只能选择一条记录进行发票管理！");
//			return;
//		}
//		var record = selectRecords[0];
		AppUtil.addTab('BillView', {
//					proId : record.data.id,
//					proName : record.data.proName
				})
	},
	/**
	 * 收款管理
	 */
	manageReceive : function() {
//		var selectRecords = this.gridPanel.getSelectionModel().getSelections();
//		if (selectRecords.length == 0) {
//			Ext.ux.Toast.msg("信息", "请选择要进行收款管理的记录！");
//			return;
//		}
//		if (selectRecords.length > 1) {
//			Ext.ux.Toast.msg("信息", "只能选择一条记录进行收款管理！");
//			return;
//		}
//		var record = selectRecords[0];
		AppUtil.addTab('ProjectReceiveView', {
//					proId : record.data.id
				})
	},
	/**
	 * 管理费率
	 */
	managePercentage : function() {
		var selectRecords = this.gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要进行管理费率的记录！");
			return;
		}
		if (selectRecords.length > 1) {
			Ext.ux.Toast.msg("信息", "只能选择一条记录进行管理费率！");
			return;
		}
		var record = selectRecords[0];
		new ProjectPercentageForm({
					proId : record.data.id
				}).show();
	},
	/**
	 * 报销管理
	 */
	manageRepay : function() {
//		var selectRecords = this.gridPanel.getSelectionModel().getSelections();
//		if (selectRecords.length == 0) {
//			Ext.ux.Toast.msg("信息", "请选择要进行报销管理的记录！");
//			return;
//		}
//		if (selectRecords.length > 1) {
//			Ext.ux.Toast.msg("信息", "只能选择一条记录进行报销管理！");
//			return;
//		}
//		var record = selectRecords[0];
		AppUtil.addTab('ProjectRepayView', {
//					proId : record.data.id
				})
	},
	/**
	 * 垫资审批额度
	 */
	manageQuota : function() {
		var selectRecords = this.gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要进行垫资审批额度的记录！");
			return;
		}
		if (selectRecords.length > 1) {
			Ext.ux.Toast.msg("信息", "只能选择一条记录进行垫资审批额度！");
			return;
		}
		var record = selectRecords[0];
		new ProjectQuotaForm({
					proId : record.data.id
				}).show();
	},
	/**
	 * 审定金额
	 */
	manageAudit : function() {
		var selectRecords = this.gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要进行垫资审批额度的记录！");
			return;
		}
		if (selectRecords.length > 1) {
			Ext.ux.Toast.msg("信息", "只能选择一条记录进行垫资审批额度！");
			return;
		}
		var record = selectRecords[0];
		new ProjectAuditForm({
					proId : record.data.id
				}).show();
	},
	/**
	 * 添加记录
	 */
	createRecord : function() {
		new ProjectNewForm().show();
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
											+ '/statistics/multiDelProjectNew.do',
									params : {
										ids : ids
									},
									method : 'POST',
									success : function(response, options) {
										Ext.ux.Toast.msg('操作信息', '成功删除该项目！');
										Ext.getCmp('ProjectNewGrid').getStore()
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
		var gridPanel = Ext.getCmp('ProjectNewGrid');
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
		new ProjectNewForm({
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
			case 'btn-flowView' :
				AppUtil.selectProcessForm(record.data.processRunId);
				break;
			default :
				break;
		}
	},
		/**
	 * 付款基数
	 */
	payBase:function(){
//		var thiz = this;
//		var selectRecords = this.gridPanel.getSelectionModel().getSelections();
//		if (selectRecords.length == 0) {
//			Ext.ux.Toast.msg("信息", "请选择要进行付款基数管理的记录！");
//			return;
//		}
//		if (selectRecords.length > 1) {
//			Ext.ux.Toast.msg("信息", "只能选择一条记录进行付款基数管理！");
//			return;
//		}
//		var record = selectRecords[0];
//		var contentPanel = App.getContentPanel();
//		var paybaseView  = contentPanel.getItem('PaybaseView');
//		if(paybaseView!=null){
//			if(record.data.id==this.lastMaterialId){
//				contentPanel.activate(paybaseView);
//			}else{
//				Ext.Msg.confirm('信息确认','您确认换一条记录进行付款基数管理吗？',function(btn){
//					if(btn=='yes'){
//						contentPanel.remove(paybaseView);
//						paybaseView = new PaybaseView({materialId:record.data.id});
//						contentPanel.add(paybaseView);
//						contentPanel.activate(paybaseView);
//						thiz.lastMaterialId = record.data.id;
//					}
//				});//end of comfirm
//			}
//		}else{
//			paybaseView = new PaybaseView({materialId:record.data.id});
//			contentPanel.add(paybaseView);
//			contentPanel.activate(paybaseView);
//			this.lastMaterialId = record.data.id;
//		}
		AppUtil.addTab('PaybaseView',{
//		proId:record.data.id
		}
		)
	},
	bankPay:function(){
		AppUtil.addTab('BankpayapplyView')
	}
});
