function proofCode() {
    var inputCode = document.getElementById("inputCode");
    var response = getRequest(makeURL(inputCode.value));
    if (response == "Right!") {
        document.write('Nutze diesen <a target="_blank" href="https://drive.google.com/open?id=15KjuG7Q6n5hEDE5OJsZ95juNZqCnaSR0">Freigabelink</a>.');
    } else {
        document.write("Falscher Code!");
    }
}

function makeURL(code) {
    return "http://localhost:8080/proofCode/testCategory/" + code + "/";
}

function getRequest(url) {
    return "Right!";
}