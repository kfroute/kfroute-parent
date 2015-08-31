<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 	<%@include file="/pages/common/taglib.jsp"%>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=server-width, initial-scale=1.0" />
		<title>服务器地图查看</title>

		<link rel="stylesheet" href="${res}/common/css/style_tab.css"  />
		<link rel="stylesheet" href="${res}/common/css/input_tables.css"  />
 		<script src="${res}/common/js/m1320/m1320_map_view.js" ></script>
 		<style>
 			
		    #map {
		        height: 100%;
		    }
 		</style>
 		
	</head>
	<body>
		<div id="map"></div>
		<script type="text/javascript">	
			var latitude = ${param.latitude};
			var longitude = ${param.longitude};
			var map;
			//function initMap() {
			//	map = new google.maps.Map(document.getElementById('map'), {
			//		center: {lat: latitude, lng: longitude},
			//		zoom: 8
			//	});
			//}
			function initMap() {
				  var location = new google.maps.LatLng(latitude, longitude);
	
				  var map = new google.maps.Map(document.getElementById('map'), {
					  scaleControl: true,
					  center: location,
					  zoom: 8
				  });
				  console.log(location);
				  var coordInfoWindow = new google.maps.InfoWindow();
				  coordInfoWindow.setContent(createInfoWindowContent(location, map.getZoom()));
				  coordInfoWindow.setPosition(location);
				  coordInfoWindow.open(map);
	
				  map.addListener('zoom_changed', function() {
				    coordInfoWindow.setContent(createInfoWindowContent(location, map.getZoom()));
				    coordInfoWindow.open(map);
				  });
				  var infowindow = new google.maps.InfoWindow;
				  infowindow.setContent('<b>${param.cityName}</b>');

				  var marker = new google.maps.Marker({map: map, position: location});
				  marker.addListener('click', function() {
				    infowindow.open(map, marker);
				  });
			}
			var TILE_SIZE = 256;

			function createInfoWindowContent(latLng, zoom) {
				  var scale = 1 << zoom;
	
				  var worldCoordinate = project(latLng);
	
				  var pixelCoordinate = new google.maps.Point(
				      Math.floor(worldCoordinate.x * scale),
				      Math.floor(worldCoordinate.y * scale));
	
				  var tileCoordinate = new google.maps.Point(
				      Math.floor(worldCoordinate.x * scale / TILE_SIZE),
				      Math.floor(worldCoordinate.y * scale / TILE_SIZE));
	
				  return [
					'城市: ' + '${param.cityName}',
				    '经纬度: ' + latLng,
				    '显示级别: ' + zoom,
				    '世界坐标: ' + worldCoordinate,
				    '机房名称: ' + '${param.centerName}',
				    '运营商: ' + '${param.operator}'
				  ].join('<br>');
			}
			
			// The mapping between latitude, longitude and pixels is defined by the web
			// mercator projection.
			function project(latLng) {
			  var siny = Math.sin(latLng.lat() * Math.PI / 180);

			  // Truncating to 0.9999 effectively limits latitude to 89.189. This is
			  // about a third of a tile past the edge of the world tile.
			  siny = Math.min(Math.max(siny, -0.9999), 0.9999);

			  return new google.maps.Point(
			      TILE_SIZE * (0.5 + latLng.lng() / 360),
			      TILE_SIZE * (0.5 - Math.log((1 + siny) / (1 - siny)) / (4 * Math.PI)));
			}
		</script>
		<script async defer
			src="https://maps.googleapis.com/maps/api/js?signed_in=true&callback=initMap">
		</script>
		<br/><br/><br/><br/>
		<div class="modal-footer" style="position:fixed;width:100%;margin-left:auto;margin-right:auto;bottom:0px;text-align:center;">
			<a href="javascript:history.back()" class="back_link btn btn-small">返回</a>
		</div>
	</body>
</html>