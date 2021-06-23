<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<script src="https://code.iconify.design/1/1.0.6/iconify.min.js"></script>
</head>
<body>
	<div class="cover display-none">
		<form action="userTop.html" class="login-form display-none">
			<div class="btn" id="cancel">
				<span class="iconify" data-inline="false" data-icon="topcoat:cancel"></span>
			</div>
			<div class="form-wrap">
				<div class="userId">
					<label>ID<br /> <input type="text"
						name="userId" id="userId" placeholder="ID" /> <!-- エラー時
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
      <form action="userTop.html" class="singUp-form display-none">
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
            </label>
          </div>
          <div class="userName">
            <label
              >名前<br />
              <input
                type="text"
                name="userName"
                id="userName"
                placeholder="名前"
              />
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
          <div class="repass">
            <label
              >パスワード-確認<br />
              <input
                type="text"
                name="repass"
                id="repass"
                placeholder="パスワード"
              />
            </label>
          </div>
          <button>新規登録</button>
        </div>
      </form>
    </div>
    <header>
      <div class="header-wrap">
        <h1><a href="./top.html" class="page-title">おさるのレシピ</a></h1>

        <form:form action="search" modelAttribute="RecipeSearch" method="post" class="search-recipe">
          <form:input
            path="searchKeyword"
            id="searchKeyword"
            placeholder="料理名・食材名"
          /><%-- type="text" name="searchKeyword" --%>
          <form:button>レシピ検索</form:button>
        </form:form>

        <%-- <!-- 権限ごとに切り替える部分 --> --%>
        <div class="btn-wrap">
          <button type="button" id="singUp">新規登録</button>
          <button type="button" id="login">ログイン</button>
        </div>
        <!--  -->
        <%-- --%>
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

                	<div class="rank"><span>${fn:escapeXml(loop.index+1)}位</span></div>
                	<div class="good">
                  <span

                    class="iconify"
                    data-inline="false"
                    data-icon="bx:bxs-like"
                  ></span
                  ><span class="good-num">${fn:escapeXml(recipe.goodCount)}</span>
                </div>
              </div>
              <a href="/recipe?recipeID=${fn:escapeXml(recipe.recipeId)}">
                <div class="img-wrap">
                  ${fn:escapeXml(recipe.completeImage)}
                </div>
                <span class="recipe-title">${fn:escapeXml(recipe.recipeTitle)}</span>
              </a>
            </li>
            </c:forEach>
          </ul>
        </div>


        <div class="new-recipe recipes">
				<h3 class="title">
					<span class="iconify" data-inline="false"
						data-icon="fluent:food-24-filled"></span>新着レシピ
				</h3>
				<ul class="recipe-list">
					<c:forEach items="${recipeList}" var="recipe">
						<li class="card">
							<div class="good">
								<span class="iconify" data-inline="false"
									data-icon="bx:bxs-like"> </span> <span class="good-num">${fn:escapeXml(recipe.goodCount)}</span>
							</div> <a href="/recipe?recipeID=${fn:escapeXml(recipe.recipeId)}">
								<div class="img-wrap">
									${fn:escapeXml(recipe.completeImage)}</div> <span
								class="recipe-title"> ${fn:escapeXml(recipe.recipeTitle)}
							</span>
						</a>
						</li>
					</c:forEach>
				</ul>
        </div>

        <div class="recipe-category">
          <div class="title">レシピカテゴリ</div>
          <ul class="categories">
            <li class="btn"><span>和食</span></li>
            <li class="btn"><span>洋食</span></li>
            <li class="btn"><span>中華</span></li>
            <li class="btn"><span>デザート</span></li>
            <li class="btn"><span>つけあわせ</span></li>
          </ul>
        </div>
      </div>
    </main>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="js/header.js"></script>
  </body>
</html>
