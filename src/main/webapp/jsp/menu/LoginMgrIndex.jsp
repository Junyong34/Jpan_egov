<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>Login Screen</title>
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
           isclick = true;
           $("#PassWD").attr("placeholder", "Password");
           jQuery("#PassWD").bind("keypress", function(e) {
               if (e.keyCode == "13") {
        
                   button1_OnClick(this);
               }
           });
           
          
           //쿠키 삭제
           deleteCookies();
        
        });
        
        
        
        
        
        function deleteCookies() {
           var count = 0; 
            var expireDate = new Date();
        /*         var today = new Date();
          today.setDate( today.getDate() + parseInt( expireDate ) );
          document.cookie = "park" + "=" + escape( "park" ) + "; path=/; expires=" + today.toGMTString() + ";";*/
          
           if (document.cookie != "") {
               var cookies = document.cookie.split("; ");
               count = cookies.length;
        
              
              
               expireDate.setDate(expireDate.getDate() ); 
         //  alert(expireDate.toGMTString());
               for (var i = 0; i < count; i++) {
                   var cookieName = cookies[i].split("=")[0];
                  // alert(cookieName);
                   document.cookie = cookieName + "=; path=/; expires=" + expireDate.toGMTString()
               }
           }
        
           return count;
        }
        
             
             
        function button1_OnClick(myObj) {
           isloadingCheck = true;
           
           $("#button1").blur();
           if ($("#UserID").val() == "") {
               alert("Enter the UserID ");
               $("#UserID").focus();
               return;
           }
           if ($("#PassWD").val() == "") {
               alert("Enter the Password ");
               $("#PassWD").focus();
               return;
           }
           
           
           if (isclick) {
               isclick = false;
               User_check.submit(false, "logingubun");
           }
        
        
        
        }
        
           function logingubun() {
                
                   if (wizutil.getResultMsg() == 1) {
                       LoginCheck.submit(false, "callBack");
                   }else {
                       var Return_URL ="http://tt-hpms-d10.bit.socionext.com/hpms/2016041114474CookieLoginInfo_CookieMgr.do"; // IPInfo(ex www.xxxx.com)/hpms/2016041114474CookieLoginInfo_CookieMgr.do
                       $("#MenuFile").val(Return_URL);
                       
                       FormSubmit("frm", "https://ttoscar.css.socionext.com/cgi-bin/edonavi/check_id.cgi", "S[A]:S", "", false);
                       
                       isclick = true;
                   }
                
                
                }
                
        
        function callBack() {
          
           if (wizutil.getResultMsg() == "Please check UserID Password") {
        
               alert(wizutil.getResultMsg());
               isclick = true;
               return;
           }
           FormSubmit("frm", "/hpms/2016041114474LoginMgrCheck_SessionLogInfo.do", "S[A]:S", "", false);
        
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
        	<div  class="titleLabel" style=" position: absolute; top:22px; left:30px;  width:435px; height:34px; " id="label2" name="label2"><label style=" line-height:36px; " >    Login     </label>    </div>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:DarkGray;border-color:Transparent;text-align:left; position: absolute; top:88px; left:102px;  width:159px; height:19px; " id="label1" name="label1"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    Remember ID     </label>    </div>

      <div  id="group1" name="group1"  style=" background-color:White;border-width:0px;border-style:solid;border-color:DarkGray; position: absolute; top:89px; left:63px;  width:359px; height:152px; " >  
                	<input  class="btn4" style="position:absolute;left:283px;top:53px; width:68px; height:50px; " id="button1" name="button1" type="button" value="Login" tabindex="3"  onclick="button1_OnClick(this);" ></input>
                	<div   id="user1" style="border-width:1px; border:solid; background-color:Transparent; color:Black;border-color:Transparent;text-align:left;position:absolute;left:17px;top:54px; width:247px; height:43px; " >
<input class="TextBox-S" id="UserID" name="UserID" style="width:250px;margin-bottom:5px;" type="text" placeholder="ID" maxlength="60" tabindex="0">
</br>
<input type="password" class="TextBox-S" style="width:250px;"  id="PassWD" name="PassWD" maxlength="200" tabindex="0" placeholder="Password">
<input type="hidden" name="MenuFile" value="http://tt-hpms-d10.bit.socionext.com/hpms/2016041114474CookieLoginInfo_CookieMgr.do" />
<input type="hidden" name="lang" value="jp" /></div>
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
    var GridRowHeight= {};
    var ChkNumberColumns= [];
    var RadioProp= {};
    var CheckProp= {};
    var ComboObjProp= {};
    var RequiredProp= {};
    var CellEditoption= {};
    var InitItems= {};
    var sbmInit = new SubMission( frm, "/hpms","","","");
    var LoginCheck = new SubMission( frm, "/hpms/2016041114474LoginMgrCheck_wizLoginSession.do","S[A]:S","","");
    var User_check = new SubMission( frm, "/hpms/2016041114474LoginMgrCheck_System_Developer_Check.do","S[A]:S","","");
    sbmInit.InitCombo("");
    var DefalueGrid =[] ; 
    var initGrids = function() { 

    }


    $(document).ready(function () { 
        initGrids();
    });
        wizutil.setVisible("label1",false);
</script>
</html>