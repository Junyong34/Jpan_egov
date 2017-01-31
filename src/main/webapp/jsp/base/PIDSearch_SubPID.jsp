<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>PID Search Screen(subpid Numbering)</title>
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
             org_cd.submit(false);
               $(document).keydown(function(e){   
                   
                   if(e.target.nodeName != "INPUT" && e.target.nodeName != "TEXTAREA"){       
                    if(e.keyCode === 8){   
                    return false;
                     }
                    }
                  });
        }); 
        function button1_OnClick(myObj)
        {
          sbmCtlRead.submit(false, "PID_Search");
        
        }
        
        function PID_Search(){
        // 오류 체크 
         if( $("#out_message").val() != "" )
            {
              ErrorCheck();
              return;
            }  
          var PID_val = $("#S_PID").val();
        
         /* if(PID_val != ""){
             //정규식 체크 
             if(PatternVal(PID_val,"PID") == -1){
        		      return;
        		 }
          }*/
        
          PIDSearch.submit(false);
        
        }
        function grid1_B1_cellonclick_grid1(rowId)
        {
        
        	 var rowIndex=grid1.getRowIndex(rowId);
        
        	 var params = "?S1_PID="+ grid1.getCellValue(rowIndex, "PID", true) ;
        	 	params += "&S1_REGIST_ORG_CD="+ grid1.getCellValue(rowIndex, "REGIST_ORG_CD", true) ;
        	 	params += "&S1_RD_THEME="+ wizutil.JSONData_Char(grid1.getCellValue(rowIndex, "RD_THEME", true)) ;
               params += "&S1_PID_STATUS_CD="+ grid1.getCellValue(rowIndex, "PID_STATUS_CD", true) ;
            var url = "/hpms/20160912111171SubPidInfoMgr_SubPIDPopupPage.do" + params;
        //	var url = "/hpms/hpms.do?SYSID=HPMS&MENUID=2016082417883&EVENTID=loadPagePopup_sub" + params;
        	 
        	   $("#subPIDAdd").attr("frameborder", 0);
        	   $("#divPopup_subPIDadd").dialog({
        			autoPoen:false,
        			height : 480,
        			//position:[50,300],
        			width:520,
        			modal: true, 
        			title: "sub PID Add",
        			resizable: true,
        			closeOnEscape:true ,
        		 
        		close: function(){ 	
              		 $("#" + this.id).find('iframe').contents().find('body').html('');
              	}
              	});
               $("#subPIDAdd").attr("src",url);
        	   $("#divPopup_subPIDadd").dialog("open"); 
        	  
        }
        function dialogClose_subpid(){ //팝업종료및 
        
        	$("#divPopup_subPIDadd").dialog("close");
        	
        }
        
        function S_OWNER_COMPANY_CD_OnChange(myObj)
        {
           org_cd.submit(false);
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
                Return_RTN_MSG("00004","OWNER DepartmentY","S_OWNER_COMPANY_CD");
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
        	<div  class="titleLabel" style=" position: absolute; top:22px; left:30px;  width:435px; height:34px; " id="label1" name="label1"><label style=" line-height:36px; " >    SubPID Numbering     </label>    </div>

      <div  id="group1" name="group1"  style=" background-color:White;border-width:1px;border-style:solid;border-color:DarkGray; position: absolute; top:66px; left:36px;  width:456px; height:225px; " >  
                	<input  class="TextBox-S" style="position:absolute;left:171px;top:12px; width:174px; height:17px; " id="S_PID" name="S_PID" type="text" maxlength="6" tabindex="1" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:18px;top:12px; width:151px; height:19px; " id="label2" name="label2"><label style=" line-height:21px; " >        PID         </label>        </div>
                	<select  class="selectbox" style="position:absolute;left:171px;top:34px; width:179px; height:21px; " id="S_PID_STATUS_CD" name="S_PID_STATUS_CD" tabindex="2" eltype="Combo" >
        	</select>
                	<div  class="infoLabel" style="position:absolute;left:18px;top:35px; width:151px; height:19px; " id="label3" name="label3"><label style=" line-height:21px; " >        PID Status         </label>        </div>
                	<select  class="selectbox" style="position:absolute;left:171px;top:57px; width:179px; height:21px; " id="S_RD_CATEGORY_CD" name="S_RD_CATEGORY_CD" tabindex="3" eltype="Combo" >
        	</select>
                	<div  class="infoLabel" style="position:absolute;left:18px;top:58px; width:151px; height:19px; " id="label4" name="label4"><label style=" line-height:21px; " >        R&D Category         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:171px;top:81px; width:174px; height:17px; " id="S_RD_THEME" name="S_RD_THEME" type="text" maxlength="80" tabindex="4" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:18px;top:81px; width:151px; height:19px; " id="label5" name="label5"><label style=" line-height:21px; " >        R&D Theme         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:171px;top:104px; width:174px; height:17px; " id="S_ITEM_NAME" name="S_ITEM_NAME" type="text" maxlength="30" tabindex="5" >        </input>
                	<div  class="infoLabel" style="position:absolute;left:18px;top:104px; width:151px; height:19px; " id="label6" name="label6"><label style=" line-height:21px; " >        Product         </label>        </div>
                	<div  class="infoLabel" style="position:absolute;left:18px;top:127px; width:151px; height:19px; " id="label7" name="label7"><label style=" line-height:21px; " >        Nickname (For Display)         </label>        </div>
                	<input  class="TextBox-S" style="position:absolute;left:171px;top:127px; width:174px; height:17px; " id="S_NICKNAME" name="S_NICKNAME" type="text" maxlength="50" tabindex="6" >        </input>
                	<select  class="selectbox" style="position:absolute;left:171px;top:150px; width:179px; height:21px; " id="S_OWNER_COMPANY_CD" name="S_OWNER_COMPANY_CD" tabindex="8" onchange="S_OWNER_COMPANY_CD_OnChange(this);" eltype="Combo" >
        	</select>
                	<div  class="infoLabel" style="position:absolute;left:18px;top:161px; width:151px; height:25px; " id="label9" name="label9"><label style=" line-height:27px; " >        Owner Department         </label>        </div>
                	<input  class="btn3" style="position:absolute;left:376px;top:173px; width:63px; height:22px; " id="button1" name="button1" type="button" value="Search" tabindex="10"  onclick="button1_OnClick(this);" ></input>
                	<select  class="selectbox" style="position:absolute;left:171px;top:173px; width:179px; height:21px; " id="S_OWNER_ORG_CD" name="S_OWNER_ORG_CD" tabindex="9" onclick="S_OWNER_ORG_CD_OnClick(this);" eltype="Combo" >
        	</select>
      </div>
        	<div   id="user1" style="border-width:0px;  background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:171px; left:940px;  width:102px; height:87px; " >
<!-- sub PID 등록 -->
	<div id="divPopup_subPIDadd" name="divPopup_subPIDadd" style="display:none;background-color:white;">
	<iframe id="subPIDAdd" name="subPIDAdd" style="width:490px;height:400px;" scrolling="auto">
	</iframe>
</div></div>

        	<div   id="user2" style=" position: absolute; top:191px; left:843px;  width:69px; height:64px; display:none;" >
<!-- Error Code screen -->

<div id="divPopup_ErrorCode" name="divPopup_ErrorCode" style="display:none;background-color:white;">
	<iframe id="Error_cd" name="Error_cd" style="width:500px;height:190px;" scrolling="auto">
	</iframe>
</div></div>

        <div id="grid1_gdiv"  style=" position: absolute; top:314px; left:39px; width:991px;height:443px;" >
		     <table id="grid1" elType='Grid' ></table>
	    </div>
	    <div id="gridhead_grid1"  style=" position: absolute; top:304px; left:39px; " >
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
    var PIDSearch = new SubMission( frm, "/hpms/20160912111171SubPidInfoMgr_sub_PID_NumberSearch.do","S[A]:S","grid1:SEL2","");
    var SubPIDSearch = new SubMission( frm, "/hpms/20160912111171SubPidInfoMgr_Sub_PIDSearch.do","S1[A]:S1","grid2:SEL2","");
    var Company_cd = new SubMission( frm, "/hpms/20160704154561CommonCodeMgr_Company_Org_cd.do","","S_OWNER_COMPANY_CD:SEL4","");
    var org_cd = new SubMission( frm, "/hpms/20160704154561CommonCodeMgr_ORGCombobox.do","S[A]:S","S_OWNER_ORG_CD:SEL1","");
    var sbmCtlRead = new SubMission( frm, "/hpms/20161004133312Control_SearchUsingCheck.do","","","");
    sbmInit.InitCombo("S_PID_STATUS_CD,S_RD_CATEGORY_CD,S_OWNER_COMPANY_CD,S_OWNER_ORG_CD");
    var DefalueGrid =[], ColNm_grid1=[] , ColInfo_grid1=[] ; 
    var grid1= jQuery("#grid1");
    var grid1_Data = []; 
    function HeadLabel_grid1(){
	ColNm_grid1.unshift("ROWID","S","PID","PID Status","R&D Category","R&D Theme","Product","Nickname","Owner Department","Registrar Department","SubPID Numbering","PID_STATUS_CD","REGIST_ORG_CD ");
	return ColNm_grid1;
	}
    function Columninfo_grid1(){
	ColInfo_grid1.unshift(
        	 {index: "id", name:"id", width:75, key:true, hidden:true }        	, {index:'IUDFLAG',name:'IUDFLAG',editable:false,width:10,formatter:'select',edittype:'select',hidden:true,editoptions:{ value:':;I:I;U:U;D:D',defaultValue:'' }}
        	 ,{index:"PID",name:"PID",width:61,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'PID'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"PID_STATUS_NM",name:"PID_STATUS_NM",width:87,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'PID_STATUS_NM'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"RD_CATEGORY_NM",name:"RD_CATEGORY_NM",width:203,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'RD_CATEGORY_NM'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"RD_THEME",name:"RD_THEME",width:78,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'RD_THEME'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"ITEM_NAME",name:"ITEM_NAME",width:77,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'ITEM_NAME'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"NICKNAME",name:"NICKNAME",width:81,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'NICKNAME'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"OWNER_ORG_NM",name:"OWNER_ORG_NM",width:112,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'OWNER_ORG_NM'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"REGIST_ORG_NM",name:"REGIST_ORG_NM",width:111,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'REGIST_ORG_NM'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"grid1_B1",name:"grid1_B1",width:72,edittype:"text",formatter:"popup",editable:false,imagePath:"",imageStyle:"", editoptions:{ dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'grid1_B1'  }  ] } ,align:"center"}
        	 ,{index:"PID_STATUS_CD",name:"PID_STATUS_CD",width:100,hidden:true,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'PID_STATUS_CD'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"REGIST_ORG_CD",name:"REGIST_ORG_CD",width:100,hidden:true,edittype:"text",editable:true,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'REGIST_ORG_CD'  }  ] }  ,cellattr:rowspan,align:"center"}	
);
	return ColInfo_grid1;
	}

    var initGrids = function() { 
        grid1.wizGrid({   
        	data:grid1_Data,   
        	height:374,  
        	width:991,    
        	rowNum:1000,    
        	rownumbers:true,    
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
        		 
        		 
        		 
        		 
        	}  
        });



    }


    $(document).ready(function () { 
        sbmInit.submit(false, "initGrids");
        grid1.setDatasetName("G1");        
    });
        wizutil.setVisible("user1",false);
</script>
</html>