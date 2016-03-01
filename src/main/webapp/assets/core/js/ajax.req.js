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
        //var link = entry.images.get(0);
        //alert(link);
        jmString = jmString +
            "<li class=media>" +
                "<a class=pull-left href=/lot/" + entry.id  +">"+
                "<img class=media-object src='/assets/core/images/home/empty_cart.png'  alt=''>"+
                "</a>"+
                "<div class=media-body>"+
                "<ul class=sinlge-post-meta>"+
                "<li><i class='fa fa-clock-o'></i>" + entry.dateOfStart + "</li>"+
                "</ul>"+
                "<p>"+entry.lotName+"<p>"+
                "<p><h1>"+entry.bidCurrent+" usd.</h1><p>"+
                "<a class='btn btn-primary' href=/lot/" + entry.id+
                "><i class='fa fa-reply'></i>More</a>"+
                "</div>"+
                "</li>";
    });
    $('#content').html(jmString);
}