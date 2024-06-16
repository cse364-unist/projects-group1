$(document).ready(function() {
    const hobbies = ['Tourism', 'Leisure', 'Cafe', 'Nature', 'Recreation'];
    const favoriteFoods = ['Korean food', 'Chinese food', 'Japanese food', 'Western food', 'Dessert'];

    // Function to convert bitwise number to human-readable strings
    function bitwiseToReadable(bits, labels) {
        let result = [];
        for (let i = 0; i < labels.length; i++) {
            if (bits & (1 << i)) {
                result.push(labels[i]);
            }
        }
        return result.join(' and ');
    }

    // Find recommendations based on distance
    $('#findLocations').click(function() {
        const km = $('#km').val();
        const idKey = "USER-ID";
        let userId;
        userId = localStorage.getItem(idKey);
        if(userId==null){
            alert('You have to login first');
            window.location.href = 'login.html';
        }
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
        const idKey = "USER-ID";
        let userId;
        userId = localStorage.getItem(idKey);
        if(userId==null){
            alert('You have to login first');
            window.location.href = 'login.html';
        }
        $.get(`/places/recommends/${userId}`, function(data) {
            $('#preferenceRecommendationList').empty();
            data.forEach(function(location) {
                const hobbiesReadable = bitwiseToReadable(location.hobby, hobbies);
                const favoriteFoodsReadable = bitwiseToReadable(location.favoriteFood, favoriteFoods);
                $('#preferenceRecommendationList').append('<li>' + location.name + ' - Hobby: ' + hobbiesReadable + ', Favorite Food: ' + favoriteFoodsReadable + '</li>');
            });
        }).fail(function() {
            console.error('Failed to load user preference-based recommendations');
        });
    });
});