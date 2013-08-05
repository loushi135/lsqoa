/**
 * @author:
 * @class ProSubjectDefView
 * @extends Ext.Panel
 * @description [ProSubjectDef]管理
 * @company 
 * @createtime:2010-07-19
 */
ProSubjectDefView=Ext.extend(Ext.Panel,{
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
			ProSubjectDefView.superclass.constructor.call(this,{
				id:'ProSubjectDefView',
				title:'[ProSubjectDef]管理',
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
					text : ':'
				},{
					name:'Q_defId_S_LK',
					xtype:'textfield'
				},
																			{
					style : 'margin-top:5px;',
					xtype : 'label',
					text : ':'
				},{
					name:'Q_refField_S_LK',
					xtype:'textfield'
				},
																			{
					style : 'margin-top:5px;',
					xtype : 'label',
					text : ':'
				},{
					name:'Q_defaultVal_S_LK',
					xtype:'textfield'
				},
																			{
					style : 'margin-top:5px;',
					xtype : 'label',
					text : ':'
				},{
					name:'Q_refLabel_S_LK',
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
							url : __ctxPath+"/flow/listProSubjectDef.do",
							root : 'result',
							totalProperty : 'totalCounts',
							remoteSort : true,
							fields : [{name : 'id',type:'int'}
																																																	,'defId'
																																										,'refField'
																																										,'defaultVal'
																																										,'refLabel'
																																				]
		});
		this.store.setDefaultSort('id', 'desc');
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
				,hide:!isGranted("_ProSubjectDefDel")
			},
			{
				 iconCls:'btn-edit'
				,qtip:'编辑'
				,style:'margin:0 3px 0 3px'
				,hide:!isGranted("_ProSubjectDefEdit")
			}
			]
		});
		
		//初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
			columns : [sm, new Ext.grid.RowNumberer(), {
						header : 'id',
						dataIndex : 'id',
						hidden : true
					} 
																																			,{
										header : '',	
										dataIndex : 'defId'
						}
																														,{
										header : '',	
										dataIndex : 'refField'
						}
																														,{
										header : '',	
										dataIndex : 'defaultVal'
						}
																														,{
										header : '',	
										dataIndex : 'refLabel'
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
							text : '添加[ProSubjectDef]',
							xtype : 'button',
							handler:this.createRecord,
							hidden:!isGranted("_ProSubjectDefAdd")
						}, {
							iconCls : 'btn-del',
							text : '删除[ProSubjectDef]',
							xtype : 'button',
							handler :this.delRecords,
							scope: this,
							hidden:!isGranted("_ProSubjectDefDel")
						}]
			});
			
		this.gridPanel=new Ext.grid.GridPanel({
				id : 'ProSubjectDefGrid',
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
						if(isGranted("_ProSubjectDefEdit")){
							new ProSubjectDefForm({id:rec.data.id}).show();
						}
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
		new ProSubjectDefForm().show();
	},
	/**
	 * 按IDS删除记录
	 * @param {} ids
	 */
	delByIds:function(ids){
		Ext.Msg.confirm('信息确认','您确认要删除所选记录吗？',function(btn){
			if(btn=='yes'){
				Ext.Ajax.request({
								url:__ctxPath+'/flow/multiDelProSubjectDef.do',
								params:{ids:ids},
								method:'POST',
								success:function(response,options){
									Ext.ux.Toast.msg('操作信息','成功删除该[ProSubjectDef]！');
									Ext.getCmp('ProSubjectDefGrid').getStore().reload();
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
		var gridPanel=Ext.getCmp('ProSubjectDefGrid');
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
	 * @param {} record
	 */
	editRecord:function(record){
		new ProSubjectDefForm({id:record.data.id}).show();
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
				this.delByIds(record.data.id);
				break;
			case 'btn-edit':
				this.editRecord(record);
				break;
			default:
				break;
		}
	}
});
