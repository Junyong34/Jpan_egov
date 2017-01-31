<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>여일 상세데이타 추출</title>
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
        function btn_download_OnClick(myObj)
        {
        	var symd="";
        	var fromym = $("#S_FROM_YYYYMM").val();
        	var toym = $("#S_TO_YYYYMM").val();
        	
        	if(  $("#S_FROM_YYYYMM").val() == "")
        	{
        		alert("Not Found Value: FROM_YYYYMM");
        		return;
        	}
        	if(  $("#S_TO_YYYYMM").val() == "")
        	{
        		alert("Not Found Value: TO_YYYYMM");
        		return;
        	}
        	if(parseInt(fromym) > parseInt(toym))
        	{
        		alert("Search Date Value Error: FROM_YYYYMM > TO_YYYYMM ");
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
            FormSubmit("frm" , "/hpms/hpms.do?SYSID=HPMS&MENUID=20160901091139&EVENTID=ExcelDownload" , "S[A]:S" ,"",false); 
        
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
        	<div  class="titleLabel" style=" position: absolute; top:22px; left:30px;  width:435px; height:34px; " id="label1" name="label1"><label style=" line-height:36px; " >    Budget/Result Detailed     </label>    </div>

        	<input  class="TextBox-S" style=" position: absolute; top:38px; left:495px;  width:192px; height:17px; " id="S_YYYYMM" name="S_YYYYMM" type="text" maxlength="50" tabindex="0" >    </input>

        	<div  class="infoText" style=" position: absolute; top:101px; left:548px;  width:155px; height:19px; " id="label6" name="label6"><label style=" line-height:21px; " >    Extracting Period     </label>    </div>

        	<div  class="infoLabel" style=" position: absolute; top:132px; left:630px;  width:100px; height:19px; " id="label8" name="label8"><label style=" line-height:21px; " >    To:YYYYMM     </label>    </div>

        	<div  class="infoLabel" style=" position: absolute; top:132px; left:510px;  width:100px; height:19px; " id="label7" name="label7"><label style=" line-height:21px; " >    From:YYYYMM     </label>    </div>

        	<div  class="infoLabel" style=" position: absolute; top:132px; left:367px;  width:100px; height:19px; " id="label5" name="label5"><label style=" line-height:21px; " >    Data Type     </label>    </div>

        	<div  class="infoLabel" style=" position: absolute; top:132px; left:219px;  width:100px; height:19px; " id="label4" name="label4"><label style=" line-height:21px; " >    Item Code     </label>    </div>

        	<div  class="infoLabel" style=" position: absolute; top:132px; left:74px;  width:100px; height:19px; " id="label3" name="label3"><label style=" line-height:21px; " >    PID     </label>    </div>

        	<input  class="TextBox-S" style=" position: absolute; top:156px; left:74px;  width:97px; height:17px; " id="S_PID" name="S_PID" type="text" maxlength="50" tabindex="0" >    </input>

        	<select  class="selectbox" style=" position: absolute; top:156px; left:219px;  width:102px; height:21px; " id="S_ITEM_CD" name="S_ITEM_CD" tabindex="0" eltype="Combo" >
    	</select>

        	<select  class="selectbox" style=" position: absolute; top:156px; left:367px;  width:102px; height:21px; " id="S_DATA_TYPE" name="S_DATA_TYPE" tabindex="0" eltype="Combo" >
    	</select>

        <div id=="calendar_S_FROM_YYYYMM" style=" position: absolute; top:156px; left:511px; ">
      	<input  class="TextBox-S" style=" width:75px; height:17px; " id="S_FROM_YYYYMM" name="S_FROM_YYYYMM" type="text" tabindex="0" >    </input>
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
    });
</script>

        <div id=="calendar_S_TO_YYYYMM" style=" position: absolute; top:157px; left:629px; ">
      	<input  class="TextBox-S" style=" width:75px; height:17px; " id="S_TO_YYYYMM" name="S_TO_YYYYMM" type="text" tabindex="0" >    </input>
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
    });
</script>

        	<div  class="infoLabel" style=" position: absolute; top:197px; left:73px;  width:100px; height:19px; " id="label9" name="label9"><label style=" line-height:21px; " >    Company Name     </label>    </div>

        	<div  class="infoLabel" style=" position: absolute; top:197px; left:191px;  width:129px; height:19px; " id="label10" name="label10"><label style=" line-height:21px; " >    Organization Name     </label>    </div>

        	<input  class="TextBox-S" style=" position: absolute; top:222px; left:191px;  width:97px; height:17px; " id="S_USE_SECTION_CD" name="S_USE_SECTION_CD" type="text" maxlength="50" tabindex="0" >    </input>

        	<select  class="selectbox" style=" position: absolute; top:222px; left:73px;  width:102px; height:21px; " id="S_USE_COMPANY_CD" name="S_USE_COMPANY_CD" tabindex="0" eltype="Combo" >
    	</select>

        	<input  class="btn2" style=" position: absolute; top:264px; left:75px;  width:100px; height:35px; " id="btn_download" name="btn_download" type="button" value="Download" tabindex="0"  onclick="btn_download_OnClick(this);" ></input>

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
    var ComboObjProp= {"S_ITEM_CD":{"CODE":"","NAME":"","DEFAULT":"Select"}, "S_DATA_TYPE":{"CODE":"","NAME":"","DEFAULT":"Select"}, "S_USE_COMPANY_CD":{"CODE":"","NAME":"","DEFAULT":"Select"}};
    var RequiredProp= {};
    var CellEditoption= {};
    var InitItems= {};
    var sbmInit = new SubMission( frm, "/hpms","","","");
    sbmInit.InitCombo("");
    var DefalueGrid =[] ; 
    var initGrids = function() { 

    }


    $(document).ready(function () { 
        initGrids();
    });
</script>
</html>