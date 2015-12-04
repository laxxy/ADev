jQuery(document).ready(function($) {

    $("#searchform").submit(function(event) {

        // Disble the search button
        enableSearchButton(false);

        // Prevent the form from submitting via the browser.
        event.preventDefault();

        searchViaAjax();

    });

});

function searchViaAjax() {

    var search = {};
    search["searchString"] = $("#searchinput").val();

    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "/search",
        data : JSON.stringify(search),
        dataType : 'json',
        timeout : 100000,
        success : function(data) {
            display(data);
        },
        error : function(e) {
            //console.log("ERROR: ", e);
            display(e);
        },
        done : function(e) {
            //console.log("DONE");
            enableSearchButton(true);
        }
    });

}

function enableSearchButton(flag) {
    $("#btnsearch").prop("disabled", flag);
}

function display(data) {
    var json = "<pre>" + JSON.stringify(data, null, 4) + "</pre>";
    $('#feedback').html(json);
}/**
 * Created by cosxt on 04.12.2015.
 */
