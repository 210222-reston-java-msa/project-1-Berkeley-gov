function sendLogin() {

    console.log('Send login functionality triggered.');

    let uName = document.getElementById("username").value;
    let pWord = document.getElementById("pword").value;

    console.log(`user attempted login: First name (${uName}) Last name (${pWord})`);

    // Object literal "loginTemplate" is built using the login credentials
    let loginTemplate = {
        username: uName,
        password: pWord
    }

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {

        if(this.readyState === 4 && this.status === 200) {
            console.log("Successfully made an AJAX Http Request.");
            sessionStorage.setItem("currentUser", this.responseText);

            window.location = "http://localhost:8080/Employee_Reimbursement_System/home.html";
            console.log(sessionStorage.getItem('currentUser'));
        }

        if(this.readyState === 4 && this.status === 204) {
            console.log("Failed to make XMLHttpRequest in index.js");

            let childDiv = document.getElementById("warningText");
            childDiv.textContent = "Failed to login. Username or password was incorrect.";
        }

        // Open Http Post request and sends a JSON representation of LoginTemplate containing user credentials for validation
        xhr.open("POST", "http://localhost:8080/Employee_Reimbursement_System/login");
        xhr.send(JSON.stringify(loginTemplate));
    }
}