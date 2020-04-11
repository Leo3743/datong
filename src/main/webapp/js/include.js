$(function () {
    $.get("footer.html",function (data) {
        $("#footer").html(data);
    });
    $.get("shortcut.html",function (data) {
        $("#shortcut").html(data);
    });
});

