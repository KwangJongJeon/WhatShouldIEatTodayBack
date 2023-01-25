$(document).ready(function() {
    $('#loading').hide();
    $('#recommendationForm').submit(function() {
        $('#loading').show();
        return true;
    });
});