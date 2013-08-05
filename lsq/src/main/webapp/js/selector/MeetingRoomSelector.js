/**
 * 会议室选择器
 */
var MeetingRoomSelector = {
	/**
	 * @param callback
	 *            回调函数
	 * @param isSingle
	 *            是否单选
	 */
	getView : function(callback, isSingle,SEARCH_startTime,SEARCH_endTime) {
		// ---------------------------------start grid
		// panel--------------------------------
		var sm = null;
		if (isSingle) {
			var sm = new Ext.grid.CheckboxSelectionModel({
						singleSelect : true
					});		} else {
			sm = new Ext.grid.CheckboxSelectionModel();
		}
		if(Ext.isEmpty(SEARCH_startTime)){
			SEARCH_startTime = new Date().add(Date.MINUTE, 1).format('Y-m-d H:i');
		}else{
			SEARCH_startTime = Date.parseDate(SEARCH_startTime,'Y-m-d H:i:s').format('Y-m-d H:i');
		}
		if(Ext.isEmpty(SEARCH_endTime)){
			SEARCH_endTime = new Date().add(Date.HOUR, 1).format('Y-m-d H:i');
		}else{
			SEARCH_endTime = Date.parseDate(SEARCH_endTime,'Y-m-d H:i:s').format('Y-m-d H:i');
		}
    	var expander = new Ext.grid.RowExpander({
			tpl:new Ext.XTemplate('<div width=100%  style=margin-left:70px; >',  
                    '<table width=100%  cellspacing="0" cellpadding="0" border="0" >',  
                    '<tpl for="meetingList">',  
                    '<tr><td style=margin-left:70px;>占用时间</td><td style="margin-left:70px;">{startTime}至{entTime}</td> </tr>',  
                    '</tpl>',
                    '<tpl if="this.isEmpty(meetingList)"><td style=margin-left:70px;>无</td></tpl>',
                    '</table>','</div>',{isEmpty:function(list){return list == ''}}),
			// 设置行双击是否展开
//			expandOnDblClick : false,
			lazyRender : true, 
			enableCaching : false
		});
    	
		var cm = new Ext.grid.ColumnModel({
					columns : [sm, new Ext.grid.RowNumberer(), {
								header : 'id',
								dataIndex : 'id',
								hidden : true
							},expander,
							{
								header : "会议室名称",
								dataIndex : 'name',
								width : 60
							}, {
								id : 'status',
								header : "会议室状态",
								dataIndex : 'status',
								width : 60,
								renderer : function(value) {
									if (value == '0') {
										return "<font color='green'>可用</font>";
									}
									if (value == '1') {
										return "<font color='red'>占用</font>";
									}
									if (value == '2') {
										return "<font color='red'>未知</font>";
									}
								}
							}]
				});

		var store = new Ext.data.Store({
					proxy : new Ext.data.HttpProxy({
								url : __ctxPath + '/admin/listMeetingRoom.do'
							}),
					reader : new Ext.data.JsonReader({
								root : 'result',
								totalProperty : 'totalCounts',
								id : 'carId',
								fields : ['id', 'name', 'status','meetingList']
							}),
					remoteSort : true
				});

		var gridPanel = new Ext.grid.GridPanel({
					id : 'MeetingRoomSelectorGrid',
					width : 400,
					height : 300,
					region : 'center',
					title : '会议室列表',
					store : store,
					shim : true,
					trackMouseOver : true,
					disableSelection : false,
					loadMask : true,
					cm : cm,
					sm : sm,
					plugins: expander,
					viewConfig : {
						forceFit : true,
						enableRowBody : false,
						showPreview : false
					},
					// paging bar on the bottom
					bbar : new Ext.PagingToolbar({
								pageSize : 25,
								store : store,
								plugins : [new Ext.ux.PageSizePlugin()], 
								displayInfo : true,
								displayMsg : '当前显示从{0}至{1}， 共{2}条记录',
								emptyMsg : "当前没有记录"
							})
				});

		store.load({
					params : {
						start : 0,
						limit : 10,
						'SEARCH_startTime':SEARCH_startTime,
						'SEARCH_endTime':SEARCH_endTime
					}
				});
		// --------------------------------end grid
		// panel-------------------------------------

		var formPanel = new Ext.FormPanel({
			width : 400,
			height : 60,
			region : 'north',
			frame : true,
			id : 'MeetingRoomSearchForm',
			layout : 'column',
			defaults : {
				xtype : 'label'
			},
			items : [{
						xtype : 'label',
						text : '开始时间:'
					}, {
						xtype : 'datetimefield',
						name : 'SEARCH_startTime',
						id:'SEARCH_startTime',
						editable : false,
						format : 'Y-m-d H:i',
						emptyText : '---请选择日期---',
						allowBlank : false,
						width : 200,
						value:SEARCH_startTime,
						minValue :new Date()
					}, {
						xtype : 'label',
						text : '结束时间:'
					}, {
						xtype : 'datetimefield',
						name : 'SEARCH_endTime',
						id:'SEARCH_endTime',
						editable : false,
						format : 'Y-m-d H:i',
						emptyText : '---请选择日期---',
						allowBlank : false,
						width : 200,
						value:SEARCH_endTime,
						minValue :new Date()
					}, {
						xtype : 'button',
						text : '查询',
						iconCls : 'search',
						handler : function() {
							var searchPanel = Ext
									.getCmp('MeetingRoomSearchForm');
							var grid = Ext.getCmp('MeetingRoomSelectorGrid');
							if (searchPanel.getForm().isValid()) {
								searchPanel.getForm().submit({
									waitMsg : '正在提交查询',
									url : __ctxPath
											+ '/admin/listMeetingRoom.do',
									params : {
										start : 0,
										limit : 10
									},
									method : 'post',
									success : function(formPanel, action) {
										var result = Ext.util.JSON
												.decode(action.response.responseText);
										grid.getStore().loadData(result);
									}
								});
							}
						}
					}, {
						xtype : 'label',
						style : 'font-size:13px;color:red', 
						text : '请通过查询获取申请时间段内会议室是否空闲'
					}]
		});
		
		var window = new Ext.Window({
			title : '会议室选择',
			iconCls : 'menu-car',
			width : 630,
			height : 380,
			layout : 'border',
			border : false,
			items : [formPanel, gridPanel],
			modal : true,
			buttonAlign : 'center',
			buttons : [{
						iconCls : 'btn-ok',
						text : '确定',
						handler : function() {
							var grid = Ext.getCmp('MeetingRoomSelectorGrid');
							var rows = grid.getSelectionModel().getSelections();
							var ids = '';
							var names = '';
							var statuss = '';
							var beginTimes = '';
							var endTimes = '';
							if(rows.length!=0){
								if(Ext.getCmp("SEARCH_startTime").getValue()==''||Ext.getCmp("SEARCH_endTime").getValue()==''){
									Ext.MessageBox.alert('错误','请先通过申请时间段查询会议室，并选择状态为可用的会议室');
									return;
								}
							}else{
								Ext.MessageBox.alert('提示','请选择一个状态为可用的会议室');
								return;
							}
							for (var i = 0; i < rows.length; i++) {
								if (i > 0) {
									ids += ',';
									names += ',';
									statuss += ',';
									beginTimes += ',';
									endTimes += ',';
								}
								ids += rows[i].data.id;
								names += rows[i].data.name;
								statuss += rows[i].data.status;
								beginTimes += Ext.getCmp("SEARCH_startTime").getValue().format('Y-m-d H:i:s');
								endTimes += Ext.getCmp("SEARCH_endTime").getValue().format('Y-m-d H:i:s');
							}
							
							if(statuss!=0){
								Ext.MessageBox.alert('错误','请先通过申请时间段查询会议室，并选择状态为可用的会议室');
							}else{
								if (callback != null) {
									callback.call(this, ids, names, statuss,beginTimes,endTimes);
								}
								window.close();
							}
						}
					}, {
						text : '取消',
						iconCls : 'btn-cancel',
						handler : function() {
							window.close();
						}
					}]
		});
		return window;
	}
};