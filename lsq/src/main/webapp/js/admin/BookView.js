Ext.ns('BookView');
/**
 * 图书列表
 */
var BookView = function() {

}
BookView.prototype.setTypeId = function(typeId) {
	this.typeId = typeId;
	BookView.typeId = typeId;
};
BookView.prototype.getTypeId = function() {
	return this.typeId;
}
/**
 * 显示列表
 * 
 * @return {}
 */
BookView.prototype.getView = function() {

	return new Ext.Panel({
		id : 'BookView',
		title : '档案列表',
		region : 'center',
		autoScroll : true,
		items : [new Ext.FormPanel({
			frame : true,
			id : 'BookSearchForm',
			layout : 'column',
			defaults : {
				xtype : 'label'
			},
			items : [{
				text : '请输入查询条件:'
			}, {
				text : '档案名称'
			}, {
				xtype : 'textfield',
				name : 'Q_bookName_s_LK',
				width:75
			}, {
				text : '档案编号'
			}, {
				xtype : 'textfield',
				name : 'Q_isbn_S_LK',
				width:75
			}, {
				text : '归档日期'
			}, {
				xtype : 'datefield',
				format:'Y-m-d',
				name : 'Q_createDate_D_EQ',
				width:75
			}, {
				text : '归档人'
			}, {
				xtype : 'textfield',
				name : 'Q_author_S_LK',
				width:75
			}, {
				text : '归档部门'
			}, {
				xtype : 'textfield',
				name : 'Q_department_S_LK',
				width:75
			}, {
				xtype : 'button',
				text : '查询',
				iconCls : 'search',
				handler : function() {
					var searchPanel = Ext.getCmp('BookSearchForm');
					var grid = Ext.getCmp('BookGrid');
					if (searchPanel.getForm().isValid()) {
						var params = grid.getStore().baseParams;
						Ext.apply(params, searchPanel.getForm().getValues());
						grid.getStore().load(params);
					}

				}
			}]
		}), this.setup()]
	}

	);
};
/**
 * 建立视图
 */
BookView.prototype.setup = function() {
	return this.grid();
};

var showattachmentlist = function(value) {
	var files = value.split(";");
		// alert(value.length + " " + value.lastIndexOf(";"));

};

/**
 * 建立DataGrid
 */
BookView.prototype.grid = function() {
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel({
		columns : [sm, new Ext.grid.RowNumberer(), {
			header : 'bookId',
			dataIndex : 'bookId',
			hidden : true
		}, {
			header : '档案名称',
			dataIndex : 'bookName'
		}, {
			header : '档案编号',
			dataIndex : 'isbn'
		}, {
			header : '类别',
			dataIndex : 'typeName'
		},
		// {
		// header : '出版社',
		// dataIndex : 'publisher'
		// },
		// {
		// header : '价格',
		// dataIndex : 'price'
		// },
		{
			header : '所属部门',
			dataIndex : 'department'
		}, {
			header : '数量',
			dataIndex : 'amount',
			width : 50
		}, {
			header : '在库数',
			dataIndex : 'leftAmount',
			width : 60
		}, 
//		{
//			header : '附件',
//			dataIndex : 'filePath',
//			width : 60,
//			renderer : function(value, metadata, record, rowIndex,
//					colIndex) {
//				if (value == '' || value == '0' || value == null) {
//					return '无附件';
//				} else {
//					return "<a href='#'  class='attachment'></a>";
//				}
//			}
//		},
//		{
//			header : '附件名称',
//			dataIndex : 'fileName',
//			hidden : true
//		},
		{
			header : '管理',
			dataIndex : 'bookId',
			sortable : false,
			width : 100,
			renderer : function(value, metadata, record, rowIndex,
					colIndex) {
				var editId = record.data.bookId;
				var leftAmount = record.data.leftAmount;
				var str = '';
				if (isGranted('_BookDel')) {
					str = '<button title="删除" value=" " class="btn-del" onclick="BookView.remove('
							+ editId + ')"></button>';
				}
				if (isGranted('_BookEdit')) {
					str += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="BookView.edit('
							+ editId + ')"></button>';
				}
//				if (isGranted('_BookBorrow')) {
//					// 要是档案剩余数量为0则不显示借阅图标
////					if (leftAmount != 0) {
////						str += '&nbsp;<button title="借阅该书" value=" " class="menu-book-borrow" onclick="BookView.borrow('
////								+ editId + ')"></button>';
////					}
//					
//					if (leftAmount != 0) {
//						str += '&nbsp;<button title="借阅该书" value=" " class="menu-book-borrow" onclick="BookView.newFlow('
//								+ editId + ')"></button>';
//					}
//				}
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
		id : 'BookGrid',
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

	grid.addListener('rowdblclick', function(grid, rowindex, e) {
		grid.getSelectionModel().each(function(rec) {
			if (isGranted('_BookEdit')) {
				BookView.edit(rec.data.bookId);
			}
		});
	});

//	grid.addListener('cellclick', function(grid, rowIndex, columnIndex, e) {
//
//		if (columnIndex == 10) {
//			var record = grid.getStore().getAt(rowIndex);
//			if (null != record && record.data.filePath != null) {
//				var files = record.data.filePath.split(";");
//				var names = null;
//				if(record.data.fileName != null){
//					names = record.data.fileName.split(";");
//				}
//
//				if (files != "" && files.length > 0) {
//
//					var filesMenu = new Ext.menu.Menu();
//
//					Ext.each(files, function(item, i) {
//						if (item.length > 10) {
//							var fileLinkName = (i+1);
//							if(names != null && names.length == files.length){
//								fileLinkName = names[i];
//							}
//							filesMenu.addItem({
//								text : "附件" + fileLinkName,
//								handler : function() {
//									window.open((__ctxPath + "/attachFiles/"+item), "big", "");
//								}
//
//							});
//						}
//					});
//					filesMenu.showAt(e.getXY());
//				}
//
//			}
//		}
//	});

	return grid;

};

/**
 * 初始化数据
 */
BookView.prototype.store = function() {
	var store = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : __ctxPath + '/admin/listBook.do'
		}),
		baseParams:{'Q_state_N_NEQ':2},
		reader : new Ext.data.JsonReader({
			root : 'result',
			totalProperty : 'totalCounts',
			id : 'bookId',
			fields : [{
				name : 'bookId',
				type : 'int'
			}, {
				name : 'typeName',
				mapping : 'bookType.typeName'
			}, 'bookName', 'author', 'isbn', 'publisher',  'filePath',
					 'department', 'amount', 'leftAmount','fileName']
		}),
		remoteSort : true,
		listeners : {
			'datachanged' : function(s) {
				if (s.getCount() == 0) {
					Ext.ux.Toast.msg("信息", "该分类下没有档案信息！");
				}
			}
		}
	});
	store.setDefaultSort('bookId', 'desc');
	return store;
};

/**
 * 建立操作的Toolbar
 */
BookView.prototype.topbar = function() {
	var toolbar = new Ext.Toolbar({
		id : 'BookFootBar',
		height : 30,
		bodyStyle : 'text-align:left',
		items : []
	});
	if (isGranted('_BookAdd')) {
		toolbar.add(new Ext.Button({
			iconCls : 'btn-add',
			text : '添加档案',
			handler : function() {
				var bookF = new BookForm();
				var leftBookManagePanel = Ext.getCmp('leftBookManagePanel');
				var selectType = leftBookManagePanel.getSelectionModel().getSelectedNode();
				if(selectType==null||selectType.id==-1){
					Ext.ux.Toast.msg("信息", "请选择档案类别,再添加档案！");
					return;
				}
				
				Ext.getCmp("typeId").setValue(selectType.id);
				Ext.getCmp("bookTypeSelect").setValue(selectType.text);

				
				
//				Ext.Ajax.request({
//					url : __ctxPath + '/admin/nextIDBook.do?typeId='
//							+ selectType.id,
//					method : 'post',
//					success : function(response) {
//
//						var result = Ext.util.JSON.decode(response.responseText);
//						
//						Ext.getCmp('isbn').setValue(result.id);
//
//					}
//				});
				// 加载档案编号

				Ext.getCmp('BookForm').remove('bookSnContainer');
				Ext.getCmp('amoutContainer').remove('bookAmoutButton');

			}
		}));
	}
	if (isGranted('_BookDel')) {
		toolbar.add(new Ext.Button({
			iconCls : 'btn-del',
			text : '删除档案',
			handler : function() {

				var grid = Ext.getCmp("BookGrid");

				var selectRecords = grid.getSelectionModel().getSelections();

				if (selectRecords.length == 0) {
					Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
					return;
				}
				var ids = Array();
				for (var i = 0; i < selectRecords.length; i++) {
					ids.push(selectRecords[i].data.bookId);
				}

				BookView.remove(ids);
			}
		}));
	}
//	if (isGranted('_BookDel')) {
//		toolbar.add(new Ext.Button({
//			iconCls : 'btn-add',
//			text : '导入档案',
//			handler : function() {
//				new BookAdd().show();
//			}
//		}));
//	}
	return toolbar;
};

/**
 * 删除单个记录
 */
BookView.remove = function(id) {
	var grid = Ext.getCmp("BookGrid");
	var bool = true;
	if(typeof(id.length)=='undefined'){
		var record = grid.getStore().getById(id);
		var amount = record.data.amount;
		var leftAmount = record.data.leftAmount;
		if(amount!=leftAmount){
			bool = false;
		}
	}else{
		for (var i = 0; i < id.length; i++) {
			var record = grid.getStore().getById(id[i]);
			var amount = record.data.amount;
			var leftAmount = record.data.leftAmount;
			if(amount!=leftAmount){
				bool = false;
			}
		}
	}
	if(bool){
		new BookDelForm({ids:id}).show();
	}else{
		Ext.ux.Toast.msg("信息", "您选择的记录存在外借的文档， 不能删除！");
	}
};

/**
 * 
 */
BookView.edit = function(id) {
	new BookForm(id);
}
BookView.borrow = function(id) {
	// 选择借阅该书的时候根据bookId把书名自动赋上，并且把bookSn也重新加载出来
	new BookBorrowForm(null, id);
	Ext.Ajax.request({
		url : __ctxPath + '/admin/getBook.do?bookId=' + id,
		method : 'post',
		success : function(response) {
			var result = Ext.util.JSON.decode(response.responseText);
			var bookName = Ext.getCmp('bookName');
			bookName.setValue(result.data.bookName);
			var store = Ext.getCmp('borrowIsbn').getStore();
			store.reload({
				params : {
					bookId : id
				}
			});
		}
	});
}

BookView.newFlow = function(bookId) {
	Ext.Ajax.request({
		url : __ctxPath + '/flow/getByDefNameProDefinition.do' ,
		method : 'post',
		params : {
				defName : "档案借阅申请"
		},
		success : function(response) {
			var result = Ext.util.JSON.decode(response.responseText);
			var contentPanel = App.getContentPanel();
			var startPanel = contentPanel.getItem("ProcessRunStart" + result.data.defId);
			if (startPanel == null) {
				
				startPanel = new ProcessRunStart({
					id : "ProcessRunStart" + result.data.defId,
					defId : result.data.defId,
					flowName : result.data.name,
					paramsValue:bookId
				});
				contentPanel.add(startPanel);
			}
			contentPanel.activate(startPanel);
		}
	});
	
	
	
};
