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
var ContractSelector = function(callback,isSingle){
	var store = new Ext.data.Store({
				// 获取数据的方式
				proxy : new Ext.data.HttpProxy({
							url : __ctxPath+'/statistics/listDesignContract.do'
						}),
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalCounts', // 记录总数
							root : 'result' // Json中的列表数据根节点
						}, [{
									name : 'id' // Json中的属性Key值
								}, {
									name : 'contractNo'
								}, {
									name : 'contractAmount'
								},{
									name : 'projectName'
								}])
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
			header : '合同id', // 列标题
			dataIndex : 'id', // 数据索引:和Store模型对应
			sortable : true,
			hidden : true
		},{
			header : '合同编号',
			dataIndex : 'contractNo',
			sortable : true
		},{
			header : '合同总价',
			dataIndex : 'contractAmount',
			sortable : true
		},{
			header : '项目名称',
			dataIndex : 'projectName'
		}	
		])
	
	var contractGrid = new Ext.grid.GridPanel({
		id : 'contactGrid',
		height : 360,
		autoWidth:false,
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
		}
	});
	var qForm = new Ext.form.FormPanel({
				region : 'north',
				title : '<span class="commoncss">查询条件<span>',
				collapsible : true,
				border : true,
				labelWidth : 50, // 标签宽度
				// frame : true, //是否渲染表单面板背景色
				labelAlign : 'right', // 标签对齐方式
				bodyStyle : 'padding:3 5 0', // 表单元素和表单面板的边距
				buttonAlign : 'center',
				height : 95,
				layout : 'column',
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
							}],
				buttons : [{
							text : '查询',
							handler : function() {
								var params = qForm.getForm().getValues();
								params.start = 0;
								params.limit = 25;
								store.load({
											params : params
										});
							}
						}, {
							text : '重置',
							handler : function() {
								qForm.getForm().reset();
							}
						}]
			});
	var window = new Ext.Window({
			title : '选择合同',
			width : 650,
			height : 440,
			layout:'border',
			border:false,
			items : [contractGrid,qForm],
			resizable:true,
			modal:true,
			buttonAlign : 'right',
			buttons : [
				{
					text : '确定',
					listeners : {
						click : function(){
							var rows = contractGrid.getSelectionModel().getSelections();
								var id = '';
								var contractNo = '';
								var contractAmount = '';
								var projectName = '';
							for (var i = 0; i < rows.length; i++) {
								if (i > 0) {
									id += ',';
									contractNo += ',';
									contractAmount += ',';
									projectName += ',';
								}
								id += rows[i].data.id;
								contractNo += rows[i].data.contractNo;
								contractAmount += rows[i].data.contractAmount;
								projectName += rows[i].data.projectName;
							}
							if (callback != null) {								
								callback.call(this, id,contractNo,contractAmount,projectName);
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