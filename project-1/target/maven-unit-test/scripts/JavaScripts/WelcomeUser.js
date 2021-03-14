let welcome = document.getElementById('welcome');

let username = sessionStorage.getItem('username');

if(username == null) {
    window.location = "http://localhost:8080/Employee_Reimbursement_System/";
} else {
    let currentUser = username;
    console.log(username);

    if(currentUser != null) {
        welcome.innerHTML = "Welcome " + currentUser;
    }
}

function logout() {
    let xhrRequest = new XMLHttpRequest();

    xhrRequest.open("POST", "http://localhost:8080/Employee_Reimbursement_System/logout");
    xhrRequest.send();

    sessionStorage.removeItem('currentUser');
    window.location = "http://localhost:8080/Employee_Reimbursement_System/";
}