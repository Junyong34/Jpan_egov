<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
        <title>사업장마스터 관리</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/hpms/wizware/css/zTreeStyle.css"/>
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/hpms/wizware/css/jquery-ui.min.css"/>
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/hpms/wizware/css/demo.css"/>
        <link rel="stylesheet" id="JQUERY UI-J"  type="text/css" href="/hpms/wizware/css/PBUIDefalut.css"/>
        <link rel="stylesheet" id="wizcss"  type="text/css" href="/hpms/wizware/css/UI.WizGrid.css"/>
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
        $(document).ready(function(){ 
        
        }); 
        function button1_OnClick(myObj)
        {
          PIDSearch.submit(false);
        }
        function grid1_OnLoadComplete()
        {
        //그리드 전체 체크박스 숨기기기
         $('#cb_grid1').hide();
        }
        function button2_OnClick(myObj)
        {
        	//윈도우 팝업창 추가
        	 action.openWindow("/2016082417883PidInfoMgr_loadPagePopup.do" ,"" , "PID_ADD" , "width=400,height=400,scrollbars=yes,resize=no,toolbars=no" );
        }
        
        function grid1_OnCellSelect(rowIndex, Colname, cellcontent, e)
        {
            // 히든필드에 PID 코드값을 셋팅
           $("#S1_PID").val(grid1.getCellValue(rowIndex, "PID", true));
           
           SubPIDSearch.submit(false);
        }
        function button4_OnClick(myObj)
        {
           if($("#S1_PID").val() == ""){
            alert(" PID를 선택해야 추가 가능합니다 ");
           return;
           }
             //윈도우 팝업창 추가
        	 action.openWindow("/2016082417883PidInfoMgr_loadPagePopup_sub.do" ,"S1_PID="+$("#S1_PID").val() , "SUB_PID_ADD" , "width=400,height=400,scrollbars=yes,resize=no,toolbars=no" );
        }
        function grid2_OnLoadComplete()
        {
        //그리드 전체 체크박스 숨기기기
         $('#cb_grid2').hide();
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
        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:16px; left:35px;  width:107px; height:34px; " id="label1" name="label1"><label style=" line-height:36px;  font-family:굴림체;font-weight:bold;font-size:15.75pt;" >    PID 관리     </label>    </div>

        	<input  class="TextBox-S" style=" position: absolute; top:37px; left:908px;  width:97px; height:17px; " id="S1_PID" name="S1_PID" type="hidden" maxlength="50" tabindex="0" >    </input>

      <table  id="layout1" name="layout1" cellspacing='0px'  style=" background-color:White;border-width:0px;border-style:solid;border-color:Black; position: absolute; top:51px; left:36px;  width:340px; height:180px;  table-layout:fixed;" >  
    	<tr style="height:22px;">
    		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<div  style="border:solid; border-width:0px;background-color:Cornsilk; color:Black;border-color:Black;text-align:left; height:20px; " id="label2" name="label2"><label style=" line-height:16px;  font-family:굴림체;font-size:9.75pt;" >            PID             </label>            </div>
    		</td>
    		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<input  class="TextBox-S" style=" width:177px; height:16px; " id="S_PID" name="S_PID" type="text" maxlength="50" tabindex="0" >            </input>
    		</td>
    	</tr>
    	<tr style="height:22px;">
    		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<div  style="border:solid; border-width:0px;background-color:Cornsilk; color:Black;border-color:Black;text-align:left; height:20px; " id="label3" name="label3"><label style=" line-height:16px;  font-family:굴림체;font-size:9.75pt;" >            PID 상태             </label>            </div>
    		</td>
    		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<select  style="border-width:1px; background-color:White; color:Black;border-color:Black;text-align:left;  font-family:굴림체;font-size:9.75pt; width:181px; height:20px; " id="S_PID_STATUS_CD" name="S_PID_STATUS_CD" tabindex="0" eltype="Combo" >
            	</select>
    		</td>
    	</tr>
    	<tr style="height:22px;">
    		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<div  style="border:solid; border-width:0px;background-color:Cornsilk; color:Black;border-color:Black;text-align:left; height:20px; " id="label4" name="label4"><label style=" line-height:16px;  font-family:굴림체;font-size:9.75pt;" >            R_D Category             </label>            </div>
    		</td>
    		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<select  style="border-width:1px; background-color:White; color:Black;border-color:Black;text-align:left;  font-family:굴림체;font-size:9.75pt; width:181px; height:20px; " id="S_RND_CATEGROY_CD" name="S_RND_CATEGROY_CD" tabindex="0" eltype="Combo" >
            	</select>
    		</td>
    	</tr>
    	<tr style="height:22px;">
    		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<div  style="border:solid; border-width:0px;background-color:Cornsilk; color:Black;border-color:Black;text-align:left; height:20px; " id="label5" name="label5"><label style=" line-height:16px;  font-family:굴림체;font-size:9.75pt;" >            R_D Theme             </label>            </div>
    		</td>
    		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<input  class="TextBox-S" style=" width:177px; height:16px; " id="S_RND_THEME" name="S_RND_THEME" type="text" maxlength="50" tabindex="0" >            </input>
    		</td>
    	</tr>
    	<tr style="height:22px;">
    		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<div  style="border:solid; border-width:0px;background-color:Cornsilk; color:Black;border-color:Black;text-align:left; height:20px; " id="label6" name="label6"><label style=" line-height:16px;  font-family:굴림체;font-size:9.75pt;" >            Product             </label>            </div>
    		</td>
    		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<input  class="TextBox-S" style=" width:177px; height:16px; " id="S_PRODUCT_CD" name="S_PRODUCT_CD" type="text" maxlength="50" tabindex="0" >            </input>
    		</td>
    	</tr>
    	<tr style="height:22px;">
    		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<div  style="border:solid; border-width:0px;background-color:Cornsilk; color:Black;border-color:Black;text-align:left; height:20px; " id="label7" name="label7"><label style=" line-height:16px;  font-family:굴림체;font-size:9.75pt;" >            Nickname(표시용)             </label>            </div>
    		</td>
    		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<input  class="TextBox-S" style=" width:177px; height:16px; " id="S_NICKNAME_1" name="S_NICKNAME_1" type="text" maxlength="50" tabindex="0" >            </input>
    		</td>
    	</tr>
    	<tr style="height:22px;">
    		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<div  style="border:solid; border-width:0px;background-color:Cornsilk; color:Black;border-color:Black;text-align:left; height:20px; " id="label8" name="label8"><label style=" line-height:16px;  font-family:굴림체;font-size:9.75pt;" >            Nickname(사외용)             </label>            </div>
    		</td>
    		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<input  class="TextBox-S" style=" width:177px; height:16px; " id="S_NICKNAME_2" name="S_NICKNAME_2" type="text" maxlength="50" tabindex="0" >            </input>
    		</td>
    	</tr>
    	<tr style="height:22px;">
    		<td style="position:relative;width:155px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<div  style="border:solid; border-width:0px;background-color:Cornsilk; color:Black;border-color:Black;text-align:left; height:20px; " id="label9" name="label9"><label style=" line-height:16px;  font-family:굴림체;font-size:9.75pt;" >            오너부분             </label>            </div>
    		</td>
    		<td style="position:relative;width:181px;border-width:0px;border-color:Black;background-color:White;" class="tbllayout">
                        	<select  style="border-width:1px; background-color:White; color:Black;border-color:Black;text-align:left;  font-family:굴림체;font-size:9.75pt; width:181px; height:20px; " id="S_OWNER_DEPT_CD" name="S_OWNER_DEPT_CD" tabindex="0" eltype="Combo" >
            	</select>
    		</td>
    	</tr>
      </table>
        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:205px; left:936px;  width:63px; height:22px; " id="button1" name="button1" type="button" value="검색" tabindex="0"  onclick="button1_OnClick(this);" ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:241px; left:935px;  width:63px; height:22px; " id="button3" name="button3" type="button" value="저장" tabindex="0"  ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:242px; left:866px;  width:63px; height:22px; " id="button2" name="button2" type="button" value="추가" tabindex="0"  onclick="button2_OnClick(this);" ></input>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:248px; left:39px;  width:100px; height:19px; " id="label10" name="label10"><label style=" line-height:21px;  font-family:굴림체;font-weight:bold;font-size:9.75pt;" >    PID     </label>    </div>

        <div id="grid1_gdiv"  style=" position: absolute; top:279px; left:32px; width:991px;height:246px;" >
		     <table id="grid1" elType='Grid' ></table>
	    </div>

        	<div  style="border:solid; border-width:0px;background-color:Transparent; color:Black;border-color:Black;text-align:left; position: absolute; top:533px; left:38px;  width:100px; height:19px; " id="label11" name="label11"><label style=" line-height:21px;  font-family:굴림체;font-weight:bold;font-size:9.75pt;" >    Sub PID     </label>    </div>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:535px; left:758px;  width:63px; height:22px; " id="button5" name="button5" type="button" value="저장" tabindex="0"  ></input>

        	<input  style="border-width:1px;background-color:Gray; color:Black;border-color:#000000;text-align:center;  font-family:굴림체;font-size:9.75pt; position: absolute; top:535px; left:691px;  width:63px; height:22px; " id="button4" name="button4" type="button" value="추가" tabindex="0"  onclick="button4_OnClick(this);" ></input>

        <div id="grid2_gdiv"  style=" position: absolute; top:564px; left:33px; width:792px;height:153px;" >
		     <table id="grid2" elType='Grid' ></table>
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
    var GridRowHeight= {"grid1":"22","grid2":"22",};
    var ChkNumberColumns= [];
    var RadioProp= {};
    var CheckProp= {};
    var ComboObjProp= {"S_PID_STATUS_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--선택--"}, "S_RND_CATEGROY_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--선택--"}, "S_OWNER_DEPT_CD":{"CODE":"ITEM_CD","NAME":"ITEM_NM","DEFAULT":"--선택--"}};
    var RequiredProp= {};
    var CellEditoption= {};
    var InitItems= {"S_PID_STATUS_CD":":", "S_RND_CATEGROY_CD":":", "S_OWNER_DEPT_CD":":"};
    var sbmInit = new SubMission( frm, "/hpms/hpms.do?SYSID=HPMS&MENUID=2016082417883&EVENTID=MasterComboList","","S_PID_STATUS_CD:SEL2,S_RND_CATEGROY_CD:SEL4,S_OWNER_DEPT_CD:SEL6","");
    var PIDSearch = new SubMission( frm, "/hpms/hpms.do?SYSID=HPMS&MENUID=2016082417883&EVENTID=PIDSearch","S[A]:S","grid1:SEL2","");
    var SubPIDSearch = new SubMission( frm, "/hpms/hpms.do?SYSID=HPMS&MENUID=2016082417883&EVENTID=Sub_PIDSearch","S1[A]:S1","grid2:SEL2","");
    sbmInit.InitCombo("S_PID_STATUS_CD,S_RND_CATEGROY_CD,S_OWNER_DEPT_CD");
    var DefalueGrid =[], ColNm_grid1=[] , ColInfo_grid1=[], ColNm_grid2=[] , ColInfo_grid2=[] ; 
    var grid1= jQuery("#grid1");
    var grid2= jQuery("#grid2");
    var grid1_Data = []; 
    function HeadLabel_grid1(){
	ColNm_grid1.unshift("ROWID","S","PID","PID 상태","RnD Category","RnD 테마","Product","Nickname","오너부분","등록부분");
	return ColNm_grid1;
	}
    function Columninfo_grid1(){
	ColInfo_grid1.unshift(
        	 {index: "id", name:"id", width:75, key:true, hidden:true }        	, {index:'IUDFLAG',name:'IUDFLAG',editable:false,width:10,formatter:'select',edittype:'select',hidden:true,editoptions:{ value:':;I:I;U:U;D:D',defaultValue:'' }}
        	 ,{index:"PID",name:"PID",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'PID'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"PID_STATUS_NM",name:"PID_STATUS_NM",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'PID_STATUS_NM'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"RND_CATEGROY_NM",name:"RND_CATEGROY_NM",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'RND_CATEGROY_NM'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"RND_THEME",name:"RND_THEME",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'RND_THEME'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"PRODUCT_CD",name:"PRODUCT_CD",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'PRODUCT_CD'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"NICKNAME_1",name:"NICKNAME_1",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'NICKNAME_1'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"OWNER_DEPT_NM",name:"OWNER_DEPT_NM",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'OWNER_DEPT_NM'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"REG_DEPT_CD",name:"REG_DEPT_CD",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid1 ,Colname:'REG_DEPT_CD'  }  ] }  ,cellattr:rowspan,align:"center"}	
);
	return ColInfo_grid1;
	}

    var grid2_Data = []; 
    function HeadLabel_grid2(){
	ColNm_grid2.unshift("ROWID","S","SUB PID","용도","이용부서","PID");
	return ColNm_grid2;
	}
    function Columninfo_grid2(){
	ColInfo_grid2.unshift(
        	 {index: "id", name:"id", width:75, key:true, hidden:true }        	, {index:'IUDFLAG',name:'IUDFLAG',editable:false,width:10,formatter:'select',edittype:'select',editoptions:{ value:':;I:I;U:U;D:D',defaultValue:'' }}
        	 ,{index:"SUB_PID",name:"SUB_PID",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'SUB_PID'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"SUB_PID_USE",name:"SUB_PID_USE",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'SUB_PID_USE'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"USE_DEPT_NM",name:"USE_DEPT_NM",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'USE_DEPT_NM'  }  ] }  ,cellattr:rowspan,align:"center"}
        	 ,{index:"PID",name:"PID",width:100,edittype:"text",editable:false,GRound:"True",Gtype:"text", editoptions:{  maxlength:20, dataEvents: [  {  type: 'keydown', grid:grid2 ,Colname:'PID'  }  ] }  ,cellattr:rowspan,align:"center"}	
);
	return ColInfo_grid2;
	}

    var initGrids = function() { 
        grid1.wizGrid({   
        	data:grid1_Data,   
        	height:207,  
        	width:991,    
        	rownumbers:true,    
        	multiselect:true,       
        	cellEdit:true,   
        	Names: HeadLabel_grid1()
,        	Columns: Columninfo_grid1() ,
        	OnCellSelect : function(rowid, iCol, cellcontent, e) {  
        	 var rowIndex = grid1.getRowIndex(rowid);  
        	 var Colname =  grid1.getColumnName(iCol);  
        		grid1_OnCellSelect(rowIndex, Colname, cellcontent, e);  
        	}  
        	,OnEditCell : function(rowid, colname, value, iCol,iRow) {  
        	}  
        	,OnEnterRow : function(rowindex, selrow) {  
        	}  
        	,OnLoadComplete : function() {  
        		grid1_OnLoadComplete();  

	         var gridName ='grid1';

	         $("[colheight=grid1]").css('height',"22px");
        		 $("#paginate_grid1").hide();
        		 
        		 
        		 
        		 
        	}  
        });


        grid2.wizGrid({   
        	data:grid2_Data,   
        	height:114,  
        	width:792,    
        	rownumbers:true,    
        	multiselect:true,       
        	cellEdit:true,   
        	Names: HeadLabel_grid2()
,        	Columns: Columninfo_grid2() ,
        	OnEditCell : function(rowid, colname, value, iCol,iRow) {  
        	}  
        	,OnEnterRow : function(rowindex, selrow) {  
        	}  
        	,OnLoadComplete : function() {  
        		grid2_OnLoadComplete();  

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