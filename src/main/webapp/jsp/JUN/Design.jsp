<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>dd</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" type="text/css" href="/PBCloud/wizware/css/zTreeStyle.css"/>
        <link rel="stylesheet" type="text/css" href="/PBCloud/wizware/css/jquery-ui.min.css"/>
        <link rel="stylesheet" type="text/css" href="/PBCloud/wizware/css/demo.css"/>
        <link rel="stylesheet" type="text/css" href="/PBCloud/wizware/css/UI.WizGrid.css"/>
        <link rel="stylesheet" type="text/css" href="/PBCloud/wizware/css/PBUIDefalut.css"/>
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
      <div  id="group1" name="group1"  class="search-box" style=" position: absolute; top:166px; left:238px;  width:507px; height:149px; " >  
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
    var ChkNumberColumns= [];
    var RadioProp= {};
    var CheckProp= {};
    var ComboObjProp= {};
    var RequiredProp= {};
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