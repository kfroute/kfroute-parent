	function submitMerchant(){
		var id=$('#merchantId').val();
		if(id==""||id==null||id==1){
			addMerchant();
		}else{
			updateMerchant();
		}
	}
	
	function addMerchant(){
		alert("add");
		var manage=$('#loginName').val();
		var merchantCode=$('#merchantCode').val();
		var parentCode=$('#parentCode').val();
		var merchantName=$('#merchantName').val();
		var telephone=$('#phone').val();
		var openingTime=$('#openingTime').val();
		var city=$('#city').val();
		var address=$('#address').val();
		var status=$('#status option:selected').val();
		var introduce=$('#introduce').val();
		var tude=$('#tude').val();
		if(manage==""||manage==null||merchantCode==""||merchantCode==null){
			alert("登录账号和商户编码不能为空");
			return;
		}
		$.ajax({
			timeout : 150000,
			async   : true,
			cache   : false,
			type    : 'POST',
			dataType: 'json',
			data    :{
				'manage':manage,
				'merchantCode':merchantCode,
				'parentCode':parentCode,
				'merchantName':merchantName,
				'telephone':telephone,
				'openingTime':openingTime,
				'city':city,
				'address':address,
				'status':status,
				'introduce':introduce,
				'tude':tude
			},
			url     : ctx+'/merchant!addMerchant.do',
			success : function(data){
				var result=data.result;
				if(result==0){
					alert("登录账号或商户编码已经存在");
				}else if(result==1){
					window.open(ctx+"/pages/merchant/merchant.jsp","_self");
				}
				
			},
			error : function(){
				alert("添加失败");
			}
		});
		
		
	}
	
	function updateMerchant(){
		var id=$('#merchantId').val();
		var manage=$('#loginName').val();
		var merchantCode=$('#merchantCode').val();
		var parentCode=$('#parentCode').val();
		var merchantName=$('#merchantName').val();
		var telephone=$('#phone').val();
		var openingTime=$('#openingTime').val();
		var city=$('#city').val();
		var address=$('#address').val();
		var status=$('#status option:selected').val();
		var introduce=$('#introduce').val();
		var tude=$('#tude').val();
		if(manage==""||manage==null||merchantCode==""||merchantCode==null){
			alert("登录账号和商户编码不能为空");
			return;
		}
		$.ajax({
			timeout : 150000,
			async   : true,
			cache   : false,
			type    : 'POST',
			dataType: 'json',
			data    :{
				'id':id,
				'manage':manage,
				'merchantCode':merchantCode,
				'parentCode':parentCode,
				'merchantName':merchantName,
				'telephone':telephone,
				'openingTime':openingTime,
				'city':city,
				'address':address,
				'status':status,
				'introduce':introduce,
				'tude':tude
			},
			url     : ctx+'/merchant!update.do',
			success : function(data){
				var result=data.result;
				if(result==0){
					alert("商户编码重复");
				}else if(result==1){
					window.open(ctx+"/pages/merchant/merchant.jsp","_self");
				}
				
			},
			error : function(){
				alert("添加失败");
			}
		});
	}
	
	$(function(){
		var id=$('#merchantId').val();
		if(id==1||id==""||id==null){
			
		}else{
			document.getElementById('loginName').readOnly=true;
			document.getElementById('merchantCode').readOnly=true;
			$.ajax({
				timeout : 150000,
				async   : true,
				cache   : false,
				type    : 'POST',
				dataType: 'json',
				data    :{
					'id':id
				},
				url     : ctx+'/merchant!findById.do',
				success : function(data){
					var result=data.result;
					var obj=data.merchant;
					$('#merchantId').val(obj.id);
					$('#loginName').val(obj.manage);
					$('#merchantCode').val(obj.merchantCode);
					$('#parentCode').val(obj.parentCode);
					$('#merchantName').val(obj.merchantName);
					$('#phone').val(obj.telephone);
					$('#openingTime').val(obj.openingTime);
					$('#city').val(obj.city);
					$('#address').val(obj.address);
					$('#status option:selected').val(obj.status);
					$('#introduce').val(obj.introduce);
					if(obj.longitude!=null||obj.longitude!=undefined||obj.longitude!=""){
						$('#tude').val(obj.longitude+","+obj.latitude);
					}
					
				},
				error : function(){
					alert("添加失败");
				}
			});
			
			
		}
	});
	
	
	
	function choseMap(data){
		$('#mapArea').css("display","block");
	}
	
	var map = new BMap.Map("allmap");    // 创建Map实例
	var icon = new BMap.Icon(resourcePath+'/image/pin.png', new BMap.Size(20, 32), {
		anchor: new BMap.Size(10, 30)}
	);
	// 百度地图API功能
	map.centerAndZoom(new BMap.Point(117.284999,31.879698), 12);  // 初始化地图,设置中心点坐标和地图级别
	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
	map.setCurrentCity("合肥");          // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	var marker;
	var center;
	map.addEventListener("click", function(e){
		if(marker!=null&&marker!=""){
			map.removeOverlay(marker);
		}
		
		marker= new BMap.Marker(new BMap.Point(e.point.lng, e.point.lat),{
			icon:icon
		});  
		map.addOverlay(marker);
		$('#tude').val(e.point.lng+","+e.point.lat);
	});
	function theLocation(){
		var city = document.getElementById("cityName").value;
		if(city != ""){
			map.centerAndZoom(city,11);      // 用城市名设置地图中心点
			
		}
	}

	
	