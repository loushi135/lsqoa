PublicDocumentDetail = Ext.extend(Ext.Panel, {
	panel : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUI();
		PublicDocumentDetail.superclass.constructor.call(this, {
			id : "PulicDocumentDetail",
			iconCls : "menu-find-doc",
			title : "" + this.docName + "-详细信息",
			modal : true,
			layout : "fit",
			plain : true,
			items : this.panel
		});
	},
	initUI : function() {
		var a = new Ext.Toolbar({
			id : "PublicDocumentTopBar",
			height : 35,
			bodyStyle : "text-align:center",
			items : []
		});
		this.panel = new Ext.Panel({
			id : "PublicDocumentDetailPanel",
			width : '90%',
			modal : true,
			tbar : a,
			bodyStyle : "padding-left:10%;padding-right:10%;overflow-x:hidden;overflow-y:scroll",
//			autoScroll : true,
			autoLoad : {
				url : __ctxPath + "/document/publicDetailDocument.do?docId=" + this.docId
			}
		});
	}
});