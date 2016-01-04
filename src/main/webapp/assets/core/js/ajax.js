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
            displaySearch(data);
        },
        error : function(e) {
            //console.log("ERROR: ", e);
            displaySearch(e);
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

function displaySearch(data) {
    var json = "<pre>" + JSON.stringify(data, null, 4) + "</pre>";
    $('#feedback').html(json);
}
//category
function getCategory() {
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "/category",
        data : "",
        dataType : 'json',
        timeout : 100000,
        success : function(data) {
            displayCategory(JSON.stringify(data));
        },
        error : function(e) {
        },
        done : function(e) {
        }
    });
}
function displayCategory(data) {

    var st = jQuery.parseJSON(data);
    var category = "";
    st.forEach(function(entry){
        category = category+
                "<li class='dropdown'>"+
                "<a href=>"+entry.name+
                "<i class='fa fa-angle-down'></i></a>"+
                "<ul role='menu' class='sub-menu'>"+
                "</ul>"+
                "</li>";
    });
    $('#category').html(category);
}
/*<li class="dropdown"><a href="#">Shop<i class="fa fa-angle-down"></i></a>
<ul role="menu" class="sub-menu">
    <li><a href="shop.html">Products</a></li>
<li><a href="product-details.html">Product Details</a></li>
<li><a href="checkout.html">Checkout</a></li>
<li><a href="cart.html">Cart</a></li>
<li><a href="login.jsp">Login</a></li>
</ul>
</li>*/