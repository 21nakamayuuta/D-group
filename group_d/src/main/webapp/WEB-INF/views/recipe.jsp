<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>おさるのレシピ</title>
    <link rel="stylesheet" href="css/common.css" />
    <link rel="stylesheet" href="css/header.css" />
    <link rel="stylesheet" href="css/recipe.css" />
    <script src="https://code.iconify.design/1/1.0.6/iconify.min.js"></script>
  </head>
  <body>
    <div class="cover display-none">
      <form action="userTop.html" class="login-form display-none">
        <div class="btn" id="cancel">
          <span
            class="iconify"
            data-inline="false"
            data-icon="topcoat:cancel"
          ></span>
        </div>
        <div class="form-wrap">
          <div class="userId">
            <label
              >ID<br />
              <input type="text" name="userId" id="userId" placeholder="ID" />
              <!-- エラー時
                <input type="text" class="error" name="userId" id="userId" placeholder="ID" />
                <span class="error_msg">エラーメッセージ</span>
              -->
            </label>
          </div>
          <div class="password">
            <label
              >パスワード<br />
              <input
                type="text"
                name="password"
                id="password"
                placeholder="パスワード"
              />
            </label>
          </div>
          <button>ログイン</button>
        </div>
      </form>
       <!-- 新規登録 -->
      <form:form action="signUp" modelAttribute="sign" method="post" class="singUp-form  ${ display ? '' : 'display-none' }">

        <div class="btn" id="cancel">
          <span
            class="iconify"
            data-inline="false"
            data-icon="topcoat:cancel"
          ></span>
        </div>
        <div class="form-wrap">
          <div class="userId">
            <label
              >ID<br />
              <form:input
              path="userId"
              id="userId"
              placeholder="ID" />
              <form:errors path="userId" class="error_msg"/>
              <span class="error_msg">${errMsgID}</span>
            </label>
          </div>
          <div class="userName">
            <label
              >名前<br />
              <form:input
                path="userName"
                id="userName"
                placeholder="名前" />
                <form:errors path="userName" class="error_msg"/>
            </label>
          </div>
          <div class="password">
            <label
              >パスワード<br />
              <form:password
                path="password"
                id="password"
                placeholder="パスワード"/>
                <form:errors path="password" class="error_msg"/>
            </label>
          </div>
          <div class="repass">
            <label
              >パスワード-確認<br />
              <form:password
                path="repass"
                id="repass"
                placeholder="パスワード"/>
                <form:errors path="repass" class="error_msg"/>
                <span class="error_msg">${errMsgPASS}</span>
            </label>
          </div>
          <form:button type="submit">新規登録</form:button>
        </div>
      </form:form>
        </div>

    <header>
      <div class="header-wrap">
        <h1><a href=${ login ? 'userTop' : 'top' } class="page-title">おさるのレシピ</a></h1>
        <form action="./searchResult.html" class="search-recipe">
          <input
            type="text"
            name="searchKeyword"
            id="searchKeyword"
            placeholder="料理名・食材名"
          />
          <button type="submit">レシピ検索</button>
        </form>
        <!-- 権限ごとに切り替える部分 -->
        <div class="btn-wrap">
          <button type="button" id="singUp">新規登録</button>
          <button type="button" id="login">ログイン</button>
        </div>
        <!--  -->
      </div>
    </header>
    <main>
      <div class="wrapper">
        <section class="cuisine">
          <div class="good-make-wrap">
            <!-- ゲストのみ css → opacity:1 -->
            <div class="good">
              <span
                class="iconify"
                data-inline="false"
                data-icon="bx:bxs-like"
              ></span>
              <span class="good-num"> ${fn:escapeXml(totalGood)}</span>
            </div>
            <!--  -->
            <!-- 会員ユーザー、管理者のみ表示 -->
            <div class="make">
              <span
                class="iconify"
                data-inline="false"
                data-icon="akar-icons:check"
                style="font-size: 40px"
              ></span>
            </div>
            <!--  -->
            <!-- 管理者のみ表示 -->
            <div class="delete">
              <span
                class="iconify"
                data-inline="false"
                data-icon="bi:trash"
                style="color: #ff6d6d; font-size: 40px"
              ></span>
            </div>
            <!--  -->
          </div>
          <div class="empty"></div>
          <h3 class="title">
            <span
              class="iconify"
              data-inline="false"
              data-icon="fluent:food-24-filled"
            ></span
            > ${fn:escapeXml(recipeInfo.recipeTitle)}
          </h3>
          <a href="./user.html" class="post-user">
            <span
              class="iconify"
              data-inline="false"
              data-icon="carbon:user-avatar-filled"
            ></span>
            ${fn:escapeXml(recipeInfo.userName)}
          </a>
          <div class="cuisine-img">

<!--               src="https://dummyimage.com/600x400/dee0ff/edeeff.png" -->
            <img
			  src= "${fn:escapeXml(recipeInfo.completeImage)}"
              alt=""
            />
          </div>
          <div class="category-material-wrap">
            <div class="category">
              <h3>カテゴリ</h3>
              <div class="underbar"></div>
              <ul class="categories">
                <c:forEach var="c" items="${categoryInfo }">
                  <li class="btn"><span>${fn:escapeXml(c.categoryName)}</span></li>
                </c:forEach>
              </ul>
            </div>
            <div class="material">
              <h3>材料<span>(1人分)</span></h3>
              <div class="underbar"></div>
              <ul class="materials">
                <c:forEach var="f" items="${foodInfo }">
                  <li>
                    <div class="name-amount-wrap">
                      <span>${fn:escapeXml(f.foodName)}</span>
                      <span>${fn:escapeXml(f.amount)}</span>
                    </div>
                    <div class="underbar"></div>
                  </li>
                </c:forEach>
              </ul>
            </div>
          </div>
          <div class="how-to">
            <h3>作り方<span> - ${fn:escapeXml(recipeInfo.cookingTime)}分以内</span></h3>
            <div class="underbar"></div>
            <ul class="process">
              <c:forEach var="p" items="${processInfo }">
                <li>
                  <span
                    >${fn:escapeXml(p.processDescription)}</span>
                </li>
              </c:forEach>
            </ul>
          </div>
          <div class="comment">
            <h3>コメント</h3>
            <div class="underbar"></div>
            <p>
              ${fn:escapeXml(recipeInfo.overview)}
            </p>
          </div>
        </section>
      </div>
    </main>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="js/header.js"></script>
    <script src="js/recipe.js"></script>
  </body>
</html>
