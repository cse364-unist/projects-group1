$(document).ready(function() {
    // Find recommendations based on distance
    $('#findLocations').click(function() {
        const km = $('#km').val();
        const userId = 1; // Assuming a static user ID for now
        if (km) {
            $.get(`/places/recommends/distance/${km}?userId=${userId}`, function(data) {
                $('#distanceRecommendationList').empty();
                data.forEach(function(location) {
                    $('#distanceRecommendationList').append('<li>' + location.name + '</li>');
                });
            }).fail(function() {
                console.error('Failed to load distance-based recommendations');
            });
        }
    });

    // Find top 5 recommendations based on user preferences
    $('#findUserPreferences').click(function() {
        const userId = 1; // Assuming a static user ID for now
        $.get(`/places/recommends/${userId}`, function(data) {
            $('#preferenceRecommendationList').empty();
            data.forEach(function(location) {
                $('#preferenceRecommendationList').append('<li>' + location.name + ' - Hobby: ' + location.hobby + ', Favorite Food: ' + location.favoriteFood + '</li>');
            });
        }).fail(function() {
            console.error('Failed to load user preference-based recommendations');
        });
    });
});