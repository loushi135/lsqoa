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
var ContractSelector = function(callback,isSingle,isPreContract){
	
	var baseParams = {};
	if(!Ext.isEmpty(isPreContract)&&isPreContract){//预定义版本选择器
		baseParams = {'Q_contractVersion_N_NEQ':0};
	}else{
		//baseParams = {'Q_contractVersion_N_EQ':0};
	}
	var store = new Ext.data.Store({
				// 获取数据的方式
				proxy : new Ext.data.HttpProxy({
							url : __ctxPath+'/statistics/listConstructioncontract.do'
						}),
				baseParams :baseParams,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalCounts', // 记录总数
							root : 'result' // Json中的列表数据根节点
						}, [{
								name : 'contractId',
								type : 'int'
							}, 'contractNo', 'contractor', 'projectRegional','deptRegional',
							'projectName', 'projectCharger','projectChargerUser',
							'projectAbbreviation', 'businessCharger',
							'linkmanAndTel', 'numOrArea', 'sumPrice', 'payWay',
							'projectTime', 'quality', 'isFullContract',
							'isDesignCost', 'designCost', 'isModelCommunity',
							'guarantee', 'constructionBackUp',
							'constructionBackUpPerson',
							'constructionBackUpFinishTime',
							'constructionLicense', 'constructionLicensePerson',
							'constructionLicenseFinishTime', 'isoverBudget',
							'quote', 'quoteloss', 'remark', 'meno'])
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
								header : 'contractId',
								dataIndex : 'contractId',
								hidden : true
							}, {
								header : '合同编号',
								dataIndex : 'contractNo'
							}, {
								header : '项目名称',
								dataIndex : 'projectName'
							}
		])
	
	var contractGrid = new Ext.grid.GridPanel({
		id : 'contactGrid',
		width : 600,
		height : 300,
		region : "center",
		title : "合同列表",
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
				width : 600,
				region : "north",
				id : "ContractForm",
				height : 40,
				frame : false,
				border : false,
				layout : "hbox",
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
								name : 'Q_projectName_S_LK',
								xtype : 'textfield'
							}, {
								xtype : "button",
								text : "查询",
								iconCls : "search",
								handler : function() {
									var i = Ext.getCmp("ContractForm");
									var j = Ext.getCmp("contactGrid");
									if (i.getForm().isValid()) {
										var params = store.baseParams;
										Ext.apply(params, qForm.getForm().getValues());
										store.load(params);
									}
								}
							}
						]
			});
	var window = new Ext.Window({
			title : '合同选择器',
			width : 650,
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
								var projectName = '';
								var proArea = '';
								var proAreaId = '';
								var proChargerName = '';
								var proChargerTel = '';
								var proChargerId = '';
								var businessCharger = '';
							for (var i = 0; i < rows.length; i++) {
								if (i > 0) {
									id += ',';
									contractNo += ',';
									contractAmount += ',';
									projectName += ',';
									proAreaId += ',';
									proArea += ',';
									proChargerName += ',';
									proChargerTel += ',';
									proChargerId += ',';
									businessCharger +=',';
								}
								id += rows[i].data.contractId;
								contractNo += rows[i].data.contractNo;
								contractAmount += rows[i].data.sumPrice;
								projectName += rows[i].data.projectName;
								proArea += rows[i].data.deptRegional.depName;
								proAreaId += rows[i].data.deptRegional.depId;
								proChargerName += rows[i].data.projectChargerUser.fullname;
								proChargerTel += rows[i].data.projectChargerUser.mobile;
								proChargerId += rows[i].data.projectChargerUser.userId;
								businessCharger +=rows[i].data.businessCharger;
							}
							if (callback != null) {								
								callback.call(this, id,contractNo,contractAmount,projectName,proAreaId,proArea,proChargerName,proChargerTel,businessCharger,proChargerId);
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