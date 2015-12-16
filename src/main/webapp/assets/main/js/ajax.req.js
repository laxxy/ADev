/**
 * num - is a number of selected page
 * on success data goes to content div
 */
function getData(num) {

    var number = {};

    if (jQuery.type(num) === "undefined") {
        number["pageNumber"] = 1;
    }
    else number["pageNumber"] = num;

    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : window.location.href,
        data : JSON.stringify(number),
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
function display(data) { //not used

    var st = jQuery.parseJSON(data);
    var jmString = "";
    st.forEach(function(entry){
        jmString = jmString +
            "<div class=jumbotron>" +
            "<h1>"+ entry.lotName +"</h1>" +
            "</div>";
    });
    $('#content').html(jmString);
}