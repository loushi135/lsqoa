Ext.form.CKEditor = function(a) {
	this.config = a;
	Ext.form.CKEditor.superclass.constructor.call(this, a);
};
Ext.form.CKEditor.CKEDITOR_CONFIG = __ctxPath + "/js/ckeditor/config.js";
Ext.form.CKEditor.CKEDITOR_TOOLBAR = "JDefault";
Ext.extend(Ext.form.CKEditor, Ext.form.TextArea, {
			onRender : function(c, a) {
				if (!this.el) {
					this.defaultAutoCreate = {
						tag : "textarea",
						autocomplete : "off"
					};
				}
				Ext.form.TextArea.superclass.onRender.call(this, c, a);
				var b = {
					customConfig : Ext.form.CKEditor.CKEDITOR_CONFIG,
					toolbar : Ext.form.CKEditor.CKEDITOR_TOOLBAR,
					toolbarStartupExpanded : false
				};
				Ext.apply(b, this.config);
				if (!b.height) {
					b.height = 150;
				}
				var d = CKEDITOR.replace(this.id, b);
				CKFinder.setupCKEditor(d, __ctxPath + "/js/ckeditor/ckfinder/");
			},
			onDestroy : function() {
				if (CKEDITOR.instances[this.id]) {
					delete CKEDITOR.instances[this.id];
				}
			},
			setValue : function(a) {
				Ext.form.TextArea.superclass.setValue.apply(this, [a]);
				CKEDITOR.instances[this.id].setData(a);
			},
			getValue : function() {
				CKEDITOR.instances[this.id].updateElement();
				var a = CKEDITOR.instances[this.id].getData();
				Ext.form.TextArea.superclass.setValue.apply(this, [a]);
				return Ext.form.TextArea.superclass.getValue.call(this);
			},
			getRawValue : function() {
				CKEDITOR.instances[this.id].updateElement();
				return Ext.form.TextArea.superclass.getRawValue.call(this);
			}
		});
Ext.reg("ckeditor", Ext.form.CKEditor);