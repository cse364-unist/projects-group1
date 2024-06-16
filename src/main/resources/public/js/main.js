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

    // Load all movies on page load
    $.get('/places/movies', function(data) {
        $('#movieList').empty().append('<option value="" disabled selected>Select a movie</option>');
        data.forEach(function(movie) {
            $('#movieList').append('<option value="' + movie.movieId + '">' + movie.name + '</option>');
        });
    });

    // Show movie locations on movie selection
    $('#movieList').change(function() {
        const movieId = $(this).val();
        if (movieId) {
            $.get(`/places/${movieId}`, function(moviePlace) {
                $('#locationList').empty();
                const hobbiesReadable = bitwiseToReadable(moviePlace.hobby, hobbies);
                const favoriteFoodsReadable = bitwiseToReadable(moviePlace.favoriteFood, favoriteFoods);
                $('#locationList').append('<li>Name: ' + moviePlace.name + '</li>');
                $('#locationList').append('<li>Hobby: ' + hobbiesReadable + '</li>');
                $('#locationList').append('<li>Favorite Food: ' + favoriteFoodsReadable + '</li>');
            })
        }
    });
});