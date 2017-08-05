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
						var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -28)});
						var loupans = data.data;
						for(var i = 0; i < loupans.length; i++) {
							var item = loupans[i];
							marker = new AMap.Marker({
								map: map,
								icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_r.png",
								position: [item.lng, item.lat],
								content: '<div class="marker-content">' + (i+1) + '</div>'
							});
						        
							markers.push(marker);
							marker.content = item.name;
							$('.img-parent').append(
									'<div class="img-frame"><img class="img-item" src="' + item.absIndexImg + '"/><br><span>' + item.name + '</span></div>'
							);
						        
							marker.on('mouseover', function(e){
								infoWindow.setContent(e.target.content);
								infoWindow.open(map, e.target.getPosition());
							});
							
							marker.on('click', function(){
								map.setZoomAndCenter(18, [marker.getPosition().getLng(), marker.getPosition().getLat()]);
							});
						}
					}
				}
		);
}

var autoOptions = {input: "input-addr"};
var auto = new AMap.Autocomplete(autoOptions);
var placeSearch = new AMap.PlaceSearch({
	map: map,
	panel: 'panel'
}); 

AMap.event.addListener(auto, "select", select);
function select(e) {
	placeSearch.setCity(e.poi.adcode);
	placeSearch.search(e.poi.name); // 关键字查询查询
}
$('#input-addr').on('input', function(){
	$('#panel').html('');			
});
