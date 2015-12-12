/**
 * Created by cosxt on 12.12.2015.
 */
function getData() {

    var total;

    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "/filter/1",
        data : JSON.stringify(""),
        dataType : 'json',
        timeout : 100000,
        success : function(data) {
            //display(data);
            countin = data.dataToView;
            alert(datain)
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
    var json = "<pre>" + JSON.stringify(data, null, 4) + "</pre>";
    $('#content').html(json);
}