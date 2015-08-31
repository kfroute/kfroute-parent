//产品展示标签 切换效果
$("#content_tiltle_label li").click(function(){
	//标签切换
	   $(this).addClass("current");
	   $(this).removeClass("others");
	    $(this).siblings().addClass("others");
		$(this).siblings().removeClass("current");
	   //div切换
		var index=$("#content_tiltle_label li").index(this);
		index=index-1;
	    $("#content_tiltle_div").children("div").eq(index).show().siblings("div").hide();
})