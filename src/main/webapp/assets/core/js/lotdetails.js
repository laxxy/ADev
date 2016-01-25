/**
 *
 */
function postMessage(message) {
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : window.location.href,
        data : JSON.stringify(message),
        dataType : 'json',
        success : function(data) {
            $('#result').html(jQuery.parseJSON(data));
        },
        error : function(e) {
        },
        done : function(e) {
            //console.log("DONE");
        }
    });
}

