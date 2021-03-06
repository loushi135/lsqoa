/**
 * @author:
 * @class MaterialBaseDataView
 * @extends Ext.Panel
 * @description [MaterialContract]管理
 * @company 
 * @createtime:2010-07-19
 */
MaterialBaseDataView=Ext.extend(Ext.Panel,{
	//条件搜索Panel
	searchPanel:null,
	//数据展示Panel
	gridPanel:null,
	//GridPanel的数据Store
	store:null,
	//头部工具栏
	topbar:null,
	lastMaterialId:null,
	//构造函数
	constructor:function(_cfg){
			Ext.applyIf(this,_cfg);
			//初始化组件
			this.initUIComponents();
			//调用父类构造
			MaterialBaseDataView.superclass.constructor.call(this,{
				id:'MaterialBaseDataView',
				title:'材料发包合同基数',
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
			height : 50,
			frame:true,
			defaults:{
				border:false,
				anchor:'98%,98%'
			},
		    items : [	
				{
					style : 'margin-top:3px;',
					xtype : 'label',
					text : '请输入查询条件:'
				},{
					style : 'margin-top:3px;margin-left:5px;',
					xtype : 'label',
					text : '合同编号:'
				},{
					name:'Q_contractNo_S_LK',
					xtype:'textfield',
					width:90
				},{
					style : 'margin-top:3px;margin-left:5px;',
					xtype : 'label',
					text : '项目名称:'
				},{
					name:'Q_proName_S_LK',
					xtype:'textfield',
					width:90
				},{
					style : 'margin-top:3px;margin-left:5px;',
					xtype : 'label',
					text : '项目编号:'
				},{
					name:'Q_proNo_S_LK',
					xtype:'textfield',
					width:90
				},{
						xtype : 'button',
						text : '查询',
						iconCls : 'search',
						handler : this.search.createCallback(this)
					}
				]
		});//end of the searchPanel
		
		//加载数据至store
		this.store = new Ext.data.JsonStore({
							url : __ctxPath+"/statistics/listMaterialContract.do",
							root : 'result',
							totalProperty : 'totalCounts',
							remoteSort : true,
							fields : [{name : 'id',type:'int'},'contractNo','proName','proNo','applyer','dept','contractAmount','chValue','areanum','contractName','mainItem',
							'sysItem','xydw','tel','yfk','jdk','jdkReceive','wgk','jsk','zbj','usefull','contractAttachIDs','contractFile','remark', 'processRunId']
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
			actions:[
			{
				 iconCls:'btn-del'
				,qtip:'删除'
				,style:'margin:0 3px 0 3px',
				hide:!isGranted("_MaterialContractDel")
			},
			{
				 iconCls:'btn-edit'
				,qtip:'编辑'
				,style:'margin:0 3px 0 3px',
				hide:!isGranted("_MaterialContractEdit")
			},{
				iconCls:'btn-flowView',
				qtip:'查看审批表单',
				style:'margin:0 3px 0 3px'
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
					} ,{
						header : '合同编号',	
						dataIndex : 'contractNo'
					},{
						header : '项目名称',	
						dataIndex : 'proName'
					},{
						header : '项目编号',	
						dataIndex : 'proNo'
					},{
						header : '申请人/经办人',	
						dataIndex : 'applyer'
					},{
						header : '申请部门',	
						dataIndex : 'dept'
					},{
						header : '合同总价(数字)',	
						dataIndex : 'contractAmount',
						renderer:function(value){
							if(!Ext.isEmpty(value)){
								return value+"元";
							}
						}
					},{
						header : '合同总价(中文)',	
						dataIndex : 'chValue'
					},{
						header : '数量/面积',	
						dataIndex : 'areanum'
					},{
						header : '合同类别',	
						dataIndex : 'mainItem'
					},{
						header : '系统类别',	
						dataIndex : 'sysItem'
					},{
						header : '签约单位',	
						dataIndex : 'xydw'
					},{
						header : '联系人及电话',	
						dataIndex : 'tel'
					}
//					, this.rowActions
					],
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
				items : [
//					{
//							iconCls : 'assets-type',
//							text : '发票管理',
//							xtype : 'button',
//							hidden:!(isGranted("_BillQuery")||isGranted("_BillAdd")||isGranted("_BillEdit")||isGranted("_BillDel")),
//							handler:this.manageBill,
//							scope: this
//						},
						]
			});
			
		this.gridPanel=new Ext.grid.GridPanel({
				id : 'MaterialContractGrid',
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
							displayInfo : true,
							displayMsg : '当前页记录索引{0}-{1}， 共{2}条记录',
							emptyMsg : "当前没有记录"
				})
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
	manageBill:function(){
		var selectRecords = this.gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要进行发票管理的记录！");
			return;
		}
		if (selectRecords.length > 1) {
			Ext.ux.Toast.msg("信息", "只能选择一条记录进行发票管理！");
			return;
		}
		var record = selectRecords[0];
		AppUtil.addTab('BillView',{materialId:record.data.id})
	},
	/**
	 * 付款基数
	 */
	payBase:function(){
//		var thiz = this;
		var selectRecords = this.gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要进行付款基数管理的记录！");
			return;
		}
		if (selectRecords.length > 1) {
			Ext.ux.Toast.msg("信息", "只能选择一条记录进行付款基数管理！");
			return;
		}
		var record = selectRecords[0];
//		var contentPanel = App.getContentPanel();
//		var paybaseView  = contentPanel.getItem('PaybaseView');
//		if(paybaseView!=null){
//			if(record.data.id==this.lastMaterialId){
//				contentPanel.activate(paybaseView);
//			}else{
//				Ext.Msg.confirm('信息确认','您确认换一条记录进行付款基数管理吗？',function(btn){
//					if(btn=='yes'){
//						contentPanel.remove(paybaseView);
//						paybaseView = new PaybaseView({materialId:record.data.id});
//						contentPanel.add(paybaseView);
//						contentPanel.activate(paybaseView);
//						thiz.lastMaterialId = record.data.id;
//					}
//				});//end of comfirm
//			}
//		}else{
//			paybaseView = new PaybaseView({materialId:record.data.id});
//			contentPanel.add(paybaseView);
//			contentPanel.activate(paybaseView);
//			this.lastMaterialId = record.data.id;
//		}
		AppUtil.addTab('PaybaseView',{materialId:record.data.id})
	},
	/**
	 * 按IDS删除记录
	 * @param {} ids
	 */
	delByIds:function(ids){
		Ext.Msg.confirm('信息确认','您确认要删除所选记录吗？',function(btn){
			if(btn=='yes'){
				Ext.Ajax.request({
								url:__ctxPath+'/statistics/multiDelMaterialContract.do',
								params:{ids:ids},
								method:'POST',
								success:function(response,options){
									Ext.ux.Toast.msg('操作信息','成功删除该[材料发包合同]！');
									Ext.getCmp('MaterialContractGrid').getStore().reload();
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
		var gridPanel=Ext.getCmp('MaterialContractGrid');
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
		new MaterialContractForm({id:record.data.id}).show();
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
