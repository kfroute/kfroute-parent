/**
 * add by zhangyl at 2015-08-03 for m1310_main.jsp
 */
var chooseDeviceList = "";
var table;
var oTable;
$(function(){
	//* datatable must be rendered first
    common_m1310_table.init();
    //* actions for tables, datatables
    common_select_row.init();
	common_delete_rows.simple();
	common_delete_rows.dt();
	//$('#dt_gal').dataTable();
	
	$(".input-table tbody tr th").addClass("sorting_1");
	$("#device_detail_filter label input").attr("placeholder","输入MAC地址或归属信息快速检索");
	var lastIdx = null;
    table = $('#device_detail').DataTable();//oTable.api();//
    $('#device_detail tbody')
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
            	if((obj.attr('checked')=='checked')==this.checked){//当前列的选中状态已经和表头一致时，不做处理
            		continue;
            	}
            	obj.attr('checked',this.checked);
            	chooseThis(obj.attr('id').substring(6),obj.attr('checked'),event);
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
common_m1310_table = {
    init: function() {
    	oTable = $('#device_detail').dataTable({
    		"sDom": "<'row'<'span6'<'dt_actions'>l><'span6'f>r>t<'row'<'span6'i><'span6'p>>",
    		"scrollX": true,
    		"aoColumnDefs": [//各列的一些个性显示信息
				{
					sDefaultContent: '',
					aTargets: [ '_all' ]
				},
				{
					orderable:false,//禁用排序
					aTargets: [0,3,4,5,6,7,8,9,10,12]
				}
			],
			"ajax":{url:ctx+"/m1310/queryDevice.do"},//用于在m13*0_query中获取到ajax的data
    		"sAjaxDataProp" : "rows",//ajax获取数据的来源，对应服务端返回的数据对象
			"sPaginationType": "full_numbers",
            "aaSorting": [[ 1, "desc" ]],
            "bSortClasses":false,//取消排序列的sorting_1样式，不显示颜色
    		"searching":true,//搜索窗口
    		"bServerSide": true,//指定从服务器端获取数据    
    		"sAjaxSource": ctx+"/m1310/queryDevice.do",  //获取数据的url (一般是action)   
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
    		//'fnServerParams':function(aoData){//向服务器发送额外的数据
    			   
    		//},
    		'fnRowCallback':function(obj,data){//行事件触发，用于处理选中
    			if(chooseDeviceList.indexOf(data.rIdNo)>=0){//id被选中
    				$(obj).find(".select_row").attr("checked","checked");
    			}
    			//alert('行事件触发');
    		},
    		"createdRow": function ( row, data, index ) {//构建rowId
	            $(row).attr("onclick","selectRow(this)");
    		},
    		"aoColumns" :[
    		              { "sTitle":'<input type="checkbox" name="select_rows" class="select_rows" data-tableid="device_detail" />',"mDataProp": "checkBox",
    		            	"sClass": "center table_checkbox",
    		            	"bSearchable": false,
    		            	"mRender": function(data, type, full) { 
	    		                  var str ="<input type=\"checkbox\" name=\"row_sel\" id=\"check_"+full.rIdNo+"\" onclick='chooseThis("+full.rIdNo+",this.checked,event)' class=\"select_row\" />"; 
	    		                  return str;  
	    		            }
	    		          },
    		              { "sTitle":"流水","mDataProp": "createAccept" ,"sName": "create_accept","bSearchable": false,"sClass": "center"},
    		              { "sTitle":"MAC","mDataProp": "mac" ,"sName": "mac","sClass": "center"},
    		              { "sTitle":"型号","mDataProp": "modelType" ,"bSearchable": false,"sClass": "center"},
    		              { "sTitle":"版本","mDataProp": "version" ,"bSearchable": false,"sClass": "center"},
    		              { "sTitle":"归属类型","mDataProp": "belongType" ,"bSearchable": false,"sClass": "center","mRender": function (data, type, full) {
	    		            	  if(full.belongType==1){
	    		            		  return "自有渠道";
	    		            	  }else{
	    		            		  return "代理商渠道";
	    		            	  }
		                          
		                      }
    		              },
    		              { "sTitle":"归属","mDataProp": "groupName" ,"sName": "group_name","sClass": "center","mRender": function (data, type, full) {
    		            	  if(full.groupName==null||!full.groupName){
	    		            		  return "自有";
	    		            	  }else{
	    		            		  return full.groupName;
	    		            	  }
		                          
		                      }
			              },
    		              { "sTitle":"芯片型号","mDataProp": "chipModel" ,"bSearchable": false,"sClass": "center"},
    		              //{ "sTitle":"主频","mDataProp": "basicFreq" ,"bSearchable": false,"sClass": "center"},
    		              { "sTitle":"ram","mDataProp": "ram" ,"bSearchable": false,"sClass": "center"},
    		              //{ "sTitle":"flash","mDataProp": "flash" ,"bSearchable": false,"sClass": "center"},
    		              { "sTitle":"品牌","mDataProp": "brandName" ,"bSearchable": false,"sClass": "center"},           
    		              { "sTitle":"操作日志","mDataProp": "opNote" ,"bSearchable": false,"sClass": "center"},
    		              { "sTitle":"操作时间","mDataProp": "updateTimestamp" ,"sName": "update_timestamp","bSearchable": false,"sClass": "center"},
    		              { //自定义列
    		            	  "sTitle":"操作",
    		            	  "sName": "oper",
    		            	  "sClass": "center",
    		            	  "bSearchable": false,
    		            	  "mRender": function (data, type, full) {
    		            		  var str = '';
    		            		  str+=' <a href="#" class="sepV_a" title="编辑" onclick="showUpdateFrame(\''+full.mac+'\',event)"><i class="icon-pencil"></i></a>';
    		            		  str+=' <a href="#" title="删除" onclick="showDelFrame('+full.rIdNo+',\''+full.mac+'\',event)"><i class="icon-trash"></i></a>';
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
		
       $('.dt_actions').html($('.device_detail_actions').html());
      
    }
};

function showAddFrame(){
	layer.open({
        type: 2,
        skin: 'layui-layer-rim',
        title: '设备增加',
        shift:1,//动画效果
        //shadeClose: true,
        maxmin: true,
        area: ['800px','427px'],
        offset: '10px',
        moveType:1,
        content: ctx+'/pages/m1310/m1310_main_add.jsp'
    });

}
function showUpdateFrame(mac,e){
	e.stopPropagation();//阻止冒泡
	layer.open({
        type: 2,
        skin: 'layui-layer-rim',
        title: '设备修改',
        shift:1,//动画效果
        //shadeClose: true,
        maxmin: true,
        area: ['800px','427px'],
        offset: '10px',
        moveType:1,
        content: ctx+'/m1310/updateDevice.do?routerMsg.mac='+mac
    });

}
function showDelFrame(id,mac,e){
	e.stopPropagation();//阻止冒泡
	layer.confirm('确定删除该设备吗？', {icon: 3,title:'提示', offset: '10px'}, function(index){
		layer.close(index);	
		layer.load(2);
		$.ajax( {   
	        "url": ctx+'/m1310/delDevice.do',   
	        "type": "POST",     
	        "dataType": "json",   
	        "data": {'routerMsg.mac':mac}, //以json格式传递   
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
function showQueryFrame(){
	layer.open({
        type: 2,
        skin: 'layui-layer-rim',
        title: '设备查询',
        shift:1,//动画效果
        //shadeClose: true,
        maxmin: true,
        area: ['800px','430px'],
        offset: '10px',
        moveType:1,
        content: ctx+'/pages/m1310/m1310_main_query.jsp'
    });

}
function showBatchUpdateFrame(){
	if(!chooseDeviceList){
		layer.alert("请选择设备",{icon: 5, title:'提示',offset: '10px'});
	}else{
		layer.open({
	        type: 2,
	        skin: 'layui-layer-rim',
	        title: '设备批量修改',
	        shift:1,//动画效果
	        //shadeClose: true,
	        maxmin: true,
	        area: ['800px','480px'],
	        offset: '10px',
	        moveType:1,
	        content: ctx+'/m1310/batchUpdateDevice.do?deviceList='+chooseDeviceList
	    });
	}
	

}
function showImpFrame(){
	layer.open({
        type: 2,
        skin: 'layui-layer-rim',
        title: '设备增加',
        shift:1,//动画效果
        //shadeClose: true,
        maxmin: true,
        area: ['800px','200px'],
        offset: '10px',
        moveType:1,
        content: ctx+'/pages/m1310/m1310_main_imp.jsp'
    });

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
 * 列表单个选中事件
 * @param serverId
 */
function chooseThis(rIdNo,checked,e){
	e.stopPropagation();
	if(checked){//选中
		if(chooseDeviceList==""){
			chooseDeviceList+=rIdNo;
		}else{
			chooseDeviceList+=","+rIdNo;
		}
	}else{//取消
		if(chooseDeviceList==''+rIdNo){
			chooseDeviceList="";
			return;
		}
		if(chooseDeviceList.indexOf(rIdNo)==0){//开始位置
			chooseDeviceList = chooseDeviceList.replace(rIdNo+",","");
		}else{
			chooseDeviceList = chooseDeviceList.replace(","+rIdNo,"");
		}
	}
}
/**
*行选中事件
*/
function selectRow(obj){

	$(obj).toggleClass("highlight_choose");
}