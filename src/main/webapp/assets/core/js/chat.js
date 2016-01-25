/**

 */
function getMessages(size, name) {
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : window.location.href,
        data : JSON.stringify(size),
        dataType : 'json',
        success : function(data) {
            display(JSON.stringify(data), name);
            getMessages(data.length, name)
        },
        error : function(e) {
            getMessages(size, name)
        },
        done : function(e) {
            //console.log("DONE");
        }
    });
}
function display(data, name) {

    var st = jQuery.parseJSON(data);
    var jmString = "";
    st.forEach(function(entry){
        if (entry.author === name) {
            jmString = jmString+ "<li class='clearfix'>"+
                    "<div class='message-data align-right'>"+
                    "<span class='message-data-time'>" + entry.date + "</span> &nbsp; &nbsp;"+
                    "<span class='message-data-name'>" + entry.author + "</span> <i class='fa fa-circle me'></i>"+
                    "</div>"+
                    "<div class='message other-message float-right'>"+entry.message+"</div>"+
                    "</li>";
        }
        else {
            jmString = jmString+ "<li>"+
                    "<div class='message-data'>"+
                    "<span class='message-data-name'><i class='fa fa-circle online'></i>" + entry.author + "</span>"+
                    "<span class='message-data-time'>" + entry.date + "</span>"+
                    "</div>"+
                    "<div class='message my-message'>"+entry.message+"</div>"+
                    "</li>";
        }
    });
    $('#ms-data').html(jmString);
}
function postMessage(message) {
    $.ajax({
        type : "PUT",
        contentType : "application/json",
        url : window.location.href,
        data : JSON.stringify(message),
        dataType : 'json',
        success : function(data) {
        },
        error : function(e) {
        },
        done : function(e) {
            //console.log("DONE");
        }
    });
}