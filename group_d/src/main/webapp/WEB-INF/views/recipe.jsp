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
  <!-- task
    ・いいねボタン機能 -> ゲストのみ総数表示
    ・作ったよボタン機能 -> 会員ユーザー、管理者限定
    ・レシピ削除ボタン機能 -> 管理者限定
    ・iconクリック時のuserページへの遷移
    ・カテゴリをクリックするとカテゴリ検索をするかorクリック出来なくする
    ・画像サイズを調整
  -->
  <body>

     <div class="cover display-none">
      <form
        class="signUp-form display-none"
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
              <input name="userId" id="userId" placeholder="ID"  />
              <span class="error_msg userId"></span>
            </label>
          </div>
          <div class="userName">
            <label
              >名前<br />
              <input name="userName" id="userName" placeholder="名前"   />
              <span class="error_msg userName"></span>
            </label>
          </div>
          <div class="password">
            <label
              >パスワード<br />
              <input
              type="password"
                name="password"
                id="password"
                placeholder="パスワード"

              />
              <span class="error_msg password"></span>
            </label>
          </div>
          <div class="repass">
            <label
              >パスワード-確認<br />
              <input
              type="password"
                name="repass"
                id="repass"
                placeholder="パスワード"
              />
              <span class="error_msg repass"></span>
              <span class="error_msg errNotPassMatch"></span>
            </label>
          </div>
          <button type="button">新規登録</button>
        </div>
      </form>
      <form
        class="login-form display-none"
      >
        <div class="btn" id="cancel">
          <span
            class="iconify"
            data-inline="false"
            data-icon="topcoat:cancel"
          ></span>
        </div>
        <div class="form-wrap">
          <label class="error_msg errNotUserIdOrPass"></label>
          <div class="userId">
            <label
              >ID<br />
              <input
                id="userId"
                placeholder="ID"
                name="loginName"
              />
              <span class="error_msg loginName"></span>
            </label>
          </div>
          <div class="password">
            <label
              >パスワード<br />
              <input
                type="password"
                name="password"
                id="password"
                placeholder="パスワード"
              />
              <span class="error_msg password"></span>
            </label>
          </div>
          <button type="button">ログイン</button>
        </div>
      </form>
    </div>

   <header>
      <div class="header-wrap">
      <h1><a href="./top" class="page-title">おさるのレシピ</a></h1>
      <form:form action="search" modelAttribute="RecipeSearch" method="post" class="search-recipe">
        <form:input path="searchKeyword" id="searchKeyword" placeholder="料理名・食材名"
           autocomplete="off" />
        <form:button>レシピ検索</form:button>
      </form:form>
      <div class="btn-wrap">
        <c:choose>
          <c:when test="${empty user}">
            <button type="button" id="signUp">新規登録</button>
            <button type="button" id="login">ログイン</button>
          </c:when>

          <c:otherwise>
            <a href="post" class="to-post btn">レシピを投稿する</a>
            <div class="user-icon">

              <div class="btn">
                <span class="iconify" data-inline="false" data-icon="carbon:user-avatar-filled"></span>
              </div>

              <div class="tooltip display-none">
                <c:if test="${user.roleId == 1}">
                  <a href="./admin" class="to-admin item">
                    <span class="iconify" data-inline="false" data-icon="dashicons:admin-network"></span>
                    管理ページ
                  </a>
                </c:if>
                 <a href="./mypage" class="to-mypage item">
                    <span class="iconify" data-inline="false" data-icon="carbon:user-avatar-filled"></span>
                    マイページ
                  </a>
                <button type="submit" class="logout item">
                    <span class="iconify" data-inline="false" data-icon="carbon:logout"></span>
                    ログアウト
                  </button>
              </div>
          </c:otherwise>
        </c:choose>
      </div>
    </div>
    </header>

    <main>
      <div class="wrapper">
        <section class="cuisine">
          <div class="good-make-wrap">

            <div class="good ${empty user ? 'not-click' : ''}">
              <span
                class="iconify"
                data-inline="false"
                data-icon="bx:bxs-like"
              ></span>
              <span class="good-num"> ${fn:escapeXml(totalGood)}</span>
            </div>
             <c:if test="${not empty user}">
            <div class="make">
              <span
                class="iconify"
                data-inline="false"
                data-icon="akar-icons:check"
                style="font-size: 40px"
              ></span>
            </div>
             <c:if test="${user.roleId == 1}">
            <div class="delete">
            <form:form action="deleteRecipeAdmin" method="POST" modelAttribute="MyPageForm">
              <span class="iconify" data-inline="false" data-icon="bi:trash" style="color: #ff6d6d; font-size: 40px">
              </span>
             </form:form>
            </div>
            </c:if>
            </c:if>

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


          <a href="/user?userID=${fn:escapeXml(recipeInfo.userId)}" class="post-user">
            <span
              class="iconify"
              data-inline="false"
              data-icon="carbon:user-avatar-filled"
            ></span>
            ${fn:escapeXml(recipeInfo.userName)}
          </a>


          <div class="cuisine-img">
            <img
			  src= "../../imgs/${fn:escapeXml(recipeInfo.completeImage)}"
              alt="${fn:escapeXml(recipeInfo.completeImage)}"
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
  <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="js/auth.js"></script>
    <script src="js/recipe.js"></script>
  </body>
</html>
