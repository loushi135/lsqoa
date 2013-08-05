ProcessNextFormForYhoa = Ext.extend(Ext.Panel, {
	constructor : function(c) {
		Ext.applyIf(this, c);
		ProcessNextFormForYhoa.superclass.constructor.call(this, {
					id : "ProcessNextFormForYhoa" + this.taskId,
					iconCls : "btn-approvalTask",
					title : this.taskName,
					layout : "fit",
					bodyStyle : "padding:5px",
					html : '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src='+ this.url + '></iframe>',
					listeners:{
						'close':function(){
							var myTaskGrid = Ext.getCmp("MyTaskGrid");													
							var appHomeTaskGrid = Ext.getCmp("TaskPanelView");
							var messagePanelView=Ext.getCmp("MessagePanelView");
							
							if (myTaskGrid != null) {
									myTaskGrid.getStore().reload();
								}
								if (appHomeTaskGrid != null) {
									appHomeTaskGrid.getUpdater().update(__ctxPath+ "/flow/displayTask.do");
								}
																			
								if(messagePanelView!=null){
									messagePanelView.getUpdater().update(__ctxPath + "/info/displayInMessage.do");
								}
						},
						'deactivate':function(){
							var myTaskGrid = Ext.getCmp("MyTaskGrid");													
							var appHomeTaskGrid = Ext.getCmp("TaskPanelView");
							var messagePanelView=Ext.getCmp("MessagePanelView");
							
							if (myTaskGrid != null) {
								myTaskGrid.getStore().reload();
							}
							if (appHomeTaskGrid != null) {
								appHomeTaskGrid.getUpdater().update(__ctxPath+ "/flow/displayTask.do");
							}
																		
							if(messagePanelView!=null){
								messagePanelView.getUpdater().update(__ctxPath + "/info/displayInMessage.do");
							}

						}						
					}
				});
	}
});


