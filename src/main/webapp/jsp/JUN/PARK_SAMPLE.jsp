<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>샘플화면</title>
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
        function button1_OnClick(myObj)
        {
             Search.submit(false);
            Search2.submit(false);
        }
        function button2_OnClick(myObj)
        {
        
          SAVE.submit(false);
        }
        
        function button3_OnClick(myObj)
        {
         grid1.readonlyGrid();
        }
        
        function grid1_OnLoadComplete()
        {
        }
        
        </script>
        <style> 






</style>
</head>
<body >
    <div id="pbview" >
<form id='frm' name='frm' >
    <div id="hiddenvariable" style="display:none">
        <input style="display:none" id="out_message" name="out_message"  value="" ></input>
        <input style="display:none" id="out_code" name="out_code"  value="" ></input>
    </div>
        <input type='hidden'  id="InWIzJsonParma" name="InWIzJsonParma"  value="" ></input>
        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:57px; left:384px;  width:101px; height:21px; " id="button1" name="button1" type="button" value="조회" tabindex="0"  onclick="button1_OnClick(this);" ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:58px; left:512px;  width:101px; height:21px; " id="button2" name="button2" type="button" value="저장" tabindex="0"  onclick="button2_OnClick(this);" ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:86px; left:244px;  width:101px; height:21px; " id="button3" name="button3" type="button" value="button" tabindex="0"  onclick="button3_OnClick(this);" ></input>

        <div id="grid1_gdiv"  style=" position: absolute; top:123px; left:86px; width:816px;height:256px;" >
		     <table id="grid1" elType='Grid' ></table>
	    </div>

        <div id="grid2_gdiv"  style=" position: absolute; top:419px; left:50px; width:894px;height:270px;" >
		     <table id="grid2" elType='Grid' ></table>
	    </div>

</form>	
<form id='fileform' name='fileform' encType='multipart/form-data'>
	<div style='display:none'>
	<iframe id='FileDownload'  name='FileDownload' style='border-width:0px;' src='' frameborder='0' ></iframe>
	</div>
	
</form>
    </div>
</body>
<script type="text/javascript" >
    var GridRowHeight= {"grid1":"63","grid2":"22",};
    var ChkNumberColumns= [];
    var RadioProp= {};
    var CheckProp= {};
    var ComboObjProp= {};
    var RequiredProp= {};
    var CellEditoption= {};
    var InitItems= {};
    var sbmInit = new SubMission( frm, "/PBCloud","","","");
    var Search = new SubMission( frm, "/PBCloud/20160518172747TEST_PARK_SAMPLE_SAWON_PARK12.do","","grid1:SEL2","");
    var SAVE = new SubMission( frm, "/PBCloud/20160518172747TEST_PARK_SAMPLE_SAWON_SAVE_12.do","grid1[C]:S1","grid1:PEX13","");
    SAVE.setConfirmMsg("저장하시겠습니까?");
    var Search2 = new SubMission( frm, "/PBCloud/20160518172747TEST_PARK_SAMPLE_SAWON_PARK12.do","","grid2:SEL2","");
    sbmInit.InitCombo("");
    var grid1= jQuery("#grid1");
    var grid2= jQuery("#grid2");
    var grid1_Data = [];
    var grid2_Data = [];
    function initGrids() { 
        grid1.wizGrid({   
        	data:grid1_Data,   
        	height:217,  
        	width:816,    
        	rownumbers:true,    
        	multiselect:true,       
        	cellEdit:true,   
        	Names: ["ROWID","S","사원 ID","asdasdasda","","이름ㅇ","EMAIL"],
        	Columns: [  
        	 {index: "id", name:"id", width:75, key:true, hidden:true }        	, {index:'IUDFLAG',name:'IUDFLAG',editable:false,width:10,formatter:'select',edittype:'select',hidden:true,editoptions:{ value:':;I:I;U:U;D:D',defaultValue:'' }}
        	 ,{index:"EMPLOYEEID",name:"EMPLOYEEID",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'EMPLOYEEID'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AGE",name:"AGE",width:100,edittype:"text",editable:true,Gtype:"date", editoptions:{  maxlength:20, dataInit : function (elem) {$(elem).attr('readonly','readonly');},dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'AGE'  }  ] } ,align:"center"}
        	 ,{index:"AGE_DATE",name:"AGE_DATE",width:25,edittype:"text",formatter:"calendar",editable:false,imagePath:"",align:"center",imageStyle:""}
        	 ,{index:"NAME",name:"NAME",width:185,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'NAME'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"EMAIL",name:"EMAIL",width:284,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'EMAIL'  }  ] }  ,cellattr:rowspan,align:"center"}
	        ],
        	OnEditCell : function(rowid, colname, value, iCol,iRow) {  
        	}  
        	,OnEnterRow : function(rowindex, selrow) {  
        	}  
        	,OnLoadComplete : function() {  
        		grid1_OnLoadComplete();  

	         var gridName ='grid1';

	         $("[colheight=grid1]").css('height',"26px");
        		 $("#paginate_grid1").hide();
        		 
        		 
        		 
        		 
        $('#grid1_AGE_DATE').css('display','none');
        $('table.ui-wizgrid-htable tr.ui-wizgrid-labels #grid1_AGE').attr('colspan','2').css('width','130');
        	}  
        });


        grid2.wizGrid({   
        	data:grid2_Data,   
        	height:231,  
        	width:894,    
        	multiselect:true,       
        	cellEdit:true,   
        	Names: ["ROWID","S","사원 ID","나이","","이름","EMAIL"],
        	Columns: [  
        	 {index: "id", name:"id", width:75, key:true, hidden:true }        	, {index:'IUDFLAG',name:'IUDFLAG',editable:false,width:10,formatter:'select',edittype:'select',editoptions:{ value:':;I:I;U:U;D:D',defaultValue:'' }}
        	 ,{index:"EMPLOYEEID",name:"EMPLOYEEID",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'EMPLOYEEID'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"AGE",name:"AGE",width:100,edittype:"text",editable:true,Gtype:"date", editoptions:{  maxlength:20, dataInit : function (elem) {$(elem).attr('readonly','readonly');},dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'AGE'  }  ] } ,align:"center"}
        	 ,{index:"AGE_DATE",name:"AGE_DATE",width:25,edittype:"text",formatter:"calendar",editable:false,imagePath:"",align:"center",imageStyle:""}
        	 ,{index:"NAME",name:"NAME",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'NAME'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"EMAIL",name:"EMAIL",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'EMAIL'  }  ] }  ,cellattr:rowspan,align:"center"}
	        ],
        	OnEditCell : function(rowid, colname, value, iCol,iRow) {  
        	}  
        	,OnEnterRow : function(rowindex, selrow) {  
        	}  
        	,OnLoadComplete : function() {  

	         var gridName ='grid2';

	         $("[colheight=grid2]").css('height',"22px");
        		 $("#paginate_grid2").hide();
        		 
        		 
        		 
        		 
        $('#grid2_AGE_DATE').css('display','none');
        $('table.ui-wizgrid-htable tr.ui-wizgrid-labels #grid2_AGE').attr('colspan','2').css('width','130');
        	}  
        });



    }

        function AGE_DATE_OnClick_grid1(rowid,iCol) { 
         if(typeof AGE_DATE_dateonclick_grid1 == "function"){
          if( AGE_DATE_dateonclick_grid1(rowid)){ 
          return;
 		}  
	} 
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
         ,afterShow: function(input, inst) { 
            $("#ui-datepicker-div > table").hide(); } 
        ,onClose: function(dateText, inst) { 
               var thisCalendar = $(this); 
               thisCalendar.datepicker('setDate', new Date(inst.selectedYear, inst.selectedMonth, inst.selectedDay)); 
                           DateClose(iRow,iCol,grid1);
         }                      ,onSelect : function(dateText, inst) {
                           DateClose(iRow,iCol,grid1);
                       }
              }); 
         },10);
        } 

        function AGE_DATE_OnClick_grid2(rowid,iCol) { 
         if(typeof AGE_DATE_dateonclick_grid2 == "function"){
          if( AGE_DATE_dateonclick_grid2(rowid)){ 
          return;
 		}  
	} 
            var colSaveName = grid2.wizGrid('getGridParam', 'Columns');
            iCol = iCol-1; 
            var iRow = grid2.getRowIdx(rowid);
            setTimeout(function() { 
              grid2.editCell(iRow, iCol, true);
              $('#' + iRow + '_' + colSaveName[iCol]['name'])
                  .datepicker({
                      inline:true
                      ,dateFormat:"yy-mm-dd"
                      ,changeMonth:true
                      ,changeYear:true
                      ,showButtonPanel:true
                      ,currentText:"today"
                      ,closeText:"close"
        ,onClose: function(dateText, inst) { 
               var thisCalendar = $(this); 
               thisCalendar.datepicker('setDate', new Date(inst.selectedYear, inst.selectedMonth, inst.selectedDay)); 
                           DateClose(iRow,iCol,grid2);
         }                      ,onSelect : function(dateText, inst) {
                           DateClose(iRow,iCol,grid2);
                       }
              }); 
         },10);
        } 


    $(document).ready(function () { 
        initGrids();
        grid1.setDatasetName("S1");        
        grid2.setDatasetName("S1");        
    });
</script>
</html>