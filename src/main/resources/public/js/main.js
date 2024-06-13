$(document).ready(function() {
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
                $('#locationList').append('<li>Name: ' + moviePlace.name + '</li>');
                $('#locationList').append('<li>Hobby: ' + moviePlace.hobby + '</li>');
                $('#locationList').append('<li>Favorite Food: ' + moviePlace.favoriteFood + '</li>');
            })
        }
    });
});