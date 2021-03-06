var ArticleSendDetail = function(a) {
	return new Ext.Panel({
		title : "发文详情",
		iconCls : "menu-notice",
		id : "ArticleSendDetail",
		autoScroll : true,
		autoWidth : true,
		tbar : new Ext.Toolbar({
			height : 30,
			bodyStyle : "text-align:left",
			defaultType : "button",
			items : [{
						text : "关闭",
						iconCls : "btn-mail_remove",
						handler : function() {
							var b = Ext.getCmp("centerTabPanel");
							var c = b.getItem("ArticleSendDetail");
							b.remove(c);
						}
					}, {
						text : "上一条",
						iconCls : "btn-up",
						handler : function() {
							var c = document
									.getElementById("__haveNextArticleSendFlag");
							if (c != null && c.value != "endPre") {
								var b = document
										.getElementById("__curArticleSendId").value;
								Ext.getCmp("HomeArticleSendDetailPanel").load({
									url : __ctxPath
											+ "/pages/info/articleSendDetail.jsp?opt=_pre&noticeId="
											+ b
								});
							} else {
								Ext.ux.Toast.msg("提示信息", "这里已经是第一条了.");
							}
						}
					}, {
						text : "下一条",
						iconCls : "btn-last",
						handler : function() {
							var c = document
									.getElementById("__haveNextArticleSendFlag");
							if (c != null && c.value != "endNext") {
								var b = document
										.getElementById("__curArticleSendId").value;
								Ext.getCmp("HomeArticleSendDetailPanel").load({
									url : __ctxPath
											+ "/pages/info/articleSendDetail.jsp?opt=_next&noticeId="
											+ b
								});
							} else {
								Ext.ux.Toast.msg("提示信息", "这里已经是最后一条了.");
							}
						}
					}]
		}),
		items : [new Ext.Panel({
					width : '90%',
					id : "HomeArticleSendDetailPanel",
					autoScroll : true,
					style : "padding-left:10%;padding-top:10px;",
					autoHeight : true,
					border : false,
					autoLoad : {
						url : __ctxPath
								+ "/pages/info/articleSendDetail.jsp?noticeId=" + a
					}
				})]
	});
};