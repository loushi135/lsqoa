Ext.ns('BookBorrowForPublicView');
/**
 * 档案借阅列表
 */
var BookBorrowForPublicView = function() {
	return new Ext.Panel({
		id : 'BookBorrowForPublicView',
		title : '档案借阅列表',
		iconCls : 'menu-book-borrow',
		autoScroll : true,
		items : [new Ext.FormPanel({
			height : 35,
			frame : true,
			id : 'BookBorrowForPublicSearchForm',
			layout : 'column',
			defaults : {
				xtype : 'label'
			},
			items : [{
				text : '请输入查询条件:'
			}, {
				text : '借出档案名称'
			}, {
				xtype : 'textfield',
				name : 'Q_bookName_S_LK'
			}, {
				text : '借出档案的ISBN'
			}, {
				xtype : 'textfield',
				name : 'Q_borrowIsbn_S_LK'
			}, {
				xtype : 'button',
				text : '查询',
				iconCls : 'search',
				handler : function() {
					var searchPanel = Ext.getCmp('BookBorrowForPublicSearchForm');
					var grid = Ext.getCmp('BookBorrowForPublicGrid');
					if (searchPanel.getForm().isValid()) {
						searchPanel.getForm().submit({
							waitMsg : '正在提交查询',
							url : __ctxPath + '/admin/listForPublicBookBorRet.do',
							success : function(formPanel, action) {
								var result = Ext.util.JSON.decode(action.response.responseText);
								var params = grid.getStore().baseParams; 
			            		Ext.apply(params,searchPanel.getForm().getValues());
								grid.getStore().loadData(result);
							}
						});
					}

				}
			}]
		}), this.setup()]
	});
};
/**
 * 建立视图
 */
BookBorrowForPublicView.prototype.setup = function() {
	return this.grid();
};
/**
 * 建立DataGrid
 */
BookBorrowForPublicView.prototype.grid = function() {
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel({
		columns : [sm, new Ext.grid.RowNumberer(), {
			header : 'recordId',
			dataIndex : 'recordId',
			hidden : true
		}, {
			header : '登记人',
			dataIndex : 'registerName'
		}, {
			header : '借阅人',
			dataIndex : 'fullname'
		}, {
			header : '借出档案名称',
			dataIndex : 'bookName'
		},{
			header : '借出时间',
			dataIndex : 'borrowTime'
		}, {
			header : '应还时间',
			dataIndex : 'returnTime',
			renderer : function(value) {
				return value.substring(0, 10);
			}
		},{
			header : '借出数量',
			dataIndex : 'boorowAmount'
		}, {
			header : '附件',
			dataIndex : 'filePath',
			width : 60,
			renderer : function(value, metadata, record, rowIndex,
					colIndex) {
				if(record.data.status=='已确认'){
					if (value == '' || value == '0' || value == null) {
						return '无附件';
					} else {
						return "<a href='#'  class='attachment'></a>";
					}
				}else{
					return '无权限查看';
				}
				
			}
		},
		{
			header : '附件名称',
			dataIndex : 'fileName',
			hidden : true
		}, {
			header : '状态',
			dataIndex : 'status'
		}],
		defaults : {
			sortable : true,
			menuDisabled : false,
			width : 100
		}
	});

	var store = this.store();
	store.load({
		params : {
			start : 0,
			limit : 25
		}
	});
	var grid = new Ext.grid.GridPanel({
		id : 'BookBorrowForPublicGrid',
		tbar : this.topbar(),
		store : store,
		trackMouseOver : true,
		disableSelection : false,
		loadMask : true,
		autoHeight : true,
		cm : cm,
		sm : sm,
		viewConfig : {
			forceFit : true,
			enableRowBody : false,
			showPreview : false
		},
		bbar : new Ext.PagingToolbar({
			pageSize : 25,
			store : store,
			plugins : [new Ext.ux.PageSizePlugin()], 
			displayInfo : true,
			displayMsg : '当前显示从{0}至{1}， 共{2}条记录',
			emptyMsg : "当前没有记录"
		})
	});
	
	
	
	grid.addListener('cellclick', function(grid, rowIndex, columnIndex, e) {

		if (columnIndex == 8) {
			var record = grid.getStore().getAt(rowIndex);
			if (null != record && record.data.filePath != null) {
				var files = record.data.filePath.split(";");
				var names = null;
				if(record.data.fileName != null){
					names = record.data.fileName.split(";");
				}

				if (files != "" && files.length > 0) {

					var filesMenu = new Ext.menu.Menu();

					Ext.each(files, function(item, i) {
						if (item.length > 10) {
							var fileLinkName = (i+1);
							if(names != null && names.length == files.length){
								fileLinkName = names[i];
							}
							filesMenu.addItem({
								text : "附件" + fileLinkName,
								handler : function() {
									window.open((__ctxPath + "/attachFiles/"+item), "big", "");
								}

							});
						}
					});
					filesMenu.showAt(e.getXY());
				}

			}
		}
	});
	return grid;

};

/**
 * 初始化数据
 */
BookBorrowForPublicView.prototype.store = function() {
	var store = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : __ctxPath + '/admin/listForPublicBookBorRet.do'
		}),
		reader : new Ext.data.JsonReader({
			root : 'result',
			totalProperty : 'totalCounts',
			id : 'id',
			fields : [{
				name : 'recordId',
				type : 'int'
			}, {
				name : 'bookSnId',
				mapping : 'bookSn.bookSnId'
			}, 'borrowTime', 'returnTime', 'lastReturnTime', 'borrowIsbn',
					'bookName', 'registerName', 'fullname','status',{name:'filePath',mapping:'bookSn.book.filePath'},{name:'fileName',mapping:'bookSn.book.fileName'},'boorowAmount']
		}),
		remoteSort : true
	});
	store.setDefaultSort('recordId', 'desc');
	return store;
};

/**
 * 建立操作的Toolbar
 */
BookBorrowForPublicView.prototype.topbar = function() {
	var toolbar = new Ext.Toolbar({
		id : 'BookBorrowForPublicFootBar',
		height : 30,
		bodyStyle : 'text-align:left',
		items : []
	});
//	if (isGranted('_BookBorrowConfirm')) {
//		toolbar.add(new Ext.Button({
//			iconCls : 'menu-goods-apply',
//			text : '领用确认',
//			handler : function() {
//				BookBorrowForPublicView.confirm()
//			}
//		}));
//	}
	return toolbar;
};

BookBorrowForPublicView.confirm = function() {
	var gridPanel=Ext.getCmp('BookBorrowForPublicGrid');
	var selectRecords = gridPanel.getSelectionModel().getSelections();
	if (selectRecords.length == 0) {
		Ext.ux.Toast.msg("信息", "请选择要确认的借阅记录！");
		return;
	}
	var ids = Array();
	for (var i = 0; i < selectRecords.length; i++) {
		if(selectRecords[i].data.status != '待领用确认'){
			Ext.ux.Toast.msg("信息", "请选择状态为'待领用确认'的记录！");
			return;
		}
		ids.push(selectRecords[i].data.recordId);
	}
	new BookBorrowConfirmWin(ids);
};
