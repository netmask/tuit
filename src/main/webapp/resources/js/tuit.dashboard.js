$(document).ready(function() {

    $("#tuit-post").submit(function(event) {

        event.preventDefault();
        $.ajax({
            url: $(this).attr("action"),
            context: document.body,
            type: "POST",
            dataType: 'json',
            data: $(this).serialize(),
            complete: function(data){
                update_tuit(data);
            }
        });
        $('#tuit-post')[0].reset();
        $('span#charCount').html(0);
    });

    function update_tuit(data){
        var tuit = jQuery.parseJSON(data.responseText);
        $('<div class="row" style="display: none; border-bottom: 1px solid #E5E5E5; padding-bottom: 10px; padding-top: 10px;"><div class="span4"><h5>'
            + tuit.user.username +'</h5><h6>'
            + new Date(tuit.stamp) +'</h6><span>'
            + tuit.content +'</span></div></div>')
            .prependTo('#tuits-container').slideDown("fast");

    }


    $('textarea#content').keyup(function() {
        var charLength = $(this).val().length;
        $('span#charCount').html(charLength);
    });
});