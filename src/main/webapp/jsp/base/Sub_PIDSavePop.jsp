<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>sub PID Numbering(Popup)</title>
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
        $(document).ready(function(){ 
           isloadingCheck =true;
             $("#S_PID").val( getParamdata('S1_PID'));
              $("#S_REGIST_ORG_CD").val( getParamdata('S1REGIST_ORG_CD'));
              $("#S_RD_THEME").val( wizutil.DecodeData(getParamdata('S1_RD_THEME')));
               $("#S_PID_STATUS_CD").val( getParamdata('S1_PID_STATUS_CD'));
             COMPANY_ORG.submit(false,"Seessionset");
             
              
               $(document).keydown(function(e){   
                if(e.target.nodeName != "INPUT" && e.target.nodeName != "TEXTAREA"){       
                    if(e.keyCode === 8){   
                    return false;
                    }
                }
            });
         
            window.history.forward(0);
        }); 
        
        function Seessionset(){
             
             $("#S_REGIST_COMPANY_CD").val("${G.COMPANY_CD}"+"${G.COMPANY_ORG_CD}");
             $("#S_REGIST_ORG_CD").val("${G.ORG_CD}");
            $("#S_OWNER_COMPANY_CD").val("${G.COMPANY_CD}"+"${G.COMPANY_ORG_CD}");
             // 텍스트 박스에 값 전달
             $("#REGIST_ORG_CD").val( $("#S_REGIST_ORG_CD :selected").text());
             $("#REGIST_COMPANY_CD").val( $("#S_REGIST_COMPANY_CD :selected ").text());
             
              Change_Combo.submit(false,"ORG_COMBO_SET");
        	
        }
        function ORG_COMBO_SET(){
        
        $("#S_OWNER_ORG_CD").val("${G.ORG_CD}");
        }
        function button3_OnClick(myObj)
        {
        	sbmCtlSave.submit(false , "SUB_PIDSave");
           
        }
        
        function SUB_PIDSave(){
          // 오류 체크 
            if( $("#out_message").val() != "" )
            {
              ErrorCheck();
              return;
            } 
            
        /*	if($("#S_APPLICATION").val() == ""){
        	Return_RTN_MSG("00003" ,"Purpose","S_APPLICATION");
        	return;
        	}*/
            if($("#S_OWNER_COMPANY_CD").val() == ""){
        	Return_RTN_MSG("00004" ,"Owner Department","S_OWNER_COMPANY_CD");
        	return;
        	}
        	if($("#S_OWNER_ORG_CD").val() == ""){
        	Return_RTN_MSG("00004" ,"Owner Department","S_OWNER_ORG_CD");
        	return;
        	}
        
        	if(confirm(confirm_MSG("0001"))){
        	
        
         var params = "/hpms/20160912111171SubPidInfoMgr_subPIDResult.do";
          params += "?S_PID="+$("#S_PID").val() + "&S_APPLICATION="+wizutil.JSONData_Char($("#S_APPLICATION").val());
          params += "&S_PID_STATUS_CD="+$("#S_PID_STATUS_CD").val();
          params += "&S_RD_THEME="+$("#S_RD_THEME").val();
          params +="&S_OWNER_ORG_CD="+$("#S_OWNER_ORG_CD").val()+ "&S_OWNER_COMPANY_CD="+$("#S_OWNER_COMPANY_CD").val();
          params +="&S_REGIST_COMPANY_CD="+$("#S_REGIST_COMPANY_CD").val()+ "&S_REGIST_ORG_CD="+$("#S_REGIST_ORG_CD").val();
          params +="&OWNER_COMPANY="+wizutil.JSONData_Char($("#S_OWNER_COMPANY_CD :selected").text())+"&OWNER_ORG="+wizutil.JSONData_Char($("#S_OWNER_ORG_CD :selected").text());
          params +="&REG_COMPANY="+wizutil.JSONData_Char($("#REGIST_COMPANY_CD").val()) +" &REG_ORG="+wizutil.JSONData_Char($("#REGIST_ORG_CD").val());
         
           parent.$("#subPIDAdd").attr('src',params);
           }
        
        
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
        
        function S_OWNER_ORG_CD_OnClick(myObj)
        {
          if($("#S_OWNER_COMPANY_CD").val() ==""){
                Return_RTN_MSG("00004","OWNER Department","S_OWNER_COMPANY_CD");
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
        	<div  class="titleLabel" style=" position: absolute; top:22px; left:30px;  width:435px; height:34px; " id="label1" name="label1"><label style=" line-height:36px; " >    Subpid Numbering Screen     </label>    </div>

      <div  id="group1" name="group1"  style=" background-color:White;border-width:1px;border-style:solid;border-color:DarkGray; position: absolute; top:86px; left:44px;  width:362px; height:296px; " >  
                	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left;position:absolute;left:155px;top:28px; width:179px; height:19px; " id="label6" name="label6"><label style=" line-height:21px;  font-family:굴림체;font-weight:bold;font-size:9.75pt;" >        Automatic Numbering         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:22px;top:28px; width:131px; height:19px; " id="label3" name="label3"><label style=" line-height:21px; " >        SUB_PID         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:22px;top:52px; width:131px; height:19px; " id="label2" name="label2"><label style=" line-height:21px; " >        PID         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:155px;top:52px; width:176px; height:17px; " id="S_PID" name="S_PID" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:22px;top:76px; width:131px; height:19px; " id="label9" name="label9"><label style=" line-height:21px; " >        R&D Theme         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:155px;top:76px; width:176px; height:17px; " id="S_RD_THEME" name="S_RD_THEME" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:22px;top:100px; width:131px; height:19px; " id="label8" name="label8"><label style=" line-height:21px; " >        Purpose         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:155px;top:100px; width:176px; height:17px; " id="S_APPLICATION" name="S_APPLICATION" type="text" maxlength="100" tabindex="1" >        </input>
                	<select  class="selectbox" style="position:absolute;left:157px;top:143px; width:181px; height:21px; " id="S_OWNER_COMPANY_CD" name="S_OWNER_COMPANY_CD" tabindex="2" onchange="S_OWNER_COMPANY_CD_OnChange(this);" eltype="Combo" >
        	</select>
                	<div  class="infoLabel" style="position:absolute;left:24px;top:149px; width:131px; height:35px; " id="label4" name="label4"><label style=" line-height:37px; " >        Owner Department         </label>        </div>
                	<select  class="selectbox" style="position:absolute;left:157px;top:166px; width:181px; height:21px; " id="S_OWNER_ORG_CD" name="S_OWNER_ORG_CD" tabindex="3" onclick="S_OWNER_ORG_CD_OnClick(this);" eltype="Combo" >
        	</select>
                	<input  class="TextBox-S" style="position:absolute;left:157px;top:189px; width:176px; height:17px; " id="REGIST_COMPANY_CD" name="REGIST_COMPANY_CD" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:24px;top:194px; width:131px; height:36px; " id="label5" name="label5"><label style=" line-height:38px; " >        Registrar Department         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:157px;top:213px; width:176px; height:17px; " id="REGIST_ORG_CD" name="REGIST_ORG_CD" type="text" readonly  maxlength="50" tabindex="0" >        </input>
                	<input  class="btn3" style="position:absolute;left:255px;top:250px; width:76px; height:22px; " id="button3" name="button3" type="button" value="Save" tabindex="4"  onclick="button3_OnClick(this);" ></input>
      </div>
        	<div   id="user1" style=" position: absolute; top:216px; left:723px;  width:100px; height:100px; display:none;" >
<!-- Error Code screen -->

<div id="divPopup_ErrorCode" name="divPopup_ErrorCode" style="display:none;background-color:white;">
	<iframe id="Error_cd" name="Error_cd" style="width:500px;height:190px;" scrolling="auto">
	</iframe>
</div></div>

        	<input  class="TextBox-S" style=" position: absolute; top:431px; left:332px;  width:97px; height:17px; " id="S_PID_STATUS_CD" name="S_PID_STATUS_CD" type="hidden" value="Z99" maxlength="50" tabindex="0" >    </input>

        	<select  style=" position: absolute; top:432px; left:208px;  width:102px; height:21px; display:none;" id="S_REGIST_COMPANY_CD" name="S_REGIST_COMPANY_CD" tabindex="0" eltype="Combo" >
    	</select>

        	<select  style=" position: absolute; top:459px; left:209px;  width:102px; height:21px; display:none;" id="S_REGIST_ORG_CD" name="S_REGIST_ORG_CD" tabindex="0" eltype="Combo" >
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
    var ComboObjProp= {"S_OWNER_COMPANY_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S_OWNER_ORG_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S_REGIST_COMPANY_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":""}, "S_REGIST_ORG_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":""}};
    var RequiredProp= {};
    var CellEditoption= {};
    var InitItems= {"S_OWNER_COMPANY_CD":":", "S_OWNER_ORG_CD":":", "S_REGIST_COMPANY_CD":":", "S_REGIST_ORG_CD":":"};
    var sbmInit = new SubMission( frm, "/hpms","","","");
    var COMPANY_ORG = new SubMission( frm, "/hpms/20160704154561CommonCodeMgr_Company_Org_cd.do","","S_OWNER_COMPANY_CD:SEL4,S_REGIST_COMPANY_CD:SEL5,S_REGIST_ORG_CD:SEL7","");
    var Change_Combo = new SubMission( frm, "/hpms/20160704154561CommonCodeMgr_SessionCombo.do","S[A]:S","S_OWNER_ORG_CD:SEL1","");
    var owner_combo_Select = new SubMission( frm, "/hpms/20160704154561CommonCodeMgr_ORGCombobox.do","S[A]:S","S_OWNER_ORG_CD:SEL1","");
    var sbmCtlSave = new SubMission( frm, "/hpms/20161004133312Control_saveUsingCheck.do","","","");
    sbmInit.InitCombo("S_OWNER_COMPANY_CD,S_OWNER_ORG_CD,S_REGIST_COMPANY_CD,S_REGIST_ORG_CD");
    var DefalueGrid =[] ; 
    var initGrids = function() { 

    }


    $(document).ready(function () { 
        initGrids();
    });
</script>
</html>