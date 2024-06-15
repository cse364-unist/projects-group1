$(document).ready(function() {
    // Load all movies on page load
    $.get('/cse364-project/partyroom/movies', function(data) {
        const movieContainer = $('#movies');
        movieContainer.empty();

        data.forEach(function(movie) {
            const movieElement = $('<div class="movie"></div>');
            
            const movieName = $('<h3></h3>').text(movie.name);
            movieElement.append(movieName);
            
            const movieGenre = $('<p></p>').text(`Genre: ${movie.genre}`);
            movieElement.append(movieGenre);

            movieElement.on('click', function() {
                window.location.href = movie.url;
            });

            movieContainer.append(movieElement);
        });
    })
    .fail(function(error) {
        console.error('Error fetching movies:', error);
    });
});