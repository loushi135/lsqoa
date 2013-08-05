var FlowManagerView = function() {
    return this.getView();
};
FlowManagerView.prototype.getView = function() {
    var f;
    var d = new ProDefinitionView(true);
    var c = new Ext.tree.TreePanel({
        id: "proTypeLeftPanel",
        region: "west",
        title: "流程分类",
        collapsible: true,
        split: true,
        width: 150,
        height: 900,
        tbar: new Ext.Toolbar({
            id: "proTypeAdd",
            items: [{
                xtype: "button",
                iconCls: "btn-refresh",
                text: "刷新",
                handler: function() {
                    c.root.reload();
                }
            }]
        }),
        loader: new Ext.tree.TreeLoader({
            url: __ctxPath + "/flow/rootProType.do"
        }),
        root: new Ext.tree.AsyncTreeNode({
            expanded: true
        }),
        rootVisible: false,
        listeners: {
            "click": function(k) {
                d.setTypeId(k.id);
                var j = Ext.getCmp("ProDefinitionGrid");
                j.getStore().proxy.conn.url = __ctxPath + "/flow/listProDefinition.do?typeId=" + k.id;
                j.getStore().load({
                    params: {
                        start: 0,
                        limit: 25
                    }
                });
            }
        }
    });
    function b(j, k) {
        f = new Ext.tree.TreeNode({
            id: j.id,
            text: j.text
        });
        g.showAt(k.getXY());
    }
    if (isGranted("_ProTypeManage")) {
        Ext.getCmp("proTypeAdd").add(new Ext.Button({
            text: "添加分类",
            iconCls: "btn-add",
            handler: function() {
                new ProTypeForm();
            }
        }));
        c.on("contextmenu", b, c);
    }
    var g = new Ext.menu.Menu({
        id: "FlowManagerTreeMenu",
        items: [{
            text: "新建分类",
            scope: this,
            iconCls: "btn-add",
            handler: i
        },
        {
            text: "修改分类",
            scope: this,
            iconCls: "btn-edit",
            handler: h
        },
        {
            text: "删除分类",
            scope: this,
            iconCls: "btn-delete",
            handler: e
        }]
    });
    function i() {
        new ProTypeForm();
    }
    function h() {
        var j = f.id;
        new ProTypeForm(j);
    }
    function e() {
        var j = f.id;
        $request({
            url: __ctxPath + "/flow/removeProType.do",
            params: {
                typeId: j
            },
            success: function(k, l) {
                c.root.reload();
            }
        });
    }
    var a = new Ext.Panel({
        title: "流程管理",
        layout: "border",
        iconCls: "menu-flowManager",
        id: "FlowManagerView",
        height: 800,
        items: [c, d.getView()]
    });
    return a;
};