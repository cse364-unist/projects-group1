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
            $('#video').attr('src', data.imageUrl); // 이미지 URL 설정
            $('#chat').attr('src', data.chatUrl);

            // type이 'movie'일 때 movieId를 저장, 그렇지 않으면 기존 id를 사용
            quizId = type === 'movie' ? data.movieId : id;

            // 웹소켓 연결 설정
            const socket = new SockJS('/ws');
            const stompClient = Stomp.over(socket);

            stompClient.connect({}, function(frame) {
                console.log('Connected: ' + frame);

                // 특정 채팅 방에 구독
                stompClient.subscribe('/topic/public', function(messageOutput) {
                    showMessageOutput(JSON.parse(messageOutput.body));
                });

                // 사용자 추가
                stompClient.send("/app/chat.addUser", {}, JSON.stringify({sender: 'User', type: 'JOIN'}));

                // 메시지 전송
                $('#sendButton').click(function() {
                    const messageContent = $('#messageInput').val();
                    if (messageContent && stompClient) {
                        const chatMessage = {
                            sender: 'User',
                            content: messageContent,
                            type: 'CHAT'
                        };
                        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
                        $('#messageInput').val('');
                    }
                });
            });
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

    function showMessageOutput(messageOutput) {
        const chat = $('#chat');
        const messageElement = $('<div>').text(messageOutput.sender + ': ' + messageOutput.content);
        chat.append(messageElement);
    }
});