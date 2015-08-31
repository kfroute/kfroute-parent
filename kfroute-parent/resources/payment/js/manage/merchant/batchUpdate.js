var table_company;
$(function(){	
	var merchantCode;
	table_company = $("#product_table").myDataTable({
		sAjaxSource:ctx+"/device!queryDevice.do",
		paramSelector:"#merchantName,#address",
		aoColumns : [   
                     {	"bSortable": false, 
                    	 "aTargets": [ 0 ] ,
                    	 "fnRender" : function(obj) {
                    		var id=obj.aData[1];
  	  				    	merchantCode=obj.aData[6];
  	  			    		return "<input type='checkbox' name='subBox' value='"+merchantCode+"' id='"+id+"'/>";
  	  		    		}
                     },
                     {},
                     {},
                     {},
                     {},
                     {},
                     {},
                     {}
				]
	});
	
	$("#query_btn").on("click",function(){
		table_company.fnPageChange("first",true);
	});
	
	
});


function checkAll(){
	if($('#checkAll').is(':checked')){
		var obj=document.getElementsByName('subBox');
		for(var i=0;i<obj.length;i++){
			obj[i].checked=true;
		}
		return false;
	}else{
		$('[name="subBox"]').removeAttr("checked");
		return false;
	}
};

function release(){
	var obj=document.getElementsByName('subBox'); //选择所有name="'test'"的对象，返回数组 
	//取到对象数组后，我们来循环检测它是不是被选中 
	var s='';
	var macs='';
	var j=0;
	for(var i=0; i<obj.length; i++){ 
		if(obj[i].checked){
			s+=obj[i].value+","; //如果选中，将value添加到变量s中 
			macs+=obj[i].id+",";
			j++;
		} 
	}
	//那么现在来检测s的值就知道选中的复选框的值了 
	if(s==""){
		alert("没有选择任何商户");
		return;
	}
	var fileType=$('#fileType option:selected').val();
	var fileTypeText=$('#fileType option:selected').text();
	
	if(!confirm("你已经选择了"+j+"家商户："+macs+"选择文件类型为"+fileTypeText+"，确定发布吗？")){
		return;
	};
	$.ajax({
		timeout : 150000,
		async   : true,
		cache   : false,
		type    : 'POST',
		dataType: 'json',
		data    :{
			'merchantCodes':s,
			'fileType':fileType,
			'macs':macs
		},
		url     : ctx+'/fileUpload!batchUpdate.do',
		success : function(data){
			var result=data.result;
			if(result==1){
				alert("发布成功");
			}else if(result==0){
				alert("文件不存在，请上传文件");
			}
			
			
		},
		error : function(){
			alert("添加失败");
		}
	});
	
}

