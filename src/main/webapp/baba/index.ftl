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
			    mapStyle: 'amap://styles/a1f44375a8dc3c976b90fb6165bf901b',
			    zoom: 14
			});
			
			map.plugin('AMap.Geolocation', function() {
			    geolocation = new AMap.Geolocation({
			        enableHighAccuracy: true,//是否使用高精度定位，默认:true
			        timeout: 10000,          //超过10秒后停止定位，默认：无穷大
			        buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
			        zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
			        noIpLocate: 2,
			        buttonPosition:'RT'
			    });
			    map.addControl(geolocation);
			    geolocation.getCurrentPosition();
			    AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
			    AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
			});
			//解析定位结果
			function onComplete(data) {
			    var str=['定位成功'];
			    str.push('经度：' + data.position.getLng());
			    str.push('纬度：' + data.position.getLat());
			    if(data.accuracy){
			         str.push('精度：' + data.accuracy + ' 米');
			    }//如为IP精确定位结果则没有精度信息
			    str.push('是否经过偏移：' + (data.isConverted ? '是' : '否'));
			    map.setZoom(14);
			}
			//解析定位错误信息
			function onError(data) {
			     alert('定位失败');
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