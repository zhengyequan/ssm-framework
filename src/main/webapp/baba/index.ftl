<!DOCTYPE html>
<html>
<head>
	<title>首页</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
	<link rel="stylesheet" type="text/css" href="../baba/css/index.css">
	<script type="text/javascript" src="../resources/js/jquery.min.js"></script>
	<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.3&key=0829386e87eb80d8fddf8deb214ce617"></script>
	<script type="text/javascript">
		$(function(){
			var map = new AMap.Map('map-container', {
				resizeEnable: true,
			    mapStyle: 'amap://styles/a1f44375a8dc3c976b90fb6165bf901b',
			    zoom: 12
			});
			
			map.on('complete', onComplete);
			map.on('moveend', onComplete);
			function onComplete() {
				var bounds = map.getBounds();
				var southwestLng = bounds.southwest.lng;
				var southwestLat = bounds.southwest.lat;
				var northeastLng = bounds.northeast.lng;
				var northeastLat = bounds.northeast.lat;
				
				alert('西南：[' + southwestLng + ', ' + southwestLat + ']');
				alert('东北：[' + northeastLng + ', ' + northeastLat + ']');
		    }
		});
	</script>
	<style>
		.amap-simple-marker.my-marker .amap-simple-marker-label {
	        color: #000;
	        font-style: italic;
	        text-decoration: line-through;
	    }
	</style>
</head>
<body>
	<header>
		<div class="first-ad"><img src="../baba/img/first_ad.png"/></div>
		<div class="second-ad"><img src="../baba/img/second_ad.png"/></div>
	</header>
	<div class="back-main"><img class="logo-main" src="../baba/img/logo_main.png"/><div class="visitor-count">今日访问量：<span>88888</span></div></div>
	<nav><input class="search-content" placeholder="&nbsp;&nbsp;&nbsp;输入地区、楼盘" /><input class="btn-search" value="搜索" readonly></input></nav>
	<div class="main-content">
		<div class="img-parent">
			<img class="img-item" src="../baba/img/img_demo.png"/><img class="img-item" src="../baba/img/img_demo.png"/>
			<img class="img-item" src="../baba/img/img_demo.png"/><img class="img-item" src="../baba/img/img_demo.png"/>
			<img class="img-item" src="../baba/img/img_demo.png"/><img class="img-item" src="../baba/img/img_demo.png"/>
		</div>
		<div id="map-container" class="map-parent"></div>
	</div>
	<div></div>
</body>
</html>