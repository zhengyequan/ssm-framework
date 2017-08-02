<!DOCTYPE html>
<html>
<head>
	<title>首页</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
	<link rel="stylesheet" type="text/css" href="../baba/css/index.css">
	<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.3&key=0829386e87eb80d8fddf8deb214ce617"></script>
	<script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.10"></script>
	<script type="text/javascript" src="../resources/js/jquery.min.js"></script>
	<script type="text/javascript">
		$(function(){
			var map = new AMap.Map('map-container', {
				resizeEnable: true,
			    mapStyle: 'amap://styles/a1f44375a8dc3c976b90fb6165bf901b',
			    zoom: 12<#if data??>,center[${data.lng}, ${data.lat}]</#if>
			});
			
			var markers = [];
			map.on('complete', onComplete);
			map.on('moveend', onComplete);
			function onComplete() {
				$('.img-parent').html('');
				for(var i = 0; i < markers.length; i++) {
					markers[i].setMap(null);
				}
				var bounds = map.getBounds();
				var southwestLng = bounds.southwest.lng;
				var southwestLat = bounds.southwest.lat;
				var northeastLng = bounds.northeast.lng;
				var northeastLat = bounds.northeast.lat;
				
				var center = map.getCenter();
				marker = new AMap.Marker({
					map: map,
		            icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
		            position: [center.getLng(), center.getLat()],
		            title: '我的位置'
		        });
				markers.push(marker);
				jQuery.post("listLoupans.do",
    				{
    					minLng: southwestLng,
    					maxLng: northeastLng,
    					minLat: southwestLat,
    					maxLat: northeastLat
    				},
    				function(data){
    					data = eval('(' + data + ')');
    					if(data.result == 0) {
							var loupans = data.data;
	    					for(var i = 0; i < loupans.length; i++) {
	    						var item = loupans[i];
	    						marker = new AMap.Marker({
	    							map: map,
						            icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_r.png",
						            position: [item.lng, item.lat],
						            title: item.name
						        });
						        markers.push(marker);
						        $('.img-parent').append(
						        	'<div class="img-frame"><img class="img-item" src="' + item.absIndexImg + '"/><br><span>' + item.name + '</span></div>'
						        );
	    					}
    					}
    				}
    			);
		    }
		});
	</script>
</head>
<body>
	<header>
		<div class="first-ad"><img src="../baba/img/first_ad.png"/></div>
		<div class="second-ad"><img src="../baba/img/second_ad.png"/></div>
	</header>
	<div class="back-main"><img class="logo-main" src="../baba/img/logo_main.png"/><div class="visitor-count">今日访问量：<span>88888</span></div></div>
	<nav><input class="search-content" placeholder="&nbsp;&nbsp;&nbsp;输入地区、楼盘" /><input class="btn-search" value="搜索" readonly></input></nav>
	<div class="main-content">
		<div class="img-parent"></div>
		<div id="map-container" class="map-parent"></div>
	</div>
	<div></div>
</body>
</html>