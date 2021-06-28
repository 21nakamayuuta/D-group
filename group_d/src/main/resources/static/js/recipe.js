let goodIcon = $(".good");
let makeIcon = $(".make");
let deleteIcon = $(".delete");

let userId, recipeId;

goodIcon.click(function () {
    if (!$(this).hasClass("not-click")) {
        goodIcon.css("opacity") != "1" ?
            goodCount() :
            goodCountReset()
    }

});

makeIcon.click(function () {
    makeIcon.css("opacity") != "1" ?
        madeRecipe() :
        resetCheck()
});

deleteIcon.click(() => {
    if (confirm("このレシピ削除しますか"))
        deleteRecipe()
})

let goodCount = () => {
    let params = new URLSearchParams();
    params.append("recipeId", recipeId);
    params.append("userId", userId);
    axios.post("/recipe/good", params).then((el) => {
        $(".good-num").text(el.data);
        goodIcon.css("opacity", "1");
    }).catch(el => console.log(el))
}

let goodCountReset = () => {
    let p = {
        recipeId: recipeId,
        userId: userId,
        year: new Date().getFullYear(),
        month: new Date().getMonth() + 1,
        day: new Date().getDate()
    }
    axios.delete(`/recipe/good/${p.recipeId}/${p.userId}/${p.year}/${p.month}/${p.day}`).then((el) => {
        $(".good-num").text(el.data);
        goodIcon.css("opacity", ".35")
    }).catch(el => console.log(el))
}
let madeRecipe = () => {
    let params = new URLSearchParams();
    params.append("userId", userId);
    params.append("recipeId", recipeId);
    axios.post("/recipe/made", params).then((el) => {
        makeIcon.css("opacity", "1");
    }).catch(el => console.log(el))
}

let resetCheck = () => {
    let p = {
        recipeId: recipeId,
        userId: userId,
        year: new Date().getFullYear(),
        month: new Date().getMonth() + 1,
        day: new Date().getDate()
    }
    axios.delete(`/recipe/made/${p.recipeId}/${p.userId}/${p.year}/${p.month}/${p.day}`).then((el) => {
        console.log(el.data);
        makeIcon.css("opacity", ".35")
    }).catch(el => console.log(el))
}
let deleteRecipe = () => {
    axios.delete(`/recipe/post/${recipeId}`).then((el) => {
        console.log(el);
        location.href = "top";
    }).catch(el => console.log(el))
}

let getRecipeInfo = () => {
    axios.get("/recipe/getInfo").then((el) => {
        userId = el.data.user.userId;
        recipeId = el.data.recipeId;
        goodCheck();
        madeCheck();
    }).catch(el => console.log(el))
}
let goodCheck = () => {
    axios.get("/recipe/goodCheck", {
        params: {
            recipeId: recipeId,
            userId: userId,
            year: new Date().getFullYear(),
            month: new Date().getMonth() + 1,
            day: new Date().getDate()
        }
    }).then((el) => {
        if (el.data) {
            goodIcon.css("opacity", "1");
        }
    }).catch(el => console.log(el))
}
let madeCheck = () => {
    axios.get("/recipe/madeCheck", {
        params: {
            recipeId: recipeId,
            userId: userId,
            year: new Date().getFullYear(),
            month: new Date().getMonth() + 1,
            day: new Date().getDate()
        }
    }).then((el) => {
        if (el.data) {
            makeIcon.css("opacity", "1");
        }
    }).catch(el => console.log(el))
}
window.onload = function () {
    getRecipeInfo();
};