let cover = $(".cover");
let signUpBtn = $("#signUp");
let loginBtn = $("#login");
let userIcon = $(".user-icon .btn");
let tooltip = $(".tooltip");
let logoutBtn = $(".logout");

let signUpFormDom = $(".signUp-form");
let loginFormDom = $(".login-form");
let signUpForm = {
    userId: signUpFormDom.find("#userId"),
    userName: signUpFormDom.find("#userName"),
    password: signUpFormDom.find("#password"),
    repass: signUpFormDom.find("#repass"),
    btn: signUpFormDom.find("button"),
    cancel: signUpFormDom.find("#cancel")
}
let loginForm = {
    userId: loginFormDom.find("#userId"),
    password: loginFormDom.find("#password"),
    btn: loginFormDom.find("button"),
    cancel: loginFormDom.find("#cancel")
}

let switchShowHide = (form) => {
    cover.toggleClass("display-none");
    form[0].reset();
    form.toggleClass("display-none");
    $(".error_msg").text("");
}

let displayErrMsg = (errMsg) => {
    $(".error_msg").text("");
    for (const [key, value] of Object.entries(errMsg)) {
        $(`.error_msg.${key}`).text(value);
    }
    if (!signUpForm.userId.val()) {
        $(".error_msg.userId").text("IDを入力してください");
    }
    if (signUpForm.userId.val().match(/^[a-zA-Z0-9]+$/)) {
        $(".error_msg.userId").text("半角英数字のみ入力してください");
    }
}

let signUp = () => {
    let params = new URLSearchParams();
    params.append("userId", signUpForm.userId.val());
    params.append("userName", signUpForm.userName.val());
    params.append("password", signUpForm.password.val());
    params.append("repass", signUpForm.repass.val());
    axios.post("/auth/signUp", params).then((res) => {
        let errMsg = res.data.errMsg;
        console.log(errMsg);
        if (Object.keys(errMsg).length < 1) {
            switchShowHide(signUpFormDom);
            location.href = "";
        } else {
            displayErrMsg(errMsg);
        }
    }).catch(el => console.log(el))
}

let login = () => {
    let params = new URLSearchParams();
    params.append("loginName", loginForm.userId.val());
    params.append("password", loginForm.password.val());
    axios.post("/auth/login", params).then((res) => {
        let errMsg = res.data.errMsg;
        if (Object.keys(errMsg).length < 1) {
            switchShowHide(loginFormDom);
            location.href = "";
        } else {
            displayErrMsg(errMsg);
        }
    }).catch(el => console.log(el))
}

let logout = () => {
    axios.post("/auth/logout").then(() => {
        location.href = "top";
    }).catch(el => console.log(el))
}

// modalの表示非表示
signUpBtn.click(() => switchShowHide(signUpFormDom));
loginBtn.click(() => switchShowHide(loginFormDom));
signUpForm.cancel.click(() => switchShowHide(signUpFormDom));
loginForm.cancel.click(() => switchShowHide(loginFormDom));

// 認証処理
signUpForm.btn.click(() => signUp());
loginForm.btn.click(() => login());

// tooltipの表示非表示
userIcon.click(() => tooltip.toggleClass("display-none"));

// ログアウト
logoutBtn.click(() => {
    if (confirm("ログアウトしますか？")) {
        logout();
    }
})