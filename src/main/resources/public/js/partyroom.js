$(document).ready(function() {
    // Load movies on page load
    $.get('/partyroom/movies', function(data) {
        const movieContainer = $('#movies');
        movieContainer.empty();

        data.forEach(function(movie) {
            const movieElement = $('<div class="movie-box"></div>');
            
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

    // Load contents on page load
    $.get('/partyroom/contents', function(data) {
        const contentContainer = $('#contents');
        contentContainer.empty();

        data.forEach(function(content) {
            const contentElement = $('<div class="content-box"></div>');
            
            const contentName = $('<h3></h3>').text(content.name);
            contentElement.append(contentName);

            contentElement.on('click', function() {
                window.location.href = content.url;
            });

            contentContainer.append(contentElement);
        });
    })
    .fail(function(error) {
        console.error('Error fetching contents:', error);
    });

    // Add new content
    $('#addContentButton').on('click', function() {
        const contentName = $('#contentName').val();
        if (contentName) {
            $.ajax({
                url: '/partyroom/contents',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({ contentName: contentName }),
                success: function(newContent) {
                    const contentContainer = $('#contents');
                    
                    const contentElement = $('<div class="content-box"></div>');
                    
                    const contentName = $('<h3></h3>').text(newContent.name);
                    contentElement.append(contentName);

                    contentElement.on('click', function() {
                        window.location.href = newContent.url;
                    });

                    contentContainer.append(contentElement);
                },
                error: function(error) {
                    console.error('Error adding content:', error);
                }
            });
        } else {
            alert('Please enter a content name.');
        }
    });
});