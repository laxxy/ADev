/**
 * num - is a number of selected page
 * on success data goes to content div
 */
function getData() {

    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : window.location.href,
        //data : JSON.stringify(number),
        dataType : 'json',
        timeout : 100000,
        success : function(data) {
            display(JSON.stringify(data));
        },
        error : function(e) {
            //console.log("ERROR: ", e);
            //display(e);
        },
        done : function(e) {
            //console.log("DONE");
        }
    });
}
function display(data) {

    var st = jQuery.parseJSON(data);
    var jmString = "";
    st.forEach(function(entry){
        jmString = jmString
    });
    $('#content').html(jmString);
}