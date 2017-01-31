   /********************************************************************
   *   제작        : (주)위즈웨어 
   *   제작 배포일 : 2012. 02. 01
   *   작성자      : 기술연구소 김지석 [ email : k2star7@hanmail.net ]
   *   사용용도    : 물질정보 사용자화면 제어공통
   *   기능        : 일반공통 , jquery action 공통 제어
   *   homepage    : www.processbuilder.co.kr
   *********************************************************************/
    (function(t){"use strict";function e(t,e,r){return t.addEventListener?t.addEventListener(e,r,!1):t.attachEvent?t.attachEvent("on"+e,r):void 0}function r(t,e){var r,n;for(r=0,n=t.length;n>r;r++)if(t[r]===e)return!0;return!1}function n(t,e){var r;t.createTextRange?(r=t.createTextRange(),r.move("character",e),r.select()):t.selectionStart&&(t.focus(),t.setSelectionRange(e,e))}function a(t,e){try{return t.type=e,!0}catch(r){return!1}}t.Placeholders={Utils:{addEventListener:e,inArray:r,moveCaret:n,changeType:a}}})(this),function(t){"use strict";function e(){}function r(){try{return document.activeElement}catch(t){}}function n(t,e){var r,n,a=!!e&&t.value!==e,u=t.value===t.getAttribute(V);return(a||u)&&"true"===t.getAttribute(D)?(t.removeAttribute(D),t.value=t.value.replace(t.getAttribute(V),""),t.className=t.className.replace(R,""),n=t.getAttribute(F),parseInt(n,10)>=0&&(t.setAttribute("maxLength",n),t.removeAttribute(F)),r=t.getAttribute(P),r&&(t.type=r),!0):!1}function a(t){var e,r,n=t.getAttribute(V);return""===t.value&&n?(t.setAttribute(D,"true"),t.value=n,t.className+=" "+I,r=t.getAttribute(F),r||(t.setAttribute(F,t.maxLength),t.removeAttribute("maxLength")),e=t.getAttribute(P),e?t.type="text":"password"===t.type&&M.changeType(t,"text")&&t.setAttribute(P,"password"),!0):!1}function u(t,e){var r,n,a,u,i,l,o;if(t&&t.getAttribute(V))e(t);else for(a=t?t.getElementsByTagName("input"):b,u=t?t.getElementsByTagName("textarea"):f,r=a?a.length:0,n=u?u.length:0,o=0,l=r+n;l>o;o++)i=r>o?a[o]:u[o-r],e(i)}function i(t){u(t,n)}function l(t){u(t,a)}function o(t){return function(){m&&t.value===t.getAttribute(V)&&"true"===t.getAttribute(D)?M.moveCaret(t,0):n(t)}}function c(t){return function(){a(t)}}function s(t){return function(e){return A=t.value,"true"===t.getAttribute(D)&&A===t.getAttribute(V)&&M.inArray(C,e.keyCode)?(e.preventDefault&&e.preventDefault(),!1):void 0}}function d(t){return function(){n(t,A),""===t.value&&(t.blur(),M.moveCaret(t,0))}}function g(t){return function(){t===r()&&t.value===t.getAttribute(V)&&"true"===t.getAttribute(D)&&M.moveCaret(t,0)}}function v(t){return function(){i(t)}}function p(t){t.form&&(T=t.form,"string"==typeof T&&(T=document.getElementById(T)),T.getAttribute(U)||(M.addEventListener(T,"submit",v(T)),T.setAttribute(U,"true"))),M.addEventListener(t,"focus",o(t)),M.addEventListener(t,"blur",c(t)),m&&(M.addEventListener(t,"keydown",s(t)),M.addEventListener(t,"keyup",d(t)),M.addEventListener(t,"click",g(t))),t.setAttribute(j,"true"),t.setAttribute(V,x),(m||t!==r())&&a(t)}var b,f,m,h,A,y,E,x,L,T,N,S,w,B=["text","search","url","tel","email","password","number","textarea"],C=[27,33,34,35,36,37,38,39,40,8,46],k="#ccc",I="placeholdersjs",R=RegExp("(?:^|\\s)"+I+"(?!\\S)"),V="data-placeholder-value",D="data-placeholder-active",P="data-placeholder-type",U="data-placeholder-submit",j="data-placeholder-bound",q="data-placeholder-focus",z="data-placeholder-live",F="data-placeholder-maxlength",G=document.createElement("input"),H=document.getElementsByTagName("head")[0],J=document.documentElement,K=t.Placeholders,M=K.Utils;if(K.nativeSupport=void 0!==G.placeholder,!K.nativeSupport){for(b=document.getElementsByTagName("input"),f=document.getElementsByTagName("textarea"),m="false"===J.getAttribute(q),h="false"!==J.getAttribute(z),y=document.createElement("style"),y.type="text/css",E=document.createTextNode("."+I+" { color:"+k+"; }"),y.styleSheet?y.styleSheet.cssText=E.nodeValue:y.appendChild(E),H.insertBefore(y,H.firstChild),w=0,S=b.length+f.length;S>w;w++)N=b.length>w?b[w]:f[w-b.length],x=N.attributes.placeholder,x&&(x=x.nodeValue,x&&M.inArray(B,N.type)&&p(N));L=setInterval(function(){for(w=0,S=b.length+f.length;S>w;w++)N=b.length>w?b[w]:f[w-b.length],x=N.attributes.placeholder,x?(x=x.nodeValue,x&&M.inArray(B,N.type)&&(N.getAttribute(j)||p(N),(x!==N.getAttribute(V)||"password"===N.type&&!N.getAttribute(P))&&("password"===N.type&&!N.getAttribute(P)&&M.changeType(N,"text")&&N.setAttribute(P,"password"),N.value===N.getAttribute(V)&&(N.value=x),N.setAttribute(V,x)))):N.getAttribute(D)&&(n(N),N.removeAttribute(V));h||clearInterval(L)},100)}M.addEventListener(t,"beforeunload",function(){K.disable()}),K.disable=K.nativeSupport?e:i,K.enable=K.nativeSupport?e:l}(this);
  /*
  * 공통 변수 영역
  */
  var enableOnBlur = true;
  var DATE_SEPERATOR="-";

  var color = 
  {
      trBackGroundOver:function trBackGroundOver(object)
			{
				if(jQuery("#hddnSelectRow").val()==object.id)
				{
				}
				else
				{
					jQuery("#"+object.id).css("background","#FFBB77");
				}
			}
			,
			trBackGroundOut:function trBackGroundOut(object)
			{
				if(jQuery("#hddnSelectRow").val()==object.id){
				}else{
					jQuery("#"+object.id).css("background","#FFF");
				}
			}
  }
  
  var tree =
  {
      branch:function branch(object, contextPath)
			{
				//현재 a 태그이용해서 레벨표시 이미지등으로 변경시 노드 뎁스는다시 확인필요 ?N?¿? 
				var oClickedRow = object.parentNode.parentNode;
				var sClickedRowId = oClickedRow.id;
				var sPMImgName = jQuery("#"+sClickedRowId).attr("view");
				var iClickedRowLevel = parseInt(oClickedRow.getAttribute("levelInx"));
				var oTableL = document.getElementById("codeList");
				var oTBodyL = oTableL.tBodies[0];
				var sDisplay = "";
				
				//선택된값이 현재 펼쳐져있는지 접혀있는지 여부
				if("open"==sPMImgName)
				{
					sDisplay = "";
					jQuery("#"+sClickedRowId).attr("view","close");
					jQuery("#"+sClickedRowId+" TD img").each(function(){
						if("icnHeadImg"== jQuery(this).attr("id")){
							jQuery(this).attr("src",contextPath+"/image/madams/ko/IcnPlus.gif");
						}else if("icnFolderImg"== jQuery(this).attr("id")){
							jQuery(this).attr("src",contextPath+"/image/madams/ko/IcnFolderClosed.png");
						}
					});
				}
				else
				{
					sDisplay = "none";
					jQuery("#"+sClickedRowId).attr("view","open");
					jQuery("#"+sClickedRowId+" TD img").each(function()
					{
						if("icnHeadImg"== jQuery(this).attr("id"))
						{
							jQuery(this).attr("src",contextPath+"/image/madams/ko/IcnMinus.gif");
						}
						else if("icnFolderImg"== jQuery(this).attr("id"))
						{
							jQuery(this).attr("src",contextPath+"/image/madams/ko/IcnFolderOpen.png");
						}
					});
				}
				var status ="";
				
				//자기자신은 제외한 하위레벨 노드들에 대해서 트리접기
				for (var i=oClickedRow.rowIndex-2; i<oTBodyL.rows.length; i++)
				{
					if(parseInt(oTBodyL.rows[oClickedRow.rowIndex-2].getAttribute("levelInx")) >= 
						parseInt(oTBodyL.rows[oClickedRow.rowIndex-1].getAttribute("levelInx"))){
						status="";
						break;
					}
					if ((parseInt(oTBodyL.rows[i].getAttribute("levelInx")) > iClickedRowLevel))
					{
						status ="start";
						if (sDisplay == "")
						{
							jQuery("#"+oTBodyL.rows[i].id).css("display","none");
						}
						else
						{
							jQuery("#"+oTBodyL.rows[i].id).css("display","");
						}
					}
					//하위노드들을 검색하다 현재 레벨보다 작거나 같은 레벨이 나오면 정지
					if("start"==status && (parseInt(oTBodyL.rows[i].getAttribute("levelInx")) <= iClickedRowLevel)){
						status="";
						break;
					}
				}
			},
			branchType2:function branchType2(object, contextPath)
			{
				//현재 a 태그이용해서 레벨표시 이미지등으로 변경시 노드 뎁스는다시 확인필요 ?N?¿? 
				var oClickedRow = object.parentNode.parentNode;
				var sClickedRowId = oClickedRow.id;
				var sPMImgName = jQuery("#"+sClickedRowId).attr("view");
				var iClickedRowLevel = parseInt(oClickedRow.getAttribute("levelInx"));
				var oTableL = document.getElementById("codeList");
				var oTBodyL = oTableL.tBodies[0];
				var sDisplay = "";
				
				//선택된값이 현재 펼쳐져있는지 접혀있는지 여부
				if("open"==sPMImgName)
				{
					sDisplay = "";
					jQuery("#"+sClickedRowId).attr("view","close");
					jQuery("#"+sClickedRowId+" TD img").each(function(){
						if("icnHeadImg"== jQuery(this).attr("id")){
							jQuery(this).attr("src",contextPath+"/image/madams/ko/IcnPlus.gif");
						}else if("icnFolderImg"== jQuery(this).attr("id")){
							jQuery(this).attr("src",contextPath+"/image/madams/ko/IcoMaterial.gif");
						}
					});
				}
				else
				{
					sDisplay = "none";
					jQuery("#"+sClickedRowId).attr("view","open");
					jQuery("#"+sClickedRowId+" TD img").each(function()
					{
						if("icnHeadImg"== jQuery(this).attr("id"))
						{
							jQuery(this).attr("src",contextPath+"/image/madams/ko/IcnMinus.gif");
						}
						else if("icnFolderImg"== jQuery(this).attr("id"))
						{
							jQuery(this).attr("src",contextPath+"/image/madams/ko/IcoMaterial.gif");
						}
					});
				}
				var status ="";
				
				//자기자신은 제외한 하위레벨 노드들에 대해서 트리접기
				for (var i=oClickedRow.rowIndex-2; i<oTBodyL.rows.length; i++)
				{
					if(parseInt(oTBodyL.rows[oClickedRow.rowIndex-2].getAttribute("levelInx")) >= 
						parseInt(oTBodyL.rows[oClickedRow.rowIndex-1].getAttribute("levelInx"))){
						status="";
						break;
					}
					if ((parseInt(oTBodyL.rows[i].getAttribute("levelInx")) > iClickedRowLevel))
					{
						status ="start";
						if (sDisplay == "")
						{
							jQuery("#"+oTBodyL.rows[i].id).css("display","none");
						}
						else
						{
							jQuery("#"+oTBodyL.rows[i].id).css("display","");
						}
					}
					//하위노드들을 검색하다 현재 레벨보다 작거나 같은 레벨이 나오면 정지
					if("start"==status && (parseInt(oTBodyL.rows[i].getAttribute("levelInx")) <= iClickedRowLevel)){
						status="";
						break;
					}
				}
			}
  }
  
  
  var menux =
  {
      showTop:function showTop(sArgMenuId)
			{
				var menuCount = jQuery(".TmList ul").length;
				for (var i=1; i<=menuCount; i++)
				{
		    	if(i == sArgMenuId)
		    	{
		    		document.getElementById("Layer"+i).style.display = "block";
		   		}
		   		else
		   		{
		    		document.getElementById("Layer"+i).style.display = "none";
		    	}
			  }
			}
			,
			hideSub:function hideSub()
			{
				var menuCount = jQuery(".TmList ul").length;
				for (var i=1; i<=menuCount; i++)
				{
					document.getElementById("Layer"+i).style.display = "none";
				}
			}
			,
			goMenu:function goMenu(url) 
			{ 
          action.submitGet("frm_menu", url );
			} 
			,
			goHome:function goHome()
			{
			  alert("gohome");
			}
  }
  
  /**
  * value의 Check 기능 (length, 및 특수값, format 등
  */
  var chk =
  {
    eqLength:function equLength(obj, len, msg)   //길이와 다르면 오류
    {
      if(obj.value.length == len)
      {
        return true;
      }
      chk.alertFocus(obj, msg);
      return false;      
    }
    ,
    gtLength:function gtLength(obj, len, msg)   //길이보다 크면 오류
    {
      if(obj.value.length > len)
      {
        chk.alertFocus(obj, msg); 
        return false;
      }
      return true;
    }
    ,
    ltLength:function ltLength(obj, len, msg)   //길이보다 작다으면 오류
    {
      if(obj.value.length < len)
      {
        chk.alertFocus(obj, msg); 
        return false;
      }
      return true;
    }
    ,
    isNumber:function ltLength(obj, msg)   //
    {
       var chars = ".0123456789";
       if(util.containsCharsOnly(obj,chars) == false)
       {
         chk.alertFocus(obj, msg); 
         return false;
       }
       return true;
    }
    ,
    alertFocus:function alertFocus(obj, message )  //check 발생시 해당 object에 대한 Message와 Focus처리를 한다.
  	{
  		 enableOnBlur = false;
  	   if ( message != '') alert( message );
  	   obj.focus();
  	   if (obj.type == 'text' && obj.value.length >=1 ) obj.select();
  		 enableOnBlur = true;
  	}
  	,
  	returnCode:function returnCode(code, message ) 
  	{
  	  if(code < 0)
  	  {
  	    alert(message);
  	  }
  	  else
  	  {
  	  }
  	}
  	
  }
  
  /*
  *
  */
 
  //
  //MADS
  //
  
  
  /*
  * 사용자 Operation에서 key-foucs의 이동처리 기능
  */
  var cursor =
  {
      test:function test()
      {
        alert("test");
      }
      ,
     	autoUpMoveField:function autoUpMoveField(obj,frm)
      {
      	if (!event) return;
      	for ( var i=0; i<frm.elements.length; i++) 
      	{
      		var ele = frm.elements[i];
      		if(ele == obj)
      		{
      			for (var j=(i-1); j>0; j-- ) 
      			{
      				if( frm.elements[j].disabled == false )
      				 {
      					 frm.elements[j].focus();
      					 return;
      				 }
      			 }
      		 }
      	 }
      }

  }
  
  /**
  *  기타 utility 기능 
  */
  var util =
  {
     isDummyKeyCode:function isDummyKeyCode() 
  	 {
  		  if (!event) return false;	
  		  var keyCode = event.keyCode;
  		  return 
  		  (
    			(keyCode == 8) ||	//BACK
    			(keyCode == 9) ||	//TAB
    			(keyCode == 16) ||	//SHIFT
    			(keyCode == 17) ||	//CTL
    			(keyCode == 18) ||	//ALT
    			(keyCode == 46) ||	//DEL
    			(keyCode == 37) ||	//←
    			(keyCode == 38) ||	//↑
    			(keyCode == 39) ||	//→
    			(keyCode == 40) ||	//↓
    			(keyCode == 107) ||	// +
    			(keyCode == 13)	||	// Enter
    			(keyCode == 36) ||	// HOME
    			(keyCode == 35)		// END
  			 );
  	  }
  	  ,
    	isNumComma:function isNumComma(obj) 
    	{
          var chars = ",.0123456789";
          return util.containsCharsOnly(obj,chars);
      }
      ,
      containsCharsOnly:function containsCharsOnly(obj,chars) 
      {
          var pointcnt=0;
          var objvalue =  obj.value;
          objvalue = util.removeComma(objvalue);
          for (var inx = 0; inx < objvalue.length; inx++) 
          {
             if(objvalue.charAt(inx)=='.') pointcnt++;
             if (chars.indexOf(objvalue.charAt(inx)) == -1)
             {
                 return false;
             }
          }
          if(pointcnt > 1) return false;
          return true;
      }
      ,
      chkzero:function chkzero(obj) 
      {
    		var numstr_b = obj.value;
    		numstr = util.removeComma(numstr_b)
    		
    		if (numstr.length >= 2) 
    		{
    			if (numstr.substr(0,1) == 0 && numstr.indexOf(".") == -1) 
    			{
    				alert("맨처음에는 0 이 올수 없습니다.");
    				obj.focus();
    				if (obj.type == 'text' && obj.value.length >=1 ) obj.select();
    				return;
    			}
    			else
    			{
    				return true;
    			}
    		}
      }
      ,
      removeObjComma:function removeObjComma(obj)
      {
         var str = obj.value;
         obj.value =str.replace(/,/gi,"");
      }
      ,
      removeComma:function removeComma(str) 
      {
         return str.replace(/,/gi,"");
      }
      ,
      isEmpty:function isEmpty(obj) 
      {
    	    if (obj.value == null || obj.value.replace(/ /gi,"") == "") {
    	        return true;
    	    }
    	    return false;
    	}
    	,
    	removePeriod:function removePeriod(str) 
    	{
      	 str = util.replaceStr(str, '.', '/');
  		   str = util.replaceStr(str, '-', '/');
         return util.replaceStr(str, '/', '');
      }
      ,
    	replaceStr:function replaceStr(str, find, replace)
    	{
    	    var pos = 0;
    	    pos = str.indexOf(find);
    
    	    while(pos != -1)
    	    {
    	        pre_str = str.substring(0, pos);
    	        post_str = str.substring(pos + find.length, str.length);
    	        str = pre_str + replace + post_str;
    	        pos = str.indexOf(find);
    	    }
    	    return str;
    	}
  }
  

  /**
  * 숫자 기능
  */
  var num = 
  {
      removeFmtObj:function removeFmtObj(obj)
      {
         var str = obj.value;
         obj.value =str.replace(/,/gi,"");
      }
      ,
      removeFmt:function removeFmt(str) 
      {
         return str.replace(/,/gi,"");
      }
      ,
      removeFmtObjects:function removeFmtObjects(frm, names)
      {
        var rtn=0;
        var aobj;
        var  arry = names.split(",");
        for(var i=0;i<arry.length;i++)
        {
          aobj = eval("document."+frm+"."+arry[i]);
          if(aobj.length == undefined )
          {
              num.removeFmtObj(aobj);
          }
          else 
          {
            for(var j=0;j<aobj.length;j++)
            {
              num.removeFmtObj(aobj[j]);
            }
          }
        }
      }
      ,
      addFmt:function addFmt(numstr)  //컴마 삭제처리것을 보정 처리해준다.
      {
          numstr = num.removeFmt(numstr);
          var rxSplit = new RegExp('([0-9])([0-9][0-9][0-9][,.])');
          var arrNumber = numstr.split('.');
          arrNumber[0] += '.';
          do 
          {
              arrNumber[0] = arrNumber[0].replace(rxSplit, '$1,$2');
          }
          while (rxSplit.test(arrNumber[0]));
          
          if (arrNumber.length > 1) 
          {
              ret = arrNumber.join('');
          }
          else 
          {
              ret = arrNumber[0].split('.')[0];
          }
          return ret;
      }
      ,
      addFmtObj:function addFmtObj(obj) 
      {
        var ret;
    		var form = (num.addFmtObj.arguments.length > 1) ? num.addFmtObj.arguments[1] : null;

        if (!util.isNumComma(obj)) 
        {
            chk.alertFocus(obj, "숫자만 입력하십시오.");
            return;
        }
        
		    if(!util.chkzero(obj)) return;
        
        var numstr = obj.value;
        numstr = num.removeFmt(numstr);
        var rxSplit = new RegExp('([0-9])([0-9][0-9][0-9][,.])');
        var arrNumber = numstr.split('.');
        arrNumber[0] += '.';
        do 
        {
            arrNumber[0] = arrNumber[0].replace(rxSplit, '$1,$2');
        }
        while (rxSplit.test(arrNumber[0]));
        
        if (arrNumber.length > 1) 
        {
            ret = arrNumber.join('');
        } 
        else 
        {
            ret = arrNumber[0].split('.')[0];
        }
        obj.value = ret;
      }
      ,
      addFmtObjects:function addFmtObjects(frm, names)
      {
        var rtn=0;
        var aobj;
        var  arry = names.split(",");
        for(var i=0;i<arry.length;i++)
        {
          aobj = eval("document."+frm+"."+arry[i]);
          if(aobj.length == undefined )
          {
              num.addFmtObj(aobj)
          }
          else 
          {
            for(var j=0;j<aobj.length;j++)
            {
              num.addFmtObj(aobj[j]);
            }
          }
        }
      }
      
   }
  
  /**
  * 일자 처리 
  */
  var day = 
  {
    removeFmtObj:function removeFmtObj(obj)
    {
       var str = obj.value;
       obj.value =str.replace(/-/gi,"");
    }
    ,
    removeFmt:function removeFmt(str) 
    {
       return str.replace(/-/gi,"");
    }
    ,
    removeFmtObjects:function removeFmtObjects(frm, names)
    {
      var rtn=0;
      var aobj;
      var  arry = names.split(",");
      for(var i=0;i<arry.length;i++)
      {
        aobj = eval("document."+frm+"."+arry[i]);
        if(aobj.length == undefined )
        {
           day.removeFmtObj(aobj);
        }
        else 
        {
          for(var j=0;j<aobj.length;j++)
          {
            day.removeFmtObj(aobj[j]);
          }
        }
      }
    }
    ,
    addFmtObjects:function addFmtObjects(frm, names)
    {
       var rtn=0;
       var aobj;
       var  arry = names.split(",");
       for(var i=0;i<arry.length;i++)
       {
          aobj = eval("document."+frm+"."+arry[i]);
          if(aobj.length == undefined )
          {
             day.addFmtObj(aobj)
          }
          else 
          {
            for(var j=0;j<aobj.length;j++)
            {
              day.addFmtObj(aobj[j]);
            }
          }        
        }
     }
     ,
     addFmtObj:function  addFmtObj(obj)
     {
        if (util.isEmpty(obj)) return;
        var numstr = util.removePeriod(obj.value);
        if (numstr.length != 8) 
        {
            chk.alertFocus(obj, "날짜는 YYYYMMDD의 형식으로 입력해주십시오");
            return false;
        }
        var rxSplit = new RegExp('([0-9][0-9][0-9][0-9])([0-9][0-9])([0-9][0-9])');
        numstr = numstr.replace(rxSplit, '$1'+DATE_SEPERATOR+'$2'+DATE_SEPERATOR+'$3');
        obj.value = numstr;
      }
      ,
      addFmt:function  addFmt(txt)
      {
        if(txt == "") return;
        var numstr = util.removePeriod(txt);
        if (numstr.length != 8) 
        {
            chk.alertFocus(obj, "날짜는 YYYYMMDD의 형식으로 입력해주십시오");
            return false;
        }
        var rxSplit = new RegExp('([0-9][0-9][0-9][0-9])([0-9][0-9])([0-9][0-9])');
        numstr = numstr.replace(rxSplit, '$1'+DATE_SEPERATOR+'$2'+DATE_SEPERATOR+'$3');
        return numstr;
      }
   }
  
  /**
  * 처리결과
  */
  var result = 
  {
      /**
    	* xml return시 처리 결과 코드와 Message확인
    	* arg[0] : xml  --> 결과 xml
    	*/
      msg:function (xml)
      { 
        var code = $(xml).find("code"); 
        var msgx  = $(xml).find("retmsg");
        if(code.attr("value") == -1) 
        {
          alert(msgx.attr("value")); 
          return "-1";
        }
        return msgx.attr("value");
      }
   }
    
   /** 
	 * selectbox을 지원한 object입니다.
	 */
   var selectbox = 
   {
    /**
  	 * selectbox에 data를 loading 처리 합니다.
  	 * arg[0] : urlx     --> 해당 action url을 설정 합니다.
  	 * arg[1] : selectbox id  
  	 * arg[2] : selected 처리 필요시 값  : Option 선택사항입니다.
  	 * arg[3] : header표시값 (선택하세요 등) 해당 표시에 대응되는 값 : Option 선택사항입니다.
  	 * arg[4] : header표시명 (선택하세요 등) 해당 표시명 :             Option 선택사항입니다.
  	 */
      loading:function ()
      {
        var args = arguments;
        var arg0 = args[0]+"&"+args[1];
        $.ajax({
            type: "POST",
            url: arg0,
            success: function(msg) 
            {
                if(args.length == 3) 
                {
                  $(args[2]).html(msg);
                }
                else if (args.length == 4) 
                {
                  selectbox.htmlload(args[2], msg, args[3]);
                }
                else if(args.length == 5) 
                {
                  msg = "<option value='"+args[3]+"'>"+args[4]+"</option>" + msg;
                  selectbox.htmlload(args[2], msg, args[3]);
                }
                else if (args.length == 6) 
                {
                  msg = "<option value='"+args[4]+"'>"+args[5]+"</option>" + msg;
                  selectbox.htmlload(args[2], msg, args[3]);
                }
            }
            ,
            error: function(request, status, error) 
            {
                alert("Ajax Calling Error:" + status +":" + error);
            }
        });
      }
      ,
      cloading:function ()
      {
        var args = arguments;
        var arg0 = args[0]+"&"+args[1];
        $.ajax({
            type: "POST",
            url: arg0,
            success: function(msg) 
            {
                if (args.length == 7) 
                {
                  msg = "<option value='"+args[4]+"'>"+args[5]+"</option>" + msg;
                  selectbox.htmlload(args[2], msg, args[3], args[6]);
                }
            }
            ,
            error: function(request, status, error) 
            {
                alert("Ajax Calling Error:" + status +":" + error);
            }
        });
      }
      ,
      htmlload:function (selectid, msg , valuex, widthx)
      {
        $(selectid).html(msg);
        $(selectid).val(valuex).attr("selected", "selected");
        var args = arguments;
        if(args.length == 4)
        {
           $(selectid).val(valuex).attr("className", widthx);
        }
      }
  }
  /**
	 * single dataset의 Data처리를 지원하는 기능입니다.
	 */
  var single = {
     /**
   	 * single Record의 Data을 전송하고 결과를 해당 dataset에 setting합니다.
  	 * call과 다른점은 out처리 할 Dataset의 값을 clear처리 하지 않습니다.
  	 * arg[0] : urlx     --> 해당 action url을 설정 합니다.
  	 * arg[1] : indset   --> 입력처리한 datasets을 입력합니다(단 단일 record만 입력dataset으로 사용해야 합니다. : Dataset1,Dataset2의 형식입니다.
  	 * arg[2] : outmap   --> 처리 결과를 assign합니다. 화면측Dataset:Model측결과Dataset,화면측Dataset:Model측결과Dataset 형식입니다.
  	 * arg[3] : callbackfunc --> input, output처리후 callback함수 호출이 필요시에 정의 합니다( 해당 arg[3]는 Option 선택사항입니다.
  	 */
      nclearcall: function ( urlx, indset, outmap , callbackfunc)
      {
        var datax= this.getDatasets(indset);
        var outset = outmap.split(",");
        var retmsg    ="";

        $.ajax({
            type: "POST",
            url: urlx,
            data:datax,
            dayaType:"xml",
            success: function(msg) 
            {
                var tmpset;
                var iset;
                var oset;
                
                if((retmsg = result.msg(msg)) == "-1")
                {
                  return;
                }
                if(outmap != '')
                {

                  for(var i=0;i<outset.length;i++)
                  {
                    tmpset= outset[i].split(":");
                    iset = tmpset[0];

                    oset= tmpset[1];
                    var sel1 = $(msg).find(oset);
                    if(sel1.attr("colnames") == undefined) continue;
                    
                    var colnames = sel1.attr("colnames").split(",");
                    
                    $(sel1).find("row").each
                    (
                        function()
                        {
                          for(var j=0;j<colnames.length;j++)
                          {
                            //alert( "#"+iset +"_" + colnames[j] +"::"+  $(this).find(colnames[j]).text() +":"); 
                             if($("#"+iset +"_" + colnames[j])[0] == undefined) continue;
                             switch( $("#"+iset +"_" + colnames[j])[0].tagName + $("#"+iset +"_" + colnames[j])[0].type) 
                             {
                               case 'INPUTcheckbox':
                                    if($("#"+iset +"_" + colnames[j]).val() == $(this).find(colnames[j]).text())
                                    {
                                      $("#"+iset +"_" + colnames[j])[0].checked = true;
                                    }
                                    break;
                               case 'SELECTselect-one':
                                    $("#"+iset +"_" + colnames[j]).val( $(this).find(colnames[j]).text() );
                                    //alert($("#"+iset +"_" + colnames[j]).val());
                                    if($("#"+iset +"_" + colnames[j]).val() != $(this).find(colnames[j]).text())
                                    {
                                      $("#"+iset +"_" + colnames[j]).html("<option value='"+$(this).find(colnames[j]).text()+"'></option>");
                                      $("#"+iset +"_" + colnames[j]).val( $(this).find(colnames[j]).text() );
                                    }
                                    break;
                               default :
                                    $("#"+iset +"_" + colnames[j]).val( $(this).find(colnames[j]).text() );
                                    break;
                             }
                             
                          }
                        }
                     );
                   }
                }
                if (callbackfunc != '') 
                {
                   eval(callbackfunc + '();' );
                }
            }
            ,
            error: function(request, status, error) 
            {
                 alert("Ajax Calling Error(actionCallXml):" + status +":" + error);
            }
        });
      },
    	/**
    	 * single Record의 Data을 전송하고 결과를 해당 dataset에 setting합니다.
    	 * arg[0] : urlx     --> 해당 action url을 설정 합니다.
    	 * arg[1] : indset   --> 입력처리한 datasets을 입력합니다(단 단일 record만 입력dataset으로 사용해야 합니다. : Dataset1,Dataset2의 형식입니다.
    	 * arg[2] : outmap   --> 처리 결과를 assign합니다. 화면측Dataset:Model측결과Dataset,화면측Dataset:Model측결과Dataset 형식입니다.
    	 * arg[3] : callbackfunc --> input, output처리후 callback함수 호출이 필요시에 정의 합니다( 해당 arg[3]는 Option 선택사항입니다.
    	 */
      call: function ( urlx, indset, outmap , callbackfunc)
      {

        var datax= this.getDatasets(indset);
        var outset = outmap.split(",");
        var retmsg    ="";
        var argv = arguments;

        $.ajax({
            type: "POST",
            url: urlx,
            data:datax,
            dayaType:"xml",
            success: function(msg) 
            {
                //alert(msg);
                var tmpset;
                var iset;
                var oset;
                
                if((retmsg = result.msg(msg)) == "-1")
                {
                  return;
                }
                
                for(var i=0;i<outset.length;i++)
                {
                  tmpset= outset[i].split(":");
                  iset = tmpset[0];
                  single.clearDataset(iset);
                  
                  oset= tmpset[1];
                  var sel1 = $(msg).find(oset);
                  if(sel1.attr("colnames") == undefined) continue;
                  var colnames = sel1.attr("colnames").split(",");
                  $(sel1).find("row").each
                  (
                      function()
                      {
                        for(var j=0;j<colnames.length;j++)
                        {
                           if($("#"+iset +"_" + colnames[j])[0] == undefined) continue;
                           
                          // alert($("#"+iset +"_" + colnames[j])[0].tagName + $("#"+iset +"_" + colnames[j])[0].type);
                           
                           switch( $("#"+iset +"_" + colnames[j])[0].tagName + $("#"+iset +"_" + colnames[j])[0].type) 
                           {
                             case 'INPUTcheckbox':
                                  if($("#"+iset +"_" + colnames[j]).val() == $(this).find(colnames[j]).text())
                                  {
                                    $("#"+iset +"_" + colnames[j])[0].checked = true;
                                  }
                                  break;
                             case 'SELECTselect-one':
                                  $("#"+iset +"_" + colnames[j]).val( $(this).find(colnames[j]).text() );
                                  //alert($("#"+iset +"_" + colnames[j]).val());
                                  if($("#"+iset +"_" + colnames[j]).val() != $(this).find(colnames[j]).text())
                                  {
                                    $("#"+iset +"_" + colnames[j]).html("<option value='"+$(this).find(colnames[j]).text()+"'></option>");
                                    $("#"+iset +"_" + colnames[j]).val( $(this).find(colnames[j]).text() );
                                  }                                 
                                  break;
                             default :
                                  $("#"+iset +"_" + colnames[j]).val( $(this).find(colnames[j]).text() );
                                  break;
                           }
                           //alert( "#"+iset +"_" + colnames[j] +"::"+  $(this).find(colnames[j]).text() +":");
                        }
                      }
                   );
                   //alert("xxxx:" + i);
                 }
                if (argv.length == 4) 
                {
                   eval(callbackfunc + '();' );
                }
            }
            ,
            error: function(request, status, error) 
            {
                 alert("Ajax Calling Error(actionCallXml):" + status +":" + error);
            }
        });
      },
      /**
    	 * single Record의 입력 복수의 Datasets의 Data를 전송 format의 Data로 return합니다.
    	 * arg[0] : names     --> 입력 Datasets을 설정합니다 :입력 Format ( dataset명,dataset명 )
    	 */
      getDatasets:function (names)
      {
        var rtn="";
        var dsnames = names.split(',');
        if(dsnames.length == undefined) return rtn;
        for(var i = 0 ; i < dsnames.length ; i++)
        {
          var dval = this.getSingleDataset(dsnames[i]);
          if(i==0)  rtn += dval;
          else      rtn += "&" +dval;
        }
        return rtn;
      },
      /**
    	 * single Record의 입력 단일 Dataset의 Data를 전송 format의 Data로 return합니다.
    	 * arg[0] : names     --> 입력 Datasets을 설정합니다 :입력 Format ( dataset명 )
    	 */
      getSingleDataset:function (datasetnm)
      {
          var rtn = "";
          var namex="";
          var dsname="";
          
          $(document).find("INPUT").each
          (
              function()
              {
                var namex =this.id
                if(namex == null || namex=="") return;
                if(namex.indexOf("_") == -1) 
                {
                   return;
                }
                var dsname = namex.substring(0, namex.indexOf("_"));
                if(single.checkName(dsname)==0) 
                {
                    return;
                }
                if(dsname != datasetnm) return;                        
                switch($("#"+namex)[0].type)
                {
                  case 'text'      :
                  case 'hidden'    :
                  case 'password'  :
                        rtn += namex + '=' + single.paramEscape($("#"+namex).val()) + '&';
                        break;
                  case 'radio' :
                      if(this.checked)  
                      {
                          this.checked = false;
                          rtn += namex + '=' + single.paramEscape($("#"+namex).val()) + '&';
                      }
                      break;
                  case 'checkbox' :
                      if(this.checked)  
                      {
                          rtn += namex + '=' + single.paramEscape($("#"+namex).val()) + '&';
                      }
                      break;
                }
              }
           );
          $(document).find("TEXTAREA").each
          (
              function()
              {
                var namex =this.id
                if(namex == null || namex=="") return;
                if(namex.indexOf("_") == -1) 
                {
                   return;
                }
                var dsname = namex.substring(0, namex.indexOf("_"));
                if(single.checkName(dsname)==0) 
                {
                    return;
                }
                if(dsname != datasetnm) return;      
                rtn += namex + '=' + single.paramEscape($("#"+namex).val()) + '&';
              }
           );

          $(document).find("SELECT").each(
              function()
              {
               
                var namex =this.id
                if(namex == null || namex=="") return;
                if(namex.indexOf("_") == -1) 
                {
                   return;
                }
                var dsname = namex.substring(0, namex.indexOf("_"));
                if(single.checkName(dsname)==0) 
                {
                    return;
                }
                if(dsname != datasetnm) return;    
                rtn += namex + '=' + single.paramEscape($("#"+namex).val()) + '&'; 
              }
           );
          if(rtn.length > 0) rtn = rtn.substring(0, rtn.length - 1);
          return rtn;
      },
      /**
    	 * single Record의 화면상의 Data를 clear시켜 줍니다. 
    	 * 신규등에서 사용합니다.
    	 * arg[0] : datasetnm  --> clear 대상 dataset명
    	 */
      clearDataset:function (datasetnm)
      {
          var rtn = "";
          var namex="";
          var dsname="";
          
          $(document).find("INPUT").each(
              function()
              {
                var namex =this.id
                if(namex == null || namex=="") return;
                if(namex.indexOf("_") == -1) 
                {
                   return;
                }
                var dsname = namex.substring(0, namex.indexOf("_"));
                if(single.checkName(dsname)==0) 
                {
                    return;
                }
                if(dsname != datasetnm) return;                        
                switch($("#"+namex)[0].type)
                {
                  case 'text'      :
                  case 'hidden'    :
                  case 'password'  :
                        $("#"+namex).val( "" );
                        break;
                  case 'radio' :
                      if(this.checked)  
                      {
                          this.checked = false;
                      }
                      break;
                  case 'checkbox' :
                      if(this.checked)  
                      {
                          this.checked = false;
                      }
                      break;
                }
              }
           );

          $(document).find("TEXTAREA").each(
              function()
              {
                
                var namex =this.id
                if(namex == null || namex=="") return;
                if(namex.indexOf("_") == -1) 
                {
                   return;
                }
                var dsname = namex.substring(0, namex.indexOf("_"));
                if(single.checkName(dsname)==0) 
                {
                    return;
                }
                if(dsname != datasetnm) return;      
                $("#"+namex).val( "" );   
              }
           );

          $(document).find("SELECT").each(
              function()
              {
               
                var namex =this.id
                if(namex == null || namex=="") return;
                if(namex.indexOf("_") == -1) 
                {
                   return;
                }
                var dsname = namex.substring(0, namex.indexOf("_"));
                if(single.checkName(dsname)==0) 
                {
                    return;
                }
                if(dsname != datasetnm) return;
 
                $("#"+namex).val("").attr("selected", "selected");
  
              }
           );

          return rtn;
      }, 
      paramEscape:function (paramValue)
      {
          return encodeURIComponent(paramValue);
      },
      checkName:function   (dsname)
      {
          if(dsname == "S") return 1;
          for(var i=0;i<99;i++)
          {
            if(dsname == ("S" + i )) 
            {
              return 1;
            }
          }
          return 0;
      }
    }
   