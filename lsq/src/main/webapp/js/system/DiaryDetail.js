var DiaryDetail = function(b) {
	var a = new Ext.Window({
				title : "工作日志详情",
				iconCls : "menu-diary",
				autoHeight : true,
				modal : true,
				width : 500,
				border : false,
				buttonAlign : "center",
				autoLoad : {
					url : __ctxPath + "/system/checkDiary.do?diaryId=" + b
				},
				buttons : [{
							text : "关闭",
							iconCls : "btn-close",
							handler : function() {
								a.close();
							}
						}]
			});
	a.show();
};