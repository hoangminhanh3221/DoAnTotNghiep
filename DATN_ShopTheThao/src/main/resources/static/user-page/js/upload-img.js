// Cập nhật trong mã JavaScript của trang user-info.html
document.getElementById("avatar-upload").addEventListener("change", function () {
    var fileInput = document.getElementById("avatar-upload");
    var file = fileInput.files[0];
    var formData = new FormData();
    formData.append("file", file);

    fetch("/user/upload-avatar", {
        method: "POST",
        body: formData,
    })
        .then((response) => response.text())
        .then((message) => {
            console.log(message);
            // Xử lý thông báo tải lên thành công hoặc lỗi (nếu cần thiết)
        })
        .catch((error) => {
            console.error("Error:", error);
            // Xử lý thông báo lỗi (nếu cần thiết)
        });
});

// Gửi thông tin tài khoản khi bấm nút "Lưu Thông Tin"
document.getElementById("form-info").addEventListener("submit", function (event) {
    event.preventDefault(); // Ngăn form submit mặc định

    var form = event.target;
    var formData = new FormData(form);

    fetch("/user/edit", {
        method: "POST",
        body: formData,
    })
        .then((response) => response.text())
        .then((message) => {
            console.log(message);
            // Xử lý thông báo lưu thông tin thành công hoặc lỗi (nếu cần thiết)
            // Có thể làm mới trang sau khi lưu thành công
            window.location.reload();
        })
        .catch((error) => {
            console.error("Error:", error);
            // Xử lý thông báo lỗi (nếu cần thiết)
        });
});
