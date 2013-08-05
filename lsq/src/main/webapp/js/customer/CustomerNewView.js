/**
 * @author:
 * @class CustomerNewView
 * @extends Ext.Panel
 * @description [CustomerNew]管理
 * @company
 * @createtime:2010-07-19
 */
CustomerNewView = Ext.extend(Ext.Panel, {
	// 条件搜索Panel
	searchPanel : null,
	// 数据展示Panel
	gridPanel : null,
	// GridPanel的数据Store
	treePanel:null,
	mainPanel:null,
	store : null,
	// 头部工具栏
	topbar : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		CustomerNewView.superclass.constructor.call(this, {
					id : 'CustomerNewView',
					title : '客户信息管理',
					region : 'center',
					layout : 'border',
					items : [this.treePanel, this.mainPanel]
				});
	},// end of constructor

	// 初始化组件
	initUIComponents : function() {
		// 初始化搜索条件Panel
		var provinceTreePanel = new ProvinceTreePanel();
			provinceTreePanel.type='2';
		this.treePanel = provinceTreePanel.getTree();
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
								text : '客户名称:'
							}, {
								name : 'Q_name_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '客户全名:'
							}, {
								name : 'Q_fullname_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '编号:'
							}, {
								name : 'Q_code_S_LK',
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
					url : __ctxPath + "/customer/listCustomerNew.do",
					root : 'result',
					totalProperty : 'totalCounts',
					remoteSort : true,
					fields : [{
								name : 'id',
								type : 'int'
							}, 'name', 'fullname', 'code','delFlag','province'
										,'city']
				});
		this.store.setDefaultSort('id', 'desc');
		// 加载数据
		this.store.load({
					params : {
						start : 0,
						limit : 25
					}
				});
		if (isGranted('_CustomerNewEdit') && isGranted('_CustomerNewDel')) {
			this.rowActions = new Ext.ux.grid.RowActions({
						header : '管理',
						width : 80,
						actions : [{
									iconCls : 'btn-del',
									qtip : '删除',
									style : 'margin:0 3px 0 3px'
								}, {
									iconCls : 'btn-edit',
									qtip : '编辑',
									style : 'margin:0 3px 0 3px'
								}]
					})
		} else {
			if (isGranted('_CustomerNewEdit')) {
				this.rowActions = new Ext.ux.grid.RowActions({
							header : '管理',
							width : 80,
							actions : [{
										iconCls : 'btn-edit',
										qtip : '编辑',
										style : 'margin:0 3px 0 3px'
									}]
						})

			} else if (isGranted('_CustomerNewDel')) {
				this.rowActions = new Ext.ux.grid.RowActions({
							header : '管理',
							width : 80,
							actions : [{
										iconCls : 'btn-del',
										qtip : '删除',
										style : 'margin:0 3px 0 3px'
									}]
						})
			}
		}

		// 初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
					columns : [sm, new Ext.grid.RowNumberer(), {
								header : 'id',
								dataIndex : 'id',
								hidden : true
							}, {
								header : '代码',
								dataIndex : 'code'
							}, {
								header : '客户名称',
								dataIndex : 'name'
							}, {
								header : '客户全名',
								dataIndex : 'fullname'
							},{
								header : '地区',	
								renderer : function(val,meta,record,rowIndex,colIndex,store){
										var province = record.data.province;
										var city = record.data.city;
										var str = "";
										if(province!=''&&province!=null){
											str+=province.provinceName;
											if(city!=''&&city!=null){
												if(city.cityName!=province.provinceName){
													str+="-"+city.cityName;
												}
											}
										}else if(city!=''&&city!=null){
											str+=city.cityName;
										}
										return str;
								}
						},{
								header : '是否删除',
								dataIndex : 'delFlag',
								renderer : function(value){
									if(value == 0){
										return '可用'
									}else if(value == 1){
										return '不可用'
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
								text : '添加客户信息',
								hidden:!isGranted('_CustomerNewAdd'),
								xtype : 'button',
								handler : this.createRecord
							}, {
								iconCls : 'btn-del',
								text : '删除客户信息',
								hidden:!isGranted('_CustomerNewDel'),
								xtype : 'button',
								handler : this.delRecords,
								scope : this
							}, {
								iconCls : 'btn-edit',
								text : '更新关联省市',
//								hidden:!isGranted('_updateCity'),
								hidden:true,
								xtype : 'button',
								handler : this.updateCity,
								scope : this
							}]
				});

		this.gridPanel = new Ext.grid.GridPanel({
					id : 'CustomerNewGrid',
					region : 'center',
					stripeRows : true,
					tbar : this.topbar,
					store : this.store,
					trackMouseOver : true,
					disableSelection : false,
					loadMask : true,
					cm : cm,
					sm : sm,
					plugins : this.rowActions,
					viewConfig : {
						forceFit : true,
						autoFill : true // 自动填充
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
						if(isGranted('_CustomerNewEdit')){
						 new CustomerNewForm({
											id : rec.data.id
										}).show();}
								
							});
				});
		this.rowActions.on('action', this.onRowAction, this);
		this.mainPanel = new Ext.Panel({
		            region:'center',
		            title:'客户列表',
		            layout:'border',
		            items: [this.searchPanel,this.gridPanel]
        	});
	},// end of the initComponents()

	/**
	 * 
	 * @param {}
	 *            self 当前窗体对象
	 */
	search : function(self) {
		if(self.searchPanel.getForm().isValid()){//如果合法
			var params = self.gridPanel.getStore().baseParams; 
            Ext.apply(params,self.searchPanel.getForm().getValues());
            self.gridPanel.getStore().load(params);
		}
	},

	/**
	 * 添加记录
	 */
	createRecord : function() {
		new CustomerNewForm().show();
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
									+ '/customer/multiDelCustomerNew.do',
							params : {
								ids : ids
							},
							method : 'POST',
							success : function(response, options) {
								Ext.ux.Toast.msg('操作信息', '成功删除该记录！');
								Ext.getCmp('CustomerNewGrid').getStore()
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
		var gridPanel = Ext.getCmp('CustomerNewGrid');
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
		new CustomerNewForm({
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
			default :
				break;
		}
	},
	updateCity:function(){
		Ext.Ajax.request({
							url : __ctxPath
									+ '/customer/updateCityCustomerNew.do',
							method : 'POST',
							success : function(response, options) {
								Ext.ux.Toast.msg('操作信息', '操作成功！');
								Ext.getCmp('CustomerNewGrid').getStore()
										.reload();
							},
							failure : function(response, options) {
								Ext.ux.Toast.msg('操作信息', '操作出错，请联系管理员！');
							}
						});
	}
});
