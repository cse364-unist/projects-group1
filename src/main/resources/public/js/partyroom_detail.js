$(document).ready(function() {
    // URL에서 파라미터를 가져오는 함수
    function getParameterByName(name) {
        const url = new URL(window.location.href);
        return url.searchParams.get(name);
    }

    // 영화나 콘텐츠 ID를 URL에서 가져오기
    const id = getParameterByName('id');
    const type = getParameterByName('type'); // 'movie' 또는 'content'

    if (id && type) {
        const endpoint = type === 'movie' ? `/partyroom/movies/${id}` : `/partyroom/contents/${id}`;

        // 영화나 콘텐츠 정보를 가져오기
        $.get(endpoint, function(data) {
            $('#title').text(type === 'movie' ? data.movieName : data.contentName);
            $('#video').attr('src', data.streamVideoUrl);
            $('#chat').attr('src', data.chatUrl);
        })
        .fail(function(error) {
            console.error('Error fetching details:', error);
            $('#title').text('Error loading details');
        });
    } else {
        $('#title').text('Invalid ID or type');
    }
});