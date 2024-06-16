document.addEventListener('DOMContentLoaded', function() {
  const userIdElement = document.getElementById('userId');

  const idKey = "USER-ID";
  let userId;
  userId = localStorage.getItem(idKey);
  if(userId==null){
    alert('You have to login first');
    window.location.href = 'login.html';
  } else {
    userIdElement.textContent = userId;
  }

  const logoutButton = document.getElementById('logoutButton');

  logoutButton.addEventListener('click', function() {
    localStorage.removeItem(idKey);
    alert('Logged out!');
    window.location.href = 'index.html';
  });
});
