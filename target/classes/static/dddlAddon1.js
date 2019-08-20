function resultIntoArray(result) {
    alert("resultInto...: " + result);
    var returning = result.split("UnD");
    alert(returning);
    return returning;
}

function getBackgroundsString() {
    get("/getBackgrounds", function(msg) {
        alert(msg);
        part2(msg);
    });
}

function buildOptions(backgrounds) {
    var options = "";
    for (i = 0; i < backgrounds.length; i++) {
        options = options + "<option>" + backgrounds[i] + "</option>";
    }
    return options;
}

function generateOptions(result) {
    return buildOptions(resultIntoArray(result));
}

function writeIntoDropDownList(list, options) {
    list.innerHTML = options;
}

var lists = [];

function getBackgrounds(listIds) {
    alert(listIds);
    for (i = 0; i < listIds.length; i++) {
        lists = [];
        lists[i] = document.getElementById(listIds[i]);
    }
    list = 
    part1();
}

function part1() {
    getBackgroundsString();
}

function part2(backgroundsString) {
    for (list in lists) {
        writeIntoDropDownList(list, generateOptions(backgroundsString));
    }
}