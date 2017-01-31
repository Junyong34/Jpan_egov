<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>마스터 입출력</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/hpms/wizware/css/zTreeStyle.css"/>
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/hpms/wizware/css/jquery-ui.min.css"/>
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/hpms/wizware/css/demo.css"/>
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/hpms/wizware/css/PBUIDefalut.css"/>
        <link rel="stylesheet" id="wizcss"  type="text/css" href="/hpms/wizware/css/UI.WizGrid.css"/>
        <link rel="stylesheet" id="scostStyle.css"  type="text/css" href="/hpms/css/scostStyle.css"/>
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/hpms/wizware/css/zTreeStyle.css"/>
        <script type="text/javascript" src="/hpms/wizware/js/jquery-1.11.0.min.js"></script>
        <script type="text/javascript" src="/hpms/wizware/js/grid.locale-en.js"></script>
        <script type="text/javascript" src="/hpms/wizware/js/jquery-ui.js"></script>
        <script type="text/javascript" src="/hpms/wizware/js/jquery.ztree.all-3.5.js"></script>
        <script type="text/javascript" src="/hpms/wizware/js/HuskyEZCreator.js"></script>
        <script type="text/javascript" src="/hpms/wizware/js/paging.js"></script>
        <script type="text/javascript" src="/hpms/wizware/js/jquery.form.js"></script>
        <script type="text/javascript" src="/hpms/wizware/js/Wizware_WizGrid.js"></script>
        <script type="text/javascript" src="/hpms/wizware/js/WizGrid.js"></script>
        <script type="text/javascript" src="/hpms/wizware/js/wizware.js"></script>
        <script type="text/javascript" src="/hpms/js/hcost.js"></script>
        <script> 
        $(function(){          
        	/*$('#UPLOAD').click(function(e){
        		$("#F_FILE").val("");
        		e.preventDefault();   //이벤트 중단 현재	         
        		$("#F_FILE").click();             
        	});    
        	
         	$('#UPLOAD2').click(function(e){
        		$("#F_FILE2").val("");
        		e.preventDefault();   //이벤트 중단 현재	         
        		$("#F_FILE2").click();             
        	}); 
        	
        	$('#UPLOAD3').click(function(e){
        		$("#F_FILE3").val("");
        		e.preventDefault();   //이벤트 중단 현재	         
        		$("#F_FILE3").click();             
        	}); */
        });  
        function Result(){
        Return_RTN_MSG();
        
        if($("#out_code").val() == 0) Return_RTN_MSG("00002");
        }
        
        function FIleUPload_F_FILE()
        {
        
           if($("#F_FILE").val() != "")wizutil.FileAjaxSubmit(Contextpath+'/jsp/File/fileupload.jsp', fileform, 'F_FILE', '', ['xls','xlsx']);
        }
        function FIleUPload_F_FILE2()
        {
            if($("#F_FILE2").val() != "")wizutil.FileAjaxSubmit(Contextpath+'/jsp/File/fileupload.jsp', fileform, 'F_FILE2', '', ['xls','xlsx']);
        }
        function FIleUPload_F_FILE3()
        {
           if($("#F_FILE3").val() != "")wizutil.FileAjaxSubmit(Contextpath+'/jsp/File/fileupload.jsp', fileform, 'F_FILE3', '', ['xls','xlsx']);
        }
        
        
        function F_FILE_OnChange(myObj)
        {
        	$("#S_FLAG").val("CURRENCY");
        	FIleUPload_F_FILE();
        
        }
        function F_FILE2_OnChange(myObj)
        {
        FIleUPload_F_FILE2();
        
        }
        function F_FILE3_OnChange(myObj)
        {
        FIleUPload_F_FILE3();
        
        }
        //업로드 파일 인서트 시킴킴
        function UPLOAD_OnClick(myObj)
        {
        $("#F_FILE").val("");
        
          CurrencyExcel.submit(false,"Result");
        }
        function UPLOAD2_OnClick(myObj)
        {
        $("#F_FILE2").val("");
        }
        function UPLOAD3_OnClick(myObj)
        {
        $("#F_FILE3").val("");
        }
        
        
        function btn_currency_down_OnClick(myObj)
        {
        	$("#S_FLAG").val("CURRENCY");
        	FormSubmit("frm" , "/hpms/hpms.do?SYSID=HPMS&MENUID=20160901091140&EVENTID=MasterExcelDown" , "S[A]:S" ,"",false); 
        }
        function btn_application_down_OnClick(myObj)
        {
        	$("#S_FLAG").val("APPLICATION");
        	FormSubmit("frm" , "/hpms/hpms.do?SYSID=HPMS&MENUID=20160901091140&EVENTID=MasterExcelDown" , "S[A]:S" ,"",false); 
        }
        function btn_department_down_OnClick(myObj)
        {
        	$("#S_FLAG").val("DEPARTMENT");
        	FormSubmit("frm" , "/hpms/hpms.do?SYSID=HPMS&MENUID=20160901091140&EVENTID=MasterExcelDown" , "S[A]:S" ,"",false); 
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
        	<div  class="titleLabel" style=" position: absolute; top:22px; left:30px;  width:435px; height:34px; " id="label1" name="label1"><label style=" line-height:36px; " >    Master Input and Output     </label>    </div>

        	<input  class="btn2" style=" position: absolute; top:100px; left:397px;  width:73px; height:36px; " id="UPLOAD" name="UPLOAD" type="button" value="UPLOAD" tabindex="0"  onclick="UPLOAD_OnClick(this);" ></input>

        	<input  class="btn2" style=" position: absolute; top:100px; left:526px;  width:73px; height:36px; " id="btn_currency_down" name="btn_currency_down" type="button" value="Download" tabindex="0"  onclick="btn_currency_down_OnClick(this);" ></input>

        	<div  class="infoLabel" style=" position: absolute; top:101px; left:48px;  width:123px; height:29px; " id="label2" name="label2"><label style=" line-height:31px; " >    Currency Master     </label>    </div>

        	<input type='hidden'  id="F_FILE_NAME" name="F_FILE_NAME"/>
    	<input type='hidden'  id="F_FILE_PATH" name="F_FILE_PATH"/>
    	<input type='hidden'  id="F_FILE_UNNAME" name="F_FILE_UNNAME"/>
    	<input type='hidden'  id="F_FILE_SIZE" name="F_FILE_SIZE"/>

        	<input  class="btn2" style=" position: absolute; top:160px; left:526px;  width:73px; height:36px; " id="btn_department_down" name="btn_department_down" type="button" value="Download" tabindex="0"  onclick="btn_department_down_OnClick(this);" ></input>

        	<input  class="btn2" style=" position: absolute; top:160px; left:397px;  width:73px; height:36px; " id="UPLOAD2" name="UPLOAD2" type="button" value="UPLOAD" tabindex="0"  onclick="UPLOAD2_OnClick(this);" ></input>

        	<div  class="infoLabel" style=" position: absolute; top:161px; left:48px;  width:123px; height:29px; " id="label3" name="label3"><label style=" line-height:31px; " >    Department Master     </label>    </div>

        	<input type='hidden'  id="F_FILE2_NAME" name="F_FILE2_NAME"/>
    	<input type='hidden'  id="F_FILE2_PATH" name="F_FILE2_PATH"/>
    	<input type='hidden'  id="F_FILE2_UNNAME" name="F_FILE2_UNNAME"/>
    	<input type='hidden'  id="F_FILE2_SIZE" name="F_FILE2_SIZE"/>

        	<input  class="btn2" style=" position: absolute; top:220px; left:526px;  width:73px; height:36px; " id="btn_application_down" name="btn_application_down" type="button" value="Download" tabindex="0"  onclick="btn_application_down_OnClick(this);" ></input>

        	<div  class="infoLabel" style=" position: absolute; top:220px; left:50px;  width:123px; height:29px; " id="label4" name="label4"><label style=" line-height:31px; " >    Allocation Master     </label>    </div>

        	<input  class="btn2" style=" position: absolute; top:220px; left:397px;  width:73px; height:36px; " id="UPLOAD3" name="UPLOAD3" type="button" value="UPLOAD" tabindex="0"  onclick="UPLOAD3_OnClick(this);" ></input>

        	<input type='hidden'  id="F_FILE3_NAME" name="F_FILE3_NAME"/>
    	<input type='hidden'  id="F_FILE3_PATH" name="F_FILE3_PATH"/>
    	<input type='hidden'  id="F_FILE3_UNNAME" name="F_FILE3_UNNAME"/>
    	<input type='hidden'  id="F_FILE3_SIZE" name="F_FILE3_SIZE"/>

        	<input  class="TextBox-S" style=" position: absolute; top:281px; left:48px;  width:97px; height:17px; " id="S_FLAG" name="S_FLAG" type="text" maxlength="50" tabindex="0" >    </input>

</form>	
<form id='fileform' name='fileform' encType='multipart/form-data'>
        	<input type='file' style="border:solid; border-width:0px;background-color:White; color:Black;border-color:Black;text-align:left; position: absolute; top:107px; left:183px;  width:196px;" id="F_FILE" name="F_FILE" tabindex="0"  onchange="F_FILE_OnChange(this);" ></input>
        	<input type='file' style="border:solid; border-width:0px;background-color:White; color:Black;border-color:Black;text-align:left; position: absolute; top:165px; left:183px;  width:196px;" id="F_FILE2" name="F_FILE2" tabindex="0"  onchange="F_FILE2_OnChange(this);" ></input>
        	<input type='file' style="border:solid; border-width:0px;background-color:White; color:Black;border-color:Black;text-align:left; position: absolute; top:227px; left:183px;  width:196px;" id="F_FILE3" name="F_FILE3" tabindex="0"  onchange="F_FILE3_OnChange(this);" ></input>
	<div style='display:none'>
	<iframe id='FileDownload'  name='FileDownload' style='border-width:0px;' src='' frameborder='0' ></iframe>
	</div>
	
</form>
    </div>
</body>
<script type="text/javascript" >
    var GridRowHeight= {};
    var ChkNumberColumns= [];
    var RadioProp= {};
    var CheckProp= {};
    var ComboObjProp= {};
    var RequiredProp= {};
    var CellEditoption= {};
    var InitItems= {};
    var sbmInit = new SubMission( frm, "/hpms","","","");
    var CurrencyExcel = new SubMission( frm, "/hpms/hpms.do?SYSID=HPMS&MENUID=2016082417899&EVENTID=ForecastTitleSet","F[A]:F,S[A]:S","","");
    sbmInit.InitCombo("");
    var DefalueGrid =[] ; 
    var initGrids = function() { 

    }


    $(document).ready(function () { 
        initGrids();
    });
        wizutil.setVisible("S_FLAG",false);
</script>
</html>