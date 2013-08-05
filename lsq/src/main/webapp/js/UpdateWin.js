var updateWin=new Ext.Window({
		id : 'updateWin',
		title : '最新通知',
		width : 400,
		iconCls : 'menu-job-check',
		height : 400,
		shadow : false,
		modal : true,
		plain : true,
		bodyStyle : 'padding:5px;',
		buttonAlign : 'center',
		items:[{
			xtype:'panel',
			id:'updatePanel',
			height:220,
			frame:false,
			html:'',
			listeners:{
				render : function(){
					Ext.Ajax.request({
						url:__ctxPath+'/system/getLastAnnounce.do',
						method:'post',
						success:function(response,options){
							var object = Ext.util.JSON.decode(response.responseText);
							var data = object.data;
							Ext.DomHelper.overwrite(Ext.getCmp("updatePanel").body,
								'<html><body><div style="width:366px;height:212px;OVERFLOW-y:auto;OVERFLOW-x:hidden;padding:3px">' +
								'&nbsp;&nbsp;&nbsp;&nbsp;'+data.context+
								'</div></body></html>');
							var fileAttachs = data.fileAttachs;
							var display = Ext.getCmp('updatePanel1');
							if(fileAttachs.length > 0){
								for(var i = 0 ; i < fileAttachs.length ; i ++){
									Ext.DomHelper.append(display.body,'<span><a href="#" onclick="FileAttachDetail.show('+fileAttachs[i].fileId+')">'+fileAttachs[i].fileName+'</a>&nbsp;|&nbsp;</span>');
								}
							}
						}
					});
				}
			}
		},{
			xtype:'panel',
			id:'updatePanel1',
			height:100,
			frame:false,
			html:''}],
		buttons : [{
				text : '查看全部并设置提示',
				iconCls : 'btn-flowView',
				handler : function() {
					var tabs = Ext.getCmp('centerTabPanel');
					var panel = new AnnounceRemindView();
					tabs.add(panel);
					tabs.activate(panel);
					updateWin.close();
				}
			},{
				text : '关闭',
				iconCls : 'btn-cancel',
				handler : function() {
					updateWin.close();
				}
		}]
});