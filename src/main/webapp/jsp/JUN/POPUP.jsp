<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>팝업화면</title>
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
        $(document).ready(function () { 
              $('#input1').val(getParamdata("SAWONNO"));
              $('#input2').val(getParamdata("SAWONNM"));
           });
        
        
        function button1_OnClick(myObj)
        {
        
        SAWONLIST.submit(false);
        }
        function grid1_OndblClickRow(rowIndex)
        {;
               var VAL_1 = grid1.getCellValue("R"+rowIndex, "SAWONNM", true);
                var VAL_2 = grid1.getCellValue("R"+rowIndex, "SAWONNO", true);
                alert(VAL_1);
               $("#input2",parent.document).val(VAL_1);
               $("#input1",parent.document).val(VAL_2);
             $(parent.location).attr("href","javascript:fnSubOffClose();"); 
                
        
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
        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:30px; left:711px;  width:101px; height:21px; " id="button2" name="button2" type="button" value="button" tabindex="0"  ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:34px; left:508px;  width:104px; height:41px; " id="button1" name="button1" type="button" value="사원조회" tabindex="0"  onclick="button1_OnClick(this);" ></input>

        	<input  class="TextBox-S" style=" position: absolute; top:39px; left:196px;  width:97px; height:17px; " id="input2" name="input2" type="text" maxlength="50" tabindex="0"  onchange="wizutil.CheckNumberValue(this);">    </input>

        	<input  class="TextBox-S" style=" position: absolute; top:39px; left:78px;  width:97px; height:17px; " id="input1" name="input1" type="text" maxlength="50" tabindex="0"  onchange="wizutil.CheckNumberValue(this);">    </input>

        <div id="grid1_gdiv"  style=" position: absolute; top:104px; left:28px; width:886px;height:459px;" >
		     <table id="grid1" elType='Grid' ></table>
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
    var GridRowHeight= {"grid1":"22",};
    var ChkNumberColumns= [];
    var RadioProp= {};
    var CheckProp= {};
    var ComboObjProp= {};
    var RequiredProp= {};
    var CellEditoption= {};
    var InitItems= {};
    var sbmInit = new SubMission( frm, "/PBCloud","","","");
    var SAWONLIST = new SubMission( frm, "/PBCloud/20160401107892DEMOJUN_SAWONLIST.do","","grid1:SEL2","");
    sbmInit.InitCombo("");
    var grid1= jQuery("#grid1");
    var grid1_Data = [];
    function initGrids() { 
        grid1.wizGrid({   
        	data:grid1_Data,   
        	height:420,  
        	width:886,    
        	rownumbers:true,    
        	cellEdit:true,   
        	Names: ["ROWID","S","사원번호","사원명","주소","비고","부서코드","입사일자","직급코드","결혼여부","성별"],
        	Columns: [  
        	 {index: "id", name:"id", width:75, key:true, hidden:true }        	, {index:'IUDFLAG',name:'IUDFLAG',editable:false,width:10,formatter:'select',edittype:'select',hidden:true,editoptions:{ value:':;I:I;U:U;D:D',defaultValue:'' }}
        	 ,{index:"SAWONNO",name:"SAWONNO",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'SAWONNO'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"SAWONNM",name:"SAWONNM",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'SAWONNM'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"ADDR",name:"ADDR",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ADDR'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"BIGO",name:"BIGO",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'BIGO'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"DEPTCD",name:"DEPTCD",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'DEPTCD'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"IPSADATE",name:"IPSADATE",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'IPSADATE'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"JIKGEUPCD",name:"JIKGEUPCD",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'JIKGEUPCD'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"MARRYYN",name:"MARRYYN",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'MARRYYN'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"SEX",name:"SEX",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'SEX'  }  ] }  ,cellattr:rowspan,align:"center"}
	        ],
        	OnEditCell : function(rowid, colname, value, iCol,iRow) {  
        	}  
        	,OnEnterRow : function(rowindex, selrow) {  
        	}  
        	,OndblClickRow : function(rowid) {  
        	 var rowIndex = grid1.getRowIndex(rowid);  
        		grid1_OndblClickRow(rowIndex);  
        	}  
        	,OnLoadComplete : function() {  

	         var gridName ='grid1';
        		 $("#paginate_grid1").hide();
        		 
        		 
        		 
        		 
        	}  
        });


    }


    $(document).ready(function () { 
        initGrids();
        grid1.setDatasetName("S1");        
    });
</script>
</html>