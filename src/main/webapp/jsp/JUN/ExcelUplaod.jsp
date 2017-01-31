<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>엑셀업로드(데모)</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" type="text/css" href="/PBCloud/wizware/css/zTreeStyle.css"/>
        <link rel="stylesheet" type="text/css" href="/PBCloud/wizware/css/jquery-ui.min.css"/>
        <link rel="stylesheet" type="text/css" href="/PBCloud/wizware/css/demo.css"/>
        <link rel="stylesheet" type="text/css" href="/PBCloud/wizware/css/UI.WizGrid.css"/>
        <link rel="stylesheet" type="text/css" href="/PBCloud/wizware/css/PBUIDefalut.css"/>
        <link rel="stylesheet" type="text/css" href="/PBCloud/wizware/css/default.css"/>
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
        <script> 
        function button2_OnClick(myObj)
        {
          isloadingCheck =true;
           Execelupload_jun.submit(false);
         
        }
        function FIleUPload_S1_FILE()
        {
        wizutil.FileAjaxSubmit(Contextpath+'/jsp/File/fileupload.jsp', fileform, 'S1_FILE', 'Excel', ['xls','xlsx']);
        }
        function Excel(){
        
        }
        function S1_FILE_OnChange(myObj)
        {
        
        FIleUPload_S1_FILE();
        }
        
        function button3_OnClick(myObj)
        { 
                $("#group1").dialog({  
                        autoOpen: false,  
                        height: 620,  
                        width: 1085, 
                        modal: true,  
                        title: "제목",  
                        resizable: true,  
                        closeOnEscape: true 
                      
               
                       , create: function (event, ui) {  
                        },  
                        open: function (event, ui) {  
                        },  
                        close: function () {  
                        },  
                        cancle: function () {  
                        }  
                    });  
                    $("#group1").dialog("open"); 
        
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
        	<input  class="TextBox-S" style=" font-family:굴림체;font-size:9.75pt; position: absolute; top:32px; left:147px;  width:97px; height:17px; " id="S1_GUBUN" name="S1_GUBUN" type="text" value="JUN" maxlength="50" tabindex="0"  onchange="wizutil.CheckNumberValue(this);wizutil.CheckNumberValue(this);">    </input>

        	<input  style=" font-family:굴림체;font-size:9.75pt;border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center; position: absolute; top:39px; left:484px;  width:122px; height:38px; " id="button1" name="button1" type="button" value="저장" tabindex="0"  ></input>

        	<input  style=" font-family:굴림체;font-size:9.75pt;border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center; position: absolute; top:39px; left:614px;  width:122px; height:38px; " id="button2" name="button2" type="button" value="엑셀업로드" tabindex="0"  onclick="button2_OnClick(this);" ></input>

        	<input  style=" font-family:굴림체;font-size:9.75pt;border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center; position: absolute; top:73px; left:817px;  width:101px; height:21px; " id="button3" name="button3" type="button" value="button" tabindex="0"  onclick="button3_OnClick(this);" ></input>

        	<input type='hidden'  id="S1_FILE_NAME" name="S1_FILE_NAME"/>
    	<input type='hidden'  id="S1_FILE_PATH" name="S1_FILE_PATH"/>
    	<input type='hidden'  id="S1_FILE_UNNAME" name="S1_FILE_UNNAME"/>
    	<input type='hidden'  id="S1_FILE_SIZE" name="S1_FILE_SIZE"/>

        <div id="grid1_gdiv"  style=" position: absolute; top:129px; left:21px; width:733px;height:209px;" >
		     <table id="grid1" elType='Grid' ></table>
	    </div>

</form>	
<form id='fileform' name='fileform' encType='multipart/form-data'>
        	<input type='file' style="border-width:0px;background-color:White; color:Black;border-color:Black;text-align:left; position: absolute; top:80px; left:118px;  width:344px;" id="S1_FILE" name="S1_FILE" tabindex="0"  onchange="S1_FILE_OnChange(this);" ></input>
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
    var ComboObjProp= {};
    var RequiredProp= {};
    var InitItems= {};
    var sbmInit = new SubMission( frm, "/PBCloud","","","");
    var Execelupload_jun = new SubMission( frm, "/PBCloud/20160401107892DEMOJUN_ExcelUpload.do","S1[A]:S1","grid1:SEL12","");
    sbmInit.InitCombo("");
    var grid1= jQuery("#grid1");
    grid1.setDatasetName("S");
    var grid1_Data = [];
    function initGrids() { 
        grid1.wizGrid({   
        	data:grid1_Data,   
        	height:147,  
        	width:733,    
        	multiselect:true,       
        	cellEdit:true,   
        	Names: ["ROWID","S","사원번호","사원명","주소","부서코드","비교"],
        	Columns: [  
        	 {index: "id", name:"id", width:75, key:true, hidden:true }        	, {index:'IUDFLAG',name:'IUDFLAG',editable:false,width:10,formatter:'select',edittype:'select',editoptions:{ value:':;I:I;U:U;D:D',defaultValue:'' }}
        	 ,{index:"SAWONNO",name:"SAWONNO",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'SAWONNO'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"SAWONNM",name:"SAWONNM",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'SAWONNM'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"ADDR",name:"ADDR",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ADDR'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"DEPTCD",name:"DEPTCD",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'DEPTCD'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"BIGO",name:"BIGO",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'BIGO'  }  ] }  ,cellattr:rowspan,align:"center"}
	        ],
        	OnAfterSaveCell : function(rowid, colname, value, iCol,iRow) {  
        	}  
        	,OnEnterRow : function(rowindex, selrow) {  
        	}  
        	,OnLoadComplete : function() {  
        		 $("#paginate_grid1").hide();
        		
	         var gridName ='grid1';
 
        	}  
        });


    }


    $(document).ready(function () { 
        initGrids();
    });
</script>
</html>