document.addEventListener('DOMContentLoaded', function() {
  const loginForm = document.getElementById('login-form');
  const idKey = 'USER-ID';

  loginForm.addEventListener('submit', function(event) {
    event.preventDefault();

    const userId = document.getElementById('userId').value;
    localStorage.setItem(idKey, userId);

    alert('Logged in!');
    window.history.back();
  });
});
