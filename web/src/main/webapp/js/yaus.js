
initialize_visual_objects = function () {
    $("#ok-message").hide();
    $("#fail-message").hide();
    $("#single-result").hide();
    $(".general-results").show();
    $("#url_field").val("");

    // chart
    nv.addGraph(function() {
      chart = nv.models.pieChart()
          .x(function(d) { return d.url })
          .y(function(d) { return d.count })
          .showLabels(true)     //Display pie labels
          .labelThreshold(.05)  //Configure the minimum slice size for labels to show up
          .labelType("percent") //Configure what type of data to show in the label. Can be "key", "value" or "percent"
          .donut(true)          //Turn on Donut mode. Makes pie chart look tasty!
          .donutRatio(0.35)     //Configure how big you want the donut hole size to be.
          ;
    });
};

populate_data = function () {
    // visits
    $.ajax({
        url: "amount/visits",
        success: function(data) {
            if (data != 0) {
                $(".visits").html(data + " visits");
            }
        }
    });

    // urls shorted
    $.ajax({
        url: "amount/hashes",
        success: function(data) {
            if (data != 0) {
               $(".urls-shorted").html(data + " URLs visited");
            }
        }
    });

    // amount of visits per url
    $.getJSON("amount", function (json) {
        d3.select("#chart svg")
            .datum(json)
            .transition().duration(350)
            .call(chart);

        var latest = [];
        $.each(json, function( key, val ) {
            latest.push(generateNewRow (val.url, val.hash, val.shortUrl, val.count));
        });
        $("#results > tbody").html(latest);

	    $(".single-result-link").click(function(){
		    $("#single-result").show();
            $(".general-results").hide();

            show_results_for_single_result($(this).data("url"), $(this).data("shorturl"), $(this).data("hash"));
	    });
    });
};

show_results_for_single_result = function (url, shortUrl, hash) {
    $("#single-result > h1").html(url);
    $("#single-result > h4").html(shortUrl);

    $.getJSON("visits/" + hash, function (json) {
        var visits = [];
        $.each(json, function( key, val ) {
            visits.push(generateVisitRow(val.year, val.month, val.day, val.hour, val.minute, val.second, val.ip, val.userAgent));
        });
        $("#single-result-table > tbody").html(visits);
    });
};

generateVisitRow = function(year, month, day, hour, minute, second, ip, userAgent) {
    var row = "<tr>";
    row = row + "<td class='text-center'>" + day + "/" + month + "/" + year + " " + hour + ":" + minute + ":" + second + "</td>";
    row = row + "<td class='text-center'>" + ip + "</td>";
    row = row + "<td class='text-center'>" + userAgent + "</td>";
    row = row + "</tr>";
    return row;
};

generateNewRow = function(url, hash, shortUrl, count) {
    var row = "<tr>";
    row = row + "<td><a href='" + url + "' target='_blank'>" + url + "</td>";
    row = row + "<td><a href='" + shortUrl + "' target='_blank'>" + shortUrl + "</td>";
    row = row + "<td class='text-center'>" + count + "</td>";
    row = row + "<td class='text-center'><span class='glyphicon glyphicon-eye-open single-result-link' aria-hidden='true' data-hash='" + hash + "' data-url='" + url + "' data-shorturl='" + shortUrl + "'></span></td>";
    row = row + "</tr>";
    return row;
};

manage_short_button = function () {
    $("#short_button").click(function() {
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
};

manage_go_back_button = function () {
    $("#go_back").click(function(){
	    $("#single-result").hide();
		$(".general-results").show();
        populate_data();
	});
};
