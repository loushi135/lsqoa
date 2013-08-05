MessageWin = Ext.extend(Ext.Window, {
	constructor : function(a) {
		Ext.applyIf(this, a);
		MessageWin.superclass.constructor.call(this, {
					id : "win",
					title : "",
					iconCls : "btn-replyM",
					region : "west",
					width : 300,
					height : 200,
					x : 398,
					y : 25,
					layout : "fit",
					plain : true,
					border : false,
					bodyStyle : "padding:5px;",
					buttonAlign : "center",
					items : [],
					buttons : []
				});
		this.addUI();
	},
	addUI : function() {
		Ext.Ajax.request({
			url : __ctxPath + "/info/readInMessage.do",
			method : "Get",
			success : function(a, c) {
				var b = Ext.util.JSON.decode(a.responseText);
				var e = b.message;
				var f = Ext.getCmp("win");
				if (e != null) {
					f.setTitle(e.sender + "--发送的消息");
					f.add({
								id : "pp",
								xtype : "panel",
								height : 150,
								width : 160,
								html : "<p>  " + e.sender + "  " + e.sendTime
										+ '</p><p style="color:blue;">'
										+ e.content + "</p>"
							});
					if (e.haveNext) {
						f.addButton({
									text : "下一条",
									iconCls : "btn-down",
									id : "nextMessage",
									handler : function() {
										var g = Ext.getCmp("win");
										if (g != null) {
											g.close();
										}
										var a = Ext.getCmp("messageTip");
										var g = Ext.getCmp("messageTipBar");
										count=count-1;
										if(count!=0){
											g.setWidth(count<10?92:count<100?98:count<1000?105:count<10000?111:120);
											a.setText('<div style="height:20px;"><img src="'
												+ __ctxPath
												+ '/images/newpm.gif" style="height:12px;"/>你有<strong style="color: red;">'
												+ count + "</strong>信息</div>")
										}else{
//											a.hide();
											
											g.hide();
										}
										new MessageWin().show();
									}
								});
					}
					if (e.msgType == 1) {
						f.addButton({
									text : "回复",
									iconCls : "btn-replyM",
									id : "replyMessage",
									handler : function() {
										var g = Ext.getCmp("win");
										g.close();
										new ReMessageWin({
													id : e.senderId,
													name : e.sender
												}).show();
									}
								});
					}
					f.addButton({
						text : "删除",
						iconCls : "btn-del",
						handler : function() {
							Ext.Ajax.request({
										url : __ctxPath
												+ "/info/multiRemoveInMessage.do",
										method : "POST",
										params : {
											ids : e.receiveId
										},
										success : function(g, h) {
											var i = Ext.getCmp("win");
											i.close();
											Ext.ux.Toast.msg("操作信息", "信息删除成功！");
										},
										failure : function(g, h) {
											Ext.ux.Toast.msg("操作信息", "信息删除失败！");
										},
										scope : this
									});
						}
					});
					f.doLayout();
					var d = Ext.getCmp("MessagePanelView");
					if (d != null) {
						d.getUpdater().update(__ctxPath
								+ "/info/displayInMessage.do");
					}
				} else {
					if (f != null) {
						f.close();
					}
				}
			}
		});
	}
});