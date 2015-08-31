
function addCategory(){

	var newCategory= $('#newCategory').val();
	var seq=$('#categorySeq').val();
	if(isNaN(seq)){
		alert("顺序必须是数字");
		return;
	}
	if(newCategory==""||newCategory==null){
		alert("请输入菜品名称");
		return;
	}
	$.ajax({
		timeout : 15000,
		async   : true,
		cache   : false,
		type    : 'POST',
		url     : ctx+'/merchantCategory!add.do',
		dataType: 'json',
		data    : {
			'newCategory': newCategory,
			'seq':seq
		},
		success : function(data){
			flag=true;
			var result=data.result;
			if(result==1){
				alert("添加成功");
				document.all.ly.style.display='none';
				document.all.dish_class.style.display='none';
				loadCategory();
				loadFoodEditor();
				loadProduct();
				return false;
			}else if(result==0){
				alert("已有同名类型，请勿重名");
			}else if(result==-1){
				alert("登录超时，请重新登录");
			}
		},
		error   : function(data){
			flag=true;
			alert("读取数据失败");
		}
		
	});
	
	
}

function addProduct(){
	var categoryId=$('#categoryId').val();
	var productName=$('#productName').val();
	var categroyType=$("#categoryType option:selected").val();
	var price=$('#price').val();
	var unitType=$('#unitType option:selected').val();
	var goodsInfo=$('#goodsInfo').val();
	var stock=$('#stock').val();
	var scPrice=$('#scPrice').val();
	var displayOrder=$('#displayOrder').val();
	
	if(stock==""||isNaN(stock)||stock.indexOf(".")>0){
		alert("库存必须是整数");
		return;
	}
	if(scPrice==""||isNaN(scPrice)){
		alert("价格必须是数字");
		return;
	}
	if(displayOrder==""||isNaN(displayOrder)||displayOrder.indexOf(".")>0){
		alert("顺序必须是整数");
		return;
	}
	
	if(productName==""||productName==null){
		alert("请输入菜名");
		return;
	}
	if(categroyType=="--选择菜品分类--"){
		alert("请选择菜品类型");
		return;
	}
	if(price==""||price==null){
		alert("请输入单价");
		return;
	}
	if(unitType=="--请选择菜品单位--"){
		alert("请选择菜品单位");
		return;
	}
	$.ajax({
		timeout : 15000,
		async   : true,
		cache   : false,
		type    : 'POST',
		url     : ctx+'/product!add.do',
		dataType: 'json',
		data    : {
			'productName': productName,
			'categoryType': categroyType,
			'price':price,
			'unitType':unitType,
			'goodsInfo':goodsInfo,
			'stock':stock,
			'displayOrder':displayOrder,
			'scPrice':scPrice
		},
		success : function(data){
			var result=data.result;
			if(result==1){
				alert("添加成功");
				document.all.ly.style.display='none';
				document.all.food_editor.style.display='none';
				if(categoryId==''){
					loadProduct();
				}else{
					findList(categoryId,categroyType);
				}
				$('#productName').val('');
				$('#categoryType').val('');
				$('#price').val('');
				$('#unitType').val('');
				$('#goodsInfo').val('');
				$('#scPrice').val('');
				$('#displayOrder').val('');
				$('#stock').val('');
				return false;
			}else if(result==0){
				alert("已有同名的菜，请勿重名");
			}else if(result==-1){
				alert("登录超时，请重新登录");
			}
			
		},
		error   : function(data){
			alert("读取数据失败");
		}
		
	});
}

$(function(){
	loadCategory();
	loadProduct();
	loadFoodEditor();
});


function loadCategory(){
	var url=ctx+'/product!loadCategory.do';
	$('#menu_class').load(url);
}

function loadProduct(){
	$('#categoryId').val('');
	var url=ctx+"/product!loadProduct.do";
	$('#mmr_cont').load(url);
	
}

function loadFoodEditor(){
	var url=ctx+"/product!loadFoodEditor.do";
	$('#food_editor').load(url);
}

function updateCategory(id,name,seq){
	document.all.ly.style.display="block";
	document.all.ly.style.width=document.body.clientWidth;
	document.all.ly.style.height=document.body.clientHeight;
	document.all.dish_class.style.display='block';
	$('#newCategory').val(name);
	$('#categorySeq').val(seq);
	$('#addCategory').attr('onclick','').click(eval(function(){updateCategoryNow(id)}));
	
}

function updateCategoryNow(id){
	var seq=$('#categorySeq').val();
	if(isNaN(seq)){
		alert("请输入整数");
		return;
	}
	if(seq.indexOf(".")>=0){
		alert("请输入整数");
		return;
	}
	
	var name= $('#newCategory').val();
	name=name.trim();
	if(name==null||name==""){
		alert("类型名不能为空");
		return;
	}
	$.ajax({
		timeout : 15000,
		async   : true,
		cache   : false,
		type    : 'POST',
		url     : ctx+'/merchantCategory!update.do',
		dataType: 'json',
		data    : {
			'id' : id,
			'name':name,
			'seq':seq
		},
		success : function(data){
			var result=data.result;
			if(result==-1){
				alert("登录超时，请重新登录");
			}else if(result==0){
				alert("已有同名类型，请勿重名");
			}else if(result==1){
				alert("修改成功");
				document.all.ly.style.display='none';
				document.all.food_editor.style.display='none';
				loadCategory();
				loadFoodEditor();
				loadProduct();
				$('#addCategory').attr('onclick','addCategory()');
				return false;
			}
		},
		error   : function(data){
			alert("读取数据失败");
		}
		
	});
	
}

function deleteCategory(id){
	if(confirm("确定删除吗！")){
		$.ajax({
			timeout : 15000,
			async   : true,
			cache   : false,
			type    : 'POST',
			url     : ctx+'/merchantCategory!delete.do',
			dataType: 'json',
			data    : {
				'id' : id
			},
			success : function(data){
				var result=data.result;
				if(result==-1){
					alert("登陆超时，请重新登录");
				}else if(result==0){
					alert("现有菜 "+data.productNames+" 是这个类目的，不可删除");
				}else{
					document.all.ly.style.display='none';
					document.all.food_editor.style.display='none';
					loadCategory();
					return false;
				}
			},
			error   : function(data){
				alert("删除失败");
			}
			
		});
		
	}
}

function updateProduct(id,name,price,goodsInfo,categoryName,unitName,scPrice,displayOrder,stock){
	document.all.ly.style.display="block";
	document.all.ly.style.width=document.body.clientWidth;
	document.all.ly.style.height=document.body.clientHeight;
	document.all.food_editor.style.display='block';
	$('#productName').val(name);
	$('#categoryType').val(categoryName);
	$('#price').val(price);
	$('#unitType').val(unitName);
	$('#goodsInfo').val(goodsInfo);
	$('#scPrice').val(scPrice);
	$('#displayOrder').val(displayOrder);
	$('#stock').val(stock);
//	$('#addProduct').attr('onclick','').click(eval(function(){updateProductNow(id)}));
	$('#addProduct').attr('onclick','updateProductNow('+id+')');
}

function updateProductNow(id){
	var categoryId=$('#categoryId').val();
	var productName=$('#productName').val();
	var categoryType=$("#categoryType option:selected").val();
	var price=$('#price').val();
	var unitType=$('#unitType option:selected').val();
	var goodsInfo=$('#goodsInfo').val();
	var stock=$('#stock').val();
	var scPrice=$('#scPrice').val();
	var displayOrder=$('#displayOrder').val();
	
	if(stock==""||isNaN(stock)||stock.indexOf(".")>0){
		alert("库存必须是整数");
		return;
	}
	if(scPrice==""||isNaN(scPrice)){
		alert("价格必须是数字");
		return;
	}
	if(displayOrder==""||isNaN(displayOrder)||displayOrder.indexOf(".")>0){
		alert("顺序必须是整数");
		return;
	}
	if(productName==""||productName==null){
		alert("请输入菜名");
		return;
	}
	if(categoryType=="--选择菜品分类--"){
		alert("请选择菜品类型");
		return;
	}
	if(price==""||price==null){
		alert("请输入单价");
		return;
	}
	if(unitType=="--请选择菜品单位--"){
		alert("请选择菜品单位");
		return;
	}
	
	$.ajax({
		timeout : 15000,
		async   : true,
		cache   : false,
		type    : 'POST',
		url     : ctx+'/product!update.do',
		dataType: 'json',
		data    : {
			'id' : id,
			'productName':productName,
			'categoryType':categoryType,
			'price':price,
			'unitType':unitType,
			'goodsInfo':goodsInfo,
			'stock':stock,
			'displayOrder':displayOrder,
			'scPrice':scPrice
		},
		success : function(data){
			var result=data.result;
			if(result==-1){
				alert("登录超时，请重新登录");
			}else if(result==0){
				alert("已经有同名的菜，请不要重名");
			}else if(result==1){
				alert("修改成功！");
				$('#addProduct').attr('onclick','addProduct()');
				$('#productName').val('');
				$('#categoryType').val('');
				$('#price').val('');
				$('#unitType').val('');
				$('#goodsInfo').val('');
				$('#scPrice').val('');
				$('#displayOrder').val('');
				$('#stock').val('');
				if(categoryId==''){
					loadProduct();
				}else{
					findList(categoryId,categoryType);
				}
				document.all.ly.style.display='none';
				document.all.food_editor.style.display='none';
			}
			
			
		},
		error   : function(data){
			alert("修改失败");
		}
		
	});
}

function deleteProduct(id){
	var categoryId=$('#categoryId').val();
	var categoryType=$('#categoryType').val();
	if(confirm("确定删除吗！")){
		$.ajax({
			timeout : 15000,
			async   : true,
			cache   : false,
			type    : 'POST',
			url     : ctx+'/product!delete.do',
			dataType: 'json',
			data    : {
				'id' : id
			},
			success : function(data){
				if(categoryId==''){
					loadProduct();
				}else{
					findList(categoryId,categoryType);
				}
			},
			error   : function(data){
				alert("读取数据失败");
			}
			
		});
	}
}

function startUploadImg(goodsCode){
	$('#imgFile').val('');
	$('#imgFile').click();
	$('#goodsCode').val(goodsCode);
}

//上传图片
function uploadImg(){
	var goodsCode=$('#goodsCode').val();
	var classStr=".image_"+goodsCode;
	$('#divClass').val(classStr);
	var advertImgName = $('#imgFile').val();
	if (advertImgName == "" || advertImgName == null) {
		alert("请选择文件！");
		return;
	}
	var file_name = advertImgName
			.substring(advertImgName.lastIndexOf("\\") + 1);
	var filename_atr = advertImgName.substring(
			advertImgName.lastIndexOf(".") + 1, advertImgName.length);
	var reg1 = "gif,jpg,jpeg,png,bmp,GIF,JPG,JPEG,PNG,BMP";
	if ((reg1.indexOf(filename_atr) < 0)) {
		alert("请选择gif,jpg,jpeg,png,bmp格式的图片文件！");
		$('#file').val('');
		return;
	}
	var options = {
	      dataType : "json",
	      async: false,
	      data :{
	    	  'filename_atr': filename_atr,
	    	  'filePath'	: ctx,
	    	  'goodsCode'	: goodsCode
	      },
	      success :showFrontResponse,
	      error:showFrontError,
	      timeout: 30000      //限制请求的时间，当请求大于3秒后，跳出请求
	  };
	$('#uploadProductImg').attr('action',ctx+'/product!uploadImg.do');
	jQuery("#uploadProductImg").ajaxSubmit(options);
	$('#uploadProductImg').attr('action','');
	
	
}

function showFrontResponse(data){
	var path=resourcePath+'/'+data.url+'?d='+Math.random();
	var goodsCode=$('#goodsCode').val();
	$('#pic_product_'+goodsCode).attr("src",path);}

function showFrontError(){
	alert("上传失败");
}

function findList(id,categoryName){
	$('#categoryId').val(id);
	$('#categoryType').get(0).value=categoryName;
	loadProductList(id);
}

function loadProductList(id){
	var url=ctx+"/product!findList.do";
	$('#mmr_cont').load(url,{"id":id});
}

function addBatch(){
	var filePath=$('#theXls').val();
	var str="xls,xlsx";
	var filePath_atr=filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length);
	if(str.indexOf(filePath_atr)<0){
		alert("请选择Excel文件");
		return;
	}
	var options = {
	      dataType : "json",
	      async: false,
	      data :{
	    	  'filePath_atr': filePath_atr,
	    	  'filePath'	: ctx
	      },
	      success :showFrontResponseExcel,
	      error:showFrontErrorExcel,
	      timeout: 150000      //限制请求的时间，当请求大于15秒后，跳出请求
	  };
	$('#uploadExcel').attr('action',ctx+'/product!uploadExcel.do');
	jQuery("#uploadExcel").ajaxSubmit(options);
	$('#uploadExcel').attr('action','');
	
}

function showFrontResponseExcel(data){
	var result=data.result;
	if(result==1){
		alert("成功");
		loadProduct();		
	}else{
		alert(data.msg);
	}
	
}

function showFrontErrorExcel(data){
	alert("批量导入失败");
	alert(data.msg);
}

