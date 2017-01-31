<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>PID List Screen</title>
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
              Company_cd.submit(false);
             $(document).keydown(function(e){   
                   if(e.target.nodeName != "INPUT" && e.target.nodeName != "TEXTAREA"){       
                    if(e.keyCode === 8){   
                    return false;
                     }
                    }
                  });
          // Org_cd.submit(false);
        }); 
        function button1_OnClick(myObj)
        {
          sbmCtlRead.submit(false , "PIDListSearch");
         
        }
        function PIDListSearch(){
        // 오류코드 메시지 
         if( $("#out_message").val() != "" )
            {
              ErrorCheck();
              return;
            } 
         var PID_val ,SUBPID_val ,status , category , theme , product , nickname , owner;
         PID_val = $("#S_PID").val();
         SUBPID_val = $("#S_SUB_PID").val();
         status = $("#S_PID_STATUS_CD").val();
         category = $("#S_RND_CATEGROY_CD").val();
         theme = $("#S_RND_THEME").val();
         product = $("#S_PRODUCT_CD").val();
         nickname = $("#S_NICKNAME_1").val();
         owner = $("#S_OWNER_DEPT_CD").val();
         
            /* if( PID_val== "" && SUBPID_val == "" && status == "Z" && category == "Z" && theme =="" && product == "" && nickname == "" && owner =="Z"){
             
              Return_RTN_MSG("00017");
              return;
              }*/
              
        	  /*if(PID_val != ""){
        	    //정규식 체크 
        		    if(PatternVal(PID_val,"PID") == -1){
        		      return;
        		    }
        	  
        	  }
        	   if(SUBPID_val != ""){
        	        if(PatternVal(PID_val,"SUB_PID") == -1){
        		      return;
        		    }
        	  
        	  }*/
         
         /*	if($("#S_RND_CATEGROY_CD").val() == "Z"){
        	Return_RTN_MSG("00004" ,"R&D Category","S_RND_CATEGROY_CD");
        	return;
        	}
        	if($("#S_RND_THEME").val() == ""){
        	Return_RTN_MSG("00003" ,"R&D Theme","S_RND_THEME");
        	return;
        	}*/
        
          PIDSearch.submit(false);
        
        }
        function S_OWNER_COMPANY_CD_OnChange(myObj)
        {
           Org_cd.submit(false);
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
        	<div  class="titleLabel" style=" position: absolute; top:22px; left:30px;  width:435px; height:34px; " id="label1" name="label1"><label style=" line-height:36px; " >    PID List     </label>    </div>

      <div  id="group1" name="group1"  style=" background-color:White;border-width:1px;border-style:solid;border-color:DarkGray; position: absolute; top:66px; left:36px;  width:394px; height:256px; " >  
                	<input  class="TextBox-S" style="position:absolute;left:165px;top:17px; width:138px; height:17px; " id="S_PID" name="S_PID" type="text" maxlength="6" tabindex="1" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:12px;top:17px; width:151px; height:19px; " id="label2" name="label2"><label style=" line-height:21px; " >        PID         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:165px;top:40px; width:138px; height:17px; " id="S_SUB_PID" name="S_SUB_PID" type="text" maxlength="6" tabindex="2" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:12px;top:40px; width:151px; height:19px; " id="label8" name="label8"><label style=" line-height:21px; " >        SubPID         </label>        </div>
                	<select  class="selectbox" style="position:absolute;left:165px;top:63px; width:143px; height:21px; " id="S_PID_STATUS_CD" name="S_PID_STATUS_CD" tabindex="3" eltype="Combo" >
        	</select>
                	<div  class="infoLabel" style="position:absolute;left:12px;top:63px; width:151px; height:19px; " id="label3" name="label3"><label style=" line-height:21px; " >        PID Status         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:12px;top:86px; width:151px; height:19px; " id="label4" name="label4"><label style=" line-height:21px; " >        R&D Category         </label>        </div>
                	<select  class="selectbox" style="position:absolute;left:165px;top:86px; width:143px; height:21px; " id="S_RD_CATEGORY_CD" name="S_RD_CATEGORY_CD" tabindex="4" eltype="Combo" >
        	</select>
                	<input  class="TextBox-S" style="position:absolute;left:165px;top:109px; width:138px; height:17px; " id="S_RD_THEME" name="S_RD_THEME" type="text" maxlength="80" tabindex="5" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:12px;top:109px; width:151px; height:19px; " id="label5" name="label5"><label style=" line-height:21px; " >        R&D Theme         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:165px;top:132px; width:138px; height:17px; " id="S_ITEM_NAME" name="S_ITEM_NAME" type="text" maxlength="30" tabindex="6" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:12px;top:132px; width:151px; height:19px; " id="label6" name="label6"><label style=" line-height:21px; " >        Product         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:165px;top:155px; width:138px; height:17px; " id="S_NICKNAME" name="S_NICKNAME" type="text" maxlength="50" tabindex="7" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:12px;top:155px; width:151px; height:19px; " id="label7" name="label7"><label style=" line-height:21px; " >        Nickname (For Display)         </label>        </div>
                	<select  class="selectbox" style="position:absolute;left:165px;top:178px; width:143px; height:21px; " id="S_OWNER_COMPANY_CD" name="S_OWNER_COMPANY_CD" tabindex="8" onchange="S_OWNER_COMPANY_CD_OnChange(this);" eltype="Combo" >
        	</select>
                	<div  class="infoLabel" style="position:absolute;left:12px;top:180px; width:151px; height:39px; " id="label9" name="label9"><label style=" line-height:41px; " >        Owner Department         </label>        </div>
                	<select  class="selectbox" style="position:absolute;left:165px;top:201px; width:143px; height:21px; " id="S_OWNER_ORG_CD" name="S_OWNER_ORG_CD" tabindex="9" onclick="S_OWNER_ORG_CD_OnClick(this);" eltype="Combo" >
        	</select>
                	<input  class="btn3" style="position:absolute;left:317px;top:224px; width:63px; height:22px; " id="button1" name="button1" type="button" value="Search" tabindex="10"  onclick="button1_OnClick(this);" ></input>
      </div>
        	<div   id="user1" style=" position: absolute; top:121px; left:724px;  width:100px; height:100px; display:none;" >
<!-- Error Code screen -->

<div id="divPopup_ErrorCode" name="divPopup_ErrorCode" style="display:none;background-color:white;">
	<iframe id="Error_cd" name="Error_cd" style="width:500px;height:190px;" scrolling="auto">
	</iframe>
</div></div>

        <div id="grid1_gdiv"  style=" position: absolute; top:346px; left:35px; width:1091px;height:440px;" >
		     <table id="grid1" elType='Grid' ></table>
	    </div>
	    <div id="gridhead_grid1"  style=" position: absolute; top:336px; left:35px; " >
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
    var GridRowHeight= {"grid1":"22",};
    var ChkNumberColumns= [];
    var RadioProp= {};
    var CheckProp= {};
    var ComboObjProp= {"S_PID_STATUS_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S_RD_CATEGORY_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S_OWNER_COMPANY_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}, "S_OWNER_ORG_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--Choice--"}};
    var RequiredProp= {};
    var CellEditoption= {};
    var InitItems= {"S_PID_STATUS_CD":":", "S_RD_CATEGORY_CD":":", "S_OWNER_COMPANY_CD":":", "S_OWNER_ORG_CD":":"};
    var sbmInit = new SubMission( frm, "/hpms/20160704154561CommonCodeMgr_CommonCombo.do","","S_PID_STATUS_CD:SEL2,S_RD_CATEGORY_CD:SEL4","");
    var PIDSearch = new SubMission( frm, "/hpms/20160902161145PidListMgr_PIDListinfo.do","S[A]:S","grid1:SEL2","");
    var Company_cd = new SubMission( frm, "/hpms/20160704154561CommonCodeMgr_Company_Org_cd.do","","S_OWNER_COMPANY_CD:SEL4","");
    var Org_cd = new SubMission( frm, "/hpms/20160704154561CommonCodeMgr_ORGCombobox.do","S[A]:S","S_OWNER_ORG_CD:SEL1","");
    var sbmCtlRead = new SubMission( frm, "/hpms/20161004133312Control_SearchUsingCheck.do","","","");
    sbmInit.InitCombo("S_PID_STATUS_CD,S_RD_CATEGORY_CD,S_OWNER_COMPANY_CD,S_OWNER_ORG_CD");
    var DefalueGrid =[], ColNm_grid1=[] , ColInfo_grid1=[] ; 
    var grid1= jQuery("#grid1");
    var grid1_Data = []; 
    function HeadLabel_grid1(){
	ColNm_grid1.unshift("ROWID","PID","SUB PID","PID Status","R&D Category","R&D Theme","Product","Nickname","Purpose","Owner Department","Registrar Department","Register Date");
	return ColNm_grid1;
	}
    function Columninfo_grid1(){
	ColInfo_grid1.unshift(
        	 {index: "id", name:"id", width:75, key:true, hidden:true }
        	 ,{index:"PID",name:"PID",width:58,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'PID'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"SUB_PID",name:"SUB_PID",width:58,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'SUB_PID'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"PID_STATUS_NM",name:"PID_STATUS_NM",width:109,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'PID_STATUS_NM'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"RD_CATEGORY_NM",name:"RD_CATEGORY_NM",width:158,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'RD_CATEGORY_NM'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"RD_THEME",name:"RD_THEME",width:91,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'RD_THEME'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"ITEM_NAME",name:"ITEM_NAME",width:80,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ITEM_NAME'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"NICKNAME",name:"NICKNAME",width:72,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'NICKNAME'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"APPLICATION",name:"APPLICATION",width:78,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'APPLICATION'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"OWNER_ORG_NM",name:"OWNER_ORG_NM",width:98,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'OWNER_ORG_NM'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"REGIST_ORG_NM",name:"REGIST_ORG_NM",width:92,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'REGIST_ORG_NM'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"REGIST_TIME",name:"REGIST_TIME",width:85,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'REGIST_TIME'  }  ] }  ,cellattr:rowspan,align:"center"}	
);
	return ColInfo_grid1;
	}

    var initGrids = function() { 
        grid1.wizGrid({   
        	data:grid1_Data,   
        	height:371,  
        	width:1091,    
        	rowNum:1000,    
        	cellEdit:false,   
        	Names: HeadLabel_grid1()
,        	Columns: Columninfo_grid1() ,
        	OnEditCell : function(rowid, colname, value, iCol,iRow) {  
        	}  
        	,OnEnterRow : function(rowindex, selrow) {  
        	}  
        	,OnLoadComplete : function() {  

	         var gridName ='grid1';

	         $("[colheight=grid1]").css('height',"28px");
        		 
        		 
        		 
        		 
        	}  
        });



    }


    $(document).ready(function () { 
        sbmInit.submit(false, "initGrids");
        grid1.setDatasetName("G1");        
    });
</script>
</html>