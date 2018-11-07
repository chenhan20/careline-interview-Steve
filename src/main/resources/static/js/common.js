function ajax(config) {
    var origSuccess = typeof config.success !== "undefined" ? config.success
                    : function(data, textStatus, jqXHR) {};
    var origError = typeof config.error !== "undefined" ? config.error
                    : function(jqXHR, textStatus, errorThrown, exceptionData) {};
    
    var handleError = function(jqXHR, textStatus, errorThrown, exceptionData) {
        if (exceptionData && exceptionData.isException === true) {
            alert("Exception : "+exceptionData.msg);
        } else {
            alert("["+jqXHR.status+"] "+textStatus+":"+errorThrown);
        }
        origError(jqXHR, textStatus, errorThrown, exceptionData);
    };
    
    config.success = function(data, textStatus, jqXHR) {
        if (data.isException === true) {
            handleError(jqXHR, textStatus, "Exception", data);
        } else {
            origSuccess(data, textStatus, jqXHR);
        }
    };
    config.error = function(jqXHR, textStatus, errorThrown) {
        handleError(jqXHR, textStatus, errorThrown, undefined);
    };

    $.ajax(config);
}
function prettyJSON(data) {
    return JSON.stringify(data, undefined, 4);
}
function randomNum(min, max) {
    return Math.floor(Math.random()*(max-min+1)+min);
}
