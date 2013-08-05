Ext.ns('BookTypeView');
/**
 * 图书类别列表
 */
var BookTypeView = function() {

	var search = function() {

		var searchPanel = Ext.getCmp('BookTypeSearchForm');
		var grid = Ext.getCmp('BookTypeGrid');

		var toolbar = grid.getTopToolbar();
		var toolbar_container = toolbar.items.get(3);

		if (searchPanel.getForm().isValid()) {
			toolbar_container.removeAll(true);

			var _store = grid.getStore();
			_store.setBaseParam("Q_ptypeId_L_EQ", "NOTADD");

			var typename = searchPanel.items.get(2).getValue();
			var typesn = searchPanel.items.get(4).getValue();
			if (typename == typesn != "") {
				typename = typesn = "NOTADD";
				_store.setBaseParam("Q_ptypeId_L_EQ", -1);
			}

			_store.setBaseParam("Q_typeName_S_LK", typename);
			_store.setBaseParam("Q_typeSN_S_LK", typesn);
			Ext.getCmp('BookTypeGrid_PagingToolbar').moveFirst();
		}

	};

	return new Ext.Panel({
		id : 'BookTypeView',
		title : '档案类别列表',
		iconCls : 'menu-book-type',
		autoScroll : true,
		items : [new Ext.FormPanel({
			height : 35,
			frame : true,
			id : 'BookTypeSearchForm',
			layout : 'column',
			defaults : {
				xtype : 'label'
			},
			items : [{
				text : '请输入查询条件:'
			}, {
				text : '档案类别'
			}, {
				xtype : 'textfield',
				name : 'Q_typeName_S_LK'
			}, {
				text : '档案编号'
			}, {
				xtype : 'textfield',
				name : 'Q_typeSN_S_LK'
			}, {
				xtype : 'button',
				text : '查询',
				iconCls : 'search',
				handler : function() {

					search();
			
				}
			}, {
				xtype : 'button',
				text : '取消查询条件',
				iconCls : 'btn-del',
				handler : function() {
					var searchPanel = Ext.getCmp('BookTypeSearchForm');
					var grid = Ext.getCmp('BookTypeGrid');
					searchPanel.items.get(2).setValue("");
					searchPanel.items.get(4).setValue("");
					search();

				}
			}]
		}), this.setup()]
	});
};
/**
 * 建立视图
 */
BookTypeView.prototype.setup = function() {
	return this.grid();
};
/**
 * 建立DataGrid
 */
BookTypeView.prototype.grid = function() {
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel({
		columns : [sm, {
			header : 'typeId',
			dataIndex : 'typeId',
			hidden : true
		}, {
			header : '类别名称',
			dataIndex : 'typeName',
			width : 30
		}, {
			header : '类别编号',
			dataIndex : 'typeSN',
			width : 30
		}, {
			header : '类别位置',
			dataIndex : 'path',
			width : 50
				// ,
				// renderer : function(value, metadata, record, rowIndex,
				// colIndex) {
				// return "<a style='color:black;text-decoration:none;' "
				// + "title='进入该分类' href='" + "javascript:alert(" +
				//
				// record.data.ptypeId
				//
				// + ");return false;'>" + value + "</a>"};

		}, {
			header : '说明',
			dataIndex : 'remark',
			width : 50
		}, {
					header : '管理',
					dataIndex : 'typeId',
					sortable : false,
					width : 50,
					renderer : function(value, metadata, record, rowIndex,
							colIndex) {
						var editId = record.data.typeId;
						var str = '';
						if (isGranted('_BookTypeDel')) {
							str = '<button title="删除" value=" " class="btn-del" onclick="BookTypeView.remove('
									+ editId + ')"></button>';
						}
						if (isGranted('_BookTypeEdit')) {
							str += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="BookTypeView.edit('
									+ editId + ')"></button>';
						}
						return str;
					}
				}],
		defaults : {
			sortable : true,
			menuDisabled : false
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
		id : 'BookTypeGrid',
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
			id : "BookTypeGrid_PagingToolbar",
			pageSize : 25,
			store : store,
			plugins : [new Ext.ux.PageSizePlugin()], 
			displayInfo : true,
			displayMsg : '当前显示从{0}至{1}， 共{2}条记录',
			emptyMsg : "当前没有记录"
		})
	});

	// 原先是修改功能
	// grid.addListener('rowdblclick', function(grid, rowindex, e) {
	// grid.getSelectionModel().each(function(rec) {
	// if(isGranted('_BookTypeEdit')){
	// BookTypeView.edit(rec.data.typeId);
	// }
	// });
	// });

	// 先改为进入当前类别下
	grid.addListener('rowdblclick', function(grid, rowindex, e) {

		var row = grid.getSelectionModel().getSelected();
		var typeId = row.data.typeId;
		var toolbar = grid.getTopToolbar();
		var toolbar_container = toolbar.items.get(3);

		// -------------------------ajax 查询 目录层级
		// 开始----------------------------------

		var loadData = function() {

			Ext.Ajax.request({
				url : __ctxPath + '/admin/pathBookType.do',
				params : {
					typeId : typeId
				},
				method : 'post',
				success : function(result, request) {

					var res = Ext.util.JSON.decode(result.responseText);
					if (res.directSubTypeCount == 0) {
						Ext.ux.Toast.msg("操作信息", "不存在子类");
					} else {
						toolbar_container.removeAll(true);
						// ----存在子类 设置path
						if (typeId != -1) {
							toolbar_container.add(fldh);
						}
						Ext.each(res.data, function(e, index) {

							if (index == 0) {
								toolbar_container.add({
									iconCls : 'menu-book-type',
									xtype : 'button',
									text : "顶级分类",
									handler : function() {
										typeId = -1;
										loadData();
									}
								});

								if (res.data.length > 0) {
									toolbar_container.add({
										xtype : 'button',
										text : ">"
									});
								}

							}

							toolbar_container.add({
								iconCls : 'menu-book-type',
								xtype : 'button',
								text : e.typeName,
								handler : function() {
									typeId = e.typeId;
									loadData();
								}
							});

							if (res.data.length != index + 1) {
								toolbar_container.add({
									xtype : 'button',
									text : ">"
								});
							}

						});

						toolbar.doLayout();

						// 重新加载数据 当前父类下的子类
						store.setBaseParam("Q_ptypeId_L_EQ", typeId);
						Ext.getCmp('BookTypeGrid_PagingToolbar').moveFirst();

					}
				}
			});

		};

		loadData();
			// -------------------------ajax 查询 目录层级
			// 结束----------------------------------

		});

	var fldh = {
		xtype : 'tbtext',
		text : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分类导航&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; '
	};

	grid.getTopToolbar().add({
		xtype : 'container',
		layout : 'column',
		width : 800
	});

	return grid;

};

/**
 * 初始化数据
 */
BookTypeView.prototype.store = function() {
	var store = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : __ctxPath + '/admin/listBookType.do'
		}),
		reader : new Ext.data.JsonReader({
			root : 'result',
			totalProperty : 'totalCounts',
			id : 'id',
			fields : [{
				name : 'typeId',
				type : 'int'
			}, 'typeName', "path", "ptypeId", "typeSN", "remark"]
		}),
		remoteSort : true
	});
	store.setDefaultSort('typeId', 'asc');
	store.setBaseParam("Q_ptypeId_L_EQ", "-1");

	return store;
};

/**
 * 建立操作的Toolbar
 */
BookTypeView.prototype.topbar = function() {
	var toolbar = new Ext.Toolbar({
		id : 'BookTypeFootBar',
		height : 30,
		bodyStyle : 'text-align:left',
		items : []
	});
	if (isGranted('_BookTypeAdd')) {
		toolbar.add(new Ext.Button({
			iconCls : 'btn-add',
			text : '添加档案类别',
			handler : function() {

				var grid = Ext.getCmp("BookTypeGrid");

				var selectRecords = grid.getSelectionModel().getSelections();
				if (selectRecords.length <= 1) {

					var ptypeId = -1;
					var ptypeName = null;
					if (selectRecords.length == 1) {
						var row = grid.getSelectionModel().getSelected();
						ptypeId = row.data.typeId;
						if (row.data.ptypeId <= 0)
							ptypeName = row.data.typeName;
						else
							ptypeName = row.data.path + " > "
									+ row.data.typeName;
					}
					if (selectRecords.length == 0) {
						var store = grid.getStore();
						if (store.getCount() != 0) {
							var record = store.getAt(0);

							ptypeId = record.data.ptypeId;
							// var pathArray = record.data.path.split(" > ");
							// ptypeName = pathArray[pathArray.length - 1];
							ptypeName = record.data.path;
						}

					}
					// alert(" ptypeId:" + ptypeId + " ptypeName:" + ptypeName);
					new BookTypeForm(null, ptypeId, ptypeName);

				} else {
					Ext.ux.Toast.msg("信息", "您只能选择一项作为添加时的父类，如果不选择则默认添加到当前分类下");
					return;
				}

			}
		}));
	}
	if (isGranted('_BookTypeDel')) {
		toolbar.add(new Ext.Button({
			iconCls : 'btn-del',
			text : '删除档案类别',
			handler : function() {

				var grid = Ext.getCmp("BookTypeGrid");

				var selectRecords = grid.getSelectionModel().getSelections();

				if (selectRecords.length == 0) {
					Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
					return;
				}
				var ids = Array();
				for (var i = 0; i < selectRecords.length; i++) {
					ids.push(selectRecords[i].data.typeId);
				}
				BookTypeView.remove(ids);
			}
		}));
	}

	if (isGranted('_BookTypeEdit')) {
		toolbar.add(new Ext.Button({
			iconCls : 'btn-edit',
			text : '修改档案类别',
			handler : function() {

				var grid = Ext.getCmp("BookTypeGrid");

				var selectRecords = grid.getSelectionModel().getSelections();

				if (selectRecords.length != 1) {
					Ext.ux.Toast.msg("信息", "请选择一条需要修改的记录！");
					return;
				}
				var row = grid.getSelectionModel().getSelected();
				BookTypeView.edit(row.data.typeId)

			}
		}));
	}

	return toolbar;
};

/**
 * 删除单个记录
 */
BookTypeView.remove = function(id) {
	var grid = Ext.getCmp("BookTypeGrid");
	Ext.Msg.confirm('信息确认', '您确认要删除该记录吗？', function(btn) {
		if (btn == 'yes') {
			Ext.Ajax.request({
				url : __ctxPath + '/admin/multiDelBookType.do',
				params : {
					ids : id
				},
				method : 'post',
				success : function(result, request) {
					var res = Ext.util.JSON.decode(result.responseText);
					if (res.success == false) {
						Ext.ux.Toast.msg("操作信息", res.message);
					} else {
						Ext.ux.Toast.msg("信息提示", "成功删除所选记录！");
						grid.getStore().reload({
							params : {
								start : 0,
								limit : 25
							}
						});
					}
				}
			});
		}
	});
};

/**
 * 
 */
BookTypeView.edit = function(id, ptypeId, ptypeName) {
	new BookTypeForm(id, ptypeId, ptypeName);
}
