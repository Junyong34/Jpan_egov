<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>디자인 샘플</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" type="text/css" href="/PBCloud/wizware/css/zTreeStyle.css"/>
        <link rel="stylesheet" type="text/css" href="/PBCloud/wizware/css/jquery-ui.min.css"/>
        <link rel="stylesheet" type="text/css" href="/PBCloud/wizware/css/demo.css"/>
        <link rel="stylesheet" type="text/css" href="/PBCloud/wizware/css/UI.WizGrid.css"/>
        <link rel="stylesheet" type="text/css" href="/PBCloud/wizware/css/PBUIDefalut.css"/>
        <link rel="stylesheet" type="text/css" href="/PBCloud/css/scostStyle.css"/>
        <script type="text/javascript" src="/PBCloud/wizware/js/jquery-1.11.0.min.js"></script>
        <script type="text/javascript" src="/PBCloud/wizware/js/grid.locale-en.js"></script>
        <script type="text/javascript" src="/PBCloud/wizware/js/jquery-ui.js"></script>
        <script type="text/javascript" src="/PBCloud/wizware/js/jquery.ztree.all-3.5.js"></script>
        <script type="text/javascript" src="/PBCloud/wizware/js/HuskyEZCreator.js"></script>
        <script type="text/javascript" src="/PBCloud/wizware/js/paging.js"></script>
        <script type="text/javascript" src="/PBCloud/wizware/js/jquery.form.js"></script>
        <script type="text/javascript" src="/PBCloud/wizware/js/Wizware_WizGrid.js"></script>
        <script type="text/javascript" src="/PBCloud/wizware/js/WizGrid.js"></script>
        <script type="text/javascript" src="/PBCloud/wizware/js/wizware.js"></script>
        <script type="text/javascript" src="/PBCloud/js/Scost.js"></script>
        <script> 
        
        </script>
        
</head>
<body >
    <div id="pbview" >
<form id='frm' name='frm' >
    <div id="hiddenvariable" style="display:none">
        <input style="display:none" id="out_message" name="out_message"  value="" ></input>
        <input style="display:none" id="out_code" name="out_code"  value="" ></input>
    </div>
        <input type='hidden'  id="InWIzJsonParma" name="InWIzJsonParma"  value="" ></input>
        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:center; position: absolute; top:22px; left:24px;  width:584px; height:63px; " id="label1" name="label1"><label style=" line-height:65px;  font-family:굴림체;font-weight:bold;font-size:18pt;" >    각 컨트롤 Class 정보를 보고 확인 하면 됩니다.     </label>    </div>

        	<div  class="titleLabel" style=" position: absolute; top:95px; left:36px;  width:151px; height:31px; " id="label5" name="label5"><label style=" line-height:33px; " >    기준 환율     </label>    </div>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Crimson;border-color:Black;text-align:left; position: absolute; top:137px; left:57px;  width:286px; height:19px; " id="label6" name="label6"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    제목 타이틀 Label Class 명 : titleLabel     </label>    </div>

      <div  id="group1" name="group1"  class="search-box" style=" position: absolute; top:165px; left:31px;  width:543px; height:73px; " >  
                	<div  class="serachlabel" style="position:absolute;left:28px;top:17px; width:100px; height:19px; " id="label3" name="label3"><label style=" line-height:21px; " >        기준연도         </label>        </div>
      </div>
        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Crimson;border-color:Black;text-align:left; position: absolute; top:256px; left:31px;  width:238px; height:25px; " id="label2" name="label2"><label style=" line-height:27px;  font-family:굴림체;font-size:9.75pt;" >    Group Box Class 명 : search-box     </label>    </div>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Crimson;border-color:Black;text-align:left; position: absolute; top:257px; left:275px;  width:322px; height:23px; " id="label4" name="label4"><label style=" line-height:25px;  font-family:굴림체;font-size:9.75pt;" >    Group Box 안에 Label Class 명 :  serachlabel     </label>    </div>

</form>	
<form id='fileform' name='fileform' encType='multipart/form-data'>
	<div style='display:none'>
	<iframe id='FileDownload'  name='FileDownload' style='border-width:0px;' src='' frameborder='0' ></iframe>
	</div>
	
</form>
    </div>
</body>
<script type="text/javascript" >
    var ChkNumberColumns= [];
    var RadioProp= {};
    var CheckProp= {};
    var ComboObjProp= {};
    var RequiredProp= {};
    var CellEditoption= {};
    var InitItems= {};
    var sbmInit = new SubMission( frm, "/PBCloud","","","");
    sbmInit.InitCombo("");
    function initGrids() { 

    }


    $(document).ready(function () { 
        initGrids();
    });
</script>
</html>