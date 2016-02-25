window.onload = function(){
    $.ajax({
        type : "GET",
        contentType : "application/json",
        url : "/conversations/unreaded",
        dataType : 'json',
        success : function(data) {
            display(JSON.stringify(data));
        },
        error : function(e) {
        },
        done : function(e) {
            //console.log("DONE");
        }
    });
    function display(data) {
        var st = jQuery.parseJSON(data);
        $('#income').html("<a href='/conversations'><i class='fa fa-envelope'></i> Income messages : " + st.size + "</a>");
    }
};
