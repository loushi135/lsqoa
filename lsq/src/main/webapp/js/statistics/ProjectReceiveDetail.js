ProjectReceiveDetail = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	store : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		ProjectReceiveDetail.superclass.constructor.call(this, {
					id : 'ProjectReceiveDetailWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					autoScroll : true,
					width : 715,
					height : 350,
					maximizable : true,
					title : '收款详细记录',
					buttonAlign : 'center',
					buttons : this.buttons
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		this.store = new Ext.data.JsonStore({
					url : __ctxPath + "/statistics/listProjectReceive.do",
					baseParams : {
						'Q_project.id_L_EQ' : this.proId
					},
					root : 'result',
					totalProperty : 'totalCounts',
					remoteSort : true,
					autoLoad : false,
					fields : [{
								name : 'id',
								type : 'int'
							}, 'amountBig', 'amount', 'project', 'createtime',
							'createUser', 'receiveTime', 'zy', 'pzzh']
				})
		this.store.setDefaultSort('id', 'desc');
		this.formPanel = new Ext.FormPanel({
					layout : 'form',
					bodyStyle : 'padding:10px 10px 10px 10px;',
					border : false,
					id : 'ProjectReceiveDetail',
					defaults : {
						anchor : '98%,98%'
					},
					items : [{
								xtype : 'grid',
								id : 'repayGrid',
								height : 250,
								width : 600,
								trackMouseOver : true,
								disableSelection : false,
								autoScroll : true,
								loadMask : true,
								viewConfig : {
									forceFit : false
								},
								store : this.store,
								cm : new Ext.grid.ColumnModel({
											columns : [
													new Ext.grid.RowNumberer(),
													{
														header : '项目名称',
														dataIndex : 'project',
														renderer : function(
																value) {
															return value.proName
														}
													}, {
														header : '收款时间',
														dataIndex : 'receiveTime'
													}, {
														header : '收款金额',
														dataIndex : 'amount'
													}, {
														header : '收款金额大写',
														dataIndex : 'amountBig'
													}, {
														header : '凭证字号',
														dataIndex : 'pzzh'
													}, {
														header : '摘要',
														dataIndex : 'zy'
													}],
											defaults : {
												sortable : true,
												menuDisabled : false,
												width : 100
											}
										}),
								bbar : new Ext.PagingToolbar({
											pageSize : 25,
											store : this.store,
											displayInfo : true,
											displayMsg : '当前页记录索引{0}-{1}， 共{2}条记录',
											emptyMsg : "当前没有记录"
										})
							}]
				});
		// 加载数据
		this.store.load({
					params : {
						start : 0,
						limit : 25
					}
				});

		// 初始化功能按钮
		this.buttons = [{
					text : '关闭',
					iconCls : 'btn-cancel',
					handler : this.cancel.createCallback(this)
				}];
	},// end of the initcomponents
	cancel : function(window) {
		window.close();
	}
});