
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
    <link rel="stylesheet" href="css/calendar.css" />
    <link rel="stylesheet" href="css/recipeList.css" />
    <script src="https://code.iconify.design/1/1.0.6/iconify.min.js"></script>
  </head>
  <body>
    <header>
      <div class="header-wrap">
        <h1><a href="./userTop" class="page-title">おさるのレシピ</a></h1>
        <form:form action="search" modelAttribute="RecipeSearch" method="post" class="search-recipe">
          <form:input
            path="searchKeyword"
            id="searchKeyword"
            placeholder="料理名・食材名"
          /><%-- type="text" name="searchKeyword" --%>
          <form:button>レシピ検索</form:button>
        </form:form>
        <!-- 権限ごとに切り替える部分 -->
        <div class="btn-wrap">
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
              <a href="" class="to-admin item">
                <span
                  class="iconify"
                  data-inline="false"
                  data-icon="dashicons:admin-network"
                ></span>
                管理ページ
              </a>
              <!--  -->
              <a href="./mypage.html" class="to-mypage item">
                <span
                  class="iconify"
                  data-inline="false"
                  data-icon="carbon:user-avatar-filled"
                ></span>
                マイページ
              </a>
              <form:form action="top" method="POST">
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
        </div>
        <!--  -->
      </div>
    </header>
    <main>
      <div class="wrapper">
        <div class="calendar-wrap">
          <div class="calendar"></div>
          <div class="info"></div>
        </div>
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
            <c:forEach var="category" items="${categoryList}">
      		  <form:form action="categorySearch" modelAttribute="categorySearch" method="get">
      		    <%-- formにcategoryIdとcategoryNameを反映させたいのでinputタグを使用している --%>
       		    <form:input path="categoryId" type="hidden" value="${fn:escapeXml(category.categoryId)}"  />
       		    <form:input path="categoryName" type="hidden" value="${fn:escapeXml(category.categoryName)}"  />
       		    <li class="category btn"><form:button > ${fn:escapeXml(category.categoryName)}</form:button> </li>
       		  </form:form>
		    </c:forEach>
          </ul>
        </div>
      </div>
    </main>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="js/header.js"></script>
  </body>
</html>
