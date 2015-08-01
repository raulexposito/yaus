
initialize_visual_objects = function () {
    $("#ok-message").hide();
    $("#fail-message").hide();
    $("#url_field").val("");
};

populate_data = function () {
    // visits
    $.ajax({
        url: "amount/visits",
        success: function(data) {
            $(".visits").html(data + " visits");
        }
    });

    // urls shorted
    $.ajax({
        url: "amount/hashes",
        success: function(data) {
            $(".urls-shorted").html(data + " URLs visited");
        }
    });

    // amount of visits per url
    $.getJSON("amount", function (json) {

        // chart
        nv.addGraph(function() {
          var chart = nv.models.pieChart()
              .x(function(d) { return d.url })
              .y(function(d) { return d.count })
              .showLabels(true)     //Display pie labels
              .labelThreshold(.05)  //Configure the minimum slice size for labels to show up
              .labelType("percent") //Configure what type of data to show in the label. Can be "key", "value" or "percent"
              .donut(true)          //Turn on Donut mode. Makes pie chart look tasty!
              .donutRatio(0.35)     //Configure how big you want the donut hole size to be.
              ;

            d3.select("#chart svg")
                .datum(json)
                .transition().duration(350)
                .call(chart);

              return chart;
        });

        var latest = [];
        $.each(json, function( key, val ) {
            latest.push(generateNewRow (val.url, val.shortUrl, val.count));
        });
        $("#results > tbody").html(latest);
    });
};

generateNewRow = function(url, shortUrl, count) {
    var row = "<tr>";
    row = row + "<td><a href='" + url + "'' target='_blank'>" + url + "</td>";
    row = row + "<td><a href='" + shortUrl + "'' target='_blank'>" + shortUrl + "</td>";
    row = row + "<td class='centered'>" + count + "</td>";
    row = row + "<td class='centered'><span class='glyphicon glyphicon-eye-open' aria-hidden='true'></span></td>";
    row = row + "</tr>";
    return row;
};

manage_short_button = function () {
    $("#short_button").click(function(){

        $("#ok-message").hide();
        $("#fail-message").hide();

        $.ajax({
            type: 'post',
            url: 'shortener',
            data: {
                'url': $("#url_field").val()
            },
            success: function(msg){
                $("#ok-message").show();
                $("#ok-message > p").html("<a href='" + msg + "' target='_blank'>" + msg + "</a>");
            },
            error: function(msg){
                $("#fail-message").show();
            },
        });
    });

    $(document).keypress(function(e) {
        if(e.which == 13) {
            $("#short_button").click();
        }
    });
}