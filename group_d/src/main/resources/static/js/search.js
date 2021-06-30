let searchBtn = $(".search-form button");
// let searchKeyword = $("#searchKeyword").val();

let resultRecipe = $(".result-recipe");
let resultRecipeDom = {
    searchKeyword: resultRecipe.find(".title .search-keyword"),
    resultNum: resultRecipe.find(".title .result-num"),
    notResultMsg: resultRecipe.find(".not-result-message"),
    recipeList: resultRecipe.find(".recipe-list"),
};

let displaySearchResult = (result) => {
    // location.href = "search"
    resultRecipe.searchKeyword.text(keyword);
    resultRecipe.resultNum.text(result.length);
    if (!result.length) {
        resultRecipe.notResultMsg.text("一致するレシピは見つかりませんでした。");
    }

    result.forEach((recipe) => {
        let card = $(`
        <li class = "card">
             <div class = "good">
             <span class = "iconify"
                data-inline = "false"
                data-icon = "bx:bxs-like">
            </span>
            <span class = "good-num">${recipe.goodCount}</span>
             </div>
             <a href="/recipe?recipeID=${recipe.recipeId}">
             <div class="img-wrap">
                <img src = "../../imgs/${recipe.completeImage}"
                alt = "${recipe.completeImage}" >
             </div>
             <span class="recipe-title">${recipe.title}</span>
             </a>
        </li>`);
        resultRecipe.recipeList.append(card);
    });
};

let getKeywordSearchResult = (keyword) => {
    console.log(keyword);
    axios
        .get(`/search/keyword/${keyword}`)
        .then((res) => {
            console.log(res);
            let result = res.data;
            if (result.length) {
                // location.href = "search";
                displaySearchResult(result);
            }
        })
        .catch((err) => {
            console.log(err);
        });
};

let getCategorySearchResult = (categoryId) => {
    axios
        .get("/search/category", {
            params: {
                categoryId: categoryId,
            },
        })
        .then((res) => {
            console.log(res);
            let result = res.data;
            if (result.length) {
                // location.href = "search";
                displaySearchResult(result);
            }
        })
        .catch((err) => {
            console.log(err);
        });
};

searchBtn.click(() => {
    // console.log(searchKeyword);
    if ($("#searchKeyword").val()) {
        getKeywordSearchResult($("#searchKeyword").val());
        location.href = `search/`;

    } else {
        getKeywordSearchResult($("#searchKeyword").val());
    }
});
window.onload = () => {
    if (location.href == "search") {
        console.log("aaa");
        getKeywordSearchResult($("#searchKeyword").val());
    }
}