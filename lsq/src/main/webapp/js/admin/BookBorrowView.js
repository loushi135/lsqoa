Ext.ns('BookBorrowView');
/**
 * 档案借阅列表
 */
var BookBorrowView = function() {
	return new Ext.Panel({
		id : 'BookBorrowView',
		title : '档案借阅列表',
		iconCls : 'menu-book-borrow',
		autoScroll : true,
		items : [new Ext.FormPanel({
			height : 35,
			frame : true,
			id : 'BookBorrowSearchForm',
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
					var searchPanel = Ext.getCmp('BookBorrowSearchForm');
					var grid = Ext.getCmp('BookBorrowGrid');
					if (searchPanel.getForm().isValid()) {
						searchPanel.getForm().submit({
							waitMsg : '正在提交查询',
							url : __ctxPath + '/admin/listBookBorRet.do',
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
BookBorrowView.prototype.setup = function() {
	return this.grid();
};
/**
 * 建立DataGrid
 */
BookBorrowView.prototype.grid = function() {
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
				return '<a style="color:red;" title="注意应还时间！">'+value.substring(0, 10)+'</a>';
			}
		},{
			header : '借出数量',
			dataIndex : 'boorowAmount'
		},  {
			header : '状态',
			dataIndex : 'status'
		}, {
			header : '管理',
			dataIndex : 'recordId',
			sortable : false,
			width : 50,
			renderer : function(value, metadata, record, rowIndex, colIndex) {
				var editId = record.data.recordId;
				var processRunId=record.data.processRunId;
				var str = '';
//				if (isGranted('_BookBorrowEdit')) {
//					str = '<button title="编辑" value=" " class="btn-edit" onclick="BookBorrowView.edit('
//							+ editId + ')"></button>';
//				}
//				if (isGranted('_BookBorrowConfirm')) {
//					str += '<button title="确认" value=" " class="btn-ok" onclick="BookBorrowView.confirm()"></button>';
//				}
				if (isGranted('_BookReturn')) {
					str += '<button title="归还" value=" " class="menu-book-return" onclick="BookBorrowView.returnB('
							+ editId + ')"></button>&nbsp;';
				}
				str += '<button title="查看审批表单" value=" " class="btn-flowView" onclick="AppUtil.selectProcessForm('
							+ processRunId + ')"></button>';
				return str;
			}
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
		id : 'BookBorrowGrid',
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

//	grid.addListener('rowdblclick', function(grid, rowindex, e) {
//		grid.getSelectionModel().each(function(rec) {
//			if (isGranted('_BookBorrowEdit')) {
//				BookBorrowView.edit(rec.data.recordId);
//			}
//		});
//	});
	return grid;

};

/**
 * 初始化数据
 */
BookBorrowView.prototype.store = function() {
	var store = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : __ctxPath + '/admin/listBorrowBookBorRet.do'
		}),
		reader : new Ext.data.JsonReader({
			root : 'result',
			totalProperty : 'totalCounts',
			id : 'id',
			fields : [{
				name : 'recordId',
				type : 'int'
			}

			, {
				name : 'bookSnId',
				mapping : 'bookSn.bookSnId'
			}, 'borrowTime', 'returnTime', 'lastReturnTime', 'borrowIsbn',
					'bookName', 'registerName', 'fullname','status','processRunId','boorowAmount']
		}),
		remoteSort : true
	});
	store.setDefaultSort('recordId', 'desc');
	return store;
};

/**
 * 建立操作的Toolbar
 */
BookBorrowView.prototype.topbar = function() {
	var toolbar = new Ext.Toolbar({
		id : 'BookBorrowFootBar',
		height : 30,
		bodyStyle : 'text-align:left',
		items : []
	});
	if (isGranted('_BookBorrowAdd')) {
		toolbar.add(new Ext.Button({
			iconCls : 'btn-add',
			text : '添加借出记录 ',
			handler : function() {
				new BookBorrowForm();
			}
		}));
	}
	if (isGranted('_BookBorrowExport')) {
		toolbar.add(new Ext.Button({
			iconCls : 'btn-word',
			text : '导出季度资料管理报告',
			handler : function() {
				new BookBorrowExport();
			}
		}));
	}
	return toolbar;
};

/**
 * 
 */
BookBorrowView.edit = function(id) {
	new BookBorrowForm(id);
}

BookBorrowView.returnB = function(id) {
	new BookReturnForm(id);
	Ext.getCmp('ReturnFormButtonSel').hide();
	Ext.getCmp('ReturnFormButtonCle').hide();
}

BookBorrowView.confirm = function() {
	var gridPanel=Ext.getCmp('BookBorrowGrid');
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

BookBorrowView.selectProcessForm=function(runId){
		var window = new Ext.Window({
			id : 'flowForm',
			title : '审批表单',
			width : 700,
			height:400,
			y:100,
			autoHeight : true,
			shadow : false,
			modal : true,
			layout : 'anchor',
			iconCls: "btn-showDetail",
			plain : true,
			bodyStyle : 'padding:5px;',
			buttonAlign : 'center',
			autoLoad: {
	            url: __ctxPath + "/flow/processRunDetail.do?runId=" + runId
	        }
		});
		
		window.show();
};
