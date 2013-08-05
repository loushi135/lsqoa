/**
 * @author 
 * @createtime 
 * @class TrainReportForm
 * @extends Ext.Window
 * @description TrainReport表单
 */
TrainReportUserEditForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		TrainReportUserEditForm.superclass.constructor.call(this,{
			id:'TrainReportUserEditFormWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			height:380,
			width:390,
			maximizable:true,
			title:'详细信息',
			buttonAlign : 'center',
			buttons:this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents:function(){
		var thiz = this;
		var notAttendcbm = new Ext.grid.CheckboxSelectionModel();
		var attendcbm = new Ext.grid.CheckboxSelectionModel();
		var notAttendGridTR = new Ext.grid.GridPanel({
					id : "notAttendGridTR",
					region : "west",
					title : "未出席人员",
					width : 165,
					autoScroll : true,
					store : new Ext.data.ArrayStore({
								fields : ["id", "userName"]
							}),
					trackMouseOver : true,
					sm : notAttendcbm,
					columns : [notAttendcbm, new Ext.grid.RowNumberer(), {
								header : 'id',
								dataIndex : 'id',
								hidden : true
							},{
								header : "用户名",
								dataIndex : "userName"
							}]
				});
		var attendGridTR = new Ext.grid.GridPanel({
					id : "attendGridTR",
					region : "east",
					title : "出席人员",
					autoScroll : true,
					width : 165,
					store : new Ext.data.ArrayStore({
								fields : ["id", "userName"]
							}),
					trackMouseOver : true,
					sm : attendcbm,
					columns : [attendcbm, new Ext.grid.RowNumberer(), {
								header : 'id',
								dataIndex : 'id',
								hidden : true
							},{
								header : "用户名",
								dataIndex : "userName"
							}]
				});
		var buttonPanel = new Ext.Panel({
						frame : true,
						region : "center",
						layout : {
							type : "vbox",
							pack : "center",
							align : "stretch"
						},
						defaults : {
							margins : "0 0 5 0"
						},
						items : [{
									xtype : "button",
									text : "&gt;&gt;",
									handler : function(){
										thiz.removeAttendUser("notAttendGridTR","attendGridTR");
									}
								}, {
									xtype : "button",
									text : "&lt&lt;",
									handler : function(){
										thiz.removeAttendUser("attendGridTR","notAttendGridTR");
									}
								}]
					});
		this.formPanel=new Ext.FormPanel({
			layout : "border",
			items : [attendGridTR,buttonPanel,notAttendGridTR]
		});
		//加载表单对应的数据	
		if(!Ext.isEmpty(this.array)){
			var array =  this.array;
			var notAttendGridTRStore = notAttendGridTR.getStore()
			var attendGridTRStore = attendGridTR.getStore()
			for(var i=0; i < array.length; i++){
				var id = array[i].id;
				var userName = array[i].userName;
				var attend = array[i].attend;
				var tempField = {"id":id, "userName":userName}
				if(attend == '0'){
					var tempRecord = new attendGridTRStore.recordType(tempField);
					attendGridTR.stopEditing();
					attendGridTRStore.add(tempRecord);
				}else{
					var tempRecord = new notAttendGridTRStore.recordType(tempField);
					notAttendGridTR.stopEditing();
					notAttendGridTRStore.add(tempRecord);
				}
			}
		}
		//初始化功能按钮
		this.buttons=[{
				text : '保存',
				iconCls : 'btn-save',
				handler :this.save.createCallback(this.formPanel,this)
			},{
				text : '取消',
				iconCls : 'btn-cancel',
				handler : this.cancel.createCallback(this)
			}];
	},//end of the initcomponents
	
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
		var attendStoreRecord = Ext.getCmp("attendGridTR").getStore();
		var notAttendStoreRecord = Ext.getCmp("notAttendGridTR").getStore();
		var attendArray = new Array();
		var notAttendArray = new Array();
		var notAttendFullnameArray = new Array();
		for (var i = 0; i < attendStoreRecord.getCount(); i++) {
			attendArray.push(attendStoreRecord.getAt(i).data.id);
		};
		for (var i = 0; i < notAttendStoreRecord.getCount(); i++) {
			notAttendFullnameArray.push(notAttendStoreRecord.getAt(i).data.userName)
			notAttendArray.push(notAttendStoreRecord.getAt(i).data.id);
		};
		Ext.Msg.confirm('提示信息','是否确定要删除:['+notAttendFullnameArray+']参会人员',function(btn){
			if(btn=='yes'){
				Ext.Ajax.request({
					url : __ctxPath + '/hrm/updateAllAttendTrainUser.do',
					params : {'attendArray':attendArray,'notAttendArray':notAttendArray},
					success : function(response,options){
						window.close();
						Ext.ux.Toast.msg('操作信息','操作成功！');
					},
					failure : function(){
						window.close();
						Ext.ux.Toast.msg('操作信息','操作失败！');
					}
				})
			}
		})
	},//end of save
	removeAttendUser:function(startId,endId){
		var startGrid = Ext.getCmp(startId);
		var startStroe = startGrid.getStore();
		var endGrid = Ext.getCmp(endId);
		var endStroe = endGrid.getStore();
		var startRecords = startGrid.getSelectionModel().getSelections();
		for (var i = 0; i < startRecords.length; i++) {
			var id = startRecords[i].data.id;
			var userName = startRecords[i].data.userName;
				var tempField = {
					'id' : id,
					'userName' : userName
				};
				var tempRecord = new endStroe.recordType(tempField);
				endGrid.stopEditing();
				endStroe.add(tempRecord);
				startStroe.remove(startRecords[i]);
		}
	}
});