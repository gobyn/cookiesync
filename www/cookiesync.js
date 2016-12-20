var exec = require('cordova/exec');

var CookieSync = {
    sync : function (callback) {
        exec(callback, function (err) {
            console.log("synccookie failed");
        }, "CookieSync", "sync", []);
    },
    persistCookies: function () {
        exec(function(){console.log("cookie persist success")}, function (err) {
            console.log("cookie persist failed");
        }, "CookieSync", "persistCookies", []);
    },
    restoreCookies: function (callback, errorCallback) {
        exec(callback, errorCallback, "CookieSync", "restoreCookies", []);
    }
}

module.exports = CookieSync;