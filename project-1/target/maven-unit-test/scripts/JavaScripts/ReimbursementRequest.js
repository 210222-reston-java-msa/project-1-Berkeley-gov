function submitRequest() {

    console.log('Employee has made a request for reimbursement.');

    let amount = document.getElementById('amount').value;
    let description = document.getElementById('description').value;
    let typeId = document.getElementById('typeId').selectedIndex;

    let user = sessionStorage.getItem('currentUser');

    let ReimbursementTemplate = {
        expenses: amount,
        submissionDate: Date,
        resolvedDate: null,
        itemDescription: description,
        receipt: null,
        author: user.employee_id,
        resolver: null,
        status: 1,
        reimbursementType: typeId

    }

    console.log(ReimbursementTemplate);

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {

        if (this.readyState === 4 && this.status === 200) {
            console.log("Reimbursement request successfully submitted.");
        }

        if(this.readyState === 4 && this.status === 204) {
            console.log("failed to submit the reimbursement request.");
        }
    }

    xhr.open("POST", "http://localhost:8080/Employee_Reimbursement_System/reimbursement");
    xhr.send(JSON.stringify(ReimbursementTemplate));
}