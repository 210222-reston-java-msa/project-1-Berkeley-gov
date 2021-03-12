function sendLogin() {
    console.log("send login triggered");
    let uName = document.getElementById('username').value;
    let pWord = document.getElementById('password').value;

    console.log(`Username: ${uName}`);
    console.log(`Password: ${pWord}`);

    let loginTemplate = {
        username: uName,
        password: pWord
    }

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            console.log("success");

            sessionStorage.setItem('currentUser', this.responseText);

            window.sessionStorage.setItem('username', loginTemplate.username);
            window.sessionStorage.setItem('password', loginTemplate.password);

            window.location = "http://localhost:8080/Employee_Reimbursement_System/home.html";

            console.log(sessionStorage.getItem('username'));
            console.log(sessionStorage.getItem('password'));
            console.log("Employee has logged.");
        } else if (this.readyState === 4 && this.status === 204) {

            console.log("failed to find user");

            let childDiv = document.getElementById('warningText');
            childDiv.textContent = "Failed to login!  Username of Password is incorrect"
        }
    }

    xhr.open("POST", "http://localhost:8080/Employee_Reimbursement_System/login");
    xhr.send(JSON.stringify(loginTemplate));
}