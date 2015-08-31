/**
 * add by zhangyl at 2015-08-03 for m1320_main.jsp
 */
var chooseServerList = "";
var table;
var oTable;
$(function(){
	//* datatable must be rendered first
    common_m1320_table.init();
    //* actions for tables, datatables
    common_select_row.init();
	common_delete_rows.simple();
	common_delete_rows.dt();
	//$('#dt_gal').dataTable();
	
	$(".input-table tbody tr th").addClass("sorting_1");
	$("#server_detail_filter label input").attr("placeholder","输入名称或ip快速检索");
	var lastIdx = null;
    table = $('#server_detail').DataTable();
    $('#server_detail tbody')
     .on( 'mouseover', 'tr', function () {
  	   //console.log(table);
         var rowIdx = table.row(this).index();
         if ( rowIdx !== lastIdx ) {
             $( table.rows().nodes() ).removeClass( 'highlight' );
             $( table.rows( rowIdx ).nodes() ).addClass( 'highlight' );
             //$( table.rows( rowIdx ).nodes() ).find(".sorting_1").removeClass( 'sorting_1' );
             //$( table.rows( rowIdx ).nodes() ).css("background-color","red");
         }
     } )
     .on( 'mouseleave', function () {
         $( table.rows().nodes() ).removeClass( 'highlight' );
     } );
});

//加载扩展模块
layer.config({
    extend: 'extend/layer.ext.js'
});
/* [ ---- common Admin Panel - tables ---- ] */



//* select all rows
common_select_row = {
	init: function() {
		$('.select_rows').click(function () {
			var tableid = $(this).data('tableid');
            //$('#'+tableid).find('input[name=row_sel]').attr('checked', this.checked);
            var chooseList =  $('#'+tableid).find('input[name=row_sel]');
            for(var i=0;i<chooseList.length;i++){
            	var obj = $(chooseList[i]);
            	//console.log((obj.attr('checked')=='checked')+"===="+this.checked);
            	if((obj.attr('checked')=='checked')==this.checked){//当前列的选中状态已经和表头一致时，不做处理
            		continue;
            	}
            	obj.attr('checked',this.checked);
            	chooseThis(obj.attr('id').substring(6),obj.attr('checked'));
            }
		});
	}
};

//* delete rows
common_delete_rows = {
	simple: function() {
		$(".delete_rows_simple").on('click',function (e) {
			e.preventDefault();
			var tableid = $(this).data('tableid');
            if($('input[name=row_sel]:checked', '#'+tableid).length) {
                $.colorbox({
                    initialHeight: '0',
                    initialWidth: '0',
                    href: "#confirm_dialog",
                    inline: true,
                    opacity: '0.3',
                    onComplete: function(){
                        $('.confirm_yes').click(function(e){
                            e.preventDefault();
                            $('input[name=row_sel]:checked', '#'+tableid).closest('tr').fadeTo(300, 0, function () { 
                                $(this).remove();
                                $('.select_rows','#'+tableid).attr('checked',false);
                            });
                            $.colorbox.close();
                        });
                        $('.confirm_no').click(function(e){
                            e.preventDefault();
                            $.colorbox.close(); 
                        });
                    }
                });
            }
		});
	},
    dt: function() {
		$(".delete_rows_dt").on('click',function (e) {
			e.preventDefault();
			var tableid = $(this).data('tableid'),
                oTable = $('#'+tableid).dataTable();
            if($('input[name=row_sel]:checked', '#'+tableid).length) {
                $.colorbox({
                    initialHeight: '0',
                    initialWidth: '0',
                    href: "#confirm_dialog",
                    inline: true,
                    opacity: '0.3',
                    onComplete: function(){
                        $('.confirm_yes').click(function(e){
                            e.preventDefault();
                            $('input[name=row_sel]:checked', oTable.fnGetNodes()).closest('tr').fadeTo(300, 0, function () {
                                $(this).remove();
								oTable.fnDeleteRow( this );
                                $('.select_rows','#'+tableid).attr('checked',false);
                            });
                            $.colorbox.close();
                        });
                        $('.confirm_no').click(function(e){
                            e.preventDefault();
                            $.colorbox.close(); 
                        });
                    }
                });
            }    
		});
	}
};

//* gallery table view
common_m1320_table = {
    init: function() {
    	var server_detail_table = $('#server_detail').dataTable({
    		"sDom": "<'row'<'span6'<'dt_actions'>l><'span6'f>r>t<'row'<'span6'i><'span6'p>>",
    		"scrollX": true,
    		"aoColumnDefs": [//各列的一些个性显示信息
				{
					sDefaultContent: '',
					aTargets: [ '_all' ]
				},
				{
					orderable:false,//禁用排序
					aTargets: [0,2,3,4,5,6,7,8,9,11]
				}
			],
			"ajax":{url:ctx+"/m1320/queryServer.do"},//用于在m13*0_query中获取到ajax的data
    		"sAjaxDataProp" : "rows",//ajax获取数据的来源，对应服务端返回的数据对象
    		"sAjaxDataProp" : "rows",//ajax获取数据的来源，对应服务端返回的数据对象
			"sPaginationType": "full_numbers",
            "aaSorting": [[ 1, "desc" ]],
            "bSortClasses":false,//取消排序列的sorting_1样式，不显示颜色
    		"searching":true,//搜索窗口
    		"bServerSide": true,//指定从服务器端获取数据    
    		"sAjaxSource": ctx+"/m1320/queryServer.do",  //获取数据的url (一般是action)   
    		'bLengthChange': true,//每页显示的数量
    		'fnHeaderCallback':function(){//用于在每次draw发生时，修改table的heade
    			$(".select_rows").removeAttr("checked");;
    		},
    		"fnServerData": function ( sSource, aoData, fnCallback, oSettings ) {
			      oSettings.jqXHR = $.ajax( {
			        "dataType": 'json',
			        "type": "POST",
			        "url": sSource,
			        "data": aoData,
			        "success": fnCallback
			      } );
		    },
    		'fnRowCallback':function(obj,data){//行事件触发，用于处理选中
    			if(chooseServerList.indexOf(data.serverId)>=0){//id被选中
    				$(obj).find(".select_row").attr("checked","checked");
    			}
    			//alert('行事件触发');
    		},
    		"createdRow": function ( row, data, index ) {//构建rowId
    	            $(row).attr("id",data.serverId);
    	            $(row).attr("onclick","selectRow(this)");
    	    },
    		"aoColumns" :[
    		              { "sTitle":'<input type="checkbox" name="select_rows" class="select_rows"   data-tableid="server_detail" />',"mDataProp": "checkBox",
    		            	"sClass": "center table_checkbox",
    		            	"bSearchable": false,
    		            	"mRender": function(data, type, full) { 
    		            		var str="";
    		            		  if(full.sysStatus==1){
    		            			  str ="<input type=\"checkbox\" disabled  id=\"check_"+full.serverId+"\" onclick='chooseThis("+full.serverId+",this.checked)' />";			   
    		            		  }else{
    		            			  str ="<input type=\"checkbox\" name=\"row_sel\" id=\"check_"+full.serverId+"\" onclick='chooseThis("+full.serverId+",this.checked)' name=\"row_sel\" class=\"select_row\" />";
    		            		  }
	    		                 
	    		                  return str;  
	    		            }
	    		          },
    		              { "sTitle":"流水","mDataProp": "createAccept" ,"sName": "create_accept","bSearchable": false,"sClass": "center"},
    		              { "sTitle":"名称","mDataProp": "serverName" ,"sName": "server_name","sClass": "center"},
    		              { "sTitle":"IP","mDataProp": "ipAddress" ,"sName": "ip_address","sClass": "center"},
    		              
    		              //{ "sTitle":"操作系统","mDataProp": "operSystem" ,"bSearchable": false,"sClass": "center"},
    		              //{ "sTitle":"内核版本","mDataProp": "sysKernel" ,"bSearchable": false,"sClass": "center"},
    		              //{ "sTitle":"文件系统","mDataProp": "fileHandles" ,"bSearchable": false,"sClass": "center"},
    		              //{ "sTitle":"cpu型号","mDataProp": "cpuModel" ,"bSearchable": false,"sClass": "center"},
    		              //{ "sTitle":"主频","mDataProp": "cpuFreq" ,"bSearchable": false,"sClass": "center"},
    		              //{ "sTitle":"机房","mDataProp": "centerName" ,"bSearchable": false,"sClass": "center"},
    		              //{ "sTitle":"运营商","mDataProp": "operator" ,"bSearchable": false,"sClass": "center"},
    		              { "sTitle":"位置","mDataProp": "location" ,"bSearchable": false,"sClass": "center"},
    		              { "sTitle":"流量限制","mDataProp": "bandWidth" ,"bSearchable": false,"sClass": "center"},
    		              { "sTitle":"费用","mDataProp": "rate" ,"bSearchable": false,"sClass": "center"},
    		              { "sTitle":"激活时间","mDataProp": "activeTimestamp" ,"bSearchable": false,"sClass": "center", "sWidth" : "120px"},
    		              { "sTitle":"状态","mDataProp": "sysStatus" ,"bSearchable": false,"sClass": "center","mRender": function(data, type, full) {  
		    		            	  if(full.sysStatus==0){
				            			  return "未激活";
				            		  } else if (full.sysStatus==1){
				            			  return "已激活";
				            		  }
				            		  else if (full.sysStatus==9){
				            			  return "锁定";
				            		  }
		    		            }
    		              },
    		              { "sTitle":"系统分组","mDataProp": "belongGroupName" ,"bSearchable": false,"sClass": "center"},
    		              { "sTitle":"操作时间","mDataProp": "updateTimestamp" ,"sName": "update_timestamp","bSearchable": false,"sClass": "center","sWidth" : "120px"},
    		              //{ "sTitle":"备注","mDataProp": "opNote" ,"bSearchable": false,"sClass": "center"},
    		              { //自定义列
    		            	  "sTitle":"操作",
    		            	  "sName": "oper",
    		            	  "sClass": "left",
    		            	  "bSearchable": false,
    		            	  "sWidth" : "70px",
    		            	  "mRender": function (data, type, full) {

    		            		  var str = '';
    		            		  str+=' <a href="#" class="sepV_a" onclick="showUpdateFrame(\''+full.ipAddress+'\',event)" title="编辑"><i class="icon-pencil"></i></a>';
    		            		  str+=' <a href="#" class="sepV_a" onclick="showViewFrame(\''+full.ipAddress+'\',event)" title="查看"><i class="icon-eye-open"></i></a>';
    		            		  str+=' <a href="#" onclick="showDelFrame('+full.serverId+',\''+full.ipAddress+'\',event)" title="删除"><i class="icon-trash"></i></a>';
    		            		  if(full.sysStatus==0){
    		            			  str+=' <a href="#" class="serverActive" title="激活" onclick="activeServ('+full.serverId+');"><i class="icon-hand-right"></i></a>';
    		            		  }
    		            		  
    		            		  return str;
    	                      }
    		              }
    		          ],
        
          "oLanguage": {
                "sLengthMenu": "每页显示 _MENU_条",
                "sZeroRecords": "没有找到符合条件的数据",
                "sProcessing": "<img src=’./loading.gif’ />",
                "sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
                "sInfoEmpty": "没有记录",
                "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
                "sSearch": "搜索：",
            	"oPaginate": {
	            	"sFirst": "首页",
	            	"sPrevious": "前一页",
	            	"sNext": "后一页",
	            	"sLast": "尾页"
		        }
			}
    	});
       $('.dt_actions').html($('.server_detail_actions').html());
    }
};
function showDelFrame(id,ipAddress,e){
	e.stopPropagation();//阻止冒泡
	layer.confirm('确定删除该服务器吗？', {icon: 3,title:'提示', offset: '10px'}, function(index){
		layer.close(index);	
		layer.load(2);
		$.ajax( {   
	        "url": ctx+'/m1320/delServer.do',   
	        "type": "POST",     
	        "dataType": "json",   
	        "data": {'serverMsg.ipAddress':ipAddress}, //以json格式传递   
	        "success": function(data) {   
	           layer.closeAll('loading');
	           if(data.retCode=='0000'){//激活成功
	        	   
	        	   layer.alert("删除成功",{icon: 1,title:'提示', offset: '10px',end:function(){
	        		   window.location.reload();
	        	   }});
	        	   
	           }else{
	        	   layer.alert("删除失败！原因：【"+data.retMsg+"】，请联系管理员或稍后重试！",{icon: 2,title:'提示', offset: '10px'});	
	           }
	        },
	        "error":function(){
	        	layer.closeAll('loading');
	        }
	    });   
	});

}
function showUpdateFrame(ipAddress,e){
	e.stopPropagation();//阻止冒泡
	layer.open({
        type: 2,
        skin: 'layui-layer-rim',
        title: '服务器修改',
        shift:1,//动画效果
        //shadeClose: true,
        maxmin: true,
        area: ['800px','505px'],
        offset: '10px',
        moveType:1,
        content: ctx+'/m1320/updateServer.do?serverMsg.ipAddress='+ipAddress
    });

}
function showViewFrame(ipAddress,e){
	e.stopPropagation();//阻止冒泡
	layer.open({
        type: 2,
        skin: 'layui-layer-rim',
        title: '服务器查看',
        shift:1,//动画效果
        //shadeClose: true,
        maxmin: true,
        area: ['1100px','565px'],
        offset: '10px',
        moveType:1,
        content: ctx+'/m1320/queryServerDetail.do?serverMsg.ipAddress='+ipAddress
    });

}

function showAddFrame(){
	layer.open({
        type: 2,
        skin: 'layui-layer-rim',
        title: '服务器增加',
        shift:1,//动画效果
        //shadeClose: true,
        maxmin: true,
        area: ['800px','510px'],
        offset: '10px',
        moveType:1,
        content: ctx+'/pages/m1320/m1320_main_add.jsp'
    });

}
function showQueryFrame(){
	layer.open({
        type: 2,
        skin: 'layui-layer-rim',
        title: '服务器查询',
        shift:1,//动画效果
        //shadeClose: true,
        maxmin: true,
        area: ['800px','380px'],
        offset: '10px',
        moveType:1,
        content: ctx+'/pages/m1320/m1320_main_query.jsp'
    });

}
function showImpFrame(){
	layer.open({
        type: 2,
        skin: 'layui-layer-rim',
        title: '服务器导入',
        shift:1,//动画效果
        //shadeClose: true,
        maxmin: true,
        area: ['800px','200px'],
        offset: '10px',
        moveType:1,
        content: ctx+'/pages/m1320/m1320_main_imp.jsp'
    });

}
function showBatchActiveFrame(){
	if(!chooseServerList||chooseServerList==""){
		layer.alert("请选择服务器",{icon: 5, title:'提示',offset: '10px'});
	}else{
		layer.confirm('确定激活这些服务器吗？', {icon: 3,title:'提示', offset: '10px'}, function(index){
			layer.close(index);	
			layer.load(2);
			$.ajax( {   
		        "url": ctx+'/m1320/activeServer.do',   
		        "type": "POST",     
		        "dataType": "json",   
		        "data": {serverIdList:chooseServerList}, //以json格式传递   
		        "success": function(data) {   
		           layer.closeAll('loading');
		           var obj = data.retMsg.retObj;
		           if(data.retCode=='0000'&&data.retMsg.fail==0){//激活成功
		        	   
		        	   layer.alert("激活成功",{icon: 1,title:'提示', offset: '10px',end:function(){
		        		   
			        	   for(var i=0;i<obj.length;i++){
			        		   $("#"+obj[i].serverId+" td").eq(0).html("<input type=\"checkbox\" disabled  id=\"check_"+obj[i].serverId+"\" onclick='chooseThis("+obj[i].serverId+",this.checked)' />");
			        		   $("#"+obj[i].serverId+" td").eq(7).html(obj[i].activeTimestamp);
			        		   $("#"+obj[i].serverId+" td").eq(10).html(obj[i].updateTimestamp);
			        		   $("#"+obj[i].serverId+" td").eq(8).html("已激活");
			        		   $("#"+obj[i].serverId+" td").eq(11).find(".serverActive").remove();
			        	   }
			        	   chooseServerList="";
		        	   }});
		        	   
		           }else{
		        	   layer.alert("部分服务器激活失败！原因：【"+data.retMsg.failMsg+"】",{icon: 3,title:'提示', offset: '10px'});	
		        	   for(var i=0;i<obj.length;i++){
		        		   $("#"+obj[i].serverId+" td").eq(0).html("<input type=\"checkbox\" disabled  id=\"check_"+obj[i].serverId+"\" onclick='chooseThis("+obj[i].serverId+",this.checked)' />");
		        		   $("#"+obj[i].serverId+" td").eq(8).html(obj[i].activeTimestamp);
		        		   $("#"+obj[i].serverId+" td").eq(11).html(obj[i].updateTimestamp);
		        		   $("#"+obj[i].serverId+" td").eq(9).html("已激活");
		        		   $("#"+obj[i].serverId+" td").eq(12).find(".serverActive").remove();
		        	   }
		        	   chooseServerList="";
		           }
		        },
		        "error":function(){
		        	layer.closeAll('loading');
		        }
		    });   
		});
	}

}
function retrieveData( sSource, aoData, fnCallback ) {   
    //将客户名称加入参数数组   
    //aoData.push( { "name": "customerName", "value": $("#customerName").val() } );   
  
    $.ajax( {   
        "type": "POST",    
        "contentType": "application/json",   
        "url": sSource,    
        "dataType": "json",   
        "data": JSON.stringify(aoData), //以json格式传递   
        "success": function(resp) {   
            fnCallback(resp.rows); //服务器端返回的对象的returnObject部分是要求的格式  
        }   
    });   
} 
/**
 * 单个服务器激活
 * @param servId
 */
function activeServ(servId){

	layer.confirm('确定激活该服务器吗？', {icon: 3,title:'提示', offset: '10px'}, function(index){
		layer.close(index);	
		layer.load(2);
		$.ajax( {   
	        "url": ctx+'/m1320/activeServer.do',   
	        "type": "POST",     
	        "dataType": "json",   
	        "data": {serverIdList:servId}, //以json格式传递   
	        "success": function(data) {   
	           layer.closeAll('loading');
	           var obj = data.retMsg.retObj;
	           if(data.retCode=='0000'&&data.retMsg.fail==0){//激活成功
	        	   
	        	   layer.alert("激活成功",{icon: 1,title:'提示', offset: '10px',end:function(){
	        		   
		        	   for(var i=0;i<obj.length;i++){
		        		   $("#"+obj[i].serverId+" td").eq(0).html("<input type=\"checkbox\" disabled  id=\"check_"+obj[i].serverId+"\" onclick='chooseThis("+obj[i].serverId+",this.checked)' />");
		        		   $("#"+obj[i].serverId+" td").eq(7).html(obj[i].activeTimestamp);
		        		   $("#"+obj[i].serverId+" td").eq(10).html(obj[i].updateTimestamp);
		        		   $("#"+obj[i].serverId+" td").eq(8).html("已激活");
		        		   $("#"+obj[i].serverId+" td").eq(11).find(".serverActive").remove();
		        	   }
	        	   }});
	        	   
	           }else{
	        	   layer.alert("激活失败！原因：【"+data.retMsg.failMsg+"】，请联系管理员或稍后重试！",{icon: 2,title:'提示', offset: '10px'});	
	        	   $("#"+obj[0].serverId+" td").eq(12).find(".serverActive").removeAttr("disabled");
	           }
	        },
	        "error":function(){
	        	layer.closeAll('loading');
	        }
	    });   
	});

}

/**
 * 列表单个选中事件
 * @param serverId
 */
function chooseThis(serverId,checked){
	if(checked){//选中
		if(chooseServerList==""){
			chooseServerList+=serverId;
		}else{
			chooseServerList+=","+serverId;
		}
	}else{//取消
		if(chooseServerList==''+serverId){
			chooseServerList="";
			return;
		}
		if(chooseServerList.indexOf(serverId)==0){//开始位置
			chooseServerList = chooseServerList.replace(serverId+",","");
		}else{
			chooseServerList = chooseServerList.replace(","+serverId,"");
		}
	}
}


/**
*行选中事件
*/
function selectRow(obj){
	$(obj).toggleClass("highlight_choose");
}