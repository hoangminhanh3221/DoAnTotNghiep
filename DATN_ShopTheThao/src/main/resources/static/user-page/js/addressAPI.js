// JavaScript
const host = "https://provinces.open-api.vn/api/";

var callAPI = (api) => {
    return axios.get(api)
        .then((response) => {
            renderData(response.data, "province");
        });
};

callAPI(host + "?depth=1");

var callApiDistrict = (api) => {
    return axios.get(api)
        .then((response) => {
            renderData(response.data.districts, "district");
        });
};

var callApiWard = (api) => {
    return axios.get(api)
        .then((response) => {
            renderData(response.data.wards, "ward");
        });
};

var renderData = (array, select) => {
    let row = ' <option disabled value="">--Chọn--</option>';
    array.forEach(element => {
        row += `<option value="${element.name}" data-code="${element.code}">${element.name}</option>`;
    });
    document.querySelector("#" + select).innerHTML = row;
};

$("#province").change(() => {
    callApiDistrict(host + "p/" + $("#province option:selected").data("code") + "?depth=2");
    $("#district").empty(); // Xóa danh sách quận/huyện khi thay đổi tỉnh/thành phố
    $("#ward").empty(); // Xóa danh sách phường/xã khi thay đổi tỉnh/thành phố
    printResult();
});

$("#district").change(() => {
    callApiWard(host + "d/" + $("#district option:selected").data("code") + "?depth=2");
    $("#ward").empty(); // Xóa danh sách phường/xã khi thay đổi quận/huyện
    printResult();
});

$("#ward").change(() => {
    printResult();
});

var printResult = () => {
    if ($("#district").val() != "" && $("#province").val() != "" &&
        $("#ward").val() != "") {
        let result = $("#province option:selected").text() +
            " | " + $("#district option:selected").text() + " | " +
            $("#ward option:selected").text();
        $("#result").text(result);
    }
};
