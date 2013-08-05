Ext.namespace("Ext.ux.Utils");
Ext.ux.Utils.EventQueue = function(A, B) {
	if (!A) {
		throw "Handler is required."
	}
	this.handler = A;
	this.scope = B || window;
	this.queue = [];
	this.is_processing = false;
	this.postEvent = function(C, D) {
		D = D || null;
		this.queue.push({
					event : C,
					data : D
				});
		if (!this.is_processing) {
			this.process()
		}
	};
	this.flushEventQueue = function() {
		this.queue = []
	}, this.process = function() {
		while (this.queue.length > 0) {
			this.is_processing = true;
			var C = this.queue.shift();
			this.handler.call(this.scope, C.event, C.data)
		}
		this.is_processing = false
	}
};
Ext.ux.Utils.FSA = function(A, C, B) {
	this.current_state = A;
	this.trans_table = C || {};
	this.trans_table_scope = B || window;
	Ext.ux.Utils.FSA.superclass.constructor.call(this, this.processEvent, this)
};
Ext.extend(Ext.ux.Utils.FSA, Ext.ux.Utils.EventQueue, {
			current_state : null,
			trans_table : null,
			trans_table_scope : null,
			state : function() {
				return this.current_state
			},
			processEvent : function(D, F) {
				var G = this.currentStateEventTransitions(D);
				if (!G) {
					throw "State '" + this.current_state
							+ "' has no transition for event '" + D + "'."
				}
				for (var A = 0, H = G.length; A < H; A++) {
					var E = G[A];
					var I = E.predicate || E.p || true;
					var B = E.action || E.a || Ext.emptyFn;
					var J = E.state || E.s || this.current_state;
					var C = E.scope || this.trans_table_scope;
					if (this.computePredicate(I, C, F, D)) {
						this.callAction(B, C, F, D);
						this.current_state = J;
						return
					}
				}
				throw "State '" + this.current_state
						+ "' has no transition for event '" + D
						+ "' in current context"
			},
			currentStateEventTransitions : function(A) {
				return this.trans_table[this.current_state]
						? this.trans_table[this.current_state][A] || false
						: false
			},
			computePredicate : function(C, G, E, D) {
				var A = false;
				switch (Ext.type(C)) {
					case "function" :
						A = C.call(G, E, D, this);
						break;
					case "array" :
						A = true;
						for (var F = 0, B = C.length; A && (F < B); F++) {
							if (Ext.type(C[F]) == "function") {
								A = C[F].call(G, E, D, this)
							} else {
								throw ["Predicate: ", C[F],
										' is not callable in "',
										this.current_state,
										'" state for event "', D].join("")
							}
						}
						break;
					case "boolean" :
						A = C;
						break;
					default :
						throw ["Predicate: ", C, ' is not callable in "',
								this.current_state, '" state for event "', D]
								.join("")
				}
				return A
			},
			callAction : function(B, F, D, C) {
				switch (Ext.type(B)) {
					case "array" :
						for (var E = 0, A = B.length; E < A; E++) {
							if (Ext.type(B[E]) == "function") {
								B[E].call(F, D, C, this)
							} else {
								throw ["Action: ", B[E],
										' is not callable in "',
										this.current_state,
										'" state for event "', C].join("")
							}
						}
						break;
					case "function" :
						B.call(F, D, C, this);
						break;
					default :
						throw ["Action: ", B, ' is not callable in "',
								this.current_state, '" state for event "', C]
								.join("")
				}
			}
		});
Ext.namespace("Ext.ux.UploadDialog");
Ext.ux.UploadDialog.BrowseButton = Ext.extend(Ext.Button, {
	input_name : "file",
	input_file : null,
	original_handler : null,
	original_scope : null,
	initComponent : function() {
		Ext.ux.UploadDialog.BrowseButton.superclass.initComponent.call(this);
		this.original_handler = this.handler || null;
		this.original_scope = this.scope || window;
		this.handler = null;
		this.scope = null
	},
	onRender : function(A, B) {
		Ext.ux.UploadDialog.BrowseButton.superclass.onRender.call(this, A, B);
		this.createInputFile()
	},
	createInputFile : function() {
		var A = this.el;
		A.position("relative");
		this.input_file = Ext.DomHelper.insertAfter(A, {
			tag : "input",
			type : "file",
			size : 1,
			name : this.input_name || Ext.id(this.el),
			style : "position: absolute; display: block; border: none; cursor: pointer"
		}, true);
		var D = A.getBox();
		this.input_file.setStyle("font-size", (D.width * 0.5) + "px");
		var B = this.input_file.getBox();
		var C = {
			x : 3,
			y : 3
		};
		if (Ext.isIE) {
			C = {
				x : 0,
				y : 3
			}
		}
		this.input_file.setLeft(D.width - B.width + C.x + "px");
		this.input_file.setTop(D.height - B.height + C.y + "px");
		this.input_file.setOpacity(0);
		if (this.handleMouseEvents) {
			this.input_file.on("mouseover", this.onMouseOver, this);
			this.input_file.on("mousedown", this.onMouseDown, this)
		}
		if (this.tooltip) {
			if (typeof this.tooltip == "object") {
				Ext.QuickTips.register(Ext.apply({
							target : this.input_file
						}, this.tooltip))
			} else {
				this.input_file.dom[this.tooltipType] = this.tooltip
			}
		}
		this.input_file.on("change", this.onInputFileChange, this);
		this.input_file.on("click", function(E) {
					E.stopPropagation()
				})
	},
	detachInputFile : function(B) {
		var A = this.input_file;
		B = B || false;
		if (typeof this.tooltip == "object") {
			Ext.QuickTips.unregister(this.input_file)
		} else {
			this.input_file.dom[this.tooltipType] = null
		}
		this.input_file.removeAllListeners();
		this.input_file = null;
		if (!B) {
			this.createInputFile()
		}
		return A
	},
	getInputFile : function() {
		return this.input_file
	},
	disable : function() {
		Ext.ux.UploadDialog.BrowseButton.superclass.disable.call(this);
		this.input_file.dom.disabled = true
	},
	enable : function() {
		Ext.ux.UploadDialog.BrowseButton.superclass.enable.call(this);
		this.input_file.dom.disabled = false
	},
	destroy : function() {
		var A = this.detachInputFile(true);
		A.remove();
		A = null;
		Ext.ux.UploadDialog.BrowseButton.superclass.destroy.call(this)
	},
	onInputFileChange : function() {
		if (this.original_handler) {
			this.original_handler.call(this.original_scope, this)
		}
	}
});
Ext.ux.UploadDialog.TBBrowseButton = Ext.extend(
		Ext.ux.UploadDialog.BrowseButton, {
			hideParent : true,
			onDestroy : function() {
				Ext.ux.UploadDialog.TBBrowseButton.superclass.onDestroy
						.call(this);
				if (this.container) {
					this.container.remove()
				}
			}
		});
Ext.ux.UploadDialog.FileRecord = Ext.data.Record.create([{
			name : "fileId",
			type : "int"
		}, {
			name : "filename"
		}, {
			name : "filepath"
		}, {
			name : "state",
			type : "int"
		}, {
			name : "note"
		}, {
			name : "input_element"
		}]);
Ext.ux.UploadDialog.FileRecord.STATE_QUEUE = 0;
Ext.ux.UploadDialog.FileRecord.STATE_FINISHED = 1;
Ext.ux.UploadDialog.FileRecord.STATE_FAILED = 2;
Ext.ux.UploadDialog.FileRecord.STATE_PROCESSING = 3;
Ext.ux.UploadDialog.Dialog = function(A) {
	var B = {
		border : false,
		width : 450,
		height : 300,
		minWidth : 450,
		minHeight : 300,
		plain : true,
		constrainHeader : true,
		draggable : true,
		closable : true,
		maximizable : false,
		minimizable : false,
		resizable : true,
		autoDestroy : true,
		closeAction : "close",
		title : this.i18n.title,
		cls : "ext-ux-uploaddialog-dialog",
		url : "",
		base_params : {},
		permitted_extensions : [],
		permitted_extensions_size : [],
		permitted_max_size : 0,//以B为单位
		reset_on_hide : true,
		allow_close_on_upload : false,
		upload_autostart : false
	};
	A = Ext.applyIf(A || {}, B);
	A.layout = "absolute";
	Ext.ux.UploadDialog.Dialog.superclass.constructor.call(this, A)
};
Ext.extend(Ext.ux.UploadDialog.Dialog, Ext.Window, {
	fsa : null,
	state_tpl : null,
	form : null,
	grid_panel : null,
	progress_bar : null,
	is_uploading : false,
	initial_queued_count : 0,
	upload_frame : null,
	iconCls : "menu-file",
	file_cat : "others",
	callback : null,
	initComponent : function() {
		Ext.ux.UploadDialog.Dialog.superclass.initComponent.call(this);
		var A = {
			"created" : {
				"window-render" : [{
					action : [this.createForm, this.createProgressBar,
							this.createGrid],
					state : "rendering"
				}],
				"destroy" : [{
							action : this.flushEventQueue,
							state : "destroyed"
						}]
			},
			"rendering" : {
				"grid-render" : [{
							action : [this.fillToolbar, this.updateToolbar],
							state : "ready"
						}],
				"destroy" : [{
							action : this.flushEventQueue,
							state : "destroyed"
						}]
			},
			"ready" : {
				"file-selected" : [{
							predicate : [this.fireFileTestEvent,
									this.isPermittedFile],
							action : this.addFileToUploadQueue,
							state : "adding-file"
						}, {}],
				"grid-selection-change" : [{
							action : this.updateToolbar
						}],
				"remove-files" : [{
							action : [this.removeFiles,
									this.fireFileRemoveEvent]
						}],
				"reset-queue" : [{
							action : [this.resetQueue, this.fireResetQueueEvent]
						}],
				"start-upload" : [{
					predicate : this.hasUnuploadedFiles,
					action : [this.setUploadingFlag,
							this.saveInitialQueuedCount, this.updateToolbar,
							this.updateProgressBar, this.prepareNextUploadTask,
							this.fireUploadStartEvent],
					state : "uploading"
				}, {}],
				"stop-upload" : [{}],
				"hide" : [{
							predicate : [this.isNotEmptyQueue,
									this.getResetOnHide],
							action : [this.resetQueue, this.fireResetQueueEvent]
						}, {}],
				"destroy" : [{
							action : this.flushEventQueue,
							state : "destroyed"
						}]
			},
			"adding-file" : {
				"file-added" : [{
					predicate : this.isUploading,
					action : [this.incInitialQueuedCount,
							this.updateProgressBar, this.fireFileAddEvent],
					state : "uploading"
				}, {
					predicate : this.getUploadAutostart,
					action : [this.startUpload, this.fireFileAddEvent],
					state : "ready"
				}, {
					action : [this.updateToolbar, this.fireFileAddEvent],
					state : "ready"
				}]
			},
			"uploading" : {
				"file-selected" : [{
							predicate : [this.fireFileTestEvent,
									this.isPermittedFile],
							action : this.addFileToUploadQueue,
							state : "adding-file"
						}, {}],
				"grid-selection-change" : [{}],
				"start-upload" : [{}],
				"stop-upload" : [{
					predicate : this.hasUnuploadedFiles,
					action : [this.resetUploadingFlag, this.abortUpload,
							this.updateToolbar, this.updateProgressBar,
							this.fireUploadStopEvent],
					state : "ready"
				}, {
					action : [this.resetUploadingFlag, this.abortUpload,
							this.updateToolbar, this.updateProgressBar,
							this.fireUploadStopEvent,
							this.fireUploadCompleteEvent],
					state : "ready"
				}],
				"file-upload-start" : [{
					action : [this.uploadFile, this.findUploadFrame,
							this.fireFileUploadStartEvent]
				}],
				"file-upload-success" : [{
					predicate : this.hasUnuploadedFiles,
					action : [this.resetUploadFrame, this.updateRecordState,
							this.updateProgressBar, this.prepareNextUploadTask,
							this.fireUploadSuccessEvent]
				}, {
					action : [this.resetUploadFrame, this.resetUploadingFlag,
							this.updateRecordState, this.updateToolbar,
							this.updateProgressBar,
							this.fireUploadSuccessEvent,
							this.fireUploadCompleteEvent],
					state : "ready"
				}],
				"file-upload-error" : [{
					predicate : this.hasUnuploadedFiles,
					action : [this.resetUploadFrame, this.updateRecordState,
							this.updateProgressBar, this.prepareNextUploadTask,
							this.fireUploadErrorEvent]
				}, {
					action : [this.resetUploadFrame, this.resetUploadingFlag,
							this.updateRecordState, this.updateToolbar,
							this.updateProgressBar, this.fireUploadErrorEvent,
							this.fireUploadCompleteEvent],
					state : "ready"
				}],
				"file-upload-failed" : [{
					predicate : this.hasUnuploadedFiles,
					action : [this.resetUploadFrame, this.updateRecordState,
							this.updateProgressBar, this.prepareNextUploadTask,
							this.fireUploadFailedEvent]
				}, {
					action : [this.resetUploadFrame, this.resetUploadingFlag,
							this.updateRecordState, this.updateToolbar,
							this.updateProgressBar, this.fireUploadFailedEvent,
							this.fireUploadCompleteEvent],
					state : "ready"
				}],
				"hide" : [{
							predicate : this.getResetOnHide,
							action : [this.stopUpload, this.repostHide]
						}, {}],
				"destroy" : [{
					predicate : this.hasUnuploadedFiles,
					action : [this.resetUploadingFlag, this.abortUpload,
							this.fireUploadStopEvent, this.flushEventQueue],
					state : "destroyed"
				}, {
					action : [this.resetUploadingFlag, this.abortUpload,
							this.fireUploadStopEvent,
							this.fireUploadCompleteEvent, this.flushEventQueue],
					state : "destroyed"
				}]
			},
			"destroyed" : {}
		};
		this.fsa = new Ext.ux.Utils.FSA("created", A, this);
		this.addEvents({
					"filetest" : true,
					"fileadd" : true,
					"fileremove" : true,
					"resetqueue" : true,
					"uploadsuccess" : true,
					"uploaderror" : true,
					"uploadfailed" : true,
					"uploadstart" : true,
					"uploadstop" : true,
					"uploadcomplete" : true,
					"fileuploadstart" : true
				});
		this.on("render", this.onWindowRender, this);
		this.on("beforehide", this.onWindowBeforeHide, this);
		this.on("hide", this.onWindowHide, this);
		this.on("destroy", this.onWindowDestroy, this);
		this.state_tpl = new Ext.Template("<div class='ext-ux-uploaddialog-state ext-ux-uploaddialog-state-{state}'>&#160;</div>")
				.compile()
	},
	createForm : function() {
		this.form = Ext.DomHelper.append(this.body, {
			tag : "form",
			method : "post",
			action : this.url,
			style : "position: absolute; left: -100px; top: -100px; width: 100px; height: 100px"
		})
	},
	createProgressBar : function() {
		this.progress_bar = this.add(new Ext.ProgressBar({
					x : 0,
					y : 0,
					anchor : "0",
					value : 0,
					text : this.i18n.progress_waiting_text
				}))
	},
	createGrid : function() {
		var A = new Ext.data.Store({
			proxy : new Ext.data.MemoryProxy([]),
			reader : new Ext.data.JsonReader({}, Ext.ux.UploadDialog.FileRecord),
			sortInfo : {
				field : "state",
				direction : "DESC"
			},
			pruneModifiedRecords : true
		});
		var B = new Ext.grid.ColumnModel([{
					header : this.i18n.state_col_title,
					width : this.i18n.state_col_width,
					resizable : false,
					dataIndex : "state",
					sortable : true,
					renderer : this.renderStateCell.createDelegate(this)
				}, {
					header : this.i18n.filename_col_title,
					width : this.i18n.filename_col_width,
					dataIndex : "filename",
					sortable : true,
					renderer : this.renderFilenameCell.createDelegate(this)
				}, {
					header : this.i18n.note_col_title,
					width : this.i18n.note_col_width,
					dataIndex : "note",
					sortable : true,
					renderer : this.renderNoteCell.createDelegate(this)
				}]);
		this.grid_panel = new Ext.grid.GridPanel({
					ds : A,
					cm : B,
					x : 0,
					y : 22,
					anchor : "0 -22",
					border : true,
					viewConfig : {
						autoFill : true,
						forceFit : true
					},
					bbar : new Ext.Toolbar()
				});
		this.grid_panel.on("render", this.onGridRender, this);
		this.add(this.grid_panel);
		this.grid_panel.getSelectionModel().on("selectionchange",
				this.onGridSelectionChange, this)
	},
	fillToolbar : function() {
		var A = this.grid_panel.getBottomToolbar();
		A.x_buttons = {};
		A.x_buttons.add = A.addItem(new Ext.ux.UploadDialog.TBBrowseButton({
					text : this.i18n.add_btn_text,
					tooltip : this.i18n.add_btn_tip,
					iconCls : "ext-ux-uploaddialog-addbtn",
					handler : this.onAddButtonFileSelected,
					scope : this
				}));
		A.x_buttons.remove = A.addButton({
					text : this.i18n.remove_btn_text,
					tooltip : this.i18n.remove_btn_tip,
					iconCls : "ext-ux-uploaddialog-removebtn",
					handler : this.onRemoveButtonClick,
					scope : this
				});
		A.x_buttons.reset = A.addButton({
					text : this.i18n.reset_btn_text,
					tooltip : this.i18n.reset_btn_tip,
					iconCls : "ext-ux-uploaddialog-resetbtn",
					handler : this.onResetButtonClick,
					scope : this
				});
		A.add("-");
		A.x_buttons.upload = A.addButton({
					text : this.i18n.upload_btn_start_text,
					tooltip : this.i18n.upload_btn_start_tip,
					iconCls : "ext-ux-uploaddialog-uploadstartbtn",
					handler : this.onUploadButtonClick,
					scope : this
				});
		A.add("-");
		A.x_buttons.indicator = A.addItem(new Ext.Toolbar.Item(Ext.DomHelper
				.append(A.getEl(), {
							id : "x-button-indicator",
							tag : "div",
							cls : "ext-ux-uploaddialog-indicator-stoped",
							html : "&#160"
						})));
		A.add("->");
		A.x_buttons.close = A.addButton({
					iconCls : "btn-ok",
					text : this.i18n.close_btn_text,
					tooltip : this.i18n.close_btn_tip,
					handler : this.onCloseButtonClick,
					scope : this
				})
	},
	renderStateCell : function(F, D, B, A, E, C) {
		return this.state_tpl.apply({
					state : F
				})
	},
	renderFilenameCell : function(B, D, H, G, A, C) {
		var F = this.grid_panel.getView();
		var E = function() {
			try {
				Ext.fly(F.getCell(G, A)).child(".x-grid3-cell-inner").dom["qtip"] = B
			} catch (I) {
			}
		};
		E.defer(1000);
		return B
	},
	renderNoteCell : function(B, D, H, G, A, C) {
		var F = this.grid_panel.getView();
		var E = function() {
			try {
				Ext.fly(F.getCell(G, A)).child(".x-grid3-cell-inner").dom["qtip"] = B
			} catch (I) {
			}
		};
		E.defer(1000);
		return B
	},
	getFileExtension : function(B) {
		var A = null;
		var C = B.split(".");
		if (C.length > 1) {
			A = C.pop()
		}
		return Ext.util.Format.lowercase(A);
	},
	isPermittedFileType : function(B) {
		var A = true;
		if (this.permitted_extensions.length > 0) {
			A = this.permitted_extensions.indexOf(this.getFileExtension(B)) != -1
		}
		return A;
	},
	round : function(number, count) {
        return Math.round(number * Math.pow(10, count)) / Math.pow(10, count);
    },
	calFileSize : function(size) {
		var sizeLabel = ["B", "KB", "MB", "GB"];
        for (var index = 0; index < sizeLabel.length; index++) {
            
            if (size < 1024) {
                return this.round(size, 2) + sizeLabel[index];
            }
            
            size = size / 1024;
        }

        return this.round(size, 2) + sizeLabel[index];
    },
    getPermittedFileSize : function(target){
		var fileSize = 0;
		var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
        if (isIE && !target.files) {
            var filePath = target.value;
            var fileSystem = new ActiveXObject("Scripting.FileSystemObject");   
            var file = fileSystem.GetFile (filePath);
            fileSize = file.Size;
        } else {
            fileSize = target.files[0].size;
    	}
    	return fileSize;
	},
	isPermittedFileSize : function(target){
		var A = true;
        if(this.permitted_max_size != 0){
        	var fileSize = this.getPermittedFileSize(target);
        	if(fileSize > this.permitted_max_size){
        		A = false;
        	}
        }
		return A
	},
	isPermittedFileTypeAndSize : function(file,target){
		var A = true;
		var size = 0;
		if(this.permitted_extensions_size.length > 0){
			for(var i = 0 ; i < this.permitted_extensions_size.length ; i++){
				var A = true;
				if(this.permitted_extensions_size[i].type.length > 0){
					var bool = (this.permitted_extensions_size[i].type).indexOf(this.getFileExtension(file)) != -1
					if(bool){
						var fileSize = this.getPermittedFileSize(target);
						size = this.permitted_extensions_size[i].MaxSize
			        	if(fileSize > this.permitted_extensions_size[i].MaxSize){
			        		A = false;
			        		break;
			        	}
					}
				}
			}
		}
		return [A,size];
	},
//	var A = true;
//		if (this.permitted_extensions.length > 0) {
//			A = this.permitted_extensions.indexOf(this.getFileExtension(B)) != -1
//		}
//		return A
	isPermittedFile : function(C) {
		var A = false;
		var B = C.getInputFile().dom.value;
		if (this.isPermittedFileType(B)) {
			A = true;
			if (!this.isPermittedFileSize(C.getInputFile().dom)){
				Ext.Msg.alert(
							this.i18n.error_msgbox_title,
							String.format(
											this.i18n.err_file_size_not_permitted,
											B,
											this.calFileSize(this.permitted_max_size)));
				A = false;
			}else{
				var obj = this.isPermittedFileTypeAndSize(B,C.getInputFile().dom);
				var flag = obj[0];
				var size = obj[1];
				if(!flag){
					Ext.Msg.alert(this.i18n.error_msgbox_title,String.format(
											this.i18n.err_file_size_not_permitted,
											B,
											this.calFileSize(size)));
					A = false;
				}
			}
		} else {
			Ext.Msg.alert(
							this.i18n.error_msgbox_title,
							String.format(
											this.i18n.err_file_type_not_permitted,
											B,
											this.permitted_extensions.join(this.i18n.permitted_extensions_join_str)));
			A = false
		}
		return A
	},
	fireFileTestEvent : function(A) {
		return this.fireEvent("filetest", this, A.getInputFile().dom.value) !== false
	},
	addFileToUploadQueue : function(C) {
		var A = C.detachInputFile();
		A.appendTo(this.form);
		A.setStyle("width", "100px");
		A.dom.disabled = true;
		var B = this.grid_panel.getStore();
		B.add(new Ext.ux.UploadDialog.FileRecord({
					state : Ext.ux.UploadDialog.FileRecord.STATE_QUEUE,
					filename : A.dom.value,
					note : this.i18n.note_queued_to_upload,
					input_element : A
				}));
		this.fsa.postEvent("file-added", A.dom.value)
	},
	fireFileAddEvent : function(A) {
		this.fireEvent("fileadd", this, A)
	},
	updateProgressBar : function() {
		if (this.is_uploading) {
			var B = this.getQueuedCount(true);
			var A = 1 - B / this.initial_queued_count;
			this.progress_bar.updateProgress(A, String.format(
							this.i18n.progress_uploading_text,
							this.initial_queued_count - B,
							this.initial_queued_count))
		} else {
			this.progress_bar
					.updateProgress(0, this.i18n.progress_waiting_text)
		}
	},
	updateToolbar : function() {
		var A = this.grid_panel.getBottomToolbar();
		if (this.is_uploading) {
			A.x_buttons.remove.disable();
			A.x_buttons.reset.disable();
			A.x_buttons.upload.enable();
			if (!this.getAllowCloseOnUpload()) {
				A.x_buttons.close.disable()
			}
			Ext.fly(A.x_buttons.indicator.getEl()).replaceClass(
					"ext-ux-uploaddialog-indicator-stoped",
					"ext-ux-uploaddialog-indicator-processing");
			A.x_buttons.upload
					.setIconClass("ext-ux-uploaddialog-uploadstopbtn");
			A.x_buttons.upload.setText(this.i18n.upload_btn_stop_text);
			A.x_buttons.upload.getEl().child(A.x_buttons.upload.buttonSelector).dom[A.x_buttons.upload.tooltipType] = this.i18n.upload_btn_stop_tip
		} else {
			A.x_buttons.remove.enable();
			A.x_buttons.reset.enable();
			A.x_buttons.close.enable();
			Ext.fly(A.x_buttons.indicator.getEl()).replaceClass(
					"ext-ux-uploaddialog-indicator-processing",
					"ext-ux-uploaddialog-indicator-stoped");
			A.x_buttons.upload
					.setIconClass("ext-ux-uploaddialog-uploadstartbtn");
			A.x_buttons.upload.setText(this.i18n.upload_btn_start_text);
			if (this.getQueuedCount() > 0) {
				A.x_buttons.upload.enable()
			} else {
				A.x_buttons.upload.disable()
			}
			if (this.grid_panel.getSelectionModel().hasSelection()) {
				A.x_buttons.remove.enable()
			} else {
				A.x_buttons.remove.disable()
			}
			if (this.grid_panel.getStore().getCount() > 0) {
				A.x_buttons.reset.enable()
			} else {
				A.x_buttons.reset.disable()
			}
		}
	},
	saveInitialQueuedCount : function() {
		this.initial_queued_count = this.getQueuedCount()
	},
	incInitialQueuedCount : function() {
		this.initial_queued_count++
	},
	setUploadingFlag : function() {
		this.is_uploading = true
	},
	resetUploadingFlag : function() {
		this.is_uploading = false
	},
	prepareNextUploadTask : function() {
		var A = this.grid_panel.getStore();
		var B = null;
		A.each(function(C) {
			if (!B
					&& C.get("state") == Ext.ux.UploadDialog.FileRecord.STATE_QUEUE) {
				B = C
			} else {
				C.get("input_element").dom.disabled = true
			}
		});
		B.get("input_element").dom.disabled = false;
		B.set("state", Ext.ux.UploadDialog.FileRecord.STATE_PROCESSING);
		B.set("note", this.i18n.note_processing);
		B.commit();
		this.fsa.postEvent("file-upload-start", B)
	},
	fireUploadStartEvent : function() {
		this.fireEvent("uploadstart", this)
	},
	removeFiles : function(E) {
		var B = this.grid_panel.getStore();
		for (var D = 0, A = E.length; D < A; D++) {
			var C = E[D];
			C.get("input_element").remove();
			B.remove(C)
		}
	},
	fireFileRemoveEvent : function(C) {
		for (var B = 0, A = C.length; B < A; B++) {
			this.fireEvent("fileremove", this, C[B].get("filename"))
		}
	},
	resetQueue : function() {
		var A = this.grid_panel.getStore();
		A.each(function(B) {
					B.get("input_element").remove()
				});
		A.removeAll()
	},
	fireResetQueueEvent : function() {
		this.fireEvent("resetqueue", this)
	},
	uploadFile : function(B) {
		var A = this.base_params || this.baseParams || this.params;
		Ext.apply(A, {
					file_cat : this.file_cat
				});
		Ext.Ajax.request({
					url : this.url,
					params : A,
					method : "POST",
					form : this.form,
					isUpload : true,
					success : this.onAjaxSuccess,
					failure : this.onAjaxFailure,
					scope : this,
					record : B
				})
	},
	fireFileUploadStartEvent : function(A) {
		this.fireEvent("fileuploadstart", this, A.get("filename"))
	},
	updateRecordState : function(A) {
		if ("success" in A.response && A.response.success) {
			A.record
					.set("state", Ext.ux.UploadDialog.FileRecord.STATE_FINISHED);
			A.record.set("note", A.response.message || A.response.error
							|| this.i18n.note_upload_success)
		} else {
			A.record.set("state", Ext.ux.UploadDialog.FileRecord.STATE_FAILED);
			A.record.set("note", A.response.message || A.response.error
							|| this.i18n.note_upload_error)
		}
		A.record.commit()
	},
	fireUploadSuccessEvent : function(A) {
		this.fireEvent("uploadsuccess", this, A.record.get("filename"),
				A.response)
	},
	fireUploadErrorEvent : function(A) {
		this.fireEvent("uploaderror", this, A.record.get("filename"),
				A.response)
	},
	fireUploadFailedEvent : function(A) {
		this.fireEvent("uploadfailed", this, A.record.get("filename"))
	},
	fireUploadCompleteEvent : function() {
		this.fireEvent("uploadcomplete", this)
	},
	findUploadFrame : function() {
		this.upload_frame = Ext.getBody().child("iframe.x-hidden:last")
	},
	resetUploadFrame : function() {
		this.upload_frame = null
	},
	removeUploadFrame : function() {
		if (this.upload_frame) {
			this.upload_frame.removeAllListeners();
			this.upload_frame.dom.src = "about:blank";
			this.upload_frame.remove()
		}
		this.upload_frame = null
	},
	abortUpload : function() {
		this.removeUploadFrame();
		var A = this.grid_panel.getStore();
		var B = null;
		A.each(function(C) {
			if (C.get("state") == Ext.ux.UploadDialog.FileRecord.STATE_PROCESSING) {
				B = C;
				return false
			}
		});
		B.set("state", Ext.ux.UploadDialog.FileRecord.STATE_FAILED);
		B.set("note", this.i18n.note_aborted);
		B.commit()
	},
	fireUploadStopEvent : function() {
		this.fireEvent("uploadstop", this)
	},
	repostHide : function() {
		this.fsa.postEvent("hide")
	},
	flushEventQueue : function() {
		this.fsa.flushEventQueue()
	},
	onWindowRender : function() {
		this.fsa.postEvent("window-render")
	},
	onWindowBeforeHide : function() {
		return this.isUploading() ? this.getAllowCloseOnUpload() : true
	},
	onWindowHide : function() {
		this.fsa.postEvent("hide")
	},
	onWindowDestroy : function() {
		this.fsa.postEvent("destroy")
	},
	onGridRender : function() {
		this.fsa.postEvent("grid-render")
	},
	onGridSelectionChange : function() {
		this.fsa.postEvent("grid-selection-change")
	},
	onAddButtonFileSelected : function(A) {
		this.fsa.postEvent("file-selected", A)
	},
	onUploadButtonClick : function() {
		if (this.is_uploading) {
			this.fsa.postEvent("stop-upload")
		} else {
			this.fsa.postEvent("start-upload")
		}
	},
	onRemoveButtonClick : function() {
		var A = this.grid_panel.getSelectionModel().getSelections();
		this.fsa.postEvent("remove-files", A)
	},
	onResetButtonClick : function() {
		this.fsa.postEvent("reset-queue")
	},
	onCloseButtonClick : function() {
		var B = this.grid_panel.getStore();
		var A = new Array();
		for (var D = 0; D < B.getCount(); D++) {
			var C = B.getAt(D);
			if (C.data.state == Ext.ux.UploadDialog.FileRecord.STATE_FINISHED) {
				A.push(C.data)
			}
		}
		if (this.callback != null) {
			this.callback.call(this, A)
		}
		this.close()
	},
	onAjaxSuccess : function(C, E) {
		var B = {
			"success" : false,
			"error" : this.i18n.note_upload_error
		};
		try {
			var A = C.responseText;
			var F = A.match(/^<[^>]+>((?:.|\n)*)<\/[^>]+>$/);
			if (F) {
				A = F[1]
			}
			var B = Ext.util.JSON.decode(A);
			E.record.data.filename = B.fileName;
			E.record.data.fileId = B.fileId;
			E.record.data.filepath = B.filePath
		} catch (D) {
			console.info(D)
		}
		var G = {
			record : E.record,
			response : B
		};
		if (B.success == true) {
			this.fsa.postEvent("file-upload-success", G)
		} else {
			this.fsa.postEvent("file-upload-error", G)
		}
	},
	onAjaxFailure : function(A, B) {
		var C = {
			record : B.record,
			response : {
				"success" : false,
				"error" : this.i18n.note_upload_failed
			}
		};
		this.fsa.postEvent("file-upload-failed", C)
	},
	startUpload : function() {
		this.fsa.postEvent("start-upload")
	},
	stopUpload : function() {
		this.fsa.postEvent("stop-upload")
	},
	getUrl : function() {
		return this.url
	},
	setUrl : function(A) {
		this.url = A
	},
	getBaseParams : function() {
		return this.base_params
	},
	setBaseParams : function(A) {
		this.base_params = A
	},
	getUploadAutostart : function() {
		return this.upload_autostart
	},
	setUploadAutostart : function(A) {
		this.upload_autostart = A
	},
	getAllowCloseOnUpload : function() {
		return this.allow_close_on_upload
	},
	setAllowCloseOnUpload : function(A) {
		this.allow_close_on_upload
	},
	getResetOnHide : function() {
		return this.reset_on_hide
	},
	setResetOnHide : function(A) {
		this.reset_on_hide = A
	},
	getPermittedExtensions : function() {
		return this.permitted_extensions
	},
	setPermittedExtensions : function(A) {
		this.permitted_extensions = A
	},
	getPermittedMaxSize : function(){
		return this.permitted_max_size
	},
	setPermittedMaxSize : function(A){
		this.permitted_max_size = A
	},
	isUploading : function() {
		return this.is_uploading
	},
	isNotEmptyQueue : function() {
		return this.grid_panel.getStore().getCount() > 0
	},
	getQueuedCount : function(C) {
		var A = 0;
		var B = this.grid_panel.getStore();
		B.each(function(D) {
			if (D.get("state") == Ext.ux.UploadDialog.FileRecord.STATE_QUEUE) {
				A++
			}
			if (C
					&& D.get("state") == Ext.ux.UploadDialog.FileRecord.STATE_PROCESSING) {
				A++
			}
		});
		return A
	},
	hasUnuploadedFiles : function() {
		return this.getQueuedCount() > 0
	}
});
var p = Ext.ux.UploadDialog.Dialog.prototype;
p.i18n = {
	title : "上传文件",
	state_col_title : "状态",
	state_col_width : 70,
	filename_col_title : "文件名",
	filename_col_width : 230,
	note_col_title : "备注",
	note_col_width : 150,
	add_btn_text : "添加",
	add_btn_tip : "添加文件到上传队列。",
	remove_btn_text : "删除",
	remove_btn_tip : "从上传队列删除文件。",
	reset_btn_text : "重置",
	reset_btn_tip : "重置队列。",
	upload_btn_start_text : "开始上传",
	upload_btn_stop_text : "中断上传",
	upload_btn_start_tip : "上传文件对列。",
	upload_btn_stop_tip : "停止上传。",
	close_btn_text : "确定",
	close_btn_tip : "关闭上传对话框。",
	progress_waiting_text : "等待...",
	progress_uploading_text : "上传中: {0} 的 {1} 文件集合成功。",
	error_msgbox_title : "错误",
	permitted_extensions_join_str : ",",
	err_file_type_not_permitted : "不支持上传该类型文件.<br/>请选择下列类型的文件集合 {1}",
	err_file_size_not_permitted : "不支持上传大于 {1}",
	note_queued_to_upload : "上传的队列。",
	note_processing : "上传中...",
	note_upload_failed : "当前请求过多，服务器正忙，不能及时响应或者因特网服务器发生错误。",
	note_upload_success : "成功。",
	note_upload_error : "上传文件出错。",
	note_aborted : "已经被用户中断"
};
//使用
//permitted_max_size :1024*1024*20,  //20M
//permitted_extensions : ['bmp','jpeg','txt','doc'],
//permitted_extensions_size : [{'type':['bmp','jpeg'],'MaxSize':1024*260},{'type':['txt','doc'],'MaxSize':1024*60}]