function sendLogin() {
    console.log("send login triggered");

    let uName = document.getElementById('username').value;
    let pWord = document.getElementById('password').value;

    console.log(`Username: ${uName} Password: ${pWord}`);

    let loginTemplate = {
        username: uName,
        password: pWord
    }

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            console.log("Login success");

            sessionStorage.setItem('currentUser', this.responseText);

            window.location = "http://localhost:8080/Employee_Reimbursement_System/home.html";

            console.log(sessionStorage.getItem('currentUser'));
        }

        if (this.readyState === 4 && this.status === 204) {

            console.log("failed to find user");
        }
    }

    xhr.open("POST", "http://localhost:8080/Employee_Reimbursement_System/login");
    xhr.send(JSON.stringify(loginTemplate));
}