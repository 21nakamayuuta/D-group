let inputImage = $(".image input");
let previewImage = $(".image .preview");

inputImage.on("change", function (e) {
    var fileset = $(this).val();
    if (fileset === "") {
        previewImage.attr("src", "");
        previewImage.addClass("display-none");
    } else {
        var reader = new FileReader(e);
        reader.onload = function (e) {
            console.log(e.target.result);
            previewImage.attr("src", e.target.result);
            previewImage.removeClass("display-none");
            // inputImage.addClass("display-none");
        }
        reader.readAsDataURL(e.target.files[0]);
    }
})