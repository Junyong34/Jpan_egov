<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>PID Numbering Result Screen</title>
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
                $("#mypage").attr("src","#");
           
               if("$(MESSAGE}" == "00009")Return_RTN_MSG("00009");
               isloadingCheck = true;
              $("#OWNER_COMPANY").val(wizutil.DecodeData( getParamdata('OWNER_COMPANY')));
              $("#OWNER_ORG").val( wizutil.DecodeData(getParamdata('OWNER_ORG')));
              $("#REG_COMPANY").val( wizutil.DecodeData(getParamdata('REG_COMPANY')));
              $("#REG_ORG").val(wizutil.DecodeData( getParamdata('REG_ORG')));
                $("#S1_PID").val($("#S_PID").val());
                $("#button2").click();
              });
        
        
        
        function button1_OnClick(myObj)
        {
        
        	parent.dialogClose_MYPAGE();			
        			
        	//	parent.$("#divPopup").dialog("close");
        }
        
        function button2_OnClick(myObj)
        {
        PidSearch.submit(false);
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
        	<div  class="titleLabel" style=" position: absolute; top:22px; left:30px;  width:435px; height:34px; " id="label1" name="label1"><label style=" line-height:36px; " >    PID Numbering Result Screen     </label>    </div>

      <div  id="group1" name="group1"  style=" background-color:White;border-width:1px;border-style:solid;border-color:DarkGray; position: absolute; top:72px; left:33px;  width:430px; height:368px; " >  
          <table  id="layout1" name="layout1" cellspacing='0px'  style=" background-color:White;border-width:0px;border-style:solid;border-color:Black; position: absolute; top:16px; left:36px;  width:340px; height:180px;  table-layout:fixed;" >  
        	<tr style="height:22px;">
        		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<div  class="infoLabel" style=" height:20px; " id="label2" name="label2"><label style=" line-height:16px; " >                PID                 </label>                </div>
        		</td>
        		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<input  class="TextBox-S" style=" width:177px; height:16px; " id="S_PID" name="S_PID" type="text" readonly  value="${SEL4.PID}" maxlength="50" tabindex="0" >                </input>
        		</td>
        	</tr>
        	<tr style="height:22px;">
        		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<div  class="infoLabel" style=" height:20px; " id="label3" name="label3"><label style=" line-height:16px; " >                PID Status                 </label>                </div>
        		</td>
        		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<input  class="TextBox-S" style=" width:177px; height:16px; " id="S_PID_STATUS_NM" name="S_PID_STATUS_NM" type="text" readonly  value="${SEL4.PID_STATUS_NM}" maxlength="50" tabindex="0" >                </input>
        		</td>
        	</tr>
        	<tr style="height:22px;">
        		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<div  class="infoLabel" style=" height:20px; " id="label4" name="label4"><label style=" line-height:16px; " >                R&D Category                 </label>                </div>
        		</td>
        		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<input  class="TextBox-S" style=" width:177px; height:16px; " id="S_RD_CATEGORY_NM" name="S_RD_CATEGORY_NM" type="text" readonly  maxlength="50" tabindex="0" >                </input>
        		</td>
        	</tr>
        	<tr style="height:22px;">
        		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<div  class="infoLabel" style=" height:20px; " id="label5" name="label5"><label style=" line-height:16px; " >                R&D Theme                 </label>                </div>
        		</td>
        		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<input  class="TextBox-S" style=" width:177px; height:16px; " id="S_RD_THEME" name="S_RD_THEME" type="text" readonly  value="${SEL4.RD_THEME}" maxlength="50" tabindex="0" >                </input>
        		</td>
        	</tr>
        	<tr style="height:22px;">
        		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<div  class="infoLabel" style=" height:20px; " id="label6" name="label6"><label style=" line-height:16px; " >                Product                 </label>                </div>
        		</td>
        		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<input  class="TextBox-S" style=" width:177px; height:16px; " id="S_ITEM_NAME" name="S_ITEM_NAME" type="text" readonly  value="${SEL4.ITEM_NAME}" maxlength="50" tabindex="0" >                </input>
        		</td>
        	</tr>
        	<tr style="height:22px;">
        		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<div  class="infoLabel" style=" height:20px; " id="label7" name="label7"><label style=" line-height:16px; " >                Nickname (For Display)                 </label>                </div>
        		</td>
        		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<input  class="TextBox-S" style=" width:177px; height:16px; " id="S_NICKNAME" name="S_NICKNAME" type="text" readonly  value="${SEL4.NICKNAME}" maxlength="50" tabindex="0" >                </input>
        		</td>
        	</tr>
        	<tr style="height:22px;">
        		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<div  class="infoLabel" style=" height:20px; " id="label8" name="label8"><label style=" line-height:16px; " >                Nickname (For Outside)                 </label>                </div>
        		</td>
        		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<input  class="TextBox-S" style=" width:177px; height:16px; " id="S_NICKNAME_EXCL" name="S_NICKNAME_EXCL" type="text" readonly  value="${SEL4.NICKNAME_EXCL}" maxlength="50" tabindex="0" >                </input>
        		</td>
        	</tr>
        	<tr style="height:22px;">
        		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
        		</td>
        		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
        		</td>
        	</tr>
          </table>                	<div  class="infoLabel" style="position:absolute;left:37px;top:200px; width:156px; height:43px; " id="label9" name="label9"><label style=" line-height:45px; " >        Owner Department         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:195px;top:200px; width:177px; height:17px; " id="OWNER_COMPANY" name="OWNER_COMPANY" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:195px;top:223px; width:177px; height:17px; " id="OWNER_ORG" name="OWNER_ORG" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:37px;top:252px; width:156px; height:43px; " id="label10" name="label10"><label style=" line-height:45px; " >        Registrar Department         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:195px;top:253px; width:177px; height:17px; " id="REG_COMPANY" name="REG_COMPANY" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:195px;top:276px; width:177px; height:17px; " id="REG_ORG" name="REG_ORG" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<input  style="border-width:0px;background-color:Transparent; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt;position:absolute;left:19px;top:283px; width:13px; height:15px; " id="button2" name="button2" type="button" tabindex="0"  onclick="button2_OnClick(this);" ></input>
                	<input  class="btn3" style="position:absolute;left:321px;top:315px; width:50px; height:29px; " id="button1" name="button1" type="button" value="Close" tabindex="0"  onclick="button1_OnClick(this);" ></input>
                	<input  class="TextBox-S" style="position:absolute;left:201px;top:327px; width:97px; height:17px; " id="S1_PID" name="S1_PID" type="hidden" maxlength="50" tabindex="0" >        </input>
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
    var PidSearch = new SubMission( frm, "/hpms/2016082417883PidInfoMgr_Result_loadPage.do","S1[A]:S1","S:SEL5","");
    sbmInit.InitCombo("");
    var DefalueGrid =[] ; 
    var initGrids = function() { 

    }


    $(document).ready(function () { 
        initGrids();
    });
</script>
</html>