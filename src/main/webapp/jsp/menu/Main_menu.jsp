<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<head>

        <title>MS1</title>
		<c:set var="context" value="${pageContext.request.contextPath}"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" type="text/css" href="${context}/wizware/css/zTreeStyle.css"/>
        <link rel="stylesheet" type="text/css" href="${context}/wizware/css/jquery-ui.min.css"/>
        <link rel="stylesheet" type="text/css" href="${context}/wizware/css/demo.css"/>
        <link rel="stylesheet" type="text/css" href="${context}/wizware/css/UI.WizGrid.css"/>
        <link rel="stylesheet" type="text/css" href="${context}/wizware/css/PBUIDefalut.css"/>
	    <link rel="stylesheet" type="text/css" href="${context}/css/scostStyle.css"/> 
        <script type="text/javascript" src="${context}/wizware/js/jquery-1.11.0.min.js"></script>
        <script type="text/javascript" src="${context}/wizware/js/grid.locale-en.js"></script>
        <script type="text/javascript" src="${context}/wizware/js/jquery-ui.js"></script>
        <script type="text/javascript" src="${context}/wizware/js/jquery.ztree.all-3.5.js"></script>
        <script type="text/javascript" src="${context}/wizware/js/HuskyEZCreator.js"></script>
        <script type="text/javascript" src="${context}/wizware/js/paging.js"></script>
        <script type="text/javascript" src="${context}/wizware/js/jquery.form.js"></script>
        <script type="text/javascript" src="${context}/wizware/js/Wizware_WizGrid.js"></script>
        <script type="text/javascript" src="${context}/wizware/js/WizGrid.js"></script>
        <script type="text/javascript" src="${context}/wizware/js/wizware.js"></script>
        <script> 
        function tabrefresh(myobj,url){
          //alert($(myobj).parent().attr('id'));
          var parentidx = $(myobj).parent().attr('id');
          var menuindex = parentidx.substring(3,parentidx.length);
         // alert(menuindex +" ## " +  parentidx.length);
         if( confirm("??? ?????? ?"))
          $("#menu"+menuindex).attr('src',url);
        }
        function tabClick(myobj){
          var inum = myobj.id.substring(3,4);
         
          menustatusx = 1;
           wizmenu.loadPage($('#'+myobj.id),inum);
           //  menutimerx = setTimeout( function() { if( menustatusx== 1) {  wizmenu.loadPage($('#'+myobj.id),inum); } }, 250);   

        }
        function tabDbClick(myobj,url){
         //alert(myobj);
         var inum = myobj.id.substring(3,4);
        
         //alert(inum+ " ## " + $('#menu'+preinum).attr('src'));
          wizmenu.removeTabMenuPage($('#'+myobj.id),inum);

          
        }
       /*?? ??? ?? ????? ??? DS01_LABEL  	parent.DS01_LABEL_onNodeDblClick("E","ID",{MENU_NM: "", URL: "/PBCloud/2016041114480FactoryCapacityMgr_loadPage.do"}) */
        function DS01_LABEL_onNodeDblClick(event, treeId, treeNode, getparam)
        {
			
		  var urlx = "";
		  var IcoId= treeNode.tId +"_ico";
          var Isicon1 = $("#"+IcoId).hasClass('button ico_close');
          var Isicon2 = $("#"+IcoId).hasClass('button ico_open');
		  var tabYNCheck = 0;
		  var currentTabIndex ;
			//console.log(treeNode.URL + " : " + treeNode.MENU_ID);
		  var menuURL = treeNode.URL;
		  //src=cst
		    
		if(treeNode.URL.indexOf("?") == -1)
		{
			menuURL = treeNode.URL +"?";
			
		}
	    
		urlx = "${context}"+menuURL.replace(/&amp;/gi, '&');
		//urlx = menuURL;
		//console.log(urlx);
		// console.log(urlx);   $("#menu"+i).attr('src').substring(48,55);
         
		  //tab ?? ??
        /*  $("iframe[name^='frame']").each(function(i){
			
			//  if(urlx == $("#menu"+i).attr('src')){
				var srcMenuID = $("#menu"+i).attr('src');
				var treeMenuId = treeNode.URL;
				if(treeMenuId.substring((treeMenuId.indexOf("src")+4),treeMenuId.length) == $("#menu"+i).attr('src').substring((srcMenuID.indexOf("src")+4),srcMenuID.indexOf("&"))){
				  tabYNCheck++;
				  //??? ? indext
				  currentTabIndex= i;
			  }
				
				
				
                
             });*/
          
          //?? ?? ?? ??? ??? ?? 
          if(Isicon1||Isicon2){
            return;
          }
		   $('#intro').hide();
          var tabcount =0;
          //? ?? 10? ?? 
        /*$("#tabmenu > li").each(function(i){
                  tabcount++;
             });
			 
		  	if(tabYNCheck == 1){
				
				// ?? 10??? 
				if(tabcount == 10){
					$("#DetabPage10").css('display','none');
					$("#tab"+currentTabIndex).click();
					  return;
				}else{
					$("#tab"+currentTabIndex).click();
					 $("#menu"+currentTabIndex).attr('src',urlx);
					  return;
					
				}
			
			  
		  }*/
          // ??? 10?? ?? ?? ??? ???? url ???? .
        /*  if(tabcount == 10){
            // 10 ??? ??? ??? ?? 
              $("#tabmenu > li").each(function(i){
                  $("#tabPage"+i).css('display','none');
             });
*/
		
		
            
			$("#DetabPage10").css('display','block');
		
            $("#Demenu10").attr('src',urlx);
			/*$('#Demenu10').load(function(){    // iframe? ?? load?? ??
				$(this).contents().find('body');
				});*/
         
			
          //    show_popup();
            
       /*  }else{
			  // ?? ? 0 ???
			  tabYNCheck = 0;
            wizmenu.addMenuTab(treeNode.MENU_NM, urlx,treeNode.MENU_ID, treeNode.P_MENU_ID );
            $("#DetabPage10").css('display','none');
          }
          */
       
        }
		
        // ?? ??
        function leftFrameMove(){
              var leftwidth = $('#mainlist').width();
			  
			  
             if(leftwidth == "1200"){
               
            $('#menulist').hide(100);
            $('#mainlist').css('left','13px');
            $('#mainlist').width('99%');
			

            $('#divbar').css('left','0px');
            $('#tablist').css('left','0px');
            $('#tablist').width('99%');
            }else{
                 
            $('#menulist').show(100);
            $('#mainlist').width('1200px');
            $('#mainlist').css('left','255px');
	     
            $('#divbar').css('left','244px');
            $('#tablist').css('left','242px');
            $('#tablist').width('1450px');
            }
            
        }
          //??? ???
        function show_popup()
        {
           $('._popup').show("fast");
        }
        
        function Enter_Check(){

         if($("#S4_TEXT_VAL").val() ==""){
            return;

         }
          if (event.keyCode == 13) {
              searchmenu.submit(false,"treeAll");
             
        }
          
        }
        //???? 
        function treeAll(){
          //alert(1);
          var zTree = $.fn.zTree.getZTreeObj("DS01_LABEL");
          zTree.expandAll(true);

        }

        //?? ?? ?? 
/*
      $(function(){
          var arrytext = "";
           
          var datax ='{ "paramwizware":[{ "inDsList":"S4",  "recordList":[{"TEXT_VAL": '+'"?"'+'} ] } ] }';
          var inputdatax = "&InWIzJsonParma="+datax;
         $("#S4_TEXT_VAL").autocomplete({
              //request.term <-- ???? ??? ?? ?? ? 
            source:  function( request, response ) {
?????????????$.ajax({
????????????????????type: 'post',
????????????????????url: "${context}/20160316093603MENULIST_autoSearch.do?",
????????????????????dataType: "json",
????????????????????data: '&InWIzJsonParma={ "paramwizware":[{ "inDsList":"S4",  "recordList":[{"TEXT_VAL": "'+ request.term+'"} ] } ] }',
????????????????????success: function(data) {
                    //???? json ??? response ? ??? ???? ??
                               var MSG = data["SEL2"];
                        response( 
                            $.map(MSG, function(item) {

                                return {
                                    label: item.LABEL
                                    
                                }
                            })
                        );??????????
????????????????????}
???????????????});
????????????},
????????//??? ?? ?????
????????minLength: 2,
????????select: function( event, ui ) {
????????????// ?? ??????? ?????? ??? ???? ?? ?????
????????}
????});
})
*/



//?????
function RefreshMenu(){
  if(confirm("?? ??? ?????? ?")){
	  
  sbmInit.submit(false);
  }
}
function RefreshMenu(){
 
	  
  sbmInit.submit(false);
  
}
function Mypage_move(obj){
	    
	
	
	
}
function dialogClose_MYPAGE(){ //????? 

	$("#divPopup_mypage").dialog("close");
	
}
function auth_substr(val , Si,Ei){
	
	return val.substr(Si,Ei);
	
}
function MenuCreate(obj){
	  var Menulength = obj.length;
	  var urlx= "";
	  var url= "";
	  var SessionAUTH = "${G.AUTH_CD}";
	  var AUTH_VAL ="";
	  var AUTH_VAL2 = "";
	  
	  for(var i=0; i<Menulength; i++)
	  {
	   //msg[datasetnm][i].MENU_ID;
	   if(SessionAUTH != "")
	   {
		
		 
		 //?? ??
		 AUTH_VAL = auth_substr(SessionAUTH,0,1)-1;
		 AUTH_VAL2 = auth_substr(obj[i].AUTH,AUTH_VAL,1);
		 
		 $("#MENU_ID_"+i).text(obj[i].MENU_NM);
			 url =  obj[i].URL;
			 urlx = Contextpath+url.replace(/&amp;/gi, '&');
		 $("#MENU_ID_"+i).attr('onclick','MenuOnclick("'+urlx+'",'+AUTH_VAL2+')');
		 
		 //??? ?? ??
		 if(AUTH_VAL2 == 0){
			 $("#MENU_ID_"+i).css({'color':'#B0C4DE' ,'opacity':'.8','cursor' : 'Default' });
			 
		 }
		 
		} 
	  }
	
}
function MenuOnclick(url,auth){
	
	var urlx =""
	if( auth == 1){
		urlx = url.replace(/&amp;/gi, '&');
		$("#tabPage0").css('display','block');
		$("#menu0").attr('src',urlx);
	}else{
	      alert("You do not have permission.");
	}
}
        </script>
     
</head>
<body id="MAIN_View"  >
    <div id="pbview" >
	
<form id='frm' name='frm' >
    <div id="hiddenvariable" style="display:none">
        <input style="display:none" id="out_message" name="out_message"  value="" ></input>
        <input style="display:none" id="out_code" name="out_code"  value="" ></input>
    </div>
	<!-- mypage ?? -->
	<div id="divPopup_mypage" name="divPopup_mypage" style="display:none;background-color:white;">
	<iframe id="mypage" name="mypage" style="width:370px;height:260px;" scrolling="auto">
	</iframe>
</div>
 <input type='hidden'  id="InWIzJsonParma" name="InWIzJsonParma"  value="" ></input>
    
      <div  id="top" name="top" style="border-width:1px;background-color:#75C4FF; color:Black;border-color:Black;text-align:left; position: absolute; top:0px; left:0px;  width:1450px; height:80px; " >
	
	        <div id="header">
			<div class="logo">
				<img src="${context}/jsp/menu/img/socionext.jpg" alt="N Systems" class="nsystems">
				<!--<img src="#" alt="" class="" >
				<a href="#"><img src="${context}/jsp/menu/img/scost.png" alt="S-COST"></a>  -->
			</div>
			<div class="btns">
				<span><a href="#"></a></span><span class="dot"><img src="${context}/jsp/menu/img/dot.png"></span><span class="user">${G.NE_USER}</span>
				<!--<a href="#"><img src="${context}/jsp/menu/img/btn-config.png" alt="????"></a> 
				<a href="#"><img src="${context}/jsp/menu/img/btn-mypage.png" alt="Mypage" onClick="Mypage_move(this)"></a>-->
				<a href="${context}/2016041114474loggout.do"><img src="${context}/jsp/menu/img/btn-logout.png" alt="Logout"></a>
			</div>
		</div>
	</div>
      <div id="divbar" name="divbar" onclick="leftFrameMove()" style="cursor:pointer;border-width:1px;text-align:left; position: absolute; top:81px; left:243px; width:15px; height:793px;" >
          <img src="${context}/jsp/menu/img/main/divide-handle.png" id="divbar_image" style="position: relative;top: 45%; ">
      </div>

          
          

      <div  id="menulist" name="menulist"  style=" background-color:Gold;border-width:1px;border-style:solid;border-color:white; position: absolute; top:80px; left:0px;  width:180px; height:793px; " >  
           <!-- ?? ?? ? 
           
            <img alt="??" src="${context}/wizware/css/img/menu/toolbar_find.png" style="z-index:10;background-color:white;width:18px;height:18px;position: absolute;top:5px; left:1px;" />
           <input   id="S4_TEXT_VAL" type="text" name="S4_TEXT_VAL"  onkeydown="JavaScript:Enter_Check();" style="position: absolute;top:0px; left:0px;  width:194px; height:28px;border-width:1px;border:none;text-indent:1.9em;line-height: 14px;" placeholder="Serach">
           <img alt="?? ???" src="${context}/wizware/css/img/menu/refresh.ico" style="background-color:white;width:21px;height:19px;position: absolute;top:5px; left:195px;" onclick="javascript:RefreshMenu();" />
           <input onclick="wizmenu.diplayMainTreeMenu('mainMenu');"  id="menu" type="button" name="menu" value="?" style="position: absolute;top:4px; left:218px;  width:25px;background-color:Gray;">
          -->
		   
			  <div id="mainMenu" style="z-index:7;position: absolute; top:-1px; left:0px;  width:242px; height:300px;" >
			  <!--<img alt="?? ???" src="${context}/wizware/css/img/menu/refresh.png" style="cursor:pointer;width:21px;height:19px;position: absolute;top:5px; left:195px;" onclick="javascript:RefreshMenu();" />-->
                <ul  style="border :solid 1px #6382C4;color:Black; overflow-y:auto; height:823px; " 
                     class="ztree" elType="menu" id="DS01_LABEL" name="DS01_LABEL">
					 
						<li><label id="MENU_ID_0" class="MenuStyle"></label></li>
						<li><label id="MENU_ID_1" class="MenuStyle"></label></li>
						<li><label id="MENU_ID_2" class="MenuStyle"></label></li>
						<li><label id="MENU_ID_3" class="MenuStyle"></label></li>
						<li><label id="MENU_ID_4" class="MenuStyle"></label></li>
						<li><label id="MENU_ID_5" class="MenuStyle"></label></li>
						<li><label id="MENU_ID_6" class="MenuStyle"></label></li>
						<li><label id="MENU_ID_7" class="MenuStyle"></label></li>
						<li><label id="MENU_ID_8" class="MenuStyle"></label></li>
						<li><label id="MENU_ID_9" class="MenuStyle"></label></li>
						<li><label id="MENU_ID_10" class="MenuStyle"></label></li>
											   
                </ul>
              </div> 

      </div>
	  
	   <div id="intro" style="position: absolute; top:93px;left:255px; width:1580px; height:800px;  ">
		<!-- <img src="${context}/jsp/menu/img/intro_4.jpg"  style=" width:100%;height:100%;"> -->
	  </div>
	  
      <div  id="mainlist" name="mainlist"  style=" position: absolute; top:83px; left:255px;  width:1200px; height:800px; " >  
              
               <div id="tabPage0" style="back-ground:blue;z-index:5;display:none;position: absolute; top:0px; left:0px;  width:99%; height:800px;  ">
              <iframe id="menu0" name="frame" height="800px" width="99%"   src="" >
              </iframe>
            </div>
            <div id="tabPage1"style="z-index:5;display:none;position: absolute; top:0px; left:0px;  width:99%; height:800px;  ">
             <iframe id="menu1" name="frame" height="800px" width="99%"   src="" >
              </iframe>
            </div>
            <div id="tabPage2" style="z-index:5;display:none;position: absolute; top:0px; left:0px;  width:99%; height:800px;  ">
              <iframe id="menu2" name="frame" height="800px"width="99%"   src="" >
              </iframe>
            </div>
            <div id="tabPage3" style="z-index:5;display:none;position: absolute; top:0px; left:0px;  width:99%; height:800px;  ">
                 <iframe id="menu3" name="frame" height="800px" width="99%"   src="" >
              </iframe>
            </div>
            <div id="tabPage4" style="z-index:5;display:none;position: absolute; top:0px; left:0px;   width:99%;  height:800px;  ">
                <iframe id="menu4" name="frame" height="800px" width="99%"   src="" >
              </iframe>
            </div>
           <div id="tabPage5"style="z-index:5;display:none;position: absolute; top:0px; left:0px;   width:99%; height:800px;  ">
               <iframe id="menu5" name="frame" height="800px" width="99%"   src="" >
              </iframe>
            </div>
            <div id="tabPage6" style="z-index:5;display:none;position: absolute; top:0px; left:0px;  width:99%; height:800px;  ">
               <iframe id="menu6" name="frame" height="800px" width="99%"   src="" >
              </iframe>
            </div>
            <div id="tabPage7" style="z-index:5;display:none;position: absolute; top:0px; left:0px;   width:99%;  height:800px;  ">
              <iframe id="menu7" name="frame" height="800px" width="99%"   src="" >
              </iframe>
            </div>
           <div id="tabPage8" style="z-index:5;display:none;position: absolute; top:0px; left:0px;   width:99%; height:800px;  ">
               <iframe id="menu8" name="frame" height="800px" width="99%"   src="" >
              </iframe>
            </div>
           <div id="tabPage9" style="z-index:5;display:none;position: absolute; top:0px; left:0px;  width:99%; height:800px;  ">
                <iframe id="menu9" name="frame" height="800px" width="99%"   src="" >
              </iframe>
            </div>  
            <!-- ??? ??? -->
            <div id="DetabPage10" style="z-index:5;display:none;position: absolute; top:0px; left:0px;  width:99%;  height:800px;  ">
                <iframe id="Demenu10"  height="800px" width="99%"   src="" >
              </iframe>
            </div> 

      </div>
        <div  id="bottom" name="bottom"  style=" background-color:#eeeeee; position: absolute; top:914px; left:0px;  width:1450px;height:45px; " > 
        </div>
        <div id="alertchk" style="background-color:#4C4C4C;position: absolute; top:915px; left:1540px;z-index:7;">
        
            <div class="_popup" style="height:15px;color:#FFFFFF; border:0px solid #FFFFFF;display:none;width:auto;font-size:12px;font-family:dotum;">
              MenuTab? ?? 10? ?? ?????.
            </div>
        
        </div>

      <div  id="tablist" name="tablist"  style="  position: absolute; top:885px; left:250px;  width:1200px; height:28px; " >  
         <div id="container">
            <ul id="tabmenu" class="tabs">
               
           </ul>
      </div>  

       <!--<table id="tab_menu_pageIndex"  style="background-color:white;border-width:1px;position:absolute;top:0px;left:0px;width:100px;height:27px; ">
                  <tr id="tab_menu_tr" ></tr>
       <table>-->
        
      </div>
	 
</form> 

    </div>
</body>
<script type="text/javascript" >
      var ChkNumberColumns= [];
    var RadioProp= {};
    var CheckProp= {};
    var ComboObjProp= {};
    var RequiredProp= {};
    var InitItems= {};
    var sbmInit = new SubMission( frm, "${context}/20160412101097MainMenu_loadPage.do","","DS01_LABEL:SEL1","");
   // var searchmenu = new SubMission( frm, "${context}/20160316093603MENULIST_TREE_MENU.do","S4[A]:S4","DS01_LABEL:SEL4","");
    sbmInit.InitCombo("");
     var setDS01_LABEL=
        {
            data:{
                key:{
                    name: "MENU_NM"
                },
                simpleData:{
                    enable: true,
                    idKey: "MENU_ID",
                    pIdKey: "P_MENU_ID",
                    rootPId:null
                }
            },
          callback:{
			  beforeExpand:treeAuth,
              onClick:DS01_LABEL_onNodeDblClick
			
          }
      };
      var nodeDS01_LABEL=[];
      function initGrids() { 
	  //?? ???
	 // $("#expandAllBtn").bind("click", {type:"expandAll"}, expandNode);
       // var treeObj = $.fn.zTree.getZTreeObj("DS01_LABEL");
		//treeObj.expandAll(true);
		
		
		// sbmInit.submit(false);
		//alert( $("#out_code").val());
	
		 //treeAuth(treeObj);
    }

	function treeAuth(){
		var obj = $.fn.zTree.getZTreeObj("DS01_LABEL");
		var sNodes = obj.getSelectedNodes();
		alert(sNodes);
		if(sNodes.length>0){
			
			
			var node = sNodes[0].getIndex();
			alert(sNodes.length +  "@@ " +  node);
		}
		
		
	}
    $(document).ready(function () { 
	  
	  // alert(${context});
	    isloadingCheck =true;
	
       /*  $(document).mousedown(function(e){
        $('._popup').each(function(){
       if( $(this).css('display') == 'block' )
        {
            var l_position = $(this).offset();
            l_position.right = parseInt(l_position.left) + ($(this).width());
            l_position.bottom = parseInt(l_position.top) + parseInt($(this).height());

 
            if( ( l_position.left <= e.pageX && e.pageX <= l_position.right )
                && ( l_position.top <= e.pageY && e.pageY <= l_position.bottom ) )
            {
                //alert( 'popup in click' );
            }
            else
            {
                //alert( 'popup out click' );
                $(this).hide("fast");
            }
        }
        });
		
		

		
		
}); */
   $(document).keydown(function(e){   
           
            if(e.keyCode === 8){   
            return false;
            }
          });
       //?? ?? ???
      //  $('#menu0').attr('src','${context}/jsp/menu/intro.jsp');
      //  $.fn.zTree.init($("#DS01_LABEL"), setDS01_LABEL, nodeDS01_LABEL);
        sbmInit.submit(false, "initGrids");
		
       
    });
</script>
</html>