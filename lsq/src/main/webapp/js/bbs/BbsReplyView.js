/**
 * @author:
 * @class BbsReplyView
 * @extends Ext.Panel
 * @description [BbsReply]管理
 * @company
 * @createtime:2010-07-19
 */
BbsReplyView = Ext.extend(Ext.Panel, {
	// 条件搜索Panel
	searchPanel : null,
	// 数据展示Panel
	gridPanel : null,
	// GridPanel的数据Store
	store : null,
	// 头部工具栏
	topbar : null,
	replyWin:null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		BbsReplyView.superclass.constructor.call(this, {
					id : 'BbsReplyView',
					title : '回复管理',
					region : 'center',
					layout : 'fit',
					items : [this.gridPanel]
				});
	},// end of constructor
	listeners:{
			close:function(){
				if(this.replyWin!=null){
					this.replyWin.close();
				}
			},
			activate:function(){
				if(this.replyWin!=null){
					this.replyWin.show();
				}
			},
			deactivate:function(){
				if(this.replyWin!=null){
					this.replyWin.hide();
				}
			}
		
	},
	// 初始化组件
	initUIComponents : function() {
		// 初始化搜索条件Panel
		var thiz = this;
		// 加载数据至store
		this.store = new Ext.data.JsonStore({
			url : __ctxPath + "/bbs/listBbsReply.do",
			root : 'result',
			baseParams : {topicId : this.topicId},
			totalProperty : 'totalCounts',
			remoteSort : true,
			fields : ['id','content', 'username', 'photo', 'fullname','accessionTime','publishTime','description','buttonType']
		});
//		this.store.setDefaultSort('id', 'desc');
		// 加载数据
		this.store.load({
					params : {
						start : 0,
						limit : 25
					}
				});
		
		this.rowActions = new Ext.ux.grid.RowActions({
					header : '管理',
					width : 80,
					actions : [{
								iconCls : 'autoHeight',
								qtip : '删除',
								style : 'margin:0 3px 0 3px'
							}
//							, {
//								iconCls : 'btn-edit',
//								qtip : '编辑',
//								style : 'margin:0 3px 0 3px'
//							}
							]
				});

		// 初始化ColumnModel
	//	var sm = new Ext.grid.CheckboxSelectionModel();  new Ext.grid.RowNumberer(),
		var cm = new Ext.grid.ColumnModel({
					columns : [ {
								header : 'id',
								dataIndex : 'id',
								hidden : true
							},{
								header : "标题："+this.title,
								dataIndex : 'replyContent',
								align :'center',
								renderer:this.rendererProperty
							}
							],
					defaults : {
						sortable : true,
						menuDisabled : false,
						width : 100
					}
				});
		// 初始化工具栏
//		this.topbar = new Ext.Toolbar({
//					height : 30,
//					bodyStyle : 'text-align:left',
//					items : [
//						    {
//								iconCls : 'btn-add',
//								text : '回复',
//								xtype : 'button',
//								handler : this.createRecord.createCallback(this)
//							},
//							{
//								iconCls : 'btn-del',
//								text : '删除回复',
//								xtype : 'button',
//								handler : this.delRecords,
//								scope : this
//							}]
//				});

		this.gridPanel = new Ext.grid.GridPanel({
					id : 'BbsReplyGrid',
					region : 'center',
					stripeRows : true,
//					tbar : this.topbar,
					store : this.store,
					trackMouseOver : true,
					height:500,
					disableSelection : false,
					loadMask : true,
					cm : cm,
//					sm : sm,
					plugins : this.rowActions,
					viewConfig:{
					   forceFit: true // 注意不要用autoFill:true,那样设置的话当GridPanel的大小变化（比如你resize了它）时不会自动调整column的宽度
//					   scrollOffset: 0 //不加这个的话，会在grid的最右边有个空白，留作滚动条的位置
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
								new BbsReplyForm({
											id : rec.data.id
										}).show();
							});
				});
		this.rowActions.on('action', this.onRowAction, this);
		
		this.folatReplyWin();
//		this.folatReplyWin();
	},// end of the initComponents()

	/**
	 * 
	 * @param {}
	 *            self 当前窗体对象
	 */
	search : function(self) {
		if (self.searchPanel.getForm().isValid()) {// 如果合法
			self.searchPanel.getForm().submit({
				waitMsg : '正在提交查询',
				url : __ctxPath + '/bbs/listBbsReply.do',
				success : function(formPanel, action) {
					var result = Ext.util.JSON
							.decode(action.response.responseText);
					self.gridPanel.getStore().loadData(result);
				}
			});
		}
	},
	
	createRecord : function(v) {
		var topicId=v.topicId;
		new BbsReplyForm({topicId:topicId}).show();
	},
	rendererProperty:function(value,metadata,record){
   	      var temp= String
				.format(
						'<table width="98%" border="0" cellpadding="0" cellspacing="5" class="italk-row" >' +
							'<tr bgcolor=""><td width="18%" align="center" valign="top" bgcolor="#D6E3F2" >'+
								 '<table width="100%"border="0" cellspacing="0" cellpadding="0">' +
									'<tr><td colspan="2" align="center" valign="top">' +
										'<div id="{8}" class="photo-outter">' +
											'<div class="photo-inner"><img width="142px" height="235px" src="' + __ctxPath + '{0}"></img></div>' +
										'</div>' +
									'</td></tr>' +
									'<tr><td height="25"align="left"><span>用户名:</span></td><td align="left">{1}</td></tr>' +
									'<tr><td height="25"align="left"><span>真实姓名:</span></td><td align="left">{2}</td></tr>' +
									'<tr><td height="25"align="left"><span>注册时间:</span></td><td align="left">{3}</td></tr>' +
								  '</table></td>'+
								  '<td width="74%" align="left" valign="top" bgcolor="">' +
								  '<div class="right-time"><span>发布时间:{4}</span></div>' +
								  '<hr color="#E1DFDA"><div class="content">{5}</div>' +
								  '<div class="descBox">个人签名：{6}</div></td>' +
								  '<td><input type="button" class="button" style="{8}" onclick="BbsReplyView.editReply({7})" value="引用"></input></td>'+
								  '<td><input type="button" class="button1" style="{8}" onclick="BbsReplyView.delReply({7})" value="删除"></input></td>'+
						'</tr></table>',
						record.data.photo, record.data.username,
						record.data.fullname, record.data.accessionTime, 
						record.data.publishTime,
						record.data.content, record.data.description,
						record.data.id,record.data.buttonType);
					    return temp;
					
	},
	/**
	 * 按IDS删除记录
	 * 
	 * 
	 * @param {}
	 *            ids
	 */
	delByIds : function(ids) {
		Ext.Msg.confirm('信息确认', '您确认要删除所选记录吗？', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
									url : __ctxPath
											+ '/bbs/multiDelBbsReply.do',
									params : {
										ids : ids
									},
									method : 'POST',
									success : function(response, options) {
										var respText = Ext.util.JSON
												.decode(response.responseText);
										Ext.ux.Toast.msg('操作信息',
												'成功删除该回复！');
										Ext.getCmp('BbsReplyGrid').getStore()
												.reload();
									},
									failure : function(response, options) {
										Ext.ux.Toast
												.msg('操作信息', '操作出错，请联系管理员！');
									}
								});
					}
				});// end of comfirm
	},
	/**
	 * 删除多条记录
	 */
	delRecords : function() {
		var gridPanel = Ext.getCmp('BbsReplyGrid');
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
		new BbsReplyForm({
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
	folatReplyWin: function(){
		this.replyWin = new Ext.Window({
		        layout:"fit",
		        floating :true,
		        draggable:true,  
		        id : 'win_alert',  
		        animCollapse :true,  
		        plain : true,  
		        closable:false,  
		        width : 52,  
		        height : 25,  
		        minHeight :10,
		        minWidth :20,
		        modal : false,  
		        shadow :true,
		        unstyled :true,
		        x: document.body.clientWidth-55*2-90,  
		        y: document.body.clientHeight/3,  
		        resizable:false
        });
          
        var str = "";  
          
        var panel = new Ext.Panel({
        		border:false,
           		items:[{
								text : '回复',
								width:'100%',
								iconCls : 'btn-reply',
								height:'100%',
								xtype : 'button',
								handler : this.createRecord.createCallback(this)
							}]
       	 })
        this.replyWin.add(panel);  
        this.replyWin.setAnimateTarget(this);  
        this.replyWin.show();  
	},
   setScroll: function(){
   		if(Ext.get("win_alert")!=null){
            if(document.body.clientHeight+document.body.scrollTop<document.body.scrollHeight){  
                Ext.get("win_alert").setY(document.body.clientHeight+document.body.scrollTop-100-14)  
                Ext.get("win_alert").setX(Ext.get("alert").getX()-310)  
            }else{
                Ext.get("win_alert").setY(document.body.scrollHeight-14-100)  
                Ext.get("win_alert").setX(Ext.get("alert").getX()-310)  
            }  
            window.setTimeout(setScroll,800);  
        }  
   }
});
BbsReplyView.editReply=function(v) {
	var replyId=v
		new BbsReplyForm({
			    replyId:replyId
				}).show();
	}  
BbsReplyView.delReply=function(id){
            var replyId=id;
            Ext.Msg.confirm('信息确认', '您确认要删除所选记录吗？', function(btn) {
              if (btn == 'yes') {
  			Ext.Ajax.request({
								url : __ctxPath
										+ '/bbs/multiDelBbsReply.do',
								params : {
									id:replyId
								},
								method : 'POST',
								success : function(response, options) {
									var respText = Ext.util.JSON.decode(response.responseText);
									if(respText.success==true){
									Ext.ux.Toast.msg('操作信息',
											'成功删除该回复！');
									}else{
									Ext.ux.Toast.msg('操作信息',
											'只有发布人才能删除该回复！');
									}
											
									Ext.getCmp('BbsReplyGrid').getStore()
											.reload();
								},
								failure : function(response, options) {
									Ext.ux.Toast
											.msg('操作信息', '操作出错，请联系管理员！');
									}
								});
                               }
								})

}