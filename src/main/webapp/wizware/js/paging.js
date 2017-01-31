//var PagingCurrentId =0;
var Pagingfirst = false;
function initPage(gridId,totalSize,currentPage){
	

	
	if(currentPage==""){
		var currentPage = $('#'+gridId).getGridParam('page');
	}
	// 한 페이지에 보여줄 페이지 수 
	var pageCount = 10;
	// 그리드 데이터 전체의 페이지 수
	var totalPage = Math.ceil(totalSize/$('#'+gridId).getGridParam('rowNum'));
	// 전체 페이지 수를 한화면에 보여줄 페이지로 나눈다.
	var totalPageList = Math.ceil(totalPage/pageCount);
	// 페이지 리스트가 몇번째 리스트인지
	var pageList=Math.ceil(currentPage/pageCount);
	
	//alert("currentPage="+currentPage+"/ totalPage="+totalSize);
	//alert("pageCount="+pageCount+"/ pageList="+pageList);
	
	// 페이지 리스트가 1보다 작으면 1로 초기화
	if(pageList<1) pageList=1;
	// 페이지 리스트가 총 페이지 리스트보다 커지면 총 페이지 리스트로 설정
	if(pageList>totalPageList) pageList = totalPageList;
	// 시작 페이지
	var startPageList=((pageList-1)*pageCount)+1;
	// 끝 페이지
	var endPageList=startPageList+pageCount-1;
	
	
	//alert("startPageList="+startPageList+"/ endPageList="+endPageList);
	
	// 시작 페이지와 끝페이지가 1보다 작으면 1로 설정
	// 끝 페이지가 마지막 페이지보다 클 경우 마지막 페이지값으로 설정
	if(startPageList<1) startPageList=1;
	if(endPageList>totalPage) endPageList=totalPage;
	if(endPageList<1) endPageList=1;
	
	// 페이징 DIV에 넣어줄 태그 생성변수 
	var pageInner="";
	
	// 페이지 리스트가 1이나 데이터가 없을 경우 
	if(pageList<2){
		
		pageInner+="<img src="+Contextpath+"/wizware/css/img/pageing/first.png class='nextpre'>";
		pageInner+="<img src="+Contextpath+"/wizware/css/img/pageing/prev.png class='nextpre'>";
		
	}
	// 이전 페이지 리스트가 있을 경우 
	if(pageList>1){
		
		pageInner+="<a class='first' href='javascript:firstPage("+totalSize+','+gridId+","+totalPage+")'><img src="+Contextpath+"/wizware/css/img/pageing/first.png  class='nextpre'></a>";
		pageInner+="<a class='pre' href='javascript:prePage("+totalSize+','+gridId+","+totalPage+")' ><img src="+Contextpath+"/wizware/css/img/pageing/prev.png  class='nextpre'></a>";
		
	}
	// 페이지 숫자를 찍으며 태그생성  
	
	for(var i=startPageList; i<=endPageList; i++){
		if(i==currentPage){
			pageInner = pageInner +"<a  href='javascript:goPage("+(i)+','+gridId+","+totalPage+")' id='"+(i)+"' class='pageGrid'><strong >"+(i)+"</strong></a> ";
		}else{
			pageInner = pageInner +"<a  href='javascript:goPage("+(i)+','+gridId+","+totalPage+")' id='"+(i)+"' class='pageGrid' >"+(i)+"</a> ";
		}
		
	}
	//alert(totalPage);
	//alert("총페이지 갯수"+totalPageList);
	//alert("현재페이지리스트 번호"+pageList);
	
	// 다음 페이지 리스트가 있을 경우
	if(totalPageList>pageList){
		
		pageInner+="<a class='next' href='javascript:nextPage("+totalSize+','+gridId+","+totalPage+")' ><img src="+Contextpath+"/wizware/css/img/pageing/next.png class='nextpre'></a>";
		pageInner+="<a class='last' href='javascript:lastPage("+totalSize+','+gridId+","+totalPage+")' ><img src="+Contextpath+"/wizware/css/img/pageing/last.png class='nextpre'></a>";
	}
	// 현재 페이지리스트가 마지막 페이지 리스트일 경우
	if(totalPageList==pageList){
		
		pageInner+="<img src="+Contextpath+"/wizware/css/img/pageing/next.png class='nextpre'>";
		pageInner+="<img src="+Contextpath+"/wizware/css/img/pageing/last.png class='nextpre'>";
	}   
	//alert(pageInner);
	// 페이징할 DIV태그에 우선 내용을 비우고 페이징 태그삽입
	
	$("#paginate_"+gridId).html("");
	$("#gridhead_"+gridId).html("");
	$("#paginate_"+gridId).append(pageInner);
	//alert(currentPage);
	
	if(pageList == 0 ){
	//$("#paginate_"+gridId).append("[1/"+totalSize+"]");  gridhead
	//$("#paginate_"+gridId).append("<label style='font:12px/1.5 Arial, Tahoma, Helvetica, sans-serif;color:#666;'>총"+ 0 +"개의 게시물이 있습니다. [" +0+"/"+0+"]</label>");  
	$("#gridhead_"+gridId).append("<label class='GridGunsu'>총"+ 0 +"개의 게시물이 있습니다. [" +0+"/"+0+"]</label>");  
	Pagingfirst= false;
	}else if(pageList ==1 && currentPage==1 && Pagingfirst ==false){
	//$("#paginate_"+gridId).append("총"+totalSize +"개의 게시물이 있습니다. [" +1+"/"+endPageList+"]");  
	$("#gridhead_"+gridId).append("<label class='GridGunsu'>총"+totalSize +"개의 게시물이 있습니다. [" +1+"/"+totalPage+"]</label>");  
	Pagingfirst= true;
	}else if(pageList ==1 && currentPage==1 && Pagingfirst ==true){
	//$("#paginate_"+gridId).append("총"+totalSize +"개의 게시물이 있습니다. [" +1+"/"+endPageList+"]");  
	$("#gridhead_"+gridId).append("<label class='GridGunsu'>총"+totalSize +"개의 게시물이 있습니다. [" +1+"/"+totalPage+"]</label>");  
	//Pagingfirst= true;
	}
	//alert(currentPage+"@@"+Pagingfirst+"@@"+pageList);
	
	
}

// 그리드 첫페이지로 이동 
function firstPage(totalSize,gridId,lastnum){
		
	gridId.wizGrid('setGridParam', {
							page:1
						}).trigger("reloadGrid");
	 //$("#gridhead_"+gridId[0].id).append("<label class='GridGunsu'>총"+ totalSize +"개의 게시물이 있습니다. [" +1+"/"+lastnum+"]</label>");  
		
}
// 그리드 이전페이지 이동 
function prePage(totalSize,gridId,lastnum){
		
		var currentPage = gridId.getGridParam('page');
		var pageCount = 10;
		
		currentPage-=pageCount;
		pageList=Math.ceil(currentPage/pageCount);
		currentPage=(pageList-1)*pageCount+pageCount;
		
		initPage("wizGrid",totalSize,currentPage);
		
		gridId.wizGrid('setGridParam', {
							page:currentPage
						}).trigger("reloadGrid");
		$("#gridhead_"+gridId[0].id).append("<label class='GridGunsu'>총"+ totalSize +"개의 게시물이 있습니다. [" +currentPage+"/"+lastnum+"]</label>");  
		
}
// 그리드 다음페이지 이동 	
function nextPage(totalSize,gridId,lastnum){
		
		var currentPage = grid1.getGridParam('page');
		var pageCount = 10;
		
		currentPage+=pageCount;
		pageList=Math.ceil(currentPage/pageCount);
		currentPage=(pageList-1)*pageCount+1;
		
		initPage("wizGrid",totalSize,currentPage);
		
		gridId.wizGrid('setGridParam', {
							page:currentPage
						}).trigger("reloadGrid");
	$("#gridhead_"+gridId[0].id).append("<label class='GridGunsu'>총"+ totalSize +"개의 게시물이 있습니다. [" +currentPage+"/"+lastnum+"]</label>");  
}
// 그리드 마지막페이지 이동 
function lastPage(totalSize,gridId,lastnum){
	
	gridId.wizGrid('setGridParam', {
							page:lastnum
						}).trigger("reloadGrid");
   $("#gridhead_"+gridId[0].id).append("<label class='GridGunsu'>총"+ totalSize +"개의 게시물이 있습니다. [" +lastnum+"/"+lastnum+"]</label>");  
}
// 그리드 페이지 이동 
function goPage(num,gridId,lastnum){
		    // $('#S1_PAGE').val(num);
			//alert(gridId[0].id);
		     var nTotalCnt = gridId.wizGrid('getGridParam','records');
		     var nPage = gridId.wizGrid('getGridParam','page');
		     var nRows = gridId.wizGrid('getGridParam','rowNum');
		     var No= nTotalCnt-(nPage-1)*nRows;
			// alert(gridId.toString() +"@@"+nTotalCnt + "@@" + nPage + "@@" + nRows + "@@" + No);
		  
		    
		gridId.wizGrid('setGridParam', {
							page:num,
							records:nRows
						}).trigger("reloadGrid");
						//총 ${PAGE.TOTAL} 개의 게시물이 있습니다. Page [${PAGE.CURRENT+1}/${PAGE.PAGECOUNT}]
						if(num != 1) $("#gridhead_"+gridId[0].id).append("<label class='GridGunsu'>총"+ nTotalCnt +"개의 게시물이 있습니다. [" +num+"/"+lastnum+"]</label>");  
	   
	  
		
}
