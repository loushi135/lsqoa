CompanyPhoneBookView = Ext.extend(Ext.Panel,{
	deptTree : null,
	searchPanel : null,
	gridPanel : null,
	store : null,
	constructor : function(_cfg){
		Ext.applyIf(this, _cfg);
		this.initUIComponents();
		CompanyPhoneBookView.superclass.constructor.call(this, {
			id : 'CompanyPhoneBookView',
			title : '公司通讯录',
			region : 'center',
			layout : 'border',
			items : [this.deptTree,{
						region : 'center',
						border : false ,
						layout : 'anchor',
						autoScroll : true,
						items:[
							this.searchPanel,this.gridPanel
						]
					}]
		})
	},
	initUIComponents : function() {
		var thiz = this;
		this.deptTree = new Ext.tree.TreePanel({
			region : 'west',
			id : "CompanyPhoneBookTreePanel",
			title : "部门信息显示",
			collapsible : true,
			split : true,
			height : 800,
			width : 180,
			tbar : new Ext.Toolbar({
				items : [{
							xtype : "button",
							iconCls : "btn-refresh",
							text : "刷新",
							handler : function() {
								thiz.deptTree.root.reload();
							}
						}, {
							xtype : "button",
							text : "展开",
							iconCls : "btn-expand",
							handler : function() {
								thiz.deptTree.expandAll();
							}
						}, {
							xtype : "button",
							text : "收起",
							iconCls : "btn-collapse",
							handler : function() {
								thiz.deptTree.collapseAll();
							}
						}]
			}),
			loader : new Ext.tree.TreeLoader({
				url : __ctxPath + "/system/listDepartment.do"
			}),
			root : new Ext.tree.AsyncTreeNode({
				expanded : true
			}),
			rootVisible : false
		});
		this.deptTree.addListener("click",function(node,e){
			if(node.id == 0){
				thiz.store.baseParams = {
					"Q_department.depId_L_EQ" : '',
					"Q_status_SN_EQ" : 1
				};
			}else{
				thiz.store.baseParams = {
					"Q_department.depId_L_EQ" : node.id,
					"Q_status_SN_EQ" : 1
				};
			}
			thiz.store.reload({
							params : {
								start : 0,
								limit : 25
							}
						});
		});
		this.searchPanel = new Ext.FormPanel({
			region : 'north',
			layout : 'column',
			bodyStyle : 'padding:6px 10px 6px 10px',
			border : false,
			frame : true,
			height:50,
			defaults : {
				border : false,
				anchor : '98%,98%'
			},
			items : [{
						style : 'margin-top:5px;',
						xtype : 'label',
						text : '请输入查询条件:'
					}, {
						style : 'margin-top:5px;margin-left:5px',
						xtype : 'label',
						text : '姓名:'
					}, {
						name : "Q_fullname_S_LK",
						xtype : 'textfield'
					}, {
						xtype : 'button',
						text : '查询',
						iconCls : 'search',
						handler : this.search
								.createCallback(this)
					}]
		});
		this.store = new Ext.data.JsonStore({
			url : __ctxPath + "/system/listAppUser.do",
			root : 'result',
			totalProperty : 'totalCounts',
			remoteSort : true,
			baseParams:{"Q_status_SN_EQ" : 1},
			fields : [
				{name : "userId",type : "int"},
				'fullname',
				'department',
				'phone',
				'fax',
				'mobile',
				'mobile2',
				'address',
				'zip',
				'email',
				'position'
			]
		});
		this.store.setDefaultSort('userId', 'asc');
		// 加载数据
		this.store.load({
			params : {
				start : 0,
				limit : 25
			}
		});
		this.rowActions = new Ext.ux.grid.RowActions({
			header:'管理',
			width:80,
			actions:[
			{
				 iconCls:'search'
				,qtip:'查看'
				,style:'margin:0 3px 0 3px'
			}
			]
		});
		// 初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
			columns : [
				sm,
				new Ext.grid.RowNumberer(),
				{
					header : 'userId',
					dataIndex : 'userId',
					hidden : true
				},{
					header : '姓名',
					dataIndex : 'fullname'
				},{
					header : "部门",
					dataIndex : 'department',
					renderer : function(value) {
						if(value==null){
						  return '';
						}else{
						  return value.depName;
						}
					}
				},{ 
				    header : '职位',
					dataIndex : 'position'
				},{
					header : '直线',
					dataIndex : 'phone'
				},{
					header : '分机',
					dataIndex : 'fax'
				},{
					header : '手机',
					dataIndex : 'mobile'
				},{
					header : '手机2',
					dataIndex : 'mobile2'
				},
//				{
//					header : '家庭住址',
//					dataIndex : 'address'
//				},{
//					header : '邮编',
//					dataIndex : 'zip'
//				},
				{
					header : 'E-mail',
					dataIndex : 'email'
				},
				this.rowActions
			]
		})
		
		this.gridPanel = new Ext.grid.GridPanel({
			id : 'CompanyPhoneBookGrid',
			region : 'center',
			stripeRows : true,
			store : this.store,
			trackMouseOver : true,
			disableSelection : false,
			loadMask : true,
			autoHeight : true,
			cm : cm,
			sm : sm,
			plugins:this.rowActions,
			viewConfig : {
								forceFit : true,
								autoFill : true, // 自动填充
								forceFit : true
							},
			bbar : new Ext.PagingToolbar({
										pageSize : 25,
										store : this.store,
										displayInfo : true,
										plugins : [new Ext.ux.PageSizePlugin()], 
										displayMsg : '当前页记录索引{0}-{1}， 共{2}条记录',
										emptyMsg : "当前没有记录"
									})
		});
		this.rowActions.on('action', this.onRowAction, this);
		this.gridPanel.addListener('rowdblclick', function(grid,
								rowindex, e) {
							grid.getSelectionModel().each(function(rec) {
										new CompanyPhoneBookForm({
													userId : rec.data.userId
												}).show();
									});
						})
	},
	search : function(self) {
		if (self.searchPanel.getForm().isValid()) {// 如果合法
			var params = self.gridPanel.getStore().baseParams;
			Ext.apply(params, self.searchPanel.getForm().getValues());
			self.gridPanel.getStore().load(params);
		}
	},
	editRecord : function(self){
		new CompanyPhoneBookForm({
			userId : self
		}).show();
	},
	onRowAction:function(gridPanel, record, action, row, col) {
		switch(action) {
			case 'search':
				this.editRecord(record.data.userId);
				break;
			default:
				break;
		}
	}
})