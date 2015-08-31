var table_company;

$(function(){	
	
	table_company = $("#product_table").myDataTable({
		sAjaxSource:ctx+"/merchant!queryMerchant.do",
		paramSelector:"#merchantName,#address",
		aoColumns : [   
                     {},
                     {},
                     {},
                     {},
                     {},
                     {},
                     {
 	  			    	"fnRender" : function(obj) {
 	  				    	var id = obj.aData[6];
 	  			    		return "<a href='javascript:deleteHospital({0});' onclick='addMerchant("+id+");'>编辑</a>".format(id)+"&nbsp;|&nbsp"
 	  			    		+"<a href='javascript:deleteHospital({0});' onclick='deleteMerchant("+id+");'>删除</a>".format(id);
 	  		    		}
                      }
				]
	});
	
	$("#query_btn").on("click",function(){
		table_company.fnPageChange("first",true);
	});
});

/*function addPage(){
	alert(1111);
	var url=ctx + '/merchant!returnAddPage.do';
	$('#merchantPage').load(url);
}*/

function addMerchant(type){
	window.open(ctx+"/pages/merchant/editUser.jsp?id="+type,"_self");
}

function deleteMerchant(id){
	if(confirm("确定删除吗？")){
		$.ajax({
			timeout : 150000,
			async   : true,
			cache   : false,
			type    : 'POST',
			dataType: 'json',
			data    :{
				'id':id
			},
			url     : ctx+'/merchant!delete.do',
			success : function(data){
				var result=data.result;
				if(result==1){
					alert("删除成功");
				}else{
					alert("删除失败");
				}
				
			},
			error : function(){
				alert("删除失败");
			}
		});
	}
}

