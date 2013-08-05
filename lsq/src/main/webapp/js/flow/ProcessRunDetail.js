var ProcessRunDetail = function(d, a, c, b) {
    this.runId = d;
    this.defId = a;
    this.piId = c;
    this.name = b;
    return this.setup();
};
ProcessRunDetail.prototype.setup = function() {
    var d = this.piId;
    var a = this.defId;
    var name=this.name;
    var runId = this.runId;
    var c = new Ext.Panel({
        title: "流程示意图",
        width: 550,
        autoScroll: true,
        height: 800,
        split: true,
        collapsible: true,
        region: "west",
        margin: "5 5 5 5",
        html: '<img src="' + __ctxPath + "/jbpmImage?piId=" + d + "&defId=" + a + "&rand=" + Math.random() + '"/>'
    });
    var e = this.getRightPanel(this.piId, this.runId);
    var f = new Ext.Toolbar({
        height: 28,
        items: [{
            text: "刷新",
            iconCls: "btn-refresh",
            handler: function() {
                c.body.update('<img src="' + __ctxPath + "/jbpmImage?piId=" + d + "&defId=" + a + "&rand=" + Math.random() + '"/>');
                e.doAutoLoad();
            }
        },{
        		id:this.runId+"flow_print",
 				text:'打印',
 				iconCls:'btn-print',
 				handler:function(){
 					startPrint(document.getElementById('divContext'+runId),name);
 				},
 				hidden:true
 			}
 		]
    });
    
    Ext.Ajax.request({
		url : __ctxPath + "/flow/canPrintProOtherDef.do?runId=" + this.runId,
		method : "POST",
		success : function(e, ff) {
			var d = Ext.util.JSON.decode(e.responseText);
			if(d.success){
				Ext.getCmp(this.runId+"flow_print").show()
			}else{
				Ext.getCmp(this.runId+"flow_print").hide()
			}
			
		},
		failure : function(d, e) {
			 Ext.getCmp(this.runId+"flow_print").hide()
		},
		scope : this
	});
    
    var b = new Ext.Panel({
        id: "ProcessRunDetail" + this.runId,
        title: "流程详细－" + this.name,
        iconCls: "menu-flowEnd",
        layout: "border",
        tbar: f,
        bodyStyle:'margin-bottom:4px;',
        autoScroll: true,
        items: [c, e]
    });
    return b;
};
ProcessRunDetail.prototype.getRightPanel = function(c, b) {
    var a = new Ext.Panel({
        title: "流程审批信息",
        region: "center",
        width: 400,
        autoScroll: true,
        autoLoad: {
            url: __ctxPath + "/flow/processRunDetail.do?piId=" + c + "&runId=" + b
        }
    });
    return a;
};