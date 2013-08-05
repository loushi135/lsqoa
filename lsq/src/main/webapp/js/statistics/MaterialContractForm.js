/**
 * @author 
 * @createtime 
 * @class MaterialContractForm
 * @extends Ext.Window
 * @description MaterialContract表单
 */
MaterialContractForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		MaterialContractForm.superclass.constructor.call(this,{
			id:'MaterialContractFormWin',
			items:this.formPanel,
			modal:true,
			layout:'fit',
			width:620,
			height:550,
			title:'材料发包合同详细信息',
			buttonAlign : 'center',
			buttons:this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents:function(){
		var thiz = this;
		var _url = __ctxPath + '/system/listWorkDepartment.do?opt=appUser';
		var depSelector = new TreeSelector('materialContract.materialProjectRegional', _url, '',
				'materialProjectRegional', true, 110,'materialProjectRegional');
						
		Ext.getCmp("materialContract.materialProjectRegionalTree").on("click", function(g) {
			Ext.getCmp("materialContract.materialDeptId").setValue(Ext.getCmp("materialContract.materialProjectRegional").id);
		});
		this.formPanel=new Ext.FormPanel({
				layout : 'form',
//				bodyStyle: 'padding:10px 10px 10px 10px;overflow-x:hidden;overflow-y:scroll',
				bodyStyle: 'padding:10px 10px 10px 10px;',
				trackResetOnLoad:true,
				border:false,
				url : __ctxPath + '/statistics/saveMaterialContract.do',
				id : 'MaterialContractForm',
				defaults : {
					anchor : '95%,95%'
				},
				defaultType : 'textfield',
				items : [{
							name : 'materialContract.id',
							xtype:'hidden',
							value : this.id == null ? '' : this.id
						},{ 
							xtype:'hidden',	
							name:'materialContract.processRunId',
							value:-1
						 },{
							xtype : 'container',
							layout : 'column',
							id : 'subjectContainer',
							style : 'padding-left:0px;padding-bottom:5px;',
							items : [{
								xtype : 'container',
								layout : 'column',
								id : 'firstContainer',
								style : 'padding-left:0px;padding-bottom:5px;',
								items : [{
										xtype : 'label',
										style : 'padding-left:0px;padding-top:3px;',
										html : 'a.适用于分包与采购合同签订审核；</br>b.条款改变需经法务部审核；</br>c.本表由核算部归档；',
										width : 220
								},{
									xtype : 'label',
									style : 'margin-left:55px;margin-top:40px;',
									text : '合同编号:',
									width : 55
								}, {
									name : 'materialContract.contractNo',
									xtype : 'textfield',
									style : 'margin-left:0px;padding:0px;margin-top:40px;',
									id : 'contractNo',
									emptyText : '流程审批通过后自动生成',
									allowBlank : true,
									readOnly:true,
									width:210
								}]
							}]
							
						},{
							xtype : 'container',
							layout : 'column',
							id : 'proContainer',
							style : 'padding-left:0px;padding-bottom:5px;',
							items : [{
								xtype:'label',
								text:'项目编号:',
								style : 'padding-left:0px;padding-top:3px;',
								width : 70
								
							},{
								id:'proNo',
								xtype:'textfield',
								name:'materialContract.proNo',
								editable:true,
								readOnly:false,
								allowBlank:true,
								emptyText:'请选择项目',
								width:200,
								listeners:{
									'focus':function(){
										ProjectNewSelector.getView(function(proId,proName,proNo,contractId,contractNo,contractAmount,proAreaId,proArea){
											Ext.getCmp("proNo").setValue(proNo);
											Ext.getCmp("proName").setValue(proName);
											if(!Ext.isEmpty(proArea)&&Ext.isEmpty(proAreaId)){
												var url = __ctxPath + '/system/getDeptByNameDepartment.do'
												var params = "depName="+proArea;
												var data = ajaxSyncCall(url,params).data;
												if(!Ext.isEmpty(data)){
													Ext.getCmp("materialContract.materialDeptId").setValue(data.depId);
													Ext.getCmp("materialContract.materialProjectRegional").setValue(data.depName);
												}
											}else{
												Ext.getCmp("materialContract.materialDeptId").setValue(proAreaId);
												Ext.getCmp("materialContract.materialProjectRegional").setValue(proArea);
											}
										},true,true).show();
									}
								}
							},{
								xtype:'label',
								text:'项目名称:',
								style:'margin-left:10px;'
							},{
								xtype:'textfield',
								emptyText:'请选择项目',
								id:'proName',
								name:'materialContract.proName',
								editable:true,
								readOnly:false,
								style:'margin-left:3px;',
								width:215,
								allowBlank:true,
								listeners:{
									'focus':function(){
										ProjectNewSelector.getView(function(proId,proName,proNo,contractId,contractNo,contractAmount,proAreaId,proArea){
											Ext.getCmp("proNo").setValue(proNo);
											Ext.getCmp("proName").setValue(proName);
											
											if(!Ext.isEmpty(proArea)&&Ext.isEmpty(proAreaId)){
												var url = __ctxPath + '/system/getDeptByNameDepartment.do'
												var params = "depName="+proArea;
												var data = ajaxSyncCall(url,params).data;
												if(!Ext.isEmpty(data)){
													Ext.getCmp("materialContract.materialDeptId").setValue(data.depId);
													Ext.getCmp("materialContract.materialProjectRegional").setValue(data.depName);
												}
											}else{
												Ext.getCmp("materialContract.materialDeptId").setValue(proAreaId);
												Ext.getCmp("materialContract.materialProjectRegional").setValue(proArea);
											}
										},true,true).show();
									}
								}
							}]
						},{
							xtype : 'container',
							layout : 'column',
							style : 'padding-left:0px;padding-bottom:5px;',
							items : [{
								xtype:'label',
								text:'预决算员:',
								style : 'padding-left:0px;padding-top:3px;',
								width : 70
							},{
								xtype:'combo',
								mode : 'remote',
								editable : false,
								allowBlank:true,
								width:195,
								valueField : 'userId',
								displayField : 'fullname',
								triggerAction : 'all',
								store : new Ext.data.JsonStore({
										url : __ctxPath
												+ '/system/listAppUser.do',
										baseParams:{'Q_roles.roleName_S_EQ':'流程-预决算员'},
										root : 'result',
										totalProperty : 'totalCounts',
										fields : [{name:'userId',type:'int'},'fullname']
									}),
								hiddenName:'preCalcUserId',
								id:'materialContract.preCalcUserId',
								anchor:'90%',
								listeners:{
										select : function(combo, record,index) {
														var preCalcUsername = record.data.fullname;
														Ext.getCmp("materialContract.preCalcUsername").setValue(preCalcUsername);
												}
								}
							},
							{
								xtype:'label',
								style : 'padding-left:0px;padding-top:3px;margin-left:10px;',
								text:'类型:'
							},
							{
								xtype:'combo',
								mode : 'local',
								editable : false,
								allowBlank:false,
								width:60,
								triggerAction : 'all',
								store : ['区域','经理'],
								hiddenName:'materialDeptType',
								id:'materialContract.materialDeptType',
								allowBlank:false,
								value:'区域',
								anchor:'90%',
								listeners:{
										select : function(combo, record,index) {
														if(combo.getValue()=='经理'){
															Ext.getCmp("materialContract.materialProjectRegional").hide();
															Ext.getCmp("materialContract.materialDeptLabel").hide();
															Ext.getCmp("materialContract.materialDeptContainer").show();
															Ext.getCmp("materialContract.materialDeptContainer").doLayout();
														}else if(combo.getValue()=='区域'){
															Ext.getCmp("materialContract.materialProjectRegional").show();
															Ext.getCmp("materialContract.materialDeptLabel").show();
															Ext.getCmp("materialContract.materialDeptContainer").hide();
														}
												 }
								}
							},
							{
								xtype:'hidden',
								name:'preCalcUsername',
								id:'materialContract.preCalcUsername'
							},{
								xtype:'label',
								text:'施工区域:',
								id:'materialContract.materialDeptLabel',
								style:'margin-left:10px;'
							}, {
								xtype : 'hidden',
								name : 'materialDeptId',
								id : 'materialContract.materialDeptId'
							}, depSelector,
							{
								xtype:'container',
								id:'materialContract.materialDeptContainer',
								items:[
									{
										xtype:'label',
										id:'materialContract.materialDeptChargerLabel',
										style:'margin-left:10px;',
										text:'区域经理:'
									},
									{
										xtype:"textfield",
										name:"materialContract.materialDeptChargerName",
										id:'materialDeptChargerName',
										width:110,
										listeners : {
											'focus' : function() {
												UserSelector.getView(function(n,l,mobile) {
																	var materialDeptChargerId = Ext.getCmp("materialContract.materialDeptCharger.userId");
																	var materialDeptCharger = Ext.getCmp("materialDeptChargerName");
																	materialDeptChargerId.setValue(n);
																	materialDeptCharger.setValue(l);
																},true)
														.show()
											}
										}
									},{
										xtype:'hidden',
										name:'materialContract.materialDeptCharger.userId',
										id:'materialContract.materialDeptCharger.userId',
										listeners :{
													afterrender:function(container){
															Ext.getCmp("materialContract.materialDeptContainer").hide();
													}
												}
									}
								]
							}
							]
						},{
							xtype : 'container',
							layout : 'column',
							style : 'padding-left:0px;padding-bottom:5px;',
							items : [{
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;',
								text : '申请部门:',
								width : 70
							}, {
								name : 'materialContract.dept',
								id : 'dept',
								xtype : 'textfield',
								allowBlank : false,
								width : 200,
								editable : false,
								readOnly : true,
								style:"background:#F0F0F0;",
								value:__currentUserDept
							},{
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;margin-left:10px;',
								text : '申请人/经办人:',
								width : 90
							}, {
								name : 'materialContract.applyer',
								xtype : 'textfield',
								id : 'applyer',
								allowBlank : false,
								editable:false,
								readOnly:true,
								width : 182,
								style:"background:#F0F0F0;",
								value:__currentUser
							}]
							
						},{
							xtype : 'container',
							layout : 'column',
							style : 'padding-left:0px;padding-bottom:5px;',
							items : [{
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;',
								text : '合同总价:',
								width : 70
							}, {
								name : 'materialContract.contractAmount',
								xtype : 'textfield',
								id : 'contractAmount',
								width:80,
								allowBlank : false,
								enableKeyEvents:true,
								regex:/^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/,
								regexText:'请输入正确格式的金额',
								listeners : { 
									keyup: function(field) { 	
										function Arabia_to_Chinese(Num)
										　　{
										　　for(i=Num.length-1;i>=0;i--)
										　　{
										　　Num = Num.replace(",","")//替换tomoney()中的","
										　　Num = Num.replace(" ","")//替换tomoney()中的空格
										　　}
										　　Num = Num.replace("￥","")//替换掉可能出现的￥字符
										　　if(isNaN(Num))
										　　{ //验证输入的字符是否为数字
										　　//alert("请检查小写金额是否正确");
										　　return;
										　　}
										　　//---字符处理完毕，开始转换，转换采用前后两部分分别转换---//
										　　part = String(Num).split(".");
										　　newchar = "";
										　　//小数点前进行转化
										　　for(i=part[0].length-1;i>=0;i--)
										　　{
										　　if(part[0].length > 10)
										　　{
										　　alert("位数过大，无法计算");
										　　return "";
										　　}//若数量超过拾亿单位，提示
										　　tmpnewchar = ""
										　　perchar = part[0].charAt(i);
										　　switch(perchar)
										　　{
										　　case "0": tmpnewchar="零" + tmpnewchar ;break;
										　　case "1": tmpnewchar="壹" + tmpnewchar ;break;
										　　case "2": tmpnewchar="贰" + tmpnewchar ;break;
										　　case "3": tmpnewchar="叁" + tmpnewchar ;break;
										　　case "4": tmpnewchar="肆" + tmpnewchar ;break;
										　　case "5": tmpnewchar="伍" + tmpnewchar ;break;
										　　case "6": tmpnewchar="陸" + tmpnewchar ;break;
										　　case "7": tmpnewchar="柒" + tmpnewchar ;break;
										　　case "8": tmpnewchar="捌" + tmpnewchar ;break;
										　　case "9": tmpnewchar="玖" + tmpnewchar ;break;
										　　}
										　　switch(part[0].length-i-1)
										　　{
										　　case 0: tmpnewchar = tmpnewchar +"元" ;break;
										　　case 1: if(perchar!=0)tmpnewchar= tmpnewchar +"拾" ;break;
										　　case 2: if(perchar!=0)tmpnewchar= tmpnewchar +"佰" ;break;
										　　case 3: if(perchar!=0)tmpnewchar= tmpnewchar +"仟" ;break;
										　　case 4: tmpnewchar= tmpnewchar +"万" ;break;
										　　case 5: if(perchar!=0)tmpnewchar= tmpnewchar +"拾" ;break;
										　　case 6: if(perchar!=0)tmpnewchar= tmpnewchar +"佰" ;break;
										　　case 7: if(perchar!=0)tmpnewchar= tmpnewchar +"仟" ;break;
										　　case 8: tmpnewchar= tmpnewchar +"亿" ;break;
										　　case 9: tmpnewchar= tmpnewchar +"拾" ;break;
										　　}
										　　newchar = tmpnewchar + newchar;
										　　}//for
										　　//小数点之后进行转化
										　　if(Num.indexOf(".")!=-1)
										　　{
										　　if(part[1].length > 2)
										　　{
										　　alert("小数点之后只能保留两位,系统将自动截段");
										　　part[1] = part[1].substr(0,2)
										　　}
										　　for(i=0;i<part[1].length;i++)
										　　{//for2
										　　tmpnewchar = ""
										　　perchar = part[1].charAt(i)
										　　switch(perchar)
										　　{
										　　case "0": tmpnewchar="零" + tmpnewchar ;break;
										　　case "1": tmpnewchar="壹" + tmpnewchar ;break;
										　　case "2": tmpnewchar="贰" + tmpnewchar ;break;
										　　case "3": tmpnewchar="叁" + tmpnewchar ;break;
										　　case "4": tmpnewchar="肆" + tmpnewchar ;break;
										　　case "5": tmpnewchar="伍" + tmpnewchar ;break;
										　　case "6": tmpnewchar="陸" + tmpnewchar ;break;
										　　case "7": tmpnewchar="柒" + tmpnewchar ;break;
										　　case "8": tmpnewchar="捌" + tmpnewchar ;break;
										　　case "9": tmpnewchar="玖" + tmpnewchar ;break;
										　　}
										　　if(i==0)tmpnewchar =tmpnewchar + "角";
										　　if(i==1)tmpnewchar = tmpnewchar + "分";
										　　newchar = newchar + tmpnewchar;
										　　}//for2
										　　}
										　　//替换所有无用汉字
										　　while(newchar.search("零零") != -1)
										　　newchar = newchar.replace("零零", "零");
										　　newchar = newchar.replace("亿零万", "亿");
										　　newchar = newchar.replace("零亿", "亿");
										　　newchar = newchar.replace("亿万", "亿");
										　　newchar = newchar.replace("零万", "万");
										　　newchar = newchar.replace("零元", "元");
										　　newchar = newchar.replace("零角", "");
										　　newchar = newchar.replace("零分", "");
										　　if (newchar.charAt(newchar.length-1) == "元" || newchar.charAt(newchar.length-1) == "角")
										　　newchar = newchar+"整"
										　　return newchar;
										}
										Ext.getCmp('materialContract.chlabel').setText('('+Arabia_to_Chinese(field.getValue())+')'); 
										Ext.getCmp('chValue').setValue(Arabia_to_Chinese(field.getValue())); 
									} 
								} 
							},{
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;color:red;',
								id:'materialContract.chlabel',
								text:'(请输入纯数字的金额)',
								width :120
							},{
								name : 'materialContract.chValue',
								id : 'chValue',
								xtype:'hidden'
							},{
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;margin-left:10px;',
								text : '数量/面积:',
								width : 74
							}, {
								name : 'materialContract.areanum',
								xtype : 'textfield',
								id : 'areanum',
								allowBlank:false,
								width : 197,
								maxLength:20
							}]
						},{
							xtype : 'container',
							layout : 'column',
							style : 'padding-left:0px;padding-bottom:5px;',
							items : [{
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;',
								text : '合同类别',
								width : 70
							}, {			
								xtype : 'combo',
								id : 'mainItem',
								hiddenName : 'materialContract.mainItem',
								emptyText : '---请选择---',
								selectOnFocus : true,
								forceSelection : true,
								triggerAction : 'all',
								store : [],
								allowBlank:false,
								width:195,
								listeners:{
									'select':function(cmb){
										if(cmb.getValue()=='04单包'||cmb.getValue()=='05小双包'||cmb.getValue()=='06大双包'){
											Ext.getCmp('sysItem').setValue('无');
										}else{
											Ext.getCmp('sysItem').setValue('');
										}					
									},
									'focus' : function(combo) {
											var _store = Ext.getCmp('mainItem').getStore();
											if (_store.getCount() <= 0){
												Ext.Ajax.request({
													url : __ctxPath+ '/system/loadDictionary.do',
													method : 'post',
													params : {
															itemName : '合同类别'
													},
													success : function(response) {
															var result = Ext.util.JSON.decode(response.responseText);
															_store.loadData(result);
													}
												});
											}
										}
								}
							},{
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;margin-left:10px;',
								text : '系统类别:',
								width : 74
							}, {
								hiddenName: 'materialContract.sysItem',
								xtype : 'combo',
								id : 'sysItem',
								emptyText : '---请选择---',
								selectOnFocus : true,
								forceSelection : true,
								editable:false,
								triggerAction : 'all',
								store:[],
								allowBlank:false,
								listeners:{
									'focus' : function(combo) {
											var _store = Ext.getCmp('sysItem').getStore();
											if (_store.getCount() <= 0){
												Ext.Ajax.request({
													url : __ctxPath+ '/system/loadDictionary.do',
													method : 'post',
													params : {
															itemName : '系统类别'
													},
													success : function(response) {
															var result = Ext.util.JSON.decode(response.responseText);
															_store.loadData(result);
													}
												});
											}
										}
								},
								width:190
							}]
							
						},{
							xtype : 'container',
							layout : 'column',
							style : 'padding-left:0px;padding-bottom:5px;',
							items : [{
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;',
								text : '签约单位:',
								width : 70
							}, {
								id : 'xydw',
								name:'materialContract.xydw',
								allowBlank : false,
								xtype : 'textfield',
								width : 195,
								readOnly:true,
								listeners:{
									focus:function(){
										new SuppliersAssessSelector(function(supplierId,supplierName,supplierNo){
												Ext.getCmp("xydw").setValue(supplierName);
												Ext.getCmp("materialContract.suppliersAssess.suppliersId").setValue(supplierId);
										},true).show();
									}
								}
							},{
								xtype:'hidden',
								name:'materialContract.suppliersAssess.suppliersId',
								id:'materialContract.suppliersAssess.suppliersId'
							},{
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;margin-left:10px;',
								text : '联系人及电话:',
								width : 90
							}, {
								name : 'materialContract.tel',
								xtype : 'textfield',
								id : 'tel',
								allowBlank : false,
								width : 180,
								maxLength:25
							}]
							
						},{
							xtype : 'container',
							layout : 'column',
							style : 'padding-left:0px;padding-bottom:5px;',
							items : [{
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;',
								text : '付款方式:',
								width : 70
							}, {
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;',
								text : '预付款:',
								width : 45
							},{
								name : 'materialContract.yfk',
								xtype : 'textfield',
								id : 'yfk',
								allowBlank : false,
								regex:/^(([1-9]\d{0,2})|0)(\.\d{1,2})?$/,
								width :40,
								listeners : {
										blur : function(obj){
											thiz.checkTextFiled(obj);
										}
									}
							}, {
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;',
								text : '%',
								width :10
							}, {
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;margin-left:10px;',
								text : '进度款:',
								width :45
							},{
								name : 'materialContract.jdk',
								xtype : 'textfield',
								id : 'jdk',
								regex:/^(([1-9]\d{0,2})|0)(\.\d{1,2})?$/,
								allowBlank : false,
								width :40,
								listeners : {
										blur : function(obj){
											thiz.checkTextFiled(obj);
										}
									}
							}, {
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;',
								text : '%',
								width : 7
							},{
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;margin-left:10px;',
								text : '完工款:',
								width : 45
							},{
								name : 'materialContract.wgk',
								xtype : 'textfield',
								id : 'wgk',
							    regex:/^(([1-9]\d{0,2})|0)(\.\d{1,2})?$/,
								allowBlank : false,
								width :40,
								listeners : {
										blur : function(obj){
											thiz.checkTextFiled(obj);
										}
									}
							}, {
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;',
								text : '%',
								width :7
							},{
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;margin-left:10px;',
								text : '结算款:',
								width :45
							},{
								name : 'materialContract.jsk',
								xtype : 'textfield',
								id : 'jsk',
							    regex:/^(([1-9]\d{0,2})|0)(\.\d{1,2})?$/,
								allowBlank : false,
								width :40,
								listeners : {
										blur : function(obj){
											thiz.checkTextFiled(obj);
										}
									}
							}, {
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;',
								text : '%',
								width : 7
							},{
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;margin-left:10px;',
								text : '质保金:',
								width : 45
							},{
								name : 'materialContract.zbj',
								xtype : 'textfield',
								id : 'zbj',
							    regex:/^(([1-9]\d{0,2})|0)(\.\d{1,2})?$/,
								allowBlank : false,
								width :40,
								listeners : {
										blur : function(obj){
											thiz.checkTextFiled(obj);
										}
									}
							}, {
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;',
								text : '%',
								width : 7
							}]
						},{
							xtype : 'container',
							layout : 'column',
							style : 'padding-left:0px;padding-bottom:5px;',
							items : [
								{
									xtype : 'label',
									id : 'error_info'
								}
							]
						},{
							xtype : 'container',
							layout : 'column',
							style : 'padding-left:0px;padding-bottom:5px;',
							items : [{
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;',
								text : '进度款收取时间:',
								width : 70
							}, {
								xtype:'combo',
								mode : 'local',
								editable : false,
								allowBlank:false,
								width : 470,
								triggerAction : 'all',
								store : ['货前','货后'],
								hiddenName:'materialContract.jdkReceive',
								id:'jdkReceive',
								allowBlank:false
							}]
						},{
							xtype : 'container',
							layout : 'column',
							style : 'padding-left:0px;padding-bottom:5px;',
							items : [{
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;',
								text : '合同陈述:',
								width : 70
							}, {
								name : 'materialContract.usefull',
								xtype : 'textarea',
								id : 'usefull',
								allowBlank : true,
								width : 470,
								height:70
							}]
						},
						{
							xtype : 'container',
							autoHeight : true,
							layout : 'column',
							autoWidth : true,
							defaultType : 'label',
							style : 'padding-left:0px;padding-bottom:5px;',
							items : [{
										text : '合同附件:',
										width : 61,
										style : 'padding-left:0px;padding-top:3px;'
									},{
										xtype : 'hidden',
										name : 'materialContract.contractAttachIDs',
										id : 'contractAttachIDs'
									},{
										xtype : 'hidden',
										name : 'materialContract.contractFile',
										id : 'contractFile'
									},{
										xtype : 'panel',
										id:'materialContract.displayAttach',
										width : 430,
										height: 65,
										frame:false,
										autoScroll:true,
										style : 'padding-left:10px;padding-top:0px;',
										html : ''
									},{
										xtype : 'button',
										iconCls : 'btn-upload',
										text : '上传',
										handler : function() {
											
											var dialog = App.createUploadDialog({
																	file_cat : 'flow/materialContract',
																	callback : function(data) {
																		Ext.getCmp('materialContract.displayAttach').body.update('');
																		Ext.getCmp("contractFile").setValue('');
																		Ext.getCmp('contractAttachIDs').setValue('');
																		var contractFile = Ext.getCmp("contractFile");
																		var fileIds = Ext.getCmp('contractAttachIDs');
																		var display = Ext.getCmp('materialContract.displayAttach');
																		for (var i = 0; i < data.length; i++) {
																			if (fileIds.getValue() != '') {
																				fileIds.setValue(fileIds.getValue()+ ',');
																				contractFile.setValue(contractFile.getValue()+ ',');
																			}
																			contractFile.setValue(contractFile.getValue()+data[i].filepath+":"+data[i].filename.replace(/\s+/g,""));
//																			contractFile.setValue(contractFile.getValue()+"<a href=javascript:window.location.href=\""+__ctxPath+"/file-Download?filePath=attachFiles/"+data[i].filepath+"&fileName="+data[i].filename.replace(/\s+/g,"")+"\">"+data[i].filename.replace(/\s+/g,"")+"</a>");
																			fileIds.setValue(fileIds.getValue()+data[i].fileId);
																			Ext.DomHelper.append(display.body,'<span><a href="#" onclick="FileAttachDetail.show('+data[i].fileId+')">'+data[i].filename.replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
																		}
																	},
																	permitted_max_size :1024*1024*20
																});
												dialog.show(this);
										}
									}]
						},{
							xtype : 'container',
							layout : 'column',
							style : 'padding-left:0px;padding-bottom:5px;',
							items : [{
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;',
								text : '经办人员:',
								width : 70
							}, {
								name : 'materialContract.remark',
								id : 'remark',
								readOnly:true,
								xtype : 'textfield',
								allowBlank : true,
								width : 470,
								style:"background:#F0F0F0;",
								value:__currentUser
							}]
							
						}]
			});
		//加载表单对应的数据	
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getMaterialContract.do?id='+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON.decode(action.response.responseText);
					var res = jsonData.data;
					Ext.getCmp("materialContract.suppliersAssess.suppliersId").setValue(res.suppliersAssess.suppliersId);
					Ext.getCmp("materialContract.suppliersAssess.suppliersId").originalValue = res.suppliersAssess.suppliersId;
					var attachId = res.contractAttachIDs;
					var attachFile = res.contractFile;
					if(attachId!= null && attachId != 'undefined' && attachFile!= null && attachFile != 'undefined'){
							var ids = attachId.split(',');
							var files= attachFile.split(',');
							for(var i=0;i<ids.length;i++){
								if(files[i].lastIndexOf('<a href')!=-1){
									  Ext.DomHelper.append(Ext.getCmp('materialContract.displayAttach').body,'<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+files[i].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
								}else if(files[i].lastIndexOf(':')!=-1){
									  var fg = files[i].split(':');
									  Ext.DomHelper.append(Ext.getCmp('materialContract.displayAttach').body,'<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+fg[1].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
								}
							}
					}
					if(!Ext.isEmpty(res.materialDeptCharger)){
						Ext.getCmp("materialContract.materialDeptCharger.userId").setValue(res.materialDeptCharger.userId);
						Ext.getCmp("materialContract.materialDeptCharger.userId").originalValue = res.materialDeptCharger.userId;
						Ext.getCmp("materialContract.materialProjectRegional").hide();
						Ext.getCmp("materialContract.materialDeptLabel").hide();
						Ext.getCmp("materialContract.materialDeptContainer").show();
						Ext.getCmp("materialContract.materialDeptContainer").doLayout();
						Ext.getCmp("materialContract.materialDeptType").setValue("经理");
						Ext.getCmp("materialContract.materialDeptType").originalValue = "经理";
					}else{
						Ext.getCmp("materialContract.materialDeptType").setValue("区域");
						Ext.getCmp("materialContract.materialDeptType").originalValue = "区域";
					}
				},
				failure : function(form, action) {
				}
			});
		}
		//初始化功能按钮
		this.buttons=[
			{
				text : '保存',
				iconCls : 'btn-save',
				handler :this.save.createCallback(this.formPanel,this)
			}, {
				text : '重置',
				iconCls : 'btn-reset',
				handler :this.reset.createCallback(this.formPanel)
			},
			{
				text : '关闭',
				iconCls : 'btn-cancel',
				handler : this.cancel.createCallback(this)
			}];
	},//end of the initcomponents
	
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
					var gridPanel=Ext.getCmp('MaterialContractGrid');
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
					window.close();
				}
			});
		}
	}//end of save
	,
	checkTextFiled:function(obj){
		var yfk = Ext.getCmp("yfk").getValue();       
		var jdk = Ext.getCmp("jdk").getValue();
		var wgk = Ext.getCmp("wgk").getValue();
		var jsk = Ext.getCmp("jsk").getValue();
		var zbj = Ext.getCmp("zbj").getValue();
		if(yfk != "" && jdk != "" && wgk != "" && jsk != "" && zbj != ""){
			if((yfk*1 + jdk*1 + wgk*1 + jsk*1 + zbj*1) != 100){
				obj.setValue("");
				Ext.getCmp("error_info").setText("<font color='red'>提示:请输入正确的金额以达到预付款方式的总和为100%</font>",false);
			}else{
				Ext.getCmp("error_info").setText("",false);
			}
		}else{
			Ext.getCmp("error_info").setText("",false);
		}
	}
});