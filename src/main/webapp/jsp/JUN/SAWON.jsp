<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>그리드 함수 SAMPLE</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/PBCloud/wizware/css/zTreeStyle.css"/>
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/PBCloud/wizware/css/jquery-ui.min.css"/>
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/PBCloud/wizware/css/demo.css"/>
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/PBCloud/wizware/css/PBUIDefalut.css"/>
        <link rel="stylesheet" id="scostStyle"  type="text/css" href="/PBCloud/css/scostStyle.css"/>
        <link rel="stylesheet" id="wizcss"  type="text/css" href="/PBCloud/wizware/css/UI.WizGrid.css"/>
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
        function grid1_OnLoadComplete()
        {
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
        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:48px; left:308px;  width:101px; height:21px; " id="button7" name="button7" type="button" value="추가" tabindex="0"  onclick="button7_OnClick(this);" ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:50px; left:52px;  width:101px; height:21px; " id="button23" name="button23" type="button" value="Row선택" tabindex="0"  onclick="button23_OnClick(this);" ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:55px; left:656px;  width:101px; height:21px; " id="button4" name="button4" type="button" value="ColumnShow" tabindex="0"  onclick="button4_OnClick(this);" ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:55px; left:544px;  width:101px; height:21px; " id="button3" name="button3" type="button" value="ColumnHide" tabindex="0"  onclick="button3_OnClick(this);" ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:55px; left:869px;  width:101px; height:21px; " id="button6" name="button6" type="button" value="DeleteRow" tabindex="0"  onclick="button6_OnClick(this);" ></input>

        	<input  class="btn" style=" position: absolute; top:55px; left:166px;  width:101px; height:21px; " id="button1" name="button1" type="button" value="조회" tabindex="0"  onclick="button1_OnClick(this);" ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:56px; left:762px;  width:101px; height:21px; " id="button5" name="button5" type="button" value="insertRow" tabindex="0"  onclick="button5_OnClick(this);" ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:92px; left:646px;  width:136px; height:21px; " id="button9" name="button9" type="button" value="선택된 Row Index값" tabindex="0"  onclick="button9_OnClick(this);" ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:92px; left:531px;  width:101px; height:21px; " id="button8" name="button8" type="button" value="CellStyle" tabindex="0"  onclick="button8_OnClick(this);" ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:92px; left:792px;  width:136px; height:21px; " id="button10" name="button10" type="button" value="Column Object 값" tabindex="0"  onclick="button10_OnClick(this);" ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:131px; left:418px;  width:240px; height:21px; " id="button11" name="button11" type="button" value="Column Index로 이름가져오기" tabindex="0"  onclick="button11_OnClick(this);" ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:131px; left:667px;  width:274px; height:21px; " id="button12" name="button12" type="button" value="Column 이름으로 ColIndex값 가져오기" tabindex="0"  onclick="button12_OnClick(this);" ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:164px; left:648px;  width:132px; height:20px; " id="button14" name="button14" type="button" value="Cell 값 가져오기" tabindex="0"  onclick="button14_OnClick(this);" ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:164px; left:431px;  width:199px; height:21px; " id="button13" name="button13" type="button" value="상태 Flag 값 가져오기" tabindex="0"  onclick="button13_OnClick(this);" ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:164px; left:796px;  width:149px; height:21px; " id="btn" name="btn" type="button" value="cell 값 변경 하기" tabindex="0"  onclick="btn_OnClick(this);" ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:199px; left:632px;  width:225px; height:21px; " id="button16" name="button16" type="button" value="Flag가 U인 Row 정보 가져오기" tabindex="0"  onclick="button16_OnClick(this);" ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:199px; left:869px;  width:101px; height:21px; " id="button20" name="button20" type="button" value="Grid Clear" tabindex="0"  onclick="button20_OnClick(this);" ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:199px; left:427px;  width:190px; height:21px; " id="button15" name="button15" type="button" value="선택된 Row Cell 값 가져오기" tabindex="0"  onclick="button15_OnClick(this);" ></input>

        	<input  class="btn-search-s" style=" position: absolute; top:204px; left:184px;  width:64px; height:26px; " id="button22" name="button22" type="button" tabindex="0"  ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:237px; left:692px;  width:161px; height:21px; " id="button18" name="button18" type="button" value="Grid Column 총 수" tabindex="0"  onclick="button18_OnClick(this);" ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:237px; left:862px;  width:162px; height:21px; " id="button19" name="button19" type="button" value="Grid Row 총 수" tabindex="0"  onclick="button19_OnClick(this);" ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:237px; left:428px;  width:248px; height:21px; " id="button17" name="button17" type="button" value="Grid Edit 상태 Close 처리" tabindex="0"  onclick="button17_OnClick(this);" ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:283px; left:357px;  width:110px; height:21px; " id="button" name="button" type="button" value="파일다운로드" tabindex="0"  onclick="button_OnClick(this);" ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:283px; left:558px;  width:194px; height:21px; " id="button21" name="button21" type="button" value="그리드 스타일 변경" tabindex="0"  onclick="button21_OnClick(this);" ></input>

        	<input  style="border-width:1px;background-color:DarkGray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:286px; left:791px;  width:101px; height:21px; " id="button2" name="button2" type="button" value="Colmerge" tabindex="0"  onclick="button2_OnClick(this);" ></input>

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
    var sbmInit = new SubMission( frm, "/PBCloud","","","");
    var saveList = new SubMission( frm, "/PBCloud/2016041114476AccountTitleMgr_saveAccountTitle.do","grid1[A]:S","grid1:SEL2","");
    saveList.setConfirmMsg("저장하시겠습니까?");
    var excelDown = new SubMission( frm, "/PBCloud/excelDown.excel","","","");
    var selectList = new SubMission( frm, "/PBCloud/2016041114476AccountTitleMgr_selectAccountTitle.do","","","");
    sbmInit.InitCombo("");
    function initGrids() { 

    }


    $(document).ready(function () { 
        initGrids();
    });
</script>
</html>