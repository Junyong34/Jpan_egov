<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>Forecast Management</title>
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
              
              COMPANY_ORG_COMBO.submit(false, "sessionSet");
              $(document).keydown(function(e){   
                   
                   if(e.target.nodeName != "INPUT" && e.target.nodeName != "TEXTAREA"){       
                    if(e.keyCode === 8){   
                    return false;
                     }
                    }
                  });
                  
              });  
        
        
        function sessionSet(){
        if("${G.COMPANY_CD}" != ""){
            // $("#S2_COMPANY_CD").val("${G.COMPANY_CD}"+"${G.COMPANY_ORG_CD}");
        	// $("#S4_COMPANY_CD").val("${G.COMPANY_CD}"+"${G.COMPANY_ORG_CD}");
        	// ORG_combo_S4.submit(false);
        //	 ORG_combo_S2.submit(false);
        }
        
        }
        function ValCheck(){
        
         if ($("#C_CNT").val() != 1) {
        
                Return_RTN_MSG("00020");
                $("#C_CNT").val('');
                return;
        
            }
          /* if( $("#out_message").val() != "" )
            {
              ErrorCheck();
              return;
            }   */
          
           if(confirm(confirm_MSG("0001"))){
           
            TitleSet.submit(false,"Result");
           }
           
        }
        function ValCheck2(){
        
        
          if ($("#C_CNT").val() != 1) {
        
                Return_RTN_MSG("00020");
                $("#C_CNT").val('');
                return;
        
            }
         
           if(confirm(confirm_MSG("0001"))){
           
            DataCopy.submit(false,"ReslutMSG");
             
           }
           
        }
        function ValCheck3(){
        
        
          if ($("#C_CNT").val() != 1) {
        
                Return_RTN_MSG("00019","PID");
                $("#C_CNT").val('');
                return;
        
            }
         
           if(confirm(confirm_MSG("0001"))){
           
            DataCopy.submit(false,"ReslutMSG");
             
           }
           
        }
        function ReslutMSG(){
         InputClear();
         Return_RTN_MSG("00002");
          /* var rtn_msg ="";
           rtn_msg= $("#out_message").val(); 
           if(rtn_msg == 00019){
            Return_RTN_MSG(rtn_msg,"PID","S3_PID");
           return;
           }else if(rtn_msg == 00020){
            Return_RTN_MSG(rtn_msg,"PID","S3_PID");
           return;
           }else{
              Return_RTN_MSG(rtn_msg);
           }
        */
        }
        function Result(){
        
            
              InputClear();
               Return_RTN_MSG("00002");
               
            
        }
        function button1_OnClick(myObj)
        {
        
           sbmCtlSave.submit(false, "title1"); 
           
        }
        function title1(){
        
            if( $("#out_message").val() != "" )
            {
              ErrorCheck();
              return;
            }    
            if( $("#S1_DATA_TYPE").val() == ""  ){
                Return_RTN_MSG("00004","Forecast","S1_DATA_TYPE");
                return;
            }else if( $("#S1_PID").val() == "" ){
                Return_RTN_MSG("00003","PID","S1_PID");
                 return;
            }else if(  $("#S1_DATA_TYPE_NM").val() == "" ){
               Return_RTN_MSG("00003","TITLE","S1_DATA_TYPE_NM");
                return;
            }
            //정규식 체크 
            if(PatternVal($("#S1_PID").val(),"PID") == -1){
              return;
            }
            
             
                 
                   
               
          
                PID_CHECK.submit(false,"PID_AUTH_CHECK"); //pid체크 파라미터 수정해야함 현재안됨.
            
          
           
         
        }
        function PID_AUTH_CHECK(){
        	
        	if( $("#C_CNT").val() == "0"){
        	
        	   Return_RTN_MSG("00019","PID");
        	   return;
        	}else{
        	 if("${G.AUTH_CD}" != "50"){
        	
               $("#C_CNT").val(1);
                  ValCheck();
              
        	  }else{
        	
        	     PID_Confirm2.submit(false,"ValCheck");
        	  }
          }
        }
        function button2_OnClick(myObj)
        {
        
         sbmCtlSave.submit(false, "title2"); 
        
        
        }
        function title2(){
        
             if( $("#out_message").val() != "" )
            {
              ErrorCheck();
              return;
            }    
        	if($("#S2_COMPANY_CD").val() == ""){
        	   Return_RTN_MSG("00004","Company Name","S2_COMPANY_CD");
        	   return;
            }else if( $("#S2_ORG_CD").val() == "" ){
                Return_RTN_MSG("00004","Organization Name","S2_ORG_CD");
                return;
            }else if( $("#S2_DATA_TYPE").val() == ""  ){
               Return_RTN_MSG("00004","Forecast","S2_DATA_TYPE");
               return;
            }else if(  $("#S2_DATA_TYPE_NM").val() == "" ){
               Return_RTN_MSG("00003","TITLE","S2_DATA_TYPE_NM");
               return;
            }
            
            if("${G.AUTH_CD}" != "50"){
               $("#C_CNT").val(1);
               ORG_Title();
               
            }else{
             
             ORG_Confirm.submit(false,"ORG_Title");
             
            }
            
        
        }
        function ORG_Title(){
        
          if ($("#C_CNT").val() != 1) {
        
                Return_RTN_MSG("00020");
                $("#C_CNT").val('');
                return;
        
            }
           /* if( $("#out_message").val() != "" )
            {
              ErrorCheck();
              return;
            }  */  
            if(confirm(confirm_MSG("0001"))){
        		TitleSet2.submit(false,"Result");
            }
        
        }
        function button3_OnClick(myObj)
        {
          sbmCtlWrite.submit(false, "FCSTSAVE1");  
        }
        function FCSTSAVE1(){
        
           if( $("#out_message").val() != "" )
            {
              ErrorCheck();
              return;
            }    
           if( $("#S3_PID").val() == "" ){
                Return_RTN_MSG("00003","PID","S3_PID");
                return;
            }else if( $("#S3_TO").val() == "" ){
                Return_RTN_MSG("00004","TO","S3_TO");
                return;
            }else if( $("#S3_FROM").val() == "" ){
               Return_RTN_MSG("00004","FROM","S3_FROM");
               return;
            }
            
            if( $("#S3_TO").val() == $("#S3_FROM").val()){
                  Return_RTN_MSG("00035");
                  return;
            }
            
            //정규식 체크 
            if(PatternVal($("#S3_PID").val(),"PID") == -1){
              return;
            }
            
             if("${G.AUTH_CD}" != "50"){
               $("#C_CNT").val("1");
            PID_CHECK.submit(false,"ValCheck3");
             
              
          }else{
           PID_Confirm.submit(false,"ValCheck2");
          }
              
            
        
        }
        function button4_OnClick(myObj)
        {
          sbmCtlWrite.submit(false, "FCSTSAVE2");  
            
        }
        function FCSTSAVE2(){
        
            if( $("#out_message").val() != "" )
            {
              ErrorCheck();
              return;
            }    
            
            if($("#S4_COMPANY_CD").val() == ""){
        	   Return_RTN_MSG("00004","Company Name","S4_COMPANY_CD");
        	   return;
            }else if( $("#S4_ORG_CD").val() == ""  ){
               Return_RTN_MSG("00004","Organization Name","S4_ORG_CD");
               return;
            }else if( $("#S4_TO").val() == "" ){
                Return_RTN_MSG("00004","TO","S4_TO");
                return;
            }else if( $("#S4_FROM").val() == "" ){
               Return_RTN_MSG("00004","FROM","S4_FROM");
               return;
            }
            
            if( $("#S4_TO").val() == $("#S4_FROM").val()){
                  Return_RTN_MSG("00035");
                  return;
            }
             if("${G.AUTH_CD}" != "50"){
                $("#C_CNT").val("1");
               ORG_Copy();
               
            }else{
             
             ORG_Confirm2.submit(false,"ORG_Copy");
             
            }
            
            
           
        
        }
        function ORG_Copy(){
        
         if ($("#C_CNT").val() != 1) {
        
                Return_RTN_MSG("00020");
                $("#C_CNT").val('');
                return;
        
            }
        /* if( $("#out_message").val() != "" )
            {
              ErrorCheck();
              return;
            }  */  
        
         if(confirm(confirm_MSG("0001"))){
             DataCopy2.submit(false,"Result"); 
             
                }
        
        }
        function S2_ORG_CD_OnClick(myObj)
        {
           if($("#S2_COMPANY_CD").val() == ""){
                Return_RTN_MSG("00004","Company Name","S2_COMPANY_CD");
                return;
           }
        }
        function S2_COMPANY_CD_OnChange(myObj)
        {
         if( $("#S2_COMPANY_CD").val()==""){
             $("#S2_ORG_CD").val("");
             return;
          }
         
         ORG_combo_S2.submit(false);
         
        }
        function S4_ORG_CD_OnClick(myObj)
        {
        if($("#S4_COMPANY_CD").val() == ""){
                Return_RTN_MSG("00004","Company Name","S4_COMPANY_CD");
                return;
           }
        }
        function S4_COMPANY_CD_OnChange(myObj)
        {
        if( $("#S4_COMPANY_CD").val() ==""){
          
           $("#S4_ORG_CD").val("");
             return;
        }
        ORG_combo_S4.submit(false);
         
        }
        
        function InputClear(){
        
         $('input[type=text]').val('');
         $('select').each(function(){
        	$(this).find('option:eq(0)').prop('selected','true');
        	});
        }
        
        function ErrorCheck(){
        //오류가 발생할 때 
        if(ErroMsgDisplay() != 0) {
            
           // 메시지 코드드
           var ERROR_CODE ="";
           ERROR_CODE= $("#out_message").val();
           
           if(ErroMsgDisplay() == 9999){
                ERROR_CODE= 9999;
           }
        
           var param = ""
           param += "?ERROR_CODE="+ERROR_CODE+"&ERROR_MSG="+ErroMsgDisplay();
            
           var url = "/hpms/20160906131150ErrorDisplay_loadpage.do"+param;
        	 
        	   $("#Error_cd").attr("src",url);
        	   $("#Error_cd").attr("frameborder", 0);
        	   $("#divPopup_ErrorCode").dialog({
        			autoPoen:false,
        			height : 260,
        			//position:[50,300],
        			width:530,
        			modal: true, 
        			title: "Error Indication",
        			resizable: true,
        			closeOnEscape:true ,
        		 
        		close: function(){ 
                 
        		//	window.location.reload();
        			// iframe 초기화.
               $("#" + this.id).find('iframe').contents().find('body').html('');
              	}
              	});
        	   $("#divPopup_ErrorCode").dialog("open"); 
           	}
        }
        
        
        
        
        function S1_DATA_TYPE_OnClick(myObj)
        {
        
         if($("#S1_DATA_TYPE  option:eq(0)").text() !="--Choice--"&& $("#S1_PID").val()==""){
            Return_RTN_MSG("00003","PID","S1_PID");
            return;
          } 
        }
        function S2_DATA_TYPE_OnClick(myObj)
        {
        if($("#S2_DATA_TYPE  option:eq(0)").text() !="--Choice--"){
            Return_RTN_MSG("00004","Organization Name","S2_ORG_CD");
             return;
          } 
        }
        function S3_FROM_OnClick(myObj)
        {
        if($("#S3_FROM  option:eq(0)").text() !="--Choice--"&& $("#S3_PID").val()==""){
            Return_RTN_MSG("00003","PID","S4_PID");
             return;
          } 
        }
        function S3_TO_OnClick(myObj)
        {
        if($("#S3_TO  option:eq(0)").text() !="--Choice--" && $("#S3_PID").val()==""){
            Return_RTN_MSG("00003","PID","S4_PID");
             return;
          } 
        }
        function S4_FROM_OnMouseDown(myObj)
        {
        if($("#S4_FROM  option:eq(0)").text() !="--Choice--"){
            Return_RTN_MSG("00004","Organization Name","S4_ORG_CD");
             return;
          } 
        }
        function S4_TO_OnClick(myObj)
        {
        if($("#S4_TO  option:eq(0)").text() !="--Choice--"){
            Return_RTN_MSG("00004","Organization Name","S4_ORG_CD");
             return;
          } 
        }
        function S1_PID_OnKeyUp(myObj)
        {
         if($("#S1_PID").val() != "" && $("#S1_PID").val().length ==6){
           
             FCST_Title_Combo.submit(false);
           }
        }
        function S3_PID_OnKeyUp(myObj)
        {
        if($("#S3_PID").val() != ""  && $("#S3_PID").val().length ==6){
           
             FCST_TitleFrom_Combo.submit(false);
             FCST_TitleTo_Combo.submit(false);
           }
        }
        function S2_ORG_CD_OnChange(myObj)
        {
         if( $("#S2_ORG_CD").val() != ""){
               FCST_Title_ORG_Combo.submit(false);
           } 
        }
        function S4_ORG_CD_OnChange(myObj)
        {
          if( $("#S4_ORG_CD").val() != ""){
               FCST_Title_ORG_Combo_COPY.submit(false);
           } 
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
        	<div  class="titleLabel" style=" position: absolute; top:22px; left:30px;  width:435px; height:34px; " id="label1" name="label1"><label style=" line-height:36px; " >    Forecast Management     </label>    </div>

        	<input  class="TextBox-S" style=" position: absolute; top:79px; left:668px;  width:97px; height:17px; " id="C_CNT" name="C_CNT" type="hidden" maxlength="50" tabindex="0" >    </input>

        	<div   id="user1" style=" position: absolute; top:81px; left:910px;  width:58px; height:73px; display:none;" >
<!-- Error Code screen -->

<div id="divPopup_ErrorCode" name="divPopup_ErrorCode" style="display:none;background-color:white;">
	<iframe id="Error_cd" name="Error_cd" style="width:500px;height:190px;" scrolling="auto">
	</iframe>
</div></div>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:86px; left:63px;  width:270px; height:39px; " id="label2" name="label2"><label style=" line-height:41px;  font-family:굴림체;font-weight:bold;font-size:14.25pt;" >    Forecast Title Setting     </label>    </div>

      <div  id="group1" name="group1"  style=" background-color:White;border-width:1px;border-style:solid;border-color:DarkGray; position: absolute; top:135px; left:65px;  width:740px; height:175px; " >  
                	<div  class="infoLabel" style="position:absolute;left:331px;top:24px; width:122px; height:19px; " id="label6" name="label6"><label style=" line-height:21px; " >        Forecast         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:181px;top:24px; width:122px; height:19px; " id="label5" name="label5"><label style=" line-height:21px; " >        PID         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:478px;top:24px; width:122px; height:19px; " id="label7" name="label7"><label style=" line-height:21px; " >        Title         </label>        </div>
                	<input  style="position:absolute;left:619px;top:47px; width:52px; height:21px; aa" id="button1" name="button1" type="button" disabled="disabled" value="set" tabindex="4"  onclick="button1_OnClick(this);" ></input>
                	<select  class="selectbox" style="position:absolute;left:332px;top:47px; width:124px; height:21px; " id="S1_DATA_TYPE" name="S1_DATA_TYPE" tabindex="2" onclick="S1_DATA_TYPE_OnClick(this);" eltype="Combo" >
        	</select>
                	<input  class="TextBox-S" style="position:absolute;left:182px;top:47px; width:119px; height:17px; " id="S1_PID" name="S1_PID" type="text" maxlength="6" tabindex="1"  onkeyup="S1_PID_OnKeyUp(this);">        </input>
                	<input  class="TextBox-S" style="position:absolute;left:478px;top:48px; width:119px; height:17px; " id="S1_DATA_TYPE_NM" name="S1_DATA_TYPE_NM" type="text" maxlength="60" tabindex="3" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:478px;top:98px; width:122px; height:19px; " id="label10" name="label10"><label style=" line-height:21px; " >        Title         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:32px;top:98px; width:122px; height:19px; " id="label4" name="label4"><label style=" line-height:21px; " >        Company Name         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:331px;top:98px; width:122px; height:19px; " id="label9" name="label9"><label style=" line-height:21px; " >        Forecast         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:180px;top:98px; width:122px; height:19px; " id="label8" name="label8"><label style=" line-height:21px; " >        Organization Name         </label>        </div>
                	<input  style="position:absolute;left:620px;top:121px; width:52px; height:21px; aaa" id="button2" name="button2" type="button" disabled="disabled" value="set" tabindex="9"  onclick="button2_OnClick(this);" ></input>
                	<select  class="selectbox" style="position:absolute;left:180px;top:121px; width:124px; height:21px; " id="S2_ORG_CD" name="S2_ORG_CD" tabindex="6" onclick="S2_ORG_CD_OnClick(this);" onchange="S2_ORG_CD_OnChange(this);" eltype="Combo" >
        	</select>
                	<select  class="selectbox" style="position:absolute;left:32px;top:121px; width:124px; height:21px; " id="S2_COMPANY_CD" name="S2_COMPANY_CD" tabindex="5" onchange="S2_COMPANY_CD_OnChange(this);" eltype="Combo" >
        	</select>
                	<select  class="selectbox" style="position:absolute;left:331px;top:121px; width:124px; height:21px; " id="S2_DATA_TYPE" name="S2_DATA_TYPE" tabindex="7" onclick="S2_DATA_TYPE_OnClick(this);" eltype="Combo" >
        	</select>
                	<input  class="TextBox-S" style="position:absolute;left:479px;top:122px; width:119px; height:17px; " id="S2_DATA_TYPE_NM" name="S2_DATA_TYPE_NM" type="text" maxlength="60" tabindex="8" >        </input>
      </div>
        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:325px; left:63px;  width:270px; height:39px; " id="label3" name="label3"><label style=" line-height:41px;  font-family:굴림체;font-weight:bold;font-size:14.25pt;" >    Forecast Data Copy     </label>    </div>

      <div  id="group2" name="group2"  style=" background-color:White;border-width:1px;border-style:solid;border-color:DarkGray; position: absolute; top:382px; left:62px;  width:740px; height:175px; " >  
                	<div  class="infoLabel" style="position:absolute;left:487px;top:29px; width:122px; height:19px; " id="label16" name="label16"><label style=" line-height:21px; " >        To         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:340px;top:29px; width:122px; height:19px; " id="label13" name="label13"><label style=" line-height:21px; " >        From         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:190px;top:29px; width:122px; height:19px; " id="label12" name="label12"><label style=" line-height:21px; " >        PID         </label>        </div>
                	<select  class="selectbox" style="position:absolute;left:487px;top:52px; width:124px; height:21px; " id="S3_TO" name="S3_TO" tabindex="12" onclick="S3_TO_OnClick(this);" eltype="Combo" >
        	</select>
                	<select  class="selectbox" style="position:absolute;left:340px;top:52px; width:124px; height:21px; " id="S3_FROM" name="S3_FROM" tabindex="11" onclick="S3_FROM_OnClick(this);" eltype="Combo" >
        	</select>
                	<input  class="TextBox-S" style="position:absolute;left:190px;top:52px; width:119px; height:17px; " id="S3_PID" name="S3_PID" type="text" maxlength="6" tabindex="10"  onkeyup="S3_PID_OnKeyUp(this);">        </input>
                	<input  class="btn3" style="position:absolute;left:629px;top:56px; width:52px; height:21px; " id="button3" name="button3" type="button" value="Copy" tabindex="13"  onclick="button3_OnClick(this);" ></input>
                	<div  class="infoLabel" style="position:absolute;left:41px;top:89px; width:122px; height:19px; " id="label11" name="label11"><label style=" line-height:21px; " >        Company Name         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:189px;top:89px; width:122px; height:19px; " id="label15" name="label15"><label style=" line-height:21px; " >        Organization Name         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:487px;top:92px; width:122px; height:19px; " id="label17" name="label17"><label style=" line-height:21px; " >        To         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:340px;top:92px; width:122px; height:19px; " id="label14" name="label14"><label style=" line-height:21px; " >        From         </label>        </div>
                	<select  class="selectbox" style="position:absolute;left:41px;top:112px; width:124px; height:21px; " id="S4_COMPANY_CD" name="S4_COMPANY_CD" tabindex="14" onchange="S4_COMPANY_CD_OnChange(this);" eltype="Combo" >
        	</select>
                	<select  class="selectbox" style="position:absolute;left:189px;top:112px; width:124px; height:21px; " id="S4_ORG_CD" name="S4_ORG_CD" tabindex="15" onclick="S4_ORG_CD_OnClick(this);" onchange="S4_ORG_CD_OnChange(this);" eltype="Combo" >
        	</select>
                	<select  class="selectbox" style="position:absolute;left:488px;top:114px; width:124px; height:21px; " id="S4_TO" name="S4_TO" tabindex="17" onclick="S4_TO_OnClick(this);" eltype="Combo" >
        	</select>
                	<input  class="btn3" style="position:absolute;left:629px;top:115px; width:52px; height:21px; " id="button4" name="button4" type="button" value="Copy" tabindex="18"  onclick="button4_OnClick(this);" ></input>
                	<select  class="selectbox" style="position:absolute;left:340px;top:115px; width:124px; height:21px; " id="S4_FROM" name="S4_FROM" tabindex="16" onmousedown="S4_FROM_OnMouseDown(this);" eltype="Combo" >
        	</select>
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
    var ComboObjProp= {"S1_DATA_TYPE":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S2_ORG_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S2_COMPANY_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S2_DATA_TYPE":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S3_TO":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S3_FROM":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S4_COMPANY_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S4_ORG_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S4_TO":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S4_FROM":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}};
    var RequiredProp= {};
    var CellEditoption= {};
    var InitItems= {"S1_DATA_TYPE":":", "S2_ORG_CD":":", "S2_COMPANY_CD":":", "S2_DATA_TYPE":":", "S3_TO":":", "S3_FROM":":", "S4_COMPANY_CD":":", "S4_ORG_CD":":", "S4_TO":":", "S4_FROM":":"};
    var sbmInit = new SubMission( frm, "/hpms","","","");
    var TitleSet = new SubMission( frm, "/hpms/2016082417899ForecastInfoMgr_ForecastTitleSet.do","S1[A]:S1","","");
    var PID_Confirm = new SubMission( frm, "/hpms/2016082417899ForecastInfoMgr_Forecast_PIDcheck.do","S3[A]:S1","C:SEL2","");
    var COMPANY_ORG_COMBO = new SubMission( frm, "/hpms/20160704154561CommonCodeMgr_Company_Org_cd.do","","S2_COMPANY_CD:SEL4,S4_COMPANY_CD:SEL4","");
    var TitleSet2 = new SubMission( frm, "/hpms/2016082417899ForecastInfoMgr_ForecastTitleSet2.do","S2[A]:S2","","");
    var ORG_combo_S2 = new SubMission( frm, "/hpms/20160704154561CommonCodeMgr_ORGAuthCombobox.do","S2[A]:S1","S2_ORG_CD:SEL1","");
    var ORG_combo_S4 = new SubMission( frm, "/hpms/20160704154561CommonCodeMgr_ORGAuthCombobox.do","S4[A]:S1","S4_ORG_CD:SEL1","");
    var DataCopy = new SubMission( frm, "/hpms/2016082417899ForecastInfoMgr_ForecastDataCopy.do","S3[A]:S3","","");
    var DataCopy2 = new SubMission( frm, "/hpms/2016082417899ForecastInfoMgr_ForecastDataCopy2.do","S4[A]:S4","","");
    var sbmCtlSave = new SubMission( frm, "/hpms/20161004133312Control_saveUsingCheck.do","","","");
    var PID_Confirm2 = new SubMission( frm, "/hpms/2016082417899ForecastInfoMgr_Forecast_PIDcheck.do","S1[A]:S1","C:SEL4","");
    var sbmCtlWrite = new SubMission( frm, "/hpms/20161004133312Control_MonthlyUsingCheck.do","","","");
    var ORG_Confirm = new SubMission( frm, "/hpms/2016082417899ForecastInfoMgr_Forecast_ORGCheck.do","S2[A]:S2","C:SEL4","");
    var ORG_Confirm2 = new SubMission( frm, "/hpms/2016082417899ForecastInfoMgr_Forecast_ORGCheck.do","S4[A]:S2","C:SEL2","");
    var FCST_Title_Combo = new SubMission( frm, "/hpms/2016082417899ForecastInfoMgr_FCST_TITLE_COMBO.do","S1[A]:S5","S1_DATA_TYPE:SEL2","");
    var FCST_Title_Combo2 = new SubMission( frm, "/hpms/20160901101143PLSheetOutput_initCombo.do","S3[A]:S2","S3_FROM:SEL2","");
    var FCST_TitleFrom_Combo = new SubMission( frm, "/hpms/2016082417899ForecastInfoMgr_FCSTCOPYCombo.do","S3[A]:S2","S3_FROM:SEL1","");
    var FCST_TitleTo_Combo = new SubMission( frm, "/hpms/2016082417899ForecastInfoMgr_FCST_TITLE_COMBO.do","S3[A]:S5","S3_TO:SEL4","");
    var FCST_Title_ORG_Combo = new SubMission( frm, "/hpms/2016082417899ForecastInfoMgr_FCST_TITLE_COMBO.do","S2[A]:S5","S2_DATA_TYPE:SEL6","");
    var FCST_Title_ORG_Combo_COPY = new SubMission( frm, "/hpms/2016082417899ForecastInfoMgr_FCST_TITLE_COMBO.do","S4[A]:S4","S4_TO:SEL8,S4_FROM:SEL9","");
    var PID_CHECK = new SubMission( frm, "/hpms/20160901101143PLSheetOutput_PIDCheck.do","S3[A]:S2","C:SEL3","");
    sbmInit.InitCombo("S1_DATA_TYPE,S2_ORG_CD,S2_COMPANY_CD,S2_DATA_TYPE,S3_TO,S3_FROM,S4_COMPANY_CD,S4_ORG_CD,S4_TO,S4_FROM");
    var DefalueGrid =[] ; 
    var initGrids = function() { 

    }


    $(document).ready(function () { 
        initGrids();
    });
</script>
</html>