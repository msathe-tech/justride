$(function () {
    var $mapMarkers = $('#mapMarkers');
    $.ajax({
        type: 'GET',
        url: '/mapMarkers',
        success: function (mapMarkers) {
            console.log('success', mapMarkers);
            $.each(mapMarkers, function (i, mapMarker) {
                $mapMarkers.append('<li>'+'latitude: ' + mapMarker.latitude + ', logitude:' + mapMarker.longitude +'</li>');
            });
        }

    });
});