
// 기초정보조회 팝업
function popSysCodeInfo(myObj, fromWhere, title, grpCd, itemCd, itemNm,  pItemCd, cdVal1
	                   ,targetGridId, targetRowIndex, targetItemCd, targetItemNm)
{
	/*
    // 필수수정 영역
	var fromWhere = 'G' ;      // S:조회조건, G:그리드
	var title  = '통화' ;      // 팝업이름
	var grpCd  = 'MONEY_UNIT'; // 그룹코드
	var itemCd = grid1.getCellValueAfterSave(rowIndex, "MONEY_UNIT", true); // 코드
	var itemNm = '' ; // 명칭	
	
	// 반환정보 입력 영역
    var targetGridId   = 'grid1';    // 반환대상 그리드 아이디(그리드가 아닌경우 null) 
    var targetRowIndex = rowIndex;   // 반환대상 그리드 Row Index (그리드가 아닌경우 null) 
    var targetItemCd = 'MONEY_UNIT'; // 반환대상 코드 필드명  
    var targetItemNm = 'ITEM_NM';    // 반환대상 명칭 필드명  
	*/	

	var params = '?FROM_WHERE=' + fromWhere
	           + '&TITLE='   + title
	           + '&GRP_CD='  + grpCd
	           + '&ITEM_CD=' + itemCd
	           + '&ITEM_NM=' + itemNm 
	           + '&P_ITEM_CD=' + pItemCd
	           + '&CD_VAL1='   + cdVal1 
	           + '&TARGET_GRID_ID='   + targetGridId 
	           + '&TARGET_ROW_INDEX=' + targetRowIndex
	           + '&TARGET_ITEM_CD=' + targetItemCd 
	           + '&TARGET_ITEM_NM=' + targetItemNm 
	           + '&ENTER_YN=' + (myObj == null ? 'Y' : 'N') ;

	var url = Contextpath+'/jsp/common/SysCodeInfoPop.jsp' + params ;	
		
	$("#ifmPopup").attr("src", url);  
	$("#ifmPopup").attr("frameborder", 0);  
	$("#ifmPopup").css("height", 495);  
	$("#ifmPopup").css("width", 510);  
	$("#divPopup").dialog({  
		autoOpen: false,  
		height: 565,  
		width: 550, 
		modal: true,  
		title: title + ' 조회', 
		resizable: true,  
		closeOnEscape: true, 		
		create: function(event, ui){  
		},  
		open: function(event, ui){  
		},  
		close: function(){ 
			// iframe 초기화.
			$("#ifmPopup").html('') ;
		},  
		cancel: function(){  
		}  
	 });  
	
	$("#divPopup").dialog("open"); 
}

// 기초정보조회 팝업 더블클릭시
function sysCodeInfoDblClick(fromWhere, itemCd, itemNm, 
							targetGridId, targetRowIndex, targetItemCd, targetItemNm)
{	
	// 조회영역에서 호출
	if(fromWhere== 'S')
	{
		if(targetItemCd != 'null') $('#' + targetItemCd).val(itemCd);
		if(targetItemNm != 'null') $('#' + targetItemNm).val(itemNm);
	}
	// 그리드영역에서 호출
	else if(fromWhere== 'G')
	{	
		if(targetItemCd != 'null') $('#' + targetGridId).setCellValue(targetRowIndex, targetItemCd,  itemCd, true); 
		if(targetItemNm != 'null') $('#' + targetGridId).setCellValue(targetRowIndex, targetItemNm,  itemNm, true); 		
	}	

	if(targetItemCd == "ACT_CENTER_ATTR_CD")//활동센터속성 입력시 활동센터코드 셋팅
	{
		var bizPlaceCd 		= $('#' + targetGridId).getCellValue(targetRowIndex,  "BIZ_PLACE_CD", true);
		var actCenterAttrCd = $('#' + targetGridId).getCellValue(targetRowIndex, "ACT_CENTER_ATTR_CD", true); 
		var actCd 			= $('#' + targetGridId).getCellValue(targetRowIndex, "ACT_CD", true); 
		$('#' + targetGridId).setCellValue(targetRowIndex,'ACT_CENTER_CD',  bizPlaceCd + actCenterAttrCd + actCd, true);

		var rowId 			= $('#' + targetGridId).getGridRowId(targetRowIndex);
	 	var bizPlaceNm 		= $("#BIZ_PLACE_CD"+rowId).text(); 
	  	var actCenterAttrNm = $('#' + targetGridId).getCellValue(targetRowIndex, "ACT_CENTER_ATTR_NM", true); 
	  	var actNm 			= $('#' + targetGridId).getCellValue(targetRowIndex, "ACT_NM", true); 	 
	  	$('#' + targetGridId).setCellValue(targetRowIndex,'ACT_CENTER_NM',  bizPlaceNm + actCenterAttrNm + actNm, true);
	}
}

// 활동조회 팝업
function popMstAct(myObj, fromWhere, title, actGbn, actCd, actNm
						 ,targetGridId, targetRowIndex, targetActCd, targetActNm) 
{
	/*
    // 필수수정 영역
	var fromWhere = 'G' ;      // S:조회조건, G:그리드
	var title  = '활동' ;      // 팝업이름
	var grpCd  = 'ACT_CENTER_GBN'; // 그룹코드
	var itemCd = grid1.getCellValueAfterSave(rowIndex, "ACT_CENTER_GBN", true); // 코드
	var itemNm = '' ; // 명칭	
	
	// 반환정보 입력 영역
    var targetGridId   = 'grid1';    // 반환대상 그리드 아이디(그리드가 아닌경우 null) 
    var targetRowIndex = rowIndex;   // 반환대상 그리드 Row Index (그리드가 아닌경우 null) 
    var targetItemCd = 'ACT_CENTER_GBN'; // 반환대상 코드 필드명  
    var targetItemNm = 'ITEM_NM';    // 반환대상 명칭 필드명  
	*/	

	var params = '?FROM_WHERE=' + fromWhere
	           + '&TITLE='   + title 
	           + '&ACT_GBN=' + actGbn
	           + '&ACT_CD='  + actCd
	           + '&ACT_NM='  + actNm
	           + '&TARGET_GRID_ID='   + targetGridId 
	           + '&TARGET_ROW_INDEX=' + targetRowIndex
	           + '&TARGET_ACT_CD=' + targetActCd 
	           + '&TARGET_ACT_NM=' + targetActNm 
	           + '&ENTER_YN=' + (myObj == null ? 'Y' : 'N') ;

	var url = Contextpath+'/jsp/common/MstActPop.jsp' + params ;	
		
	$("#ifmPopup").attr("src", url);  
	$("#ifmPopup").attr("frameborder", 0);  
	$("#ifmPopup").css("height", 520);  
	$("#ifmPopup").css("width", 540);  
	$("#divPopup").dialog({  
		autoOpen: false,  
		height: 580,  
		width: 570, 
		modal: true,  
		title: title + ' 조회', 
		resizable: true,  
		closeOnEscape: true, 		
		create: function(event, ui){  
		},  
		open: function(event, ui){  
		},  
		close: function(){ 
			// iframe 초기화.
			$("#ifmPopup").html('') ;
		},  
		cancel: function(){  
		}  
	 });  
	
	$("#divPopup").dialog("open"); 
}

// 활동조회 팝업 더블클릭시
function mstActDblClick(fromWhere, actCd, actNm
							,targetGridId, targetRowIndex, targetActCd, targetActNm)
{	
	// 조회영역에서 호출
	if(fromWhere== 'S')
	{
		if(targetActCd != 'null') $('#' + targetActCd).val(actCd);
		if(targetActNm != 'null') $('#' + targetActNm).val(actNm);
	}
	// 그리드영역에서 호출
	else if(fromWhere== 'G')
	{	 
		if(targetActCd != 'null') $('#' + targetGridId).setCellValue(targetRowIndex, targetActCd, actCd, true); 
		if(targetActNm != 'null') $('#' + targetGridId).setCellValue(targetRowIndex, targetActNm, actNm, true); 	

		// 활동 입력시 활동센터코드 셋팅
		 
		var bizPlaceCd 		= $('#' + targetGridId).getCellValue(targetRowIndex,  "BIZ_PLACE_CD", true);
		var actCenterAttrCd = $('#' + targetGridId).getCellValue(targetRowIndex, "ACT_CENTER_ATTR_CD", true); 
		var actCd 			= $('#' + targetGridId).getCellValue(targetRowIndex, "ACT_CD", true); 
		$('#' + targetGridId).setCellValue(targetRowIndex,'ACT_CENTER_CD',  bizPlaceCd + actCenterAttrCd + actCd, true);

		var rowId 			= $('#' + targetGridId).getGridRowId(targetRowIndex);
	 	var bizPlaceNm 		= $("#BIZ_PLACE_CD"+rowId).text(); 
	  	var actCenterAttrNm = $('#' + targetGridId).getCellValue(targetRowIndex, "ACT_CENTER_ATTR_NM", true); 
	  	var actNm 			= $('#' + targetGridId).getCellValue(targetRowIndex, "ACT_NM", true); 	 
	  	$('#' + targetGridId).setCellValue(targetRowIndex,'ACT_CENTER_NM',  bizPlaceNm + actCenterAttrNm + actNm, true);
		 
	}	
}
// 활동센터조회 팝업
function popBaseActCenter(myObj, fromWhere, baseYear, bizPlaceCd, actCenterGbn, actCenterCd, actCenterNm
						 ,targetGridId, targetRowIndex, targetBizPlaceCd, targetActCenterCd, targetActCenterNm) 
{
	var params = '?FROM_WHERE=' + fromWhere
	           + '&BASE_YEAR='  + baseYear
	           + '&BIZ_PLACE_CD='  + bizPlaceCd
	           + '&ACT_CENTER_GBN=' + actCenterGbn
	           + '&ACT_CENTER_CD='  + actCenterCd
	           + '&ACT_CENTER_NM='  + actCenterNm
	           + '&TARGET_GRID_ID='   + targetGridId 
	           + '&TARGET_ROW_INDEX=' + targetRowIndex
	           + '&TARGET_BIZ_PLACE_CD=' + targetBizPlaceCd 
	           + '&TARGET_ACT_CENTER_CD=' + targetActCenterCd 
	           + '&TARGET_ACT_CENTER_NM=' + targetActCenterNm
	           + '&ENTER_YN=' + (myObj == null ? 'Y' : 'N') ;

	var url = Contextpath+'/jsp/base/common/BaseActCenterPop.jsp'+ params; 
	
    $("#ifmPopup").attr("src", url);  
    $("#ifmPopup").attr("frameborder", 0);  
    $("#ifmPopup").css("height", 430);  
	$("#ifmPopup").css("width", 690);  
    $("#divPopup").dialog({  
            autoOpen: false,  
            height: 480,  
            width: 720, 
            modal: true,  
            title: '활동센터 조회',
            resizable: true,  
            closeOnEscape: true, 
             
            create: function (event, ui) {  
            },  
            open: function (event, ui) {  
            },  
            close: function () {  
            	$("#ifmPopup").html('');
            },  
            cancel: function () {  
            }  
    });  
    $("#divPopup").dialog("open");
}

// 활동센터조회 팝업 더블클릭시
function baseActCenterDblClick(fromWhere, bizPlaceCd, actCenterCd, actCenterNm
							,targetGridId, targetRowIndex, targetBizPlaceCd, targetActCenterCd, targetActCenterNm)
{	
	// 조회영역에서 호출
	if(fromWhere== 'S')
	{
		if(targetBizPlaceCd && targetBizPlaceCd != 'null') $('#' + targetBizPlaceCd).val(bizPlaceCd);
		if(targetActCenterCd && targetActCenterCd != 'null') $('#' + targetActCenterCd).val(actCenterCd);
		if(targetActCenterNm && targetActCenterNm != 'null') $('#' + targetActCenterNm).val(actCenterNm);
	}
	// 그리드영역에서 호출
	else if(fromWhere== 'G')
	{	
		//alert(targetBizPlaceCd) ;
		if(targetBizPlaceCd && targetBizPlaceCd != 'null') $('#' + targetGridId).setCellValue(targetRowIndex, targetBizPlaceCd, bizPlaceCd, true); 
		if(targetActCenterCd && targetActCenterCd != 'null') $('#' + targetGridId).setCellValue(targetRowIndex, targetActCenterCd, actCenterCd, true); 
		if(targetActCenterNm && targetActCenterNm != 'null') $('#' + targetGridId).setCellValue(targetRowIndex, targetActCenterNm, actCenterNm, true); 		
	}	
}
// 활동센터조회 팝업
function popScenarioActCenter(myObj, fromWhere, snrNo, applyStartYear, bizPlaceCd, actCenterGbn, actCenterCd, actCenterNm
						 ,targetGridId, targetRowIndex, targetBizPlaceCd, targetActCenterCd, targetActCenterNm) 
{

	var params = '?FROM_WHERE=' + fromWhere
	           + '&SNR_NO='  + snrNo
	           + '&APPLY_START_YEAR'  + applyStartYear
	           + '&BIZ_PLACE_CD='  + bizPlaceCd
	           + '&ACT_CENTER_GBN=' + actCenterGbn
	           + '&ACT_CENTER_CD='  + actCenterCd
	           + '&ACT_CENTER_NM='  + actCenterNm
	           + '&TARGET_GRID_ID='   + targetGridId 
	           + '&TARGET_ROW_INDEX=' + targetRowIndex
	           + '&TARGET_BIZ_PLACE_CD=' + targetBizPlaceCd 
	           + '&TARGET_ACT_CENTER_CD=' + targetActCenterCd 
	           + '&TARGET_ACT_CENTER_NM=' + targetActCenterNm
	           + '&ENTER_YN=' + (myObj == null ? 'Y' : 'N') ;

	var url = Contextpath+'/jsp/scenario/common/SenarioActCenterPop.jsp'+ params; 
	
    $("#ifmPopup").attr("src", url);  
    $("#ifmPopup").attr("frameborder", 0);  
    $("#ifmPopup").css("height", 430);  
	$("#ifmPopup").css("width", 700);  
    $("#divPopup").dialog({  
            autoOpen: false,  
            height: 480,  
            width: 730, 
            modal: true,  
            title: '활동센터 조회(시나리오)',
            resizable: true,  
            closeOnEscape: true, 
             
            create: function (event, ui) {  
            },  
            open: function (event, ui) {  
            	 
            },  
            close: function () {  
            	 $("#ifmPopup").html('');
            },  
            cancel: function () {  
            }  
    });  
    $("#divPopup").dialog("open");
}

// 활동센터조회 팝업 더블클릭시
function scenarioActCenterDblClick(fromWhere, bizPlaceCd, actCenterCd, actCenterNm
							,targetGridId, targetRowIndex, targetBizPlaceCd, targetActCenterCd, targetActCenterNm)
{	
	// 조회영역에서 호출
	if(fromWhere== 'S')
	{
		if(targetBizPlaceCd && targetBizPlaceCd != 'null') $('#' + targetBizPlaceCd).val(bizPlaceCd);
		if(targetActCenterCd && targetActCenterCd != 'null') $('#' + targetActCenterCd).val(actCenterCd);
		if(targetActCenterNm && targetActCenterNm != 'null') $('#' + targetActCenterNm).val(actCenterNm);
	}
	// 그리드영역에서 호출
	else if(fromWhere== 'G')
	{	
		//alert(targetBizPlaceCd) ;
		if(targetBizPlaceCd && targetBizPlaceCd != 'null') $('#' + targetGridId).setCellValue(targetRowIndex, targetBizPlaceCd, bizPlaceCd, true); 
		if(targetActCenterCd && targetActCenterCd != 'null') $('#' + targetGridId).setCellValue(targetRowIndex, targetActCenterCd, actCenterCd, true); 
		if(targetActCenterNm && targetActCenterNm != 'null') $('#' + targetGridId).setCellValue(targetRowIndex, targetActCenterNm, actCenterNm, true); 		
	}	
}

// 품목조회 팝업
function popBaseItemMst(myObj, fromWhere, baseYear, actCenterCd, actCenterNm, itemTypeCd, itemCd, itemNm
						 ,targetGridId, targetRowIndex, targetItemTypeCd, targetItemCd, targetItemNm
						 ,targetItemUnitCd) 
{
	var params = '?FROM_WHERE=' + fromWhere
	           + '&BASE_YEAR='   + baseYear
	           + '&ACT_CENTER_CD=' + actCenterCd
	           + '&ACT_CENTER_NM=' + actCenterNm
	           + '&ITEM_TYPE_CD=' + itemTypeCd
	           + '&ITEM_CD=' + itemCd
	           + '&ITEM_NM=' + itemNm
	           + '&TARGET_GRID_ID='   + targetGridId  
	           + '&TARGET_ROW_INDEX=' + targetRowIndex
	           + '&TARGET_ITEM_TYPE_CD=' + targetItemTypeCd 
	           + '&TARGET_ITEM_CD=' + targetItemCd 
	           + '&TARGET_ITEM_NM=' + targetItemNm
	           + '&TARGET_ITEM_UNIT_CD=' + targetItemUnitCd
	           + '&ENTER_YN=' + (myObj == null ? 'Y' : 'N') ;

    var url = Contextpath+'/jsp/base/common/BaseItemMstPop.jsp' + params;
     
    $("#ifmPopup").attr("src", url);  
    $("#ifmPopup").attr("frameborder", 0);
    $("#ifmPopup").css("height", 430);  
	$("#ifmPopup").css("width", 900); 
    $("#divPopup").dialog({  
            autoOpen: false,  
            height: 480,  
            width: 925, 
            modal: true,  
            title: '품목 조회', 
            resizable: true,  
            closeOnEscape: true, 
             
            create: function (event, ui) {  
            },  
            open: function (event, ui) {  
            },  
            close: function () {  
            	$("#ifmPopup").html('');
            },  
            cancel: function () {  
            }  
     });  
     $("#divPopup").dialog("open"); 
}

// 품목조회 팝업 더블클릭시
function baseItemMstDblClick(fromWhere, itemTypeCd, itemCd, itemNm, itemUnitCd
							,targetGridId, targetRowIndex, targetItemTypeCd, targetItemCd, targetItemNm, targetItemUnitCd)
{	
	// 조회영역에서 호출
	if(fromWhere== 'S')
	{
		if(targetItemTypeCd && targetItemTypeCd != 'null') $('#' + targetItemTypeCd).val(itemTypeCd);
		if(targetItemCd && targetItemCd != 'null') $('#' + targetItemCd).val(itemCd);
		if(targetItemNm && targetItemNm != 'null') $('#' + targetItemNm).val(itemNm);
		if(targetItemUnitCd && targetItemUnitCd != 'null') $('#' + targetItemUnitCd).val(itemUnitCd);
	}
	// 그리드영역에서 호출
	else if(fromWhere== 'G')
	{	
		if(targetItemTypeCd && targetItemTypeCd != 'null') $('#' + targetGridId).setCellValue(targetRowIndex, targetItemTypeCd, itemTypeCd, true); 
		if(targetItemCd && targetItemCd != 'null') $('#' + targetGridId).setCellValue(targetRowIndex, targetItemCd, itemCd, true); 
		if(targetItemNm && targetItemNm != 'null') $('#' + targetGridId).setCellValue(targetRowIndex, targetItemNm, itemNm, true); 		
		if(targetItemUnitCd && targetItemUnitCd != 'null') $('#' + targetGridId).setCellValue(targetRowIndex, targetItemUnitCd, itemUnitCd, true); 		
	}	
}
// 계정과목조회 팝업
function popAccountTitle(myObj, fromWhere, baseYear, accntTypeCd, accntTitleCd, accntTitleNm
						 ,targetGridId, targetRowIndex, targetAccntTitleCd, targetAccntTitleNm) 
{
	var params = '?FROM_WHERE=' + fromWhere
	           + '&BASE_YEAR='  + baseYear
	           + '&ACCNT_TYPE_CD='  + accntTypeCd  
	           + '&ACCNT_TITLE_CD='  + accntTitleCd
	           + '&ACCNT_TITLE_NM='  + accntTitleNm
	           + '&TARGET_GRID_ID='   + targetGridId 
	           + '&TARGET_ROW_INDEX=' + targetRowIndex 
	           + '&TARGET_ACCNT_TITLE_CD=' + targetAccntTitleCd 
	           + '&TARGET_ACCNT_TITLE_NM=' + targetAccntTitleNm
	           + '&ENTER_YN=' + (myObj == null ? 'Y' : 'N') ;

	var url = Contextpath+'/jsp/base/common/AccountTitlePop.jsp'+ params; 
	
    $("#ifmPopup").attr("src", url);  
    $("#ifmPopup").attr("frameborder", 0);  
    $("#ifmPopup").css("height", 550);  
	$("#ifmPopup").css("width", 620);  
    $("#divPopup").dialog({  
            autoOpen: false,  
            height: 620,  
            width: 670, 
            modal: true,  
            title: '계정과목 조회',
            resizable: true,  
            closeOnEscape: true, 
             
            create: function (event, ui) {  
            },  
            open: function (event, ui) {  
            },  
            close: function () {  
            	$("#ifmPopup").html('');
            },  
            cancel: function () {  
            }  
    });  
    $("#divPopup").dialog("open");
}

// 계정과목조회 팝업 더블클릭시
function accountTitleDblClick(fromWhere, accntTypeCd, accntTitleCd, accntTitleNm
						 ,targetGridId, targetRowIndex, targetAccntTitleCd, targetAccntTitleNm)
{	 
	// 조회영역에서 호출
	if(fromWhere== 'S')
	{ 
		if(targetAccntTitleCd != 'null') $('#' + targetAccntTitleCd).val(accntTitleCd);
		if(targetAccntTitleNm != 'null') $('#' + targetAccntTitleNm).val(accntTitleNm);
	}
	// 그리드영역에서 호출
	else if(fromWhere== 'G')
	{	
		if(targetAccntTitleCd != 'null') $('#' + targetGridId).setCellValue(targetRowIndex, targetAccntTitleCd, accntTitleCd, true); 
		if(targetAccntTitleNm != 'null') $('#' + targetGridId).setCellValue(targetRowIndex, targetAccntTitleNm, accntTitleNm, true); 		
	}	
}
// 엑셀업로드 팝업
function popExcelUpload(myObj, fromWhere, title, baseYear, pgmId) 
{
	var params = '?FROM_WHERE=' + fromWhere
			   + '&TITLE='   + title
	           + '&BASE_YEAR='  + baseYear
	           + '&PGM_ID='  + pgmId 
	           + '&ENTER_YN=' + (myObj == null ? 'Y' : 'N') ;
	           
	var url = Contextpath+'/jsp/base/common/ExcelUpload.jsp'+ params; 
	
    $("#ifmPopup").attr("src", url);  
    $("#ifmPopup").attr("frameborder", 0);  
    $("#ifmPopup").css("height", 700);  
	$("#ifmPopup").css("width", 640);  
    $("#divPopup").dialog({  
            autoOpen: false,  
            height: 750,  
            width: 670, 
            modal: true,  
            title: '엑셀업로드 - ' + title,
            resizable: true,  
            closeOnEscape: true, 
             
            create: function (event, ui) {  
            },  
            open: function (event, ui) {  
            },  
            close: function () {  
            	$("#ifmPopup").html('');
            },  
            cancel: function () {  
            }  
    });  
    $("#divPopup").dialog("open");
}
// 그리드 OnChange이벤트 초기화
function initGridOnChange(gridId)
{
	//var arrColNms = colNms.split(' ').join('').colNms.split(',') ;
	var arrColNms = getGridColNms(gridId) ;

	// 동적변수 선언
	try{
		eval(gridId + '_INIT')
	}catch(e){
		eval(gridId + '_INIT='+false) ;
	} 

	if(!eval(gridId + '_INIT'))
	{
		for(var i=0 ; i < arrColNms.length ; i++)
		{
			if($('select[name='+arrColNms[i]+']'))
			{
				// 이벤트 삭제
				//$('select[name='+arrColNms[i]+']').off('change');

				$(document).on('change','select[name='+arrColNms[i]+']', function(){ 	

					var rowIndex = this.id.split('_')[0]-1 ;										
					eval(gridId + '_OnChange('+rowIndex+',"'+ this.name+'");');								

				});	 		
			}
		}
	}
	eval(gridId + '_INIT='+true) ;
}

// 그리드 쉼표구분자 컬럼아이디 반환 
function getGridColNms(gridId)
{
	var colList= $('#' + gridId).wizGrid('getGridParam', 'Columns');

	var colNms = [];

	for(var j = 1; j < colList.length; j++)
	{
		colNms.push(colList[j].name) ;
	}
		   
	return colNms ;
}

// 그리드 저장 체크
function iudSaveCheck(gridObj)
{
	var rCnt = gridObj.getRowCount(); 
	var iudCnt = 0 ;
	for(var i = 0 ; i < rCnt ; i++)
	{
		var iudFlag = gridObj.getCellValue(i, "IUDFLAG", true);
		
		if(iudFlag == 'I' || iudFlag == 'U' || iudFlag == 'D') 
			iudCnt++ ;		
		
		if(iudCnt > 0) return true ;
	}
	
	alert('저장 할 데이터가 없습니다');
	return false ;		
}

// 숫자여부체크 and 소수점 자리수 초과체크
// value : 값
// pointCount : 소수점 MAX 개수
// 비고 : 정수만 체크할때 pointCount값 없이 사용하면 됩니다
function validNumber(value, pointCount)
{
	if(isNaN(value)) return false ;

	if(value.indexOf('.') > -1 && pointCount){
	
		if(value.split('.').length == 2)
		{
			if(pointCount < value.split('.')[1].length)
			{
				return false ;
			}else{
				return true ;
			}			
		}
		else
		{
			return false ;
		}	    
	}
	return true ;
}