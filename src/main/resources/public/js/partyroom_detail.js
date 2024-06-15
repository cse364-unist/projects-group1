$(document).ready(function() {
    // 뒤로가기 버튼 클릭 이벤트 핸들러
    $('#back-button').on('click', function() {
        window.history.back();
    });

    // URL에서 파라미터를 가져오는 함수
    function getParameterByName(name) {
        const url = new URL(window.location.href);
        return url.searchParams.get(name);
    }

    // 영화나 콘텐츠 ID를 URL에서 가져오기
    const id = getParameterByName('id');
    const type = getParameterByName('type'); // 'movie' 또는 'content'
    let quizId;

    if (id && type) {
        const endpoint = type === 'movie' ? `/partyroom/movies/${id}` : `/partyroom/contents/${id}`;

        // 영화나 콘텐츠 정보를 가져오기
        $.get(endpoint, function(data) {
            $('#title').text(type === 'movie' ? data.movieName : data.contentName);
            $('#video').attr('src', data.streamVideoUrl); // 이미지 URL 설정
            $('#chat').attr('src', data.chatUrl);

            // type이 'movie'일 때 movieId를 저장, 그렇지 않으면 기존 id를 사용
            quizId = type === 'movie' ? data.movieId : id;
        })
        .fail(function(error) {
            console.error('Error fetching details:', error);
            $('#title').text('Error loading details');
        });

        // 퀴즈 버튼 클릭 이벤트 핸들러
        $('#quiz-button').on('click', function() {
            $.ajax({
                url: `quiz.html`,
                type: 'HEAD',
                error: function() {
                    alert('Quiz page is not available.');
                },
                success: function() {
                    window.location.href = `quiz.html?id=${quizId}`;
                }
            });
        });
    } else {
        $('#title').text('Invalid ID or type');
    }
});