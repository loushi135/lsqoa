FormEditorWindow = Ext.extend(Ext.Window, {
    constructor: function(b) {
        Ext.apply(this, b);
        var a = new Ext.Panel({
            title: "VM表单源代码",
            layout: "fit",
            items: [{
                xtype: "textarea",
                anchor: "98%,98%",
                height: 420,
                name: "vmSources",
                id: "vmSources"
            }]
        });
        var c = new Ext.Panel({
            title: "XML表单源代码",
            layout: "fit",
            items: [{
                xtype: "textarea",
                anchor: "98%,98%",
                height: 420,
                name: "xmlSources",
                id: "xmlSources"
            }]
        });
        this.tabPanel = new Ext.TabPanel({
            activeTab: 0,
            border: false,
            defaults: {
                border: false
            },
            items: [a, c]
        });
        this.buttons = [{
            text: "保存",
            iconCls: "btn-save",
            scope: this,
            handler: this.onSave
        },
        {
            text: "取消",
            iconCls: "btn-cancel",
            scope: this,
            handler: this.onCancel
        }];
        FormEditorWindow.superclass.constructor.call(this, {
            title: "流程表单--源代码",
            iconCls: "btn-form-tag",
            layout: "fit",
            border: false,
            height: 500,
            width: 800,
            modal: true,
            maximizable: true,
            buttonAlign: "center",
            items: this.tabPanel
        });
    },
    show: function() {
        FormEditorWindow.superclass.show.call(this);
        Ext.Ajax.request({
            url: __ctxPath + "/flow/getVmXmlFormDef.do",
            params: {
                deployId: this.deployId,
                activityName: this.activityName
            },
            method: "POST",
            success: function(b, c) {
                var a = Ext.util.JSON.decode(b.responseText);
                Ext.getCmp("vmSources").setValue(a.vmSources);
                Ext.getCmp("xmlSources").setValue(a.xmlSources);
            },
            failure: function(a, b) {
                Ext.ux.Toast.msg("操作信息", "操作出错，请联系管理员！");
            }
        });
    },
    onSave: function() {
        var b = this;
        var c = Ext.getCmp("vmSources").getValue();
        var a = Ext.getCmp("xmlSources").getValue();
        Ext.Ajax.request({
            url: __ctxPath + "/flow/saveVmXmlFormDef.do",
            params: {
                deployId: this.deployId,
                activityName: this.activityName,
                vmSources: c,
                xmlSources: a
            },
            method: "POST",
            success: function(d, e) {
                Ext.ux.Toast.msg("操作信息", "成功保存该流程表单定义！");
                b.close();
            },
            failure: function(d, e) {
                Ext.ux.Toast.msg("操作信息", "操作出错，请联系管理员！");
            }
        });
    },
    onCancel: function() {
        this.close();
    }
});