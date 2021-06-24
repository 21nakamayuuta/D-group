let yearDom = $(".calendar .year");
let monthDom = $(".calendar .month");
let calendarHeadYear = $(".calendar .year");
let calendarHeadMonth = $(".calendar .month");
let daysDom = $(".calendar .days");
let recipeInfoTitle = $(".calendar-wrap .info .title.date");
let selected = $("<span />", {
    "class": "selected"
});
let postRecipeListDom = $(".post-recipe-list");
let madeRecipeListDom = $(".made-recipe-list");

let cell = "";
let prevBtn = $(".prev");
let nextBtn = $(".next");

const today = new Date();

let selectDate = [
    new Date().getFullYear(),
    new Date().getMonth(),
    new Date().getDate(),
];

/** Todo
 * ヘッダーの動的変更
 * */
let postDate = [];
let madeDate = [];

recipeInfoTitle.text(`${selectDate[0]}年${selectDate[1]}月${selectDate[2]}日`);

let getDates = (year, month) => {
    let firstWeekDay = new Date(year, month, 1).getDay();
    let end = new Date(year, month + 1, 0).getDate();
    let result = [];

    for (let j = 0; j < firstWeekDay; j++) {
        result.unshift([
            new Date(year, month, -j).getFullYear(),
            new Date(year, month, -j).getMonth(),
            new Date(year, month, -j).getDate()
        ]);
    }

    for (let i = 1; i <= end; i++) {
        result.push([year, month, i]);
    }

    let nDate = 1;
    for (let k = result.length; k < 42; k++) {
        result.push([
            new Date(year, month + 1, nDate).getFullYear(),
            new Date(year, month + 1, nDate).getMonth(),
            new Date(year, month + 1, nDate).getDate(),
        ]);
        nDate++;
    }

    createCalendar(year, month, result);
    getRecipeTitle(selectDate);
    recipeInfoTitle.text(`${selectDate[0]}年${selectDate[1]+1}月${selectDate[2]}日`);
}

let createCalendar = (year, month, dates) => {
    calendarHeadYear.text(`${year}年`);
    calendarHeadMonth.text(`${month + 1}月`);
    daysDom.empty();
    let ul = $("<ul />");
    let today = [
        new Date().getFullYear(),
        new Date().getMonth(),
        new Date().getDate(),
    ];
    for (let i = 0; i < dates.length; i++) {
        let notThisMonth =
            dates[i][0] != year ||
            dates[i][1] != month;
        let isToday = dates[i][0] === today[0] && dates[i][1] === today[1] && dates[i][2] === today[2];
        let isSelected = dates[i][0] === selectDate[0] && dates[i][1] === selectDate[1] && dates[i][2] === selectDate[2]

        let li = $("<li />", {
            "class": `cell ${dates[i][0]}-${dates[i][1]}-${dates[i][2]}`
        });

        let mark = $("<div />", {
            "class": "mark",
        })
        let pMark = $("<span />", {
            "class": "pMark"
        });
        let mMark = $("<span />", {
            "class": "mMark"
        });

        if (notThisMonth) {
            li.addClass("not-this-date");
        };

        if (isToday) {
            li.addClass("text-bold");
        };

        ul.append(isSelected ?
            li.append(selected.text(dates[i][2])[0]) :
            li.text(dates[i][2])[0]);

        postDate.forEach(el => {
            if (li.attr("class").split(" ")[1] === el) {
                mark.append(pMark[0]);
            }
        });
        madeDate.forEach(el => {
            if (li.attr("class").split(" ")[1] === el) {
                mark.append(mMark[0]);
            }
        });
        if (mark.children().length && !isSelected) {
            li.append(mark[0]);
        }

        if ((i + 1) % 7 === 0) {
            daysDom.append(ul[0]);
            ul = $("<ul />");
        };

    }
}

let displayRecipe = (postRecipe, madeRecipe) => {
    postRecipeListDom.empty();
    madeRecipeListDom.empty();

    postRecipe.forEach(el => {
        let recipeListDom = $(`<li>
                  <a href="">
                    <span class="icon">
                      <span
                        class="iconify"
                        data-inline="false"
                        data-icon="fluent:food-24-filled"
                      ></span>
                    </span>
                    <span class="recipe-title"></span>
                  </a>
                </li>`);
        recipeListDom.find('a').attr("href", `recipe?recipeID=${el.recipeId}`);
        recipeListDom.find('.recipe-title').text(el.recipeTitle);
        postRecipeListDom.append(recipeListDom);
    });

    madeRecipe.forEach(el => {
        let recipeListDom = $(`<li>
                  <a href="">
                    <span class="icon">
                      <span
                        class="iconify"
                        data-inline="false"
                        data-icon="fluent:food-24-filled"
                      ></span>
                    </span>
                    <span class="recipe-title"></span>
                  </a>
                </li>`);
        recipeListDom.find('a').attr("href", `recipe?recipeID=${el.recipeId}`);
        recipeListDom.find('.recipe-title').text(el.recipeTitle);
        madeRecipeListDom.append(recipeListDom);
    });
}

window.onload = function () {
    getPostMadeDate();
};

prevBtn.click(() => {
    let month = today.getMonth();
    today.setMonth(month - 1);
    getDates(today.getFullYear(), today.getMonth());
});

nextBtn.click(() => {
    let month = today.getMonth();
    today.setMonth(month + 1);
    getDates(today.getFullYear(), today.getMonth());
});

$(".days").click(function (e) {

    if ($(e.target).attr("class").split(" ").length != 1) {

        let targetDate = $(e.target).
        attr("class").
        split(" ")[1].split("-").
        map(el => Number(el));


        if (Number(e.target.textContent) != NaN) {
            selectDate = [...targetDate];
            getDates(today.getFullYear(), today.getMonth());

        }
    }
});

let getRecipeTitle = (selectDate) => {
    axios.get("/userTop/getRecipeTitle", {
        params: {
            year: selectDate[0],
            month: selectDate[1],
            day: selectDate[2],
        }
    }).then((res) => {
        displayRecipe(res.data.postRecipe, res.data.madeRecipe);
    }).catch((err) => {
        console.log(err);
    });
}

let getPostMadeDate = () => {
    axios.get("/userTop/getPostMadeDate").then((res) => {
        postDate = Array.from(new Set(res.data.postRecipe.map(el => `${el.year}-${el.month}-${el.day}`)));
        madeDate = Array.from(new Set(res.data.madeRecipe.map(el => `${el.year}-${el.month}-${el.day}`)));
        getDates(today.getFullYear(), today.getMonth());
    }).catch(el => console.log(el))
}