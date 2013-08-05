Ext.ux.Printer = function() {
	return {
		renderers : {},
		registerRenderer : function(b, a) {
			this.renderers[b] = new (a)();
		},
		getRenderer : function(a) {
			return this.renderers[a];
		},
		print : function(a) {
			var d = a.getXTypes().split("/");
			for (var b = d.length - 1; b >= 0; b--) {
				var e = d[b], c = this.getRenderer(e);
				if (c != undefined) {
					c.print(a);
					break;
				}
			}
		}
	};
}();
Ext.override(Ext.Component, {
	getXTypes : function() {
		var a = this.constructor;
		if (!a.xtypes) {
			var e = [], b = this;
			while (b) {
				var d = b.constructor.xtype;
				if (d != undefined) {
					e.unshift(d);
				}
				b = b.constructor.superclass;
			}
			a.xtypeChain = e;
			a.xtypes = e.join("/");
		}
		return a.xtypes;
	}
});
Ext.ux.Printer.BaseRenderer = Ext.extend(Object, {
	print : function(b) {
		var a = b && b.getXType ? String.format("print_{0}_{1}", b.getXType(), b.id) : "print";
		var c = window.open("", a);
		c.document.write(this.generateHTML(b));
		c.document.close();
		c.print();
		c.close();
	},
	generateHTML : function(a) {
		return new Ext.XTemplate('<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">',
		"<html>", "<head>", '<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />', '<link href="' + this.stylesheetPath
		+ '" rel="stylesheet" type="text/css" media="screen,print" />', "<title>" + this.getTitle(a) + "</title>", "</head>", "<body>", this.generateBody(a),
		"</body>", "</html>").apply(this.prepareData(a));
	},
	generateBody : Ext.emptyFn,
	prepareData : function(a) {
		return a;
	},
	getTitle : function(a) {
		return typeof a.getTitle == "function" ? a.getTitle() : (a.title || "Printing");
	},
	stylesheetPath : __ctxPath + "/css/print.css"
});
Ext.ux.Printer.ColumnTreeRenderer = Ext.extend(Ext.ux.Printer.BaseRenderer, {
	generateBody : function(b) {
		var c = this.getColumns(b);
		var d = this.headerTpl.apply(c);
		var a = this.bodyTpl.apply(c);
		return String.format('<table>{0}<tpl for=".">{1}</tpl></table>', d, a);
	},
	getColumns : function(a) {
		return a.columns;
	},
	prepareData : function(a) {
		var b = a.root, d = [], g = this.getColumns(a), e = this.indentPadding;
		var c = function(f) {
			if (f.hidden === true || f.isHiddenRoot() === true) {
				return;
			}
			var h = Ext.apply({
				depth : f.getDepth() * e
			}, f.attributes);
			Ext.iterate(h, function(i, j) {
				Ext.each(g, function(k) {
					if (k.dataIndex == i) {
						h[i] = k.renderer ? k.renderer(j) : j;
					}
				}, this);
			});
			h[this.getColumns(a)[0].dataIndex] = f.attributes.text;
			d.push(h);
		};
		b.cascade(c, this);
		return d;
	},
	indentPadding : 15,
	headerTpl : new Ext.XTemplate("<tr>", '<tpl for=".">', '<th width="{width}">{header}</th>', "</tpl>", "</tr>"),
	bodyTpl : new Ext.XTemplate("<tr>", '<tpl for=".">', '<td style="padding-left: {[xindex == 1 ? "\\{depth\\}" : "0"]}px">{{dataIndex}}</td>', "</tpl>",
	"</tr>")
});
Ext.ux.Printer.registerRenderer("columntree", Ext.ux.Printer.ColumnTreeRenderer);
Ext.ux.Printer.GridPanelRenderer = Ext.extend(Ext.ux.Printer.BaseRenderer, {
	generateBody : function(c) {
		var b = this.getColumns(c);
		var d = this.headerTpl.apply(b);
		var a = this.bodyTpl.apply(b);
		return String.format('<table>{0}<tpl for=".">{1}</tpl></table>', d, a);
	},
	prepareData : function(b) {
		var a = this.getColumns(b);
		var c = [];
		b.store.data.each(function(d) {
			var e = {};
			Ext.iterate(d.data, function(f, g) {
				Ext.each(a, function(h) {
					if (h.dataIndex == f) {
						e[f] = h.renderer ? h.renderer(g, null, d) : g;
						return false;
					}
				}, this);
			});
			c.push(e);
		});
		return c;
	},
	getColumns : function(b) {
		var a = [];
		Ext.each(b.getColumnModel().config, function(c) {
			if (c.hidden != true) {
				a.push(c);
			}
		}, this);
		return a;
	},
	headerTpl : new Ext.XTemplate("<tr>", '<tpl for=".">', "<th>{header}</th>", "</tpl>", "</tr>"),
	bodyTpl : new Ext.XTemplate("<tr>", '<tpl for=".">', "<td>{{dataIndex}}</td>", "</tpl>", "</tr>")
});
Ext.ux.Printer.registerRenderer("grid", Ext.ux.Printer.GridPanelRenderer);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Customizations.
//http://github.com/edspencer/Ext.ux.Printer/

//Ext.ux.Printer.BaseRenderer.prototype.stylesheetPath = '/sites/default/files/content/blog/extjs-printer/Ext.ux.Printer.css';

Ext.override(Ext.ux.Printer.GridPanelRenderer, {
    bodyTpl: new Ext.XTemplate(
        '<tr>',
          '<tpl for=".">',
            '<tpl if="dataIndex"><td>\{{dataIndex}\}</td></tpl>',
            '<tpl if="!dataIndex"><td>&nbsp;</td></tpl>',
          '</tpl>',
        '</tr>'
  )
});

Ext.ux.Printer.GroupedGridPanelRenderer = Ext.extend(Ext.ux.Printer.GridPanelRenderer, {

    generateBody: function(grid) {
        var view = grid.view;

        if (view instanceof Ext.grid.GroupingView && view.canGroup()) {
            this.overrideForGrouping(grid);

            var columns = this.getColumns(grid);

            this.bodyTpl.numColumns = this.getColumns(grid).length;
            var cells = this.bodyTpl.cellTpl.apply(columns);
            this.bodyTpl.innerTemplate = String.format('<tpl for="groupRecords"><tr>{0}</tr></tpl>', cells);

            if (grid.hasPlugin(Ext.grid.GroupSummary)) {
                var summaryCells = this.bodyTpl.groupSummaryCellTemplate.apply(columns);
                this.bodyTpl.groupSummaryTemplate = String.format('<table class=\'group-summary\'><tpl for="summaries"><tr>{0}</tr></tpl></table>', summaryCells);
            } else {
                this.bodyTpl.groupSummaryTemplate = '';
            }

            var headings = this.headerTpl.apply(columns);
            var body = this.bodyTpl.apply({});

            return (String.format('<table class=\'table-parent\'>{0}<tpl for=".">{1}</tpl>{2}</table>', headings, body, this.generateGridTotals(grid)));

        } else {
            //No grouping, use base class logic.
            return (Ext.ux.Printer.GroupedGridPanelRenderer.superclass.generateBody.call(this, grid));
        }
    },

    gridTotalsTpl: new Ext.XTemplate(
                '<tr>',
                  '<td colspan=\'{[values.length]}\'>',
                    '<table class=\'grid-totals\'>',
                        '<tr>',
                          '<tpl for=".">',
                            '<td style=\'{style}\'>{total}</td>',
                          '</tpl>',
                        '</tr>',
                    '</table>',
                  '</td>',
                '</tr>'
              ),

    generateGridTotals: function(grid) {
        var plugin = grid.getPluginByType(Ext.ux.GridTotals);
        if (Ext.isEmpty(plugin)) {
            return ('');
        } else {
            var totals = plugin.getRenderedTotals();
            var columns = this.getColumns(grid);

            var rendered = new Array(columns.length);
            for (var i = 0; i < columns.length; i++) {
                var col = columns[i];
                rendered[i] = { total: totals[col.actualIndex], style: columns[i].style };
            }
            return (this.gridTotalsTpl.apply(rendered));
        }
    },

    overrideForGrouping: function(grid) {
        var me = this;

        Ext.apply(this, {
            columns: null,

            headerTpl: new Ext.XTemplate(
                '<tr>',
                  '<tpl for=".">',
                    '<th style=\'{style}\'>{header}</th>',
                  '</tpl>',
                '</tr>'
              ),

            bodyTpl: new Ext.XTemplate(
                '<tr>',
                  '<td colspan=\'{[this.getColumnCount()]}\'>',
                    '<div class=\'group-header\'>{[this.getGroupTextTemplate()]}</div>',
                    '<table class=\'group-body\'>',
                      '{[this.getInnerTemplate()]}',
                    '</table>',
                    '{[this.getGroupSummaryTemplate()]}',
                  '</td>',
                '</tr>',

                {
                    numColumns: 0,
                    cellTpl: new Ext.XTemplate('<tpl for="."><td style=\'{style}\'>\{{dataIndex}\}</td></tpl>'),
                    groupSummaryCellTemplate: new Ext.XTemplate('<tpl for="."><td style=\'{style}\'>\{{dataIndex}\}</td></tpl>'),
                    innerTemplate: null,
                    groupSummaryTemplate: null,

                    getColumnCount: function() {
                        return (this.numColumns);
                    },

                    getGroupTextTemplate: function() {
                        return ('{groupText}');
                    },

                    getInnerTemplate: function() {
                        return (this.innerTemplate);
                    },

                    getGroupSummaryTemplate: function() {
                        return (this.groupSummaryTemplate);
                    }
                }),

            prepareData: function(grid) {
                var view = grid.view;

                var columns = this.getColumns(grid);
                var groups = this.getGroupedData(grid);
                var groupTextTpl = new Ext.XTemplate(view.groupTextTpl);

                Ext.each(groups, function(group) {
                    var groupRecords = [];
                    group.groupText = groupTextTpl.apply(group);

                    Ext.each(group.rs, function(item) {
                        var convertedData = {};

                        //apply renderers from column model
                        Ext.iterate(item.data, function(key, value) {
                            Ext.each(columns, function(column) {
                                if (column.dataIndex == key) {
                                    convertedData[key] = column.renderer ? column.renderer(value, null, item) : value;
                                    return false;
                                }
                            }, this);
                        });

                        groupRecords.push(convertedData);
                    });

                    group.groupRecords = groupRecords;

                    var summaryRenderer = grid.getPluginByType(Ext.grid.GroupSummary);
                    if (!Ext.isEmpty(summaryRenderer)) {
                        //Summary calculation for column in each group.
                        var cs = view.getColumnData();
                        group.summaries = {};
                        var data = summaryRenderer.calculate(group.rs, cs);

                        Ext.each(columns, function(col) {
                            var rendered = '';
                            if (col.summaryType || col.summaryRenderer) {
                                rendered = (col.summaryRenderer || col.renderer)(data[col.name], {}, { data: data }, 0, col.actualIndex, grid.store);
                            }
                            if (rendered == undefined || rendered === "") rendered = "&#160;";

                            group.summaries[col.dataIndex] = rendered;
                        });
                    }

                    delete group.rs;
                });

                return (groups);
            },

            getColumns: function(grid) {
                if (this.columns == null) {
                    var columns = [];
                    var columnData = grid.view.getColumnData();

                    Ext.each(grid.getColumnModel().config, function(col, index) {
                        if (col.hidden != true) {
                            columns.push(
                                {
                                    dataIndex: col.dataIndex,
                                    header: col.header,
                                    renderer: col.renderer,
                                    summaryType: col.summaryType,
                                    summaryRenderer: col.summaryRenderer,
                                    style: columnData[index].style,
                                    name: columnData[index].name,
                                    actualIndex: index
                                });
                        }
                    }, this);

                    this.columns = columns;
                }
                return this.columns;
            },

            getGroupedData: function(grid) {
                var rs = grid.store.getRange();
                var ds = grid.store;
                var view = grid.view;

                var groupField = ds.getGroupState(),
                    colIndex = grid.colModel.findColumnIndex(groupField),
                    cfg = grid.colModel.config[colIndex],
                    groupRenderer = cfg.groupRenderer || cfg.renderer,
                    prefix = view.showGroupName ? (cfg.groupName || cfg.header) + ': ' : '',
                    groups = [],
                    curGroup, i, len, gid;

                for (i = 0, len = rs.length; i < len; i++) {
                    var rowIndex = i,
                        r = rs[i],
                        gvalue = r.data[groupField];

                    g = view.getGroup(gvalue, r, groupRenderer, rowIndex, colIndex, ds);
                    if (!curGroup || curGroup.group != g) {
                        gid = view.constructId(gvalue, groupField, colIndex);
                        curGroup = {
                            group: g,
                            gvalue: gvalue,
                            text: prefix + g,
                            groupId: gid,
                            startRow: rowIndex,
                            rs: [r]
                        };
                        groups.push(curGroup);
                    } else {
                        curGroup.rs.push(r);
                    }
                    r._groupId = gid;
                }

                return (groups);
            }
        });
    }
});
Ext.ux.Printer.registerRenderer('grid', Ext.ux.Printer.GroupedGridPanelRenderer);

//Utility Methods, not part of the Printer itself.
Ext.Component.prototype.getPluginByType = function(constructorFn) {
    if (Ext.isEmpty(constructorFn)) {
        return (null);
    }
    
    if (Ext.isArray(this.plugins)) {
        for (var i = 0, len = this.plugins.length; i < len; i++) {
            if (this.plugins[i] instanceof constructorFn) {
                return (this.plugins[i]);
            }
        }
    } else {
        if (this.plugins instanceof constructorFn) {
            return (this.plugins);
        }
    }

    return (null);
}

Ext.Component.prototype.hasPlugin = function(constructorFn) {
    var plugin = this.getPluginByType(constructorFn);
    return (!Ext.isEmpty(plugin));
}