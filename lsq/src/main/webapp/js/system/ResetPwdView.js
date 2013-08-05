/**
 * @author:
 * @class ResetPwdView
 * @extends Ext.Panel
 * @description [密码重置]管理
 * @company
 * @createtime:2010-07-19
 */
ResetPwdView = Ext.extend(Ext.Panel, {
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
				ResetPwdView.superclass.constructor.call(this, {
							id : 'ResetPwdView',
							title : '[密码重置]管理',
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
										text : '用户姓名:'
									}, {
										name : 'Q_userFullName_S_LK',
										xtype : 'textfield'
									}, {
										style : 'margin-top:5px;',
										xtype : 'label',
										text : '用户名:'
									}, {
										name : 'Q_userLoginName_S_LK',
										xtype : 'textfield'
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
							url : __ctxPath + "/system/listResetPwd.do",
							root : 'result',
							totalProperty : 'totalCounts',
							remoteSort : true,
							fields : [{
										name : 'id',
										type : 'int'
									}, 'userFullName', 'userLoginName',
									'resetPWD', 'timeCreate', 'opeaterUser']
						});
				this.store.setDefaultSort('id', 'desc');
				// 加载数据
				this.store.load({
							params : {
								start : 0,
								limit : 25
							}
						});

//				 this.rowActions = new Ext.ux.grid.RowActions({
//					 header : '管理',
//					 width : 80,
//					 actions : [ {
//					 iconCls : 'btn-reset',
//					 qtip : '重置密码',
//					 style : 'margin:0 3px 0 3px',
//					 hide : !isGranted("_ResetPwdAdd")
//					 }]
//				 });

				// 初始化ColumnModel
				var sm = new Ext.grid.CheckboxSelectionModel();
				var cm = new Ext.grid.ColumnModel({
							columns : [sm, new Ext.grid.RowNumberer(), {
										header : 'id',
										dataIndex : 'id',
										hidden : true
									}, {
										header : '用户姓名',
										dataIndex : 'userFullName'
									}, {
										header : '用户名',
										dataIndex : 'userLoginName'
									}, {
										header : '重置的密码',
										dataIndex : 'resetPWD'
									}, {
										header : '重置时间',
										dataIndex : 'timeCreate'
									}, {
										header : '操作者',
										dataIndex : 'opeaterUser',
										renderer : function(value) {
											var str = '';
											if (!Ext.isEmpty(value)) {
												str = value.fullname;
											} else {
												str = "[自助]";
											}
											return str;
										}
									}
							 , {
							 	
										header : '操作',
										dataIndex : 'resetPWD',
										sortable : false,
										renderer : function(value,metadata,record) {
											var str="";
											if (!Ext.isEmpty(value)) {
												str="";
											} else {
												str = "<div class='btn-reset' onclick='new ResetPwdForm({resetId:\""+record.data.id+"\",username:\""+
															record.data.userLoginName+"\"}).show()' " +
														"style='width:16px ;cursor: pointer;' title='重置密码'>&nbsp</div";
											}
											return str;
										}
									
							 }
							],
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
										iconCls : 'btn-reset',
										text : '重置密码',
										xtype : 'button',
										handler : this.createRecord,
										hidden : !isGranted("_ResetPwdAdd")
									}]
						});

				this.gridPanel = new Ext.grid.GridPanel({
							id : 'ResetPwdGrid',
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
//							plugins : this.rowActions,
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

				// this.gridPanel.addListener('rowdblclick', function(grid,
				// rowindex, e) {
				// grid.getSelectionModel().each(function(rec) {
				// new ResetPwdForm({
				// id : rec.data.id
				// }).show();
				// });
				// });
//				 this.rowActions.on('action', this.onRowAction, this);
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
				new ResetPwdForm().show();
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
													+ '/system/multiDelResetPwd.do',
											params : {
												ids : ids
											},
											method : 'POST',
											success : function(response,
													options) {
												Ext.ux.Toast.msg('操作信息',
														'成功删除该[密码重置]！');
												Ext.getCmp('ResetPwdGrid')
														.getStore().reload();
											},
											failure : function(response,
													options) {
												Ext.ux.Toast.msg('操作信息',
														'操作出错，请联系管理员！');
											}
										});
							}
						});// end of comfirm
			},
			/**
			 * 删除多条记录
			 */
			delRecords : function() {
				var gridPanel = Ext.getCmp('ResetPwdGrid');
				var selectRecords = gridPanel.getSelectionModel()
						.getSelections();
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
				new ResetPwdForm({
							username : record.data.userLoginName
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
					case 'btn-reset' :
						this.editRecord(record);
						break;
					default :
						break;
				}
			}
		});
