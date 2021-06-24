let cover = $(".cover");

let loginBtn = $("#login");
let loginForm = $(".login-form");
let loginCancel = $(".login-form #cancel");

let singUpBtn = $("#singUp");
let singUpForm = $(".singUp-form");
let singUpCancel = $(".singUp-form #cancel");

let userIcon = $(".user-icon .btn");
let tooltip = $(".tooltip");
let logout = $(".logout");

loginBtn.click(() => {
    cover.removeClass("display-none");
    loginForm.removeClass("display-none");
});
loginCancel.click(() => {
    cover.addClass("display-none");
    loginForm.addClass("display-none");
});

singUpBtn.click(() => {
    cover.removeClass("display-none");
    singUpForm.removeClass("display-none");
});
singUpCancel.click(() => {
    cover.addClass("display-none");
    singUpForm.addClass("display-none");
});

userIcon.click(() => {
    tooltip.toggleClass("display-none");
});
logout.click((e) => {
    if (!confirm("ログアウトしますか？")) {
        e.preventDefault();
    }
})