$(document).ready(function() {
    function getParameterByName(name) {
        const url = new URL(window.location.href);
        return url.searchParams.get(name);
    }
    const quizId = getParameterByName('id');
    const quizUrl = `/quizzes/${quizId}`;
    const idKey = "USER-ID";
    let userId;
    userId = localStorage.getItem(idKey);
    if(userId==null){
        alert('You have to login first');
        //여기부터 지워주세요
        userId = 1;
        //여기까지
    }
    fetch(quizUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            displayQuiz(data);
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });

    function displayQuiz(data) {
        const quizDiv = document.getElementById('quiz');

            const movieNameElement = document.createElement('h2');
            movieNameElement.textContent = data.movieName;
            const quizBodyElement = document.createElement('p');
            quizBodyElement.textContent = data.quizBody;

            quizDiv.appendChild(movieNameElement);
            quizDiv.appendChild(quizBodyElement);

    }
    document.querySelectorAll('#answerContainer button').forEach(button => {
        button.addEventListener('click', function() {
            const userAnswer = this.getAttribute('data-answer');
            if (userId!=null){
                postAnswer(userId, quizId, userAnswer);
            }
            else {
                alert("You have to login first")
            }
        });
    });

    function postAnswer(userId, quizId, userAnswer) {
        const postUrl = `/quizzes/${quizId}`;
        const payload = {
            userId: userId,
            userAnswer: userAnswer
        };

        fetch(postUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            alert(`${data.resultMessage}`);
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
    }
});