<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>editable(true)</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/PBCloud/wizware/css/zTreeStyle.css"/>
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/PBCloud/wizware/css/jquery-ui.min.css"/>
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/PBCloud/wizware/css/demo.css"/>
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/PBCloud/wizware/css/PBUIDefalut.css"/>
        <link rel="stylesheet" id="scostStyle"  type="text/css" href="/PBCloud/css/scostStyle.css"/>
        <link rel="stylesheet" id="wizcss"  type="text/css" href="/PBCloud/wizware/css/UI.WizGrid.css"/>
        <script type="text/javascript" src="/PBCloud/wizware/js/jquery-1.11.0.min.js"></script>
        <script type="text/javascript" src="/PBCloud/wizware/js/grid.locale-en.js"></script>
        <script type="text/javascript" src="/PBCloud/wizware/js/jquery-ui.js"></script>
        <script type="text/javascript" src="/PBCloud/wizware/js/jquery.ztree.all-3.5.js"></script>
        <script type="text/javascript" src="/PBCloud/wizware/js/HuskyEZCreator.js"></script>
        <script type="text/javascript" src="/PBCloud/wizware/js/paging.js"></script>
        <script type="text/javascript" src="/PBCloud/wizware/js/jquery.form.js"></script>
        <script type="text/javascript" src="/PBCloud/wizware/js/Wizware_WizGrid.js"></script>
        <script type="text/javascript" src="/PBCloud/wizware/js/WizGrid.js"></script>
        <script type="text/javascript" src="/PBCloud/wizware/js/wizware.js"></script>
        <script type="text/javascript" src="/PBCloud/js/Scost.js"></script>
        <script> 
        $(document).ready(function(){
        
        	$('#gbox_grid2').css('border', 0) ;
        }) ;
        
        // 추가
        function button5_OnClick(myObj)
        {
        	if(!validate()) return ;
        
        	if($('#S2_PURVEY_GBN_CD').val() == '01')
        	{
        		var rowIndex = grid1.getRowCount();
        		
        		grid1.insertRow();
        		grid1.setCellValue(rowIndex, "BASE_YEAR", $('#S2_BASE_YEAR').val(), true);				
        		grid1.setCellValue(rowIndex, "PURVEY_GBN_CD", "01", true);				
        	}
        	else if($('#S2_PURVEY_GBN_CD').val() == '02')
        	{
        		var rowIndex = grid2.getRowCount();		
        
        		grid2.insertRow();
        		grid2.setCellValue(rowIndex, "BASE_YEAR", $('#S2_BASE_YEAR').val(), true);				
        		grid2.setCellValue(rowIndex, "PURVEY_GBN_CD", "02", true);						
        	}	
        }
        
        // 조회
        function button1_OnClick(myObj)
        {
        	if(!validate()) return ;
        	
        	if($('#S2_PURVEY_GBN_CD').val() == '01')
        	{
        		// 초기화
        		G1_CURR_ROW_INDEX = -1 ;
        		
        		selectList.submit(false); 	
        	}
        	else if($('#S2_PURVEY_GBN_CD').val() == '02')
        	{
        		// 초기화
        		G2_CURR_ROW_INDEX = -1 ;
        		
        		selectList2.submit(false); 	
        	}	
        	
        }
        
        // 생산탭 이동
        function tab1_Page1_OnClick(myObj)
        {
        	$('#S2_PURVEY_GBN_CD').val('01');	
        	$('#S2_MAKE_GBN_CD').attr('disabled', false);
        }
        
        // 구매탭 이동
        function tab1_Page2_OnClick(myObj)
        {
        	$('#S2_PURVEY_GBN_CD').val('02');
        	$('#S2_MAKE_GBN_CD').val('Z');
        	$('#S2_MAKE_GBN_CD').attr('disabled', true);
        }
        
        
        var G1_CURR_ROW_INDEX = -1 ;
        function grid1_OnChange(rowIndex, colName)
        {
        	G1_CURR_ROW_INDEX = rowIndex ;
        
        	// 삭제 체크박스 부분 이벤트 해제.
        	if(colName == 'cb') return ;
        	
        	var iudFlag = grid1.getCellValue(rowIndex, 'IUDFLAG', true);
        	var MAKE_GBN_CD = grid1.getCellValue(rowIndex, 'MAKE_GBN_CD', true);
        	
        	if(iudFlag == 'I')
        	{
        		// 품목코드 Readonly 해제
        		setCellOpt(rowIndex, grid1.getColumnIndex('ITEM_CD'));	
        	}
        
        	// 생산구분	
        	if(colName == 'MAKE_GBN_CD')
        	{
        		if(MAKE_GBN_CD == '01') // 자체생산
        		{
        		}
        		else if(MAKE_GBN_CD == '02') // 외주생산
        		{
        			var rowId = grid1.getGridRowId(rowIndex) ;
        /*	alert(rowId);
        	grid1.setCell(rowId, 'ACT_CENTER_01', null);
        	grid1.setCell(rowId, 'ACT_CENTER_02', null);
        	grid1.setCell(rowId, 'ACT_CENTER_03', null);
        	grid1.setCell(rowId, 'ACT_CENTER_04', null);
        	grid1.setCell(rowId, 'ACT_CENTER_05', null);
        	grid1.setCell(rowId, 'ACT_CENTER_06', null);
        	grid1.setCell(rowId, 'ACT_CENTER_07', null);
        	grid1.setCell(rowId, 'ACT_CENTER_08', null);
        	grid1.setCell(rowId, 'ACT_CENTER_09', null);
        	grid1.setCell(rowId, 'ACT_CENTER_10', null);
        	
        	grid1.setCell(rowId, 'ACT_CENTER_NM_01', null);
        	grid1.setCell(rowId, 'ACT_CENTER_NM_02', null);
        	grid1.setCell(rowId, 'ACT_CENTER_NM_03', null);
        	grid1.setCell(rowId, 'ACT_CENTER_NM_04', null);
        	grid1.setCell(rowId, 'ACT_CENTER_NM_05', null);
        	grid1.setCell(rowId, 'ACT_CENTER_NM_06', null);
        	grid1.setCell(rowId, 'ACT_CENTER_NM_07', null);
        	grid1.setCell(rowId, 'ACT_CENTER_NM_08', null);
        	grid1.setCell(rowId, 'ACT_CENTER_NM_09', null);
        	grid1.setCell(rowId, 'ACT_CENTER_NM_10', null);
        */	
        
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_01'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_02'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_03'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_04'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_05'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_06'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_07'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_08'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_09'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_10'), null, true);
        
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_NM_01'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_NM_02'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_NM_03'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_NM_04'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_NM_05'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_NM_06'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_NM_07'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_NM_08'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_NM_09'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_NM_10'), null, true);
        			
        			
        		}
        	}
        	
        	if(colName == 'BIZ_PLACE_CD')
        	{
        		alert('1');
        	}
        }	
        
        var G2_CURR_ROW_INDEX = -1 ;
        function grid2_OnChange(rowIndex, colName)
        {
        	G2_CURR_ROW_INDEX = rowIndex ;
        
        	// 삭제 체크박스 부분 이벤트 해제.
        	if(colName == 'cb') return ;
        	
        	var iudFlag = grid2.getCellValue(rowIndex, 'IUDFLAG', true);
        	if(iudFlag == 'I')
        	{
        		// 품목코드 Readonly 해제
        		setCellOpt(rowIndex, grid2.getColumnIndex('ITEM_CD'));	
        	}
        
        }
        
        // 저장
        function button4_OnClick(myObj)
        {
            var rowCount = grid1.getRowCount();
                
            for(var rowIndex = 0; rowIndex <= rowCount; rowIndex++)
            {
                var iudFlag = grid1.getCellValue(rowIndex, 'IUDFLAG', true);
                                      
                if(iudFlag == 'I' || iudFlag == 'U')
                {
        			// 계정유형     
        			//var ACCNT_TYPE_CD = grid1.getCellValue(rowIndex, 'ACCNT_TYPE_CD', true);	    			
                                
          		}
          	}   	
          	 	
        	if($('#S2_PURVEY_GBN_CD').val() == '01')
        	{
        		saveList.submit(false); 
        	}
        	else if($('#S2_PURVEY_GBN_CD').val() == '02')
        	{
        		saveList2.submit(false);
        	}  	 	
            
        }
        
        // 활동센터조회
        function popActCenter(rowId,rowIndex,suffix)
        {
        	if(!validate()) return;
        
            var iudFlag = grid1.getCellValue(rowIndex, 'IUDFLAG', true);                           
            
            /*** 활동센터조회 팝업 시작 ***/
        	   
            // 필수 수정
        	var fromWhere = 'G' ; // S:조회조건, G:그리드
        		
        	// 조회조건 영역
        	var baseYear = grid1.getCellValueAfterSave(rowIndex , "BASE_YEAR", true);
        	var bizPlaceCd = grid1.getCellValueAfterSave(rowIndex , "BIZ_PLACE_CD", true);	
        	var actCenterGbn = '';
        	var actCenterCd = '';
        	var actCenterNm = '';
        		
        	// 반환정보 입력 영역 (부모창)
            var targetGridId   = 'grid1';    // 반환대상 그리드 아이디(그리드가 아닌경우 '') 
            var targetRowIndex = rowIndex;   // 반환대상 그리드 Row Index (그리드가 아닌경우 '') 
            var targetBizPlaceCd = '';       // 반환대상 사업장코드 필드명 (대상없으면 '') 
            var targetActCenterCd = 'ACT_CENTER_'+suffix;  // 반환대상 활동센터코드 필드명 (대상없으면 '')   
            var targetActCenterNm = 'ACT_CENTER_NM_'+suffix;  // 반환대상 활동센터명칭 필드명 (대상없으면 '')   
        
        	if(!bizPlaceCd)
        	{
        		alert('사업장을 선택하세요');		
        		return false ;	
        	}	
        
        	// 활동센터조회 팝업 - Scost.js
            popBaseActCenter(rowId, fromWhere, baseYear, bizPlaceCd, actCenterGbn, actCenterCd, actCenterNm
        		              ,targetGridId, targetRowIndex, targetBizPlaceCd, targetActCenterCd, targetActCenterNm) // 필수 고정
        		
        	/*** 활동센터조회 팝업 종료 ***/
        }
        
        function validate()
        {
        	if($('#S2_BASE_YEAR').val() == 'Z')
        	{
        		alert('기준연도를 선택하세요');
        		$('#S2_BASE_YEAR').focus();
        		return false ;	
        	}	
        	return true ;
        }
        function grid1_OnCellSelect(rowIndex, Colname, cellcontent, e)
        {
        	if(Colname == 'ITEM_TYPE_CD') return ;
        	if(Colname == 'ITEM_UNIT') return ;
        	if(Colname == 'PURVEY_GBN_CD') return;
        	if(Colname == 'MAKE_GBN_CD') return ;
        	if(Colname == 'BIZ_PLACE_CD') return ;
        	if(Colname == 'MAKE_GBN_CD') return ;
        	
        	grid1_OnChange(rowIndex, Colname) ;
        }
        function grid2_OnCellSelect(rowIndex, Colname, cellcontent, e)
        {
        	if(Colname == 'ITEM_TYPE_CD') return ;
        	if(Colname == 'ITEM_UNIT') return ;
        	if(Colname == 'PURVEY_GBN_CD') return ;
        	if(Colname == 'MAKE_GBN_CD') return ;
        	if(Colname == 'BIZ_PLACE_CD') return ;
        	if(Colname == 'BUY_TYPE_CD') return ;
        	if(Colname == 'MONEY_UNIT') return ;
        
        	grid2_OnChange(rowIndex, Colname) ;
        }
        function ACT_CENTER_NM_01_POPUP_cellonclick_grid1(rowId,rowIndex)
        {
        	popActCenter(rowId,rowIndex,'01');
        }
        function ACT_CENTER_NM_02_POPUP_cellonclick_grid1(rowId,rowIndex)
        {
        	popActCenter(rowId,rowIndex,'02');
        }
        function ACT_CENTER_NM_03_POPUP_cellonclick_grid1(rowId,rowIndex)
        {
        	popActCenter(rowId,rowIndex,'03');
        }
        function ACT_CENTER_NM_04_POPUP_cellonclick_grid1(rowId,rowIndex)
        {
        	popActCenter(rowId,rowIndex,'04');
        }
        function ACT_CENTER_NM_05_POPUP_cellonclick_grid1(rowId,rowIndex)
        {
        	popActCenter(rowId,rowIndex,'05');
        }
        function ACT_CENTER_NM_06_POPUP_cellonclick_grid1(rowId,rowIndex)
        {
        	popActCenter(rowId,rowIndex,'06');
        }
        function ACT_CENTER_NM_07_POPUP_cellonclick_grid1(rowId,rowIndex)
        {
        	popActCenter(rowId,rowIndex,'07');
        }
        function ACT_CENTER_NM_08_POPUP_cellonclick_grid1(rowId,rowIndex)
        {
        	popActCenter(rowId,rowIndex,'08');
        }
        function ACT_CENTER_NM_09_POPUP_cellonclick_grid1(rowId,rowIndex)
        {
        	popActCenter(rowId,rowIndex,'09');
        }
        function ACT_CENTER_NM_10_POPUP_cellonclick_grid1(rowId,rowIndex)
        {
        	popActCenter(rowId,rowIndex,'10');
        }
        function grid1_OnLoadComplete()
        {
            // 그리드 OnChange이벤트 초기화
        	//initGridOnChange('grid1') ;		
        }
        function grid2_OnLoadComplete()
        {
            // 그리드 OnChange이벤트 초기화
        	initGridOnChange('grid2') ;		
        }
        
        function grid1_OnInitGrid()
        {
         // 그리드 OnChange이벤트 초기화
        	initGridOnChange('grid1') ;		
        }
        
        function button7_OnClick(myObj)
        {
        	grid1.setCellValue(0, grid1.getColumnIndex('ACT_CENTER_01'), null, true);
        	grid1.setCellValue(0, grid1.getColumnIndex('ACT_CENTER_02'), null, true);
        	grid1.setCellValue(0, grid1.getColumnIndex('ACT_CENTER_03'), null, true);
        	grid1.setCellValue(0, grid1.getColumnIndex('ACT_CENTER_04'), null, true);
        	grid1.setCellValue(0, grid1.getColumnIndex('ACT_CENTER_05'), null, true);
        	grid1.setCellValue(0, grid1.getColumnIndex('ACT_CENTER_06'), null, true);
        	grid1.setCellValue(0, grid1.getColumnIndex('ACT_CENTER_07'), null, true);
        	grid1.setCellValue(0, grid1.getColumnIndex('ACT_CENTER_08'), null, true);
        	grid1.setCellValue(0, grid1.getColumnIndex('ACT_CENTER_09'), null, true);
        	grid1.setCellValue(0, grid1.getColumnIndex('ACT_CENTER_10'), null, true);
        
        	grid1.setCellValue(0, grid1.getColumnIndex('ACT_CENTER_NM_01'), null, true);
        	grid1.setCellValue(0, grid1.getColumnIndex('ACT_CENTER_NM_02'), null, true);
        	grid1.setCellValue(0, grid1.getColumnIndex('ACT_CENTER_NM_03'), null, true);
        	grid1.setCellValue(0, grid1.getColumnIndex('ACT_CENTER_NM_04'), null, true);
        	grid1.setCellValue(0, grid1.getColumnIndex('ACT_CENTER_NM_05'), null, true);
        	grid1.setCellValue(0, grid1.getColumnIndex('ACT_CENTER_NM_06'), null, true);
        	grid1.setCellValue(0, grid1.getColumnIndex('ACT_CENTER_NM_07'), null, true);
        	grid1.setCellValue(0, grid1.getColumnIndex('ACT_CENTER_NM_08'), null, true);
        	grid1.setCellValue(0, grid1.getColumnIndex('ACT_CENTER_NM_09'), null, true);
        	grid1.setCellValue(0, grid1.getColumnIndex('ACT_CENTER_NM_10'), null, true);
         /* grid1.setCell("R1", 'ACT_CENTER_01', null);
        					grid1.setCell("R1", 'ACT_CENTER_02', null);
        					grid1.setCell("R1", 'ACT_CENTER_03', null);
        					grid1.setCell("R1", 'ACT_CENTER_04', null);
        					grid1.setCell("R1", 'ACT_CENTER_05', null);
        					grid1.setCell("R1", 'ACT_CENTER_06', null);
        					grid1.setCell("R1", 'ACT_CENTER_07', null);
        					grid1.setCell("R1", 'ACT_CENTER_08', null);
        					grid1.setCell("R1", 'ACT_CENTER_09', null);
        					grid1.setCell("R1", 'ACT_CENTER_10', null);
        					
        					grid1.setCell("R1", 'ACT_CENTER_NM_01', null);
        					grid1.setCell("R1", 'ACT_CENTER_NM_02', null);
        					grid1.setCell("R1", 'ACT_CENTER_NM_03', null);
        					grid1.setCell("R1", 'ACT_CENTER_NM_04', null);
        					grid1.setCell("R1", 'ACT_CENTER_NM_05', null);
        					grid1.setCell("R1", 'ACT_CENTER_NM_06', null);
        					grid1.setCell("R1", 'ACT_CENTER_NM_07', null);
        					grid1.setCell("R1", 'ACT_CENTER_NM_08', null);
        					grid1.setCell("R1", 'ACT_CENTER_NM_09', null);
        					grid1.setCell("R1", 'ACT_CENTER_NM_10', null);
        					
        						grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_01'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_02'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_03'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_04'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_05'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_06'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_07'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_08'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_09'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_10'), null, true);
        
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_NM_01'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_NM_02'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_NM_03'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_NM_04'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_NM_05'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_NM_06'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_NM_07'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_NM_08'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_NM_09'), null, true);
        	grid1.setCellValue("R1", grid1.getColumnIndex('ACT_CENTER_NM_10'), null, true);
        	
        	*/
        			
        }
        
        </script>
        
</head>
<body >
    <div id="pbview" >
<form id='frm' name='frm' >
    <div id="hiddenvariable" style="display:none">
        <input style="display:none" id="out_message" name="out_message"  value="" ></input>
        <input style="display:none" id="out_code" name="out_code"  value="" ></input>
    </div>
        <input type='hidden'  id="InWIzJsonParma" name="InWIzJsonParma"  value="" ></input>
        	<div  class="titleLabel" style=" position: absolute; top:0px; left:0px;  width:1250px; height:48px; " id="label1" name="label1"><label style=" line-height:50px; " >    품목관리     </label>    </div>

        	<input  class="TextBox-S" style=" position: absolute; top:16px; left:162px;  width:97px; height:17px; " id="S2_PURVEY_GBN_CD" name="S2_PURVEY_GBN_CD" type="hidden" value="01" maxlength="50" tabindex="0"  onchange="wizutil.CheckNumberValue(this);">    </input>

        	<div   id="user1" style="border-width:1px; border:solid; background-color:White; color:Black;border-color:Black;text-align:left; position: absolute; top:16px; left:279px;  width:77px; height:22px; " >
<div id="divPopup" name="divPopup" style="display:none;background-color:white;" >
	<iframe id="ifmPopup" name="ifmPopup" style="width:1050px;height:540px;" scrolling="no">
	</iframe>
</div></div>

      <div  id="group1" name="group1"  class="search-box" style=" position: absolute; top:51px; left:1px;  width:1250px; height:60px; " >  
          <div  id="group2" name="group2"  class="search-box-inner" style=" position: absolute; top:10px; left:4px;  width:1240px; height:40px; " >  
                        	<select  style="border-width:1px; background-color:White; color:Black;border-color:Black;text-align:left;  font-family:굴림체;font-size:9.75pt;position:absolute;left:88px;top:10px; width:102px; height:21px; " id="S2_BASE_YEAR" name="S2_BASE_YEAR" tabindex="0" eltype="Combo" >
            	</select>
                        	<div  class="searchLabel" style="position:absolute;left:247px;top:10px; width:100px; height:19px; " id="label3" name="label3"><label style=" line-height:21px; " >            품목구분             </label>            </div>
                        	<input  class="TextBox-S" style="position:absolute;left:536px;top:10px; width:59px; height:17px; " id="S2_ITEM_CD" name="S2_ITEM_CD" type="text" maxlength="50" tabindex="0"  onchange="wizutil.CheckNumberValue(this);">            </input>
                        	<div  class="searchLabel" style="position:absolute;left:467px;top:10px; width:100px; height:19px; " id="label4" name="label4"><label style=" line-height:21px; " >            품번/명             </label>            </div>
                        	<input  class="btn-search-s" style="position:absolute;left:703px;top:10px; width:26px; height:21px; " id="button6" name="button6" type="button" tabindex="0"  ></input>
                        	<div  class="searchLabel" style="position:absolute;left:786px;top:10px; width:100px; height:19px; " id="label5" name="label5"><label style=" line-height:21px; " >            생산구분             </label>            </div>
                        	<div  class="searchLabel" style="position:absolute;left:24px;top:10px; width:100px; height:19px; " id="label2" name="label2"><label style=" line-height:21px; " >            기준연도             </label>            </div>
                        	<input  class="TextBox-S" style="position:absolute;left:605px;top:10px; width:87px; height:17px; " id="S2_ITEM_NM" name="S2_ITEM_NM" type="text" maxlength="50" tabindex="0"  onchange="wizutil.CheckNumberValue(this);">            </input>
                        	<select  style="border-width:1px; background-color:White; color:Black;border-color:Black;text-align:left;  font-family:굴림체;font-size:9.75pt;position:absolute;left:311px;top:10px; width:102px; height:21px; " id="S2_ITEM_TYPE_CD" name="S2_ITEM_TYPE_CD" tabindex="0" eltype="Combo" >
            	</select>
                        	<input  class="btn3" style="position:absolute;left:1076px;top:10px; width:101px; height:21px; " id="button1" name="button1" type="button" value="조회" tabindex="0"  onclick="button1_OnClick(this);" ></input>
                        	<select  style="border-width:1px; background-color:White; color:Black;border-color:Black;text-align:left;  font-family:굴림체;font-size:9.75pt;position:absolute;left:867px;top:10px; width:102px; height:21px; " id="S2_MAKE_GBN_CD" name="S2_MAKE_GBN_CD" tabindex="0" eltype="Combo" >
            	</select>
          </div>      </div>
      <div  id="group3" name="group3"  class="button-box" style=" position: absolute; top:112px; left:221px;  width:1029px; height:40px; z-index:9999;" >  
                	<input  class="btn4" style="position:absolute;left:894px;top:9px; width:101px; height:21px; " id="button2" name="button2" type="button" value="엑셀다운로드" tabindex="0"  ></input>
                	<input  class="btn4" style="position:absolute;left:786px;top:9px; width:101px; height:21px; " id="button3" name="button3" type="button" value="엑셀일괄등록" tabindex="0"  ></input>
                	<input  class="btn" style="position:absolute;left:642px;top:10px; width:61px; height:20px; " id="button5" name="button5" type="button" value="추가" tabindex="0"  onclick="button5_OnClick(this);" ></input>
                	<input  class="btn" style="position:absolute;left:710px;top:10px; width:61px; height:20px; " id="button4" name="button4" type="button" value="저장" tabindex="0"  onclick="button4_OnClick(this);" ></input>
      </div>
        <div id="tab1" style=" position: absolute; top:136px; left:0px;  width:1250px; height:500px;    border:0px solid ; border-color:Black;   " class='tab_Class' >
    		<ul style=" height:24px;margin:0; padding:0;  " >
        		<li class='on'  style=" color:white; width:94px;height:24px; ">    <span  style="width:92px;height:23px;background-color:LightGray; border:0px solid Black;  font-family:Microsoft Sans Serif;font-size:10pt; color:white; ">생산품목</span></li>
        		<li  style=" color:white; width:92px;height:24px; ">    <span  style="width:90px;height:23px;background-color:LightGray; border:0px solid Black;  font-family:Microsoft Sans Serif;font-size:10pt; color:white; ">구매품목</span></li>
    		</ul>
    			<div id="tab1_Page1" class=" Mulittab1" style="position:relative;black:none;width:1248px; height:478px; background-color:White; border:0px solid Black; " >
    			                <div id="grid1_gdiv"  style="position:absolute;left:2px;top:2px;width:1240px;height:485px;" >
		         <table id="grid1" elType='Grid' ></table>
	        </div>

    			</div>
    			<div id="tab1_Page2" class=" Mulittab1" style="position:relative;display:none;width:1248px; height:478px; background-color:White; border:0px solid Black; " >
    			                <div id="grid2_gdiv"  style="position:absolute;left:2px;top:2px;width:1240px;height:485px;" >
		         <table id="grid2" elType='Grid' ></table>
	        </div>

    			</div>
        </div>
<script>
    $('#tab1 li:first').find('span:first').css('background-color','SteelBlue');
    $('.tab_Class').show();
$(function(){
    $('#tab1 li').click(function(){
        $(this).parent().parent().find('.Mulittab1').hide();
        $(this).parent().parent().find('.Mulittab1').eq($(this).index()).show();
        $(this).parent().find('li').removeClass('on');
        $(this).parent().find('span').css('background-color','LightGray');
        $(this).addClass('on');
         $(this).hasClass('on') ? $(this).parent().find('span').eq($(this).index()).css('background-color','SteelBlue') : false;
    });
});
</script>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:703px; left:375px;  width:101px; height:21px; " id="button7" name="button7" type="button" value="button" tabindex="0"  onclick="button7_OnClick(this);" ></input>

        	<input  class="TextBox-S" style=" position: absolute; top:735px; left:555px;  width:97px; height:17px; " id="S1_ITEM_CD" name="S1_ITEM_CD" type="text" maxlength="50" tabindex="0"  onchange="wizutil.CheckNumberValue(this);">    </input>

        	<input  class="TextBox-S" style=" position: absolute; top:743px; left:708px;  width:97px; height:17px; " id="S1_ACT_CENTER_NM_01" name="S1_ACT_CENTER_NM_01" type="text" maxlength="50" tabindex="0"  onchange="wizutil.CheckNumberValue(this);">    </input>

</form>	
<form id='fileform' name='fileform' encType='multipart/form-data'>
	<div style='display:none'>
	<iframe id='FileDownload'  name='FileDownload' style='border-width:0px;' src='' frameborder='0' ></iframe>
	</div>
	
</form>
    </div>
</body>
<script type="text/javascript" >
    var ChkNumberColumns= [];
    var RadioProp= {};
    var CheckProp= {};
    var ComboObjProp= {"S2_BASE_YEAR":{"CODE":"CBO_CD","NAME":"CBO_NM","DEFAULT":"::선택::"}, "S2_ITEM_TYPE_CD":{"CODE":"CBO_CD","NAME":"CBO_NM","DEFAULT":"::전체::"}, "S2_MAKE_GBN_CD":{"CODE":"CBO_CD","NAME":"CBO_NM","DEFAULT":"::전체::"}, "grid2_ITEM_TYPE_CD":{"CODE":"CBO_CD","NAME":"CBO_NM","DEFAULT":"선택"}, "grid2_ITEM_UNIT":{"CODE":"CBO_CD","NAME":"CBO_NM","DEFAULT":"선택"}, "grid2_PURVEY_GBN_CD":{"CODE":"CBO_CD","NAME":"CBO_NM","DEFAULT":"선택"}, "grid2_BIZ_PLACE_CD":{"CODE":"CBO_CD","NAME":"CBO_NM","DEFAULT":"선택"}, "grid2_BUY_PLACE_CD":{"CODE":"CBO_CD","NAME":"CBO_NM","DEFAULT":"선택"}, "grid2_BUY_TYPE_CD":{"CODE":"CBO_CD","NAME":"CBO_NM","DEFAULT":"선택"}, "grid2_MONEY_UNIT":{"CODE":"CBO_CD","NAME":"CBO_NM","DEFAULT":"선택"}, "grid1_ITEM_TYPE_CD":{"CODE":"CBO_CD","NAME":"CBO_NM","DEFAULT":"선택"}, "grid1_ITEM_UNIT":{"CODE":"CBO_CD","NAME":"CBO_NM","DEFAULT":"선택"}, "grid1_PURVEY_GBN_CD":{"CODE":"CBO_CD","NAME":"CBO_NM","DEFAULT":"선택"}, "grid1_MAKE_GBN_CD":{"CODE":"CBO_CD","NAME":"CBO_NM","DEFAULT":"선택"}, "grid1_BIZ_PLACE_CD":{"CODE":"CBO_CD","NAME":"CBO_NM","DEFAULT":"선택"}};
    var RequiredProp= {};
    var CellEditoption= {};
    var InitItems= {"S2_BASE_YEAR":":", "S2_ITEM_TYPE_CD":":", "S2_MAKE_GBN_CD":":", "grid2_ITEM_TYPE_CD":":", "grid2_ITEM_UNIT":":", "grid2_PURVEY_GBN_CD":":", "grid2_BIZ_PLACE_CD":":", "grid2_BUY_PLACE_CD":":", "grid2_BUY_TYPE_CD":":", "grid2_MONEY_UNIT":":", "grid1_ITEM_TYPE_CD":":", "grid1_ITEM_UNIT":":", "grid1_PURVEY_GBN_CD":":", "grid1_MAKE_GBN_CD":":", "grid1_BIZ_PLACE_CD":":"};
    var sbmInit = new SubMission( frm, "/PBCloud/2016041114478ItemMasterMgr_comboItemMaster.do","","S2_BASE_YEAR:SEL3,S2_ITEM_TYPE_CD:SEL9,S2_MAKE_GBN_CD:SEL11,grid1_ITEM_TYPE_CD:SEL8,grid1_ITEM_UNIT:SEL9,grid1_BIZ_PLACE_CD:SEL12,grid1_MAKE_GBN_CD:SEL11,grid2_BIZ_PLACE_CD:SEL12,grid2_ITEM_TYPE_CD:SEL9,grid2_ITEM_TYPE_CD:SEL6,grid2_PURVEY_GBN_CD:SEL8,grid2_BUY_PLACE_CD:SEL14,grid2_BUY_TYPE_CD:SEL16,grid2_MONEY_UNIT:SEL16","");
    var aaa33 = new SubMission( frm, "/PBCloud/20160412101097MainScreenMgr_loadPage.do","","S_MENU_NM:SEL1","");
    var bbb = new SubMission( frm, "/PBCloud/20160401107892DEMOJUN_SAWONLIST.do","S1[A]:S1","grid1:SEL7","");
    var selectList = new SubMission( frm, "/PBCloud/2016041114478ItemMasterMgr_selectItemMaster.do","S2[A]:S2","grid1:SEL2","");
    var saveList = new SubMission( frm, "/PBCloud/2016041114478ItemMasterMgr_saveItemMaster.do","S2[A]:S2,grid1[C]:S1","grid1:SEL2","");
    var selectList2 = new SubMission( frm, "/PBCloud/2016041114478ItemMasterMgr_selectItemMaster.do","S2[A]:S2","grid2:SEL4","");
    sbmInit.InitCombo("S2_BASE_YEAR,S2_ITEM_TYPE_CD,S2_MAKE_GBN_CD");
    var grid2= jQuery("#grid2");
    grid2.setDatasetName("S1");
        grid2.addBind("S1_ITEM_CD", "ITEM_CD");
    var grid1= jQuery("#grid1");
    grid1.setDatasetName("S1");
        grid1.addBind("S1_ITEM_CD", "ITEM_CD");
        grid1.addBind("S1_ACT_CENTER_NM_01", "ACT_CENTER_NM_01");
    var grid2_Data = [];
    var grid1_Data = [];
    function initGrids() { 
        grid2.wizGrid({   
        	data:grid2_Data,   
        	height:446,  
        	width:1240,    
        	multiselect:true,       
        	cellEdit:true,   
        	Names: ["ROWID","S","기준연도","품목구분(*)","품목코드(*)","품목명(*)","품목규격","품목모델","품목단위(*)","조달구분","사업장","구매처","구매형태","통화","단가","부대비용율","등록자","등록일자","수정자","수정일자"],
        	Columns: [  
        	 {index: "id", name:"id", width:75, key:true, hidden:true }        	, {index:'IUDFLAG',name:'IUDFLAG',editable:false,width:10,formatter:'select',edittype:'select',editoptions:{ value:':;I:I;U:U;D:D',defaultValue:'' }}
        	 ,{index:"BASE_YEAR",name:"BASE_YEAR",width:100,hidden:true,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'BASE_YEAR'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"ITEM_TYPE_CD",name:"ITEM_TYPE_CD",width:100,edittype:"select",formatter:"select",editoptions:{value:InitItems.grid2_ITEM_TYPE_CD} ,editable:true ,cellattr:rowspan,align:"center"}
        	 ,{index:"ITEM_CD",name:"ITEM_CD",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'ITEM_CD'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"ITEM_NM",name:"ITEM_NM",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'ITEM_NM'  }  ] }  ,cellattr:rowspan,align:"left"}
        	 ,{index:"ITEM_SPEC",name:"ITEM_SPEC",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'ITEM_SPEC'  }  ] }  ,cellattr:rowspan,align:"left"}
        	 ,{index:"ITEM_MODEL",name:"ITEM_MODEL",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'ITEM_MODEL'  }  ] }  ,cellattr:rowspan,align:"left"}
        	 ,{index:"ITEM_UNIT",name:"ITEM_UNIT",width:100,edittype:"select",formatter:"select",editoptions:{value:InitItems.grid2_ITEM_UNIT} ,editable:true ,cellattr:rowspan,align:"center"}
        	 ,{index:"PURVEY_GBN_CD",name:"PURVEY_GBN_CD",width:100,edittype:"select",formatter:"select",editoptions:{value:InitItems.grid2_PURVEY_GBN_CD} ,editable:false ,cellattr:rowspan,align:"center"}
        	 ,{index:"BIZ_PLACE_CD",name:"BIZ_PLACE_CD",width:100,edittype:"select",formatter:"select",editoptions:{value:InitItems.grid2_BIZ_PLACE_CD} ,editable:true ,cellattr:rowspan,align:"center"}
        	 ,{index:"BUY_PLACE_CD",name:"BUY_PLACE_CD",width:100,edittype:"select",formatter:"select",editoptions:{value:InitItems.grid2_BUY_PLACE_CD} ,editable:true ,cellattr:rowspan,align:"center"}
        	 ,{index:"BUY_TYPE_CD",name:"BUY_TYPE_CD",width:100,edittype:"select",formatter:"select",editoptions:{value:InitItems.grid2_BUY_TYPE_CD} ,editable:true ,cellattr:rowspan,align:"center"}
        	 ,{index:"MONEY_UNIT",name:"MONEY_UNIT",width:100,edittype:"select",formatter:"select",editoptions:{value:InitItems.grid2_MONEY_UNIT} ,editable:true ,cellattr:rowspan,align:"center"}
        	 ,{index:"UNIT_PRICE",name:"UNIT_PRICE",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'UNIT_PRICE'  }  ] }  ,cellattr:rowspan,align:"right"}
        	 ,{index:"INCIDENT_COST_RATE",name:"INCIDENT_COST_RATE",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'INCIDENT_COST_RATE'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"INS_USER",name:"INS_USER",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'INS_USER'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"INS_DATE",name:"INS_DATE",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'INS_DATE'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"UPD_USER",name:"UPD_USER",width:100,hidden:true,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'UPD_USER'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"UPD_DATE",name:"UPD_DATE",width:100,hidden:true,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'UPD_DATE'  }  ] }  ,cellattr:rowspan,align:"center"}
	        ],
        	OnCellSelect : function(rowid, iCol, cellcontent, e) {  
        	 var rowIndex = grid2.getRowIndex(rowid);  
        	 var Colname =  grid2.getColumnName(iCol);  
        		grid2_OnCellSelect(rowIndex, Colname, cellcontent, e);  
        	}  
        	,OnEditCell : function(rowid, colname, value, iCol,iRow) {  
        	}  
        	,OnEnterRow : function(rowindex, selrow) {  
        	}  
        	,OnLoadComplete : function() {  
        		grid2_OnLoadComplete();  

	         var gridName ='grid2';
        		 $("#paginate_grid2").hide();
        		 
        		 
        		 
        		 
        	}  
        });

        grid1.wizGrid({   
        	data:grid1_Data,   
        	height:446,  
        	width:1240,    
        	multiselect:true,       
        	cellEdit:true,   
        	Names: ["ROWID","S","품목구분","품목코드","품목명","품목규격","품목모델","품목단위","조달구분","생산구분","사업장","활동센터코드1","활동센터1","","활동센터코드2","활동센터2","","활동센터코드3","활동센터3","","활동센터코드4","활동센터4","","활동센터코드5","활동센터5","","활동센터코드6","활동센터6","","활동센터코드7","활동센터7","","활동센터코드8","활동센터8","","활동센터코드9","활동센터9","","활동센터코드10","활동센터10","","등록자","등록일자","","기준연도"],
        	Columns: [  
        	 {index: "id", name:"id", width:75, key:true, hidden:true }        	, {index:'IUDFLAG',name:'IUDFLAG',editable:false,width:10,formatter:'select',edittype:'select',editoptions:{ value:':;I:I;U:U;D:D',defaultValue:'' }}
        	 ,{index:"ITEM_TYPE_CD",name:"ITEM_TYPE_CD",width:100,edittype:"select",formatter:"select",editoptions:{value:InitItems.grid1_ITEM_TYPE_CD} ,editable:true ,cellattr:rowspan,align:"center"}
        	 ,{index:"ITEM_CD",name:"ITEM_CD",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ITEM_CD'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"ITEM_NM",name:"ITEM_NM",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ITEM_NM'  }  ] }  ,cellattr:rowspan,align:"left"}
        	 ,{index:"ITEM_SPEC",name:"ITEM_SPEC",width:86,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ITEM_SPEC'  }  ] }  ,cellattr:rowspan,align:"left"}
        	 ,{index:"ITEM_MODEL",name:"ITEM_MODEL",width:89,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ITEM_MODEL'  }  ] }  ,cellattr:rowspan,align:"left"}
        	 ,{index:"ITEM_UNIT",name:"ITEM_UNIT",width:83,edittype:"select",formatter:"select",editoptions:{value:InitItems.grid1_ITEM_UNIT} ,editable:true ,cellattr:rowspan,align:"center"}
        	 ,{index:"PURVEY_GBN_CD",name:"PURVEY_GBN_CD",width:91,edittype:"select",formatter:"select",editoptions:{value:InitItems.grid1_PURVEY_GBN_CD} ,editable:false ,cellattr:rowspan,align:"center"}
        	 ,{index:"MAKE_GBN_CD",name:"MAKE_GBN_CD",width:87,edittype:"select",formatter:"select",editoptions:{value:InitItems.grid1_MAKE_GBN_CD} ,editable:true ,cellattr:rowspan,align:"center"}
        	 ,{index:"BIZ_PLACE_CD",name:"BIZ_PLACE_CD",width:100,edittype:"select",formatter:"select",editoptions:{value:InitItems.grid1_BIZ_PLACE_CD} ,editable:true ,cellattr:rowspan,align:"center"}
        	 ,{index:"ACT_CENTER_01",name:"ACT_CENTER_01",width:100,hidden:true,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ACT_CENTER_01'  }  ] }  ,cellattr:rowspan,align:"left"}
        	 ,{index:"ACT_CENTER_NM_01",name:"ACT_CENTER_NM_01",width:100,edittype:"text",editable:false, editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ACT_CENTER_NM_01'  }  ] } ,align:"center"}
        	 ,{index:"ACT_CENTER_NM_01_POPUP",name:"ACT_CENTER_NM_01_POPUP",width:25,edittype:"text",formatter:"popup",editable:false,imagePath:""}
        	 ,{index:"ACT_CENTER_02",name:"ACT_CENTER_02",width:100,hidden:true,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ACT_CENTER_02'  }  ] }  ,cellattr:rowspan,align:"left"}
        	 ,{index:"ACT_CENTER_NM_02",name:"ACT_CENTER_NM_02",width:100,edittype:"text",editable:true, editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ACT_CENTER_NM_02'  }  ] } ,align:"center"}
        	 ,{index:"ACT_CENTER_NM_02_POPUP",name:"ACT_CENTER_NM_02_POPUP",width:25,edittype:"text",formatter:"popup",editable:false,imagePath:""}
        	 ,{index:"ACT_CENTER_03",name:"ACT_CENTER_03",width:100,hidden:true,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ACT_CENTER_03'  }  ] }  ,cellattr:rowspan,align:"left"}
        	 ,{index:"ACT_CENTER_NM_03",name:"ACT_CENTER_NM_03",width:100,edittype:"text",editable:true, editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ACT_CENTER_NM_03'  }  ] } ,align:"center"}
        	 ,{index:"ACT_CENTER_NM_03_POPUP",name:"ACT_CENTER_NM_03_POPUP",width:25,edittype:"text",formatter:"popup",editable:false,imagePath:""}
        	 ,{index:"ACT_CENTER_04",name:"ACT_CENTER_04",width:100,hidden:true,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ACT_CENTER_04'  }  ] }  ,cellattr:rowspan,align:"left"}
        	 ,{index:"ACT_CENTER_NM_04",name:"ACT_CENTER_NM_04",width:100,edittype:"text",editable:true, editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ACT_CENTER_NM_04'  }  ] } ,align:"center"}
        	 ,{index:"ACT_CENTER_NM_04_POPUP",name:"ACT_CENTER_NM_04_POPUP",width:25,edittype:"text",formatter:"popup",editable:false,imagePath:""}
        	 ,{index:"ACT_CENTER_05",name:"ACT_CENTER_05",width:100,hidden:true,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ACT_CENTER_05'  }  ] }  ,cellattr:rowspan,align:"left"}
        	 ,{index:"ACT_CENTER_NM_05",name:"ACT_CENTER_NM_05",width:100,edittype:"text",editable:true, editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ACT_CENTER_NM_05'  }  ] } ,align:"center"}
        	 ,{index:"ACT_CENTER_NM_05_POPUP",name:"ACT_CENTER_NM_05_POPUP",width:25,edittype:"text",formatter:"popup",editable:false,imagePath:""}
        	 ,{index:"ACT_CENTER_06",name:"ACT_CENTER_06",width:100,hidden:true,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ACT_CENTER_06'  }  ] }  ,cellattr:rowspan,align:"left"}
        	 ,{index:"ACT_CENTER_NM_06",name:"ACT_CENTER_NM_06",width:100,edittype:"text",editable:true, editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ACT_CENTER_NM_06'  }  ] } ,align:"center"}
        	 ,{index:"ACT_CENTER_NM_06_POPUP",name:"ACT_CENTER_NM_06_POPUP",width:25,edittype:"text",formatter:"popup",editable:false,imagePath:""}
        	 ,{index:"ACT_CENTER_07",name:"ACT_CENTER_07",width:100,hidden:true,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ACT_CENTER_07'  }  ] }  ,cellattr:rowspan,align:"left"}
        	 ,{index:"ACT_CENTER_NM_07",name:"ACT_CENTER_NM_07",width:100,edittype:"text",editable:true, editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ACT_CENTER_NM_07'  }  ] } ,align:"center"}
        	 ,{index:"ACT_CENTER_NM_07_POPUP",name:"ACT_CENTER_NM_07_POPUP",width:25,edittype:"text",formatter:"popup",editable:false,imagePath:""}
        	 ,{index:"ACT_CENTER_08",name:"ACT_CENTER_08",width:100,hidden:true,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ACT_CENTER_08'  }  ] }  ,cellattr:rowspan,align:"left"}
        	 ,{index:"ACT_CENTER_NM_08",name:"ACT_CENTER_NM_08",width:100,edittype:"text",editable:true, editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ACT_CENTER_NM_08'  }  ] } ,align:"center"}
        	 ,{index:"ACT_CENTER_NM_08_POPUP",name:"ACT_CENTER_NM_08_POPUP",width:25,edittype:"text",formatter:"popup",editable:false,imagePath:""}
        	 ,{index:"ACT_CENTER_09",name:"ACT_CENTER_09",width:100,hidden:true,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ACT_CENTER_09'  }  ] }  ,cellattr:rowspan,align:"left"}
        	 ,{index:"ACT_CENTER_NM_09",name:"ACT_CENTER_NM_09",width:100,edittype:"text",editable:true, editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ACT_CENTER_NM_09'  }  ] } ,align:"center"}
        	 ,{index:"ACT_CENTER_NM_09_POPUP",name:"ACT_CENTER_NM_09_POPUP",width:25,edittype:"text",formatter:"popup",editable:false,imagePath:""}
        	 ,{index:"ACT_CENTER_10",name:"ACT_CENTER_10",width:100,hidden:true,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ACT_CENTER_10'  }  ] }  ,cellattr:rowspan,align:"left"}
        	 ,{index:"ACT_CENTER_NM_10",name:"ACT_CENTER_NM_10",width:100,edittype:"text",editable:true, editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ACT_CENTER_NM_10'  }  ] } ,align:"center"}
        	 ,{index:"ACT_CENTER_NM_10_POPUP",name:"ACT_CENTER_NM_10_POPUP",width:25,edittype:"text",formatter:"popup",editable:false,imagePath:""}
        	 ,{index:"INS_USER",name:"INS_USER",width:100,edittype:"text",formatter:"popup",editable:false,imagePath:"", editoptions:{ dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'INS_USER'  }  ] } ,align:"center"}
        	 ,{index:"INS_DATE",name:"INS_DATE",width:100,edittype:"text",editable:true,Gtype:"date", editoptions:{  maxlength:20, dataInit : function (elem) {$(elem).attr('readonly','readonly');},dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'INS_DATE'  }  ] } ,align:"center"}
        	 ,{index:"INS_DATE_DATE",name:"INS_DATE_DATE",width:25,edittype:"text",formatter:"calendar",editable:false,imagePath:"",align:"center",imageStyle:""}
        	 ,{index:"BASE_YEAR",name:"BASE_YEAR",width:100,hidden:true,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'BASE_YEAR'  }  ] }  ,cellattr:rowspan,align:"center"}
	        ],
        	OnCellSelect : function(rowid, iCol, cellcontent, e) {  
        	 var rowIndex = grid1.getRowIndex(rowid);  
        	 var Colname =  grid1.getColumnName(iCol);  
        		grid1_OnCellSelect(rowIndex, Colname, cellcontent, e);  
        	}  
        	,OnEditCell : function(rowid, colname, value, iCol,iRow) {  
        	}  
        	,OnEnterRow : function(rowindex, selrow) {  
        	}  
        	,OnInitGrid : function() {  
        		grid1_OnInitGrid(); 
        	}  
        	,OnLoadComplete : function() {  
        		grid1_OnLoadComplete();  

	         var gridName ='grid1';
        		 $("#paginate_grid1").hide();
        		 
        		 
        		 
        		 
        	}  
        });
        $('#grid1_ACT_CENTER_NM_01_POPUP').hide();
        $('table.ui-wizgrid-htable tr.ui-wizgrid-labels #grid1_ACT_CENTER_NM_01').attr('colspan','2').css('width','130');
        $('#grid1_ACT_CENTER_NM_02_POPUP').hide();
        $('table.ui-wizgrid-htable tr.ui-wizgrid-labels #grid1_ACT_CENTER_NM_02').attr('colspan','2').css('width','130');
        $('#grid1_ACT_CENTER_NM_03_POPUP').hide();
        $('table.ui-wizgrid-htable tr.ui-wizgrid-labels #grid1_ACT_CENTER_NM_03').attr('colspan','2').css('width','130');
        $('#grid1_ACT_CENTER_NM_04_POPUP').hide();
        $('table.ui-wizgrid-htable tr.ui-wizgrid-labels #grid1_ACT_CENTER_NM_04').attr('colspan','2').css('width','130');
        $('#grid1_ACT_CENTER_NM_05_POPUP').hide();
        $('table.ui-wizgrid-htable tr.ui-wizgrid-labels #grid1_ACT_CENTER_NM_05').attr('colspan','2').css('width','130');
        $('#grid1_ACT_CENTER_NM_06_POPUP').hide();
        $('table.ui-wizgrid-htable tr.ui-wizgrid-labels #grid1_ACT_CENTER_NM_06').attr('colspan','2').css('width','130');
        $('#grid1_ACT_CENTER_NM_07_POPUP').hide();
        $('table.ui-wizgrid-htable tr.ui-wizgrid-labels #grid1_ACT_CENTER_NM_07').attr('colspan','2').css('width','130');
        $('#grid1_ACT_CENTER_NM_08_POPUP').hide();
        $('table.ui-wizgrid-htable tr.ui-wizgrid-labels #grid1_ACT_CENTER_NM_08').attr('colspan','2').css('width','130');
        $('#grid1_ACT_CENTER_NM_09_POPUP').hide();
        $('table.ui-wizgrid-htable tr.ui-wizgrid-labels #grid1_ACT_CENTER_NM_09').attr('colspan','2').css('width','130');
        $('#grid1_ACT_CENTER_NM_10_POPUP').hide();
        $('table.ui-wizgrid-htable tr.ui-wizgrid-labels #grid1_ACT_CENTER_NM_10').attr('colspan','2').css('width','130');
        $('#grid1_INS_DATE_DATE').css('display','none');
        $('table.ui-wizgrid-htable tr.ui-wizgrid-labels #grid1_INS_DATE').attr('colspan','2').css('width','130');


    }

        function INS_DATE_DATE_OnClick_grid1(rowid,iCol) { 
            var colSaveName = grid1.wizGrid('getGridParam', 'Columns');
            iCol = iCol-1; 
            var iRow = grid1.getRowIdx(rowid);
            setTimeout(function() { 
              grid1.editCell(iRow, iCol, true);
              $('#' + iRow + '_' + colSaveName[iCol]['name'])
                  .datepicker({
                      inline:true
                      ,dateFormat:"yy-mm-dd"
                      ,changeMonth:true
                      ,changeYear:true
                      ,showButtonPanel:true
                      ,currentText:"today"
                      ,closeText:"close"
                      ,onSelect : function(dateText, inst) {
                           DateClose(iRow,iCol);
                       }
           });
          },10);
        } 

        function DateClose(iRow,iCol) { 
           grid1.editCell(iRow, iCol, false);
        } 
        function INS_DATE_DATE_OnClick_grid1(rowid,iCol) { 
            var colSaveName = grid1.wizGrid('getGridParam', 'Columns');
            iCol = iCol-1; 
            var iRow = grid1.getRowIdx(rowid);
            setTimeout(function() { 
              grid1.editCell(iRow, iCol, true);
              $('#' + iRow + '_' + colSaveName[iCol]['name'])
                  .datepicker({
                      inline:true
                      ,dateFormat:"yy-mm-dd"
                      ,changeMonth:true
                      ,changeYear:true
                      ,showButtonPanel:true
                      ,currentText:"today"
                      ,closeText:"close"
                      ,onSelect : function(dateText, inst) {
                           DateClose(iRow,iCol);
                       }
           });
          },10);
        } 

        function DateClose(iRow,iCol) { 
           grid1.editCell(iRow, iCol, false);
        } 

    $(document).ready(function () { 
        $('#S1_ITEM_CD').change(function() { grid2.setCurrentBindCell('ITEM_CD', this); });
        $('#S1_ITEM_CD').change(function() { grid1.setCurrentBindCell('ITEM_CD', this); });
        $('#S1_ACT_CENTER_NM_01').change(function() { grid1.setCurrentBindCell('ACT_CENTER_NM_01', this); });
        sbmInit.submit(false, "initGrids");
    });
        wizutil.setVisible("user1",false);
</script>
</html>