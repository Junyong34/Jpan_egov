<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>프로젝트부담율확인</title>
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
        function Onselect_calendar1(date,el)
        {
        alert( date  + " @@ " + el);
        
         $("#calendar1").val("");
        }
        
        function btn_download_OnClick(myObj)
        {
        ORG_combo_S1.submit(true);
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
        	<div  class="titleLabel" style=" position: absolute; top:22px; left:30px;  width:435px; height:34px; " id="label1" name="label1"><label style=" line-height:36px; " >    Project Allocation Rate     </label>    </div>

        	<select  class="selectbox" style=" position: absolute; top:107px; left:185px;  width:146px; height:21px; " id="S1_COMPANY_CD" name="S1_COMPANY_CD" tabindex="0" onchange="S1_COMPANY_CD_OnChange(this);" eltype="Combo" >
    	</select>

        	<div  class="infoLabel" style=" position: absolute; top:107px; left:58px;  width:124px; height:19px; " id="label2" name="label2"><label style=" line-height:21px; " >    Company Name     </label>    </div>

        	<select  class="selectbox" style=" position: absolute; top:160px; left:185px;  width:146px; height:21px; " id="S1_ORG_CD" name="S1_ORG_CD" tabindex="0" onclick="S1_ORG_CD_OnClick(this);" eltype="Combo" >
    	</select>

        	<div  class="infoLabel" style=" position: absolute; top:160px; left:58px;  width:124px; height:19px; " id="label3" name="label3"><label style=" line-height:21px; " >    Organization Name     </label>    </div>

        <div id=="calendar_calendar1" style=" position: absolute; top:160px; left:374px; ">
      	<input  style="border-width:1px;background-color:White; color:Black;border-color:Black;text-align:left;  font-family:굴림체;font-size:9.75pt; width:75px; height:17px; " id="calendar1" name="calendar1" type="text" tabindex="0" >    </input>
        </div>
<script>
    $('#calendar1').datepicker({ 
      inline:true
      ,dateFormat:"yymmdd"
      ,constrainInput:true
      ,showOn:"button"
      ,changeMonth:true
      ,changeYear:true
      ,showButtonPanel:true
      ,currentText:"today"
      ,closeText:"close"
    });
</script>

        	<input  class="btn2" style=" position: absolute; top:214px; left:187px;  width:131px; height:26px; " id="btn_download" name="btn_download" type="button" value="Download" tabindex="0"  onclick="btn_download_OnClick(this);" ></input>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:266px; left:478px;  width:100px; height:19px; " id="S_E" name="S_E"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    </label>    </div>

        	<input  class="TextBox-S" style=" position: absolute; top:341px; left:305px;  width:97px; height:17px; " id="S3_PID" name="S3_PID" type="text" value="P00125" maxlength="50" tabindex="0" >    </input>

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
    var ComboObjProp= {"S1_COMPANY_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S1_ORG_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}};
    var RequiredProp= {};
    var CellEditoption= {};
    var InitItems= {"S1_COMPANY_CD":":", "S1_ORG_CD":":"};
    var sbmInit = new SubMission( frm, "/hpms","","","");
    var ORG_combo_S1 = new SubMission( frm, "/hpms/2016082417884DcpInfoMgr_ApprovalDataSearch.do","S3[A]:S3","S:SEL10","");
    sbmInit.InitCombo("S1_COMPANY_CD,S1_ORG_CD");
    var DefalueGrid =[] ; 
    var initGrids = function() { 

    }


    $(document).ready(function () { 
        initGrids();
    });
</script>
</html>