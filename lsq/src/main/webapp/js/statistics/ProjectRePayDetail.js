ProjectRePayDetail = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	store : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		ProjectRePayDetail.superclass.constructor.call(this, {
					id : 'ProjectRePayDetailWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					autoScroll : true,
					width : 615,
					height : 350,
					maximizable : true,
					title : '报销详细记录',
					buttonAlign : 'center',
					buttons : this.buttons
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		this.store = new Ext.data.JsonStore({
					url : __ctxPath + "/statistics/listProjectRepay.do",
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
							'createUser', 'repayTime', 'repayUser']
				})
		this.store.setDefaultSort('id', 'desc');
		this.formPanel = new Ext.FormPanel({
					layout : 'form',
					bodyStyle : 'padding:10px 10px 10px 10px;',
					border : false,
					id : 'ProjectRePayDetail',
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
														header : 'id',
														dataIndex : 'id',
														hidden : true
													}, {
														header : '项目名称',
														dataIndex : 'project',
														renderer : function(
																value) {
															if (!Ext
																	.isEmpty(value)) {
																return value.proName;
															}
														}
													}, {
														header : '报销人',
														dataIndex : 'repayUser',
														renderer : function(
																value) {
															if (!Ext
																	.isEmpty(value)) {
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