/**
 * @author:
 * @class ExpressApplyView
 * @extends Ext.Panel
 * @description [ExpressApply]管理
 * @company 
 * @createtime:2010-07-19
 */
ExpressApplyView=Ext.extend(Ext.Panel,{
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
			ExpressApplyView.superclass.constructor.call(this,{
				id:'ExpressApplyView',
				title:'快递管理',
				iconCls : 'menu-express',
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
			height:40,
			frame:false,
			defaults:{
				border:false,
				anchor:'98%,98%'
			},
		    items : [	
				{
					style : 'margin-top:3px;',
					xtype : 'label',
					text : '请输入查询条件:'
				},
				{
					style : 'margin-top:3px;margin-left:5px;',
					xtype : 'label',
					text : '申请人:'
				},{
					name:'Q_applyer_S_LK',
					xtype:'textfield',
					width:75
				},
				
				{
					style : 'margin-top:3px;margin-left:5px;',
					xtype : 'label',
					text : '申请日期:'
				},{
					name:'Q_applyDate_S_LK',
					xtype:'textfield',
					width:75
				},
				{
					style : 'margin-top:3px;margin-left:5px;',
					xtype : 'label',
					text : '申请类型:'
				},{
					name:'Q_expressType_S_LK',
					xtype:'textfield',
					width:75
				},				
				{
					style : 'margin-top:3px;margin-left:5px;',
					xtype : 'label',
					text : '目的单位:'
				},{
					name:'Q_expressName_S_LK',
					xtype:'textfield',
					width:120
				},
			    {
					style : 'margin-top:3px;margin-left:5px;',
					xtype : 'label',
					text : '快递单号:'
				},{
					name:'Q_expressNo_S_LK',
					xtype:'textfield',
					width:75
				},{
					style : 'margin-top:3px;margin-left:5px;',
					xtype : 'label',
					text : '区域:'
				},{
					name:'Q_projectNew.area_S_LK',
					xtype:'textfield',
					width:75
				},{
					style : 'margin-top:3px;margin-left:5px;',
					xtype : 'label',
					text : '部门:'
				},{
					name:'Q_dept.depName_S_LK',
					xtype:'textfield',
					width:75
				},
				{
					xtype : 'button',
					text : '查询',
					iconCls : 'search',
					style : 'margin-left:5px;',
					handler : this.search.createCallback(this)
				}]
		});//end of the searchPanel
		
		//加载数据至store
		this.store = new Ext.data.JsonStore({
							url : __ctxPath+"/admin/listExpressApply.do",
							root : 'result',
							totalProperty : 'totalCounts',
							remoteSort : true,
							fields : [{name : 'id',type:'int'},'applyer'
									,'applyerId'
									,'applyDate'
									,'expressType'
									,'province'
									,'city'
									,'toWhereName'
									,'expressName'
									,'expressNo'
									,'processRunId','projectNew','dept']
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
			},
			{
				 iconCls:'btn-edit'
				,qtip:'编辑'
				,style:'margin:0 3px 0 3px'
			},
			{
				 iconCls:'btn-flowView'
				,qtip:'查看审批表单'
				,style:'margin:0 3px 0 3px'
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
										header : '申请人',	
										dataIndex : 'applyer'
						}
						,{
										header : '申请日期',	
										dataIndex : 'applyDate',
										renderer:function(value){
											return value.substring(0,10);
										}
						}
						,{
										header : '申请类型',	
										dataIndex : 'expressType',
										renderer:function(value){
											if(value==1){
												return '客户';											
											}else if(value==2){
												return '供应商';	
											}else if(value==3){
												return '其他';
											} 
										}
						}						
						,{
										header : '目的地城市',	
										dataIndex : 'city'
						}
						,{
										header : '目的单位',	
										dataIndex : 'toWhereName'
						},{
										header : '分摊对象',	
										renderer:function(value,metadata,record){
											var str = "";
											if(!Ext.isEmpty(record.data.dept)){
												str = "部门";
											}else if(!Ext.isEmpty(record.data.projectNew)){
												str = "项目";
											}
											return str;
										}
						},{
										header : '部门',	
										dataIndex : 'dept',
										renderer:function(value){
											if(!Ext.isEmpty(value)){
												return value.depName;
											}
										}
						},{
							header : '区域',
							dataIndex : 'projectNew',
							renderer:function(value){
									if(!Ext.isEmpty(value)){
										return value.area;
									}
								}
							
						},{
										header : '项目',	
										dataIndex : 'projectNew',
										renderer:function(value){
											if(!Ext.isEmpty(value)){
												return value.proName;
											}
										}
						}
						,{
										header : '快递公司',	
										dataIndex : 'expressName'
						}
						,{
										header : '快递单号',	
										dataIndex : 'expressNo'
						}, this.rowActions],
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
							text : '添加快递信息',
							xtype : 'button',
							handler:this.createRecord
						}, {
							iconCls : 'btn-del',
							text : '删除快递信息',
							xtype : 'button',
							handler :this.delRecords,
							scope: this
						},{
							iconCls : 'btn-xls',
							text : '导出',
							xtype : 'button',
							handler :this.toExllist.createCallback(this),
							scope: this
						}]
			});
			
		this.gridPanel=new Ext.grid.GridPanel({
				id : 'ExpressApplyGrid',
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
						new ExpressApplyForm({id:rec.data.id}).show();
				});
			});		
			this.rowActions.on('action', this.onRowAction, this);
	},//end of the initComponents()
	
	/**
	 * 
	 * @param {} self 当前窗体对象
	 */
	search:function(self){
		if(self.searchPanel.getForm().isValid()){//如果合法
				self.searchPanel.getForm().submit({
					waitMsg:'正在提交查询',
					url:__ctxPath+'/admin/listExpressApply.do',
					success:function(formPanel,action){
			            var result=Ext.util.JSON.decode(action.response.responseText);
			            var params = self.gridPanel.getStore().baseParams; 
		            	Ext.apply(params,self.searchPanel.getForm().getValues());
			            self.gridPanel.getStore().loadData(result);
					}
			});
		}
	},
	toExllist:function(self){
		var p = {"dir":"DESC","limit":"99999","sort":"id","start":"0"};
		var params = self.gridPanel.getStore().baseParams; 
    	Ext.apply(params,self.searchPanel.getForm().getValues());
    	Ext.apply(params,p);
    	var jsonStr = Ext.util.JSON.encode(params);
		var exportIframe = document.createElement('iframe');
		exportIframe.src = __ctxPath+'/admin/toExllistExpressApply.do?jsonStr='+jsonStr;
		exportIframe.style.display = "none";
		document.body.appendChild(exportIframe);
	},
	/**
	 * 添加记录
	 */
	createRecord:function(){
		new ExpressApplyForm().show();
	},
	/**
	 * 按IDS删除记录
	 * @param {} ids
	 */
	delByIds:function(ids){
		Ext.Msg.confirm('信息确认','您确认要删除所选记录吗？',function(btn){
			if(btn=='yes'){
				Ext.Ajax.request({
								url:__ctxPath+'/admin/multiDelExpressApply.do',
								params:{ids:ids},
								method:'POST',
								success:function(response,options){
									Ext.ux.Toast.msg('操作信息','成功删除该信息！');
									Ext.getCmp('ExpressApplyGrid').getStore().reload();
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
		var gridPanel=Ext.getCmp('ExpressApplyGrid');
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
		new ExpressApplyForm({id:record.data.id}).show();
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
			case 'btn-flowView':
				AppUtil.selectProcessForm(record.data.processRunId);
				break;
			default:
				break;
		}
	}
	
});
