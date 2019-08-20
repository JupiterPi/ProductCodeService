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

// ----- convert -----

function convertURLToRequestPart(url) {
    var cache = "";
    var finish = false;
    while(!finish) {
        cache = url.replace("/", "SlAsH");
        cache = cache.replace("?", "FrgZchn");
        if (cache == url) finish = true;
        else url = cache;
    }
    return "url::" + url;
}

function convertDropDownListValueToCategoryName(val, resultion) {
    var cache = "";
    var finish = false;
    while (!finish) {
        cache = val.replace(" ", "_");
        if (cache == val) finish = true;
        else val = cache;
    }
    return "DH_" + val + "_" + resultion;
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
    var inputType = value("addCategory.inputType");
    if (inputType == "DropDownList") {
        var name = convertDropDownListValueToCategoryName(value("addCategory.categoryByDropDownList"), value("addCategory.resultion"));
    } else if (inputType == "Name") {
        var name = value("addCategory.categoryByName");
    }
    var url = "/addCategory/" + username + "/" + password + "/" + name;
    post(url);
}

function addCode() {
    var inputTypeCategory = value("addCode.inputTypeCategory");
    if (inputTypeCategory == "DropDownList") {
        var category = convertDropDownListValueToCategoryName(value("addCode.categoryByDropDownList"), value("addCode.resultion"));
    } else if (inputTypeCategory == "Name") {
        var category = value("addCode.categoryByName");
    } else alert("Error: inputTypeCategory: " + inputTypeCategory);

    var inputTypeData = value("addCode.inputTypeData");
    if (inputTypeData == "url") {
        var data = convertURLToRequestPart(value("addCode.dataByURL"));
    } else if (inputTypeData == "text") {
        var data = value("addCode.dataByText");
    }

    post("/addCode/" + username + "/" + password + "/" + category + "/" + value("addCode.code") + "/" + data + "/" + value("addCode.note"));
}

function load() {
    post("/load");
}

function write() {
    post("/write");
}