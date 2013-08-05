/**
 * @author:
 * @class CommonReportView
 * @extends Ext.Panel
 * @description [CommonReport]管理
 * @company
 * @createtime:2010-07-19
 */
CommonReportView = Ext.extend(Ext.Panel, {
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
				CommonReportView.superclass.constructor.call(this, {
							id : 'CommonReportView',
							title : '通用报告管理',
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
							height : 70,
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
										text : '报告人:'
									}, {
										name : 'Q_reporter_S_LK',
										xtype : 'textfield',
										width:90
									}, {
										style : 'margin-top:5px;',
										xtype : 'label',
										text : '所在部门:'
									}, {
										name : 'Q_deptName_S_LK',
										xtype : 'textfield',
										width:90
									}, {
										style : 'margin-top:5px;',
										xtype : 'label',
										text : '类型:'
									}, {
										name : 'Q_state_S_LK',
										xtype : 'textfield',
										width:90
									}, {
										style : 'margin-top:5px;',
										xtype : 'label',
										text : '所在公司:'
									}, {
										name : 'Q_company_S_LK',
										xtype : 'textfield',
										width:90
									}, {
										xtype : 'button',
										text : '查询',
										iconCls : 'search',
										handler : this.search
												.createCallback(this)
									}]
						});// end of the searchPanel

				// 加载数据至store
				this.store = new Ext.data.JsonStore({
							url : __ctxPath + "/statistics/listCommonReport.do",
							root : 'result',
							totalProperty : 'totalCounts',
							remoteSort : true,
							fields : [{
										name : 'id',
										type : 'int'
									}, 'reporter', 'deptName', 'state',
									'company', 'sendDept', 'content']
						});
				this.store.setDefaultSort('id', 'desc');
				// 加载数据
				this.store.load({
							params : {
								start : 0,
								limit : 25
							}
						});
               if(isGranted('_CommonReportQuery')){
				this.rowActions = new Ext.ux.grid.RowActions({
							header : '管理',
							width : 80,
							actions : [{
										iconCls : 'btn-readdocument',
										qtip : '查看',
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
							columns : [sm, new Ext.grid.RowNumberer(), {
										header : 'id',
										dataIndex : 'id',
										hidden : true
									}, {
										header : '报告人',
										dataIndex : 'reporter'
									}, {
										header : '所在部门',
										dataIndex : 'deptName'
									}, {
										header : '类型',
										dataIndex : 'state'
									}, {
										header : '所在公司',
										dataIndex : 'company'
									}, {
										header : '抄送部门',
										dataIndex : 'sendDept'
									}, this.rowActions],
							defaults : {
								sortable : true,
								menuDisabled : false,
								width : 100
							}
						});
				this.topbar = new Ext.Toolbar({
					height : 30,
					bodyStyle : 'text-align:left',
					items : [{
								iconCls : 'btn-add',
								text : '添加[]',
								xtype : 'button',
								hidden:true,
								handler : this.createRecord
							}, {
								iconCls : 'btn-del',
								text : '删除[]',
								xtype : 'button',
								hidden:true,
								handler : this.delRecords,
								scope : this
							}]
				});

				this.gridPanel = new Ext.grid.GridPanel({
							id : 'CommonReportGrid',
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
										displayMsg : "当前页记录索引{0}-{1}， 共{2}条记录",
										emptyMsg : "当前没有记录"
									})
						});

				this.gridPanel.addListener('rowdblclick', function(grid,
								rowindex, e) {
							grid.getSelectionModel().each(function(rec) {
								if(isGranted('_CommonReportQuery')){
										new CommonReportForm({
													id : rec.data.id
												}).show();
								    }
									});
						});
				this.rowActions.on('action', this.onRowAction, this);
			},

			search : function(self) {
				if (self.searchPanel.getForm().isValid()) {// 如果合法
					self.searchPanel.getForm().submit({
						waitMsg : '正在提交查询',
						url : __ctxPath + '/statistics/listCommonReport.do',
						success : function(formPanel, action) {
							var result=Ext.util.JSON.decode(action.response.responseText);
				            var params = self.gridPanel.getStore().baseParams; 
				            Ext.apply(params,self.searchPanel.getForm().getValues());
				            self.gridPanel.getStore().loadData(result);
						}
					});
				}
			},

			editRecord : function(record) {
				new CommonReportForm({
							id : record.data.id
						}).show();
			},
			onRowAction : function(gridPanel, record, action, row, col) {
				switch (action) {
					case 'btn-readdocument' :
						this.editRecord(record);
						break;
					default :
						break;
				}
			}
		});
