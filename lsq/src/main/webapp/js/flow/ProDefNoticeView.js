/**
 * @author:
 * @class ProDefNoticeView
 * @extends Ext.Panel
 * @description [ProDefNotice]管理
 * @company 
 * @createtime:2010-07-19
 */
ProDefNoticeView=Ext.extend(Ext.Panel,{
	//条件搜索Panel
	searchPanel:null,
	//数据展示Panel
	gridPanel:null,
	//GridPanel的数据Store
	store:null,
	//头部工具栏
	topbar:null,
	//构造函数
	constructor:function(_cfg){
			Ext.applyIf(this,_cfg);
			//初始化组件
			this.initUIComponents();
			//调用父类构造
			ProDefNoticeView.superclass.constructor.call(this,{
				id:'ProDefNoticeView',
				title:'[ProDefNotice]管理',
				region:'center',
				layout:'border',
				items:[this.searchPanel,this.gridPanel]
			});
	},//end of constructor

	//初始化组件
	initUIComponents:function(){
		//初始化搜索条件Panel
		this.searchPanel=new Ext.FormPanel({
		    layout : 'column',
		    region:'north',
			bodyStyle: 'padding:6px 10px 6px 10px',
			border:false,
			frame:true,
			height : 50,
			defaults:{
				border:false,
				anchor:'98%,98%'
			},
		    items : [	
				{
					style : 'margin-top:5px;',
					xtype : 'label',
					text : '请输入查询条件:'
				},
																						{
					style : 'margin-top:5px;',
					xtype : 'label',
					text : '通知员工ID:'
				},{
					name:'Q_noticeUserIds_S_LK',
					xtype:'textfield'
				},
																			{
					style : 'margin-top:5px;',
					xtype : 'label',
					text : ':'
				},{
					name:'Q_noticeUserNames_S_LK',
					xtype:'textfield'
				},
																			{
					style : 'margin-top:5px;',
					xtype : 'label',
					text : '通知部门ID:'
				},{
					name:'Q_noticeDepIds_S_LK',
					xtype:'textfield'
				},
																			{
					style : 'margin-top:5px;',
					xtype : 'label',
					text : ':'
				},{
					name:'Q_noticeDepNames_S_LK',
					xtype:'textfield'
				},
																			{
					style : 'margin-top:5px;',
					xtype : 'label',
					text : '通知角色ID:'
				},{
					name:'Q_noticeRoleIds_S_LK',
					xtype:'textfield'
				},
																			{
					style : 'margin-top:5px;',
					xtype : 'label',
					text : ':'
				},{
					name:'Q_noticeRoleNames_S_LK',
					xtype:'textfield'
				},
																	{
						xtype : 'button',
						text : '查询',
						iconCls : 'search',
						handler : this.search.createCallback(this)
					}
				]
		});//end of the searchPanel
		
		//加载数据至store
		this.store = new Ext.data.JsonStore({
							url : __ctxPath+"/flow/listProDefNotice.do",
							root : 'result',
							totalProperty : 'totalCounts',
							remoteSort : true,
							fields : [{name : 'defId',type:'int'}
																																																	,'noticeUserIds'
																																										,'noticeUserNames'
																																										,'noticeDepIds'
																																										,'noticeDepNames'
																																										,'noticeRoleIds'
																																										,'noticeRoleNames'
																																				]
		});
		this.store.setDefaultSort('defId', 'desc');
		//加载数据
		this.store.load({params : {
					start : 0,
					limit : 25
		}});
		
		this.rowActions = new Ext.ux.grid.RowActions({
			header:'管理',
			width:80,
			actions:[{
				 iconCls:'btn-del'
				,qtip:'删除'
				,style:'margin:0 3px 0 3px'
				,hide:!isGranted("_ProDefNoticeDel")
			},
			{
				 iconCls:'btn-edit'
				,qtip:'编辑'
				,style:'margin:0 3px 0 3px'
				,hide:!isGranted("_ProDefNoticeEdit")
			}
			]
		});
		
		//初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
			columns : [sm, new Ext.grid.RowNumberer(), {
						header : 'defId',
						dataIndex : 'defId',
						hidden : true
					} 
																																			,{
										header : '通知员工ID',	
										dataIndex : 'noticeUserIds'
						}
																														,{
										header : '',	
										dataIndex : 'noticeUserNames'
						}
																														,{
										header : '通知部门ID',	
										dataIndex : 'noticeDepIds'
						}
																														,{
										header : '',	
										dataIndex : 'noticeDepNames'
						}
																														,{
										header : '通知角色ID',	
										dataIndex : 'noticeRoleIds'
						}
																														,{
										header : '',	
										dataIndex : 'noticeRoleNames'
						}
																									, this.rowActions],
				defaults : {
					sortable : true,
					menuDisabled : false,
					width : 100
				}
			});
		//初始化工具栏
		this.topbar=new Ext.Toolbar({
				height : 30,
				bodyStyle : 'text-align:left',
				items : [{
							iconCls : 'btn-add',
							text : '添加[ProDefNotice]',
							xtype : 'button',
							handler:this.createRecord,
							hidden:!isGranted("_ProDefNoticeAdd")
						}, {
							iconCls : 'btn-del',
							text : '删除[ProDefNotice]',
							xtype : 'button',
							handler :this.delRecords,
							scope: this,
							hidden:!isGranted("_ProDefNoticeDel")
						}]
			});
			
		this.gridPanel=new Ext.grid.GridPanel({
				id : 'ProDefNoticeGrid',
				region:'center',
				stripeRows:true,
				tbar : this.topbar,
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
					autoFill : true, //自动填充
					forceFit : true
					//showPreview : false
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
							new ProDefNoticeForm({defId:rec.data.defId}).show();
				});
			});		
			this.rowActions.on('action', this.onRowAction, this);
	},//end of the initComponents()
	
	/**
	 * 
	 * @param {} self 当前窗体对象
	 */
	search:function(self){
		if (self.searchPanel.getForm().isValid()) {// 如果合法
			var params = self.gridPanel.getStore().baseParams;
			Ext.apply(params, self.searchPanel.getForm().getValues());
			self.gridPanel.getStore().load(params);
		}
	},
	
	/**
	 * 添加记录
	 */
	createRecord:function(){
		new ProDefNoticeForm().show();
	},
	/**
	 * 按IDS删除记录
	 * @param {} ids
	 */
	delByIds:function(ids){
		Ext.Msg.confirm('信息确认','您确认要删除所选记录吗？',function(btn){
			if(btn=='yes'){
				Ext.Ajax.request({
								url:__ctxPath+'/flow/multiDelProDefNotice.do',
								params:{ids:ids},
								method:'POST',
								success:function(response,options){
									Ext.ux.Toast.msg('操作信息','成功删除该[ProDefNotice]！');
									Ext.getCmp('ProDefNoticeGrid').getStore().reload();
								},
								failure:function(response,options){
									Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
								}
							});
			}
		});//end of comfirm
	},
	/**
	 * 删除多条记录
	 */
	delRecords:function(){
		var gridPanel=Ext.getCmp('ProDefNoticeGrid');
		var selectRecords = gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var ids = Array();
		for (var i = 0; i < selectRecords.length; i++) {
			ids.push(selectRecords[i].data.defId);
		}
		this.delByIds(ids);
	},
	
	/**
	 * 编辑记录
	 * @param {} record
	 */
	editRecord:function(record){
		new ProDefNoticeForm({defId:record.data.defId}).show();
	},
	/**
	 * 管理列中的事件处理
	 * @param {} grid
	 * @param {} record
	 * @param {} action
	 * @param {} row
	 * @param {} col
	 */
	onRowAction:function(gridPanel, record, action, row, col) {
		switch(action) {
			case 'btn-del':
				this.delByIds(record.data.defId);
				break;
			case 'btn-edit':
				this.editRecord(record);
				break;
			default:
				break;
		}
	}
});
