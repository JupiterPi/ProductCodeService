function resultIntoArray(result) {
    var returning = result.split("UnD");
    return returning;
}

function getBackgroundsString(listId) {
    if (backgroundsString == undefined) {
        console.log("getBackgroundsString: listId: " + listId + "; loading ressources");
        get("/getBackgrounds", function(msg) {
            backgroundsString = msg;
            writeDropDownLists(listId);
        });
    } else {
        console.log("getBackgroundsString: listId: " + listId + "; ressources available");
        writeDropDownLists(listId);
    }
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

var list;
var backgroundsString;

function writeDropDownLists(listId) {
    writeIntoDropDownList(document.getElementById(listId), generateOptions(backgroundsString));
}