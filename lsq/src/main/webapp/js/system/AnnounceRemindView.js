/**
 * @author:
 * @class AnnounceRemindView
 * @extends Ext.Panel
 * @description [AnnounceRemind]管理
 * @company 
 * @createtime:2010-07-19
 */
AnnounceRemindView=Ext.extend(Ext.Panel,{
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
			AnnounceRemindView.superclass.constructor.call(this,{
				id:'AnnounceRemindView',
				title:'[通告提醒]管理',
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
			height : 60,
			frame:true,
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
					text : '内容:'
				},{
					name:'Q_announce.context_S_LK',
					xtype:'textfield'
				},{
					name:'Q_user.userId_L_EQ',
					xtype:'textfield',
					hidden : true,
					value:__currentUserId
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
							url : __ctxPath+"/system/listAnnounceRemind.do",
							root : 'result',
							totalProperty : 'totalCounts',
							baseParams:{'Q_user.userId_L_EQ':__currentUserId},
							remoteSort : true,
							fields : [{name : 'id',type:'int'}
										,'announce'
										,'user'
										,'flag'
									]
		});
		this.store.setDefaultSort('id', 'desc');
		//加载数据
		this.store.load({params : {
					start : 0,
					limit : 25
		}});
		
		//初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
			columns : [sm, new Ext.grid.RowNumberer(), {
						header : 'id',
						dataIndex : 'id',
						hidden : true
					} 
																																			,{
										header : '内容',	
										dataIndex : 'announce',
										renderer:function(value){
											return value.context
										}
						}
																														,{
										header : '用户',	
										dataIndex : 'user',
										renderer:function(value){
											return value.fullname
										}
						}
																														,{
										header : '不再提示',	
										dataIndex : 'flag',
										renderer:function(value, metadata, record, rowIndex,colIndex){
											 if(value == 1){
									         	return "<img src='images/valid_s.gif' style='cursor:pointer' onClick='AnnounceRemindView.notice("+record.id+")'/>";
								      		 }else{
										      	return "<img src='images/invalid_s.gif' style='cursor:pointer' onClick='AnnounceRemindView.notice("+record.id+")'/>";
									      	 }
										}
						}
						],
				defaults : {
					sortable : true,
					menuDisabled : false,
					width : 100
				}
			});
		//初始化工具栏
		this.gridPanel=new Ext.grid.GridPanel({
				id : 'AnnounceRemindGrid',
				region:'center',
				stripeRows:true,
				store : this.store,
				trackMouseOver : true,
				disableSelection : false,
				loadMask : true,
				autoHeight : true,
				cm : cm,
				sm : sm,
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
//						new AnnounceRemindForm({id:rec.data.id}).show();
				});
			});		
	},//end of the initComponents()
	
	/**
	 * 
	 * @param {} self 当前窗体对象
	 */
	search:function(self){
		if(self.searchPanel.getForm().isValid()){//如果合法
				self.searchPanel.getForm().submit({
					waitMsg:'正在提交查询',
					url:__ctxPath+'/system/listAnnounceRemind.do',
					success:function(formPanel,action){
			            var result=Ext.util.JSON.decode(action.response.responseText);
			            var params = self.gridPanel.getStore().baseParams;
			            Ext.apply(params,self.searchPanel.getForm().getValues());
			            self.gridPanel.getStore().loadData(result);
					}
			});
		}
	},
	
	/**
	 * 编辑记录
	 * @param {} record
	 */
	editRecord:function(record){
		new AnnounceRemindForm({id:record.data.id}).show();
	}
});
AnnounceRemindView.notice = function(id){
	Ext.Ajax.request({
		url:__ctxPath+'/system/noticeAnnounceRemind.do',
		params:{id:id},
		method:'POST',
		success:function(response,options){
			Ext.ux.Toast.msg('操作信息','操作成功！');
			Ext.getCmp('AnnounceRemindGrid').getStore().reload();
		},
		failure:function(response,options){
			Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
		}
	});
}