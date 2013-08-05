var BookBorrowConfirmWin=function(ids){
    var formPanel=new Ext.FormPanel({
        id:'BookBorrowConfirmPanel',
        url : __ctxPath + '/admin/multiConfirmBookBorRet.do',
        baseParams:{ids:ids},
        formId : 'BookBorrowConfirmPanelId',
        layout : 'form',
        frame:false,
        items:[{
				xtype : 'panel',
				//style : 'padding-left:50%;',
				html : '<span style="padding-left:30%;font-size:28px;color:blue;">领用确认</span><br/><span style="padding-left:20%;font-size:18px;color:blue;">您确认借阅的档案已拿到？</span>'
			},{
				name : 'status',
				xtype : 'hidden',
				id:'BookBorrowConfirmPanel.status'
			}]
    });
    var win=new Ext.Window({
       title:'领用确认',
       id:'BookBorrowConfirmWin',
       iconCls:'menu-hireIssue',
       width:500,
       autoHeight:true,
       buttonAlign : 'center',
       items:[formPanel],
       buttons: [{
		text : '确认',
		iconCls : 'btn-empProfile-pass',
		id:'PassConfirmSb',
		handler : function(){
			Ext.getCmp('BookBorrowConfirmPanel.status').setValue('已确认');
			var fp=Ext.getCmp('BookBorrowConfirmPanel');
			if (fp.getForm().isValid()) {
					fp.getForm().submit({
						method : 'post',
						waitMsg : '正在提交数据...',
						success : function(fp, action) {
							Ext.ux.Toast.msg('操作信息', '成功保存信息！');
							var bookBorrowGrid=Ext.getCmp('BookBorrowGrid');
							if(bookBorrowGrid!=null){
								bookBorrowGrid.getStore().reload();
							}
							var bookBorrowForPublicGrid=Ext.getCmp('BookBorrowForPublicGrid');
							if(bookBorrowForPublicGrid!=null){
								bookBorrowForPublicGrid.getStore().reload();
							}
							
							win.close();
						},
						failure : function(fp, action) {
							Ext.MessageBox.show({
										title : '操作信息',
										msg : '信息保存出错，请联系管理员！',
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR
									});
							win.close();
						}
					});
				}
		}
	}, {
	   text : '关闭',
	   iconCls:'btn-close',
		handler : function(){
			win.close();
		}
	}]
    });
    win.show();
    
   
}
