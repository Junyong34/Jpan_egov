





<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>pilot</title>
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
        // onLoad
        $(document).ready(function(){
            comboList.submit(false,"cobmoselect");
        //	$("S1_ACTUAL_CD_2 option:eq(2)").attr("selected", "selected");
        //	$("#S1_ACTUAL_CD_2").val("B020");
        
          // 모두 체크 
          $("#ACCOUNT_YN").click(function() {
            
        			$("input:checkbox").each(function() {
        			  if($("#ACCOUNT_YN").prop("checked")) {
        				//해당화면에 전체 checkbox들을 체크해준다
        			   if(this.className != 'cbox'){
        				$("#"+this.id).prop("checked",true);
        				}
        			// 전체선택 체크박스가 해제된 경우
        			} else {
        				//해당화면에 모든 checkbox들의 체크를해제시킨다.
        				if(this.className != 'cbox'){
        					$("#"+this.id).prop("checked",false);
        				}
        			}
        			});
        		});
        	
        }) ;
        function cobmoselect(){
        	$("#S1_ACTUAL_CD_2").val("B040");
        }
        function button1_OnClick(myObj)
        {
        
           if( grid1.getRowCount() == 0 ){
               alert( "조회를 하고 추가하세요.");
              return;
           }
        	var curowindx = grid1.getCurrentRowIndex();
        	if(curowindx != -1){	
        	var rowid = grid1.getGridRowId(curowindx);
          
         	grid1.insertRow("after",rowid);
         	
         }else{
         alert( "추가하기 위해서 Row를 선택 해주세요");
         //	grid1.insertRow("last");
         }
        }
        
        function button7_OnClick(myObj)
        {
          search.submit(true, "selectedCombo");
        }
        
        function selectedCombo()
        {
           for(var i=1; i< 13; i++)
           {
        	  if(i < 10)
        	  {
        	     grid1.setColLabel("AMT_B_0"+i,$('#S1_ACTUAL_CD_1 option:selected').text());
        	  }else{
        	    grid1.setColLabel("AMT_B_"+i,$('#S1_ACTUAL_CD_1 option:selected').text());
        	  }
           }
           for(var i=1; i< 13; i++)
           {
        	  if(i < 10)
        	  {
        	     grid1.setColLabel("AMT_C_0"+i,$('#S1_ACTUAL_CD_2 option:selected').text());
        	  }else{
        	   
        	    grid1.setColLabel("AMT_C_"+i,$('#S1_ACTUAL_CD_2 option:selected').text());
        	  }
           }
        }
        
        function grid1_OnLoadComplete()
        {
        
         for(var i=1; i< 13; i++){
         
          if(i < 10){
             grid1.setColLabel("AMT_B_0"+i,"중기");
             grid1.setColLabel("AMT_C_0"+i,"Forcast1");
          }else{
            grid1.setColLabel("AMT_B_"+i,"중기");
            grid1.setColLabel("AMT_C_"+i,"Forcast1");
          }
         }
         
        }
        function S1_ACTUAL_CD_OnChange(myObj)
        {
           if($('#S1_ACTUAL_CD_1').val() == $('#S1_ACTUAL_CD_2').val()){
                alert("선택1, 선택2 는 같은 값을 선택 할 수 없습니다.");
                $('#S1_ACTUAL_CD_1').val(Select_One);
                return;
           }
          
        
          
        }
        function S1_ACTUAL_CD_2_OnChange(myObj)
        {
         if($('#S1_ACTUAL_CD_1').val() == $('#S1_ACTUAL_CD_2').val()){
                alert("선택1, 선택2 는 같은 값을 선택 할 수 없습니다.");
                  $('#S1_ACTUAL_CD_2').val(Select_Two);
                return;
           }
           
        }
        function grid1_OnInsertRow(rowIndex)
        {
        
        	var curowindx =  grid1.getCurrentRowIndex();	//var curowindx = grid1.getCurrentRowIndex();
        
        	if(curowindx != -1){	
        
            var M_ITEM_CD_VAL = grid1.getCellValue(curowindx,  "M_ITEM_CD", false); 
        	  grid1.setCellValue(rowIndex, "M_ITEM_CD",  M_ITEM_CD_VAL, false);
        	var M_ITEM_NM_VAL = grid1.getCellValue(curowindx,  "M_ITEM_NM", false); 
        	  grid1.setCellValue(rowIndex, "M_ITEM_NM",  M_ITEM_NM_VAL, false);
        	var PID_CD_VAL = grid1.getCellValue(curowindx,  "PID_CD", false); 
        	  grid1.setCellValue(rowIndex, "PID_CD",  PID_CD_VAL, false);
        	var PID_NM_VAL = grid1.getCellValue(curowindx,  "PID_NM", false); 
        	  grid1.setCellValue(rowIndex, "PID_NM",  PID_NM_VAL, false);
        	var ACCOUNT_CD_VAL = grid1.getCellValue(curowindx,  "ACCOUNT_CD", false); 
        	  grid1.setCellValue(rowIndex, "ACCOUNT_CD",  ACCOUNT_CD_VAL, false);
        	var ACCOUNT_NM_VAL = grid1.getCellValue(curowindx,  "ACCOUNT_NM", false); 
        	  grid1.setCellValue(rowIndex, "ACCOUNT_NM",  ACCOUNT_NM_VAL, false);  
        	var TYPE_NM_VAL = grid1.getCellValue(curowindx,  "TYPE_NM", false); 
        	  grid1.setCellValue(rowIndex, "TYPE_NM",  TYPE_NM_VAL, false);
        	var SEQ_VAL = grid1.getCellValue(curowindx,  "SEQ", false); 
        	  grid1.setCellValue(rowIndex, "SEQ",  SEQ_VAL, false);
              
         	
         }else{
         
         alert( "추가하기 위해서 Row를 선택 해주세요");
         
         }
        
        }
        function grid1_OnCellSelect(rowIndex, Colname, cellcontent, e)
        {
            var IUDFLAG_VAL = grid1.getCellValue(rowIndex,  "IUDFLAG", false); 
            //구분명 구분 수정
            if( IUDFLAG_VAL == "I"){
            if(Colname == "SEQ") setCellOpt(rowIndex, grid1.getColumnIndex('SEQ'));
           if(Colname == "TYPE_NM") setCellOpt(rowIndex, grid1.getColumnIndex('TYPE_NM'));
           }
        }
        
        function button4_OnClick(myObj)
        {
        
        SAVE.submit(false);
        }
        // before value set
        var Select_One ="";
        var Select_Two ="";
        
        function S1_ACTUAL_CD_1_OnMouseOver(myObj)
        {
         Select_One =  $('#S1_ACTUAL_CD_1').val();
        }
        function S1_ACTUAL_CD_2_OnMouseOver(myObj)
        {
        Select_Two = $('#S1_ACTUAL_CD_2').val();
        }
        
        
        function button2_OnClick(myObj)
        {
              excel.setInput("grid1");  //엑셀 다운로드할 그리드 id 
                	    excel.setExcelName("기준정보_품목관리_생산품목.xlsx");  //엑셀파일 설정  입력 안하면 Default 값 으로 파일만들어짐
                	    excel.gridExcel(true);  //엑셀 다운로드 서브미션 실행 false 히든칼럼 뺴고 다운 true 히든칼럼 포함 다운	
                	    }
        
        function button3_OnClick(myObj)
        {
        excel.setInput("grid1");  //엑셀 다운로드할 그리드 id 
                	    excel.setExcelName("기준정보_품목관리_생산품목.xlsx");  //엑셀파일 설정  입력 안하면 Default 값 으로 파일만들어짐
                	    excel.gridExcel(true);  //엑셀 다운로드 서브미션 실행 false 히든칼럼 뺴고 다운 true 히든칼럼 포함 다운	
                	    
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
        	<div  class="titleLabel" style=" position: absolute; top:0px; left:0px;  width:1109px; height:36px; " id="label1" name="label1"><label style=" line-height:38px; " >    조회조건     </label>    </div>

      <div  id="group2" name="group2"  style=" background-color:WhiteSmoke;border-width:1px;border-style:solid;border-color:DarkGray; position: absolute; top:42px; left:0px;  width:1106px; height:47px; " >  
                	<select  style="border-width:1px; background-color:White; color:Black;border-color:Black;text-align:left;  font-family:굴림체;font-size:9.75pt;position:absolute;left:879px;top:14px; width:102px; height:21px; " id="S1_ACTUAL_CD_2" name="S1_ACTUAL_CD_2" tabindex="0" onmouseover="S1_ACTUAL_CD_2_OnMouseOver(this);" onchange="S1_ACTUAL_CD_2_OnChange(this);" eltype="Combo" >
        	</select>
                	<select  style="border-width:1px; background-color:White; color:Black;border-color:Black;text-align:left;  font-family:굴림체;font-size:9.75pt;position:absolute;left:247px;top:15px; width:102px; height:21px; " id="S1_PID_CD" name="S1_PID_CD" tabindex="0" eltype="Combo" >
        	</select>
                	<div  class="infoLabel" style="position:absolute;left:405px;top:15px; width:70px; height:19px; " id="label5" name="label5"><label style=" line-height:21px; " >        기준연도         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:208px;top:15px; width:38px; height:19px; " id="label2" name="label2"><label style=" line-height:21px; " >        PID         </label>        </div>
                	<select  style="border-width:1px; background-color:White; color:Black;border-color:Black;text-align:left;  font-family:굴림체;font-size:9.75pt;position:absolute;left:708px;top:15px; width:102px; height:21px; " id="S1_ACTUAL_CD_1" name="S1_ACTUAL_CD_1" tabindex="0" onmouseover="S1_ACTUAL_CD_1_OnMouseOver(this);" onchange="S1_ACTUAL_CD_OnChange(this);" eltype="Combo" >
        	</select>
                	<select  style="border-width:1px; background-color:White; color:Black;border-color:Black;text-align:left;  font-family:굴림체;font-size:9.75pt;position:absolute;left:98px;top:15px; width:102px; height:21px; " id="S1_M_ITEM_CD" name="S1_M_ITEM_CD" tabindex="0" eltype="Combo" >
        	</select>
                <div id=="UpDown_S1_BASE_YEAR" style="position:absolute;left:477px;top:15px;">
        	<input type='text' style="border:solid;border-width:0px;background-color:White; color:Black;border-color:Black;text-align:center; width:53px; height:19px; " id="S1_BASE_YEAR" name="S1_BASE_YEAR" value="2016" maxlength="1" tabindex="0" >          </input>
        </div>
<script>
        var S1_BASE_YEAR = $('#S1_BASE_YEAR');
        S1_BASE_YEAR.spinner({ 
          step:1,
          max:2016,
          min:2016
        });
</script>
                	<div  class="infoLabel" style="position:absolute;left:648px;top:15px; width:60px; height:19px; " id="label4" name="label4"><label style=" line-height:21px; " >        선택1         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:829px;top:15px; width:48px; height:19px; " id="label6" name="label6"><label style=" line-height:21px; " >        선택2         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:19px;top:15px; width:76px; height:19px; " id="label3" name="label3"><label style=" line-height:21px; " >        직제코드         </label>        </div>
      </div>
      <div  id="group1" name="group1"  style=" background-color:WhiteSmoke;border-width:1px;border-style:solid;border-color:DarkGray; position: absolute; top:94px; left:0px;  width:1106px; height:42px; " >  
                	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left;position:absolute;left:958px;top:11px; width:49px; height:19px; " id="label13" name="label13"><label style=" line-height:21px;  font-family:Microsoft Sans Serif;font-size:10pt;" >        NRE         </label>        </div>
                	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left;position:absolute;left:439px;top:11px; width:57px; height:19px; " id="label9" name="label9"><label style=" line-height:21px;  font-family:Microsoft Sans Serif;font-size:10pt;" >        인건비         </label>        </div>
                	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left;position:absolute;left:537px;top:11px; width:93px; height:19px; " id="label10" name="label10"><label style=" line-height:21px;  font-family:Microsoft Sans Serif;font-size:10pt;" >        일반재료비         </label>        </div>
                	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left;position:absolute;left:667px;top:11px; width:97px; height:19px; " id="label11" name="label11"><label style=" line-height:21px;  font-family:Microsoft Sans Serif;font-size:10pt;" >        외주위탁비         </label>        </div>
                	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left;position:absolute;left:354px;top:11px; width:49px; height:19px; " id="label7" name="label7"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >        매출         </label>        </div>
                	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left;position:absolute;left:800px;top:11px; width:124px; height:19px; " id="label12" name="label12"><label style=" line-height:21px;  font-family:Microsoft Sans Serif;font-size:10pt;" >        EDA 라이선스비         </label>        </div>
                	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left;position:absolute;left:215px;top:11px; width:102px; height:19px; " id="label15" name="label15"><label style=" line-height:21px;  font-family:Microsoft Sans Serif;font-size:10pt;" >        전체선택/해제         </label>        </div>
                	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left;position:absolute;left:8px;top:11px; width:173px; height:19px; " id="label8" name="label8"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >        표시항목(계정과목) 선택:         </label>        </div>
                	<input type="checkbox"  style=" width:11px; height:13px; border-width:0px;background-color:White; color:Black;border-color:#000000;text-align:left;position:absolute;left:938px;top:12px;" id="S1_ACCOUNT_A060_YN" name="S1_ACCOUNT_A060_YN" value="Y" tabindex="0" >                	<input type="checkbox"  style=" width:11px; height:13px; border-width:0px;background-color:White; color:Black;border-color:#000000;text-align:left;position:absolute;left:780px;top:12px;" id="S1_ACCOUNT_A050_YN" name="S1_ACCOUNT_A050_YN" value="Y" tabindex="0" >                	<input type="checkbox"  style=" width:11px; height:13px; border-width:0px;background-color:White; color:Black;border-color:#000000;text-align:left;position:absolute;left:646px;top:12px;" id="S1_ACCOUNT_A040_YN" name="S1_ACCOUNT_A040_YN" value="Y" tabindex="0" >                	<input type="checkbox"  style=" width:11px; height:13px; border-width:0px;background-color:White; color:Black;border-color:#000000;text-align:left;position:absolute;left:518px;top:12px;" id="S1_ACCOUNT_A030_YN" name="S1_ACCOUNT_A030_YN" value="Y" tabindex="0" >                	<input type="checkbox"  style=" width:11px; height:13px; border-width:0px;background-color:White; color:Black;border-color:#000000;text-align:left;position:absolute;left:419px;top:12px;" id="S1_ACCOUNT_A020_YN" name="S1_ACCOUNT_A020_YN" value="Y" tabindex="0" >                	<input type="checkbox"  style=" width:11px; height:13px; border-width:0px;background-color:White; color:Black;border-color:#000000;text-align:left;position:absolute;left:334px;top:12px;" id="S1_ACCOUNT_A010_YN" name="S1_ACCOUNT_A010_YN" value="Y" tabindex="0" >                	<input type="checkbox"  style=" width:11px; height:13px; border-width:0px;background-color:White; color:Black;border-color:#000000;text-align:left;position:absolute;left:197px;top:12px;" id="ACCOUNT_YN" name="ACCOUNT_YN" value="Y" tabindex="0" >      </div>
      <div  id="group3" name="group3"  class="button-box" style=" position: absolute; top:146px; left:0px;  width:1108px; height:39px; " >  
                	<input  class="btn3" style="position:absolute;left:1026px;top:7px; width:74px; height:21px; " id="button7" name="button7" type="button" value="조회" tabindex="0"  onclick="button7_OnClick(this);" ></input>
                	<input  class="btn" style="position:absolute;left:959px;top:7px; width:61px; height:21px; " id="button4" name="button4" type="button" value="저장" tabindex="0"  onclick="button4_OnClick(this);" ></input>
                	<input  class="btn" style="position:absolute;left:891px;top:7px; width:63px; height:21px; " id="button1" name="button1" type="button" value="추가" tabindex="0"  onclick="button1_OnClick(this);" ></input>
      </div>
        <div id="grid1_gdiv"  style=" position: absolute; top:189px; left:119px; width:989px;height:445px;" >
		     <table id="grid1" elType='Grid' ></table>
	    </div>
	    <div id="gridhead_grid1"  style=" position: absolute; top:179px; left:119px; " >
	    </div>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:713px; left:638px;  width:101px; height:21px; " id="button3" name="button3" type="button" value="button" tabindex="0"  onclick="button3_OnClick(this);" ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:822px; left:158px;  width:101px; height:23px; " id="button2" name="button2" type="button" value="button" tabindex="0"  onclick="button2_OnClick(this);" ></input>

</form>	
<form id='fileform' name='fileform' encType='multipart/form-data'>
	<div style='display:none'>
	<iframe id='FileDownload'  name='FileDownload' style='border-width:0px;' src='' frameborder='0' ></iframe>
	</div>
	
</form>
    </div>
</body>
<script type="text/javascript" >
    var GridRowHeight= {"grid1":"22",};
    var ChkNumberColumns= [];
    var RadioProp= {};
    var CheckProp= {"":"S1_ACCOUNT_A060_YN","":"S1_ACCOUNT_A050_YN","":"S1_ACCOUNT_A040_YN","":"S1_ACCOUNT_A030_YN","":"S1_ACCOUNT_A020_YN","":"S1_ACCOUNT_A010_YN","":"ACCOUNT_YN"};
    var ComboObjProp= {"S1_ACTUAL_CD_2":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":""}, "S1_PID_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":":: 전체 ::"}, "S1_ACTUAL_CD_1":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":""}, "S1_M_ITEM_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":":: 전체 ::"}};
    var RequiredProp= {"S1_PID_CD":"111111"};
    var CellEditoption= {};
    var InitItems= {"S1_ACTUAL_CD_2":":", "S1_PID_CD":":", "S1_ACTUAL_CD_1":":", "S1_M_ITEM_CD":":", "S1_ACCOUNT_A060_YN": {TrueValue:"Y", FalseValue:"N",Column:"_" }, "S1_ACCOUNT_A050_YN": {TrueValue:"Y", FalseValue:"N",Column:"_" }, "S1_ACCOUNT_A040_YN": {TrueValue:"Y", FalseValue:"N",Column:"_" }, "S1_ACCOUNT_A030_YN": {TrueValue:"Y", FalseValue:"N",Column:"_" }, "S1_ACCOUNT_A020_YN": {TrueValue:"Y", FalseValue:"N",Column:"_" }, "S1_ACCOUNT_A010_YN": {TrueValue:"Y", FalseValue:"N",Column:"_" }, "ACCOUNT_YN": {TrueValue:"Y", FalseValue:"N",Column:"_" }};
    var sbmInit = new SubMission( frm, "/PBCloud","","","");
    var search = new SubMission( frm, "/PBCloud/20160617137108PilotSample_M_ITEM_SEARCH.do","S1[A]:S1","grid1:SEL2","");
    var SAVE = new SubMission( frm, "/PBCloud/20160617137108PilotSample_M_ITEM_SAVE.do","S1[A]:S1,grid1[C]:S","grid1:PEX66","");
    SAVE.setConfirmMsg("저장하시겠습니까?");
    var comboList = new SubMission( frm, "/PBCloud/20160617137108PilotSample_PILOT_COMBO.do","","S1_M_ITEM_CD:SEL2,S1_PID_CD:SEL4,S1_ACTUAL_CD_1:SEL6,S1_ACTUAL_CD_2:SEL8","");
    var excel = new SubMission( frm, "/PBCloud/excelDown.excel","","","");
    sbmInit.InitCombo("S1_ACTUAL_CD_2,S1_PID_CD,S1_ACTUAL_CD_1,S1_M_ITEM_CD");
    var grid1= jQuery("#grid1");
    var grid1_Data = [];
    function initGrids() { 
        grid1.wizGrid({   
        	data:grid1_Data,   
        	height:363,  
        	width:989,    
        	rowNum:100,    
        	multiselect:true,       
        	cellEdit:true,   
        	sortable : true    ,   
        	Names: ["ROWID","S","직제코드","직제코드명","PID코드","PID명","계정항목","계정항목명","구분","구분명","실적","선택1","선택2","실적","선택1","선택2","실적","선택1","선택2","실적","선택1","선택2","실적","선택1","선택2","실적","선택1","선택2","실적","선택1","선택2","실적","선택1","선택2","실적","선택1","선택2","실적","선택1","선택2","실적","선택1","선택2","실적","선택1","선택2"],
        	Columns: [  
        	 {index: "id", name:"id", width:75, key:true, hidden:true }        	, {index:'IUDFLAG',name:'IUDFLAG',editable:false,width:10,formatter:'select',edittype:'select',editoptions:{ value:':;I:I;U:U;D:D',defaultValue:'' }}
        	 ,{index:"M_ITEM_CD",name:"M_ITEM_CD",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'M_ITEM_CD'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"M_ITEM_NM",name:"M_ITEM_NM",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'M_ITEM_NM'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"PID_CD",name:"PID_CD",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'PID_CD'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"PID_NM",name:"PID_NM",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'PID_NM'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"ACCOUNT_CD",name:"ACCOUNT_CD",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ACCOUNT_CD'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"ACCOUNT_NM",name:"ACCOUNT_NM",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ACCOUNT_NM'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"SEQ",name:"SEQ",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'SEQ'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"TYPE_NM",name:"TYPE_NM",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'TYPE_NM'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_A_04",name:"AMT_A_04",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_A_04'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_B_04",name:"AMT_B_04",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_B_04'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_C_04",name:"AMT_C_04",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_C_04'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_A_05",name:"AMT_A_05",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_A_05'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_B_05",name:"AMT_B_05",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_B_05'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_C_05",name:"AMT_C_05",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_C_05'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_A_06",name:"AMT_A_06",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_A_06'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_B_06",name:"AMT_B_06",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_B_06'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_C_06",name:"AMT_C_06",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_C_06'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_A_07",name:"AMT_A_07",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_A_07'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_B_07",name:"AMT_B_07",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_B_07'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_C_07",name:"AMT_C_07",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_C_07'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_A_08",name:"AMT_A_08",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_A_08'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_B_08",name:"AMT_B_08",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_B_08'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_C_08",name:"AMT_C_08",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_C_08'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_A_09",name:"AMT_A_09",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_A_09'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_B_09",name:"AMT_B_09",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_B_09'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_C_09",name:"AMT_C_09",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_C_09'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_A_10",name:"AMT_A_10",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_A_10'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_B_10",name:"AMT_B_10",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_B_10'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_C_10",name:"AMT_C_10",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_C_10'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_A_11",name:"AMT_A_11",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_A_11'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_B_11",name:"AMT_B_11",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_B_11'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_C_11",name:"AMT_C_11",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_C_11'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_A_12",name:"AMT_A_12",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_A_12'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_B_12",name:"AMT_B_12",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_B_12'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_C_12",name:"AMT_C_12",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_C_12'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_A_01",name:"AMT_A_01",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_A_01'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_B_01",name:"AMT_B_01",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_B_01'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_C_01",name:"AMT_C_01",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_C_01'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_A_02",name:"AMT_A_02",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_A_02'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_B_02",name:"AMT_B_02",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_B_02'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_C_02",name:"AMT_C_02",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_C_02'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_A_03",name:"AMT_A_03",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_A_03'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_B_03",name:"AMT_B_03",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_B_03'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AMT_C_03",name:"AMT_C_03",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AMT_C_03'  }  ] }  ,cellattr:rowspan,align:"center"}
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
        	,OnLoadComplete : function() {  
        		grid1_OnLoadComplete();  

	         var gridName ='grid1';

	         $("[colheight=grid1]").css('height',"22px");
        		 
        		 
        		 
        		 
        	}  
        	,OnInsertRow : function(rowid, rowdata, rowelem) {  
        	 var rowIndex = grid1.getRowIndex(rowid);  
        		grid1_OnInsertRow(rowIndex );  
        	}  
        });

        grid1.wizGrid('HeadersMerge', {
	         useColSpanStyle: true,
	         groupHeaders:[
	        {startColumnName:"AMT_A_04", numberOfColumns:3, titleText:"4월"}
	        , {startColumnName:"AMT_A_05", numberOfColumns:3, titleText:"5월"}
	        , {startColumnName:"AMT_A_07", numberOfColumns:3, titleText:"7월"}
	        , {startColumnName:"AMT_A_08", numberOfColumns:3, titleText:"8월"}
	        , {startColumnName:"AMT_A_09", numberOfColumns:3, titleText:"9월"}
	        , {startColumnName:"AMT_A_10", numberOfColumns:3, titleText:"10월"}
	        , {startColumnName:"AMT_A_11", numberOfColumns:3, titleText:"11월"}
	        , {startColumnName:"AMT_A_12", numberOfColumns:3, titleText:"12월"}
	        , {startColumnName:"AMT_A_01", numberOfColumns:3, titleText:"01월"}
	        , {startColumnName:"AMT_A_02", numberOfColumns:3, titleText:"02월"}
	        , {startColumnName:"AMT_A_03", numberOfColumns:3, titleText:"03월"}
	        ]
         });  

    }


    $(document).ready(function () { 
        initGrids();
        grid1.setDatasetName("S");        
    });
        wizutil.setVisible("button2",false);
</script>
</html>