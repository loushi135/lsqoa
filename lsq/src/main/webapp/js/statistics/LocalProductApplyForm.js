/**
 * @author 
 * @createtime 
 * @class LocalProductApplyForm
 * @extends Ext.Window
 * @description LocalProductApply表单
 */
LocalProductApplyForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		LocalProductApplyForm.superclass.constructor.call(this,{
			id:'LocalProductApplyFormWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			width : 800,
			height : 450,
			maximizable:true,
			title:'[土特产领用]详细信息',
			buttonAlign : 'center',
			buttons:this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents:function(){
		var thizId = this.id;
		var summary = new Ext.ux.grid.GridSummary();
		this.formPanel=new Ext.FormPanel({
				layout : 'form',
				trackResetOnLoad:true,
				bodyStyle: 'padding:10px 10px 10px 10px',
				border:false,
				url : __ctxPath + '/statistics/saveLocalProductApply.do',
				id : 'LocalProductApplyForm',
				defaults : {
					anchor : '98%,98%'
				},
				defaultType : 'textfield',
				items : [{
							name : 'localProductApply.id',
							id : 'id',
							xtype:'hidden',
							value : this.id == null ? '' : this.id
						},{
					xtype : 'container',
					layout : 'column',
					height : 25,
					defaults : {
						xtype : 'label',
						style : {
							margin : '0px 0px 3px 0px'
						}
					},
					items : [{
								text : '报告人：',
								columnWidth : .15
							}, {
								xtype : 'textfield',
								name : 'localProductApply.applyUser.fullname',
								id : 'localProductApply.applyUser.fullname',
								columnWidth : .15,
								readOnly:true,
								value:__currentUser
							}, {
								xtype : "hidden",
								name : "localProductApply.applyUser.userId",
								id : "localProductApply.applyUser.userId",
								value:__currentUserId
							},{
								text : '所属部门：',
								style:'margin-left:10px;',
								columnWidth : .15
							}, {
								xtype : 'textfield',
								name : 'localProductApply.dept.depName',
								id : 'localProductApply.dept.depName',
								columnWidth : .15,
								readOnly:true,
								value:__currentUserDept
							}, {
								xtype : "hidden",
								name : "localProductApply.dept.depId",
								id : "localProductApply.dept.depId",
								value:__currentUserDeptId
							}, {
								text : '报告时间：',
								style : 'margin-left:10px;',
								columnWidth : .15
							}, {
								xtype : 'datefield',
								name : 'localProductApply.applyDate',
								id : 'applyDate',
								allowBlank : false,
								columnWidth : .15,
								format : 'Y-m-d',
								value : new Date()
							}]
				}, {
					xtype : 'container',
					layout : 'column',
					height : 25,
					defaults : {
						xtype : 'label',
						style : {
							margin : '0px 0px 3px 0px'
						}
					},
					items : [{
								text : '报告详情：'
							}, {
								style : 'color:red',
								id : 'localProductApplyErrorLabel',
								text : '(*必填)'
							}]
				}, {
					xtype : 'container',
					style : 'padding-left:0px;margin-bottom:10px',
					layout : 'column',
					items : [{
								name : 'resultGridData',
								xtype : 'hidden',
								id : 'localProductApply.resultGridData'
							}, {
								name : 'dataList',
								xtype : 'hidden',
								id : 'localProductApply.dataList'
							}, {
								xtype : 'grid',
								id : 'localProductApply.resultGridDataGrid',
								height : 220,
								width : '98%',
								stripeRows : true,
								trackMouseOver : true,
								clicksToEdit : 1,
								plugins : [new Ext.ux.grid.RowEditor({
											saveText : '保存',
											cancelText : '取消',
											errorText : '请输入完整信息',
											errorSummary : false
										}), summary],
								store : new Ext.data.JsonStore({
									url : __ctxPath + '/statistics/listLocalProductApplyInfo.do',
									root : 'result',
									totalProperty : 'totalCounts',
									remoteSort : true,
									fields : ['id', 'name', 'price', 'num', 'amount', {name:'proId',mapping:'project.id'}, {name:'proName',mapping:'project.proName'}],
									listeners : {
										'update' : function(value) {
											var r = this.getRange();
											var resultArray = [];
											var jsonArr = [];
											var sum = 0;
											var rlen = r.length;
											for (var i = 0; i < rlen; i++) {
												resultArray.push([]);
												if (r[i].fields) {
													var fields = r[i].fields;
													var flen = fields.length;
													var obj = {
														project:{
															id:null
														}
													};
													var emptyNum = 0;//为空的次数 
													for (var z = 0; z < flen; z++) {
														var fieldValue = r[i].get(fields.keys[z]);
														if (Ext.isEmpty(fieldValue) || fieldValue == '请双击选择' || fieldValue == '请双击填写') {
															emptyNum++;
														}
														if (fields.keys[z] == 'id') {
															resultArray[i].push('ID:' + fieldValue);
															if (fieldValue != '') {
																obj.id = fieldValue;
															}
														} else if (fields.keys[z] == 'name') {
															resultArray[i].push('名称:' + fieldValue);
															obj.name = fieldValue;
														} else if (fields.keys[z] == 'price') {
															resultArray[i].push('单价（元）:' + fieldValue);
															obj.price = fieldValue;
														} else if (fields.keys[z] == 'num') {
															resultArray[i].push('数量:' + fieldValue);
															obj.num = fieldValue;
														} else if (fields.keys[z] == 'amount') {
															resultArray[i].push('总价（元）:' + fieldValue);
															obj.amount = fieldValue;
														} else if (fields.keys[z] == 'proId') {
															resultArray[i].push('项目ID:' + fieldValue);
															obj.project.id = fieldValue;
														} else if (fields.keys[z] == 'proName') {
															resultArray[i].push('项目名称:' + fieldValue+ ';');
														}
													}
													if(flen!=emptyNum){//长度 不等于 为空的次数（一行数据全为空）
														   jsonArr.push(obj);
													}else{//相等时 删除resultArray中加的数据
														   resultArray.splice(i,1,"");
													}
												}
											}
											//删除空元素
											for (var i = 0; i < resultArray.length; i++) {
												if (resultArray[i].length == 0) {
													resultArray.splice(i, 1);
													i--;
												}
											}
											Ext.getCmp('localProductApply.dataList').setValue(Ext.util.JSON.encode(jsonArr));
											Ext.getCmp('localProductApply.resultGridData').setValue(resultArray);
										}
									}
								}),
								cm : new Ext.grid.ColumnModel({
									columns : [new Ext.grid.RowNumberer(), {
												dataIndex : 'id',
												width : 50,
												hidden : true
											}, {
												header : '名称',
												dataIndex : 'name',
												editor : new Ext.grid.GridEditor(new Ext.form.TextField({
													allowBlank : false,
													maxLength : 64,
													listeners : {
														focus : function(field) {
															if (field.getValue() == '请双击填写') {
																field.setValue('');
															}
														}
													}
												})),
												width : 200,
												summaryRenderer : function(v, params, data) {
													return '<font color="red">合计:</font>';
												}
											}, {
												header : '单价（元）',
												dataIndex : 'price',
												editor : new Ext.grid.GridEditor(new Ext.form.NumberField({
													allowBlank : false,
													maxLength : 13,
													id:'localProductApply.price',
													regex:/^(([1-9]\d{0,10})|0)(\.\d{1,2})?$/,
													regexText : '请输入正确格式的金额',
													enableKeyEvents : true,
													listeners:{
														keyup:function(){
															var num = Ext.getCmp('localProductApply.num').getValue();
															var price = Ext.getCmp('localProductApply.price').getValue();
															if(!Ext.isEmpty(num)&&!Ext.isEmpty(price)){
																Ext.getCmp('localProductApply.amount').setValue(num*price);
															}
														}
													}
												}))
											}, {
												header : '数量',
												dataIndex : 'num',
												editor : new Ext.grid.GridEditor(new Ext.form.NumberField({
													allowBlank : false,
													maxLength : 13,
													id:'localProductApply.num',
													regex:/^(([1-9]\d{0,10})|0)(\.\d{1,2})?$/,
													regexText : '请输入正确格式的金额',
													enableKeyEvents : true,
													listeners:{
														keyup:function(){
															var num = Ext.getCmp('localProductApply.num').getValue();
															var price = Ext.getCmp('localProductApply.price').getValue();
															if(!Ext.isEmpty(num)&&!Ext.isEmpty(price)){
																Ext.getCmp('localProductApply.amount').setValue(num*price);
															}
														}
													}
												}))
											}, {
												header : '总价（元）',
												dataIndex : 'amount',
												summaryType : 'sum',
												editor : new Ext.grid.GridEditor(new Ext.form.NumberField({
													allowBlank : false,
													maxLength : 13,
													id:'localProductApply.amount',
													readOnly:true,
													regex:/^(([1-9]\d{0,10})|0)(\.\d{1,2})?$/,
													regexText : '请输入正确格式的金额'
												}))
//												summaryRenderer : function(v, params, data) {
//													var amount = data.gridData['amount'];
//													var amountBig = AmountInWords(amount)
//													Ext.getCmp('localProductApply.amount').setValue(amount);
//													Ext.getCmp('localProductApply.amountBig').setValue(amountBig);
//													return '<font color="red">大写:</font>' + amountBig;
//												}
											}, {
												header : '',
												dataIndex : 'proId',
												editor : new Ext.grid.GridEditor(new Ext.form.Hidden({
													id : 'localProductApply.proId'
												})),
												hidden:true
											}, {
												header : '项目名称',
												dataIndex : 'proName',
												width : 220,
												editor : new Ext.grid.GridEditor(new Ext.form.TextField({
													id : 'localProductApply.proName',
													readOnly:true,
													allowBlank : false,
													emptyText:'请选择项目',
													listeners:{
														focus:function(){
															ProjectNewSelector.getView(function(proId, proName, proNo) {
																Ext.getCmp("localProductApply.proId").setValue(proId);
																Ext.getCmp("localProductApply.proName").setValue(proName);
															}, true, true).show();
														}
													}
												}))
											}
											],
									defaults : {
										sortable : false,
										menuDisabled : false,
										width : 100
									}
								}),
								tbar : new Ext.Toolbar({
									height : 30,
									items : [{
												iconCls : 'btn-add',
												text : '添加',
												xtype : 'button',
												handler : function() {
													var DataRecord = Ext.data.Record.create([{
																name : 'id'
															}, {
																name : 'name'
															}, {
																name : 'price'
															}, {
																name : 'num'
															}, {
																name : 'amount'
															}, {
																name : 'proId'
															}, {
																name : 'proName'
															}]);
													var store = Ext.getCmp('localProductApply.resultGridDataGrid').store;
													var grid = Ext.getCmp('localProductApply.resultGridDataGrid');

													var n1 = '请双击填写';
													var n2 = '';
													
													var newRowData = {
														name : n1,
														price : n1,
														num:n2,
														amount : n2,
														proId:n2,
														proName:n2
													};
													var newRecord = new DataRecord(newRowData);
													store.add([newRecord]);

													grid.getSelectionModel().on('selectionchange', function(sm) {
														Ext.getCmp('localProductApply.removeBtn').setDisabled(sm.getCount() < 1);
													});
												}
											}, {
												iconCls : 'btn-del',
												text : '删除',
												id : 'localProductApply.removeBtn',
												xtype : 'button',
												handler : function() {
													var store = Ext.getCmp('localProductApply.resultGridDataGrid').store;
													var grid = Ext.getCmp('localProductApply.resultGridDataGrid');
													var s = grid.getSelectionModel().getSelections();
													for (var i = 0, r; r = s[i]; i++) {
														store.remove(r);
													}
													store.fireEvent("update");//更新事件
												}
											}]
								})
							}]
				}, {
					xtype : 'textfield',
					hidden : true,
					validator : function() {
						var resultGridData = Ext.getCmp('localProductApply.resultGridData').getValue();
						var errorLabel = Ext.getCmp("localProductApplyErrorLabel");
						if (Ext.isEmpty(thizId)) {
							if (Ext.isEmpty(resultGridData)) {//为空显示提示
								errorLabel.setText('请填写报告详情');
								return false;
							}
						}
						return true
					}
				}, {
					xtype : 'container',
					layout : 'column',
					height : 80,
					defaults : {
						xtype : 'label',
						style : {
							margin : '0px 0px 3px 0px'
						}
					},
					items : [{
								text : '物品及申购事由：',
								columnWidth : .15
							}, {
								xtype : 'textarea',
								height : 80,
								name : 'localProductApply.applyReason',
								id : 'applyReason',
								columnWidth : .83
							}]
				}]
			});
		//加载表单对应的数据	
		this.initData();
		//初始化功能按钮
		this.buttons=[{
				text : '保存',
				iconCls : 'btn-save',
				hidden:!(isGranted("_LocalProductApplyAdd")||isGranted("_LocalProductApplyEdit")) ,
				handler :this.save.createCallback(this.formPanel,this)
			}, {
				text : '重置',
				iconCls : 'btn-reset',
				hidden:!(isGranted("_LocalProductApplyAdd")||isGranted("_LocalProductApplyEdit")) ,
				handler :this.reset.createCallback(this.formPanel)
			},{
				text : '取消',
				iconCls : 'btn-cancel',
				handler : this.cancel.createCallback(this)
			}];
	},//end of the initcomponents
	/**
	 * 初始化数据
	 * @param {} formPanel
	 */
	initData:function(){
		//加载表单对应的数据	
		if (this.id != null && this.id != 'undefined') {
			Ext.getCmp('localProductApply.resultGridDataGrid').getStore().load({params:{
					'Q_localProductApply.id_L_EQ':this.id,
					start:0,
					limit:9999
			}});
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getLocalProductApply.do?id='+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
						var jsonData = Ext.util.JSON.decode(action.response.responseText);
						var res = jsonData.data;
						if (!Ext.isEmpty(res.applyUser)) {
							Ext.getCmp("localProductApply.applyUser.fullname").setValue(res.applyUser.fullname);
							Ext.getCmp("localProductApply.applyUser.fullname").originalValue = res.applyUser.fullname;
							Ext.getCmp("localProductApply.applyUser.userId").setValue(res.applyUser.userId);
							Ext.getCmp("localProductApply.applyUser.userId").originalValue = res.applyUser.userId;
						}
						if (!Ext.isEmpty(res.dept)) {
							Ext.getCmp("localProductApply.dept.depName").setValue(res.dept.depName);
							Ext.getCmp("localProductApply.dept.depName").originalValue = res.dept.depName;
							Ext.getCmp("localProductApply.dept.depId").setValue(res.dept.depId);
							Ext.getCmp("localProductApply.dept.depId").originalValue = res.dept.depId;
						}
						//Ext.getCmp("").originalValue=
				},
				failure : function(form, action) {
				}
			});
		}
	},
	/**
	 * 重置
	 * @param {} formPanel
	 */
	reset:function(formPanel){
		formPanel.getForm().reset();
	},
	/**
	 * 取消
	 * @param {} window
	 */
	cancel:function(window){
		window.close();
	},
	/**
	 * 保存记录
	 */
	save:function(formPanel,window){
		if (formPanel.getForm().isValid()) {
			formPanel.getForm().submit({
				method : 'POST',
				waitMsg : '正在提交数据...',
				success : function(fp, action) {
					Ext.ux.Toast.msg('操作信息', '成功保存信息！');
					var gridPanel=Ext.getCmp('LocalProductApplyGrid');
					if(gridPanel!=null){
						gridPanel.getStore().reload();
					}
					window.close();
				},
				failure : function(fp, action) {
					Ext.MessageBox.show({
								title : '操作信息',
								msg : '信息保存出错，请联系管理员！',
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.ERROR
							});
					//window.close();
				}
			});
		}
	}//end of save
	
});