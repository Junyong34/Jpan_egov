
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
         $("#" + this.id).find('iframe').contents().find('body').html('');
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
         $("#" + this.id).find('iframe').contents().find('body').html('');
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
            close: function(){ 
         // iframe 초기화.
         $("#" + this.id).find('iframe').contents().find('body').html('');
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
function popScenarioActCenter(myObj, fromWhere, snrNo,  bizPlaceCd, actCenterGbn, actCenterCd, actCenterNm
						 ,targetGridId, targetRowIndex, targetBizPlaceCd, targetActCenterCd, targetActCenterNm) 
{

	var params = '?FROM_WHERE=' + fromWhere
	           + '&SNR_NO='  + snrNo 
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
function popScenarioItemMst(myObj, fromWhere, snrNo, actCenterCd, actCenterNm, itemTypeCd, itemCd, itemNm
						 ,targetGridId, targetRowIndex, targetItemTypeCd, targetItemCd, targetItemNm
						 ,targetItemUnitCd) 
{
	var params = '?FROM_WHERE=' + fromWhere
	           + '&SNR_NO='  + snrNo
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

    var url = Contextpath+'/jsp/scenario/common/ScenarioItemMstPop.jsp' + params;
     
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
            close: function(){ 
         // iframe 초기화.
         $("#" + this.id).find('iframe').contents().find('body').html('');
      },   
            cancel: function () {  
            }  
     });  
     $("#divPopup").dialog("open"); 
}

// 품목 팝업 더블클릭시
function scenarioItemMstDblClick(fromWhere, itemTypeCd, itemCd, itemNm, itemUnitCd
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
            close: function(){ 
         // iframe 초기화.
         $("#" + this.id).find('iframe').contents().find('body').html('');
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
function popAccountTitle(myObj, fromWhere, baseYear, accntTypeCd, accntTitleCd, accntTitleNm,actCenterAttrCd,processTypeCd
                   ,targetGridId, targetRowIndex, targetAccntTitleCd, targetAccntTitleNm) 
{
   var params = '?FROM_WHERE=' + fromWhere
              + '&BASE_YEAR='  + baseYear
              + '&ACCNT_TYPE_CD='  + accntTypeCd  
              + '&ACCNT_TITLE_CD='  + accntTitleCd
              + '&ACCNT_TITLE_NM='  + accntTitleNm
              + '&ACT_CENTER_ATTR_CD=' + actCenterAttrCd
              + '&PROCESS_TYPE_CD=' + processTypeCd
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
// 계정과목조회 팝업(시나리오)
function popScenarioAccountTitle(myObj, fromWhere, snrNo, accntTypeCd, accntTitleCd, accntTitleNm,actCenterAttrCd 
                   ,targetGridId, targetRowIndex, targetAccntTitleCd, targetAccntTitleNm) 
{
   var params = '?FROM_WHERE=' + fromWhere
              + '&SNR_NO='  + snrNo
              + '&ACCNT_TYPE_CD='  + accntTypeCd  
              + '&ACCNT_TITLE_CD='  + accntTitleCd
              + '&ACCNT_TITLE_NM='  + accntTitleNm
              + '&ACT_CENTER_ATTR_CD=' + actCenterAttrCd
              + '&TARGET_GRID_ID='   + targetGridId 
              + '&TARGET_ROW_INDEX=' + targetRowIndex 
              + '&TARGET_ACCNT_TITLE_CD=' + targetAccntTitleCd 
              + '&TARGET_ACCNT_TITLE_NM=' + targetAccntTitleNm
              + '&ENTER_YN=' + (myObj == null ? 'Y' : 'N') ;

   var url = Contextpath+'/jsp/scenario/common/ScenarioAccountTitlePop.jsp'+ params; 
   
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
            close: function(){ 
         // iframe 초기화.
         $("#" + this.id).find('iframe').contents().find('body').html('');
      }, 
            cancel: function () {  
            }  
    });  
    $("#divPopup").dialog("open");
}

// 계정과목조회 팝업 더블클릭시(시나리오)
function scenarioAccountTitlePopDblClick(fromWhere, accntTypeCd, accntTitleCd, accntTitleNm
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
// 시나리오생성 페이지로 이동
function moveSnrMainPage()
{
   // 메뉴이동
   parent.DS01_LABEL_onNodeDblClick("", "", {MENU_NM: '시나리오 생성', URL: '/PBCloud/20160603138157ScenarioMain_loadPage.do?'} ,
                  {
                   GRID1_ROW_INDEX: getParamdata('GRID1_ROW_INDEX'), 
                   GRID2_ROW_INDEX: getParamdata('GRID2_ROW_INDEX'),
                   GRID1_SCROLL_TOP: getParamdata('GRID1_SCROLL_TOP')
                  }
               );
}

// 기준정보생성 페이지로 이동
function moveBasMainPage()
{
   // 메뉴이동
   parent.DS01_LABEL_onNodeDblClick("", "", {MENU_NM: '기준정보 생성', URL: '/PBCloud/20160620151744BaseMain_loadPage.do?'} ,
                  {
                   GRID1_ROW_INDEX: getParamdata('GRID1_ROW_INDEX'), 
                   GRID2_ROW_INDEX: getParamdata('GRID2_ROW_INDEX'),
                   GRID1_SCROLL_TOP: getParamdata('GRID1_SCROLL_TOP')
                  }
               );
}

// 원가계산실행 페이지로 이동
function moveCostMainPage()
{
   // 메뉴이동
   parent.DS01_LABEL_onNodeDblClick("", "", {MENU_NM: '원가계산 실행', URL: '/PBCloud/20160629154045CostEstMain_loadPage.do?'} ,
                  {
                   GRID1_ROW_INDEX: getParamdata('GRID1_ROW_INDEX'), 
                   GRID2_ROW_INDEX: getParamdata('GRID2_ROW_INDEX'),
                   GRID1_SCROLL_TOP: getParamdata('GRID1_SCROLL_TOP')
                  }
               );
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
            close: function(){ 
         // iframe 초기화.
         $("#" + this.id).find('iframe').contents().find('body').html('');
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
/*
 * 정수인지 음수부호도 포함하여 검사
 */
function is_integer(x)
{
    //var reg = /^[-|+]?\d+$/;
     var reg = /^[+]?\d+$/;
    if(x == '')
    	return true;
    else 
    	return reg.test(x);
}
   // 엑셀업로드 팝업
 function  scenarioExcelUploadPop(myObj, fromWhere, title, snrNo, baseYear, pgmId)  
{
   var params = '?FROM_WHERE=' + fromWhere
            + '&TITLE='   + title
              + '&SNR_NO='  + snrNo
              + '&BASE_YEAR='  + baseYear
              + '&PGM_ID='  + pgmId 
              + '&ENTER_YN=' + (myObj == null ? 'Y' : 'N') ;
              
   var url = Contextpath+'/jsp/scenario/common/scenarioExcelUpload.jsp'+ params; 
   
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
            close: function(){ 
         // iframe 초기화.
         $("#" + this.id).find('iframe').contents().find('body').html('');
      }, 
            cancel: function () {  
            }  
    });  
    $("#divPopup").dialog("open");
}
// 기준정보출처조회 팝업
function popBaseYearFromPop(myObj, fromWhere, baseYear
                   ,targetGridId, targetRowIndex, targetBaseYear) 
{
   var params = '?FROM_WHERE=' + fromWhere
              + '&BASE_YEAR='  + baseYear 
              + '&TARGET_GRID_ID='   + targetGridId 
              + '&TARGET_ROW_INDEX=' + targetRowIndex 
              + '&TARGET_BASE_YEAR=' + targetBaseYear  
              + '&ENTER_YN=' + (myObj == null ? 'Y' : 'N') ;

   var url = Contextpath+'/jsp/base/BaseMainPop.jsp'+ params; 
   
    $("#ifmPopup").attr("src", url);  
    $("#ifmPopup").attr("frameborder", 0);  
    $("#ifmPopup").css("height", 330);  
    $("#ifmPopup").css("width", 350);  
    $("#divPopup").dialog({  
            autoOpen: false,  
            height: 390,  
            width: 380, 
            modal: true,  
            title: '기준연도 출처 조회',
            resizable: true,  
            closeOnEscape: true, 
             
            create: function (event, ui) {  
            },  
            open: function (event, ui) {  
            },  
            close: function(){ 
         	// iframe 초기화.
         	$("#" + this.id).find('iframe').contents().find('body').html('');
     		 }, 
            cancel: function () {  
            }  
    });  
    $("#divPopup").dialog("open");
}

// 기준정보출처조회 팝업 더블클릭시 
function baseYearFromPopDblClick(fromWhere,  baseYear
                   ,targetGridId, targetRowIndex, targetBaseYear)
{    
   // 조회영역에서 호출
   if(fromWhere== 'S')
   { 
      if(targetBaseYear != 'null') $('#' + targetBaseYear).val(baseYear); 
   }
   // 그리드영역에서 호출
   else if(fromWhere== 'G')
   {   
      if(targetBaseYear != 'null') $('#' + targetGridId).setCellValue(targetRowIndex, targetBaseYear, baseYear, true);       
   }   
}

// Scost.js 시스템 환경설정 값.=> 추후 DB연동 할 것
var SYS_CONF_RATE_DOT_CNT = 2 ;// 비율소숫점자리수         
var SYS_CONF_QTY_DOT_CNT = 4 ; // 수량소숫점자리수
var SYS_CONF_RSRC_APPLY_RATER_DOT_CNT = 4 ;// 자원적용율 소숫점자리수(계정과목관리 등)      
// 지정자리 반올림 (값, 자릿수)
function Round(n, pos) {
    var digits = Math.pow(10, pos);

    var sign = 1;
    if (n < 0) {
        sign = -1;
    }

    // 음수이면 양수처리후 반올림 한 후 다시 음수처리
    n = n * sign;
    var num = Math.round(n * digits) / digits;
    num = num * sign;

    return num.toFixed(pos);
}

// 지정자리 버림 (값, 자릿수)
function Floor(n, pos) {
    var digits = Math.pow(10, pos);

    var num = Math.floor(n * digits) / digits;

    return num.toFixed(pos);
}

// 지정자리 올림 (값, 자릿수)
function Ceiling(n, pos) {
    var digits = Math.pow(10, pos);

    var num = Math.ceil(n * digits) / digits;

    return num.toFixed(pos);
}
function GridGetCellValue(grid, rowIndex, colName, flag, eventName)      
{
    if(eventName == 'ON_EDIT_CELL')      
        return grid.getEditCellValue(rowIndex, colName, flag);
    else
        return grid.getCellValue(rowIndex, colName, flag);
}

// 그리드 셀 값 Setter - Scost.js
function GridSetCellValue(grid, rowIndex, colName, val, flag, eventName)
{
    if(eventName == 'ON_EDIT_CELL')      
        grid.setEditCellValue(rowIndex, colName, val, flag);
    else
        grid.setCellValue(rowIndex, colName, val, flag);
}

//로딩바 구현 박준용 추가
var isloadingCheck = true;
$(document).ready(function () { 

if(typeof parent.DS01_LABEL_onNodeDblClick == "function"){
if(parent.$('#MAIN_View').attr('id') == undefined){
						   $(window).ajaxStart(function(){
					 
				 if(isloadingCheck ==true){
				 
				$('body').append("<div id='visiablestop' style='position:absolute;top:0px;left:0px;width:100%;height:100%;background:white;z-index:888;filter:alpha(opacity=50);opacity:0.5;' > </div>");
				$('body').append("<img id='prcess_Loading' style='position:absolute;left:50%;top:50%;z-index:890;'/>");
				$('#prcess_Loading').attr('src',Contextpath+'/wizware/js/loading41.gif');  
				 
			   }else{
			   isloadingCheck = true; 
			   } 
				
			  })
			  .ajaxStop(function(){
				  $('#visiablestop').remove();
				  $('#prcess_Loading').remove();
			  });
    		 
    	 }else{   
			  $(window).ajaxStart(function(){
    	 
				 if(isloadingCheck ==true){
				 
				parent.$('#MAIN_View').append("<div id='visiablestop' style='position:absolute;top:0px;left:0px;width:100%;height:100%;background:white;z-index:888;filter:alpha(opacity=50);opacity:0.5;' > </div>");
				parent.$('#MAIN_View').append("<img id='prcess_Loading' style='position:absolute;left:50%;top:50%;z-index:890;'/>");
				parent.$('#prcess_Loading').attr('src',Contextpath+'/wizware/js/loading41.gif');  
				 
			   }else{
			   isloadingCheck = true; 
			   } 
				
			  })
			  .ajaxStop(function(){
				  parent.$('#visiablestop').remove();
				  parent.$('#prcess_Loading').remove();
			  });
    		
    	 }
}else{


}
 
		 
		 

  
});



