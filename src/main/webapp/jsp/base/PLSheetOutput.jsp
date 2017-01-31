<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title> PL Sheet Output</title>
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
        $(document).ready(function() {
             // Combo2.submit(false);  
             
              $(document).keydown(function(e){   
                   
                    if(e.target.nodeName != "INPUT" && e.target.nodeName != "TEXTAREA"){       
                    if(e.keyCode === 8){   
                    return false;
                     }
                    }
                  });
        
         });
             
        function btn_download_OnClick(myObj)
        {
        	sbmCtlRead.submit(false, "download");
        }
        function download()
        {
           if( $("#out_message").val() != "" )
           {
        	    ErrorCheck();
        	    return;
           }
        
           if($("#S2_PID").val() == "")
        	{
        		Return_RTN_MSG("00003","PID");
        		return;
        	}
        	
        	if($("#S2_DATA_TYPE").val() == "")
        	{
        		Return_RTN_MSG("00004","Data Type");
        		return;
        	}
        	
        	$("#S3_CNT").val("");
        	sbmPIDCheck.submit(false, "callbackDownload" );
        }
        
        function callbackDownload()
        {
           if($("#S3_CNT").val() == "0")
        	{
        		Return_RTN_MSG("00019","PID");
        		return;
           }
         
           FormSubmit("frm" , "/hpms/20160901101143PLSheetOutput_PLSheetDownload.do" , "S2[A]:S2" ,"" ,false ); 
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
        
        
        function S2_PID_OnKeyUp(myObj)
        {
          if($("#S2_PID").val() != "" && $("#S2_PID").val().length ==6){
           
             TilteCombo.submit(false);
           }
        }
        function S2_DATA_TYPE_OnClick(myObj)
        {
         if($("#S2_DATA_TYPE  option:eq(0)").text() !="--Choice--"&& $("#S2_PID").val()==""){
            Return_RTN_MSG("00003","PID","S2_PID");
            return;
          } 
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
        	<div  class="titleLabel" style=" position: absolute; top:22px; left:30px;  width:435px; height:34px; " id="label1" name="label1"><label style=" line-height:36px; " >    PL Sheet Output     </label>    </div>

        	<div   id="user1" style="border-width:1px; border:solid; background-color:Transparent; color:Black;border-color:Transparent;text-align:left; position: absolute; top:25px; left:532px;  width:100px; height:100px; " >
<!-- Error Code screen -->
<div id="divPopup_ErrorCode" name="divPopup_ErrorCode" style="display:none;background-color:white;">
	<iframe id="Error_cd" name="Error_cd" style="width:500px;height:190px;" scrolling="auto">
	</iframe>
</div></div>

        	<div  class="infoLabel" style=" position: absolute; top:107px; left:58px;  width:124px; height:19px; " id="label2" name="label2"><label style=" line-height:21px; " >    PID     </label>    </div>

        	<input  class="TextBox-S" style=" position: absolute; top:108px; left:186px;  width:141px; height:17px; " id="S2_PID" name="S2_PID" type="text" maxlength="6" tabindex="0"  onkeyup="S2_PID_OnKeyUp(this);">    </input>

        	<div  class="infoLabel" style=" position: absolute; top:145px; left:58px;  width:124px; height:19px; " id="label3" name="label3"><label style=" line-height:21px; " >    Date Type     </label>    </div>

        	<select  class="selectbox" style=" position: absolute; top:145px; left:185px;  width:146px; height:21px; " id="S2_DATA_TYPE" name="S2_DATA_TYPE" tabindex="0" onclick="S2_DATA_TYPE_OnClick(this);" eltype="Combo" >
    	</select>

        	<input  class="btn2" style=" position: absolute; top:215px; left:185px;  width:145px; height:26px; " id="btn_download" name="btn_download" type="button" value="Download" tabindex="0"  onclick="btn_download_OnClick(this);" ></input>

        	<input  class="TextBox-S" style=" position: absolute; top:247px; left:184px;  width:57px; height:17px; " id="S3_CNT" name="S3_CNT" type="text" maxlength="50" tabindex="0" >    </input>

</form>	
<form id='fileform' name='fileform' encType='multipart/form-data'>
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
    var ComboObjProp= {"S2_DATA_TYPE":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}};
    var RequiredProp= {};
    var CellEditoption= {};
    var InitItems= {"S2_DATA_TYPE":":"};
    var sbmInit = new SubMission( frm, "/hpms","","","");
    var sbmPIDCheck = new SubMission( frm, "/hpms/20160901101143PLSheetOutput_PIDCheck.do","S2[A]:S2","S3:SEL1","");
    var sbmCtlRead = new SubMission( frm, "/hpms/20161004133312Control_SearchUsingCheck.do","","","");
    var TilteCombo = new SubMission( frm, "/hpms/20160901101143PLSheetOutput_initCombo.do","S2[A]:S2","S2_DATA_TYPE:SEL2","");
    sbmInit.InitCombo("S2_DATA_TYPE");
    var DefalueGrid =[] ; 
    var initGrids = function() { 

    }


    $(document).ready(function () { 
        initGrids();
    });
        wizutil.setVisible("user1",false);
        wizutil.setVisible("S3_CNT",false);
</script>
</html>