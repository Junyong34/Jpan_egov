<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>01.로그인</title>
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
        <script type="text/javascript" src="/PBCloud/wizware/js/MaskEdit.js"></script>
        <script> 
        jQuery(function($){
        	
        	//$.mask.definitions['B']="[c]";
        	   $("#date").mask("9999/99/99",{placeholder:"yyyy/mm/dd"});
        	   $("#phone").mask("(999) 999-9999");
        	   $("#tin").mask("999999999.00원");
        	   $("#ssn").mask("999-99-9999");
        	   $("#product").mask("99/99/9999",{completed:function(){alert("You typed the following: "+this.val());}});
      
        	   $.mask.definitions['~']='[$]';
        	   $("#eyescript").mask("~9.99 ~9.99 999");
        	});
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
         <input type='text'  id="date" name="date"  value="" ></input>
          <input type='text'  id="tin" name="tin"  value="" ></input>
          <input type='text'  id="product" name="product"  value="" ></input>
        	<input  class="TextBox-S" style=" font-family:굴림체;font-size:9.75pt; position: absolute; top:47px; left:48px;  width:233px; height:17px; " id="S_USER_ID" name="S_USER_ID" type="text" placeholder="아이디를 입력하세요." maxlength="50" tabindex="0"  onchange="wizutil.CheckNumberValue(this);wizutil.CheckNumberValue(this);">    </input>

        	<input  class="TextBox-S" style=" font-family:굴림체;font-size:9.75pt; position: absolute; top:86px; left:47px;  width:235px; height:17px; " id="S_USER_PW" name="S_USER_PW" type="text" placeholder="비밀번호를 입력하세요" maxlength="50" tabindex="0"  onchange="wizutil.CheckNumberValue(this);wizutil.CheckNumberValue(this);">    </input>

        	<div  style=" font-family:굴림체;font-size:9.75pt;border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:124px; left:75px;  width:100px; height:19px; " id="label3" name="label3"><label style=" line-height:21px;" >    Remember ID     </label>    </div>

        	<input  style=" font-family:굴림체;font-size:9.75pt;border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center; position: absolute; top:124px; left:184px;  width:101px; height:21px; " id="button1" name="button1" type="button" value="Login" tabindex="0"  ></input>

        	<input type="checkbox"  style=" width:13px; height:13px; border-width:0px;background-color:White; color:Black;border-color:#000000;text-align:left; position: absolute; top:122px; left:50px; " id="check1" name="check1" value="" tabindex="0" >
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
    var CheckProp= {"":"check1"};
    var ComboObjProp= {};
    var RequiredProp= {};
    var InitItems= {"check1": {TrueValue:"", FalseValue:"",Column:"_" }};
    var sbmInit = new SubMission( frm, "/PBCloud","","","");
    sbmInit.InitCombo("");
    function initGrids() { 

    }


    $(document).ready(function () { 
        initGrids();
    });
</script>
</html>