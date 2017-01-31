<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>Plan Input Management</title>
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
              
              // Compnay_cd.submit(false);   
             $(document).keydown(function(e){   
                   
                     if(e.target.nodeName != "INPUT" && e.target.nodeName != "TEXTAREA"){       
                    if(e.keyCode === 8){   
                    return false;
                     }
                    }
                  });
              });
        
        
        $(function(){          
        	$('#btn_upload').click(function(e){
        		$("#S1_FILE").val("");
        		e.preventDefault();   
        		         
        		$("#S1_FILE").click();
        		          
        	/*	var ext = $("input:file").val().split(".").pop().toLowerCase();
        		if(ext.length > 0){
        			if($.inArray(ext, ["gif","png","jpg","jpeg"]) == -1) { 
        				alert("gif,png,jpg 파일만 업로드 할수 있습니다.");
        				return false;  
        			}                  
        		}
        		$("input:file").val().toLowerCase();*/
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
        	//파일 서버로 전송 	
        	sbmCtlWrite.submit(false, "FIleUPload_S1_FILE" );
        	//FIleUPload_S1_FILE();
        }
        
        function uploadsubmit()
        {
        
          //alert("Excel File Server complete.");
          sbmExcelUpload.submit(false,"ErrorCheck");
        //  NoCheckingUpload.submit(false,"ErrorCheck");
         /* if($("#input1").val() == "A"){
           NoCheckingUpload.submit(false,"ErrorCheck");
          }else{
           sbmExcelUpload.submit(false,"ErrorCheck");
          }*/
         
        }
        
        
        function ErrorCheck(){
        //오류가 발생할 때 
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
                 
        		//	window.location.reload();
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
        function dialogClose_MYPAGE()
        {   
           //팝업종료및 
        	$("#divPopup_ErrorCode").dialog("close");
        }
        
        function button1_OnClick(myObj)
        {
           sbmCtlRead.submit(false, "download");
           //download();
        }
        
        function download()
        {
        
            if( $("#out_message").val() != "" )
            {
              ErrorCheck();
              return;
            }    
            	if(  $("#S_FROM_YYYYMM").val() == "")
        	{
        		  Return_RTN_MSG("00024");
        		return;
        	}
        	if(  $("#S_TO_YYYYMM").val() == "")
        	{
        		  Return_RTN_MSG("00025");
        		return;
        	}
            if(  wizutil.isNumber($("#S_FROM_YYYYMM").val()) == false)
            {
               Return_RTN_MSG("00023");
               return ;
            }
            
            if(  wizutil.isNumber($("#S_TO_YYYYMM").val()) == false)
            {
                Return_RTN_MSG("00023");
               return ;
            }
            
        	var symd="";
        	var fromym = $("#S_FROM_YYYYMM").val();
        	var toym = $("#S_TO_YYYYMM").val();
        	
        
        	if(!isValidDate(fromym)){
        	   Return_RTN_MSG("00006","" ,"S_FROM_YYYYMM");
                	return ;
        	}
        	
        	if(!isValidDate(toym)){
        	   Return_RTN_MSG("00006","" ,"S_TO_YYYYMM");
                	return ;
        	}
        	
        	if(parseInt(fromym) > parseInt(toym))
        	{
        		 Return_RTN_MSG("00026");
        		return;
        	}
        	
        	if(  $("#S_PID").val() == "" && $("#S_ORG_CD").val()== ""  )
        	{
        	   Return_RTN_MSG("00027");
        		return;
        	}
        	
        	if( $("#S_COMPANY_CD").val()!="" && $("#S_ORG_CD").val() == ""  )
        	{
        	    Return_RTN_MSG("00028");
        		return;
        	} 
        	
        	if(fromym == toym) 
        	{
        		symd= fromym;
        	}
        	else
        	{
        		symd= fromym;
        		while(true)
        		{
        			fromym = plusMonth(fromym);
        			symd = symd +"," + fromym;
        			if(parseInt(fromym) >= parseInt(toym))
        			{
        				break;
        			}
        		}
        	}
        	
        	$("#S_YYYYMM").val(symd);
        	 
            FormSubmit("frm" , "/hpms/20160901091138PlanMgr_planExcelDownload.do" , "S[A]:S" ,"",false); 
         
        }
        
        
        
        
        
        function plusMonth(fromym)
        {
            var datefmt ="";
        	var yyyy = parseInt(fromym.substring(0,4));
        	var mm = 0;
        	var mc = fromym.substring(4,5);
        	if(mc == '0')
        	{
        		 mm = parseInt(fromym.substring(5));
        	}
        	else
        	{
        		 mm = parseInt(fromym.substring(4));
        	}
        	
        	if(mm == 12)
        	{
        		datefmt = (yyyy+1) + '01';
        	}
        	else
        	{
        		mm++;
        		if(mm>9) datefmt = yyyy +"" + mm;
        		else   datefmt = yyyy +"0" + mm;
        	}
        	return datefmt;
        }
        
        function S_ORG_CD_OnClick(myObj)
        {
             if($("#S_COMPANY_CD").val() == ""){
                Return_RTN_MSG("00004","Company Name","S_COMPANY_CD");
                return;
           }
        }
        
        function S_COMPANY_CD_OnChange(myObj)
        {
           if( $("#S_COMPANY_CD").val() == "" )
           {
             $("#S_ORG_CD").val("");
             return ;
           }   
           
           
           ORG_combo_S.submit(false);
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
        	
           FormSubmit("frm" , "/hpms/20160901091138PlanMgr_errorConfirm.do" , "S2[A]:S2" ,"", false); 
        }
        
        
        function S_DATA_TYPE_OnClick(myObj)
        {
        if($("#S_DATA_TYPE  option:eq(0)").text() !="--Choice--"){
           Return_RTN_MSG("00003","PID","S_PID")
             return;
          } 
        }
        
        function S_PID_OnKeyUp(myObj)
        {
          /* if($("#S_PID").val() != ""  && $("#S_PID").val().length ==6 ){
           
             sbmInit.submit(false);
           }*/
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
        	<div  class="titleLabel" style=" position: absolute; top:22px; left:30px;  width:435px; height:34px; " id="label1" name="label1"><label style=" line-height:36px; " >    Plan Input     </label>    </div>

        	<div   id="user1" style=" position: absolute; top:35px; left:660px;  width:100px; height:55px; display:none;" >
<!-- Error Code screen -->

<div id="divPopup_ErrorCode" name="divPopup_ErrorCode" style="display:none;background-color:white;">
	<iframe id="Error_cd" name="Error_cd" style="width:500px;height:190px;" scrolling="auto">
	</iframe>
</div></div>

        	<input  class="TextBox-S" style=" position: absolute; top:35px; left:793px;  width:97px; height:17px; " id="S_YYYYMM" name="S_YYYYMM" type="hidden" maxlength="50" tabindex="0" >    </input>

        	<input  class="TextBox-S" style=" position: absolute; top:72px; left:792px;  width:99px; height:17px; " id="S2_LOG_SEQ" name="S2_LOG_SEQ" type="hidden" maxlength="50" tabindex="0" >    </input>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:83px; left:48px;  width:297px; height:30px; " id="label2" name="label2"><label style=" line-height:32px;  font-family:굴림체;font-weight:bold;font-size:12pt;" >    Plan Data Extraction     </label>    </div>

      <div  id="group1" name="group1"  style=" background-color:White;border-width:1px;border-style:solid;border-color:DarkGray; position: absolute; top:116px; left:47px;  width:852px; height:225px; " >  
                	<div  class="infoText" style="position:absolute;left:657px;top:17px; width:130px; height:19px; " id="label6" name="label6"><label style=" line-height:21px; " >        Extracting Period         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:309px;top:47px; width:138px; height:19px; " id="label4" name="label4"><label style=" line-height:21px; " >        HPMS ID Group         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:476px;top:47px; width:100px; height:19px; " id="label5" name="label5"><label style=" line-height:21px; " >        Data Type         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:605px;top:47px; width:100px; height:19px; " id="label7" name="label7"><label style=" line-height:21px; " >        From:YYYYMM         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:725px;top:47px; width:100px; height:19px; " id="label8" name="label8"><label style=" line-height:21px; " >        To:YYYYMM         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:164px;top:47px; width:100px; height:19px; " id="label3" name="label3"><label style=" line-height:21px; " >        PID         </label>        </div>
                	<select  class="selectbox" style="position:absolute;left:309px;top:71px; width:140px; height:21px; " id="S_HPMS_GRP_ID" name="S_HPMS_GRP_ID" tabindex="0" eltype="Combo" >
        	</select>
                	<select  class="selectbox" style="position:absolute;left:476px;top:71px; width:102px; height:21px; " id="S_DATA_TYPE" name="S_DATA_TYPE" tabindex="0" onclick="S_DATA_TYPE_OnClick(this);" eltype="Combo" >
        	</select>
                	<input  class="TextBox-S" style="position:absolute;left:164px;top:71px; width:97px; height:17px; " id="S_PID" name="S_PID" type="text" maxlength="6" tabindex="0"  onkeyup="S_PID_OnKeyUp(this);">        </input>
                <div id=="calendar_S_FROM_YYYYMM" style="position:absolute;left:605px;top:73px;">
          	<input  class="TextBox-S" style=" width:75px; height:17px; " id="S_FROM_YYYYMM" name="S_FROM_YYYYMM" type="text" tabindex="0" >        </input>
            </div>
<script>
        $('#S_FROM_YYYYMM').datepicker({ 
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
                <div id=="calendar_S_TO_YYYYMM" style="position:absolute;left:726px;top:73px;">
          	<input  class="TextBox-S" style=" width:75px; height:17px; " id="S_TO_YYYYMM" name="S_TO_YYYYMM" type="text" tabindex="0" >        </input>
            </div>
<script>
        $('#S_TO_YYYYMM').datepicker({ 
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
                	<div  class="infoLabel" style="position:absolute;left:46px;top:112px; width:100px; height:19px; " id="label9" name="label9"><label style=" line-height:21px; " >        Company Name         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:164px;top:112px; width:129px; height:19px; " id="label10" name="label10"><label style=" line-height:21px; " >        Organization Name         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:309px;top:113px; width:100px; height:19px; " id="label12" name="label12"><label style=" line-height:21px; " >        HPMS ID         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:309px;top:135px; width:97px; height:17px; " id="S_HPMS_ID" name="S_HPMS_ID" type="text" maxlength="6" tabindex="0" >        </input>
                	<select  class="selectbox" style="position:absolute;left:47px;top:136px; width:102px; height:21px; " id="S_COMPANY_CD" name="S_COMPANY_CD" tabindex="0" onchange="S_COMPANY_CD_OnChange(this);" eltype="Combo" >
        	</select>
                	<select  class="selectbox" style="position:absolute;left:164px;top:136px; width:102px; height:21px; " id="S_ORG_CD" name="S_ORG_CD" tabindex="0" onclick="S_ORG_CD_OnClick(this);" eltype="Combo" >
        	</select>
                	<input  class="btn2" style="position:absolute;left:45px;top:172px; width:100px; height:35px; " id="button1" name="button1" type="button" value="Download" tabindex="0"  onclick="button1_OnClick(this);" ></input>
      </div>
        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:365px; left:48px;  width:297px; height:30px; " id="label11" name="label11"><label style=" line-height:32px;  font-family:굴림체;font-weight:bold;font-size:12pt;" >    Plan Data Registration     </label>    </div>

      <div  id="group2" name="group2"  style=" background-color:White;border-width:1px;border-style:solid;border-color:DarkGray; position: absolute; top:399px; left:47px;  width:855px; height:72px; " >  
                	<input  class="btn2" style="position:absolute;left:171px;top:16px; width:132px; height:35px; " id="btn_error" name="btn_error" type="button" value="Error Confirm" tabindex="0"  onclick="btn_error_OnClick(this);" ></input>
                	<input  class="btn2" style="position:absolute;left:42px;top:16px; width:100px; height:35px; " id="btn_upload" name="btn_upload" type="button" value="Upload" tabindex="0"  ></input>
                	<input type='hidden'  id="S1_FILE_NAME" name="S1_FILE_NAME"/>
        	<input type='hidden'  id="S1_FILE_PATH" name="S1_FILE_PATH"/>
        	<input type='hidden'  id="S1_FILE_UNNAME" name="S1_FILE_UNNAME"/>
        	<input type='hidden'  id="S1_FILE_SIZE" name="S1_FILE_SIZE"/>
      </div>
</form>	
<form id='fileform' name='fileform' encType='multipart/form-data'>
        	<input type='file' style="border:solid; border-width:0px;background-color:White; color:Black;border-color:Black;text-align:left; position: absolute; top:427px; left:621px;  width:263px;" id="S1_FILE" name="S1_FILE" tabindex="0"  onchange="S1_FILE_OnChange(this);" ></input>
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
    var ComboObjProp= {"S_HPMS_GRP_ID":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S_DATA_TYPE":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S_COMPANY_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S_ORG_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}};
    var RequiredProp= {};
    var CellEditoption= {};
    var InitItems= {"S_HPMS_GRP_ID":":", "S_DATA_TYPE":":", "S_COMPANY_CD":":", "S_ORG_CD":":"};
    var sbmInit = new SubMission( frm, "/hpms/20160901091138PlanMgr_initCombo.do","S[A]:S","S_COMPANY_CD:MRG1,S_DATA_TYPE:SEL5,S_HPMS_GRP_ID:SEL6","");
    var sbmExcelUpload = new SubMission( frm, "/hpms/20160901091138PlanMgr_ExcelNoChecking.do","S1[A]:S1","S2:ER01","");
    var Compnay_cd = new SubMission( frm, "/hpms/20160704154561CommonCodeMgr_Company_Org_cd.do","","S_COMPANY_CD:SEL4","");
    var ORG_combo_S = new SubMission( frm, "/hpms/20160704154561CommonCodeMgr_ORGCombobox.do","S[A]:S1","S_ORG_CD:SEL1","");
    var sbmCtlWrite = new SubMission( frm, "/hpms/20161004133312Control_MonthlyUsingCheck.do","","","");
    var sbmCtlRead = new SubMission( frm, "/hpms/20161004133312Control_SearchUsingCheck.do","","","");
    var NoCheckingUpload = new SubMission( frm, "/hpms/20160901091138PlanMgr_ExcelNoChecking.do","S1[A]:S1","S2:ER01","");
    sbmInit.InitCombo("S_HPMS_GRP_ID,S_DATA_TYPE,S_COMPANY_CD,S_ORG_CD");
    var DefalueGrid =[] ; 
    var initGrids = function() { 

    }


    $(document).ready(function () { 
        sbmInit.submit(false, "initGrids");
    });
        wizutil.setVisible("user1",false);
        wizutil.setVisible("S1_FILE",false);
</script>
</html>