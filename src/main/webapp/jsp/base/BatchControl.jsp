<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>Batch Control</title>
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
        $(document).ready(function(e) {
        
          $(document).keydown(function(e){   
                   
                   if(e.target.nodeName != "INPUT" && e.target.nodeName != "TEXTAREA"){       
                    if(e.keyCode === 8){   
                    return false;
                     }
                    }
                  });
        
        });
        
        function S2_DATA_TYPE_OnChange(myObj)
        {
            $("#S21_UPDATE_TIME").val("");
            $("#S21_UPDATE_USER_ID").val("");
            
        	sbmInfo.setInput("S2[A]:S");
        	sbmInfo.setOutput("S21:SEL2,S2:MRG11");
        	sbmInfo.submit(false);
        }
        function S4_DATA_TYPE_OnChange(myObj)
        {
        	$("#S41_UPDATE_TIME").val("");
            $("#S41_UPDATE_USER_ID").val("");
            
        	sbmInfo.setInput("S4[A]:S");
        	sbmInfo.setOutput("S41:SEL2");
        	sbmInfo.submit(false);
        }
        function S3_DATA_TYPE_OnChange(myObj)
        {
            $("#S31_UPDATE_TIME").val("");
            $("#S31_UPDATE_USER_ID").val("");
            
        	sbmInfo.setInput("S3[A]:S");
        	sbmInfo.setOutput("S31:SEL2");
        	sbmInfo.submit(false);
        }
        
        
        function btn_mgrbuild_OnClick(myObj)
        {
         if(  AUTH_Check()){
          	sbmCtlRead.submit(false, "mgrbuild");
           }else{
              Return_RTN_MSG("00020");
           }
        
        }
        function mgrbuild()
        {
          if( $("#out_message").val() != "" )
          {
              ErrorCheck();
              return;
          }
          if( $("#S1_DATA_TYPE").val() == "")
          {
             Return_RTN_MSG("00004","Data Type");
            return;
          }
        
          sbmMgrbuild.submit(false,"MgrMSG");
          
        }
        function MgrMSG(){
        
          //경영종합표 돌고 있으면 오류 메시지 표시시
          if( $("#out_message").val() != "" )
          {
              ErrorCheck();
              return;
          }
        }
        
        function btn_Middle_OnClick(myObj)
        {
          sbmCtlRead.submit(false, "submitMTP");
          //submitMTP();
        }
        function submitMTP()
        {
          if( $("#out_message").val() != "" )
          {
              ErrorCheck();
              return;
          }    
          if( $("#S4_YYYY").val() == "" )
           {
             Return_RTN_MSG("00034","Year");
             return;
           }
           
           if( $("#S4_DATA_TYPE").val() == "")
           {
              Return_RTN_MSG("00004","Data Type");
             return;
           }
           
          // fromyyymm = $("#S4_YYYY").val() +"03";
           //toyymm = ( parseInt($("#S4_YYYY").val())+ 1 ) +"04";
           
          // $("#S4_FROM_YYYYMM").val(fromyyymm);
          // $("#S4_TO_YYYYMM").val(toyymm);
           
           sbmMTP.submit(false);   
        }
        
        
        function btn_MP_OnClick(myObj)
        {
           
        
           sbmCtlRead.submit(false, "submitMP");
           //submitMP();
        }
        function submitMP()
        {
           var fromyyymm="";
           var toyymm="";
           if( $("#out_message").val() != "" )
           {
              ErrorCheck();
              return;
           }
           
           if( $("#S3_YYYY").val() == "")
           {
             Return_RTN_MSG("00003","Year");
             return;
           }
           
           if( $("#S3_DATA_TYPE").val() == "" )
           {
            Return_RTN_MSG("00004","Data Type");
             return;
           }
           
           
          // fromyyymm = $("#S3_YYYY").val() +"03";
          // toyymm = ( parseInt($("#S3_YYYY").val())+ 1 ) +"04";
           
           
           $("#S3_FROM_YYYYMM").val(fromyyymm);
           $("#S3_TO_YYYYMM").val(toyymm);
         
           sbmMP.submit(false);  
        }
        
        
        function btn_LMP_OnClick(myObj)
        {
           sbmCtlRead.submit(false, "LMP_ConfirmCheck");
           //submitLMP();
        }
        function LMP_ConfirmCheck(){
        
          
          if( $("#out_message").val() != "" )
           {
          
              ErrorCheck();
              return;
           }
        
          LMPConfirm.submit(false , "submitLMP" )
        }
        function submitLMP()
        {
         
           if( $("#out_message").val() != "" )
           {
              ErrorCheck();
              return;
           }
          
           if( $("#S2_YYYYMM").val()=="")
           {
             Return_RTN_MSG("00003","Year");
             return;
           }
           if( $("#S2_DATA_TYPE").val()=="")
           {
             Return_RTN_MSG("00004","Data Type");
             return;
           }
        
           sbmLMP.submit(false,"ReSearch");  
        }
        function ReSearch(){
        
        sbmInfo.setInput("S2[A]:S");
        	sbmInfo.setOutput("S21:SEL2,S2:MRG11");
        	sbmInfo.submit(false);
        }
        function btn_release_OnClick(myObj)
        {
          if(  AUTH_Check()){
           sbmCtlRead.submit(false, "release");
           }else{
              Return_RTN_MSG("00020");
           }
          
           //release();
        }
        function release()
        {
            if( $("#out_message").val() != "" )
            {
              ErrorCheck();
              return;
            }
          
            $("#S51_UPDATE_TIME").val("");
            $("#S51_UPDATE_USER_ID").val("");
            
            $("#S52_UPDATE_TIME").val("");
            $("#S52_UPDATE_USER_ID").val("");
        
            sbmRelease.submit(false, "setStatus");
        }
        
        
        function btn_stop_OnClick(myObj)
        {
           if(  AUTH_Check()){
           sbmCtlRead.submit(false, "stop");
           }else{
              Return_RTN_MSG("00020");
           }
           //stop();
        }
        function stop()
        {
           if( $("#out_message").val() != "" )
           {
              ErrorCheck();
              return;
           }
           
           $("#S51_UPDATE_TIME").val("");
           $("#S51_UPDATE_USER_ID").val("");
            
           $("#S52_UPDATE_TIME").val("");
           $("#S52_UPDATE_USER_ID").val("");
           
           
           sbmStop.submit(false, "setStatus");
        }
        
        function setYYYY()
        {
            var yyyy = $("#S6_YYYY").val();
            var yyyymm = $("#S6_YYYYMM").val();
            var mm = yyyymm.substr(4,2);
            if(mm == "01" || mm == "02" ||mm == "03"){
              //회계년도일때 -1 년도 처리 해준다 
            yyyy = ( parseInt(yyyy)- 1 );
            $("#S3_YYYY").val(yyyy);
            $("#S4_YYYY").val(yyyy);
            
            }else{
            
             $("#S3_YYYY").val(yyyy);
             $("#S4_YYYY").val(yyyy);
            
            }
            
           
            
             setStatus();
        }
        function setStatus()
        {
            if($("#S51_UPDATE_USER_ID").val() =="")
            {
               $("#lbl_status").html("<label id='open'> OPEN</label>");
               $("#open").css({'color':'black' ,'font-weight':'bold' ,'font-size':'36px' });
               $("#close").remove();
            }
            else
            {
           
               $("#lbl_status").html("<label id='close'> CLOSE</label>");
                $("#close").css({'color':'black' ,'font-weight':'bold' ,'font-size':'36px' });
                $("#open").remove();
            }  
        }
        
        
        
        function ErrorCheck()
        {
          if(ErroMsgDisplay() != 0) {
            
           var ERROR_CODE ="";
           ERROR_CODE= $("#out_message").val();
           
           if(ErroMsgDisplay() == 9999)
           {
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
                 
               $("#" + this.id).find('iframe').contents().find('body').html('');
              	}
              	});
        	   $("#divPopup_ErrorCode").dialog("open"); 
           	}
           	else
           	{
           	  alert("Upload complete");
           	}
          }
        	
          function dialogClose_MYPAGE()
          { //팝업종료및 
        	$("#divPopup_ErrorCode").dialog("close");
          }
        function AUTH_Check(){
             if("${G.AUTH_CD}" =="40" || "${G.AUTH_CD}" =="20" || "${G.AUTH_CD}" =="30"){
                 return true;
             }else{
             
              return false;
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
        	<div   id="user1" style="border-width:1px; border:solid; background-color:Transparent; color:Black;border-color:Transparent;text-align:left; position: absolute; top:15px; left:771px;  width:77px; height:77px; " >
<!-- Error Code screen -->
<div id="divPopup_ErrorCode" name="divPopup_ErrorCode" style="display:none;background-color:white;">
	<iframe id="Error_cd" name="Error_cd" style="width:500px;height:190px;" scrolling="auto">
	</iframe>
</div></div>

        	<div  class="titleLabel" style=" position: absolute; top:21px; left:31px;  width:435px; height:34px; " id="label1" name="label1"><label style=" line-height:36px; " >    Biz Admin Proc.     </label>    </div>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:66px; left:57px;  width:435px; height:34px; " id="label3" name="label3"><label style=" line-height:36px;  font-family:Microsoft Sans Serif;font-size:10pt;" >    Data Change Control     </label>    </div>

      <div  id="group3" name="group3"  style=" background-color:White;border-width:1px;border-style:solid;border-color:DarkGray; position: absolute; top:103px; left:76px;  width:745px; height:89px; " >  
                	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:center;position:absolute;left:40px;top:16px; width:52px; height:28px; " id="lbl_status" name="lbl_status"><label style=" line-height:30px;  font-family:Microsoft Sans Serif;font-weight:bold;font-size:18pt;" >        </label>        </div>
                	<input  style="border-width:0px;background-color:White;border-color:Black;text-align:left;color:Black;  font-family:Microsoft Sans Serif;font-size:10pt;position:absolute;left:593px;top:19px; width:117px; height:17px; " id="S51_UPDATE_USER_ID" name="S51_UPDATE_USER_ID" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<input  style="border-width:0px;background-color:White;border-color:Black;text-align:left;color:Black;  font-family:Microsoft Sans Serif;font-size:9.75pt;position:absolute;left:442px;top:19px; width:142px; height:17px; " id="S51_UPDATE_TIME" name="S51_UPDATE_TIME" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<input  class="btn2" style="position:absolute;left:218px;top:19px; width:200px; height:21px; " id="btn_stop" name="btn_stop" type="button" value="Plan/Output Data Change:Stop" tabindex="0"  onclick="btn_stop_OnClick(this);" ></input>
                	<input  style="border-width:0px;background-color:White;border-color:Black;text-align:left;color:Black;  font-family:Microsoft Sans Serif;font-size:10pt;position:absolute;left:592px;top:48px; width:117px; height:17px; " id="S52_UPDATE_USER_ID" name="S52_UPDATE_USER_ID" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<input  style="border-width:0px;background-color:White;border-color:Black;text-align:left;color:Black;  font-family:Microsoft Sans Serif;font-size:10pt;position:absolute;left:441px;top:48px; width:142px; height:17px; " id="S52_UPDATE_TIME" name="S52_UPDATE_TIME" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<input  class="btn2" style="position:absolute;left:218px;top:48px; width:200px; height:21px; " id="btn_release" name="btn_release" type="button" value="resume" tabindex="0"  onclick="btn_release_OnClick(this);" ></input>
      </div>
        	<input  class="TextBox-S" style=" position: absolute; top:135px; left:834px;  width:36px; height:17px; " id="S_CD" name="S_CD" type="hidden" readonly  value="UPLOAD" maxlength="50" tabindex="0"  onclick="S_CD_OnClick(this);">    </input>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:195px; left:56px;  width:435px; height:34px; " id="label2" name="label2"><label style=" line-height:36px;  font-family:Microsoft Sans Serif;font-size:9.75pt;" >    Comprehensive data management table creation     </label>    </div>

      <div  id="group4" name="group4"  style=" background-color:White;border-width:1px;border-style:solid;border-color:DarkGray; position: absolute; top:233px; left:76px;  width:747px; height:69px; " >  
                	<select  class="selectbox" style="position:absolute;left:19px;top:22px; width:102px; height:21px; " id="S1_DATA_TYPE" name="S1_DATA_TYPE" tabindex="0" eltype="Combo" >
        	</select>
                	<input  style="position:absolute;left:431px;top:23px; width:142px; height:17px; border-width:0px;background-color:White;border-color:White;text-align:center;color:Black;  font-family:Microsoft Sans Serif;font-size:10pt;" id="S61_VAL1" name="S61_VAL1" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<input  style="position:absolute;left:581px;top:23px; width:156px; height:17px; border-width:0px;background-color:White;border-color:White;text-align:center;color:Black;  font-family:Microsoft Sans Serif;font-size:10pt;" id="S61_VAL2" name="S61_VAL2" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<input  class="btn2" style="position:absolute;left:218px;top:23px; width:200px; height:21px; " id="btn_mgrbuild" name="btn_mgrbuild" type="button" value="Submit" tabindex="0"  onclick="btn_mgrbuild_OnClick(this);" ></input>
      </div>
        	<input  class="TextBox-S" style=" position: absolute; top:258px; left:835px;  width:36px; height:17px; " id="S1_CD" name="S1_CD" type="hidden" readonly  value="A01" maxlength="50" tabindex="0" >    </input>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:307px; left:56px;  width:435px; height:34px; " id="label4" name="label4"><label style=" line-height:36px;  font-family:Microsoft Sans Serif;font-size:10pt;" >    Management Comprehensive Data Build     </label>    </div>

      <div  id="group2" name="group2"  style=" background-color:White;border-width:1px;border-style:solid;border-color:DarkGray; position: absolute; top:346px; left:76px;  width:747px; height:80px; " >  
                	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left;position:absolute;left:129px;top:16px; width:79px; height:19px; " id="label6" name="label6"><label style=" line-height:21px;  font-family:Microsoft Sans Serif;font-size:9.75pt;" >        YYYYMM         </label>        </div>
                	<input  style="border-width:0px;background-color:White;border-color:Black;text-align:left;color:Black;  font-family:Microsoft Sans Serif;font-size:10pt;position:absolute;left:590px;top:43px; width:117px; height:17px; " id="S21_UPDATE_USER_ID" name="S21_UPDATE_USER_ID" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<input  style="border-width:0px;background-color:White;border-color:Black;text-align:left;color:Black;  font-family:Microsoft Sans Serif;font-size:10pt;position:absolute;left:439px;top:43px; width:142px; height:17px; " id="S21_UPDATE_TIME" name="S21_UPDATE_TIME" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<select  class="selectbox" style="position:absolute;left:19px;top:43px; width:104px; height:21px; " id="S2_DATA_TYPE" name="S2_DATA_TYPE" tabindex="0" onchange="S2_DATA_TYPE_OnChange(this);" eltype="Combo" >
        	</select>
                	<input  class="TextBox-S" style="position:absolute;left:129px;top:43px; width:78px; height:17px; " id="S2_YYYYMM" name="S2_YYYYMM" type="text" readonly  maxlength="6" tabindex="0" >        </input>
                	<input  class="btn2" style="position:absolute;left:218px;top:43px; width:200px; height:21px; " id="btn_LMP" name="btn_LMP" type="button" value="Data Build" tabindex="0"  onclick="btn_LMP_OnClick(this);" ></input>
      </div>
        	<input  class="TextBox-S" style=" position: absolute; top:385px; left:835px;  width:36px; height:17px; " id="S2_CD" name="S2_CD" type="hidden" readonly  value="A02" maxlength="50" tabindex="0" >    </input>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:441px; left:56px;  width:435px; height:34px; " id="label5" name="label5"><label style=" line-height:36px;  font-family:Microsoft Sans Serif;font-size:10pt;" >    Data Confirm     </label>    </div>

      <div  id="group1" name="group1"  style=" background-color:White;border-width:1px;border-style:solid;border-color:DarkGray; position: absolute; top:481px; left:76px;  width:748px; height:108px; " >  
                	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left;position:absolute;left:129px;top:17px; width:69px; height:19px; " id="label8" name="label8"><label style=" line-height:21px;  font-family:Microsoft Sans Serif;font-size:10pt;" >        FY_YYYY         </label>        </div>
                	<input  style="border-width:0px;background-color:White;border-color:Black;text-align:left;color:Black;  font-family:Microsoft Sans Serif;font-size:10pt;position:absolute;left:590px;top:43px; width:119px; height:17px; " id="S31_UPDATE_USER_ID" name="S31_UPDATE_USER_ID" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<input  style="border-width:0px;background-color:White;border-color:Black;text-align:left;color:Black;  font-family:Microsoft Sans Serif;font-size:10pt;position:absolute;left:439px;top:43px; width:144px; height:17px; " id="S31_UPDATE_TIME" name="S31_UPDATE_TIME" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:129px;top:43px; width:78px; height:17px; " id="S3_YYYY" name="S3_YYYY" type="text" maxlength="4" tabindex="0" >        </input>
                	<select  class="selectbox" style="position:absolute;left:19px;top:43px; width:106px; height:21px; " id="S3_DATA_TYPE" name="S3_DATA_TYPE" tabindex="0" onchange="S3_DATA_TYPE_OnChange(this);" eltype="Combo" >
        	</select>
                	<input  class="btn2" style="position:absolute;left:218px;top:44px; width:202px; height:21px; " id="btn_MP" name="btn_MP" type="button" value="MP Data Confirm" tabindex="0"  onclick="btn_MP_OnClick(this);" ></input>
                	<input  style="border-width:0px;background-color:White;border-color:Black;text-align:left;color:Black;  font-family:Microsoft Sans Serif;font-size:10pt;position:absolute;left:590px;top:73px; width:119px; height:17px; " id="S41_UPDATE_USER_ID" name="S41_UPDATE_USER_ID" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<input  style="border-width:0px;background-color:White;border-color:Black;text-align:left;color:Black;  font-family:Microsoft Sans Serif;font-size:10pt;position:absolute;left:439px;top:73px; width:144px; height:17px; " id="S41_UPDATE_TIME" name="S41_UPDATE_TIME" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<input  class="TextBox-S" style="position:absolute;left:129px;top:73px; width:78px; height:17px; " id="S4_YYYY" name="S4_YYYY" type="text" maxlength="4" tabindex="0" >        </input>
                	<select  class="selectbox" style="position:absolute;left:19px;top:73px; width:106px; height:21px; " id="S4_DATA_TYPE" name="S4_DATA_TYPE" tabindex="0" onchange="S4_DATA_TYPE_OnChange(this);" eltype="Combo" >
        	</select>
                	<input  class="btn2" style="position:absolute;left:217px;top:73px; width:202px; height:21px; " id="btn_Middle" name="btn_Middle" type="button" value="MTP Data Confirm" tabindex="0"  onclick="btn_Middle_OnClick(this);" ></input>
      </div>
        	<input  class="TextBox-S" style=" position: absolute; top:488px; left:835px;  width:36px; height:17px; " id="S6_YYYY" name="S6_YYYY" type="hidden" readonly  maxlength="50" tabindex="0" >    </input>

        	<input  class="TextBox-S" style=" position: absolute; top:520px; left:878px;  width:47px; height:17px; " id="S3_FROM_YYYYMM" name="S3_FROM_YYYYMM" type="hidden" readonly  maxlength="50" tabindex="0" >    </input>

        	<input  class="TextBox-S" style=" position: absolute; top:521px; left:835px;  width:36px; height:17px; " id="S3_CD" name="S3_CD" type="hidden" readonly  value="A03" maxlength="50" tabindex="0" >    </input>

        	<input  class="TextBox-S" style=" position: absolute; top:521px; left:932px;  width:51px; height:17px; " id="S3_TO_YYYYMM" name="S3_TO_YYYYMM" type="hidden" readonly  maxlength="50" tabindex="0" >    </input>

        	<input  class="TextBox-S" style=" position: absolute; top:554px; left:878px;  width:47px; height:17px; " id="S4_FROM_YYYYMM" name="S4_FROM_YYYYMM" type="text" readonly  maxlength="50" tabindex="0" >    </input>

        	<input  class="TextBox-S" style=" position: absolute; top:554px; left:932px;  width:51px; height:17px; " id="S4_TO_YYYYMM" name="S4_TO_YYYYMM" type="text" readonly  maxlength="50" tabindex="0" >    </input>

        	<input  class="TextBox-S" style=" position: absolute; top:554px; left:835px;  width:36px; height:17px; " id="S4_CD" name="S4_CD" type="hidden" readonly  value="A04" maxlength="50" tabindex="0" >    </input>

        	<input  class="TextBox-S" style=" position: absolute; top:595px; left:865px;  width:97px; height:17px; " id="S6_YYYYMM" name="S6_YYYYMM" type="hidden" maxlength="50" tabindex="0" >    </input>

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
    var ComboObjProp= {"S1_DATA_TYPE":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S2_DATA_TYPE":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S3_DATA_TYPE":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S4_DATA_TYPE":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}};
    var RequiredProp= {};
    var CellEditoption= {};
    var InitItems= {"S1_DATA_TYPE":":", "S2_DATA_TYPE":":", "S3_DATA_TYPE":":", "S4_DATA_TYPE":":"};
    var sbmInit = new SubMission( frm, "/hpms/20160912111170BatchControl_pageInit.do","","S1_DATA_TYPE:SEL2,S2_DATA_TYPE:SEL2,S3_DATA_TYPE:SEL2,S4_DATA_TYPE:SEL2,S2:SEL4,S51:SEL8,S52:SEL10,S6:SEL6,S61:SEL19","setYYYY");
    var sbmInfo = new SubMission( frm, "/hpms/20160912111170BatchControl_getConfirmInfo.do","S2[A]:S","S21:SEL2,S2:SEL4","");
    var sbmStop = new SubMission( frm, "/hpms/20160912111170BatchControl_writeStop.do","S[A]:S","S51:SEL5,S52:SEL4","");
    var sbmRelease = new SubMission( frm, "/hpms/20160912111170BatchControl_writeRelease.do","S[A]:S","S51:SEL3,S52:SEL2","");
    var sbmMgrbuild = new SubMission( frm, "/hpms/20160912111170BatchControl_managedatabuild.do","S[A]:S,S1[A]:S1","","");
    var sbmLMP = new SubMission( frm, "/hpms/20160912111170BatchControl_copyLMP.do","S2[A]:S2","S21:SEL8","");
    var sbmMP = new SubMission( frm, "/hpms/20160912111170BatchControl_copyMP.do","S3[A]:S3","S31:SEL3","");
    var sbmMTP = new SubMission( frm, "/hpms/20160912111170BatchControl_copyMiddle.do","S4[A]:S4","S41:SEL3","");
    var sbmCtlRead = new SubMission( frm, "/hpms/20161004133312Control_SearchUsingCheck.do","","","");
    var LMPConfirm = new SubMission( frm, "/hpms/20160912111170BatchControl_LMP_ConfirmCheck.do","","","");
    sbmInit.InitCombo("S1_DATA_TYPE,S2_DATA_TYPE,S3_DATA_TYPE,S4_DATA_TYPE");
    var DefalueGrid =[] ; 
    var initGrids = function() { 

    }


    $(document).ready(function () { 
        sbmInit.submit(false, "initGrids");
    });
        wizutil.setVisible("user1",false);
        wizutil.setVisible("S_CD",false);
        wizutil.setVisible("S1_CD",false);
        wizutil.setVisible("S2_CD",false);
        wizutil.setVisible("S6_YYYY",false);
        wizutil.setVisible("S3_CD",false);
        wizutil.setVisible("S4_FROM_YYYYMM",false);
        wizutil.setVisible("S4_TO_YYYYMM",false);
        wizutil.setVisible("S4_CD",false);
</script>
</html>