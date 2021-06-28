let goodIcon = $(".good");
let makeIcon = $(".make");
let deleteIcon = $(".delete");

goodIcon.click(function () {
    if (!$(this).hasClass("not-click")) {
        goodIcon.css("opacity") != "1" ?
            goodIcon.css("opacity", "1") :
            goodIcon.css("opacity", ".35")
    }
});

makeIcon.click(function () {
    makeIcon.css("opacity") != "1" ?
        makeIcon.css("opacity", "1") :
        makeIcon.css("opacity", ".35")
});

deleteIcon.click(() => {
    if (confirm("このレシピ削除しますか"))
        location.href = "search";
})

let goodCount = () => {

}
let madeRecipe = () => {

}
let deleteRecipe = () => {

}

let resetCheck = () => {

}