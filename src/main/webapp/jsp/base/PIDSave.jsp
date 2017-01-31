<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>PID Numbering Screen</title>
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
              
          
             COMPANY_ORG.submit(false,"Seessionset");
            
               $(document).keydown(function(e){   
                   
                    if(e.target.nodeName != "INPUT" && e.target.nodeName != "TEXTAREA"){       
                    if(e.keyCode === 8){   
                    return false;
                     }
                    }
                  });
              });
             
        
        function Seessionset(){
             $("#S_REGIST_COMPANY_CD").val("${G.COMPANY_CD}"+"${G.COMPANY_ORG_CD}");
             $("#S_REGIST_ORG_CD").val("${G.ORG_CD}");
             $("#S_OWNER_COMPANY_CD").val("${G.COMPANY_CD}"+"${G.COMPANY_ORG_CD}");
             
             
             $("#REGIST_ORG_CD").val( $("#S_REGIST_ORG_CD :selected").text());
             $("#REGIST_COMPANY_CD").val( $("#S_REGIST_COMPANY_CD :selected ").text());
             
            Change_Combo.submit(false,"ORG_COMBO_SET");
        	
        }
        function ORG_COMBO_SET(){
        
        $("#S_OWNER_ORG_CD").val("${G.ORG_CD}");
        }
        function PIDSave(){
        //  
         if( $("#out_message").val() != "" )
            {
              ErrorCheck();
              return;
            }    
        /***** 
         Are you sure you want to Save?
        *****/
        	if($("#S_PID_STATUS_CD").val() == ""){
        	Return_RTN_MSG("00004" ,"PID Status","S_PID_STATUS_CD");
        	return;
        	}
        /*	if($("#S_RD_CATEGORY_CD").val() == ""){
        	Return_RTN_MSG("00004" ,"R&D Category","S_RD_CATEGORY_CD");
        	return;
        	}
        	if($("#S_RD_THEME").val() == ""){
        	Return_RTN_MSG("00003" ,"R&D Theme","S_RD_THEME");
        	return;
        	}
        	if($("#S_ITEM_NAME").val() == ""){
        	Return_RTN_MSG("00003" ,"Product","S_ITEM_NAME");
        	return;
        	}
        	if($("#S_NICKNAME").val() == ""){
        	Return_RTN_MSG("00003" ,"Nickname (For Diplay)","S_NICKNAME");
        	return;
        	}
        	if($("#S_NICKNAME_EXCL").val() == ""){
        	Return_RTN_MSG("00003" ,"Nickname (For Outside)","S_NICKNAME_EXCL");
        	return;
        	}
        	if($("#S_OWNER_COMPANY_CD").val() == ""){
        	Return_RTN_MSG("00004" ,"Owner Department","S_OWNER_COMPANY_CD");
        	return;
        	}
        
        	*/
            if($("#S_OWNER_ORG_CD").val() == ""){
        	Return_RTN_MSG("00004" ,"Owner Department","S_OWNER_ORG_CD");
        	return;
        	}
        	
            
        	if(confirm(confirm_MSG("0001"))){
        	 
           // $("#button3").attr("disabled",true);  
        		
        	//PIDAdd.submit(false,"complete_save");
        	complete_save();
        	}
        
        }
        function button3_OnClick(myObj)
        {
        
        
         sbmCtlSave.submit(false, "PIDSave");
        
        }
        
        function complete_save(){
        
         
        
        
        if($("#submitCNT").val() == 0){
        
         // alert( " submit call " );
          // FormSubmit("frm" , "/hpms/hpms.do?SYSID=HPMS&amp;amp;amp;amp;MENUID=2016082417883&amp;amp;amp;amp;EVENTID=CompletePage&amp;amp;amp;amp;PID="+PID , "" ,"frm",false); 
          var param = ""
              param += "?S_PID_STATUS_CD="+$("#S_PID_STATUS_CD").val()+"&S_RD_CATEGORY_CD="+$("#S_RD_CATEGORY_CD").val()+"&S_RD_THEME="+wizutil.JSONData_Char($("#S_RD_THEME").val());
              param +="&S_ITEM_NAME="+wizutil.JSONData_Char($("#S_ITEM_NAME").val())+"&S_NICKNAME="+wizutil.JSONData_Char($("#S_NICKNAME").val())+"&S_NICKNAME_EXCL="+wizutil.JSONData_Char($("#S_NICKNAME_EXCL").val());    
              param +="&S_OWNER_ORG_CD="+$("#S_OWNER_ORG_CD").val()+ "&S_OWNER_COMPANY_CD="+$("#S_OWNER_COMPANY_CD").val();
              param +="&S_REGIST_COMPANY_CD="+$("#S_REGIST_COMPANY_CD").val()+ "&S_REGIST_ORG_CD="+$("#S_REGIST_ORG_CD").val();
              param +="&OWNER_COMPANY="+wizutil.JSONData_Char($("#S_OWNER_COMPANY_CD :selected").text())+"&OWNER_ORG="+wizutil.JSONData_Char($("#S_OWNER_ORG_CD :selected").text());
              param +="&REG_COMPANY="+wizutil.JSONData_Char($("#REGIST_COMPANY_CD").val()) +" &REG_ORG="+wizutil.JSONData_Char($("#REGIST_ORG_CD").val());
             
          var url = "/hpms/2016082417883PidInfoMgr_CompletePage.do"+param;
          $("#submitCNT").val(1);
         }	 
        	 
        	   $("#mypage").attr("frameborder", 0);
        	//   url="";
        	   
        	   $("#divPopup_mypage").dialog({
        			autoOpen:false,
        			height : 530,
        			//position:[0,0],
        			width:530,
        			modal: true, 
        			title: "PID Save Result",
        			resizable: true,
        			closeOnEscape:true ,
        		 
        		close: function(){ 
                   reset();
        		//	window.location.reload();
        	
               $("#" + this.id).find('iframe').contents().find('body').html('');
              	}
              	});
              
               $("#mypage").attr("src",url);
        	   $("#divPopup_mypage").dialog("open"); 
           
          // $("#button3").attr("disabled",false);  
           	
        }
        function dialogClose_MYPAGE(){ //
        
        	$("#divPopup_mypage").dialog("close");
        
        	//window.location.reload(true);
        	reset();
        }
        
        function reset(){
          $('#S_PID_STATUS_CD').val("");
          $('#S_RD_CATEGORY_CD').val("");
          $('#S_RD_THEME').val("");
        	 $('#S_ITEM_NAME').val("");
        	 $('#S_NICKNAME').val("");
        	 $('#S_NICKNAME_EXCL').val("");
        	 //$('#S_OWNER_ORG_CD').val("");
         $("#submitCNT").val(0);
          
        
        }
        
        function S_OWNER_COMPANY_CD_OnChange(myObj)
        {
            owner_combo_Select.submit(false);
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
        			autoOpen:false,
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
           	}else{
              //오류업시 성공적으로 업로드 완료 했을 때    	
           	  alert("Upload complete");
           	}
        }
        
        function S_OWNER_ORG_CD_OnClick(myObj)
        {
          if($("#S_OWNER_COMPANY_CD").val() ==""){
                Return_RTN_MSG("00004","Owner Department","S_OWNER_COMPANY_CD");
                return;
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
        	<div  class="titleLabel" style=" position: absolute; top:22px; left:30px;  width:435px; height:34px; " id="label1" name="label1"><label style=" line-height:36px; " >    PID Numbering     </label>    </div>

        	<div   id="user2" style=" position: absolute; top:34px; left:682px;  width:100px; height:100px; display:none;" >
<!-- Error Code screen -->

<div id="divPopup_ErrorCode" name="divPopup_ErrorCode" style="display:none;background-color:white;">
	<iframe id="Error_cd" name="Error_cd" style="width:500px;height:190px;" scrolling="auto">
	</iframe>
</div></div>

      <div  id="group1" name="group1"  style=" background-color:White;border-width:1px;border-style:solid;border-color:DarkGray; position: absolute; top:74px; left:40px;  width:426px; height:361px; " >  
          <table  id="layout1" name="layout1" cellspacing='0px'  style=" background-color:White;border-width:0px;border-style:solid;border-color:Black; position: absolute; top:21px; left:47px;  width:340px; height:180px;  table-layout:fixed;" >  
        	<tr style="height:22px;">
        		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<div  class="infoLabel" style=" height:20px; " id="label2" name="label2"><label style=" line-height:16px; " >                PID                 </label>                </div>
        		</td>
        		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; height:20px; " id="PID" name="PID"><label style=" line-height:16px;  font-family:굴림체;font-weight:bold;font-size:9.75pt;" >                Automatic Numbering                 </label>                </div>
        		</td>
        	</tr>
        	<tr style="height:22px;">
        		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<div  class="infoLabel" style=" height:20px; " id="label3" name="label3"><label style=" line-height:16px; " >                PID Status                 </label>                </div>
        		</td>
        		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<select  class="selectbox" style=" width:181px; height:20px; " id="S_PID_STATUS_CD" name="S_PID_STATUS_CD" tabindex="1" eltype="Combo" >
                	</select>
        		</td>
        	</tr>
        	<tr style="height:22px;">
        		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<div  class="infoLabel" style=" height:20px; " id="label4" name="label4"><label style=" line-height:16px; " >                R&D Category                 </label>                </div>
        		</td>
        		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<select  class="selectbox" style=" width:181px; height:20px; " id="S_RD_CATEGORY_CD" name="S_RD_CATEGORY_CD" tabindex="2" eltype="Combo" >
                	</select>
        		</td>
        	</tr>
        	<tr style="height:22px;">
        		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<div  class="infoLabel" style=" height:20px; " id="label5" name="label5"><label style=" line-height:16px; " >                R&D Theme                 </label>                </div>
        		</td>
        		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<input  class="TextBox-S" style=" width:177px; height:16px; " id="S_RD_THEME" name="S_RD_THEME" type="text" maxlength="200" tabindex="3" >                </input>
        		</td>
        	</tr>
        	<tr style="height:22px;">
        		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<div  class="infoLabel" style=" height:20px; " id="label6" name="label6"><label style=" line-height:16px; " >                Product                 </label>                </div>
        		</td>
        		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<input  class="TextBox-S" style=" width:177px; height:16px; " id="S_ITEM_NAME" name="S_ITEM_NAME" type="text" maxlength="30" tabindex="4" >                </input>
        		</td>
        	</tr>
        	<tr style="height:22px;">
        		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<div  class="infoLabel" style=" height:20px; " id="label7" name="label7"><label style=" line-height:16px; " >                Nickname (For Display)                 </label>                </div>
        		</td>
        		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<input  class="TextBox-S" style=" width:177px; height:16px; " id="S_NICKNAME" name="S_NICKNAME" type="text" maxlength="100" tabindex="5" >                </input>
        		</td>
        	</tr>
        	<tr style="height:22px;">
        		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<div  class="infoLabel" style=" height:20px; " id="label8" name="label8"><label style=" line-height:16px; " >                Nickname (For Outside)                 </label>                </div>
        		</td>
        		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                                	<input  class="TextBox-S" style=" width:177px; height:16px; " id="S_NICKNAME_EXCL" name="S_NICKNAME_EXCL" type="text" maxlength="100" tabindex="6" >                </input>
        		</td>
        	</tr>
        	<tr style="height:22px;">
        		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
        		</td>
        		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
        		</td>
        	</tr>
          </table>                	<div  class="infoLabel" style="position:absolute;left:48px;top:204px; width:156px; height:43px; " id="label9" name="label9"><label style=" line-height:45px; " >        Owner Department         </label>        </div>
                	<select  class="selectbox" style="position:absolute;left:205px;top:205px; width:182px; height:21px; " id="S_OWNER_COMPANY_CD" name="S_OWNER_COMPANY_CD" tabindex="7" onchange="S_OWNER_COMPANY_CD_OnChange(this);" eltype="Combo" >
        	</select>
                	<select  class="selectbox" style="position:absolute;left:205px;top:228px; width:182px; height:21px; " id="S_OWNER_ORG_CD" name="S_OWNER_ORG_CD" tabindex="8" onclick="S_OWNER_ORG_CD_OnClick(this);" eltype="Combo" >
        	</select>
                	<input  class="TextBox-S" style="position:absolute;left:204px;top:258px; width:177px; height:17px; " id="REGIST_COMPANY_CD" name="REGIST_COMPANY_CD" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:47px;top:258px; width:156px; height:41px; " id="label10" name="label10"><label style=" line-height:43px; " >        Registrar Department         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:204px;top:281px; width:177px; height:17px; " id="REGIST_ORG_CD" name="REGIST_ORG_CD" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<input  class="btn3" style="position:absolute;left:323px;top:321px; width:63px; height:22px; " id="button3" name="button3" type="button" value="Save" tabindex="9"  onclick="button3_OnClick(this);" ></input>
      </div>
        	<input  class="TextBox-S" style=" position: absolute; top:287px; left:635px;  width:97px; height:17px; " id="submitCNT" name="submitCNT" type="hidden" value="0" maxlength="50" tabindex="0" >    </input>

        	<div   id="user1" style=" position: absolute; top:335px; left:545px;  width:424px; height:188px; display:none;" >
<div id="divPopup_mypage" name="divPopup_mypage" style="display:none;background-color:white;">
	<iframe id="mypage" name="mypage" style="width:500px;height:450px;" scrolling="auto">
	</iframe>
</div></div>

        	<select  class="TextBox-S" style=" position: absolute; top:449px; left:228px;  width:87px; height:21px; display:none;" id="S_REGIST_COMPANY_CD" name="S_REGIST_COMPANY_CD" tabindex="0" eltype="Combo" >
    	</select>

        	<select  class="selectbox" style=" position: absolute; top:478px; left:229px;  width:90px; height:21px; display:none;" id="S_REGIST_ORG_CD" name="S_REGIST_ORG_CD" tabindex="0" eltype="Combo" >
    	</select>

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
    var ComboObjProp= {"S_PID_STATUS_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S_RD_CATEGORY_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S_OWNER_COMPANY_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S_OWNER_ORG_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S_REGIST_COMPANY_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":""}, "S_REGIST_ORG_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":""}};
    var RequiredProp= {};
    var CellEditoption= {};
    var InitItems= {"S_PID_STATUS_CD":":", "S_RD_CATEGORY_CD":":", "S_OWNER_COMPANY_CD":":", "S_OWNER_ORG_CD":":", "S_REGIST_COMPANY_CD":":", "S_REGIST_ORG_CD":":"};
    var sbmInit = new SubMission( frm, "/hpms/20160704154561CommonCodeMgr_CommonCombo.do","","S_PID_STATUS_CD:SEL2,S_RD_CATEGORY_CD:SEL4","");
    var COMPANY_ORG = new SubMission( frm, "/hpms/20160704154561CommonCodeMgr_Company_Org_cd.do","","S_OWNER_COMPANY_CD:SEL4,S_REGIST_ORG_CD:SEL7,S_REGIST_COMPANY_CD:SEL5","");
    var Change_Combo = new SubMission( frm, "/hpms/20160704154561CommonCodeMgr_SessionCombo.do","","S_OWNER_ORG_CD:SEL1","");
    var owner_combo_Select = new SubMission( frm, "/hpms/20160704154561CommonCodeMgr_ORGCombobox.do","S[A]:S","S_OWNER_ORG_CD:SEL1","");
    var sbmCtlSave = new SubMission( frm, "/hpms/20161004133312Control_saveUsingCheck.do","","","");
    sbmInit.InitCombo("S_PID_STATUS_CD,S_RD_CATEGORY_CD,S_OWNER_COMPANY_CD,S_OWNER_ORG_CD,S_REGIST_COMPANY_CD,S_REGIST_ORG_CD");
    var DefalueGrid =[] ; 
    var initGrids = function() { 

    }


    $(document).ready(function () { 
        sbmInit.submit(false, "initGrids");
    });
        wizutil.setVisible("user2",false);
        wizutil.setVisible("user1",false);
</script>
</html>