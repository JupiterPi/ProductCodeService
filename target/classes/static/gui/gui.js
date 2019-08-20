// ----- http.js -----

function get(path, handling) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            handling(this.responseText);
        }
    }
    xhttp.open("GET", path);
    xhttp.send();
}

function post(path) {
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", path);
    xhttp.send();
}

// ----- document -----

function a(msg) {
    alert(msg);
}

function element(id) {
    return document.getElementById(id);
}

function value(id) {
    return element(id).value;
}

// ----- gui.js -----

var username;
var password;

function proofUser() {
    username = element("username").value;
    password = element("password").value;
    var out_proofUser = element("proofUser.error");
    get("/proofUser/" + username + "/" + password, function(msg) {
        if (msg == "Right!") {
            out_proofUser.className = "success";
            out_proofUser.innerHTML = "Angemeldet!";
        } else if (msg == "Wrong!") {
            out_proofUser.className = "error";
            out_proofUser.innerHTML = "Benutzername oder Passwort falsch!";
        } else alert("Runtime error! See console.");
    });
    //element("username").value = username;a("6");
    //element("password").value = password;a("7");
}

function getWholeData() {
    get("/getWholeData/" + username + "/" + password, function(msg) {
        element("getWholeData.out").value = msg;
    });
}

function addCategory() {
    var name = value("addCategory.name");
    var url = "/addCategory/" + username + "/" + password + "/" + name;
    post(url);
}

function addCode() {
    var category = value("addCode.category");
    var code = value("addCode.code");
    var note = value("addCode.note");

    var data = value("addCode.data");
    var cache = "";
    var finish = false;
    while(!finish) {
        cache = data.replace("/", "SlAsH");
        cache = cache.replace("?", "FrgZchn");
        if (cache == data) finish = true;
        else data = cache;
    }
    data = "url::" + data;

    post("/addCode/" + username + "/" + password + "/" + category + "/" + code + "/" + data + "/" + note);
}