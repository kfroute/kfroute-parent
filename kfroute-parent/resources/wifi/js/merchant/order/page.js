
var currentPage = 1;
var pageTotal = $("#totalPage").val();

function hideNumbers(){
	var total = $("#totalPage").val();
	if(total<5){
		for(var i=0;i<5-total;i++){
			var index = parseInt(total)+parseInt(i)+parseInt(2);
			$("#pageLi li:eq("+index+")").hide();
		}
	}
}

function firstPage(){
	currentPage = 1
	getPage(currentPage);
}

function lastPage(){
	currentPage = pageTotal;
	getPage(currentPage);
}

function prePage(){
	
	var page = parseInt(currentPage)-1;
	if(page<0||page==0){
		return;
	}
	currentPage--;
	getPage(page);
}

function nextPage(){
	
	var page = parseInt(currentPage)+1;
	if(page>pageTotal){
		return;
	}
	currentPage++;
	getPage(page);
}

function specifiedPage(number){
	chandeColor(number);
	var clickPage = getClickPage(number);
	currentPage = clickPage;
	getPage(clickPage);
}

function getPage(clickPage){
	if(currentPage>pageTotal||currentPage<0){
		return;
	}
	var url=ctx + '/orderBase!queryOrder.do';
	//alert(url);
	$('#tb_data').load(url,{"page.currentPage":clickPage});
	pageTotal = $("#totalPage").val();
	adjustIndex();	
}

function chandeColor(index){
	switch(parseInt(index)){
	case 1:
	  //$("#onePage").attr("class", "diy_over");
	  $(".numberPage").attr("onMouseOut", "this.className='diy_out numberPage'");
	  $(".numberPage").attr("class", "diy_out numberPage");
	  $("#onePage").attr("onMouseOut", "this.className='diy_over numberPage'");
	  $("#onePage").attr("class", "diy_over numberPage");
	  break;
	case 2:
		//$("#twoPage").className='diy_over';
		 $(".numberPage").attr("onMouseOut", "this.className='diy_out numberPage'");
		 $(".numberPage").attr("class", "diy_out numberPage");
		  $("#twoPage").attr("onMouseOut", "this.className='diy_over numberPage'");
		  $("#twoPage").attr("class", "diy_over numberPage");
	  break;
	case 3:
		//$("#threePage").className='diy_over';
		$(".numberPage").attr("onMouseOut", "this.className='diy_out numberPage'");
		$(".numberPage").attr("class", "diy_out numberPage");
		  $("#threePage").attr("onMouseOut", "this.className='diy_over numberPage'");
		  $("#threePage").attr("class", "diy_over numberPage");
		  break;
	case 4:
		//$("#fourPage").className='diy_over';
		$(".numberPage").attr("onMouseOut", "this.className='diy_out numberPage'");
		$(".numberPage").attr("class", "diy_out numberPage");
		  $("#fourPage").attr("onMouseOut", "this.className='diy_over numberPage'");
		  $("#fourPage").attr("class", "diy_over numberPage");
		  break;
	default:
		//$("#fivePage").className='diy_over';
	$(".numberPage").attr("onMouseOut", "this.className='diy_out numberPage'");
	$(".numberPage").attr("class", "diy_out numberPage");
	  $("#fivePage").attr("onMouseOut", "this.className='diy_over numberPage'");
	  $("#fivePage").attr("class", "diy_over numberPage");
 }
}

function getClickPage(index){

	var clickPageIndex = 0;
	if(currentPage<4){
		clickPageIndex = index;
	}else if(3<currentPage&&currentPage<(parseInt(pageTotal)-1)){
		clickPageIndex = parseInt(currentPage) - 3 + parseInt(index);
	}else{
		clickPageIndex = parseInt(pageTotal)-5+parseInt(index);
	}
	return clickPageIndex;
}

function adjustIndex(){
	
	var last=pageTotal;
	var lastPre = parseInt(pageTotal)-1;
	if(currentPage == lastPre && pageTotal > 5 ){
		$("#onePage").text(currentPage-3);
		$("#twoPage").text(currentPage-2);
		$("#threePage").text(currentPage-1);
		$("#fourPage").text(currentPage);
		$("#fivePage").text(currentPage+1);
		chandeColor(4);
	}else if(currentPage == last && pageTotal > 5){
		$("#onePage").text(currentPage-4);
		$("#twoPage").text(currentPage-3);
		$("#threePage").text(currentPage-2);
		$("#fourPage").text(currentPage-1);
		$("#fivePage").text(currentPage);
		chandeColor(5);
	}else if(2<currentPage&& currentPage<lastPre && pageTotal > 5){
		$("#onePage").text(currentPage-2);
		$("#twoPage").text(currentPage-1);
		$("#threePage").text(currentPage);
		$("#fourPage").text(currentPage+1);
		$("#fivePage").text(currentPage+2);
		chandeColor(3);
	}else if(2==currentPage&&pageTotal > 5){
		$("#onePage").text(currentPage-1);
		$("#twoPage").text(currentPage);
		$("#threePage").text(currentPage+1);
		$("#fourPage").text(currentPage+2);
		$("#fivePage").text(currentPage+3);
		chandeColor(2);
	}else if(1==currentPage&&pageTotal > 5){
		$("#onePage").text(currentPage);
		$("#twoPage").text(currentPage+1);
		$("#threePage").text(currentPage+2);
		$("#fourPage").text(currentPage+3);
		$("#fivePage").text(currentPage+4);
		chandeColor(1);
	}else{
		chandeColor(currentPage);
	}
}
