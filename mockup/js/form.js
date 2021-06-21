let myName = $(".edit-name #name");
let editBtnOfNameForMyPage = $(".edit-name .edit");
let saveBtnOfNameForMyPage = $(".edit-name .save");

let myPass = $(".edit-pass #pass");
let editBtnOfPassForMyPage = $(".edit-pass .edit");
let saveBtnOfPassForMyPage = $(".edit-pass .save");

editBtnOfNameForMyPage.click(() => {
    if (myName.prop("disabled")) {
        myName.prop("disabled", false);
        editBtnOfNameForMyPage.toggleClass("display-none");
        saveBtnOfNameForMyPage.toggleClass("display-none");
    }
});
saveBtnOfNameForMyPage.click(() => {
    if (!myName.prop("disabled")) {
        myName.prop("disabled", true);
        editBtnOfNameForMyPage.toggleClass("display-none");
        saveBtnOfNameForMyPage.toggleClass("display-none");
    }
});

editBtnOfPassForMyPage.click(() => {
    if (myPass.prop("disabled")) {
        myPass.prop("disabled", false);
        editBtnOfPassForMyPage.toggleClass("display-none");
        saveBtnOfPassForMyPage.toggleClass("display-none");
    }
});
saveBtnOfPassForMyPage.click(() => {
    if (!myPass.prop("disabled")) {
        myPass.prop("disabled", true);
        editBtnOfPassForMyPage.toggleClass("display-none");
        saveBtnOfPassForMyPage.toggleClass("display-none");
    }
});