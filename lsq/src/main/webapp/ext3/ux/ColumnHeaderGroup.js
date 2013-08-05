/*
 * Ext JS Library 3.1.1 Copyright(c) 2006-2010 Ext JS, LLC licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.ns("Ext.ux.grid");
Ext.ux.grid.ColumnHeaderGroup = Ext.extend(Ext.util.Observable, {
	constructor : function(a) {
		this.config = a;
	},
	init : function(a) {
		Ext.applyIf(a.colModel, this.config);
		Ext.apply(a.getView(), this.viewConfig);
	},
	viewConfig : {
		initTemplates : function() {
			this.constructor.prototype.initTemplates.apply(this, arguments);
			var a = this.templates || {};
			if (!a.gcell) {
				a.gcell = new Ext.XTemplate('<td class="x-grid3-hd x-grid3-gcell x-grid3-td-{id} ux-grid-hd-group-row-{row} {cls}" style="{style}">',
				'<div {tooltip} class="x-grid3-hd-inner x-grid3-hd-{id}" unselectable="on" style="{istyle}">', this.grid.enableHdMenu
				? '<a class="x-grid3-hd-btn" href="#"></a>'
				: "", "{value}</div></td>");
			}
			this.templates = a;
			this.hrowRe = new RegExp("ux-grid-hd-group-row-(\\d+)", "");
		},
		renderHeaders : function() {
			var h = this.templates, d = [], l = this.cm, p = l.rows, j = "width:" + this.getTotalWidth() + ";";
			for (var o = 0, k = p.length; o < k; o++) {
				var a = p[o], n = [];
				for (var f = 0, e = 0, g = a.length; f < g; f++) {
					var m = a[f];
					m.colspan = m.colspan || 1;
					var b = this.getColumnId(m.dataIndex ? l.findColumnIndex(m.dataIndex) : e), c = Ext.ux.grid.ColumnHeaderGroup.prototype.getGroupStyle.call(
					this, m, e);
					n[f] = h.gcell.apply({
						cls : "ux-grid-hd-group-cell",
						id : b,
						row : o,
						style : "width:" + c.width + ";" + (c.hidden ? "display:none;" : "") + (m.align ? "text-align:" + m.align + ";" : ""),
						tooltip : m.tooltip ? (Ext.QuickTips.isEnabled() ? "ext:qtip" : "title") + '="' + m.tooltip + '"' : "",
						istyle : m.align == "right" ? "padding-right:16px" : "",
						btn : this.grid.enableHdMenu && m.header,
						value : m.header || "&nbsp;"
					});
					e += m.colspan;
				}
				d[o] = h.header.apply({
					tstyle : j,
					cells : n.join("")
				});
			}
			d.push(this.constructor.prototype.renderHeaders.apply(this, arguments));
			return d.join("");
		},
		onColumnWidthUpdated : function() {
			this.constructor.prototype.onColumnWidthUpdated.apply(this, arguments);
			Ext.ux.grid.ColumnHeaderGroup.prototype.updateGroupStyles.call(this);
		},
		onAllColumnWidthsUpdated : function() {
			this.constructor.prototype.onAllColumnWidthsUpdated.apply(this, arguments);
			Ext.ux.grid.ColumnHeaderGroup.prototype.updateGroupStyles.call(this);
		},
		onColumnHiddenUpdated : function() {
			this.constructor.prototype.onColumnHiddenUpdated.apply(this, arguments);
			Ext.ux.grid.ColumnHeaderGroup.prototype.updateGroupStyles.call(this);
		},
		getHeaderCell : function(a) {
			return this.mainHd.query(this.cellSelector)[a];
		},
		findHeaderCell : function(a) {
			return a ? this.fly(a).findParent("td.x-grid3-hd", this.cellSelectorDepth) : false;
		},
		findHeaderIndex : function(b) {
			var a = this.findHeaderCell(b);
			return a ? this.getCellIndex(a) : false;
		},
		updateSortIcon : function(b, a) {
			var d = this.sortClasses, c = this.mainHd.select(this.cellSelector).removeClass(d);
			c.item(b).addClass(d[a == "DESC" ? 1 : 0]);
		},
		handleHdDown : function(h, d) {
			var f = Ext.get(d);
			if (f.hasClass("x-grid3-hd-btn")) {
				h.stopEvent();
				var g = this.findHeaderCell(d);
				Ext.fly(g).addClass("x-grid3-hd-menu-open");
				var c = this.getCellIndex(g);
				this.hdCtxIndex = c;
				var b = this.hmenu.items, a = this.cm;
				b.get("asc").setDisabled(!a.isSortable(c));
				b.get("desc").setDisabled(!a.isSortable(c));
				this.hmenu.on("hide", function() {
					Ext.fly(g).removeClass("x-grid3-hd-menu-open");
				}, this, {
					single : true
				});
				this.hmenu.show(d, "tl-bl?");
			} else {
				if (f.hasClass("ux-grid-hd-group-cell") || Ext.fly(d).up(".ux-grid-hd-group-cell")) {
					h.stopEvent();
				}
			}
		},
		handleHdMove : function(h, d) {
			var g = this.findHeaderCell(this.activeHdRef);
			if (g && !this.headersDisabled && !Ext.fly(g).hasClass("ux-grid-hd-group-cell")) {
				var b = this.splitHandleWidth || 5, f = this.activeHdRegion, a = h.getPageX(), c = g.style, i = "";
				if (this.grid.enableColumnResize !== false) {
					if (a - f.left <= b && this.cm.isResizable(this.activeHdIndex - 1)) {
						i = Ext.isAir ? "move" : Ext.isWebKit ? "e-resize" : "col-resize";
					} else {
						if (f.right - a <= (!this.activeHdBtn ? b : 2) && this.cm.isResizable(this.activeHdIndex)) {
							i = Ext.isAir ? "move" : Ext.isWebKit ? "w-resize" : "col-resize";
						}
					}
				}
				c.cursor = i;
			}
		},
		handleHdOver : function(d, a) {
			var c = this.findHeaderCell(a);
			if (c && !this.headersDisabled) {
				this.activeHdRef = a;
				this.activeHdIndex = this.getCellIndex(c);
				var b = this.fly(c);
				this.activeHdRegion = b.getRegion();
				if (!(this.cm.isMenuDisabled(this.activeHdIndex) || b.hasClass("ux-grid-hd-group-cell"))) {
					b.addClass("x-grid3-hd-over");
					this.activeHdBtn = b.child(".x-grid3-hd-btn");
					if (this.activeHdBtn) {
						this.activeHdBtn.dom.style.height = (c.firstChild.offsetHeight - 1) + "px";
					}
				}
			}
		},
		handleHdOut : function(c, a) {
			var b = this.findHeaderCell(a);
			if (b && (!Ext.isIE || !c.within(b, true))) {
				this.activeHdRef = null;
				this.fly(b).removeClass("x-grid3-hd-over");
				b.style.cursor = "";
			}
		},
		handleHdMenuClick : function(q) {
			var l = this.hdCtxIndex, o = this.cm, d = this.ds, b = q.getItemId();
			switch (b) {
				case "asc" :
					d.sort(o.getDataIndex(l), "ASC");
					break;
				case "desc" :
					d.sort(o.getDataIndex(l), "DESC");
					break;
				default :
					if (b.substr(0, 5) == "group") {
						var h = b.split("-"), s = parseInt(h[1], 10), e = parseInt(h[2], 10), a = this.cm.rows[s], p, f = 0;
						for (var h = 0, k = a.length; h < k; h++) {
							p = a[h];
							if (e >= f && e < f + p.colspan) {
								break;
							}
							f += p.colspan;
						}
						if (q.checked) {
							var m = o.getColumnsBy(this.isHideableColumn, this).length;
							for (var h = f, k = f + p.colspan; h < k; h++) {
								if (!o.isHidden(h)) {
									m--;
								}
							}
							if (m < 1) {
								this.onDenyColumnHide();
								return false;
							}
						}
						for (var h = f, k = f + p.colspan; h < k; h++) {
							if (o.config[h].fixed !== true && o.config[h].hideable !== false) {
								o.setHidden(h, q.checked);
							}
						}
					} else {
						l = o.getIndexById(b.substr(4));
						if (l != -1) {
							if (q.checked && o.getColumnsBy(this.isHideableColumn, this).length <= 1) {
								this.onDenyColumnHide();
								return false;
							}
							o.setHidden(l, q.checked);
						}
					}
					q.checked = !q.checked;
					if (q.menu) {
						var j = function(i) {
							i.items.each(function(r) {
								if (!r.disabled) {
									r.setChecked(q.checked, false);
									if (r.menu) {
										j(r.menu);
									}
								}
							});
						};
						j(q.menu);
					}
					var g = q, c;
					while (g = g.parentMenu) {
						if (!g.parentMenu || !(c = g.parentMenu.items.get(g.getItemId())) || !c.setChecked) {
							break;
						}
						var n = g.items.findIndexBy(function(i) {
							return i.checked;
						}) >= 0;
						c.setChecked(n, true);
					}
					q.checked = !q.checked;
			}
			return true;
		},
		beforeColMenuShow : function() {
			var h = this.cm, j = this.cm.rows;
			this.colMenu.removeAll();
			for (var d = 0, m = h.getColumnCount(); d < m; d++) {
				var a = this.colMenu, x = h.getColumnHeader(d), l = [];
				if (h.config[d].fixed !== true && h.config[d].hideable !== false) {
					for (var e = 0, s = j.length; e < s; e++) {
						var n = j[e], g, p = 0;
						for (var t = 0, u = n.length; t < u; t++) {
							g = n[t];
							if (d >= p && d < p + g.colspan) {
								break;
							}
							p += g.colspan;
						}
						if (g && g.header) {
							if (h.hierarchicalColMenu) {
								var o = "group-" + e + "-" + p;
								var v = a.items.item(o);
								var k = v ? v.menu : null;
								if (!k) {
									k = new Ext.menu.Menu({
										itemId : o
									});
									k.on("itemclick", this.handleHdMenuClick, this);
									var f = false, b = true;
									for (var w = p, q = p + g.colspan; w < q; w++) {
										if (!h.isHidden(w)) {
											f = true;
										}
										if (h.config[w].hideable !== false) {
											b = false;
										}
									}
									a.add({
										itemId : o,
										text : g.header,
										menu : k,
										hideOnClick : false,
										checked : f,
										disabled : b
									});
								}
								a = k;
							} else {
								l.push(g.header);
							}
						}
					}
					l.push(x);
					a.add(new Ext.menu.CheckItem({
						itemId : "col-" + h.getColumnId(d),
						text : l.join(" "),
						checked : !h.isHidden(d),
						hideOnClick : false,
						disabled : h.config[d].hideable === false
					}));
				}
			}
		},
		renderUI : function() {
			this.constructor.prototype.renderUI.apply(this, arguments);
			Ext.apply(this.columnDrop, Ext.ux.grid.ColumnHeaderGroup.prototype.columnDropConfig);
			Ext.apply(this.splitZone, Ext.ux.grid.ColumnHeaderGroup.prototype.splitZoneConfig);
		}
	},
	splitZoneConfig : {
		allowHeaderDrag : function(a) {
			return !a.getTarget(null, null, true).hasClass("ux-grid-hd-group-cell");
		}
	},
	columnDropConfig : {
		getTargetFromEvent : function(b) {
			var a = Ext.lib.Event.getTarget(b);
			return this.view.findHeaderCell(a);
		},
		positionIndicator : function(b, f, d) {
			var c = Ext.ux.grid.ColumnHeaderGroup.prototype.getDragDropData.call(this, b, f, d);
			if (c === false) {
				return false;
			}
			var a = c.px + this.proxyOffsets[0];
			this.proxyTop.setLeftTop(a, c.r.top + this.proxyOffsets[1]);
			this.proxyTop.show();
			this.proxyBottom.setLeftTop(a, c.r.bottom);
			this.proxyBottom.show();
			return c.pt;
		},
		onNodeDrop : function(o, A, v, C) {
			var u = C.header;
			if (u != o) {
				var x = Ext.ux.grid.ColumnHeaderGroup.prototype.getDragDropData.call(this, u, o, v);
				if (x === false) {
					return false;
				}
				var j = this.grid.colModel, z = x.oldIndex < x.newIndex, k = j.rows;
				for (var f = x.row, p = k.length; f < p; f++) {
					var l = k[f], t = l.length, B = 0, q = 1, D = t;
					for (var s = 0, m = 0; s < t; s++) {
						var g = l[s];
						if (x.oldIndex >= m && x.oldIndex < m + g.colspan) {
							B = s;
						}
						if (x.oldIndex + x.colspan - 1 >= m && x.oldIndex + x.colspan - 1 < m + g.colspan) {
							q = s - B + 1;
						}
						if (x.newIndex >= m && x.newIndex < m + g.colspan) {
							D = s;
						}
						m += g.colspan;
					}
					var w = l.splice(B, q);
					k[f] = l.splice(0, D - (z ? q : 0)).concat(w).concat(l);
				}
				for (var y = 0; y < x.colspan; y++) {
					var b = x.oldIndex + (z ? 0 : y), a = x.newIndex + (z ? -1 : y);
					j.moveColumn(b, a);
					this.grid.fireEvent("columnmove", b, a);
				}
				return true;
			}
			return false;
		}
	},
	getGroupStyle : function(g, c) {
		var e = 0, f = true;
		for (var d = c, a = c + g.colspan; d < a; d++) {
			if (!this.cm.isHidden(d)) {
				var b = this.cm.getColumnWidth(d);
				if (typeof b == "number") {
					e += b;
				}
				f = false;
			}
		}
		return {
			width : (Ext.isBorderBox || (Ext.isWebKit && !Ext.isSafari2) ? e : Math.max(e - this.borderWidth, 0)) + "px",
			hidden : f
		};
	},
	updateGroupStyles : function(b) {
		var g = this.mainHd.query(".x-grid3-header-offset > table"), e = this.getTotalWidth(), k = this.cm.rows;
		for (var j = 0; j < g.length; j++) {
			g[j].style.width = e;
			if (j < k.length) {
				var h = g[j].firstChild.firstChild.childNodes;
				for (var d = 0, c = 0; d < h.length; d++) {
					var f = k[j][d];
					if ((typeof b != "number") || (b >= c && b < c + f.colspan)) {
						var a = Ext.ux.grid.ColumnHeaderGroup.prototype.getGroupStyle.call(this, f, c);
						h[d].style.width = a.width;
						h[d].style.display = a.hidden ? "none" : "";
					}
					c += f.colspan;
				}
			}
		}
	},
	getGroupRowIndex : function(b) {
		if (b) {
			var a = b.className.match(this.hrowRe);
			if (a && a[1]) {
				return parseInt(a[1], 10);
			}
		}
		return this.cm.rows.length;
	},
	getGroupSpan : function(g, c) {
		if (g < 0) {
			return {
				col : 0,
				colspan : this.cm.getColumnCount()
			};
		}
		var e = this.cm.rows[g];
		if (e) {
			for (var d = 0, b = 0, a = e.length; d < a; d++) {
				var f = e[d];
				if (c >= b && c < b + f.colspan) {
					return {
						col : b,
						colspan : f.colspan
					};
				}
				b += f.colspan;
			}
			return {
				col : b,
				colspan : 0
			};
		}
		return {
			col : c,
			colspan : 1
		};
	},
	getDragDropData : function(f, d, g) {
		if (f.parentNode != d.parentNode) {
			return false;
		}
		var m = this.grid.colModel, k = Ext.lib.Event.getPageX(g), a = Ext.lib.Dom.getRegion(d.firstChild), l, q;
		if ((a.right - k) <= (a.right - a.left) / 2) {
			l = a.right + this.view.borderWidth;
			q = "after";
		} else {
			l = a.left;
			q = "before";
		}
		var j = this.view.getCellIndex(f), o = this.view.getCellIndex(d);
		if (m.isFixed(o)) {
			return false;
		}
		var p = Ext.ux.grid.ColumnHeaderGroup.prototype.getGroupRowIndex.call(this.view, f), b = Ext.ux.grid.ColumnHeaderGroup.prototype.getGroupSpan.call(
		this.view, p, j), c = Ext.ux.grid.ColumnHeaderGroup.prototype.getGroupSpan.call(this.view, p, o), j = b.col;
		o = c.col + (q == "after" ? c.colspan : 0);
		if (o >= b.col && o <= b.col + b.colspan) {
			return false;
		}
		var i = Ext.ux.grid.ColumnHeaderGroup.prototype.getGroupSpan.call(this.view, p - 1, j);
		if (o < i.col || o > i.col + i.colspan) {
			return false;
		}
		return {
			r : a,
			px : l,
			pt : q,
			row : p,
			oldIndex : j,
			newIndex : o,
			colspan : b.colspan
		};
	}
});

/**
* 修订 Ext.ux.grid.ColumnHeaderGroup 出现选择框图片的bug
*
*/
Ext.override(Ext.ux.grid.ColumnHeaderGroup, {
    viewConfig : Ext.apply(Ext.ux.grid.ColumnHeaderGroup.prototype.viewConfig, {
        renderHeaders : function() {
            var ts = this.templates,
                headers = [],
                cm = this.cm,
                rows = cm.rows,
                tstyle = 'width:' + this.getTotalWidth() + ';';

            for (var row = 0, rlen = rows.length; row < rlen; row++) {
                var r = rows[row],
                    cells = [];
                for (var i = 0, gcol = 0, len = r.length; i < len; i++) {
                    var group = r[i];
                    group.colspan = group.colspan || 1;
                    var id = this.getColumnId(group.dataIndex ? cm.findColumnIndex(group.dataIndex) : gcol),
                        gs = Ext.ux.grid.ColumnHeaderGroup.prototype.getGroupStyle.call(this, group, gcol);
                    cells[i] = ts.gcell.apply({
                        cls : 'ux-grid-hd-group-cell',
                        id : i === 0 ? (id === 'checker' ? 'rekcehc' : id) : id,
                        row : row,
                        style : 'width:' + gs.width + ';' + (gs.hidden ? 'display:none;' : '')
                            + (group.align ? 'text-align:' + group.align + ';' : ''),
                        tooltip : group.tooltip ? (Ext.QuickTips.isEnabled() ? 'ext:qtip' : 'title') + '="'
                            + group.tooltip + '"' : '',
                        istyle : group.align == 'right' ? 'padding-right:16px' : '',
                        btn : this.grid.enableHdMenu && group.header,
                        value : group.header || '&nbsp;'
                    });
                    gcol += group.colspan;
                }
                headers[row] = ts.header.apply({
                    tstyle : tstyle,
                    cells : cells.join('')
                });
            }
            headers.push(this.constructor.prototype.renderHeaders.apply(this, arguments));
            return headers.join('');
        }
    })
});

Ext.override(Ext.grid.ColumnModel, {
    isMenuDisabled : function(col) {
        if (this.config[col]) {
            return !!this.config[col].menuDisabled; //非吾所愿，加上的，囧
        }
        return false;
    }
});