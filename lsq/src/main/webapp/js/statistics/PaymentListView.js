/**
 * @author:
 * @class PaymentListView
 * @extends Ext.Panel
 * @description [PaymentList]管理
 * @company
 * @createtime:2010-07-19
 */
PaymentListView = Ext.extend(Ext.Panel, {
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
		PaymentListView.superclass.constructor.call(this, {
					id : 'PaymentListView',
					title : '暂支单管理',
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
								name : 'Q_project.proName_S_LK',
								xtype : 'textfield',
								width:90
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '项目编号:'
							}, {
								name : 'Q_project.proNo_S_LK',
								xtype : 'textfield',
								width:90
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '借款人:'
							}, {
								name : 'Q_borrower_S_LK',
								xtype : 'textfield',
								width:90
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '暂支类别:'
							}, {
								name : 'Q_paymentType_S_LK',
								xtype : 'textfield',
								width:90
							}, {
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								handler : this.search.createCallback(this)
							}]
				});// end of the searchPanel

		// 加载数据至store
				
		this.store = new Ext.data.JsonStore({
					url : __ctxPath + "/statistics/listPaymentList.do",
					root : 'result',
					totalProperty : 'totalCounts',
					remoteSort : true,
					fields : [{
								name : 'id',
								type : 'int'
							},
							'project','deptName', 'paymentSumBig',
							'paymentSumSmall', 'preNowReturnTime', 'owedSum',
							'preOwedReturnTime', 'borrower', 'paymentType','processRunId']
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
					actions : [
							{
								iconCls : 'btn-del',
								qtip : '删除',
								style : 'margin:0 3px 0 3px',
								hide:!isGranted("_PaymentListDel")
							},
							{
								iconCls : 'btn-edit',
								qtip : '编辑',
								style : 'margin:0 3px 0 3px',
								hide:!isGranted("_PaymentListEdit")
							},{
								 iconCls:'btn-flowView',
								 qtip:'查看审批表单',
								 style:'margin:0 3px 0 3px'
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
								header : '项目名称',
								dataIndex : 'project',
								renderer :function(data){
								  return data.proName
								   
								}
							}, {
								header : '项目编号',
								dataIndex : 'project',
								renderer :function(data){
								  return data.proNo
								}
							}, {
								header : '部门名称',
								dataIndex : 'deptName'
							}, {
								header : '本次借款金额大写',
								dataIndex : 'paymentSumBig'
							}, {
								header : '本次借款金额小写',
								dataIndex : 'paymentSumSmall',
								renderer:function(value){
									if(!Ext.isEmpty(value)){
										return value+"元";
									}
								}
							}, {
								header : '本次借款预计归还时间',
								dataIndex : 'preNowReturnTime'
							}, {
								header : '目前欠款金额',
								dataIndex : 'owedSum',
								renderer:function(value, metadata, record){
									var str = "";
									if(!Ext.isEmpty(value)){
										if(parseFloat(value)!=0.00){
											str= "<font color='red'>"+value+"元</font>";
										}else{
											str= "<font color='green'>"+"已还清</font>"
										}
									}
									return "<a href='javascript:void(0)' onClick='PaymentListView.showRepayment("+record.data.id+");' title='点击查看还款详细'>"+str+"</a>";
								}
							},{
								header : '借款人',
								dataIndex : 'borrower'
							}, {
								header : '暂支类别',
								dataIndex : 'paymentType'
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
					items : [
						{
								iconCls : 'btn-add',
								text : '添加[暂支单]',
								xtype : 'button',
								hidden:!isGranted("_PaymentListAdd"),
								handler : this.createRecord
							}, {
								iconCls : 'btn-del',
								text : '删除[暂支单]',
								xtype : 'button',
								hidden:!isGranted("_PaymentListDel"),
								handler : this.delRecords,
								scope : this
							},
							{
								text : '还款',
								xtype : 'button',
								iconCls:'menu-salary',
								hidden:!isGranted("_PaymentListDel"),
								handler : this.repayment,
								scope : this
							}
							]
				});

		this.gridPanel = new Ext.grid.GridPanel({
					id : 'PaymentListGrid',
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
								displayInfo : true,
								displayMsg : '当前页记录索引{0}-{1}， 共{2}条记录',
								emptyMsg : "当前没有记录"
							})
				});

		this.gridPanel.addListener('rowdblclick', function(grid, rowindex, e) {
					grid.getSelectionModel().each(function(rec) {
						if(isGranted("_PaymentListEdit")){
							new PaymentListForm({id : rec.data.id}).show();
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
			var params = self.gridPanel.getStore().baseParams;
			Ext.apply(params, self.searchPanel.getForm().getValues());
			self.gridPanel.getStore().load(params);
		}
	},

	/**
	 * 添加记录
	 */
	createRecord : function() {
		new PaymentListForm().show();
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
									+ '/statistics/multiDelPaymentList.do',
							params : {
								ids : ids
							},
							method : 'POST',
							success : function(response, options) {
								Ext.ux.Toast.msg('操作信息', '成功删除该[暂支单]！');
								Ext.getCmp('PaymentListGrid').getStore()
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
		var gridPanel = Ext.getCmp('PaymentListGrid');
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
		new PaymentListForm({
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
			default :
				break;
		}
	},
	repayment: function(){
		var gridPanel = Ext.getCmp('PaymentListGrid');
		var selectRecords = gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要进行还款的暂支单记录！");
			return;
		}
		if (selectRecords.length > 1) {
			Ext.ux.Toast.msg("信息", "只能选择一条暂支单记录进行还款！");
			return;
		}
		var paymentId = selectRecords[0].data.id;
		new RepaymentForm({paymentId:paymentId}).show();
	}
	
});

PaymentListView.showRepayment=function(paymentId){
	new RepaymentView({
					paymentId : paymentId
				}).show();
}
