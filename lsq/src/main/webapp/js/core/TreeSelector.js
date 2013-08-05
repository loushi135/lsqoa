var TreeSelector = function(g, f, h, e, c,wid,hiddenName,onlySelectLeaf,hasOtherAttr,otherAttr) {
	var d= {};
	var osf = onlySelectLeaf == true ? true : false;
	if(typeof(wid)!=undefined){
		 d = {
			id : g,
			store : new Ext.data.SimpleStore({
						fields : [],
						data : [[]]
					}),
			editable : false,
			mode : "local",
			fieldLabel : h,
			hiddenName:hiddenName,
			allowBlank : c == false ? false : true,
			triggerAction : "all",
			maxHeight : 200,
			width:wid,
			tpl : "<tpl for='.'><div style='height:200px'><div id='" + g
					+ "tree'></div></div></tpl>",
			selectedClass : "",
			onSelect : Ext.emptyFn,
			onViewClick : function(doFocus) {//解决 点击 +展开  面板关闭 2013/1/22
				var index = this.view.getSelectedIndexes()[0], s = this.store, r = s.getAt(index);
				if (r) {
					this.onSelect(r, index);
				}
				if (doFocus !== false) {
					this.el.focus();
				}
			}
		};
	}else{
		d = {
			id : g,
			store : new Ext.data.SimpleStore({
						fields : [],
						data : [[]]
					}),
			editable : false,
			mode : "local",
			fieldLabel : h,
			hiddenName:hiddenName,
			allowBlank : c == false ? false : true,
			triggerAction : "all",
			maxHeight : 200,
			tpl : "<tpl for='.'><div style='height:200px'><div id='" + g
					+ "tree'></div></div></tpl>",
			selectedClass : "",
			onSelect : Ext.emptyFn,
			onViewClick : function(doFocus) {//解决 点击 +展开  面板关闭 2013/1/22
				var index = this.view.getSelectedIndexes()[0], s = this.store, r = s.getAt(index);
				if (r) {
					this.onSelect(r, index);
				}
				if (doFocus !== false) {
					this.el.focus();
				}
			}
		};
	}
	var b = new Ext.form.ComboBox(d);
	var a = new Ext.tree.TreePanel({
				id : g + "Tree",
				height : 200,
				autoScroll : true,
				split : true,
				loader : new Ext.tree.TreeLoader({
							url : f
						}),
				root : new Ext.tree.AsyncTreeNode({
							expanded : true
						}),
				rootVisible : false
			});
 	
	a.on("click", function(j) {
			var hasOther = hasOtherAttr == true ? true : false;
			var otherAttribute = "";
			if(!Ext.isEmpty(otherAttr)){
				otherAttribute = otherAttr;
			}
			var attrValue = '';
			if(hasOther){
				attrValue = eval('j.attributes.'+otherAttribute);
//				console.log('attrValue='+attrValue);
			}
			if(osf){
					var i = Ext.getCmp(e);
					if (j.id != null && j.id != "") {
						b.setValue(j.text);
						b.id = j.id;
						b.collapse();
						if (i != null) {
							i.setValue(hasOther?attrValue:j.id);
						}
					}
			}else{
					var i = Ext.getCmp(e)
					if (j.id != null && j.id != "") {
						if(j.id == -1){
							Ext.ux.Toast.msg("操作信息", "不能选择最顶级分类，请选择合适的子类");
						}else{
							b.setValue(j.text);
							b.id = j.id;
							b.collapse();
							if (i != null) {
								i.setValue(hasOther?attrValue:j.id);
							}
						}
					}
			}
	});
	b.on("expand", function() {
				a.render(g + "tree");
			});
	return b;
};