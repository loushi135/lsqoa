Ext.ns('FixedAssetsView');
/**
 * [FixedAssets]列表
 */
var FixedAssetsView = function() {
}

FixedAssetsView.prototype.setTypeId = function(typeId) {
	this.typeId = typeId;
	FixedAssetsView.typeId = typeId;
}

FixedAssetsView.prototype.getTypeId = function() {
	return this.typeId;
}

FixedAssetsView.prototype.getView = function() {
	return new Ext.Panel({
		id : 'FixedAssetsView',
		title : '固定资产列表',
		region : 'center',
		autoScroll : true,
		frame : true,
		items : [new Ext.FormPanel({
			height : 35,
			frame : true,
			region:'north',
			id : 'FixedAssetsSearchForm',
			layout : 'column',
			defaults : {
				xtype : 'label'
			},
			items : [{
						text : '查询条件:'
					},{
						text : '资产编号'
					},{
						name : 'Q_assetsNo_S_LK',
						xtype : 'textfield',
						width:80
					},{
						text : '资产名称'
					},{
						xtype : 'textfield',
						name : 'Q_assetsName_S_LK',
						width:80
					},{
						text : '存放地点:'
					},{
						xtype : 'textfield',
						name : 'Q_beDep_S_LK',
						width:80
					},{
						text : '保管人:',
					},{
						xtype : 'textfield',
						name : 'Q_custodian_S_LK',
						width:80
					},{
						text : '状态:',
					},{
						xtype : 'combo',
						id : 'combo_cmp',
						readOnly : true,
						editable : false,
						emptyText : '--请选择--',
						triggerAction : 'all',
						store : [[0,'存库'],[1,'使用'],[2,'报废']],
						hiddenName : 'Q_status_SN_EQ',
						width:80
					},{
						xtype : 'button',
						text : '查询',
						iconCls : 'search',
						handler : function() {
							var searchPanel = Ext
									.getCmp('FixedAssetsSearchForm');
							var grid = Ext.getCmp('FixedAssetsGrid');
							if (searchPanel.getForm().isValid()) {
								searchPanel.getForm().submit({
									waitMsg : '正在提交查询',
									url : __ctxPath
											+ '/admin/listFixedAssets.do',
									success : function(formPanel, action) {
										var result = Ext.util.JSON.decode(action.response.responseText);
										var params = grid.getStore().baseParams; 
			            				Ext.apply(params,searchPanel.getForm().getValues());
										grid.getStore().loadData(result);
									}
								});
							}

						}
					},{
						xtype : 'button',
						iconCls : 'btn-clear',
						text : '清空',
						handler : function() {
							var searchPanel = Ext.getCmp('FixedAssetsSearchForm');
							var combo_cmp = Ext.getCmp('combo_cmp');
							searchPanel.getForm().reset();
							combo_cmp.clearValue();
						},
						width : 60
					}]
		}), this.setup()]
	});
};
/**
 * 建立视图
 */
FixedAssetsView.prototype.setup = function() {
	
	return this.grid();
};
/**
 * 建立DataGrid
 */
FixedAssetsView.prototype.grid = function() {
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel({
		columns : [sm, new Ext.grid.RowNumberer(), {
					header : 'assetsId',
					dataIndex : 'assetsId',
					hidden : true
				}, {
					header : '固定资产编号',
					dataIndex : 'assetsNo',
					width:130
				}, {
					header : '资产名称',
					dataIndex : 'assetsName'
				}, {
					header : '品牌',
					dataIndex : 'manufacturer'
				}, {
					header : '型号',
					dataIndex : 'model',
					width:120
				}, {
					header : '资产原值',
					dataIndex : 'assetscost'
				}, {
					header : '保管人',
					dataIndex : 'custodian',
					width:60
				}, {
					header : '存放地点',
					dataIndex : 'beDep'
				},{
					header : '入库日期',
					dataIndex : 'buyDate',
					renderer:function(value){
						 return value!=null? value.substring(0,10):'';
					}
				},{
					header : '备注',
					dataIndex : 'notes'
				}, {
					header : '标贴',
					dataIndex : 'labelStatus',
					width:50,
					renderer:function(value){
						if(value){
							return "<img src='images/valid_s.gif'/>";
						}else{
							return "<img src='images/invalid_s.gif'/>";
						}
					}
				}, {
					header : '状态',
					dataIndex : 'status',
					width:50,
					renderer:function(value){
						switch(value){
						
							case 0:return '存库';break;
							case 1:return '使用';break;
							case 2:return '报废';break;
							default:break;
						}
					}
				},{
					header : '管理',
					dataIndex : 'assetsId',
					width : 50,
					sortable : false,
					renderer : function(value, metadata, record, rowIndex,
							colIndex) {
						var editId = record.data.assetsId;
						var str='';
						if(isGranted('_FixedAssetsDel')){
						str += '&nbsp;<button title="删除" value=" " class="btn-del" onclick="FixedAssetsView.remove('
								+ editId + ')">&nbsp;</button>';
						}
//						if(isGranted('_FixedAssetsEdit')){
						str += '&nbsp;<button title="详细" value=" " class="btn-edit" onclick="FixedAssetsView.edit('
								+ editId + ')">&nbsp;</button>';
//						}
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
				id : 'FixedAssetsGrid',
				region:'center',
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

	grid.addListener(
		'rowdblclick', function(grid, rowindex, e) {
				grid.getSelectionModel().each(function(rec) {
							FixedAssetsView.edit(rec.data.assetsId);
						});
			}
	);
	AppUtil.addPrintExport(grid);	
	
	return grid;

};

/**
 * 初始化数据
 */
FixedAssetsView.prototype.store = function() {
	var store = new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
							url : __ctxPath + '/admin/listFixedAssets.do'
						}),
				reader : new Ext.data.JsonReader({
							root : 'result',
							totalProperty : 'totalCounts',
							id : 'id',
							fields : [{
										name : 'assetsId',
										type : 'int'
									}

									, 'assetsNo', 'assetsName', 'model', {
										name : 'assetsTypeName',
										mapping : 'assetsType.typeName'
									}, 'manufacturer', 'buyDate',
									'beDep', 'custodian', 'notes',
									'sn','assetscost','user','labelStatus','status','configuration']
						}),
				remoteSort : true
			});
	store.setDefaultSort('assetsNo', 'desc');
	return store;
};

/**
 * 建立操作的Toolbar
 */
FixedAssetsView.prototype.topbar = function() {
	var toolbar = new Ext.Toolbar({
				id : 'FixedAssetsFootBar',
				height : 30,
				bodyStyle : 'text-align:left',
				items : []
			});
			if(isGranted('_FixedAssetsAdd')){
			   toolbar.add(new Ext.Button({
			      iconCls : 'btn-add',
					text : '创建固定资产项',
					handler : function() {
						new FixedAssetsForm();
//						Ext.getCmp('intendTermContainer').hide();
//						Ext.getCmp('WorkGrossPanel').hide();
//						Ext.getCmp('FixedAssetsForm').remove('assetCurValue');
//						Ext.getCmp('depreTypeId').getStore().reload();
//						Ext.getCmp('assetsTypeId').getStore().reload();
//						Ext.getCmp('FixedAssetsForm').remove('assetsNoContainer');
					}
			   }));
			}
			if(isGranted('_FixedAssetsDel')){
			  toolbar.add(new Ext.Button({
			   iconCls : 'btn-del',
					text : '删除固定资产项',
					handler : function() {

						var grid = Ext.getCmp("FixedAssetsGrid");

						var selectRecords = grid.getSelectionModel()
								.getSelections();

						if (selectRecords.length == 0) {
							Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
							return;
						}
						var ids = Array();
						for (var i = 0; i < selectRecords.length; i++) {
							ids.push(selectRecords[i].data.assetsId);
						}

						FixedAssetsView.remove(ids);
					}
			  }));
			}
	return toolbar;
};

/**
 * 删除单个记录
 */
FixedAssetsView.remove = function(id) {
	var grid = Ext.getCmp("FixedAssetsGrid");
	Ext.Msg.confirm('信息确认', '您确认要删除该记录吗？', function(btn) {
				if (btn == 'yes') {
					Ext.Ajax.request({
								url : __ctxPath
										+ '/admin/multiDelFixedAssets.do',
								params : {
									ids : id
								},
								method : 'post',
								success : function() {
									Ext.ux.Toast.msg("信息提示", "成功删除所选记录！");
									grid.getStore().reload({
												params : {
													start : 0,
													limit : 25
												}
											});
								}
							});
				}
			});
};

/**
 * 
 */
FixedAssetsView.edit = function(id) {
	new FixedAssetsForm(id);
//	Ext.getCmp('intendTermContainer').hide();
//	Ext.getCmp('WorkGrossPanel').hide();
//	Ext.getCmp('depreTypeId').getStore().reload();
//	Ext.getCmp('assetsTypeId').getStore().reload();

}

/**
 * 折旧
 */
FixedAssetsView.depreciate = function(id,method) {
	if(method==2){
		     new WorkGrossWin(id);
	}else if(method==1||method==3||method==4){
	  Ext.Msg.confirm('操作提示','你决定开始折算了吗？',function(btn){
	      if(btn=='yes'){
	         Ext.Ajax.request({
				url : __ctxPath + '/admin/depreciateDepreRecord.do',
				params : {ids : id},
				method : 'post',
				success : function(response, options) {
					var result = Ext.util.JSON.decode(response.responseText);
					if(result.success){					
					    Ext.ux.Toast.msg("信息提示", "成功产生折旧记录！");
					}else{
					    Ext.ux.Toast.msg("信息提示", result.message);
					}
				},
				failure:function(response, options){
				    var result = Ext.util.JSON.decode(response.responseText);
					Ext.ux.Toast.msg("信息提示",result.message);
				}
			});
	      }
	  	
	  });	
	
	}else{
	   Ext.ux.Toast.msg("信息提示","抱歉，该类型的折算方法待实现！");
	}
}
