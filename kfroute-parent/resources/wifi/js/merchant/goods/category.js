$(loadCategory());

function loadCategory(){
	var url=ctx+'/product!loadCategory.do';
	$('#menu_class').load(url);
}