<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>PID 등록 팝업</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/hpms/wizware/css/zTreeStyle.css"/>
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/hpms/wizware/css/jquery-ui.min.css"/>
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/hpms/wizware/css/demo.css"/>
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/hpms/wizware/css/PBUIDefalut.css"/>
        <link rel="stylesheet" id="wizcss"  type="text/css" href="/hpms/wizware/css/UI.WizGrid.css"/>
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
        <script> 
        function button3_OnClick(myObj)
        {
        
        /*****
         등록값이 널일 경우 체크 로직 추가해야함.
        *****/
        
          PIDAdd.submit(false);
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
        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:16px; left:35px;  width:107px; height:34px; " id="label1" name="label1"><label style=" line-height:36px;  font-family:굴림체;font-weight:bold;font-size:15.75pt;" >    PID 등록     </label>    </div>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:64px; left:304px;  width:63px; height:22px; " id="button3" name="button3" type="button" value="등록" tabindex="0"  onclick="button3_OnClick(this);" ></input>

      <table  id="layout1" name="layout1" cellspacing='0px'  style=" background-color:White;border-width:0px;border-style:solid;border-color:Black; position: absolute; top:108px; left:31px;  width:340px; height:180px;  table-layout:fixed;" >  
    	<tr style="height:22px;">
    		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<div  style="border:solid; border-width:0px;background-color:Cornsilk; color:Black;border-color:Black;text-align:left; height:20px; " id="label2" name="label2"><label style=" line-height:16px;  font-family:굴림체;font-size:9.75pt;" >            PID             </label>            </div>
    		</td>
    		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<input  class="TextBox-S" style=" width:177px; height:16px; " id="S_PID" name="S_PID" type="text" readonly  value="${SEL4.PID}" maxlength="50" tabindex="0" >            </input>
    		</td>
    	</tr>
    	<tr style="height:22px;">
    		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<div  style="border:solid; border-width:0px;background-color:Cornsilk; color:Black;border-color:Black;text-align:left; height:20px; " id="label3" name="label3"><label style=" line-height:16px;  font-family:굴림체;font-size:9.75pt;" >            PID 상태             </label>            </div>
    		</td>
    		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<select  style="border-width:1px; background-color:White; color:Black;border-color:Black;text-align:left;  font-family:굴림체;font-size:9.75pt; width:181px; height:20px; " id="S_PID_STATUS_CD" name="S_PID_STATUS_CD" tabindex="0" eltype="Combo" >
            	</select>
    		</td>
    	</tr>
    	<tr style="height:22px;">
    		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<div  style="border:solid; border-width:0px;background-color:Cornsilk; color:Black;border-color:Black;text-align:left; height:20px; " id="label4" name="label4"><label style=" line-height:16px;  font-family:굴림체;font-size:9.75pt;" >            R_D Category             </label>            </div>
    		</td>
    		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<select  style="border-width:1px; background-color:White; color:Black;border-color:Black;text-align:left;  font-family:굴림체;font-size:9.75pt; width:181px; height:20px; " id="S_RND_CATEGROY_CD" name="S_RND_CATEGROY_CD" tabindex="0" eltype="Combo" >
            	</select>
    		</td>
    	</tr>
    	<tr style="height:22px;">
    		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<div  style="border:solid; border-width:0px;background-color:Cornsilk; color:Black;border-color:Black;text-align:left; height:20px; " id="label5" name="label5"><label style=" line-height:16px;  font-family:굴림체;font-size:9.75pt;" >            R_D Theme             </label>            </div>
    		</td>
    		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<input  class="TextBox-S" style=" width:177px; height:16px; " id="S_RND_THEME" name="S_RND_THEME" type="text" maxlength="50" tabindex="0" >            </input>
    		</td>
    	</tr>
    	<tr style="height:22px;">
    		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<div  style="border:solid; border-width:0px;background-color:Cornsilk; color:Black;border-color:Black;text-align:left; height:20px; " id="label6" name="label6"><label style=" line-height:16px;  font-family:굴림체;font-size:9.75pt;" >            Product             </label>            </div>
    		</td>
    		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<input  class="TextBox-S" style=" width:177px; height:16px; " id="S_PRODUCT_CD" name="S_PRODUCT_CD" type="text" maxlength="50" tabindex="0" >            </input>
    		</td>
    	</tr>
    	<tr style="height:22px;">
    		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<div  style="border:solid; border-width:0px;background-color:Cornsilk; color:Black;border-color:Black;text-align:left; height:20px; " id="label7" name="label7"><label style=" line-height:16px;  font-family:굴림체;font-size:9.75pt;" >            Nickname(표시용)             </label>            </div>
    		</td>
    		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<input  class="TextBox-S" style=" width:177px; height:16px; " id="S_NICKNAME_1" name="S_NICKNAME_1" type="text" maxlength="50" tabindex="0" >            </input>
    		</td>
    	</tr>
    	<tr style="height:22px;">
    		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<div  style="border:solid; border-width:0px;background-color:Cornsilk; color:Black;border-color:Black;text-align:left; height:20px; " id="label8" name="label8"><label style=" line-height:16px;  font-family:굴림체;font-size:9.75pt;" >            Nickname(사외용)             </label>            </div>
    		</td>
    		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<input  class="TextBox-S" style=" width:177px; height:16px; " id="S_NICKNAME_2" name="S_NICKNAME_2" type="text" maxlength="50" tabindex="0" >            </input>
    		</td>
    	</tr>
    	<tr style="height:22px;">
    		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<div  style="border:solid; border-width:0px;background-color:Cornsilk; color:Black;border-color:Black;text-align:left; height:20px; " id="label9" name="label9"><label style=" line-height:16px;  font-family:굴림체;font-size:9.75pt;" >            오너부분             </label>            </div>
    		</td>
    		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<select  style="border-width:1px; background-color:White; color:Black;border-color:Black;text-align:left;  font-family:굴림체;font-size:9.75pt; width:181px; height:20px; " id="S_OWNER_DEPT_CD" name="S_OWNER_DEPT_CD" tabindex="0" eltype="Combo" >
            	</select>
    		</td>
    	</tr>
      </table>
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
    var ComboObjProp= {"S_PID_STATUS_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--선택--"}, "S_RND_CATEGROY_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--선택--"}, "S_OWNER_DEPT_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--선택--"}};
    var RequiredProp= {};
    var CellEditoption= {};
    var InitItems= {"S_PID_STATUS_CD":":", "S_RND_CATEGROY_CD":":", "S_OWNER_DEPT_CD":":"};
    var sbmInit = new SubMission( frm, "/hpms/2016082417883PidInfoMgr_MasterComboList.do","","S_PID_STATUS_CD:SEL2,S_RND_CATEGROY_CD:SEL4,S_OWNER_DEPT_CD:SEL6","");
    var PIDAdd = new SubMission( frm, "/hpms/2016082417883PidInfoMgr_PID_ADD.do","S[A]:S","","");
    PIDAdd.setConfirmMsg("등록하시겠습니까?");
    sbmInit.InitCombo("S_PID_STATUS_CD,S_RND_CATEGROY_CD,S_OWNER_DEPT_CD");
    function initGrids() { 

    }


    $(document).ready(function () { 
        sbmInit.submit(false, "initGrids");
    });
</script>
</html>