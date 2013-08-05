var NoticeDetail = function(a) {
	return new Ext.Panel({
		title : "公告详情",
		iconCls : "menu-notice",
		id : "NoticeDetail",
		autoScroll : true,
		autoWidth : true,
		tbar : new Ext.Toolbar({
			height : 30,
			bodyStyle : "text-align:left",
			defaultType : "button",
			items : [{
						text : "关闭",
						iconCls : "btn-mail_remove",
						handler : function() {
							var b = Ext.getCmp("centerTabPanel");
							var c = b.getItem("NoticeDetail");
							b.remove(c);
						}
					}, {
						text : "上一条",
						iconCls : "btn-up",
						handler : function() {
							var c = document
									.getElementById("__haveNextNoticeFlag");
							if (c != null && c.value != "endPre") {
								var b = document
										.getElementById("__curNoticeId").value;
								Ext.getCmp("HomeNoticeDetailPanel").load({
									url : __ctxPath
											+ "/pages/info/noticedetail.jsp?opt=_pre&noticeId="
											+ b+"&currentUserId="+__currentUserId
								});
							} else {
								Ext.ux.Toast.msg("提示信息", "这里已经是第一条了.");
							}
						}
					}, {
						text : "下一条",
						iconCls : "btn-last",
						handler : function() {
							var c = document
									.getElementById("__haveNextNoticeFlag");
							if (c != null && c.value != "endNext") {
								var b = document
										.getElementById("__curNoticeId").value;
								Ext.getCmp("HomeNoticeDetailPanel").load({
									url : __ctxPath
											+ "/pages/info/noticedetail.jsp?opt=_next&noticeId="
											+ b+"&currentUserId="+__currentUserId
								});
							} else {
								Ext.ux.Toast.msg("提示信息", "这里已经是最后一条了.");
							}
						}
					}]
		}),
		items : [new Ext.Panel({
					width : '90%',
					id : "HomeNoticeDetailPanel",
					autoScroll : true,
					style : "padding-left:10%;padding-top:10px;",
					autoHeight : true,
					border : false,
					autoLoad : {
						url : __ctxPath
								+ "/pages/info/noticedetail.jsp?noticeId=" + a+"&currentUserId="+__currentUserId
					}
				})]
	});
};

Ext.ns("NoticeViewDetail");
NoticeViewDetail.show = function(noticeId) {
	//加载数据至store
	var store = new Ext.data.JsonStore({
		url : __ctxPath + "/info/listNoticeViewDetail.do",
		root : 'result',
		totalProperty : 'totalCounts',
		baseParams:{'Q_notice.noticeId_L_EQ':noticeId},
		remoteSort : true,
		fields : [{
				name : 'id',
				type : 'int'
			}, 'notice', 'appUser', 'viewDate']
	});
	store.setDefaultSort('id', 'desc');
	//加载数据
	store.load({
		params : {
			start : 0,
			limit : 25
		}
	});
	var cm = new Ext.grid.ColumnModel({
			columns : [new Ext.grid.RowNumberer(), {
					header : 'id',
					dataIndex : 'id',
					hidden : true
				}, {
					header : '公告标题',
					dataIndex : 'notice',
					renderer:function(value,meta,record){
						if(!Ext.isEmpty(value)){
							return value.noticeTitle;
						}
					},
					width:200
				}, {
					header : '查看人',
					dataIndex : 'appUser',
					renderer:function(value,meta,record){
						if(!Ext.isEmpty(value)){
							return value.fullname;
						}
					}
				}, {
					header : '查看时间',
					dataIndex : 'viewDate'
				}],
			defaults : {
				sortable : true,
				menuDisabled : false,
				width : 100
			}
		});
	var gridPanel = new Ext.grid.GridPanel({
//			region : 'center',
			height:300,
			width:580,
			stripeRows : true,
			store : store,
			trackMouseOver : true,
			disableSelection : false,
			loadMask : true,
			cm : cm,
			viewConfig : {
				forceFit : true
//				autoFill : true, //自动填充
//				forceFit : true
				//showPreview : false
			},
			bbar : new Ext.PagingToolbar({
				pageSize : 25,
				store : store,
				plugins : [new Ext.ux.PageSizePlugin()],
				displayInfo : true,
				displayMsg : '当前页记录索引{0}-{1}， 共{2}条记录',
				emptyMsg : "当前没有记录"
			})
		});
	var window = new Ext.Window({
		title : "公告浏览详情",
		iconCls : "menu-notice",
		width : 600,
		height : 365,
		modal : true,
		layout : "form",
		buttonAlign : "center",
		items:[gridPanel],
		buttons : [{
					xtype : "button",
					iconCls : "btn-close",
					text : "关闭",
					handler : function() {
						window.close();
					}
				}]
	});
	window.show();
};