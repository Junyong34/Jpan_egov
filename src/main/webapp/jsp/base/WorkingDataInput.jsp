<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title> Working Data Input Screen</title>
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
        	$('#btn_upload').click(function(e){
        		$("#S1_FILE").val("");
        		e.preventDefault();   
        		$("#S1_FILE").click();
        	});      
        	  $(document).keydown(function(e){   
                   
                   if(e.target.nodeName != "INPUT" && e.target.nodeName != "TEXTAREA"){       
                    if(e.keyCode === 8){   
                    return false;
                     }
                    }
                  });                   
        });                                     
        
        function FIleUPload_S1_FILE()
        {
          if( $("#out_message").val() != "" )
          {
            ErrorCheck();
            return;
          }
        
          if($("#S1_FILE").val() != "")
          {
           wizutil.FileAjaxSubmit(Contextpath+'/jsp/File/fileupload.jsp', fileform, 'S1_FILE', 'uploadsubmit', ['xls','xlsx']);
          }
        }
        
        function S1_FILE_OnChange(myObj)
        {
            sbmCtlWrite.submit(false, "FIleUPload_S1_FILE");
        	//FIleUPload_S1_FILE();
        }
        
        function uploadsubmit()
        {
        
        	sbmUpload.submit(false,"ErrorCheck");
        }
        
        
        
        
        function ErrorCheck()
        {
        
          if(ErroMsgDisplay() != 0) {
            
           // 메시지 코드드
           var ERROR_CODE ="";
           ERROR_CODE= $("#out_message").val();
           
           if(ErroMsgDisplay() == 9999){
                ERROR_CODE= 9999;
           }
        
           var param = ""
           param += "?ERROR_CODE="+ERROR_CODE+"&ERROR_MSG="+ErroMsgDisplay();
            
           var url = "/hpms/20160906131150ErrorDisplay_loadpage.do"+param;
        	 
        	   $("#Error_cd").attr("src",url);
        	   $("#Error_cd").attr("frameborder", 0);
        	   $("#divPopup_ErrorCode").dialog({
        			autoPoen:false,
        			height : 260,
        			//position:[50,300],
        			width:530,
        			modal: true, 
        			title: "Error Indication",
        			resizable: true,
        			closeOnEscape:true ,
        		 
        		close: function(){ 
                 
        	   // window.location.reload();
        	   // iframe 초기화.
               $("#" + this.id).find('iframe').contents().find('body').html('');
              	}
              	});
        	   $("#divPopup_ErrorCode").dialog("open"); 
           	}else{
              //오류업시 성공적으로 업로드 완료 했을 때    	
           	  alert("Upload complete");
           	}
          }
        	
          function dialogClose_MYPAGE(){ //팝업종료및 
        	$("#divPopup_ErrorCode").dialog("close");
          }
        
        function btn_error_OnClick(myObj)
        {
        
           if(  $("#out_message").val() == "" ||  $("#out_message").val() == "0" )
           {
           
           		Return_RTN_MSG("00032");
                return;
           }
        
           if( $("#out_message").val() == "1012" ||  $("#out_message").val() == "1013" || 
               $("#out_message").val() == "1010" ||  $("#out_message").val() == "1011" )
           {
           	  Return_RTN_MSG("00033");
              return;
           }
           
           FormSubmit("frm" , "/hpms/20160912111169WorkDataInput_errorConfirm.do" , "S9[A]:S9" ,"", false); 
           
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
        	<div  class="titleLabel" style=" position: absolute; top:22px; left:30px;  width:435px; height:34px; " id="label1" name="label1"><label style=" line-height:36px; " >     Working Man-Day Input     </label>    </div>

        	<div   id="user1" style="border-width:1px; border:solid; background-color:Transparent; color:Black;border-color:Transparent;text-align:left; position: absolute; top:25px; left:485px;  width:78px; height:79px; " >
<!-- Error Code screen -->
<div id="divPopup_ErrorCode" name="divPopup_ErrorCode" style="display:none;background-color:white;">
	<iframe id="Error_cd" name="Error_cd" style="width:500px;height:190px;" scrolling="auto">
	</iframe>
</div></div>

        	<input  class="btn2" style=" position: absolute; top:104px; left:250px;  width:125px; height:35px; " id="btn_error" name="btn_error" type="button" value="Error Confirm" tabindex="0"  onclick="btn_error_OnClick(this);" ></input>

        	<input  class="btn2" style=" position: absolute; top:104px; left:101px;  width:125px; height:35px; " id="btn_upload" name="btn_upload" type="button" value="Upload" tabindex="0"  ></input>

        	<input  class="TextBox-S" style=" position: absolute; top:110px; left:387px;  width:97px; height:17px; " id="S9_LOG_SEQ" name="S9_LOG_SEQ" type="hidden" maxlength="50" tabindex="0" >    </input>

        	<input type='hidden'  id="S1_FILE_NAME" name="S1_FILE_NAME"/>
    	<input type='hidden'  id="S1_FILE_PATH" name="S1_FILE_PATH"/>
    	<input type='hidden'  id="S1_FILE_UNNAME" name="S1_FILE_UNNAME"/>
    	<input type='hidden'  id="S1_FILE_SIZE" name="S1_FILE_SIZE"/>

</form>	
<form id='fileform' name='fileform' encType='multipart/form-data'>
        	<input type='file' style="border:solid; border-width:0px;background-color:White; color:Black;border-color:Black;text-align:left; position: absolute; top:148px; left:105px;  width:100px;" id="S1_FILE" name="S1_FILE" tabindex="0"  onchange="S1_FILE_OnChange(this);" ></input>
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
    var sbmUpload = new SubMission( frm, "/hpms/20160912111169WorkDataInput_workCountUpload.do","S1[A]:S1","S9:ER01","");
    var sbmCtlWrite = new SubMission( frm, "/hpms/20161004133312Control_MonthlyUsingCheck.do","","","");
    sbmInit.InitCombo("");
    var DefalueGrid =[] ; 
    var initGrids = function() { 

    }


    $(document).ready(function () { 
        initGrids();
    });
        wizutil.setVisible("user1",false);
        wizutil.setVisible("S9_LOG_SEQ",false);
        wizutil.setVisible("S1_FILE",false);
</script>
</html>