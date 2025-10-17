$(document).ready(function () {

    $("#searchForm").submit(function (e) {
        e.preventDefault();
        let keyword = $("#keyword").val();

        $.ajax({
            url: "/blog/search",
            type: "GET",
            data: { keyword: keyword },
            success: function (data) {
                $("#blogList").html(data);
            },
            error: function () {
                alert("Lỗi khi tìm kiếm bài viết!");
            }
        });
    });

    let offset = 20;
    $("#loadMore").click(function () {
        $.ajax({
            url: "/blog/load-more",
            type: "GET",
            data: { offset: offset },
            success: function (data) {
                $("#blogList ul").append($(data).find("li"));
                offset += 20;
            },
            error: function () {
                alert("Không thể tải thêm bài viết!");
            }
        });
    });
});
