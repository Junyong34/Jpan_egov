/**
 * 
 * @license Guriddo wizGrid JS - v4.8.2 - 2015-03-24 Copyright(c) 2008, Tony
 *          Tomov, tony@trirand.com
 * 
 * License: http://guriddo.net/?page_id=103334
 */
//달력 닫기

	function DateClose(iRow,iCol,gridobj) { 
		gridobj.editCell(iRow, iCol, false);
	}
 //그리드 달력 팝업 
 function GridCalendar_info(Gridobj ,iRow, iCol,dateFormat,info){

  			 var colSaveName = Gridobj.wizGrid('getGridParam', 'Columns');
   			 var dateFormat_info= info.dateFormat;
             var changeMonth_info=info.changeMonth;
             var changeYear_info=info.changeYear;
             var showButtonPanel_info=info.showButtonPanel;
             var currentText_info=info.currentText;
             var closeText_info=info.closeText;
             var minDate_info=info.minDate;
             var maxDate_info=info.maxDate;

             Gridobj.editCell(iRow, iCol, true);
 			if(dateFormat == "YYYYMM"){
              $('#' + iRow + '_' + colSaveName[iCol]['name'])
                  .datepicker({
                      inline:true
                      ,dateFormat:dateFormat_info
                      ,changeMonth:changeMonth_info
                      ,changeYear:changeYear_info
                      ,showButtonPanel:showButtonPanel_info
                      ,currentText:currentText_info
                      ,closeText:closeText_info
                      ,minDate:minDate_info
                      ,maxDate:maxDate_info
                ,onClose: function(dateText, inst) { 
                 },afterShow: function(input, inst) { 
                    $("#ui-datepicker-div > table").hide(); } 
                              ,onSelect : function(dateText, inst) {
                                   DateClose(iRow,iCol);
                               }
           }).focus(function() {
               var thisCalendar = $(this); 
               $('.ui-datepicker-close').click(function() { 
                  var month = '';
                  var year ='';
                  if($(".ui-datepicker-year").children().length == 0)year = $('.ui-datepicker-year').text(); 
                   else year = $(".ui-datepicker-year :selected").val(); 
                  if($(".ui-datepicker-month").children().length == 0)month = $('.ui-datepicker-month').text().replace(/월/g, '')-1;
                   else month = $(".ui-datepicker-month :selected").val(); 
                  thisCalendar.datepicker('setDate', new Date(year, month, 1));
                  Gridobj.editCell(iRow, iCol, false);
                 }); 
              }); 
       }else{
       		 $('#' + iRow + '_' + colSaveName[iCol]['name'])
                  .datepicker({
                      inline:true
                      ,dateFormat:"yy-mm"
                      ,changeMonth:true
                      ,changeYear:false
                      ,showButtonPanel:true
                      ,currentText:"today"
                      ,closeText:"close"
                      ,onSelect : function(dateText, inst) {
                    	  Gridobj.editCell(iRow, iCol, false);
                       }
           });

       }

 }
function GridStyleChange(csspath) {
	var tg = document.getElementById('wizcss');
	if (tg) {
		tg.href = (csspath);
	}
	else {
		alert("link css style ID가 wizcss가 맞는지 확인 해주세요."); // +JPN+
	}
}

function setCellOpt(rowIndex, colIndex) {
	var main = new Array(); // json의 전체를 가리키는 배열
	sub = new Object(); // 객체 값 입력후 main배열의 0번 index에 셋팅
	sub['rowIndex'] = rowIndex + 1;
	sub['colIndex'] = colIndex;
	main[0] = sub;
	CellEditoption["Cell"] = sub;
}
var rowspan = function(rowId, val, rawObject, cm, rdata) { // rowspan id정보 셋팅
	return 'id=\'' + cm.name + rowId + "\'";
}

function Rowmerge(gridName, CellName) { // pjy rowspan
	var mya = $("#" + gridName + "").getDataIDs();
	var length = mya.length;
	for (var i = 0; i < length; i++) {
		var before = $("#" + gridName + "").wizGrid('getRowData', mya[i]);
		var rowSpanTaxCount = 1;
		for (j = i + 1; j <= length; j++) {
			var end = $("#" + gridName + "").wizGrid('getRowData', mya[j]);
			if (before[CellName] == end[CellName]) {
				rowSpanTaxCount++;
				$("#" + gridName + "").setCell(mya[j], CellName, '', {
					display: 'none'
				});
			}
			else {
				rowSpanTaxCount = 1;
				break;
			}
			// $("#" + CellName + "" + mya[i] + "").attr("rowspan",
			// rowSpanTaxCount);
			// alert($("td[aria-describedby='" + gridName + "_" + CellName +
			// "']","#" + gridName + "").eq(i));
			$("td[aria-describedby='" + gridName + "_" + CellName + "']", "#" + gridName + "").eq(i).attr("rowspan"
				, rowSpanTaxCount);
		}
	}
}

function Colmerge(gridName, ColName, targetcol, colspan) { // pjy
	var mya = $("#" + gridName + "").getDataIDs();
	var gridID = $("#" + gridName + "");
	var rowlength = mya.length;
	var Jsonkey = new Array();
	var ColKey = new Array();
	var collength = $("#" + gridName + "").getColumnCount();
	$.each(ColName, function(key, value) {
		Jsonkey.push(key);
	});
	var Jsonkeylength = Jsonkey.length;
	var jsoncount = 0;
	for (var i = 0; i < rowlength; i++) {
		var currCol = $("#" + gridName + "").wizGrid('getRowData', mya[i]);
		for (var c = 0; c < collength; c++) {
			for (var ji = 0; ji < Jsonkey.length; ji++) {
				var BeforecolNM = gridID.getColumnName(c);
				var rowId = gridID.getRowId(i + 1);
				var BeforecolVal = gridID.getCell(rowId, BeforecolNM); // getCellValue
				// :
				// function(rowpin,
				// colpin,
				// formatdelyn)
				// json 컬럼 조건 비교
				if (BeforecolNM == Jsonkey[ji]) {
					if (BeforecolVal == ColName[Jsonkey[ji]]) {
						jsoncount++;
						// alert(mya[i] + " @@ "+BeforecolVal + " 전
						// "+aftercolVal + " 후 "+jsoncount);
						// 값이 같은 컬럼 중 제일 처음 뺴고 화면에서 숨긴다.
						// gridID.setCell(mya[i], afterNM, '', { display: 'none'
						// });
						if (jsoncount == Jsonkeylength) {
							// alert(c);
							// alert(Jsonkeylength + " / " +jsoncount + " / "
							// +BeforecolVal + " / " + ColName[Jsonkey[ji]] + "
							// // " + Jsonkey[ji] + " // " + i);
							$("td[aria-describedby='" + gridName + "_" + targetcol + "']", "#" + gridName + "").eq(i).attr(
								"colspan", colspan);
							for (var io = 1; io < colspan; io++) {
								var tarcolindex = $("#" + gridName + "").getColumnIndex(targetcol);
								var colnone = tarcolindex + io;
								var colnoneName = $("#" + gridName + "").getColumnObject(colnone).name
									// alert(grid1.getColumnObject(4).name + " / "
									// +gridName + " // " +colnone);
								$("td[aria-describedby='" + gridName + "_" + colnoneName + "']", "#" + gridName + "").eq(i).css(
									"display", "none");
							}
						}
					}
				}
			}
		}
		jsoncount = 0;
	}
}

function RowStyle(gridName, ColName, cellstyles) { // pjy
	var mya = $("#" + gridName + "").getDataIDs();
	var rowlength = mya.length;
	var Jsonkey = new Array();
	var CellCss = cellstyles.split(":");
	$.each(ColName, function(key, value) {
		Jsonkey.push(key);
	});
	var Jsonkeylength = Jsonkey.length;
	var jsoncount = 0;
	for (var i = 0; i < rowlength; i++) {
		var currCol = $("#" + gridName + "").wizGrid('getRowData', mya[i]);
		for (var ji = 0; ji < Jsonkey.length; ji++) {
			// alert(currCol[Jsonkey[ji]]+ " @@ " + ColName[Jsonkey[ji]]);
			if (currCol[Jsonkey[ji]] == ColName[Jsonkey[ji]]) {
				jsoncount++;
			}
		}
		if (jsoncount == Jsonkeylength) {
			$("#" + mya[i]).css('background-color', CellCss[0]);
			$("#" + mya[i]).css('color', CellCss[1]);
		}
		jsoncount = 0;
		// alert('key:' + key + ' / ' + 'value:' + value + " @@ " + i);
		// //alert(before['SAWONNM']);
	}
}

function CellStyle(gridName, ColName, targetCol, cellstyles) { // pjy
	var mya = $("#" + gridName + "").getDataIDs();
	var gridID = $("#" + gridName + "");
	var rowlength = mya.length;
	var Jsonkey = new Array();
	var ColKey = new Array();
	var collength = $("#" + gridName + "").getColumnCount();
	var CellCss = cellstyles.split(":");
	$.each(ColName, function(key, value) {
		Jsonkey.push(key);
	});
	var Jsonkeylength = Jsonkey.length;
	var jsoncount = 0;
	for (var i = 0; i < rowlength; i++) {
		var currCol = $("#" + gridName + "").wizGrid('getRowData', mya[i]);
		for (var c = 0; c < collength; c++) {
			for (var ji = 0; ji < Jsonkey.length; ji++) {
				var colNM = gridID.getColumnName(c);
				var rowId = gridID.getRowId(i + 1);
				var BeforecolVal = gridID.getCell(rowId, colNM); // getCellValue
				// :
				// function(rowpin,
				// colpin,
				// formatdelyn)
				if (colNM == Jsonkey[ji]) {
					// json 컬럼 값 비교
					if (currCol[colNM] == ColName[Jsonkey[ji]]) {
						// alert( BeforecolVal + " @@ " + ColName[Jsonkey[ji]] +
						// " // " + currCol[colNM] )
						jsoncount++;
						// alert(currCol[colNM] + " / " + ColName[Jsonkey[ji]] +
						// " @ " + jsoncount + " @ " + Jsonkeylength);
						if (jsoncount == Jsonkeylength) {
							gridID.setCell(mya[i], targetCol, '', {
								'background-color': CellCss[0]
							});
							gridID.setCell(mya[i], targetCol, '', {
								'color': CellCss[1]
							});
						}
					} // 컬럼값 비교
				} // 컬럼 이름 비교
			}
		}
		jsoncount = 0;
	}
	// alert(ColKey[0] + " / " + ColKey[1] + " / " +ColKey[2] + " / " +
	// ColKey[3]);
}

function Combo_OnChange(gridId, arrColNms) {
	for (var i = 0; i < arrColNms.length; i++) {
		var Colname = arrColNms[i];
		$(document).on('change', 'select[name=' + arrColNms[i] + ']', function() {
			var rowIndex = this.id.split('_')[0] - 1;
			var colIndex = $("#" + gridId).getColumnIndex(this.name);
			eval(gridId + '_' + this.name + '_OnChange(' + rowIndex + ',"' + colIndex + '");');
		});
	}
}

function CheckIUD(colnam, gridId, rowid, Ctype) {
	var strAt = $('#' + gridId).wizGrid('getCell', rowid, "IUDFLAG");
	var RowIndex = $('#' + gridId).getRowIndex(rowid);
	// alert(1);
	if (Ctype == 'checkbox') {
		var newValue = 'U';
		var reValue = 'R';
		var ColumnsId = $('#' + gridId).wizGrid('getGridParam', 'Columns');
		// alert($("#checkbox_"+rowid+"_"+colnam).prop('checked'));
		if ("I" != strAt && strAt !== false) {
			if ($("#checkbox_" + rowid + "_" + colnam).prop('checked')) { // row
				// ????
				// edit
				// $('#'+gridId).wizGrid('setCell',RowIndex, 'IUDFLAG',
				// newValue);
				$('#' + gridId).setCellValue(RowIndex, 'IUDFLAG', newValue, true);
			}
			else {
				$('#' + gridId).setCellValue(RowIndex, 'IUDFLAG', newValue, true);
				// $('#'+gridId).wizGrid('setCell',RowIndex, 'IUDFLAG',
				// reValue);
			}
		}
	}
	/*
	 * if(Ctype == 'radio'){
	 * 
	 * var newValue='U'; var reValue='R'; var ColumnsId
	 * =$('#'+gridId).wizGrid('getGridParam', 'Columns'); var RowCount =
	 * $('#'+gridId).getRowCount();
	 * 
	 * if("I" != strAt && strAt !== false){
	 * 
	 * if($("#radio_"+rowid).prop('checked')){ //row ???? edit
	 * 
	 * $('#'+gridId).wizGrid('setCell',rowid, 'IUDFLAG', newValue); for( i= 1; i<
	 * RowCount; i++){
	 * 
	 * var rowID = "R"+i;
	 * 
	 * if(rowID !=rowid){ // $('#'+gridId).wizGrid('setCell',rowID, 'IUDFLAG',
	 * reValue); } }
	 * 
	 * 
	 *  } } }
	 */
}

function keyNavi(e, WGrid, Colname) { // pjy
	// alert(WGrid + " @@ " + Colname);
	var key = e.charCode || e.keyCode;
	var SelRowI = WGrid.getCurrRowIndex();
	var SelColI = WGrid.getColumnIndex(Colname);
	var ids = WGrid.getDataIDs();
	var id = WGrid.wizGrid('getGridParam', 'selrow');
	// alert(WGrid+"##"+SelRowI +"@@" +SelColI);
	if (key == 40) // ???
	{
		var Rowcnt = SelRowI + 1;
		// alert(selIRow + "@@" + gridxx.getCurrRowIndex() +"##" );
		// alert("selICol :" + selICol + "@@" +
		// gridxx.getColumnIndex("SAWONNM"));
		if (ids.length > SelRowI) {
			setTimeout(function() {
				WGrid.editCell(Rowcnt, SelColI, true);
			}, 50);
		}
	}
	else if (key == 38) { // ??
		var Rowcnt = SelRowI - 1;
		if (Rowcnt > 0) {
			setTimeout(function() {
				WGrid.editCell(Rowcnt, SelColI, true)
			}, 50);
		}
	}
}
(function(factory) {
		"use strict";
		if (typeof define === "function" && define.amd) {
			// AMD. Register as an anonymous module.
			define(["jquery"], factory);
		}
		else {
			// Browser globals
			factory(jQuery);
		}
	}
	(function($) {
		"use strict";
		// module begin
		$.wizgrid = $.wizgrid || {};
		if (!$.wizgrid.hasOwnProperty("defaults")) {
			$.wizgrid.defaults = {};
		}
		$.extend($.wizgrid, {
			version: "4.8.2"
			, htmlDecode: function(value) {
				if (value && (value === '&nbsp;' || value === '&#160;' || (value.length === 1 && value.charCodeAt(0) ===
						160))) {
					return "";
				}
				return !value ? value : String(value).replace(/&gt;/g, ">").replace(/&lt;/g, "<").replace(/&quot;/g
					, '"').replace(/&amp;/g, "&");
			}
			, htmlEncode: function(value) {
				return !value ? value : String(value).replace(/&/g, "&amp;").replace(/\"/g, "&quot;").replace(/</g
					, "&lt;").replace(/>/g, "&gt;");
			}
			, template: function(format) { //
				var args = $.makeArray(arguments).slice(1)
					, j, al = args.length;
				if (format == null) {
					format = "";
				}
				return format.replace(/\{([\w\-]+)(?:\:([\w\.]*)(?:\((.*?)?\))?)?\}/g, function(m, i) {
					if (!isNaN(parseInt(i, 10))) {
						return args[parseInt(i, 10)];
					}
					for (j = 0; j < al; j++) {
						if ($.isArray(args[j])) {
							var nmarr = args[j]
								, k = nmarr.length;
							while (k--) {
								if (i === nmarr[k].nm) {
									return nmarr[k].v;
								}
							}
						}
					}
				});
			}
			, msie: navigator.appName === 'Microsoft Internet Explorer'
			, msiever: function() {
				var rv = -1;
				var ua = navigator.userAgent;
				var re = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
				if (re.exec(ua) != null) {
					rv = parseFloat(RegExp.$1);
				}
				return rv;
			}
			, getCellIndex: function(cell) {
				var c = $(cell);
				if (c.is('tr')) {
					return -1;
				}
				c = (!c.is('td') && !c.is('th') ? c.closest("td,th") : c)[0];
				if ($.wizgrid.msie) {
					return $.inArray(c, c.parentNode.cells);
				}
				return c.cellIndex;
			}
			, stripHtml: function(v) {
				v = String(v);
				var regexp = /<("[^"]*"|'[^']*'|[^'">])*>/gi;
				if (v) {
					v = v.replace(regexp, "");
					return (v && v !== '&nbsp;' && v !== '&#160;') ? v.replace(/\"/g, "'") : "";
				}
				return v;
			}
			, stripPref: function(pref, id) {
				var obj = $.type(pref);
				if (obj === "string" || obj === "number") {
					pref = String(pref);
					id = pref !== "" ? String(id).replace(String(pref), "") : id;
				}
				return id;
			}
			, parse: function(jsonString) {
				var js = jsonString;
				if (js.substr(0, 9) === "while(1);") {
					js = js.substr(9);
				}
				if (js.substr(0, 2) === "/*") {
					js = js.substr(2, js.length - 4);
				}
				if (!js) {
					js = "{}";
				}
				return ($.wizgrid.useJSON === true && typeof JSON === 'object' && typeof JSON.parse === 'function') ?
					JSON.parse(js) : eval('(' + js + ')');
			}
			, parseDate: function(format, date, newformat, opts) { // pjy date
				var token = /\\.|[dDjlNSwzWFmMntLoYyaABgGhHisueIOPTZcrU]/g
					, timezone =
					/\b(?:[PMCEA][SDP]T|(?:Pacific|Mountain|Central|Eastern|Atlantic) (?:Standard|Daylight|Prevailing) Time|(?:GMT|UTC)(?:[-+]\d{4})?)\b/g
					, timezoneClip = /[^-+\dA-Z]/g
					, msDateRegExp = new RegExp("^\/Date\\((([-+])?[0-9]+)(([-+])([0-9]{2})([0-9]{2}))?\\)\/$")
					, msMatch = ((typeof date === 'string') ? date.match(msDateRegExp) : null)
					, pad = function(value, length) {
						value = String(value);
						length = parseInt(length, 10) || 2;
						while (value.length < length) {
							value = '0' + value;
						}
						return value;
					}
					, ts = {
						m: 1
						, d: 1
						, y: 1970
						, h: 0
						, i: 0
						, s: 0
						, u: 0
					}
					, timestamp = 0
					, dM, k, hl, h12to24 = function(ampm, h) {
						if (ampm === 0) {
							if (h === 12) {
								h = 0;
							}
						}
						else {
							if (h !== 12) {
								h += 12;
							}
						}
						return h;
					}
					, offset = 0;
				if (opts === undefined) {
					opts = $.wizgrid.getRegional(this, "formatter.date"); // $.wizgrid.formatter.date;
				}
				// old lang files
				if (opts.parseRe === undefined) {
					opts.parseRe = /[#%\\\/:_;.,\t\s-]/;
				}
				if (opts.masks.hasOwnProperty(format)) {
					format = opts.masks[format];
				}
				if (date && date != null) {
					if (!isNaN(date - 0) && String(format).toLowerCase() === "u") {
						// Unix timestamp
						timestamp = new Date(parseFloat(date) * 1000);
					}
					else if (date.constructor === Date) {
						timestamp = date;
						// Microsoft date format support
					}
					else if (msMatch !== null) {
						timestamp = new Date(parseInt(msMatch[1], 10));
						if (msMatch[3]) {
							offset = Number(msMatch[5]) * 60 + Number(msMatch[6]);
							offset *= ((msMatch[4] === '-') ? 1 : -1);
							offset -= timestamp.getTimezoneOffset();
							timestamp.setTime(Number(Number(timestamp) + (offset * 60 * 1000)));
						}
					}
					else {
						// Support ISO8601Long that have Z
						// at the end to indicate UTC
						// timezone
						if (opts.srcformat === 'ISO8601Long' && date.charAt(date.length - 1) === 'Z') {
							offset -= (new Date()).getTimezoneOffset();
						}
						date = String(date).replace(/\T/g, "#").replace(/\t/, "%").split(opts.parseRe);
						format = format.replace(/\T/g, "#").replace(/\t/, "%").split(opts.parseRe);
						// parsing for month names
						for (k = 0, hl = format.length; k < hl; k++) {
							switch (format[k]) {
								case 'M':
									dM = $.inArray(date[k], opts.monthNames);
									if (dM !== -1 && dM < 12) {
										date[k] = dM + 1;
										ts.m = date[k];
									}
									break;
								case 'F':
									dM = $.inArray(date[k], opts.monthNames, 12);
									if (dM !== -1 && dM > 11) {
										date[k] = dM + 1 - 12;
										ts.m = date[k];
									}
									break;
								case 'n':
									format[k] = 'm';
									break;
								case 'j':
									format[k] = 'd';
									break;
								case 'a':
									dM = $.inArray(date[k], opts.AmPm);
									if (dM !== -1 && dM < 2 && date[k] === opts.AmPm[dM]) {
										date[k] = dM;
										ts.h = h12to24(date[k], ts.h);
									}
									break;
								case 'A':
									dM = $.inArray(date[k], opts.AmPm);
									if (dM !== -1 && dM > 1 && date[k] === opts.AmPm[dM]) {
										date[k] = dM - 2;
										ts.h = h12to24(date[k], ts.h);
									}
									break;
								case 'g':
									ts.h = parseInt(date[k], 10);
									break;
							}
							if (date[k] !== undefined) {
								ts[format[k].toLowerCase()] = parseInt(date[k], 10);
							}
						}
						if (ts.f) {
							ts.m = ts.f;
						}
						if (ts.m === 0 && ts.y === 0 && ts.d === 0) {
							return "&#160;";
						}
						ts.m = parseInt(ts.m, 10) - 1;
						var ty = ts.y;
						if (ty >= 70 && ty <= 99) {
							ts.y = 1900 + ts.y;
						}
						else if (ty >= 0 && ty <= 69) {
							ts.y = 2000 + ts.y;
						}
						timestamp = new Date(ts.y, ts.m, ts.d, ts.h, ts.i, ts.s, ts.u);
						// Apply offset to show date as
						// local time.
						if (offset > 0) {
							timestamp.setTime(Number(Number(timestamp) + (offset * 60 * 1000)));
						}
					}
				}
				else {
					timestamp = new Date(ts.y, ts.m, ts.d, ts.h, ts.i, ts.s, ts.u);
				}
				if (opts.userLocalTime && offset === 0) {
					offset -= (new Date()).getTimezoneOffset();
					if (offset > 0) {
						timestamp.setTime(Number(Number(timestamp) + (offset * 60 * 1000)));
					}
				}
				if (newformat === undefined) {
					return timestamp;
				}
				if (opts.masks.hasOwnProperty(newformat)) {
					newformat = opts.masks[newformat];
				}
				else if (!newformat) {
					newformat = 'Y-m-d';
				}
				var G = timestamp.getHours()
					, i = timestamp.getMinutes()
					, j = timestamp.getDate()
					, n = timestamp.getMonth() + 1
					, o = timestamp.getTimezoneOffset()
					, s = timestamp.getSeconds()
					, u = timestamp.getMilliseconds()
					, w = timestamp.getDay()
					, Y = timestamp.getFullYear()
					, N = (w + 6) % 7 + 1
					, z = (new Date(Y, n - 1, j) - new Date(Y, 0, 1)) / 86400000
					, flags = {
						// Day
						d: pad(j)
						, D: opts.dayNames[w]
						, j: j
						, l: opts.dayNames[w + 7]
						, N: N
						, S: opts.S(j)
						, // j < 11 || j > 13 ? ['st', 'nd', 'rd',
						// 'th'][Math.min((j - 1) % 10, 3)] :
						// 'th',
						w: w
						, z: z
						, // Week
						W: N < 5 ? Math.floor((z + N - 1) / 7) + 1 : Math.floor((z + N - 1) / 7) || ((new Date(Y - 1, 0, 1)
							.getDay() + 6) % 7 < 4 ? 53 : 52)
						, // Month
						F: opts.monthNames[n - 1 + 12]
						, m: pad(n)
						, M: opts.monthNames[n - 1]
						, n: n
						, t: '?'
						, // Year
						L: '?'
						, o: '?'
						, Y: Y
						, y: String(Y).substring(2)
						, // Time
						a: G < 12 ? opts.AmPm[0] : opts.AmPm[1]
						, A: G < 12 ? opts.AmPm[2] : opts.AmPm[3]
						, B: '?'
						, g: G % 12 || 12
						, G: G
						, h: pad(G % 12 || 12)
						, H: pad(G)
						, i: pad(i)
						, s: pad(s)
						, u: u
						, // Timezone
						e: '?'
						, I: '?'
						, O: (o > 0 ? "-" : "+") + pad(Math.floor(Math.abs(o) / 60) * 100 + Math.abs(o) % 60, 4)
						, P: '?'
						, T: (String(timestamp).match(timezone) || [""]).pop().replace(timezoneClip, "")
						, Z: '?'
						, // Full Date/Time
						c: '?'
						, r: '?'
						, U: Math.floor(timestamp / 1000)
					};
				return newformat.replace(token, function($0) {
					return flags.hasOwnProperty($0) ? flags[$0] : $0.substring(1);
				});
			}
			, wizID: function(sid) {
				return String(sid).replace(/[!"#$%&'()*+,.\/:; <=>?@\[\\\]\^`{|}~]/g, "\\$&");
			}
			, guid: 1
			, uidPref: 'wizg'
			, randId: function(prefix) {
				return (prefix || $.wizgrid.uidPref) + ($.wizgrid.guid++);
			}
			, getAccessor: function(obj, expr) {
				var ret, p, prm = []
					, i;
				if (typeof expr === 'function') {
					return expr(obj);
				}
				ret = obj[expr];
				// alert("+2+ " ret);
				if (ret === undefined) {
					try {
						if (typeof expr === 'string') {
							prm = expr.split('.');
							// alert("+1+ " +prm);
						}
						i = prm.length;
						if (i) {
							ret = obj;
							while (ret && i--) {
								p = prm.shift();
								// alert("+3+ " + prm + " @2
								// " + p);
								ret = ret[p];
							}
						}
					}
					catch (e) {}
				}
				return ret;
			}
			, getXmlData: function(obj, expr, returnObj) {
				var ret, m = typeof expr === 'string' ? expr.match(/^(.*)\[(\w+)\]$/) : null;
				if (typeof expr === 'function') {
					return expr(obj);
				}
				if (m && m[2]) {
					// m[2] is the attribute selector
					// m[1] is an optional element selector
					// examples: "[id]", "rows[page]"
					return m[1] ? $(m[1], obj).attr(m[2]) : $(obj).attr(m[2]);
				}
				ret = $(expr, obj);
				if (returnObj) {
					return ret;
				}
				// $(expr, obj).filter(':last'); // we use
				// ':last' to be more compatible with old
				// version of wizGrid
				return ret.length > 0 ? $(ret).text() : undefined;
			}
			, cellWidth: function() {
				var $testDiv = $(
						"<div class='ui-wizgrid' style='left:10000px'><table class='ui-wizgrid-btable' style='width:5px;'><tr class='wizgrow'><td style='width:5px;display:block;'></td></tr></table></div>"
					)
					, testCell = $testDiv.appendTo("body").find("td").width();
				$testDiv.remove();
				return Math.abs(testCell - 5) > 0.1;
			}
			, isLocalStorage: function() {
				try {
					return 'localStorage' in window && window.localStorage !== null;
				}
				catch (e) {
					return false;
				}
			}
			, getRegional: function(inst, param, def_val) {
				var ret;
				if (def_val !== undefined) {
					return def_val;
				}
				if (inst.p && inst.p.regional && $.wizgrid.regional) {
					ret = $.wizgrid.getAccessor($.wizgrid.regional[inst.p.regional] || {}, param);
				}
				if (ret === undefined) {
					ret = $.wizgrid.getAccessor($.wizgrid, param);
				}
				return ret;
			}
			, cell_width: true
			, ajaxOptions: {}
			, from: function(source) {
				// Original Author Hugo Bonacci
				// License MIT
				// http://jlinq.codeplex.com/license
				var $t = this
					, QueryObject = function(d, q) {
						if (typeof d === "string") {
							d = $.data(d);
						}
						var self = this
							, _data = d
							, _usecase = true
							, _trim = false
							, _query = q
							, _stripNum = /[\$,%]/g
							, _lastCommand = null
							, _lastField = null
							, _orDepth = 0
							, _negate = false
							, _queuedOperator = ""
							, _sorting = []
							, _useProperties = true;
						if (typeof d === "object" && d.push) {
							if (d.length > 0) {
								if (typeof d[0] !== "object") {
									_useProperties = false;
								}
								else {
									_useProperties = true;
								}
							}
						}
						else {
							throw "data provides is not an array";
						}
						this._hasData = function() {
							return _data === null ? false : _data.length === 0 ? false : true;
						};
						this._getStr = function(s) {
							var phrase = [];
							if (_trim) {
								phrase.push("jQuery.trim(");
							}
							phrase.push("String(" + s + ")");
							if (_trim) {
								phrase.push(")");
							}
							if (!_usecase) {
								phrase.push(".toLowerCase()");
							}
							return phrase.join("");
						};
						this._strComp = function(val) {
							if (typeof val === "string") {
								return ".toString()";
							}
							return "";
						};
						this._group = function(f, u) {
							return ({
								field: f.toString()
								, unique: u
								, items: []
							});
						};
						this._toStr = function(phrase) {
							if (_trim) {
								phrase = $.trim(phrase);
							}
							phrase = phrase.toString().replace(/\\/g, '\\\\').replace(/\"/g, '\\"');
							return _usecase ? phrase : phrase.toLowerCase();
						};
						this._funcLoop = function(func) {
							var results = [];
							$.each(_data, function(i, v) {
								results.push(func(v));
							});
							return results;
						};
						this._append = function(s) {
							var i;
							if (_query === null) {
								_query = "";
							}
							else {
								_query += _queuedOperator === "" ? " && " : _queuedOperator;
							}
							for (i = 0; i < _orDepth; i++) {
								_query += "(";
							}
							if (_negate) {
								_query += "!";
							}
							_query += "(" + s + ")";
							_negate = false;
							_queuedOperator = "";
							_orDepth = 0;
						};
						this._setCommand = function(f, c) {
							_lastCommand = f;
							_lastField = c;
						};
						this._resetNegate = function() {
							_negate = false;
						};
						this._repeatCommand = function(f, v) {
							if (_lastCommand === null) {
								return self;
							}
							if (f !== null && v !== null) {
								return _lastCommand(f, v);
							}
							if (_lastField === null) {
								return _lastCommand(f);
							}
							if (!_useProperties) {
								return _lastCommand(f);
							}
							return _lastCommand(_lastField, f);
						};
						this._equals = function(a, b) {
							return (self._compare(a, b, 1) === 0);
						};
						this._compare = function(a, b, d) {
							var toString = Object.prototype.toString;
							if (d === undefined) {
								d = 1;
							}
							if (a === undefined) {
								a = null;
							}
							if (b === undefined) {
								b = null;
							}
							if (a === null && b === null) {
								return 0;
							}
							if (a === null && b !== null) {
								return 1;
							}
							if (a !== null && b === null) {
								return -1;
							}
							if (toString.call(a) === '[object Date]' && toString.call(b) === '[object Date]') {
								if (a < b) {
									return -d;
								}
								if (a > b) {
									return d;
								}
								return 0;
							}
							if (!_usecase && typeof a !== "number" && typeof b !== "number") {
								a = String(a);
								b = String(b);
							}
							if (a < b) {
								return -d;
							}
							if (a > b) {
								return d;
							}
							return 0;
						};
						this._performSort = function() {
							if (_sorting.length === 0) {
								return;
							}
							_data = self._doSort(_data, 0);
						};
						this._doSort = function(d, q) {
							var by = _sorting[q].by
								, dir = _sorting[q].dir
								, type = _sorting[q].type
								, dfmt = _sorting[q].datefmt
								, sfunc = _sorting[q].sfunc;
							if (q === _sorting.length - 1) {
								return self._getOrder(d, by, dir, type, dfmt, sfunc);
							}
							q++;
							var values = self._getGroup(d, by, dir, type, dfmt)
								, results = []
								, i, j, sorted;
							for (i = 0; i < values.length; i++) {
								sorted = self._doSort(values[i].items, q);
								for (j = 0; j < sorted.length; j++) {
									results.push(sorted[j]);
								}
							}
							return results;
						};
						this._getOrder = function(data, by, dir, type, dfmt, sfunc) {
							var sortData = []
								, _sortData = []
								, newDir = dir === "a" ? 1 : -1
								, i, ab, j, findSortKey;
							if (type === undefined) {
								type = "text";
							}
							if (type === 'float' || type === 'number' || type === 'currency' || type === 'numeric') {
								findSortKey = function($cell) {
									var key = parseFloat(String($cell).replace(_stripNum, ''));
									return isNaN(key) ? Number.NEGATIVE_INFINITY : key;
								};
							}
							else if (type === 'int' || type === 'integer') {
								findSortKey = function($cell) {
									return $cell ? parseFloat(String($cell).replace(_stripNum, '')) : Number.NEGATIVE_INFINITY;
								};
							}
							else if (type === 'date' || type === 'datetime') {
								findSortKey = function($cell) {
									return $.wizgrid.parseDate.call($t, dfmt, $cell).getTime();
								};
							}
							else if ($.isFunction(type)) {
								findSortKey = type;
							}
							else {
								findSortKey = function($cell) {
									$cell = $cell ? $.trim(String($cell)) : "";
									return _usecase ? $cell : $cell.toLowerCase();
								};
							}
							$.each(data, function(i, v) {
								ab = by !== "" ? $.wizgrid.getAccessor(v, by) : v;
								if (ab === undefined) {
									ab = "";
								}
								ab = findSortKey(ab, v);
								_sortData.push({
									'vSort': ab
									, 'index': i
								});
							});
							if ($.isFunction(sfunc)) {
								_sortData.sort(function(a, b) {
									a = a.vSort;
									b = b.vSort;
									return sfunc.call(this, a, b, newDir);
								});
							}
							else {
								_sortData.sort(function(a, b) {
									a = a.vSort;
									b = b.vSort;
									return self._compare(a, b, newDir);
								});
							}
							j = 0;
							var nrec = data.length;
							// overhead, but we do not change
							// the original data.
							while (j < nrec) {
								i = _sortData[j].index;
								sortData.push(data[i]);
								j++;
							}
							return sortData;
						};
						this._getGroup = function(data, by, dir, type, dfmt) {
							var results = []
								, group = null
								, last = null
								, val;
							$.each(self._getOrder(data, by, dir, type, dfmt), function(i, v) {
								val = $.wizgrid.getAccessor(v, by);
								if (val == null) {
									val = "";
								}
								if (!self._equals(last, val)) {
									last = val;
									if (group !== null) {
										results.push(group);
									}
									group = self._group(by, val);
								}
								group.items.push(v);
							});
							if (group !== null) {
								results.push(group);
							}
							return results;
						};
						this.ignoreCase = function() {
							_usecase = false;
							return self;
						};
						this.useCase = function() {
							_usecase = true;
							return self;
						};
						this.trim = function() {
							_trim = true;
							return self;
						};
						this.noTrim = function() {
							_trim = false;
							return self;
						};
						this.execute = function() {
							var match = _query
								, results = [];
							if (match === null) {
								return self;
							}
							$.each(_data, function() {
								if (eval(match)) {
									results.push(this);
								}
							});
							_data = results;
							return self;
						};
						this.data = function() {
							return _data;
						};
						this.select = function(f) {
							self._performSort();
							if (!self._hasData()) {
								return [];
							}
							self.execute();
							if ($.isFunction(f)) {
								var results = [];
								$.each(_data, function(i, v) {
									results.push(f(v));
								});
								return results;
							}
							return _data;
						};
						this.hasMatch = function() {
							if (!self._hasData()) {
								return false;
							}
							self.execute();
							return _data.length > 0;
						};
						this.andNot = function(f, v, x) {
							_negate = !_negate;
							return self.and(f, v, x);
						};
						this.orNot = function(f, v, x) {
							_negate = !_negate;
							return self.or(f, v, x);
						};
						this.not = function(f, v, x) {
							return self.andNot(f, v, x);
						};
						this.and = function(f, v, x) {
							_queuedOperator = " && ";
							if (f === undefined) {
								return self;
							}
							return self._repeatCommand(f, v, x);
						};
						this.or = function(f, v, x) {
							_queuedOperator = " || ";
							if (f === undefined) {
								return self;
							}
							return self._repeatCommand(f, v, x);
						};
						this.orBegin = function() {
							_orDepth++;
							return self;
						};
						this.orEnd = function() {
							if (_query !== null) {
								_query += ")";
							}
							return self;
						};
						this.isNot = function(f) {
							_negate = !_negate;
							return self.is(f);
						};
						this.is = function(f) {
							self._append('this.' + f);
							self._resetNegate();
							return self;
						};
						this._compareValues = function(func, f, v, how, t) {
							var fld;
							if (_useProperties) {
								fld = 'jQuery.wizgrid.getAccessor(this,\'' + f + '\')';
							}
							else {
								fld = 'this';
							}
							if (v === undefined) {
								v = null;
							}
							// var val=v===null?f:v,
							var val = v
								, swst = t.stype === undefined ? "text" : t.stype;
							if (v !== null) {
								switch (swst) {
									case 'int':
									case 'integer':
										val = (isNaN(Number(val)) || val === "") ? '0' : val; // To be
										// fixed
										// with more
										// inteligent
										// code
										fld = 'parseInt(' + fld + ',10)';
										val = 'parseInt(' + val + ',10)';
										break;
									case 'float':
									case 'number':
									case 'numeric':
										val = String(val).replace(_stripNum, '');
										val = (isNaN(Number(val)) || val === "") ? '0' : val; // To be
										// fixed
										// with more
										// inteligent
										// code
										fld = 'parseFloat(' + fld + ')';
										val = 'parseFloat(' + val + ')';
										break;
									case 'date':
									case 'datetime':
										val = String($.wizgrid.parseDate.call($t, t.srcfmt || 'Y-m-d', val).getTime());
										fld = 'jQuery.wizgrid.parseDate.call(jQuery("#' + $.wizgrid.wizID($t.p.id) + '")[0],"' + t.srcfmt +
											'",' + fld + ').getTime()';
										break;
									default:
										fld = self._getStr(fld);
										val = self._getStr('"' + self._toStr(val) + '"');
								}
							}
							self._append(fld + ' ' + how + ' ' + val);
							self._setCommand(func, f);
							self._resetNegate();
							return self;
						};
						this.equals = function(f, v, t) {
							return self._compareValues(self.equals, f, v, "==", t);
						};
						this.notEquals = function(f, v, t) {
							return self._compareValues(self.equals, f, v, "!==", t);
						};
						this.isNull = function(f, v, t) {
							return self._compareValues(self.equals, f, null, "===", t);
						};
						this.greater = function(f, v, t) {
							return self._compareValues(self.greater, f, v, ">", t);
						};
						this.less = function(f, v, t) {
							return self._compareValues(self.less, f, v, "<", t);
						};
						this.greaterOrEquals = function(f, v, t) {
							return self._compareValues(self.greaterOrEquals, f, v, ">=", t);
						};
						this.lessOrEquals = function(f, v, t) {
							return self._compareValues(self.lessOrEquals, f, v, "<=", t);
						};
						this.startsWith = function(f, v) {
							var val = (v == null) ? f : v
								, length = _trim ? $.trim(val.toString()).length : val.toString().length;
							if (_useProperties) {
								self._append(self._getStr('jQuery.wizgrid.getAccessor(this,\'' + f + '\')') + '.substr(0,' +
									length + ') == ' + self._getStr('"' + self._toStr(v) + '"'));
							}
							else {
								if (v != null) {
									length = _trim ? $.trim(v.toString()).length : v.toString().length;
								}
								self._append(self._getStr('this') + '.substr(0,' + length + ') == ' + self._getStr('"' + self._toStr(
									f) + '"'));
							}
							self._setCommand(self.startsWith, f);
							self._resetNegate();
							return self;
						};
						this.endsWith = function(f, v) {
							var val = (v == null) ? f : v
								, length = _trim ? $.trim(val.toString()).length : val.toString().length;
							if (_useProperties) {
								self._append(self._getStr('jQuery.wizgrid.getAccessor(this,\'' + f + '\')') + '.substr(' + self._getStr(
										'jQuery.wizgrid.getAccessor(this,\'' + f + '\')') + '.length-' + length + ',' + length +
									') == "' + self._toStr(v) + '"');
							}
							else {
								self._append(self._getStr('this') + '.substr(' + self._getStr('this') + '.length-"' + self._toStr(
									f) + '".length,"' + self._toStr(f) + '".length) == "' + self._toStr(f) + '"');
							}
							self._setCommand(self.endsWith, f);
							self._resetNegate();
							return self;
						};
						this.contains = function(f, v) {
							if (_useProperties) {
								self._append(self._getStr('jQuery.wizgrid.getAccessor(this,\'' + f + '\')') + '.indexOf("' +
									self._toStr(v) + '",0) > -1');
							}
							else {
								self._append(self._getStr('this') + '.indexOf("' + self._toStr(f) + '",0) > -1');
							}
							self._setCommand(self.contains, f);
							self._resetNegate();
							return self;
						};
						this.groupBy = function(by, dir, type, datefmt) {
							if (!self._hasData()) {
								return null;
							}
							return self._getGroup(_data, by, dir, type, datefmt);
						};
						this.orderBy = function(by, dir, stype, dfmt, sfunc) {
							dir = dir == null ? "a" : $.trim(dir.toString().toLowerCase());
							if (stype == null) {
								stype = "text";
							}
							if (dfmt == null) {
								dfmt = "Y-m-d";
							}
							if (sfunc == null) {
								sfunc = false;
							}
							if (dir === "desc" || dir === "descending") {
								dir = "d";
							}
							if (dir === "asc" || dir === "ascending") {
								dir = "a";
							}
							_sorting.push({
								by: by
								, dir: dir
								, type: stype
								, datefmt: dfmt
								, sfunc: sfunc
							});
							return self;
						};
						return self;
					};
				return new QueryObject(source, null);
			}
			, getMethod: function(name) {
				return this.getAccessor($.fn.wizGrid, name);
			}
			, extend: function(methods) {
				$.extend($.fn.wizGrid, methods);
				if (!this.no_legacy_api) {
					$.fn.extend(methods);
				}
			}
			, clearBeforeUnload: function(wizGridId) {
				var $t = $("#" + $.wizgrid.wizID(wizGridId))[0]
					, grid;
				if (!$t.grid) {
					return;
				}
				grid = $t.grid;
				if ($.isFunction(grid.emptyRows)) {
					grid.emptyRows.call($t, true, true); // this
					// work
					// quick
					// enough
					// and
					// reduce
					// the
					// size
					// of
					// memory
					// leaks
					// if
					// we
					// have
					// someone
				}
				$(document).unbind("mouseup.wizGrid" + $t.p.id);
				$(grid.hDiv).unbind("mousemove"); // TODO
				// add
				// namespace
				$($t).unbind();
				var i, l = grid.headers.length
					, removevents = ['formatCol', 'sortData', 'updatepager', 'refreshIndex', 'setHeadCheckBox'
						, 'constructTr', 'formatter', 'addXmlData', 'addJSONData', 'grid', 'p'
					];
				for (i = 0; i < l; i++) {
					grid.headers[i].el = null;
				}
				for (i in grid) {
					if (grid.hasOwnProperty(i)) {
						grid[i] = null;
					}
				}
				// experimental
				for (i in $t.p) {
					if ($t.p.hasOwnProperty(i)) {
						$t.p[i] = $.isArray($t.p[i]) ? [] : null;
					}
				}
				l = removevents.length;
				for (i = 0; i < l; i++) {
					if ($t.hasOwnProperty(removevents[i])) {
						$t[removevents[i]] = null;
						delete($t[removevents[i]]);
					}
				}
			}
			, gridUnload: function(wizGridId) {
				if (!wizGridId) {
					return;
				}
				wizGridId = $.trim(wizGridId);
				if (wizGridId.indexOf("#") === 0) {
					wizGridId = wizGridId.substring(1);
				}
				var $t = $("#" + $.wizgrid.wizID(wizGridId))[0];
				if (!$t.grid) {
					return;
				}
				var defgrid = {
					id: $($t).attr('id')
					, cl: $($t).attr('class')
				};
				if ($t.p.pager) {
					$($t.p.pager).unbind().empty().removeClass(
						"ui-state-default-grid ui-wizgrid-pager ui-corner-bottom-grid");
				}
				var newtable = document.createElement('table');
				newtable.className = defgrid.cl;
				var gid = $.wizgrid.wizID($t.id);
				$(newtable).removeClass("ui-wizgrid-btable").insertBefore("#gbox_" + gid);
				if ($($t.p.pager).parents("#gbox_" + gid).length === 1) {
					$($t.p.pager).insertBefore("#gbox_" + gid);
				}
				$.wizgrid.clearBeforeUnload(wizGridId);
				$("#gbox_" + gid).remove();
				$(newtable).attr({
					id: defgrid.id
					,eltype:'Grid' // pjy 추가  초기화시 필요
				});
				$("#alertmod_" + $.wizgrid.wizID(wizGridId)).remove();
			}
			, gridDestroy: function(wizGridId) {
				if (!wizGridId) {
					return;
				}
				wizGridId = $.trim(wizGridId);
				if (wizGridId.indexOf("#") === 0) {
					wizGridId = wizGridId.substring(1);
				}
				var $t = $("#" + $.wizgrid.wizID(wizGridId))[0];
				if (!$t.grid) {
					return;
				}
				if ($t.p.pager) { // if not part of grid
					$($t.p.pager).remove();
				}
				try {
					$.wizgrid.clearBeforeUnload(wizGridId);
					$("#gbox_" + $.wizgrid.wizID(wizGridId)).remove();
				}
				catch (_) {}
			}
		});
		$.fn.wizGrid = function(pin) {
			if (typeof pin === 'string') {
				var fn = $.wizgrid.getMethod(pin);
				if (!fn) {
					throw ("wizGrid - No such method: " + pin);
				}
				var args = $.makeArray(arguments).slice(1);
				return fn.apply(this, args);
			}
			return this.each(function() {
				if (this.grid) {
					return;
				}
				var localData;
				if (pin != null && pin.data !== undefined) {
					localData = pin.data;
					pin.data = [];
				}
				var p = $.extend(true, {
					url: ""
					, // 추가 변수
					Abindkey: {}
					, Bbindkeyx: []
					, uniqueRowindex: 0
					, bindxrowindex: -1
					, datasetname: ""
					, oldvalue: ""
					, 
					height: 150
					, page: 1
					, rowNum: 100000
					, rowTotal: null
					, records: 0
					, pager: ""
					, pgbuttons: true
					, pginput: true
					, Columns: []
					, rowList: []
					, Names: []
					, sortorder: "asc"
					, sortname: ""
					, datatype: "local"
					, mtype: "GET"
					, altRows: false
					, selarrrow: []
					, savedRow: []
					, shrinkToFit: false
					, xmlReader: {}
					, jsonReader: {}
					, subGrid: false
					, subGridModel: []
					, reccount: 0
					, lastpage: 0
					, lastsort: 0
					, selrow: null
					, OnBeforeSelectRow: null
					, OnSelectRow: null
					, OnColumnSort: null
					, OndblClickRow: null
					, onRightClickRow: null
					, OnPaging: null
					, OnSelectAll: null
					, OnInitGrid: null
					, OnLoadComplete: null
					, gridComplete: null
					, loadError: null
					, loadBeforeSend: null
					, OnInsertRow: null
					, beforeRequest: null
					, OnBeforeProcessing: null
					, OnCaptionClick: null
					, viewrecords: false
					, loadonce: false
					, multiselect: false
					, multikey: false
					, editurl: null
					, search: false
					, caption: ""
					, hidegrid: true
					, hiddengrid: false
					, postData: {}
					, userData: {}
					, treeGrid: false
					, treeGridModel: 'nested'
					, treeReader: {}
					, treeANode: -1
					, ExpandColumn: null
					, tree_root_level: 0
					, prmNames: {
						page: "page"
						, rows: "rows"
						, sort: "sidx"
						, order: "sord"
						, search: "_search"
						, nd: "nd"
						, id: "id"
						, oper: "oper"
						, editoper: "edit"
						, addoper: "add"
						, deloper: "del"
						, subgridid: "id"
						, npage: null
						, totalrows: "totalrows"
					}
					, forceFit: false
					, gridstate: "visible"
					, cellEdit: false
					, // cellsubmit: "remote",
					cellsubmit: "clientArray", // pjy변경
					nv: 0
					, loadui: "enable"
					, toolbar: [false, ""]
					, scroll: false
					, multiboxonly: false
					, deselectAfterSort: true
					, scrollrows: false
					, autowidth: false
					, scrollOffset: 18
					, cellLayout: 5
					, subGridWidth: 20
					, multiselectWidth: 20
					, gridview: true, // treeGrid,
					// subGrid,
					// afterInsertRow
					// event 의
					// 경우를 제외하고
					// true 써야
					// 속도 빠름.
					// false경우
					// 속도차이 심하면
					// 10초까지 남
					rownumWidth: 25
					, rownumbers: false
					, pagerpos: 'center'
					, recordpos: 'right'
					, footerrow: false
					, userDataOnFooter: false
					, hoverrows: true
					, altclass: 'ui-priority-secondary-grid'
					, viewsortcols: [false, 'vertical', true]
					, resizeclass: ''
					, autoencode: false
					, remapColumns: []
					, ajaxGridOptions: {}
					, direction: "ltr"
					, toppager: false
					, headertitles: false
					, scrollTimeout: 40
					, data: []
					, _index: {}
					, grouping: false
					, groupingView: {
						groupField: []
						, groupOrder: []
						, groupText: []
						, groupColumnShow: []
						, groupSummary: []
						, showSummaryOnHide: false
						, sortitems: []
						, sortnames: []
						, summary: []
						, summaryval: []
						, plusicon: 'ui-icon-circlesmall-plus-grid'
						, minusicon: 'ui-icon-circlesmall-minus-grid'
						, displayField: []
						, groupSummaryPos: []
						, formatDisplayField: []
						, _locgr: false
					}
					, ignoreCase: true
					, cmTemplate: {}
					, idPrefix: ""
					, multiSort: false
					, minColWidth: 33
					, scrollPopUp: false
					, scrollTopOffset: 0, // pixel
					scrollLeftOffset: "100%", // percent
					storeNavOptions: false
					, regional: "en"
				}, $.wizgrid.defaults, pin);
				if (localData !== undefined) {
					p.data = localData;
					pin.data = localData;
				}
				var ts = this
					, grid = {
						headers: []
						, cols: []
						, footers: []
						, dragStart: function(i, x, y) {
							var gridLeftPos = $(this.bDiv).offset().left;
							this.resizing = {
								idx: i
								, startX: x.pageX
								, sOL: x.pageX - gridLeftPos
							};
							this.hDiv.style.cursor = "col-resize";
							this.curGbox = $("#rs_m" + $.wizgrid.wizID(p.id), "#gbox_" + $.wizgrid.wizID(p.id));
							this.curGbox.css({
								display: "block"
								, left: x.pageX - gridLeftPos
								, top: y[1]
								, height: y[2]
							});
							$(ts).triggerHandler("wizGridResizeStart", [x, i]);
							if ($.isFunction(p.resizeStart)) {
								p.resizeStart.call(ts, x, i);
							}
							document.onselectstart = function() {
								return false;
							};
						}
						, dragMove: function(x) {
							if (this.resizing) {
								var diff = x.pageX - this.resizing.startX
									, h = this.headers[this.resizing.idx]
									, newWidth = p.direction === "ltr" ? h.width + diff : h.width - diff
									, hn, nWn;
								if (newWidth > 33) {
									this.curGbox.css({
										left: this.resizing.sOL + diff
									});
									if (p.forceFit === true) {
										hn = this.headers[this.resizing.idx + p.nv];
										nWn = p.direction === "ltr" ? hn.width - diff : hn.width + diff;
										if (nWn > p.minColWidth) {
											h.newWidth = newWidth;
											hn.newWidth = nWn;
										}
									}
									else {
										this.newWidth = p.direction === "ltr" ? p.tblwidth + diff : p.tblwidth - diff;
										h.newWidth = newWidth;
									}
								}
							}
						}
						, dragEnd: function() {
							this.hDiv.style.cursor = "default";
							if (this.resizing) {
								var idx = this.resizing.idx
									, nw = this.headers[idx].newWidth || this.headers[idx].width;
								nw = parseInt(nw, 10);
								this.resizing = false;
								$("#rs_m" + $.wizgrid.wizID(p.id)).css("display", "none");
								p.Columns[idx].width = nw;
								this.headers[idx].width = nw;
								this.headers[idx].el.style.width = nw + "px";
								this.cols[idx].style.width = nw + "px";
								if (this.footers.length > 0) {
									this.footers[idx].style.width = nw + "px";
								}
								if (p.forceFit === true) {
									nw = this.headers[idx + p.nv].newWidth || this.headers[idx + p.nv].width;
									this.headers[idx + p.nv].width = nw;
									this.headers[idx + p.nv].el.style.width = nw + "px";
									this.cols[idx + p.nv].style.width = nw + "px";
									if (this.footers.length > 0) {
										this.footers[idx + p.nv].style.width = nw + "px";
									}
									p.Columns[idx + p.nv].width = nw;
								}
								else {
									p.tblwidth = this.newWidth || p.tblwidth;
									$('table:first', this.bDiv).css("width", p.tblwidth + "px");
									$('table:first', this.hDiv).css("width", p.tblwidth + "px");
									this.hDiv.scrollLeft = this.bDiv.scrollLeft;
									if (p.footerrow) {
										$('table:first', this.sDiv).css("width", p.tblwidth + "px");
										this.sDiv.scrollLeft = this.bDiv.scrollLeft;
									}
								}
								$(ts).triggerHandler("wizGridResizeStop", [nw, idx]);
								if ($.isFunction(p.resizeStop)) {
									p.resizeStop.call(ts, nw, idx);
								}
							}
							this.curGbox = null;
							document.onselectstart = function() {
								return true;
							};
						}
						, populateVisible: function() {
							if (grid.timer) {
								clearTimeout(grid.timer);
							}
							grid.timer = null;
							var dh = $(grid.bDiv).height();
							if (!dh) {
								return;
							}
							var table = $("table:first", grid.bDiv);
							var rows, rh;
							if (table[0].rows.length) {
								try {
									rows = table[0].rows[1];
									rh = rows ? $(rows).outerHeight() || grid.prevRowHeight : grid.prevRowHeight;
								}
								catch (pv) {
									rh = grid.prevRowHeight;
								}
							}
							if (!rh) {
								return;
							}
							grid.prevRowHeight = rh;
							var rn = p.rowNum;
							var scrollTop = grid.scrollTop = grid.bDiv.scrollTop;
							var ttop = Math.round(table.position().top) - scrollTop;
							var tbot = ttop + table.height();
							var div = rh * rn;
							var page, npage, empty;
							if (tbot < dh && ttop <= 0 && (p.lastpage === undefined || (parseInt(
									(tbot + scrollTop + div - 1) / div, 10) || 0) <= p.lastpage)) {
								npage = parseInt((dh - tbot + div - 1) / div, 10) || 1;
								if (tbot >= 0 || npage < 2 || p.scroll === true) {
									page = (Math.round((tbot + scrollTop) / div) || 0) + 1;
									ttop = -1;
								}
								else {
									ttop = 1;
								}
							}
							if (ttop > 0) {
								page = (parseInt(scrollTop / div, 10) || 0) + 1;
								npage = (parseInt((scrollTop + dh) / div, 10) || 0) + 2 - page;
								empty = true;
							}
							if (npage) {
								if (p.lastpage && (page > p.lastpage || p.lastpage === 1 || (page === p.page && page === p.lastpage))) {
									return;
								}
								if (grid.hDiv.loading) {
									grid.timer = setTimeout(grid.populateVisible, p.scrollTimeout);
								}
								else {
									p.page = page;
									if (empty) {
										grid.selectionPreserver(table[0]);
										grid.emptyRows.call(table[0], false, false);
									}
									grid.populate(npage);
								}
								if (p.scrollPopUp && p.lastpage != null) {
									$("#scroll_g" + p.id).show().html($.wizgrid.template($.wizgrid.getRegional(ts
										, "defaults.pgtext", p.pgtext), p.page, p.lastpage)).css({
										"top": p.scrollTopOffset + scrollTop * ((parseInt(p.height, 10) - 45) / (parseInt(rh, 10) *
											parseInt(p.records, 10))) + "px"
										, "left": p.scrollLeftOffset
									});
									$(this).mouseout(function() {
										$("#scroll_g" + p.id).hide();
									});
								}
							}
						}
						, scrollGrid: function(e) {
							if (p.scroll) {
								var scrollTop = grid.bDiv.scrollTop;
								if (grid.scrollTop === undefined) {
									grid.scrollTop = 0;
								}
								if (scrollTop !== grid.scrollTop) {
									grid.scrollTop = scrollTop;
									if (grid.timer) {
										clearTimeout(grid.timer);
									}
									grid.timer = setTimeout(grid.populateVisible, p.scrollTimeout);
								}
							}
							grid.hDiv.scrollLeft = grid.bDiv.scrollLeft;
							if (p.footerrow) {
								grid.sDiv.scrollLeft = grid.bDiv.scrollLeft;
							}
							if (e) {
								e.stopPropagation();
							}
						}
						, selectionPreserver: function(ts) {
							var p = ts.p
								, sr = p.selrow
								, sra = p.selarrrow ? $.makeArray(p.selarrrow) : null
								, left = ts.grid.bDiv.scrollLeft
								, restoreSelection = function() {
									var i;
									p.selrow = null;
									p.selarrrow = [];
									if (p.multiselect && sra && sra.length > 0) {
										for (i = 0; i < sra.length; i++) {
											if (sra[i] !== sr) {
												$(ts).wizGrid("setSelection", sra[i], false, null);
											}
										}
									}
									if (sr) {
										$(ts).wizGrid("setSelection", sr, false, null);
									}
									ts.grid.bDiv.scrollLeft = left;
									$(ts).unbind('.selectionPreserver', restoreSelection);
								};
							$(ts).bind('wizGridGridComplete.selectionPreserver', restoreSelection);
						}
					};
				if (this.tagName.toUpperCase() !== 'TABLE' || this.id == null) {
					alert("Element is not a table or has no id!");
					return;
				}
				if (document.documentMode !== undefined) { // IE
					// only
					if (document.documentMode <= 5) {
						alert("Grid can not be used in this ('quirks') mode!");
						return;
					}
				}
				$(this).empty().attr("tabindex", "0");
				this.p = p;
				this.p.useProp = !!$.fn.prop;
				var i, dir;
				if (this.p.Names.length === 0) {
					for (i = 0; i < this.p.Columns.length; i++) {
						this.p.Names[i] = this.p.Columns[i].label || this.p.Columns[i].name;
					}
				}
				if (this.p.Names.length !== this.p.Columns.length) {
					alert($.wizgrid.getRegional(this, "errors.model"));
					return;
				}
				var gv = $("<div class='ui-wizgrid-view' role='grid'></div>")
					, isMSIE = $.wizgrid.msie;
				ts.p.direction = $.trim(ts.p.direction.toLowerCase());
				ts.p._ald = false;
				if ($.inArray(ts.p.direction, ["ltr", "rtl"]) === -1) {
					ts.p.direction = "ltr";
				}
				dir = ts.p.direction;
				$(gv).insertBefore(this);
				$(this).removeClass("scroll").appendTo(gv);
				// var headdetail = $('<div id="gridhead_grid1">
				// </div>');//작업중
				var eg = $("<div class='ui-wizgrid ui-widget ui-widget-content-grid ui-corner-all-grid'></div>");
				// headdetail.insertBefore(eg);
				$(eg).attr({
					"id": "gbox_" + this.id
					, "dir": dir
				}).insertBefore(gv);
				$(gv).attr("id", "gview_" + this.id).appendTo(eg).append($(' <div id="paginate_' + this.id +
					'"  style="text-align:center;margin-top:7px;" ></div>')); // paginate_grid1
				// 페이징
				// 처리
				// 추가
				$("<div class='ui-widget-overlay wizgrid-overlay' id='lui_" + this.id + "'></div>").insertBefore(gv);
				$("<div class='loading ui-state-default-grid ui-state-active' id='load_" + this.id + "'>" + $.wizgrid
					.getRegional(ts, "defaults.loadtext", this.p.loadtext) + "</div>").insertBefore(gv);
				$(this).attr({
					cellspacing: "0"
					, cellpadding: "0"
					, border: "0"
					, "role": "presentation"
					, "aria-multiselectable": !!this.p.multiselect
					, "aria-labelledby": "gbox_" + this.id
				});
				var sortkeys = ["shiftKey", "altKey", "ctrlKey"]
					, intNum = function(val, defval) {
						val = parseInt(val, 10);
						if (isNaN(val)) {
							return defval || 0;
						}
						return val;
					}
					, formatCol = function(pos, rowInd, tv, rawObject, rowId, rdata) {
						var cm = ts.p.Columns[pos]
							, cellAttrFunc, ral = cm.align
							, result = "style=\""
							, clas = cm.classes
							, nm = cm.name
							, celp, acp = [];
						if (ral) {
							result += "text-align:" + ral + ";";
						}
						if (cm.hidden === true) {
							result += "display:none;";
						}
						if (rowInd === 0) {
							result += "width: " + grid.headers[pos].width + "px;";
						}
						else if ($.isFunction(cm.cellattr) || (typeof cm.cellattr === "string" && $.wizgrid.cellattr !=
								null && $.isFunction($.wizgrid.cellattr[cm.cellattr]))) {
							cellAttrFunc = $.isFunction(cm.cellattr) ? cm.cellattr : $.wizgrid.cellattr[cm.cellattr];
							celp = cellAttrFunc.call(ts, rowId, tv, rawObject, cm, rdata);
							if (celp && typeof celp === "string") {
								celp = celp.replace(/style/i, 'style').replace(/title/i, 'title');
								if (celp.indexOf('title') > -1) {
									cm.title = false;
								}
								if (celp.indexOf('class') > -1) {
									clas = undefined;
								}
								acp = celp.replace(/\-style/g, '-sti').split(/style/);
								if (acp.length === 2) {
									acp[1] = $.trim(acp[1].replace(/\-sti/g, '-style').replace("=", ""));
									if (acp[1].indexOf("'") === 0 || acp[1].indexOf('"') === 0) {
										acp[1] = acp[1].substring(1);
									}
									result += acp[1].replace(/'/gi, '"');
								}
								else {
									result += "\"";
								}
							}
						}
						if (!acp.length) {
							acp[0] = "";
							result += "\"";
						}
						result += (clas !== undefined ? (" class=\"" + clas + "\"") : "") + ((cm.title && tv) ? (
							" title=\"" + $.wizgrid.stripHtml(tv) + "\"") : "");
						result += " aria-describedby=\"" + ts.p.id + "_" + nm + "\"";
						return result + acp[0];
					}
					, cellVal = function(val) {
						return val == null || val === "" ? "&#160;" : (ts.p.autoencode ? $.wizgrid.htmlEncode(val) : String(
							val));
					}
					, formatter = function(rowId, cellval, colpos, rwdat, _act) {
						var cm = ts.p.Columns[colpos]
							, v;
						if (cm.formatter !== undefined) {
							rowId = String(ts.p.idPrefix) !== "" ? $.wizgrid.stripPref(ts.p.idPrefix, rowId) : rowId;
							var opts = {
								rowId: rowId
								, Columns: cm
								, gid: ts.p.id
								, pos: colpos
							}; // pjy 정보
							if ($.isFunction(cm.formatter)) {
								v = cm.formatter.call(ts, cellval, opts, rwdat, _act);
							}
							else if ($.fmatter) {
								v = $.fn.fmatter.call(ts, cm.formatter, cellval, opts, rwdat, _act);
							}
							else {
								v = cellVal(cellval);
							}
						}
						else {
							v = cellVal(cellval);
						}
						return v;
					}
					, addCell = function(rowId, cell, pos, irow, srvr, rdata) {
						var v, prp;
						v = formatter(rowId, cell, pos, srvr, 'add');
						prp = formatCol(pos, irow, v, srvr, rowId, rdata);
						return "<td role=\"gridcell\" " + prp + ">" + v + "</td>";
					}
					, addMulti = function(rowid, pos, irow, checked) {
						var v = "<input role=\"checkbox\" type=\"checkbox\"" + " id=\"wizg_" + ts.p.id + "_" + rowid +
							"\" class=\"cbox\" name=\"wizg_" + ts.p.id + "_" + rowid + "\"" + (checked ? "checked=\"checked\"" :
								"") + "/>"
							, prp = formatCol(pos, irow, '', null, rowid, true);
						return "<td  style='width:50px;text-align:center;' role=\"gridcell\" " + prp + ">" + v + "</td>"; // pjy
						// text-align추가
					}
					, addRowNum = function(pos, irow, pG, rN) {
						var v = (parseInt(pG, 10) - 1) * parseInt(rN, 10) + 1 + irow
							, prp = formatCol(pos, irow, v, null, irow, true);
						return "<td role=\"gridcell\" class=\"ui-state-default-grid wizgrid-rownum\" " + prp + ">" + v +
							"</td>";
					}
					, reader = function(datatype) {
						var field, f = []
							, j = 0
							, i;
						for (i = 0; i < ts.p.Columns.length; i++) {
							field = ts.p.Columns[i];
							if (field.name !== 'cb' && field.name !== 'subgrid' && field.name !== 'rn') {
								f[j] = datatype === "local" ? field.name : ((datatype === "xml" || datatype === "xmlstring") ?
									field.xmlmap || field.name : field.jsonmap || field.name);
								if (ts.p.keyName !== false && field.key === true) {
									ts.p.keyName = f[j];
								}
								j++;
							}
						}
						return f;
					}
					, orderedCols = function(offset) {
						var order = ts.p.remapColumns;
						if (!order || !order.length) {
							order = $.map(ts.p.Columns, function(v, i) {
								return i;
							});
						}
						if (offset) {
							order = $.map(order, function(v) {
								return v < offset ? null : v - offset;
							});
						}
						return order;
					}
					, emptyRows = function(scroll, locdata) {
						var firstrow;
						if (this.p.deepempty) {
							$(this.rows).slice(1).remove();
						}
						else {
							firstrow = this.rows.length > 0 ? this.rows[0] : null;
							$(this.firstChild).empty().append(firstrow);
						}
						if (scroll && this.p.scroll) {
							$(this.grid.bDiv.firstChild).css({
								height: "auto"
							});
							$(this.grid.bDiv.firstChild.firstChild).css({
								height: 0
								, display: "none"
							});
							if (this.grid.bDiv.scrollTop !== 0) {
								this.grid.bDiv.scrollTop = 0;
							}
						}
						if (locdata === true && this.p.treeGrid && !this.p.loadonce) {
							this.p.data = [];
							this.p._index = {};
						}
					}
					, normalizeData = function() {
						var p = ts.p
							, data = p.data
							, dataLength = data.length
							, i, j, cur, idn, idr, ccur, v, rd, localReader = p.localReader
							, Columns = p.Columns
							, cellName = localReader.cell
							, iOffset = (p.multiselect === true ? 1 : 0) + (p.subGrid === true ? 1 : 0) + (p.rownumbers ===
								true ? 1 : 0)
							, br = p.scroll ? $.wizgrid.randId() : 1
							, arrayReader, objectReader, rowReader;
						if (p.datatype !== "local" || localReader.repeatitems !== true) {
							return; // nothing to do
						}
						arrayReader = orderedCols(iOffset);
						objectReader = reader("local");
						// read ALL input items and convert items to be
						// read by
						// $.wizgrid.getAccessor with column name as the
						// second parameter
						idn = p.keyIndex === false ? ($.isFunction(localReader.id) ? localReader.id.call(ts, data) :
							localReader.id) : p.keyIndex;
						for (i = 0; i < dataLength; i++) {
							cur = data[i];
							// read id in the same way like addJSONData
							// do
							// probably it would be better to start with
							// "if (cellName) {...}"
							// but the goal of the current
							// implementation was just have THE SAME
							// id values like in addJSONData ...
							idr = $.wizgrid.getAccessor(cur, idn);
							if (idr === undefined) {
								if (typeof idn === "number" && Columns[idn + iOffset] != null) {
									// reread id by name
									idr = $.wizgrid.getAccessor(cur, Columns[idn + iOffset].name);
								}
								if (idr === undefined) {
									idr = br + i;
									if (cellName) {
										ccur = $.wizgrid.getAccessor(cur, cellName) || cur;
										idr = ccur != null && ccur[idn] !== undefined ? ccur[idn] : idr;
										ccur = null;
									}
								}
							}
							rd = {};
							rd[localReader.id] = idr;
							if (cellName) {
								cur = $.wizgrid.getAccessor(cur, cellName) || cur;
							}
							rowReader = $.isArray(cur) ? arrayReader : objectReader;
							for (j = 0; j < rowReader.length; j++) {
								v = $.wizgrid.getAccessor(cur, rowReader[j]);
								rd[Columns[j + iOffset].name] = v;
							}
							$.extend(true, data[i], rd);
						}
					}
					, refreshIndex = function() {
						var datalen = ts.p.data.length
							, idname, i, val;
						if (ts.p.keyName === false || ts.p.loadonce === true) {
							idname = ts.p.localReader.id;
						}
						else {
							idname = ts.p.keyName;
						}
						ts.p._index = [];
						for (i = 0; i < datalen; i++) {
							val = $.wizgrid.getAccessor(ts.p.data[i], idname);
							if (val === undefined) {
								val = String(i + 1);
							}
							ts.p._index[val] = i;
						}
					}
					, constructTr = function(id, hide, altClass, rd, cur, selected) {
						var tabindex = '-1'
							, restAttr = ''
							, attrName, style = hide ? 'display:none;' : ''
							, classes = 'ui-widget-content-grid wizgrow ui-row-' + ts.p.direction + (altClass ? ' ' + altClass :
								'') + (selected ? ' ui-state-highlight-grid' : '')
							, rowAttrObj = $(ts).triggerHandler("wizGridRowAttr", [rd, cur, id]);
						if (typeof rowAttrObj !== "object") {
							rowAttrObj = $.isFunction(ts.p.rowattr) ? ts.p.rowattr.call(ts, rd, cur, id) : (typeof ts.p.rowattr ===
								"string" && $.wizgrid.rowattr != null && $.isFunction($.wizgrid.rowattr[ts.p.rowattr]) ? $.wizgrid
								.rowattr[ts.p.rowattr].call(ts, rd, cur, id) : {});
						}
						if (!$.isEmptyObject(rowAttrObj)) {
							if (rowAttrObj.hasOwnProperty("id")) {
								id = rowAttrObj.id;
								delete rowAttrObj.id;
							}
							if (rowAttrObj.hasOwnProperty("tabindex")) {
								tabindex = rowAttrObj.tabindex;
								delete rowAttrObj.tabindex;
							}
							if (rowAttrObj.hasOwnProperty("style")) {
								style += rowAttrObj.style;
								delete rowAttrObj.style;
							}
							if (rowAttrObj.hasOwnProperty("class")) {
								classes += ' ' + rowAttrObj['class'];
								delete rowAttrObj['class'];
							}
							// dot't allow to change role attribute
							try {
								delete rowAttrObj.role;
							}
							catch (ra) {}
							for (attrName in rowAttrObj) {
								if (rowAttrObj.hasOwnProperty(attrName)) {
									restAttr += ' ' + attrName + '=' + rowAttrObj[attrName];
								}
							}
						}
						return '<tr role="row" id="' + id + '" tabindex="' + tabindex + '"   class="' + classes + '"' + (
								style === '' ? ' style="' + 'height:' + GridRowHeight[ts.p.id] + 'px;"' : ' style="' + style +
								' ' + '"') + restAttr + '>';
						/*var textcheck = "T"
							// ROW Height 설정
						if (textcheck == "T") {
							return '<tr role="row" id="' + id + '" tabindex="' + tabindex + '" class="' + classes + '"' + (
								style === '' ? '' : ' style="' + style + '"') + restAttr + '>';
						}
						else {
							return '<tr role="row" id="' + id + '" tabindex="' + tabindex + '"   class="' + classes + '"' + (
								style === '' ? ' style="' + 'height:' + GridRowHeight[ts.p.id] + 'px;"' : ' style="' + style +
								' ' + '"') + restAttr + '>';
						}*/
					}
					, addXmlData = function(xml, rcnt, more, adjust) {
						var startReq = new Date()
							, locdata = (ts.p.datatype !== "local" && ts.p.loadonce) || ts.p.datatype === "xmlstring"
							, xmlid = "_id_"
							, xmlRd = ts.p.xmlReader
							, frd = ts.p.datatype === "local" ? "local" : "xml";
						if (locdata) {
							ts.p.data = [];
							ts.p._index = {};
							ts.p.localReader.id = xmlid;
						}
						ts.p.reccount = 0;
						if ($.isXMLDoc(xml)) {
							if (ts.p.treeANode === -1 && !ts.p.scroll) {
								emptyRows.call(ts, false, true);
								rcnt = 1;
							}
							else {
								rcnt = rcnt > 1 ? rcnt : 1;
							}
						}
						else {
							return;
						}
						var self = $(ts)
							, i, fpos, ir = 0
							, v, gi = ts.p.multiselect === true ? 1 : 0
							, si = 0
							, addSubGridCell, ni = ts.p.rownumbers === true ? 1 : 0
							, idn, getId, f = []
							, F, rd = {}
							, xmlr, rid, rowData = []
							, cn = (ts.p.altRows === true) ? ts.p.altclass : ""
							, cn1;
						if (ts.p.subGrid === true) {
							si = 1;
							addSubGridCell = $.wizgrid.getMethod("addSubGridCell");
						}
						if (!xmlRd.repeatitems) {
							f = reader(frd);
						}
						if (ts.p.keyName === false) {
							idn = $.isFunction(xmlRd.id) ? xmlRd.id.call(ts, xml) : xmlRd.id;
						}
						else {
							idn = ts.p.keyName;
						}
						if (String(idn).indexOf("[") === -1) {
							if (f.length) {
								getId = function(trow, k) {
									return $(idn, trow).text() || k;
								};
							}
							else {
								getId = function(trow, k) {
									return $(xmlRd.cell, trow).eq(idn).text() || k;
								};
							}
						}
						else {
							getId = function(trow, k) {
								return trow.getAttribute(idn.replace(/[\[\]]/g, "")) || k;
							};
						}
						ts.p.userData = {};
						ts.p.page = intNum($.wizgrid.getXmlData(xml, xmlRd.page), ts.p.page);
						ts.p.lastpage = intNum($.wizgrid.getXmlData(xml, xmlRd.total), 1);
						ts.p.records = intNum($.wizgrid.getXmlData(xml, xmlRd.records));
						if ($.isFunction(xmlRd.userdata)) {
							ts.p.userData = xmlRd.userdata.call(ts, xml) || {};
						}
						else {
							$.wizgrid.getXmlData(xml, xmlRd.userdata, true).each(function() {
								ts.p.userData[this.getAttribute("name")] = $(this).text();
							});
						}
						var gxml = $.wizgrid.getXmlData(xml, xmlRd.root, true);
						gxml = $.wizgrid.getXmlData(gxml, xmlRd.row, true);
						if (!gxml) {
							gxml = [];
						}
						var gl = gxml.length
							, j = 0
							, grpdata = []
							, rn = parseInt(ts.p.rowNum, 10)
							, br = ts.p.scroll ? $.wizgrid.randId() : 1
							, altr;
						if (gl > 0 && ts.p.page <= 0) {
							ts.p.page = 1;
						}
						if (gxml && gl) {
							if (adjust) {
								rn *= adjust + 1;
							}
							var afterInsRow = $.isFunction(ts.p.OnInsertRow)
								, hiderow = false
								, groupingPrepare;
							if (ts.p.grouping) {
								hiderow = ts.p.groupingView.groupCollapse === true;
								groupingPrepare = $.wizgrid.getMethod("groupingPrepare");
							}
							while (j < gl) {
								xmlr = gxml[j];
								rid = getId(xmlr, br + j);
								rid = ts.p.idPrefix + rid;
								altr = rcnt === 0 ? 0 : rcnt + 1;
								cn1 = (altr + j) % 2 === 1 ? cn : '';
								var iStartTrTag = rowData.length;
								rowData.push("");
								if (ni) {
									rowData.push(addRowNum(0, j, ts.p.page, ts.p.rowNum));
								}
								if (gi) {
									rowData.push(addMulti(rid, ni, j, false));
								}
								if (si) {
									rowData.push(addSubGridCell.call(self, gi + ni, j + rcnt));
								}
								if (xmlRd.repeatitems) {
									if (!F) {
										F = orderedCols(gi + si + ni);
									}
									var cells = $.wizgrid.getXmlData(xmlr, xmlRd.cell, true);
									$.each(F, function(k) {
										var cell = cells[this];
										if (!cell) {
											return false;
										}
										v = cell.textContent || cell.text;
										rd[ts.p.Columns[k + gi + si + ni].name] = v;
										rowData.push(addCell(rid, v, k + gi + si + ni, j + rcnt, xmlr, rd));
									});
								}
								else {
									for (i = 0; i < f.length; i++) {
										v = $.wizgrid.getXmlData(xmlr, f[i]);
										rd[ts.p.Columns[i + gi + si + ni].name] = v;
										rowData.push(addCell(rid, v, i + gi + si + ni, j + rcnt, xmlr, rd));
									}
								}
								rowData[iStartTrTag] = constructTr(rid, hiderow, cn1, rd, xmlr, false);
								rowData.push("</tr>");
								if (ts.p.grouping) {
									grpdata.push(rowData);
									if (!ts.p.groupingView._locgr) {
										groupingPrepare.call(self, rd, j);
									}
									rowData = [];
								}
								if (locdata || (ts.p.treeGrid === true && !(ts.p._ald))) {
									rd[xmlid] = $.wizgrid.stripPref(ts.p.idPrefix, rid);
									ts.p.data.push(rd);
									ts.p._index[rd[xmlid]] = ts.p.data.length - 1;
								}
								if (ts.p.gridview === false) {
									$("tbody:first", ts.grid.bDiv).append(rowData.join(''));
									self.triggerHandler("wizGridAfterInsertRow", [
										rid, rd, xmlr
									]);
									if (afterInsRow) {
										ts.p.OnInsertRow.call(ts, rid, rd, xmlr);
									}
									rowData = [];
								}
								rd = {};
								ir++;
								j++;
								if (ir === rn) {
									break;
								}
							}
						}
						if (ts.p.gridview === true) {
							fpos = ts.p.treeANode > -1 ? ts.p.treeANode : 0;
							if (ts.p.grouping) {
								if (!locdata) {
									self.wizGrid('groupingRender', grpdata, ts.p.Columns.length, ts.p.page, rn);
									grpdata = null;
								}
							}
							else if (ts.p.treeGrid === true && fpos > 0) {
								$(ts.rows[fpos]).after(rowData.join(''));
							}
							else {
								// $("tbody:first",t).append(rowData.join(''));
								if (ts.firstElementChild) {
									ts.firstElementChild.innerHTML += rowData.join(''); // append to
									// innerHTML of
									// tbody which
									// contains the
									// first row
									// (.wizgfirstrow)
								}
								else {
									$("#" + $.wizgrid.wizID(ts.p.id) + " tbody:first").append(rowData.join(''));
								}
								ts.grid.cols = ts.rows[0].cells; // update
								// cached
								// first
								// row
							}
						}
						if (ts.p.subGrid === true) {
							try {
								self.wizGrid("addSubGrid", gi + ni);
							}
							catch (_) {}
						}
						ts.p.totaltime = new Date() - startReq;
						if (ir > 0) {
							if (ts.p.records === 0) {
								ts.p.records = gl;
							}
						}
						rowData = null;
						if (ts.p.treeGrid === true) {
							try {
								self.wizGrid("setTreeNode", fpos + 1, ir + fpos + 1);
							}
							catch (e) {}
						}
						// if(!ts.p.treeGrid && !ts.p.scroll)
						// {ts.grid.bDiv.scrollTop = 0;}
						ts.p.reccount = ir;
						ts.p.treeANode = -1;
						if (ts.p.userDataOnFooter) {
							self.wizGrid("footerData", "set", ts.p.userData, true);
						}
						if (locdata) {
							ts.p.records = gl;
							ts.p.lastpage = Math.ceil(gl / rn);
						}
						if (!more) {
							ts.updatepager(false, true);
						}
						if (locdata) {
							while (ir < gl) {
								xmlr = gxml[ir];
								rid = getId(xmlr, ir + br);
								rid = ts.p.idPrefix + rid;
								if (xmlRd.repeatitems) {
									if (!F) {
										F = orderedCols(gi + si + ni);
									}
									var cells2 = $.wizgrid.getXmlData(xmlr, xmlRd.cell, true);
									$.each(F, function(k) {
										var cell = cells2[this];
										if (!cell) {
											return false;
										}
										v = cell.textContent || cell.text;
										rd[ts.p.Columns[k + gi + si + ni].name] = v;
									});
								}
								else {
									for (i = 0; i < f.length; i++) {
										v = $.wizgrid.getXmlData(xmlr, f[i]);
										rd[ts.p.Columns[i + gi + si + ni].name] = v;
									}
								}
								rd[xmlid] = $.wizgrid.stripPref(ts.p.idPrefix, rid);
								if (ts.p.grouping) {
									groupingPrepare.call(self, rd, ir);
								}
								ts.p.data.push(rd);
								ts.p._index[rd[xmlid]] = ts.p.data.length - 1;
								rd = {};
								ir++;
							}
							if (ts.p.grouping) {
								ts.p.groupingView._locgr = true;
								self.wizGrid('groupingRender', grpdata, ts.p.Columns.length, ts.p.page, rn);
								grpdata = null;
							}
						}
					}
					, addJSONData = function(data, rcnt, more, adjust) // load
					// data처리
					// :
					{
						var startReq = new Date();
						if (data) {
							if (ts.p.treeANode === -1 && !ts.p.scroll) {
								emptyRows.call(ts, false, true);
								rcnt = 1;
							}
							else {
								rcnt = rcnt > 1 ? rcnt : 1;
							}
						}
						else {
							return;
						}
						var dReader, locid = "_id_"
							, frd, locdata = (ts.p.datatype !== "local" && ts.p.loadonce) || ts.p.datatype === "jsonstring";
						if (locdata) {
							ts.p.data = [];
							ts.p._index = {};
							ts.p.localReader.id = locid;
						}
						ts.p.reccount = 0;
						if (ts.p.datatype === "local") {
							dReader = ts.p.localReader;
							frd = 'local';
						}
						else {
							dReader = ts.p.jsonReader;
							frd = 'json';
						}
						var self = $(ts)
							, ir = 0
							, v, i, j, f = []
							, cur, gi = ts.p.multiselect ? 1 : 0
							, si = ts.p.subGrid === true ? 1 : 0
							, addSubGridCell, ni = ts.p.rownumbers === true ? 1 : 0
							, arrayReader = orderedCols(gi + si + ni)
							, objectReader = reader(frd)
							, rowReader, len, drows, idn, rd = {}
							, fpos, idr, rowData = []
							, cn = (ts.p.altRows === true) ? ts.p.altclass : ""
							, cn1;
						ts.p.page = intNum($.wizgrid.getAccessor(data, dReader.page), ts.p.page);
						ts.p.lastpage = intNum($.wizgrid.getAccessor(data, dReader.total), 1);
						ts.p.records = intNum($.wizgrid.getAccessor(data, dReader.records));
						ts.p.userData = $.wizgrid.getAccessor(data, dReader.userdata) || {};
						if (si) {
							addSubGridCell = $.wizgrid.getMethod("addSubGridCell");
						}
						if (ts.p.keyName === false) {
							idn = $.isFunction(dReader.id) ? dReader.id.call(ts, data) : dReader.id;
						}
						else {
							idn = ts.p.keyName;
						}
						drows = $.wizgrid.getAccessor(data, dReader.root);
						if (drows == null && $.isArray(data)) {
							drows = data;
						}
						if (!drows) {
							drows = [];
						}
						len = drows.length;
						i = 0;
						if (len > 0 && ts.p.page <= 0) {
							ts.p.page = 1;
						}
						var rn = parseInt(ts.p.rowNum, 10)
							, br = ts.p.scroll ? $.wizgrid.randId() : 1
							, altr, selected = false
							, selr;
						if (adjust) {
							rn *= adjust + 1;
						}
						if (ts.p.datatype === "local" && !ts.p.deselectAfterSort) {
							selected = true;
						}
						var afterInsRow = $.isFunction(ts.p.OnInsertRow)
							, grpdata = []
							, hiderow = false
							, groupingPrepare;
						if (ts.p.grouping) {
							hiderow = ts.p.groupingView.groupCollapse === true;
							groupingPrepare = $.wizgrid.getMethod("groupingPrepare");
						}
						while (i < len) {
							cur = drows[i];
							idr = $.wizgrid.getAccessor(cur, idn);
							if (idr === undefined) {
								if (typeof idn === "number" && ts.p.Columns[idn + gi + si + ni] != null) {
									// reread id by name
									idr = $.wizgrid.getAccessor(cur, ts.p.Columns[idn + gi + si + ni].name);
								}
								if (idr === undefined) {
									idr = br + i;
									if (f.length === 0) {
										if (dReader.cell) {
											var ccur = $.wizgrid.getAccessor(cur, dReader.cell) || cur;
											idr = ccur != null && ccur[idn] !== undefined ? ccur[idn] : idr;
											ccur = null;
										}
									}
								}
							}
							idr = ts.p.idPrefix + idr;
							altr = rcnt === 1 ? 0 : rcnt;
							cn1 = (altr + i) % 2 === 1 ? cn : '';
							if (selected) {
								if (ts.p.multiselect) {
									selr = ($.inArray(idr, ts.p.selarrrow) !== -1);
								}
								else {
									selr = (idr === ts.p.selrow);
								}
							}
							var iStartTrTag = rowData.length;
							rowData.push("");
							if (ni) {
								rowData.push(addRowNum(0, i, ts.p.page, ts.p.rowNum));
							}
							if (gi) {
								rowData.push(addMulti(idr, ni, i, selr));
							}
							if (si) {
								rowData.push(addSubGridCell.call(self, gi + ni, i + rcnt));
							}
							rowReader = objectReader;
							if (dReader.repeatitems) {
								if (dReader.cell) {
									cur = $.wizgrid.getAccessor(cur, dReader.cell) || cur;
								}
								if ($.isArray(cur)) {
									rowReader = arrayReader;
								}
							}
							for (j = 0; j < rowReader.length; j++) {
								v = $.wizgrid.getAccessor(cur, rowReader[j]);
								// -----------------------------kjs :
								// 2015.10.29 추가 GFormat 기능 추가 format 처리
								// 기능 추가
								if (ts.p.Columns[j + gi + si + ni].Gtype != undefined && ts.p.Columns[j + gi + si + ni].Gtype ==
									"number") {
									var roundyn = true;
									if (ts.p.Columns[j + gi + si + ni].GRound != undefined && ts.p.Columns[j + gi + si + ni].GRound ==
										"false") {
										roundyn = false;
									}
									// alert("4===jsonstring:" + v +":"+
									// ts.p.Columns[j+gi+si+ni].Gtype
									// +":" +
									// ts.p.Columns[j+gi+si+ni].GRound
									// +":" +
									// ts.p.Columns[j+gi+si+ni].GFormat);
									v = wizutil.getFormatNumber(v, ts.p.Columns[j + gi + si + ni].GFormat, roundyn);
								}
								else if (ts.p.Columns[j + gi + si + ni].GFormat != undefined) {
									v = wizutil.getGFormat(ts.p.Columns[j + gi + si + ni].GFormat, v);
								}
								// -----------------------------------------------------------------------------------end
								rd[ts.p.Columns[j + gi + si + ni].name] = v;
								rowData.push(addCell(idr, v, j + gi + si + ni, i + rcnt, cur, rd));
							}
							rowData[iStartTrTag] = constructTr(idr, hiderow, cn1, rd, cur, selr);
							rowData.push("</tr>");
							if (ts.p.grouping) {
								grpdata.push(rowData);
								if (!ts.p.groupingView._locgr) {
									groupingPrepare.call(self, rd, i);
								}
								rowData = [];
							}
							if (locdata || (ts.p.treeGrid === true && !(ts.p._ald))) {
								rd[locid] = $.wizgrid.stripPref(ts.p.idPrefix, idr);
								ts.p.data.push(rd);
								ts.p._index[rd[locid]] = ts.p.data.length - 1;
							}
							if (ts.p.gridview === false) {
								$("#" + $.wizgrid.wizID(ts.p.id) + " tbody:first").append(rowData.join(''));
								self.triggerHandler("wizGridAfterInsertRow", [idr
									, rd, cur
								]);
								if (afterInsRow) {
									ts.p.OnInsertRow.call(ts, idr, rd, cur);
								}
								rowData = []; // ari=0;
							}
							rd = {};
							ir++;
							i++;
							if (ir === rn) {
								break;
							}
						}
						if (ts.p.gridview === true) {
							fpos = ts.p.treeANode > -1 ? ts.p.treeANode : 0;
							if (ts.p.grouping) {
								if (!locdata) {
									self.wizGrid('groupingRender', grpdata, ts.p.Columns.length, ts.p.page, rn);
									grpdata = null;
								}
							}
							else if (ts.p.treeGrid === true && fpos > 0) {
								$(ts.rows[fpos]).after(rowData.join(''));
							}
							else {
								if (ts.firstElementChild) {
									// ts.firstElementChild.innerHTML += rowData.join(''); // append to 원본
									//아래가 수정본 IE9부터 오류남 innerHtml 쓰면
										 $(ts.firstElementChild).html($(ts.firstElementChild).html() + rowData.join(''));
									// innerHTML of
									// tbody which
									// contains the
									// first row
									// (.wizgfirstrow)
								}
								else {
									$("#" + $.wizgrid.wizID(ts.p.id) + " tbody:first").append(rowData.join(''));
								}
								ts.grid.cols = ts.rows[0].cells;
							}
						}
						if (ts.p.subGrid === true) {
							try {
								self.wizGrid("addSubGrid", gi + ni);
							}
							catch (_) {}
						}
						ts.p.totaltime = new Date() - startReq;
						if (ir > 0) {
							if (ts.p.records === 0) {
								ts.p.records = len;
							}
						}
						rowData = null;
						if (ts.p.treeGrid === true) {
							try {
								self.wizGrid("setTreeNode", fpos + 1, ir + fpos + 1);
							}
							catch (e) {}
						}
						// if(!ts.p.treeGrid && !ts.p.scroll)
						// {ts.grid.bDiv.scrollTop = 0;}
						ts.p.reccount = ir;
						ts.p.treeANode = -1;
						if (ts.p.userDataOnFooter) {
							self.wizGrid("footerData", "set", ts.p.userData, true);
						}
						if (locdata) {
							ts.p.records = len;
							ts.p.lastpage = Math.ceil(len / rn);
						}
						if (!more) {
							ts.updatepager(false, true);
						}
						if (locdata) {
							while (ir < len && drows[ir]) {
								cur = drows[ir];
								idr = $.wizgrid.getAccessor(cur, idn);
								if (idr === undefined) {
									if (typeof idn === "number" && ts.p.Columns[idn + gi + si + ni] != null) {
										// reread id by name
										idr = $.wizgrid.getAccessor(cur, ts.p.Columns[idn + gi + si + ni].name);
									}
									if (idr === undefined) {
										idr = br + ir;
										if (f.length === 0) {
											if (dReader.cell) {
												var ccur2 = $.wizgrid.getAccessor(cur, dReader.cell) || cur;
												idr = ccur2 != null && ccur2[idn] !== undefined ? ccur2[idn] : idr;
												ccur2 = null;
											}
										}
									}
								}
								if (cur) {
									idr = ts.p.idPrefix + idr;
									rowReader = objectReader;
									if (dReader.repeatitems) {
										if (dReader.cell) {
											cur = $.wizgrid.getAccessor(cur, dReader.cell) || cur;
										}
										if ($.isArray(cur)) {
											rowReader = arrayReader;
										}
									}
									for (j = 0; j < rowReader.length; j++) {
										rd[ts.p.Columns[j + gi + si + ni].name] = $.wizgrid.getAccessor(cur, rowReader[j]);
									}
									rd[locid] = $.wizgrid.stripPref(ts.p.idPrefix, idr);
									if (ts.p.grouping) {
										groupingPrepare.call(self, rd, ir);
									}
									ts.p.data.push(rd);
									ts.p._index[rd[locid]] = ts.p.data.length - 1;
									rd = {};
								}
								ir++;
							}
							if (ts.p.grouping) {
								ts.p.groupingView._locgr = true;
								self.wizGrid('groupingRender', grpdata, ts.p.Columns.length, ts.p.page, rn);
								grpdata = null;
							}
						}
					}
					, addLocalData = function() {
						var st = ts.p.multiSort ? [] : ""
							, sto = []
							, fndsort = false
							, cmtypes = {}
							, grtypes = []
							, grindexes = []
							, srcformat, sorttype, newformat, sfld;
						if (!$.isArray(ts.p.data)) {
							return;
						}
						var grpview = ts.p.grouping ? ts.p.groupingView : false
							, lengrp, gin;
						$.each(ts.p.Columns, function() {
							sorttype = this.sorttype || "text";
							if (sorttype === "date" || sorttype === "datetime") {
								if (this.formatter && typeof this.formatter === 'string' && this.formatter === 'date') {
									if (this.formatoptions && this.formatoptions.srcformat) {
										srcformat = this.formatoptions.srcformat;
									}
									else {
										srcformat = $.wizgrid.getRegional(ts, "formatter.date.srcformat");
									}
									if (this.formatoptions && this.formatoptions.newformat) {
										newformat = this.formatoptions.newformat;
									}
									else {
										newformat = $.wizgrid.getRegional(ts, "formatter.date.newformat");
									}
								}
								else {
									srcformat = newformat = this.datefmt || "Y-m-d";
								}
								cmtypes[this.name] = {
									"stype": sorttype
									, "srcfmt": srcformat
									, "newfmt": newformat
									, "sfunc": this.sortfunc || null
								};
							}
							else {
								cmtypes[this.name] = {
									"stype": sorttype
									, "srcfmt": ''
									, "newfmt": ''
									, "sfunc": this.sortfunc || null
								};
							}
							if (ts.p.grouping) {
								for (gin = 0, lengrp = grpview.groupField.length; gin < lengrp; gin++) {
									if (this.name === grpview.groupField[gin]) {
										var grindex = this.name;
										if (this.index) {
											grindex = this.index;
										}
										grtypes[gin] = cmtypes[grindex];
										grindexes[gin] = grindex;
									}
								}
							}
							if (ts.p.multiSort) {
								if (this.lso) {
									st.push(this.name);
									var tmplso = this.lso.split("-");
									sto.push(tmplso[tmplso.length - 1]);
								}
							}
							else {
								if (!fndsort && (this.index === ts.p.sortname || this.name === ts.p.sortname)) {
									st = this.name; // ???
									fndsort = true;
								}
							}
						});
						if (ts.p.treeGrid && ts.p._sort) {
							$(ts).wizGrid("SortTree", st, ts.p.sortorder, cmtypes[st].stype || 'text', cmtypes[st].srcfmt ||
								'');
							return;
						}
						var compareFnMap = {
								'eq': function(queryObj) {
									return queryObj.equals;
								}
								, 'ne': function(queryObj) {
									return queryObj.notEquals;
								}
								, 'lt': function(queryObj) {
									return queryObj.less;
								}
								, 'le': function(queryObj) {
									return queryObj.lessOrEquals;
								}
								, 'gt': function(queryObj) {
									return queryObj.greater;
								}
								, 'ge': function(queryObj) {
									return queryObj.greaterOrEquals;
								}
								, 'cn': function(queryObj) {
									return queryObj.contains;
								}
								, 'nc': function(queryObj, op) {
									return op === "OR" ? queryObj.orNot().contains : queryObj.andNot().contains;
								}
								, 'bw': function(queryObj) {
									return queryObj.startsWith;
								}
								, 'bn': function(queryObj, op) {
									return op === "OR" ? queryObj.orNot().startsWith : queryObj.andNot().startsWith;
								}
								, 'en': function(queryObj, op) {
									return op === "OR" ? queryObj.orNot().endsWith : queryObj.andNot().endsWith;
								}
								, 'ew': function(queryObj) {
									return queryObj.endsWith;
								}
								, 'ni': function(queryObj, op) {
									return op === "OR" ? queryObj.orNot().equals : queryObj.andNot().equals;
								}
								, 'in': function(queryObj) {
									return queryObj.equals;
								}
								, 'nu': function(queryObj) {
									return queryObj.isNull;
								}
								, 'nn': function(queryObj, op) {
									return op === "OR" ? queryObj.orNot().isNull : queryObj.andNot().isNull;
								}
							}
							, query = $.wizgrid.from.call(ts, ts.p.data);
						if (ts.p.ignoreCase) {
							query = query.ignoreCase();
						}

						function tojLinq(group) {
							var s = 0
								, index, gor, ror, opr, rule, fld;
							if (group.groups != null) {
								gor = group.groups.length && group.groupOp.toString().toUpperCase() === "OR";
								if (gor) {
									query.orBegin();
								}
								for (index = 0; index < group.groups.length; index++) {
									if (s > 0 && gor) {
										query.or();
									}
									try {
										tojLinq(group.groups[index]);
									}
									catch (e) {
										alert(e);
									}
									s++;
								}
								if (gor) {
									query.orEnd();
								}
							}
							if (group.rules != null) {
								// if(s>0) {
								// var result = query.select();
								// query = $.wizgrid.from( result);
								// if (ts.p.ignoreCase) { query =
								// query.ignoreCase(); }
								// }
								try {
									ror = group.rules.length && group.groupOp.toString().toUpperCase() === "OR";
									if (ror) {
										query.orBegin();
									}
									for (index = 0; index < group.rules.length; index++) {
										rule = group.rules[index];
										opr = group.groupOp.toString().toUpperCase();
										if (compareFnMap[rule.op] && rule.field) {
											if (s > 0 && opr && opr === "OR") {
												query = query.or();
											}
											fld = cmtypes[rule.field];
											if (fld.stype === 'date') {
												if (fld.srcfmt && fld.newfmt && fld.srcfmt !== fld.newfmt) {
													rule.data = $.wizgrid.parseDate.call(ts, fld.newfmt, rule.data, fld.srcfmt);
												}
											}
											query = compareFnMap[rule.op]
												(query, opr)
												(rule.field, rule.data, cmtypes[rule.field]);
										}
										s++;
									}
									if (ror) {
										query.orEnd();
									}
								}
								catch (g) {
									alert(g);
								}
							}
						}
						if (ts.p.search === true) {
							var srules = ts.p.postData.filters;
							if (srules) {
								if (typeof srules === "string") {
									srules = $.wizgrid.parse(srules);
								}
								tojLinq(srules);
							}
							else {
								try {
									sfld = cmtypes[ts.p.postData.searchField];
									if (sfld.stype === 'date') {
										if (sfld.srcfmt && sfld.newfmt && sfld.srcfmt !== sfld.newfmt) {
											ts.p.postData.searchString = $.wizgrid.parseDate.call(ts, sfld.newfmt, ts.p.postData.searchString
												, sfld.srcfmt);
										}
									}
									query = compareFnMap[ts.p.postData.searchOper]
										(query)
										(ts.p.postData.searchField, ts.p.postData.searchString, cmtypes[ts.p.postData.searchField]);
								}
								catch (se) {}
							}
						}
						else {
							if (ts.p.treeGrid && ts.p.treeGridModel === "nested") {
								query.orderBy(ts.p.treeReader.left_field, 'asc', 'integer', '', null);
							}
						}
						if (ts.p.treeGrid && ts.p.treeGridModel === "adjacency") {
							lengrp = 0;
							st = null;
						}
						if (ts.p.grouping) {
							for (gin = 0; gin < lengrp; gin++) {
								query.orderBy(grindexes[gin], grpview.groupOrder[gin], grtypes[gin].stype, grtypes[gin].srcfmt);
							}
						}
						if (ts.p.multiSort) {
							$.each(st, function(i) {
								query.orderBy(this, sto[i], cmtypes[this].stype, cmtypes[this].srcfmt, cmtypes[this].sfunc);
							});
						}
						else {
							if (st && ts.p.sortorder && fndsort) {
								if (ts.p.sortorder.toUpperCase() === "DESC") {
									query.orderBy(ts.p.sortname, "d", cmtypes[st].stype, cmtypes[st].srcfmt, cmtypes[st].sfunc);
								}
								else {
									query.orderBy(ts.p.sortname, "a", cmtypes[st].stype, cmtypes[st].srcfmt, cmtypes[st].sfunc);
								}
							}
						}
						var queryResults = query.select()
							, recordsperpage = parseInt(ts.p.rowNum, 10)
							, total = queryResults.length
							, page = parseInt(ts.p.page, 10)
							, totalpages = Math.ceil(total / recordsperpage)
							, retresult = {};
						if ((ts.p.search || ts.p.resetsearch) && ts.p.grouping && ts.p.groupingView._locgr) {
							ts.p.groupingView.groups = [];
							var j, grPrepare = $.wizgrid.getMethod("groupingPrepare")
								, key, udc;
							if (ts.p.footerrow && ts.p.userDataOnFooter) {
								for (key in ts.p.userData) {
									if (ts.p.userData.hasOwnProperty(key)) {
										ts.p.userData[key] = 0;
									}
								}
								udc = true;
							}
							for (j = 0; j < total; j++) {
								if (udc) {
									for (key in ts.p.userData) {
										if (ts.p.userData.hasOwnProperty(key)) {
											ts.p.userData[key] += parseFloat(queryResults[j][key] || 0);
										}
									}
								}
								grPrepare.call($(ts), queryResults[j], j, recordsperpage);
							}
						}
						if (ts.p.treeGrid && ts.p.search) {
							queryResults = $(ts).wizGrid("searchTree", queryResults);
						}
						else {
							queryResults = queryResults.slice(
								(page - 1) * recordsperpage, page * recordsperpage);
						}
						query = null;
						cmtypes = null;
						retresult[ts.p.localReader.total] = totalpages;
						retresult[ts.p.localReader.page] = page;
						retresult[ts.p.localReader.records] = total;
						retresult[ts.p.localReader.root] = queryResults;
						retresult[ts.p.localReader.userdata] = ts.p.userData;
						queryResults = null;
						return retresult;
					}
					, updatepager = function(rn, dnd) {
						var cp, last, base, from, to, tot, fmt, pgboxes = ""
							, sppg, pgid = ts.p.pager ? $.wizgrid.wizID(ts.p.pager.substr(1)) : ""
							, tspg = pgid ? "_" + pgid : ""
							, tspg_t = ts.p.toppager ? "_" + ts.p.toppager.substr(1) : "";
						base = parseInt(ts.p.page, 10) - 1;
						if (base < 0) {
							base = 0;
						}
						base = base * parseInt(ts.p.rowNum, 10);
						to = base + ts.p.reccount;
						if (ts.p.scroll) {
							var rows = $("tbody:first > tr:gt(0)", ts.grid.bDiv);
							base = to - rows.length;
							ts.p.reccount = rows.length;
							var rh = rows.outerHeight() || ts.grid.prevRowHeight;
							if (rh) {
								var top = base * rh;
								var height = parseInt(ts.p.records, 10) * rh;
								$(">div:first", ts.grid.bDiv).css({
									height: height
								}).children("div:first").css({
									height: top
									, display: top ? "" : "none"
								});
								if (ts.grid.bDiv.scrollTop === 0 && ts.p.page > 1) {
									ts.grid.bDiv.scrollTop = ts.p.rowNum * (ts.p.page - 1) * rh;
								}
							}
							ts.grid.bDiv.scrollLeft = ts.grid.hDiv.scrollLeft;
						}
						pgboxes = ts.p.pager || "";
						pgboxes += ts.p.toppager ? (pgboxes ? "," + ts.p.toppager : ts.p.toppager) : "";
						if (pgboxes) {
							fmt = $.wizgrid.getRegional(ts, "formatter.integer");
							cp = intNum(ts.p.page);
							last = intNum(ts.p.lastpage);
							$(".selbox", pgboxes)[this.p.useProp ? 'prop' : 'attr']("disabled", false);
							if (ts.p.pginput === true) {
								$("#input" + tspg).html($.wizgrid.template($.wizgrid.getRegional(ts, "defaults.pgtext", ts.p.pgtext) ||
									""
									, "<input class='ui-pg-input ui-corner-all-grid' type='text' size='2' maxlength='7' value='0' role='textbox'/>"
									, "<span id='sp_1_" + $.wizgrid.wizID(pgid) + "'></span>"));
								if (ts.p.toppager) {
									$("#input_t" + tspg_t).html($.wizgrid.template($.wizgrid.getRegional(ts, "defaults.pgtext", ts.p
											.pgtext) || ""
										, "<input class='ui-pg-input' type='text' size='2' maxlength='7' value='0' role='textbox'/>"
										, "<span id='sp_1_" + $.wizgrid.wizID(pgid) + "_toppager'></span>"));
								}
								$('.ui-pg-input', pgboxes).val(ts.p.page);
								sppg = ts.p.toppager ? '#sp_1' + tspg + ",#sp_1" + tspg_t : '#sp_1' + tspg;
								$(sppg).html($.fmatter ? $.fmatter.util.NumberFormat(ts.p.lastpage, fmt) : ts.p.lastpage);
							}
							if (ts.p.viewrecords) {
								if (ts.p.reccount === 0) {
									$(".ui-paging-info", pgboxes).html($.wizgrid.getRegional(ts, "defaults.emptyrecords", ts.p.emptyrecords));
								}
								else {
									from = base + 1;
									tot = ts.p.records;
									if ($.fmatter) {
										from = $.fmatter.util.NumberFormat(from, fmt);
										to = $.fmatter.util.NumberFormat(to, fmt);
										tot = $.fmatter.util.NumberFormat(tot, fmt);
									}
									var rt = $.wizgrid.getRegional(ts, "defaults.recordtext", ts.p.recordtext);
									$(".ui-paging-info", pgboxes).html($.wizgrid.template(rt, from, to, tot));
								}
							}
							if (ts.p.pgbuttons === true) {
								if (cp <= 0) {
									cp = last = 0;
								}
								if (cp === 1 || cp === 0) {
									$("#first" + tspg + ", #prev" + tspg).addClass('ui-state-disabled').removeClass(
										'ui-state-hover-grid');
									if (ts.p.toppager) {
										$("#first_t" + tspg_t + ", #prev_t" + tspg_t).addClass('ui-state-disabled').removeClass(
											'ui-state-hover-grid');
									}
								}
								else {
									$("#first" + tspg + ", #prev" + tspg).removeClass('ui-state-disabled');
									if (ts.p.toppager) {
										$("#first_t" + tspg_t + ", #prev_t" + tspg_t).removeClass('ui-state-disabled');
									}
								}
								if (cp === last || cp === 0) {
									$("#next" + tspg + ", #last" + tspg).addClass('ui-state-disabled').removeClass(
										'ui-state-hover-grid');
									if (ts.p.toppager) {
										$("#next_t" + tspg_t + ", #last_t" + tspg_t).addClass('ui-state-disabled').removeClass(
											'ui-state-hover-grid');
									}
								}
								else {
									$("#next" + tspg + ", #last" + tspg).removeClass('ui-state-disabled');
									if (ts.p.toppager) {
										$("#next_t" + tspg_t + ", #last_t" + tspg_t).removeClass('ui-state-disabled');
									}
								}
							}
						}
						if (rn === true && ts.p.rownumbers === true) {
							$(">td.wizgrid-rownum", ts.rows).each(function(i) {
								$(this).html(base + 1 + i);
							});
						}
						if (dnd && ts.p.wizgdnd) {
							$(ts).wizGrid('gridDnD', 'updateDnD');
						}
						$(ts).triggerHandler("wizGridGridComplete");
						if ($.isFunction(ts.p.gridComplete)) {
							ts.p.gridComplete.call(ts);
						}
						$(ts).triggerHandler("wizGridAfterGridComplete");
					}
					, beginReq = function() {
						ts.grid.hDiv.loading = true;
						if (ts.p.hiddengrid) {
							return;
						}
						$(ts).wizGrid("progressBar", {
							method: "show"
							, loadtype: ts.p.loadui
							, htmlcontent: $.wizgrid.getRegional(ts, "defaults.loadtext", ts.p.loadtext)
						});
					}
					, endReq = function() {
						ts.grid.hDiv.loading = false;
						$(ts).wizGrid("progressBar", {
							method: "hide"
							, loadtype: ts.p.loadui
						});
					}
					, populate = function(npage) {
						if (!ts.grid.hDiv.loading) {
							var pvis = ts.p.scroll && npage === false
								, prm = {}
								, dt, dstr, pN = ts.p.prmNames;
							if (ts.p.page <= 0) {
								ts.p.page = Math.min(1, ts.p.lastpage);
							}
							if (pN.search !== null) {
								prm[pN.search] = ts.p.search;
							}
							if (pN.nd !== null) {
								prm[pN.nd] = new Date().getTime();
							}
							if (pN.rows !== null) {
								prm[pN.rows] = ts.p.rowNum;
							}
							if (pN.page !== null) {
								prm[pN.page] = ts.p.page;
							}
							if (pN.sort !== null) {
								prm[pN.sort] = ts.p.sortname;
							}
							if (pN.order !== null) {
								prm[pN.order] = ts.p.sortorder;
							}
							if (ts.p.rowTotal !== null && pN.totalrows !== null) {
								prm[pN.totalrows] = ts.p.rowTotal;
							}
							var lcf = $.isFunction(ts.p.OnLoadComplete)
								, lc = lcf ? ts.p.OnLoadComplete : null;
							// pjy rowsapn
							var adjust = 0;
							npage = npage || 1;
							if (npage > 1) {
								if (pN.npage !== null) {
									prm[pN.npage] = npage;
									adjust = npage - 1;
									npage = 1;
								}
								else {
									lc = function(req) {
										ts.p.page++;
										ts.grid.hDiv.loading = false;
										if (lcf) {
											ts.p.OnLoadComplete.call(ts, req);
										}
										populate(npage - 1);
									};
								}
							}
							else if (pN.npage !== null) {
								delete ts.p.postData[pN.npage];
							}
							if (ts.p.grouping) {
								$(ts).wizGrid('groupingSetup');
								var grp = ts.p.groupingView
									, gi, gs = "";
								for (gi = 0; gi < grp.groupField.length; gi++) {
									var index = grp.groupField[gi];
									$.each(ts.p.Columns, function(cmIndex, cmValue) {
										if (cmValue.name === index && cmValue.index) {
											index = cmValue.index;
										}
									});
									gs += index + " " + grp.groupOrder[gi] + ", ";
								}
								prm[pN.sort] = gs + prm[pN.sort];
							}
							$.extend(ts.p.postData, prm);
							var rcnt = !ts.p.scroll ? 1 : ts.rows.length - 1;
							var bfr = $(ts).triggerHandler("wizGridBeforeRequest");
							if (bfr === false || bfr === 'stop') {
								return;
							}
							if ($.isFunction(ts.p.datatype)) {
								ts.p.datatype.call(ts, ts.p.postData, "load_" + ts.p.id, rcnt, npage, adjust);
								return;
							}
							if ($.isFunction(ts.p.beforeRequest)) {
								bfr = ts.p.beforeRequest.call(ts);
								if (bfr === undefined) {
									bfr = true;
								}
								if (bfr === false) {
									return;
								}
							}
							dt = ts.p.datatype.toLowerCase();
							switch (dt) {
								case "json":
								case "jsonp":
								case "xml":
								case "script":
									$.ajax($.extend({
										url: ts.p.url
										, type: ts.p.mtype
										, dataType: dt
										, data: $.isFunction(ts.p.serializeGridData) ? ts.p.serializeGridData.call(ts, ts.p.postData) : ts
											.p.postData
										, success: function(data, st, xhr) {
											if ($.isFunction(ts.p.OnBeforeProcessing)) {
												if (ts.p.OnBeforeProcessing.call(ts, data, st, xhr) === false) {
													endReq();
													return;
												}
											}
											if (dt === "xml") {
												addXmlData(data, rcnt, npage > 1, adjust);
											}
											else {
												addJSONData(data, rcnt, npage > 1, adjust);
											}
											$(ts).triggerHandler("wizGridLoadComplete", [data]);
											if (lc) {
												lc.call(ts, data);
											}
											$(ts).triggerHandler("wizGridAfterLoadComplete", [data]);
											if (pvis) {
												ts.grid.populateVisible();
											}
											if (ts.p.loadonce || ts.p.treeGrid) {
												ts.p.datatype = "local";
											}
											data = null;
											if (npage === 1) {
												endReq();
											}
										}
										, error: function(xhr, st, err) {
											if ($.isFunction(ts.p.loadError)) {
												ts.p.loadError.call(ts, xhr, st, err);
											}
											if (npage === 1) {
												endReq();
											}
											xhr = null;
										}
										, beforeSend: function(xhr, settings) {
											var gotoreq = true;
											if ($.isFunction(ts.p.loadBeforeSend)) {
												gotoreq = ts.p.loadBeforeSend.call(ts, xhr, settings);
											}
											if (gotoreq === undefined) {
												gotoreq = true;
											}
											if (gotoreq === false) {
												return false;
											}
											beginReq();
										}
									}, $.wizgrid.ajaxOptions, ts.p.ajaxGridOptions));
									break;
								case "xmlstring":
									beginReq();
									dstr = typeof ts.p.datastr !== 'string' ? ts.p.datastr : $.parseXML(ts.p.datastr);
									addXmlData(dstr);
									$(ts).triggerHandler("wizGridLoadComplete", [dstr]);
									if (lcf) {
										ts.p.OnLoadComplete.call(ts, dstr);
									}
									$(ts).triggerHandler("wizGridAfterLoadComplete", [dstr]);
									ts.p.datatype = "local";
									ts.p.datastr = null;
									endReq();
									break;
								case "jsonstring":
									beginReq();
									if (typeof ts.p.datastr === 'string') {
										dstr = $.wizgrid.parse(ts.p.datastr);
									}
									else {
										dstr = ts.p.datastr;
									}
									addJSONData(dstr);
									$(ts).triggerHandler("wizGridLoadComplete", [dstr]);
									if (lcf) {
										ts.p.OnLoadComplete.call(ts, dstr);
									}
									$(ts).triggerHandler("wizGridAfterLoadComplete", [dstr]);
									ts.p.datatype = "local";
									ts.p.datastr = null;
									endReq();
									break;
								case "local":
								case "clientside":
									beginReq();
									ts.p.datatype = "local";
									ts.p._ald = true;
									var req = addLocalData();
									addJSONData(req, rcnt, npage > 1, adjust);
									$(ts).triggerHandler("wizGridLoadComplete", [req]);
									if (lc) {
										lc.call(ts, req);
									}
									$(ts).triggerHandler("wizGridAfterLoadComplete", [req]);
									if (pvis) {
										ts.grid.populateVisible();
									}
									endReq();
									ts.p._ald = false;
									break;
							}
							ts.p._sort = false;
							// pjy 페이징 처리 시작
							var PageID = $(ts);
							var PageGId = ts.p.id;
							var allRowsInGrid = PageID.wizGrid('getGridParam', 'records');
							// alert(allRowsInGrid);
							if ($('#NoData_' + PageGId).length > 0) {
								// 데이터가 없을 경우 (먼저 태그 초기화 한 후에 적용)
								$("#NoData_" + PageGId).html("");
								if (allRowsInGrid == 0) {
									$("#NoData_" + PageGId).html(
										"<P style=' font-family:굴림;font-weight:bold;font-size:8pt;overflow:hidden;'><br>조회 결과 데이터가 없습니다.<br></P>"
									); // +JPN+
									// PageID.html("<P id='NOTDATA'
									// style='
									// font-family:굴림;font-weight:bold;font-size:8pt;overflow:auto;'><br>조회
									// 결과 데이터가 없습니다.<br></P>");
								}
								else {
									// $('#NOTDATA').hide();
								} //
								// 처음 currentPage는 널값으로 세팅 (=1)
							}
							if ($('#paginate_' + PageGId).length > 0) {
								initPage(PageGId, allRowsInGrid, "");
							}
							// 끝
						}
					}
					, setHeadCheckBox = function(checked) {
						$('#cb_' + $.wizgrid.wizID(ts.p.id), ts.grid.hDiv)[ts.p.useProp ? 'prop' : 'attr']("checked"
							, checked);
						var fid = ts.p.frozenColumns ? ts.p.id + "_frozen" : "";
						if (fid) {
							$('#cb_' + $.wizgrid.wizID(ts.p.id), ts.grid.fhDiv)[ts.p.useProp ? 'prop' : 'attr']("checked"
								, checked);
						}
					}
					, setPager = function(pgid, tp) {
						// TBD - consider escaping pgid with pgid =
						// $.wizgrid.wizID(pgid);
						var sep =
							"<td class='ui-pg-button ui-state-disabled' style='width:4px;'><span class='ui-separator'></span></td>"
							, pginp = ""
							, pgl =
							"<table cellspacing='0' cellpadding='0' border='0' style='table-layout:auto;' class='ui-pg-table'><tbody><tr>"
							, str = ""
							, pgcnt, lft, cent, rgt, twd, tdw, i, clearVals = function(OnPaging) {
								var ret;
								if ($.isFunction(ts.p.OnPaging)) {
									ret = ts.p.OnPaging.call(ts, OnPaging);
								}
								if (ret === 'stop') {
									return false;
								}
								ts.p.selrow = null;
								if (ts.p.multiselect) {
									ts.p.selarrrow = [];
									setHeadCheckBox(false);
								}
								ts.p.savedRow = [];
								return true;
							};
						pgid = pgid.substr(1);
						tp += "_" + pgid;
						pgcnt = "pg_" + pgid;
						lft = pgid + "_left";
						cent = pgid + "_center";
						rgt = pgid + "_right";
						$("#" + $.wizgrid.wizID(pgid)).append("<div id='" + pgcnt +
							"' class='ui-pager-control' role='group'><table cellspacing='0' cellpadding='0' border='0' class='ui-pg-table' style='width:100%;table-layout:fixed;height:100%;'><tbody><tr><td id='" +
							lft + "' align='left'></td><td id='" + cent +
							"' align='center' style='white-space:pre;'></td><td id='" + rgt +
							"' align='right'></td></tr></tbody></table></div>").attr("dir", "ltr"); // explicit
						// setting
						if (ts.p.rowList.length > 0) {
							str = "<td dir=\"" + dir + "\">";
							str +=
								"<select class=\"ui-pg-selbox ui-widget-content-grid ui-corner-all-grid\" role=\"listbox\" title=\"" +
								($.wizgrid.getRegional(ts, "defaults.pgrecs", ts.p.pgrecs) || "") + "\">";
							var strnm;
							for (i = 0; i < ts.p.rowList.length; i++) {
								strnm = ts.p.rowList[i].toString().split(":");
								if (strnm.length === 1) {
									strnm[1] = strnm[0];
								}
								str += "<option role=\"option\" value=\"" + strnm[0] + "\"" + ((intNum(ts.p.rowNum, 0) === intNum(
									strnm[0], 0)) ? " selected=\"selected\"" : "") + ">" + strnm[1] + "</option>";
							}
							str += "</select></td>";
						}
						if (dir === "rtl") {
							pgl += str;
						}
						if (ts.p.pginput === true) {
							pginp = "<td id='input" + tp + "' dir='" + dir + "'>" + $.wizgrid.template($.wizgrid.getRegional(
									ts, "defaults.pgtext", ts.p.pgtext) || ""
								, "<input class='ui-pg-input' type='text' size='2' maxlength='7' value='0' role='textbox'/>"
								, "<span id='sp_1_" + $.wizgrid.wizID(pgid) + "'></span>") + "</td>";
						}
						if (ts.p.pgbuttons === true) {
							var po = ["first" + tp, "prev" + tp, "next" + tp, "last" + tp];
							if (dir === "rtl") {
								po.reverse();
							}
							pgl += "<td id='" + po[0] + "' class='ui-pg-button ui-corner-all-grid' title='" + ($.wizgrid.getRegional(
									ts, "defaults.pgfirst", ts.p.pgfirst) || "") + "'" +
								"><span class='ui-icon ui-icon-seek-first'></span></td>";
							pgl += "<td id='" + po[1] + "' class='ui-pg-button ui-corner-all-grid' title='" + ($.wizgrid.getRegional(
									ts, "defaults.pgprev", ts.p.pgprev) || "") + "'" +
								"><span class='ui-icon ui-icon-seek-prev'></span></td>";
							pgl += pginp !== "" ? sep + pginp + sep : "";
							pgl += "<td id='" + po[2] + "' class='ui-pg-button ui-corner-all-grid' title='" + ($.wizgrid.getRegional(
									ts, "defaults.pgnext", ts.p.pgnext) || "") + "'" +
								"><span class='ui-icon ui-icon-seek-next'></span></td>";
							pgl += "<td id='" + po[3] + "' class='ui-pg-button ui-corner-all-grid' title='" + ($.wizgrid.getRegional(
									ts, "defaults.pglast", ts.p.pglast) || "") + "'" +
								"><span class='ui-icon ui-icon-seek-end'></span></td>";
						}
						else if (pginp !== "") {
							pgl += pginp;
						}
						if (dir === "ltr") {
							pgl += str;
						}
						pgl += "</tr></tbody></table>";
						if (ts.p.viewrecords === true) {
							$("td#" + pgid + "_" + ts.p.recordpos, "#" + pgcnt).append("<div dir='" + dir +
								"' style='text-align:" + ts.p.recordpos + "' class='ui-paging-info'></div>");
						}
						$("td#" + pgid + "_" + ts.p.pagerpos, "#" + pgcnt).append(pgl);
						tdw = $(".ui-wizgrid").css("font-size") || "11px";
						$(document.body).append(
							"<div id='testpg' class='ui-wizgrid ui-widget ui-widget-content-grid' style='font-size:" + tdw +
							";visibility:hidden;' ></div>");
						twd = $(pgl).clone().appendTo("#testpg").width();
						$("#testpg").remove();
						if (twd > 0) {
							if (pginp !== "") {
								twd += 50;
							} // should be param
							$("td#" + pgid + "_" + ts.p.pagerpos, "#" + pgcnt).width(twd);
						}
						ts.p._nvtd = [];
						ts.p._nvtd[0] = twd ? Math.floor((ts.p.width - twd) / 2) : Math.floor(ts.p.width / 3);
						ts.p._nvtd[1] = 0;
						pgl = null;
						$('.ui-pg-selbox', "#" + pgcnt).bind('change', function() {
							if (!clearVals('records')) {
								return false;
							}
							ts.p.page = Math.round(ts.p.rowNum * (ts.p.page - 1) / this.value - 0.5) + 1;
							ts.p.rowNum = this.value;
							if (ts.p.pager) {
								$('.ui-pg-selbox', ts.p.pager).val(this.value);
							}
							if (ts.p.toppager) {
								$('.ui-pg-selbox', ts.p.toppager).val(this.value);
							}
							populate();
							return false;
						});
						if (ts.p.pgbuttons === true) {
							$(".ui-pg-button", "#" + pgcnt).hover(function() {
								if ($(this).hasClass('ui-state-disabled')) {
									this.style.cursor = 'default';
								}
								else {
									$(this).addClass('ui-state-hover-grid');
									this.style.cursor = 'pointer';
								}
							}, function() {
								if (!$(this).hasClass('ui-state-disabled')) {
									$(this).removeClass('ui-state-hover-grid');
									this.style.cursor = "default";
								}
							});
							$("#first" + $.wizgrid.wizID(tp) + ", #prev" + $.wizgrid.wizID(tp) + ", #next" + $.wizgrid.wizID(
								tp) + ", #last" + $.wizgrid.wizID(tp)).click(function() {
								if ($(this).hasClass("ui-state-disabled")) {
									return false;
								}
								var cp = intNum(ts.p.page, 1)
									, last = intNum(ts.p.lastpage, 1)
									, selclick = false
									, fp = true
									, pp = true
									, np = true
									, lp = true;
								if (last === 0 || last === 1) {
									fp = false;
									pp = false;
									np = false;
									lp = false;
								}
								else if (last > 1 && cp >= 1) {
									if (cp === 1) {
										fp = false;
										pp = false;
									}
									else if (cp === last) {
										np = false;
										lp = false;
									}
								}
								else if (last > 1 && cp === 0) {
									np = false;
									lp = false;
									cp = last - 1;
								}
								if (!clearVals(this.id.split("_")[0])) {
									return false;
								}
								if (this.id === 'first' + tp && fp) {
									ts.p.page = 1;
									selclick = true;
								}
								if (this.id === 'prev' + tp && pp) {
									ts.p.page = (cp - 1);
									selclick = true;
								}
								if (this.id === 'next' + tp && np) {
									ts.p.page = (cp + 1);
									selclick = true;
								}
								if (this.id === 'last' + tp && lp) {
									ts.p.page = last;
									selclick = true;
								}
								if (selclick) {
									populate();
								}
								return false;
							});
						}
						if (ts.p.pginput === true) {
							$("#" + pgcnt).on('keypress', 'input.ui-pg-input', function(e) {
								var key = e.charCode || e.keyCode || 0;
								if (key === 13) {
									if (!clearVals('user')) {
										return false;
									}
									$(this).val(intNum($(this).val(), 1));
									ts.p.page = ($(this).val() > 0) ? $(this).val() : ts.p.page;
									populate();
									return false;
								}
								return this;
							});
						}
					}
					, multiSort = function(iCol, obj) {
						var splas, sort = ""
							, cm = ts.p.Columns
							, fs = false
							, ls, selTh = ts.p.frozenColumns ? obj : ts.grid.headers[iCol].el
							, so = "";
						$("span.ui-grid-ico-sort", selTh).addClass('ui-state-disabled');
						$(selTh).attr("aria-selected", "false");
						if (cm[iCol].lso) {
							if (cm[iCol].lso === "asc") {
								cm[iCol].lso += "-desc";
								so = "desc";
							}
							else if (cm[iCol].lso === "desc") {
								cm[iCol].lso += "-asc";
								so = "asc";
							}
							else if (cm[iCol].lso === "asc-desc" || cm[iCol].lso === "desc-asc") {
								cm[iCol].lso = "";
							}
						}
						else {
							cm[iCol].lso = so = cm[iCol].firstsortorder || 'asc';
						}
						if (so) {
							$("span.s-ico", selTh).show();
							$("span.ui-icon-" + so, selTh).removeClass('ui-state-disabled');
							$(selTh).attr("aria-selected", "true");
						}
						else {
							if (!ts.p.viewsortcols[0]) {
								$("span.s-ico", selTh).hide();
							}
						}
						ts.p.sortorder = "";
						$.each(cm, function(i) {
							if (this.lso) {
								if (i > 0 && fs) {
									sort += ", ";
								}
								splas = this.lso.split("-");
								sort += cm[i].index || cm[i].name;
								sort += " " + splas[splas.length - 1];
								fs = true;
								ts.p.sortorder = splas[splas.length - 1];
							}
						});
						ls = sort.lastIndexOf(ts.p.sortorder);
						sort = sort.substring(0, ls);
						ts.p.sortname = sort;
					}
					, sortData = function(index, idxcol, reload, sor, obj) {
						// row hidden 처리 했을때 컬럼 sort 하게되면 다시 row 히든처리가
						// 풀리는 현상 컬럼정렬하게 되면 flag I값은 삭제
						/*
						 * var FlagI_length =
						 * $(ts).getModifiedRow("I").length
						 * 
						 * if(FlagI_length > 0) { alert( "행이 추가된 경우
						 * Column Sort 불가능 합니다."); return; } for(var fi =
						 * 0; fi <= FlagI_length-1 ; fi++){ alert(fi);
						 * var F_RowId =
						 * $(ts).getModifiedRow("I")[fi].id;
						 * $('#'+ts.p.id).delRowData(F_RowId); }
						 */
						// 끝
						if (!ts.p.Columns[idxcol].sortable) {
							return;
						}
						if (ts.p.savedRow.length > 0) {
							return;
						}
						if (!reload) {
							if (ts.p.lastsort === idxcol && ts.p.sortname !== "") {
								if (ts.p.sortorder === 'asc') {
									ts.p.sortorder = 'desc';
								}
								else if (ts.p.sortorder === 'desc') {
									ts.p.sortorder = 'asc';
								}
							}
							else {
								ts.p.sortorder = ts.p.Columns[idxcol].firstsortorder || 'asc';
							}
							ts.p.page = 1;
						}
						if (ts.p.multiSort) {
							multiSort(idxcol, obj);
						}
						else {
							if (sor) {
								if (ts.p.lastsort === idxcol && ts.p.sortorder === sor && !reload) {
									return;
								}
								ts.p.sortorder = sor;
							}
							var previousSelectedTh = ts.grid.headers[ts.p.lastsort] ? ts.grid.headers[ts.p.lastsort].el : null
								, newSelectedTh = ts.p.frozenColumns ? obj : ts.grid.headers[idxcol].el;
							$("span.ui-grid-ico-sort", previousSelectedTh).addClass('ui-state-disabled');
							$(previousSelectedTh).attr("aria-selected", "false");
							if (ts.p.frozenColumns) {
								ts.grid.fhDiv.find("span.ui-grid-ico-sort").addClass('ui-state-disabled');
								ts.grid.fhDiv.find("th").attr("aria-selected", "false");
							}
							$("span.ui-icon-" + ts.p.sortorder, newSelectedTh).removeClass('ui-state-disabled');
							$(newSelectedTh).attr("aria-selected", "true");
							if (!ts.p.viewsortcols[0]) {
								if (ts.p.lastsort !== idxcol) {
									if (ts.p.frozenColumns) {
										ts.grid.fhDiv.find("span.s-ico").hide();
									}
									$("span.s-ico", previousSelectedTh).hide();
									$("span.s-ico", newSelectedTh).show();
								}
								else if (ts.p.sortname === "") { // if
									// ts.p.lastsort
									// ===
									// idxcol
									// but
									// ts.p.sortname
									// ===
									// ""
									$("span.s-ico", newSelectedTh).show();
								}
							}
							index = index.substring(5 + ts.p.id.length + 1); // bad
							// to
							// be
							// changed!?!
							ts.p.sortname = ts.p.Columns[idxcol].index || index;
						}
						if ($(ts).triggerHandler("wizGridSortCol", [ts.p.sortname, idxcol
								, ts.p.sortorder
							]) === 'stop') {
							ts.p.lastsort = idxcol;
							return;
						}
						if ($.isFunction(ts.p.OnColumnSort)) {
							if (ts.p.OnColumnSort.call(ts, ts.p.sortname, idxcol, ts.p.sortorder) === 'stop') {
								ts.p.lastsort = idxcol;
								return;
							}
						}
						if (ts.p.datatype === "local") {
							if (ts.p.deselectAfterSort) {
								$(ts).wizGrid("resetSelection");
							}
						}
						else {
							ts.p.selrow = null;
							if (ts.p.multiselect) {
								setHeadCheckBox(false);
							}
							ts.p.selarrrow = [];
							ts.p.savedRow = [];
						}
						if (ts.p.scroll) {
							var sscroll = ts.grid.bDiv.scrollLeft;
							emptyRows.call(ts, true, false);
							ts.grid.hDiv.scrollLeft = sscroll;
						}
						if (ts.p.subGrid && ts.p.datatype === 'local') {
							$("td.sgexpanded", "#" + $.wizgrid.wizID(ts.p.id)).each(function() {
								$(this).trigger("click");
							});
						}
						ts.p._sort = true;
						populate();
						ts.p.lastsort = idxcol;
						if (ts.p.sortname !== index && idxcol) {
							ts.p.lastsort = idxcol;
						}
					}
					, setColWidth = function() {
						var initwidth = 0
							, brd = $.wizgrid.cell_width ? 0 : intNum(ts.p.cellLayout, 0)
							, vc = 0
							, lvc, scw = intNum(ts.p.scrollOffset, 0)
							, cw, hs = false
							, aw, gw = 0
							, cr;
						$.each(ts.p.Columns, function() {
							if (this.hidden === undefined) {
								this.hidden = false;
							}
							if (ts.p.grouping && ts.p.autowidth) {
								var ind = $.inArray(this.name, ts.p.groupingView.groupField);
								if (ind >= 0 && ts.p.groupingView.groupColumnShow.length > ind) {
									this.hidden = !ts.p.groupingView.groupColumnShow[ind];
								}
							}
							this.widthOrg = cw = intNum(this.width, 0);
							if (this.hidden === false) {
								initwidth += cw + brd;
								if (this.fixed) {
									gw += cw + brd;
								}
								else {
									vc++;
								}
							}
						});
						if (isNaN(ts.p.width)) {
							ts.p.width = initwidth + ((ts.p.shrinkToFit === false && !isNaN(ts.p.height)) ? scw : 0);
						}
						grid.width = ts.p.width;
						ts.p.tblwidth = initwidth;
						if (ts.p.shrinkToFit === false && ts.p.forceFit === true) {
							ts.p.forceFit = false;
						}
						if (ts.p.shrinkToFit === true && vc > 0) {
							aw = grid.width - brd * vc - gw;
							if (!isNaN(ts.p.height)) {
								aw -= scw;
								hs = true;
							}
							initwidth = 0;
							$.each(ts.p.Columns, function(i) {
								if (this.hidden === false && !this.fixed) {
									cw = Math.round(aw * this.width / (ts.p.tblwidth - brd * vc - gw));
									this.width = cw;
									initwidth += cw;
									lvc = i;
								}
							});
							cr = 0;
							if (hs) {
								if (grid.width - gw - (initwidth + brd * vc) !== scw) {
									cr = grid.width - gw - (initwidth + brd * vc) - scw;
								}
							}
							else if (!hs && Math.abs(grid.width - gw - (initwidth + brd * vc)) !== 1) {
								cr = grid.width - gw - (initwidth + brd * vc);
							}
							ts.p.Columns[lvc].width += cr;
							ts.p.tblwidth = initwidth + cr + brd * vc + gw;
							if (ts.p.tblwidth > ts.p.width) {
								ts.p.Columns[lvc].width -= (ts.p.tblwidth - parseInt(ts.p.width, 10));
								ts.p.tblwidth = ts.p.width;
							}
						}
					}
					, nextVisible = function(iCol) {
						var ret = iCol
							, j = iCol
							, i;
						for (i = iCol + 1; i < ts.p.Columns.length; i++) {
							if (ts.p.Columns[i].hidden !== true) {
								j = i;
								break;
							}
						}
						return j - ret;
					}
					, getOffset = function(iCol) {
						var $th = $(ts.grid.headers[iCol].el)
							, ret = [$th.position().left + $th.outerWidth()];
						if (ts.p.direction === "rtl") {
							ret[0] = ts.p.width - ret[0];
						}
						ret[0] -= ts.grid.bDiv.scrollLeft;
						ret.push($(ts.grid.hDiv).position().top);
						ret.push($(ts.grid.bDiv).offset().top - $(ts.grid.hDiv).offset().top + $(ts.grid.bDiv).height());
						return ret;
					}
					, getColumnHeaderIndex = function(th) {
						var i, headers = ts.grid.headers
							, ci = $.wizgrid.getCellIndex(th);
						for (i = 0; i < headers.length; i++) {
							if (th === headers[i].el) {
								ci = i;
								break;
							}
						}
						return ci;
					}
					, colTemplate;
				this.p.id = this.id;
				if ($.inArray(ts.p.multikey, sortkeys) === -1) {
					ts.p.multikey = false;
				}
				ts.p.keyName = false;
				for (i = 0; i < ts.p.Columns.length; i++) {
					colTemplate = typeof ts.p.Columns[i].template === "string" ? ($.wizgrid.cmTemplate != null && typeof $
						.wizgrid.cmTemplate[ts.p.Columns[i].template] === "object" ? $.wizgrid.cmTemplate[ts.p.Columns[i].template] : {}
					) : ts.p.Columns[i].template;
					ts.p.Columns[i] = $.extend(true, {}, ts.p.cmTemplate, colTemplate || {}, ts.p.Columns[i]);
					if (ts.p.keyName === false && ts.p.Columns[i].key === true) {
						ts.p.keyName = ts.p.Columns[i].name;
					}
				}
				ts.p.sortorder = ts.p.sortorder.toLowerCase();
				$.wizgrid.cell_width = $.wizgrid.cellWidth();
				if (ts.p.grouping === true) {
					ts.p.scroll = false;
					ts.p.rownumbers = false;
					// ts.p.subGrid = false; expiremental
					ts.p.treeGrid = false;
					ts.p.gridview = true;
				}
				if (this.p.treeGrid === true) {
					try {
						$(this).wizGrid("setTreeGrid");
					}
					catch (_) {}
					if (ts.p.datatype !== "local") {
						ts.p.localReader = {
							id: "_id_"
						};
					}
				}
				if (this.p.subGrid) {
					try {
						$(ts).wizGrid("setSubGrid");
					}
					catch (s) {}
				}
				if (this.p.multiselect) {
					this.p.Names.unshift("<input role='checkbox' id='cb_" + this.p.id +
						"' class='cbox' type='checkbox'/>");
					this.p.Columns.unshift({
						name: 'cb'
						, width: $.wizgrid.cell_width ? ts.p.multiselectWidth + ts.p.cellLayout : ts.p.multiselectWidth
						, sortable: false
						, resizable: false
						, hidedlg: true
						, search: false
						, align: 'center'
						, fixed: true
					});
				}
				if (this.p.rownumbers) {
					this.p.Names.unshift("");
					this.p.Columns.unshift({
						name: 'rn'
						, width: ts.p.rownumWidth
						, sortable: false
						, resizable: false
						, hidedlg: true
						, search: false
						, align: 'center'
						, fixed: true
					});
				}
				ts.p.xmlReader = $.extend(true, {
					root: "rows"
					, row: "row"
					, page: "rows>page"
					, total: "rows>total"
					, records: "rows>records"
					, repeatitems: true
					, cell: "cell"
					, id: "[id]"
					, userdata: "userdata"
					, subgrid: {
						root: "rows"
						, row: "row"
						, repeatitems: true
						, cell: "cell"
					}
				}, ts.p.xmlReader);
				ts.p.jsonReader = $.extend(true, {
					root: "rows"
					, page: "page"
					, total: "total"
					, records: "records"
					, repeatitems: true
					, cell: "cell"
					, id: "id"
					, userdata: "userdata"
					, subgrid: {
						root: "rows"
						, repeatitems: true
						, cell: "cell"
					}
				}, ts.p.jsonReader);
				ts.p.localReader = $.extend(true, {
					root: "rows"
					, page: "page"
					, total: "total"
					, records: "records"
					, repeatitems: false
					, cell: "cell"
					, id: "id"
					, userdata: "userdata"
					, subgrid: {
						root: "rows"
						, repeatitems: true
						, cell: "cell"
					}
				}, ts.p.localReader);
				if (ts.p.scroll) {
					ts.p.pgbuttons = false;
					ts.p.pginput = false;
					ts.p.rowList = [];
				}
				if (ts.p.data.length) {
					normalizeData();
					refreshIndex();
				}
				// Gird Head Column 높이 설정
				var thead = "<thead><tr class='ui-wizgrid-labels' colheight=" + ts.p.id + " role='row'>"
					, tdc, idn, w, res, sort, td, ptr, tbody, imgs, iac = ""
					, idc = ""
					, sortarr = []
					, sortord = []
					, sotmp = [];
				if (ts.p.shrinkToFit === true && ts.p.forceFit === true) {
					for (i = ts.p.Columns.length - 1; i >= 0; i--) {
						if (!ts.p.Columns[i].hidden) {
							ts.p.Columns[i].resizable = false;
							break;
						}
					}
				}
				if (ts.p.viewsortcols[1] === 'horizontal') {
					iac = " ui-i-asc";
					idc = " ui-i-desc";
				}
				tdc = isMSIE ? "class='ui-th-div-ie'" : "";
				imgs =
					"<span class='s-ico' style='display:none'><span sort='asc' class='ui-grid-ico-sort ui-icon-asc" +
					iac + " ui-state-disabled ui-icon ui-icon-triangle-1-n ui-sort-" + dir + "'></span>";
				imgs += "<span sort='desc' class='ui-grid-ico-sort ui-icon-desc" + idc +
					" ui-state-disabled ui-icon ui-icon-triangle-1-s ui-sort-" + dir + "'></span></span>";
				if (ts.p.multiSort) {
					sortarr = ts.p.sortname.split(",");
					for (i = 0; i < sortarr.length; i++) {
						sotmp = $.trim(sortarr[i]).split(" ");
						sortarr[i] = $.trim(sotmp[0]);
						sortord[i] = sotmp[1] ? $.trim(sotmp[1]) : ts.p.sortorder || "asc";
					}
				}
				for (i = 0; i < this.p.Names.length; i++) {
					if (ts.p.Columns[i].name == "cb") { // pjy 추가
						// 멀티체크
						var tooltip = ts.p.headertitles ? (" title=\"" + $.wizgrid.stripHtml(ts.p.Names[i]) + "\"") : "";
						thead += "<th id='" + ts.p.id + "_" + ts.p.Columns[i].name +
							"' role='columnheader' class='ui-state-default-grid ui-th-column ui-th-" + dir + "'" + tooltip +
							">";
						//
						idn = ts.p.Columns[i].index || ts.p.Columns[i].name;
						thead += "<div  id='wizgh_" + ts.p.id + "_" + ts.p.Columns[i].name + "' " + tdc + ">" + ts.p.Names[
							i];
						if (!ts.p.Columns[i].width) {
							ts.p.Columns[i].width = 150;
						}
						else {
							ts.p.Columns[i].width = parseInt(ts.p.Columns[i].width, 30);
						}
						if (typeof ts.p.Columns[i].title !== "boolean") {
							ts.p.Columns[i].title = true;
						}
						ts.p.Columns[i].lso = "";
						if (idn === ts.p.sortname) {
							ts.p.lastsort = i;
						}
						if (ts.p.multiSort) {
							sotmp = $.inArray(idn, sortarr);
							if (sotmp !== -1) {
								ts.p.Columns[i].lso = sortord[sotmp];
							}
						}
						thead += "삭제";
						thead += imgs + "</div></th>";
					}
					else if (ts.p.Columns[i].name == "rn") { // pjy
						// 추가
						// rownumber
						var tooltip = ts.p.headertitles ? (" title=\"" + $.wizgrid.stripHtml(ts.p.Names[i]) + "\"") : "";
						thead += "<th id='" + ts.p.id + "_" + ts.p.Columns[i].name +
							"' role='columnheader' class='ui-state-default-grid ui-th-column ui-th-" + dir + "'" + tooltip +
							">";
						thead += "No";
						idn = ts.p.Columns[i].index || ts.p.Columns[i].name;
						thead += "<div  id='wizgh_" + ts.p.id + "_" + ts.p.Columns[i].name + "' " + tdc + ">" + ts.p.Names[
							i];
						if (!ts.p.Columns[i].width) {
							ts.p.Columns[i].width = 150;
						}
						else {
							ts.p.Columns[i].width = parseInt(ts.p.Columns[i].width, 10);
						}
						if (typeof ts.p.Columns[i].title !== "boolean") {
							ts.p.Columns[i].title = true;
						}
						ts.p.Columns[i].lso = "";
						if (idn === ts.p.sortname) {
							ts.p.lastsort = i;
						}
						if (ts.p.multiSort) {
							sotmp = $.inArray(idn, sortarr);
							if (sotmp !== -1) {
								ts.p.Columns[i].lso = sortord[sotmp];
							}
						}
						thead += imgs + "</div></th>";
					}
					else {
						var tooltip = ts.p.headertitles ? (" title=\"" + $.wizgrid.stripHtml(ts.p.Names[i]) + "\"") : "";
						thead += "<th id='" + ts.p.id + "_" + ts.p.Columns[i].name +
							"' role='columnheader' class='ui-state-default-grid ui-th-column ui-th-" + dir + "'" + tooltip +
							">";
						idn = ts.p.Columns[i].index || ts.p.Columns[i].name;
						thead += "<div  id='wizgh_" + ts.p.id + "_" + ts.p.Columns[i].name + "' " + tdc + ">" + ts.p.Names[
							i];
						if (!ts.p.Columns[i].width) {
							ts.p.Columns[i].width = 150;
						}
						else {
							ts.p.Columns[i].width = parseInt(ts.p.Columns[i].width, 10);
						}
						if (typeof ts.p.Columns[i].title !== "boolean") {
							ts.p.Columns[i].title = true;
						}
						ts.p.Columns[i].lso = "";
						if (idn === ts.p.sortname) {
							ts.p.lastsort = i;
						}
						if (ts.p.multiSort) {
							sotmp = $.inArray(idn, sortarr);
							if (sotmp !== -1) {
								ts.p.Columns[i].lso = sortord[sotmp];
							}
						}
						thead += imgs + "</div></th>";
					}
				}
				thead += "</tr></thead>";
				imgs = null;
				$(this).append(thead);
				$("thead tr:first th", this).hover(function() {
					$(this).addClass('ui-state-hover-grid');
				}, function() {
					$(this).removeClass('ui-state-hover-grid');
				});
				if (this.p.multiselect) {
					var emp = []
						, chk;
					$('#cb_' + $.wizgrid.wizID(ts.p.id), this).bind('click', function() {
						ts.p.selarrrow = [];
						var froz = ts.p.frozenColumns === true ? ts.p.id + "_frozen" : "";
						if (this.checked) {

							// cell을 닫는다.
						if($('#'+ts.p.id).getCurrentRowIndex() != -1) $('#'+ts.p.id).editRowClose($('#'+ts.p.id).getCurrentRowIndex());
						
										 
					$(ts.rows).each(function(i) {
						if (i>0) {
							if(!$(this).hasClass("ui-subgrid") && !$(this).hasClass("wizgroup") && !$(this).hasClass('ui-state-disabled') && !$(this).hasClass("wizfoot")){

							

								$("#wizg_"+$.wizgrid.wizID(ts.p.id)+"_"+$.wizgrid.wizID(this.id) )[ts.p.useProp ? 'prop': 'attr']("checked",true);
								$(this).addClass("ui-state-highlight-grid").attr("aria-selected","true");  
								ts.p.selarrrow.push(this.id);
								ts.p.selrow = this.id;
								
							    //멀티체크 클릭시 pjy 전체체크
								// pjy start 삭제 플레그 처리
								
								var strAt =$('#'+ts.p.id).wizGrid('getCell',this.id, "IUDFLAG");
								
								  if("I" == strAt && strAt !== false){  
											//IUDFLAG가 I이면 삭제 처리 실행
											$('#'+ts.p.id).delRowData(this.id);
										}
						    
							     var newValue='D'; 
								// alert(strAt + " @ " + ts.p.id + " # " + this.id);
								$('#'+ts.p.id).wizGrid('setCell', this.id, 'IUDFLAG', newValue);

							var colSaveName= $('#'+ts.p.id).wizGrid('getGridParam', 'Columns');
						
						 // 수정 못하게 스타일 입힌다.
							$('#'+ts.p.id +"> tbody > #R"+i +" > td").addClass("not-editable-cell");
							//$('#'+PageGId +"> tbody > #R"+i +" > td:eq("+(j)+")")
						/* for( var j=0; j <colSaveName.length-1; j++){
									//alert('#checkbox_'+this.id+'_'+ colSaveName[j]['name']);
									//$($t).wizGrid("editCell",i,j,false);
									$('#'+ts.p.id).wizGrid('setCell',this.id,colSaveName[j]['name'],'','not-editable-cell');
									//$('#checkbox_'+this.id+'_'+ colSaveName[j]['name']).attr('disabled','disabled');	
									
							   }*/

				 			  //end

					
								if(froz) {
									$("#wizg_"+$.wizgrid.wizID(ts.p.id)+"_"+$.wizgrid.wizID(this.id), ts.grid.fbDiv )[ts.p.useProp ? 'prop': 'attr']("checked",true);
									$("#"+$.wizgrid.wizID(this.id), ts.grid.fbDiv).addClass("ui-state-highlight-grid");
								}
							}
						}
					});
					chk=true;
					emp=[];
				}
				else {
					$(ts.rows).each(function(i) {
						if(i>0) 
						{
							if(!$(this).hasClass("ui-subgrid") && !$(this).hasClass("wizgroup") && !$(this).hasClass('ui-state-disabled') && !$(this).hasClass("wizfoot"))
							{
							
								
								$("#wizg_"+$.wizgrid.wizID(ts.p.id)+"_"+$.wizgrid.wizID(this.id) )[ts.p.useProp ? 'prop': 'attr']("checked", false);
								$(this).removeClass("ui-state-highlight-grid").attr("aria-selected","false");
								emp.push(this.id);

									//멀티체크 해제할때 pjy
									  var newValue='R';
									  var colSaveName= $('#'+ts.p.id).wizGrid('getGridParam', 'Columns');
								$('#'+ts.p.id).wizGrid('setCell', this.id, 'IUDFLAG', newValue);
								$('#'+ts.p.id +"> tbody > #R"+i +" > td").removeClass("not-editable-cell");
								 /*for( var j=0; j <colSaveName.length-1; j++){
								
									 $("#"+ts.p.id+" tr[id='"+this.id+"'] td[aria-describedby="+ts.p.id+"_"+colSaveName[j]['name']+"]").removeClass('not-editable-cell'); 
									//$('#checkbox_'+this.id+'_'+ colSaveName[j]['name']).removeAttr('disabled');
									
							   }*/
								//end
								if(froz) {
									$("#wizg_"+$.wizgrid.wizID(ts.p.id)+"_"+$.wizgrid.wizID(this.id), ts.grid.fbDiv )[ts.p.useProp ? 'prop': 'attr']("checked",false);
									$("#"+$.wizgrid.wizID(this.id), ts.grid.fbDiv).removeClass("ui-state-highlight-grid");
								}
							}
						}
					});
					ts.p.selrow = null;
					chk=false;
				}				
				$(ts).triggerHandler("wizGridSelectAll", [chk ? ts.p.selarrrow : emp, chk]);
				if($.isFunction(ts.p.OnSelectAll)) {ts.p.OnSelectAll.call(ts, chk ? ts.p.selarrrow : emp,chk);}
			});
		}
				if (ts.p.autowidth === true) {
					var pw = $(eg).innerWidth();
					ts.p.width = pw > 0 ? pw : 'nw';
				}
				setColWidth();
				$(eg).css("width", grid.width + "px").append("<div class='ui-wizgrid-resize-mark' id='rs_m" + ts.p.id +
					"'>&#160;</div>");
				if (ts.p.scrollPopUp) {
					$(eg).append("<div class='loading ui-scroll-popup ui-widget-content-grid' id='scroll_g" + ts.p.id +
						"'></div>");
				}
				$(gv).css("width", grid.width + "px");
				thead = $("thead:first", ts).get(0);
				var tfoot = "";
				if (ts.p.footerrow) {
					tfoot += "<table role='presentation' style='width:" + ts.p.tblwidth +
						"px' class='ui-wizgrid-ftable' cellspacing='0' cellpadding='0' border='0'><tbody><tr role='row' class='ui-widget-content-grid footrow footrow-" +
						dir + "'>";
				}
				var thr = $("tr:first", thead)
					, firstr = "<tr class='wizgfirstrow' role='row' style='height:auto'>";
				ts.p.disableClick = false;
				$("th", thr).each(function(j) { // pjy 팝업,달력 이 있으면 그
					// 전 칼럼 resizable를
					// false로 무조건 변경하기
					// 위해서 추가
					// alert(ts.p.Columns[j].Gtype);
					if (ts.p.Columns[j].formatter == 'popup') {
						ts.p.Columns[j - 1].resizable = false;
						$(this).attr('eltype','popup');
						
					}else if(ts.p.Columns[j].formatter == 'calendar'){
						ts.p.Columns[j - 1].resizable = false;	
						$(this).attr('eltype','calendar');
						
					}
				}); // end
				$("th", thr).each(function(j) {
					w = ts.p.Columns[j].width;
					if (ts.p.Columns[j].resizable === undefined) {
						ts.p.Columns[j].resizable = true;
					}
					if (ts.p.Columns[j].resizable) {
						res = document.createElement("span");
						$(res).html("&#160;").addClass('ui-wizgrid-resize ui-wizgrid-resize-' + dir).css("cursor"
							, "col-resize");
						$(this).addClass(ts.p.resizeclass);
					}
					else {
						res = "";
					}
					$(this).css("width", w + "px").prepend(res);
					res = null;
					var hdcol = "";
					if (ts.p.Columns[j].hidden) {
						$(this).css("display", "none");
						hdcol = "display:none;";
					}
					firstr += "<td role='gridcell' style='height:0px;width:" + w + "px;" + hdcol + "'></td>";
					grid.headers[j] = {
						width: w
						, el: this
					};
					sort = ts.p.Columns[j].sortable;
					if (typeof sort !== 'boolean') {
						ts.p.Columns[j].sortable = true;
						sort = true;
					}
					var nm = ts.p.Columns[j].name;
					if (!(nm === 'cb' || nm === 'subgrid' || nm === 'rn')) {
						if (ts.p.viewsortcols[2]) {
							$(">div", this).addClass('ui-wizgrid-sortable');
						}
					}
					if (sort) {
						if (ts.p.multiSort) {
							if (ts.p.viewsortcols[0]) {
								$("div span.s-ico", this).show();
								if (ts.p.Columns[j].lso) {
									$("div span.ui-icon-" + ts.p.Columns[j].lso, this).removeClass("ui-state-disabled");
								}
							}
							else if (ts.p.Columns[j].lso) {
								$("div span.s-ico", this).show();
								$("div span.ui-icon-" + ts.p.Columns[j].lso, this).removeClass("ui-state-disabled");
							}
						}
						else {
							if (ts.p.viewsortcols[0]) {
								$("div span.s-ico", this).show();
								if (j === ts.p.lastsort) {
									$("div span.ui-icon-" + ts.p.sortorder, this).removeClass("ui-state-disabled");
								}
							}
							else if (j === ts.p.lastsort && ts.p.sortname !== "") {
								$("div span.s-ico", this).show();
								$("div span.ui-icon-" + ts.p.sortorder, this).removeClass("ui-state-disabled");
							}
						}
					}
					if (ts.p.footerrow) {
						tfoot += "<td role='gridcell' " + formatCol(j, 0, '', null, '', false) + ">&#160;</td>";
					}
				}).mousedown(function(e) {
					
					if ($(e.target).closest("th>span.ui-wizgrid-resize").length !== 1) {
						return;
					}
					var ci = getColumnHeaderIndex(this);
					if (ts.p.forceFit === true) {
						ts.p.nv = nextVisible(ci);
					}
					grid.dragStart(ci, e, getOffset(ci));
					return false;
				}).click(function(e) {
					
					if (ts.p.disableClick) {
						ts.p.disableClick = false;
						return false;
					}
					var s = "th>div.ui-wizgrid-sortable"
						, r, d;
					if (!ts.p.viewsortcols[2]) {
						s = "th>div>span>span.ui-grid-ico-sort";
					}
					var t = $(e.target).closest(s);
					if (t.length !== 1) {
						return;
					}
					var ci;
					if (ts.p.frozenColumns) {
						var tid = $(this)[0].id.substring(ts.p.id.length + 1);
						$(ts.p.Columns).each(function(i) {
							if (this.name === tid) {
								ci = i;
								return false;
							}
						});
					}
					else {
						ci = getColumnHeaderIndex(this);
					}
					if (!ts.p.viewsortcols[2]) {
						r = true;
						d = t.attr("sort");
					}
					if (ci != null) {
						sortData($('div', this)[0].id, ci, r, d, this);
					}
					return false;
				});
				if (ts.p.sortable && $.fn.sortable) {
					try {
					
						$(ts).wizGrid("sortableColumns", thr);
					}
					catch (e) {}
				}
				if (ts.p.footerrow) {
					tfoot += "</tr></tbody></table>";
				}
				firstr += "</tr>";
				tbody = document.createElement("tbody");
				this.appendChild(tbody);
				$(this).addClass('ui-wizgrid-btable').append(firstr);
				firstr = null;
				var hTable = $("<table class='ui-wizgrid-htable' style='width:" + ts.p.tblwidth +
						"px;' role='presentation' aria-labelledby='gbox_" + this.id +
						"' cellspacing='0' cellpadding='0' border='0'></table>").append(thead)
					, hg = (ts.p.caption && ts.p.hiddengrid === true) ? true : false
					, hb = $("<div class='ui-wizgrid-hbox" + (dir === "rtl" ? "-rtl" : "") + "'></div>");
				thead = null;
				grid.hDiv = document.createElement("div");
				$(grid.hDiv).css({
					width: grid.width + "px"
				}).addClass("ui-state-default-grid ui-wizgrid-hdiv").append(hb);
				$(hb).append(hTable);
				hTable = null;
				if (hg) {
					$(grid.hDiv).hide();
				}
				if (ts.p.pager) {
					// TBD -- escape ts.p.pager here?
					if (typeof ts.p.pager === "string") {
						if (ts.p.pager.substr(0, 1) !== "#") {
							ts.p.pager = "#" + ts.p.pager;
						}
					}
					else {
						ts.p.pager = "#" + $(ts.p.pager).attr("id");
					}
					$(ts.p.pager).css({
						width: grid.width + "px"
					}).addClass('ui-state-default-grid ui-wizgrid-pager ui-corner-bottom-grid').appendTo(eg);
					if (hg) {
						$(ts.p.pager).hide();
					}
					setPager(ts.p.pager, '');
				}
				if (ts.p.cellEdit === false && ts.p.hoverrows === true) {
					$(ts).bind('mouseover', function(e) {  // 마우스 오버시
						ptr = $(e.target).closest("tr.wizgrow").css('cursor','pointer');//마우스 손모양 추가.
						if ($(ptr).attr("class") !== "ui-subgrid") {
							$(ptr).addClass("ui-state-hover-grid");
						}
					}).bind('mouseout', function(e) {
						ptr = $(e.target).closest("tr.wizgrow");
						$(ptr).removeClass("ui-state-hover-grid");
					});
				}
				var ri, ci, tdHtml;
				$(ts).before(grid.hDiv).click(function(e) {
					td = e.target;
					ptr = $(td, ts.rows).closest("tr.wizgrow");
					if ($(ptr).length === 0 || ptr[0].className.indexOf('ui-state-disabled') > -1 || ($(td, ts).closest(
							"table.ui-wizgrid-btable").attr('id') || '').replace("_frozen", "") !== ts.id) {
						return this;
					}
					var scb = $(td).hasClass("cbox")
						, cSel = $(ts).triggerHandler("wizGridBeforeSelectRow", [ptr[0].id, e]);
					cSel = (cSel === false || cSel === 'stop') ? false : true;
					if ($.isFunction(ts.p.OnBeforeSelectRow)) {
						var allowRowSelect = ts.p.OnBeforeSelectRow.call(ts, ptr[0].id, e);
						var cellSelectO = $(this).MultiselectCheck(ptr[0].id, e); // ----pjy
						// 2015-08-17
						if (allowRowSelect === false || allowRowSelect === 'stop') {
							cSel = false;
						}
					}
					else {
						var cellSelectO = $(this).MultiselectCheck(ptr[0].id, e); // pjy
						// 2015-08-17
						if (cellSelectO === false) {
							cSel = false;
						}
					}
					if (td.tagName === 'A' || ((td.tagName === 'INPUT' || td.tagName === 'TEXTAREA' || td.tagName ===
							'OPTION' || td.tagName === 'SELECT') && !scb)) {
						return;
					}
					ri = ptr[0].id;
					td = $(td).closest("tr.wizgrow>td");
					if (td.length > 0) {
						ci = $.wizgrid.getCellIndex(td);
						tdHtml = $(td).closest("td,th").html();
						$(ts).triggerHandler("wizGridCellSelect", [
							ri
							, ci
							, tdHtml
							, e
						]);
						if ($.isFunction(ts.p.OnCellSelect)) {
							ts.p.OnCellSelect.call(ts, ri, ci, tdHtml, e);
						

							// pjy 페이징 선택로우값 필요해서
							// 추가함
							var PagingCurrentId = 0;
							PagingCurrentId = ri.replace("R", "");
							var PageID = $(ts);
							var PageGId = ts.p.id;
							var allRowsInGrid = PageID.wizGrid('getGridParam', 'records');
							// $("#paginate_"+PageGId).append("["+PagingCurrentId+"/"+allRowsInGrid+"]");
							// end
						}
					}
						
					if (ts.p.cellEdit === true) {
						if (ts.p.multiselect && scb && cSel) {
							$(ts).wizGrid("setSelection", ri, true, e);
						}
						else if (td.length > 0) {
							ri = ptr[0].rowIndex;
							try {
								$(ts).wizGrid("editCell", ri, ci, true);
							}
							catch (_) {}
						}
					}
					if (!cSel) {
						return;
					}
					if (!ts.p.multikey) {
						if (ts.p.multiselect && ts.p.multiboxonly) {
							if (scb) {
								$(ts).wizGrid("setSelection", ri, true, e);
							}
							else {
								var frz = ts.p.frozenColumns ? ts.p.id + "_frozen" : "";
								$(ts.p.selarrrow).each(function(i, n) {
									var trid = $(ts).wizGrid('getGridRowById', n);
									if (trid) {
										$(trid).removeClass("ui-state-highlight-grid");
									}
									$("#wizg_" + $.wizgrid.wizID(ts.p.id) + "_" + $.wizgrid.wizID(n))[ts.p.useProp ? 'prop' :
											'attr']
										("checked", false);
									if (frz) {
										$("#" + $.wizgrid.wizID(n), "#" + $.wizgrid.wizID(frz)).removeClass(
											"ui-state-highlight-grid");
										$("#wizg_" + $.wizgrid.wizID(ts.p.id) + "_" + $.wizgrid.wizID(n), "#" + $.wizgrid.wizID(frz))[
												ts.p.useProp ? 'prop' : 'attr']
											("checked", false);
									}
								});
								ts.p.selarrrow = [];
								$(ts).wizGrid("setSelection", ri, true, e);
							}
						}
						else {
							$(ts).wizGrid("setSelection", ri, true, e);
						}
					}
					else {
						if (e[ts.p.multikey]) {
							$(ts).wizGrid("setSelection", ri, true, e);
						}
						else if (ts.p.multiselect && scb) {
							scb = $("#wizg_" + $.wizgrid.wizID(ts.p.id) + "_" + ri).is(":checked");
							$("#wizg_" + $.wizgrid.wizID(ts.p.id) + "_" + ri)[ts.p.useProp ? 'prop' : 'attr']("checked", scb);
						}
					}
				}).bind('reloadGrid', function(e, opts) {
					if (ts.p.treeGrid === true) {
						ts.p.datatype = ts.p.treedatatype;
					}
					if (opts && opts.current) {
						ts.grid.selectionPreserver(ts);
					}
					if (ts.p.datatype === "local") {
						$(ts).wizGrid("resetSelection");
						if (ts.p.data.length) {
							normalizeData();
							refreshIndex();
						}
					}
					else if (!ts.p.treeGrid) {
						ts.p.selrow = null;
						if (ts.p.multiselect) {
							ts.p.selarrrow = [];
							setHeadCheckBox(false);
						}
						ts.p.savedRow = [];
					}
					if (ts.p.scroll) {
						emptyRows.call(ts, true, false);
					}
					if (opts && opts.page) {
						var page = opts.page;
						if (page > ts.p.lastpage) {
							page = ts.p.lastpage;
						}
						if (page < 1) {
							page = 1;
						}
						ts.p.page = page;
						if (ts.grid.prevRowHeight) {
							ts.grid.bDiv.scrollTop = (page - 1) * ts.grid.prevRowHeight * ts.p.rowNum;
						}
						else {
							ts.grid.bDiv.scrollTop = 0;
						}
					}
					if (ts.grid.prevRowHeight && ts.p.scroll) {
						delete ts.p.lastpage;
						ts.grid.populateVisible();
					}
					else {
						ts.grid.populate();
					}
					if (ts.p.inlineNav === true) {
						$(ts).wizGrid('showAddEditButtons');
					}
					return false;
				}).dblclick(function(e) {
					td = e.target;
					ptr = $(td, ts.rows).closest("tr.wizgrow");
					if ($(ptr).length === 0) {
						return;
					}
					ri = ptr[0].rowIndex;
					ci = $.wizgrid.getCellIndex(td);
					var dbcr = $(ts).triggerHandler("wizGridDblClickRow", [
						$(ptr).attr("id")
						, ri, ci
						, e
					]);
					if (dbcr != null) {
						return dbcr;
					}
					if ($.isFunction(ts.p.OndblClickRow)) {
						dbcr = ts.p.OndblClickRow.call(ts, $(ptr).attr("id"), ri, ci, e);
						if (dbcr != null) {
							return dbcr;
						}
					}
				}).bind('contextmenu', function(e) {
					td = e.target;
					ptr = $(td, ts.rows).closest("tr.wizgrow");
					if ($(ptr).length === 0) {
						return;
					}
					if (!ts.p.multiselect) {
						$(ts).wizGrid("setSelection", ptr[0].id, true, e);
					}
					ri = ptr[0].rowIndex;
					ci = $.wizgrid.getCellIndex(td);
					var rcr = $(ts).triggerHandler("wizGridRightClickRow", [$(ptr).attr("id")
						, ri, ci, e
					]);
					if (rcr != null) {
						return rcr;
					}
					if ($.isFunction(ts.p.onRightClickRow)) {
						rcr = ts.p.onRightClickRow.call(ts, $(ptr).attr("id"), ri, ci, e);
						if (rcr != null) {
							return rcr;
						}
					}
				});
				grid.bDiv = document.createElement("div");
				if (isMSIE) {
					if (String(ts.p.height).toLowerCase() === "auto") {
						ts.p.height = "100%";
					}
				}
				$(grid.bDiv).append($('<div id="NoData_' + ts.p.id + '" style="text-align:center;" > </div>'))
					// 박준용 조회 데이타 업을때 글자 표시 위해 추가함
					.append($('<div style="position:relative;"></div>').append('<div></div>').append(this)).addClass(
						"ui-wizgrid-bdiv").css({
						height: ts.p.height + (isNaN(ts.p.height) ? "" : "px")
						, width: (grid.width) + "px"
					}).scroll(grid.scrollGrid);
				$("table:first", grid.bDiv).css({
					width: ts.p.tblwidth + "px"
				});
				if (!$.support.tbody) { // IE
					if ($("tbody", this).length === 2) {
						$("tbody:gt(0)", this).remove();
					}
				}
				if (ts.p.multikey) {
					if ($.wizgrid.msie) {
						$(grid.bDiv).bind("selectstart", function() {
							return false;
						});
					}
					else {
						$(grid.bDiv).bind("mousedown", function() {
							return false;
						});
					}
				}
				if (hg) {
					$(grid.bDiv).hide();
				}
				grid.cDiv = document.createElement("div");
				var arf = ts.p.hidegrid === true ? $(
					"<a role='link' class='ui-wizgrid-titlebar-close ui-corner-all-grid HeaderButton' title='" + ($.wizgrid
						.getRegional(ts, "defaults.showhide", ts.p.showhide) || "") + "'" + " />").hover(function() {
					arf.addClass('ui-state-hover-grid');
				}, function() {
					arf.removeClass('ui-state-hover-grid');
				}).append("<span class='ui-icon ui-icon-circle-triangle-n'></span>").css((dir === "rtl" ? "left" :
					"right"), "0px") : "";
				$(grid.cDiv).append(arf).append("<span class='ui-wizgrid-title'>" + ts.p.caption + "</span>").addClass(
					"ui-wizgrid-titlebar ui-wizgrid-caption" + (dir === "rtl" ? "-rtl" : "") +
					" ui-widget-header-grid ui-corner-top ui-helper-clearfix-grid");
				$(grid.cDiv).insertBefore(grid.hDiv);
				if (ts.p.toolbar[0]) {
					grid.uDiv = document.createElement("div");
					if (ts.p.toolbar[1] === "top") {
						$(grid.uDiv).insertBefore(grid.hDiv);
					}
					else if (ts.p.toolbar[1] === "bottom") {
						$(grid.uDiv).insertAfter(grid.hDiv);
					}
					if (ts.p.toolbar[1] === "both") {
						grid.ubDiv = document.createElement("div");
						$(grid.uDiv).addClass("ui-userdata ui-state-default-grid").attr("id", "t_" + this.id).insertBefore(
							grid.hDiv);
						$(grid.ubDiv).addClass("ui-userdata ui-state-default-grid").attr("id", "tb_" + this.id).insertAfter(
							grid.hDiv);
						if (hg) {
							$(grid.ubDiv).hide();
						}
					}
					else {
						$(grid.uDiv).width(grid.width).addClass("ui-userdata ui-state-default-grid").attr("id", "t_" + this
							.id);
					}
					if (hg) {
						$(grid.uDiv).hide();
					}
				}
				if (ts.p.toppager) {
					ts.p.toppager = $.wizgrid.wizID(ts.p.id) + "_toppager";
					grid.topDiv = $("<div id='" + ts.p.toppager + "'></div>")[0];
					ts.p.toppager = "#" + ts.p.toppager;
					$(grid.topDiv).addClass('ui-state-default-grid ui-wizgrid-toppager').width(grid.width).insertBefore(
						grid.hDiv);
					setPager(ts.p.toppager, '_t');
				}
				if (ts.p.footerrow) {
					grid.sDiv = $("<div class='ui-wizgrid-sdiv'></div>")[0];
					hb = $("<div class='ui-wizgrid-hbox" + (dir === "rtl" ? "-rtl" : "") + "'></div>");
					$(grid.sDiv).append(hb).width(grid.width).insertAfter(grid.hDiv);
					$(hb).append(tfoot);
					grid.footers = $(".ui-wizgrid-ftable", grid.sDiv)[0].rows[0].cells;
					if (ts.p.rownumbers) {
						grid.footers[0].className = 'ui-state-default-grid wizgrid-rownum';
					}
					if (hg) {
						$(grid.sDiv).hide();
					}
				}
				hb = null;
				if (ts.p.caption) {
					var tdt = ts.p.datatype;
					if (ts.p.hidegrid === true) {
						$(".ui-wizgrid-titlebar-close", grid.cDiv).click(function(e) {
							var onHdCl = $.isFunction(ts.p.OnCaptionClick)
								, elems = ".ui-wizgrid-bdiv, .ui-wizgrid-hdiv, .ui-wizgrid-pager, .ui-wizgrid-sdiv"
								, counter, self = this;
							if (ts.p.toolbar[0] === true) {
								if (ts.p.toolbar[1] === 'both') {
									elems += ', #' + $(grid.ubDiv).attr('id');
								}
								elems += ', #' + $(grid.uDiv).attr('id');
							}
							counter = $(elems, "#gview_" + $.wizgrid.wizID(ts.p.id)).length;
							if (ts.p.gridstate === 'visible') {
								$(elems, "#gbox_" + $.wizgrid.wizID(ts.p.id)).slideUp("fast", function() {
									counter--;
									if (counter === 0) {
										$("span", self).removeClass("ui-icon-circle-triangle-n").addClass(
											"ui-icon-circle-triangle-s");
										ts.p.gridstate = 'hidden';
										if ($("#gbox_" + $.wizgrid.wizID(ts.p.id)).hasClass("ui-resizable")) {
											$(".ui-resizable-handle", "#gbox_" + $.wizgrid.wizID(ts.p.id)).hide();
										}
										$(ts).triggerHandler("wizGridHeaderClick", [
											ts.p.gridstate
											, e
										]);
										if (onHdCl) {
											if (!hg) {
												ts.p.OnCaptionClick.call(ts, ts.p.gridstate, e);
											}
										}
									}
								});
							}
							else if (ts.p.gridstate === 'hidden') {
								$(elems, "#gbox_" + $.wizgrid.wizID(ts.p.id)).slideDown("fast", function() {
									counter--;
									if (counter === 0) {
										$("span", self).removeClass("ui-icon-circle-triangle-s").addClass(
											"ui-icon-circle-triangle-n");
										if (hg) {
											ts.p.datatype = tdt;
											populate();
											hg = false;
										}
										ts.p.gridstate = 'visible';
										if ($("#gbox_" + $.wizgrid.wizID(ts.p.id)).hasClass("ui-resizable")) {
											$(".ui-resizable-handle", "#gbox_" + $.wizgrid.wizID(ts.p.id)).show();
										}
										$(ts).triggerHandler("wizGridHeaderClick", [
											ts.p.gridstate
											, e
										]);
										if (onHdCl) {
											if (!hg) {
												ts.p.OnCaptionClick.call(ts, ts.p.gridstate, e);
											}
										}
									}
								});
							}
							return false;
						});
						if (hg) {
							ts.p.datatype = "local";
							$(".ui-wizgrid-titlebar-close", grid.cDiv).trigger("click");
						}
					}
				}
				else {
					$(grid.cDiv).hide();
					if (!ts.p.toppager) {
						$(grid.hDiv).addClass('ui-corner-top');
					}
				}
				$(grid.hDiv).after(grid.bDiv).mousemove(function(e) {
					if (grid.resizing) {
						grid.dragMove(e);
						return false;
					}
				});
				$(".ui-wizgrid-labels", grid.hDiv).bind("selectstart", function() {
					return false;
				});
				$(document).bind("mouseup.wizGrid" + ts.p.id, function() {
					if (grid.resizing) {
						grid.dragEnd();
						return false;
					}
					return true;
				});
				ts.formatCol = formatCol;
				ts.sortData = sortData;
				ts.updatepager = updatepager;
				ts.refreshIndex = refreshIndex;
				ts.setHeadCheckBox = setHeadCheckBox;
				ts.constructTr = constructTr;
				ts.formatter = function(rowId, cellval, colpos, rwdat, act) {
					return formatter(rowId, cellval, colpos, rwdat, act);
				};
				$.extend(grid, {
					populate: populate
					, emptyRows: emptyRows
					, beginReq: beginReq
					, endReq: endReq
				});
				this.grid = grid;
				ts.addXmlData = function(d) {
					addXmlData(d);
				};
				ts.addJSONData = function(d) {
					addJSONData(d);
				};
				this.grid.cols = this.rows[0].cells;
				$(ts).triggerHandler("wizGridInitGrid");
				if ($.isFunction(ts.p.OnInitGrid)) {
					ts.p.OnInitGrid.call(ts);
				}
				populate();
				ts.p.hiddengrid = false;
			});
		};
		$.wizgrid.extend({
			/// 글로벌 변수 테스트 
			getGridParam: function(name, module) {
				var $t = this[0]
					, ret;
				if (!$t || !$t.grid) {
					return;
				}
				if (module === undefined && typeof module !== 'string') {
					module = 'wizGrid'; // $t.p
				}
				ret = $t.p;
				if (module !== 'wizGrid') {
					try {
						ret = $($t).data(module);
					}
					catch (e) {
						ret = $t.p;
					}
				}
				if (!name) {
					return ret;
				}
				return ret[name] !== undefined ? ret[name] : null;
			}
			, setGridParam: function(newParams, overwrite) {
				return this.each(function() {
					if (overwrite == null) {
						overwrite = false;
					}
				
					if (this.grid && typeof newParams === 'object') {
						if (overwrite === true) {
							var params = $.extend({}, this.p, newParams);
							this.p = params;
						}
						else {
							$.extend(true, this.p, newParams);
						}
					}
				});
			}
			, getGridRowById: function(rowid) {
				var row;
				this.each(function() {
					try {
						// row = this.rows.namedItem( rowid
						// );
						var i = this.rows.length;
						while (i--) {
							if (rowid.toString() === this.rows[i].id) {
								row = this.rows[i];
								break;
							}
						}
					}
					catch (e) {
						row = $(this.grid.bDiv).find("#" + $.wizgrid.wizID(rowid));
					}
				});
				return row;
			}
			, getDataIDs: function() {
				var ids = []
					, i = 0
					, len, j = 0;
				this.each(function() {
					len = this.rows.length;
					if (len && len > 0) {
						while (i < len) {
							if ($(this.rows[i]).hasClass('wizgrow')) {
								ids[j] = this.rows[i].id;
								j++;
							}
							i++;
						}
					}
				});
				return ids;
			}
			, setSelection: function(selection, onsr, e) {
				return this.each(function() {
					var $t = this
						, stat, pt, ner, ia, tpsr, fid, csr;
					if (selection === undefined) {
						return;
					}
					onsr = onsr === false ? false : true;
					pt = $($t).wizGrid('getGridRowById', selection);
					if (!pt || !pt.className || pt.className.indexOf('ui-state-disabled') > -1) {
						return;
					}

					function scrGrid(iR) {
						var ch = $($t.grid.bDiv)[0].clientHeight
							, st = $($t.grid.bDiv)[0].scrollTop
							, rpos = $($t.rows[iR]).position().top
							, rh = $t.rows[iR].clientHeight;
						if (rpos + rh >= ch + st) {
							$($t.grid.bDiv)[0].scrollTop = rpos - (ch + st) + rh + st;
						}
						else if (rpos < ch + st) {
							if (rpos < st) {
								$($t.grid.bDiv)[0].scrollTop = rpos;
							}
						}
					}
					if ($t.p.scrollrows === true) {
						ner = $($t).wizGrid('getGridRowById', selection).rowIndex;
						if (ner >= 0) {
							scrGrid(ner);
						}
					}
					if ($t.p.frozenColumns === true) {
						fid = $t.p.id + "_frozen";
					}
					if (!$t.p.multiselect) {
						if (pt.className !== "ui-subgrid") {
							if ($t.p.selrow !== pt.id) {
								csr = $($t).wizGrid('getGridRowById', $t.p.selrow);
								if (csr) {
									$(csr).removeClass("ui-state-highlight-grid").attr({
										"aria-selected": "false"
										, "tabindex": "-1"
									});
								}
								// comment o???? ???? ????
								// ??? ???? ???
								// $(pt).addClass("ui-state-highlight-grid").attr({"aria-selected":"true",
								// "tabindex" : "0"});
								// //.focus(); //---- kjs
								// ????o?? comment?? ??????.
								// ???÷? o?? ????
								if (fid) {
									$("#" + $.wizgrid.wizID($t.p.selrow), "#" + $.wizgrid.wizID(fid)).removeClass(
										"ui-state-highlight-grid");
									$("#" + $.wizgrid.wizID(selection), "#" + $.wizgrid.wizID(fid)).addClass(
										"ui-state-highlight-grid");
								}
								stat = true;
							}
							else {
								stat = false;
							}
							$t.p.selrow = pt.id;
							if (onsr) {
								$($t).triggerHandler("wizGridSelectRow", [pt.id, stat, e]);
								if ($t.p.OnSelectRow) {
									$t.p.OnSelectRow.call($t, pt.id, stat, e);
								}
							}
						}
					}
					else {
						// unselect selectall checkbox when
						// deselecting a specific row
						$t.setHeadCheckBox(false);
						$t.p.selrow = pt.id;
						ia = $.inArray($t.p.selrow, $t.p.selarrrow);
						if (ia === -1) {
							if (pt.className !== "ui-subgrid") {
								$(pt).addClass("ui-state-highlight-grid").attr("aria-selected", "true");
							}
							stat = true;
							$t.p.selarrrow.push($t.p.selrow);
						}
						else {
							if (pt.className !== "ui-subgrid") {
								$(pt).removeClass("ui-state-highlight-grid").attr("aria-selected", "false");
							}
							stat = false;
							$t.p.selarrrow.splice(ia, 1);
							tpsr = $t.p.selarrrow[0];
							$t.p.selrow = (tpsr === undefined) ? null : tpsr;
						}
						$("#wizg_" + $.wizgrid.wizID($t.p.id) + "_" + $.wizgrid.wizID(pt.id))[$t.p.useProp ? 'prop' :
							'attr']("checked", stat);
						if (fid) {
							if (ia === -1) {
								$("#" + $.wizgrid.wizID(selection), "#" + $.wizgrid.wizID(fid)).addClass(
									"ui-state-highlight-grid");
							}
							else {
								$("#" + $.wizgrid.wizID(selection), "#" + $.wizgrid.wizID(fid)).removeClass(
									"ui-state-highlight-grid");
							}
							$("#wizg_" + $.wizgrid.wizID($t.p.id) + "_" + $.wizgrid.wizID(selection), "#" + $.wizgrid.wizID(
								fid))[$t.p.useProp ? 'prop' : 'attr']("checked", stat);
						}
						if (onsr) {
							$($t).triggerHandler("wizGridSelectRow", [pt.id, stat, e]);
							// pjy start 삭제 플레그 처리
							var newValue = 'D';
							var reValue = 'R';
							var ColumnsId = $($t).wizGrid('getGridParam', 'Columns');
							var i;
							var strAt = $($t).wizGrid('getCell', pt.id, "IUDFLAG");
							// alert($t.p.id + '@@@@'+
							// pt.id);
							if ("I" == strAt && strAt !== false) {
								// $($t).wizGrid('setCell',
								// pt.id, 'IUDFLAG', "H");
								// $("#"+pt.id).hide();
								// return;
								$($t).delRowData(pt.id); //
								return;
							}
							if (ColumnsId[0]['name'] == 'rn' && ColumnsId[1]['name'] == 'cb') {
								i = 2;
							}
							else if (ColumnsId[0]['name'] == 'rn' || ColumnsId[1]['name'] == 'cb') {
								i = 1;
							}
							else {
								i = 0;
							}
							if ($('#wizg_' + $t.p.id + '_' + pt.id).prop('checked')) { // row
								// ????
								// edit
								// 삭제
								$($t).wizGrid('setCell', pt.id, 'IUDFLAG', newValue);
								for (i; i < ColumnsId.length; i++) {
									$($t).wizGrid('setCell', pt.id, ColumnsId[i]['name'], '', 'not-editable-cell');
									// 체크박스 수정 못하게 디제이블
									$('#checkbox_' + pt.id + '_' + ColumnsId[i]['name']).attr('disabled', 'disabled');
								}
							}
							else {
								$($t).wizGrid('setCell', pt.id, 'IUDFLAG', reValue);
								for (i; i < ColumnsId.length; i++) {
									$("#" + $t.p.id + " tr[id='" + pt.id + "'] td[aria-describedby=" + $t.p.id + "_" + ColumnsId[i]
										['name'] + "]").removeClass('not-editable-cell');
									// 체크박스 수정할수 있게 디제이블 해제
									$('#checkbox_' + pt.id + '_' + ColumnsId[i]['name']).removeAttr('disabled');
								}
							}
							// end
							if ($t.p.OnSelectRow) {
								$t.p.OnSelectRow.call($t, pt.id, stat, e);
							}
						}
					}
				});
			}
			, resetSelection: function(rowid, binding) // binding(parmeter?κи?)
				// :kjs ?κ?
				// ????????
				{
					return this.each(function() {
						var t = this
							, sr, fid;
						if (t.p.frozenColumns === true) {
							fid = t.p.id + "_frozen";
						}
						if (rowid !== undefined) {
							sr = rowid === t.p.selrow ? t.p.selrow : rowid;
							$("#" + $.wizgrid.wizID(t.p.id) + " tbody:first tr#" + $.wizgrid.wizID(sr)).removeClass(
								"ui-state-highlight-grid").attr("aria-selected", "false");
							if (fid) {
								$("#" + $.wizgrid.wizID(sr), "#" + $.wizgrid.wizID(fid)).removeClass("ui-state-highlight-grid");
							}
							if (t.p.multiselect) {
								$("#wizg_" + $.wizgrid.wizID(t.p.id) + "_" + $.wizgrid.wizID(sr), "#" + $.wizgrid.wizID(t.p.id))[
									t.p.useProp ? 'prop' : 'attr']("checked", false);
								if (fid) {
									$("#wizg_" + $.wizgrid.wizID(t.p.id) + "_" + $.wizgrid.wizID(sr), "#" + $.wizgrid.wizID(fid))[t
										.p.useProp ? 'prop' : 'attr']("checked", false);
								}
								t.setHeadCheckBox(false);
								var ia = $.inArray($.wizgrid.wizID(sr), t.p.selarrrow);
								if (ia !== -1) {
									t.p.selarrrow.splice(ia, 1);
								}
							}
							sr = null;
						}
						else if (!t.p.multiselect) {
							if (t.p.selrow) {
								$("#" + $.wizgrid.wizID(t.p.id) + " tbody:first tr#" + $.wizgrid.wizID(t.p.selrow)).removeClass(
									"ui-state-highlight-grid").attr("aria-selected", "false");
								if (fid) {
									$("#" + $.wizgrid.wizID(t.p.selrow), "#" + $.wizgrid.wizID(fid)).removeClass(
										"ui-state-highlight-grid");
								}
								t.p.selrow = null;
							}
						}
						else {
							$(t.p.selarrrow).each(function(i, n) {
								$($(t).wizGrid('getGridRowById', n)).removeClass("ui-state-highlight-grid").attr(
									"aria-selected", "false");
								$("#wizg_" + $.wizgrid.wizID(t.p.id) + "_" + $.wizgrid.wizID(n))[t.p.useProp ? 'prop' : 'attr']
									("checked", false);
								if (fid) {
									$("#" + $.wizgrid.wizID(n), "#" + $.wizgrid.wizID(fid)).removeClass("ui-state-highlight-grid");
									$("#wizg_" + $.wizgrid.wizID(t.p.id) + "_" + $.wizgrid.wizID(n), "#" + $.wizgrid.wizID(fid))[
											t.p.useProp ? 'prop' : 'attr']
										("checked", false);
								}
							});
							t.setHeadCheckBox(false);
							t.p.selarrrow = [];
							t.p.selrow = null;
						}
						if (t.p.cellEdit === true && binding == null) // //binding
						// :kjs ???
						{
							if (parseInt(t.p.iCol, 10) >= 0 && parseInt(t.p.iRow, 10) >= 0) {
								$("td:eq(" + t.p.iCol + ")", t.rows[t.p.iRow]).removeClass("edit-cell ui-state-highlight-grid");
								$(t.rows[t.p.iRow]).removeClass("selected-row ui-state-hover-grid");
							}
						}
						t.p.savedRow = [];
					});
				}
			, getRowData: function(rowid, usedata) {
				var res = {}
					, resall, getall = false
					, len, j = 0;
				this.each(function() {
					var $t = this
						, nm, ind;
					if (rowid == null) {
						getall = true;
						resall = [];
						len = $t.rows.length;
					}
					else {
						ind = $($t).wizGrid('getGridRowById', rowid);
						if (!ind) {
							return res;
						}
						len = 2;
					}
					if (!(usedata && usedata === true && $t.p.data.length > 0)) {
						usedata = false;
					}
					while (j < len) {
						if (getall) {
							ind = $t.rows[j];
						}
						if ($(ind).hasClass('wizgrow')) {
							if (usedata) {
								res = $t.p.data[$t.p._index[ind.id]];
							}
							else {
								$('td[role="gridcell"]', ind).each(function(i) {
									nm = $t.p.Columns[i].name;
									if (nm !== 'cb' && nm !== 'subgrid' && nm !== 'rn') {
										if ($t.p.treeGrid === true && nm === $t.p.ExpandColumn) {
											res[nm] = $.wizgrid.htmlDecode($("span:first", this).html());
										}
										else {
											try {
												res[nm] = $.unformat.call($t, this, {
													rowId: ind.id
													, Columns: $t.p.Columns[i]
												}, i);
											}
											catch (e) {
												res[nm] = $.wizgrid.htmlDecode($(this).html());
											}
										}
									}
								});
							}
							if (getall) {
								resall.push(res);
								res = {};
							}
						}
						j++;
					}
				});
				return resall || res;
			}
			, delRowData: function(rowid) {
				var success = false
					, rowInd, ia, nextRow;
				this.each(function() {
					var $t = this;
					rowInd = $($t).wizGrid('getGridRowById', rowid);
					if (!rowInd) {
						return false;
					}
					if ($t.p.subGrid) {
						nextRow = $(rowInd).next();
						if (nextRow.hasClass('ui-subgrid')) {
							nextRow.remove();
						}
					}
					$(rowInd).remove();
					$t.p.records--;
					$t.p.reccount--;
					$t.updatepager(true, false);
					success = true;
					if ($t.p.multiselect) {
						ia = $.inArray(rowid, $t.p.selarrrow);
						if (ia !== -1) {
							$t.p.selarrrow.splice(ia, 1);
						}
					}
					if ($t.p.multiselect && $t.p.selarrrow.length > 0) {
						$t.p.selrow = $t.p.selarrrow[$t.p.selarrrow.length - 1];
					}
					else {
						$t.p.selrow = null;
					}
					if ($t.p.datatype === 'local') {
						var id = $.wizgrid.stripPref($t.p.idPrefix, rowid)
							, pos = $t.p._index[id];
						if (pos !== undefined) {
							$t.p.data.splice(pos, 1);
							$t.refreshIndex();
						}
					}
					if ($t.p.altRows === true && success) {
						var cn = $t.p.altclass;
						$($t.rows).each(function(i) {
							if (i % 2 === 10) {
								$(this).addClass(cn);
							}
							else {
								$(this).removeClass(cn);
							}
						});
					}
				});
				return success;
			}
			, setRowData: function(rowid, data, cssp) {
				var nm, success = true
					, title;
				this.each(function() {
					if (!this.grid) {
						return false;
					}
					var t = this
						, vl, ind, cp = typeof cssp
						, lcdata = {};
					ind = $(this).wizGrid('getGridRowById', rowid);
					if (!ind) {
						return false;
					}
					if (data) {
						try {
							$(this.p.Columns).each(function(i) {
								nm = this.name;
								var dval = $.wizgrid.getAccessor(data, nm);
								if (dval !== undefined) {
									lcdata[nm] = this.formatter && typeof this.formatter === 'string' && this.formatter ===
										'date' ? $.unformat.date.call(t, dval, this) : dval;
									vl = t.formatter(rowid, lcdata[nm], i, data, 'edit');
									title = this.title ? {
										"title": $.wizgrid.stripHtml(vl)
									} : {};
									if (t.p.treeGrid === true && nm === t.p.ExpandColumn) {
										$("td[role='gridcell']:eq(" + i + ") > span:first", ind).html(vl).attr(title);
									}
									else {
										$("td[role='gridcell']:eq(" + i + ")", ind).html(vl).attr(title);
									}
								}
							});
							if (t.p.datatype === 'local') {
								var id = $.wizgrid.stripPref(t.p.idPrefix, rowid)
									, pos = t.p._index[id]
									, key;
								if (t.p.treeGrid) {
									for (key in t.p.treeReader) {
										if (t.p.treeReader.hasOwnProperty(key)) {
											delete lcdata[t.p.treeReader[key]];
										}
									}
								}
								if (pos !== undefined) {
									t.p.data[pos] = $.extend(true, t.p.data[pos], lcdata);
								}
								lcdata = null;
							}
						}
						catch (e) {
							success = false;
						}
					}
					if (success) {
						if (cp === 'string') {
							$(ind).addClass(cssp);
						}
						else if (cssp !== null && cp === 'object') {
							$(ind).css(cssp);
						}
						$(t).triggerHandler("wizGridAfterGridComplete");
					}
				});
				return success;
			}
			, resetComboData: function(colnm, initcolumn) // //
				// grid
				// ??????
				// combo
				// data??
				// reseto??
				// ????.
				{
					var initdata = InitItems[initcolumn];
					this.setColProp(colnm, {
						editoptions: {
							value: initdata
						}
					});
				}
			, setDatasetName: function(dsname) // // kjs ??? method
				// :2015.07.02
				{
					//var datasetname = this.wizGrid("getGridParam", "datasetname") ;
					this.wizGrid("setGridParam", {
						datasetname: dsname
					}, true);
					//this.datasetname = dsname;
				}
			, setLocalGridCombo: function(Colnm, comboData) { // pjy
				// 그리드콤보
				// 데이타
				// 동적
				// 변환
				if (comboData == undefined) {
					alert("Grid ComboData doesn't exist.");
				}
				try {
					$(this).wizGrid('setColProp', Colnm, {
						editoptions: {
							value: comboData
						}
					});
				}
				catch (err) {
					alert("ComboData An error : " + err);
				}
			}
			, getCurrRowIndex: function() // ////// 명칭 수정 : 내부
				{
					var pos = -1;
					var selrow = this.wizGrid('getGridParam', 'selrow');
					var rowdata = this.wizGrid('getRowData', selrow);
					pos = this.getRowIdx(rowdata.id);
					return pos;
				}
			, getCurrentRowIndex: function() // // //// 명칭 수정 : 사용자용
				{
					var pos = -1;
					var selrow = this.wizGrid('getGridParam', 'selrow');
				//	var rowdata = this.wizGrid('getRowData', selrow);
						
					pos = this.getRowIdx(selrow);
					if(selrow == ""){
						 pos = -1;
					 }
					if (pos > 0) pos = pos - 1; // '0' index 보정 처리
					//alert(this.getRowIdx("R4")  +  " @@ " + pos);
					return pos;
				}
			, getObjectRowIndex: function(selrow) // // kjs ???
				// method
				// :2015.07.02
				{
					var pos = -1;
					var rowdata = this.wizGrid('getRowData', selrow);
					pos = this.getRowIdx(rowdata.id);
					if (pos > 0) pos = pos - 1; // //'0' index 보정 처리
					return pos;
				}
			, getRowId: function(rowindex) // // kjs:method
				// :2015.10.27 pjy 개발자용
				{
					var rowid = "";
					this.each(function() {
						if (this.rows.length > 0) {
							for (var i = 0; i < this.rows.length; i++) {
								if (i == rowindex) {
									rowid = this.rows[i].id;
									break;
								}
							}
						}
					});
					return rowid;
				}
			, getGridRowId: function(rowindex) // // kjs:method
				// :2015.10.27 사용자용
				{
					var rowid = "";
					this.each(function() {
						if (this.rows.length > 0) {
							for (var i = 0; i < this.rows.length; i++) {
								if (i == rowindex) {
									rowid = this.rows[i + 1].id;
									break;
								}
							}
						}
					});
					return rowid;
				}
			, getRowIdx: function(rowid) // // kjs //// 명칭 수정 : 내용용
				{
					var pos = -1;
					this.each(function() {
						if (this.rows.length > 0) {
							for (var i = 0; i < this.rows.length; i++) {
								if (this.rows[i].id == rowid) {
									pos = i;
									break;
								}
							}
						}
					});
					return pos;
				}
			, getRowIndex: function(rowid) // // 명칭 수정 : 사용자용
				{
					var pos = -1;
					this.each(function() {
						if (this.rows.length > 0) {
							for (var i = 0; i < this.rows.length; i++) {
								if (this.rows[i].id == rowid) {
									pos = i;
									break;
								}
							}
						}
					});
					if (pos > 0) pos = pos - 1; // '0' index 보정 처리
					return pos;
				}
			, getColumnObject: function(colindex) // // kjs ???
				// method
				// :2015.07.02
				{
					var colobj;
					this.each(function() {
						colobj = this.p.Columns[colindex];
					});
					return colobj;
				}
			, getColumnName: function(colindex) // // kjs ??? method
				// :2015.07.02
				{
					var colname = "";
					this.each(function() {
						colname = this.p.Columns[colindex].name;
					});
					return colname;
				}
			, getColumnIndex: function(colnm) // // kjs ??? method
				// :2015.07.02
				{
					var pos = -1;
					this.each(function() {
						for (var i = 0; i < this.p.Columns.length; i++) {
							if (this.p.Columns[i].name == colnm) {
								pos = i;
								break
							}
						}
					});
					return pos;
				}
			, getRowStatus: function(rowindex) // // kjs ??? single
				// field ???? bind
				// method
				// :2015.07.02
				{
					var status = "";
					var colpin = "IUDFLAG";
					if (rowindex == undefined || rowindex == null) {
						status = this.getCurrentCellValue(colpin);
					}
					else {
						status = this.getCellValue(rowindex, colpin);
					}
					return status;
				}
			, getCurrentRowId: function() // // kjs:method
				// :2015.10.27
				{
					var selrow = this.wizGrid('getGridParam', 'selrow');
					// var rowdata = this.wizGrid('getRowData', selrow);
					// var rowid = rowdata.id;
					if(selrow == null || selrow == "")selrow = -1;
					
					return selrow;
				}
			, getCellValue: function(rowpin, colpin, formatdelyn) // //
				// kjs:method
				// :2015.10.27
				{
					var val = "";
					var rtype = typeof rowpin;
					var ctype = typeof colpin;
					var rowid = "";
					var colname = "";
					var colindex = 0
						, rowindex = 0;
					var cobj;
					// alert("시작" + "rtype " + rtype + " ctype " + ctype
					// + " ## " + rowpin + " ## " + colpin);
					if (rtype == "number") {
						rowpin = rowpin + 1;
						rowindex = rowpin;
						if (ctype == "number") {
							colindex = colpin;
							colname = this.getColumnName(colpin);
							rowid = this.getRowId(rowpin);
						}
						else {
							colname = colpin;
							colindex = this.getColumnIndex(colname);
							rowid = this.getRowId(rowpin);
							// alert(colindex + " @@ " + rowid);
						}
					}
					else {
						rowindex = this.getRowIdx(rowid);
						if (ctype == "number") {
							colindex = colpin;
							colname = this.getColumnName(colpin);
							rowid = rowpin;
						}
						else {
							colname = colpin;
							colindex = this.getColumnIndex(colname);
							rowid = rowpin;
						}
					}
					if (rowid == "" || colname == "") return "";
					this.CellEditable(rowindex, colindex, false); // this.editCellClose();
					val = this.getCell(rowid, colname);
					// ------------------ format 적용 부 삭제
					if (formatdelyn != null && formatdelyn != undefined && formatdelyn == true) //
					{
						cobj = this.getColumnObject(colindex);
						if (cobj.GFormat != undefined) {
							val = wizutil.removeNumberFmt(val, cobj.GFormat);
						}
					}
					// ------------------ format 적용 부 삭제
					return val;
				}
			, getEditCellValue: function(rowpin, colpin, formatdelyn) // //
				// pjy
				// 그리드
				// 저장할때
				// 이벤트
				// 발생 할
				// 때
				// 셀값을
				// 가져올때
				// 이함수를
				// 사용해야함
				{
					var val = "";
					var rtype = typeof rowpin;
					var ctype = typeof colpin;
					var rowid = "";
					var colname = "";
					var colindex = 0
						, rowindex = 0;
					var cobj;
					// alert("시작" + "rtype " + rtype + " ctype " + ctype
					// + " ## " + rowpin + " ## " + colpin);
					if (rtype == "number") {
						rowpin = rowpin + 1;
						rowindex = rowpin;
						if (ctype == "number") {
							colindex = colpin;
							colname = this.getColumnName(colpin);
							rowid = this.getRowId(rowpin);
						}
						else {
							colname = colpin;
							colindex = this.getColumnIndex(colname);
							rowid = this.getRowId(rowpin);
						}
					}
					else {
						rowindex = this.getRowIdx(rowid);
						if (ctype == "number") {
							colindex = colpin;
							colname = this.getColumnName(colpin);
							rowid = rowpin;
						}
						else {
							colname = colpin;
							colindex = this.getColumnIndex(colname);
							rowid = rowpin;
						}
					}
					if (rowid == "" || colname == "") return "";
					// this.CellEditable(rowindex, colindex, false); //
					// this.editCellClose();
					val = this.getCell(rowid, colname);
					// ------------------ format 적용 부 삭제
					if (formatdelyn != null && formatdelyn != undefined && formatdelyn == true) //
					{
						cobj = this.getColumnObject(colindex);
						if (cobj.GFormat != undefined) {
							val = wizutil.removeNumberFmt(val, cobj.GFormat);
						}
					}
					// ------------------ format 적용 부 삭제
					return val;
				}
			, CellEditable: function(rowindex, colindex, value) // //
				// kjs:method
				// :2015.10.27
				{
					this.wizGrid("editCell", rowindex, colindex, value);
				}
			, setEditCellValue: function(rowpin, colpin, value, formatdelyn) // // kjs:method :2015.10.27
				{
					var rtype = typeof rowpin;
					var ctype = typeof colpin;
					var rowid = "";
					var colname = "";
					var colindex = 0
						, rowindex = 0;
					var currRowindex = this.getCurrRowIndex();
					if (rtype == "number") {
						rowpin = rowpin + 1;
						rowindex = rowpin;
						if (ctype == "number") {
							colindex = colpin;
							colname = this.getColumnName(colpin);
							rowid = this.getRowId(rowpin);
						}
						else {
							colindex = this.getColumnIndex(colpin);
							colname = colpin;
							rowid = this.getRowId(rowpin);
						}
					}
					else {
						rowindex = this.getRowIdx(rowid);
						if (ctype == "number") {
							colindex = colpin;
							colname = this.getColumnName(colpin);
							rowid = rowpin;
						}
						else {
							colindex = this.getColumnIndex(colpin);
							colname = colpin;
							rowid = rowpin;
						}
					}
					if (rowid == "" || colname == "") return;
					// ------------------ format 적용 부 추가
					if (formatdelyn != null && formatdelyn != undefined && formatdelyn == true) //
					{
						var cobj = this.getColumnObject(colindex);
						if (cobj.GFormat != undefined) {
							var roundyn = true;
							if (cobj.GRound != undefined && cobj.GRound == "false") {
								roundyn = false;
							}
							value = wizutil.getFormatNumber(value, cobj.GFormat, roundyn);
						}
					}
					// ------------------ format 적용 부 추가 end
					// this.editRowClose(rowindex); // aftersave 이벤트 발생시
					// 오류 원인 주석처리함
					this.setCellVal(rowid, colname, value);
					if (this.getCellValue(rowid, "IUDFLAG") == "I") {
						this.setCellVal(rowid, "IUDFLAG", "I");
					}
					else {
						this.setCellVal(rowid, "IUDFLAG", "U");
					}
					if (currRowindex != -1) this.editRowClose(currRowindex);
				}
			, setCellValue: function(rowpin, colpin, value, formatdelyn) // // kjs:method :2015.10.27
				{
					var rtype = typeof rowpin;
					var ctype = typeof colpin;
					var rowid = "";
					var colname = "";
					var colindex = 0
						, rowindex = 0;
					var currRowindex = this.getCurrRowIndex();
					if (rtype == "number") {
						rowpin = rowpin + 1; // rowindex가 0부터 시작하도록
						// 보정 처리함.
						rowindex = rowpin;
						if (ctype == "number") {
							colindex = colpin;
							colname = this.getColumnName(colpin);
							rowid = this.getRowId(rowpin);
						}
						else {
							colindex = this.getColumnIndex(colpin);
							colname = colpin;
							rowid = this.getRowId(rowpin);
						}
					}
					else {
						rowindex = this.getRowIdx(rowid);
						if (ctype == "number") {
							colindex = colpin;
							colname = this.getColumnName(colpin);
							rowid = rowpin;
						}
						else {
							colindex = this.getColumnIndex(colpin);
							colname = colpin;
							rowid = rowpin;
						}
					}
					if (rowid == "" || colname == "") return;
					// ------------------ format 적용 부 추가
					if (formatdelyn != null && formatdelyn != undefined && formatdelyn == true) //
					{
						var cobj = this.getColumnObject(colindex);
						if (cobj.GFormat != undefined) {
							var roundyn = true;
							if (cobj.GRound != undefined && cobj.GRound == "false") {
								roundyn = false;
							}
							value = wizutil.getFormatNumber(value, cobj.GFormat, roundyn);
						}
					}
					// ------------------ format 적용 부 추가 end
					// this.editRowClose(rowindex);
					var getFlag = this.getCellValue(rowid, "IUDFLAG");
					if (getFlag == "I") {
						this.setCellVal(rowid, colname, value);
						this.setCellVal(rowid, "IUDFLAG", "I");
					}
					else if (getFlag == "D") {
						this.setCell(rowid, colname, value, 'not-editable-cell');
					}
					else {
						
						this.setCellVal(rowid, colname, value);
						this.setCellVal(rowid, "IUDFLAG", "U");
					}
					// if(currRowindex != -1)
					// this.editRowClose(currRowindex);
				}
			, hideColumn: function(colList) { // pjy 칼럼 숨기기
				this.wizGrid("hideCol", colList);
			}
			, showColumn: function(colList) { // pjy 칼럼 보이기
				this.wizGrid("showCol", colList);
			}
			, setCellVal: function(rowid, colname, val) // // kjs
				// ???
				// single
				// field
				// ???? bind
				// method
				// :2015.07.02
				{
					this.resetSelection(rowid, true);
					this.setCell(rowid, colname, val);
				}
			, getCurrentCellValue: function(colpin, formatdelyn) // //
				// kjs:method
				// :2015.10.27
				// getCell
				// :
				// function(rowid,col)
				// {
				{
					var val = "";
					var ctype = typeof colpin;
					var rowid = this.getCurrentRowId();
					var colname = "";
					var colindex = 0;
					var rowindex = 0;
					if (ctype == "number") {
						colname = this.getColumnName(colpin);
						colindex = colpin;
					}
					else {
						colname = colpin;
						colindex = this.getColumnIndex(colpin);
					}
					if (rowid == "" || colname == "") return;
					
					
					rowindex = this.getRowIdx(rowid);
				
					if(rowindex == -1){
						return -1;
						
					}
					
					this.CellEditable(rowindex, colindex, false);
					val = this.getCell(rowid, colname);
					// ------------------ format 적용 부 삭제
					if (formatdelyn != null && formatdelyn != undefined && formatdelyn == true) //
					{
						var cobj = this.getColumnObject(colindex);
						if (cobj.GFormat != undefined) {
							val = wizutil.removeNumberFmt(val, cobj.GFormat);
						}
					}
					// ------------------ format 적용 부 삭제
					return val;
				}
			, setCurrentCellValue: function(colpin, value, formatdelyn) // // kjs:method :2015.10.27
				{
					var ctype = typeof colpin;
					var colname = "";
					var colindex = 0;
					if (ctype == "number") {
						colname = this.getColumnName(colpin);
						colindex = colpin;
					}
					else {
						colname = colpin;
						colindex = this.getColumnIndex(colpin);
					}
					// ------------------ format 적용 부 추가
					if (formatdelyn != null && formatdelyn != undefined && formatdelyn == true) //
					{
						var cobj = this.getColumnObject(colindex);
						if (cobj.GFormat != undefined) {
							var roundyn = true;
							if (cobj.GRound != undefined && cobj.GRound == "false") {
								roundyn = false;
							}
							value = wizutil.getFormatNumber(value, cobj.GFormat, roundyn);
						}
					}
					// ------------------ format 적용 부 추가 end
					var selrow = this.wizGrid('getGridParam', 'selrow');
					
					if(selrow == null ){
							alert("not select row");
							return;
					}
					this.setCurrentCell(colpin, value);
					this.setCurrentCell("IUDFLAG", "U");
				}
			, setColumnValue: function(singlecolumn, valuex) // //pjya
				// kjs
				// ???
				// method
				// :2015.07.02
				// //checkbox???
				// hidden
				// ???:
				// name
				// (Dataset
				// +"_"
				// +
				// ColumnID)
				{
					var checkobj;
					// alert($("#"+singlecolumn)[0].type
					// +"@"+$("#"+singlecolumn)[0].tagName);
					switch ($("#" + singlecolumn)[0].tagName + $("#" + singlecolumn)[0].type) {
						case 'INPUTcheckbox':
							checkobj = InitItems[singlecolumn];
							if (checkobj.TrueValue == valuex) {
								$("#" + singlecolumn)[0].checked = true;
								$("#" + checkobj.Column).val(checkobj.TrueValue);
							}
							else {
								$("#" + singlecolumn)[0].checked = false;
								$("#" + checkobj.Column).val(checkobj.FalseValue);
							}
							// alert(checkobj +":" +
							// $("#"+checkobj.Column).val() );
							break;
						case 'INPUTradio':
							var radioValue = $("#" + singlecolumn).val();
							// $("#"+singlecolumn)[0].checked = false;
							// alert($("#"+singlecolumn).val());
							// alert($("#"+singlecolumn).val()
							// +"@@@@@@@@@@@@@@@@"+ valuex);
							if (radioValue == valuex) {
								// alert(radioValue + "@@@" + valuex+"@@@"
								// +singlecolumn);
								// alert(valuex.indexOf($("#"+singlecolumn).val()));
								$("#" + singlecolumn).prop('checked', 'true');
							}
							// alert(checkobj +":" +
							// $("#"+checkobj.Column).val() );
							break;
						default:
							// alert(singlecolumn +"@@@" + valuex);
							$("#" + singlecolumn).val(valuex);
							break;
					}
				}
			, MultiselectCheck: function(rowid, e) // // pjy 각셀을
				// 선택하기위함
				// :2015.07.02
				{
					var $self = $(this)
						, iCol, cm, $td = $(e.target).closest("tr.wizgrow>td")
						, $tr = $td.closest("tr.wizgrow")
						, p = $self.wizGrid("getGridParam");
					if ($(e.target).is("input[type=checkbox]") && $td.length > 0) {
						iCol = $.wizgrid.getCellIndex($td[0]);
						cm = p.Columns[iCol];
						if (cm != null && cm.name === "cb") {
							$self.wizGrid("setSelection", $tr.attr("id"), true, e);
						}
					}
					return false;
				}
			, setCurrentCell: function(gridcolumn, val) // // kjs
				// ???
				// single
				// field
				// ???? bind
				// method
				// :2015.07.02
				{
			
					var selrow = this.wizGrid('getGridParam', 'selrow');
					
				
					var rowindex = this.getCurrRowIndex();
					var colindex = this.getColumnIndex(gridcolumn);
					this.editRowClose(rowindex);
					this.resetSelection(selrow, true);
					this.setCell(selrow, gridcolumn, val);
				}
			, setCurrentBindCell: function(gridcolumn, objx) // //
				// kjs
				// ???
				// single
				// field
				// ????
				// bind
				// method
				// :2015.07.02
				{
					var selrow = this.wizGrid('getGridParam', 'selrow');
					if (selrow == null) return;
					var rowdata = this.wizGrid('getRowData', selrow);
					var rowindex = this.getCurrRowIndex();
					var colindex = this.getColumnIndex(gridcolumn);
					this.resetSelection(selrow, true);
					var valuex = objx.value;
					var taginfo = objx.tagName + objx.type;
					if (taginfo == "INPUTcheckbox") {
						var checkobj = InitItems[objx.id];
						if (objx.checked) {
							valuex = checkobj.TrueValue;
						}
						else {
							valuex = checkobj.FalseValue;
						}
						$("#" + checkobj.Column).val(valuex);
					}
					// alert("setCurrentBindCell:2:" + selrow +":" +
					// gridcolumn + ":" + objx.value +":" + valuex +":"
					// + rowdata.SAWONNO);
					this.setCell(selrow, gridcolumn, valuex);
					// pjy start 바인딩 수정 시
					var iROWId = "R" + rowindex
					var strAt = this.wizGrid('getCell', iROWId, "IUDFLAG");
					if ("I" != strAt && strAt !== false) {
					
						this.wizGrid('setCell', iROWId, "IUDFLAG", "U");
					}
					// end
					this.each(function() {
						this.p.OnEditCell.call(this, selrow, gridcolumn, valuex, colindex, rowindex);
					});
					this.closeRow(rowindex);
					// this.resetSelection(rowid, true);
					// this.wizGrid("editCell", rowindex-1, colindex,
					// false);
					// this.setCell(rowid, gridcolumn, objx.value,
					// false, false, true);
					// var rowindex = this.getCurrRowIndex();
					// var colindex = this.getColumnIndex(gridcolumn);
					// this.nextCell(rowindex, colindex);
					// --------- test parttern #1 ????????
					// var rowindex = this.getCurrRowIndex();
					// var colindex = this.getColumnIndex(gridcolumn);
					// var newvalue = objx.value;
					// this.wizGrid("editCell",rowindex,colindex,true);
					//
					// var selrow = this.wizGrid('getGridParam',
					// 'selrow');
					// var rowdata = this.wizGrid('getRowData', selrow);
					// var rowid = rowdata.id;
					// objx.value = newvalue;
					// this.setCell(rowid, gridcolumn, newvalue); ///
					// kjs ??? (*** ??????? : input type?? ??
					// o??(checkbox, radio, combo)
					// ------------------------------------------------------------------------------------------------------------------------------
					// -------comment o?? ???
					// alert("::setCurrentBindCell::" + rowindex +":" +
					// colindex +":" + rowid +":" + gridcolumn +":" +
					// this.getCell(selrow,gridcolumn));
					// this.each(function()
					// {
					// this.p.OnEditCell.call(this, rowdata ,
					// gridcolumn, objx.value, rowindex, colindex);
					// });
				}
			, checkConstraint: function(namex, rowid, value) // //
				// kjs
				// ???
				// after
				// edit
				// o??
				// ??
				// binding
				{
					var colname = "";
					var colSaveName = this.wizGrid('getGridParam', 'Columns');
					for (var ic = 0; ic < colSaveName.length; ic++) {
						colname = colSaveName[ic]['name'];
						if (colname == namex) {
							if (colSaveName[ic].constraint == null) return true;
							var rowdata = this.wizGrid('getRowData', rowid);
							var rtn = colSaveName[ic].constraint.call("", value, rowdata);
							if (rtn == false) return false;
						}
					}
					return true;
				}
			, setBindRowObject: function(selrow) // // kjs ??? after
				// edit o?? ??
				// binding
				{
					var singlecolumn;
					var colname;
					var cellvalue;
					var find = false;
					var bindx = this.wizGrid("getGridParam", "Abindkey");
					var bindkeyx = this.wizGrid("getGridParam", "Bbindkeyx");
					if (bindkeyx.length == null) {
						return;
					}
					for (var i = 0; i < bindkeyx.length; i++) {
						colname = bindx[bindkeyx[i]];
						if (colname == null) cellvalue = "";
						else cellvalue = this.getCell(selrow, colname);
						// alert("setBindRowObject:"+bindkeyx[i] +":" +
						// cellvalue +":" + colname +":" +
						// selrow[colname] );
						this.setColumnValue(bindkeyx[i], cellvalue);
					}
				}
			, setBindRowIdObject: function(rowid) // // kjs ???
				// after edit
				// o?? ??
				// binding
				{
					var selrow = this.getDataRow(rowid);
					this.setBindArrowKeyRowObject(selrow);
				}
			, setBindArrowKeyRowObject: function(selrow) // // kjs
				// ???
				// method
				// :2015.07.02
				{
					var singlecolumn;
					var colname;
					var cellvalue;
					var find = false;
					var bindx = this.wizGrid("getGridParam", "Abindkey");
					var bindkeyx = this.wizGrid("getGridParam", "Bbindkeyx");
					if (bindkeyx.length == null) {
						return;
					}
					for (var i = 0; i < bindkeyx.length; i++) {
						colname = bindx[bindkeyx[i]];
						cellvalue = selrow[colname];
						// alert("setBindRowObject:"+bindkeyx[i] +":" +
						// cellvalue +":" + colname +":" +
						// selrow[colname] );
						this.setColumnValue(bindkeyx[i], cellvalue);
					}
				}
			, setBindArrowKeyRow: function(rindex) // // kjs ???
				// method
				// :2015.07.02 :
				// keydown,
				// keyup?? ????
				// o?? ???.
				{
					var bindxrowindex = this.wizGrid("getGridParam", "bindxrowindex");
					if (bindxrowindex == rindex) // 같은 row에 cell 선택시 리턴 시킴 
						return;
					this.wizGrid("setGridParam", {
						bindxrowindex: rindex
					}, true); // 선택된 rowindex 값 셋팅
					var row = this.getGridRow(rindex);
					var rowid = row.id;
					// var selrow = this.getDataRow(rowid);
					var selrow = this.wizGrid('getRowData', rowid);
					// ------------------ on enter ??????? -----
					this.each(function() {
						this.p.OnEnterRow.call("", rindex, selrow);
					});
					// -----------------------------------------
					if (selrow == null) {
						return;
					}
					// alert(rindex +":" + row +":" + rowid + ":" +
					// selrow);
					this.setBindArrowKeyRowObject(selrow);
					return selrow;
				}
			, getBindColumn: function(singlecolumn) // // kjs ???
				// method
				// :2015.07.02
				{
					// alert("getBindColumn[" + bindkeyx +"]:" +
					// bindx[singlecolumn] +":" + singlecolumn);
					var bindx = this.wizGrid("getGridParam", "Abindkey");
					return bindx[singlecolumn];
				}
			, addBind: function(singlecolumn, gridcolumn) // //
				// kjs
				// ???
				// method
				// :2015.07.02
				{
					//alert(singlecolumn);
					/*this.wizGrid("getGridParam", "Bbindkeyx") 
					this.bindx[singlecolumn] = gridcolumn; {}
					this.bindkeyx.push(singlecolumn);   []*/
					var bindx = this.wizGrid("getGridParam", "Abindkey"); // {}
					var bindkeyx = this.wizGrid("getGridParam", "Bbindkeyx"); // []
					bindx[singlecolumn] = gridcolumn;
					bindkeyx.push(singlecolumn);
					this.wizGrid("setGridParam", {
						Abindkey: bindx
					}, true);
					this.wizGrid("setGridParam", {
						Bbindkeyx: bindkeyx
					}, true);
				}
			, getCurrentCell: function(colname) // // kjs ??? method
				// :2015.07.02
				{
					this.editCellClose();
					var selr = this.wizGrid("getGridParam", "selrow");
					var aa = this.getCell(selr, colname);
					return aa;
				}
			, reload: function() // // kjs ??? method :2015.07.02
				{
					this.trigger('reloadGrid');
				}
			, clearGrid: function() { // pjy 그리드 클리어 함수
				this.wizGrid("clearGridData", true).trigger("reloadGrid");
			}
			, SortGrid: function(ChageVar) { 
				
				//grid1.setColProp('NAME', {sortable: true});
				 // 열려있는 cell 닫기
				var curindex = this.getCurrentRowIndex();
				if(curindex != -1){
					this.editRowClose(curindex+1);
					
				}					
				var colSaveName= this.wizGrid('getGridParam', 'Columns');
				   for(var i =0 ; i < colSaveName.length; i ++){		
					// alert(colSaveName[i]['name']);
						var cm = this.wizGrid('getColProp',colSaveName[i]['name']);
						if(ChageVar){
							
							cm.sortable =true;
						}else{
							cm.sortable =false;
						}
						
					}
				
				
			}
			, SortColumn: function(ChageVar,Colmnlist) { 
				
				//
				 // 열려있는 cell 닫기
				var curindex = this.getCurrentRowIndex();
				if(curindex != -1){
					this.editRowClose(curindex+1);
					
				}					
			//	alert(Colmnlist.length + " @@ " + Colmnlist[0]+ " @@ " + Colmnlist[1]);
				for(var i = 0 ; i <  Colmnlist.length; i++){
					if(ChageVar){
						this.setColProp(Colmnlist[i], {sortable: true});
					}else{
						this.setColProp(Colmnlist[i], {sortable: false});
					}
					
				}		
			}
			,readonlyGrid: function(flag) { // pjy 그리드 readonly
				 // 열려있는 cell 닫기
			
				//this.hideColumn("cb");
				this.wizGrid("setGridParam", {
	        		cellEdit: flag
				}).trigger("reloadGrid", [{
					
				}]);
				/*var curindex = this.getCurrentRowIndex();
				if(curindex != -1){
					this.editRowClose(curindex+1);
					
				}					
				//삭제 체크 숨김
				this.hideColumn("cb");
				var colSaveName= this.wizGrid('getGridParam', 'Columns');
				   for(var i =0 ; i < colSaveName.length; i ++){		
					// alert(colSaveName[i]['name']);
						var cm = this.wizGrid('getColProp',colSaveName[i]['name']);
						cm.editable =false;
					}
					*/
			}
			, getModifiedRow: function(flag) // // kjs ??? method
				// :2015.07.02
				{
					var rowdatas;
					var modrow = [];
					this.each(function() {
						rowdatas = this.p.data;
						for (var i = 0; i < rowdatas.length; i++) {
							if (rowdatas[i].IUDFLAG == flag) {
								modrow.push(rowdatas[i]);
								// alert(rowdatas[i].SAWONNM);
								continue;
							}
							if (flag == "A" && (rowdatas[i].IUDFLAG == "I" || rowdatas[i].IUDFLAG == "U" || rowdatas[i].IUDFLAG ==
									"D")) {
								modrow.push(rowdatas[i]);
							}
						}
					});
					return modrow;
				}
			, getSendParameters: function(flag) // // kjs ??? method
				// :2015.07.02 :
				// request:parameter??
				// :????? set?? o??
				// ???
				{
					var isfirst = true;
					var rowdatas;
					var colSaveName = this.wizGrid('getGridParam', 'Columns');
					var plain_text = "";
					var $t = this;
					var colname = "";
					var datasetname = this.wizGrid("getGridParam", "datasetname");
					this.each(function() {
						rowdatas = this.p.data;
						// alert(rowdatas[0].IUDFLAG+ "@@ " +
						// JSON.stringify(rowdatas));
						for (var rowindex = 0; rowindex < rowdatas.length; rowindex++) {
							if ((flag == "A" && (rowdatas[rowindex].IUDFLAG == "I" || rowdatas[rowindex].IUDFLAG == "U" ||
									rowdatas[rowindex].IUDFLAG == "D")) || rowdatas[rowindex].IUDFLAG == flag) {
								if (colSaveName[0]['name'] == 'rn' && colSaveName[1]['name'] == 'cb') {
									for (var ic = 2; ic < colSaveName.length; ic++) {
										colname = colSaveName[ic]['name'];
										// alert(3 + ' ## '+
										// colnam);
										if (isfirst) {
											plain_text += datasetname + "_" + colname + "=" + rowdatas[rowindex][colname];
											isfirst = false;
										}
										else {
											plain_text += "&" + datasetname + "_" + colname + "=" + rowdatas[rowindex][colname];
										}
									}
								}
								else if (colSaveName[0]['name'] == 'rn' || colSaveName[0]['name'] == 'cb') // ???u???
								// ????????
								// ???
								// ???
								// ????????
								{
									for (var ic = 1; ic < colSaveName.length; ic++) {
										colname = colSaveName[ic]['name'];
										if (isfirst) {
											plain_text += datasetname + "_" + colname + "=" + rowdatas[rowindex][colname];
											isfirst = false;
										}
										else {
											plain_text += "&" + datasetname + "_" + colname + "=" + rowdatas[rowindex][colname];
										}
									}
								}
								else {
									for (var ic = 0; ic < colSaveName.length; ic++) {
										colname = colSaveName[ic]['name'];
										if (isfirst) {
											plain_text += datasetname + "_" + colname + "=" + rowdatas[rowindex][colname];
											isfirst = false;
										}
										else {
											plain_text += "&" + datasetname + "_" + colname + "=" + rowdatas[rowindex][colname]; // $t.wizGrid('getCell',
											// rowindex,
											// colSaveName[ic]['name']
										}
									}
								}
							}
						}
					});
					// alert(plain_text);
					return plain_text;
				}
			, getInputSendParameters: function(flag) // // pjy 데이타
				// 만들기
				{
					var isfirst = true;
					var rowdatas;
					var colSaveName = this.wizGrid('getGridParam', 'Columns');
					var plain_text = "";
					var $t = this;
					var colname = "";
					var cellvalue = "";
					var datasetname = this.wizGrid("getGridParam", "datasetname");
					this.each(function() {
						rowdatas = this.p.data;
						for (var rowindex = 0; rowindex < rowdatas.length; rowindex++) {
							if (flag == "A") // 데이터 전체를 넘길 때
							{
								if (colSaveName[0]['name'] == 'rn' && colSaveName[1]['name'] == 'cb') // 멀티체크
								// 순번
								// 둘다
								// 있을때
								{
									for (var ic = 2; ic < colSaveName.length; ic++) {
										colname = colSaveName[ic]['name'];
										cellvalue = rowdatas[rowindex][colname];
										if (colSaveName[ic]['Gtype'] != undefined && colSaveName[ic]['Gtype'] == "number") {
											cellvalue = this.removeNumberFmt(cellvalue, colSaveName[ic]['GFormat']);
										}
										else if (colSaveName[ic]['GFormat'] != undefined) {
											cellvalue = wizutil.removeGFormat(colSaveName[ic]['GFormat'], cellvalue);
										}
										if (isfirst) {
											plain_text += datasetname + "_" + colname + "=" + cellvalue;
											isfirst = false;
										}
										else {
											plain_text += "&" + datasetname + "_" + colname + "=" + cellvalue;
										}
									}
								}
								else if (colSaveName[0]['name'] == 'rn' || colSaveName[0]['name'] == 'cb') // 멀티체크
								// 또는
								// 순번 이
								// 있을때
								{
									for (var ic = 1; ic < colSaveName.length; ic++) {
										colname = colSaveName[ic]['name'];
										cellvalue = rowdatas[rowindex][colname];
										if (colSaveName[ic]['Gtype'] != undefined && colSaveName[ic]['Gtype'] == "number") {
											cellvalue = wizutil.removeNumberFmt(cellvalue, colSaveName[ic]['GFormat']);
										}
										else if (colSaveName[ic]['GFormat'] != undefined) {
											cellvalue = wizutil.removeGFormat(colSaveName[ic]['GFormat'], cellvalue);
										}
										if (isfirst) {
											datasetnamehis.datasetname + "_" + colname + "=" + cellvalue;
											isfirst = false;
										}
										else {
											plain_text += "&" + datasetname + "_" + colname + "=" + cellvalue;
										}
									}
								}
								else {
									for (var ic = 0; ic < colSaveName.length; ic++) {
										colname = colSaveName[ic]['name'];
										cellvalue = rowdatas[rowindex][colname];
										if (colSaveName[ic]['Gtype'] != undefined && colSaveName[ic]['Gtype'] == "number") {
											cellvalue = wizutil.removeNumberFmt(cellvalue, colSaveName[ic]['GFormat']);
										}
										else if (colSaveName[ic]['GFormat'] != undefined) {
											cellvalue = wizutil.removeGFormat(colSaveName[ic]['GFormat'], cellvalue);
										}
										if (isfirst) {
											plain_text += datasetname + "_" + colname + "=" + cellvalue;
											isfirst = false;
										}
										else {
											plain_text += "&" + datasetname + "_" + colname + "=" + cellvalue; // $t.wizGrid('getCell',
											// rowindex,
											// colSaveName[ic]['name']
										}
									}
								}
							}
							else if (flag == "U" || flag == "D" || flag == "I") { // 수정
								// 플래그
								// 데이터
								// 넘길때
								if (rowdatas[rowindex].IUDFLAG == flag) {
									if (colSaveName[0]['name'] == 'rn' && colSaveName[1]['name'] == 'cb') // 멀티체크
									// 순번
									// 둘다
									// 있을때
									{
										for (var ic = 2; ic < colSaveName.length; ic++) {
											colname = colSaveName[ic]['name'];
											cellvalue = rowdatas[rowindex][colname];
											if (colSaveName[ic]['Gtype'] != undefined && colSaveName[ic]['Gtype'] == "number") {
												cellvalue = this.removeNumberFmt(cellvalue, colSaveName[ic]['GFormat']);
											}
											else if (colSaveName[ic]['GFormat'] != undefined) {
												cellvalue = wizutil.removeGFormat(colSaveName[ic]['GFormat'], cellvalue);
											}
											if (isfirst) {
												plain_text += datasetname + "_" + colname + "=" + cellvalue;
												isfirst = false;
											}
											else {
												plain_text += "&" + datasetname + "_" + colname + "=" + cellvalue;
											}
										}
									}
									else if (colSaveName[0]['name'] == 'rn' || colSaveName[0]['name'] == 'cb') // 멀티체크
									// 또는
									// 순번 이
									// 있을때
									{
										for (var ic = 1; ic < colSaveName.length; ic++) {
											colname = colSaveName[ic]['name'];
											cellvalue = rowdatas[rowindex][colname];
											if (colSaveName[ic]['Gtype'] != undefined && colSaveName[ic]['Gtype'] == "number") {
												cellvalue = wizutil.removeNumberFmt(cellvalue, colSaveName[ic]['GFormat']);
											}
											else if (colSaveName[ic]['GFormat'] != undefined) {
												cellvalue = wizutil.removeGFormat(colSaveName[ic]['GFormat'], cellvalue);
											}
											if (isfirst) {
												plain_text += datasetname + "_" + colname + "=" + cellvalue;
												isfirst = false;
											}
											else {
												plain_text += "&" + datasetname + "_" + colname + "=" + cellvalue;
											}
										}
									}
									else {
										for (var ic = 0; ic < colSaveName.length; ic++) {
											colname = colSaveName[ic]['name'];
											cellvalue = rowdatas[rowindex][colname];
											if (colSaveName[ic]['Gtype'] != undefined && colSaveName[ic]['Gtype'] == "number") {
												cellvalue = wizutil.removeNumberFmt(cellvalue, colSaveName[ic]['GFormat']);
											}
											else if (colSaveName[ic]['GFormat'] != undefined) {
												cellvalue = wizutil.removeGFormat(colSaveName[ic]['GFormat'], cellvalue);
											}
											if (isfirst) {
												plain_text += datasetname + "_" + colname + "=" + cellvalue;
												isfirst = false;
											}
											else {
												plain_text += "&" + datasetname + "_" + colname + "=" + cellvalue; // $t.wizGrid('getCell',
												// rowindex,
												// colSaveName[ic]['name']
											}
										}
									}
								}
							}
							else if (flag == "C") { // 수정 삭제
								// 입력
								// 플래그
								// 데이터
								// 넘길 때.
								if (rowdatas[rowindex].IUDFLAG == "I" || rowdatas[rowindex].IUDFLAG == "U" || rowdatas[rowindex]
									.IUDFLAG == "D") {
									if (colSaveName[0]['name'] == 'rn' && colSaveName[1]['name'] == 'cb') // 멀티체크
									// 순번
									// 둘다
									// 있을때
									{
										for (var ic = 2; ic < colSaveName.length; ic++) {
											colname = colSaveName[ic]['name'];
											cellvalue = rowdatas[rowindex][colname];
											if (colSaveName[ic]['Gtype'] != undefined && colSaveName[ic]['Gtype'] == "number") {
												cellvalue = this.removeNumberFmt(cellvalue, colSaveName[ic]['GFormat']);
											}
											else if (colSaveName[ic]['GFormat'] != undefined) {
												cellvalue = wizutil.removeGFormat(colSaveName[ic]['GFormat'], cellvalue);
											}
											if (isfirst) {
												plain_text += datasetname + "_" + colname + "=" + cellvalue;
												isfirst = false;
											}
											else {
												plain_text += "&" + datasetname + "_" + colname + "=" + cellvalue;
											}
										}
									}
									else if (colSaveName[0]['name'] == 'rn' || colSaveName[0]['name'] == 'cb') // 멀티체크
									// 또는
									// 순번 이
									// 있을때
									{
										for (var ic = 1; ic < colSaveName.length; ic++) {
											colname = colSaveName[ic]['name'];
											cellvalue = rowdatas[rowindex][colname];
											if (colSaveName[ic]['Gtype'] != undefined && colSaveName[ic]['Gtype'] == "number") {
												cellvalue = wizutil.removeNumberFmt(cellvalue, colSaveName[ic]['GFormat']);
											}
											else if (colSaveName[ic]['GFormat'] != undefined) {
												cellvalue = wizutil.removeGFormat(colSaveName[ic]['GFormat'], cellvalue);
											}
											if (isfirst) {
												plain_text += datasetname + "_" + colname + "=" + cellvalue;
												isfirst = false;
											}
											else {
												plain_text += "&" + datasetname + "_" + colname + "=" + cellvalue;
											}
										}
									}
									else {
										for (var ic = 0; ic < colSaveName.length; ic++) {
											colname = colSaveName[ic]['name'];
											cellvalue = rowdatas[rowindex][colname];
											if (colSaveName[ic]['Gtype'] != undefined && colSaveName[ic]['Gtype'] == "number") {
												cellvalue = wizutil.removeNumberFmt(cellvalue, colSaveName[ic]['GFormat']);
											}
											else if (colSaveName[ic]['GFormat'] != undefined) {
												cellvalue = wizutil.removeGFormat(colSaveName[ic]['GFormat'], cellvalue);
											}
											if (isfirst) {
												plain_text += datasetname + "_" + colname + "=" + cellvalue;
												isfirst = false;
											}
											else {
												plain_text += "&" + datasetname + "_" + colname + "=" + cellvalue; // $t.wizGrid('getCell',
												// rowindex,
												// colSaveName[ic]['name']
											}
										}
									}
								}
							}
						}
					});
					// alert(plain_text);
					return plain_text;
				}
			, getExcelSendParameters: function() // // pjy 엑셀 다운로드
				// 데이타 만들기 히든뺴고 다운로드
				{
					var isfirst = true;
					var rowdatas;
					var colSaveName = this.wizGrid('getGridParam', 'Columns');
					var plain_text = "";
					var $t = this;
					var colname = "";
					var celldata = "";
					var datasetname = this.wizGrid("getGridParam", "datasetname");
					this.each(function() {
						rowdatas = this.p.data;
						// alert(rowdatas[0].IUDFLAG+ "@@ " +
						// JSON.stringify(rowdatas));
						for (var rowindex = 0; rowindex < rowdatas.length; rowindex++) {
							for (var ic = 0; ic < colSaveName.length; ic++) {
								if (colSaveName[ic]['hidden'] == false) {
									// alert(
									// colSaveName[ic]['hidden']
									// + " @@@@@@@@@@@@ " +
									// colSaveName[ic]['name']);
									colname = colSaveName[ic]['name'];
									celldata = wizutil.JSONData_Char(rowdatas[rowindex][colname]);
									if(celldata == null){
										celldata = "";
									}
									if (isfirst) {
										if (typeof(celldata) == "undefined") {
											plain_text += "S1000" + "_" + colname + "=" + "";
										}
										else {
											plain_text += "S1000"  + "_" + colname + "=" + celldata;
										}
										isfirst = false;
									}
									else {
										celldata = rowdatas[rowindex][colname];
										if (typeof(celldata) == "undefined") {
											plain_text += "&" + "S1000"  + "_" + colname + "=" + "";
										}
										else {
											plain_text += "&" + "S1000"  + "_" + colname + "=" + celldata;
										}
										// $t.wizGrid('getCell',
										// rowindex,
										// colSaveName[ic]['name']
									}
								}
							}
						}
					});
					// alert(plain_text);
					return plain_text;
				}
			, getExcelSendParametersHidden: function() // // pjy 엑셀
				// 다운로드 데이타
				// 만들기 히든 칼럼
				// 포함
				{
					var isfirst = true;
					var rowdatas;
					var colSaveName = this.wizGrid('getGridParam', 'Columns');
					var plain_text = "";
					var $t = this;
					var colname = "";
					var celldata = "";
					this.each(function() {
						rowdatas = this.p.data;
						// alert(rowdatas[0].IUDFLAG+ "@@ " +
						// JSON.stringify(rowdatas));
						for (var rowindex = 0; rowindex < rowdatas.length; rowindex++) {
							for (var ic = 0; ic < colSaveName.length; ic++) {
								// alert(
								// colSaveName[ic]['hidden'] + "
								// @@@@@@@@@@@@ " +
								// colSaveName[ic]['name']);
								colname = colSaveName[ic]['name'];
								celldata = wizutil.JSONData_Char(rowdatas[rowindex][colname]);
								if(celldata == null){
									celldata = "";
								}
								if (isfirst) {
									if (typeof(celldata) == "undefined") {
										plain_text +=  "S1000" + "_" + colname + "=" + "";
									}
									else {
										plain_text +=  "S1000" + "_" + colname + "=" + celldata;
									}
									isfirst = false;
								}
								else {
									if (typeof(celldata) == "undefined") {
										plain_text += "&" +  "S1000" + "_" + colname + "=" + "";
									}
									else {
										plain_text += "&" +  "S1000" + "_" + colname + "=" + celldata;
									}
									// $t.wizGrid('getCell',
									// rowindex,
									// colSaveName[ic]['name']
								}
							}
						}
					});
					// alert(plain_text);
					return plain_text;
				}
			, getDataRows: function() // // kjs ??? method
				// :2015.07.02
				{
					var rowdatas;
					this.each(function() {
						rowdatas = this.p.data;
					});
					return rowdatas;
				}
			, getDataRow: function(rowid) // // kjs ??? method
				// :2015.07.02
				{
					return this.wizGrid('getRowData', rowid);
					/*
					 * var row; var rowdatas; this.each(function() {
					 * rowdatas = this.p.data; for(var i=0;i<rowdatas.length;i++) {
					 * //alert("getDataRow:"+rowdatas[i].id +":" +
					 * rowid); if(rowdatas[i].id == rowid) { row =
					 * rowdatas[i]; break; } } }); return row;
					 */
				}
			, getDataRowCount: function() // // kjs ??? method
				// :2015.07.02
				{
					var len = 0;
					this.each(function() {
						len = this.p.data.length;
					});
					return len;
				}
			, getColumnCount: function() // // kjs ??? method
				// :2015.07.02
				{
					var len = 0;
					this.each(function() {
						len = this.p.Columns.length;
					});
					return len;
				}
			, getRowCount: function() // // kjs ??? method
				// :2015.07.02
				{
					var len = 0;
					this.each(function() {
						len = this.rows.length - 1;
					});
					return len;
				}
			, getGridRow: function(idx) // // kjs ??? method
				// :2015.07.02
				{
					var row;
					this.each(function() {
						row = this.rows[idx];
					});
					return row;
				}
			, editRowClose: function(rowindex) // // kjs ??? method
				// :2015.07.02
				{
					this.each(function() {
						var $t = this;
						if ($t.rows.length > 0) {
							for (var j = 0; j < this.rows[0].cells.length; j++) {
								$($t).wizGrid("editCell", rowindex, j, false);
							}
						}
					});
				}
			, editCellClose: function() // // kjs ??? method
				// :2015.07.02
				{
					this.each(function() {
						var $t = this;
						if ($t.rows.length > 0) {
							for (var i = 0; i < $t.rows.length; i++) {
								for (var j = 0; j < this.rows[0].cells.length; j++) {
									$($t).wizGrid("editCell", i, j, false);
								}
							}
						}
					});
				}
			, removeRow: function(rowindex) {
				var rowid = "";
				if (rowindex == undefined) {
					rowid = this.getCurrentRowId();
				}
				else {
					rowindex = rowindex + 1;
					rowid = this.getRowId(rowindex);
				}
				if (rowid == null || rowid == "") {
					return;
				}
				this.delRowData(rowid);
			}
			,insertRow : function(position, src) {                            ////EEEkjs: method :2015.07.02, 2015.12.11 return rowindex 수정
			      
				  
			    var rowidx = 'R';
				var rowid = "";
				var rdata = {};
				var pos =position;
				var rowindex = this.getRowCount()+1;
				// 글로벌 현재 rowid 값 가져오기
				var curRowID = this.wizGrid('getGridParam', 'selrow');//this.getCurrentRowIndex();
				var currowindx = this.getCurrentRowIndex();  // this.getRowIndex(curRowID);   //
				var uniqueRowindex = this.wizGrid("getGridParam", "uniqueRowindex");
				var beforerowID = "";
				uniqueRowindex++;
	                 
				rowid = rowidx + rowindex;
				beforerowID= rowidx + (rowindex-1);
				//rowid 가 존재하면 +1 함
				if(this.getRowIndex(rowid) != -1){
					rowindex++;
				rowid = rowidx + rowindex;
				beforerowID= rowidx + (rowindex-1);
				}
				
				if(["first", "last", "before", "after"].indexOf(pos) == -1) {pos = "last";}
				var success = false, nm, row, gi, si, ni,sind, i, v, prp="", aradd, cnm, cn, data, cm, id;

				var returnrowindex=-1;
				if(rdata) 
				{
					if($.isArray(rdata)) {
						aradd=true;
						//pos = "last";
						cnm = rowid;
					} else {
						rdata = [rdata];
						aradd = false;
					}
					this.each(function() {
						
						var t = this, datalen = rdata.length;
							// 조회결과 데이터가없습니다. 지우기 위해 처리  시작
						  var PageGId = t.p.id;
						   $("#NoData_"+PageGId).hide();
						   //끝 
						   //insert row 할떄 수정 상태에서 하게되면 오류 발생  cell 다 닫고 실행해야함 시작 
						    // editcell 닫기
						        // 현재 선택된 row edit 상태 닫기
								//$('#'+PageGId).reload();   $('#'+PageGId).editCellClose();
	  					     var ids = $('#'+PageGId).getDataIDs();
							 var colSaveName= $('#'+PageGId).wizGrid('getGridParam', 'Columns');
								// 선택된 row 스타일 제거
								//	$('#'+PageGId).removeClass("selected-row ui-state-hover-grid");  //edit-cell ui-state-highlight-grid
							
										// cell 닫기
							// cell 닫기

						   if(currowindx != -1) {
							 
						
								$('#'+PageGId).editRowClose(currowindx+1);
								

							}				
					


						
						ni = t.p.rownumbers===true ? 1 :0;
						gi = t.p.multiselect ===true ? 1 :0;
						si = t.p.subGrid===true ? 1 :0;
						if(!aradd) {
							if(rowid !== undefined) { rowid = String(rowid);}
							else {
								rowid = $.wizgrid.randId();
								if(t.p.keyName !== false) {
									cnm = t.p.keyName;
									if(rdata[0][cnm] !== undefined) { rowid = rdata[0][cnm]; }
								}
							}
						}
						
						cn = t.p.altclass;
						var k = 0, cna ="", lcdata = {},
						air = $.isFunction(t.p.OnInsertRow) ? true : false;
										
						while(k < datalen) {
							data = rdata[k];
							row=[];
							if(aradd) {
								try {
									rowid = data[cnm];
									if(rowid===undefined) {
										rowid = $.wizgrid.randId();
									}
								}
								catch (e) {rowid = $.wizgrid.randId();}
								cna = t.p.altRows === true ?  (t.rows.length-1)%2 === 0 ? cn : "" : "";
							}
							id = rowid;
							rowid  = t.p.idPrefix + rowid;
							if(ni){
								prp = t.formatCol(0,1,'',null,rowid, true);
								row[row.length] = "<td role=\"gridcell\" class=\"ui-state-default-grid wizgrid-rownum\" "+prp+">0</td>";
							}
							if(gi) {
								v = "<input role=\"checkbox\" type=\"checkbox\""+" id=\"wizg_"+t.p.id+"_"+rowid+"\" class=\"cbox\"/>";
								prp = t.formatCol(ni,1,'', null, rowid, true);
								row[row.length] = "<td role=\"gridcell\" "+prp+">"+v+"</td>";
							}
							if(si) {
								row[row.length] = $(t).wizGrid("addSubGridCell",gi+ni,1);
							}
							for(i = gi+si+ni; i < t.p.Columns.length;i++)
							{
								cm = t.p.Columns[i];
								nm = cm.name;

								lcdata[nm] = data[nm];
								//alert(lcdata[nm][1] + " @@ " + data[nm][1]);
								v = t.formatter( rowid, $.wizgrid.getAccessor(data,nm), i, data );
								//alert("I:"+ i + " V:  " + v + " lcdata: " + lcdata.editable + " data: " +data.editable + " rowid: " + rowid);//pjy1133
								prp = t.formatCol(i,1,v, data, rowid, lcdata);
								
								
								row[row.length] = "<td role=\"gridcell\" "+prp+">"+v+"</td>";
								
							}
							row.unshift( t.constructTr(rowid, false, cna, lcdata, data, false ) );//id, hide, altClass, rd, cur, selected)
							row[row.length] = "</tr>";
							
							
							//$('#'+PageGId).reload(); 
							if(t.rows.length === 0){
								$("table:first",t.grid.bDiv).append(row.join(''));
							} else {
								switch (pos) {
									case 'last':
										$(t.rows[t.rows.length-1]).after(row.join(''));
										sind = t.rows.length-1;
										break;
									case 'first':
										$(t.rows[0]).after(row.join(''));
										sind = 1;
										break;
									case 'after':

										sind = $(t).wizGrid('getGridRowById', src);
										
										if (sind) {
											if($(t.rows[sind.rowIndex+1]).hasClass("ui-subgrid")) { $(t.rows[sind.rowIndex+1]).after(row); }
											else { $(sind).after(row.join('')); }
											
											sind=currowindx + 1;
										}	
										break;
									case 'before':
										sind = $(t).wizGrid('getGridRowById', src);
										if(sind) {
											$(sind).before(row.join(''));
											sind=sind.rowIndex - 1;
										}
										break;
								}
							
								returnrowindex = sind;
							}
							if(t.p.subGrid===true) {
								$(t).wizGrid("addSubGrid",gi+ni, sind);
							}
							t.p.records++;
							t.p.reccount++;
							
							
							$(t).triggerHandler("wizGridAfterInsertRow", [rowid,data,data]);
							
							if(air) { 
							
							t.p.OnInsertRow.call(t,rowid,data,data); 
							 

							}
							k++;
							if(t.p.datatype === 'local') {
								lcdata[t.p.localReader.id] = id;
								t.p._index[id] = t.p.data.length;
								t.p.data.push(lcdata);
								lcdata = {};
							}
						}
						
						if( t.p.altRows === true && !aradd) {
							if (pos === "last") {
								if ((t.rows.length-1)%2 === 1)  {$(t.rows[t.rows.length-1]).addClass(cn);}
							} else {
								$(t.rows).each(function(i){
									if(i % 2 ===1) { $(this).addClass(cn); }
									else { $(this).removeClass(cn); }
								});
							}
						}
						
						t.updatepager(true,true);
						success = true;
						
						if(currowindx != -1) {
							 // 선택된 row class 제거
							  if(position == "last"){
							  	$('#'+PageGId + ' tr#'+curRowID).removeClass("selected-row ui-state-hover-grid");
							  	$('#'+PageGId + ' tr#'+curRowID).children().each(function() {
					     	 	});
     
							  }
							if(position == "first"){
								$('#'+PageGId + ' tr#'+rowid).children().each(function() {
                                
								$(this).removeClass("ui-state-highlight-grid"); 
							  	 	});
									
							    $('#'+PageGId +' tr#'+curRowID).removeClass("selected-row ui-state-hover-grid"); 

							}
							

						}
						/*if(position == "first"){
									$('#'+rowid).children().each(function() {
                                      
									$(this).removeClass("ui-state-highlight-grid"); 
								  	 	});
										// 추가 (전 row) class 제거
								    $('#'+beforerowID).removeClass("selected-row ui-state-hover-grid"); 

								}*/
						
						// IUDFLAG 값 I를 셋팅
						for(i = gi+si+ni; i < t.p.Columns.length;i++)
							{
								cm = t.p.Columns[i];
								nm = cm.name;
							if(nm == "IUDFLAG")
								{
									
									$('#'+PageGId).setCellVal(rowid , i , "I" , false);
								 
								}
							}

						


					});
				}
					
					  
				// row 추가시 추가된 row 선택
				/*var colsize= this.wizGrid('getGridParam', 'Columns');
				var Inserrowindex = this.getRowIndex(rowid);
				this.editCell(Inserrowindex+1, 7,false);  */
				 // end
				if(returnrowindex > 0) returnrowindex--;
				return returnrowindex
				//return success;
			},
			appendRow: function(src) { // // kjs: method
				// :2015.07.02, 2015.12.11
				// return rowindex 수정
				var rowid = "new";
				var rdata = {};
				var pos = "last";
				var uniqueRowindex = this.wizGrid("getGridParam", "uniqueRowindex");
				uniqueRowindex++;
				rowid = rowid + uniqueRowindex;
				if (["first", "last", "before", "after"].indexOf(pos) == -1) {
					pos = "last";
				}
				var success = false
					, nm, row, gi, si, ni, sind, i, v, prp = ""
					, aradd, cnm, cn, data, cm, id;
				var returnrowindex = -1;
				if (rdata) {
					if ($.isArray(rdata)) {
						aradd = true;
						// pos = "last";
						cnm = rowid;
					}
					else {
						rdata = [rdata];
						aradd = false;
					}
					this.each(function() {
						var t = this
							, datalen = rdata.length;
						ni = t.p.rownumbers === true ? 1 : 0;
						gi = t.p.multiselect === true ? 1 : 0;
						si = t.p.subGrid === true ? 1 : 0;
						if (!aradd) {
							if (rowid !== undefined) {
								rowid = String(rowid);
							}
							else {
								rowid = $.wizgrid.randId();
								if (t.p.keyName !== false) {
									cnm = t.p.keyName;
									if (rdata[0][cnm] !== undefined) {
										rowid = rdata[0][cnm];
									}
								}
							}
						}
						cn = t.p.altclass;
						var k = 0
							, cna = ""
							, lcdata = {}
							, air = $.isFunction(t.p.OnInsertRow) ? true : false;
						while (k < datalen) {
							data = rdata[k];
							row = [];
							if (aradd) {
								try {
									rowid = data[cnm];
									if (rowid === undefined) {
										rowid = $.wizgrid.randId();
									}
								}
								catch (e) {
									rowid = $.wizgrid.randId();
								}
								cna = t.p.altRows === true ? (t.rows.length - 1) % 2 === 0 ? cn : "" : "";
							}
							id = rowid;
							rowid = t.p.idPrefix + rowid;
							if (ni) {
								prp = t.formatCol(0, 1, '', null, rowid, true);
								row[row.length] = "<td role=\"gridcell\" class=\"ui-state-default-grid wizgrid-rownum\" " + prp +
									">0</td>";
							}
							if (gi) {
								v = "<input role=\"checkbox\" type=\"checkbox\"" + " id=\"wizg_" + t.p.id + "_" + rowid +
									"\" class=\"cbox\"/>";
								prp = t.formatCol(ni, 1, '', null, rowid, true);
								row[row.length] = "<td role=\"gridcell\" " + prp + ">" + v + "</td>";
							}
							if (si) {
								row[row.length] = $(t).wizGrid("addSubGridCell", gi + ni, 1);
							}
							for (i = gi + si + ni; i < t.p.Columns.length; i++) {
								cm = t.p.Columns[i];
								nm = cm.name;
								lcdata[nm] = data[nm];
								v = t.formatter(rowid, $.wizgrid.getAccessor(data, nm), i, data);
								prp = t.formatCol(i, 1, v, data, rowid, lcdata);
								row[row.length] = "<td role=\"gridcell\" " + prp + ">" + v + "</td>";
							}
							row.unshift(t.constructTr(rowid, false, cna, lcdata, data, false));
							row[row.length] = "</tr>";
							if (t.rows.length === 0) {
								$("table:first", t.grid.bDiv).append(row.join(''));
							}
							else {
								switch (pos) {
									case 'last':
										$(t.rows[t.rows.length - 1]).after(row.join(''));
										sind = t.rows.length - 1;
										break;
									case 'first':
										$(t.rows[0]).after(row.join(''));
										sind = 1;
										break;
									case 'after':
										sind = $(t).wizGrid('getGridRowById', src);
										if (sind) {
											if ($(t.rows[sind.rowIndex + 1]).hasClass("ui-subgrid")) {
												$(t.rows[sind.rowIndex + 1]).after(row);
											}
											else {
												$(sind).after(row.join(''));
											}
											sind = sind.rowIndex + 1;
										}
										break;
									case 'before':
										sind = $(t).wizGrid('getGridRowById', src);
										if (sind) {
											$(sind).before(row.join(''));
											sind = sind.rowIndex - 1;
										}
										break;
								}
								returnrowindex = sind;
							}
							if (t.p.subGrid === true) {
								$(t).wizGrid("addSubGrid", gi + ni, sind);
							}
							t.p.records++;
							t.p.reccount++;
							$(t).triggerHandler("wizGridAfterInsertRow", [rowid, data
								, data
							]);
							if (air) {
								t.p.OnInsertRow.call(t, rowid, data, data);
							}
							k++;
							if (t.p.datatype === 'local') {
								lcdata[t.p.localReader.id] = id;
								t.p._index[id] = t.p.data.length;
								t.p.data.push(lcdata);
								lcdata = {};
							}
						}
						if (t.p.altRows === true && !aradd) {
							if (pos === "last") {
								if ((t.rows.length - 1) % 2 === 1) {
									$(t.rows[t.rows.length - 1]).addClass(cn);
								}
							}
							else {
								$(t.rows).each(function(i) {
									if (i % 2 === 1) {
										$(this).addClass(cn);
									}
									else {
										$(this).removeClass(cn);
									}
								});
							}
						}
						t.updatepager(true, true);
						success = true;
					});
				}
				if (returnrowindex > 0) returnrowindex--;
				return returnrowindex;
			}
			, addRowData: function(rowid, rdata, pos, src) {
				if (["first", "last", "before", "after"].indexOf(pos) == -1) {
					pos = "last";
				}
				var success = false
					, nm, row, gi, si, ni, sind, i, v, prp = ""
					, aradd, cnm, cn, data, cm, id;
				if (rdata) {
					if ($.isArray(rdata)) {
						aradd = true;
						// pos = "last";
						cnm = rowid;
					}
					else {
						rdata = [rdata];
						aradd = false;
					}
					this.each(function() {
						var t = this
							, datalen = rdata.length;
						ni = t.p.rownumbers === true ? 1 : 0;
						gi = t.p.multiselect === true ? 1 : 0;
						si = t.p.subGrid === true ? 1 : 0;
						if (!aradd) {
							if (rowid !== undefined) {
								rowid = String(rowid);
							}
							else {
								rowid = $.wizgrid.randId();
								if (t.p.keyName !== false) {
									cnm = t.p.keyName;
									if (rdata[0][cnm] !== undefined) {
										rowid = rdata[0][cnm];
									}
								}
							}
						}
						cn = t.p.altclass;
						var k = 0
							, cna = ""
							, lcdata = {}
							, air = $.isFunction(t.p.OnInsertRow) ? true : false;
						while (k < datalen) {
							data = rdata[k];
							row = [];
							if (aradd) {
								try {
									rowid = data[cnm];
									if (rowid === undefined) {
										rowid = $.wizgrid.randId();
									}
								}
								catch (e) {
									rowid = $.wizgrid.randId();
								}
								cna = t.p.altRows === true ? (t.rows.length - 1) % 2 === 0 ? cn : "" : "";
							}
							id = rowid;
							rowid = t.p.idPrefix + rowid;
							if (ni) {
								prp = t.formatCol(0, 1, '', null, rowid, true);
								row[row.length] = "<td role=\"gridcell\" class=\"ui-state-default-grid wizgrid-rownum\" " + prp +
									">0</td>";
							}
							if (gi) {
								v = "<input role=\"checkbox\" type=\"checkbox\"" + " id=\"wizg_" + t.p.id + "_" + rowid +
									"\" class=\"cbox\"/>";
								prp = t.formatCol(ni, 1, '', null, rowid, true);
								row[row.length] = "<td role=\"gridcell\" " + prp + ">" + v + "</td>";
							}
							if (si) {
								row[row.length] = $(t).wizGrid("addSubGridCell", gi + ni, 1);
							}
							for (i = gi + si + ni; i < t.p.Columns.length; i++) {
								cm = t.p.Columns[i];
								nm = cm.name;
								lcdata[nm] = data[nm];
								v = t.formatter(rowid, $.wizgrid.getAccessor(data, nm), i, data);
								prp = t.formatCol(i, 1, v, data, rowid, lcdata);
								row[row.length] = "<td role=\"gridcell\" " + prp + ">" + v + "</td>";
							}
							row.unshift(t.constructTr(rowid, false, cna, lcdata, data, false));
							row[row.length] = "</tr>";
							if (t.rows.length === 0) {
								$("table:first", t.grid.bDiv).append(row.join(''));
							}
							else {
								switch (pos) {
									case 'last':
										$(t.rows[t.rows.length - 1]).after(row.join(''));
										sind = t.rows.length - 1;
										break;
									case 'first':
										$(t.rows[0]).after(row.join(''));
										sind = 1;
										break;
									case 'after':
										sind = $(t).wizGrid('getGridRowById', src);
										if (sind) {
											if ($(t.rows[sind.rowIndex + 1]).hasClass("ui-subgrid")) {
												$(t.rows[sind.rowIndex + 1]).after(row);
											}
											else {
												$(sind).after(row.join(''));
											}
											sind = sind.rowIndex + 1;
										}
										break;
									case 'before':
										sind = $(t).wizGrid('getGridRowById', src);
										if (sind) {
											$(sind).before(row.join(''));
											sind = sind.rowIndex - 1;
										}
										break;
								}
							}
							if (t.p.subGrid === true) {
								$(t).wizGrid("addSubGrid", gi + ni, sind);
							}
							t.p.records++;
							t.p.reccount++;
							// pjy start 추가
							var newValue = "I"
							$(t).wizGrid('setCell', rowid, 'IUDFLAG', newValue);
							// end
							$(t).triggerHandler("wizGridAfterInsertRow", [rowid, data
								, data
							]);
							if (air) {
								t.p.OnInsertRow.call(t, rowid, data, data);
							}
							k++;
							if (t.p.datatype === 'local') {
								lcdata[t.p.localReader.id] = id;
								t.p._index[id] = t.p.data.length;
								t.p.data.push(lcdata);
								lcdata = {};
							}
						}
						if (t.p.altRows === true && !aradd) {
							if (pos === "last") {
								if ((t.rows.length - 1) % 2 === 1) {
									$(t.rows[t.rows.length - 1]).addClass(cn);
								}
							}
							else {
								$(t.rows).each(function(i) {
									if (i % 2 === 1) {
										$(this).addClass(cn);
									}
									else {
										$(this).removeClass(cn);
									}
								});
							}
						}
						t.updatepager(true, true);
						success = true;
					});
				}
				return success;
			}
			, footerData: function(action, data, format) {
				var nm, success = false
					, res = {}
					, title;

				function isEmpty(obj) {
					var i;
					for (i in obj) {
						if (obj.hasOwnProperty(i)) {
							return false;
						}
					}
					return true;
				}
				if (action === undefined) {
					action = "get";
				}
				if (typeof format !== "boolean") {
					format = true;
				}
				action = action.toLowerCase();
				this.each(function() {
					var t = this
						, vl;
					if (!t.grid || !t.p.footerrow) {
						return false;
					}
					if (action === "set") {
						if (isEmpty(data)) {
							return false;
						}
					}
					success = true;
					$(this.p.Columns).each(function(i) {
						nm = this.name;
						if (action === "set") {
							if (data[nm] !== undefined) {
								vl = format ? t.formatter("", data[nm], i, data, 'edit') : data[nm];
								title = this.title ? {
									"title": $.wizgrid.stripHtml(vl)
								} : {};
								$("tr.footrow td:eq(" + i + ")", t.grid.sDiv).html(vl).attr(title);
								success = true;
							}
						}
						else if (action === "get") {
							res[nm] = $("tr.footrow td:eq(" + i + ")", t.grid.sDiv).html();
						}
					});
				});
				return action === "get" ? res : success;
			}
			, showHideCol: function(colname, show) {
				return this.each(function() {
					var $t = this
						, fndh = false
						, brd = $.wizgrid.cell_width ? 0 : $t.p.cellLayout
						, cw;
					if (!$t.grid) {
						return;
					}
					if (typeof colname === 'string') {
						colname = [colname];
					}
					show = show !== "none" ? "" : "none";
					var sw = show === "" ? true : false
						, gh = $t.p.groupHeader && (typeof $t.p.groupHeader === 'object' || $.isFunction($t.p.groupHeader));
					if (gh) {
						$($t).wizGrid('destroyGroupHeader', false);
					}
					$(this.p.Columns).each(function(i) {
						if ($.inArray(this.name, colname) !== -1 && this.hidden === sw) {
							if ($t.p.frozenColumns === true && this.frozen === true) {
								return true;
							}
							$("tr[role=row]", $t.grid.hDiv).each(function() {
								$(this.cells[i]).css("display", show);
							});
							$($t.rows).each(function() {
								if (!$(this).hasClass("wizgroup")) {
									$(this.cells[i]).css("display", show);
								}
							});
							if ($t.p.footerrow) {
								$("tr.footrow td:eq(" + i + ")", $t.grid.sDiv).css("display", show);
							}
							cw = parseInt(this.width, 10);
							if (show === "none") {
								$t.p.tblwidth -= cw + brd;
							}
							else {
								$t.p.tblwidth += cw + brd;
							}
							this.hidden = !sw;
							fndh = true;
							$($t).triggerHandler("wizGridShowHideCol", [
								sw
								, this.name
								, i
							]);
						}
					});
					if (fndh === true) {
						if ($t.p.shrinkToFit === true && !isNaN($t.p.height)) {
							$t.p.tblwidth += parseInt($t.p.scrollOffset, 10);
						}
						$($t).wizGrid("setGridWidth", $t.p.shrinkToFit === true ? $t.p.tblwidth : $t.p.width);
					}
					if (gh) {
						$($t).wizGrid('HeadersMerge', $t.p.groupHeader);
					}
				});
			}
			, hideCol: function(colname) {
				return this.each(function() {
					$(this).wizGrid("showHideCol", colname, "none");
				});
			}
			, showCol: function(colname) {
				return this.each(function() {
					$(this).wizGrid("showHideCol", colname, "");
				});
			}
			, remapColumns: function(permutation, updateCells, keepHeader) {
				function resortArray(a) {
					var ac;
					if (a.length) {
						ac = $.makeArray(a);
					}
					else {
						ac = $.extend({}, a);
					}
					$.each(permutation, function(i) {
						a[i] = ac[this];
					});
				}
				var ts = this.get(0);

				function resortRows(parent, clobj) {
					$(">tr" + (clobj || ""), parent).each(function() {
						var row = this;
						var elems = $.makeArray(row.cells);
						$.each(permutation, function() {
							var e = elems[this];
							if (e) {
								row.appendChild(e);
							}
						});
					});
				}
				resortArray(ts.p.Columns);
				resortArray(ts.p.Names);
				resortArray(ts.grid.headers);
				resortRows($("thead:first", ts.grid.hDiv), keepHeader && ":not(.ui-wizgrid-labels)");
				if (updateCells) {
					resortRows($("#" + $.wizgrid.wizID(ts.p.id) + " tbody:first")
						, ".wizgfirstrow, tr.wizgrow, tr.wizfoot");
				}
				if (ts.p.footerrow) {
					resortRows($("tbody:first", ts.grid.sDiv));
				}
				if (ts.p.remapColumns) {
					if (!ts.p.remapColumns.length) {
						ts.p.remapColumns = $.makeArray(permutation);
					}
					else {
						resortArray(ts.p.remapColumns);
					}
				}
				ts.p.lastsort = $.inArray(ts.p.lastsort, permutation);
				if (ts.p.treeGrid) {
					ts.p.expColInd = $.inArray(ts.p.expColInd, permutation);
				}
				$(ts).triggerHandler("wizGridRemapColumns", [permutation, updateCells, keepHeader]);
			}
			, setGridWidth: function(nwidth, shrink) {
				return this.each(function() {
					if (!this.grid) {
						return;
					}
					var $t = this
						, cw, initwidth = 0
						, brd = $.wizgrid.cell_width ? 0 : $t.p.cellLayout
						, lvc, vc = 0
						, hs = false
						, scw = $t.p.scrollOffset
						, aw, gw = 0
						, cr;
					if (typeof shrink !== 'boolean') {
						shrink = $t.p.shrinkToFit;
					}
					if (isNaN(nwidth)) {
						return;
					}
					nwidth = parseInt(nwidth, 10);
					$t.grid.width = $t.p.width = nwidth;
					$("#gbox_" + $.wizgrid.wizID($t.p.id)).css("width", nwidth + "px");
					$("#gview_" + $.wizgrid.wizID($t.p.id)).css("width", nwidth + "px");
					$($t.grid.bDiv).css("width", nwidth + "px");
					$($t.grid.hDiv).css("width", nwidth + "px");
					if ($t.p.pager) {
						$($t.p.pager).css("width", nwidth + "px");
					}
					if ($t.p.toppager) {
						$($t.p.toppager).css("width", nwidth + "px");
					}
					if ($t.p.toolbar[0] === true) {
						$($t.grid.uDiv).css("width", nwidth + "px");
						if ($t.p.toolbar[1] === "both") {
							$($t.grid.ubDiv).css("width", nwidth + "px");
						}
					}
					if ($t.p.footerrow) {
						$($t.grid.sDiv).css("width", nwidth + "px");
					}
					if (shrink === false && $t.p.forceFit === true) {
						$t.p.forceFit = false;
					}
					if (shrink === true) {
						$.each($t.p.Columns, function() {
							if (this.hidden === false) {
								cw = this.widthOrg;
								initwidth += cw + brd;
								if (this.fixed) {
									gw += cw + brd;
								}
								else {
									vc++;
								}
							}
						});
						if (vc === 0) {
							return;
						}
						$t.p.tblwidth = initwidth;
						aw = nwidth - brd * vc - gw;
						if (!isNaN($t.p.height)) {
							if ($($t.grid.bDiv)[0].clientHeight < $($t.grid.bDiv)[0].scrollHeight || $t.rows.length === 1) {
								hs = true;
								aw -= scw;
							}
						}
						initwidth = 0;
						var cle = $t.grid.cols.length > 0;
						$.each($t.p.Columns, function(i) {
							if (this.hidden === false && !this.fixed) {
								cw = this.widthOrg;
								cw = Math.round(aw * cw / ($t.p.tblwidth - brd * vc - gw));
								if (cw < 0) {
									return;
								}
								this.width = cw;
								initwidth += cw;
								$t.grid.headers[i].width = cw;
								$t.grid.headers[i].el.style.width = cw + "px";
								if ($t.p.footerrow) {
									$t.grid.footers[i].style.width = cw + "px";
								}
								if (cle) {
									$t.grid.cols[i].style.width = cw + "px";
								}
								lvc = i;
							}
						});
						if (!lvc) {
							return;
						}
						cr = 0;
						if (hs) {
							if (nwidth - gw - (initwidth + brd * vc) !== scw) {
								cr = nwidth - gw - (initwidth + brd * vc) - scw;
							}
						}
						else if (Math.abs(nwidth - gw - (initwidth + brd * vc)) !== 1) {
							cr = nwidth - gw - (initwidth + brd * vc);
						}
						$t.p.Columns[lvc].width += cr;
						$t.p.tblwidth = initwidth + cr + brd * vc + gw;
						if ($t.p.tblwidth > nwidth) {
							var delta = $t.p.tblwidth - parseInt(nwidth, 10);
							$t.p.tblwidth = nwidth;
							cw = $t.p.Columns[lvc].width = $t.p.Columns[lvc].width - delta;
						}
						else {
							cw = $t.p.Columns[lvc].width;
						}
						$t.grid.headers[lvc].width = cw;
						$t.grid.headers[lvc].el.style.width = cw + "px";
						if (cle) {
							$t.grid.cols[lvc].style.width = cw + "px";
						}
						if ($t.p.footerrow) {
							$t.grid.footers[lvc].style.width = cw + "px";
						}
					}
					if ($t.p.tblwidth) {
						$('table:first', $t.grid.bDiv).css("width", $t.p.tblwidth + "px");
						$('table:first', $t.grid.hDiv).css("width", $t.p.tblwidth + "px");
						$t.grid.hDiv.scrollLeft = $t.grid.bDiv.scrollLeft;
						if ($t.p.footerrow) {
							$('table:first', $t.grid.sDiv).css("width", $t.p.tblwidth + "px");
						}
					}
				});
			}
			, setGridHeight: function(nh) {
				return this.each(function() {
					var $t = this;
					if (!$t.grid) {
						return;
					}
					var bDiv = $($t.grid.bDiv);
					bDiv.css({
						height: nh + (isNaN(nh) ? "" : "px")
					});
					if ($t.p.frozenColumns === true) {
						// follow the original set height to use 16,
						// better scrollbar width detection
						$('#' + $.wizgrid.wizID($t.p.id) + "_frozen").parent().height(bDiv.height() - 16);
					}
					$t.p.height = nh;
					if ($t.p.scroll) {
						$t.grid.populateVisible();
					}
				});
			}
			, setCaption: function(newcap) {
				return this.each(function() {
					this.p.caption = newcap;
					$("span.ui-wizgrid-title, span.ui-wizgrid-title-rtl", this.grid.cDiv).html(newcap);
					$(this.grid.cDiv).show();
					$(this.grid.hDiv).removeClass('ui-corner-top');
				});
			}
			, setColLabel: function(colname, nData, prop, attrp) {  //setLabel
				return this.each(function() {
					
					var $t = this
						, pos = -1;
					if (!$t.grid) {
						return;
					}
					if (colname !== undefined) {
						$($t.p.Columns).each(function(i) {
						     
							if (this.name === colname) {

								pos = i;
								return false;
							}
						});
					}
					else {
						return;
					}
					if (pos >= 0) {
						var thecol = $("tr.ui-wizgrid-labels th:eq(" + pos + ")", $t.grid.hDiv);
						if (nData) {
							// 밑에 3줄 원본 
							/*var ico = $(".s-ico", thecol);
							$("[id^=wizgh_]", thecol).empty().html(nData).append(ico);
							$t.p.Names[pos] = nData;*/


							/* 수정 처리 pjy 인덱스 값이 안맞음  */
							var gridID=  $t.p.id;
							$("#wizgh_"+gridID+"_"+colname).text(nData) ;//wizgh_grid1_AMT_B_04
							// end
						}
						if (prop) {
							if (typeof prop === 'string') {
								$(thecol).addClass(prop);
							}
							else {
								$(thecol).css(prop);
							}
						}
						if (typeof attrp === 'object') {
							$(thecol).attr(attrp);
						}
					}
				});
			}
			, setCell: function(rowid, colname, nData, cssp, attrp, forceupd) {
				return this.each(function() {
					var $t = this
						, pos = -1
						, v, title;
					var rowindex = -1; // /--- kjs ??? ?κ?
					if (!$t.grid) {
						return;
					}
					if (isNaN(colname)) {
						$($t.p.Columns).each(function(i) {
							if (this.name === colname) {
								pos = i;
								return false;
							}
						});
					}
					else {
						pos = parseInt(colname, 10);
					}
					// --------------------------------------
					// kjs ???
					// if(this.rows.length> 0)
					// {
					// for(var i=0;i<this.rows.length;i++)
					// {
					// if(this.rows[i].id == rowid)
					// {
					// rowindex = i;
					// break;
					// }
					// }
					// }
					// alert("bbbb setCell:" + rowindex);
					// --------------------------------------
					// kjs end
					if (pos >= 0) {
						var ind = $($t).wizGrid('getGridRowById', rowid);
						if (ind) {
							var tcell = $("td:eq(" + pos + ")", ind)
								, cl = 0
								, rawdat = [];
							if (nData !== "" || forceupd === true) {
								if (ind.cells !== undefined) {
									while (cl < ind.cells.length) {
										// slow down speed
										rawdat.push(ind.cells[cl].innerHTML);
										cl++;
									}
								}
								v = $t.formatter(rowid, nData, pos, rawdat, 'edit');
								title = $t.p.Columns[pos].title ? {
									"title": $.wizgrid.stripHtml(v)
								} : {};
								if ($t.p.treeGrid && $(".tree-wrap", $(tcell)).length > 0) {
									$("span", $(tcell)).html(v).attr(title);
								}
								else {
									$(tcell).html(v).attr(title);
									$("#" + rowindex + "_" + colname, $t.rows[rowindex]).val(v);
								}
								if ($t.p.datatype === "local") {
									var cm = $t.p.Columns[pos]
										, index;
									nData = cm.formatter && typeof cm.formatter === 'string' && cm.formatter === 'date' ? $.unformat
										.date.call($t, nData, cm) : nData;
									index = $t.p._index[$.wizgrid.stripPref($t.p.idPrefix, rowid)];
									if (index !== undefined) {
										$t.p.data[index][cm.name] = nData;
									}
								}
							}
							if (typeof cssp === 'string') {
								$(tcell).addClass(cssp);
							}
							else if (cssp) {
								$(tcell).css(cssp);
							}
							if (typeof attrp === 'object') {
								$(tcell).attr(attrp);
							}
						}
					}
				});
			}
			, getCell: function(rowid, col) {
				var ret = false;
				this.each(function() {
					var $t = this
						, pos = -1;
					if (!$t.grid) {
						return;
					}
					if (isNaN(col)) {
						$($t.p.Columns).each(function(i) {
							if (this.name === col) {
								pos = i;
								return false;
							}
						});
					}
					else {
						pos = parseInt(col, 10);
					}
					if (pos >= 0) {
						var ind = $($t).wizGrid('getGridRowById', rowid);
						if (ind) {
							try {
								ret = $.unformat.call($t, $("td:eq(" + pos + ")", ind), {
									rowId: ind.id
									, Columns: $t.p.Columns[pos]
								}, pos);
							}
							catch (e) {
								ret = $.wizgrid.htmlDecode($("td:eq(" + pos + ")", ind).html());
							}
						}
					}
				});
				return ret;
			}
			, getCol: function(col, obj, mathopr) {
				var ret = []
					, val, sum = 0
					, min, max, v;
				obj = typeof obj !== 'boolean' ? false : obj;
				if (mathopr === undefined) {
					mathopr = false;
				}
				this.each(function() {
					var $t = this
						, pos = -1;
					if (!$t.grid) {
						return;
					}
					if (isNaN(col)) {
						$($t.p.Columns).each(function(i) {
							if (this.name === col) {
								pos = i;
								return false;
							}
						});
					}
					else {
						pos = parseInt(col, 10);
					}
					if (pos >= 0) {
						var ln = $t.rows.length
							, i = 0
							, dlen = 0;
						if (ln && ln > 0) {
							while (i < ln) {
								if ($($t.rows[i]).hasClass('wizgrow')) {
									try {
										val = $.unformat.call($t, $($t.rows[i].cells[pos]), {
											rowId: $t.rows[i].id
											, Columns: $t.p.Columns[pos]
										}, pos);
									}
									catch (e) {
										val = $.wizgrid.htmlDecode($t.rows[i].cells[pos].innerHTML);
									}
									if (mathopr) {
										v = parseFloat(val);
										if (!isNaN(v)) {
											sum += v;
											if (max === undefined) {
												max = min = v;
											}
											min = Math.min(min, v);
											max = Math.max(max, v);
											dlen++;
										}
									}
									else if (obj) {
										ret.push({
											id: $t.rows[i].id
											, value: val
										});
									}
									else {
										ret.push(val);
									}
								}
								i++;
							}
							if (mathopr) {
								switch (mathopr.toLowerCase()) {
									case 'sum':
										ret = sum;
										break;
									case 'avg':
										ret = sum / dlen;
										break;
									case 'count':
										ret = (ln - 1);
										break;
									case 'min':
										ret = min;
										break;
									case 'max':
										ret = max;
										break;
								}
							}
						}
					}
				});
				return ret;
			}
			, clearGridData: function(clearfooter) {
				return this.each(function() {
					var $t = this;
					if (!$t.grid) {
						return;
					}
					if (typeof clearfooter !== 'boolean') {
						clearfooter = false;
					}
					if ($t.p.deepempty) {
						$("#" + $.wizgrid.wizID($t.p.id) + " tbody:first tr:gt(0)").remove();
					}
					else {
						var trf = $("#" + $.wizgrid.wizID($t.p.id) + " tbody:first tr:first")[0];
						$("#" + $.wizgrid.wizID($t.p.id) + " tbody:first").empty().append(trf);
					}
					if ($t.p.footerrow && clearfooter) {
						$(".ui-wizgrid-ftable td", $t.grid.sDiv).html("&#160;");
					}
					$t.p.selrow = null;
					$t.p.selarrrow = [];
					$t.p.savedRow = [];
					$t.p.records = 0;
					$t.p.page = 1;
					$t.p.lastpage = 0;
					$t.p.reccount = 0;
					$t.p.data = [];
					$t.p._index = {};
					$t.updatepager(true, false);
				});
			}
			, getInd: function(rowid, rc) {
				var ret = false
					, rw;
				this.each(function() {
					rw = $(this).wizGrid('getGridRowById', rowid);
					if (rw) {
						ret = rc === true ? rw : rw.rowIndex;
					}
				});
				return ret;
			}
			, bindKeys: function(settings) {
				var o = $.extend({
					onEnter: null
					, onSpace: null
					, onLeftKey: null
					, onRightKey: null
					, scrollingRows: true
				}, settings || {});
				return this.each(function() {
					var $t = this;
					if (!$('body').is('[role]')) {
						$('body').attr('role', 'application');
					}
					$t.p.scrollrows = o.scrollingRows;
					$($t).keydown(function(event) {
						var target = $($t).find('tr[tabindex=0]')[0]
							, id, r, mind, expanded = $t.p.treeReader.expanded_field;
						// check for arrow
						// keys
						if (target) {
							mind = $t.p._index[$.wizgrid.stripPref($t.p.idPrefix, target.id)];
							if (event.keyCode === 37 || event.keyCode === 38 || event.keyCode === 39 || event.keyCode ===
								40) {
								// up key
								if (event.keyCode === 38) {
									r = target.previousSibling;
									id = "";
									if (r) {
										if ($(r).is(":hidden")) {
											while (r) {
												r = r.previousSibling;
												if (!$(r).is(":hidden") && $(r).hasClass('wizgrow')) {
													id = r.id;
													break;
												}
											}
										}
										else {
											id = r.id;
										}
									}
									$($t).wizGrid('setSelection', id, true, event);
									event.preventDefault();
								}
								// if key is
								// down
								// arrow
								if (event.keyCode === 40) {
									r = target.nextSibling;
									id = "";
									if (r) {
										if ($(r).is(":hidden")) {
											while (r) {
												r = r.nextSibling;
												if (!$(r).is(":hidden") && $(r).hasClass('wizgrow')) {
													id = r.id;
													break;
												}
											}
										}
										else {
											id = r.id;
										}
									}
									$($t).wizGrid('setSelection', id, true, event);
									event.preventDefault();
								}
								// left
								if (event.keyCode === 37) {
									if ($t.p.treeGrid && $t.p.data[mind][expanded]) {
										$(target).find("div.treeclick").trigger('click');
									}
									$($t).triggerHandler("wizGridKeyLeft", [$t.p.selrow]);
									if ($.isFunction(o.onLeftKey)) {
										o.onLeftKey.call($t, $t.p.selrow);
									}
								}
								// right
								if (event.keyCode === 39) {
									if ($t.p.treeGrid && !$t.p.data[mind][expanded]) {
										$(target).find("div.treeclick").trigger('click');
									}
									$($t).triggerHandler("wizGridKeyRight", [$t.p.selrow]);
									if ($.isFunction(o.onRightKey)) {
										o.onRightKey.call($t, $t.p.selrow);
									}
								}
							}
							// check if
							// enter was
							// pressed on a
							// grid or
							// treegrid node
							else if (event.keyCode === 13) {
								$($t).triggerHandler("wizGridKeyEnter", [$t.p.selrow]);
								if ($.isFunction(o.onEnter)) {
									o.onEnter.call($t, $t.p.selrow);
								}
							}
							else if (event.keyCode === 32) {
								$($t).triggerHandler("wizGridKeySpace", [$t.p.selrow]);
								if ($.isFunction(o.onSpace)) {
									o.onSpace.call($t, $t.p.selrow);
								}
							}
						}
					});
				});
			}
			, unbindKeys: function() {
				return this.each(function() {
					$(this).unbind('keydown');
				});
			}
			, getLocalRow: function(rowid) {
				var ret = false
					, ind;
				this.each(function() {
					if (rowid !== undefined) {
						ind = this.p._index[$.wizgrid.stripPref(this.p.idPrefix, rowid)];
						if (ind >= 0) {
							ret = this.p.data[ind];
						}
					}
				});
				return ret;
			}
			, progressBar: function(p) {
				p = $.extend({
					htmlcontent: ""
					, method: "hide"
					, loadtype: "disable"
				}, p || {});
				return this.each(function() {
					var sh = p.method === "show" ? true : false;
					if (p.htmlcontent !== "") {
						$("#load_" + $.wizgrid.wizID(this.p.id)).html(p.htmlcontent);
					}
					switch (p.loadtype) {
						case "disable":
							break;
						case "enable":
							$("#load_" + $.wizgrid.wizID(this.p.id)).toggle(sh);
							break;
						case "block":
							$("#lui_" + $.wizgrid.wizID(this.p.id)).toggle(sh);
							$("#load_" + $.wizgrid.wizID(this.p.id)).toggle(sh);
							break;
					}
				});
			}
			, getColProp: function(colname) {
				var ret = {}
					, $t = this[0];
				if (!$t.grid) {
					return false;
				}
				var cM = $t.p.Columns
					, i;
				for (i = 0; i < cM.length; i++) {
					if (cM[i].name === colname) {
						ret = cM[i];
						break;
					}
				}
				return ret;
			}
			, setColProp: function(colname, obj) {
				// do not set width will not work
				return this.each(function() {
					if (this.grid) {
						if (obj) {
							var cM = this.p.Columns
								, i;
							for (i = 0; i < cM.length; i++) {
								if (cM[i].name === colname) {
									// alert(this.p.Columns[i],obj);
									$.extend(true, this.p.Columns[i], obj);
									break;
								}
							}
						}
					}
				});
			}
			, sortGrid: function(colname, reload, sor) {
				return this.each(function() {
					var $t = this
						, idx = -1
						, i, sobj = false;
					if (!$t.grid) {
						return;
					}
					if (!colname) {
						colname = $t.p.sortname;
					}
					for (i = 0; i < $t.p.Columns.length; i++) {
						if ($t.p.Columns[i].index === colname || $t.p.Columns[i].name === colname) {
							idx = i;
							if ($t.p.frozenColumns === true && $t.p.Columns[i].frozen === true) {
								sobj = $t.grid.fhDiv.find("#" + $t.p.id + "_" + colname);
							}
							break;
						}
					}
					if (idx !== -1) {
						var sort = $t.p.Columns[idx].sortable;
						if (!sobj) {
							sobj = $t.grid.headers[idx].el;
						}
						if (typeof sort !== 'boolean') {
							sort = true;
						}
						if (typeof reload !== 'boolean') {
							reload = false;
						}
						if (sort) {
							$t.sortData("wizgh_" + $t.p.id + "_" + colname, idx, reload, sor, sobj);
						}
					}
				});
			}
			, setGridState: function(state) {
				return this.each(function() {
					if (!this.grid) {
						return;
					}
					var $t = this;
					if (state === 'hidden') {
						$(".ui-wizgrid-bdiv, .ui-wizgrid-hdiv", "#gview_" + $.wizgrid.wizID($t.p.id)).slideUp("fast");
						if ($t.p.pager) {
							$($t.p.pager).slideUp("fast");
						}
						if ($t.p.toppager) {
							$($t.p.toppager).slideUp("fast");
						}
						if ($t.p.toolbar[0] === true) {
							if ($t.p.toolbar[1] === 'both') {
								$($t.grid.ubDiv).slideUp("fast");
							}
							$($t.grid.uDiv).slideUp("fast");
						}
						if ($t.p.footerrow) {
							$(".ui-wizgrid-sdiv", "#gbox_" + $.wizgrid.wizID($t.p.id)).slideUp("fast");
						}
						$(".ui-wizgrid-titlebar-close span", $t.grid.cDiv).removeClass("ui-icon-circle-triangle-n").addClass(
							"ui-icon-circle-triangle-s");
						$t.p.gridstate = 'hidden';
					}
					else if (state === 'visible') {
						$(".ui-wizgrid-hdiv, .ui-wizgrid-bdiv", "#gview_" + $.wizgrid.wizID($t.p.id)).slideDown("fast");
						if ($t.p.pager) {
							$($t.p.pager).slideDown("fast");
						}
						if ($t.p.toppager) {
							$($t.p.toppager).slideDown("fast");
						}
						if ($t.p.toolbar[0] === true) {
							if ($t.p.toolbar[1] === 'both') {
								$($t.grid.ubDiv).slideDown("fast");
							}
							$($t.grid.uDiv).slideDown("fast");
						}
						if ($t.p.footerrow) {
							$(".ui-wizgrid-sdiv", "#gbox_" + $.wizgrid.wizID($t.p.id)).slideDown("fast");
						}
						$(".ui-wizgrid-titlebar-close span", $t.grid.cDiv).removeClass("ui-icon-circle-triangle-s").addClass(
							"ui-icon-circle-triangle-n");
						$t.p.gridstate = 'visible';
					}
				});
			}
			, setFrozen: function() {
				return this.each(function() {
					if (!this.grid) {
						return;
					}
					var $t = this
						, cm = $t.p.Columns
						, i = 0
						, len = cm.length
						, maxfrozen = -1
						, frozen = false;
					// TODO treeGrid and grouping Support
					if ($t.p.subGrid === true || $t.p.treeGrid === true || $t.p.cellEdit === true || $t.p.sortable ||
						$t.p.scroll) {
						return;
					}
					if ($t.p.rownumbers) {
						i++;
					}
					if ($t.p.multiselect) {
						i++;
					}
					// get the max index of frozen col
					while (i < len) {
						// from left, no breaking frozen
						if (cm[i].frozen === true) {
							frozen = true;
							maxfrozen = i;
						}
						else {
							break;
						}
						i++;
					}
					if (maxfrozen >= 0 && frozen) {
						var top = $t.p.caption ? $($t.grid.cDiv).outerHeight() : 0
							, hth = $(".ui-wizgrid-htable", "#gview_" + $.wizgrid.wizID($t.p.id)).height();// 원본
						//, hth = $("[colheight=grid1]").css('height').replace("px","");;
						if(hth == 0)hth = $("[colheight=grid1]").css('height').replace("px","");// hth 값이 0이면 헤더 높이값을 따로 가져온다.
						
						// headers
						if ($t.p.toppager) {
							top = top + $($t.grid.topDiv).outerHeight();
						}
						if ($t.p.toolbar[0] === true) {
							if ($t.p.toolbar[1] !== "bottom") {
								top = top + $($t.grid.uDiv).outerHeight();
							}
						}
						//alert(top + " @@ " + hth);
						$t.grid.fhDiv = $('<div style="position:absolute;left:0px;top:' + top + 'px;height:' + hth +
							'px;" class="frozen-div ui-state-default-grid ui-wizgrid-hdiv"></div>');
						$t.grid.fbDiv = $('<div style="position:absolute;left:0px;top:' + (parseInt(top, 10) + parseInt(
							hth, 10) + 1) + 'px;overflow-y:hidden" class="frozen-bdiv ui-wizgrid-bdiv"></div>');
						$("#gview_" + $.wizgrid.wizID($t.p.id)).append($t.grid.fhDiv);
						var htbl = $(".ui-wizgrid-htable", "#gview_" + $.wizgrid.wizID($t.p.id)).clone(true);
						// groupheader support - only if
						// useColSpanstyle is false
						
						if ($t.p.groupHeader) {
							$("tr.wizg-first-row-header, tr.wizg-third-row-header", htbl).each(function() {
								$("th:gt(" + maxfrozen + ")", this).remove();
							});
							var swapfroz = -1
								, fdel = -1
								, cs, rs;
							$("tr.wizg-second-row-header th", htbl).each(function() {
								cs = parseInt($(this).attr("colspan"), 10);
								rs = parseInt($(this).attr("rowspan"), 10);
								if (rs) {
									swapfroz++;
									fdel++;
								}
								if (cs) {
									swapfroz = swapfroz + cs;
									fdel++;
								}
								if (swapfroz === maxfrozen) {
									return false;
								}
							});
							if (swapfroz !== maxfrozen) {
								fdel = maxfrozen;
							}
							$("tr.wizg-second-row-header", htbl).each(function() {
								$("th:gt(" + fdel + ")", this).remove();
							});
						}
						else {
							$("tr", htbl).each(function() {
								$("th:gt(" + maxfrozen + ")", this).remove();
							});
						}
						$(htbl).width(1);
						$(htbl).children().children().height(hth); // 박준용 틀고정 그리드 헤더높이에따라 레이아웃 꺠짐 현상으로 인행 높이 사이즈 맞추는 코드 넣음
						//$(htbl).children().children().css('height',hth+"px"); 
						// resizing stuff
						$($t.grid.fhDiv).append(htbl).mousemove(function(e) {
							if ($t.grid.resizing) {
								$t.grid.dragMove(e);
								return false;
							}
						});
						if ($t.p.footerrow) {
							var hbd = $(".ui-wizgrid-bdiv", "#gview_" + $.wizgrid.wizID($t.p.id)).height();
							$t.grid.fsDiv = $('<div style="position:absolute;left:0px;top:' + (parseInt(top, 10) + parseInt(
								hth, 10) + parseInt(hbd, 10) + 1) + 'px;" class="frozen-sdiv ui-wizgrid-sdiv"></div>');
							$("#gview_" + $.wizgrid.wizID($t.p.id)).append($t.grid.fsDiv);
							var ftbl = $(".ui-wizgrid-ftable", "#gview_" + $.wizgrid.wizID($t.p.id)).clone(true);
							$("tr", ftbl).each(function() {
								$("td:gt(" + maxfrozen + ")", this).remove();
							});
							$(ftbl).width(1);
							$($t.grid.fsDiv).append(ftbl);
						}
						$($t).bind('wizGridResizeStop.setFrozen', function(e, w, index) {
							var rhth = $(".ui-wizgrid-htable", $t.grid.fhDiv);
							$("th:eq(" + index + ")", rhth).width(w);
							var btd = $(".ui-wizgrid-btable", $t.grid.fbDiv);
							$("tr:first td:eq(" + index + ")", btd).width(w);
							if ($t.p.footerrow) {
								var ftd = $(".ui-wizgrid-ftable", $t.grid.fsDiv);
								$("tr:first td:eq(" + index + ")", ftd).width(w);
							}
						});
						// sorting stuff
						$($t).bind('wizGridSortCol.setFrozen', function(e, index, idxcol) {
							var previousSelectedTh = $("tr.ui-wizgrid-labels:last th:eq(" + $t.p.lastsort + ")", $t.grid.fhDiv)
								, newSelectedTh = $("tr.ui-wizgrid-labels:last th:eq(" + idxcol + ")", $t.grid.fhDiv);
							$("span.ui-grid-ico-sort", previousSelectedTh).addClass('ui-state-disabled');
							$(previousSelectedTh).attr("aria-selected", "false");
							$("span.ui-icon-" + $t.p.sortorder, newSelectedTh).removeClass('ui-state-disabled');
							$(newSelectedTh).attr("aria-selected", "true");
							if (!$t.p.viewsortcols[0]) {
								if ($t.p.lastsort !== idxcol) {
									$("span.s-ico", previousSelectedTh).hide();
									$("span.s-ico", newSelectedTh).show();
								}
							}
						});
						// data stuff
						// TODO support for setRowData
						$("#gview_" + $.wizgrid.wizID($t.p.id)).append($t.grid.fbDiv);
						$($t.grid.bDiv).scroll(function() {
							$($t.grid.fbDiv).scrollTop($(this).scrollTop());
						});
						if ($t.p.hoverrows === true) {
							$("#" + $.wizgrid.wizID($t.p.id)).unbind('mouseover').unbind('mouseout');
						}
						$($t).bind('wizGridAfterGridComplete.setFrozen', function() {
							$("#" + $.wizgrid.wizID($t.p.id) + "_frozen").remove();
							$($t.grid.fbDiv).height($($t.grid.bDiv).height() - 16);
							var btbl = $("#" + $.wizgrid.wizID($t.p.id)).clone(true);
							$("tr[role=row]", btbl).each(function() {
								$("td[role=gridcell]:gt(" + maxfrozen + ")", this).remove();
							});
							$(btbl).width(1).attr("id", $t.p.id + "_frozen");
							$($t.grid.fbDiv).append(btbl);
							if ($t.p.hoverrows === true) {
								$("tr.wizgrow", btbl).hover(function() {
									$(this).addClass("ui-state-hover-grid");
									$("#" + $.wizgrid.wizID(this.id), "#" + $.wizgrid.wizID($t.p.id)).addClass(
										"ui-state-hover-grid");
								}, function() {
									$(this).removeClass("ui-state-hover-grid");
									$("#" + $.wizgrid.wizID(this.id), "#" + $.wizgrid.wizID($t.p.id)).removeClass(
										"ui-state-hover-grid");
								});
								$("tr.wizgrow", "#" + $.wizgrid.wizID($t.p.id)).hover(function() {
									$(this).addClass("ui-state-hover-grid");
									$("#" + $.wizgrid.wizID(this.id), "#" + $.wizgrid.wizID($t.p.id) + "_frozen").addClass(
										"ui-state-hover-grid");
								}, function() {
									$(this).removeClass("ui-state-hover-grid");
									$("#" + $.wizgrid.wizID(this.id), "#" + $.wizgrid.wizID($t.p.id) + "_frozen").removeClass(
										"ui-state-hover-grid");
								});
							}
							btbl = null;
						});
						if (!$t.grid.hDiv.loading) {
							$($t).triggerHandler("wizGridAfterGridComplete");
						}
						$t.p.frozenColumns = true;
					}
				});
			}
			, destroyFrozenColumns: function() {
				return this.each(function() {
					if (!this.grid) {
						return;
					}
					if (this.p.frozenColumns === true) {
						var $t = this;
						$($t.grid.fhDiv).remove();
						$($t.grid.fbDiv).remove();
						$t.grid.fhDiv = null;
						$t.grid.fbDiv = null;
						if ($t.p.footerrow) {
							$($t.grid.fsDiv).remove();
							$t.grid.fsDiv = null;
						}
						$(this).unbind('.setFrozen');
						if ($t.p.hoverrows === true) {
							var ptr;
							$("#" + $.wizgrid.wizID($t.p.id)).bind('mouseover', function(e) {
								ptr = $(e.target).closest("tr.wizgrow");
								if ($(ptr).attr("class") !== "ui-subgrid") {
									$(ptr).addClass("ui-state-hover-grid");
								}
							}).bind('mouseout', function(e) {
								ptr = $(e.target).closest("tr.wizgrow");
								$(ptr).removeClass("ui-state-hover-grid");
							});
						}
						this.p.frozenColumns = false;
					}
				});
			}
		});
		// module begin
		$.wizgrid.extend({
			editCell: function(iRow, iCol, ed) {
				$(this).setBindArrowKeyRow(iRow); // // kjs ???
				// bind o????
				return this.each(function() {
					var $t = this
						, nm, tmp, cc, cm;
					if (!$t.grid || $t.p.cellEdit !== true) {
						return;
					}
					iCol = parseInt(iCol, 10);
					// select the row that can be used for
					// other methods
					$t.p.selrow = $t.rows[iRow].id;
					if (!$t.p.knv) {
						$($t).wizGrid("GridNav");
					}
					// check to see if we have already
					// edited cell
					if ($t.p.savedRow.length > 0) {
						// prevent second click on that
						// field and enable selects
						if (ed === true) {
							if (iRow == $t.p.iRow && iCol == $t.p.iCol) {
								return;
							}
						}
						// save the cell
						$($t).wizGrid("saveCell", $t.p.savedRow[0].id, $t.p.savedRow[0].ic);
					}
					else {
						window.setTimeout(function() {
							$("#" + $.wizgrid.wizID($t.p.knv)).attr("tabindex", "-1").focus();
						}, 1);
					}
					cm = $t.p.Columns[iCol];
					nm = cm.name;
					if (nm === 'subgrid' || nm === 'cb' || nm === 'rn') {
						return;
					}
					cc = $("td:eq(" + iCol + ")", $t.rows[iRow]);
					// pjy editable cell
					// cell readonly 넣기
					var cellrow;
					var cellcol;
					//ed 수정가능한 상태
					if( ed === true || cm.editable ==false){
						
					
						$.each(CellEditoption["Cell"], function(key, value){
							if(key =="rowIndex"){
								cellrow = value;
							}
							if(key =="colIndex"){
								cellcol = value;
							}
							//alert('key:' + key + ' / ' + 'value:' + value);
						});
						if(iRow == cellrow && iCol== cellcol){
							
							cm.editable = true;
							
						}
						
					}else{
						 // 수정한 가능한 flag 아니면 값 초기화 시킴
						//CellEditoption = {};
					}
					// 끝
					if (cm.editable === true && ed === true && !cc.hasClass("not-editable-cell") && (!$.isFunction($t.p
							.isCellEditable) || $t.p.isCellEditable.call($t, nm, iRow, iCol))) {
						// editable json초기화
						CellEditoption = {};
						if (iRow == cellrow && iCol == cellcol) {
							cm.editable = false;
						}
						else {}
						// 초기화 끝
						// alert("1:"+$(cc).html());
						if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
							$("td:eq(" + $t.p.iCol + ")", $t.rows[$t.p.iRow]).removeClass(
								"edit-cell ui-state-highlight-grid");
							$($t.rows[$t.p.iRow]).removeClass("selected-row ui-state-hover-grid");
						}
						$(cc).addClass("edit-cell ui-state-highlight-grid");
						$($t.rows[iRow]).addClass("selected-row ui-state-hover-grid");
						try {
							tmp = $.unformat.call($t, cc, {
								rowId: $t.rows[iRow].id
								, Columns: cm
							}, iCol);
						}
						catch (_) {
							tmp = (cm.edittype && cm.edittype === 'textarea') ? $(cc).text() : $(cc).html();
						}
						if ($t.p.autoencode) {
							tmp = $.wizgrid.htmlDecode(tmp);
						}
						if (!cm.edittype) {
							cm.edittype = "text";
						}
						$t.p.savedRow.push({
							id: iRow
							, ic: iCol
							, name: nm
							, v: tmp
						}); // !@#
						if (tmp === "&nbsp;" || tmp === "&#160;" || (tmp.length === 1 && tmp.charCodeAt(0) === 160)) {
							tmp = '';
						}
						if ($.isFunction($t.p.formatCell)) {
							var tmp2 = $t.p.formatCell.call($t, $t.rows[iRow].id, nm, tmp, iRow, iCol);
							if (tmp2 !== undefined) {
								tmp = tmp2;
							}
						}
						
						$($t).triggerHandler("wizGridBeforeEditCell", [$t.rows[iRow].id, nm
							, tmp, iRow, iCol
						]);
						if ($.isFunction($t.p.OnBeforeEditCell)) {
							$t.p.OnBeforeEditCell.call($t, $t.rows[iRow].id, nm, tmp, iRow, iCol);
						}
						// Celloption ={};
						// alert("42:"+$(cc).html());
						var opt = $.extend({}, cm.editoptions || {}, {
							id: iRow + "_" + nm
							, name: nm
							, rowId: $t.rows[iRow].id
							, oper: 'edit'
						});
						var elc = $.wizgrid.createEl.call($t, cm.align, cm.edittype, opt, tmp, true, $.extend({}, $.wizgrid
							.ajaxOptions, $t.p.ajaxSelectOptions, cm.align || {})); // pjy
						
						// ,cm.align
						// 추가
						// alert("43:"+$(cc).html()
						// +":"+elc);
						$(cc).html("").append(elc).attr("tabindex", "0");
						// alert("44:"+$(cc).html());
						$.wizgrid.bindEv.call($t, elc, opt);
						// alert("45:"+$(cc).html());
						window.setTimeout(function () { 
							$(elc).focus().select();
							//달력 cell 은 제외 처리
							if(!$(elc).hasClass('hasDatepicker')){
									//포커스 제거시 셀 자동 닫기
							 $(elc).bind('blur',function(){
							    $($t).wizGrid("editCell", iRow, iCol, false);
						         });
								
							  }
							},10);
						
						// alert("56:"+$(cc).html());
						$("input, select, textarea", cc).bind("keydown", function(e) {
							if (e.keyCode === 27) {
								if ($("input.hasDatepicker", cc).length > 0) {
									if ($(".ui-datepicker").is(":hidden")) {
										$($t).wizGrid("restoreCell", iRow, iCol);
									}
									else {
										$("input.hasDatepicker", cc).datepicker('hide');
									}
								}
								else {
									$($t).wizGrid("restoreCell", iRow, iCol);
								}
							} // ESC
							if (e.keyCode === 13 && !e.shiftKey) {
								if (!e.ctrlKey) { // pjy
									// ???
									$($t).wizGrid("saveCell", iRow, iCol);
									// Prevent
									// default
									// action
									return false;
								}
							} // Enter
							if (e.keyCode === 9) {
								if (!$t.grid.hDiv.loading) {
									if (e.shiftKey) {
										$($t).wizGrid("prevCell", iRow, iCol);
									} // Shift
									// TAb
									else {
										$($t).wizGrid("nextCell", iRow, iCol);
									} // Tab
								}
								else {
									return false;
								}
							}
							e.stopPropagation();
						});
						$($t).triggerHandler("wizGridAfterEditCell", [$t.rows[iRow].id, nm, tmp, iRow, iCol
						]);
						if ($.isFunction($t.p.OnAfterEditCell)) {
							var oldvalue = this.wizGrid("getGridParam", "oldvalue");
							this.wizGrid("setGridParam", {
								oldvalue: tmp
							}, true);
							//	alert(tmp);
							//oldvalue = tmp;
							$t.p.OnAfterEditCell.call($t, $t.rows[iRow].id, nm, tmp, iRow, iCol);
						}
					}
					else {
						if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
							$("td:eq(" + $t.p.iCol + ")", $t.rows[$t.p.iRow]).removeClass(
								"edit-cell ui-state-highlight-grid");
							$($t.rows[$t.p.iRow]).removeClass("selected-row ui-state-hover-grid");
						}
						cc.addClass("edit-cell ui-state-highlight-grid");
						$($t.rows[iRow]).addClass("selected-row ui-state-hover-grid");
						tmp = cc.html().replace(/\&#160\;/ig, '');
						$($t).triggerHandler("wizGridSelectCell", [$t.rows[iRow].id, nm
							, tmp, iRow, iCol
						]);
						if ($.isFunction($t.p.OnSelectCell)) {
							$t.p.OnSelectCell.call($t, $t.rows[iRow].id, nm, tmp, iRow, iCol);
						}
					}
					$t.p.iCol = iCol;
					$t.p.iRow = iRow;
					// alert("33:"+$(cc).html());
				});
			}
			, closeRow: function(iRow) // --- kjs ??? (editcell
				// ?????????)
				{
					var $wizgrid = this;
					return this.each(function() {
						var $t = this
							, nm, tmp, cc, cm, v;
						if (!$t.grid || $t.p.cellEdit !== true) {
							return;
						}
						for (var i = 0; i < $t.p.Columns.length; i++) {
							cm = $t.p.Columns[i];
							if (cm.editable) {
								nm = cm.name;
								if (nm === 'subgrid' || nm === 'cb' || nm === 'rn') {
									return;
								}
								cc = $("td:eq(" + i + ")", $t.rows[iRow]);
								$($t.rows[iRow]).addClass("selected-row ui-state-hover-grid");
								v = $("#" + iRow + "_" + nm, $t.rows[iRow]).val();
								cc.html(v);
							}
						}
					});
				}
			, saveCell: function(iRow, iCol) {
				return this.each(function() {
					var $t = this
						, fr, errors = $.wizgrid.getRegional(this, 'errors')
						, edit = $.wizgrid.getRegional(this, 'edit');
					if (!$t.grid || $t.p.cellEdit !== true) {
						return;
					}
					if ($t.p.savedRow.length >= 1) {
						fr = 0;
					}
					else {
						fr = null;
					}
					if (fr !== null) {
						var cc = $("td:eq(" + iCol + ")", $t.rows[iRow])
							, v, v2, cm = $t.p.Columns[iCol]
							, nm = cm.name
							, nmwiz = $.wizgrid.wizID(nm);
						switch (cm.edittype) {
							case "select":
								if (!cm.editoptions.multiple) {
									v = $("#" + iRow + "_" + nmwiz + " option:selected", $t.rows[iRow]).val();
									v2 = $("#" + iRow + "_" + nmwiz + " option:selected", $t.rows[iRow]).text();
								}
								else {
									var sel = $("#" + iRow + "_" + nmwiz, $t.rows[iRow])
										, selectedText = [];
									v = $(sel).val();
									if (v) {
										v.join(",");
									}
									else {
										v = "";
									}
									$("option:selected", sel).each(function(i, selected) {
										selectedText[i] = $(selected).text();
									});
									v2 = selectedText.join(",");
								}
								if (cm.formatter) {
									v2 = v;
								}
								break;
							case "checkbox":
								var cbv = ["Yes", "No"];
								if (cm.editoptions) {
									cbv = cm.editoptions.value.split(":");
								}
								v = $("#" + iRow + "_" + nmwiz, $t.rows[iRow]).is(":checked") ? cbv[0] : cbv[1];
								v2 = v;
								break;
							case "password":
							case "text":
							case "textarea":
							case "button":
								v = $("#" + iRow + "_" + nmwiz, $t.rows[iRow]).val();
								// alert("#"+iRow+"_"+nmwiz
								// +$t.rows[iRow]);
								// ------------------------------
								// kjs
								if (v.length > 0) {
									if (v.substring(v.length - 1) == '\t') {
										v = v.substring(0, v.length - 1);
									}
								}
								// -----------------------------------------------------------------
								v2 = v;
								if (cm.Gtype != undefined && cm.Gtype == "number") {
									var roundyn = true;
									if (cm.GRound != undefined && cm.GRound == "false") {
										roundyn = false;
									}
									v2 = wizutil.removeNumberFmt(v2, cm.GFormat);
									v2 = wizutil.getFormatNumber(v2, cm.GFormat, roundyn);
								}
								// alert("aaa:savecell:" + v2
								// +":" + cm.Gtype +":" +
								// cm.GFormat +":" + cm.GRound
								// +":" + nm +":"+ nmwiz +":" );
								// //+
								// this.getCell(this.getGridRow(),nm
								// )
								break;
							case 'custom':
								try {
									if (cm.editoptions && $.isFunction(cm.editoptions.custom_value)) {
										v = cm.editoptions.custom_value.call($t, $(".customelement", cc), 'get');
										if (v === undefined) {
											throw "e2";
										}
										else {
											v2 = v;
										}
									}
									else {
										throw "e1";
									}
								}
								catch (e) {
									if (e === "e1") {
										$.wizgrid.info_dialog(errors.errcap, "function 'custom_value' " + edit.msg.nodefined, edit.bClose);
									}
									if (e === "e2") {
										$.wizgrid.info_dialog(errors.errcap, "function 'custom_value' " + edit.msg.novalue, edit.bClose);
									}
									else {
										$.wizgrid.info_dialog(errors.errcap, e.message, edit.bClose);
									}
								}
								break;
						}
						
						// The common approach is if nothing
						// changed do not do anything
						if (v2 !== $t.p.savedRow[fr].v) {
							var vvv = $($t).triggerHandler("wizGridBeforeSaveCell", [
								$t.rows[iRow].id
								, nm, v
								, iRow
								, iCol
							]);
							if (vvv) {
								v = vvv;
								v2 = vvv;
							}
							if ($.isFunction($t.p.OnBeforeSaveCell)) {
								var vv = $t.p.OnBeforeSaveCell.call($t, $t.rows[iRow].id, nm, v, iRow, iCol);
								if (vv) {
									v = vv;
									v2 = vv;
								}
							}
							var cv = $.wizgrid.checkValues.call($t, v, iCol);
							if (cv[0] === true) {
								var addpost = $($t).triggerHandler("wizGridBeforeSubmitCell", [
									$t.rows[iRow].id
									, nm
									, v
									, iRow
									, iCol
								]) || {};
								if ($.isFunction($t.p.OnBeforeSubmitCell)) {
									addpost = $t.p.OnBeforeSubmitCell.call($t, $t.rows[iRow].id, nm, v, iRow, iCol);
									if (!addpost) {
										addpost = {};
									}
								}
								if ($("input.hasDatepicker", cc).length > 0) {
									$("input.hasDatepicker", cc).datepicker('hide');
								}
								if ($t.p.cellsubmit === 'remote') {
									if ($t.p.cellurl) {
										var postdata = {};
										if ($t.p.autoencode) {
											v = $.wizgrid.htmlEncode(v);
										}
										postdata[nm] = v;
										var idname, oper, opers;
										opers = $t.p.prmNames;
										idname = opers.id;
										oper = opers.oper;
										postdata[idname] = $.wizgrid.stripPref($t.p.idPrefix, $t.rows[iRow].id);
										postdata[oper] = opers.editoper;
										postdata = $.extend(addpost, postdata);
										$($t).wizGrid("progressBar", {
											method: "show"
											, loadtype: $t.p.loadui
											, htmlcontent: $.wizgrid.getRegional($t, 'defaults.savetext')
										});
										$t.grid.hDiv.loading = true;
										$.ajax($.extend({
											url: $t.p.cellurl
											, data: $.isFunction($t.p.serializeCellData) ? $t.p.serializeCellData.call($t, postdata) : postdata
											, type: "POST"
											, complete: function(result, stat) {
												$($t).wizGrid("progressBar", {
													method: "hide"
													, loadtype: $t.p.loadui
												});
												$t.grid.hDiv.loading = false;
												if (stat === 'success') {
													var ret = $($t).triggerHandler("wizGridAfterSubmitCell", [
														$t
														, result
														, postdata.id
														, nm
														, v
														, iRow
														, iCol
													]) || [
														true, ''
													];
													if (ret[0] === true && $.isFunction($t.p.OnAfterSubmitCell)) {
														ret = $t.p.OnAfterSubmitCell.call($t, result, postdata.id, nm, v, iRow, iCol);
													}
													if (ret[0] === true) {
														$(cc).empty();
														$($t).wizGrid("setCell", $t.rows[iRow].id, iCol, v2, false, false, true);
														$(cc).addClass("dirty-cell");
														$($t.rows[iRow]).addClass("edited");
														$($t).triggerHandler("wizGridAfterSaveCell", [
															$t.rows[iRow].id
															, nm
															, v
															, iRow
															, iCol
														]);
														if ($.isFunction($t.p.OnEditCell)) {
															$t.p.OnEditCell.call($t, $t.rows[iRow].id, nm, v, iCol, iRow); // --
															// kjs
															// ????
															// ??????????
															// ???
															// ??u
															// ??
															// ????
															// ???
															$($t).setBindRowIdObject($t.rows[iRow].id); // ---
															// kjs
															// add
															// bind
															// o??
														}
														$t.p.savedRow.splice(0, 1);
													}
													else {
														$.wizgrid.info_dialog(errors.errcap, ret[1], edit.bClose);
														$($t).wizGrid("restoreCell", iRow, iCol);
													}
												}
											}
											, error: function(res, stat, err) {
												$("#lui_" + $.wizgrid.wizID($t.p.id)).hide();
												$t.grid.hDiv.loading = false;
												$($t).triggerHandler("wizGridErrorCell", [
													res
													, stat
													, err
												]);
												if ($.isFunction($t.p.errorCell)) {
													$t.p.errorCell.call($t, res, stat, err);
													$($t).wizGrid("restoreCell", iRow, iCol);
												}
												else {
													$.wizgrid.info_dialog(errors.errcap, res.status + " : " + res.statusText + "<br/>" +
														stat, edit.bClose);
													$($t).wizGrid("restoreCell", iRow, iCol);
												}
											}
										}, $.wizgrid.ajaxOptions, $t.p.ajaxCellOptions || {}));
									}
									else {
										try {
											$.wizgrid.info_dialog(errors.errcap, errors.nourl, edit.bClose);
											$($t).wizGrid("restoreCell", iRow, iCol);
										}
										catch (e) {}
									}
								}
								
								// alert("saveCell:" +
								// $t.p.cellsubmit);
								//alert($(cc));
							
								
								if ($t.p.cellsubmit === 'clientArray') {
									if ($($t).checkConstraint(nm, $t.rows[iRow].id, v2) == false) // ---
									// kjs
									// add
									// contraint
									// o??
									// ???????
									{
										v2 = this.wizGrid("getGridParam", "oldvalue");
									}
									$(cc).empty();
									$($t).wizGrid("setCell", $t.rows[iRow].id, iCol, v2, false, false, true);
									$(cc).addClass("dirty-cell");
									$($t.rows[iRow]).addClass("edited");
									$($t).triggerHandler("wizGridAfterSaveCell", [
										$t.rows[iRow].id
										, nm
										, v
										, iRow
										, iCol
									]);
									// pjy start
									var strAt = $($t).wizGrid('getCell', $t.rows[iRow].id, "IUDFLAG");
									if ("I" != strAt && strAt !== false && "D" != strAt) {
										$($t).wizGrid('setCell', $t.rows[iRow].id, "IUDFLAG", "U");
									}
									// end
									if ($.isFunction($t.p.OnEditCell)) {
										// alert( $t + " @ "
										// +
										// $t.rows[iRow].id
										// + " @ " + nm + "
										// @ " + v + " @ " +
										// iCol + " @ " +
										// iRow );
										$t.p.OnEditCell.call($t, $t.rows[iRow].id, nm, v, iCol, iRow);
										var selrowx = $($t).wizGrid("getGridParam", 'selrow');
										$($t).setBindRowIdObject(selrowx); // ---
										// kjs
										// add
										// bind
										// o??
									}
									$t.p.savedRow.splice(0, 1);
								}
							}
							else {
								try {
									window.setTimeout(function() {
										$.wizgrid.info_dialog(errors.errcap, v + " " + cv[1], edit.bClose);
									}, 100);
									$($t).wizGrid("restoreCell", iRow, iCol);
								}
								catch (e) {}
							}
						}
						else {
							$($t).wizGrid("restoreCell", iRow, iCol);
						}
					}
					window.setTimeout(function() {
						$("#" + $.wizgrid.wizID($t.p.knv)).attr("tabindex", "-1").focus();
					}, 0);
				});
			}
			, restoreCell: function(iRow, iCol) {
				return this.each(function() {
					var $t = this
						, fr;
					if (!$t.grid || $t.p.cellEdit !== true) {
						return;
					}
					if ($t.p.savedRow.length >= 1) {
						fr = 0;
					}
					else {
						fr = null;
					}
					if (fr !== null) {
						var cc = $("td:eq(" + iCol + ")", $t.rows[iRow]);
						// datepicker fix
						if ($.isFunction($.fn.datepicker)) {
							try {
								$("input.hasDatepicker", cc).datepicker('hide');
							}
							catch (e) {}
						}
						$(cc).empty().attr("tabindex", "-1");
						$($t).wizGrid("setCell", $t.rows[iRow].id, iCol, $t.p.savedRow[fr].v, false, false, true);
						$($t).triggerHandler("wizGridAfterRestoreCell", [$t.rows[iRow].id
							, $t.p.savedRow[fr].v, iRow
							, iCol
						]);
						if ($.isFunction($t.p.OnAfterRestoreCell)) {
							$t.p.OnAfterRestoreCell.call($t, $t.rows[iRow].id, $t.p.savedRow[fr].v, iRow, iCol);
						}
						$t.p.savedRow.splice(0, 1);
					}
					window.setTimeout(function() {
						$("#" + $t.p.knv).attr("tabindex", "-1").focus();
					}, 0);
				});
			}
			, nextCell: function(iRow, iCol) {
				// alert("nextCell");
				return this.each(function() {
					var $t = this
						, nCol = false
						, i;
					if (!$t.grid || $t.p.cellEdit !== true) {
						return;
					}
					// try to find next editable cell
					for (i = iCol + 1; i < $t.p.Columns.length; i++) {
						if ($t.p.Columns[i].editable === true && (!$.isFunction($t.p.isCellEditable) || $t.p.isCellEditable
								.call($t, $t.p.Columns[i].name, iRow, i))) {
							nCol = i;
							break;
						}
					}
					if (nCol !== false) {
						$($t).wizGrid("editCell", iRow, nCol, true);
					}
					else {
						if ($t.p.savedRow.length > 0) {
							$($t).wizGrid("saveCell", iRow, iCol);
						}
					}
				});
			}
			, prevCell: function(iRow, iCol) {
				// alert("prevCell");
				return this.each(function() {
					var $t = this
						, nCol = false
						, i;
					if (!$t.grid || $t.p.cellEdit !== true) {
						return;
					}
					// try to find next editable cell
					for (i = iCol - 1; i >= 0; i--) {
						if ($t.p.Columns[i].editable === true && (!$.isFunction($t.p.isCellEditable) || $t.p.isCellEditable
								.call($t, $t.p.Columns[i].name, iRow, i))) {
							nCol = i;
							break;
						}
					}
					if (nCol !== false) {
						$($t).wizGrid("editCell", iRow, nCol, true);
					}
					else {
						if ($t.p.savedRow.length > 0) {
							$($t).wizGrid("saveCell", iRow, iCol);
						}
					}
				});
			}
			, GridNav: function() {
				return this.each(function() {
					var $t = this;
					if (!$t.grid || $t.p.cellEdit !== true) {
						return;
					}
					// trick to process keydown on non input
					// elements
					$t.p.knv = $t.p.id + "_kn";
					var selection = $(
							"<div style='position:fixed;top:0px;width:1px;height:1px;' tabindex='0'><div tabindex='-1' style='width:1px;height:1px;' id='" +
							$t.p.knv + "'></div></div>")
						, i, kdir;

					function scrollGrid(iR, iC, tp) {
						if (tp.substr(0, 1) === 'v') {
							var ch = $($t.grid.bDiv)[0].clientHeight
								, st = $($t.grid.bDiv)[0].scrollTop
								, nROT = $t.rows[iR].offsetTop + $t.rows[iR].clientHeight
								, pROT = $t.rows[iR].offsetTop;
							if (tp === 'vd') {
								if (nROT >= ch) {
									$($t.grid.bDiv)[0].scrollTop = $($t.grid.bDiv)[0].scrollTop + $t.rows[iR].clientHeight;
								}
							}
							if (tp === 'vu') {
								if (pROT < st) {
									$($t.grid.bDiv)[0].scrollTop = $($t.grid.bDiv)[0].scrollTop - $t.rows[iR].clientHeight;
								}
							}
						}
						if (tp === 'h') {
							var cw = $($t.grid.bDiv)[0].clientWidth
								, sl = $($t.grid.bDiv)[0].scrollLeft
								, nCOL = $t.rows[iR].cells[iC].offsetLeft + $t.rows[iR].cells[iC].clientWidth
								, pCOL = $t.rows[iR].cells[iC].offsetLeft;
							if (nCOL >= cw + parseInt(sl, 10)) {
								$($t.grid.bDiv)[0].scrollLeft = $($t.grid.bDiv)[0].scrollLeft + $t.rows[iR].cells[iC].clientWidth;
							}
							else if (pCOL < sl) {
								$($t.grid.bDiv)[0].scrollLeft = $($t.grid.bDiv)[0].scrollLeft - $t.rows[iR].cells[iC].clientWidth;
							}
						}
					}

					function findNextVisible(iC, act) {
						var ind, i;
						if (act === 'lft') {
							ind = iC + 1;
							for (i = iC; i >= 0; i--) {
								if ($t.p.Columns[i].hidden !== true) {
									ind = i;
									break;
								}
							}
						}
						if (act === 'rgt') {
							ind = iC - 1;
							for (i = iC; i < $t.p.Columns.length; i++) {
								if ($t.p.Columns[i].hidden !== true) {
									ind = i;
									break;
								}
							}
						}
						return ind;
					}
					$(selection).insertBefore($t.grid.cDiv);
					$("#" + $t.p.knv).focus().keydown(function(e) {
						var ridx = 0;
						kdir = e.keyCode;
						if ($t.p.direction === "rtl") {
							if (kdir === 37) {
								kdir = 39;
							}
							else if (kdir === 39) {
								kdir = 37;
							}
						}
						switch (kdir) {
							case 38:
								if ($t.p.iRow - 1 > 0) {
									ridx = $t.p.iRow - 1; // ---
									// kjs
									// ???
									// ?κ?
									// (key
									// down?y?
									// setbindo??)
									scrollGrid(ridx, $t.p.iCol, 'vu');
									$($t).wizGrid("editCell", ridx, $t.p.iCol, false);
									// alert("e.keyCode----up----:"
									// +":38:" +
									// ridx +":"
									// +
									// $t.p.iRow);
									// $($t).setBindArrowKeyRow(ridx);
									// //--- kjs
									// ??? ?κ?
									// (key
									// down?y?
									// setbindo??)
									// ----
									// currentrow
									// setting
								}
								break;
							case 40:
								if ($t.p.iRow + 1 <= $t.rows.length - 1) {
									ridx = $t.p.iRow + 1;
									scrollGrid(ridx, $t.p.iCol, 'vd');
									$($t).wizGrid("editCell", ridx, $t.p.iCol, false);
									// alert("e.keyCode------down--------:"+":40:"
									// + ridx
									// +":"
									// +$t.p.iRow
									// );
									// $($t).setBindArrowKeyRow(ridx);
									// //--- kjs
									// ??? ?κ?
									// (key
									// down?y?
									// setbindo??)
									// ----
									// currentrow
									// setting
								}
								break;
							case 37:
								if ($t.p.iCol - 1 >= 0) {
									i = findNextVisible($t.p.iCol - 1, 'lft');
									scrollGrid($t.p.iRow, i, 'h');
									$($t).wizGrid("editCell", $t.p.iRow, i, false);
								}
								break;
							case 39:
								if ($t.p.iCol + 1 <= $t.p.Columns.length - 1) {
									i = findNextVisible($t.p.iCol + 1, 'rgt');
									scrollGrid($t.p.iRow, i, 'h');
									$($t).wizGrid("editCell", $t.p.iRow, i, false);
								}
								break;
							case 13:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break; // START
								// pjy
								
							case 9:
								// alert('?????');
							
								if (!e.shiftKey) {
									//alert($t.p.iCol  + " @@ " +  $t.p.Columns.length );
									if ($t.p.iCol + 1 <= $t.p.Columns.length - 1) {
										
										i = findNextVisible($t.p.iCol + 1, 'rgt');
										scrollGrid($t.p.iRow, i, 'h');
										$($t).wizGrid("editCell", $t.p.iRow, i, false);
									}else{
									   // 칼럼 끝에 포커스 오면 다음 row 행으로 이동
										var colSaveName= $($t).wizGrid('getGridParam', 'Columns');
										var startcolmn = "3"; 
										if(colSaveName[0]['name']=='rn' && colSaveName[1]['name'] =='cb') // 멀티체크 순번 둘다 있을때 
								         {
											//상태 플래그 히든인지 아닌지 판단
											if(colSaveName[3]['hidden']){
												startcolmn = 3;
												
											}else{
												startcolmn = 4;
											}
											
								         }
										 else if(colSaveName[0]['name'] =='rn' || colSaveName[0]['name'] =='cb')  // 멀티체크 또는 순번 이 있을때
						    			{  
											//상태 플래그 히든인지 아닌지 판단
												if(colSaveName[3]['hidden']){
													startcolmn = 2;
													
												}else{
													startcolmn = 3;
												}
						    			}
										 else  
					    				{
											//상태 플래그 히든인지 아닌지 판단
												if(colSaveName[3]['hidden']){
													startcolmn = 1;
													
												}else{
													startcolmn = 2;
												}
											 
					    				}
										ridx = $t.p.iRow + 1;
										//alert($($t).getRowCount() +" @@@ "+ $t.p.iRow);
										// 제일 마지막 row 경우 리턴시킴
										if($($t).getRowCount()+1 == ridx){
											return;
										}
										scrollGrid(ridx, startcolmn, 'vd');
										$($t).wizGrid("editCell", ridx, startcolmn, false);
										
									}
								}
								else {
									if ($t.p.iCol - 1 >= 0) {
										
										i = findNextVisible($t.p.iCol - 1, 'lft');
										scrollGrid($t.p.iRow, i, 'h');
										$($t).wizGrid("editCell", $t.p.iRow, i, false);
									}else{
										//alert("PJY KEY EVENT");
										
									}
								}
								break;
							case 65:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break;
							case 66:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									// $($t).wizGrid('setCell',
									// $t.p.iRow,
									// $t.p.Columns[$t.p.iCol]['name'],String.fromCharCode(kdir)
									// );
									setTimeout(function() {
										$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
									}, 50);
									// setTimeout(function(){$('#keynav').editCell(
									// Rowcnt,
									// selICol ,
									// true)},
									// 50);
								}
								break;
							case 67:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break;
							case 68:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break;
							case 69:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break;
							case 70:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break;
							case 71:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break;
							case 72:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break;
							case 73:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break;
							case 74:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break;
							case 75:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break;
							case 76:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break;
							case 77:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break;
							case 78:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break;
							case 79:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break;
							case 80:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break;
							case 81:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break;
							case 82:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break;
							case 83:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break;
							case 84:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break;
							case 85:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break;
							case 86:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break;
							case 87:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break;
							case 88:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break;
							case 89:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break;
							case 90:
								if (parseInt($t.p.iCol, 10) >= 0 && parseInt($t.p.iRow, 10) >= 0) {
									$($t).wizGrid("editCell", $t.p.iRow, $t.p.iCol, true);
								}
								break;
							default:
								return true;
						}
						return false;
					});
				});
			}
			, getChangedCells: function(mthd) {
					var ret = [];
					if (!mthd) {
						mthd = 'all';
					}
					this.each(function() {
						var $t = this
							, nm;
						if (!$t.grid || $t.p.cellEdit !== true) {
							return;
						}
						$($t.rows).each(function(j) {
							var res = {};
							if ($(this).hasClass("edited")) {
								$('td', this).each(function(i) {
									nm = $t.p.Columns[i].name;
									if (nm !== 'cb' && nm !== 'subgrid') {
										if (mthd === 'dirty') {
											if ($(this).hasClass('dirty-cell')) {
												try {
													res[nm] = $.unformat.call($t, this, {
														rowId: $t.rows[j].id
														, Columns: $t.p.Columns[i]
													}, i);
												}
												catch (e) {
													res[nm] = $.wizgrid.htmlDecode($(this).html());
												}
											}
										}
										else {
											try {
												res[nm] = $.unformat.call($t, this, {
													rowId: $t.rows[j].id
													, Columns: $t.p.Columns[i]
												}, i);
											}
											catch (e) {
												res[nm] = $.wizgrid.htmlDecode($(this).html());
											}
										}
									}
								});
								res.id = this.id;
								ret.push(res);
							}
						});
					});
					return ret;
				}
				// / end cell editing
		});
		// module begin
		$.extend($.wizgrid, {
			// Modal functions
			showModal: function(h) {
				h.w.show();
			}
			, closeModal: function(h) {
				h.w.hide().attr("aria-hidden", "true");
				if (h.o) {
					h.o.remove();
				}
			}
			, hideModal: function(selector, o) {
				o = $.extend({
					wizm: true
					, gb: ''
					, removemodal: false
					, formprop: false
					, form: ''
				}, o || {});
				var thisgrid = o.gb && typeof o.gb === "string" && o.gb.substr(0, 6) === "#gbox_" ? $("#" + o.gb.substr(
					6))[0] : false;
				if (o.onClose) {
					var oncret = thisgrid ? o.onClose.call(thisgrid, selector) : o.onClose(selector);
					if (typeof oncret === 'boolean' && !oncret) {
						return;
					}
				}
				if (o.formprop && thisgrid && o.form) {
					var fh = $(selector)[0].style.height;
					if (fh.indexOf("px") > -1) {
						fh = parseFloat(fh);
					}
					var frmgr, frmdata;
					if (o.form === 'edit') {
						frmgr = '#' + $.wizgrid.wizID("FrmGrid_" + o.gb.substr(6));
						frmdata = "formProp";
					}
					else if (o.form === 'view') {
						frmgr = '#' + $.wizgrid.wizID("ViewGrid_" + o.gb.substr(6));
						frmdata = "viewProp";
					}
					$(thisgrid).data(frmdata, {
						top: parseFloat($(selector).css("top"))
						, left: parseFloat($(selector).css("left"))
						, width: $(selector).width()
						, height: fh
						, dataheight: $(frmgr).height()
						, datawidth: $(frmgr).width()
					});
				}
				if ($.fn.wizm && o.wizm === true) {
					$(selector).attr("aria-hidden", "true").wizmHide();
				}
				else {
					if (o.gb !== '') {
						try {
							$(".wizgrid-overlay:first", o.gb).hide();
						}
						catch (e) {}
					}
					$(selector).hide().attr("aria-hidden", "true");
				}
				if (o.removemodal) {
					$(selector).remove();
				}
			}
			, // Helper functions
			findPos: function(obj) {
				var curleft = 0
					, curtop = 0;
				if (obj.offsetParent) {
					do {
						curleft += obj.offsetLeft;
						curtop += obj.offsetTop;
					} while (obj = obj.offsetParent);
					// do not change obj == obj.offsetParent
				}
				return [curleft, curtop];
			}
			, createModal: function(aIDs, content, p, insertSelector, posSelector, appendsel, css) {
				p = $.extend(true, {}, $.wizgrid.wizModal || {}, p);
				var mw = document.createElement('div')
					, rtlsup, self = this;
				css = $.extend({}, css || {});
				rtlsup = $(p.gbox).attr("dir") === "rtl" ? true : false;
				mw.className = "ui-widget ui-widget-content-grid ui-corner-all-grid .ui-wizdialog";
				mw.id = aIDs.themodal;
				var mh = document.createElement('div');
				mh.className =
					".ui-wizdialog-titlebar ui-widget-header-grid ui-corner-all-grid ui-helper-clearfix-grid";
				mh.id = aIDs.modalhead;
				$(mh).append("<span class='.ui-wizdialog-title'>" + p.caption + "</span>");
				var ahr = $("<a class='.ui-wizdialog-titlebar-close ui-corner-all-grid'></a>").hover(function() {
					ahr.addClass('ui-state-hover-grid');
				}, function() {
					ahr.removeClass('ui-state-hover-grid');
				}).append("<span class='ui-icon ui-icon-closethick'></span>");
				$(mh).append(ahr);
				if (rtlsup) {
					mw.dir = "rtl";
					$("..ui-wizdialog-title", mh).css("float", "right");
					$("..ui-wizdialog-titlebar-close", mh).css("left", 0.3 + "em");
				}
				else {
					mw.dir = "ltr";
					$("..ui-wizdialog-title", mh).css("float", "left");
					$("..ui-wizdialog-titlebar-close", mh).css("right", 0.3 + "em");
				}
				var mc = document.createElement('div');
				$(mc).addClass(".ui-wizdialog-content ui-widget-content-grid").attr("id", aIDs.modalcontent);
				$(mc).append(content);
				mw.appendChild(mc);
				$(mw).prepend(mh);
				if (appendsel === true) {
					$('body').append(mw);
				} // append as first child in body -for
				// alert dialog
				else if (typeof appendsel === "string") {
					$(appendsel).append(mw);
				}
				else {
					$(mw).insertBefore(insertSelector);
				}
				$(mw).css(css);
				if (p.wizModal === undefined) {
					p.wizModal = true;
				} // internal use
				var coord = {};
				if ($.fn.wizm && p.wizModal === true) {
					if (p.left === 0 && p.top === 0 && p.overlay) {
						var pos = [];
						pos = $.wizgrid.findPos(posSelector);
						p.left = pos[0] + 4;
						p.top = pos[1] + 4;
					}
					coord.top = p.top + "px";
					coord.left = p.left;
				}
				else if (p.left !== 0 || p.top !== 0) {
					coord.left = p.left;
					coord.top = p.top + "px";
				}
				$("a..ui-wizdialog-titlebar-close", mh).click(function() {
					var oncm = $("#" + $.wizgrid.wizID(aIDs.themodal)).data("onClose") || p.onClose;
					var gboxclose = $("#" + $.wizgrid.wizID(aIDs.themodal)).data("gbox") || p.gbox;
					self.hideModal("#" + $.wizgrid.wizID(aIDs.themodal), {
						gb: gboxclose
						, wizm: p.wizModal
						, onClose: oncm
						, removemodal: p.removemodal || false
						, formprop: !p.recreateForm || false
						, form: p.form || ''
					});
					return false;
				});
				if (p.width === 0 || !p.width) {
					p.width = 300;
				}
				if (p.height === 0 || !p.height) {
					p.height = 200;
				}
				if (!p.zIndex) {
					var parentZ = $(insertSelector).parents("*[role=dialog]").filter(':first').css("z-index");
					if (parentZ) {
						p.zIndex = parseInt(parentZ, 10) + 2;
					}
					else {
						p.zIndex = 950;
					}
				}
				var rtlt = 0;
				if (rtlsup && coord.left && !appendsel) {
					rtlt = $(p.gbox).width() - (!isNaN(p.width) ? parseInt(p.width, 10) : 0) - 8; // to
					// do
					// just in case
					coord.left = parseInt(coord.left, 10) + parseInt(rtlt, 10);
				}
				if (coord.left) {
					coord.left += "px";
				}
				$(mw).css($.extend({
					width: isNaN(p.width) ? "auto" : p.width + "px"
					, height: isNaN(p.height) ? "auto" : p.height + "px"
					, zIndex: p.zIndex
					, overflow: 'hidden'
				}, coord)).attr({
					tabIndex: "-1"
					, "role": "dialog"
					, "aria-labelledby": aIDs.modalhead
					, "aria-hidden": "true"
				});
				if (p.drag === undefined) {
					p.drag = true;
				}
				if (p.resize === undefined) {
					p.resize = true;
				}
				if (p.drag) {
					$(mh).css('cursor', 'move');
					if ($.fn.wizDrag) {
						$(mw).wizDrag(mh);
					}
					else {
						try {
							$(mw).draggable({
								handle: $("#" + $.wizgrid.wizID(mh.id))
							});
						}
						catch (e) {}
					}
				}
				if (p.resize) {
					if ($.fn.wizResize) {
						$(mw).append(
							"<div class='wizResize ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se'></div>"
						);
						$("#" + $.wizgrid.wizID(aIDs.themodal)).wizResize(".wizResize", aIDs.scrollelm ? "#" + $.wizgrid.wizID(
							aIDs.scrollelm) : false);
					}
					else {
						try {
							$(mw).resizable({
								handles: 'se, sw'
								, alsoResize: aIDs.scrollelm ? "#" + $.wizgrid.wizID(aIDs.scrollelm) : false
							});
						}
						catch (r) {}
					}
				}
				if (p.closeOnEscape === true) {
					$(mw).keydown(function(e) {
						if (e.which === 27) {
							var cone = $("#" + $.wizgrid.wizID(aIDs.themodal)).data("onClose") || p.onClose;
							self.hideModal("#" + $.wizgrid.wizID(aIDs.themodal), {
								gb: p.gbox
								, wizm: p.wizModal
								, onClose: cone
								, removemodal: p.removemodal || false
								, formprop: !p.recreateForm || false
								, form: p.form || ''
							});
						}
					});
				}
			}
			, viewModal: function(selector, o) {
				o = $.extend({
					toTop: true
					, overlay: 10
					, modal: false
					, overlayClass: 'ui-widget-overlay'
					, onShow: $.wizgrid.showModal
					, onHide: $.wizgrid.closeModal
					, gbox: ''
					, wizm: true
					, wizM: true
				}, o || {});
				if ($.fn.wizm && o.wizm === true) {
					if (o.wizM) {
						$(selector).attr("aria-hidden", "false").wizm(o).wizmShow();
					}
					else {
						$(selector).attr("aria-hidden", "false").wizmShow();
					}
				}
				else {
					if (o.gbox !== '') {
						$(".wizgrid-overlay:first", o.gbox).show();
						$(selector).data("gbox", o.gbox);
					}
					$(selector).show().attr("aria-hidden", "false");
					try {
						$(':input:visible', selector)[0].focus();
					}
					catch (_) {}
				}
			}
			, info_dialog: function(caption, content, c_b, modalopt) {
				var mopt = {
					width: 290
					, height: 'auto'
					, dataheight: 'auto'
					, drag: true
					, resize: false
					, left: 250
					, top: 170
					, zIndex: 1000
					, wizModal: true
					, modal: false
					, closeOnEscape: true
					, align: 'center'
					, buttonalign: 'center'
					, buttons: []
						// {text:'textbutt', id:"buttid", onClick :
						// function(){...}}
						// if the id is not provided we set it like
						// info_button_+ the index in the array -
						// i.e info_button_0,info_button_1...
				};
				$.extend(true, mopt, $.wizgrid.wizModal || {}, {
					caption: "<b>" + caption + "</b>"
				}, modalopt || {});
				var jm = mopt.wizModal
					, self = this;
				if ($.fn.wizm && !jm) {
					jm = false;
				}
				// in case there is no wizModal
				var buttstr = ""
					, i;
				if (mopt.buttons.length > 0) {
					for (i = 0; i < mopt.buttons.length; i++) {
						if (mopt.buttons[i].id === undefined) {
							mopt.buttons[i].id = "info_button_" + i;
						}
						buttstr += "<a id='" + mopt.buttons[i].id +
							"' class='fm-button ui-state-default-grid ui-corner-all-grid'>" + mopt.buttons[i].text + "</a>";
					}
				}
				var dh = isNaN(mopt.dataheight) ? mopt.dataheight : mopt.dataheight + "px"
					, cn = "text-align:" + mopt.align + ";";
				var cnt = "<div id='info_id'>";
				cnt +=
					"<div id='infocnt' style='margin:0px;padding-bottom:1em;width:100%;overflow:auto;position:relative;height:" +
					dh + ";" + cn + "'>" + content + "</div>";
				cnt += c_b ? "<div class='ui-widget-content-grid ui-helper-clearfix-grid' style='text-align:" + mopt.buttonalign +
					";padding-bottom:0.8em;padding-top:0.5em;background-image: none;border-width: 1px 0 0 0;'><a id='closedialog' class='fm-button ui-state-default-grid ui-corner-all-grid'>" +
					c_b + "</a>" + buttstr + "</div>" : buttstr !== "" ?
					"<div class='ui-widget-content-grid ui-helper-clearfix-grid' style='text-align:" + mopt.buttonalign +
					";padding-bottom:0.8em;padding-top:0.5em;background-image: none;border-width: 1px 0 0 0;'>" +
					buttstr + "</div>" : "";
				cnt += "</div>";
				try {
					if ($("#info_dialog").attr("aria-hidden") === "false") {
						$.wizgrid.hideModal("#info_dialog", {
							wizm: jm
						});
					}
					$("#info_dialog").remove();
				}
				catch (e) {}
				$.wizgrid.createModal({
					themodal: 'info_dialog'
					, modalhead: 'info_head'
					, modalcontent: 'info_content'
					, scrollelm: 'infocnt'
				}, cnt, mopt, '', '', true);
				// attach onclick after inserting into the
				// dom
				if (buttstr) {
					$.each(mopt.buttons, function(i) {
						$("#" + $.wizgrid.wizID(this.id), "#info_id").bind('click', function() {
							mopt.buttons[i].onClick.call($("#info_dialog"));
							return false;
						});
					});
				}
				$("#closedialog", "#info_id").click(function() {
					self.hideModal("#info_dialog", {
						wizm: jm
						, onClose: $("#info_dialog").data("onClose") || mopt.onClose
						, gb: $("#info_dialog").data("gbox") || mopt.gbox
					});
					return false;
				});
				$(".fm-button", "#info_dialog").hover(function() {
					$(this).addClass('ui-state-hover-grid');
				}, function() {
					$(this).removeClass('ui-state-hover-grid');
				});
				if ($.isFunction(mopt.beforeOpen)) {
					mopt.beforeOpen();
				}
				$.wizgrid.viewModal("#info_dialog", {
					onHide: function(h) {
						h.w.hide().remove();
						if (h.o) {
							h.o.remove();
						}
					}
					, modal: mopt.modal
					, wizm: jm
				});
				if ($.isFunction(mopt.afterOpen)) {
					mopt.afterOpen();
				}
				try {
					$("#info_dialog").focus();
				}
				catch (m) {}
			}
			, bindEv: function(el, opt) {
				var $t = this;
				if ($.isFunction(opt.dataInit)) {
					opt.dataInit.call($t, el, opt);
				}
				if (opt.dataEvents) {
					$.each(opt.dataEvents, function() {
						if (this.data !== undefined) {
							$(el).bind(this.type, this.data, this.fn);
						}
						else {
							// pjy start
							var girdRe = this.grid;
							var ColNa = this.Colname;
							if (this.type == "keydown") {
								$(el).bind(this.type, function(e) {
									keyNavi(e, girdRe, ColNa);
								});
							}
							else {
								$(el).bind(this.type, this.fn);
							}
							// end
						}
					});
				}
			}
			, // Form Functions
			createEl: function(align, eltype, options, vl, autowidth, ajaxso) {
				var elem = ""
					, $t = this;

				function setAttributes(elm, atr, exl) {
					var exclude = ['dataInit', 'dataEvents', 'dataUrl', 'buildSelect', 'sopt', 'searchhidden'
						, 'defaultValue', 'attr', 'custom_element', 'custom_value', 'oper'
					];
					if (exl !== undefined && $.isArray(exl)) {
						$.merge(exclude, exl);
					}
					$.each(atr, function(key, value) {
						if ($.inArray(key, exclude) === -1) {
							$(elm).attr(key, value);
						}
					});
					if (!atr.hasOwnProperty('id')) {
						$(elm).attr('id', $.wizgrid.randId());
					}
				}
				switch (eltype) {
					case "textarea":
						elem = document.createElement("textarea");
						if (autowidth) {
							if (!options.cols) {
								$(elem).css({
									width: "98%"
									, height: "100%"
								});
							}
						}
						else if (!options.cols) {
							options.cols = 20;
						}
						if (!options.rows) {
							options.rows = 2;
						}
						if (vl === '&nbsp;' || vl === '&#160;' || (vl.length === 1 && vl.charCodeAt(0) === 160)) {
							vl = "";
						}
						elem.value = vl;
						setAttributes(elem, options);
						$(elem).attr({
							"role": "textbox"
							, "multiline": "true"
						});
						break;
					case "checkbox": // what code for simple
						// checkbox
						elem = document.createElement("input");
						elem.type = "checkbox";
						if (!options.value) {
							var vl1 = (vl + "").toLowerCase();
							if (vl1.search(/(false|f|0|no|n|off|undefined)/i) < 0 && vl1 !== "") {
								elem.checked = true;
								elem.defaultChecked = true;
								elem.value = vl;
							}
							else {
								elem.value = "on";
							}
							$(elem).attr("offval", "off");
						}
						else {
							var cbval = options.value.split(":");
							if (vl === cbval[0]) {
								elem.checked = true;
								elem.defaultChecked = true;
							}
							elem.value = cbval[0];
							$(elem).attr("offval", cbval[1]);
						}
						setAttributes(elem, options, ['value']);
						$(elem).attr("role", "checkbox");
						break;
					case "select":
						elem = document.createElement("select");
						elem.setAttribute("role", "select");
						elem.setAttribute("class", "select_Grid");
						var msl, ovm = [];
						if (options.multiple === true) {
							msl = true;
							elem.multiple = "multiple";
							$(elem).attr("aria-multiselectable", "true");
						}
						else {
							msl = false;
						}
						if (options.dataUrl != null) {
							var rowid = null
								, postData = options.postData || ajaxso.postData;
							try {
								rowid = options.rowId;
							}
							catch (e) {}
							if ($t.p && $t.p.idPrefix) {
								rowid = $.wizgrid.stripPref($t.p.idPrefix, rowid);
							}
							$.ajax($.extend({
								url: $.isFunction(options.dataUrl) ? options.dataUrl.call($t, rowid, vl, String(options.name)) : options
									.dataUrl
								, type: "GET"
								, dataType: "html"
								, data: $.isFunction(postData) ? postData.call($t, rowid, vl, String(options.name)) : postData
								, context: {
									elem: elem
									, options: options
									, vl: vl
								}
								, success: function(data) {
									var ovm = []
										, elem = this.elem
										, vl = this.vl
										, options = $.extend({}, this.options)
										, msl = options.multiple === true
										, cU = options.cacheUrlData === true
										, oV = ''
										, txt, vl, a = $.isFunction(options.buildSelect) ? options.buildSelect.call($t, data) :
										data;
									if (typeof a === 'string') {
										a = $($.trim(a)).html();
									}
									if (a) {
										$(elem).append(a);
										setAttributes(elem, options, postData ? ['postData'] : undefined);
										if (options.size === undefined) {
											options.size = msl ? 3 : 1;
										}
										if (msl) {
											ovm = vl.split(",");
											ovm = $.map(ovm, function(n) {
												return $.trim(n);
											});
										}
										else {
											ovm[0] = $.trim(vl);
										}
										// $(elem).attr(options);
										setTimeout(function() {
											$("option", elem).each(function(i) {
												txt = $(this).text();
												vl = $(this).val() || txt;
												if (cU) {
													oV += (i !== 0 ? ";" : "") + vl + ":" + txt;
												}
												// if(i===0)
												// {
												// this.selected
												// =
												// "";
												// }
												// fix
												// IE8/IE7
												// problem
												// with
												// selecting
												// of
												// the
												// first
												// item
												// on
												// multiple=true
												if (i === 0 && elem.multiple) {
													this.selected = false;
												}
												$(this).attr("role", "option");
												if ($.inArray($.trim(txt), ovm) > -1 || $.inArray($.trim(vl), ovm) > -1) {
													this.selected = "selected";
												}
											});
											if (cU) {
												if (options.oper === 'edit') {
													$($t).wizGrid('setColProp', options.name, {
														editoptions: {
															buildSelect: null
															, dataUrl: null
															, value: oV
														}
													});
												}
												else if (options.oper === 'search') {
													$($t).wizGrid('setColProp', options.name, {
														searchoptions: {
															dataUrl: null
															, value: oV
														}
													});
												}
												else if (options.oper === 'filter') {
													if ($("#fbox_" + $t.p.id)[0].p) {
														var cols = $("#fbox_" + $t.p.id)[0].p.columns
															, nm;
														$.each(cols, function(i) {
															nm = this.index || this.name;
															if (options.name === nm) {
																this.searchoptions.dataUrl = null;
																this.searchoptions.value = oV;
																return false;
															}
														});
													}
												}
											}
										}, 0);
									}
								}
							}, ajaxso || {}));
						}
						else if (options.value) {
							var i;
							if (options.size === undefined) {
								options.size = msl ? 3 : 1;
							}
							if (msl) {
								ovm = vl.split(",");
								ovm = $.map(ovm, function(n) {
									return $.trim(n);
								});
							}
							if (typeof options.value === 'function') {
								options.value = options.value();
							}
							var so, sv, ov, sep = options.separator === undefined ? ":" : options.separator
								, delim = options.delimiter === undefined ? ";" : options.delimiter;
							if (typeof options.value === 'string') {
								so = options.value.split(delim);
								for (i = 0; i < so.length; i++) {
									sv = so[i].split(sep);
									if (sv.length > 2) {
										sv[1] = $.map(sv, function(n, ii) {
											if (ii > 0) {
												return n;
											}
										}).join(sep);
									}
									ov = document.createElement("option");
									ov.setAttribute("role", "option");
									ov.value = sv[0];
									ov.innerHTML = sv[1];
									elem.appendChild(ov);
									if (!msl && ($.trim(sv[0]) === $.trim(vl) || $.trim(sv[1]) === $.trim(vl))) {
										ov.selected = "selected";
									}
									if (msl && ($.inArray($.trim(sv[1]), ovm) > -1 || $.inArray($.trim(sv[0]), ovm) > -1)) {
										ov.selected = "selected";
									}
								}
							}
							else if (Object.prototype.toString.call(options.value) === "[object Array]") {
								var oSv = options.value;
								// array of arrays [[Key,
								// Value], [Key, Value], ...]
								for (var i = 0; i < oSv.length; i++) {
									if (oSv[i].length === 2) {
										var key = oSv[i][0]
											, value = oSv[i][1];
										ov = document.createElement("option");
										ov.setAttribute("role", "option");
										ov.value = key;
										ov.innerHTML = value;
										elem.appendChild(ov);
										if (!msl && ($.trim(key) === $.trim(vl) || $.trim(value) === $.trim(vl))) {
											ov.selected = "selected";
										}
										if (msl && ($.inArray($.trim(value), ovm) > -1 || $.inArray($.trim(key), ovm) > -1)) {
											ov.selected = "selected";
										}
									}
								}
							}
							else if (typeof options.value === 'object') {
								var oSv = options.value
									, key;
								for (key in oSv) {
									if (oSv.hasOwnProperty(key)) {
										ov = document.createElement("option");
										ov.setAttribute("role", "option");
										ov.value = key;
										ov.innerHTML = oSv[key];
										elem.appendChild(ov);
										if (!msl && ($.trim(key) === $.trim(vl) || $.trim(oSv[key]) === $.trim(vl))) {
											ov.selected = "selected";
										}
										if (msl && ($.inArray($.trim(oSv[key]), ovm) > -1 || $.inArray($.trim(key), ovm) > -1)) {
											ov.selected = "selected";
										}
									}
								}
							}
							setAttributes(elem, options, ['value']);
						}
						break;
					case "text":
					case "password":
					case "button":
						// alert(eltype);
						var role;
						if (eltype === "button") {
							role = "button";
						}
						else {
							role = "textbox";
						}
						elem = document.createElement("input");
						elem.type = eltype;
						elem.value = vl;
						setAttributes(elem, options);
						if (eltype !== "button") {
							if (autowidth) {
								if (!options.size) {
									$(elem).css({
										width: "98%"
										, 'text-align': align
									});
								} // pjy text-align 추가
							}
							else if (!options.size) {
								options.size = 20;
							}
						}
						$(elem).attr("role", role);
						break;
					case "image":
					case "file":
						elem = document.createElement("input");
						elem.type = eltype;
						setAttributes(elem, options);
						break;
					case "custom":
						elem = document.createElement("span");
						try {
							if ($.isFunction(options.custom_element)) {
								var celm = options.custom_element.call($t, vl, options);
								if (celm) {
									celm = $(celm).addClass("customelement").attr({
										id: options.id
										, name: options.name
									});
									$(elem).empty().append(celm);
								}
								else {
									throw "e2";
								}
							}
							else {
								throw "e1";
							}
						}
						catch (e) {
							var errors = $.wizgrid.getRegional($t, 'errors')
								, edit = $.wizgrid.getRegional($t, 'edit');
							if (e === "e1") {
								$.wizgrid.info_dialog(errors.errcap, "function 'custom_element' " + edit.msg.nodefined, edit.bClose);
							}
							if (e === "e2") {
								$.wizgrid.info_dialog(errors.errcap, "function 'custom_element' " + edit.msg.novalue, edit.bClose);
							}
							else {
								$.wizgrid.info_dialog(errors.errcap, typeof e === "string" ? e : e.message, edit.bClose);
							}
						}
						break;
				}
				return elem;
			}
			, // Date Validation Javascript
			checkDate: function(format, date) {
				var daysInFebruary = function(year) {
						// February has 29 days in any year
						// evenly divisible by four,
						// EXCEPT for centurial years which are
						// not also divisible by 400.
						return (((year % 4 === 0) && (year % 100 !== 0 || (year % 400 === 0))) ? 29 : 28);
					}
					, tsp = {}
					, sep;
				format = format.toLowerCase();
				// we search for /,-,. for the date
				// separator
				if (format.indexOf("/") !== -1) {
					sep = "/";
				}
				else if (format.indexOf("-") !== -1) {
					sep = "-";
				}
				else if (format.indexOf(".") !== -1) {
					sep = ".";
				}
				else {
					sep = "/";
				}
				format = format.split(sep);
				date = date.split(sep);
				if (date.length !== 3) {
					return false;
				}
				var j = -1
					, yln, dln = -1
					, mln = -1
					, i;
				for (i = 0; i < format.length; i++) {
					var dv = isNaN(date[i]) ? 0 : parseInt(date[i], 10);
					tsp[format[i]] = dv;
					yln = format[i];
					if (yln.indexOf("y") !== -1) {
						j = i;
					}
					if (yln.indexOf("m") !== -1) {
						mln = i;
					}
					if (yln.indexOf("d") !== -1) {
						dln = i;
					}
				}
				if (format[j] === "y" || format[j] === "yyyy") {
					yln = 4;
				}
				else if (format[j] === "yy") {
					yln = 2;
				}
				else {
					yln = -1;
				}
				var daysInMonth = [0, 31, 29, 31, 30, 31
						, 30, 31, 31, 30, 31, 30, 31
					]
					, strDate;
				if (j === -1) {
					return false;
				}
				strDate = tsp[format[j]].toString();
				if (yln === 2 && strDate.length === 1) {
					yln = 1;
				}
				if (strDate.length !== yln || (tsp[format[j]] === 0 && date[j] !== "00")) {
					return false;
				}
				if (mln === -1) {
					return false;
				}
				strDate = tsp[format[mln]].toString();
				if (strDate.length < 1 || tsp[format[mln]] < 1 || tsp[format[mln]] > 12) {
					return false;
				}
				if (dln === -1) {
					return false;
				}
				strDate = tsp[format[dln]].toString();
				if (strDate.length < 1 || tsp[format[dln]] < 1 || tsp[format[dln]] > 31 || (tsp[format[mln]] === 2 &&
						tsp[format[dln]] > daysInFebruary(tsp[format[j]])) || tsp[format[dln]] > daysInMonth[tsp[format[mln]]]) {
					return false;
				}
				return true;
			}
			, isEmpty: function(val) {
				if (val.match(/^\s+$/) || val === "") {
					return true;
				}
				return false;
			}
			, checkTime: function(time) {
				// checks only hh:ss (and optional am/pm)
				var re = /^(\d{1,2}):(\d{2})([apAP][Mm])?$/
					, regs;
				if (!$.wizgrid.isEmpty(time)) {
					regs = time.match(re);
					if (regs) {
						if (regs[3]) {
							if (regs[1] < 1 || regs[1] > 12) {
								return false;
							}
						}
						else {
							if (regs[1] > 23) {
								return false;
							}
						}
						if (regs[2] > 59) {
							return false;
						}
					}
					else {
						return false;
					}
				}
				return true;
			}
			, checkValues: function(val, valref, customobject, nam) {
				var edtrul, i, nm, dft, len, g = this
					, cm = g.p.Columns
					, msg = $.wizgrid.getRegional(this, 'edit.msg')
					, fmtdate;
				if (customobject === undefined) {
					if (typeof valref === 'string') {
						for (i = 0, len = cm.length; i < len; i++) {
							if (cm[i].name === valref) {
								edtrul = cm[i].editrules;
								valref = i;
								if (cm[i].formoptions != null) {
									nm = cm[i].formoptions.label;
								}
								break;
							}
						}
					}
					else if (valref >= 0) {
						edtrul = cm[valref].editrules;
					}
				}
				else {
					edtrul = customobject;
					nm = nam === undefined ? "_" : nam;
				}
				if (edtrul) {
					if (!nm) {
						nm = g.p.Names != null ? g.p.Names[valref] : cm[valref].label;
					}
					if (edtrul.required === true) {
						if ($.wizgrid.isEmpty(val)) {
							return [
								false
								, nm + ": " + msg.required, ""
							];
						}
					}
					// force required
					var rqfield = edtrul.required === false ? false : true;
					if (edtrul.number === true) {
						if (!(rqfield === false && $.wizgrid.isEmpty(val))) {
							if (isNaN(val)) {
								return [
									false
									, nm + ": " + msg.number, ""
								];
							}
						}
					}
					if (edtrul.minValue !== undefined && !isNaN(edtrul.minValue)) {
						if (parseFloat(val) < parseFloat(edtrul.minValue)) {
							return [
								false
								, nm + ": " + msg.minValue + " " + edtrul.minValue, ""
							];
						}
					}
					if (edtrul.maxValue !== undefined && !isNaN(edtrul.maxValue)) {
						if (parseFloat(val) > parseFloat(edtrul.maxValue)) {
							return [
								false
								, nm + ": " + msg.maxValue + " " + edtrul.maxValue, ""
							];
						}
					}
					var filter;
					if (edtrul.email === true) {
						if (!(rqfield === false && $.wizgrid.isEmpty(val))) {
							// taken from $ Validate plugin
							filter =
								/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i;
							if (!filter.test(val)) {
								return [
									false
									, nm + ": " + msg.email, ""
								];
							}
						}
					}
					if (edtrul.integer === true) {
						if (!(rqfield === false && $.wizgrid.isEmpty(val))) {
							if (isNaN(val)) {
								return [
									false
									, nm + ": " + msg.integer, ""
								];
							}
							if ((val % 1 !== 0) || (val.indexOf('.') !== -1)) {
								return [
									false
									, nm + ": " + msg.integer, ""
								];
							}
						}
					}
					if (edtrul.date === true) {
						if (!(rqfield === false && $.wizgrid.isEmpty(val))) {
							if (cm[valref].formatoptions && cm[valref].formatoptions.newformat) {
								dft = cm[valref].formatoptions.newformat;
								fmtdate = $.wizgrid.getRegional(g, 'formatter.date.masks');
								if (fmtdate && fmtdate.hasOwnProperty(dft)) {
									dft = fmtdate[dft];
								}
							}
							else {
								dft = cm[valref].datefmt || "Y-m-d";
							}
							if (!$.wizgrid.checkDate(dft, val)) {
								return [
									false
									, nm + ": " + msg.date + " - " + dft, ""
								];
							}
						}
					}
					if (edtrul.time === true) {
						if (!(rqfield === false && $.wizgrid.isEmpty(val))) {
							if (!$.wizgrid.checkTime(val)) {
								return [
									false
									, nm + ": " + msg.date + " - hh:mm (am/pm)", ""
								];
							}
						}
					}
					if (edtrul.url === true) {
						if (!(rqfield === false && $.wizgrid.isEmpty(val))) {
							filter =
								/^(((https?)|(ftp)):\/\/([\-\w]+\.)+\w{2,3}(\/[%\-\w]+(\.\w{2,})?)*(([\w\-\.\?\\\/+@&#;`~=%!]*)(\.\w{2,})?)*\/?)/i;
							if (!filter.test(val)) {
								return [
									false
									, nm + ": " + msg.url, ""
								];
							}
						}
					}
					if (edtrul.custom === true) {
						if (!(rqfield === false && $.wizgrid.isEmpty(val))) {
							if ($.isFunction(edtrul.custom_func)) {
								var ret = edtrul.custom_func.call(g, val, nm, valref);
								return $.isArray(ret) ? ret : [
									false
									, msg.customarray, ""
								];
							}
							return [false
								, msg.customfcheck, ""
							];
						}
					}
				}
				return [true, "", ""];
			}
		});
		// module begin
		$.fn.wizFilter = function(arg) {
			if (typeof arg === 'string') {
				var fn = $.fn.wizFilter[arg];
				if (!fn) {
					throw ("wizFilter - No such method: " + arg);
				}
				var args = $.makeArray(arguments).slice(1);
				return fn.apply(this, args);
			}
			var p = $.extend(true, {
				filter: null
				, columns: []
				, onChange: null
				, afterRedraw: null
				, checkValues: null
				, error: false
				, errmsg: ""
				, errorcheck: true
				, showQuery: true
				, sopt: null
				, ops: []
				, operands: null
				, numopts: ['eq', 'ne', 'lt', 'le', 'gt', 'ge', 'nu', 'nn', 'in', 'ni']
				, stropts: ['eq', 'ne', 'bw', 'bn', 'ew', 'en', 'cn', 'nc', 'nu', 'nn', 'in', 'ni']
				, strarr: ['text', 'string', 'blob']
				, groupOps: [{
					op: "AND"
					, text: "AND"
				}, {
					op: "OR"
					, text: "OR"
				}]
				, groupButton: true
				, ruleButtons: true
				, direction: "ltr"
			}, $.wizgrid.filter, arg || {});
			return this.each(function() {
				if (this.filter) {
					return;
				}
				this.p = p;
				// setup filter in case if they is not defined
				if (this.p.filter === null || this.p.filter === undefined) {
					this.p.filter = {
						groupOp: this.p.groupOps[0].op
						, rules: []
						, groups: []
					};
				}
				var i, len = this.p.columns.length
					, cl, isIE = /msie/i.test(navigator.userAgent) && !window.opera;
				// translating the options
				this.p.initFilter = $.extend(true, {}, this.p.filter);
				// set default values for the columns if they are
				// not set
				if (!len) {
					return;
				}
				for (i = 0; i < len; i++) {
					cl = this.p.columns[i];
					if (cl.stype) {
						// grid compatibility
						cl.inputtype = cl.stype;
					}
					else if (!cl.inputtype) {
						cl.inputtype = 'text';
					}
					if (cl.sorttype) {
						// grid compatibility
						cl.searchtype = cl.sorttype;
					}
					else if (!cl.searchtype) {
						cl.searchtype = 'string';
					}
					if (cl.hidden === undefined) {
						// wizGrid compatibility
						cl.hidden = false;
					}
					if (!cl.label) {
						cl.label = cl.name;
					}
					if (cl.index) {
						cl.name = cl.index;
					}
					if (!cl.hasOwnProperty('searchoptions')) {
						cl.searchoptions = {};
					}
					if (!cl.hasOwnProperty('searchrules')) {
						cl.searchrules = {};
					}
				}
				if (this.p.showQuery) {
					$(this).append(
						"<table class='queryresult ui-widget ui-widget-content-grid' style='display:block;max-width:440px;border:0px none;' dir='" +
						this.p.direction + "'><tbody><tr><td class='query'></td></tr></tbody></table>");
				}
				var getGrid = function() {
					return $("#" + $.wizgrid.wizID(p.id))[0] || null;
				};
				/*
				 * Perform checking.
				 * 
				 */
				var checkData = function(val, colModelItem) {
					var ret = [true, ""]
						, $t = getGrid();
					if ($.isFunction(colModelItem.searchrules)) {
						ret = colModelItem.searchrules.call($t, val, colModelItem);
					}
					else if ($.wizgrid && $.wizgrid.checkValues) {
						try {
							ret = $.wizgrid.checkValues.call($t, val, -1, colModelItem.searchrules, colModelItem.label);
						}
						catch (e) {}
					}
					if (ret && ret.length && ret[0] === false) {
						p.error = !ret[0];
						p.errmsg = ret[1];
					}
				};
				/*
				 * moving to common randId = function() { return
				 * Math.floor(Math.random()*10000).toString(); };
				 */
				this.onchange = function() {
					// clear any error
					this.p.error = false;
					this.p.errmsg = "";
					return $.isFunction(this.p.onChange) ? this.p.onChange.call(this, this.p) : false;
				};
				/*
				 * Redraw the filter every time when new field is
				 * added/deleted and field is changed
				 */
				this.reDraw = function() {
					$("table.group:first", this).remove();
					var t = this.createTableForGroup(p.filter, null);
					$(this).append(t);
					if ($.isFunction(this.p.afterRedraw)) {
						this.p.afterRedraw.call(this, this.p);
					}
				};
				/*
				 * Creates a grouping data for the filter @param
				 * group - object @param parentgroup - object
				 */
				this.createTableForGroup = function(group, parentgroup) {
					var that = this
						, i;
					// this table will hold all the group (tables)
					// and rules (rows)
					var table = $(
							"<table class='group ui-widget ui-widget-content-grid ui-search-table' style='border:0px none;'><tbody></tbody></table>"
						)
						, // create error message row
						align = "left";
					if (this.p.direction === "rtl") {
						align = "right";
						table.attr("dir", "rtl");
					}
					if (parentgroup === null) {
						table.append(
							"<tr class='error' style='display:none;'><th colspan='5' class='ui-state-error' align='" + align +
							"'></th></tr>");
					}
					var tr = $("<tr></tr>");
					table.append(tr);
					// this header will hold the group operator type
					// and group action buttons for
					// creating subgroup "+ {}", creating rule "+"
					// or deleting the group "-"
					var th = $("<th colspan='5' align='" + align + "'></th>");
					tr.append(th);
					if (this.p.ruleButtons === true) {
						// dropdown for: choosing group operator
						// type
						var groupOpSelect = $("<select class='opsel ui-widget-content-grid ui-corner-all-grid'></select>");
						th.append(groupOpSelect);
						// populate dropdown with all posible group
						// operators: or, and
						var str = ""
							, selected;
						for (i = 0; i < p.groupOps.length; i++) {
							selected = group.groupOp === that.p.groupOps[i].op ? " selected='selected'" : "";
							str += "<option value='" + that.p.groupOps[i].op + "'" + selected + ">" + that.p.groupOps[i].text +
								"</option>";
						}
						groupOpSelect.append(str).bind('change', function() {
							group.groupOp = $(groupOpSelect).val();
							that.onchange(); // signals
							// that
							// the
							// filter
							// has
							// changed
						});
					}
					// button for adding a new subgroup
					var inputAddSubgroup = "<span></span>";
					if (this.p.groupButton) {
						inputAddSubgroup = $(
							"<input type='button' value='+ {}' title='Add subgroup' class='add-group ui-widget-content-grid ui-corner-all-grid'/>"
						);
						inputAddSubgroup.bind('click', function() {
							if (group.groups === undefined) {
								group.groups = [];
							}
							group.groups.push({
								groupOp: p.groupOps[0].op
								, rules: []
								, groups: []
							}); // adding a new group
							that.reDraw(); // the html has changed,
							// force reDraw
							that.onchange(); // signals that the
							// filter has
							// changed
							return false;
						});
					}
					th.append(inputAddSubgroup);
					if (this.p.ruleButtons === true) {
						// button for adding a new rule
						var inputAddRule = $(
								"<input type='button' value='+' title='Add rule' class='add-rule ui-add ui-widget-content-grid ui-corner-all-grid'/>"
							)
							, cm;
						inputAddRule.bind('click', function() {
							// if(!group) { group =
							// {};}
							if (group.rules === undefined) {
								group.rules = [];
							}
							for (i = 0; i < that.p.columns.length; i++) {
								// but show only
								// serchable and
								// serchhidden =
								// true fields
								var searchable = (that.p.columns[i].search === undefined) ? true : that.p.columns[i].search
									, hidden = (that.p.columns[i].hidden === true)
									, ignoreHiding = (that.p.columns[i].searchoptions.searchhidden === true);
								if ((ignoreHiding && searchable) || (searchable && !hidden)) {
									cm = that.p.columns[i];
									break;
								}
							}
							var opr;
							if (cm.searchoptions.sopt) {
								opr = cm.searchoptions.sopt;
							}
							else if (that.p.sopt) {
								opr = that.p.sopt;
							}
							else if ($.inArray(cm.searchtype, that.p.strarr) !== -1) {
								opr = that.p.stropts;
							}
							else {
								opr = that.p.numopts;
							}
							group.rules.push({
								field: cm.name
								, op: opr[0]
								, data: ""
							}); // adding a new rule
							that.reDraw(); // the
							// html
							// has
							// changed,
							// force
							// reDraw
							// for the moment no
							// change have been made
							// to the rule, so
							// this will not trigger
							// onchange event
							return false;
						});
						th.append(inputAddRule);
					}
					// button for delete the group
					if (parentgroup !== null) { // ignore the first
						// group
						var inputDeleteGroup = $(
							"<input type='button' value='-' title='Delete group' class='delete-group ui-widget-content-grid ui-corner-all-grid'/>"
						);
						th.append(inputDeleteGroup);
						inputDeleteGroup.bind('click', function() {
							// remove group from
							// parent
							for (i = 0; i < parentgroup.groups.length; i++) {
								if (parentgroup.groups[i] === group) {
									parentgroup.groups.splice(i, 1);
									break;
								}
							}
							that.reDraw(); // the
							// html
							// has
							// changed,
							// force
							// reDraw
							that.onchange(); // signals
							// that
							// the
							// filter
							// has
							// changed
							return false;
						});
					}
					// append subgroup rows
					if (group.groups !== undefined) {
						for (i = 0; i < group.groups.length; i++) {
							var trHolderForSubgroup = $("<tr></tr>");
							table.append(trHolderForSubgroup);
							var tdFirstHolderForSubgroup = $("<td class='first'></td>");
							trHolderForSubgroup.append(tdFirstHolderForSubgroup);
							var tdMainHolderForSubgroup = $("<td colspan='4'></td>");
							tdMainHolderForSubgroup.append(this.createTableForGroup(group.groups[i], group));
							trHolderForSubgroup.append(tdMainHolderForSubgroup);
						}
					}
					if (group.groupOp === undefined) {
						group.groupOp = that.p.groupOps[0].op;
					}
					// append rules rows
					if (group.rules !== undefined) {
						for (i = 0; i < group.rules.length; i++) {
							table.append(this.createTableRowForRule(group.rules[i], group));
						}
					}
					return table;
				};
				/*
				 * Create the rule data for the filter
				 */
				this.createTableRowForRule = function(rule, group) {
					// save current entity in a variable so that it
					// could
					// be referenced in anonimous method calls
					var that = this
						, $t = getGrid()
						, tr = $("<tr></tr>")
						, // document.createElement("tr"),
						// first column used for padding
						// tdFirstHolderForRule =
						// document.createElement("td"),
						i, op, trpar, cm, str = ""
						, selected;
					// tdFirstHolderForRule.setAttribute("class",
					// "first");
					tr.append("<td class='first'></td>");
					// create field container
					var ruleFieldTd = $("<td class='columns'></td>");
					tr.append(ruleFieldTd);
					// dropdown for: choosing field
					var ruleFieldSelect = $("<select class='ui-widget-content-grid ui-corner-all-grid'></select>")
						, ina, aoprs = [];
					ruleFieldTd.append(ruleFieldSelect);
					ruleFieldSelect.bind('change', function() {
						rule.field = $(ruleFieldSelect).val();
						trpar = $(this).parents("tr:first");
						for (i = 0; i < that.p.columns.length; i++) {
							if (that.p.columns[i].name === rule.field) {
								cm = that.p.columns[i];
								break;
							}
						}
						if (!cm) {
							return;
						}
						cm.searchoptions.id = $.wizgrid.randId();
						cm.searchoptions.name = rule.field;
						cm.searchoptions.oper = 'filter';
						if (isIE && cm.inputtype === "text") {
							if (!cm.searchoptions.size) {
								cm.searchoptions.size = 10;
							}
						}
						var elm = $.wizgrid.createEl.call($t, cm.inputtype, cm.searchoptions, "", true, that.p.ajaxSelectOptions || {}
							, true);
						$(elm).addClass("input-elm ui-widget-content-grid ui-corner-all-grid");
						// that.createElement(rule,
						// "");
						if (cm.searchoptions.sopt) {
							op = cm.searchoptions.sopt;
						}
						else if (that.p.sopt) {
							op = that.p.sopt;
						}
						else if ($.inArray(cm.searchtype, that.p.strarr) !== -1) {
							op = that.p.stropts;
						}
						else {
							op = that.p.numopts;
						}
						// operators
						var s = ""
							, so = 0;
						aoprs = [];
						$.each(that.p.ops, function() {
							aoprs.push(this.oper);
						});
						for (i = 0; i < op.length; i++) {
							ina = $.inArray(op[i], aoprs);
							if (ina !== -1) {
								if (so === 0) {
									rule.op = that.p.ops[ina].oper;
								}
								s += "<option value='" + that.p.ops[ina].oper + "'>" + that.p.ops[ina].text + "</option>";
								so++;
							}
						}
						$(".selectopts", trpar).empty().append(s);
						$(".selectopts", trpar)[0].selectedIndex = 0;
						if ($.wizgrid.msie && $.wizgrid.msiever() < 9) {
							var sw = parseInt($("select.selectopts", trpar)[0].offsetWidth, 10) + 1;
							$(".selectopts", trpar).width(sw);
							$(".selectopts", trpar).css("width", "auto");
						}
						// data
						$(".data", trpar).empty().append(elm);
						$.wizgrid.bindEv.call($t, elm, cm.searchoptions);
						$(".input-elm", trpar).bind('change', function(e) {
							var elem = e.target;
							rule.data = elem.nodeName.toUpperCase() === "SPAN" && cm.searchoptions && $.isFunction(cm.searchoptions
								.custom_value) ? cm.searchoptions.custom_value.call($t, $(elem).children(
								".customelement:first"), 'get') : elem.value;
							that.onchange(); // signals
							// that
							// the
							// filter
							// has
							// changed
						});
						setTimeout(function() { // IE,
							// Opera,
							// Chrome
							rule.data = $(elm).val();
							that.onchange(); // signals
							// that
							// the
							// filter
							// has
							// changed
						}, 0);
					});
					// populate drop down with user provided column
					// definitions
					var j = 0;
					for (i = 0; i < that.p.columns.length; i++) {
						// but show only serchable and serchhidden =
						// true fields
						var searchable = (that.p.columns[i].search === undefined) ? true : that.p.columns[i].search
							, hidden = (that.p.columns[i].hidden === true)
							, ignoreHiding = (that.p.columns[i].searchoptions.searchhidden === true);
						if ((ignoreHiding && searchable) || (searchable && !hidden)) {
							selected = "";
							if (rule.field === that.p.columns[i].name) {
								selected = " selected='selected'";
								j = i;
							}
							str += "<option value='" + that.p.columns[i].name + "'" + selected + ">" + that.p.columns[i].label +
								"</option>";
						}
					}
					ruleFieldSelect.append(str);
					// create operator container
					var ruleOperatorTd = $("<td class='operators'></td>");
					tr.append(ruleOperatorTd);
					cm = p.columns[j];
					// create it here so it can be referentiated in
					// the onchange event
					// var RD = that.createElement(rule, rule.data);
					cm.searchoptions.id = $.wizgrid.randId();
					if (isIE && cm.inputtype === "text") {
						if (!cm.searchoptions.size) {
							cm.searchoptions.size = 10;
						}
					}
					cm.searchoptions.name = rule.field;
					cm.searchoptions.oper = 'filter';
					var ruleDataInput = $.wizgrid.createEl.call($t, cm.inputtype, cm.searchoptions, rule.data, true
						, that.p.ajaxSelectOptions || {}, true);
					if (rule.op === 'nu' || rule.op === 'nn') {
						$(ruleDataInput).attr('readonly', 'true');
						$(ruleDataInput).attr('disabled', 'true');
					} // retain the state of disabled text fields
					// in case of null ops
					// dropdown for: choosing operator
					var ruleOperatorSelect = $(
						"<select class='selectopts ui-widget-content-grid ui-corner-all-grid'></select>");
					ruleOperatorTd.append(ruleOperatorSelect);
					ruleOperatorSelect.bind('change', function() {
						rule.op = $(ruleOperatorSelect).val();
						trpar = $(this).parents("tr:first");
						var rd = $(".input-elm", trpar)[0];
						if (rule.op === "nu" || rule.op === "nn") { // disable
							// for
							// operator
							// "is
							// null"
							// and
							// "is
							// not
							// null"
							rule.data = "";
							if (rd.tagName.toUpperCase() !== 'SELECT') {
								rd.value = "";
							}
							rd.setAttribute("readonly", "true");
							rd.setAttribute("disabled", "true");
						}
						else {
							if (rd.tagName.toUpperCase() === 'SELECT') {
								rule.data = rd.value;
							}
							rd.removeAttribute("readonly");
							rd.removeAttribute("disabled");
						}
						that.onchange(); // signals
						// that
						// the
						// filter
						// has
						// changed
					});
					// populate drop down with all available
					// operators
					if (cm.searchoptions.sopt) {
						op = cm.searchoptions.sopt;
					}
					else if (that.p.sopt) {
						op = that.p.sopt;
					}
					else if ($.inArray(cm.searchtype, that.p.strarr) !== -1) {
						op = that.p.stropts;
					}
					else {
						op = that.p.numopts;
					}
					str = "";
					$.each(that.p.ops, function() {
						aoprs.push(this.oper);
					});
					for (i = 0; i < op.length; i++) {
						ina = $.inArray(op[i], aoprs);
						if (ina !== -1) {
							selected = rule.op === that.p.ops[ina].oper ? " selected='selected'" : "";
							str += "<option value='" + that.p.ops[ina].oper + "'" + selected + ">" + that.p.ops[ina].text +
								"</option>";
						}
					}
					ruleOperatorSelect.append(str);
					// create data container
					var ruleDataTd = $("<td class='data'></td>");
					tr.append(ruleDataTd);
					// textbox for: data
					// is created previously
					// ruleDataInput.setAttribute("type", "text");
					ruleDataTd.append(ruleDataInput);
					$.wizgrid.bindEv.call($t, ruleDataInput, cm.searchoptions);
					$(ruleDataInput).addClass("input-elm ui-widget-content-grid ui-corner-all-grid").bind('change'
						, function() {
							rule.data = cm.inputtype === 'custom' ? cm.searchoptions.custom_value.call($t, $(this).children(
								".customelement:first"), 'get') : $(this).val();
							that.onchange(); // signals
							// that
							// the
							// filter
							// has
							// changed
						});
					// create action container
					var ruleDeleteTd = $("<td></td>");
					tr.append(ruleDeleteTd);
					// create button for: delete rule
					if (this.p.ruleButtons === true) {
						var ruleDeleteInput = $(
							"<input type='button' value='-' title='Delete rule' class='delete-rule ui-del ui-widget-content-grid ui-corner-all-grid'/>"
						);
						ruleDeleteTd.append(ruleDeleteInput);
						// $(ruleDeleteInput).html("").height(20).width(30).button({icons:
						// { primary: "ui-icon-minus",
						// text:false}});
						ruleDeleteInput.bind('click', function() {
							// remove rule from
							// group
							for (i = 0; i < group.rules.length; i++) {
								if (group.rules[i] === rule) {
									group.rules.splice(i, 1);
									break;
								}
							}
							that.reDraw(); // the
							// html
							// has
							// changed,
							// force
							// reDraw
							that.onchange(); // signals
							// that
							// the
							// filter
							// has
							// changed
							return false;
						});
					}
					return tr;
				};
				this.getStringForGroup = function(group) {
					var s = "("
						, index;
					if (group.groups !== undefined) {
						for (index = 0; index < group.groups.length; index++) {
							if (s.length > 1) {
								s += " " + group.groupOp + " ";
							}
							try {
								s += this.getStringForGroup(group.groups[index]);
							}
							catch (eg) {
								alert(eg);
							}
						}
					}
					if (group.rules !== undefined) {
						try {
							for (index = 0; index < group.rules.length; index++) {
								if (s.length > 1) {
									s += " " + group.groupOp + " ";
								}
								s += this.getStringForRule(group.rules[index]);
							}
						}
						catch (e) {
							alert(e);
						}
					}
					s += ")";
					if (s === "()") {
						return ""; // ignore groups that don't have
						// rules
					}
					return s;
				};
				this.getStringForRule = function(rule) {
					var opUF = ""
						, opC = ""
						, i, cm, ret, val, numtypes = ['int', 'integer', 'float', 'number', 'currency']; // wizGrid
					for (i = 0; i < this.p.ops.length; i++) {
						if (this.p.ops[i].oper === rule.op) {
							opUF = this.p.operands.hasOwnProperty(rule.op) ? this.p.operands[rule.op] : "";
							opC = this.p.ops[i].oper;
							break;
						}
					}
					for (i = 0; i < this.p.columns.length; i++) {
						if (this.p.columns[i].name === rule.field) {
							cm = this.p.columns[i];
							break;
						}
					}
					if (cm === undefined) {
						return "";
					}
					val = rule.data;
					if (opC === 'bw' || opC === 'bn') {
						val = val + "%";
					}
					if (opC === 'ew' || opC === 'en') {
						val = "%" + val;
					}
					if (opC === 'cn' || opC === 'nc') {
						val = "%" + val + "%";
					}
					if (opC === 'in' || opC === 'ni') {
						val = " (" + val + ")";
					}
					if (p.errorcheck) {
						checkData(rule.data, cm);
					}
					if ($.inArray(cm.searchtype, numtypes) !== -1 || opC === 'nn' || opC === 'nu') {
						ret = rule.field + " " + opUF + " " + val;
					}
					else {
						ret = rule.field + " " + opUF + " \"" + val + "\"";
					}
					return ret;
				};
				this.resetFilter = function() {
					this.p.filter = $.extend(true, {}, this.p.initFilter);
					this.reDraw();
					this.onchange();
				};
				this.hideError = function() {
					$("th.ui-state-error", this).html("");
					$("tr.error", this).hide();
				};
				this.showError = function() {
					$("th.ui-state-error", this).html(this.p.errmsg);
					$("tr.error", this).show();
				};
				this.toUserFriendlyString = function() {
					return this.getStringForGroup(p.filter);
				};
				this.toString = function() {
					// this will obtain a string that can be used to
					// match an item.
					var that = this;

					function getStringRule(rule) {
						if (that.p.errorcheck) {
							var i, cm;
							for (i = 0; i < that.p.columns.length; i++) {
								if (that.p.columns[i].name === rule.field) {
									cm = that.p.columns[i];
									break;
								}
							}
							if (cm) {
								checkData(rule.data, cm);
							}
						}
						return rule.op + "(item." + rule.field + ",'" + rule.data + "')";
					}

					function getStringForGroup(group) {
						var s = "("
							, index;
						if (group.groups !== undefined) {
							for (index = 0; index < group.groups.length; index++) {
								if (s.length > 1) {
									if (group.groupOp === "OR") {
										s += " || ";
									}
									else {
										s += " && ";
									}
								}
								s += getStringForGroup(group.groups[index]);
							}
						}
						if (group.rules !== undefined) {
							for (index = 0; index < group.rules.length; index++) {
								if (s.length > 1) {
									if (group.groupOp === "OR") {
										s += " || ";
									}
									else {
										s += " && ";
									}
								}
								s += getStringRule(group.rules[index]);
							}
						}
						s += ")";
						if (s === "()") {
							return ""; // ignore groups that don't
							// have rules
						}
						return s;
					}
					return getStringForGroup(this.p.filter);
				};
				// Here we init the filter
				this.reDraw();
				if (this.p.showQuery) {
					this.onchange();
				}
				// mark is as created so that it will not be created
				// twice on this element
				this.filter = true;
			});
		};
		$.extend($.fn.wizFilter, {
			/*
			 * Return SQL like string. Can be used directly
			 */
			toSQLString: function() {
				var s = "";
				this.each(function() {
					s = this.toUserFriendlyString();
				});
				return s;
			}
			, /*
			 * Return filter data as object.
			 */
			filterData: function() {
				var s;
				this.each(function() {
					s = this.p.filter;
				});
				return s;
			}
			, getParameter: function(param) {
				if (param !== undefined) {
					if (this.p.hasOwnProperty(param)) {
						return this.p[param];
					}
				}
				return this.p;
			}
			, resetFilter: function() {
				return this.each(function() {
					this.resetFilter();
				});
			}
			, addFilter: function(pfilter) {
				if (typeof pfilter === "string") {
					pfilter = $.wizgrid.parse(pfilter);
				}
				this.each(function() {
					this.p.filter = pfilter;
					this.reDraw();
					this.onchange();
				});
			}
		});
		$.wizgrid.extend({
			filterToolbar: function(p) {
				var regional = $.wizgrid.getRegional(this[0], 'search');
				p = $.extend({
					autosearch: true
					, autosearchDelay: 500
					, searchOnEnter: true
					, beforeSearch: null
					, afterSearch: null
					, beforeClear: null
					, afterClear: null
					, searchurl: ''
					, stringResult: false
					, groupOp: 'AND'
					, defaultSearch: "bw"
					, searchOperators: false
					, resetIcon: "x"
					, operands: {
						"eq": "=="
						, "ne": "!"
						, "lt": "<"
						, "le": "<="
						, "gt": ">"
						, "ge": ">="
						, "bw": "^"
						, "bn": "!^"
						, "in": "="
						, "ni": "!="
						, "ew": "|"
						, "en": "!@"
						, "cn": "~"
						, "nc": "!~"
						, "nu": "#"
						, "nn": "!#"
					}
				}, regional, p || {});
				return this.each(function() {
					var $t = this;
					if ($t.p.filterToolbar) {
						return;
					}
					if (!$($t).data('filterToolbar')) {
						$($t).data('filterToolbar', p);
					}
					if ($t.p.force_regional) {
						p = $.extend(p, regional);
					}
					var triggerToolbar = function() {
							var sdata = {}
								, j = 0
								, v, nm, sopt = {}
								, so;
							$.each($t.p.Columns, function() {
								var $elem = $("#gs_" + $t.p.idPrefix + $.wizgrid.wizID(this.name), (this.frozen === true &&
									$t.p.frozenColumns === true) ? $t.grid.fhDiv : $t.grid.hDiv);
								nm = this.index || this.name;
								if (p.searchOperators) {
									so = $elem.parent().prev().children("a").attr("soper") || p.defaultSearch;
								}
								else {
									so = (this.searchoptions && this.searchoptions.sopt) ? this.searchoptions.sopt[0] : this.stype ===
										'select' ? 'eq' : p.defaultSearch;
								}
								v = this.stype === "custom" && $.isFunction(this.searchoptions.custom_value) && $elem.length >
									0 && $elem[0].nodeName.toUpperCase() === "SPAN" ? this.searchoptions.custom_value.call($t
										, $elem.children(".customelement:first"), "get") : $elem.val();
								if (v || so === "nu" || so === "nn") {
									sdata[nm] = v;
									sopt[nm] = so;
									j++;
								}
								else {
									try {
										delete $t.p.postData[nm];
									}
									catch (z) {}
								}
							});
							var sd = j > 0 ? true : false;
							if (p.stringResult === true || $t.p.datatype === "local" || p.searchOperators === true) {
								var ruleGroup = "{\"groupOp\":\"" + p.groupOp + "\",\"rules\":[";
								var gi = 0;
								$.each(sdata, function(i, n) {
									if (gi > 0) {
										ruleGroup += ",";
									}
									ruleGroup += "{\"field\":\"" + i + "\",";
									ruleGroup += "\"op\":\"" + sopt[i] + "\",";
									n += "";
									ruleGroup += "\"data\":\"" + n.replace(/\\/g, '\\\\').replace(/\"/g, '\\"') + "\"}";
									gi++;
								});
								ruleGroup += "]}";
								$.extend($t.p.postData, {
									filters: ruleGroup
								});
								$.each(
									['searchField', 'searchString', 'searchOper']
									, function(i, n) {
										if ($t.p.postData.hasOwnProperty(n)) {
											delete $t.p.postData[n];
										}
									});
							}
							else {
								$.extend($t.p.postData, sdata);
							}
							var saveurl;
							if ($t.p.searchurl) {
								saveurl = $t.p.url;
								$($t).wizGrid("setGridParam", {
									url: $t.p.searchurl
								});
							}
							var bsr = $($t).triggerHandler("wizGridToolbarBeforeSearch") === 'stop' ? true : false;
							if (!bsr && $.isFunction(p.beforeSearch)) {
								bsr = p.beforeSearch.call($t);
							}
							if (!bsr) {
								$($t).wizGrid("setGridParam", {
									search: sd
								}).trigger("reloadGrid", [{
									page: 1
								}]);
							}
							if (saveurl) {
								$($t).wizGrid("setGridParam", {
									url: saveurl
								});
							}
							$($t).triggerHandler("wizGridToolbarAfterSearch");
							if ($.isFunction(p.afterSearch)) {
								p.afterSearch.call($t);
							}
						}
						, clearToolbar = function(trigger) {
							var sdata = {}
								, j = 0
								, nm;
							trigger = (typeof trigger !== 'boolean') ? true : trigger;
							$.each($t.p.Columns, function() {
								var v, $elem = $("#gs_" + $t.p.idPrefix + $.wizgrid.wizID(this.name), (this.frozen === true &&
									$t.p.frozenColumns === true) ? $t.grid.fhDiv : $t.grid.hDiv);
								if (this.searchoptions && this.searchoptions.defaultValue !== undefined) {
									v = this.searchoptions.defaultValue;
								}
								nm = this.index || this.name;
								switch (this.stype) {
									case 'select':
										$elem.find("option").each(function(i) {
											if (i === 0) {
												this.selected = true;
											}
											if ($(this).val() === v) {
												this.selected = true;
												return false;
											}
										});
										if (v !== undefined) {
											// post
											// the
											// key
											// and
											// not
											// the
											// text
											sdata[nm] = v;
											j++;
										}
										else {
											try {
												delete $t.p.postData[nm];
											}
											catch (e) {}
										}
										break;
									case 'text':
										$elem.val(v || "");
										if (v !== undefined) {
											sdata[nm] = v;
											j++;
										}
										else {
											try {
												delete $t.p.postData[nm];
											}
											catch (y) {}
										}
										break;
									case 'custom':
										if ($.isFunction(this.searchoptions.custom_value) && $elem.length > 0 && $elem[0].nodeName.toUpperCase() ===
											"SPAN") {
											this.searchoptions.custom_value.call($t, $elem.children(".customelement:first"), "set", v ||
												"");
										}
										break;
								}
							});
							var sd = j > 0 ? true : false;
							$t.p.resetsearch = true;
							if (p.stringResult === true || $t.p.datatype === "local") {
								var ruleGroup = "{\"groupOp\":\"" + p.groupOp + "\",\"rules\":[";
								var gi = 0;
								$.each(sdata, function(i, n) {
									if (gi > 0) {
										ruleGroup += ",";
									}
									ruleGroup += "{\"field\":\"" + i + "\",";
									ruleGroup += "\"op\":\"" + "eq" + "\",";
									n += "";
									ruleGroup += "\"data\":\"" + n.replace(/\\/g, '\\\\').replace(/\"/g, '\\"') + "\"}";
									gi++;
								});
								ruleGroup += "]}";
								$.extend($t.p.postData, {
									filters: ruleGroup
								});
								$.each(
									['searchField', 'searchString', 'searchOper']
									, function(i, n) {
										if ($t.p.postData.hasOwnProperty(n)) {
											delete $t.p.postData[n];
										}
									});
							}
							else {
								$.extend($t.p.postData, sdata);
							}
							var saveurl;
							if ($t.p.searchurl) {
								saveurl = $t.p.url;
								$($t).wizGrid("setGridParam", {
									url: $t.p.searchurl
								});
							}
							var bcv = $($t).triggerHandler("wizGridToolbarBeforeClear") === 'stop' ? true : false;
							if (!bcv && $.isFunction(p.beforeClear)) {
								bcv = p.beforeClear.call($t);
							}
							if (!bcv) {
								if (trigger) {
									$($t).wizGrid("setGridParam", {
										search: sd
									}).trigger("reloadGrid", [{
										page: 1
									}]);
								}
							}
							if (saveurl) {
								$($t).wizGrid("setGridParam", {
									url: saveurl
								});
							}
							$($t).triggerHandler("wizGridToolbarAfterClear");
							if ($.isFunction(p.afterClear)) {
								p.afterClear();
							}
						}
						, toggleToolbar = function() {
							var trow = $("tr.ui-search-toolbar", $t.grid.hDiv)
								, trow2 = $t.p.frozenColumns === true ? $("tr.ui-search-toolbar", $t.grid.fhDiv) : false;
							if (trow.css("display") === 'none') {
								trow.show();
								if (trow2) {
									trow2.show();
								}
							}
							else {
								trow.hide();
								if (trow2) {
									trow2.hide();
								}
							}
						}
						, buildRuleMenu = function(elem, left, top) {
							$("#sopt_menu").remove();
							left = parseInt(left, 10);
							top = parseInt(top, 10) + 18;
							var fs = $('.ui-wizgrid-view').css('font-size') || '11px';
							var str = '<ul id="sopt_menu" class="ui-search-menu" role="menu" tabindex="0" style="font-size:' +
								fs + ';left:' + left + 'px;top:' + top + 'px;">'
								, selected = $(elem).attr("soper")
								, selclass, aoprs = []
								, ina;
							var i = 0
								, nm = $(elem).attr("colname")
								, len = $t.p.Columns.length;
							while (i < len) {
								if ($t.p.Columns[i].name === nm) {
									break;
								}
								i++;
							}
							var cm = $t.p.Columns[i]
								, options = $.extend({}, cm.searchoptions);
							if (!options.sopt) {
								options.sopt = [];
								options.sopt[0] = cm.stype === 'select' ? 'eq' : p.defaultSearch;
							}
							$.each(p.odata, function() {
								aoprs.push(this.oper);
							});
							for (i = 0; i < options.sopt.length; i++) {
								ina = $.inArray(options.sopt[i], aoprs);
								if (ina !== -1) {
									selclass = selected === p.odata[ina].oper ? "ui-state-highlight-grid" : "";
									str += '<li class="ui-menu-item ' + selclass +
										'" role="presentation"><a class="ui-corner-all g-menu-item" tabindex="0" role="menuitem" value="' +
										p.odata[ina].oper + '" oper="' + p.operands[p.odata[ina].oper] +
										'"><table cellspacing="0" cellpadding="0" border="0"><tr><td width="25px">' + p.operands[p.odata[
											ina].oper] + '</td><td>' + p.odata[ina].text + '</td></tr></table></a></li>';
								}
							}
							str += "</ul>";
							$('body').append(str);
							$("#sopt_menu").addClass("ui-menu ui-widget ui-widget-content-grid ui-corner-all-grid");
							$("#sopt_menu > li > a").hover(function() {
								$(this).addClass("ui-state-hover-grid");
							}, function() {
								$(this).removeClass("ui-state-hover-grid");
							}).click(function() {
								var v = $(this).attr("value")
									, oper = $(this).attr("oper");
								$($t).triggerHandler("wizGridToolbarSelectOper", [
									v
									, oper
									, elem
								]);
								$("#sopt_menu").hide();
								$(elem).text(oper).attr("soper", v);
								if (p.autosearch === true) {
									var inpelm = $(elem).parent().next().children()[0];
									if ($(inpelm).val() || v === "nu" || v === "nn") {
										triggerToolbar();
									}
								}
							});
						};
					// create the row
					var tr = $("<tr class='ui-search-toolbar' role='row'></tr>");
					var timeoutHnd;
					$.each($t.p.Columns, function(ci) {
						var cm = this
							, soptions, select = ""
							, sot = "="
							, so, i, st, csv, df, elem, th = $(
								"<th role='columnheader' class='ui-state-default-grid ui-th-" + $t.p.direction + "' id='gsh_" +
								$t.p.id + "_" + cm.name + "' ></th>")
							, thd = $("<div></div>")
							, stbl = $(
								"<table class='ui-search-table' cellspacing='0'><tr><td class='ui-search-oper' headers=''></td><td class='ui-search-input' headers=''></td><td class='ui-search-clear' headers=''></td></tr></table>"
							);
						if (this.hidden === true) {
							$(th).css("display", "none");
						}
						this.search = this.search === false ? false : true;
						if (this.stype === undefined) {
							this.stype = 'text';
						}
						soptions = $.extend({}, this.searchoptions || {}, {
							name: cm.index || cm.name
							, id: "gs_" + $t.p.idPrefix + cm.name
							, oper: 'search'
						});
						if (this.search) {
							if (p.searchOperators) {
								so = (soptions.sopt) ? soptions.sopt[0] : cm.stype === 'select' ? 'eq' : p.defaultSearch;
								for (i = 0; i < p.odata.length; i++) {
									if (p.odata[i].oper === so) {
										sot = p.operands[so] || "";
										break;
									}
								}
								st = soptions.searchtitle != null ? soptions.searchtitle : p.operandTitle;
								select = "<a title='" + st + "' style='padding-right: 0.5em;' soper='" + so +
									"' class='soptclass' colname='" + this.name + "'>" + sot + "</a>";
							}
							$("td:eq(0)", stbl).attr("colindex", ci).append(select);
							if (soptions.clearSearch === undefined) {
								soptions.clearSearch = true;
							}
							if (soptions.clearSearch) {
								csv = p.resetTitle || 'Clear Search Value';
								$("td:eq(2)", stbl).append("<a title='" + csv +
									"' style='padding-right: 0.3em;padding-left: 0.3em;' class='clearsearchclass'>" + p.resetIcon +
									"</a>");
							}
							else {
								$("td:eq(2)", stbl).hide();
							}
							if (this.surl) {
								soptions.dataUrl = this.surl;
							}
							df = "";
							if (soptions.defaultValue) {
								df = $.isFunction(soptions.defaultValue) ? soptions.defaultValue.call($t) : soptions.defaultValue;
							}
							elem = $.wizgrid.createEl.call($t, this.stype, soptions, df, false, $.extend({}, $.wizgrid.ajaxOptions
								, $t.p.ajaxSelectOptions || {}));
							$(elem).css({
								width: "100%"
							}).addClass("ui-widget-content-grid ui-corner-all-grid");
							$("td:eq(1)", stbl).append(elem);
							$(thd).append(stbl);
							switch (this.stype) {
								case "select":
									if (p.autosearch === true) {
										soptions.dataEvents = [{
											type: "change"
											, fn: function() {
												triggerToolbar();
												return false;
											}
										}];
									}
									break;
								case "text":
									if (p.autosearch === true) {
										if (p.searchOnEnter) {
											soptions.dataEvents = [{
												type: "keypress"
												, fn: function(e) {
													var key = e.charCode || e.keyCode || 0;
													if (key === 13) {
														triggerToolbar();
														return false;
													}
													return this;
												}
											}];
										}
										else {
											soptions.dataEvents = [{
												type: "keydown"
												, fn: function(e) {
													var key = e.which;
													switch (key) {
														case 13:
															return false;
														case 9:
														case 16:
														case 37:
														case 38:
														case 39:
														case 40:
														case 27:
															break;
														default:
															if (timeoutHnd) {
																clearTimeout(timeoutHnd);
															}
															timeoutHnd = setTimeout(function() {
																triggerToolbar();
															}, p.autosearchDelay);
													}
												}
											}];
										}
									}
									break;
							}
							$.wizgrid.bindEv.call($t, elem, soptions);
						}
						$(th).append(thd);
						$(tr).append(th);
						if (!p.searchOperators) {
							$("td:eq(0)", stbl).hide();
						}
					});
					$("table thead", $t.grid.hDiv).append(tr);
					if (p.searchOperators) {
						$(".soptclass", tr).click(function(e) {
							var offset = $(this).offset()
								, left = (offset.left)
								, top = (offset.top);
							buildRuleMenu(this, left, top);
							e.stopPropagation();
						});
						$("body").on('click', function(e) {
							if (e.target.className !== "soptclass") {
								$("#sopt_menu").hide();
							}
						});
					}
					$(".clearsearchclass", tr).click(function() {
						var ptr = $(this).parents("tr:first")
							, coli = parseInt($("td.ui-search-oper", ptr).attr('colindex'), 10)
							, sval = $.extend({}, $t.p.Columns[coli].searchoptions || {})
							, dval = sval.defaultValue ? sval.defaultValue : "";
						if ($t.p.Columns[coli].stype === "select") {
							if (dval) {
								$("td.ui-search-input select", ptr).val(dval);
							}
							else {
								$("td.ui-search-input select", ptr)[0].selectedIndex = 0;
							}
						}
						else {
							$("td.ui-search-input input", ptr).val(dval);
						}
						// ToDo custom
						// search type
						if (p.autosearch === true) {
							triggerToolbar();
						}
					});
					this.p.filterToolbar = true;
					this.triggerToolbar = triggerToolbar;
					this.clearToolbar = clearToolbar;
					this.toggleToolbar = toggleToolbar;
				});
			}
			, destroyFilterToolbar: function() {
				return this.each(function() {
					if (!this.p.filterToolbar) {
						return;
					}
					this.triggerToolbar = null;
					this.clearToolbar = null;
					this.toggleToolbar = null;
					this.p.filterToolbar = false;
					$(this.grid.hDiv).find("table thead tr.ui-search-toolbar").remove();
				});
			}
			, searchGrid: function(p) {
				var regional = $.wizgrid.getRegional(this[0], 'search');
				p = $.extend(true, {
					recreateFilter: false
					, drag: true
					, sField: 'searchField'
					, sValue: 'searchString'
					, sOper: 'searchOper'
					, sFilter: 'filters'
					, loadDefaults: true, // this options
					// activates loading of
					// default filters from
					// grid's postData for
					// Multipe Search only.
					beforeShowSearch: null
					, afterShowSearch: null
					, onInitializeSearch: null
					, afterRedraw: null
					, afterChange: null
					, closeAfterSearch: false
					, closeAfterReset: false
					, closeOnEscape: false
					, searchOnEnter: false
					, multipleSearch: false
					, multipleGroup: false
					, // cloneSearchRowOnAdd: true,
					top: 0
					, left: 0
					, wizModal: true
					, modal: false
					, resize: true
					, width: 450
					, height: 'auto'
					, dataheight: 'auto'
					, showQuery: false
					, errorcheck: true
					, sopt: null
					, stringResult: undefined
					, onClose: null
					, onSearch: null
					, onReset: null
					, toTop: true
					, overlay: 30
					, columns: []
					, tmplNames: null
					, tmplFilters: null
					, tmplLabel: ' Template: '
					, showOnLoad: false
					, layer: null
					, operands: {
						"eq": "="
						, "ne": "<>"
						, "lt": "<"
						, "le": "<="
						, "gt": ">"
						, "ge": ">="
						, "bw": "LIKE"
						, "bn": "NOT LIKE"
						, "in": "IN"
						, "ni": "NOT IN"
						, "ew": "LIKE"
						, "en": "NOT LIKE"
						, "cn": "LIKE"
						, "nc": "NOT LIKE"
						, "nu": "IS NULL"
						, "nn": "ISNOT NULL"
					}
				}, regional, p || {});
				return this.each(function() {
					var $t = this;
					if (!$t.grid) {
						return;
					}
					var fid = "fbox_" + $t.p.id
						, showFrm = true
						, mustReload = true
						, IDs = {
							themodal: 'searchmod' + fid
							, modalhead: 'searchhd' + fid
							, modalcontent: 'searchcnt' + fid
							, scrollelm: fid
						}
						, defaultFilters = $t.p.postData[p.sFilter]
						, fl;
					if (typeof defaultFilters === "string") {
						defaultFilters = $.wizgrid.parse(defaultFilters);
					}
					if (p.recreateFilter === true) {
						$("#" + $.wizgrid.wizID(IDs.themodal)).remove();
					}

					function showFilter(_filter) {
						showFrm = $($t).triggerHandler("wizGridFilterBeforeShow", [_filter]);
						if (showFrm === undefined) {
							showFrm = true;
						}
						if (showFrm && $.isFunction(p.beforeShowSearch)) {
							showFrm = p.beforeShowSearch.call($t, _filter);
						}
						if (showFrm) {
							$.wizgrid.viewModal("#" + $.wizgrid.wizID(IDs.themodal), {
								gbox: "#gbox_" + $.wizgrid.wizID(fid)
								, wizm: p.wizModal
								, modal: p.modal
								, overlay: p.overlay
								, toTop: p.toTop
							});
							$($t).triggerHandler("wizGridFilterAfterShow", [_filter]);
							if ($.isFunction(p.afterShowSearch)) {
								p.afterShowSearch.call($t, _filter);
							}
						}
					}
					if ($("#" + $.wizgrid.wizID(IDs.themodal))[0] !== undefined) {
						showFilter($("#fbox_" + $.wizgrid.wizID($t.p.id)));
					}
					else {
						var fil = $("<div><div id='" + fid + "' class='searchFilter' style='overflow:auto'></div></div>")
							.insertBefore("#gview_" + $.wizgrid.wizID($t.p.id))
							, align = "left"
							, butleft = "";
						if ($t.p.direction === "rtl") {
							align = "right";
							butleft = " style='text-align:left'";
							fil.attr("dir", "rtl");
						}
						var columns = $.extend([], $t.p.Columns)
							, bS = "<a id='" + fid +
							"_search' class='fm-button ui-state-default-grid ui-corner-all-grid fm-button-icon-right ui-reset'><span class='ui-icon ui-icon-search'></span>" +
							p.Find + "</a>"
							, bC = "<a id='" + fid +
							"_reset' class='fm-button ui-state-default-grid ui-corner-all-grid fm-button-icon-left ui-search'><span class='ui-icon ui-icon-arrowreturnthick-1-w'></span>" +
							p.Reset + "</a>"
							, bQ = ""
							, tmpl = ""
							, colnm, found = false
							, bt, cmi = -1;
						if (p.showQuery) {
							bQ = "<a id='" + fid +
								"_query' class='fm-button ui-state-default-grid ui-corner-all-grid fm-button-icon-left'><span class='ui-icon ui-icon-comment'></span>Query</a>";
						}
						if (!p.columns.length) {
							$.each(columns, function(i, n) {
								if (!n.label) {
									n.label = $t.p.Names[i];
								}
								// find
								// first
								// searchable
								// column
								// and set
								// it if no
								// default
								// filter
								if (!found) {
									var searchable = (n.search === undefined) ? true : n.search
										, hidden = (n.hidden === true)
										, ignoreHiding = (n.searchoptions && n.searchoptions.searchhidden === true);
									if ((ignoreHiding && searchable) || (searchable && !hidden)) {
										found = true;
										colnm = n.index || n.name;
										cmi = i;
									}
								}
							});
						}
						else {
							columns = p.columns;
							cmi = 0;
							colnm = columns[0].index || columns[0].name;
						}
						// old behaviour
						if ((!defaultFilters && colnm) || p.multipleSearch === false) {
							var cmop = "eq";
							if (cmi >= 0 && columns[cmi].searchoptions && columns[cmi].searchoptions.sopt) {
								cmop = columns[cmi].searchoptions.sopt[0];
							}
							else if (p.sopt && p.sopt.length) {
								cmop = p.sopt[0];
							}
							defaultFilters = {
								groupOp: "AND"
								, rules: [{
									field: colnm
									, op: cmop
									, data: ""
								}]
							};
						}
						found = false;
						if (p.tmplNames && p.tmplNames.length) {
							found = true;
							tmpl = p.tmplLabel;
							tmpl += "<select class='ui-template'>";
							tmpl += "<option value='default'>Default</option>";
							$.each(p.tmplNames, function(i, n) {
								tmpl += "<option value='" + i + "'>" + n + "</option>";
							});
							tmpl += "</select>";
						}
						bt = "<table class='EditTable' style='border:0px none;margin-top:5px' id='" + fid +
							"_2'><tbody><tr><td colspan='2'><hr class='ui-widget-content-grid' style='margin:1px'/></td></tr><tr><td class='EditButton' style='text-align:" +
							align + "'>" + bC + tmpl + "</td><td class='EditButton' " + butleft + ">" + bQ + bS +
							"</td></tr></tbody></table>";
						fid = $.wizgrid.wizID(fid);
						$("#" + fid).wizFilter({
							columns: columns
							, filter: p.loadDefaults ? defaultFilters : null
							, showQuery: p.showQuery
							, errorcheck: p.errorcheck
							, sopt: p.sopt
							, groupButton: p.multipleGroup
							, ruleButtons: p.multipleSearch
							, afterRedraw: p.afterRedraw
							, ops: p.odata
							, operands: p.operands
							, ajaxSelectOptions: $t.p.ajaxSelectOptions
							, groupOps: p.groupOps
							, onChange: function() {
								if (this.p.showQuery) {
									$('.query', this).html(this.toUserFriendlyString());
								}
								if ($.isFunction(p.afterChange)) {
									p.afterChange.call($t, $("#" + fid), p);
								}
							}
							, direction: $t.p.direction
							, id: $t.p.id
						});
						fil.append(bt);
						if (found && p.tmplFilters && p.tmplFilters.length) {
							$(".ui-template", fil).bind('change', function() {
								var curtempl = $(this).val();
								if (curtempl === "default") {
									$("#" + fid).wizFilter('addFilter', defaultFilters);
								}
								else {
									$("#" + fid).wizFilter('addFilter', p.tmplFilters[parseInt(curtempl, 10)]);
								}
								return false;
							});
						}
						if (p.multipleGroup === true) {
							p.multipleSearch = true;
						}
						$($t).triggerHandler("wizGridFilterInitialize", [$("#" + fid)]);
						if ($.isFunction(p.onInitializeSearch)) {
							p.onInitializeSearch.call($t, $("#" + fid));
						}
						p.gbox = "#gbox_" + fid;
						if (p.layer) {
							$.wizgrid.createModal(IDs, fil, p, "#gview_" + $.wizgrid.wizID($t.p.id), $("#gbox_" + $.wizgrid.wizID(
								$t.p.id))[0], "#" + $.wizgrid.wizID(p.layer), {
								position: "relative"
							});
						}
						else {
							$.wizgrid.createModal(IDs, fil, p, "#gview_" + $.wizgrid.wizID($t.p.id), $("#gbox_" + $.wizgrid.wizID(
								$t.p.id))[0]);
						}
						if (p.searchOnEnter || p.closeOnEscape) {
							$("#" + $.wizgrid.wizID(IDs.themodal)).keydown(function(e) {
								var $target = $(e.target);
								if (p.searchOnEnter && e.which === 13 && // 13
									// ===
									// $.ui.keyCode.ENTER
									!$target.hasClass('add-group') && !$target.hasClass('add-rule') && !$target.hasClass(
										'delete-group') && !$target.hasClass('delete-rule') && (!$target.hasClass("fm-button") || !
										$target.is("[id$=_query]"))) {
									$("#" + fid + "_search").click();
									return false;
								}
								if (p.closeOnEscape && e.which === 27) { // 27
									// ===
									// $.ui.keyCode.ESCAPE
									$("#" + $.wizgrid.wizID(IDs.modalhead)).find("..ui-wizdialog-titlebar-close").click();
									return false;
								}
							});
						}
						if (bQ) {
							$("#" + fid + "_query").bind('click', function() {
								$(".queryresult", fil).toggle();
								return false;
							});
						}
						if (p.stringResult === undefined) {
							// to provide backward
							// compatibility, inferring
							// stringResult value from
							// multipleSearch
							p.stringResult = p.multipleSearch;
						}
						$("#" + fid + "_search").bind('click', function() {
							var sdata = {}
								, res, filters;
							fl = $("#" + fid);
							fl.find(".input-elm:focus").change();
							filters = fl.wizFilter('filterData');
							if (p.errorcheck) {
								fl[0].hideError();
								if (!p.showQuery) {
									fl.wizFilter('toSQLString');
								}
								if (fl[0].p.error) {
									fl[0].showError();
									return false;
								}
							}
							if (p.stringResult) {
								try {
									res = JSON.stringify(filters);
								}
								catch (e2) {}
								if (typeof res === "string") {
									sdata[p.sFilter] = res;
									$.each(
										[
											p.sField
											, p.sValue
											, p.sOper
										]
										, function() {
											sdata[this] = "";
										});
								}
							}
							else {
								if (p.multipleSearch) {
									sdata[p.sFilter] = filters;
									$.each(
										[
											p.sField
											, p.sValue
											, p.sOper
										]
										, function() {
											sdata[this] = "";
										});
								}
								else {
									sdata[p.sField] = filters.rules[0].field;
									sdata[p.sValue] = filters.rules[0].data;
									sdata[p.sOper] = filters.rules[0].op;
									sdata[p.sFilter] = "";
								}
							}
							$t.p.search = true;
							$.extend($t.p.postData, sdata);
							mustReload = $($t).triggerHandler("wizGridFilterSearch");
							if (mustReload === undefined) {
								mustReload = true;
							}
							if (mustReload && $.isFunction(p.onSearch)) {
								mustReload = p.onSearch.call($t, $t.p.filters);
							}
							if (mustReload !== false) {
								$($t).trigger("reloadGrid", [{
									page: 1
								}]);
							}
							if (p.closeAfterSearch) {
								$.wizgrid.hideModal("#" + $.wizgrid.wizID(IDs.themodal), {
									gb: "#gbox_" + $.wizgrid.wizID($t.p.id)
									, wizm: p.wizModal
									, onClose: p.onClose
								});
							}
							return false;
						});
						$("#" + fid + "_reset").bind('click', function() {
							var sdata = {}
								, fl = $("#" + fid);
							$t.p.search = false;
							$t.p.resetsearch = true;
							if (p.multipleSearch === false) {
								sdata[p.sField] = sdata[p.sValue] = sdata[p.sOper] = "";
							}
							else {
								sdata[p.sFilter] = "";
							}
							fl[0].resetFilter();
							if (found) {
								$(".ui-template", fil).val("default");
							}
							$.extend($t.p.postData, sdata);
							mustReload = $($t).triggerHandler("wizGridFilterReset");
							if (mustReload === undefined) {
								mustReload = true;
							}
							if (mustReload && $.isFunction(p.onReset)) {
								mustReload = p.onReset.call($t);
							}
							if (mustReload !== false) {
								$($t).trigger("reloadGrid", [{
									page: 1
								}]);
							}
							if (p.closeAfterReset) {
								$.wizgrid.hideModal("#" + $.wizgrid.wizID(IDs.themodal), {
									gb: "#gbox_" + $.wizgrid.wizID($t.p.id)
									, wizm: p.wizModal
									, onClose: p.onClose
								});
							}
							return false;
						});
						showFilter($("#" + fid));
						$(".fm-button:not(.ui-state-disabled)", fil).hover(function() {
							$(this).addClass('ui-state-hover-grid');
						}, function() {
							$(this).removeClass('ui-state-hover-grid');
						});
					}
				});
			}
		});
		// module begin
		var rp_ge = {};
		$.wizgrid.extend({
			editGridRow: function(rowid, p) {
				var regional = $.wizgrid.getRegional(this[0], 'edit');
				p = $.extend(true, {
					top: 0
					, left: 0
					, width: 300
					, datawidth: 'auto'
					, height: 'auto'
					, dataheight: 'auto'
					, modal: false
					, overlay: 30
					, drag: true
					, resize: true
					, url: null
					, mtype: "POST"
					, clearAfterAdd: true
					, closeAfterEdit: false
					, reloadAfterSubmit: true
					, onInitializeForm: null
					, beforeInitData: null
					, beforeShowForm: null
					, afterShowForm: null
					, beforeSubmit: null
					, afterSubmit: null
					, onclickSubmit: null
					, afterComplete: null
					, onclickPgButtons: null
					, afterclickPgButtons: null
					, editData: {}
					, recreateForm: false
					, wizModal: true
					, closeOnEscape: false
					, addedrow: "first"
					, topinfo: ''
					, bottominfo: ''
					, saveicon: []
					, closeicon: []
					, savekey: [false, 13]
					, navkeys: [false, 38, 40]
					, checkOnSubmit: false
					, checkOnUpdate: false
					, _savedData: {}
					, processing: false
					, onClose: null
					, ajaxEditOptions: {}
					, serializeEditData: null
					, viewPagerButtons: true
					, overlayClass: 'ui-widget-overlay'
					, removemodal: true
					, form: 'edit'
					, template: null
				}, regional, p || {});
				rp_ge[$(this)[0].p.id] = p;
				return this.each(function() {
					var $t = this;
					if (!$t.grid || !rowid) {
						return;
					}
					var gID = $t.p.id
						, frmgr = "FrmGrid_" + gID
						, frmtborg = "TblGrid_" + gID
						, frmtb = "#" + $.wizgrid.wizID(frmtborg)
						, frmtb2, IDs = {
							themodal: 'editmod' + gID
							, modalhead: 'edithd' + gID
							, modalcontent: 'editcnt' + gID
							, scrollelm: frmgr
						}
						, showFrm = true
						, maxCols = 1
						, maxRows = 0
						, postdata, diff, frmoper, templ = typeof rp_ge[$t.p.id].template === "string" && rp_ge[$t.p.id].template
						.length > 0
						, errors = $.wizgrid.getRegional(this, 'errors');
					if (rowid === "new") {
						rowid = "_empty";
						frmoper = "add";
						p.caption = rp_ge[$t.p.id].addCaption;
					}
					else {
						p.caption = rp_ge[$t.p.id].editCaption;
						frmoper = "edit";
					}
					if (!p.recreateForm) {
						if ($($t).data("formProp")) {
							$.extend(rp_ge[$(this)[0].p.id], $($t).data("formProp"));
						}
					}
					var closeovrl = true;
					if (p.checkOnUpdate && p.wizModal && !p.modal) {
						closeovrl = false;
					}

					function getFormData() {
						$(frmtb).find(".FormElement").each(function() {
							var celm = $(".customelement", this);
							if (celm.length) {
								var elem = celm[0]
									, nm = $(elem).attr('name');
								$.each($t.p.Columns, function() {
									if (this.name === nm && this.editoptions && $.isFunction(this.editoptions.custom_value)) {
										try {
											postdata[nm] = this.editoptions.custom_value.call($t, $("#" + $.wizgrid.wizID(nm), frmtb)
												, 'get');
											if (postdata[nm] === undefined) {
												throw "e1";
											}
										}
										catch (e) {
											if (e === "e1") {
												$.wizgrid.info_dialog(errors.errcap, "function 'custom_value' " + rp_ge[$(this)[0]].p.msg
													.novalue, rp_ge[$(this)[0]].p.bClose);
											}
											else {
												$.wizgrid.info_dialog(errors.errcap, e.message, rp_ge[$(this)[0]].p.bClose);
											}
										}
										return true;
									}
								});
							}
							else {
								switch ($(this).get(0).type) {
									case "checkbox":
										// alert(1);
										if ($(this).is(":checked")) {
											postdata[this.name] = $(this).val();
										}
										else {
											var ofv = $(this).attr("offval");
											postdata[this.name] = ofv;
										}
										break;
									case "select-one":
										postdata[this.name] = $("option:selected", this).val();
										break;
									case "select-multiple":
										postdata[this.name] = $(this).val();
										if (postdata[this.name]) {
											postdata[this.name] = postdata[this.name].join(",");
										}
										else {
											postdata[this.name] = "";
										}
										var selectedText = [];
										$("option:selected", this).each(function(i, selected) {
											selectedText[i] = $(selected).text();
										});
										break;
									case "password":
									case "text":
									case "textarea":
									case "button":
										postdata[this.name] = $(this).val();
										break;
								}
								if ($t.p.autoencode) {
									postdata[this.name] = $.wizgrid.htmlEncode(postdata[this.name]);
								}
							}
						});
						return true;
					}

					function createData(rowid, obj, tb, maxcols) {
						var nm, hc, trdata, cnt = 0
							, tmp, dc, elc, retpos = []
							, ind = false
							, tdtmpl = "<td class='CaptionTD'>&#160;</td><td class='DataTD'>&#160;</td>"
							, tmpl = ""
							, i; // *2
						for (i = 1; i <= maxcols; i++) {
							tmpl += tdtmpl;
						}
						if (rowid !== '_empty') {
							ind = $(obj).wizGrid("getInd", rowid);
						}
						$(obj.p.Columns).each(function(i) {
							nm = this.name;
							// hidden fields
							// are included
							// in the form
							if (this.editrules && this.editrules.edithidden === true) {
								hc = false;
							}
							else {
								hc = this.hidden === true ? true : false;
							}
							dc = hc ? "style='display:none'" : "";
							if (nm !== 'cb' && nm !== 'subgrid' && this.editable === true && nm !== 'rn') {
								if (ind === false) {
									tmp = "";
								}
								else {
									if (nm === obj.p.ExpandColumn && obj.p.treeGrid === true) {
										tmp = $("td[role='gridcell']:eq(" + i + ")", obj.rows[ind]).text();
									}
									else {
										try {
											tmp = $.unformat.call(obj, $("td[role='gridcell']:eq(" + i + ")", obj.rows[ind]), {
												rowId: rowid
												, Columns: this
											}, i);
										}
										catch (_) {
											tmp = (this.edittype && this.edittype === "textarea") ? $("td[role='gridcell']:eq(" + i +
												")", obj.rows[ind]).text() : $("td[role='gridcell']:eq(" + i + ")", obj.rows[ind]).html();
										}
										if (!tmp || tmp === "&nbsp;" || tmp === "&#160;" || (tmp.length === 1 && tmp.charCodeAt(0) ===
												160)) {
											tmp = '';
										}
									}
								}
								var opt = $.extend({}, this.editoptions || {}, {
										id: nm
										, name: nm
										, rowId: rowid
										, oper: 'edit'
									})
									, frmopt = $.extend({}, {
										elmprefix: ''
										, elmsuffix: ''
										, rowabove: false
										, rowcontent: ''
									}, this.formoptions || {})
									, rp = parseInt(frmopt.rowpos, 10) || cnt + 1
									, cp = parseInt(
										(parseInt(frmopt.colpos, 10) || 1) * 2, 10);
								if (rowid === "_empty" && opt.defaultValue) {
									tmp = $.isFunction(opt.defaultValue) ? opt.defaultValue.call($t) : opt.defaultValue;
								}
								if (!this.edittype) {
									this.edittype = "text";
								}
								if ($t.p.autoencode) {
									tmp = $.wizgrid.htmlDecode(tmp);
								}
								elc = $.wizgrid.createEl.call($t, this.edittype, opt, tmp, false, $.extend({}, $.wizgrid.ajaxOptions
									, obj.p.ajaxSelectOptions || {}));
								// if(tmp
								// === "" &&
								// this.edittype
								// ==
								// "checkbox")
								// {tmp =
								// $(elc).attr("offval");}
								// if(tmp
								// === "" &&
								// this.edittype
								// ==
								// "select")
								// {tmp =
								// $("option:eq(0)",elc).text();}
								if (this.edittype === "select") {
									tmp = $(elc).val();
									if ($(elc).get(0).type === 'select-multiple') {
										tmp = tmp.join(",");
									}
								}
								if (this.edittype === 'checkbox') {
									if ($(elc).is(":checked")) {
										tmp = $(elc).val();
									}
									else {
										tmp = $(elc).attr("offval");
									}
								}
								if (rp_ge[$t.p.id].checkOnSubmit || rp_ge[$t.p.id].checkOnUpdate) {
									rp_ge[$t.p.id]._savedData[nm] = tmp;
								}
								$(elc).addClass("FormElement");
								if ($.inArray(this.edittype, ['text', 'textarea', 'password', 'select']) > -1) {
									$(elc).addClass("ui-widget-content-grid ui-corner-all-grid");
								}
								if (templ) {
									$(frm).find("#" + nm).replaceWith(elc);
								}
								else {
									// --------------------
									trdata = $(tb).find("tr[rowpos=" + rp + "]");
									if (frmopt.rowabove) {
										var newdata = $("<tr><td class='contentinfo' colspan='" + (maxcols * 2) + "'>" + frmopt.rowcontent +
											"</td></tr>");
										$(tb).append(newdata);
										newdata[0].rp = rp;
									}
									if (trdata.length === 0) {
										trdata = $("<tr " + dc + " rowpos='" + rp + "'></tr>").addClass("FormData").attr("id"
											, "tr_" + nm);
										$(trdata).append(tmpl);
										$(tb).append(trdata);
										trdata[0].rp = rp;
									}
									$("td:eq(" + (cp - 2) + ")", trdata[0]).html(frmopt.label === undefined ? obj.p.Names[i] :
										frmopt.label);
									$("td:eq(" + (cp - 1) + ")", trdata[0]).append(frmopt.elmprefix).append(elc).append(frmopt.elmsuffix);
									// -------------------------
								}
								if (this.edittype === 'custom' && $.isFunction(opt.custom_value)) {
									opt.custom_value.call($t, $("#" + nm, frmgr), 'set', tmp);
								}
								$.wizgrid.bindEv.call($t, elc, opt);
								retpos[cnt] = i;
								cnt++;
							}
						});
						if (cnt > 0) {
							var idrow;
							if (templ) {
								idrow =
									"<div class='FormData' style='display:none'><input class='FormElement' id='id_g' type='text' name='" +
									obj.p.id + "_id' value='" + rowid + "'/>";
								$(frm).append(idrow);
							}
							else {
								idrow = $("<tr class='FormData' style='display:none'><td class='CaptionTD'></td><td colspan='" +
									(maxcols * 2 - 1) +
									"' class='DataTD'><input class='FormElement' id='id_g' type='text' name='" + obj.p.id +
									"_id' value='" + rowid + "'/></td></tr>");
								idrow[0].rp = cnt + 999;
								$(tb).append(idrow);
							}
							// $(tb).append(idrow);
							if (rp_ge[$t.p.id].checkOnSubmit || rp_ge[$t.p.id].checkOnUpdate) {
								rp_ge[$t.p.id]._savedData[obj.p.id + "_id"] = rowid;
							}
						}
						return retpos;
					}

					function fillData(rowid, obj, fmid) {
						var nm, cnt = 0
							, tmp, fld, opt, vl, vlc;
						if (rp_ge[$t.p.id].checkOnSubmit || rp_ge[$t.p.id].checkOnUpdate) {
							rp_ge[$t.p.id]._savedData = {};
							rp_ge[$t.p.id]._savedData[obj.p.id + "_id"] = rowid;
						}
						var cm = obj.p.Columns;
						if (rowid === '_empty') {
							$(cm).each(function() {
								nm = this.name;
								opt = $.extend({}, this.editoptions || {});
								fld = $("#" + $.wizgrid.wizID(nm), fmid);
								if (fld && fld.length && fld[0] !== null) {
									vl = "";
									if (this.edittype === 'custom' && $.isFunction(opt.custom_value)) {
										opt.custom_value.call($t, $("#" + nm, fmid), 'set', vl);
									}
									else if (opt.defaultValue) {
										vl = $.isFunction(opt.defaultValue) ? opt.defaultValue.call($t) : opt.defaultValue;
										if (fld[0].type === 'checkbox') {
											vlc = vl.toLowerCase();
											if (vlc.search(/(false|f|0|no|n|off|undefined)/i) < 0 && vlc !== "") {
												fld[0].checked = true;
												fld[0].defaultChecked = true;
												fld[0].value = vl;
											}
											else {
												fld[0].checked = false;
												fld[0].defaultChecked = false;
											}
										}
										else {
											fld.val(vl);
										}
									}
									else {
										if (fld[0].type === 'checkbox') {
											fld[0].checked = false;
											fld[0].defaultChecked = false;
											vl = $(fld).attr("offval");
										}
										else if (fld[0].type && fld[0].type.substr(0, 6) === 'select') {
											fld[0].selectedIndex = 0;
										}
										else {
											fld.val(vl);
										}
									}
									if (rp_ge[$t.p.id].checkOnSubmit === true || rp_ge[$t.p.id].checkOnUpdate) {
										rp_ge[$t.p.id]._savedData[nm] = vl;
									}
								}
							});
							$("#id_g", fmid).val(rowid);
							return;
						}
						var tre = $(obj).wizGrid("getInd", rowid, true);
						if (!tre) {
							return;
						}
						$('td[role="gridcell"]', tre).each(function(i) {
							nm = cm[i].name;
							// hidden fields
							// are included
							// in the form
							if (nm !== 'cb' && nm !== 'subgrid' && nm !== 'rn' && cm[i].editable === true) {
								if (nm === obj.p.ExpandColumn && obj.p.treeGrid === true) {
									tmp = $(this).text();
								}
								else {
									try {
										tmp = $.unformat.call(obj, $(this), {
											rowId: rowid
											, Columns: cm[i]
										}, i);
									}
									catch (_) {
										tmp = cm[i].edittype === "textarea" ? $(this).text() : $(this).html();
									}
								}
								if ($t.p.autoencode) {
									tmp = $.wizgrid.htmlDecode(tmp);
								}
								if (rp_ge[$t.p.id].checkOnSubmit === true || rp_ge[$t.p.id].checkOnUpdate) {
									rp_ge[$t.p.id]._savedData[nm] = tmp;
								}
								nm = $.wizgrid.wizID(nm);
								switch (cm[i].edittype) {
									case "password":
									case "text":
									case "button":
									case "image":
									case "textarea":
										if (tmp === "&nbsp;" || tmp === "&#160;" || (tmp.length === 1 && tmp.charCodeAt(0) === 160)) {
											tmp = '';
										}
										$("#" + nm, fmid).val(tmp);
										break;
									case "select":
										var opv = tmp.split(",");
										opv = $.map(opv, function(n) {
											return $.trim(n);
										});
										$("#" + nm + " option", fmid).each(function() {
											if (!cm[i].editoptions.multiple && ($.trim(tmp) === $.trim($(this).text()) || opv[0] ===
													$.trim($(this).text()) || opv[0] === $.trim($(this).val()))) {
												this.selected = true;
											}
											else if (cm[i].editoptions.multiple) {
												if ($.inArray($.trim($(this).text()), opv) > -1 || $.inArray($.trim($(this).val()), opv) >
													-1) {
													this.selected = true;
												}
												else {
													this.selected = false;
												}
											}
											else {
												this.selected = false;
											}
										});
										if (rp_ge[$t.p.id].checkOnSubmit === true || rp_ge[$t.p.id].checkOnUpdate) {
											tmp = $("#" + nm, fmid).val();
											if (cm[i].editoptions.multiple) {
												tmp = tmp.join(",");
											}
											rp_ge[$t.p.id]._savedData[nm] = tmp;
										}
										break;
									case "checkbox": // pjy
										tmp = String(tmp);
										if (cm[i].editoptions && cm[i].editoptions.value) {
											var cb = cm[i].editoptions.value.split(":");
											if (cb[0] === tmp) {
												$("#" + nm, fmid)[$t.p.useProp ? 'prop' : 'attr']
													({
														"checked": true
														, "defaultChecked": true
													});
											}
											else {
												$("#" + nm, fmid)[$t.p.useProp ? 'prop' : 'attr']
													({
														"checked": false
														, "defaultChecked": false
													});
											}
										}
										else {
											tmp = tmp.toLowerCase();
											if (tmp.search(/(false|f|0|no|n|off|undefined)/i) < 0 && tmp !== "") {
												$("#" + nm, fmid)[$t.p.useProp ? 'prop' : 'attr']
													("checked", true);
												$("#" + nm, fmid)[$t.p.useProp ? 'prop' : 'attr']
													("defaultChecked", true); // ie
											}
											else {
												$("#" + nm, fmid)[$t.p.useProp ? 'prop' : 'attr']
													("checked", false);
												$("#" + nm, fmid)[$t.p.useProp ? 'prop' : 'attr']
													("defaultChecked", false); // ie
											}
										}
										if (rp_ge[$t.p.id].checkOnSubmit === true || rp_ge[$t.p.id].checkOnUpdate) {
											if ($("#" + nm, fmid).is(":checked")) {
												tmp = $("#" + nm, fmid).val();
											}
											else {
												tmp = $("#" + nm, fmid).attr("offval");
											}
										}
										break;
									case 'custom':
										try {
											if (cm[i].editoptions && $.isFunction(cm[i].editoptions.custom_value)) {
												cm[i].editoptions.custom_value.call($t, $("#" + nm, fmid), 'set', tmp);
											}
											else {
												throw "e1";
											}
										}
										catch (e) {
											if (e === "e1") {
												$.wizgrid.info_dialog(errors.errcap, "function 'custom_value' " + rp_ge[$(this)[0]].p.msg
													.nodefined, $.rp_ge[$(this)[0]].p.bClose);
											}
											else {
												$.wizgrid.info_dialog(errors.errcap, e.message, $.rp_ge[$(this)[0]].p.bClose);
											}
										}
										break;
								}
								cnt++;
							}
						});
						if (cnt > 0) {
							$("#id_g", frmtb).val(rowid);
						}
					}

					function setNulls() {
						$.each($t.p.Columns, function(i, n) {
							if (n.editoptions && n.editoptions.NullIfEmpty === true) {
								if (postdata.hasOwnProperty(n.name) && postdata[n.name] === "") {
									postdata[n.name] = 'null';
								}
							}
						});
					}

					function postIt() {
						var copydata, ret = [true, "", ""]
							, onCS = {}
							, opers = $t.p.prmNames
							, idname, oper, key, selr, i, url;
						var retvals = $($t).triggerHandler("wizGridAddEditBeforeCheckValues", [$(frmgr), frmoper]);
						if (retvals && typeof retvals === 'object') {
							postdata = retvals;
						}
						if ($.isFunction(rp_ge[$t.p.id].beforeCheckValues)) {
							retvals = rp_ge[$t.p.id].beforeCheckValues.call($t, postdata, $(frmgr), frmoper);
							if (retvals && typeof retvals === 'object') {
								postdata = retvals;
							}
						}
						for (key in postdata) {
							if (postdata.hasOwnProperty(key)) {
								ret = $.wizgrid.checkValues.call($t, postdata[key], key);
								if (ret[0] === false) {
									break;
								}
							}
						}
						setNulls();
						if (ret[0]) {
							onCS = $($t).triggerHandler("wizGridAddEditClickSubmit", [
								rp_ge[$t.p.id]
								, postdata
								, frmoper
							]);
							if (onCS === undefined && $.isFunction(rp_ge[$t.p.id].onclickSubmit)) {
								onCS = rp_ge[$t.p.id].onclickSubmit.call($t, rp_ge[$t.p.id], postdata, frmoper) || {};
							}
							ret = $($t).triggerHandler("wizGridAddEditBeforeSubmit", [
								postdata
								, $(frmgr)
								, frmoper
							]);
							if (ret === undefined) {
								ret = [true, "", ""];
							}
							if (ret[0] && $.isFunction(rp_ge[$t.p.id].beforeSubmit)) {
								ret = rp_ge[$t.p.id].beforeSubmit.call($t, postdata, $(frmgr), frmoper);
							}
						}
						if (ret[0] && !rp_ge[$t.p.id].processing) {
							rp_ge[$t.p.id].processing = true;
							$("#sData", frmtb + "_2").addClass('ui-state-active');
							url = rp_ge[$t.p.id].url || $($t).wizGrid('getGridParam', 'editurl');
							oper = opers.oper;
							idname = url === 'clientArray' ? $t.p.keyName : opers.id;
							// we add to pos data array the
							// action - the name is oper
							postdata[oper] = ($.trim(postdata[$t.p.id + "_id"]) === "_empty") ? opers.addoper : opers.editoper;
							if (postdata[oper] !== opers.addoper) {
								postdata[idname] = postdata[$t.p.id + "_id"];
							}
							else {
								// check to see if we have
								// allredy this field in the
								// form and if yes lieve it
								if (postdata[idname] === undefined) {
									postdata[idname] = postdata[$t.p.id + "_id"];
								}
							}
							delete postdata[$t.p.id + "_id"];
							postdata = $.extend(postdata, rp_ge[$t.p.id].editData, onCS);
							if ($t.p.treeGrid === true) {
								if (postdata[oper] === opers.addoper) {
									selr = $($t).wizGrid("getGridParam", 'selrow');
									var tr_par_id = $t.p.treeGridModel === 'adjacency' ? $t.p.treeReader.parent_id_field :
										'parent_id';
									postdata[tr_par_id] = selr;
								}
								for (i in $t.p.treeReader) {
									if ($t.p.treeReader.hasOwnProperty(i)) {
										var itm = $t.p.treeReader[i];
										if (postdata.hasOwnProperty(itm)) {
											if (postdata[oper] === opers.addoper && i === 'parent_id_field') {
												continue;
											}
											delete postdata[itm];
										}
									}
								}
							}
							postdata[idname] = $.wizgrid.stripPref($t.p.idPrefix, postdata[idname]);
							var ajaxOptions = $.extend({
								url: url
								, type: rp_ge[$t.p.id].mtype
								, data: $.isFunction(rp_ge[$t.p.id].serializeEditData) ? rp_ge[$t.p.id].serializeEditData.call(
									$t, postdata) : postdata
								, complete: function(data, status) {
									var key;
									$("#sData", frmtb + "_2").removeClass('ui-state-active');
									postdata[idname] = $t.p.idPrefix + postdata[idname];
									if (data.status >= 300 && data.status !== 304) {
										ret[0] = false;
										ret[1] = $($t).triggerHandler("wizGridAddEditErrorTextFormat", [
											data
											, frmoper
										]);
										if ($.isFunction(rp_ge[$t.p.id].errorTextFormat)) {
											ret[1] = rp_ge[$t.p.id].errorTextFormat.call($t, data, frmoper);
										}
										else {
											ret[1] = status + " Status: '" + data.statusText + "'. Error code: " + data.status;
										}
									}
									else {
										// data
										// is
										// posted
										// successful
										// execute
										// aftersubmit
										// with
										// the
										// returned
										// data
										// from
										// server
										ret = $($t).triggerHandler("wizGridAddEditAfterSubmit", [
											data
											, postdata
											, frmoper
										]);
										if (ret === undefined) {
											ret = [
												true, "", ""
											];
										}
										if (ret[0] && $.isFunction(rp_ge[$t.p.id].afterSubmit)) {
											ret = rp_ge[$t.p.id].afterSubmit.call($t, data, postdata, frmoper);
										}
									}
									if (ret[0] === false) {
										$(".FormError", frmgr).html(ret[1]);
										$(".FormError", frmgr).show();
									}
									else {
										if ($t.p.autoencode) {
											$.each(postdata, function(n, v) {
												postdata[n] = $.wizgrid.htmlDecode(v);
											});
										}
										// rp_ge[$t.p.id].reloadAfterSubmit
										// =
										// rp_ge[$t.p.id].reloadAfterSubmit
										// &&
										// $t.p.datatype
										// !=
										// "local";
										// the
										// action
										// is
										// add
										if (postdata[oper] === opers.addoper) {
											// id
											// processing
											// user
											// not
											// set
											// the
											// id
											// ret[2]
											if (!ret[2]) {
												ret[2] = $.wizgrid.randId();
											}
											if (postdata[idname] == null || postdata[idname] === "_empty") {
												postdata[idname] = ret[2];
											}
											else {
												ret[2] = postdata[idname];
											}
											if (rp_ge[$t.p.id].reloadAfterSubmit) {
												$($t).trigger("reloadGrid");
											}
											else {
												if ($t.p.treeGrid === true) {
													$($t).wizGrid("addChildNode", ret[2], selr, postdata);
												}
												else {
													$($t).wizGrid("addRowData", ret[2], postdata, p.addedrow);
												}
											}
											if (rp_ge[$t.p.id].closeAfterAdd) {
												if ($t.p.treeGrid !== true) {
													$($t).wizGrid("setSelection", ret[2]);
												}
												$.wizgrid.hideModal("#" + $.wizgrid.wizID(IDs.themodal), {
													gb: "#gbox_" + $.wizgrid.wizID(gID)
													, wizm: p.wizModal
													, onClose: rp_ge[$t.p.id].onClose
													, removemodal: rp_ge[$t.p.id].removemodal
													, formprop: !rp_ge[$t.p.id].recreateForm
													, form: rp_ge[$t.p.id].form
												});
											}
											else if (rp_ge[$t.p.id].clearAfterAdd) {
												fillData("_empty", $t, frmgr);
											}
										}
										else {
											// the
											// action
											// is
											// update
											if (rp_ge[$t.p.id].reloadAfterSubmit) {
												$($t).trigger("reloadGrid");
												if (!rp_ge[$t.p.id].closeAfterEdit) {
													setTimeout(function() {
														$($t).wizGrid("setSelection", postdata[idname]);
													}, 1000);
												}
											}
											else {
												if ($t.p.treeGrid === true) {
													$($t).wizGrid("setTreeRow", postdata[idname], postdata);
												}
												else {
													$($t).wizGrid("setRowData", postdata[idname], postdata);
												}
											}
											if (rp_ge[$t.p.id].closeAfterEdit) {
												$.wizgrid.hideModal("#" + $.wizgrid.wizID(IDs.themodal), {
													gb: "#gbox_" + $.wizgrid.wizID(gID)
													, wizm: p.wizModal
													, onClose: rp_ge[$t.p.id].onClose
													, removemodal: rp_ge[$t.p.id].removemodal
													, formprop: !rp_ge[$t.p.id].recreateForm
													, form: rp_ge[$t.p.id].form
												});
											}
										}
										if ($.isFunction(rp_ge[$t.p.id].afterComplete)) {
											copydata = data;
											setTimeout(function() {
												$($t).triggerHandler("wizGridAddEditAfterComplete", [
													copydata
													, postdata
													, $(frmgr)
													, frmoper
												]);
												rp_ge[$t.p.id].afterComplete.call($t, copydata, postdata, $(frmgr), frmoper);
												copydata = null;
											}, 500);
										}
										if (rp_ge[$t.p.id].checkOnSubmit || rp_ge[$t.p.id].checkOnUpdate) {
											$(frmgr).data("disabled", false);
											if (rp_ge[$t.p.id]._savedData[$t.p.id + "_id"] !== "_empty") {
												for (key in rp_ge[$t.p.id]._savedData) {
													if (rp_ge[$t.p.id]._savedData.hasOwnProperty(key) && postdata[key]) {
														rp_ge[$t.p.id]._savedData[key] = postdata[key];
													}
												}
											}
										}
									}
									rp_ge[$t.p.id].processing = false;
									try {
										$(':input:visible', frmgr)[0].focus();
									}
									catch (e) {}
								}
							}, $.wizgrid.ajaxOptions, rp_ge[$t.p.id].ajaxEditOptions);
							if (!ajaxOptions.url && !rp_ge[$t.p.id].useDataProxy) {
								if ($.isFunction($t.p.dataProxy)) {
									rp_ge[$t.p.id].useDataProxy = true;
								}
								else {
									ret[0] = false;
									ret[1] += " " + errors.nourl;
								}
							}
							if (ret[0]) {
								if (rp_ge[$t.p.id].useDataProxy) {
									var dpret = $t.p.dataProxy.call($t, ajaxOptions, "set_" + $t.p.id);
									if (dpret === undefined) {
										dpret = [true, ""];
									}
									if (dpret[0] === false) {
										ret[0] = false;
										ret[1] = dpret[1] || "Error deleting the selected row!";
									}
									else {
										if (ajaxOptions.data.oper === opers.addoper && rp_ge[$t.p.id].closeAfterAdd) {
											$.wizgrid.hideModal("#" + $.wizgrid.wizID(IDs.themodal), {
												gb: "#gbox_" + $.wizgrid.wizID(gID)
												, wizm: p.wizModal
												, onClose: rp_ge[$t.p.id].onClose
												, removemodal: rp_ge[$t.p.id].removemodal
												, formprop: !rp_ge[$t.p.id].recreateForm
												, form: rp_ge[$t.p.id].form
											});
										}
										if (ajaxOptions.data.oper === opers.editoper && rp_ge[$t.p.id].closeAfterEdit) {
											$.wizgrid.hideModal("#" + $.wizgrid.wizID(IDs.themodal), {
												gb: "#gbox_" + $.wizgrid.wizID(gID)
												, wizm: p.wizModal
												, onClose: rp_ge[$t.p.id].onClose
												, removemodal: rp_ge[$t.p.id].removemodal
												, formprop: !rp_ge[$t.p.id].recreateForm
												, form: rp_ge[$t.p.id].form
											});
										}
									}
								}
								else {
									if (ajaxOptions.url === "clientArray") {
										rp_ge[$t.p.id].reloadAfterSubmit = false;
										postdata = ajaxOptions.data;
										ajaxOptions.complete({
											status: 200
											, statusText: ''
										}, '');
									}
									else {
										$.ajax(ajaxOptions);
									}
								}
							}
						}
						if (ret[0] === false) {
							$(".FormError", frmgr).html(ret[1]);
							$(".FormError", frmgr).show();
							// return;
						}
					}

					function compareData(nObj, oObj) {
						var ret = false
							, key;
						for (key in nObj) {
							if (nObj.hasOwnProperty(key) && nObj[key] != oObj[key]) {
								ret = true;
								break;
							}
						}
						return ret;
					}

					function checkUpdates() {
						var stat = true;
						$(".FormError", frmgr).hide();
						if (rp_ge[$t.p.id].checkOnUpdate) {
							postdata = {};
							getFormData();
							diff = compareData(postdata, rp_ge[$t.p.id]._savedData);
							if (diff) {
								$(frmgr).data("disabled", true);
								$(".confirm", "#" + IDs.themodal).show();
								stat = false;
							}
						}
						return stat;
					}

					function restoreInline() {
						var i;
						if (rowid !== "_empty" && $t.p.savedRow !== undefined && $t.p.savedRow.length > 0 && $.isFunction(
								$.fn.wizGrid.restoreRow)) {
							for (i = 0; i < $t.p.savedRow.length; i++) {
								if ($t.p.savedRow[i].id === rowid) {
									$($t).wizGrid('restoreRow', rowid);
									break;
								}
							}
						}
					}

					function updateNav(cr, posarr) {
						var totr = posarr[1].length - 1;
						if (cr === 0) {
							$("#pData", frmtb2).addClass('ui-state-disabled');
						}
						else if (posarr[1][cr - 1] !== undefined && $("#" + $.wizgrid.wizID(posarr[1][cr - 1])).hasClass(
								'ui-state-disabled')) {
							$("#pData", frmtb2).addClass('ui-state-disabled');
						}
						else {
							$("#pData", frmtb2).removeClass('ui-state-disabled');
						}
						if (cr === totr) {
							$("#nData", frmtb2).addClass('ui-state-disabled');
						}
						else if (posarr[1][cr + 1] !== undefined && $("#" + $.wizgrid.wizID(posarr[1][cr + 1])).hasClass(
								'ui-state-disabled')) {
							$("#nData", frmtb2).addClass('ui-state-disabled');
						}
						else {
							$("#nData", frmtb2).removeClass('ui-state-disabled');
						}
					}

					function getCurrPos() {
						var rowsInGrid = $($t).wizGrid("getDataIDs")
							, selrow = $("#id_g", frmtb).val()
							, pos = $.inArray(selrow, rowsInGrid);
						return [pos, rowsInGrid];
					}

					function parseTemplate(template) {
						var tmpl = "";
						if (typeof template === "string") {
							tmpl = template.replace(/\{([\w\-]+)(?:\:([\w\.]*)(?:\((.*?)?\))?)?\}/g, function(m, i) {
								return '<span id="' + i + '" ></span>';
							});
						}
						return tmpl;
					}
					var dh = isNaN(rp_ge[$(this)[0].p.id].dataheight) ? rp_ge[$(this)[0].p.id].dataheight : rp_ge[$(
							this)[0].p.id].dataheight + "px"
						, dw = isNaN(rp_ge[$(this)[0].p.id].datawidth) ? rp_ge[$(this)[0].p.id].datawidth : rp_ge[$(this)[
							0].p.id].datawidth + "px"
						, frm = $("<form name='FormPost' id='" + frmgr +
							"' class='FormGrid' onSubmit='return false;' style='width:" + dw +
							";overflow:auto;position:relative;height:" + dh + ";'></form>").data("disabled", false)
						, tbl;
					if (templ) {
						tbl = parseTemplate(rp_ge[$(this)[0].p.id].template);
						frmtb2 = frmtb;
					}
					else {
						tbl = $("<table id='" + frmtborg +
							"' class='EditTable' cellspacing='0' cellpadding='0' border='0'><tbody></tbody></table>");
						frmtb2 = frmtb + "_2";
					}
					frmgr = "#" + $.wizgrid.wizID(frmgr);
					// errors
					$(frm).append("<div class='FormError ui-state-error' style='display:none;'></div>");
					// topinfo
					$(frm).append("<div class='tinfo topinfo'>" + rp_ge[$t.p.id].topinfo + "</div>");
					$($t.p.Columns).each(function() {
						var fmto = this.formoptions;
						maxCols = Math.max(maxCols, fmto ? fmto.colpos || 0 : 0);
						maxRows = Math.max(maxRows, fmto ? fmto.rowpos || 0 : 0);
					});
					$(frm).append(tbl);
					showFrm = $($t).triggerHandler("wizGridAddEditBeforeInitData", [frm, frmoper]);
					if (showFrm === undefined) {
						showFrm = true;
					}
					if (showFrm && $.isFunction(rp_ge[$t.p.id].beforeInitData)) {
						showFrm = rp_ge[$t.p.id].beforeInitData.call($t, frm, frmoper);
					}
					if (showFrm === false) {
						return;
					}
					restoreInline();
					// set the id.
					// use carefull only to change here
					// colproperties.
					// create data
					createData(rowid, $t, tbl, maxCols);
					// buttons at footer
					var rtlb = $t.p.direction === "rtl" ? true : false
						, bp = rtlb ? "nData" : "pData"
						, bn = rtlb ? "pData" : "nData";
					var bP = "<a id='" + bp +
						"' class='fm-button ui-state-default-grid ui-corner-left-grid'><span class='ui-icon ui-icon-triangle-1-w'></span></a>"
						, bN = "<a id='" + bn +
						"' class='fm-button ui-state-default-grid ui-corner-right'><span class='ui-icon ui-icon-triangle-1-e'></span></a>"
						, bS = "<a id='sData' class='fm-button ui-state-default-grid ui-corner-all-grid'>" + p.bSubmit +
						"</a>"
						, bC = "<a id='cData' class='fm-button ui-state-default-grid ui-corner-all-grid'>" + p.bCancel +
						"</a>";
					var bt = "<table border='0' cellspacing='0' cellpadding='0' class='EditTable' id='" + frmtborg +
						"_2'><tbody><tr><td colspan='2'><hr class='ui-widget-content-grid' style='margin:1px'/></td></tr><tr id='Act_Buttons'><td class='navButton'>" +
						(rtlb ? bN + bP : bP + bN) + "</td><td class='EditButton'>" + bS + bC + "</td></tr>";
					// bt += "<tr style='display:none'
					// class='binfo'><td class='bottominfo'
					// colspan='2'>"+rp_ge[$t.p.id].bottominfo+"</td></tr>";
					bt += "</tbody></table>";
					if (maxRows > 0) {
						var sd = [];
						$.each($(tbl)[0].rows, function(i, r) {
							sd[i] = r;
						});
						sd.sort(function(a, b) {
							if (a.rp > b.rp) {
								return 1;
							}
							if (a.rp < b.rp) {
								return -1;
							}
							return 0;
						});
						$.each(sd, function(index, row) {
							$('tbody', tbl).append(row);
						});
					}
					p.gbox = "#gbox_" + $.wizgrid.wizID(gID);
					var cle = false;
					if (p.closeOnEscape === true) {
						p.closeOnEscape = false;
						cle = true;
					}
					var tms;
					if (templ) {
						$(frm).find("#pData").replaceWith(bP);
						$(frm).find("#nData").replaceWith(bN);
						$(frm).find("#sData").replaceWith(bS);
						$(frm).find("#cData").replaceWith(bC);
						tms = $("<div id=" + frmtborg + "></div>").append(frm);
					}
					else {
						tms = $("<div></div>").append(frm).append(bt);
					}
					$(frm).append("<div class='binfo topinfo bottominfo'>" + rp_ge[$t.p.id].bottominfo + "</div>");
					$.wizgrid.createModal(IDs, tms, rp_ge[$(this)[0].p.id], "#gview_" + $.wizgrid.wizID($t.p.id), $(
						"#gbox_" + $.wizgrid.wizID($t.p.id))[0]);
					if (rtlb) {
						$("#pData, #nData", frmtb + "_2").css("float", "right");
						$(".EditButton", frmtb + "_2").css("text-align", "left");
					}
					if (rp_ge[$t.p.id].topinfo) {
						$(".tinfo", frmgr).show();
					}
					if (rp_ge[$t.p.id].bottominfo) {
						$(".binfo", frmgr).show();
					}
					tms = null;
					bt = null;
					$("#" + $.wizgrid.wizID(IDs.themodal)).keydown(function(e) {
						var wkey = e.target;
						if ($(frmgr).data("disabled") === true) {
							return false;
						} // ??
						if (rp_ge[$t.p.id].savekey[0] === true && e.which === rp_ge[$t.p.id].savekey[1]) { // save
							if (wkey.tagName !== "TEXTAREA") {
								$("#sData", frmtb + "_2").trigger("click");
								return false;
							}
						}
						if (e.which === 27) {
							if (!checkUpdates()) {
								return false;
							}
							if (cle) {
								$.wizgrid.hideModal("#" + $.wizgrid.wizID(IDs.themodal), {
									gb: p.gbox
									, wizm: p.wizModal
									, onClose: rp_ge[$t.p.id].onClose
									, removemodal: rp_ge[$t.p.id].removemodal
									, formprop: !rp_ge[$t.p.id].recreateForm
									, form: rp_ge[$t.p.id].form
								});
							}
							return false;
						}
						if (rp_ge[$t.p.id].navkeys[0] === true) {
							if ($("#id_g", frmtb).val() === "_empty") {
								return true;
							}
							if (e.which === rp_ge[$t.p.id].navkeys[1]) { // up
								$("#pData", frmtb2).trigger("click");
								return false;
							}
							if (e.which === rp_ge[$t.p.id].navkeys[2]) { // down
								$("#nData", frmtb2).trigger("click");
								return false;
							}
						}
					});
					if (p.checkOnUpdate) {
						$("a..ui-wizdialog-titlebar-close span", "#" + $.wizgrid.wizID(IDs.themodal)).removeClass(
							"wizmClose");
						$("a..ui-wizdialog-titlebar-close", "#" + $.wizgrid.wizID(IDs.themodal)).unbind("click").click(
							function() {
								if (!checkUpdates()) {
									return false;
								}
								$.wizgrid.hideModal("#" + $.wizgrid.wizID(IDs.themodal), {
									gb: "#gbox_" + $.wizgrid.wizID(gID)
									, wizm: p.wizModal
									, onClose: rp_ge[$t.p.id].onClose
									, removemodal: rp_ge[$t.p.id].removemodal
									, formprop: !rp_ge[$t.p.id].recreateForm
									, form: rp_ge[$t.p.id].form
								});
								return false;
							});
					}
					p.saveicon = $.extend([true, "left", "ui-icon-disk"], p.saveicon);
					p.closeicon = $.extend([true, "left", "ui-icon-close"], p.closeicon);
					// beforeinitdata after creation of the
					// form
					if (p.saveicon[0] === true) {
						$("#sData", frmtb2).addClass(p.saveicon[1] === "right" ? 'fm-button-icon-right' :
							'fm-button-icon-left').append("<span class='ui-icon " + p.saveicon[2] + "'></span>");
					}
					if (p.closeicon[0] === true) {
						$("#cData", frmtb2).addClass(p.closeicon[1] === "right" ? 'fm-button-icon-right' :
							'fm-button-icon-left').append("<span class='ui-icon " + p.closeicon[2] + "'></span>");
					}
					if (rp_ge[$t.p.id].checkOnSubmit || rp_ge[$t.p.id].checkOnUpdate) {
						bS =
							"<a id='sNew' class='fm-button ui-state-default-grid ui-corner-all-grid' style='z-index:1002'>" +
							p.bYes + "</a>";
						bN =
							"<a id='nNew' class='fm-button ui-state-default-grid ui-corner-all-grid' style='z-index:1002'>" +
							p.bNo + "</a>";
						bC =
							"<a id='cNew' class='fm-button ui-state-default-grid ui-corner-all-grid' style='z-index:1002'>" +
							p.bExit + "</a>";
						var zI = p.zIndex || 999;
						zI++;
						$("<div class='" + p.overlayClass + " wizgrid-overlay confirm' style='z-index:" + zI +
							";display:none;'>&#160;" +
							"</div><div class='confirm ui-widget-content-grid ui-wizconfirm' style='z-index:" + (zI + 1) +
							"'>" + p.saveData + "<br/><br/>" + bS + bN + bC + "</div>").insertAfter(frmgr);
						$("#sNew", "#" + $.wizgrid.wizID(IDs.themodal)).click(function() {
							postIt();
							$(frmgr).data("disabled", false);
							$(".confirm", "#" + $.wizgrid.wizID(IDs.themodal)).hide();
							return false;
						});
						$("#nNew", "#" + $.wizgrid.wizID(IDs.themodal)).click(function() {
							$(".confirm", "#" + $.wizgrid.wizID(IDs.themodal)).hide();
							$(frmgr).data("disabled", false);
							setTimeout(function() {
								$(":input:visible", frmgr)[0].focus();
							}, 0);
							return false;
						});
						$("#cNew", "#" + $.wizgrid.wizID(IDs.themodal)).click(function() {
							$(".confirm", "#" + $.wizgrid.wizID(IDs.themodal)).hide();
							$(frmgr).data("disabled", false);
							$.wizgrid.hideModal("#" + $.wizgrid.wizID(IDs.themodal), {
								gb: "#gbox_" + $.wizgrid.wizID(gID)
								, wizm: p.wizModal
								, onClose: rp_ge[$t.p.id].onClose
								, removemodal: rp_ge[$t.p.id].removemodal
								, formprop: !rp_ge[$t.p.id].recreateForm
								, form: rp_ge[$t.p.id].form
							});
							return false;
						});
					}
					// here initform - only once
					$($t).triggerHandler("wizGridAddEditInitializeForm", [$(frmgr), frmoper]);
					if ($.isFunction(rp_ge[$t.p.id].onInitializeForm)) {
						rp_ge[$t.p.id].onInitializeForm.call($t, $(frmgr), frmoper);
					}
					if (rowid === "_empty" || !rp_ge[$t.p.id].viewPagerButtons) {
						$("#pData,#nData", frmtb2).hide();
					}
					else {
						$("#pData,#nData", frmtb2).show();
					}
					$($t).triggerHandler("wizGridAddEditBeforeShowForm", [$(frmgr), frmoper]);
					if ($.isFunction(rp_ge[$t.p.id].beforeShowForm)) {
						rp_ge[$t.p.id].beforeShowForm.call($t, $(frmgr), frmoper);
					}
					$("#" + $.wizgrid.wizID(IDs.themodal)).data("onClose", rp_ge[$t.p.id].onClose);
					$.wizgrid.viewModal("#" + $.wizgrid.wizID(IDs.themodal), {
						gbox: "#gbox_" + $.wizgrid.wizID(gID)
						, wizm: p.wizModal
						, overlay: p.overlay
						, modal: p.modal
						, overlayClass: p.overlayClass
						, onHide: function(h) {
							var fh = $('#editmod' + gID)[0].style.height;
							if (fh.indexOf("px") > -1) {
								fh = parseFloat(fh);
							}
							$($t).data("formProp", {
								top: parseFloat($(h.w).css("top"))
								, left: parseFloat($(h.w).css("left"))
								, width: $(h.w).width()
								, height: fh
								, dataheight: $(frmgr).height()
								, datawidth: $(frmgr).width()
							});
							h.w.remove();
							if (h.o) {
								h.o.remove();
							}
						}
					});
					if (!closeovrl) {
						$("." + $.wizgrid.wizID(p.overlayClass)).click(function() {
							if (!checkUpdates()) {
								return false;
							}
							$.wizgrid.hideModal("#" + $.wizgrid.wizID(IDs.themodal), {
								gb: "#gbox_" + $.wizgrid.wizID(gID)
								, wizm: p.wizModal
								, onClose: rp_ge[$t.p.id].onClose
								, removemodal: rp_ge[$t.p.id].removemodal
								, formprop: !rp_ge[$t.p.id].recreateForm
								, form: rp_ge[$t.p.id].form
							});
							return false;
						});
					}
					$(".fm-button", "#" + $.wizgrid.wizID(IDs.themodal)).hover(function() {
						$(this).addClass('ui-state-hover-grid');
					}, function() {
						$(this).removeClass('ui-state-hover-grid');
					});
					$("#sData", frmtb2).click(function() {
						postdata = {};
						$(".FormError", frmgr).hide();
						// all depend on ret
						// array
						// ret[0] - succes
						// ret[1] - msg if
						// not succes
						// ret[2] - the id
						// that will be set
						// if reload after
						// submit false
						getFormData();
						if (postdata[$t.p.id + "_id"] === "_empty") {
							postIt();
						}
						else if (p.checkOnSubmit === true) {
							diff = compareData(postdata, rp_ge[$t.p.id]._savedData);
							if (diff) {
								$(frmgr).data("disabled", true);
								$(".confirm", "#" + $.wizgrid.wizID(IDs.themodal)).show();
							}
							else {
								postIt();
							}
						}
						else {
							postIt();
						}
						return false;
					});
					$("#cData", frmtb2).click(function() {
						if (!checkUpdates()) {
							return false;
						}
						$.wizgrid.hideModal("#" + $.wizgrid.wizID(IDs.themodal), {
							gb: "#gbox_" + $.wizgrid.wizID(gID)
							, wizm: p.wizModal
							, onClose: rp_ge[$t.p.id].onClose
							, removemodal: rp_ge[$t.p.id].removemodal
							, formprop: !rp_ge[$t.p.id].recreateForm
							, form: rp_ge[$t.p.id].form
						});
						return false;
					});
					$("#nData", frmtb2).click(function() {
						if (!checkUpdates()) {
							return false;
						}
						$(".FormError", frmgr).hide();
						var npos = getCurrPos();
						npos[0] = parseInt(npos[0], 10);
						if (npos[0] !== -1 && npos[1][npos[0] + 1]) {
							$($t).triggerHandler("wizGridAddEditClickPgButtons", ['next'
								, $(frmgr)
								, npos[1][npos[0]]
							]);
							var nposret;
							if ($.isFunction(p.onclickPgButtons)) {
								nposret = p.onclickPgButtons.call($t, 'next', $(frmgr), npos[1][npos[0]]);
								if (nposret !== undefined && nposret === false) {
									return false;
								}
							}
							if ($("#" + $.wizgrid.wizID(npos[1][npos[0] + 1])).hasClass('ui-state-disabled')) {
								return false;
							}
							fillData(npos[1][npos[0] + 1], $t, frmgr);
							$($t).wizGrid("setSelection", npos[1][npos[0] + 1]);
							$($t).triggerHandler("wizGridAddEditAfterClickPgButtons", ['next'
								, $(frmgr)
								, npos[1][npos[0]]
							]);
							/*
							 * if($.isFunction(p.afterclickPgButtons)) {
							 * p.afterclickPgButtons.call($t,
							 * 'next',$(frmgr),npos[1][npos[0]+1]); }
							 */
							updateNav(npos[0] + 1, npos);
						}
						return false;
					});
					$("#pData", frmtb2).click(function() {
						if (!checkUpdates()) {
							return false;
						}
						$(".FormError", frmgr).hide();
						var ppos = getCurrPos();
						if (ppos[0] !== -1 && ppos[1][ppos[0] - 1]) {
							$($t).triggerHandler("wizGridAddEditClickPgButtons", ['prev'
								, $(frmgr)
								, ppos[1][ppos[0]]
							]);
							var pposret;
							if ($.isFunction(p.onclickPgButtons)) {
								pposret = p.onclickPgButtons.call($t, 'prev', $(frmgr), ppos[1][ppos[0]]);
								if (pposret !== undefined && pposret === false) {
									return false;
								}
							}
							if ($("#" + $.wizgrid.wizID(ppos[1][ppos[0] - 1])).hasClass('ui-state-disabled')) {
								return false;
							}
							fillData(ppos[1][ppos[0] - 1], $t, frmgr);
							$($t).wizGrid("setSelection", ppos[1][ppos[0] - 1]);
							$($t).triggerHandler("wizGridAddEditAfterClickPgButtons", ['prev'
								, $(frmgr)
								, ppos[1][ppos[0]]
							]);
							if ($.isFunction(p.afterclickPgButtons)) {
								p.afterclickPgButtons.call($t, 'prev', $(frmgr), ppos[1][ppos[0] - 1]);
							}
							updateNav(ppos[0] - 1, ppos);
						}
						return false;
					});
					$($t).triggerHandler("wizGridAddEditAfterShowForm", [$(frmgr), frmoper]);
					if ($.isFunction(rp_ge[$t.p.id].afterShowForm)) {
						rp_ge[$t.p.id].afterShowForm.call($t, $(frmgr), frmoper);
					}
					var posInit = getCurrPos();
					updateNav(posInit[0], posInit);
				});
			}
			, viewGridRow: function(rowid, p) {
				var regional = $.wizgrid.getRegional(this[0], 'view');
				p = $.extend(true, {
					top: 0
					, left: 0
					, width: 0
					, datawidth: 'auto'
					, height: 'auto'
					, dataheight: 'auto'
					, modal: false
					, overlay: 30
					, drag: true
					, resize: true
					, wizModal: true
					, closeOnEscape: false
					, labelswidth: '30%'
					, closeicon: []
					, navkeys: [false, 38, 40]
					, onClose: null
					, beforeShowForm: null
					, beforeInitData: null
					, viewPagerButtons: true
					, recreateForm: false
					, removemodal: true
					, form: 'view'
				}, regional, p || {});
				rp_ge[$(this)[0].p.id] = p;
				return this.each(function() {
					var $t = this;
					if (!$t.grid || !rowid) {
						return;
					}
					var gID = $t.p.id
						, frmgr = "ViewGrid_" + $.wizgrid.wizID(gID)
						, frmtb = "ViewTbl_" + $.wizgrid.wizID(gID)
						, frmgr_id = "ViewGrid_" + gID
						, frmtb_id = "ViewTbl_" + gID
						, IDs = {
							themodal: 'viewmod' + gID
							, modalhead: 'viewhd' + gID
							, modalcontent: 'viewcnt' + gID
							, scrollelm: frmgr
						}
						, onBeforeInit = $.isFunction(rp_ge[$t.p.id].beforeInitData) ? rp_ge[$t.p.id].beforeInitData :
						false
						, showFrm = true
						, maxCols = 1
						, maxRows = 0;
					if (!p.recreateForm) {
						if ($($t).data("viewProp")) {
							$.extend(rp_ge[$(this)[0].p.id], $($t).data("viewProp"));
						}
					}

					function focusaref() { // Sfari 3
						// issues
						if (rp_ge[$t.p.id].closeOnEscape === true || rp_ge[$t.p.id].navkeys[0] === true) {
							setTimeout(function() {
								$("..ui-wizdialog-titlebar-close", "#" + $.wizgrid.wizID(IDs.modalhead)).attr("tabindex"
									, "-1").focus();
							}, 0);
						}
					}

					function createData(rowid, obj, tb, maxcols) {
						var nm, hc, trdata, cnt = 0
							, tmp, dc, retpos = []
							, ind = false
							, i, tdtmpl = "<td class='CaptionTD form-view-label ui-widget-content-grid' width='" + p.labelswidth +
							"'>&#160;</td><td class='DataTD form-view-data ui-helper-reset ui-widget-content-grid'>&#160;</td>"
							, tmpl = ""
							, tdtmpl2 =
							"<td class='CaptionTD form-view-label ui-widget-content-grid'>&#160;</td><td class='DataTD form-view-data ui-widget-content-grid'>&#160;</td>"
							, fmtnum = ['integer', 'number', 'currency']
							, max1 = 0
							, max2 = 0
							, maxw, setme, viewfld;
						for (i = 1; i <= maxcols; i++) {
							tmpl += i === 1 ? tdtmpl : tdtmpl2;
						}
						// find max number align rigth with
						// property formatter
						$(obj.p.Columns).each(function() {
							if (this.editrules && this.editrules.edithidden === true) {
								hc = false;
							}
							else {
								hc = this.hidden === true ? true : false;
							}
							if (!hc && this.align === 'right') {
								if (this.formatter && $.inArray(this.formatter, fmtnum) !== -1) {
									max1 = Math.max(max1, parseInt(this.width, 10));
								}
								else {
									max2 = Math.max(max2, parseInt(this.width, 10));
								}
							}
						});
						maxw = max1 !== 0 ? max1 : max2 !== 0 ? max2 : 0;
						ind = $(obj).wizGrid("getInd", rowid);
						$(obj.p.Columns).each(function(i) {
							nm = this.name;
							setme = false;
							// hidden fields
							// are included
							// in the form
							if (this.editrules && this.editrules.edithidden === true) {
								hc = false;
							}
							else {
								hc = this.hidden === true ? true : false;
							}
							dc = hc ? "style='display:none'" : "";
							viewfld = (typeof this.viewable !== 'boolean') ? true : this.viewable;
							if (nm !== 'cb' && nm !== 'subgrid' && nm !== 'rn' && viewfld) {
								if (ind === false) {
									tmp = "";
								}
								else {
									if (nm === obj.p.ExpandColumn && obj.p.treeGrid === true) {
										tmp = $("td:eq(" + i + ")", obj.rows[ind]).text();
									}
									else {
										tmp = $("td:eq(" + i + ")", obj.rows[ind]).html();
									}
								}
								setme = this.align === 'right' && maxw !== 0 ? true : false;
								var frmopt = $.extend({}, {
										rowabove: false
										, rowcontent: ''
									}, this.formoptions || {})
									, rp = parseInt(frmopt.rowpos, 10) || cnt + 1
									, cp = parseInt(
										(parseInt(frmopt.colpos, 10) || 1) * 2, 10);
								if (frmopt.rowabove) {
									var newdata = $("<tr><td class='contentinfo' colspan='" + (maxcols * 2) + "'>" + frmopt.rowcontent +
										"</td></tr>");
									$(tb).append(newdata);
									newdata[0].rp = rp;
								}
								trdata = $(tb).find("tr[rowpos=" + rp + "]");
								if (trdata.length === 0) {
									trdata = $("<tr " + dc + " rowpos='" + rp + "'></tr>").addClass("FormData").attr("id"
										, "trv_" + nm);
									$(trdata).append(tmpl);
									$(tb).append(trdata);
									trdata[0].rp = rp;
								}
								$("td:eq(" + (cp - 2) + ")", trdata[0]).html('<b>' + (frmopt.label === undefined ? obj.p.Names[
									i] : frmopt.label) + '</b>');
								$("td:eq(" + (cp - 1) + ")", trdata[0]).append("<span>" + tmp + "</span>").attr("id", "v_" +
									nm);
								if (setme) {
									$("td:eq(" + (cp - 1) + ") span", trdata[0]).css({
										'text-align': 'right'
										, width: maxw + "px"
									});
								}
								retpos[cnt] = i;
								cnt++;
							}
						});
						if (cnt > 0) {
							var idrow = $(
								"<tr class='FormData' style='display:none'><td class='CaptionTD'></td><td colspan='" + (
									maxcols * 2 - 1) +
								"' class='DataTD'><input class='FormElement' id='id_g' type='text' name='id' value='" + rowid +
								"'/></td></tr>");
							idrow[0].rp = cnt + 99;
							$(tb).append(idrow);
						}
						return retpos;
					}

					function fillData(rowid, obj) {
						var nm, hc, cnt = 0
							, tmp, trv;
						trv = $(obj).wizGrid("getInd", rowid, true);
						if (!trv) {
							return;
						}
						$('td', trv).each(function(i) {
							nm = obj.p.Columns[i].name;
							// hidden fields
							// are included
							// in the form
							if (obj.p.Columns[i].editrules && obj.p.Columns[i].editrules.edithidden === true) {
								hc = false;
							}
							else {
								hc = obj.p.Columns[i].hidden === true ? true : false;
							}
							if (nm !== 'cb' && nm !== 'subgrid' && nm !== 'rn') {
								if (nm === obj.p.ExpandColumn && obj.p.treeGrid === true) {
									tmp = $(this).text();
								}
								else {
									tmp = $(this).html();
								}
								nm = $.wizgrid.wizID("v_" + nm);
								$("#" + nm + " span", "#" + frmtb).html(tmp);
								if (hc) {
									$("#" + nm, "#" + frmtb).parents("tr:first").hide();
								}
								cnt++;
							}
						});
						if (cnt > 0) {
							$("#id_g", "#" + frmtb).val(rowid);
						}
					}

					function updateNav(cr, posarr) {
						var totr = posarr[1].length - 1;
						if (cr === 0) {
							$("#pData", "#" + frmtb + "_2").addClass('ui-state-disabled');
						}
						else if (posarr[1][cr - 1] !== undefined && $("#" + $.wizgrid.wizID(posarr[1][cr - 1])).hasClass(
								'ui-state-disabled')) {
							$("#pData", frmtb + "_2").addClass('ui-state-disabled');
						}
						else {
							$("#pData", "#" + frmtb + "_2").removeClass('ui-state-disabled');
						}
						if (cr === totr) {
							$("#nData", "#" + frmtb + "_2").addClass('ui-state-disabled');
						}
						else if (posarr[1][cr + 1] !== undefined && $("#" + $.wizgrid.wizID(posarr[1][cr + 1])).hasClass(
								'ui-state-disabled')) {
							$("#nData", frmtb + "_2").addClass('ui-state-disabled');
						}
						else {
							$("#nData", "#" + frmtb + "_2").removeClass('ui-state-disabled');
						}
					}

					function getCurrPos() {
						var rowsInGrid = $($t).wizGrid("getDataIDs")
							, selrow = $("#id_g", "#" + frmtb).val()
							, pos = $.inArray(selrow, rowsInGrid);
						return [pos, rowsInGrid];
					}
					var dh = isNaN(rp_ge[$(this)[0].p.id].dataheight) ? rp_ge[$(this)[0].p.id].dataheight : rp_ge[$(
							this)[0].p.id].dataheight + "px"
						, dw = isNaN(rp_ge[$(this)[0].p.id].datawidth) ? rp_ge[$(this)[0].p.id].datawidth : rp_ge[$(this)[
							0].p.id].datawidth + "px"
						, frm = $("<form name='FormPost' id='" + frmgr_id + "' class='FormGrid' style='width:" + dw +
							";overflow:auto;position:relative;height:" + dh + ";'></form>")
						, tbl = $("<table id='" + frmtb_id +
							"' class='EditTable' cellspacing='1' cellpadding='2' border='0' style='table-layout:fixed'><tbody></tbody></table>"
						);
					$($t.p.Columns).each(function() {
						var fmto = this.formoptions;
						maxCols = Math.max(maxCols, fmto ? fmto.colpos || 0 : 0);
						maxRows = Math.max(maxRows, fmto ? fmto.rowpos || 0 : 0);
					});
					// set the id.
					$(frm).append(tbl);
					if (onBeforeInit) {
						showFrm = onBeforeInit.call($t, frm);
						if (showFrm === undefined) {
							showFrm = true;
						}
					}
					if (showFrm === false) {
						return;
					}
					createData(rowid, $t, tbl, maxCols);
					var rtlb = $t.p.direction === "rtl" ? true : false
						, bp = rtlb ? "nData" : "pData"
						, bn = rtlb ? "pData" : "nData"
						, // buttons at footer
						bP = "<a id='" + bp +
						"' class='fm-button ui-state-default-grid ui-corner-left-grid'><span class='ui-icon ui-icon-triangle-1-w'></span></a>"
						, bN = "<a id='" + bn +
						"' class='fm-button ui-state-default-grid ui-corner-right'><span class='ui-icon ui-icon-triangle-1-e'></span></a>"
						, bC = "<a id='cData' class='fm-button ui-state-default-grid ui-corner-all-grid'>" + p.bClose +
						"</a>";
					if (maxRows > 0) {
						var sd = [];
						$.each($(tbl)[0].rows, function(i, r) {
							sd[i] = r;
						});
						sd.sort(function(a, b) {
							if (a.rp > b.rp) {
								return 1;
							}
							if (a.rp < b.rp) {
								return -1;
							}
							return 0;
						});
						$.each(sd, function(index, row) {
							$('tbody', tbl).append(row);
						});
					}
					p.gbox = "#gbox_" + $.wizgrid.wizID(gID);
					var bt = $("<div></div>").append(frm).append("<table border='0' class='EditTable' id='" + frmtb +
						"_2'><tbody><tr id='Act_Buttons'><td class='navButton' width='" + p.labelswidth + "'>" + (rtlb ?
							bN + bP : bP + bN) + "</td><td class='EditButton'>" + bC + "</td></tr></tbody></table>");
					$.wizgrid.createModal(IDs, bt, p, "#gview_" + $.wizgrid.wizID($t.p.id), $("#gview_" + $.wizgrid.wizID(
						$t.p.id))[0]);
					if (rtlb) {
						$("#pData, #nData", "#" + frmtb + "_2").css("float", "right");
						$(".EditButton", "#" + frmtb + "_2").css("text-align", "left");
					}
					if (!p.viewPagerButtons) {
						$("#pData, #nData", "#" + frmtb + "_2").hide();
					}
					bt = null;
					$("#" + IDs.themodal).keydown(function(e) {
						if (e.which === 27) {
							if (rp_ge[$t.p.id].closeOnEscape) {
								$.wizgrid.hideModal("#" + $.wizgrid.wizID(IDs.themodal), {
									gb: p.gbox
									, wizm: p.wizModal
									, onClose: p.onClose
									, removemodal: rp_ge[$t.p.id].removemodal
									, formprop: !rp_ge[$t.p.id].recreateForm
									, form: rp_ge[$t.p.id].form
								});
							}
							return false;
						}
						if (p.navkeys[0] === true) {
							if (e.which === p.navkeys[1]) { // up
								$("#pData", "#" + frmtb + "_2").trigger("click");
								return false;
							}
							if (e.which === p.navkeys[2]) { // down
								$("#nData", "#" + frmtb + "_2").trigger("click");
								return false;
							}
						}
					});
					p.closeicon = $.extend([true, "left", "ui-icon-close"], p.closeicon);
					if (p.closeicon[0] === true) {
						$("#cData", "#" + frmtb + "_2").addClass(p.closeicon[1] === "right" ? 'fm-button-icon-right' :
							'fm-button-icon-left').append("<span class='ui-icon " + p.closeicon[2] + "'></span>");
					}
					if ($.isFunction(p.beforeShowForm)) {
						p.beforeShowForm.call($t, $("#" + frmgr));
					}
					$.wizgrid.viewModal("#" + $.wizgrid.wizID(IDs.themodal), {
						gbox: "#gbox_" + $.wizgrid.wizID(gID)
						, wizm: p.wizModal
						, overlay: p.overlay
						, modal: p.modal
						, onHide: function(h) {
							$($t).data("viewProp", {
								top: parseFloat($(h.w).css("top"))
								, left: parseFloat($(h.w).css("left"))
								, width: $(h.w).width()
								, height: $(h.w).height()
								, dataheight: $("#" + frmgr).height()
								, datawidth: $("#" + frmgr).width()
							});
							h.w.remove();
							if (h.o) {
								h.o.remove();
							}
						}
					});
					$(".fm-button:not(.ui-state-disabled)", "#" + frmtb + "_2").hover(function() {
						$(this).addClass('ui-state-hover-grid');
					}, function() {
						$(this).removeClass('ui-state-hover-grid');
					});
					focusaref();
					$("#cData", "#" + frmtb + "_2").click(function() {
						$.wizgrid.hideModal("#" + $.wizgrid.wizID(IDs.themodal), {
							gb: "#gbox_" + $.wizgrid.wizID(gID)
							, wizm: p.wizModal
							, onClose: p.onClose
							, removemodal: rp_ge[$t.p.id].removemodal
							, formprop: !rp_ge[$t.p.id].recreateForm
							, form: rp_ge[$t.p.id].form
						});
						return false;
					});
					$("#nData", "#" + frmtb + "_2").click(function() {
						$("#FormError", "#" + frmtb).hide();
						var npos = getCurrPos();
						npos[0] = parseInt(npos[0], 10);
						if (npos[0] !== -1 && npos[1][npos[0] + 1]) {
							if ($.isFunction(p.onclickPgButtons)) {
								p.onclickPgButtons.call($t, 'next', $("#" + frmgr), npos[1][npos[0]]);
							}
							fillData(npos[1][npos[0] + 1], $t);
							$($t).wizGrid("setSelection", npos[1][npos[0] + 1]);
							if ($.isFunction(p.afterclickPgButtons)) {
								p.afterclickPgButtons.call($t, 'next', $("#" + frmgr), npos[1][npos[0] + 1]);
							}
							updateNav(npos[0] + 1, npos);
						}
						focusaref();
						return false;
					});
					$("#pData", "#" + frmtb + "_2").click(function() {
						$("#FormError", "#" + frmtb).hide();
						var ppos = getCurrPos();
						if (ppos[0] !== -1 && ppos[1][ppos[0] - 1]) {
							if ($.isFunction(p.onclickPgButtons)) {
								p.onclickPgButtons.call($t, 'prev', $("#" + frmgr), ppos[1][ppos[0]]);
							}
							fillData(ppos[1][ppos[0] - 1], $t);
							$($t).wizGrid("setSelection", ppos[1][ppos[0] - 1]);
							if ($.isFunction(p.afterclickPgButtons)) {
								p.afterclickPgButtons.call($t, 'prev', $("#" + frmgr), ppos[1][ppos[0] - 1]);
							}
							updateNav(ppos[0] - 1, ppos);
						}
						focusaref();
						return false;
					});
					var posInit = getCurrPos();
					updateNav(posInit[0], posInit);
				});
			}
			, delGridRow: function(rowids, p) {
				var regional = $.wizgrid.getRegional(this[0], 'del');
				p = $.extend(true, {
					top: 0
					, left: 0
					, width: 240
					, height: 'auto'
					, dataheight: 'auto'
					, modal: false
					, overlay: 30
					, drag: true
					, resize: true
					, url: ''
					, mtype: "POST"
					, reloadAfterSubmit: true
					, beforeShowForm: null
					, beforeInitData: null
					, afterShowForm: null
					, beforeSubmit: null
					, onclickSubmit: null
					, afterSubmit: null
					, wizModal: true
					, closeOnEscape: false
					, delData: {}
					, delicon: []
					, cancelicon: []
					, onClose: null
					, ajaxDelOptions: {}
					, processing: false
					, serializeDelData: null
					, useDataProxy: false
				}, regional, p || {});
				rp_ge[$(this)[0].p.id] = p;
				return this.each(function() {
					var $t = this;
					if (!$t.grid) {
						return;
					}
					if (!rowids) {
						return;
					}
					var onBeforeShow = $.isFunction(rp_ge[$t.p.id].beforeShowForm)
						, onAfterShow = $.isFunction(rp_ge[$t.p.id].afterShowForm)
						, onBeforeInit = $.isFunction(rp_ge[$t.p.id].beforeInitData) ? rp_ge[$t.p.id].beforeInitData :
						false
						, gID = $t.p.id
						, onCS = {}
						, showFrm = true
						, dtbl = "DelTbl_" + $.wizgrid.wizID(gID)
						, postd, idname, opers, oper, dtbl_id = "DelTbl_" + gID
						, IDs = {
							themodal: 'delmod' + gID
							, modalhead: 'delhd' + gID
							, modalcontent: 'delcnt' + gID
							, scrollelm: dtbl
						};
					if ($.isArray(rowids)) {
						rowids = rowids.join();
					}
					if ($("#" + $.wizgrid.wizID(IDs.themodal))[0] !== undefined) {
						if (onBeforeInit) {
							showFrm = onBeforeInit.call($t, $("#" + dtbl));
							if (showFrm === undefined) {
								showFrm = true;
							}
						}
						if (showFrm === false) {
							return;
						}
						$("#DelData>td", "#" + dtbl).text(rowids);
						$("#DelError", "#" + dtbl).hide();
						if (rp_ge[$t.p.id].processing === true) {
							rp_ge[$t.p.id].processing = false;
							$("#dData", "#" + dtbl).removeClass('ui-state-active');
						}
						if (onBeforeShow) {
							rp_ge[$t.p.id].beforeShowForm.call($t, $("#" + dtbl));
						}
						$.wizgrid.viewModal("#" + $.wizgrid.wizID(IDs.themodal), {
							gbox: "#gbox_" + $.wizgrid.wizID(gID)
							, wizm: rp_ge[$t.p.id].wizModal
							, wizM: false
							, overlay: rp_ge[$t.p.id].overlay
							, modal: rp_ge[$t.p.id].modal
						});
						if (onAfterShow) {
							rp_ge[$t.p.id].afterShowForm.call($t, $("#" + dtbl));
						}
					}
					else {
						var dh = isNaN(rp_ge[$t.p.id].dataheight) ? rp_ge[$t.p.id].dataheight : rp_ge[$t.p.id].dataheight +
							"px"
							, dw = isNaN(p.datawidth) ? p.datawidth : p.datawidth + "px"
							, tbl = "<div id='" + dtbl_id + "' class='formdata' style='width:" + dw +
							";overflow:auto;position:relative;height:" + dh + ";'>";
						tbl += "<table class='DelTable'><tbody>";
						// error data
						tbl += "<tr id='DelError' style='display:none'><td class='ui-state-error'></td></tr>";
						tbl += "<tr id='DelData' style='display:none'><td >" + rowids + "</td></tr>";
						tbl += "<tr><td class=\"delmsg\" style=\"white-space:pre;\">" + rp_ge[$t.p.id].msg +
							"</td></tr><tr><td >&#160;</td></tr>";
						// buttons at footer
						tbl += "</tbody></table></div>";
						var bS = "<a id='dData' class='fm-button ui-state-default-grid ui-corner-all-grid'>" + p.bSubmit +
							"</a>"
							, bC = "<a id='eData' class='fm-button ui-state-default-grid ui-corner-all-grid'>" + p.bCancel +
							"</a>";
						tbl += "<table cellspacing='0' cellpadding='0' border='0' class='EditTable' id='" + dtbl +
							"_2'><tbody><tr><td><hr class='ui-widget-content-grid' style='margin:1px'/></td></tr><tr><td class='DelButton EditButton'>" +
							bS + "&#160;" + bC + "</td></tr></tbody></table>";
						p.gbox = "#gbox_" + $.wizgrid.wizID(gID);
						$.wizgrid.createModal(IDs, tbl, p, "#gview_" + $.wizgrid.wizID($t.p.id), $("#gview_" + $.wizgrid.wizID(
							$t.p.id))[0]);
						if (onBeforeInit) {
							showFrm = onBeforeInit.call($t, $(tbl));
							if (showFrm === undefined) {
								showFrm = true;
							}
						}
						if (showFrm === false) {
							return;
						}
						$(".fm-button", "#" + dtbl + "_2").hover(function() {
							$(this).addClass('ui-state-hover-grid');
						}, function() {
							$(this).removeClass('ui-state-hover-grid');
						});
						p.delicon = $.extend(
							[true, "left", "ui-icon-scissors"], rp_ge[$t.p.id].delicon);
						p.cancelicon = $.extend([true, "left", "ui-icon-cancel"], rp_ge[$t.p.id].cancelicon);
						if (p.delicon[0] === true) {
							$("#dData", "#" + dtbl + "_2").addClass(p.delicon[1] === "right" ? 'fm-button-icon-right' :
								'fm-button-icon-left').append("<span class='ui-icon " + p.delicon[2] + "'></span>");
						}
						if (p.cancelicon[0] === true) {
							$("#eData", "#" + dtbl + "_2").addClass(p.cancelicon[1] === "right" ? 'fm-button-icon-right' :
								'fm-button-icon-left').append("<span class='ui-icon " + p.cancelicon[2] + "'></span>");
						}
						$("#dData", "#" + dtbl + "_2").click(function() {
							var ret = [
									true, ""
								]
								, pk, postdata = $("#DelData>td", "#" + dtbl).text(); // the
							// pair
							// is
							// name=val1,val2,...
							onCS = {};
							if ($.isFunction(rp_ge[$t.p.id].onclickSubmit)) {
								onCS = rp_ge[$t.p.id].onclickSubmit.call($t, rp_ge[$t.p.id], postdata) || {};
							}
							if ($.isFunction(rp_ge[$t.p.id].beforeSubmit)) {
								ret = rp_ge[$t.p.id].beforeSubmit.call($t, postdata);
							}
							if (ret[0] && !rp_ge[$t.p.id].processing) {
								rp_ge[$t.p.id].processing = true;
								opers = $t.p.prmNames;
								postd = $.extend({}, rp_ge[$t.p.id].delData, onCS);
								oper = opers.oper;
								postd[oper] = opers.deloper;
								idname = opers.id;
								postdata = String(postdata).split(",");
								if (!postdata.length) {
									return false;
								}
								for (pk in postdata) {
									if (postdata.hasOwnProperty(pk)) {
										postdata[pk] = $.wizgrid.stripPref($t.p.idPrefix, postdata[pk]);
									}
								}
								postd[idname] = postdata.join();
								$(this).addClass('ui-state-active');
								var ajaxOptions = $.extend({
									url: rp_ge[$t.p.id].url || $($t).wizGrid('getGridParam', 'editurl')
									, type: rp_ge[$t.p.id].mtype
									, data: $.isFunction(rp_ge[$t.p.id].serializeDelData) ? rp_ge[$t.p.id].serializeDelData.call(
										$t, postd) : postd
									, complete: function(data, status) {
										var i;
										$("#dData", "#" + dtbl + "_2").removeClass('ui-state-active');
										if (data.status >= 300 && data.status !== 304) {
											ret[0] = false;
											if ($.isFunction(rp_ge[$t.p.id].errorTextFormat)) {
												ret[1] = rp_ge[$t.p.id].errorTextFormat.call($t, data);
											}
											else {
												ret[1] = status + " Status: '" + data.statusText + "'. Error code: " + data.status;
											}
										}
										else {
											// data
											// is
											// posted
											// successful
											// execute
											// aftersubmit
											// with
											// the
											// returned
											// data
											// from
											// server
											if ($.isFunction(rp_ge[$t.p.id].afterSubmit)) {
												ret = rp_ge[$t.p.id].afterSubmit.call($t, data, postd);
											}
										}
										if (ret[0] === false) {
											$("#DelError>td", "#" + dtbl).html(ret[1]);
											$("#DelError", "#" + dtbl).show();
										}
										else {
											if (rp_ge[$t.p.id].reloadAfterSubmit && $t.p.datatype !== "local") {
												$($t).trigger("reloadGrid");
											}
											else {
												if ($t.p.treeGrid === true) {
													try {
														$($t).wizGrid("delTreeNode", $t.p.idPrefix + postdata[0]);
													}
													catch (e) {}
												}
												else {
													for (i = 0; i < postdata.length; i++) {
														$($t).wizGrid("delRowData", $t.p.idPrefix + postdata[i]);
													}
												}
												$t.p.selrow = null;
												$t.p.selarrrow = [];
											}
											if ($.isFunction(rp_ge[$t.p.id].afterComplete)) {
												setTimeout(function() {
													rp_ge[$t.p.id].afterComplete.call($t, data, postdata);
												}, 500);
											}
										}
										rp_ge[$t.p.id].processing = false;
										if (ret[0]) {
											$.wizgrid.hideModal("#" + $.wizgrid.wizID(IDs.themodal), {
												gb: "#gbox_" + $.wizgrid.wizID(gID)
												, wizm: p.wizModal
												, onClose: rp_ge[$t.p.id].onClose
											});
										}
									}
								}, $.wizgrid.ajaxOptions, rp_ge[$t.p.id].ajaxDelOptions);
								if (!ajaxOptions.url && !rp_ge[$t.p.id].useDataProxy) {
									if ($.isFunction($t.p.dataProxy)) {
										rp_ge[$t.p.id].useDataProxy = true;
									}
									else {
										ret[0] = false;
										ret[1] += " " + $.wizgrid.getRegional($t, 'errors.nourl');
									}
								}
								if (ret[0]) {
									if (rp_ge[$t.p.id].useDataProxy) {
										var dpret = $t.p.dataProxy.call($t, ajaxOptions, "del_" + $t.p.id);
										if (dpret === undefined) {
											dpret = [
												true, ""
											];
										}
										if (dpret[0] === false) {
											ret[0] = false;
											ret[1] = dpret[1] || "Error deleting the selected row!";
										}
										else {
											$.wizgrid.hideModal("#" + $.wizgrid.wizID(IDs.themodal), {
												gb: "#gbox_" + $.wizgrid.wizID(gID)
												, wizm: p.wizModal
												, onClose: rp_ge[$t.p.id].onClose
											});
										}
									}
									else {
										if (ajaxOptions.url === "clientArray") {
											postd = ajaxOptions.data;
											ajaxOptions.complete({
												status: 200
												, statusText: ''
											}, '');
										}
										else {
											$.ajax(ajaxOptions);
										}
									}
								}
							}
							if (ret[0] === false) {
								$("#DelError>td", "#" + dtbl).html(ret[1]);
								$("#DelError", "#" + dtbl).show();
							}
							return false;
						});
						$("#eData", "#" + dtbl + "_2").click(function() {
							$.wizgrid.hideModal("#" + $.wizgrid.wizID(IDs.themodal), {
								gb: "#gbox_" + $.wizgrid.wizID(gID)
								, wizm: rp_ge[$t.p.id].wizModal
								, onClose: rp_ge[$t.p.id].onClose
							});
							return false;
						});
						if (onBeforeShow) {
							rp_ge[$t.p.id].beforeShowForm.call($t, $("#" + dtbl));
						}
						$.wizgrid.viewModal("#" + $.wizgrid.wizID(IDs.themodal), {
							gbox: "#gbox_" + $.wizgrid.wizID(gID)
							, wizm: rp_ge[$t.p.id].wizModal
							, overlay: rp_ge[$t.p.id].overlay
							, modal: rp_ge[$t.p.id].modal
						});
						if (onAfterShow) {
							rp_ge[$t.p.id].afterShowForm.call($t, $("#" + dtbl));
						}
					}
					if (rp_ge[$t.p.id].closeOnEscape === true) {
						setTimeout(function() {
							$("..ui-wizdialog-titlebar-close", "#" + $.wizgrid.wizID(IDs.modalhead)).attr("tabindex", "-1")
								.focus();
						}, 0);
					}
				});
			}
			, navGrid: function(elem, p, pEdit, pAdd, pDel, pSearch, pView) {
				var regional = $.wizgrid.getRegional(this[0], 'nav');
				p = $.extend({
					edit: true
					, editicon: "ui-icon-pencil"
					, add: true
					, addicon: "ui-icon-plus"
					, del: true
					, delicon: "ui-icon-trash"
					, search: true
					, searchicon: "ui-icon-search"
					, refresh: true
					, refreshicon: "ui-icon-refresh"
					, refreshstate: 'firstpage'
					, view: false
					, viewicon: "ui-icon-document"
					, position: "left"
					, closeOnEscape: true
					, beforeRefresh: null
					, afterRefresh: null
					, cloneToTop: false
					, alertwidth: 200
					, alertheight: 'auto'
					, alerttop: null
					, alertleft: null
					, alertzIndex: null
				}, regional, p || {});
				return this.each(function() {
					if (this.p.navGrid) {
						return;
					}
					var alertIDs = {
							themodal: 'alertmod_' + this.p.id
							, modalhead: 'alerthd_' + this.p.id
							, modalcontent: 'alertcnt_' + this.p.id
						}
						, $t = this
						, twd, tdw, o;
					if (!$t.grid || typeof elem !== 'string') {
						return;
					}
					if (!$($t).data('navGrid')) {
						$($t).data('navGrid', p);
					}
					// speedoverhead, but usefull for future
					o = $($t).data('navGrid');
					if ($t.p.force_regional) {
						o = $.extend(o, regional);
					}
					if ($("#" + alertIDs.themodal)[0] === undefined) {
						if (!o.alerttop && !o.alertleft) {
							if (window.innerWidth !== undefined) {
								o.alertleft = window.innerWidth;
								o.alerttop = window.innerHeight;
							}
							else if (document.documentElement !== undefined && document.documentElement.clientWidth !==
								undefined && document.documentElement.clientWidth !== 0) {
								o.alertleft = document.documentElement.clientWidth;
								o.alerttop = document.documentElement.clientHeight;
							}
							else {
								o.alertleft = 1024;
								o.alerttop = 768;
							}
							o.alertleft = o.alertleft / 2 - parseInt(o.alertwidth, 10) / 2;
							o.alerttop = o.alerttop / 2 - 25;
						}
						$.wizgrid.createModal(alertIDs, "<div>" + o.alerttext +
							"</div><span tabindex='0'><span tabindex='-1' id='wizg_alrt'></span></span>", {
								gbox: "#gbox_" + $.wizgrid.wizID($t.p.id)
								, wizModal: true
								, drag: true
								, resize: true
								, caption: o.alertcap
								, top: o.alerttop
								, left: o.alertleft
								, width: o.alertwidth
								, height: o.alertheight
								, closeOnEscape: o.closeOnEscape
								, zIndex: o.alertzIndex
							}, "#gview_" + $.wizgrid.wizID($t.p.id), $("#gbox_" + $.wizgrid.wizID($t.p.id))[0], true);
					}
					var clone = 1
						, i, onHoverIn = function() {
							if (!$(this).hasClass('ui-state-disabled')) {
								$(this).addClass("ui-state-hover-grid");
							}
						}
						, onHoverOut = function() {
							$(this).removeClass("ui-state-hover-grid");
						};
					if (o.cloneToTop && $t.p.toppager) {
						clone = 2;
					}
					for (i = 0; i < clone; i++) {
						var tbd, navtbl = $(
								"<table cellspacing='0' cellpadding='0' border='0' class='ui-pg-table navtable' style='float:left;table-layout:auto;'><tbody><tr></tr></tbody></table>"
							)
							, sep =
							"<td class='ui-pg-button ui-state-disabled' style='width:4px;'><span class='ui-separator'></span></td>"
							, pgid, elemids;
						if (i === 0) {
							pgid = elem;
							elemids = $t.p.id;
							if (pgid === $t.p.toppager) {
								elemids += "_top";
								clone = 1;
							}
						}
						else {
							pgid = $t.p.toppager;
							elemids = $t.p.id + "_top";
						}
						if ($t.p.direction === "rtl") {
							$(navtbl).attr("dir", "rtl").css("float", "right");
						}
						pAdd = pAdd || {};
						if (o.add) {
							tbd = $("<td class='ui-pg-button ui-corner-all-grid'></td>");
							$(tbd).append("<div class='ui-pg-div'><span class='ui-icon " + o.addicon + "'></span>" + o.addtext +
								"</div>");
							$("tr", navtbl).append(tbd);
							$(tbd, navtbl).attr({
								"title": o.addtitle || ""
								, id: pAdd.id || "add_" + elemids
							}).click(function() {
								if (!$(this).hasClass('ui-state-disabled')) {
									if ($.isFunction(o.addfunc)) {
										o.addfunc.call($t);
									}
									else {
										$($t).wizGrid("editGridRow", "new", pAdd);
									}
								}
								return false;
							}).hover(onHoverIn, onHoverOut);
							tbd = null;
						}
						pEdit = pEdit || {};
						if (o.edit) {
							tbd = $("<td class='ui-pg-button ui-corner-all-grid'></td>");
							$(tbd).append("<div class='ui-pg-div'><span class='ui-icon " + o.editicon + "'></span>" + o.edittext +
								"</div>");
							$("tr", navtbl).append(tbd);
							$(tbd, navtbl).attr({
								"title": o.edittitle || ""
								, id: pEdit.id || "edit_" + elemids
							}).click(function() {
								if (!$(this).hasClass('ui-state-disabled')) {
									var sr = $t.p.selrow;
									if (sr) {
										if ($.isFunction(o.editfunc)) {
											o.editfunc.call($t, sr);
										}
										else {
											$($t).wizGrid("editGridRow", sr, pEdit);
										}
									}
									else {
										$.wizgrid.viewModal("#" + alertIDs.themodal, {
											gbox: "#gbox_" + $.wizgrid.wizID($t.p.id)
											, wizm: true
										});
										$("#wizg_alrt").focus();
									}
								}
								return false;
							}).hover(onHoverIn, onHoverOut);
							tbd = null;
						}
						pView = pView || {};
						if (o.view) {
							tbd = $("<td class='ui-pg-button ui-corner-all-grid'></td>");
							$(tbd).append("<div class='ui-pg-div'><span class='ui-icon " + o.viewicon + "'></span>" + o.viewtext +
								"</div>");
							$("tr", navtbl).append(tbd);
							$(tbd, navtbl).attr({
								"title": o.viewtitle || ""
								, id: pView.id || "view_" + elemids
							}).click(function() {
								if (!$(this).hasClass('ui-state-disabled')) {
									var sr = $t.p.selrow;
									if (sr) {
										if ($.isFunction(o.viewfunc)) {
											o.viewfunc.call($t, sr);
										}
										else {
											$($t).wizGrid("viewGridRow", sr, pView);
										}
									}
									else {
										$.wizgrid.viewModal("#" + alertIDs.themodal, {
											gbox: "#gbox_" + $.wizgrid.wizID($t.p.id)
											, wizm: true
										});
										$("#wizg_alrt").focus();
									}
								}
								return false;
							}).hover(onHoverIn, onHoverOut);
							tbd = null;
						}
						pDel = pDel || {};
						if (o.del) {
							tbd = $("<td class='ui-pg-button ui-corner-all-grid'></td>");
							$(tbd).append("<div class='ui-pg-div'><span class='ui-icon " + o.delicon + "'></span>" + o.deltext +
								"</div>");
							$("tr", navtbl).append(tbd);
							$(tbd, navtbl).attr({
								"title": o.deltitle || ""
								, id: pDel.id || "del_" + elemids
							}).click(function() {
								if (!$(this).hasClass('ui-state-disabled')) {
									var dr;
									if ($t.p.multiselect) {
										dr = $t.p.selarrrow;
										if (dr.length === 0) {
											dr = null;
										}
									}
									else {
										dr = $t.p.selrow;
									}
									if (dr) {
										if ($.isFunction(o.delfunc)) {
											o.delfunc.call($t, dr);
										}
										else {
											$($t).wizGrid("delGridRow", dr, pDel);
										}
									}
									else {
										$.wizgrid.viewModal("#" + alertIDs.themodal, {
											gbox: "#gbox_" + $.wizgrid.wizID($t.p.id)
											, wizm: true
										});
										$("#wizg_alrt").focus();
									}
								}
								return false;
							}).hover(onHoverIn, onHoverOut);
							tbd = null;
						}
						if (o.add || o.edit || o.del || o.view) {
							$("tr", navtbl).append(sep);
						}
						pSearch = pSearch || {};
						if (o.search) {
							tbd = $("<td class='ui-pg-button ui-corner-all-grid'></td>");
							$(tbd).append("<div class='ui-pg-div'><span class='ui-icon " + o.searchicon + "'></span>" + o.searchtext +
								"</div>");
							$("tr", navtbl).append(tbd);
							$(tbd, navtbl).attr({
								"title": o.searchtitle || ""
								, id: pSearch.id || "search_" + elemids
							}).click(function() {
								if (!$(this).hasClass('ui-state-disabled')) {
									if ($.isFunction(o.searchfunc)) {
										o.searchfunc.call($t, pSearch);
									}
									else {
										$($t).wizGrid("searchGrid", pSearch);
									}
								}
								return false;
							}).hover(onHoverIn, onHoverOut);
							if (pSearch.showOnLoad && pSearch.showOnLoad === true) {
								$(tbd, navtbl).click();
							}
							tbd = null;
						}
						if (o.refresh) {
							tbd = $("<td class='ui-pg-button ui-corner-all-grid'></td>");
							$(tbd).append("<div class='ui-pg-div'><span class='ui-icon " + o.refreshicon + "'></span>" + o.refreshtext +
								"</div>");
							$("tr", navtbl).append(tbd);
							$(tbd, navtbl).attr({
								"title": o.refreshtitle || ""
								, id: "refresh_" + elemids
							}).click(function() {
								if (!$(this).hasClass('ui-state-disabled')) {
									if ($.isFunction(o.beforeRefresh)) {
										o.beforeRefresh.call($t);
									}
									$t.p.search = false;
									$t.p.resetsearch = true;
									try {
										if (o.refreshstate !== 'currentfilter') {
											var gID = $t.p.id;
											$t.p.postData.filters = "";
											try {
												$("#fbox_" + $.wizgrid.wizID(gID)).wizFilter('resetFilter');
											}
											catch (ef) {}
											if ($.isFunction($t.clearToolbar)) {
												$t.clearToolbar.call($t, false);
											}
										}
									}
									catch (e) {}
									switch (o.refreshstate) {
										case 'firstpage':
											$($t).trigger("reloadGrid", [{
												page: 1
											}]);
											break;
										case 'current':
										case 'currentfilter':
											$($t).trigger("reloadGrid", [{
												current: true
											}]);
											break;
									}
									if ($.isFunction(o.afterRefresh)) {
										o.afterRefresh.call($t);
									}
								}
								return false;
							}).hover(onHoverIn, onHoverOut);
							tbd = null;
						}
						tdw = $(".ui-wizgrid").css("font-size") || "11px";
						$('body').append(
							"<div id='testpg2' class='ui-wizgrid ui-widget ui-widget-content-grid' style='font-size:" + tdw +
							";visibility:hidden;' ></div>");
						twd = $(navtbl).clone().appendTo("#testpg2").width();
						$("#testpg2").remove();
						$(pgid + "_" + o.position, pgid).append(navtbl);
						if ($t.p._nvtd) {
							if (twd > $t.p._nvtd[0]) {
								$(pgid + "_" + o.position, pgid).width(twd);
								$t.p._nvtd[0] = twd;
							}
							$t.p._nvtd[1] = twd;
						}
						tdw = null;
						twd = null;
						navtbl = null;
						$t.p.navGrid = true;
					}
					if ($t.p.storeNavOptions) {
						$t.p.navOptions = o;
						$t.p.editOptions = pEdit;
						$t.p.addOptions = pAdd;
						$t.p.delOptions = pDel;
						$t.p.searchOptions = pSearch;
						$t.p.viewOptions = pView;
					}
				});
			}
			, navButtonAdd: function(elem, p) {
				p = $.extend({
					caption: "newButton"
					, title: ''
					, buttonicon: 'ui-icon-newwin'
					, onClickButton: null
					, position: "last"
					, cursor: 'pointer'
				}, p || {});
				return this.each(function() {
					if (!this.grid) {
						return;
					}
					if (typeof elem === "string" && elem.indexOf("#") !== 0) {
						elem = "#" + $.wizgrid.wizID(elem);
					}
					var findnav = $(".navtable", elem)[0]
						, $t = this;
					if (findnav) {
						if (p.id && $("#" + $.wizgrid.wizID(p.id), findnav)[0] !== undefined) {
							return;
						}
						var tbd = $("<td></td>");
						if (p.buttonicon.toString().toUpperCase() === "NONE") {
							$(tbd).addClass('ui-pg-button ui-corner-all-grid').append("<div class='ui-pg-div'>" + p.caption +
								"</div>");
						}
						else {
							$(tbd).addClass('ui-pg-button ui-corner-all-grid').append(
								"<div class='ui-pg-div'><span class='ui-icon " + p.buttonicon + "'></span>" + p.caption +
								"</div>");
						}
						if (p.id) {
							$(tbd).attr("id", p.id);
						}
						if (p.position === 'first') {
							if (findnav.rows[0].cells.length === 0) {
								$("tr", findnav).append(tbd);
							}
							else {
								$("tr td:eq(0)", findnav).before(tbd);
							}
						}
						else {
							$("tr", findnav).append(tbd);
						}
						$(tbd, findnav).attr("title", p.title || "").click(function(e) {
							if (!$(this).hasClass('ui-state-disabled')) {
								if ($.isFunction(p.onClickButton)) {
									p.onClickButton.call($t, e);
								}
							}
							return false;
						}).hover(function() {
							if (!$(this).hasClass('ui-state-disabled')) {
								$(this).addClass('ui-state-hover-grid');
							}
						}, function() {
							$(this).removeClass("ui-state-hover-grid");
						});
					}
				});
			}
			, navSeparatorAdd: function(elem, p) {
				p = $.extend({
					sepclass: "ui-separator"
					, sepcontent: ''
					, position: "last"
				}, p || {});
				return this.each(function() {
					if (!this.grid) {
						return;
					}
					if (typeof elem === "string" && elem.indexOf("#") !== 0) {
						elem = "#" + $.wizgrid.wizID(elem);
					}
					var findnav = $(".navtable", elem)[0];
					if (findnav) {
						var sep = "<td class='ui-pg-button ui-state-disabled' style='width:4px;'><span class='" + p.sepclass +
							"'></span>" + p.sepcontent + "</td>";
						if (p.position === 'first') {
							if (findnav.rows[0].cells.length === 0) {
								$("tr", findnav).append(sep);
							}
							else {
								$("tr td:eq(0)", findnav).before(sep);
							}
						}
						else {
							$("tr", findnav).append(sep);
						}
					}
				});
			}
			, GridToForm: function(rowid, formid) {
				return this.each(function() {
					var $t = this
						, i;
					if (!$t.grid) {
						return;
					}
					var rowdata = $($t).wizGrid("getRowData", rowid);
					if (rowdata) {
						for (i in rowdata) {
							if (rowdata.hasOwnProperty(i)) {
								if ($("[name=" + $.wizgrid.wizID(i) + "]", formid).is("input:radio") || $("[name=" + $.wizgrid.wizID(
										i) + "]", formid).is("input:checkbox")) {
									$("[name=" + $.wizgrid.wizID(i) + "]", formid).each(function() {
										if ($(this).val() == rowdata[i]) {
											$(this)[$t.p.useProp ? 'prop' : 'attr']
												("checked", true);
										}
										else {
											$(this)[$t.p.useProp ? 'prop' : 'attr']
												("checked", false);
										}
									});
								}
								else {
									// this is very slow on
									// big table and form.
									$("[name=" + $.wizgrid.wizID(i) + "]", formid).val(rowdata[i]);
								}
							}
						}
					}
				});
			}
			, FormToGrid: function(rowid, formid, mode, position) {
				return this.each(function() {
					var $t = this;
					if (!$t.grid) {
						return;
					}
					if (!mode) {
						mode = 'set';
					}
					if (!position) {
						position = 'first';
					}
					var fields = $(formid).serializeArray();
					var griddata = {};
					$.each(fields, function(i, field) {
						griddata[field.name] = field.value;
					});
					if (mode === 'add') {
						$($t).wizGrid("addRowData", rowid, griddata, position);
					}
					else if (mode === 'set') {
						$($t).wizGrid("setRowData", rowid, griddata);
					}
				});
			}
		});
		// module begin
		$.wizgrid.extend({
			groupingSetup: function() {
				return this.each(function() {
					var $t = this
						, i, j, cml, cm = $t.p.Columns
						, grp = $t.p.groupingView;
					if (grp !== null && ((typeof grp === 'object') || $.isFunction(grp))) {
						if (!grp.groupField.length) {
							$t.p.grouping = false;
						}
						else {
							if (grp.visibiltyOnNextGrouping === undefined) {
								grp.visibiltyOnNextGrouping = [];
							}
							grp.lastvalues = [];
							if (!grp._locgr) {
								grp.groups = [];
							}
							grp.counters = [];
							for (i = 0; i < grp.groupField.length; i++) {
								if (!grp.groupOrder[i]) {
									grp.groupOrder[i] = 'asc';
								}
								if (!grp.groupText[i]) {
									grp.groupText[i] = '{0}';
								}
								if (typeof grp.groupColumnShow[i] !== 'boolean') {
									grp.groupColumnShow[i] = true;
								}
								if (typeof grp.groupSummary[i] !== 'boolean') {
									grp.groupSummary[i] = false;
								}
								if (!grp.groupSummaryPos[i]) {
									grp.groupSummaryPos[i] = 'footer';
								}
								if (grp.groupColumnShow[i] === true) {
									grp.visibiltyOnNextGrouping[i] = true;
									$($t).wizGrid('showCol', grp.groupField[i]);
								}
								else {
									grp.visibiltyOnNextGrouping[i] = $("#" + $.wizgrid.wizID($t.p.id + "_" + grp.groupField[i])).is(
										":visible");
									$($t).wizGrid('hideCol', grp.groupField[i]);
								}
							}
							grp.summary = [];
							if (grp.hideFirstGroupCol) {
								grp.formatDisplayField[0] = function(v) {
									return v;
								};
							}
							for (j = 0, cml = cm.length; j < cml; j++) {
								if (grp.hideFirstGroupCol) {
									if (!cm[j].hidden && grp.groupField[0] === cm[j].name) {
										cm[j].formatter = function() {
											return '';
										};
									}
								}
								if (cm[j].summaryType) {
									if (cm[j].summaryDivider) {
										grp.summary.push({
											nm: cm[j].name
											, st: cm[j].summaryType
											, v: ''
											, sd: cm[j].summaryDivider
											, vd: ''
											, sr: cm[j].summaryRound
											, srt: cm[j].summaryRoundType || 'round'
										});
									}
									else {
										grp.summary.push({
											nm: cm[j].name
											, st: cm[j].summaryType
											, v: ''
											, sr: cm[j].summaryRound
											, srt: cm[j].summaryRoundType || 'round'
										});
									}
								}
							}
						}
					}
					else {
						$t.p.grouping = false;
					}
				});
			}
			, groupingPrepare: function(record, irow) {
				this.each(function() {
					var grp = this.p.groupingView
						, $t = this
						, i, sumGroups = function() {
							if ($.isFunction(this.st)) {
								this.v = this.st.call($t, this.v, this.nm, record);
							}
							else {
								this.v = $($t).wizGrid('groupingCalculations.handler', this.st, this.v, this.nm, this.sr, this.srt
									, record);
								if (this.st.toLowerCase() === 'avg' && this.sd) {
									this.vd = $($t).wizGrid('groupingCalculations.handler', this.st, this.vd, this.sd, this.sr
										, this.srt, record);
								}
							}
						}
						, grlen = grp.groupField.length
						, fieldName, v, displayName, displayValue, changed = 0;
					for (i = 0; i < grlen; i++) {
						fieldName = grp.groupField[i];
						displayName = grp.displayField[i];
						v = record[fieldName];
						displayValue = displayName == null ? null : record[displayName];
						if (displayValue == null) {
							displayValue = v;
						}
						if (v !== undefined) {
							if (irow === 0) {
								// First record always
								// starts a new group
								grp.groups.push({
									idx: i
									, dataIndex: fieldName
									, value: v
									, displayValue: displayValue
									, startRow: irow
									, cnt: 1
									, summary: []
								});
								grp.lastvalues[i] = v;
								grp.counters[i] = {
									cnt: 1
									, pos: grp.groups.length - 1
									, summary: $.extend(true, [], grp.summary)
								};
								$.each(grp.counters[i].summary, sumGroups);
								grp.groups[grp.counters[i].pos].summary = grp.counters[i].summary;
							}
							else {
								if (typeof v !== "object" && ($.isArray(grp.isInTheSameGroup) && $.isFunction(grp.isInTheSameGroup[
											i]) ? !grp.isInTheSameGroup[i].call($t, grp.lastvalues[i], v, i, grp) : grp.lastvalues[i] !==
										v)) {
									// This record is not in
									// same group as
									// previous one
									grp.groups.push({
										idx: i
										, dataIndex: fieldName
										, value: v
										, displayValue: displayValue
										, startRow: irow
										, cnt: 1
										, summary: []
									});
									grp.lastvalues[i] = v;
									changed = 1;
									grp.counters[i] = {
										cnt: 1
										, pos: grp.groups.length - 1
										, summary: $.extend(true, [], grp.summary)
									};
									$.each(grp.counters[i].summary, sumGroups);
									grp.groups[grp.counters[i].pos].summary = grp.counters[i].summary;
								}
								else {
									if (changed === 1) {
										// This group has
										// changed because
										// an earlier group
										// changed.
										grp.groups.push({
											idx: i
											, dataIndex: fieldName
											, value: v
											, displayValue: displayValue
											, startRow: irow
											, cnt: 1
											, summary: []
										});
										grp.lastvalues[i] = v;
										grp.counters[i] = {
											cnt: 1
											, pos: grp.groups.length - 1
											, summary: $.extend(true, [], grp.summary)
										};
										$.each(grp.counters[i].summary, sumGroups);
										grp.groups[grp.counters[i].pos].summary = grp.counters[i].summary;
									}
									else {
										grp.counters[i].cnt += 1;
										grp.groups[grp.counters[i].pos].cnt = grp.counters[i].cnt;
										$.each(grp.counters[i].summary, sumGroups);
										grp.groups[grp.counters[i].pos].summary = grp.counters[i].summary;
									}
								}
							}
						}
					}
					// gdata.push( rData );
				});
				return this;
			}
			, groupingToggle: function(hid) {
				this.each(function() {
					var $t = this
						, grp = $t.p.groupingView
						, strpos = hid.split('_')
						, num = parseInt(strpos[strpos.length - 2], 10);
					strpos.splice(strpos.length - 2, 2);
					var uid = strpos.join("_")
						, minus = grp.minusicon
						, plus = grp.plusicon
						, tar = $("#" + $.wizgrid.wizID(hid))
						, r = tar.length ? tar[0].nextSibling : null
						, tarspan = $("#" + $.wizgrid.wizID(hid) + " span." + "tree-wrap-" + $t.p.direction)
						, getGroupingLevelFromClass = function(className) {
							var nums = $.map(className.split(" "), function(item) {
								if (item.substring(0, uid.length + 1) === uid + "_") {
									return parseInt(item.substring(uid.length + 1), 10);
								}
							});
							return nums.length > 0 ? nums[0] : undefined;
						}
						, itemGroupingLevel, showData, collapsed = false
						, skip = false
						, frz = $t.p.frozenColumns ? $t.p.id + "_frozen" : false
						, tar2 = frz ? $("#" + $.wizgrid.wizID(hid), "#" + $.wizgrid.wizID(frz)) : false
						, r2 = (tar2 && tar2.length) ? tar2[0].nextSibling : null;
					if (tarspan.hasClass(minus)) {
						if (grp.showSummaryOnHide) {
							if (r) {
								while (r) {
									itemGroupingLevel = getGroupingLevelFromClass(r.className);
									if (itemGroupingLevel !== undefined && itemGroupingLevel <= num) {
										break;
									}
									$(r).hide();
									r = r.nextSibling;
									if (frz) {
										$(r2).hide();
										r2 = r2.nextSibling;
									}
								}
							}
						}
						else {
							if (r) {
								while (r) {
									itemGroupingLevel = getGroupingLevelFromClass(r.className);
									if (itemGroupingLevel !== undefined && itemGroupingLevel <= num) {
										break;
									}
									$(r).hide();
									r = r.nextSibling;
									if (frz) {
										$(r2).hide();
										r2 = r2.nextSibling;
									}
								}
							}
						}
						tarspan.removeClass(minus).addClass(plus);
						collapsed = true;
					}
					else {
						if (r) {
							showData = undefined;
							while (r) {
								itemGroupingLevel = getGroupingLevelFromClass(r.className);
								if (showData === undefined) {
									showData = itemGroupingLevel === undefined; // if
									// the
									// first
									// row
									// after
									// the
									// opening
									// group
									// is
									// data
									// row
									// then
									// show
									// the
									// data
									// rows
								}
								skip = $(r).hasClass("ui-subgrid") && $(r).hasClass("ui-sg-collapsed");
								if (itemGroupingLevel !== undefined) {
									if (itemGroupingLevel <= num) {
										break; // next item
										// of the
										// same
										// lever are
										// found
									}
									if (itemGroupingLevel === num + 1) {
										if (!skip) {
											$(r).show().find(">td>span." + "tree-wrap-" + $t.p.direction).removeClass(minus).addClass(
												plus);
											if (frz) {
												$(r2).show().find(">td>span." + "tree-wrap-" + $t.p.direction).removeClass(minus).addClass(
													plus);
											}
										}
									}
								}
								else if (showData) {
									if (!skip) {
										$(r).show();
										if (frz) {
											$(r2).show();
										}
									}
								}
								r = r.nextSibling;
								if (frz) {
									r2 = r2.nextSibling;
								}
							}
						}
						tarspan.removeClass(plus).addClass(minus);
					}
					$($t).triggerHandler("wizGridGroupingClickGroup", [hid, collapsed]);
					if ($.isFunction($t.p.onClickGroup)) {
						$t.p.onClickGroup.call($t, hid, collapsed);
					}
				});
				return false;
			}
			, groupingRender: function(grdata, colspans, page, rn) {
				return this.each(function() {
					var $t = this
						, grp = $t.p.groupingView
						, str = ""
						, icon = ""
						, hid, clid, pmrtl = grp.groupCollapse ? grp.plusicon : grp.minusicon
						, gv, cp = []
						, len = grp.groupField.length;
					pmrtl += " tree-wrap-" + $t.p.direction;
					$.each($t.p.Columns, function(i, n) {
						var ii;
						for (ii = 0; ii < len; ii++) {
							if (grp.groupField[ii] === n.name) {
								cp[ii] = i;
								break;
							}
						}
					});
					var toEnd = 0;

					function findGroupIdx(ind, offset, grp) {
						var ret = false
							, i;
						if (offset === 0) {
							ret = grp[ind];
						}
						else {
							var id = grp[ind].idx;
							if (id === 0) {
								ret = grp[ind];
							}
							else {
								for (i = ind; i >= 0; i--) {
									if (grp[i].idx === id - offset) {
										ret = grp[i];
										break;
									}
								}
							}
						}
						return ret;
					}

					function buildSummaryTd(i, ik, grp, foffset) {
						var fdata = findGroupIdx(i, ik, grp)
							, cm = $t.p.Columns
							, vv, grlen = fdata.cnt
							, str = ""
							, k;
						for (k = foffset; k < colspans; k++) {
							var tmpdata = "<td " + $t.formatCol(k, 1, '') + ">&#160;</td>"
								, tplfld = "{0}";
							$.each(fdata.summary, function() {
								if (this.nm === cm[k].name) {
									if (cm[k].summaryTpl) {
										tplfld = cm[k].summaryTpl;
									}
									if (typeof this.st === 'string' && this.st.toLowerCase() === 'avg') {
										if (this.sd && this.vd) {
											this.v = (this.v / this.vd);
										}
										else if (this.v && grlen > 0) {
											this.v = (this.v / grlen);
										}
									}
									try {
										this.groupCount = fdata.cnt;
										this.groupIndex = fdata.dataIndex;
										this.groupValue = fdata.value;
										vv = $t.formatter('', this.v, k, this);
									}
									catch (ef) {
										vv = this.v;
									}
									tmpdata = "<td " + $t.formatCol(k, 1, '') + ">" + $.wizgrid.template(tplfld, vv) + "</td>";
									return false;
								}
							});
							str += tmpdata;
						}
						return str;
					}
					var sumreverse = $.makeArray(grp.groupSummary)
						, mul;
					sumreverse.reverse();
					mul = $t.p.multiselect ? " colspan=\"2\"" : "";
					$.each(grp.groups, function(i, n) {
						if (grp._locgr) {
							if (!(n.startRow + n.cnt > (page - 1) * rn && n.startRow < page * rn)) {
								return true;
							}
						}
						toEnd++;
						clid = $t.p.id + "ghead_" + n.idx;
						hid = clid + "_" + i;
						icon = "<span style='cursor:pointer;' class='ui-icon " + pmrtl + "' onclick=\"jQuery('#" + $.wizgrid
							.wizID($t.p.id) + "').wizGrid('groupingToggle','" + hid + "');return false;\"></span>";
						try {
							if ($.isArray(grp.formatDisplayField) && $.isFunction(grp.formatDisplayField[n.idx])) {
								n.displayValue = grp.formatDisplayField[n.idx].call($t, n.displayValue, n.value, $t.p.Columns[
									cp[n.idx]], n.idx, grp);
								gv = n.displayValue;
							}
							else {
								gv = $t.formatter(hid, n.displayValue, cp[n.idx], n.value);
							}
						}
						catch (egv) {
							gv = n.displayValue;
						}
						var grpTextStr = '';
						if ($.isFunction(grp.groupText[n.idx])) {
							grpTextStr = grp.groupText[n.idx].call($t, gv, n.cnt, n.summary);
						}
						else {
							grpTextStr = $.wizgrid.template(grp.groupText[n.idx], gv, n.cnt, n.summary);
						}
						if (!(typeof grpTextStr === 'string' || typeof grpTextStr === 'number')) {
							grpTextStr = gv;
						}
						if (grp.groupSummaryPos[n.idx] === 'header') {
							str += "<tr id=\"" + hid + "\"" + (grp.groupCollapse && n.idx > 0 ?
									" style=\"display:none;\" " : " ") +
								"role=\"row\" class= \"ui-widget-content-grid wizgroup ui-row-" + $t.p.direction + " " + clid +
								"\"><td style=\"padding-left:" + (n.idx * 12) + "px;" + "\"" + mul + ">" + icon + grpTextStr +
								"</td>";
							str += buildSummaryTd(i, 0, grp.groups, grp.groupColumnShow[n.idx] === false ? (mul === "" ? 2 :
								3) : ((mul === "") ? 1 : 2));
							str += "</tr>";
						}
						else {
							str += "<tr id=\"" + hid + "\"" + (grp.groupCollapse && n.idx > 0 ?
									" style=\"display:none;\" " : " ") +
								"role=\"row\" class= \"ui-widget-content-grid wizgroup ui-row-" + $t.p.direction + " " + clid +
								"\"><td style=\"padding-left:" + (n.idx * 12) + "px;" + "\" colspan=\"" + (grp.groupColumnShow[
									n.idx] === false ? colspans - 1 : colspans) + "\">" + icon + grpTextStr + "</td></tr>";
						}
						var leaf = len - 1 === n.idx;
						if (leaf) {
							var gg = grp.groups[i + 1]
								, kk, ik, offset = 0
								, sgr = n.startRow
								, end = gg !== undefined ? gg.startRow : grp.groups[i].startRow + grp.groups[i].cnt;
							if (grp._locgr) {
								offset = (page - 1) * rn;
								if (offset > n.startRow) {
									sgr = offset;
								}
							}
							for (kk = sgr; kk < end; kk++) {
								if (!grdata[kk - offset]) {
									break;
								}
								str += grdata[kk - offset].join('');
							}
							if (grp.groupSummaryPos[n.idx] !== 'header') {
								var jj;
								if (gg !== undefined) {
									for (jj = 0; jj < grp.groupField.length; jj++) {
										if (gg.dataIndex === grp.groupField[jj]) {
											break;
										}
									}
									toEnd = grp.groupField.length - jj;
								}
								for (ik = 0; ik < toEnd; ik++) {
									if (!sumreverse[ik]) {
										continue;
									}
									var hhdr = "";
									if (grp.groupCollapse && !grp.showSummaryOnHide) {
										hhdr = " style=\"display:none;\"";
									}
									str += "<tr" + hhdr + " wizfootlevel=\"" + (n.idx - ik) +
										"\" role=\"row\" class=\"ui-widget-content-grid wizfoot ui-row-" + $t.p.direction + "\">";
									str += buildSummaryTd(i, ik, grp.groups, 0);
									str += "</tr>";
								}
								toEnd = jj;
							}
						}
					});
					$("#" + $.wizgrid.wizID($t.p.id) + " tbody:first").append(str);
					// free up memory
					str = null;
				});
			}
			, groupingGroupBy: function(name, options) {
				return this.each(function() {
					var $t = this;
					if (typeof name === "string") {
						name = [name];
					}
					var grp = $t.p.groupingView;
					$t.p.grouping = true;
					grp._locgr = false;
					// Set default, in case visibilityOnNextGrouping
					// is undefined
					if (grp.visibiltyOnNextGrouping === undefined) {
						grp.visibiltyOnNextGrouping = [];
					}
					var i;
					// show previous hidden groups if they are
					// hidden and weren't removed yet
					for (i = 0; i < grp.groupField.length; i++) {
						if (!grp.groupColumnShow[i] && grp.visibiltyOnNextGrouping[i]) {
							$($t).wizGrid('showCol', grp.groupField[i]);
						}
					}
					// set visibility status of current group
					// columns on next grouping
					for (i = 0; i < name.length; i++) {
						grp.visibiltyOnNextGrouping[i] = $("#" + $.wizgrid.wizID($t.p.id) + "_" + $.wizgrid.wizID(name[i]))
							.is(":visible");
					}
					$t.p.groupingView = $.extend($t.p.groupingView, options || {});
					grp.groupField = name;
					$($t).trigger("reloadGrid");
				});
			}
			, groupingRemove: function(current) {
				return this.each(function() {
					var $t = this;
					if (current === undefined) {
						current = true;
					}
					$t.p.grouping = false;
					if (current === true) {
						var grp = $t.p.groupingView
							, i;
						// show previous hidden groups if
						// they are hidden and weren't
						// removed yet
						for (i = 0; i < grp.groupField.length; i++) {
							if (!grp.groupColumnShow[i] && grp.visibiltyOnNextGrouping[i]) {
								$($t).wizGrid('showCol', grp.groupField);
							}
						}
						$("tr.wizgroup, tr.wizfoot", "#" + $.wizgrid.wizID($t.p.id) + " tbody:first").remove();
						$("tr.wizgrow:hidden", "#" + $.wizgrid.wizID($t.p.id) + " tbody:first").show();
					}
					else {
						$($t).trigger("reloadGrid");
					}
				});
			}
			, groupingCalculations: {
				handler: function(fn, v, field, round, roundType, rc) {
					var funcs = {
						sum: function() {
							return parseFloat(v || 0) + parseFloat((rc[field] || 0));
						}
						, min: function() {
							if (v === "") {
								return parseFloat(rc[field] || 0);
							}
							return Math.min(parseFloat(v), parseFloat(rc[field] || 0));
						}
						, max: function() {
							if (v === "") {
								return parseFloat(rc[field] || 0);
							}
							return Math.max(parseFloat(v), parseFloat(rc[field] || 0));
						}
						, count: function() {
							if (v === "") {
								v = 0;
							}
							if (rc.hasOwnProperty(field)) {
								return v + 1;
							}
							return 0;
						}
						, avg: function() {
							// the same as sum, but at end we divide
							// it
							// so use sum instead of duplicating the
							// code (?)
							return funcs.sum();
						}
					};
					if (!funcs[fn]) {
						throw ("wizGrid Grouping No such method: " + fn);
					}
					var res = funcs[fn]();
					if (round != null) {
						if (roundType === 'fixed') {
							res = res.toFixed(round);
						}
						else {
							var mul = Math.pow(10, round);
							res = Math.round(res * mul) / mul;
						}
					}
					return res;
				}
			}
			, HeadersMerge: function(o) {
				o = $.extend({
					useColSpanStyle: false
					, groupHeaders: []
				}, o || {});
				return this.each(function() {
					this.p.groupHeader = o;
					var ts = this
						, i, cmi, skip = 0
						, $tr, $colHeader, th, $th, thStyle, iCol, cghi
						, // startColumnName,
						numberOfColumns, titleText, cVisibleColumns, Columns = ts.p.Columns
						, cml = Columns.length
						, ths = ts.grid.headers
						, $htable = $("table.ui-wizgrid-htable", ts.grid.hDiv)
						, $trLabels = $htable.children("thead").children("tr.ui-wizgrid-labels:last").addClass(
							"wizg-second-row-header")
						, $thead = $htable.children("thead")
						, $theadInTable, $firstHeaderRow = $htable.find(".wizg-first-row-header");
					if ($firstHeaderRow[0] === undefined) {
						$firstHeaderRow = $('<tr>', {
							role: "row"
							, "aria-hidden": "true"
						}).addClass("wizg-first-row-header").css("height", "auto");
					}
					else {
						$firstHeaderRow.empty();
					}
					var $firstRow, inColumnHeader = function(text, columnHeaders) {
						var length = columnHeaders.length
							, i;
						for (i = 0; i < length; i++) {
							if (columnHeaders[i].startColumnName === text) {
								return i;
							}
						}
						return -1;
					};
					$(ts).prepend($thead);
					$tr = $('<tr>', {
						role: "row"
					}).addClass("ui-wizgrid-labels wizg-third-row-header");
					for (i = 0; i < cml; i++) {
						th = ths[i].el;
						$th = $(th);
						cmi = Columns[i];
						// build the next cell for the first
						// header row
						thStyle = {
							height: '0px'
							, width: ths[i].width + 'px'
							, display: (cmi.hidden ? 'none' : '')
						};
						$("<th>", {
							role: 'gridcell'
						}).css(thStyle).addClass("ui-first-th-" + ts.p.direction).appendTo($firstHeaderRow);
						th.style.width = ""; // remove
						// unneeded
						// style
						iCol = inColumnHeader(cmi.name, o.groupHeaders);
						if (iCol >= 0) {
							cghi = o.groupHeaders[iCol];
							numberOfColumns = cghi.numberOfColumns;
							titleText = cghi.titleText;
							// caclulate the number of
							// visible columns from the next
							// numberOfColumns columns
							for (cVisibleColumns = 0, iCol = 0; iCol < numberOfColumns && (i + iCol < cml); iCol++) {
								if (!Columns[i + iCol].hidden) {
									cVisibleColumns++;
								}
							}
							// The next numberOfColumns
							// headers will be moved in the
							// next row
							// in the current row will be
							// placed the new column header
							// with the titleText.
							// The text will be over the
							// cVisibleColumns columns
							$colHeader = $('<th>').attr({
									role: "columnheader"
								}).addClass("ui-state-default-grid ui-th-column-header ui-th-" + ts.p.direction)
								// .css({'height':'22px',
								// 'border-top': '0
								// none'})
								.html(titleText);
							$colHeader.attr("style"," ");
							if (cVisibleColumns > 0) {
								$colHeader.attr("colspan", String(cVisibleColumns));
							}
							if (ts.p.headertitles) {
								$colHeader.attr("title", $colHeader.text());
							}
							// hide if not a visible cols
							if (cVisibleColumns === 0) {
								$colHeader.hide();
							}
							$th.before($colHeader); // insert
							// new
							// column
							// header
							// before
							// the
							// current
							$tr.append(th); // move the
							// current
							// header in the
							// next row
							// set the coumter of headers
							// which will be moved in the
							// next row
							skip = numberOfColumns - 1;
						}
						else {
							if (skip === 0) {
								if (o.useColSpanStyle) {
									// expand the header
									// height to two rows
									$th.attr("rowspan", "2");
								}
								else {
									$('<th>', {
										role: "columnheader"
									}).addClass("ui-state-default-grid ui-th-column-header ui-th-" + ts.p.direction).css({
										"display": cmi.hidden ? 'none' : ''
									}).insertBefore($th);
									$tr.append(th);
								}
							}
							else {
								// move the header to the
								// next row
								// $th.css({"padding-top":
								// "2px", height: "19px"});
								$tr.append(th);
								skip--;
							}
						}
					}
					$theadInTable = $(ts).children("thead");
					$theadInTable.prepend($firstHeaderRow);
					$tr.insertAfter($trLabels);
					$htable.append($theadInTable);
					if (o.useColSpanStyle) {
						// Increase the height of resizing
						// span of visible headers
						$htable.find("span.ui-wizgrid-resize").each(function() {
							var $parent = $(this).parent();
							if ($parent.is(":visible")) {
								this.style.cssText = 'height: ' + $parent.height() + 'px !important; cursor: col-resize;';
							}
						});
						// Set position of the sortable div
						// (the main lable)
						// with the column header text to
						// the middle of the cell.
						// One should not do this for hidden
						// headers.
						$htable.find("div.ui-wizgrid-sortable").each(function() {
							var $ts = $(this)
								, $parent = $ts.parent();
							if ($parent.is(":visible") && $parent.is(":has(span.ui-wizgrid-resize)")) {
								// minus 4px
								// from the
								// margins
								// of the
								// resize
								// markers
								$ts.css('top', ($parent.height() - $ts.outerHeight()) / 2 - 4 + 'px');
							}
						});
					}
					$firstRow = $theadInTable.find("tr.wizg-first-row-header");
					$(ts).bind('wizGridResizeStop.HeadersMerge', function(e, nw, idx) {
						$firstRow.find('th').eq(idx).width(nw);
					});
				});
			}
			, destroyGroupHeader: function(nullHeader) {
				if (nullHeader === undefined) {
					nullHeader = true;
				}
				return this.each(function() {
					var $t = this
						, $tr, i, l, headers, $th, $resizing, grid = $t.grid
						, thead = $("table.ui-wizgrid-htable thead", grid.hDiv)
						, cm = $t.p.Columns
						, hc;
					if (!grid) {
						return;
					}
					$(this).unbind('.HeadersMerge');
					$tr = $("<tr>", {
						role: "row"
					}).addClass("ui-wizgrid-labels");
					headers = grid.headers;
					for (i = 0, l = headers.length; i < l; i++) {
						hc = cm[i].hidden ? "none" : "";
						$th = $(headers[i].el).width(headers[i].width).css('display', hc);
						try {
							$th.removeAttr("rowSpan");
						}
						catch (rs) {
							// IE 6/7
							$th.attr("rowSpan", 1);
						}
						$tr.append($th);
						$resizing = $th.children("span.ui-wizgrid-resize");
						if ($resizing.length > 0) { // resizable
							// column
							$resizing[0].style.height = "";
						}
						$th.children("div")[0].style.top = "";
					}
					$(thead).children('tr.ui-wizgrid-labels').remove();
					$(thead).prepend($tr);
					if (nullHeader === true) {
						$($t).wizGrid('setGridParam', {
							'groupHeader': null
						});
					}
				});
			}
		});
		// module begin
		$.wizgrid = $.wizgrid || {};
		$.extend($.wizgrid, {
			saveState: function(wizGridId, o) {
				o = $.extend({
					useStorage: true
					, storageType: "localStorage", // localStorage
					// or
					// sessionStorage
					beforeSetItem: null
					, compression: false
					, compressionModule: 'LZString', // object
					// by
					// example
					// gzip,
					// LZString
					compressionMethod: 'compressToUTF16' // string
						// by
						// example
						// zip,
						// compressToUTF16
				}, o || {});
				if (!wizGridId) {
					return;
				}
				var gridstate = ""
					, data = ""
					, ret, $t = $("#" + wizGridId)[0]
					, tmp;
				// to use navigator set storeNavOptions to
				// true in grid options
				if (!$t.grid) {
					return;
				}
				tmp = $($t).data('inlineNav');
				if (tmp && $t.p.inlineNav) {
					$($t).wizGrid('setGridParam', {
						_iN: tmp
					});
				}
				tmp = $($t).data('filterToolbar');
				if (tmp && $t.p.filterToolbar) {
					$($t).wizGrid('setGridParam', {
						_fT: tmp
					});
				}
				gridstate = $($t).wizGrid('wizGridExport', {
					exptype: "jsonstring"
					, ident: ""
					, root: ""
				});
				$($t.grid.bDiv).find(".ui-wizgrid-btable tr:gt(0)").each(function(i, d) {
					data += d.outerHTML;
				});
				if ($.isFunction(o.beforeSetItem)) {
					ret = o.beforeSetItem.call($t, gridstate);
					if (ret != null) {
						gridstate = ret;
					}
				}
				if (o.compression) {
					if (o.compressionModule) {
						try {
							ret = window[o.compressionModule][o.compressionMethod]
								(gridstate);
							if (ret != null) {
								gridstate = ret;
								data = window[o.compressionModule][o.compressionMethod]
									(data);
							}
						}
						catch (e) {
							// can not execute a
							// compression.
						}
					}
				}
				if (o.useStorage && $.wizgrid.isLocalStorage()) {
					try {
						window[o.storageType].setItem("wizGrid" + $t.p.id, gridstate);
						window[o.storageType].setItem("wizGrid" + $t.p.id + "_data", data);
					}
					catch (e) {
						if (e.code === 22) { // chrome is
							// 21
							// just for now. we should make
							// some additionla changes and
							// eventually clear some local
							// items
							alert("Local storage limit is over!");
						}
					}
				}
				return gridstate;
			}
			, loadState: function(wizGridId, gridstring, o) {
				o = $.extend({
					useStorage: true
					, storageType: "localStorage"
					, clearAfterLoad: false, // clears
					// the
					// wizGrid
					// localStorage
					// items
					// aftre
					// load
					beforeSetGrid: null
					, decompression: false
					, decompressionModule: 'LZString', // object
					// by
					// example
					// gzip,
					// LZString
					decompressionMethod: 'decompressFromUTF16' // string
						// by
						// example
						// unzip,
						// decompressFromUTF16
				}, o || {});
				if (!wizGridId) {
					return;
				}
				var ret, tmp, $t = $("#" + wizGridId)[0]
					, data, iN, fT;
				if ($t.grid) {
					$.wizgrid.gridUnload(wizGridId);
				}
				if (o.useStorage) {
					try {
						gridstring = window[o.storageType].getItem("wizGrid" + $t.id);
						data = window[o.storageType].getItem("wizGrid" + $t.id + "_data");
					}
					catch (e) {
						// can not get data
					}
				}
				if (!gridstring) {
					return;
				}
				if (o.decompression) {
					if (o.decompressionModule) {
						try {
							ret = window[o.decompressionModule][o.decompressionMethod]
								(gridstring);
							if (ret != null) {
								gridstring = ret;
								data = window[o.decompressionModule][o.decompressionMethod]
									(data);
							}
						}
						catch (e) {
							// decompression can not be done
						}
					}
				}
				ret = wizGridUtils.parse(gridstring);
				if (ret && $.type(ret) === 'object') {
					if ($.isFunction(o.beforeSetGrid)) {
						tmp = o.beforeSetGrid(ret);
						if (tmp && $.type(tmp) === 'object') {
							ret = tmp;
						}
					}
					// some preparings
					var retfunc = function(param) {
							var p;
							p = param;
							return p;
						}
						, prm = {
							"reccount": ret.reccount
							, "records": ret.records
							, "lastpage": ret.lastpage
							, "shrinkToFit": retfunc(ret.shrinkToFit)
							, "data": retfunc(ret.data)
							, "datatype": retfunc(ret.datatype)
							, "grouping": retfunc(ret.grouping)
						};
					ret.shrinkToFit = false;
					ret.data = [];
					ret.datatype = 'local';
					ret.grouping = false;
					ret.navGrid = false;
					if (ret.inlineNav) {
						iN = retfunc(ret._iN);
						ret._iN = null;
						delete ret._iN;
					}
					if (ret.filterToolbar) {
						fT = retfunc(ret._fT);
						ret._fT = null;
						delete ret._fT;
					}
					var grid = $("#" + wizGridId).wizGrid(ret);
					grid.append(data);
					grid.wizGrid('setGridParam', prm);
					if (ret.storeNavOptions) {
						grid.wizGrid('navGrid', ret.pager, ret.navOptions, ret.editOptions, ret.addOptions, ret.delOptions
							, ret.searchOptions, ret.viewOptions);
					}
					if (ret.inlineNav && iN) {
						grid.wizGrid('setGridParam', {
							inlineNav: false
						});
						grid.wizGrid('inlineNav', ret.pager, iN);
					}
					if (ret.filterToolbar && fT) {
						grid.wizGrid('setGridParam', {
							filterToolbar: false
						});
						grid.wizGrid('filterToolbar', fT);
					}
					grid[0].updatepager(true, true);
					if (o.clearAfterLoad) {
						window[o.storageType].removeItem("wizGrid" + $t.id);
						window[o.storageType].removeItem("wizGrid" + $t.id + "_data");
					}
				}
				else {
					alert("can not convert to object");
				}
			}
			, setRegional: function(wizGridId, options) {
				$.wizgrid.saveState(wizGridId, {
					storageType: "sessionStorage"
				});
				$.wizgrid.loadState(wizGridId, null, {
					storageType: "sessionStorage"
					, beforeSetGrid: function(params) {
						params.regional = options.regional;
						params.force_regional = true;
						return params;
					}
				});
				// check for formatter actions
				var grid = $("#" + wizGridId)[0]
					, model = $(grid).wizGrid('getGridParam', 'Columns')
					, i = -1
					, nav = $.wizgrid.getRegional(grid, 'nav');
				$.each(model, function(k) {
					if (this.formatter && this.formatter === 'actions') {
						i = k;
						return false;
					}
				});
				if (i !== -1 && nav) {
					$("#" + wizGridId + " tbody tr").each(function() {
						var td = this.cells[i];
						$(td).find(".ui-inline-edit").attr("title", nav.edittitle);
						$(td).find(".ui-inline-del").attr("title", nav.deltitle);
						$(td).find(".ui-inline-save").attr("title", nav.savetitle);
						$(td).find(".ui-inline-cancel").attr("title", nav.canceltitle);
					});
				}
				try {
					window['sessionStorage'].removeItem("wizGrid" + grid.id);
					window['sessionStorage'].removeItem("wizGrid" + grid.id + "_data");
				}
				catch (e) {}
			}
			, wizGridImport: function(wizGridId, o) {
				o = $.extend({
					imptype: "xml", // xml, json,
					// xmlstring,
					// jsonstring
					impstring: ""
					, impurl: ""
					, mtype: "GET"
					, impData: {}
					, xmlGrid: {
						config: "root>grid"
						, data: "root>rows"
					}
					, jsonGrid: {
						config: "grid"
						, data: "data"
					}
					, ajaxOptions: {}
				}, o || {});
				var $t = (wizGridId.indexOf("#") === 0 ? "" : "#") + $.wizgrid.wizID(wizGridId);
				var xmlConvert = function(xml, o) {
					var cnfg = $(o.xmlGrid.config, xml)[0];
					var xmldata = $(o.xmlGrid.data, xml)[0]
						, jstr, jstr1, key;
					if (wizGridUtils.xmlToJSON) {
						jstr = wizGridUtils.xmlToJSON(cnfg);
						// jstr = $.wizgrid.parse(jstr);
						for (key in jstr) {
							if (jstr.hasOwnProperty(key)) {
								jstr1 = jstr[key];
							}
						}
						if (xmldata) {
							// save the datatype
							var svdatatype = jstr.grid.datatype;
							jstr.grid.datatype = 'xmlstring';
							jstr.grid.datastr = xml;
							$($t).wizGrid(jstr1).wizGrid("setGridParam", {
								datatype: svdatatype
							});
						}
						else {
							setTimeout(function() {
								$($t).wizGrid(jstr1);
							}, 0);
						}
					}
					else {
						alert("xml2json or parse are not present");
					}
				};
				var jsonConvert = function(jsonstr, o) {
					if (jsonstr && typeof jsonstr === 'string') {
						var json = wizGridUtils.parse(jsonstr);
						var gprm = json[o.jsonGrid.config];
						var jdata = json[o.jsonGrid.data];
						if (jdata) {
							var svdatatype = gprm.datatype;
							gprm.datatype = 'jsonstring';
							gprm.datastr = jdata;
							$($t).wizGrid(gprm).wizGrid("setGridParam", {
								datatype: svdatatype
							});
						}
						else {
							$($t).wizGrid(gprm);
						}
					}
				};
				switch (o.imptype) {
					case 'xml':
						$.ajax($.extend({
							url: o.impurl
							, type: o.mtype
							, data: o.impData
							, dataType: "xml"
							, complete: function(xml, stat) {
								if (stat === 'success') {
									xmlConvert(xml.responseXML, o);
									$($t).triggerHandler("wizGridImportComplete", [
										xml
										, o
									]);
									if ($.isFunction(o.importComplete)) {
										o.importComplete(xml);
									}
								}
								xml = null;
							}
						}, o.ajaxOptions));
						break;
					case 'xmlstring':
						// we need to make just the conversion
						// and use the same code as xml
						if (o.impstring && typeof o.impstring === 'string') {
							var xmld = $.parseXML(o.impstring);
							if (xmld) {
								xmlConvert(xmld, o);
								$($t).triggerHandler("wizGridImportComplete", [xmld, o]);
								if ($.isFunction(o.importComplete)) {
									o.importComplete(xmld);
								}
							}
						}
						break;
					case 'json':
						$.ajax($.extend({
							url: o.impurl
							, type: o.mtype
							, data: o.impData
							, dataType: "json"
							, complete: function(json) {
								try {
									jsonConvert(json.responseText, o);
									$($t).triggerHandler("wizGridImportComplete", [
										json
										, o
									]);
									if ($.isFunction(o.importComplete)) {
										o.importComplete(json);
									}
								}
								catch (ee) {}
								json = null;
							}
						}, o.ajaxOptions));
						break;
					case 'jsonstring':
						if (o.impstring && typeof o.impstring === 'string') {
							jsonConvert(o.impstring, o);
							$($t).triggerHandler("wizGridImportComplete", [o.impstring, o]);
							if ($.isFunction(o.importComplete)) {
								o.importComplete(o.impstring);
							}
						}
						break;
				}
			}
		});
		$.wizgrid.extend({
			wizGridExport: function(o) {
				o = $.extend({
					exptype: "xmlstring"
					, root: "grid"
					, ident: "\t"
					, addOptions: {}
				}, o || {});
				var ret = null;
				this.each(function() {
					if (!this.grid) {
						return;
					}
					var key, gprm = $.extend(true, {}, $(this).wizGrid("getGridParam"), o.addOptions);
					// we need to check for:
					// 1.multiselect, 2.subgrid 3. treegrid and remove the
					// unneded columns from Names
					if (gprm.rownumbers) {
						gprm.Names.splice(0, 1);
						gprm.Columns.splice(0, 1);
					}
					if (gprm.multiselect) {
						gprm.Names.splice(0, 1);
						gprm.Columns.splice(0, 1);
					}
					if (gprm.subGrid) {
						gprm.Names.splice(0, 1);
						gprm.Columns.splice(0, 1);
					}
					gprm.knv = null;
					if (gprm.treeGrid) {
						for (key in gprm.treeReader) {
							if (gprm.treeReader.hasOwnProperty(key)) {
								gprm.Names.splice(gprm.Names.length - 1);
								gprm.Columns.splice(gprm.Columns.length - 1);
							}
						}
					}
					switch (o.exptype) {
						case 'xmlstring':
							ret = "<" + o.root + ">" + wizGridUtils.jsonToXML(gprm, {
								xmlDecl: ""
							}) + "</" + o.root + ">";
							break;
						case 'jsonstring':
							ret = wizGridUtils.stringify(gprm);
							if (o.root) {
								ret = "{" + o.root + ":" + ret + "}";
							}
							break;
					}
				});
				return ret;
			}
			, excelExport: function(o) {
				o = $.extend({
					exptype: "remote"
					, url: null
					, oper: "oper"
					, tag: "excel"
					, exportOptions: {}
				}, o || {});
				return this.each(function() {
					if (!this.grid) {
						return;
					}
					var url;
					if (o.exptype === "remote") {
						var pdata = $.extend({}, this.p.postData);
						pdata[o.oper] = o.tag;
						var params = jQuery.param(pdata);
						if (o.url.indexOf("?") !== -1) {
							url = o.url + "&" + params;
						}
						else {
							url = o.url + "?" + params;
						}
						window.location = url;
					}
				});
			}
		});
		// module begin
		$.wizgrid.inlineEdit = $.wizgrid.inlineEdit || {};
		$.wizgrid.extend({
			// Editing
			editRow: function(rowid, keys, oneditfunc, successfunc, url, extraparam, aftersavefunc, errorfunc
				, afterrestorefunc) {
				// Compatible mode old versions
				var o = {}
					, args = $.makeArray(arguments).slice(1);
				if ($.type(args[0]) === "object") {
					o = args[0];
				}
				else {
					if (keys !== undefined) {
						o.keys = keys;
					}
					if ($.isFunction(oneditfunc)) {
						o.oneditfunc = oneditfunc;
					}
					if ($.isFunction(successfunc)) {
						o.successfunc = successfunc;
					}
					if (url !== undefined) {
						o.url = url;
					}
					if (extraparam !== undefined) {
						o.extraparam = extraparam;
					}
					if ($.isFunction(aftersavefunc)) {
						o.aftersavefunc = aftersavefunc;
					}
					if ($.isFunction(errorfunc)) {
						o.errorfunc = errorfunc;
					}
					if ($.isFunction(afterrestorefunc)) {
						o.afterrestorefunc = afterrestorefunc;
					}
					// last two not as param, but as object (sorry)
					// if (restoreAfterError !== undefined) {
					// o.restoreAfterError = restoreAfterError; }
					// if (mtype !== undefined) { o.mtype = mtype ||
					// "POST"; }
				}
				o = $.extend(true, {
					keys: false
					, oneditfunc: null
					, successfunc: null
					, url: null
					, extraparam: {}
					, aftersavefunc: null
					, errorfunc: null
					, afterrestorefunc: null
					, restoreAfterError: true
					, mtype: "POST"
					, focusField: true
				}, $.wizgrid.inlineEdit, o);
				// End compatible
				return this.each(function() {
					var $t = this
						, nm, tmp, editable, cnt = 0
						, focus = null
						, svr = {}
						, ind, cm, bfer;
					if (!$t.grid) {
						return;
					}
					ind = $($t).wizGrid("getInd", rowid, true);
					if (ind === false) {
						return;
					}
					bfer = $.isFunction(o.beforeEditRow) ? o.beforeEditRow.call($t, o, rowid) : undefined;
					if (bfer === undefined) {
						bfer = true;
					}
					if (!bfer) {
						return;
					}
					editable = $(ind).attr("editable") || "0";
					if (editable === "0" && !$(ind).hasClass("not-editable-row")) {
						cm = $t.p.Columns;
						$('td[role="gridcell"]', ind).each(function(i) {
							nm = cm[i].name;
							var treeg = $t.p.treeGrid === true && nm === $t.p.ExpandColumn;
							if (treeg) {
								tmp = $("span:first", this).html();
							}
							else {
								try {
									tmp = $.unformat.call($t, this, {
										rowId: rowid
										, Columns: cm[i]
									}, i);
								}
								catch (_) {
									tmp = (cm[i].edittype && cm[i].edittype === 'textarea') ? $(this).text() : $(this).html();
								}
							}
							if (nm !== 'cb' && nm !== 'subgrid' && nm !== 'rn') {
								if ($t.p.autoencode) {
									tmp = $.wizgrid.htmlDecode(tmp);
								}
								svr[nm] = tmp;
								if (cm[i].editable === true) {
									if (focus === null) {
										focus = i;
									}
									if (treeg) {
										$("span:first", this).html("");
									}
									else {
										$(this).html("");
									}
									var opt = $.extend({}, cm[i].editoptions || {}, {
										id: rowid + "_" + nm
										, name: nm
										, rowId: rowid
										, oper: 'edit'
									});
									if (!cm[i].edittype) {
										cm[i].edittype = "text";
									}
									if (tmp === "&nbsp;" || tmp === "&#160;" || (tmp.length === 1 && tmp.charCodeAt(0) === 160)) {
										tmp = '';
									}
									var elc = $.wizgrid.createEl.call($t, cm[i].edittype, opt, tmp, true, $.extend({}, $.wizgrid
										.ajaxOptions, $t.p.ajaxSelectOptions || {}));
									$(elc).addClass("editable");
									if (treeg) {
										$("span:first", this).append(elc);
									}
									else {
										$(this).append(elc);
									}
									$.wizgrid.bindEv.call($t, elc, opt);
									// Again
									// IE
									if (cm[i].edittype === "select" && cm[i].editoptions !== undefined && cm[i].editoptions.multiple ===
										true && cm[i].editoptions.dataUrl === undefined && $.wizgrid.msie) {
										$(elc).width($(elc).width());
									}
									cnt++;
								}
							}
						});
						if (cnt > 0) {
							svr.id = rowid;
							$t.p.savedRow.push(svr);
							$(ind).attr("editable", "1");
							if (o.focusField) {
								if (typeof o.focusField === 'number' && parseInt(o.focusField, 10) <= cm.length) {
									focus = o.focusField;
								}
								setTimeout(function() {
									var fe = $("td:eq(" + focus + ") :input:visible", ind).not(":disabled");
									if (fe.length > 0) {
										fe.focus();
									}
								}, 0);
							}
							if (o.keys === true) {
								$(ind).bind("keydown", function(e) {
									if (e.keyCode === 27) {
										$($t).wizGrid("restoreRow", rowid, o.afterrestorefunc);
										if ($t.p.inlineNav) {
											try {
												$($t).wizGrid('showAddEditButtons');
											}
											catch (eer1) {}
										}
										return false;
									}
									if (e.keyCode === 13) {
										var ta = e.target;
										if (ta.tagName === 'TEXTAREA') {
											return true;
										}
										if ($($t).wizGrid("saveRow", rowid, o)) {
											if ($t.p.inlineNav) {
												try {
													$($t).wizGrid('showAddEditButtons');
												}
												catch (eer2) {}
											}
										}
										return false;
									}
								});
							}
							$($t).triggerHandler("wizGridInlineEditRow", [rowid, o]);
							if ($.isFunction(o.oneditfunc)) {
								o.oneditfunc.call($t, rowid);
							}
						}
					}
				});
			}
			, saveRow: function(rowid, successfunc, url, extraparam, aftersavefunc, errorfunc, afterrestorefunc) {
				// Compatible mode old versions
				var args = $.makeArray(arguments).slice(1)
					, o = {}
					, $t = this[0];
				if ($.type(args[0]) === "object") {
					o = args[0];
				}
				else {
					if ($.isFunction(successfunc)) {
						o.successfunc = successfunc;
					}
					if (url !== undefined) {
						o.url = url;
					}
					if (extraparam !== undefined) {
						o.extraparam = extraparam;
					}
					if ($.isFunction(aftersavefunc)) {
						o.aftersavefunc = aftersavefunc;
					}
					if ($.isFunction(errorfunc)) {
						o.errorfunc = errorfunc;
					}
					if ($.isFunction(afterrestorefunc)) {
						o.afterrestorefunc = afterrestorefunc;
					}
				}
				o = $.extend(true, {
					successfunc: null
					, url: null
					, extraparam: {}
					, aftersavefunc: null
					, errorfunc: null
					, afterrestorefunc: null
					, restoreAfterError: true
					, mtype: "POST"
					, saveui: "enable"
					, savetext: $.wizgrid.getRegional($t, 'defaults.savetext')
				}, $.wizgrid.inlineEdit, o);
				// End compatible
				var success = false
					, nm, tmp = {}
					, tmp2 = {}
					, tmp3 = {}
					, editable, fr, cv, ind, nullIfEmpty = false;
				if (!$t.grid) {
					return success;
				}
				ind = $($t).wizGrid("getInd", rowid, true);
				if (ind === false) {
					return success;
				}
				var errors = $.wizgrid.getRegional(this, 'errors')
					, edit = $.wizgrid.getRegional(this, 'edit')
					, bfsr = $.isFunction(o.beforeSaveRow) ? o.beforeSaveRow.call($t, o, rowid) : undefined;
				if (bfsr === undefined) {
					bfsr = true;
				}
				if (!bfsr) {
					return;
				}
				editable = $(ind).attr("editable");
				o.url = o.url || $t.p.editurl;
				if (editable === "1") {
					var cm;
					$('td[role="gridcell"]', ind).each(function(i) {
						cm = $t.p.Columns[i];
						nm = cm.name;
						if (nm !== 'cb' && nm !== 'subgrid' && cm.editable === true && nm !== 'rn' && !$(this).hasClass(
								'not-editable-cell')) {
							switch (cm.edittype) {
								case "checkbox":
									var cbv = ["Yes", "No"];
									if (cm.editoptions) {
										cbv = cm.editoptions.value.split(":");
									}
									tmp[nm] = $("input", this).is(":checked") ? cbv[0] : cbv[1];
									break;
								case 'text':
								case 'password':
								case 'textarea':
								case "button":
									tmp[nm] = $("input, textarea", this).val();
									break;
								case 'select':
									if (!cm.editoptions.multiple) {
										tmp[nm] = $("select option:selected", this).val();
										tmp2[nm] = $("select option:selected", this).text();
									}
									else {
										var sel = $("select", this)
											, selectedText = [];
										tmp[nm] = $(sel).val();
										if (tmp[nm]) {
											tmp[nm] = tmp[nm].join(",");
										}
										else {
											tmp[nm] = "";
										}
										$("select option:selected", this).each(function(i, selected) {
											selectedText[i] = $(selected).text();
										});
										tmp2[nm] = selectedText.join(",");
									}
									if (cm.formatter && cm.formatter === 'select') {
										tmp2 = {};
									}
									break;
								case 'custom':
									try {
										if (cm.editoptions && $.isFunction(cm.editoptions.custom_value)) {
											tmp[nm] = cm.editoptions.custom_value.call($t, $(".customelement", this), 'get');
											if (tmp[nm] === undefined) {
												throw "e2";
											}
										}
										else {
											throw "e1";
										}
									}
									catch (e) {
										if (e === "e1") {
											$.wizgrid.info_dialog(errors.errcap, "function 'custom_value' " + edit.msg.nodefined, edit.bClose);
										}
										if (e === "e2") {
											$.wizgrid.info_dialog(errors.errcap, "function 'custom_value' " + edit.msg.novalue, edit.bClose);
										}
										else {
											$.wizgrid.info_dialog(errors.errcap, e.message, edit.bClose);
										}
									}
									break;
							}
							cv = $.wizgrid.checkValues.call($t, tmp[nm], i);
							if (cv[0] === false) {
								return false;
							}
							if ($t.p.autoencode) {
								tmp[nm] = $.wizgrid.htmlEncode(tmp[nm]);
							}
							if (o.url !== 'clientArray' && cm.editoptions && cm.editoptions.NullIfEmpty === true) {
								if (tmp[nm] === "") {
									tmp3[nm] = 'null';
									nullIfEmpty = true;
								}
							}
						}
					});
					if (cv[0] === false) {
						try {
							var tr = $($t).wizGrid('getGridRowById', rowid)
								, positions = $.wizgrid.findPos(tr);
							$.wizgrid.info_dialog(errors.errcap, cv[1], edit.bClose, {
								left: positions[0]
								, top: positions[1] + $(tr).outerHeight()
							});
						}
						catch (e) {
							alert(cv[1]);
						}
						return success;
					}
					var idname, opers = $t.p.prmNames
						, oldRowId = rowid;
					if ($t.p.keyName === false) {
						idname = opers.id;
					}
					else {
						idname = $t.p.keyName;
					}
					if (tmp) {
						tmp[opers.oper] = opers.editoper;
						if (tmp[idname] === undefined || tmp[idname] === "") {
							tmp[idname] = rowid;
						}
						else if (ind.id !== $t.p.idPrefix + tmp[idname]) {
							// rename rowid
							var oldid = $.wizgrid.stripPref($t.p.idPrefix, rowid);
							if ($t.p._index[oldid] !== undefined) {
								$t.p._index[tmp[idname]] = $t.p._index[oldid];
								delete $t.p._index[oldid];
							}
							rowid = $t.p.idPrefix + tmp[idname];
							$(ind).attr("id", rowid);
							if ($t.p.selrow === oldRowId) {
								$t.p.selrow = rowid;
							}
							if ($.isArray($t.p.selarrrow)) {
								var i = $.inArray(oldRowId, $t.p.selarrrow);
								if (i >= 0) {
									$t.p.selarrrow[i] = rowid;
								}
							}
							if ($t.p.multiselect) {
								var newCboxId = "wizg_" + $t.p.id + "_" + rowid;
								$("input.cbox", ind).attr("id", newCboxId).attr("name", newCboxId);
							}
							// TODO: to test the case of frozen
							// columns
						}
						if ($t.p.inlineData === undefined) {
							$t.p.inlineData = {};
						}
						tmp = $.extend({}, tmp, $t.p.inlineData, o.extraparam);
					}
					if (o.url === 'clientArray') {
						tmp = $.extend({}, tmp, tmp2);
						if ($t.p.autoencode) {
							$.each(tmp, function(n, v) {
								tmp[n] = $.wizgrid.htmlDecode(v);
							});
						}
						var k, resp = $($t).wizGrid("setRowData", rowid, tmp);
						$(ind).attr("editable", "0");
						for (k = 0; k < $t.p.savedRow.length; k++) {
							if (String($t.p.savedRow[k].id) === String(oldRowId)) {
								fr = k;
								break;
							}
						}
						if (fr >= 0) {
							$t.p.savedRow.splice(fr, 1);
						}
						$($t).triggerHandler("wizGridInlineAfterSaveRow", [rowid, resp, tmp, o]);
						if ($.isFunction(o.aftersavefunc)) {
							o.aftersavefunc.call($t, rowid, resp, tmp, o);
						}
						success = true;
						$(ind).removeClass("wizgrid-new-row").unbind("keydown");
					}
					else {
						$($t).wizGrid("progressBar", {
							method: "show"
							, loadtype: o.saveui
							, htmlcontent: o.savetext
						});
						tmp3 = $.extend({}, tmp, tmp3);
						tmp3[idname] = $.wizgrid.stripPref($t.p.idPrefix, tmp3[idname]);
						$.ajax($.extend({
							url: o.url
							, data: $.isFunction($t.p.serializeRowData) ? $t.p.serializeRowData.call($t, tmp3) : tmp3
							, type: o.mtype
							, async: false, // ?!?
							complete: function(res, stat) {
								$($t).wizGrid("progressBar", {
									method: "hide"
									, loadtype: o.saveui
									, htmlcontent: o.savetext
								});
								if (stat === "success") {
									var ret = true
										, sucret, k;
									sucret = $($t).triggerHandler("wizGridInlineSuccessSaveRow", [
										res
										, rowid
										, o
									]);
									if (!$.isArray(sucret)) {
										sucret = [
											true
											, tmp3
										];
									}
									if (sucret[0] && $.isFunction(o.successfunc)) {
										sucret = o.successfunc.call($t, res);
									}
									if ($.isArray(sucret)) {
										// expect
										// array
										// -
										// status,
										// data,
										// rowid
										ret = sucret[0];
										tmp = sucret[1] || tmp;
									}
									else {
										ret = sucret;
									}
									if (ret === true) {
										if ($t.p.autoencode) {
											$.each(tmp, function(n, v) {
												tmp[n] = $.wizgrid.htmlDecode(v);
											});
										}
										if (nullIfEmpty) {
											$.each(tmp, function(n) {
												if (tmp[n] === 'null') {
													tmp[n] = '';
												}
											});
										}
										tmp = $.extend({}, tmp, tmp2);
										$($t).wizGrid("setRowData", rowid, tmp);
										$(ind).attr("editable", "0");
										for (k = 0; k < $t.p.savedRow.length; k++) {
											if (String($t.p.savedRow[k].id) === String(rowid)) {
												fr = k;
												break;
											}
										}
										if (fr >= 0) {
											$t.p.savedRow.splice(fr, 1);
										}
										$($t).triggerHandler("wizGridInlineAfterSaveRow", [
											rowid
											, res
											, tmp
											, o
										]);
										if ($.isFunction(o.aftersavefunc)) {
											o.aftersavefunc.call($t, rowid, res, tmp, o);
										}
										success = true;
										$(ind).removeClass("wizgrid-new-row").unbind("keydown");
									}
									else {
										$($t).triggerHandler("wizGridInlineErrorSaveRow", [
											rowid
											, res
											, stat
											, null
											, o
										]);
										if ($.isFunction(o.errorfunc)) {
											o.errorfunc.call($t, rowid, res, stat, null);
										}
										if (o.restoreAfterError === true) {
											$($t).wizGrid("restoreRow", rowid, o.afterrestorefunc);
										}
									}
								}
							}
							, error: function(res, stat, err) {
								$("#lui_" + $.wizgrid.wizID($t.p.id)).hide();
								$($t).triggerHandler("wizGridInlineErrorSaveRow", [
									rowid
									, res
									, stat
									, err
									, o
								]);
								if ($.isFunction(o.errorfunc)) {
									o.errorfunc.call($t, rowid, res, stat, err);
								}
								else {
									var rT = res.responseText || res.statusText;
									try {
										$.wizgrid.info_dialog(errors.errcap, '<div class="ui-state-error">' + rT + '</div>', edit.bClose
											, {
												buttonalign: 'right'
											});
									}
									catch (e) {
										alert(rT);
									}
								}
								if (o.restoreAfterError === true) {
									$($t).wizGrid("restoreRow", rowid, o.afterrestorefunc);
								}
							}
						}, $.wizgrid.ajaxOptions, $t.p.ajaxRowOptions || {}));
					}
				}
				return success;
			}
			, restoreRow: function(rowid, afterrestorefunc) {
				// Compatible mode old versions
				var args = $.makeArray(arguments).slice(1)
					, o = {};
				if ($.type(args[0]) === "object") {
					o = args[0];
				}
				else {
					if ($.isFunction(afterrestorefunc)) {
						o.afterrestorefunc = afterrestorefunc;
					}
				}
				o = $.extend(true, {}, $.wizgrid.inlineEdit, o);
				// End compatible
				return this.each(function() {
					var $t = this
						, fr = -1
						, ind, ares = {}
						, k;
					if (!$t.grid) {
						return;
					}
					ind = $($t).wizGrid("getInd", rowid, true);
					if (ind === false) {
						return;
					}
					var bfcr = $.isFunction(o.beforeCancelRow) ? o.beforeCancelRow.call($t, o, rowid) : undefined;
					if (bfcr === undefined) {
						bfcr = true;
					}
					if (!bfcr) {
						return;
					}
					for (k = 0; k < $t.p.savedRow.length; k++) {
						if (String($t.p.savedRow[k].id) === String(rowid)) {
							fr = k;
							break;
						}
					}
					if (fr >= 0) {
						if ($.isFunction($.fn.datepicker)) {
							try {
								$("input.hasDatepicker", "#" + $.wizgrid.wizID(ind.id)).datepicker('hide');
							}
							catch (e) {}
						}
						$.each($t.p.Columns, function() {
							if (this.editable === true && $t.p.savedRow[fr].hasOwnProperty(this.name)) {
								ares[this.name] = $t.p.savedRow[fr][this.name];
							}
						});
						$($t).wizGrid("setRowData", rowid, ares);
						$(ind).attr("editable", "0").unbind("keydown");
						$t.p.savedRow.splice(fr, 1);
						if ($("#" + $.wizgrid.wizID(rowid), "#" + $.wizgrid.wizID($t.p.id)).hasClass("wizgrid-new-row")) {
							setTimeout(function() {
								$($t).wizGrid("delRowData", rowid);
								$($t).wizGrid('showAddEditButtons');
							}, 0);
						}
					}
					$($t).triggerHandler("wizGridInlineAfterRestoreRow", [rowid]);
					if ($.isFunction(o.afterrestorefunc)) {
						o.afterrestorefunc.call($t, rowid);
					}
				});
			}
			, addRow: function(p) {
				p = $.extend(true, {
					rowID: null
					, initdata: {}
					, position: "first"
					, useDefValues: true
					, useFormatter: false
					, addRowParams: {
						extraparam: {}
					}
				}, p || {});
				return this.each(function() {
					if (!this.grid) {
						return;
					}
					var $t = this;
					var bfar = $.isFunction(p.beforeAddRow) ? p.beforeAddRow.call($t, p.addRowParams) : undefined;
					if (bfar === undefined) {
						bfar = true;
					}
					if (!bfar) {
						return;
					}
					p.rowID = $.isFunction(p.rowID) ? p.rowID.call($t, p) : ((p.rowID != null) ? p.rowID : $.wizgrid.randId());
					if (p.useDefValues === true) {
						$($t.p.Columns).each(function() {
							if (this.editoptions && this.editoptions.defaultValue) {
								var opt = this.editoptions.defaultValue
									, tmp = $.isFunction(opt) ? opt.call($t) : opt;
								p.initdata[this.name] = tmp;
							}
						});
					}
					$($t).wizGrid('addRowData', p.rowID, p.initdata, p.position);
					p.rowID = $t.p.idPrefix + p.rowID;
					$("#" + $.wizgrid.wizID(p.rowID), "#" + $.wizgrid.wizID($t.p.id)).addClass("wizgrid-new-row");
					if (p.useFormatter) {
						$("#" + $.wizgrid.wizID(p.rowID) + " .ui-inline-edit", "#" + $.wizgrid.wizID($t.p.id)).click();
					}
					else {
						var opers = $t.p.prmNames
							, oper = opers.oper;
						p.addRowParams.extraparam[oper] = opers.addoper;
						$($t).wizGrid('editRow', p.rowID, p.addRowParams);
						$($t).wizGrid('setSelection', p.rowID);
					}
				});
			}
			, inlineNav: function(elem, o) {
				var regional = $.wizgrid.getRegional(this[0], 'nav');
				o = $.extend(true, {
					edit: true
					, editicon: "ui-icon-pencil"
					, add: true
					, addicon: "ui-icon-plus"
					, save: true
					, saveicon: "ui-icon-disk"
					, cancel: true
					, cancelicon: "ui-icon-cancel"
					, addParams: {
						addRowParams: {
							extraparam: {}
						}
					}
					, editParams: {}
					, restoreAfterSelect: true
				}, regional, o || {});
				return this.each(function() {
					if (!this.grid || this.p.inlineNav) {
						return;
					}
					var $t = this
						, onSelect, gID = $.wizgrid.wizID($t.p.id);
					// check to see if navgrid is started,
					// if not call it with all false
					// parameters.
					if (!$t.p.navGrid) {
						$($t).wizGrid('navGrid', elem, {
							refresh: false
							, edit: false
							, add: false
							, del: false
							, search: false
							, view: false
						});
					}
					if (!$($t).data('inlineNav')) {
						$($t).data('inlineNav', o);
					}
					if ($t.p.force_regional) {
						o = $.extend(o, regional);
					}
					$t.p.inlineNav = true;
					// detect the formatactions column
					if (o.addParams.useFormatter === true) {
						var cm = $t.p.Columns
							, i;
						for (i = 0; i < cm.length; i++) {
							if (cm[i].formatter && cm[i].formatter === "actions") {
								if (cm[i].formatoptions) {
									var defaults = {
											keys: false
											, onEdit: null
											, onSuccess: null
											, afterSave: null
											, onError: null
											, afterRestore: null
											, extraparam: {}
											, url: null
										}
										, ap = $.extend(defaults, cm[i].formatoptions);
									o.addParams.addRowParams = {
										"keys": ap.keys
										, "oneditfunc": ap.onEdit
										, "successfunc": ap.onSuccess
										, "url": ap.url
										, "extraparam": ap.extraparam
										, "aftersavefunc": ap.afterSave
										, "errorfunc": ap.onError
										, "afterrestorefunc": ap.afterRestore
									};
								}
								break;
							}
						}
					}
					if (o.add) {
						$($t).wizGrid('navButtonAdd', elem, {
							caption: o.addtext
							, title: o.addtitle
							, buttonicon: o.addicon
							, id: $t.p.id + "_iladd"
							, onClickButton: function() {
								$($t).wizGrid('addRow', o.addParams);
								if (!o.addParams.useFormatter) {
									$("#" + gID + "_ilsave").removeClass('ui-state-disabled');
									$("#" + gID + "_ilcancel").removeClass('ui-state-disabled');
									$("#" + gID + "_iladd").addClass('ui-state-disabled');
									$("#" + gID + "_iledit").addClass('ui-state-disabled');
								}
							}
						});
					}
					if (o.edit) {
						$($t).wizGrid('navButtonAdd', elem, {
							caption: o.edittext
							, title: o.edittitle
							, buttonicon: o.editicon
							, id: $t.p.id + "_iledit"
							, onClickButton: function() {
								var sr = $($t).wizGrid('getGridParam', 'selrow');
								if (sr) {
									$($t).wizGrid('editRow', sr, o.editParams);
									$("#" + gID + "_ilsave").removeClass('ui-state-disabled');
									$("#" + gID + "_ilcancel").removeClass('ui-state-disabled');
									$("#" + gID + "_iladd").addClass('ui-state-disabled');
									$("#" + gID + "_iledit").addClass('ui-state-disabled');
								}
								else {
									$.wizgrid.viewModal("#alertmod_" + gID, {
										gbox: "#gbox_" + gID
										, wizm: true
									});
									$("#wizg_alrt").focus();
								}
							}
						});
					}
					if (o.save) {
						$($t).wizGrid('navButtonAdd', elem, {
							caption: o.savetext || ''
							, title: o.savetitle || 'Save row'
							, buttonicon: o.saveicon
							, id: $t.p.id + "_ilsave"
							, onClickButton: function() {
								var sr = $t.p.savedRow[0].id;
								if (sr) {
									var opers = $t.p.prmNames
										, oper = opers.oper
										, tmpParams = o.editParams;
									if ($("#" + $.wizgrid.wizID(sr), "#" + gID).hasClass("wizgrid-new-row")) {
										o.addParams.addRowParams.extraparam[oper] = opers.addoper;
										tmpParams = o.addParams.addRowParams;
									}
									else {
										if (!o.editParams.extraparam) {
											o.editParams.extraparam = {};
										}
										o.editParams.extraparam[oper] = opers.editoper;
									}
									if ($($t).wizGrid('saveRow', sr, tmpParams)) {
										$($t).wizGrid('showAddEditButtons');
									}
								}
								else {
									$.wizgrid.viewModal("#alertmod_" + gID, {
										gbox: "#gbox_" + gID
										, wizm: true
									});
									$("#wizg_alrt").focus();
								}
							}
						});
						$("#" + gID + "_ilsave").addClass('ui-state-disabled');
					}
					if (o.cancel) {
						$($t).wizGrid('navButtonAdd', elem, {
							caption: o.canceltext || ''
							, title: o.canceltitle || 'Cancel row editing'
							, buttonicon: o.cancelicon
							, id: $t.p.id + "_ilcancel"
							, onClickButton: function() {
								var sr = $t.p.savedRow[0].id
									, cancelPrm = o.editParams;
								if (sr) {
									if ($("#" + $.wizgrid.wizID(sr), "#" + gID).hasClass("wizgrid-new-row")) {
										cancelPrm = o.addParams.addRowParams;
									}
									$($t).wizGrid('restoreRow', sr, cancelPrm);
									$($t).wizGrid('showAddEditButtons');
								}
								else {
									$.wizgrid.viewModal("#alertmod", {
										gbox: "#gbox_" + gID
										, wizm: true
									});
									$("#wizg_alrt").focus();
								}
							}
						});
						$("#" + gID + "_ilcancel").addClass('ui-state-disabled');
					}
					if (o.restoreAfterSelect === true) {
						$($t).bind("wizGridBeforeSelectRow.inlineNav", function(event, id) {
							if ($t.p.savedRow.length > 0 && $t.p.inlineNav === true && (id !== $t.p.selrow && $t.p.selrow !==
									null)) {
								if ($t.p.selrow === o.addParams.rowID) {
									$($t).wizGrid('delRowData', $t.p.selrow);
								}
								else {
									$($t).wizGrid('restoreRow', $t.p.selrow, o.editParams);
								}
								$($t).wizGrid('showAddEditButtons');
							}
						});
					}
				});
			}
			, showAddEditButtons: function() {
					return this.each(function() {
						if (!this.grid) {
							return;
						}
						var gID = $.wizgrid.wizID(this.p.id);
						$("#" + gID + "_ilsave").addClass('ui-state-disabled');
						$("#" + gID + "_ilcancel").addClass('ui-state-disabled');
						$("#" + gID + "_iladd").removeClass('ui-state-disabled');
						$("#" + gID + "_iledit").removeClass('ui-state-disabled');
					});
				}
				// end inline edit
		});
		// module begin
		if ($.wizgrid.msie && $.wizgrid.msiever() === 8) {
			$.expr[":"].hidden = function(elem) {
				return elem.offsetWidth === 0 || elem.offsetHeight === 0 || elem.style.display === "none";
			};
		}
		// requiere load multiselect before grid
		$.wizgrid._multiselect = false;
		if ($.ui) {
			if ($.ui.multiselect) {
				if ($.ui.multiselect.prototype._setSelected) {
					var setSelected = $.ui.multiselect.prototype._setSelected;
					$.ui.multiselect.prototype._setSelected = function(item, selected) {
						var ret = setSelected.call(this, item, selected);
						if (selected && this.selectedList) {
							var elt = this.element;
							this.selectedList.find('li').each(function() {
								if ($(this).data('optionLink')) {
									$(this).data('optionLink').remove().appendTo(elt);
								}
							});
						}
						return ret;
					};
				}
				if ($.ui.multiselect.prototype.destroy) {
					$.ui.multiselect.prototype.destroy = function() {
						this.element.show();
						this.container.remove();
						if ($.Widget === undefined) {
							$.widget.prototype.destroy.apply(this, arguments);
						}
						else {
							$.Widget.prototype.destroy.apply(this, arguments);
						}
					};
				}
				$.wizgrid._multiselect = true;
			}
		}
		$.wizgrid.extend({
			sortableColumns: function(tblrow) {
				return this.each(function() {
					
					var ts = this
						, tid = $.wizgrid.wizID(ts.p.id);

					function start() {
						ts.p.disableClick = true;
					}
					var sortable_opts = {
						"tolerance": "pointer"
						, "axis": "x"
						, "scrollSensitivity": "1"
						, "items": '>th:not(:has(#wizgh_' + tid + '_cb' + ',#wizgh_' + tid + '_rn'  + ',#wizgh_' + tid +
							'_subgrid),:hidden)'
						, "placeholder": {
							element: function(item) {
								var el = $(document.createElement(item[0].nodeName)).addClass(item[0].className +
									" ui-sortable-placeholder ui-state-highlight-grid").removeClass("ui-sortable-helper")[0];
								return el;
							}
							, update: function(self, p) {
								p.height(self.currentItem.innerHeight() - parseInt(self.currentItem.css('paddingTop') || 0
									, 10) - parseInt(self.currentItem.css('paddingBottom') || 0, 10));
								p.width(self.currentItem.innerWidth() - parseInt(self.currentItem.css('paddingLeft') || 0, 10) -
									parseInt(self.currentItem.css('paddingRight') || 0, 10));
							}
						}
						, "update": function(event, ui) {
							var p = $(ui.item).parent()
								, th = $(">th", p)
								, Columns = ts.p.Columns
								, cmMap = {}
								, tid = ts.p.id + "_";
							$.each(Columns, function(i) {
								cmMap[this.name] = i;
							});
							var permutation = [];
							th.each(function() {
								var id = $(">div", this).get(0).id.replace(/^wizgh_/, "").replace(tid, "");
								if (cmMap.hasOwnProperty(id)) {
									permutation.push(cmMap[id]);
								}
							});
							$(ts).wizGrid("remapColumns", permutation, true, true);
							if ($.isFunction(ts.p.sortable.update)) {
								ts.p.sortable.update(permutation);
							}
							setTimeout(function() {
								ts.p.disableClick = false;
							}, 50);
						}
					};
					if (ts.p.sortable.options) {
						$.extend(sortable_opts, ts.p.sortable.options);
					}
					else if ($.isFunction(ts.p.sortable)) {
						ts.p.sortable = {
							"update": ts.p.sortable
						};
					}
					if (sortable_opts.start) {
						var s = sortable_opts.start;
						sortable_opts.start = function(e, ui) {
							start();
							s.call(this, e, ui);
						};
					}
					else {
						sortable_opts.start = start;
					}
					if (ts.p.sortable.exclude) {
						sortable_opts.items += ":not(" + ts.p.sortable.exclude + ")";
					}
					/* 박준용 column move 처리 팝업 칼린더 막기 */
					$.each(tblrow[0].childNodes, function(i,val){
						//console.log($(val)[0].colSpan);
						   if($(val).attr('eltype') == 'popup'){
							  
							   sortable_opts.items += "th:not(:has(#" + $(val).prev().children().attr('id') + "))";
						   }else if($(val).attr('eltype') == 'calendar'){
							   sortable_opts.items += "th:not(:has(#" + $(val).prev().children().attr('id') + "))";
							   
						   }
						});
					// 끝 
					var $e = tblrow.sortable(sortable_opts)
						, dataObj = $e.data("sortable") || $e.data("uiSortable");
					if (dataObj != null) {
						dataObj.data("sortable").floating = true;
					}
				});
			}
			, columnChooser: function(opts) {
				var self = this
					, selector, select, colMap = {}
					, fixedCols = []
					, dopts, mopts, $dialogContent, multiselectData, listHeight, Columns = self.wizGrid("getGridParam"
						, "Columns")
					, Names = self.wizGrid("getGridParam", "Names")
					, getMultiselectWidgetData = function($elem) {
						return ($.ui.multiselect.prototype && $elem.data($.ui.multiselect.prototype.widgetFullName || $.ui.multiselect
							.prototype.widgetName)) || $elem.data("ui-multiselect") || $elem.data("multiselect");
					}
					, regional = $.wizgrid.getRegional(this[0], 'col');
				if ($("#colchooser_" + $.wizgrid.wizID(self[0].p.id)).length) {
					return;
				}
				selector = $('<div id="colchooser_' + self[0].p.id +
					'" style="position:relative;overflow:hidden"><div><select multiple="multiple"></select></div></div>'
				);
				select = $('select', selector);

				function insert(perm, i, v) {
					var a, b;
					if (i >= 0) {
						a = perm.slice();
						b = a.splice(i, Math.max(perm.length - i, i));
						if (i > perm.length) {
							i = perm.length;
						}
						a[i] = v;
						return a.concat(b);
					}
					return perm;
				}

				function call(fn, obj) {
					if (!fn) {
						return;
					}
					if (typeof fn === 'string') {
						if ($.fn[fn]) {
							$.fn[fn].apply(obj, $.makeArray(arguments).slice(2));
						}
					}
					else if ($.isFunction(fn)) {
						fn.apply(obj, $.makeArray(arguments).slice(2));
					}
				}
				opts = $.extend({
					width: 400
					, height: 240
					, classname: null
					, done: function(perm) {
						if (perm) {
							self.wizGrid("remapColumns", perm, true);
						}
					}
					, /*
					 * msel is either the name of a
					 * ui widget class that extends
					 * a multiselect, or a function
					 * that supports creating a
					 * multiselect object (with no
					 * argument, or when passed an
					 * object), and destroying it
					 * (when passed the string
					 * "destroy").
					 */
					msel: "multiselect"
					, /* "msel_opts" : {}, */
					/*
					 * dlog is either the name of a
					 * ui widget class that behaves
					 * in a dialog-like way, or a
					 * function, that supports
					 * creating a dialog (when
					 * passed dlog_opts) or
					 * destroying a dialog (when
					 * passed the string "destroy")
					 */
					dlog: "dialog"
					, dialog_opts: {
						minWidth: 470
						, dialogClass: ".ui-wizdialog"
					}
					, /*
					 * dlog_opts is either an option
					 * object to be passed to
					 * "dlog", or (more likely) a
					 * function that creates the
					 * options object. The default
					 * produces a suitable options
					 * object for ui.dialog
					 */
					dlog_opts: function(options) {
						var buttons = {};
						buttons[options.bSubmit] = function() {
							options.apply_perm();
							options.cleanup(false);
						};
						buttons[options.bCancel] = function() {
							options.cleanup(true);
						};
						return $.extend(true, {
							buttons: buttons
							, close: function() {
								options.cleanup(true);
							}
							, modal: options.modal || false
							, resizable: options.resizable || true
							, width: options.width + 70
							, resize: function() {
								var widgetData = getMultiselectWidgetData(select)
									, $thisDialogContent = widgetData.container.closest(".ui-dialog-content");
								if ($thisDialogContent.length > 0 && typeof $thisDialogContent[0].style === "object") {
									$thisDialogContent[0].style.width = "";
								}
								else {
									$thisDialogContent.css("width", ""); // or
									// just
									// remove
									// width
									// style
								}
								widgetData.selectedList.height(Math.max(widgetData.selectedContainer.height() - widgetData.selectedActions
									.outerHeight() - 1, 1));
								widgetData.availableList.height(Math.max(widgetData.availableContainer.height() -
									widgetData.availableActions.outerHeight() - 1, 1));
							}
						}, options.dialog_opts || {});
					}
					, /*
					 * Function to get the
					 * permutation array, and pass
					 * it to the "done" function
					 */
					apply_perm: function() {
						var perm = [];
						$('option', select).each(function() {
							if ($(this).is("[selected]")) {
								self.wizGrid("showCol", Columns[this.value].name);
							}
							else {
								self.wizGrid("hideCol", Columns[this.value].name);
							}
						});
						// fixedCols.slice(0);
						$('option[selected]', select).each(function() {
							perm.push(parseInt(this.value, 10));
						});
						$.each(perm, function() {
							delete colMap[Columns[parseInt(this, 10)].name];
						});
						$.each(colMap, function() {
							var ti = parseInt(this, 10);
							perm = insert(perm, ti, ti);
						});
						if (opts.done) {
							opts.done.call(self, perm);
						}
						self.wizGrid("setGridWidth", self[0].p.tblwidth, self[0].p.shrinkToFit);
					}
					, /*
					 * Function to cleanup the
					 * dialog, and select. Also
					 * calls the done function with
					 * no permutation (to indicate
					 * that the columnChooser was
					 * aborted
					 */
					cleanup: function(calldone) {
						call(opts.dlog, selector, 'destroy');
						call(opts.msel, select, 'destroy');
						selector.remove();
						if (calldone && opts.done) {
							opts.done.call(self);
						}
					}
					, msel_opts: {}
				}, regional, opts || {});
				if ($.ui) {
					if ($.ui.multiselect && $.ui.multiselect.defaults) {
						if (!$.wizgrid._multiselect) {
							// should be in language file
							alert("Multiselect plugin loaded after wizGrid. Please load the plugin before the wizGrid!");
							return;
						}
						// ??? the next line uses
						// $.ui.multiselect.defaults which will be
						// typically undefined
						opts.msel_opts = $.extend($.ui.multiselect.defaults, opts.msel_opts);
					}
				}
				if (opts.caption) {
					selector.attr("title", opts.caption);
				}
				if (opts.classname) {
					selector.addClass(opts.classname);
					select.addClass(opts.classname);
				}
				if (opts.width) {
					$(">div", selector).css({
						width: opts.width
						, margin: "0 auto"
					});
					select.css("width", opts.width);
				}
				if (opts.height) {
					$(">div", selector).css("height", opts.height);
					select.css("height", opts.height - 10);
				}
				select.empty();
				$.each(Columns, function(i) {
					colMap[this.name] = i;
					if (this.hidedlg) {
						if (!this.hidden) {
							fixedCols.push(i);
						}
						return;
					}
					select.append("<option value='" + i + "' " + (this.hidden ? "" : "selected='selected'") + ">" + $.wizgrid
						.stripHtml(Names[i]) + "</option>");
				});
				dopts = $.isFunction(opts.dlog_opts) ? opts.dlog_opts.call(self, opts) : opts.dlog_opts;
				call(opts.dlog, selector, dopts);
				mopts = $.isFunction(opts.msel_opts) ? opts.msel_opts.call(self, opts) : opts.msel_opts;
				call(opts.msel, select, mopts);
				// fix height of elements of the multiselect widget
				$dialogContent = $("#colchooser_" + $.wizgrid.wizID(self[0].p.id));
				$dialogContent.css({
					margin: "auto"
				});
				$dialogContent.find(">div").css({
					width: "100%"
					, height: "100%"
					, margin: "auto"
				});
				multiselectData = getMultiselectWidgetData(select);
				multiselectData.container.css({
					width: "100%"
					, height: "100%"
					, margin: "auto"
				});
				multiselectData.selectedContainer.css({
					width: multiselectData.options.dividerLocation * 100 + "%"
					, height: "100%"
					, margin: "auto"
					, boxSizing: "border-box"
				});
				multiselectData.availableContainer.css({
					width: (100 - multiselectData.options.dividerLocation * 100) + "%"
					, height: "100%"
					, margin: "auto"
					, boxSizing: "border-box"
				});
				// set height for both selectedList and
				// availableList
				multiselectData.selectedList.css("height", "auto");
				multiselectData.availableList.css("height", "auto");
				listHeight = Math.max(multiselectData.selectedList.height(), multiselectData.availableList.height());
				listHeight = Math.min(listHeight, $(window).height());
				multiselectData.selectedList.css("height", listHeight);
				multiselectData.availableList.css("height", listHeight);
			}
			, sortableRows: function(opts) {
				// Can accept all sortable options and events
				return this.each(function() {
					var $t = this;
					if (!$t.grid) {
						return;
					}
					// Currently we disable a treeGrid
					// sortable
					if ($t.p.treeGrid) {
						return;
					}
					if ($.fn.sortable) {
						opts = $.extend({
							"cursor": "move"
							, "axis": "y"
							, "items": " > .wizgrow"
						}, opts || {});
						if (opts.start && $.isFunction(opts.start)) {
							opts._start_ = opts.start;
							delete opts.start;
						}
						else {
							opts._start_ = false;
						}
						if (opts.update && $.isFunction(opts.update)) {
							opts._update_ = opts.update;
							delete opts.update;
						}
						else {
							opts._update_ = false;
						}
						opts.start = function(ev, ui) {
							$(ui.item).css("border-width", "0");
							$("td", ui.item).each(function(i) {
								this.style.width = $t.grid.cols[i].style.width;
							});
							if ($t.p.subGrid) {
								var subgid = $(ui.item).attr("id");
								try {
									$($t).wizGrid('collapseSubGridRow', subgid);
								}
								catch (e) {}
							}
							if (opts._start_) {
								opts._start_.apply(this, [
									ev, ui
								]);
							}
						};
						opts.update = function(ev, ui) {
							$(ui.item).css("border-width", "");
							if ($t.p.rownumbers === true) {
								$("td.wizgrid-rownum", $t.rows).each(function(i) {
									$(this).html(i + 1 + (parseInt($t.p.page, 10) - 1) * parseInt($t.p.rowNum, 10));
								});
							}
							if (opts._update_) {
								opts._update_.apply(this, [
									ev, ui
								]);
							}
						};
						$("tbody:first", $t).sortable(opts);
						$("tbody:first > .wizgrow", $t).disableSelection();
					}
				});
			}
			, gridDnD: function(opts) {
				return this.each(function() {
					var $t = this
						, i, cn;
					if (!$t.grid) {
						return;
					}
					// Currently we disable a treeGrid drag
					// and drop
					if ($t.p.treeGrid) {
						return;
					}
					if (!$.fn.draggable || !$.fn.droppable) {
						return;
					}

					function updateDnD() {
						var datadnd = $.data($t, "dnd");
						$("tr.wizgrow:not(.ui-draggable)", $t).draggable($.isFunction(datadnd.drag) ? datadnd.drag.call($(
							$t), datadnd) : datadnd.drag);
					}
					var appender = "<table id='wizgrid_dnd' class='ui-wizgrid-dnd'></table>";
					if ($("#wizgrid_dnd")[0] === undefined) {
						$('body').append(appender);
					}
					if (typeof opts === 'string' && opts === 'updateDnD' && $t.p.wizgdnd === true) {
						updateDnD();
						return;
					}
					opts = $.extend({
						"drag": function(opts) {
							return $.extend({
								start: function(ev, ui) {
									var i, subgid;
									// if
									// we
									// are
									// in
									// subgrid
									// mode
									// try
									// to
									// collapse
									// the
									// node
									if ($t.p.subGrid) {
										subgid = $(ui.helper).attr("id");
										try {
											$($t).wizGrid('collapseSubGridRow', subgid);
										}
										catch (e) {}
									}
									// hack
									// drag
									// and
									// drop
									// does
									// not
									// insert
									// tr
									// in
									// table,
									// when
									// the
									// table
									// has
									// no
									// rows
									// we
									// try
									// to
									// insert
									// new
									// empty
									// row
									// on
									// the
									// target(s)
									for (i = 0; i < $.data($t, "dnd").connectWith.length; i++) {
										if ($($.data($t, "dnd").connectWith[i]).wizGrid('getGridParam', 'reccount') === 0) {
											$($.data($t, "dnd").connectWith[i]).wizGrid('addRowData', 'wizg_empty_row', {});
										}
									}
									ui.helper.addClass("ui-state-highlight-grid");
									$("td", ui.helper).each(function(i) {
										this.style.width = $t.grid.headers[i].width + "px";
									});
									if (opts.onstart && $.isFunction(opts.onstart)) {
										opts.onstart.call($($t), ev, ui);
									}
								}
								, stop: function(ev, ui) {
									var i, ids;
									if (ui.helper.dropped && !opts.dragcopy) {
										ids = $(ui.helper).attr("id");
										if (ids === undefined) {
											ids = $(this).attr("id");
										}
										$($t).wizGrid('delRowData', ids);
									}
									// if
									// we
									// have
									// a
									// empty
									// row
									// inserted
									// from
									// start
									// event
									// try
									// to
									// delete
									// it
									for (i = 0; i < $.data($t, "dnd").connectWith.length; i++) {
										$($.data($t, "dnd").connectWith[i]).wizGrid('delRowData', 'wizg_empty_row');
									}
									if (opts.onstop && $.isFunction(opts.onstop)) {
										opts.onstop.call($($t), ev, ui);
									}
								}
							}, opts.drag_opts || {});
						}
						, "drop": function(opts) {
							return $.extend({
								accept: function(d) {
									if (!$(d).hasClass('wizgrow')) {
										return d;
									}
									var tid = $(d).closest("table.ui-wizgrid-btable");
									if (tid.length > 0 && $.data(tid[0], "dnd") !== undefined) {
										var cn = $.data(tid[0], "dnd").connectWith;
										return $.inArray('#' + $.wizgrid.wizID(this.id), cn) !== -1 ? true : false;
									}
									return false;
								}
								, drop: function(ev, ui) {
									if (!$(ui.draggable).hasClass('wizgrow')) {
										return;
									}
									var accept = $(ui.draggable).attr("id");
									var getdata = ui.draggable.parent().parent().wizGrid('getRowData', accept);
									if (!opts.dropbyname) {
										var j = 0
											, tmpdata = {}
											, nm, key;
										var dropmodel = $("#" + $.wizgrid.wizID(this.id)).wizGrid('getGridParam', 'Columns');
										try {
											for (key in getdata) {
												if (getdata.hasOwnProperty(key)) {
													nm = dropmodel[j].name;
													if (!(nm === 'cb' || nm === 'rn' || nm === 'subgrid')) {
														if (getdata.hasOwnProperty(key) && dropmodel[j]) {
															tmpdata[nm] = getdata[key];
														}
													}
													j++;
												}
											}
											getdata = tmpdata;
										}
										catch (e) {}
									}
									ui.helper.dropped = true;
									if (opts.beforedrop && $.isFunction(opts.beforedrop)) {
										// parameters
										// to
										// this
										// callback
										// -
										// event,
										// element,
										// data
										// to
										// be
										// inserted,
										// sender,
										// reciever
										// should
										// return
										// object
										// which
										// will
										// be
										// inserted
										// into
										// the
										// reciever
										var datatoinsert = opts.beforedrop.call(this, ev, ui, getdata, $('#' + $.wizgrid.wizID(
											$t.p.id)), $(this));
										if (datatoinsert !== undefined && datatoinsert !== null && typeof datatoinsert ===
											"object") {
											getdata = datatoinsert;
										}
									}
									if (ui.helper.dropped) {
										var grid;
										if (opts.autoid) {
											if ($.isFunction(opts.autoid)) {
												grid = opts.autoid.call(this, getdata);
											}
											else {
												grid = Math.ceil(Math.random() * 1000);
												grid = opts.autoidprefix + grid;
											}
										}
										// NULL
										// is
										// interpreted
										// as
										// undefined
										// while
										// null
										// as
										// object
										$("#" + $.wizgrid.wizID(this.id)).wizGrid('addRowData', grid, getdata, opts.droppos);
									}
									if (opts.ondrop && $.isFunction(opts.ondrop)) {
										opts.ondrop.call(this, ev, ui, getdata);
									}
								}
							}, opts.drop_opts || {});
						}
						, "onstart": null
						, "onstop": null
						, "beforedrop": null
						, "ondrop": null
						, "drop_opts": {
							"activeClass": "ui-state-active"
							, "hoverClass": "ui-state-hover-grid"
						}
						, "drag_opts": {
							"revert": "invalid"
							, "helper": "clone"
							, "cursor": "move"
							, "appendTo": "#wizgrid_dnd"
							, "zIndex": 5000
						}
						, "dragcopy": false
						, "dropbyname": false
						, "droppos": "first"
						, "autoid": true
						, "autoidprefix": "dnd_"
					}, opts || {});
					if (!opts.connectWith) {
						return;
					}
					opts.connectWith = opts.connectWith.split(",");
					opts.connectWith = $.map(opts.connectWith, function(n) {
						return $.trim(n);
					});
					$.data($t, "dnd", opts);
					if ($t.p.reccount !== 0 && !$t.p.wizgdnd) {
						updateDnD();
					}
					$t.p.wizgdnd = true;
					for (i = 0; i < opts.connectWith.length; i++) {
						cn = opts.connectWith[i];
						$(cn).droppable($.isFunction(opts.drop) ? opts.drop.call($($t), opts) : opts.drop);
					}
				});
			}
			, gridResize: function(opts) {
				return this.each(function() {
					var $t = this
						, gID = $.wizgrid.wizID($t.p.id);
					if (!$t.grid || !$.fn.resizable) {
						return;
					}
					opts = $.extend({}, opts || {});
					if (opts.alsoResize) {
						opts._alsoResize_ = opts.alsoResize;
						delete opts.alsoResize;
					}
					else {
						opts._alsoResize_ = false;
					}
					if (opts.stop && $.isFunction(opts.stop)) {
						opts._stop_ = opts.stop;
						delete opts.stop;
					}
					else {
						opts._stop_ = false;
					}
					opts.stop = function(ev, ui) {
						$($t).wizGrid('setGridParam', {
							height: $("#gview_" + gID + " .ui-wizgrid-bdiv").height()
						});
						$($t).wizGrid('setGridWidth', ui.size.width, opts.shrinkToFit);
						if (opts._stop_) {
							opts._stop_.call($t, ev, ui);
						}
					};
					if (opts._alsoResize_) {
						var optstest = "{\'#gview_" + gID + " .ui-wizgrid-bdiv\':true,'" + opts._alsoResize_ + "':true}";
						opts.alsoResize = eval('(' + optstest + ')'); // the
						// only
						// way
						// that
						// I
						// found
						// to do
						// this
					}
					else {
						opts.alsoResize = $(".ui-wizgrid-bdiv", "#gview_" + gID);
					}
					delete opts._alsoResize_;
					$("#gbox_" + gID).resizable(opts);
				});
			}
		});
		// module begin
		function _pivotfilter(fn, context) {
			/* jshint validthis: true */
			var i, value, result = []
				, length;
			if (!this || typeof fn !== 'function' || (fn instanceof RegExp)) {
				throw new TypeError();
			}
			length = this.length;
			for (i = 0; i < length; i++) {
				if (this.hasOwnProperty(i)) {
					value = this[i];
					if (fn.call(context, value, i, this)) {
						result.push(value);
						// We need break in order to cancel loop
						// in case the row is found
						break;
					}
				}
			}
			return result;
		}
		$.assocArraySize = function(obj) {
			// http://stackoverflow.com/a/6700/11236
			var size = 0
				, key;
			for (key in obj) {
				if (obj.hasOwnProperty(key)) {
					size++;
				}
			}
			return size;
		};
		$.wizgrid.extend({
			pivotSetup: function(data, options) {
				// data should come in json format
				// The function return the new Columns and the
				// transformed data
				// again with group setup options which then will be
				// passed to the grid
				var columns = []
					, pivotrows = []
					, summaries = []
					, member = []
					, labels = []
					, groupOptions = {
						grouping: true
						, groupingView: {
							groupField: []
							, groupSummary: []
							, groupSummaryPos: []
						}
					}
					, headers = []
					, o = $.extend({
						rowTotals: false
						, rowTotalsText: 'Total'
						, // summary columns
						colTotals: false
						, groupSummary: true
						, groupSummaryPos: 'header'
						, frozenStaticCols: false
					}, options || {});
				this.each(function() {
					var row, rowindex, i
						, rowlen = data.length
						, xlen, ylen, aggrlen, tmp, newObj, r = 0;
					// utility funcs
					/*
					 * Filter the data to a given criteria.
					 * Return the firt occurance
					 */
					function find(ar, fun, extra) {
						var res;
						res = _pivotfilter.call(ar, fun, extra);
						return res.length > 0 ? res[0] : null;
					}
					/*
					 * Check if the grouped row column exist
					 * (See find) If the row is not find in
					 * pivot rows retun null, otherviese the
					 * column
					 */
					function findGroup(item, index) {
						/* jshint validthis: true */
						var j = 0
							, ret = true
							, i;
						for (i in item) {
							if (item.hasOwnProperty(i)) {
								if (item[i] != this[j]) {
									ret = false;
									break;
								}
							}
							j++;
							if (j >= this.length) {
								break;
							}
						}
						if (ret) {
							rowindex = index;
						}
						return ret;
					}
					/*
					 * Perform calculations of the pivot
					 * values.
					 */
					function calculation(oper, v, field, rc) {
						var ret;
						switch (oper) {
							case "sum":
								ret = parseFloat(v || 0) + parseFloat((rc[field] || 0));
								break;
							case "count":
								if (v === "" || v == null) {
									v = 0;
								}
								if (rc.hasOwnProperty(field)) {
									ret = v + 1;
								}
								else {
									ret = 0;
								}
								break;
							case "min":
								if (v === "" || v == null) {
									ret = parseFloat(rc[field] || 0);
								}
								else {
									ret = Math.min(parseFloat(v), parseFloat(rc[field] || 0));
								}
								break;
							case "max":
								if (v === "" || v == null) {
									ret = parseFloat(rc[field] || 0);
								}
								else {
									ret = Math.max(parseFloat(v), parseFloat(rc[field] || 0));
								}
								break;
						}
						return ret;
					}
					/*
					 * The function agragates the values of
					 * the pivot grid. Return the current
					 * row with pivot summary values
					 */
					function agregateFunc(row, aggr, value, curr) {
						// default is sum
						var arrln = aggr.length
							, i, label, j, jv, mainval = ""
							, swapvals = [];
						if ($.isArray(value)) {
							jv = value.length;
							swapvals = value;
						}
						else {
							jv = 1;
							swapvals[0] = value;
						}
						member = [];
						labels = [];
						member.root = 0;
						for (j = 0; j < jv; j++) {
							var tmpmember = []
								, vl;
							for (i = 0; i < arrln; i++) {
								if (value == null) {
									label = $.trim(aggr[i].member) + "_" + aggr[i].aggregator;
									vl = label;
									swapvals[0] = vl;
								}
								else {
									vl = value[j].replace(/\s+/g, '');
									try {
										label = (arrln === 1 ? mainval + vl : mainval + vl + "_" + aggr[i].aggregator + "_" + String(
											i));
									}
									catch (e) {}
								}
								label = !isNaN(parseInt(label, 10)) ? label + " " : label;
								curr[label] = tmpmember[label] = calculation(aggr[i].aggregator, curr[label], aggr[i].member
									, row);
								if (j <= 1 && vl !== '_r_Totals' && mainval === "") { // this
									// does
									// not
									// fix
									// full
									// the
									// problem
									mainval = vl;
								}
							}
							// vl = !isNaN(parseInt(vl,10))
							// ? vl + " " : vl;
							member[label] = tmpmember;
							labels[label] = swapvals[j];
						}
						return curr;
					}
					// Making the row totals without to add
					// in yDimension
					if (o.rowTotals && o.yDimension.length > 0) {
						var dn = o.yDimension[0].dataName;
						o.yDimension.splice(0, 0, {
							dataName: dn
						});
						o.yDimension[0].converter = function() {
							return '_r_Totals';
						};
					}
					// build initial columns (Columns) from
					// xDimension
					xlen = $.isArray(o.xDimension) ? o.xDimension.length : 0;
					ylen = o.yDimension.length;
					aggrlen = $.isArray(o.aggregates) ? o.aggregates.length : 0;
					if (xlen === 0 || aggrlen === 0) {
						throw ("xDimension or aggregates optiona are not set!");
					}
					var colc;
					for (i = 0; i < xlen; i++) {
						colc = {
							name: o.xDimension[i].dataName
							, frozen: o.frozenStaticCols
						};
						if (o.xDimension[i].isGroupField == null) {
							o.xDimension[i].isGroupField = true;
						}
						colc = $.extend(true, colc, o.xDimension[i]);
						columns.push(colc);
					}
					var groupfields = xlen - 1
						, tree = {};
					// tree = { text: 'root', leaf: false,
					// children: [] };
					// loop over alll the source data
					while (r < rowlen) {
						row = data[r];
						var xValue = [];
						var yValue = [];
						tmp = {};
						i = 0;
						// build the data from xDimension
						do {
							xValue[i] = $.trim(row[o.xDimension[i].dataName]);
							tmp[o.xDimension[i].dataName] = xValue[i];
							i++;
						} while (i < xlen);
						var k = 0;
						rowindex = -1;
						// check to see if the row is in our
						// new pivotrow set
						newObj = find(pivotrows, findGroup, xValue);
						if (!newObj) {
							// if the row is not in our set
							k = 0;
							// if yDimension is set
							if (ylen >= 1) {
								// build the cols set in
								// yDimension
								for (k = 0; k < ylen; k++) {
									yValue[k] = $.trim(row[o.yDimension[k].dataName]);
									// Check to see if we
									// have user defined
									// conditions
									if (o.yDimension[k].converter && $.isFunction(o.yDimension[k].converter)) {
										yValue[k] = o.yDimension[k].converter.call(this, yValue[k], xValue, yValue);
									}
								}
								// make the colums based on
								// aggregates definition
								// and return the members
								// for late calculation
								tmp = agregateFunc(row, o.aggregates, yValue, tmp);
							}
							else if (ylen === 0) {
								// if not set use direct the
								// aggregates
								tmp = agregateFunc(row, o.aggregates, null, tmp);
							}
							// add the result in pivot rows
							pivotrows.push(tmp);
						}
						else {
							// the pivot exists
							if (rowindex >= 0) {
								k = 0;
								// make the recalculations
								if (ylen >= 1) {
									for (k = 0; k < ylen; k++) {
										yValue[k] = $.trim(row[o.yDimension[k].dataName]);
										if (o.yDimension[k].converter && $.isFunction(o.yDimension[k].converter)) {
											yValue[k] = o.yDimension[k].converter.call(this, yValue[k], xValue, yValue);
										}
									}
									newObj = agregateFunc(row, o.aggregates, yValue, newObj);
								}
								else if (ylen === 0) {
									newObj = agregateFunc(row, o.aggregates, null, newObj);
								}
								// update the row
								pivotrows[rowindex] = newObj;
							}
						}
						var kj = 0
							, current = null
							, existing = null
							, kk;
						// Build a JSON tree from the member
						// (see aggregateFunc)
						// to make later the columns
						// 
						for (kk in member) {
							if (member.hasOwnProperty(kk)) {
								if (kj === 0) {
									if (!tree.children || tree.children === undefined) {
										tree = {
											text: kk
											, level: 0
											, children: []
											, label: kk
										};
									}
									current = tree.children;
								}
								else {
									existing = null;
									for (i = 0; i < current.length; i++) {
										if (current[i].text === kk) {
											// current[i].fields=member[kk];
											existing = current[i];
											break;
										}
									}
									if (existing) {
										current = existing.children;
									}
									else {
										current.push({
											children: []
											, text: kk
											, level: kj
											, fields: member[kk]
											, label: labels[kk]
										});
										current = current[current.length - 1].children;
									}
								}
								kj++;
							}
						}
						r++;
					}
					var lastval = []
						, initColLen = columns.length
						, swaplen = initColLen;
					if (ylen > 0) {
						headers[ylen - 1] = {
							useColSpanStyle: false
							, groupHeaders: []
						};
					}
					/*
					 * Recursive function which uses the
					 * tree to build the columns from the
					 * pivot values and set the group
					 * Headers
					 */
					function list(items) {
						var l, j, key, k, col;
						for (key in items) { // iterate
							if (items.hasOwnProperty(key)) {
								// write amount of spaces
								// according to level
								// and write name and
								// newline
								if (typeof items[key] !== "object") {
									// If not a object build
									// the header of the
									// appropriate level
									if (key === 'level') {
										if (lastval[items.level] === undefined) {
											lastval[items.level] = '';
											if (items.level > 0 && items.text !== '_r_Totals') {
												headers[items.level - 1] = {
													useColSpanStyle: false
													, groupHeaders: []
												};
											}
										}
										if (lastval[items.level] !== items.text && items.children.length && items.text !==
											'_r_Totals') {
											if (items.level > 0) {
												headers[items.level - 1].groupHeaders.push({
													titleText: items.label
													, numberOfColumns: 0
												});
												var collen = headers[items.level - 1].groupHeaders.length - 1
													, colpos = collen === 0 ? swaplen : initColLen + aggrlen;
												if (items.level - 1 === (o.rowTotals ? 1 : 0)) {
													if (collen > 0) {
														var l1 = headers[items.level - 1].groupHeaders[collen - 1].numberOfColumns;
														if (l1) {
															colpos = l1 + 1 + o.aggregates.length;
														}
													}
												}
												headers[items.level - 1].groupHeaders[collen].startColumnName = columns[colpos].name;
												headers[items.level - 1].groupHeaders[collen].numberOfColumns = columns.length - colpos;
												initColLen = columns.length;
											}
										}
										lastval[items.level] = items.text;
									}
									// This is in case when
									// the member contain
									// more than one summary
									// item
									if (items.level === ylen && key === 'level' && ylen > 0) {
										if (aggrlen > 1) {
											var ll = 1;
											for (l in items.fields) {
												if (items.fields.hasOwnProperty(l)) {
													if (ll === 1) {
														headers[ylen - 1].groupHeaders.push({
															startColumnName: l
															, numberOfColumns: 1
															, titleText: items.text
														});
													}
												}
												ll++;
											}
											headers[ylen - 1].groupHeaders[headers[ylen - 1].groupHeaders.length - 1].numberOfColumns =
												ll - 1;
										}
										else {
											headers.splice(ylen - 1, 1);
										}
									}
								}
								// if object, call
								// recursively
								if (items[key] != null && typeof items[key] === "object") {
									list(items[key]);
								}
								// Finally build the
								// coulumns
								if (key === 'level') {
									if (items.level > 0) {
										j = 0;
										for (l in items.fields) {
											if (items.fields.hasOwnProperty(l)) {
												col = {};
												for (k in o.aggregates[j]) {
													if (o.aggregates[j].hasOwnProperty(k)) {
														switch (k) {
															case 'member':
															case 'label':
															case 'aggregator':
																break;
															default:
																col[k] = o.aggregates[j][k];
														}
													}
												}
												if (aggrlen > 1) {
													col.name = l;
													col.label = o.aggregates[j].label || items.label;
												}
												else {
													col.name = items.text;
													col.label = items.text === '_r_Totals' ? o.rowTotalsText : items.label;
												}
												columns.push(col);
												j++;
											}
										}
									}
								}
							}
						}
					}
					list(tree);
					var nm;
					// loop again trougth the pivot rows in
					// order to build grand total
					if (o.colTotals) {
						var plen = pivotrows.length;
						while (plen--) {
							for (i = xlen; i < columns.length; i++) {
								nm = columns[i].name;
								if (!summaries[nm]) {
									summaries[nm] = parseFloat(pivotrows[plen][nm] || 0);
								}
								else {
									summaries[nm] += parseFloat(pivotrows[plen][nm] || 0);
								}
							}
						}
					}
					// based on xDimension levels build
					// grouping
					if (groupfields > 0) {
						for (i = 0; i < groupfields; i++) {
							if (columns[i].isGroupField) {
								groupOptions.groupingView.groupField.push(columns[i].name);
								groupOptions.groupingView.groupSummary.push(o.groupSummary);
								groupOptions.groupingView.groupSummaryPos.push(o.groupSummaryPos);
							}
						}
					}
					else {
						// no grouping is needed
						groupOptions.grouping = false;
					}
					groupOptions.sortname = columns[groupfields].name;
					groupOptions.groupingView.hideFirstGroupCol = true;
				});
				// return the final result.
				return {
					"Columns": columns
					, "rows": pivotrows
					, "groupOptions": groupOptions
					, "groupHeaders": headers
					, summary: summaries
				};
			}
			, wizPivot: function(data, pivotOpt, gridOpt, ajaxOpt) {
				return this.each(function() {
					var $t = this;

					function pivot(data) {
						var pivotGrid = jQuery($t).wizGrid('pivotSetup', data, pivotOpt)
							, footerrow = $.assocArraySize(pivotGrid.summary) > 0 ? true : false
							, query = $.wizgrid.from.call($t, pivotGrid.rows)
							, i;
						for (i = 0; i < pivotGrid.groupOptions.groupingView.groupField.length; i++) {
							query.orderBy(pivotGrid.groupOptions.groupingView.groupField[i], "a", 'text', '');
						}
						jQuery($t).wizGrid($.extend(true, {
							datastr: $.extend(query.select(), footerrow ? {
								userdata: pivotGrid.summary
							} : {})
							, datatype: "jsonstring"
							, footerrow: footerrow
							, userDataOnFooter: footerrow
							, Columns: pivotGrid.Columns
							, viewrecords: true
							, sortname: pivotOpt.xDimension[0].dataName
								// ?????
						}, pivotGrid.groupOptions, gridOpt || {}));
						var gHead = pivotGrid.groupHeaders;
						if (gHead.length) {
							for (i = 0; i < gHead.length; i++) {
								if (gHead[i] && gHead[i].groupHeaders.length) {
									jQuery($t).wizGrid('HeadersMerge', gHead[i]);
								}
							}
						}
						if (pivotOpt.frozenStaticCols) {
							jQuery($t).wizGrid("setFrozen");
						}
					}
					if (typeof data === "string") {
						$.ajax($.extend({
							url: data
							, dataType: 'json'
							, success: function(response) {
								pivot($.wizgrid.getAccessor(response, ajaxOpt && ajaxOpt.reader ? ajaxOpt.reader : 'rows'));
							}
						}, ajaxOpt || {}));
					}
					else {
						pivot(data);
					}
				});
			}
		});
		// module begin
		$.wizgrid.extend({
			setSubGrid: function() {
				return this.each(function() {
					var $t = this
						, cm, i, suboptions = {
							plusicon: "ui-icon-plus"
							, minusicon: "ui-icon-minus"
							, openicon: "ui-icon-carat-1-sw"
							, expandOnLoad: false
							, delayOnLoad: 50
							, selectOnExpand: false
							, selectOnCollapse: false
							, reloadOnExpand: true
						};
					$t.p.subGridOptions = $.extend(suboptions, $t.p.subGridOptions || {});
					$t.p.Names.unshift("");
					$t.p.Columns.unshift({
						name: 'subgrid'
						, width: $.wizgrid.cell_width ? $t.p.subGridWidth + $t.p.cellLayout : $t.p.subGridWidth
						, sortable: false
						, resizable: false
						, hidedlg: true
						, search: false
						, fixed: true
					});
					cm = $t.p.subGridModel;
					if (cm[0]) {
						cm[0].align = $.extend([], cm[0].align || []);
						for (i = 0; i < cm[0].name.length; i++) {
							cm[0].align[i] = cm[0].align[i] || 'left';
						}
					}
				});
			}
			, addSubGridCell: function(pos, iRow) {
				var prp = ''
					, ic, sid;
				this.each(function() {
					prp = this.formatCol(pos, iRow);
					sid = this.p.id;
					ic = this.p.subGridOptions.plusicon;
				});
				return "<td role=\"gridcell\" aria-describedby=\"" + sid +
					"_subgrid\" class=\"ui-sgcollapsed sgcollapsed\" " + prp +
					"><a style='cursor:pointer;'><span class='ui-icon " + ic + "'></span></a></td>";
			}
			, addSubGrid: function(pos, sind) {
				return this.each(function() {
					var ts = this;
					if (!ts.grid) {
						return;
					}
					// -------------------------
					var subGridCell = function(trdiv, cell, pos) {
						var tddiv = $("<td align='" + ts.p.subGridModel[0].align[pos] + "'></td>").html(cell);
						$(trdiv).append(tddiv);
					};
					var subGridXml = function(sjxml, sbid) {
						var tddiv, i, sgmap, dummy = $(
								"<table cellspacing='0' cellpadding='0' border='0'><tbody></tbody></table>")
							, trdiv = $("<tr></tr>");
						for (i = 0; i < ts.p.subGridModel[0].name.length; i++) {
							tddiv = $("<th class='ui-state-default-grid ui-th-subgrid ui-th-column ui-th-" + ts.p.direction +
								"'></th>");
							$(tddiv).html(ts.p.subGridModel[0].name[i]);
							$(tddiv).width(ts.p.subGridModel[0].width[i]);
							$(trdiv).append(tddiv);
						}
						$(dummy).append(trdiv);
						if (sjxml) {
							sgmap = ts.p.xmlReader.subgrid;
							$(sgmap.root + " " + sgmap.row, sjxml).each(function() {
								trdiv = $("<tr class='ui-widget-content-grid ui-subtblcell'></tr>");
								if (sgmap.repeatitems === true) {
									$(sgmap.cell, this).each(function(i) {
										subGridCell(trdiv, $(this).text() || '&#160;', i);
									});
								}
								else {
									var f = ts.p.subGridModel[0].mapping || ts.p.subGridModel[0].name;
									if (f) {
										for (i = 0; i < f.length; i++) {
											subGridCell(trdiv, $(f[i], this).text() || '&#160;', i);
										}
									}
								}
								$(dummy).append(trdiv);
							});
						}
						var pID = $("table:first", ts.grid.bDiv).attr("id") + "_";
						$("#" + $.wizgrid.wizID(pID + sbid)).append(dummy);
						ts.grid.hDiv.loading = false;
						$("#load_" + $.wizgrid.wizID(ts.p.id)).hide();
						return false;
					};
					var subGridJson = function(sjxml, sbid) {
						var tddiv, result, i, cur, sgmap, j, dummy = $(
								"<table cellspacing='0' cellpadding='0' border='0'><tbody></tbody></table>")
							, trdiv = $("<tr></tr>");
						for (i = 0; i < ts.p.subGridModel[0].name.length; i++) {
							tddiv = $("<th class='ui-state-default-grid ui-th-subgrid ui-th-column ui-th-" + ts.p.direction +
								"'></th>");
							$(tddiv).html(ts.p.subGridModel[0].name[i]);
							$(tddiv).width(ts.p.subGridModel[0].width[i]);
							$(trdiv).append(tddiv);
						}
						$(dummy).append(trdiv);
						if (sjxml) {
							sgmap = ts.p.jsonReader.subgrid;
							result = $.wizgrid.getAccessor(sjxml, sgmap.root);
							if (result !== undefined) {
								for (i = 0; i < result.length; i++) {
									cur = result[i];
									trdiv = $("<tr class='ui-widget-content-grid ui-subtblcell'></tr>");
									if (sgmap.repeatitems === true) {
										if (sgmap.cell) {
											cur = cur[sgmap.cell];
										}
										for (j = 0; j < cur.length; j++) {
											subGridCell(trdiv, cur[j] || '&#160;', j);
										}
									}
									else {
										var f = ts.p.subGridModel[0].mapping || ts.p.subGridModel[0].name;
										if (f.length) {
											for (j = 0; j < f.length; j++) {
												subGridCell(trdiv, cur[f[j]] || '&#160;', j);
											}
										}
									}
									$(dummy).append(trdiv);
								}
							}
						}
						var pID = $("table:first", ts.grid.bDiv).attr("id") + "_";
						$("#" + $.wizgrid.wizID(pID + sbid)).append(dummy);
						ts.grid.hDiv.loading = false;
						$("#load_" + $.wizgrid.wizID(ts.p.id)).hide();
						return false;
					};
					var populatesubgrid = function(rd) {
						var sid, dp, i, j;
						sid = $(rd).attr("id");
						dp = {
							nd_: (new Date().getTime())
						};
						dp[ts.p.prmNames.subgridid] = sid;
						if (!ts.p.subGridModel[0]) {
							return false;
						}
						if (ts.p.subGridModel[0].params) {
							for (j = 0; j < ts.p.subGridModel[0].params.length; j++) {
								for (i = 0; i < ts.p.Columns.length; i++) {
									if (ts.p.Columns[i].name === ts.p.subGridModel[0].params[j]) {
										dp[ts.p.Columns[i].name] = $("td:eq(" + i + ")", rd).text().replace(/\&#160\;/ig, '');
									}
								}
							}
						}
						if (!ts.grid.hDiv.loading) {
							ts.grid.hDiv.loading = true;
							$("#load_" + $.wizgrid.wizID(ts.p.id)).show();
							if (!ts.p.subgridtype) {
								ts.p.subgridtype = ts.p.datatype;
							}
							if ($.isFunction(ts.p.subgridtype)) {
								ts.p.subgridtype.call(ts, dp);
							}
							else {
								ts.p.subgridtype = ts.p.subgridtype.toLowerCase();
							}
							switch (ts.p.subgridtype) {
								case "xml":
								case "json":
									$.ajax($.extend({
										type: ts.p.mtype
										, url: $.isFunction(ts.p.subGridUrl) ? ts.p.subGridUrl.call(ts, dp) : ts.p.subGridUrl
										, dataType: ts.p.subgridtype
										, data: $.isFunction(ts.p.serializeSubGridData) ? ts.p.serializeSubGridData.call(ts, dp) : dp
										, complete: function(sxml) {
											if (ts.p.subgridtype === "xml") {
												subGridXml(sxml.responseXML, sid);
											}
											else {
												subGridJson($.wizgrid.parse(sxml.responseText), sid);
											}
											sxml = null;
										}
									}, $.wizgrid.ajaxOptions, ts.p.ajaxSubgridOptions || {}));
									break;
							}
						}
						return false;
					};
					var _id, pID, atd, nhc = 0
						, bfsc, $r;
					$.each(ts.p.Columns, function() {
						if (this.hidden === true || this.name === 'rn' || this.name === 'cb') {
							nhc++;
						}
					});
					var len = ts.rows.length
						, i = 1;
					if (sind !== undefined && sind > 0) {
						i = sind;
						len = sind + 1;
					}
					while (i < len) {
						if ($(ts.rows[i]).hasClass('wizgrow')) {
							if (ts.p.scroll) {
								$(ts.rows[i].cells[pos]).unbind('click');
							}
							$(ts.rows[i].cells[pos]).bind('click', function() {
								var tr = $(this).parent("tr")[0];
								pID = ts.p.id;
								_id = tr.id;
								$r = $("#" + pID + "_" + _id + "_expandedContent");
								if ($(this).hasClass("sgcollapsed")) {
									bfsc = $(ts).triggerHandler("wizGridSubGridBeforeExpand", [
										pID + "_" + _id
										, _id
									]);
									bfsc = (bfsc === false || bfsc === 'stop') ? false : true;
									if (bfsc && $.isFunction(ts.p.subGridBeforeExpand)) {
										bfsc = ts.p.subGridBeforeExpand.call(ts, pID + "_" + _id, _id);
									}
									if (bfsc === false) {
										return false;
									}
									if (ts.p.subGridOptions.reloadOnExpand === true || (ts.p.subGridOptions.reloadOnExpand ===
											false && !$r.hasClass('ui-subgrid'))) {
										atd = pos >= 1 ? "<td colspan='" + pos + "'>&#160;</td>" : "";
										$(tr).after("<tr role='row' id='" + pID + "_" + _id + "_expandedContent" +
											"' class='ui-subgrid ui-sg-expanded'>" + atd +
											"<td class='ui-widget-content-grid subgrid-cell'><span class='ui-icon " + ts.p.subGridOptions
											.openicon + "'></span></td><td colspan='" + parseInt(ts.p.Names.length - 1 - nhc, 10) +
											"' class='ui-widget-content-grid subgrid-data'><div id=" + pID + "_" + _id +
											" class='tablediv'></div></td></tr>");
										$(ts).triggerHandler("wizGridSubGridRowExpanded", [
											pID + "_" + _id
											, _id
										]);
										if ($.isFunction(ts.p.subGridRowExpanded)) {
											ts.p.subGridRowExpanded.call(ts, pID + "_" + _id, _id);
										}
										else {
											populatesubgrid(tr);
										}
									}
									else {
										$r.show().removeClass("ui-sg-collapsed").addClass("ui-sg-expanded");
									}
									$(this).html("<a style='cursor:pointer;'><span class='ui-icon " + ts.p.subGridOptions.minusicon +
										"'></span></a>").removeClass("sgcollapsed").addClass("sgexpanded");
									if (ts.p.subGridOptions.selectOnExpand) {
										$(ts).wizGrid('setSelection', _id);
									}
								}
								else if ($(this).hasClass("sgexpanded")) {
									bfsc = $(ts).triggerHandler("wizGridSubGridRowColapsed", [
										pID + "_" + _id
										, _id
									]);
									bfsc = (bfsc === false || bfsc === 'stop') ? false : true;
									if (bfsc && $.isFunction(ts.p.subGridRowColapsed)) {
										bfsc = ts.p.subGridRowColapsed.call(ts, pID + "_" + _id, _id);
									}
									if (bfsc === false) {
										return false;
									}
									if (ts.p.subGridOptions.reloadOnExpand === true) {
										$r.remove(".ui-subgrid");
									}
									else if ($r.hasClass('ui-subgrid')) { // incase
										// of
										// dynamic
										// deleting
										$r.hide().addClass("ui-sg-collapsed").removeClass("ui-sg-expanded");;
									}
									$(this).html("<a style='cursor:pointer;'><span class='ui-icon " + ts.p.subGridOptions.plusicon +
										"'></span></a>").removeClass("sgexpanded").addClass("sgcollapsed");
									if (ts.p.subGridOptions.selectOnCollapse) {
										$(ts).wizGrid('setSelection', _id);
									}
								}
								return false;
							});
						}
						i++;
					}
					if (ts.p.subGridOptions.expandOnLoad === true) {
						$(ts.rows).filter('.wizgrow').each(function(index, row) {
							$(row.cells[0]).click();
						});
					}
					ts.subGridXml = function(xml, sid) {
						subGridXml(xml, sid);
					};
					ts.subGridJson = function(json, sid) {
						subGridJson(json, sid);
					};
				});
			}
			, expandSubGridRow: function(rowid) {
				return this.each(function() {
					var $t = this;
					if (!$t.grid && !rowid) {
						return;
					}
					if ($t.p.subGrid === true) {
						var rc = $(this).wizGrid("getInd", rowid, true);
						if (rc) {
							var sgc = $("td.sgcollapsed", rc)[0];
							if (sgc) {
								$(sgc).trigger("click");
							}
						}
					}
				});
			}
			, collapseSubGridRow: function(rowid) {
				return this.each(function() {
					var $t = this;
					if (!$t.grid && !rowid) {
						return;
					}
					if ($t.p.subGrid === true) {
						var rc = $(this).wizGrid("getInd", rowid, true);
						if (rc) {
							var sgc = $("td.sgexpanded", rc)[0];
							if (sgc) {
								$(sgc).trigger("click");
							}
						}
					}
				});
			}
			, toggleSubGridRow: function(rowid) {
				return this.each(function() {
					var $t = this;
					if (!$t.grid && !rowid) {
						return;
					}
					if ($t.p.subGrid === true) {
						var rc = $(this).wizGrid("getInd", rowid, true);
						if (rc) {
							var sgc = $("td.sgcollapsed", rc)[0];
							if (sgc) {
								$(sgc).trigger("click");
							}
							else {
								sgc = $("td.sgexpanded", rc)[0];
								if (sgc) {
									$(sgc).trigger("click");
								}
							}
						}
					}
				});
			}
		});
		// module begin
		$.wizgrid.extend({
			setTreeNode: function(i, len) {
				return this.each(function() {
					var $t = this;
					if (!$t.grid || !$t.p.treeGrid) {
						return;
					}
					var expCol = $t.p.expColInd
						, expanded = $t.p.treeReader.expanded_field
						, isLeaf = $t.p.treeReader.leaf_field
						, level = $t.p.treeReader.level_field
						, icon = $t.p.treeReader.icon_field
						, loaded = $t.p.treeReader.loaded
						, lft, rgt, curLevel, ident, lftpos, twrap, ldat, lf;
					while (i < len) {
						var ind = $.wizgrid.stripPref($t.p.idPrefix, $t.rows[i].id)
							, dind = $t.p._index[ind]
							, expan;
						ldat = $t.p.data[dind];
						// $t.rows[i].level = ldat[level];
						if ($t.p.treeGridModel === 'nested') {
							if (!ldat[isLeaf]) {
								lft = parseInt(ldat[$t.p.treeReader.left_field], 10);
								rgt = parseInt(ldat[$t.p.treeReader.right_field], 10);
								// NS Model
								ldat[isLeaf] = (rgt === lft + 1) ? 'true' : 'false';
								$t.rows[i].cells[$t.p._treeleafpos].innerHTML = ldat[isLeaf];
							}
						}
						// else {
						// row.parent_id =
						// rd[$t.p.treeReader.parent_id_field];
						// }
						curLevel = parseInt(ldat[level], 10);
						if ($t.p.tree_root_level === 0) {
							ident = curLevel + 1;
							lftpos = curLevel;
						}
						else {
							ident = curLevel;
							lftpos = curLevel - 1;
						}
						twrap = "<div class='tree-wrap tree-wrap-" + $t.p.direction + "' style='width:" + (ident * 18) +
							"px;'>";
						twrap += "<div style='" + ($t.p.direction === "rtl" ? "right:" : "left:") + (lftpos * 18) +
							"px;' class='ui-icon ";
						if (ldat[loaded] !== undefined) {
							if (ldat[loaded] === "true" || ldat[loaded] === true) {
								ldat[loaded] = true;
							}
							else {
								ldat[loaded] = false;
							}
						}
						if (ldat[isLeaf] === "true" || ldat[isLeaf] === true) {
							twrap += ((ldat[icon] !== undefined && ldat[icon] !== "") ? ldat[icon] : $t.p.treeIcons.leaf) +
								" tree-leaf treeclick";
							ldat[isLeaf] = true;
							lf = "leaf";
						}
						else {
							ldat[isLeaf] = false;
							lf = "";
						}
						ldat[expanded] = ((ldat[expanded] === "true" || ldat[expanded] === true) ? true : false) && (ldat[
							loaded] || ldat[loaded] === undefined);
						if (ldat[expanded] === false) {
							twrap += ((ldat[isLeaf] === true) ? "'" : $t.p.treeIcons.plus + " tree-plus treeclick'");
						}
						else {
							twrap += ((ldat[isLeaf] === true) ? "'" : $t.p.treeIcons.minus + " tree-minus treeclick'");
						}
						twrap += "></div></div>";
						$($t.rows[i].cells[expCol]).wrapInner("<span class='cell-wrapper" + lf + "'></span>").prepend(
							twrap);
						if (curLevel !== parseInt($t.p.tree_root_level, 10)) {
							// var pn =
							// $($t).wizGrid('getNodeParent',ldat);
							// expan = pn &&
							// pn.hasOwnProperty(expanded) ?
							// pn[expanded] : true;
							expan = $($t).wizGrid('isVisibleNode', ldat); // overhead
							if (!expan) {
								$($t.rows[i]).css("display", "none");
							}
						}
						$($t.rows[i].cells[expCol]).find("div.treeclick").bind("click", function(e) {
							var target = e.target || e.srcElement
								, ind2 = $.wizgrid.stripPref($t.p.idPrefix, $(target, $t.rows).closest("tr.wizgrow")[0].id)
								, pos = $t.p._index[ind2];
							if (!$t.p.data[pos][isLeaf]) {
								if ($t.p.data[pos][expanded]) {
									$($t).wizGrid("collapseRow", $t.p.data[pos]);
									$($t).wizGrid("collapseNode", $t.p.data[pos]);
								}
								else {
									$($t).wizGrid("expandRow", $t.p.data[pos]);
									$($t).wizGrid("expandNode", $t.p.data[pos]);
								}
							}
							return false;
						});
						if ($t.p.ExpandColClick === true) {
							$($t.rows[i].cells[expCol]).find("span.cell-wrapper").css("cursor", "pointer").bind("click"
								, function(e) {
									var target = e.target || e.srcElement
										, ind2 = $.wizgrid.stripPref($t.p.idPrefix, $(target, $t.rows).closest("tr.wizgrow")[0].id)
										, pos = $t.p._index[ind2];
									if (!$t.p.data[pos][isLeaf]) {
										if ($t.p.data[pos][expanded]) {
											$($t).wizGrid("collapseRow", $t.p.data[pos]);
											$($t).wizGrid("collapseNode", $t.p.data[pos]);
										}
										else {
											$($t).wizGrid("expandRow", $t.p.data[pos]);
											$($t).wizGrid("expandNode", $t.p.data[pos]);
										}
									}
									$($t).wizGrid("setSelection", ind2);
									return false;
								});
						}
						i++;
					}
				});
			}
			, setTreeGrid: function() {
				return this.each(function() {
					var $t = this
						, i = 0
						, pico, ecol = false
						, nm, key, tkey, dupcols = [];
					if (!$t.p.treeGrid) {
						return;
					}
					if (!$t.p.treedatatype) {
						$.extend($t.p, {
							treedatatype: $t.p.datatype
						});
					}
					if ($t.p.loadonce) {
						$t.p.treedatatype = 'local';
					}
					$t.p.subGrid = false;
					$t.p.altRows = false;
					$t.p.pgbuttons = false;
					$t.p.pginput = false;
					$t.p.gridview = true;
					if ($t.p.rowTotal === null) {
						$t.p.rowNum = 10000;
					}
					$t.p.multiselect = false;
					$t.p.rowList = [];
					$t.p.expColInd = 0;
					pico = 'ui-icon-triangle-1-' + ($t.p.direction === "rtl" ? 'w' : 'e');
					$t.p.treeIcons = $.extend({
						plus: pico
						, minus: 'ui-icon-triangle-1-s'
						, leaf: 'ui-icon-radio-off'
					}, $t.p.treeIcons || {});
					if ($t.p.treeGridModel === 'nested') {
						$t.p.treeReader = $.extend({
							level_field: "level"
							, left_field: "lft"
							, right_field: "rgt"
							, leaf_field: "isLeaf"
							, expanded_field: "expanded"
							, loaded: "loaded"
							, icon_field: "icon"
						}, $t.p.treeReader);
					}
					else if ($t.p.treeGridModel === 'adjacency') {
						$t.p.treeReader = $.extend({
							level_field: "level"
							, parent_id_field: "parent"
							, leaf_field: "isLeaf"
							, expanded_field: "expanded"
							, loaded: "loaded"
							, icon_field: "icon"
						}, $t.p.treeReader);
					}
					for (key in $t.p.Columns) {
						if ($t.p.Columns.hasOwnProperty(key)) {
							nm = $t.p.Columns[key].name;
							if (nm === $t.p.ExpandColumn && !ecol) {
								ecol = true;
								$t.p.expColInd = i;
							}
							i++;
							//
							for (tkey in $t.p.treeReader) {
								if ($t.p.treeReader.hasOwnProperty(tkey) && $t.p.treeReader[tkey] === nm) {
									dupcols.push(nm);
								}
							}
						}
					}
					$.each($t.p.treeReader, function(j, n) {
						if (n && $.inArray(n, dupcols) === -1) {
							if (j === 'leaf_field') {
								$t.p._treeleafpos = i;
							}
							i++;
							$t.p.Names.push(n);
							$t.p.Columns.push({
								name: n
								, width: 1
								, hidden: true
								, sortable: false
								, resizable: false
								, hidedlg: true
								, editable: true
								, search: false
							});
						}
					});
				});
			}
			, expandRow: function(record) {
				this.each(function() {
					var $t = this;
					if (!$t.grid || !$t.p.treeGrid) {
						return;
					}
					var childern = $($t).wizGrid("getNodeChildren", record)
						, // if
						// ($($t).wizGrid("isVisibleNode",record))
						// {
						expanded = $t.p.treeReader.expanded_field;
					$(childern).each(function() {
						var id = $t.p.idPrefix + $.wizgrid.getAccessor(this, $t.p.localReader.id);
						$($($t).wizGrid('getGridRowById', id)).css("display", "");
						if (this[expanded]) {
							$($t).wizGrid("expandRow", this);
						}
					});
					// }
				});
			}
			, collapseRow: function(record) {
				this.each(function() {
					var $t = this;
					if (!$t.grid || !$t.p.treeGrid) {
						return;
					}
					var childern = $($t).wizGrid("getNodeChildren", record)
						, expanded = $t.p.treeReader.expanded_field;
					$(childern).each(function() {
						var id = $t.p.idPrefix + $.wizgrid.getAccessor(this, $t.p.localReader.id);
						$($($t).wizGrid('getGridRowById', id)).css("display", "none");
						if (this[expanded]) {
							$($t).wizGrid("collapseRow", this);
						}
					});
				});
			}
			, // NS ,adjacency models
			getRootNodes: function(currentview) {
				var result = [];
				this.each(function() {
					var $t = this
						, level, parent_id, view;
					if (!$t.grid || !$t.p.treeGrid) {
						return;
					}
					if (typeof currentview !== 'boolean') {
						currentview = false;
					}
					if (currentview) {
						view = $($t).wizGrid('getRowData', null, true);
					}
					else {
						view = $t.p.data;
					}
					switch ($t.p.treeGridModel) {
						case 'nested':
							level = $t.p.treeReader.level_field;
							$(view).each(function() {
								if (parseInt(this[level], 10) === parseInt($t.p.tree_root_level, 10)) {
									if (currentview) {
										result.push($t.p.data[$t.p._index[this[$t.p.keyName]]]);
									}
									else {
										result.push(this);
									}
								}
							});
							break;
						case 'adjacency':
							parent_id = $t.p.treeReader.parent_id_field;
							$(view).each(function() {
								if (this[parent_id] === null || String(this[parent_id]).toLowerCase() === "null") {
									if (currentview) {
										result.push($t.p.data[$t.p._index[this[$t.p.keyName]]]);
									}
									else {
										result.push(this);
									}
								}
							});
							break;
					}
				});
				return result;
			}
			, getNodeDepth: function(rc) {
				var ret = null;
				this.each(function() {
					if (!this.grid || !this.p.treeGrid) {
						return;
					}
					var $t = this;
					switch ($t.p.treeGridModel) {
						case 'nested':
							var level = $t.p.treeReader.level_field;
							ret = parseInt(rc[level], 10) - parseInt($t.p.tree_root_level, 10);
							break;
						case 'adjacency':
							ret = $($t).wizGrid("getNodeAncestors", rc).length;
							break;
					}
				});
				return ret;
			}
			, getNodeParent: function(rc) {
				var result = null;
				this.each(function() {
					var $t = this;
					if (!$t.grid || !$t.p.treeGrid) {
						return;
					}
					switch ($t.p.treeGridModel) {
						case 'nested':
							var lftc = $t.p.treeReader.left_field
								, rgtc = $t.p.treeReader.right_field
								, levelc = $t.p.treeReader.level_field
								, lft = parseInt(rc[lftc], 10)
								, rgt = parseInt(rc[rgtc], 10)
								, level = parseInt(rc[levelc], 10);
							$(this.p.data).each(function() {
								if (parseInt(this[levelc], 10) === level - 1 && parseInt(this[lftc], 10) < lft && parseInt(
										this[rgtc], 10) > rgt) {
									result = this;
									return false;
								}
							});
							break;
						case 'adjacency':
							var parent_id = $t.p.treeReader.parent_id_field
								, dtid = $t.p.localReader.id
								, ind = rc[dtid]
								, pos = $t.p._index[ind];
							while (pos--) {
								if ($t.p.data[pos][dtid] === $.wizgrid.stripPref($t.p.idPrefix, rc[parent_id])) {
									result = $t.p.data[pos];
									break;
								}
							}
							break;
					}
				});
				return result;
			}
			, getNodeChildren: function(rc) {
				var result = [];
				this.each(function() {
					var $t = this;
					if (!$t.grid || !$t.p.treeGrid) {
						return;
					}
					switch ($t.p.treeGridModel) {
						case 'nested':
							var lftc = $t.p.treeReader.left_field
								, rgtc = $t.p.treeReader.right_field
								, levelc = $t.p.treeReader.level_field
								, lft = parseInt(rc[lftc], 10)
								, rgt = parseInt(rc[rgtc], 10)
								, level = parseInt(rc[levelc], 10);
							$(this.p.data).each(function() {
								if (parseInt(this[levelc], 10) === level + 1 && parseInt(this[lftc], 10) > lft && parseInt(
										this[rgtc], 10) < rgt) {
									result.push(this);
								}
							});
							break;
						case 'adjacency':
							var parent_id = $t.p.treeReader.parent_id_field
								, dtid = $t.p.localReader.id;
							$(this.p.data).each(function() {
								if (this[parent_id] == $.wizgrid.stripPref($t.p.idPrefix, rc[dtid])) {
									result.push(this);
								}
							});
							break;
					}
				});
				return result;
			}
			, getFullTreeNode: function(rc, expand) {
				var result = [];
				this.each(function() {
					var $t = this
						, len, expanded = $t.p.treeReader.expanded_field;
					if (!$t.grid || !$t.p.treeGrid) {
						return;
					}
					if (expand == null || typeof expand !== 'boolean') {
						expand = false;
					}
					switch ($t.p.treeGridModel) {
						case 'nested':
							var lftc = $t.p.treeReader.left_field
								, rgtc = $t.p.treeReader.right_field
								, levelc = $t.p.treeReader.level_field
								, lft = parseInt(rc[lftc], 10)
								, rgt = parseInt(rc[rgtc], 10)
								, level = parseInt(rc[levelc], 10);
							$(this.p.data).each(function() {
								if (parseInt(this[levelc], 10) >= level && parseInt(this[lftc], 10) >= lft && parseInt(this[
										lftc], 10) <= rgt) {
									if (expand) {
										this[expanded] = true;
									}
									result.push(this);
								}
							});
							break;
						case 'adjacency':
							if (rc) {
								result.push(rc);
								var parent_id = $t.p.treeReader.parent_id_field
									, dtid = $t.p.localReader.id;
								$(this.p.data).each(function(i) {
									len = result.length;
									for (i = 0; i < len; i++) {
										if ($.wizgrid.stripPref($t.p.idPrefix, result[i][dtid]) === this[parent_id]) {
											if (expand) {
												this[expanded] = true;
											}
											result.push(this);
											break;
										}
									}
								});
							}
							break;
					}
				});
				return result;
			}
			, // End NS, adjacency Model
			getNodeAncestors: function(rc) {
				var ancestors = [];
				this.each(function() {
					if (!this.grid || !this.p.treeGrid) {
						return;
					}
					var parent = $(this).wizGrid("getNodeParent", rc);
					while (parent) {
						ancestors.push(parent);
						parent = $(this).wizGrid("getNodeParent", parent);
					}
				});
				return ancestors;
			}
			, isVisibleNode: function(rc) {
				var result = true;
				this.each(function() {
					var $t = this;
					if (!$t.grid || !$t.p.treeGrid) {
						return;
					}
					var ancestors = $($t).wizGrid("getNodeAncestors", rc)
						, expanded = $t.p.treeReader.expanded_field;
					$(ancestors).each(function() {
						result = result && this[expanded];
						if (!result) {
							return false;
						}
					});
				});
				return result;
			}
			, isNodeLoaded: function(rc) {
				var result;
				this.each(function() {
					var $t = this;
					if (!$t.grid || !$t.p.treeGrid) {
						return;
					}
					var isLeaf = $t.p.treeReader.leaf_field
						, loaded = $t.p.treeReader.loaded;
					if (rc !== undefined) {
						if (rc[loaded] !== undefined) {
							result = rc[loaded];
						}
						else if (rc[isLeaf] || $($t).wizGrid("getNodeChildren", rc).length > 0) {
							result = true;
						}
						else {
							result = false;
						}
					}
					else {
						result = false;
					}
				});
				return result;
			}
			, expandNode: function(rc) {
				return this.each(function() {
					if (!this.grid || !this.p.treeGrid) {
						return;
					}
					var expanded = this.p.treeReader.expanded_field
						, parent = this.p.treeReader.parent_id_field
						, loaded = this.p.treeReader.loaded
						, level = this.p.treeReader.level_field
						, lft = this.p.treeReader.left_field
						, rgt = this.p.treeReader.right_field;
					if (!rc[expanded]) {
						var id = $.wizgrid.getAccessor(rc, this.p.localReader.id);
						var rc1 = $("#" + this.p.idPrefix + $.wizgrid.wizID(id), this.grid.bDiv)[0];
						var position = this.p._index[id];
						if ($(this).wizGrid("isNodeLoaded", this.p.data[position])) {
							rc[expanded] = true;
							$("div.treeclick", rc1).removeClass(this.p.treeIcons.plus + " tree-plus").addClass(this.p.treeIcons
								.minus + " tree-minus");
						}
						else if (!this.grid.hDiv.loading) {
							rc[expanded] = true;
							$("div.treeclick", rc1).removeClass(this.p.treeIcons.plus + " tree-plus").addClass(this.p.treeIcons
								.minus + " tree-minus");
							this.p.treeANode = rc1.rowIndex;
							this.p.datatype = this.p.treedatatype;
							if (this.p.treeGridModel === 'nested') {
								$(this).wizGrid("setGridParam", {
									postData: {
										nodeid: id
										, n_left: rc[lft]
										, n_right: rc[rgt]
										, n_level: rc[level]
									}
								});
							}
							else {
								$(this).wizGrid("setGridParam", {
									postData: {
										nodeid: id
										, parentid: rc[parent]
										, n_level: rc[level]
									}
								});
							}
							$(this).trigger("reloadGrid");
							rc[loaded] = true;
							if (this.p.treeGridModel === 'nested') {
								$(this).wizGrid("setGridParam", {
									postData: {
										nodeid: ''
										, n_left: ''
										, n_right: ''
										, n_level: ''
									}
								});
							}
							else {
								$(this).wizGrid("setGridParam", {
									postData: {
										nodeid: ''
										, parentid: ''
										, n_level: ''
									}
								});
							}
						}
					}
				});
			}
			, collapseNode: function(rc) {
				return this.each(function() {
					if (!this.grid || !this.p.treeGrid) {
						return;
					}
					var expanded = this.p.treeReader.expanded_field;
					if (rc[expanded]) {
						rc[expanded] = false;
						var id = $.wizgrid.getAccessor(rc, this.p.localReader.id);
						var rc1 = $("#" + this.p.idPrefix + $.wizgrid.wizID(id), this.grid.bDiv)[0];
						$("div.treeclick", rc1).removeClass(this.p.treeIcons.minus + " tree-minus").addClass(this.p.treeIcons
							.plus + " tree-plus");
					}
				});
			}
			, SortTree: function(sortname, newDir, st, datefmt) {
				return this.each(function() {
					if (!this.grid || !this.p.treeGrid) {
						return;
					}
					var i, len, rec, records = []
						, $t = this
						, query, roots, rt = $(this).wizGrid("getRootNodes", $t.p.search);
					// Sorting roots
					query = $.wizgrid.from.call(this, rt);
					query.orderBy(sortname, newDir, st, datefmt);
					roots = query.select();
					// Sorting children
					for (i = 0, len = roots.length; i < len; i++) {
						rec = roots[i];
						records.push(rec);
						$(this).wizGrid("collectChildrenSortTree", records, rec, sortname, newDir, st, datefmt);
					}
					$.each(records, function(index) {
						var id = $.wizgrid.getAccessor(this, $t.p.localReader.id);
						$('#' + $.wizgrid.wizID($t.p.id) + ' tbody tr:eq(' + index + ')').after($('tr#' + $.wizgrid.wizID(
							id), $t.grid.bDiv));
					});
					query = null;
					roots = null;
					records = null;
				});
			}
			, searchTree: function(recs) {
				var i, len = recs.length || 0
					, res = []
					, lid, roots = []
					, result = []
					, tid;
				this.each(function() {
					if (!this.grid || !this.p.treeGrid) {
						return;
					}
					if (len) {
						lid = this.p.localReader.id;
						for (i = 0; i < len; i++) {
							res = $(this).wizGrid('getNodeAncestors', recs[i]);
							if (!res.length) { // is root or leaf
								// root
								res.push(recs[i]);
							}
							tid = res[res.length - 1][lid]; // root
							// node
							if ($.inArray(tid, roots) !== -1) { // ignore
								// repeated
								continue;
							}
							else {
								roots.push(tid);
							}
							res = $(this).wizGrid('getFullTreeNode', res[res.length - 1], true);
							result = result.concat(res);
						}
					}
				});
				return result;
			}
			, collectChildrenSortTree: function(records, rec, sortname, newDir, st, datefmt) {
				return this.each(function() {
					if (!this.grid || !this.p.treeGrid) {
						return;
					}
					var i, len, child, ch, query, children;
					ch = $(this).wizGrid("getNodeChildren", rec);
					query = $.wizgrid.from.call(this, ch);
					query.orderBy(sortname, newDir, st, datefmt);
					children = query.select();
					for (i = 0, len = children.length; i < len; i++) {
						child = children[i];
						records.push(child);
						$(this).wizGrid("collectChildrenSortTree", records, child, sortname, newDir, st, datefmt);
					}
				});
			}
			, // experimental
			setTreeRow: function(rowid, data) {
				var success = false;
				this.each(function() {
					var t = this;
					if (!t.grid || !t.p.treeGrid) {
						return;
					}
					success = $(t).wizGrid("setRowData", rowid, data);
				});
				return success;
			}
			, delTreeNode: function(rowid) {
				return this.each(function() {
					var $t = this
						, rid = $t.p.localReader.id
						, i, left = $t.p.treeReader.left_field
						, right = $t.p.treeReader.right_field
						, myright, width, res, key;
					if (!$t.grid || !$t.p.treeGrid) {
						return;
					}
					var rc = $t.p._index[rowid];
					if (rc !== undefined) {
						// nested
						myright = parseInt($t.p.data[rc][right], 10);
						width = myright - parseInt($t.p.data[rc][left], 10) + 1;
						var dr = $($t).wizGrid("getFullTreeNode", $t.p.data[rc]);
						if (dr.length > 0) {
							for (i = 0; i < dr.length; i++) {
								$($t).wizGrid("delRowData", dr[i][rid]);
							}
						}
						if ($t.p.treeGridModel === "nested") {
							// ToDo - update grid data
							res = $.wizgrid.from.call($t, $t.p.data).greater(left, myright, {
								stype: 'integer'
							}).select();
							if (res.length) {
								for (key in res) {
									if (res.hasOwnProperty(key)) {
										res[key][left] = parseInt(res[key][left], 10) - width;
									}
								}
							}
							res = $.wizgrid.from.call($t, $t.p.data).greater(right, myright, {
								stype: 'integer'
							}).select();
							if (res.length) {
								for (key in res) {
									if (res.hasOwnProperty(key)) {
										res[key][right] = parseInt(res[key][right], 10) - width;
									}
								}
							}
						}
					}
				});
			}
			, addChildNode: function(nodeid, parentid, data, expandData) {
				// return this.each(function(){
				var $t = this[0];
				if (data) {
					// we suppose tha the id is autoincremet and
					var expanded = $t.p.treeReader.expanded_field
						, isLeaf = $t.p.treeReader.leaf_field
						, level = $t.p.treeReader.level_field
						, // icon = $t.p.treeReader.icon_field,
						parent = $t.p.treeReader.parent_id_field
						, left = $t.p.treeReader.left_field
						, right = $t.p.treeReader.right_field
						, loaded = $t.p.treeReader.loaded
						, method, parentindex, parentdata, parentlevel, i, len, max = 0
						, rowind = parentid
						, leaf, maxright;
					if (expandData === undefined) {
						expandData = false;
					}
					if (nodeid == null) {
						i = $t.p.data.length - 1;
						if (i >= 0) {
							while (i >= 0) {
								max = Math.max(max, parseInt($t.p.data[i][$t.p.localReader.id], 10));
								i--;
							}
						}
						nodeid = max + 1;
					}
					var prow = $($t).wizGrid('getInd', parentid);
					leaf = false;
					// if not a parent we assume root
					if (parentid === undefined || parentid === null || parentid === "") {
						parentid = null;
						rowind = null;
						method = 'last';
						parentlevel = $t.p.tree_root_level;
						i = $t.p.data.length + 1;
					}
					else {
						method = 'after';
						parentindex = $t.p._index[parentid];
						parentdata = $t.p.data[parentindex];
						parentid = parentdata[$t.p.localReader.id];
						parentlevel = parseInt(parentdata[level], 10) + 1;
						var childs = $($t).wizGrid('getFullTreeNode', parentdata);
						// if there are child nodes get the last
						// index of it
						if (childs.length) {
							i = childs[childs.length - 1][$t.p.localReader.id];
							rowind = i;
							i = $($t).wizGrid('getInd', rowind) + 1;
						}
						else {
							i = $($t).wizGrid('getInd', parentid) + 1;
						}
						// if the node is leaf
						if (parentdata[isLeaf]) {
							leaf = true;
							parentdata[expanded] = true;
							// var prow = $($t).wizGrid('getInd',
							// parentid);
							$($t.rows[prow]).find("span.cell-wrapperleaf").removeClass("cell-wrapperleaf").addClass(
								"cell-wrapper").end().find("div.tree-leaf").removeClass($t.p.treeIcons.leaf + " tree-leaf").addClass(
								$t.p.treeIcons.minus + " tree-minus");
							$t.p.data[parentindex][isLeaf] = false;
							parentdata[loaded] = true;
						}
					}
					len = i + 1;
					if (data[expanded] === undefined) {
						data[expanded] = false;
					}
					if (data[loaded] === undefined) {
						data[loaded] = false;
					}
					data[level] = parentlevel;
					if (data[isLeaf] === undefined) {
						data[isLeaf] = true;
					}
					if ($t.p.treeGridModel === "adjacency") {
						data[parent] = parentid;
					}
					if ($t.p.treeGridModel === "nested") {
						// this method requiere more attention
						var query, res, key;
						// maxright = parseInt(maxright,10);
						// ToDo - update grid data
						if (parentid !== null) {
							maxright = parseInt(parentdata[right], 10);
							query = $.wizgrid.from.call($t, $t.p.data);
							query = query.greaterOrEquals(right, maxright, {
								stype: 'integer'
							});
							res = query.select();
							if (res.length) {
								for (key in res) {
									if (res.hasOwnProperty(key)) {
										res[key][left] = res[key][left] > maxright ? parseInt(res[key][left], 10) + 2 : res[key][left];
										res[key][right] = res[key][right] >= maxright ? parseInt(res[key][right], 10) + 2 : res[key][
											right
										];
									}
								}
							}
							data[left] = maxright;
							data[right] = maxright + 1;
						}
						else {
							maxright = parseInt($($t).wizGrid('getCol', right, false, 'max'), 10);
							res = $.wizgrid.from.call($t, $t.p.data).greater(left, maxright, {
								stype: 'integer'
							}).select();
							if (res.length) {
								for (key in res) {
									if (res.hasOwnProperty(key)) {
										res[key][left] = parseInt(res[key][left], 10) + 2;
									}
								}
							}
							res = $.wizgrid.from.call($t, $t.p.data).greater(right, maxright, {
								stype: 'integer'
							}).select();
							if (res.length) {
								for (key in res) {
									if (res.hasOwnProperty(key)) {
										res[key][right] = parseInt(res[key][right], 10) + 2;
									}
								}
							}
							data[left] = maxright + 1;
							data[right] = maxright + 2;
						}
					}
					if (parentid === null || $($t).wizGrid("isNodeLoaded", parentdata) || leaf) {
						$($t).wizGrid('addRowData', nodeid, data, method, rowind);
						$($t).wizGrid('setTreeNode', i, len);
					}
					if (parentdata && !parentdata[expanded] && expandData) {
						$($t.rows[prow]).find("div.treeclick").click();
					}
				}
				// });
			}
		});
		// module begin
		$.fn.wizDrag = function(h) {
			return i(this, h, 'd');
		};
		$.fn.wizResize = function(h, ar) {
			return i(this, h, 'r', ar);
		};
		$.wizDnR = {
			dnr: {}
			, e: 0
			, drag: function(v) {
				if (M.k == 'd') {
					E.css({
						left: M.X + v.pageX - M.pX
						, top: M.Y + v.pageY - M.pY
					});
				}
				else {
					E.css({
						width: Math.max(v.pageX - M.pX + M.W, 0)
						, height: Math.max(v.pageY - M.pY + M.H, 0)
					});
					if (M1) {
						E1.css({
							width: Math.max(v.pageX - M1.pX + M1.W, 0)
							, height: Math.max(v.pageY - M1.pY + M1.H, 0)
						});
					}
				}
				return false;
			}
			, stop: function() {
				// E.css('opacity',M.o);
				$(document).unbind('mousemove', J.drag).unbind('mouseup', J.stop);
			}
		};
		var J = $.wizDnR
			, M = J.dnr
			, E = J.e
			, E1, M1, i = function(e, h, k, aR) {
				return e.each(function() {
					h = (h) ? $(h, e) : e;
					h.bind('mousedown', {
						e: e
						, k: k
					}, function(v) {
						var d = v.data
							, p = {};
						E = d.e;
						E1 = aR ? $(aR) : false;
						// attempt utilization of dimensions plugin to fix IE
						// issues
						if (E.css('position') != 'relative') {
							try {
								E.position(p);
							}
							catch (e) {}
						}
						M = {
							X: p.left || f('left') || 0
							, Y: p.top || f('top') || 0
							, W: f('width') || E[0].scrollWidth || 0
							, H: f('height') || E[0].scrollHeight || 0
							, pX: v.pageX
							, pY: v.pageY
							, k: d.k
								// o:E.css('opacity')
						};
						// also resize
						if (E1 && d.k != 'd') {
							M1 = {
								X: p.left || f1('left') || 0
								, Y: p.top || f1('top') || 0
								, W: E1[0].offsetWidth || f1('width') || 0
								, H: E1[0].offsetHeight || f1('height') || 0
								, pX: v.pageX
								, pY: v.pageY
								, k: d.k
							};
						}
						else {
							M1 = false;
						}
						// E.css({opacity:0.8});
						if ($("input.hasDatepicker", E[0])[0]) {
							try {
								$("input.hasDatepicker", E[0]).datepicker('hide');
							}
							catch (dpe) {}
						}
						$(document).mousemove($.wizDnR.drag).mouseup($.wizDnR.stop);
						return false;
					});
				});
			}
			, f = function(k) {
				return parseInt(E.css(k), 10) || false;
			}
			, f1 = function(k) {
				return parseInt(E1.css(k), 10) || false;
			};
		// module begin
		$.fn.wizm = function(o) {
			var p = {
				overlay: 50
				, closeoverlay: true
				, overlayClass: 'wizmOverlay'
				, closeClass: 'wizmClose'
				, trigger: '.wizModal'
				, ajax: F
				, ajaxText: ''
				, target: F
				, modal: F
				, toTop: F
				, onShow: F
				, onHide: F
				, onLoad: F
			};
			return this.each(function() {
				if (this._wizm) {
					return H[this._wizm].c = $.extend({}, H[this._wizm].c, o);
				}
				s++;
				this._wizm = s;
				H[s] = {
					c: $.extend(p, $.wizm.params, o)
					, a: F
					, w: $(this).addClass('wizmID' + s)
					, s: s
				};
				if (p.trigger) {
					$(this).wizmAddTrigger(p.trigger);
				}
			});
		};
		$.fn.wizmAddClose = function(e) {
			return hs(this, e, 'wizmHide');
		};
		$.fn.wizmAddTrigger = function(e) {
			return hs(this, e, 'wizmShow');
		};
		$.fn.wizmShow = function(t) {
			return this.each(function() {
				$.wizm.open(this._wizm, t);
			});
		};
		$.fn.wizmHide = function(t) {
			return this.each(function() {
				$.wizm.close(this._wizm, t);
			});
		};
		$.wizm = {
			hash: {}
			, open: function(s, t) {
				var h = H[s]
					, c = h.c
					, cc = '.' + c.closeClass
					, z = (parseInt(h.w.css('z-index')));
				z = (z > 0) ? z : 3000;
				var o = $('<div></div>').css({
					height: '100%'
					, width: '100%'
					, position: 'fixed'
					, left: 0
					, top: 0
					, 'z-index': z - 1
					, opacity: c.overlay / 100
				});
				if (h.a) {
					return F;
				}
				h.t = t;
				h.a = true;
				h.w.css('z-index', z);
				if (c.modal) {
					if (!A[0]) {
						setTimeout(function() {
							new L('bind');
						}, 1);
					}
					A.push(s);
				}
				else if (c.overlay > 0) {
					if (c.closeoverlay) {
						h.w.wizmAddClose(o);
					}
				}
				else {
					o = F;
				}
				h.o = (o) ? o.addClass(c.overlayClass).prependTo('body') : F;
				if (c.ajax) {
					var r = c.target || h.w
						, u = c.ajax;
					r = (typeof r === 'string') ? $(r, h.w) : $(r);
					u = (u.substr(0, 1) === '@') ? $(t).attr(u.substring(1)) : u;
					r.html(c.ajaxText).load(u, function() {
						if (c.onLoad) {
							c.onLoad.call(this, h);
						}
						if (cc) {
							h.w.wizmAddClose($(cc, h.w));
						}
						e(h);
					});
				}
				else if (cc) {
					h.w.wizmAddClose($(cc, h.w));
				}
				if (c.toTop && h.o) {
					h.w.before('<span id="wizmP' + h.w[0]._wizm + '"></span>').insertAfter(h.o);
				}
				(c.onShow) ? c.onShow(h): h.w.show();
				e(h);
				return F;
			}
			, close: function(s) {
				var h = H[s];
				if (!h.a) {
					return F;
				}
				h.a = F;
				if (A[0]) {
					A.pop();
					if (!A[0]) {
						new L('unbind');
					}
				}
				if (h.c.toTop && h.o) {
					$('#wizmP' + h.w[0]._wizm).after(h.w).remove();
				}
				if (h.c.onHide) {
					h.c.onHide(h);
				}
				else {
					h.w.hide();
					if (h.o) {
						h.o.remove();
					}
				}
				return F;
			}
			, params: {}
		};
		var s = 0
			, H = $.wizm.hash
			, A = []
			, F = false
			, e = function(h) {
				f(h);
			}
			, f = function(h) {
				try {
					$(':input:visible', h.w)[0].focus();
				}
				catch (_) {}
			}
			, L = function(t) {
				$(document)[t]("keypress", m)[t]("keydown", m)[t]("mousedown", m);
			}
			, m = function(e) {
				var h = H[A[A.length - 1]]
					, r = (!$(e.target).parents('.wizmID' + h.s)[0]);
				if (r) {
					$('.wizmID' + h.s).each(function() {
						var $self = $(this)
							, offset = $self.offset();
						if (offset.top <= e.pageY && e.pageY <= offset.top + $self.height() && offset.left <= e.pageX && e.pageX <=
							offset.left + $self.width()) {
							r = false;
							return false;
						}
					});
					f(h);
				}
				return !r;
			}
			, hs = function(w, t, c) {
				return w.each(function() {
					var s = this._wizm;
					$(t).each(function() {
						if (!this[c]) {
							this[c] = [];
							$(this).click(function() {
								for (var i in {
										wizmShow: 1
										, wizmHide: 1
									}) {
									for (var s in this[i]) {
										if (H[this[i][s]]) {
											H[this[i][s]].w[i](this);
										}
									}
								}
								return F;
							});
						}
						this[c].push(s);
					});
				});
			};
		// module begin
		$.fmatter = {};
		// opts can be id:row id for the row, rowdata:the data for the row,
		// colmodel:the column model for this column
		// example {id:1234,}
		$.extend($.fmatter, {
			isBoolean: function(o) {
				return typeof o === 'boolean';
			}
			, isObject: function(o) {
				return (o && (typeof o === 'object' || $.isFunction(o))) || false;
			}
			, isString: function(o) {
				return typeof o === 'string';
			}
			, isNumber: function(o) {
				return typeof o === 'number' && isFinite(o);
			}
			, isValue: function(o) {
				return (this.isObject(o) || this.isString(o) || this.isNumber(o) || this.isBoolean(o));
			}
			, isEmpty: function(o) {
				if (!this.isString(o) && this.isValue(o)) {
					return false;
				}
				if (!this.isValue(o)) {
					return true;
				}
				o = $.trim(o).replace(/\&nbsp\;/ig, '').replace(/\&#160\;/ig, '');
				return o === "";
			}
		});
		$.fn.fmatter = function(formatType, cellval, opts, rwd, act) {
			// build main options before element iteration
			var v = cellval;
			opts = $.extend({}, $.wizgrid.getRegional(this, 'formatter'), opts);
			try {
				v = $.fn.fmatter[formatType].call(this, cellval, opts, rwd, act);
			}
			catch (fe) {}
			return v;
		};
		$.fmatter.util = {
			// Taken from YAHOO utils
			NumberFormat: function(nData, opts) {
				if (!$.fmatter.isNumber(nData)) {
					nData *= 1;
				}
				if ($.fmatter.isNumber(nData)) {
					var bNegative = (nData < 0);
					var sOutput = String(nData);
					var sDecimalSeparator = opts.decimalSeparator || ".";
					var nDotIndex;
					if ($.fmatter.isNumber(opts.decimalPlaces)) {
						// Round to the correct decimal place
						var nDecimalPlaces = opts.decimalPlaces;
						var nDecimal = Math.pow(10, nDecimalPlaces);
						sOutput = String(Math.round(nData * nDecimal) / nDecimal);
						nDotIndex = sOutput.lastIndexOf(".");
						if (nDecimalPlaces > 0) {
							// Add the decimal separator
							if (nDotIndex < 0) {
								sOutput += sDecimalSeparator;
								nDotIndex = sOutput.length - 1;
							}
							// Replace the "."
							else if (sDecimalSeparator !== ".") {
								sOutput = sOutput.replace(".", sDecimalSeparator);
							}
							// Add missing zeros
							while ((sOutput.length - 1 - nDotIndex) < nDecimalPlaces) {
								sOutput += "0";
							}
						}
					}
					if (opts.thousandsSeparator) {
						var sThousandsSeparator = opts.thousandsSeparator;
						nDotIndex = sOutput.lastIndexOf(sDecimalSeparator);
						nDotIndex = (nDotIndex > -1) ? nDotIndex : sOutput.length;
						var sNewOutput = sOutput.substring(nDotIndex);
						var nCount = -1
							, i;
						for (i = nDotIndex; i > 0; i--) {
							nCount++;
							if ((nCount % 3 === 0) && (i !== nDotIndex) && (!bNegative || (i > 1))) {
								sNewOutput = sThousandsSeparator + sNewOutput;
							}
							sNewOutput = sOutput.charAt(i - 1) + sNewOutput;
						}
						sOutput = sNewOutput;
					}
					// Prepend prefix
					sOutput = (opts.prefix) ? opts.prefix + sOutput : sOutput;
					// Append suffix
					sOutput = (opts.suffix) ? sOutput + opts.suffix : sOutput;
					return sOutput;
				}
				return nData;
			}
		};
		$.fn.fmatter.defaultFormat = function(cellval, opts) {
			return ($.fmatter.isValue(cellval) && cellval !== "") ? cellval : opts.defaultValue || "&#160;";
		};
		$.fn.fmatter.email = function(cellval, opts) {
			if (!$.fmatter.isEmpty(cellval)) {
				return "<a href=\"mailto:" + cellval + "\">" + cellval + "</a>";
			}
			return $.fn.fmatter.defaultFormat(cellval, opts);
		};
		$.fn.fmatter.radio = function(cval, opts) { // pjy 라디오
			var op = $.extend({}, opts.checkbox)
				, ds;
			if (opts.Columns !== undefined && opts.Columns.formatoptions !== undefined) {
				op = $.extend({}, op, opts.Columns.formatoptions);
			}
			if (op.disabled === true) {
				ds = "disabled=\"disabled\"";
			}
			else {
				ds = "";
			}
			// if($.fmatter.isEmpty(cval) || cval === undefined ) {cval =
			// $.fn.fmatter.defaultFormat(cval,op);}
			// cval=String(cval);
			// cval=(cval+"").toLowerCase();
			// var bchk = cval.search(/(false|f|0|no|n|off|undefined)/i)<0 ?
			// " checked='checked' " : "";
			return "<input id='radio_" + opts.rowId + "' onclick=CheckIUD('" + opts.Columns.name + "','" + opts.gid +
				"','" + opts.rowId + "','" + 'checkbox' + "') type=\"radio\" " + " value=\"" + 'N' +
				"\" offval=\"no\" " + ds + "/>";
			// return "<input name='radio_"+opts.gid+"' type=\"radio\" " + "
			// value=\""+ 'N'+"\" offval=\"no\" "+ds+ "/>";
		};
		$.fn.fmatter.checkbox = function(cval, opts) { // pjy 체크
			var op = $.extend({}, opts.checkbox)
				, ds;
			if (opts.Columns !== undefined && opts.Columns.formatoptions !== undefined) {
				op = $.extend({}, op, opts.Columns.formatoptions);
			}
			if (op.disabled === true) {
				ds = "disabled=\"disabled\"";
			}
			else {
				ds = "";
			}
			if ($.fmatter.isEmpty(cval) || cval === undefined) {
				cval = $.fn.fmatter.defaultFormat(cval, op);
			}
			cval = String(cval);
			cval = (cval + "").toLowerCase();
			var bchk = cval.search(/(false|f|0|no|n|off|undefined)/i) < 0 ? " checked='checked' " : "";
			// var strAt =$.wizgrid.wizGrid('getCell', opts.rowId,
			// "IUDFLAG");//pjy +opts.rowId
			// return "<input type=\"checkbox\" " + bchk + " value=\""+
			// cval+"\" offval=\"no\" "+ds+ "/>";
			return "<input id='checkbox_" + opts.rowId + "_" + opts.Columns.name + "' name='" + opts.gid + "chk" +
				"' class='" + opts.gid + "_chk" + "' onChange=CheckIUD('" + opts.Columns.name + "','" + opts.gid +
				"','" + opts.rowId + "','" + 'checkbox' + "') type=\"checkbox\" " + bchk + " value=\"" + cval +
				"\" offval=\"no\" " + ds + "/>";
		};
		$.fn.fmatter.image = function(cval, opts) { // pjy 이미지
			var op = $.extend({}, opts.calendar)
				, ds;
			if (opts.Columns !== undefined && opts.Columns.formatoptions !== undefined) {
				op = $.extend({}, op, opts.Columns.formatoptions);
			}
			// var strAt =$.wizgrid.wizGrid('getCell', opts.rowId,
			// "IUDFLAG");//pjy +opts.rowId
			// return "<input type=\"checkbox\" " + bchk + " value=\""+
			// cval+"\" offval=\"no\" "+ds+ "/>";
			var GridId = opts.gid;
			var rowId = opts.rowId;
			var rowIndex = rowId.replace("R", ""); // $('#'+GridId).getRowIdx('R1');//$('#'+GridId).getRowIdx(rowId);
			var imagePath = opts.Columns.imagePath;
			var colIndex = $('#' + GridId).getColumnIndex(opts.Columns.name);
			var imageFile = opts.Columns.imageFile;
			var imageStyle = opts.Columns.imageStyle;
			// alert(cval);
			if (cval != "undefined" || cval != null || cval != "") {
				cval = imageFile;
			}
			else {}
			if (imagePath == undefined || imagePath == null || imagePath == "") {
				imagePath = "/wizware/js"
			}
			// alert(opts.Columns.imagePath);
			return "<img src='" + Contextpath + imagePath + "' id='" + opts.Columns.name + "_image" + "'  style='" +
				imageStyle + "' ></img>";
		};
		$.fn.fmatter.calendar = function(cval, opts) { // pjy 달력
			var op = $.extend({}, opts.calendar)
				, ds;
			if (opts.Columns !== undefined && opts.Columns.formatoptions !== undefined) {
				op = $.extend({}, op, opts.Columns.formatoptions);
			}
			var GridId = opts.gid;
			var rowId = opts.rowId;
			var rowIndex = rowId.replace("R", ""); // $('#'+GridId).getRowIdx('R1');//$('#'+GridId).getRowIdx(rowId);
			var imagePath = opts.Columns.imagePath;
			var colIndex = $('#' + GridId).getColumnIndex(opts.Columns.name);
			var imageStyle = opts.Columns.imageStyle;
			// alert(rowId);
			if (imagePath == undefined || imagePath == null || imagePath == "") {
				imagePath = "/wizware/js/calendar_alt.png"
			}
			if (imageStyle == undefined || imageStyle == null || imageStyle == "") {
				imageStyle = "width:28px;height:23px;"
			}
			// alert(grid1.getCurrentCellValue(opts.Columns.name,true,colIndex));
			// alert(rowId);
			return "<img src='" + Contextpath + imagePath + "' OnClick='javascript:" + opts.Columns.name +
				"_OnClick_" + opts.gid + "(\"" + rowId + "\"," + colIndex + ");' id='" + opts.Columns.name + "_popup" +
				"' name='" + opts.Columns.name + "_popup" + "'  style='cursor:pointer;" + imageStyle + "' ></img>";
			// return "<img src='"+Contextpath+imagePath+"'
			// OnClick='javascript:calendar("+rowIndex+","+colIndex+");'
			// id='"+opts.Columns.name+"_popup"+"'
			// name='"+opts.Columns.name+"_popup"+"'
			// style='cursor:pointer;width:25px;height:20px;' ></img>";
		};
		$.fn.fmatter.popup = function(cval, opts) { // pjy 팝업
			var op = $.extend({}, opts.popup)
				, ds;
			if (opts.Columns !== undefined && opts.Columns.formatoptions !== undefined) {
				op = $.extend({}, op, opts.Columns.formatoptions);
			}
			var GridId = opts.gid;
			var rowId = opts.rowId;
			var rowIndex = $('#' + GridId).getRowIndex(opts.rowId);
			var imagePath = opts.Columns.imagePath;
			if (imagePath == undefined || imagePath == null || imagePath == "") {
				imagePath = "/wizware/js/search2.png"
			}
			return "<img src='" + Contextpath + imagePath + "' OnClick='javascript:" + opts.Columns.name +
				"_cellonclick_" + opts.gid + "(\"" + rowId + "\"" + ");' id='" + opts.Columns.name + "_popup_" + opts.gid +
				"' name='" + opts.Columns.name + "_popup_" + opts.gid +
				"'  style='cursor:pointer;width:16px;height:16px;' ></img>";
		};
		$.fn.fmatter.link = function(cellval, opts) {
			var op = {
				target: opts.target
			};
			var target = "";
			if (opts.Columns !== undefined && opts.Columns.formatoptions !== undefined) {
				op = $.extend({}, op, opts.Columns.formatoptions);
			}
			if (op.target) {
				target = 'target=' + op.target;
			}
			if (!$.fmatter.isEmpty(cellval)) {
				return "<a " + target + " href=\"" + cellval + "\">" + cellval + "</a>";
			}
			return $.fn.fmatter.defaultFormat(cellval, opts);
		};
		$.fn.fmatter.showlink = function(cellval, opts) {
			var op = {
					baseLinkUrl: opts.baseLinkUrl
					, showAction: opts.showAction
					, addParam: opts.addParam || ""
					, target: opts.target
					, idName: opts.idName
				}
				, target = ""
				, idUrl;
			if (opts.Columns !== undefined && opts.Columns.formatoptions !== undefined) {
				op = $.extend({}, op, opts.Columns.formatoptions);
			}
			if (op.target) {
				target = 'target=' + op.target;
			}
			idUrl = op.baseLinkUrl + op.showAction + '?' + op.idName + '=' + opts.rowId + op.addParam;
			if ($.fmatter.isString(cellval) || $.fmatter.isNumber(cellval)) { // add
				// this
				// one
				// even
				// if
				// its
				// blank
				// string
				return "<a " + target + " href=\"" + idUrl + "\">" + cellval + "</a>";
			}
			return $.fn.fmatter.defaultFormat(cellval, opts);
		};
		$.fn.fmatter.integer = function(cellval, opts) {
			var op = $.extend({}, opts.integer);
			if (opts.Columns !== undefined && opts.Columns.formatoptions !== undefined) {
				op = $.extend({}, op, opts.Columns.formatoptions);
			}
			if ($.fmatter.isEmpty(cellval)) {
				return op.defaultValue;
			}
			return $.fmatter.util.NumberFormat(cellval, op);
		};
		$.fn.fmatter.number = function(cellval, opts) {
			var op = $.extend({}, opts.number);
			if (opts.Columns !== undefined && opts.Columns.formatoptions !== undefined) {
				op = $.extend({}, op, opts.Columns.formatoptions);
			}
			if ($.fmatter.isEmpty(cellval)) {
				return op.defaultValue;
			}
			return $.fmatter.util.NumberFormat(cellval, op);
		};
		$.fn.fmatter.currency = function(cellval, opts) {
			var op = $.extend({}, opts.currency);
			if (opts.Columns !== undefined && opts.Columns.formatoptions !== undefined) {
				op = $.extend({}, op, opts.Columns.formatoptions);
			}
			if ($.fmatter.isEmpty(cellval)) {
				return op.defaultValue;
			}
			return $.fmatter.util.NumberFormat(cellval, op);
		};
		$.fn.fmatter.date = function(cellval, opts, rwd, act) { // pjy date
			var op = $.extend({}, opts.date);
			if (opts.Columns !== undefined && opts.Columns.formatoptions !== undefined) {
				op = $.extend({}, op, opts.Columns.formatoptions);
			}
			if (!op.reformatAfterEdit && act === 'edit') {
				return $.fn.fmatter.defaultFormat(cellval, opts);
			}
			if (!$.fmatter.isEmpty(cellval)) {
				return $.wizgrid.parseDate.call(this, op.srcformat, cellval, op.newformat, op);
			}
			return $.fn.fmatter.defaultFormat(cellval, opts);
		};
		$.fn.fmatter.select = function(cellval, opts) {
			// wizGrid specific
			cellval = String(cellval);
			var oSelect = false
				, ret = []
				, sep, delim;
			if (opts.Columns.formatoptions !== undefined) {
				oSelect = opts.Columns.formatoptions.value;
				sep = opts.Columns.formatoptions.separator === undefined ? ":" : opts.Columns.formatoptions.separator;
				delim = opts.Columns.formatoptions.delimiter === undefined ? ";" : opts.Columns.formatoptions.delimiter;
			}
			else if (opts.Columns.editoptions !== undefined) {
				oSelect = opts.Columns.editoptions.value;
				sep = opts.Columns.editoptions.separator === undefined ? ":" : opts.Columns.editoptions.separator;
				delim = opts.Columns.editoptions.delimiter === undefined ? ";" : opts.Columns.editoptions.delimiter;
			}
			if (oSelect) {
				var msl = (opts.Columns.editoptions != null && opts.Columns.editoptions.multiple === true) === true ?
					true : false
					, scell = []
					, sv;
				if (msl) {
					scell = cellval.split(",");
					scell = $.map(scell, function(n) {
						return $.trim(n);
					});
				}
				if ($.fmatter.isString(oSelect)) {
					// mybe here we can use some caching with care ????
					var so = oSelect.split(delim)
						, j = 0
						, i;
					for (i = 0; i < so.length; i++) {
						sv = so[i].split(sep);
						if (sv.length > 2) {
							sv[1] = $.map(sv, function(n, i) {
								if (i > 0) {
									return n;
								}
							}).join(sep);
						}
						if (msl) {
							if ($.inArray(sv[0], scell) > -1) {
								ret[j] = sv[1];
								j++;
							}
						}
						else if ($.trim(sv[0]) === $.trim(cellval)) {
							ret[0] = sv[1];
							break;
						}
					}
				}
				else if ($.fmatter.isObject(oSelect)) {
					// this is quicker
					if (msl) {
						ret = $.map(scell, function(n) {
							return oSelect[n];
						});
					}
					else {
						ret[0] = oSelect[cellval] || "";
					}
				}
			}
			cellval = ret.join(", ");
			return cellval === "" ? $.fn.fmatter.defaultFormat(cellval, opts) : cellval;
		};
		$.fn.fmatter.rowactions = function(act) {
			var $tr = $(this).closest("tr.wizgrow")
				, rid = $tr.attr("id")
				, $id = $(this).closest("table.ui-wizgrid-btable").attr('id').replace(/_frozen([^_]*)$/, '$1')
				, $grid = $("#" + $id)
				, $t = $grid[0]
				, p = $t.p
				, cm = p.Columns[$.wizgrid.getCellIndex(this)]
				, $actionsDiv = cm.frozen ? $("tr#" + rid + " td:eq(" + $.wizgrid.getCellIndex(this) + ") > div", $grid) :
				$(this).parent()
				, op = {
					extraparam: {}
				}
				, saverow = function(rowid, res) {
					if ($.isFunction(op.afterSave)) {
						op.afterSave.call($t, rowid, res);
					}
					$actionsDiv.find("div.ui-inline-edit,div.ui-inline-del").show();
					$actionsDiv.find("div.ui-inline-save,div.ui-inline-cancel").hide();
				}
				, restorerow = function(rowid) {
					if ($.isFunction(op.afterRestore)) {
						op.afterRestore.call($t, rowid);
					}
					$actionsDiv.find("div.ui-inline-edit,div.ui-inline-del").show();
					$actionsDiv.find("div.ui-inline-save,div.ui-inline-cancel").hide();
				};
			if (cm.formatoptions !== undefined) {
				op = $.extend(op, cm.formatoptions);
			}
			if (p.editOptions !== undefined) {
				op.editOptions = p.editOptions;
			}
			if (p.delOptions !== undefined) {
				op.delOptions = p.delOptions;
			}
			if ($tr.hasClass("wizgrid-new-row")) {
				op.extraparam[p.prmNames.oper] = p.prmNames.addoper;
			}
			var actop = {
				keys: op.keys
				, oneditfunc: op.onEdit
				, successfunc: op.onSuccess
				, url: op.url
				, extraparam: op.extraparam
				, aftersavefunc: saverow
				, errorfunc: op.onError
				, afterrestorefunc: restorerow
				, restoreAfterError: op.restoreAfterError
				, mtype: op.mtype
			};
			switch (act) {
				case 'edit':
					$grid.wizGrid('editRow', rid, actop);
					$actionsDiv.find("div.ui-inline-edit,div.ui-inline-del").hide();
					$actionsDiv.find("div.ui-inline-save,div.ui-inline-cancel").show();
					$grid.triggerHandler("wizGridAfterGridComplete");
					break;
				case 'save':
					if ($grid.wizGrid('saveRow', rid, actop)) {
						$actionsDiv.find("div.ui-inline-edit,div.ui-inline-del").show();
						$actionsDiv.find("div.ui-inline-save,div.ui-inline-cancel").hide();
						$grid.triggerHandler("wizGridAfterGridComplete");
					}
					break;
				case 'cancel':
					$grid.wizGrid('restoreRow', rid, restorerow);
					$actionsDiv.find("div.ui-inline-edit,div.ui-inline-del").show();
					$actionsDiv.find("div.ui-inline-save,div.ui-inline-cancel").hide();
					$grid.triggerHandler("wizGridAfterGridComplete");
					break;
				case 'del':
					$grid.wizGrid('delGridRow', rid, op.delOptions);
					break;
				case 'formedit':
					$grid.wizGrid('setSelection', rid);
					$grid.wizGrid('editGridRow', rid, op.editOptions);
					break;
			}
		};
		$.fn.fmatter.actions = function(cellval, opts) {
			var op = {
					keys: false
					, editbutton: true
					, delbutton: true
					, editformbutton: false
				}
				, rowid = opts.rowId
				, str = ""
				, ocl, nav = $.wizgrid.getRegional(this, 'nav');
			if (opts.Columns.formatoptions !== undefined) {
				op = $.extend(op, opts.Columns.formatoptions);
			}
			if (rowid === undefined || $.fmatter.isEmpty(rowid)) {
				return "";
			}
			if (op.editformbutton) {
				ocl = "id='jEditButton_" + rowid +
					"' onclick=jQuery.fn.fmatter.rowactions.call(this,'formedit'); onmouseover=jQuery(this).addClass('ui-state-hover-grid'); onmouseout=jQuery(this).removeClass('ui-state-hover-grid'); ";
				str += "<div title='" + nav.edittitle +
					"' style='float:left;cursor:pointer;' class='ui-pg-div ui-inline-edit' " + ocl +
					"><span class='ui-icon ui-icon-pencil'></span></div>";
			}
			else if (op.editbutton) {
				ocl = "id='jEditButton_" + rowid +
					"' onclick=jQuery.fn.fmatter.rowactions.call(this,'edit'); onmouseover=jQuery(this).addClass('ui-state-hover-grid'); onmouseout=jQuery(this).removeClass('ui-state-hover-grid'); ";
				str += "<div title='" + nav.edittitle +
					"' style='float:left;cursor:pointer;' class='ui-pg-div ui-inline-edit' " + ocl +
					"><span class='ui-icon ui-icon-pencil'></span></div>";
			}
			if (op.delbutton) {
				ocl = "id='jDeleteButton_" + rowid +
					"' onclick=jQuery.fn.fmatter.rowactions.call(this,'del'); onmouseover=jQuery(this).addClass('ui-state-hover-grid'); onmouseout=jQuery(this).removeClass('ui-state-hover-grid'); ";
				str += "<div title='" + nav.deltitle +
					"' style='float:left;margin-left:5px;' class='ui-pg-div ui-inline-del' " + ocl +
					"><span class='ui-icon ui-icon-trash'></span></div>";
			}
			ocl = "id='jSaveButton_" + rowid +
				"' onclick=jQuery.fn.fmatter.rowactions.call(this,'save'); onmouseover=jQuery(this).addClass('ui-state-hover-grid'); onmouseout=jQuery(this).removeClass('ui-state-hover-grid'); ";
			str += "<div title='" + nav.savetitle +
				"' style='float:left;display:none' class='ui-pg-div ui-inline-save' " + ocl +
				"><span class='ui-icon ui-icon-disk'></span></div>";
			ocl = "id='jCancelButton_" + rowid +
				"' onclick=jQuery.fn.fmatter.rowactions.call(this,'cancel'); onmouseover=jQuery(this).addClass('ui-state-hover-grid'); onmouseout=jQuery(this).removeClass('ui-state-hover-grid'); ";
			str += "<div title='" + nav.canceltitle +
				"' style='float:left;display:none;margin-left:5px;' class='ui-pg-div ui-inline-cancel' " + ocl +
				"><span class='ui-icon ui-icon-cancel'></span></div>";
			return "<div style='margin-left:8px;'>" + str + "</div>";
		};
		$.unformat = function(cellval, options, pos, cnt) {
			// specific for wizGrid only
			var ret, formatType = options.Columns.formatter
				, op = options.Columns.formatoptions || {}
				, sep, re = /([\.\*\_\'\(\)\{\}\+\?\\])/g
				, unformatFunc = options.Columns.unformat || ($.fn.fmatter[formatType] && $.fn.fmatter[formatType].unformat);
			if (unformatFunc !== undefined && $.isFunction(unformatFunc)) {
				ret = unformatFunc.call(this, $(cellval).text(), options, cellval);
			}
			else if (formatType !== undefined && $.fmatter.isString(formatType)) {
				var opts = $.wizgrid.getRegional(this, 'formatter') || {}
					, stripTag;
				switch (formatType) {
					case 'integer':
						op = $.extend({}, opts.integer, op);
						sep = op.thousandsSeparator.replace(re, "\\$1");
						stripTag = new RegExp(sep, "g");
						ret = $(cellval).text().replace(stripTag, '');
						break;
					case 'number':
						op = $.extend({}, opts.number, op);
						sep = op.thousandsSeparator.replace(re, "\\$1");
						stripTag = new RegExp(sep, "g");
						ret = $(cellval).text().replace(stripTag, "").replace(op.decimalSeparator, '.');
						break;
					case 'currency':
						op = $.extend({}, opts.currency, op);
						sep = op.thousandsSeparator.replace(re, "\\$1");
						stripTag = new RegExp(sep, "g");
						ret = $(cellval).text();
						if (op.prefix && op.prefix.length) {
							ret = ret.substr(op.prefix.length);
						}
						if (op.suffix && op.suffix.length) {
							ret = ret.substr(0, ret.length - op.suffix.length);
						}
						ret = ret.replace(stripTag, '').replace(op.decimalSeparator, '.');
						break;
					case 'checkbox':
						var cbv = (options.Columns.editoptions) ? options.Columns.editoptions.value.split(":") : ["Yes", "No"];
						ret = $('input', cellval).is(":checked") ? cbv[0] : cbv[1];
						break;
					case 'select':
						ret = $.unformat.select(cellval, options, pos, cnt);
						break;
					case 'actions':
						return "";
					default:
						ret = $(cellval).text();
				}
			}
			return ret !== undefined ? ret : cnt === true ? $(cellval).text() : $.wizgrid.htmlDecode($(cellval).html());
		};
		$.unformat.select = function(cellval, options, pos, cnt) {
			// Spacial case when we have local data and perform a sort
			// cnt is set to true only in sortDataArray
			var ret = [];
			var cell = $(cellval).text();
			if (cnt === true) {
				return cell;
			}
			var op = $.extend({}, options.Columns.formatoptions !== undefined ? options.Columns.formatoptions :
					options.Columns.editoptions)
				, sep = op.separator === undefined ? ":" : op.separator
				, delim = op.delimiter === undefined ? ";" : op.delimiter;
			if (op.value) {
				var oSelect = op.value
					, msl = op.multiple === true ? true : false
					, scell = []
					, sv;
				if (msl) {
					scell = cell.split(",");
					scell = $.map(scell, function(n) {
						return $.trim(n);
					});
				}
				if ($.fmatter.isString(oSelect)) {
					var so = oSelect.split(delim)
						, j = 0
						, i;
					for (i = 0; i < so.length; i++) {
						sv = so[i].split(sep);
						if (sv.length > 2) {
							sv[1] = $.map(sv, function(n, i) {
								if (i > 0) {
									return n;
								}
							}).join(sep);
						}
						if (msl) {
							if ($.inArray($.trim(sv[1]), scell) > -1) {
								ret[j] = sv[0];
								j++;
							}
						}
						else if ($.trim(sv[1]) === $.trim(cell)) {
							ret[0] = sv[0];
							break;
						}
					}
				}
				else if ($.fmatter.isObject(oSelect) || $.isArray(oSelect)) {
					if (!msl) {
						scell[0] = cell;
					}
					ret = $.map(scell, function(n) {
						var rv;
						$.each(oSelect, function(i, val) {
							if (val === n) {
								rv = i;
								return false;
							}
						});
						if (rv !== undefined) {
							return rv;
						}
					});
				}
				return ret.join(", ");
			}
			return cell || "";
		};
		$.unformat.date = function(cellval, opts) {
			var op = $.wizgrid.getRegional(this, 'formatter.date') || {};
			if (opts.formatoptions !== undefined) {
				op = $.extend({}, op, opts.formatoptions);
			}
			if (!$.fmatter.isEmpty(cellval)) {
				return $.wizgrid.parseDate.call(this, op.newformat, cellval, op.srcformat, op);
			}
			return $.fn.fmatter.defaultFormat(cellval, opts);
		};
		// module begin
		window.wizGridUtils = {
			stringify: function(obj) {
				return JSON.stringify(obj, function(key, value) {
					return (typeof value === 'function') ? value.toString() : value;
				});
			}
			, parse: function(str) {
				return JSON.parse(str, function(key, value) {
					if (typeof value === "string" && value.indexOf("function") !== -1) {
						return eval('(' + value + ')');
					}
					return value;
				});
			}
			, encode: function(text) { // repeated, but should not depend
				// on grid
				return String(text).replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g
					, '&quot;');
			}
			, jsonToXML: function(tree, options) {
				var o = $.extend({
						xmlDecl: '<?xml version="1.0" encoding="UTF-8" ?>\n'
						, attr_prefix: '-'
						, encode: true
					}, options || {})
					, that = this
					, scalarToxml = function(name, text) {
						if (name === "#text") {
							return (o.encode ? that.encode(text) : text);
						}
						else if (typeof(text) === 'function') {
							return "<" + name + "><![CDATA[" + text + "]]></" + name + ">\n";
						}
						if (text === "") {
							return "<" + name + ">__EMPTY_STRING_</" + name + ">\n";
						}
						else {
							return "<" + name + ">" + (o.encode ? that.encode(text) : text) + "</" + name + ">\n";
						}
					}
					, arrayToxml = function(name, array) {
						var out = [];
						for (var i = 0; i < array.length; i++) {
							var val = array[i];
							if (typeof(val) === "undefined" || val == null) {
								out[out.length] = "<" + name + " />";
							}
							else if (typeof(val) === "object" && val.constructor == Array) {
								out[out.length] = arrayToxml(name, val);
							}
							else if (typeof(val) === "object") {
								out[out.length] = hashToxml(name, val);
							}
							else {
								out[out.length] = scalarToxml(name, val);
							}
						}
						if (!out.length) {
							out[0] = "<" + name + ">__EMPTY_ARRAY_</" + name + ">\n";
						}
						return out.join("");
					}
					, hashToxml = function(name, tree) {
						var elem = [];
						var attr = [];
						for (var key in tree) {
							if (!tree.hasOwnProperty(key)) continue;
							var val = tree[key];
							if (key.charAt(0) !== o.attr_prefix) {
								if (val == null) { // null or undefined
									elem[elem.length] = "<" + key + " />";
								}
								else if (typeof(val) === "object" && val.constructor === Array) {
									elem[elem.length] = arrayToxml(key, val);
								}
								else if (typeof(val) === "object") {
									elem[elem.length] = hashToxml(key, val);
								}
								else {
									elem[elem.length] = scalarToxml(key, val);
								}
							}
							else {
								attr[attr.length] = " " + (key.substring(1)) + '="' + (o.encode ? that.encode(val) : val) + '"';
							}
						}
						var jattr = attr.join("");
						var jelem = elem.join("");
						if (name == null) { // null or undefined
							// no tag
						}
						else if (elem.length > 0) {
							if (jelem.match(/\n/)) {
								jelem = "<" + name + jattr + ">\n" + jelem + "</" + name + ">\n";
							}
							else {
								jelem = "<" + name + jattr + ">" + jelem + "</" + name + ">\n";
							}
						}
						else {
							jelem = "<" + name + jattr + " />\n";
						}
						return jelem;
					};
				var xml = hashToxml(null, tree);
				return o.xmlDecl + xml;
			}
			, xmlToJSON: function(root, options) {
				var o = $.extend({
					force_array: [], // [ "rdf:li", "item", "-xmlns" ];
					attr_prefix: '-'
				}, options || {});
				if (!root) {
					return;
				}
				var __force_array = {};
				if (o.force_array) {
					for (var i = 0; i < o.force_array.length; i++) {
						__force_array[o.force_array[i]] = 1;
					}
				}
				if (typeof root === 'string') {
					root = $.parseXML(root);
				}
				if (root.documentElement) {
					root = root.documentElement;
				}
				var addNode = function(hash, key, cnts, val) {
						if (typeof val === 'string') {
							if (val.indexOf('function') !== -1) {
								val = eval('(' + val + ')'); // we need this
								// in our
								// implement
							}
							else {
								switch (val) {
									case '__EMPTY_ARRAY_':
										val = [];
										break;
									case '__EMPTY_STRING_':
										val = "";
										break;
									case "false":
										val = false;
										break;
									case "true":
										val = true;
										break;
								}
							}
						}
						if (__force_array[key]) {
							if (cnts === 1) {
								hash[key] = [];
							}
							hash[key][hash[key].length] = val; // push
						}
						else if (cnts === 1) { // 1st sibling
							hash[key] = val;
						}
						else if (cnts === 2) { // 2nd sibling
							hash[key] = [hash[key], val];
						}
						else { // 3rd sibling and more
							hash[key][hash[key].length] = val;
						}
					}
					, parseElement = function(elem) {
						// COMMENT_NODE
						if (elem.nodeType === 7) {
							return;
						}
						// TEXT_NODE CDATA_SECTION_NODE
						if (elem.nodeType === 3 || elem.nodeType === 4) {
							var bool = elem.nodeValue.match(/[^\x00-\x20]/);
							if (bool == null) return; // ignore white spaces
							return elem.nodeValue;
						}
						var retval, cnt = {}
							, i, key, val;
						// parse attributes
						if (elem.attributes && elem.attributes.length) {
							retval = {};
							for (i = 0; i < elem.attributes.length; i++) {
								key = elem.attributes[i].nodeName;
								if (typeof(key) !== "string") {
									continue;
								}
								val = elem.attributes[i].nodeValue;
								if (!val) {
									continue;
								}
								key = o.attr_prefix + key;
								if (typeof(cnt[key]) === "undefined") {
									cnt[key] = 0;
								}
								cnt[key]++;
								addNode(retval, key, cnt[key], val);
							}
						}
						// parse child nodes (recursive)
						if (elem.childNodes && elem.childNodes.length) {
							var textonly = true;
							if (retval) {
								textonly = false;
							} // some attributes exists
							for (i = 0; i < elem.childNodes.length && textonly; i++) {
								var ntype = elem.childNodes[i].nodeType;
								if (ntype === 3 || ntype === 4) {
									continue;
								}
								textonly = false;
							}
							if (textonly) {
								if (!retval) {
									retval = "";
								}
								for (i = 0; i < elem.childNodes.length; i++) {
									retval += elem.childNodes[i].nodeValue;
								}
							}
							else {
								if (!retval) {
									retval = {};
								}
								for (i = 0; i < elem.childNodes.length; i++) {
									key = elem.childNodes[i].nodeName;
									if (typeof(key) !== "string") {
										continue;
									}
									val = parseElement(elem.childNodes[i]);
									if (!val) {
										continue;
									}
									if (typeof(cnt[key]) === "undefined") {
										cnt[key] = 0;
									}
									cnt[key]++;
									addNode(retval, key, cnt[key], val);
								}
							}
						}
						return retval;
					};
				var json = parseElement(root); // parse root node
				if (__force_array[root.nodeName]) {
					json = [json];
				}
				if (root.nodeType !== 11) { // DOCUMENT_FRAGMENT_NODE
					var tmp = {};
					tmp[root.nodeName] = json; // root nodeName
					json = tmp;
				}
				return json;
			}
		};
	}));