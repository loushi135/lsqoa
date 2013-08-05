/**
 * @author:
 * @class StaffRecruitapplyView
 * @extends Ext.Panel
 * @description [StaffRecruitapply]管理
 * @company 
 * @createtime:2010-07-19
 */
StaffRecruitapplyView=Ext.extend(Ext.Panel,{
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
			StaffRecruitapplyView.superclass.constructor.call(this,{
				id:'StaffRecruitapplyView',
				title:'[员工招聘]管理',
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
				},{
					style : 'margin-top:5px;',
					xtype : 'label',
					text : '部门:'
				},{
					name:'Q_deptId_S_LK',
					xtype:'textfield'
				},{
					style : 'margin-top:5px;',
					xtype : 'label',
					text : '空缺职位:'
				},{
					name:'Q_jobId_S_LK',
					xtype:'textfield'
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
							url : __ctxPath+"/statistics/listStaffRecruitapply.do",
							root : 'result',
							totalProperty : 'totalCounts',
							remoteSort : true,
							fields : [{name : 'id',type:'int'}
									,'companyName'
									,'dept'
									,'job'
									,'currentNum'
									,'applyNum'
									,'applyReason'
									,'age'
									,'sex'
									,'majorDic'
									,'educationDic'
									,'workYear'
									,'positionDuty'
									,'skillRequirement'
									,'workExperience'
									,'personality'
									,'otherPosition'
									,'otherRequirement'
									,'deadline'
									,'mainPositions'
									,'personalCharacter'
									,'processRunId'
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
				,style:'margin:0 3px 0 3px',
				hide:!isGranted("_StaffRecruitapplyDel")
			},
			{
				 iconCls:'btn-edit'
				,qtip:'编辑'
				,style:'margin:0 3px 0 3px',
				hide:!isGranted("_StaffRecruitapplyEdit")
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
					} 
																																			,{
										header : '申请公司',	
										dataIndex : 'companyName'
						}
																														,{
										header : '部门',	
										dataIndex : 'dept',
										renderer : function(value, metadata, record, rowIndex,colIndex) {
												var str='';
												if(!Ext.isEmpty(value)){
													str = value.depName;
												}
												return str;
										}
						}
																														,{
										header : '空缺职位',	
										dataIndex : 'job',
										renderer : function(value, metadata, record, rowIndex,colIndex) {
												var str='';
												if(!Ext.isEmpty(value)){
													str = value.jobName;
												}
												return str;
										}
						}
																														,{
										header : '该职位目前人数',	
										dataIndex : 'currentNum'
						}
																														,{
										header : '此次申请人数',	
										dataIndex : 'applyNum'
						}
																														,{
										header : '申请理由',	
										dataIndex : 'applyReason'
						}
																														,{
										header : '年龄',	
										dataIndex : 'age'
						}
																														,{
										header : '性别',	
										dataIndex : 'sex'
						}
																														,{
										header : '专业',	
										dataIndex : 'majorDic',
										renderer : function(value, metadata, record, rowIndex,colIndex) {
												var str='';
												if(!Ext.isEmpty(value)){
													str = value.itemValue;
												}
												return str;
										}
						}
																														,{
										header : '学历',	
										dataIndex : 'educationDic',
										renderer : function(value, metadata, record, rowIndex,colIndex) {
												var str='';
												if(!Ext.isEmpty(value)){
													str = value.itemValue;
												}
												return str;
										}
						}
																														,{
										header : '工作年限',	
										dataIndex : 'workYear'
						}
																														,{
										header : '该职位的主要职责',	
										dataIndex : 'positionDuty'
						}
																														,{
										header : '技能要求',	
										dataIndex : 'skillRequirement'
						}
																														,{
										header : '工作经历',	
										dataIndex : 'workExperience'
						}
																														,{
										header : '个性',	
										dataIndex : 'personality'
						}
																														,{
										header : '主要工作地点',	
										dataIndex : 'mainPositions'
						}
																														,{
										header : '其他',	
										dataIndex : 'otherPosition'
						}
																														,{
										header : '其他要求',	
										dataIndex : 'otherRequirement'
						}
																														,{
										header : '到岗期限',	
										dataIndex : 'deadline'
						}
																														,{
										header : '人员性质',	
										dataIndex : 'personalCharacter'
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
							text : '添加[员工招聘]',
							xtype : 'button',
							handler:this.createRecord,
							hidden:!isGranted("_StaffRecruitapplyAdd")
						}, {
							iconCls : 'btn-del',
							text : '删除[员工招聘]',
							xtype : 'button',
							handler :this.delRecords,
							hidden:!isGranted("_StaffRecruitapplyDel"),
							scope: this
						}]
			});
			
		this.gridPanel=new Ext.grid.GridPanel({
				id : 'StaffRecruitapplyGrid',
				region:'center',
				stripeRows:true,
				tbar : this.topbar,
				store : this.store,
				trackMouseOver : true,
				disableSelection : false,
				autoScroll : true,
				loadMask : true,
				height : 100,
				cm : cm,
				sm : sm,
				plugins : this.rowActions,
				viewConfig : {
					forceFit : false
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
					if(isGranted("_StaffRecruitapplyEdit")){
						new StaffRecruitapplyForm({id:rec.data.id}).show();
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
		new StaffRecruitapplyForm().show();
	},
	/**
	 * 按IDS删除记录
	 * @param {} ids
	 */
	delByIds:function(ids){
		Ext.Msg.confirm('信息确认','您确认要删除所选记录吗？',function(btn){
			if(btn=='yes'){
				Ext.Ajax.request({
								url:__ctxPath+'/statistics/multiDelStaffRecruitapply.do',
								params:{ids:ids},
								method:'POST',
								success:function(response,options){
									Ext.ux.Toast.msg('操作信息','成功删除该[员工招聘]！');
									Ext.getCmp('StaffRecruitapplyGrid').getStore().reload();
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
		var gridPanel=Ext.getCmp('StaffRecruitapplyGrid');
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
		new StaffRecruitapplyForm({id:record.data.id}).show();
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
