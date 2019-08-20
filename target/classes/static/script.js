function check() {
    alert("Hallo liebe Welt");
        get("/check", function(msg) {
            if (!(msg == "connected")) alert("Es konnte keine Verbindung zum Server hergestellt werden. Bitte versuchen Sie es später erneut oder kontaktieren Sie den Kundenservice (boss.kaye@outlook.de oder auf unserem Discord-Server)!");
    });
    }

    function startDownload() {
        var product = document.getElementById("product").value;
        var resultion = document.getElementById("resultion").value;
        var code = document.getElementById("code").value;
        var replacedProduct = product;
        var cache = "";
        var finish = false;
        while(!finish) {
            cache = replacedProduct.replace("/", "SlAsH");
            cache = cache.replace(" ", "_");
            if (cache == replacedProduct) finish = true;
            else replacedProduct = cache;
        }
        var category = "DH_" + replacedProduct + "_" + resultion;
        getData(category, code);
    }

    function getData(category, code) {
        var request = "/getData/" + category + "/" + code;
        get(request, function(msg) {
            if (msg.slice(0,5) == "url::") {
                document.getElementById("outputLine").innerHTML = "";
                //var url = msg.slice(5);
                //hyperlink(url);
                hyperlink(URLPartIntoString(msg.slice(5)));
            } else if (msg == "Wrong!") {
                document.getElementById("outputLine").innerHTML = "Falscher Code!";
            } else {
                alert("An error occured: The server responsed with: " + msg);
            }
        });

        function URLPartIntoString(url) {
            var cache = "";
            var finish = false;
            while (finish == false) {
                cache = url.replace("SlAsH", "/");
                if (cache == url) finish = true;
                else url = cache;
            }
            finish = false;
            while (finish == false) {
                cache = url.replace("FrgZchn", "?");
                if (cache == url) finish = true;
                else url = cache;
            }
            return url;
        }

        function StringIntoURLPart(str) {
            var cache = "";
            var finish = false;
            while (finish == false) {
                cache = str.replace("/", "SlAsH");
                if (cache == str) finish = true;
                else str = cache;
            }
            finish = false;
            while (finish == false) {
                cache = str.replace("?", "FrgZchn");
                if (cache == str) finish = true;
                else str = cache;
            }
            return str;
        }
    }