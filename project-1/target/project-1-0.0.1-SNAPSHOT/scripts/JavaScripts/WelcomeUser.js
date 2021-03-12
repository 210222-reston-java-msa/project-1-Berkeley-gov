let welcome = document.getElementById("welcome");

let userString = document.sessionStorage.getItem("currentUser");

if(userString === null) {
    window.location("http://localhost:8080/Employee_Reimbursement_System/");
} else {
    let currentUser = JSON.parse(userString);
    console.log(userString);

    if(currentUser != null) {
        welcome.innerHTML = `Welcome ${currentUser.firstName} ${currentUser.lastName}!`;
    }
}

function logout() {
    let xhrRequest = new XMLHttpRequest();

    xhrRequest.open("POST", "http://localhost:8080/Employee_Reimbursement_System/logout");
    xhrRequest.send();

    sessionStorage.removeItem('currentUser');
    window.location("http://localhost:8080/Employee_Reimbursement_System/");
}