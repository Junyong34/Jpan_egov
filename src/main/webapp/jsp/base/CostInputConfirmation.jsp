<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>Cost Input</title>
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
        $(document).ready(function(e) {
        
          $(document).keydown(function(e){   
                   
                    if(e.target.nodeName != "INPUT" && e.target.nodeName != "TEXTAREA"){       
                    if(e.keyCode === 8){   
                    return false;
                     }
                    }
                  });
        
        });
        
        
        $(function(){          
        	$('#UPLOAD').click(function(e){
        		$("#S1_FILE").val("");
        		e.preventDefault();   //이벤트 중단 현재	         
        		$("#S1_FILE").click();             
        	});    
        }); 
        
        function FIleUPload_F_FILE()
        {
         
         if( $("#out_message").val() != "" )
         {
            ErrorCheck();
            return;
         }
        
         if(  $("#S2_DATA_TYPE").val() == "")
         {
        	Return_RTN_MSG("00004","Condition");
        	return;
         }
        
         if($("#S1_FILE").val() != "") 
            wizutil.FileAjaxSubmit(Contextpath+'/jsp/File/fileupload.jsp', fileform, 'S1_FILE', 'uploadsubmit', ['xls','xlsx']);
        }
        
        function S2_FILE_OnChange(myObj)
        {
        	sbmCtlWrite.submit(false, "FIleUPload_F_FILE");
        }
        
        function uploadsubmit()
        {
        	sbmUpload.submit(false,"ErrorCheck");
        }
        
        function btn_download_OnClick(myObj)
        {
        	sbmCtlRead.submit(false, "download");
        //	download();
        }
        
        function download()
        {
          if( $("#out_message").val() != "" )
          {
            ErrorCheck();
            return;
          }
          
          if(  $("#S2_DATA_TYPE").val() == "")
          {
        	Return_RTN_MSG("00004","Condition");;
        	return;
          }
        	
          FormSubmit("frm" , "/hpms/20160901101142CostInput_csvdownload.do" , "S2[A]:S2" ,"",false); 
        }
        
        function ErrorCheck()
        {
        	if(ErroMsgDisplay() != 0) 
        	{
        	    
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
        	     
        			//	window.location.reload();
        				// iframe 초기화.
        	   $("#" + this.id).find('iframe').contents().find('body').html('');
        	  	}
        	  	});
        		   $("#divPopup_ErrorCode").dialog("open"); 
        	 }
        	 else
        	 {
        	     //오류업시 성공적으로 업로드 완료 했을 때    	
        	   	  alert("Upload complete");
             }
        }
        	
        function dialogClose_MYPAGE(){ //팝업종료및 
        	$("#divPopup_ErrorCode").dialog("close");
        }
        
        function btn_Error_OnClick(myObj)
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
        	              
           FormSubmit("frm" , "/hpms/20160901101142CostInput_errorConfirm.do" , "S9[A]:S9" ,"", false); 
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
        	<div   id="user1" style="border-width:1px; border:solid; background-color:Transparent; color:Black;border-color:Transparent;text-align:left; position: absolute; top:26px; left:613px;  width:100px; height:100px; " >
<!-- Error Code screen -->
<div id="divPopup_ErrorCode" name="divPopup_ErrorCode" style="display:none;background-color:white;">
	<iframe id="Error_cd" name="Error_cd" style="width:500px;height:190px;" scrolling="auto">
	</iframe>
</div></div>

        	<div  class="titleLabel" style=" position: absolute; top:27px; left:29px;  width:435px; height:34px; " id="label1" name="label1"><label style=" line-height:36px; " >    Cost Input     </label>    </div>

        	<select  style="border-width:1px; background-color:White; color:Black;border-color:Black;text-align:left;  font-family:굴림체;font-size:9.75pt; position: absolute; top:111px; left:145px;  width:160px; height:21px; " id="S2_DATA_TYPE" name="S2_DATA_TYPE" tabindex="0" eltype="Combo" >
    	</select>

        	<div  class="infoLabel" style=" position: absolute; top:111px; left:73px;  width:71px; height:19px; " id="label2" name="label2"><label style=" line-height:21px; " >    Condition     </label>    </div>

        	<input  class="TextBox-S" style=" position: absolute; top:146px; left:529px;  width:115px; height:17px; " id="S9_LOG_SEQ" name="S9_LOG_SEQ" type="hidden" maxlength="50" tabindex="0" >    </input>

        	<input  class="btn2" style=" position: absolute; top:165px; left:332px;  width:112px; height:29px; " id="btn_Error" name="btn_Error" type="button" value="Error Confirm" tabindex="0"  onclick="btn_Error_OnClick(this);" ></input>

        	<input  class="btn2" style=" position: absolute; top:165px; left:203px;  width:112px; height:29px; " id="UPLOAD" name="UPLOAD" type="button" value="Upload" tabindex="0"  ></input>

        	<input  class="btn2" style=" position: absolute; top:166px; left:72px;  width:112px; height:29px; " id="btn_download" name="btn_download" type="button" value="Download" tabindex="0"  onclick="btn_download_OnClick(this);" ></input>

        	<input type='hidden'  id="S1_FILE_NAME" name="S1_FILE_NAME"/>
    	<input type='hidden'  id="S1_FILE_PATH" name="S1_FILE_PATH"/>
    	<input type='hidden'  id="S1_FILE_UNNAME" name="S1_FILE_UNNAME"/>
    	<input type='hidden'  id="S1_FILE_SIZE" name="S1_FILE_SIZE"/>

</form>	
<form id='fileform' name='fileform' encType='multipart/form-data'>
        	<input type='file' style=" position: absolute; top:226px; left:76px;  width:100px;width:0px;height:0px;" id="S1_FILE" name="S1_FILE" tabindex="0"  onchange="S2_FILE_OnChange(this);" ></input>
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
    var ComboObjProp= {"S2_DATA_TYPE":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":""}};
    var RequiredProp= {};
    var CellEditoption= {};
    var InitItems= {"S2_DATA_TYPE":":"};
    var sbmInit = new SubMission( frm, "/hpms/20160901101142CostInput_Condition_Combo.do","","S2_DATA_TYPE:SEL2","");
    var sbmUpload = new SubMission( frm, "/hpms/20160901101142CostInput_CostUpload.do","S1[A]:S1,S2[A]:S2","S9:SEL33","");
    var sbmCtlWrite = new SubMission( frm, "/hpms/20161004133312Control_MonthlyUsingCheck.do","","","");
    var sbmCtlRead = new SubMission( frm, "/hpms/20161004133312Control_SearchUsingCheck.do","","","");
    sbmInit.InitCombo("S2_DATA_TYPE");
    var DefalueGrid =[] ; 
    var initGrids = function() { 

    }


    $(document).ready(function () { 
        sbmInit.submit(false, "initGrids");
    });
        wizutil.setVisible("user1",false);
</script>
</html>