var BookBorrowExport = function() {
	var fp = this.setup();
	var window = new Ext.Window({
		id : 'BookBorrowExportWin',
		title : '资料管理报告导出',
		iconCls : 'menu-book-borrow',
		width : 320,
		// height : 420,
		autoHeight : true,
		modal : true,
		layout : 'form',
		plain : true,
		bodyStyle : 'padding:5px;',
		buttonAlign : 'center',
		items : [this.setup()],
		buttons : [{
					text : '导出',
					iconCls : 'btn-word',
					handler : function() {
						var fp = Ext.getCmp('BookBorrowExport');
						if (fp.getForm().isValid()) {
//							fp.getForm().submit({
//										method : 'post',
//										waitMsg : '正在提交数据...',
//										success : function() {
//											Ext.ux.Toast.msg("操作信息", "成功导出信息！");
//											window.close();
//										},
//										failure : function(fp, action) {
//											Ext.MessageBox.show({
//														title : '操作信息',
//														msg : '信息导出出错，请联系管理员！',
//														buttons : Ext.MessageBox.OK,
//														icon : 'ext-mb-error'
//													});
//											window.close();
//										}
//									});
							
							location= __ctxPath + '/admin/exportBookBorRet.do?format=word&exportYear='+
												Ext.getCmp('bookBorExport.exportYear').value+'&quarter='+Ext.getCmp('bookBorExport.quarter').value;
							window.close();
						}
					}
				}, {
					text : '取消',
					iconCls : 'btn-cancel',
					handler : function() {
						window.close();
					}
				}]
	});
	window.show();
};

BookBorrowExport.prototype.setup = function() {

	var formPanel = new Ext.FormPanel({
				url : __ctxPath + '/admin/exportBookBorRet.do?format=word',
				layout : 'form',
				id : 'BookBorrowExport',
				frame : true,
				formId : 'BookBorrowExportId',
				defaultType : 'textfield',
				labelWidth : '98',
				items : [{
							fieldLabel : '年份',
							name : 'exportYear',
							id : 'bookBorExport.exportYear',
							xtype : 'superDateField',
							format : 'Y',
							allowBlank : false,
							width : 150,
							value:new Date().dateFormat('Y'),  
							blankText : '年份不能为空'
						}, {
							fieldLabel : '季度',
							hiddenName : 'quarter',
							id : 'bookBorExport.quarter',
							xtype : 'combo',
							editable : false,
							allowBlank : false,
							triggerAction : 'all',
							width : 150,
							store : [['1', '第一季度'], ['4', '第二季度'],
									['7', '第三季度'], ['10', '第四季度']],
							blankText : '季度不能为空'
						}]
			});

	return formPanel;

};
