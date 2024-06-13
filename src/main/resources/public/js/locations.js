$(document).ready(function() {
    // Load all locations on page load
    $.get('/places/all', function(data) {
        $('#locationList').empty().append('<option value="" disabled selected>Select a location</option>');
        data.forEach(function(location) {
            $('#locationList').append('<option value="' + location.placeId + '">' + location.name + '</option>');
        });
    });

    // Show movies at location on location selection
    $('#locationList').change(function() {
        const placeId = $(this).val();
        if (placeId) {
            $.get(`/places/all/${placeId}`, function(movies) {
                $('#moviesList').empty();
                movies.forEach(function(movie) {
                    $('#moviesList').append('<li>' + movie.name + '</li>');
                });
            });
        }
    });
});