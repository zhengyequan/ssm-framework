<!DOCTYPE html>
<html>
<head>
	<title>首页</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
	<link rel="stylesheet" type="text/css" href="../baba/css/index.css">
	<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
	<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.3&key=0829386e87eb80d8fddf8deb214ce617&plugin=AMap.Autocomplete,AMap.PlaceSearch"></script>
	<script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.10"></script>
	<script type="text/javascript" src="../resources/js/jquery.min.js"></script>
</head>
<body>
	<!-- 
		<header>
			<div class="first-ad"><img src="../baba/img/first_ad.png"/></div>
			<div class="second-ad"><img src="../baba/img/second_ad.png"/></div>
		</header>
		<div class="back-main"><img class="logo-main" src="../baba/img/logo_main.png"/><div class="visitor-count">今日访问量：<span>88888</span></div></div>
	-->
	<canvas id="canvas" class="canvas-container"></canvas>
	<div class="main-content">
		<div class="img-parent"></div>
		<div id="map-container" class="map-parent">
			<div class="search-box">
				<input id="input-addr" type="text" placeholder="搜索位置、楼盘" maxlength="256"/><span class="cancel">x</span>
				<div id="panel"></div>
			</div>
		</div>
	</div>
	<div></div>
	<script type="text/javascript" src="../resources/js/canvas.js"></script>
	<script type="text/javascript">
		var map = new AMap.Map('map-container', {
			resizeEnable: true,
		    mapStyle: 'amap://styles/932889a387681f9ea1f0292701a8edc3',
		    zoom: 12<#if data??>,center[${data.lng}, ${data.lat}]</#if>
		});
		$('.search-box span.cancel').click(function(){
			$('#input-addr').val('');
			$('#panel').html('');
		});
	</script>
	<script type="text/javascript" src="../baba/js/index.js"></script>
</body>
</html>