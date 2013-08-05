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
var MaterialContractSelector = function(callback,isSingle){
	
	var store = new Ext.data.Store({
				// 获取数据的方式
				proxy : new Ext.data.HttpProxy({
							url : __ctxPath+'/statistics/listMaterialContract.do'
						}),
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalCounts', // 记录总数
							root : 'result' // Json中的列表数据根节点
						}, [{name : 'id',type:'int'},'contractNo','project','proName','proNo','applyer','dept','department','contractAmount','chValue','areanum','contractName','mainItem',
							'sysItem','xydw','tel','yfk','jdk','wgk','jsk','zbj','usefull','contractAttachIDs','contractFile','remark','suppliersAssess','materialDeptCharger','processRunId'])
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
	
	var cm = new Ext.grid.ColumnModel([sm,new Ext.grid.RowNumberer(),{
								header : 'id',
								dataIndex : 'id',
								hidden : true
							}, {
								header : '合同编号',
								dataIndex : 'contractNo',
								width:100
							}, {
								header : '项目名称',
								dataIndex : 'proName',
								width:250
							}, {
								header : '合同总价',
								dataIndex : 'contractAmount',
								width:100,
								renderer:function(value){
									if(!Ext.isEmpty(value)){
										return value+"元";
									}
								}
							}, {
								header : '签约单位',
								dataIndex : 'suppliersAssess',
								width:250,
								renderer:function(value){
									if(!Ext.isEmpty(value)){
										return value.suppliersName;
									}
								}
							}, {
								header : '系统类别',
								width:150,
								dataIndex : 'sysItem'
							}
		])
	
	var contractGrid = new Ext.grid.GridPanel({
		id : 'materialContactGrid',
		width : 850,
		height : 300,
		region : "center",
		title : "材料发包合同列表",
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
					displayInfo : true,
					displayMsg : "当前显示从{0}至{1}， 共{2}条记录",
					emptyMsg : "当前没有记录"
				})
	});
	var qForm = new Ext.form.FormPanel({
				width : 850,
				region : "north",
				id : "materialContactForm",
				height : 60,
				frame : false,
				border : false,
				//layout : "hbox",
				layoutConfig : {
					padding : "5",
					align : "middle"
				},
				defaults : {
					xtype : "label",
					margins : {
						top : 0,
						right : 4,
						bottom : 4,
						left : 4
					}
				},
				items : [{
							xtype : "panel",
							layout : "column",
							border : false,
							unstyled : false,
							style : 'margin-top:5px;',
							defaults : {
								xtype : 'label',
								style : {
									margin : '0px 0px 5px 0px'
								}
							},
							items : [{
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '请输入查询条件:'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '合同编号:'
							}, {
								name : 'Q_contractNo_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '项目名称:'
							}, {
								name : 'Q_proName_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '合同总价:'
							}, {
								name : 'Q_contractAmount_S_LK',
								xtype : 'textfield'
							}]},
							
							 {
							xtype : "panel",
							layout : "column",
							border : false,
							unstyled : false,
							defaults : {
								xtype : 'label',
								style : {
									margin : '0px 0px 3px 0px'
								}
							},
							items : [{
								style : 'margin-top:5px;margin-left:88px;',
								xtype : 'label',
								text : '签约单位:'
							}, {
								name : 'Q_suppliersAssess.suppliersName_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '系统类别:'
							}, {
								name : 'Q_sysItem_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-left:55px',
								xtype : "button",
								text : "查询",
								iconCls : "search",
								handler : function() {
									var i = Ext.getCmp("materialContactForm");
									var j = Ext.getCmp("materialContactGrid");
									var params={'Q_status_N_EQ':0};
									if (i.getForm().isValid()) {
										var params = store.baseParams;
										Ext.apply(params, qForm.getForm().getValues());
										store.load(params);
									}
								}
							}]}
						]
			});
	var window = new Ext.Window({
			title : '合同选择器',
			width : 850,
			height : 380,
			layout:'border',
			border:false,
			items : [contractGrid,qForm],
			resizable:true,
			modal:true,
			buttonAlign : "center",
			buttons : [
				{
					text : '确定',
					iconCls : "btn-ok",
					listeners : {
						click : function(){
							var rows = contractGrid.getSelectionModel().getSelections();
								var id = '';
								var contractNo = '';
								var contractAmount = '';
								var proName = '';
								var proNo = '';
								var suppliersName = '';
								var suppliersId = '';
								var projectId = '';
								var departmentId = '';
								var departmentIdName='';
								var projectChargerId ='';
								var projectChargerName = '';
								var processRunId = '';
							for (var i = 0; i < rows.length; i++) {
								if (i > 0) {
									id += ',';
									contractNo += ',';
									contractAmount += ',';
									proName += ',';
									proNo += ',';
									suppliersName += ',';
									suppliersId += ',';
									projectId += ',';
									departmentId += ',';
									departmentIdName += ',';
									projectChargerId += ',';
									projectChargerName += ',';
									processRunId +=',';
								}
								id += rows[i].data.id;
								contractNo += rows[i].data.contractNo;
								contractAmount += rows[i].data.contractAmount;
								proName += rows[i].data.proName;
								proNo += rows[i].data.proNo;
								if(rows[i].data.suppliersAssess!=null&&rows[i].data.suppliersAssess!=''){
									suppliersName += rows[i].data.suppliersAssess.suppliersName;
									suppliersId += rows[i].data.suppliersAssess.suppliersId;
								}
								if(!Ext.isEmpty(rows[i].data.project)){
									projectId += rows[i].data.project.id;
									if(!Ext.isEmpty(rows[i].data.project.proChargerUser)){
										projectChargerId+=rows[i].data.project.proChargerUser.userId;
										projectChargerName+=rows[i].data.project.proChargerUser.fullname;
									}else if(!Ext.isEmpty(rows[i].data.materialDeptCharger)){
										projectChargerId+=rows[i].data.materialDeptCharger.userId;
										projectChargerName+=rows[i].data.materialDeptCharger.fullname;
									}
								}else if(!Ext.isEmpty(rows[i].data.materialDeptCharger)){
									projectChargerId+=rows[i].data.materialDeptCharger.userId;
									projectChargerName+=rows[i].data.materialDeptCharger.fullname;
								}
								if(!Ext.isEmpty(rows[i].data.department)){
									departmentId += rows[i].data.department.depId;
									departmentIdName += rows[i].data.department.depName;
								}
								processRunId += rows[i].data.processRunId;
							}
							if (callback != null) {
								callback.call(this, id,contractNo,contractAmount,proName,proNo,suppliersName,suppliersId,projectId,departmentId,departmentIdName,projectChargerId,projectChargerName,processRunId);
							}
							window.close();
						}
					}
				},
				{
					text : '取消',
					iconCls : "btn-cancel",
					handler : function() {
						window.close();
					}
				}
			]
			});
	return window;
}