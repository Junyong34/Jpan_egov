<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>File6</title>
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
        	//$('#S3_SNR_NO').val(getParamdata("SNR_NO"));
        	//selectList.submit(false);
        }) ;
        
        function button1_OnClick(myObj)
        {
        	selectList.submit(false);
        }
        function grid1_OndblClickRow(rowIndex)
        {
            var SNR_NO = grid1.getCellValue(rowIndex, 'SNR_NO', true); 
            var BASE_YEAR = grid1.getCellValue(rowIndex, 'BASE_YEAR', true); 
            var EST_YEAR = grid1.getCellValue(rowIndex, 'EST_YEAR', true) ;
        	var INS_USER = grid1.getCellValue(rowIndex, 'INS_USER', true) ;
        	var SNR_DESC = grid1.getCellValue(rowIndex, 'SNR_DESC', true) ;
        	
        }
        
        function grid1_OnInitGrid()
        {
        	selectList.submit(false);
        }
        
        // 시나리오 추가
        function button3_OnClick(myObj)
        {
        	//if(!validate()) return ;
        
            grid1.insertRow('first') ;
        	grid1.setCellValue(0, 'SNR_NO',  '자동생성', true); 
        	grid1.setCellValue(0, 'FROM_GBN_CD', '10', true);    
        	//grid1.setCellValue(0, 'P_ACCNT_TITLE_CD', '*', true);
        
        }
        
        // 시나리오 저장
        function button4_OnClick(myObj)
        {
        	if(!iudSaveCheck(grid1)) return ;
        	
            var rowCount = grid1.getRowCount();
            
            for(var rowIndex = 0; rowIndex <= rowCount; rowIndex++)
            {
                var iudFlag = grid1.getCellValue(rowIndex, 'IUDFLAG', true);
                                      
                if(iudFlag == 'I' || iudFlag == 'U')
                {			
        			var SNR_NO = grid1.getCellValue(rowIndex, 'SNR_NO', true);	    			
        			var SNR_DESC = grid1.getCellValue(rowIndex, 'SNR_DESC', true);	    			
        			var FROM_GBN_CD = grid1.getCellValue(rowIndex, 'FROM_GBN_CD', true);	    			
        			var BASE_YEAR = grid1.getCellValue(rowIndex, 'BASE_YEAR', true);	    			
        			var EST_YEAR = grid1.getCellValue(rowIndex, 'EST_YEAR', true);	    			
        			var FROM_SNR_NO = grid1.getCellValue(rowIndex, 'FROM_SNR_NO', true);	    			
        			
        			if(!SNR_DESC)
        			{
        				alert('시나리오설명은 필수입력 항목입니다');
        				return ;							
        			}
        			if(!FROM_GBN_CD)
        			{
        				alert('출처구분은 필수입력 항목입니다');
        				return ;							
        			}
        			if(FROM_GBN_CD == '10' && !BASE_YEAR)
        			{
        				alert('출처구분이 [기준정보] 인 경우 기준연도는 필수입력 항목입니다');
        				return ;							
        			}
        			if(!EST_YEAR)
        			{
        				alert('추정연도는 필수입력 항목입니다');
        				return ;							
        			}
        			if(FROM_GBN_CD == '20' && !FROM_SNR_NO)
        			{
        				alert('출처구분이 [시나리오] 인 경우 출처시나리오는 필수입력 항목입니다');
        				return ;							
        			}						
          		}
        
          	} 
          	 	
            saveList.submit(false);
        }
        function grid1_OnLoadComplete()
        {
        	// 그리드 OnChange이벤트 초기화
        	initGridOnChange('grid1') ;		
        }
        // 그리드 셀 선택 이벤트 - 시나리오
        function grid1_OnCellSelect(rowIndex, Colname, cellcontent, e)
        {
        	var IUDFLAG = grid1.getCellValue(rowIndex, 'IUDFLAG', true);	    			
        	var SNR_NO = grid1.getCellValue(rowIndex, 'SNR_NO', true);	    			
        	var SNR_DESC = grid1.getCellValue(rowIndex, 'SNR_DESC', true);	    			
        	var FROM_GBN_CD = grid1.getCellValue(rowIndex, 'FROM_GBN_CD', true);	    			
        	var BASE_YEAR = grid1.getCellValue(rowIndex, 'BASE_YEAR', true);	    			
        	var EST_YEAR = grid1.getCellValue(rowIndex, 'EST_YEAR', true);	    			
        	var FROM_SNR_NO = grid1.getCellValue(rowIndex, 'FROM_SNR_NO', true);	    			
        
        	$('#S2_SNR_NO').val(SNR_NO);
        	$('#S2_FROM_GBN_CD').val(FROM_GBN_CD);
        	$('#S2_BASE_YEAR').val(BASE_YEAR);
        	$('#S2_EST_YEAR').val(EST_YEAR);
        	$('#S2_FROM_SNR_NO').val(FROM_SNR_NO);
        
        	if(IUDFLAG == 'I')
        	{
        		if(Colname == 'FROM_GBN_CD' || Colname == 'EST_YEAR')
        		{
        		    setCellOpt(rowIndex, grid1.getColumnIndex(Colname));			
        		}
        		
        		if(Colname == 'BASE_YEAR' && FROM_GBN_CD == '10')
        		{
        			
        		    setCellOpt(rowIndex, grid1.getColumnIndex(Colname));			
        		}
        		else if(Colname == 'BASE_YEAR' && FROM_GBN_CD == '20')
        		{
        			alert('출처구분이 [기준정보] 인 경우에만 입력가능합니다');
        			return ;
        		}
        		
        		if(Colname == 'FROM_SNR_NO' && FROM_GBN_CD == '10')
        		{
        			alert('출처구분이 [시나리오] 인 경우에만 입력가능합니다');
        		}
        		else if(Colname == 'FROM_SNR_NO' && FROM_GBN_CD == '20')
        		{
        		    setCellOpt(rowIndex, grid1.getColumnIndex(Colname));			
        			return ;
        		}		
        		
        	}
        }
        function grid1_OnChange(rowIndex, colName)
        {
        
        }
        function grid1_B1_cellonclick_grid1(rowId)
        {
        	var rowIndex = grid1.getRowIndex(rowId);
        
        	var IUDFLAG = grid1.getCellValue(rowIndex, 'IUDFLAG', true);	    			
        	var SNR_CREATE_DATE = grid1.getCellValue(rowIndex, 'SNR_CREATE_DATE', true);	    			
        	
        	if(IUDFLAG == 'I')
        	{
        		alert('시나리오 저장 후 생성 할 수 있습니다');
        		return ;
        	}
        
        	if(SNR_CREATE_DATE){
        		alert('일괄생성 데이터가 존재합니다. 일괄삭제 후 다시 생성 할 수 있습니다');
        		return ;
        	}
        
        	grid1_OnCellSelect(rowIndex) ;	 
            	 
        	if(!confirm('시나리오별 독립변수를 일괄생성 하시겠습니까?')) return ;
           
         
        
        	batchInsertAll.submit(false);	 
        	 
        }
        function grid1_B2_cellonclick_grid1(rowId)
        {
        	var rowIndex = grid1.getRowIndex(rowId);
        	
        	var IUDFLAG = grid1.getCellValue(rowIndex, 'IUDFLAG', true);	    				
        	var SNR_CREATE_DATE = grid1.getCellValue(rowIndex, 'SNR_CREATE_DATE', true);	    				
        	
        	if(IUDFLAG == 'I')
        	{
        		alert('시나리오 저장 및 일괄생성 이후 일괄삭제 할 수 있습니다');
        		return ;
        	}
        
        	if(!SNR_CREATE_DATE){
        		alert('일괄삭제 할 데이터가 없습니다');
        		return ;
        	}
        
        	grid1_OnCellSelect(rowIndex) ;	 
        	 
        	if(!confirm('시나리오별 독립변수를 일괄삭제[주의-전체삭제] 하시겠습니까?')) return ;
        	
        	batchDeleteAll.submit(false);
        	 
        }
        function S3_SNR_NO_OnKeyUp(myObj)
        {
        	if(event.keyCode == '13')
        		selectList.submit(false);
        }
        function S3_BASE_YEAR_OnChange(myObj)
        {
        	selectList.submit(false);
        }
        function S3_INS_USER_OnChange(myObj)
        {
        	selectList.submit(false);
        }
        function S3_EST_YEAR_OnChange(myObj)
        {
        	selectList.submit(false);
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
        	<div  class="titleLabel" style=" position: absolute; top:0px; left:0px;  width:1250px; height:48px; " id="label1" name="label1"><label style=" line-height:50px; " >    시나리오 메인     </label>    </div>

      <div  id="group1" name="group1"  class="search-box" style=" position: absolute; top:51px; left:1px;  width:1245px; height:40px; " >  
                	<input  class="btn3" style="position:absolute;left:1101px;top:8px; width:101px; height:21px; " id="button1" name="button1" type="button" value="조회" tabindex="0"  onclick="button1_OnClick(this);" ></input>
                	<select  class="selectbox" style="position:absolute;left:623px;top:9px; width:107px; height:21px; " id="S3_INS_USER" name="S3_INS_USER" tabindex="0" onchange="S3_INS_USER_OnChange(this);" eltype="Combo" >
        	</select>
                	<select  class="selectbox" style="position:absolute;left:346px;top:9px; width:82px; height:21px; " id="S3_BASE_YEAR" name="S3_BASE_YEAR" tabindex="0" onchange="S3_BASE_YEAR_OnChange(this);" eltype="Combo" >
        	</select>
                	<input  class="TextBox-S" style="position:absolute;left:137px;top:9px; width:97px; height:17px; " id="S3_SNR_NO" name="S3_SNR_NO" type="text" maxlength="50" tabindex="0"  onkeyup="S3_SNR_NO_OnKeyUp(this);">        </input>
                	<div  class="searchLabel" style="position:absolute;left:783px;top:9px; width:60px; height:19px; " id="label5" name="label5"><label style=" line-height:21px; " >        추정연도         </label>        </div>
                	<div  class="searchLabel" style="position:absolute;left:274px;top:9px; width:60px; height:19px; " id="label4" name="label4"><label style=" line-height:21px; " >        기준연도         </label>        </div>
                	<div  class="searchLabel" style="position:absolute;left:508px;top:9px; width:101px; height:19px; " id="label3" name="label3"><label style=" line-height:21px; " >        시나리오생성자         </label>        </div>
                	<div  class="searchLabel" style="position:absolute;left:29px;top:9px; width:100px; height:19px; " id="label2" name="label2"><label style=" line-height:21px; " >        시나리오번호         </label>        </div>
      </div>
      <div  id="group2" name="group2"  class="button-box" style=" position: absolute; top:92px; left:0px;  width:1250px; height:40px; " >  
                	<input  class="btn4" style="position:absolute;left:1120px;top:9px; width:101px; height:21px; " id="button5" name="button5" type="button" value="엑셀다운로드" tabindex="0"  ></input>
                	<input  class="btn" style="position:absolute;left:971px;top:9px; width:61px; height:21px; " id="button3" name="button3" type="button" value="추가" tabindex="0"  onclick="button3_OnClick(this);" ></input>
                	<input  class="btn" style="position:absolute;left:1042px;top:9px; width:61px; height:21px; " id="button4" name="button4" type="button" value="저장" tabindex="0"  onclick="button4_OnClick(this);" ></input>
                	<input  class="TextBox-S" style="position:absolute;left:16px;top:9px; width:90px; height:17px; " id="S2_SNR_NO" name="S2_SNR_NO" type="text" maxlength="50" tabindex="0" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:119px;top:9px; width:97px; height:17px; " id="S2_BASE_YEAR" name="S2_BASE_YEAR" type="text" maxlength="50" tabindex="0" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:228px;top:9px; width:97px; height:17px; " id="S2_FROM_GBN_CD" name="S2_FROM_GBN_CD" type="text" maxlength="50" tabindex="0" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:337px;top:9px; width:97px; height:17px; " id="S2_FROM_SNR_NO" name="S2_FROM_SNR_NO" type="text" maxlength="50" tabindex="0" >        </input>
      </div>
        <div id="grid1_gdiv"  style=" position: absolute; top:133px; left:0px; width:1250px;height:180px;" >
		     <table id="grid1" elType='Grid' ></table>
	    </div>

        	<select  style="border-width:1px; background-color:White; color:Black;border-color:Black;text-align:left;  font-family:굴림체;font-size:9.75pt; position: absolute; top:406px; left:951px;  width:102px; height:21px; " id="S3_EST_YEAR" name="S3_EST_YEAR" tabindex="0" onchange="S3_EST_YEAR_OnChange(this);" eltype="Combo" >
    	</select>

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
    var ComboObjProp= {"S3_INS_USER":{"CODE":"CBO_CD","NAME":"CBO_NM","DEFAULT":"::전체::"}, "S3_BASE_YEAR":{"CODE":"CBO_CD","NAME":"CBO_NM","DEFAULT":"::전체::"}, "grid1_FROM_GBN_CD":{"CODE":"CBO_CD","NAME":"CBO_NM","DEFAULT":"선택"}, "grid1_BASE_YEAR":{"CODE":"CBO_CD","NAME":"CBO_NM","DEFAULT":"선택"}, "grid1_EST_YEAR":{"CODE":"CBO_CD","NAME":"CBO_NM","DEFAULT":"선택"}, "grid1_FROM_SNR_NO":{"CODE":"CBO_CD","NAME":"CBO_NM","DEFAULT":"선택"}, "S3_EST_YEAR":{"CODE":"CBO_CD","NAME":"CBO_NM","DEFAULT":"Select"}};
    var RequiredProp= {};
    var CellEditoption= {};
    var InitItems= {"S3_INS_USER":":", "S3_BASE_YEAR":":", "grid1_FROM_GBN_CD":":", "grid1_BASE_YEAR":":", "grid1_EST_YEAR":":", "grid1_FROM_SNR_NO":":", "S3_EST_YEAR":":"};
    var sbmInit = new SubMission( frm, "/PBCloud","","","");
    sbmInit.InitCombo("S3_INS_USER,S3_BASE_YEAR,S3_EST_YEAR");
    var grid1= jQuery("#grid1");
    grid1.setDatasetName("S1");
    var grid1_Data = [];
    function initGrids() { 
        grid1.wizGrid({   
        	data:grid1_Data,   
        	height:141,  
        	width:1250,    
        	multiselect:true,       
        	cellEdit:true,   
        	Names: ["ROWID","S","시나리오번호","시나리오설명","출처구분","기준연도","추정연도","출처시나리오","생성자","일괄생성일","생성일자","수정자","수정일자","",""],
        	Columns: [  
        	 {index: "id", name:"id", width:75, key:true, hidden:true }        	, {index:'IUDFLAG',name:'IUDFLAG',editable:false,width:10,formatter:'select',edittype:'select',hidden:true,editoptions:{ value:':;I:I;U:U;D:D',defaultValue:'' }}
        	 ,{index:"SNR_NO",name:"SNR_NO",width:109,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'SNR_NO'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"SNR_DESC",name:"SNR_DESC",width:220,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'SNR_DESC'  }  ] }  ,cellattr:rowspan,align:"left"}
        	 ,{index:"FROM_GBN_CD",name:"FROM_GBN_CD",width:86,edittype:"select",formatter:"select",editoptions:{value:InitItems.grid1_FROM_GBN_CD} ,editable:false ,cellattr:rowspan,align:"center"}
        	 ,{index:"BASE_YEAR",name:"BASE_YEAR",width:81,edittype:"select",formatter:"select",editoptions:{value:InitItems.grid1_BASE_YEAR} ,editable:false ,cellattr:rowspan,align:"center"}
        	 ,{index:"EST_YEAR",name:"EST_YEAR",width:83,edittype:"select",formatter:"select",editoptions:{value:InitItems.grid1_EST_YEAR} ,editable:false ,cellattr:rowspan,align:"center"}
        	 ,{index:"FROM_SNR_NO",name:"FROM_SNR_NO",width:101,edittype:"select",formatter:"select",editoptions:{value:InitItems.grid1_FROM_SNR_NO} ,editable:false ,cellattr:rowspan,align:"left"}
        	 ,{index:"INS_USER",name:"INS_USER",width:95,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'INS_USER'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"SNR_CREATE_DATE",name:"SNR_CREATE_DATE",width:93,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'SNR_CREATE_DATE'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"INS_DATE",name:"INS_DATE",width:100,hidden:true,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'INS_DATE'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"UPD_USER",name:"UPD_USER",width:100,hidden:true,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'UPD_USER'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"UPD_DATE",name:"UPD_DATE",width:107,hidden:true,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'UPD_DATE'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"grid1_B1",name:"grid1_B1",width:100,edittype:"text",formatter:"popup",editable:false,imagePath:"/jsp/menu/img/main/btn-add.png", editoptions:{ dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'grid1_B1'  }  ] } ,align:"center"}
        	 ,{index:"grid1_B2",name:"grid1_B2",width:100,edittype:"text",formatter:"popup",editable:false,imagePath:"/jsp/menu/img/main/btn-add.png", editoptions:{ dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'grid1_B2'  }  ] } ,align:"center"}
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
        	,OndblClickRow : function(rowid) {  
        	 var rowIndex = grid1.getRowIndex(rowid);  
        		grid1_OndblClickRow(rowIndex);  
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


    }


    $(document).ready(function () { 
        initGrids();
    });
</script>
</html>