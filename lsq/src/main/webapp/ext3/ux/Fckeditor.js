Ext.namespace("Ext.ux.form");
function setTimeoutEx(c, a) {
	if (typeof c == "function") {
		var b = Array.prototype.slice.call(arguments, 2);
		var d = function() {
			c.apply(null, b);
		};
		return setTimeout(d, a);
	}
	return setTimeout(c, a);
}
Ext.ux.form.FCKeditor = function(a) {
	if (a.id == null) {
		a.id = "__fckeditor_" + parseInt(1000 * Math.random());
	}
	this.config = a;
	this.fckconfig = a;
	Ext.ux.form.FCKeditor.superclass.constructor.call(this, a);
	this.instanceLoaded = false;
	this.instanceValue = "";
	this.editorInstance = undefined;
};
Ext.extend(Ext.ux.form.FCKeditor, Ext.form.TextArea, {
			initEvents : function() {
				this.on("destroy", function() {
							if (typeof this.editorInstance != "undefined") {
								delete this.editorInstance;
							}
						});
			},
			onRender : function(b, a) {
				if (!this.el) {
					this.defaultAutoCreate = {
						tag : "textarea",
						style : "width:300px;height:660px;",
						autocomplete : "off"
					};
				}
				Ext.form.TextArea.superclass.onRender.call(this, b, a);
				this.hideMode = "visibility";
//				this.hidden = true;
				if (this.grow) {
					this.textSizeEl = Ext.DomHelper.append(document.body, {
								tag : "pre",
								cls : "x-form-grow-sizer"
							});
					if (this.preventScrollbars) {
						this.el.setStyle("overflow", "hidden");
					}
					this.el.setHeight(this.growMin);
				}
				setTimeoutEx(loadFCKeditor, 100, this.config.id, this.fckconfig);
			},
			setIsLoaded : function(a) {
				this.instanceLoaded = a;
			},
			getIsLoaded : function() {
				return this.instanceLoaded;
			},
			setValue : function(a) {
				this.instanceValue = a;
				if (this.instanceLoaded) {
					this.FCKeditorSetValue(a);
				}
				Ext.form.TextArea.superclass.setValue.apply(this, [a]);
			},
			getValue : function() {
				if (this.instanceLoaded) {
					value = this.FCKeditorGetValue();
					Ext.form.TextArea.superclass.setValue.apply(this, [value]);
					return Ext.form.TextArea.superclass.getValue.call(this);
				} else {
					return this.instanceValue;
				}
			},
			getRawValue : function() {
				if (this.instanceLoaded) {
					value = this.FCKeditorGetValue();
					Ext.form.TextArea.superclass.setRawValue.apply(this,
							[value]);
					return Ext.form.TextArea.superclass.getRawValue.call(this);
				} else {
					return this.instanceValue;
				}
			},
			FCKeditorSetValue : function(c) {
				if (this.instanceLoaded == false) {
					return;
				}
				var b = new Ext.util.TaskRunner();
				var a = {
					run : function() {
						try {
							var e = this.editorInstance;
							if (e.EditorDocument.body) {
								e.SetData(c);
								b.stop(a);
							}
						} catch (d) {
						}
					},
					interval : 100,
					scope : this
				};
				b.start(a);
			},
			FCKeditorGetValue : function() {
				var a = "";
				if (this.instanceLoaded == false) {
					return a;
				}
				a = this.editorInstance.GetData();
				return a;
			},
			isDirty : function() {
				return this.editorInstance.IsDirty();
			},
			resetIsDirty : function() {
				this.editorInstance.ResetIsDirty();
			}
		});
Ext.reg("fckeditor", Ext.ux.form.FCKeditor);
function loadFCKeditor(b, a) {
	$ImportSimpleJs([__ctxPath + "/js/fckeditor/fckeditor.js"], function() {
				var c = new FCKeditor(b, a.width, a.height);
				for (var d in a) {
					if (typeof c[d] != "undefined") {
						c[d] = a[d];
					}
				}
				c.BasePath = __ctxPath + "/js/fckeditor/";
				c.ToolbarSet = "Custom";
				c.ReplaceTextarea();
			});
}
function FCKeditor_OnComplete(a) {
	var b = Ext.getCmp(a.Name);
	b.editorInstance = a;
	b.instanceLoaded = true;
	b.FCKeditorSetValue(b.instanceValue);
	a.Events.AttachEvent("OnBlur", FCKeditor_OnBlur);
	a.Events.AttachEvent("OnFocus", FCKeditor_OnFocus);
	a.ToolbarSet.Collapse();
}
function FCKeditor_OnBlur(a) {
	a.ToolbarSet.Collapse();
}
function FCKeditor_OnFocus(a) {
	a.ToolbarSet.Expand();
}