<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    <link rel="stylesheet" href="css/recipeList.css" />
    <link rel="stylesheet" href="css/category.css" />
    <script src="https://code.iconify.design/1/1.0.6/iconify.min.js"></script>
  </head>
  <body>
    <!-- task (・ → 未完了, ✔ → 完了済み)
      header
      ・検索フォームのオートコンプリート時のスタイル
      top
      ・ランキング表示
        ✔レシピ一覧を表示させる
        ・同じいいね数の場合のランキングの表示
        ・レシピページへの遷移
     -->
    <div class="cover ${ display ? '' : 'display-none' }">
      <!-- 新規登録フォーム -->
      <form:form
        action="signUp"
        modelAttribute="sign"
        method="post"
        class="singUp-form ${ SignUpDisplay ? '' : 'display-none' }"
      >
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
              <form:input path="userId" id="userId" placeholder="ID" />
              <form:errors path="userId" class="error_msg" />
              <span class="error_msg">${errMsgID}</span>
            </label>
          </div>
          <div class="userName">
            <label
              >名前<br />
              <form:input path="userName" id="userName" placeholder="名前" />
              <form:errors path="userName" class="error_msg" />
            </label>
          </div>
          <div class="password">
            <label
              >パスワード<br />
              <form:password
                path="password"
                id="password"
                placeholder="パスワード"
              />
              <form:errors path="password" class="error_msg" />
            </label>
          </div>
          <div class="repass">
            <label
              >パスワード-確認<br />
              <form:password
                path="repass"
                id="repass"
                placeholder="パスワード"
              />
              <form:errors path="repass" class="error_msg" />
              <span class="error_msg">${errMsgPASS}</span>
            </label>
          </div>
          <form:button type="submit">新規登録</form:button>
        </div>
      </form:form>
      <!-- ログインフォーム -->
      <form:form
        action="login"
        class="login-form ${ LoginDisplay ? '' : 'display-none' }"
        method="POST"
        modelAttribute="loginForm"
      >
        <div class="btn" id="cancel">
          <span
            class="iconify"
            data-inline="false"
            data-icon="topcoat:cancel"
          ></span>
        </div>
        <div class="form-wrap">
          <label class="error_msg">${errMsg}</label>
          <div class="userId">
            <label
              >ID<br />
              <form:input
                type="text"
                name="userId"
                id="userId"
                placeholder="ID"
                path="loginName"
              />
              <form:errors
                path="loginName"
                class="error_msg"
                cssStyle="color:red"
              />
            </label>
          </div>
          <div class="password">
            <label
              >パスワード<br />
              <form:input
                type="password"
                name="password"
                id="password"
                placeholder="パスワード"
                path="password"
              />
              <form:errors
                path="password"
                class="error_msg"
                cssStyle="color:red"
              />
            </label>
          </div>
          <button>ログイン</button>
        </div>
      </form:form>
    </div>

    <header>
      <div class="header-wrap">
        <h1><a href="./top" class="page-title">おさるのレシピ</a></h1>

        <form:form
          action="search"
          modelAttribute="RecipeSearch"
          method="post"
          class="search-recipe"
        >
          <form:input
            path="searchKeyword"
            id="searchKeyword"
            placeholder="料理名・食材名"
          /><%-- type="text" name="searchKeyword" --%>
          <form:button>レシピ検索</form:button>
        </form:form>
      
        <!-- 権限ごとに切り替える部分 -->
        <div class="btn-wrap">
          <c:choose>
            <%-- 未ログイン時 --%>
            <c:when test="${empty user}">
              <button type="button" id="singUp">新規登録</button>
              <button type="button" id="login">ログイン</button>
            </c:when>
            <%-- ログイン時 --%>
            <c:otherwise>
                <a href="post" class="to-post btn">レシピを投稿する</a>
                <div class="user-icon">
                  <div class="btn">
                    <span
                      class="iconify"
                      data-inline="false"
                      data-icon="carbon:user-avatar-filled"
                    ></span>
                  </div>
                  <div class="tooltip display-none">
                    <!-- 管理者ログイン時追加 -->
                    <a href="./admin" class="to-admin item">
                      <span
                        class="iconify"
                        data-inline="false"
                        data-icon="dashicons:admin-network"
                      ></span>
                      管理ページ
                    </a>
                    <a href="./mypage" class="to-mypage item">
                      <span
                        class="iconify"
                        data-inline="false"
                        data-icon="carbon:user-avatar-filled"
                      ></span>
                      マイページ
                    </a>
                    <form:form action="logout" method="POST">
                      <button type="submit" class="logout item">
                        <span
                          class="iconify"
                          data-inline="false"
                          data-icon="carbon:logout"
                        ></span>
                        ログアウト
                      </button>
                    </form:form>
                  </div>
            　　</div>
            </c:otherwise>
          </c:choose>
        </div>
      </div>
    </header>
    <main>
      <div class="wrapper">
        <div class="popular-ranking recipes">
          <h3 class="title">
            <span
              class="iconify"
              data-inline="false"
              data-icon="fluent:food-24-filled"
            ></span>

            人気ランキング
          </h3>
          <ul class="ranking recipe-list">
            <c:forEach items="${rankingList}" var="recipe" varStatus="loop">
              <li class="card">
                <div class="rank-good-wrap">
                  <div class="rank">
                    <span>${fn:escapeXml(loop.index+1)}位</span>
                  </div>
                  <div class="good">
                    <span
                      class="iconify"
                      data-inline="false"
                      data-icon="bx:bxs-like"
                    ></span
                    ><span class="good-num"
                      >${fn:escapeXml(recipe.goodCount)}</span
                    >
                  </div>
                </div>
                <a href="/recipe?recipeID=${fn:escapeXml(recipe.recipeId)}">
                  <div class="img-wrap">
                    <img src="../../imgs/${fn:escapeXml(recipe.completeImage)}" alt="${fn:escapeXml(recipe.completeImage)}">
                  </div>
                  <span class="recipe-title"
                    >${fn:escapeXml(recipe.recipeTitle)}</span
                  >
                </a>
              </li>
            </c:forEach>
          </ul>
        </div>

        <div class="new-recipe recipes">
          <h3 class="title">
            <span
              class="iconify"
              data-inline="false"
              data-icon="fluent:food-24-filled"
            ></span
            >新着レシピ
          </h3>
          <ul class="recipe-list">
            <c:forEach items="${recipeList}" var="recipe">
              <li class="card">
                <div class="good">
                  <span
                    class="iconify"
                    data-inline="false"
                    data-icon="bx:bxs-like"
                  >
                  </span>
                  <span class="good-num"
                    >${fn:escapeXml(recipe.goodCount)}</span
                  >
                </div>
                <a href="/recipe?recipeID=${fn:escapeXml(recipe.recipeId)}">
                  <div class="img-wrap">
                   <img src="../../imgs/${fn:escapeXml(recipe.completeImage)}" alt="${fn:escapeXml(recipe.completeImage)}">
                  </div>
                  <span class="recipe-title">
                    ${fn:escapeXml(recipe.recipeTitle)}
                  </span>
                </a>
              </li>
            </c:forEach>
          </ul>
        </div>

        <div class="recipe-category">
          <div class="title">レシピカテゴリ</div>
          <ul class="categories">
            <c:forEach var="category" items="${categoryList}">
              <form:form
                action="categorySearch"
                modelAttribute="categorySearch"
                method="get"
              >
                <%--
                formにcategoryIdとcategoryNameを反映させたいのでinputタグを使用している
                --%>
                <form:input
                  path="categoryId"
                  type="hidden"
                  value="${fn:escapeXml(category.categoryId)}"
                />
                <form:input
                  path="categoryName"
                  type="hidden"
                  value="${fn:escapeXml(category.categoryName)}"
                />
                <li class="category btn">
                  <form:button>
                    ${fn:escapeXml(category.categoryName)}</form:button
                  >
                </li>
              </form:form>
            </c:forEach>
            <%--
            <li class="btn"><span>和食</span></li>
            <li class="btn"><span>洋食</span></li>
            <li class="btn"><span>中華</span></li>
            <li class="btn"><span>デザート</span></li>
            <li class="btn"><span>つけあわせ</span></li>
            --%>
          </ul>
        </div>
      </div>
    </main>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="js/header.js"></script>
  </body>
</html>
