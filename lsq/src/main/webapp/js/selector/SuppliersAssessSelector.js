/**
 * use like this
 * new ContractSelector(function(id,contractNo,contractAmount,projectName){
		alert(id+' '+contractNo+' '+contractAmount+' '+projectName);
	},true).show();
 * @param {Object} callback
 * @param {Object} isSingle
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
var SuppliersAssessSelector = function(callback,isSingle,proId){
	var store = new Ext.data.Store({
				// 获取数据的方式
				proxy : new Ext.data.HttpProxy({
							url : __ctxPath+'/customer/listSuppliersAssess.do'
						}),
				baseParams : {'Q_delFlag_S_EQ':0},
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalCounts', // 记录总数
							root : 'result' // Json中的列表数据根节点
						}, ['suppliersId' ,'suppliersNo','suppliersName','supplierContacter','project'])
			});
	store.load({params : {
					start : 0,
					limit : 25
				}
			});
	var sm=null;
	if(isSingle){
		sm=new Ext.grid.CheckboxSelectionModel({
			singleSelect: true
			});
	}else{
		sm = new Ext.grid.CheckboxSelectionModel({
		});
	}
	// 定义自动当前页行号
	var rownum = new Ext.grid.RowNumberer({
				header : 'NO',
				width : 28
			});
	
	
	var cm = new Ext.grid.ColumnModel([rownum,sm,
		{
			header : '供应商id', // 列标题
			dataIndex : 'suppliersId', // 数据索引:和Store模型对应
			sortable : true,
			hidden : true
		},{
			header : '供应商/班组编号',
			dataIndex : 'suppliersNo',
			sortable : true,
			width:80
		},{
			header : '供应商/班组名称',
			dataIndex : 'suppliersName',
			sortable : true
		}
//		,{
//			header : '业务联系人',
//			dataIndex : 'supplierContacter',
//			sortable : true
//		}	
		])
	
	var contractGrid = new Ext.grid.GridPanel({
		id : 'contactGrid',
		height : 360,
		autoWidth:false,
		title:'供应商/班组列表',
		store : store,
		shim : true,
		trackMouseOver : true,
		border:false,
		disableSelection : false,
		loadMask : true,
		region : 'center',
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
					displayMsg : '当前页记录索引{0}-{1}， 共{2}条记录',
					emptyMsg : "当前没有记录"
		})
	});
	var qForm = new Ext.form.FormPanel({
				region : "north",
				height : 40,
				frame : false,
				border : false,
				layout : "column",
				bodyStyle : 'padding : 5px 0 0 10px',
				items : [{
								xtype : 'label',
								style : 'margin:3px 0 0 10px;',
								text : '请输入查询条件:'
							}, {
								xtype : 'label',
								style : 'margin:3px 0 0 10px;',
								text : '供应商/班组编号:'
							}, {
								name : 'Q_suppliersNo_S_LK',
								xtype : 'textfield'
							},{
								style : 'margin:3px 0 0 10px;',
								xtype : 'label',
								text : '供应商/班组名称:'
							}, {
								name : 'Q_suppliersName_S_LK',
								xtype : 'textfield'
							}, {
								name : 'Q_delFlag_S_EQ',
								value : '0'
							}
//							,{
//								style : 'margin:3px 0 0 10px;',
//								xtype : 'label',
//								text : '业务联系人:'
//							}, {
//								name : 'Q_supplierContacter_S_LK',
//								xtype : 'textfield'
//							}
							,{
								xtype : "button",
								style : 'margin:0 0 0 10px;',
								text : "查询",
								iconCls : "search",
								handler : function() {
									var params = qForm.getForm().getValues();
									params.start = 0;
									params.limit = 25;
									store.load({
												params : params
											});
								}
							}
//							, {
//								text : '重置',
//								xtype : 'button',
//								style : 'margin:0 0 0 10px;',
//								handler : function() {
//									qForm.getForm().reset();
//								}
//							}
							]
			});
	var treePanel = new Ext.tree.TreePanel({
		region : 'west',
		title : '分类',
		collapsible : true,
		autoScroll : true,
		useArrows : true,
		animate : true,
		containerScroll : true,
		border : false,
		split : true,
		height : 800,
		width : 150,
		tbar : new Ext.Toolbar({
			items : [{
						xtype : 'button',
						iconCls : 'btn-refresh',
						text : '刷新',
						handler : function() {
							treePanel.root.reload();
						}
					}, {
						xtype : 'button',
						text : '展开',
						iconCls : 'btn-expand',
						handler : function() {
							treePanel.expandAll();
						}
					}, {
						xtype : 'button',
						text : '收起',
						iconCls : 'btn-collapse',
						handler : function() {
							treePanel.collapseAll();
						}
					}]
		}),
		loader: new Ext.tree.TreeLoader(),
        root: new Ext.tree.AsyncTreeNode({
            expanded: true,
            text:'分类',
            children: [{
                text: '供应商',
                leaf: true
            }, {
                text: '班组',
                leaf: true
            }]
        }),
		rootVisible : true,
		listeners : {
			'click' : function(node) {
				if(node.attributes.text=='供应商'){
					store.baseParams = {'Q_delFlag_S_EQ':0,'Q_type_N_EQ':0};
					store.load({params : {
							start : 0,
							limit : 25
						}
					});
				}else if(node.attributes.text=='班组'){
					store.baseParams = {'Q_delFlag_S_EQ':0,'Q_type_N_EQ':1};
//					console.log(store.baseParams);
					store.load({params : {
							start : 0,
							limit : 25
						}
					});
				}
			}
		}
	});
	var mainPanel = new Ext.Panel({
	            region:'center',
	            title:'供应商/班组列表',
	            layout:'border',
	            items: [contractGrid,qForm]
    	});
	var window = new Ext.Window({
			title : '供应商/班组',
			width : 800,
			height : 440,
			layout:'border',
			border:false,
			items : [treePanel,mainPanel],
			resizable:true,
			modal:true,
			buttonAlign : 'right',
			buttons : [
				{
					text : '确定',
					listeners : {
						click : function(){
							var rows = contractGrid.getSelectionModel().getSelections();
								var suppliersId = '';
								var suppliersName = '';
								var contractNo = '';
							for (var i = 0; i < rows.length; i++) {
								if (i > 0) {
									id += ',';
									suppliersId += ',';
									suppliersNo += ',';
									contractNo += ',';
								}
								suppliersId += rows[i].data.suppliersId;
								suppliersName += rows[i].data.suppliersName;
								contractNo += rows[i].data.contractNo;
								if(!Ext.isEmpty(rows[i].data.project)&&!Ext.isEmpty(proId)){
									if(rows[i].data.project.id != proId){
										Ext.ux.Toast.msg('操作信息', '此供应商或班组为临时状态，只能被项目为：<font color="red">'+rows[i].data.project.proName+'</font>选择！');
										return;
									}
								}
							}
							if (callback != null) {								
								callback.call(this, suppliersId,suppliersName,contractNo);
							}
							window.close();
						}
					}
				},
				{
					text : '关闭',
					handler : function() {
						window.close();
					}
				}
			]
			});
	return window;
}