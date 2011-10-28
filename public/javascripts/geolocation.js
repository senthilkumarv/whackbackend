
getCurrentLocationName = function(latitude, longitude) {
	var latlng = new google.maps.LatLng(latitude, longitude);
	if(latlng){
		var geocoder = new google.maps.Geocoder();
		geocoder.geocode({"latLng": latlng}, function(result, status){
			if(status == google.maps.GeocoderStatus.OK) {
				return result[0].formatted_address;
			}
			else {
				return 'Unknown Location';
			}
		});
	}
	else {
		return 'Not Supported';
	}
};
var getLocationInfo = function() {
	if(navigator.geolocation) {
		var latitude = 0;
		var longitude = 0;
		var currentLocation = '';
		navigator.geolocation.getCurrentPosition(function(position){
			latitude = position.coords.latitude;
			longitude = position.coords.longitude;
			console.log(latitude + ' ' + longitude);
			currentLocation = getCurrentLocationName(latitude, longitude);
		});
		return { 'latitude': latitude, 'longitude': longitude, 'location': currentLocation};
	}
	return null;
}