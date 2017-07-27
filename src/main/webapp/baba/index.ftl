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
			var map = new AMap.Map('map-container',{
				resizeEnable: true,
			    mapStyle: 'amap://styles/a1f44375a8dc3c976b90fb6165bf901b'
			});
			
			map.setZoom(14);
			
			AMap.plugin(['AMap.Scale', 'AMap.OverView', 'AMap.MapType', 'AMap.Geolocation'],
			    function(){
			        map.addControl(new AMap.Scale());
			        map.addControl(new AMap.OverView({isOpen:true}));
			        map.addControl(new AMap.MapType());
			     	var geolocation = new AMap.Geolocation({
			     		enableHighAccuracy: true,//是否使用高精度定位，默认:true
			        	timeout: 10000,          //超过10秒后停止定位，默认：无穷大
            			showButton: true,        //显示定位按钮，默认：true
            			buttonOffset: new AMap.Pixel(10, 118),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
            			buttonPosition:'RT',
            			showMarker: true,        //定位成功后在定位到的位置显示点标记，默认：true
		                showCircle: true,        //定位成功后用圆圈表示定位精度范围，默认：true
		                panToLocation: true,     //定位成功后将定位到的位置作为地图中心点，默认：true
            			zoomToAccuracy: true      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
			        });
			        map.addControl(geolocation);
			        AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
	        		AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
			});
			
			function onComplete(data) {
				alert('定位成功');
				map.setZoom(13);
		    }
		    //解析定位错误信息
		    function onError(data) {
		    	alert('定位失败');
		    }
		    
		    if (document.createElement('canvas') && document.createElement('canvas').getContext && document.createElement('canvas').getContext('2d')) {
		        var buildings = new AMap.Buildings();
		        buildings.setMap(map);
		    } else {
		        document.getElementById('tip').innerHTML = "对不起，运行该示例需要浏览器支持HTML5！";
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