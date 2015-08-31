/**
*sso首页js
*add by zhangyl at 2015-07-21 14:54
*copyright melinkr
*
*/

/**
 * This javascript contains fuctions 
 * @Vikram
 */
//this will hold currently focused tab
var currentTab;
var composeCount = 0;

$(function(){
	$("#mobile-nav li").click(function(){//左侧功能树选择
		$(this).children().css("color","white");
		$("#mobile-nav li a").not($(this).children()).css("color","#c1dce7");
		var _id = $(this).attr('id');
		$.ajax({
			type:"POST",
			url:"/sso/findLeftTreeData",
			data:{"functionCode":_id},
			dataType:"json",
			success:function(msg){
				var funcCodeFirstLevel = msg.firstLevelFunc;
				var funcCodeListMap = msg.funcList;
				//var _html="";
				$('#side_accordion .accordion-dynamic').remove();
				var zTree;
				var demoIframe;

				var setting = {
					view: {
						selectedMulti: false
					},
					data: {
						simpleData: {
							enable:true,
							idKey: "id",
							pIdKey: "pId",
							rootPId: ""
						}
					},
					callback: {
						beforeClick: function(treeId, treeNode) {
							var zTree = $.fn.zTree.getZTreeObj("left_tree");
							if (treeNode.isParent) {
								if(!treeNode){
									zTree.expandNode(treeNode);
								}
								return false;
							} else {
								//demoIframe.attr("src",treeNode.file + ".html");
								createTabAndOpen(treeNode.id,treeNode.funcName,treeNode.urlLink);
								return false;
							}
						}
					}
				};
				for(var i in funcCodeFirstLevel){
					var _func = funcCodeFirstLevel[i];
					var _functionCode = _func.functionCode;
					//var funcList =eval("funcCodeListMap."+_functionCode) ;//funcCodeList.key;
					var _html= '<div class="accordion-group accordion-dynamic">'+
									'<div class="accordion-heading">'+
										'<a href="#collapse'+_functionCode+'" data-parent="#side_accordion" data-toggle="collapse" class="accordion-toggle">'+
											'<i class="icon-folder-close"></i> '+_func.functionName+
										'</a>'+
									'</div>'+
									'<div class="accordion-body collapse" id="collapse'+_functionCode+'">'+
										'<div class="ztree" id="left_tree_'+_functionCode+'"></div>'+
									'</div>'+
								'</div>';
					$(_html).insertBefore("#accordion-tools");
					
					var funcList =eval("funcCodeListMap."+_functionCode) ;//funcCodeList.key;
					var zNodes = [];
					for(var i in funcList){
						var _funcCode = funcList[i];
						var _funcobj = { id:_funcCode.functionCode, pId:_funcCode.parentCode,funcName:_funcCode.functionName, name:_funcCode.functionName+'['+_funcCode.functionCode+']',urlLink:_funcCode.urlLink, open:(_funcCode.mainCode==0)};
						
						zNodes.push(_funcobj);
					}
					//ztree节点生成
					var t = $("#left_tree_"+_functionCode);
					t = $.fn.zTree.init(t, setting, zNodes);
					var zTree = $.fn.zTree.getZTreeObj("left_tree_"+_functionCode);
				}
				
				
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
		});
	});
	//when ever any tab is clicked this method will be call
    $("#contentwrapper .nav-tabs").on("click", "a", function (e) {
        e.preventDefault();

        $(this).tab('show');
        $currentTab = $(this);
    });

    registerCloseEvent();
});

function createTabAndOpen(funtionCode,functionName,urlLink){
     var tabId = "compose" + funtionCode; //this is id on tab content div where the 
     if($("#" + tabId).attr('id')){
    	 showTab(tabId);
    	 return;
     }
     if(!urlLink){
		 smoke.alert('节点链接地址为空，请联系管理员！');
		 return;
	}
     
     composeCount = composeCount + 1; //increment compose count

     $('#contentwrapper .nav-tabs').append('<li><a href="#' + tabId + '"><button class="close closeTab" type="button" >×</button>'+functionName+'</a></li>');
     $('#contentwrapper .tab-content').append('<div class="tab-pane" id="' + tabId + '"></div>');

     createNewTabAndLoadUrl("", urlLink, "#" + tabId);

     $(this).tab('show');
     showTab(tabId);
     registerCloseEvent();
}

//this method will register event on close icon on the tab..
function registerCloseEvent() {

    $(".closeTab").click(function () {

        //there are multiple elements which has .closeTab icon so close the tab whose close icon is clicked
        var tabContentId = $(this).parent().attr("href");
        $(this).parent().parent().remove(); //remove li of tab
        $('#contentwrapper .nav-tabs a:last').tab('show'); // Select first tab
        $(tabContentId).remove(); //remove respective tab content

    });
}

//shows the tab with passed content div id..paramter tabid indicates the div where the content resides
function showTab(tabId) {
    $('#contentwrapper .nav-tabs a[href="#' + tabId + '"]').tab('show');
}
//return current active tab
function getCurrentTab() {
    return currentTab;
}

//This function will create a new tab here and it will load the url content in tab content div.
function createNewTabAndLoadUrl(parms, url, loadDivSelector) {
	$("" + loadDivSelector).html("<iframe  frameborder='no' border='0' width='100%' height='"+document.body.scrollHeight+"'  marginwidth='0' marginheight='0' scrolling='auto' allowtransparency='yes' src='"+url+"'/>");

}

//this will return element from current tab
//example : if there are two tabs having  textarea with same id or same class name then when $("#someId") whill return both the text area from both tabs
//to take care this situation we need get the element from current tab.
function getElement(selector) {
    var tabContentId = $currentTab.attr("href");
    return $("" + tabContentId).find("" + selector);

}


function removeCurrentTab() {
    var tabContentId = $currentTab.attr("href");
    $currentTab.parent().remove(); //remove li of tab
    $('#contentwrapper .nav-tabs a:last').tab('show'); // Select first tab
    $(tabContentId).remove(); //remove respective tab content
}



