<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>Total Management Report</title>
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
        
           
        	
        	if(  $("#S_COMPANY_CD").val() == "")
        	{
        		Return_RTN_MSG("00004","Company NAME","S_COMPANY_CD");
        		return;
        	}
        	
        	if(  $("#S_ORG_CD").val() == "")
        	{
        		Return_RTN_MSG("00004","Organization Name","S_ORG_CD");
        		return;
        	}
        	
        	if(  $("#S_DATA_TYPE").val() == "")
        	{
        		Return_RTN_MSG("00004","Data Type","S_DATA_TYPE");
        		return;
        	}
        	 if(  $("#S_YYYYMM").val() == "")
        	{
        		Return_RTN_MSG("00003","Month","S_YYYYMM");
        		return;
        	}
        	
        	if(!isValidDate( $("#S_YYYYMM").val())){
        	   Return_RTN_MSG("00006","" ,"S_YYYYMM");
                	return ;
        	}
        	
        	
        	if(  $("#S_DATA_TYPE").val() =="MP" ||  $("#S_DATA_TYPE").val() =="MTP" )
        	{
        	   var yyyy = $("#S_YYYYMM").val().substring(0,4);   
        	   var fromyyymm = yyyy +"03";
               var toyymm = ( parseInt(yyyy)+ 1 ) +"04";
              
        	   $("#S_FROM_YYYYMM").val(fromyyymm);
        	   $("#S_TO_YYYYMM").val(toyymm);
        	}
        
        	FormSubmit("frm" , "/hpms/20160901101144ManagementComprehensive_buildSheet.do" , "S[A]:S" ,"",false); 
        }
        
        
        
        function S_ORG_CD_OnClick(myObj)
        {
           if($("#S_COMPANY_CD").val() == ""){
                Return_RTN_MSG("00004","Company Name","S_COMPANY_CD");
                return;
           }
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
        
        
        function S_ORG_CD_OnChange(myObj)
        {
         if( $("#S_ORG_CD").val() != ""){
               FCST_TITLE_Combo.submit(false);
           } 
        }
        function S_DATA_TYPE_OnClick(myObj)
        {
        if($("#S_DATA_TYPE  option:eq(0)").text() !="--Choice--"){
            Return_RTN_MSG("00004","Organization Name","S_ORG_CD");
             return;
          } 
        }
        
        //날짜유효성검사
        function isValidDate(param) {
                try
                {
                    //param = param.replace(/-/g,'');
         
                    // 자리수가 맞지않을때
                    if( isNaN(param) || param.length!=6 ) {
                        return false;
                    }
                     
                    var year = Number(param.substring(0, 4));
                    var month = Number(param.substring(4, 6));
                    //var day = Number(param.substring(6, 8));
         
                    //var dd = day / 0;
         
                     
                    if( month<1 || month>12 ) {
                        return false;
                    }     
                
                    return true;
         
                } catch (err) {
                    return false;
                }                       
            }
        
        function S_COMPANY_CD_OnChange(myObj)
        {
          ORG_COMBO.submit(false);
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
        	<div  class="titleLabel" style=" position: absolute; top:22px; left:30px;  width:470px; height:34px; " id="label1" name="label1"><label style=" line-height:36px; " >    Total Management Report Output     </label>    </div>

        	<div   id="user1" style="border-width:1px; border:solid; background-color:Transparent; color:Black;border-color:Transparent;text-align:left; position: absolute; top:24px; left:549px;  width:100px; height:100px; " >
<!-- Error Code screen -->
<div id="divPopup_ErrorCode" name="divPopup_ErrorCode" style="display:none;background-color:white;">
	<iframe id="Error_cd" name="Error_cd" style="width:500px;height:190px;" scrolling="auto">
	</iframe>
</div></div>

        	<select  class="selectbox" style=" position: absolute; top:107px; left:187px;  width:146px; height:21px; " id="S_COMPANY_CD" name="S_COMPANY_CD" tabindex="0" onchange="S_COMPANY_CD_OnChange(this);" eltype="Combo" >
    	</select>

        	<div  class="infoLabel" style=" position: absolute; top:107px; left:58px;  width:124px; height:19px; " id="label2" name="label2"><label style=" line-height:21px; " >    Company Name     </label>    </div>

        	<div  class="infoLabel" style=" position: absolute; top:160px; left:58px;  width:124px; height:19px; " id="label3" name="label3"><label style=" line-height:21px; " >    Organization Name     </label>    </div>

        	<select  class="selectbox" style=" position: absolute; top:161px; left:184px;  width:146px; height:21px; " id="S_ORG_CD" name="S_ORG_CD" tabindex="0" onclick="S_ORG_CD_OnClick(this);" onchange="S_ORG_CD_OnChange(this);" eltype="Combo" >
    	</select>

        	<div  class="infoLabel" style=" position: absolute; top:203px; left:58px;  width:124px; height:19px; " id="label4" name="label4"><label style=" line-height:21px; " >    Data Type     </label>    </div>

        	<select  class="selectbox" style=" position: absolute; top:204px; left:184px;  width:146px; height:21px; " id="S_DATA_TYPE" name="S_DATA_TYPE" tabindex="0" onclick="S_DATA_TYPE_OnClick(this);" eltype="Combo" >
    	</select>

        	<input  class="TextBox-S" style=" position: absolute; top:246px; left:356px;  width:62px; height:17px; " id="S_FROM_YYYYMM" name="S_FROM_YYYYMM" type="hidden" readonly  maxlength="50" tabindex="0" >    </input>

        <div id=="calendar_S_YYYYMM" style=" position: absolute; top:247px; left:184px; ">
      	<input  class="TextBox-S" style=" width:119px; height:17px; " id="S_YYYYMM" name="S_YYYYMM" type="text" tabindex="0" >    </input>
        </div>
<script>
    $('#S_YYYYMM').datepicker({ 
      inline:true
      ,dateFormat:"yymm"
      ,constrainInput:true
      ,buttonImage:Contextpath+"/wizware/js/calendar_alt.png" 
      ,buttonImageOnly:true
      ,showOn:"button"
      ,changeMonth:true
      ,changeYear:true
      ,showButtonPanel:true
      ,currentText:"today"
      ,closeText:"OK"
      ,monthNames:['January','February','March','April','May','June','July','August','September','October','November','December']
      ,monthNamesShort:['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
      ,dayNames: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday']
      ,dayNamesShort:['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
      ,dayNamesMin: ['Su','Mo','Tu','We','Th','Fr','Sa']
    ,onClose: function(dateText, inst) { 
        var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
        var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
        $(this).datepicker('setDate', new Date(year, month, 1));
     },afterShow: function(input, inst) { 
        $("#ui-datepicker-div > table").hide(); } 
    });
</script>

        	<div  class="infoLabel" style=" position: absolute; top:247px; left:58px;  width:124px; height:19px; " id="label5" name="label5"><label style=" line-height:21px; " >    Year Month     </label>    </div>

        	<input  class="TextBox-S" style=" position: absolute; top:248px; left:429px;  width:49px; height:17px; " id="S_TO_YYYYMM" name="S_TO_YYYYMM" type="hidden" readonly  maxlength="50" tabindex="0" >    </input>

        	<input  class="btn2" style=" position: absolute; top:307px; left:195px;  width:131px; height:26px; " id="btn_download" name="btn_download" type="button" value="Download" tabindex="0"  onclick="btn_download_OnClick(this);" ></input>

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
    var ComboObjProp= {"S_COMPANY_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S_ORG_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S_DATA_TYPE":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}};
    var RequiredProp= {};
    var CellEditoption= {};
    var InitItems= {"S_COMPANY_CD":":", "S_ORG_CD":":", "S_DATA_TYPE":":"};
    var sbmInit = new SubMission( frm, "/hpms/20160901101144ManagementComprehensive_initCombo.do","","S_COMPANY_CD:SEL1","");
    var Combo2 = new SubMission( frm, "/hpms/20160704154561CommonCodeMgr_CommonCombo.do","","S_DATA_TYPE:SEL10","");
    var ORG_CODE = new SubMission( frm, "/hpms/20160704154561CommonCodeMgr_ORGCombobox.do","S[A]:S1","S_ORG_CD:SEL1","");
    var sbmCtlRead = new SubMission( frm, "/hpms/20161004133312Control_SearchUsingCheck.do","","","");
    var FCST_TITLE_Combo = new SubMission( frm, "/hpms/20160901101144ManagementComprehensive_FCST_Title_ComboMgr.do","S[A]:S","S_DATA_TYPE:SEL1","");
    var ORG_COMBO = new SubMission( frm, "/hpms/20160901101144ManagementComprehensive_initCombo.do","S[A]:S","S_ORG_CD:SEL3","");
    sbmInit.InitCombo("S_COMPANY_CD,S_ORG_CD,S_DATA_TYPE");
    var DefalueGrid =[] ; 
    var initGrids = function() { 

    }


    $(document).ready(function () { 
        sbmInit.submit(false, "initGrids");
    });
        wizutil.setVisible("user1",false);
</script>
</html>