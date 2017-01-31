<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>DCP 관리</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/hpms/wizware/css/zTreeStyle.css"/>
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/hpms/wizware/css/jquery-ui.min.css"/>
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/hpms/wizware/css/demo.css"/>
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/hpms/wizware/css/PBUIDefalut.css"/>
        <link rel="stylesheet" id="wizcss"  type="text/css" href="/hpms/wizware/css/UI.WizGrid.css"/>
        <link rel="stylesheet" id="scostStyle.css"  type="text/css" href="/hpms/css/scostStyle.css"/>
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
        function button1_OnClick(myObj)
        {
        DCPsearch.setRequired(true);
        if($("#S_FORECAST_NO").val() =="Z"){
          alert( " 데이터 종별을 선택 해주세요 ");
          return;
        }
        DCPsearch.submit(false,"FILE_PID_FOR");
        
        }
        function PID_POP_DATE(PID){
        
              $("#S_PID").val(PID);
              Forecast_data_combo.submit(false);
        }
        
        function FileInfosubmit(){
           FIleInfoAdd.submit(true,"Complete");
        }
        function Complete(){
        alert(" 파일이 첨부 되었습니다 ");
        }
        function FILE_PID_FOR(){
        //파일업로드 키값 전송송
        $("#F_PID").val($("#S_PID").val());
        $("#F_FORECAST_NO").val($("#S_FORECAST_NO").val());
        
        }
        function FIleUPload_F_FILE()
        {
        // 10 관람시트파일일
        wizutil.FileAjaxSubmit(Contextpath+'/jsp/File/fileupload.jsp', fileform, 'F_FILE', '', ['ppt','xlsx','xls','pptx']);
        }
        function F_FILE_OnChange(myObj)
        {
        if($("#S_PID").val() =="" || $("#S_FORECAST_NO").val() == ""){
           alert(  "PID를 검색 하고 파일을 첨부해주세요.");
           $('#F_FILE').val("");
           return;
        
        }
        $("#F_FILE_GBN_CD").val(10);
        //파일 업로드 실행 
        FIleUPload_F_FILE();
        //파일 테이블 정보 입력력
        FileInfosubmit();
        }
        function FIleUPload_F_FILE1()
        {
        wizutil.FileAjaxSubmit(Contextpath+'/jsp/File/fileupload.jsp', fileform, 'F_FILE1', '',['ppt','xlsx','xls','pptx']);
        }
        function F_FILE1_OnChange(myObj)
        {
        if($("#S_PID").val() =="" || $("#S_FORECAST_NO").val() == ""){
           alert(  "PID를 검색 하고 파일을 첨부해주세요.");
           $('#F_FILE1').val("");
           return;
        
        }
        $("#F_FILE_GBN_CD").val(20);
        //파일 업로드 실행 
        FIleUPload_F_FILE1();
        //파일 테이블 정보 입력력
        FileInfosubmit();
        }
        function FIleUPload_F_FILE2()
        {
        wizutil.FileAjaxSubmit(Contextpath+'/jsp/File/fileupload.jsp', fileform, 'F_FILE2', '', ['ppt','xlsx','xls','pptx']);
        }
        function F_FILE2_OnChange(myObj)
        {
        if($("#S_PID").val() =="" || $("#S_FORECAST_NO").val() == ""){
           alert(  "PID를 검색 하고 파일을 첨부해주세요.");
           $('#F_FILE2').val("");
           return;
        
        }
        $("#F_FILE_GBN_CD").val(30);
        //파일 업로드 실행 
        FIleUPload_F_FILE2();
        //파일 테이블 정보 입력력
        FileInfosubmit();
        }
        function FIleUPload_F_FILE3()
        {
        wizutil.FileAjaxSubmit(Contextpath+'/jsp/File/fileupload.jsp', fileform, 'F_FILE3', '', ['ppt','xlsx','xls','pptx']);
        }
        function F_FILE3_OnChange(myObj)
        {
        
        if($("#S_PID").val() =="" || $("#S_FORECAST_NO").val() == ""){
           alert(  "PID를 검색 하고 파일을 첨부해주세요.");
           $('#F_FILE3').val("");
           return;
        
        }
        $("#F_FILE_GBN_CD").val(40);
        //파일 업로드 실행 
        FIleUPload_F_FILE3();
        //파일 테이블 정보 입력력
        FileInfosubmit();
        
        
        }
        function FIleUPload_F_FILE4()
        {
        wizutil.FileAjaxSubmit(Contextpath+'/jsp/File/fileupload.jsp', fileform, 'F_FILE4', '', ['ppt','xlsx','xls','pptx']);
        }
        function F_FILE4_OnChange(myObj)
        {
        if($("#S_PID").val() =="" || $("#S_FORECAST_NO").val() == ""){
           alert(  "PID를 검색 하고 파일을 첨부해주세요.");
           $('#F_FILE4').val("");
           return;
        
        }
        $("#F_FILE_GBN_CD").val(50);
        //파일 업로드 실행 
        FIleUPload_F_FILE4();
        //파일 테이블 정보 입력력
        FileInfosubmit();
        
        }
        function FIleUPload_F_FILE5()
        {
        wizutil.FileAjaxSubmit(Contextpath+'/jsp/File/fileupload.jsp', fileform, 'F_FILE5', '', ['ppt','xlsx','xls','pptx']);
        }
        function F_FILE5_OnChange(myObj)
        {
        if($("#S_PID").val() =="" || $("#S_FORECAST_NO").val() == ""){
           alert(  "PID를 검색 하고 파일을 첨부해주세요.");
           $('#F_FILE5').val("");
           return;
        
        }
        $("#F_FILE_GBN_CD").val(60);
        //파일 업로드 실행 
        FIleUPload_F_FILE5();
        //파일 테이블 정보 입력력
        FileInfosubmit();
        }
        function FIleUPload_F_FILE6()
        {
        wizutil.FileAjaxSubmit(Contextpath+'/jsp/File/fileupload.jsp', fileform, 'F_FILE6', '', ['ppt','xlsx','xls','pptx']);
        }
        
        function F_FILE6_OnChange(myObj)
        {
        if($("#S_PID").val() =="" || $("#S_FORECAST_NO").val() == ""){
           alert(  "PID를 검색 하고 파일을 첨부해주세요.");
           $('#F_FILE6').val("");
           return;
        
        }
        $("#F_FILE_GBN_CD").val(70);
        //파일 업로드 실행 
        FIleUPload_F_FILE6();
        //파일 테이블 정보 입력력
        FileInfosubmit();
        }
        function FIleUPload_F_FILE7()
        {
        wizutil.FileAjaxSubmit(Contextpath+'/jsp/File/fileupload.jsp', fileform, 'F_FILE7', '', ['ppt','xlsx','xls','pptx']);
        }
        function F_FILE7_OnChange(myObj)
        {
        if($("#S_PID").val() =="" || $("#S_FORECAST_NO").val() == ""){
           alert(  "PID를 검색 하고 파일을 첨부해주세요.");
           $('#F_FILE7').val("");
           return;
        
        }
        $("#F_FILE_GBN_CD").val(71);
        //파일 업로드 실행 
        FIleUPload_F_FILE7();
        //파일 테이블 정보 입력력
        FileInfosubmit();
        }
        function FIleUPload_F_FILE8()
        {
        wizutil.FileAjaxSubmit(Contextpath+'/jsp/File/fileupload.jsp', fileform, 'F_FILE8', '', ['ppt','xlsx','xls','pptx']);
        }
        function F_FILE8_OnChange(myObj)
        {
        if($("#S_PID").val() =="" || $("#S_FORECAST_NO").val() == ""){
           alert(  "PID를 검색 하고 파일을 첨부해주세요.");
           $('#F_FILE8').val("");
           return;
        
        }
        $("#F_FILE_GBN_CD").val(72);
        //파일 업로드 실행 
        FIleUPload_F_FILE8();
        //파일 테이블 정보 입력력
        FileInfosubmit();
        }
        
        
        function S_PID_OnClick(myObj)
        {
        //윈도우 팝업창 추가
        	 action.openWindow("/20160419114244SysCodeInfoPop_loadPage.do" ,"" , "PID_ADD" , "width=200,height=400,scrollbars=yes,resize=no,toolbars=no" );
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
        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:16px; left:36px;  width:91px; height:31px; " id="label2" name="label2"><label style=" line-height:33px;  font-family:굴림체;font-weight:bold;font-size:14.25pt;" >    DCP 관리     </label>    </div>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:82px; left:40px;  width:100px; height:19px; " id="label1" name="label1"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    PID     </label>    </div>

        	<input  class="TextBox-S" style=" position: absolute; top:82px; left:142px;  width:97px; height:17px; " id="S_PID" name="S_PID" type="text" maxlength="50" tabindex="0"  onclick="S_PID_OnClick(this);">    </input>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:107px; left:40px;  width:100px; height:19px; " id="label3" name="label3"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    테이터종별     </label>    </div>

        	<select  style="border-width:1px; background-color:White; color:Black;border-color:Black;text-align:left;  font-family:굴림체;font-size:9.75pt; position: absolute; top:107px; left:142px;  width:102px; height:21px; " id="S_FORECAST_NO" name="S_FORECAST_NO" tabindex="0" eltype="Combo" >
    	</select>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:108px; left:420px;  width:65px; height:21px; " id="button1" name="button1" type="button" value="검색" tabindex="0"  onclick="button1_OnClick(this);" ></input>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:162px; left:38px;  width:129px; height:24px; " id="label4" name="label4"><label style=" line-height:26px;  font-family:굴림체;font-weight:bold;font-size:12pt;" >    DCP 부대정보     </label>    </div>

        	<select  style="border-width:1px; background-color:White; color:Black;border-color:Black;text-align:left;  font-family:굴림체;font-size:9.75pt; position: absolute; top:217px; left:563px;  width:102px; height:21px; " id="S2_LATEST_DCP_TYPE_CD" name="S2_LATEST_DCP_TYPE_CD" tabindex="0" eltype="Combo" >
    	</select>

        	<input  class="TextBox-S" style=" position: absolute; top:217px; left:167px;  width:107px; height:17px; " id="S1_PID" name="S1_PID" type="text" maxlength="50" tabindex="0" >    </input>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:217px; left:437px;  width:124px; height:19px; " id="label12" name="label12"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    최신 DCP 종별     </label>    </div>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:217px; left:40px;  width:125px; height:19px; " id="label5" name="label5"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    PID     </label>    </div>

        	<input  class="TextBox-S" style=" position: absolute; top:242px; left:563px;  width:97px; height:17px; " id="S2_REQ_RETURN_TERM" name="S2_REQ_RETURN_TERM" type="text" maxlength="50" tabindex="0" >    </input>

        	<select  style="border-width:1px; background-color:White; color:Black;border-color:Black;text-align:left;  font-family:굴림체;font-size:9.75pt; position: absolute; top:242px; left:167px;  width:112px; height:21px; " id="S1_PID_STATUS_CD" name="S1_PID_STATUS_CD" tabindex="0" eltype="Combo" >
    	</select>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:242px; left:437px;  width:124px; height:19px; " id="label13" name="label13"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    Required return     </label>    </div>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:242px; left:40px;  width:125px; height:19px; " id="label6" name="label6"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    PID 상태     </label>    </div>

        	<select  style="border-width:1px; background-color:White; color:Black;border-color:Black;text-align:left;  font-family:굴림체;font-size:9.75pt; position: absolute; top:266px; left:167px;  width:112px; height:21px; " id="S1_RND_CATEGROY_CD" name="S1_RND_CATEGROY_CD" tabindex="0" eltype="Combo" >
    	</select>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:266px; left:40px;  width:125px; height:19px; " id="label7" name="label7"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    RnD Category     </label>    </div>

        	<input  class="TextBox-S" style=" position: absolute; top:290px; left:167px;  width:107px; height:17px; " id="S1_RND_THEME" name="S1_RND_THEME" type="text" maxlength="50" tabindex="0" >    </input>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:290px; left:40px;  width:125px; height:19px; " id="label8" name="label8"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    Rnd Theme     </label>    </div>

        	<input  class="TextBox-S" style=" position: absolute; top:313px; left:563px;  width:97px; height:17px; " id="S2_APPROV_AMT" name="S2_APPROV_AMT" type="text" maxlength="50" tabindex="0" >    </input>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:313px; left:437px;  width:124px; height:19px; " id="label14" name="label14"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    결제대상금액     </label>    </div>

        	<input  class="TextBox-S" style=" position: absolute; top:314px; left:167px;  width:107px; height:17px; " id="S1_PRODUCT_CD" name="S1_PRODUCT_CD" type="text" maxlength="50" tabindex="0" >    </input>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:314px; left:40px;  width:125px; height:19px; " id="label9" name="label9"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    Product     </label>    </div>

        	<input  class="TextBox-S" style=" position: absolute; top:337px; left:563px;  width:97px; height:17px; " id="S2_REQ_ORDER_AMT" name="S2_REQ_ORDER_AMT" type="text" maxlength="50" tabindex="0" >    </input>

        	<input  class="TextBox-S" style=" position: absolute; top:337px; left:167px;  width:107px; height:17px; " id="S1_NICKNAME_1" name="S1_NICKNAME_1" type="text" maxlength="50" tabindex="0" >    </input>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:337px; left:437px;  width:124px; height:19px; " id="label15" name="label15"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    품의주문금액     </label>    </div>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:338px; left:40px;  width:125px; height:19px; " id="label10" name="label10"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    Nickname(표시용)     </label>    </div>

        	<input  class="TextBox-S" style=" position: absolute; top:362px; left:167px;  width:107px; height:17px; " id="S1_NICKNAME_2" name="S1_NICKNAME_2" type="text" maxlength="50" tabindex="0" >    </input>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:362px; left:40px;  width:125px; height:19px; " id="label11" name="label11"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    Nickname(사외용)     </label>    </div>

        	<input  class="TextBox-S" style=" position: absolute; top:397px; left:563px;  width:97px; height:17px; " id="S2_BOTTOM_SP_AMT" name="S2_BOTTOM_SP_AMT" type="text" maxlength="50" tabindex="0" >    </input>

        	<input  class="TextBox-S" style=" position: absolute; top:397px; left:167px;  width:97px; height:17px; " id="S2_APPORV_DEPT_CD" name="S2_APPORV_DEPT_CD" type="text" maxlength="50" tabindex="0" >    </input>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:397px; left:40px;  width:125px; height:19px; " id="label19" name="label19"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    결재부서코드     </label>    </div>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:397px; left:437px;  width:124px; height:19px; " id="label16" name="label16"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    BottomSP 금액     </label>    </div>

        	<input  class="TextBox-S" style=" position: absolute; top:422px; left:563px;  width:97px; height:17px; " id="S2_BOTTOM_TP_AMT" name="S2_BOTTOM_TP_AMT" type="text" maxlength="50" tabindex="0" >    </input>

        	<input  class="TextBox-S" style=" position: absolute; top:422px; left:167px;  width:97px; height:17px; " id="S2_REQ_DEPT_CD" name="S2_REQ_DEPT_CD" type="text" maxlength="50" tabindex="0" >    </input>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:422px; left:40px;  width:125px; height:19px; " id="label20" name="label20"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    선청부서코드     </label>    </div>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:422px; left:437px;  width:124px; height:19px; " id="label17" name="label17"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    BottomTP 금액     </label>    </div>

        	<input  class="TextBox-S" style=" position: absolute; top:446px; left:563px;  width:97px; height:17px; " id="S2_SALE_COMPANY_MARGIN_RATE" name="S2_SALE_COMPANY_MARGIN_RATE" type="text" maxlength="50" tabindex="0" >    </input>

        	<input  class="TextBox-S" style=" position: absolute; top:446px; left:167px;  width:97px; height:17px; " id="S2_RESPONIBILITY" name="S2_RESPONIBILITY" type="text" maxlength="50" tabindex="0" >    </input>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:446px; left:40px;  width:125px; height:19px; " id="label21" name="label21"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    Responsibility     </label>    </div>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:446px; left:437px;  width:124px; height:19px; " id="label18" name="label18"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    판매회사마진율     </label>    </div>

        	<input  class="TextBox-S" style=" position: absolute; top:470px; left:167px;  width:97px; height:17px; " id="S2_CHARGE_PERSON_ID" name="S2_CHARGE_PERSON_ID" type="text" maxlength="50" tabindex="0" >    </input>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:470px; left:40px;  width:125px; height:19px; " id="label22" name="label22"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    Person in charge     </label>    </div>

        <div id="grid1_gdiv"  style=" position: absolute; top:519px; left:41px; width:876px;height:140px;" >
		     <table id="grid1" elType='Grid' ></table>
	    </div>

        <div id="grid2_gdiv"  style=" position: absolute; top:689px; left:45px; width:300px;height:136px;" >
		     <table id="grid2" elType='Grid' ></table>
	    </div>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:846px; left:48px;  width:104px; height:25px; " id="label23" name="label23"><label style=" line-height:27px;  font-family:굴림체;font-weight:bold;font-size:14.25pt;" >    파일첨부     </label>    </div>

        	<input type='hidden'  id="F_FILE1_NAME" name="F_FILE1_NAME"/>
    	<input type='hidden'  id="F_FILE1_PATH" name="F_FILE1_PATH"/>
    	<input type='hidden'  id="F_FILE1_UNNAME" name="F_FILE1_UNNAME"/>
    	<input type='hidden'  id="F_FILE1_SIZE" name="F_FILE1_SIZE"/>

        	<input type='hidden'  id="F_FILE_NAME" name="F_FILE_NAME"/>
    	<input type='hidden'  id="F_FILE_PATH" name="F_FILE_PATH"/>
    	<input type='hidden'  id="F_FILE_UNNAME" name="F_FILE_UNNAME"/>
    	<input type='hidden'  id="F_FILE_SIZE" name="F_FILE_SIZE"/>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:902px; left:435px;  width:100px; height:19px; " id="label26" name="label26"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    PL시트     </label>    </div>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:902px; left:50px;  width:100px; height:19px; " id="label24" name="label24"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    관람시트파일     </label>    </div>

        	<input type='hidden'  id="F_FILE3_NAME" name="F_FILE3_NAME"/>
    	<input type='hidden'  id="F_FILE3_PATH" name="F_FILE3_PATH"/>
    	<input type='hidden'  id="F_FILE3_UNNAME" name="F_FILE3_UNNAME"/>
    	<input type='hidden'  id="F_FILE3_SIZE" name="F_FILE3_SIZE"/>

        	<input type='hidden'  id="F_FILE2_NAME" name="F_FILE2_NAME"/>
    	<input type='hidden'  id="F_FILE2_PATH" name="F_FILE2_PATH"/>
    	<input type='hidden'  id="F_FILE2_UNNAME" name="F_FILE2_UNNAME"/>
    	<input type='hidden'  id="F_FILE2_SIZE" name="F_FILE2_SIZE"/>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:936px; left:435px;  width:100px; height:19px; " id="label28" name="label28"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    견적시트     </label>    </div>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:936px; left:50px;  width:100px; height:19px; " id="label27" name="label27"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    원가계산시트     </label>    </div>

        	<input type='hidden'  id="F_FILE5_NAME" name="F_FILE5_NAME"/>
    	<input type='hidden'  id="F_FILE5_PATH" name="F_FILE5_PATH"/>
    	<input type='hidden'  id="F_FILE5_UNNAME" name="F_FILE5_UNNAME"/>
    	<input type='hidden'  id="F_FILE5_SIZE" name="F_FILE5_SIZE"/>

        	<input type='hidden'  id="F_FILE4_NAME" name="F_FILE4_NAME"/>
    	<input type='hidden'  id="F_FILE4_PATH" name="F_FILE4_PATH"/>
    	<input type='hidden'  id="F_FILE4_UNNAME" name="F_FILE4_UNNAME"/>
    	<input type='hidden'  id="F_FILE4_SIZE" name="F_FILE4_SIZE"/>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:969px; left:435px;  width:100px; height:19px; " id="label25" name="label25"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    CHIP사양시트     </label>    </div>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:969px; left:50px;  width:100px; height:19px; " id="label29" name="label29"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    DR장표시트     </label>    </div>

        	<input type='hidden'  id="F_FILE7_NAME" name="F_FILE7_NAME"/>
    	<input type='hidden'  id="F_FILE7_PATH" name="F_FILE7_PATH"/>
    	<input type='hidden'  id="F_FILE7_UNNAME" name="F_FILE7_UNNAME"/>
    	<input type='hidden'  id="F_FILE7_SIZE" name="F_FILE7_SIZE"/>

        	<input type='hidden'  id="F_FILE6_NAME" name="F_FILE6_NAME"/>
    	<input type='hidden'  id="F_FILE6_PATH" name="F_FILE6_PATH"/>
    	<input type='hidden'  id="F_FILE6_UNNAME" name="F_FILE6_UNNAME"/>
    	<input type='hidden'  id="F_FILE6_SIZE" name="F_FILE6_SIZE"/>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:1000px; left:435px;  width:100px; height:19px; " id="label31" name="label31"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    그외 2     </label>    </div>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:1000px; left:50px;  width:100px; height:19px; " id="label30" name="label30"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    그외 1     </label>    </div>

        	<input type='hidden'  id="F_FILE8_NAME" name="F_FILE8_NAME"/>
    	<input type='hidden'  id="F_FILE8_PATH" name="F_FILE8_PATH"/>
    	<input type='hidden'  id="F_FILE8_UNNAME" name="F_FILE8_UNNAME"/>
    	<input type='hidden'  id="F_FILE8_SIZE" name="F_FILE8_SIZE"/>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:1033px; left:50px;  width:100px; height:19px; " id="label32" name="label32"><label style=" line-height:21px;  font-family:굴림체;font-size:9.75pt;" >    그외 3     </label>    </div>

        	<input  class="TextBox-S" style=" position: absolute; top:1041px; left:694px;  width:97px; height:17px; " id="F_FILE_GBN_CD" name="F_FILE_GBN_CD" type="hidden" maxlength="50" tabindex="0" >    </input>

        	<input  class="TextBox-S" style=" position: absolute; top:1041px; left:576px;  width:97px; height:17px; " id="F_FORECAST_NO" name="F_FORECAST_NO" type="hidden" maxlength="50" tabindex="0" >    </input>

        	<input  class="TextBox-S" style=" position: absolute; top:1041px; left:466px;  width:97px; height:17px; " id="F_PID" name="F_PID" type="hidden" maxlength="50" tabindex="0" >    </input>

</form>	
<form id='fileform' name='fileform' encType='multipart/form-data'>
        	<input type='file' style="border:solid; border-width:0px;background-color:White; color:Black;border-color:Black;text-align:left; position: absolute; top:902px; left:537px;  width:269px;" id="F_FILE1" name="F_FILE1" tabindex="0"  onchange="F_FILE1_OnChange(this);" ></input>
        	<input type='file' style="border:solid; border-width:0px;background-color:White; color:Black;border-color:Black;text-align:left; position: absolute; top:902px; left:152px;  width:269px;" id="F_FILE" name="F_FILE" tabindex="0"  onchange="F_FILE_OnChange(this);" ></input>
        	<input type='file' style="border:solid; border-width:0px;background-color:White; color:Black;border-color:Black;text-align:left; position: absolute; top:936px; left:537px;  width:269px;" id="F_FILE3" name="F_FILE3" tabindex="0"  onchange="F_FILE3_OnChange(this);" ></input>
        	<input type='file' style="border:solid; border-width:0px;background-color:White; color:Black;border-color:Black;text-align:left; position: absolute; top:936px; left:152px;  width:269px;" id="F_FILE2" name="F_FILE2" tabindex="0"  onchange="F_FILE2_OnChange(this);" ></input>
        	<input type='file' style="border:solid; border-width:0px;background-color:White; color:Black;border-color:Black;text-align:left; position: absolute; top:969px; left:537px;  width:269px;" id="F_FILE5" name="F_FILE5" tabindex="0"  onchange="F_FILE5_OnChange(this);" ></input>
        	<input type='file' style="border:solid; border-width:0px;background-color:White; color:Black;border-color:Black;text-align:left; position: absolute; top:969px; left:152px;  width:269px;" id="F_FILE4" name="F_FILE4" tabindex="0"  onchange="F_FILE4_OnChange(this);" ></input>
        	<input type='file' style="border:solid; border-width:0px;background-color:White; color:Black;border-color:Black;text-align:left; position: absolute; top:1000px; left:537px;  width:269px;" id="F_FILE7" name="F_FILE7" tabindex="0"  onchange="F_FILE7_OnChange(this);" ></input>
        	<input type='file' style="border:solid; border-width:0px;background-color:White; color:Black;border-color:Black;text-align:left; position: absolute; top:1000px; left:152px;  width:269px;" id="F_FILE6" name="F_FILE6" tabindex="0"  onchange="F_FILE6_OnChange(this);" ></input>
        	<input type='file' style="border:solid; border-width:0px;background-color:White; color:Black;border-color:Black;text-align:left; position: absolute; top:1033px; left:152px;  width:269px;" id="F_FILE8" name="F_FILE8" tabindex="0"  onchange="F_FILE8_OnChange(this);" ></input>
	<div style='display:none'>
	<iframe id='FileDownload'  name='FileDownload' style='border-width:0px;' src='' frameborder='0' ></iframe>
	</div>
	
</form>
    </div>
</body>
<script type="text/javascript" >
    var GridRowHeight= {"grid1":"22","grid2":"22",};
    var ChkNumberColumns= [];
    var RadioProp= {};
    var CheckProp= {};
    var ComboObjProp= {"S_FORECAST_NO":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--선택--"}, "S2_LATEST_DCP_TYPE_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":""}, "S1_PID_STATUS_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":""}, "S1_RND_CATEGROY_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":""}};
    var RequiredProp= {"S_PID":"PID를 입력하시오"};
    var CellEditoption= {};
    var InitItems= {"S_FORECAST_NO":":", "S2_LATEST_DCP_TYPE_CD":":", "S1_PID_STATUS_CD":":", "S1_RND_CATEGROY_CD":":"};
    var sbmInit = new SubMission( frm, "/hpms/2016082417883PidInfoMgr_MasterComboList.do","","S1_PID_STATUS_CD:SEL2,S1_RND_CATEGROY_CD:SEL4,S2_LATEST_DCP_TYPE_CD:SEL10","");
    var DCPsearch = new SubMission( frm, "/hpms/2016082417884DcpInfoMgr_DCPSearchInfo.do","S[A]:S","S1:SEL2,S2:SEL4,grid1:SEL6,grid2:SEL8","");
    var FIleInfoAdd = new SubMission( frm, "/hpms/2016082417884DcpInfoMgr_ServiceID00.do","F[A]:F","","");
    var Forecast_data_combo = new SubMission( frm, "/hpms/2016082417884DcpInfoMgr_DCP_ComboList.do","S[A]:S","S_FORECAST_NO:SEL2","");
    sbmInit.InitCombo("S_FORECAST_NO,S2_LATEST_DCP_TYPE_CD,S1_PID_STATUS_CD,S1_RND_CATEGROY_CD");
    var DefalueGrid =[], ColNm_grid1=[] , ColInfo_grid1=[], ColNm_grid2=[] , ColInfo_grid2=[] ; 
    var grid1= jQuery("#grid1");
    var grid2= jQuery("#grid2");
    var grid1_Data = []; 
    function HeadLabel_grid1(){
	ColNm_grid1.unshift("ROWID","S","PID","FORECAST번호","DCP종별코드","예정일","실시일","실시회수","승인일");
	return ColNm_grid1;
	}
    function Columninfo_grid1(){
	ColInfo_grid1.unshift(
        	 {index: "id", name:"id", width:75, key:true, hidden:true }        	, {index:'IUDFLAG',name:'IUDFLAG',editable:false,width:10,formatter:'select',edittype:'select',hidden:true,editoptions:{ value:':;I:I;U:U;D:D',defaultValue:'' }}
        	 ,{index:"PID",name:"PID",width:100,hidden:true,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'PID'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"FORECAST_NO",name:"FORECAST_NO",width:100,hidden:true,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'FORECAST_NO'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"DCP_TYPE_CD",name:"DCP_TYPE_CD",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'DCP_TYPE_CD'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"PLAN_YMD",name:"PLAN_YMD",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'PLAN_YMD'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"EXEC_YMD",name:"EXEC_YMD",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'EXEC_YMD'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"EXEC_NUM",name:"EXEC_NUM",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'EXEC_NUM'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"APPROV_YMD",name:"APPROV_YMD",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'APPROV_YMD'  }  ] }  ,cellattr:rowspan,align:"center"}	
);
	return ColInfo_grid1;
	}

    var grid2_Data = []; 
    function HeadLabel_grid2(){
	ColNm_grid2.unshift("ROWID","S","PID","SUB PID","용도","이용부서코드");
	return ColNm_grid2;
	}
    function Columninfo_grid2(){
	ColInfo_grid2.unshift(
        	 {index: "id", name:"id", width:75, key:true, hidden:true }        	, {index:'IUDFLAG',name:'IUDFLAG',editable:false,width:10,formatter:'select',edittype:'select',hidden:true,editoptions:{ value:':;I:I;U:U;D:D',defaultValue:'' }}
        	 ,{index:"PID",name:"PID",width:100,hidden:true,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'PID'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"SUB_PID",name:"SUB_PID",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'SUB_PID'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"SUB_PID_USE",name:"SUB_PID_USE",width:100,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'SUB_PID_USE'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"USE_DEPT_CD",name:"USE_DEPT_CD",width:100,hidden:true,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'USE_DEPT_CD'  }  ] }  ,cellattr:rowspan,align:"center"}	
);
	return ColInfo_grid2;
	}

    var initGrids = function() { 
        grid1.wizGrid({   
        	data:grid1_Data,   
        	height:101,  
        	width:876,    
        	cellEdit:true,   
        	Names: HeadLabel_grid1()
,        	Columns: Columninfo_grid1() ,
        	OnEditCell : function(rowid, colname, value, iCol,iRow) {  
        	}  
        	,OnEnterRow : function(rowindex, selrow) {  
        	}  
        	,OnLoadComplete : function() {  

	         var gridName ='grid1';

	         $("[colheight=grid1]").css('height',"22px");
        		 $("#paginate_grid1").hide();
        		 
        		 
        		 
        		 
        	}  
        });


        grid2.wizGrid({   
        	data:grid2_Data,   
        	height:97,  
        	width:300,    
        	cellEdit:true,   
        	Names: HeadLabel_grid2()
,        	Columns: Columninfo_grid2() ,
        	OnEditCell : function(rowid, colname, value, iCol,iRow) {  
        	}  
        	,OnEnterRow : function(rowindex, selrow) {  
        	}  
        	,OnLoadComplete : function() {  

	         var gridName ='grid2';

	         $("[colheight=grid2]").css('height',"22px");
        		 $("#paginate_grid2").hide();
        		 
        		 
        		 
        		 
        	}  
        });



    }


    $(document).ready(function () { 
        sbmInit.submit(false, "initGrids");
        grid1.setDatasetName("G1");        
        grid2.setDatasetName("G2");        
    });
</script>
</html>