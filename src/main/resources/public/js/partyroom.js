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
                window.location.href = `partyroom_detail.html?id=${movie.movieId}&type=movie`;
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

            const contentName = $('<div class="content-name"></div>').text(content.name);
            contentElement.append(contentName);

            const deleteButton = $('<button class="delete-button">DELETE</button>');
            deleteButton.on('click', function(event) {
                event.stopPropagation();
                $.ajax({
                    url: `/partyroom/contents/${content.name}`,
                    type: 'DELETE',
                    success: function(result) {
                        contentElement.remove();
                    },
                    error: function(error) {
                        console.error('Error deleting content:', error);
                    }
                });
            });
            contentElement.append(deleteButton);

            contentName.on('click', function() {
                window.location.href = `partyroom_detail.html?id=${content.name}&type=content`;
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

                    const contentName = $('<div class="content-name"></div>').text(newContent.name);
                    contentElement.append(contentName);

                    const deleteButton = $('<button class="delete-button">DELETE</button>');
                    deleteButton.on('click', function(event) {
                        event.stopPropagation();
                        $.ajax({
                            url: `/partyroom/contents/${newContent.name}`,
                            type: 'DELETE',
                            success: function(result) {
                                contentElement.remove();
                            },
                            error: function(error) {
                                console.error('Error deleting content:', error);
                            }
                        });
                    });
                    contentElement.append(deleteButton);

                    contentName.on('click', function() {
                        window.location.href = `partyroom_detail.html?id=${newContent.name}&type=content`;
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