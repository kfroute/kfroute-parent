/**
 * add by zhangyl at 2015-08-03 for m1320_main.jsp
 */
var table;
var oTable;
$(function(){
	//* datatable must be rendered first
    common_m1320_port_table.init();

	
	$(".input-table tbody tr th").addClass("sorting_1");
	$("#server_port_view_tab_filter label input").attr("placeholder","输入端口号快速检索");
	var lastIdx = null;
    table = $('#server_port_view_tab').DataTable();
    $('#server_port_view_tab tbody')
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



//* gallery table view
common_m1320_port_table = {
    init: function() {
    	var server_port_view_table = $('#server_port_view_tab').dataTable({
    		"sDom": "<'row'<'span6'<'dt_actions'>l><'span6'f>r>t<'row'<'span6'i><'span6'p>>",
    		"aoColumnDefs": [//各列的一些个性显示信息
				{
					sDefaultContent: '',
					aTargets: [ '_all' ]
				},
				{
					orderable:false,//禁用排序
					aTargets: [0,2,3,4,6]
				}
			],
			"ajax":{url:ctx+"/m1320/queryServerPortDetail.do?ipAddress="+ipAddress},//用于在m13*0_query中获取到ajax的data
    		"sAjaxDataProp" : "rows",//ajax获取数据的来源，对应服务端返回的数据对象
    		"sAjaxDataProp" : "rows",//ajax获取数据的来源，对应服务端返回的数据对象
			"sPaginationType": "full_numbers",
            "aaSorting": [[ 1, "asc" ]],
            "bSortClasses":false,//取消排序列的sorting_1样式，不显示颜色
    		"searching":true,//搜索窗口
    		"bServerSide": true,//指定从服务器端获取数据    
    		"sAjaxSource": ctx+"/m1320/queryServerPortDetail.do?serverPortMsg.sourceIp="+ipAddress,  //获取数据的url (一般是action)   
    		'bLengthChange': true,//每页显示的数量
    		"fnServerData": function ( sSource, aoData, fnCallback, oSettings ) {
			      oSettings.jqXHR = $.ajax( {
			        "dataType": 'json',
			        "type": "POST",
			        "url": sSource,
			        "data": aoData,
			        "success": fnCallback
			      } );
		    },
    		"createdRow": function ( row, data, index ) {//构建rowId
    	            $(row).attr("id",data.serverId);
    	            $(row).attr("onclick","selectRow(this)");
    	    },
    		"aoColumns" :[
    		              
    		              { "sTitle":"ip","mDataProp": "sourceIp" ,"sName": "source_ip","bSearchable": false,"sClass": "center"},
    		              { "sTitle":"端口","mDataProp": "port" ,"sName": "port","sClass": "center"},
    		              { "sTitle":"密码","mDataProp": "password" ,"bSearchable": false,"sClass": "center"},
    		              { "sTitle":"状态","mDataProp": "useFlag" ,"bSearchable": false,"sClass": "center","mRender": function(data, type, full) {  
	    		            	  if(full.useFlag==1){
			            			  return "正常";
			            		  } else if (full.useFlag==0){
			            			  return "关闭";
			            		  } else{
			            			  return "锁定";
			            		  }
	    		            }
    		              },
    		              { "sTitle":"超时时间","mDataProp": "timeOut" ,"bSearchable": false,"sClass": "center"},
    		              { "sTitle":"操作时间","mDataProp": "updateTime" ,"sName": "update_time","bSearchable": false,"sClass": "center"},
    		              { "sTitle":"备注","mDataProp": "updateDesc" ,"bSearchable": false,"sClass": "center"}
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
    }
};

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