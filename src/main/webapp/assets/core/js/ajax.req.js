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
function display(data) {

    var st = jQuery.parseJSON(data);
    var jmString = "";
    st.forEach(function(entry){
        jmString = jmString +
            "<li class=media>" +
                "<a class=pull-left href=#>"+
                "<img class=media-object src='' alt=''>"+
                "</a>"+
                "<div class=media-body>"+
                "<ul class=sinlge-post-meta>"+
                /*"<li><i class='fa fa-user'></i>" + entry.user.login + "</li>"+*/
                "<li><i class='fa fa-clock-o'></i>" + entry.dateOfStart + "</li>"+
                "<li><i class='fa fa-calendar'></i>" + entry.bidCurrent + "</li>"+
                "</ul>"+
                "<p>"+entry.lotName+"<p>"+
                "<a class='btn btn-primary' href=/lot/" + entry.id+
                "><i class='fa fa-reply'></i>More</a>"+
                "</div>"+
                "</li>";
    });
    $('#content').html(jmString);
}