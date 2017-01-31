<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>sub_PID Result</title>
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
        $(document).ready(function(){ 
             isloadingCheck =true;
              if("$(MESSAGE}" == "00009")Return_RTN_MSG("00009");
              $("#OWNER_COMPANY").val(wizutil.DecodeData( getParamdata('OWNER_COMPANY')));
              $("#OWNER_ORG").val( wizutil.DecodeData(getParamdata('OWNER_ORG')));
              $("#REG_COMPANY").val( wizutil.DecodeData(getParamdata('REG_COMPANY')));
              $("#REG_ORG").val(wizutil.DecodeData( getParamdata('REG_ORG')));
                 $("#S1_SUB_PID").val($("#S_SUB_PID").val());
                $("#button1").click();
               if(getParamdata('REG_COMPANY') != undefined){
                //  Return_RTN_MSG("00001");
                  }
        }); 
        
        
        function button3_OnClick(myObj)
        {
        	parent.dialogClose_subpid();
        }
        
        function button1_OnClick(myObj)
        {
        isloadingCheck = true;
        Result_search.submit(false);
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
        	<div  class="titleLabel" style=" position: absolute; top:22px; left:30px;  width:435px; height:34px; " id="label1" name="label1"><label style=" line-height:36px; " >    SubPID Nubering Result     </label>    </div>

      <div  id="group1" name="group1"  style=" background-color:White;border-width:1px;border-style:solid;border-color:DarkGray; position: absolute; top:80px; left:45px;  width:353px; height:281px; " >  
                	<div  class="infoLabel" style="position:absolute;left:22px;top:21px; width:131px; height:19px; " id="label3" name="label3"><label style=" line-height:21px; " >        SUB_PID         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:155px;top:21px; width:176px; height:17px; " id="S_SUB_PID" name="S_SUB_PID" type="text" readonly  value="${SEL2.SUB_PID}" maxlength="50" tabindex="0" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:22px;top:44px; width:131px; height:19px; " id="label2" name="label2"><label style=" line-height:21px; " >        PID         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:155px;top:44px; width:176px; height:17px; " id="S_PID" name="S_PID" type="text" readonly  value="${SEL2.PID}" maxlength="50" tabindex="0" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:22px;top:67px; width:131px; height:19px; " id="label9" name="label9"><label style=" line-height:21px; " >        R&D Theme         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:155px;top:67px; width:176px; height:17px; " id="S_RND_THEME" name="S_RND_THEME" type="text" readonly  value="${SEL2.RD_THEME}" maxlength="50" tabindex="0" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:155px;top:90px; width:176px; height:17px; " id="S_APPLICATION" name="S_APPLICATION" type="text" readonly  value="${SEL2.APPLICATION}" maxlength="50" tabindex="0" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:22px;top:90px; width:131px; height:19px; " id="label6" name="label6"><label style=" line-height:21px; " >        Purpose         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:155px;top:137px; width:176px; height:17px; " id="OWNER_COMPANY" name="OWNER_COMPANY" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:21px;top:141px; width:131px; height:34px; " id="label4" name="label4"><label style=" line-height:36px; " >        Owner Department         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:155px;top:160px; width:176px; height:17px; " id="OWNER_ORG" name="OWNER_ORG" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:155px;top:188px; width:176px; height:17px; " id="REG_COMPANY" name="REG_COMPANY" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:22px;top:192px; width:131px; height:34px; " id="label5" name="label5"><label style=" line-height:36px; " >        Registrar Department         </label>        </div>
                	<input  style="border-width:0px;background-color:Transparent; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt;position:absolute;left:10px;top:210px; width:9px; height:12px; " id="button1" name="button1" type="button" tabindex="0"  onclick="button1_OnClick(this);" ></input>
                	<input  class="TextBox-S" style="position:absolute;left:155px;top:211px; width:176px; height:17px; " id="REG_ORG" name="REG_ORG" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:40px;top:242px; width:97px; height:17px; " id="S1_SUB_PID" name="S1_SUB_PID" type="hidden" maxlength="50" tabindex="0" >        </input>
                	<input  class="btn3" style="position:absolute;left:266px;top:243px; width:63px; height:22px; " id="button3" name="button3" type="button" value="Close" tabindex="0"  onclick="button3_OnClick(this);" ></input>
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
    var subPID_Ret = new SubMission( frm, "/hpms/20160912111171SubPidInfoMgr_Sub_PIDSearch.do","S[A]:S","S:SEL2","");
    var Result_search = new SubMission( frm, "/hpms/20160912111171SubPidInfoMgr_Result_loadPage.do","S1[A]:S1","S:SEL1","");
    sbmInit.InitCombo("");
    var DefalueGrid =[] ; 
    var initGrids = function() { 

    }


    $(document).ready(function () { 
        initGrids();
    });
</script>
</html>