<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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


        <!-- 権限ごとに切り替える部分 -->
        <div class="btn-wrap">
          <button type="button" id="singUp">新規登録</button>
          <button type="button" id="login">ログイン</button>
        </div>
      </div>
    </header>
    <main>
      <div class="wrapper">
        <div class="result-recipe recipes">
          <h3 class="title">
            <span
              class="iconify"
              data-inline="false"
              data-icon="fluent:food-24-filled"
            ></span>
            <span class="search-keyword">${fn:escapeXml(searchKeyword)}</span>
            のレシピ
            <span class="result-num">${searchList.size()}</span>件
          </h3>
          <%--検索結果がないときのエラーメッセージ 表示の仕方を直す必要がある--%>
          <span class="result-num"> ${fn:escapeXml(message)}</span>

          <ul class="recipe-list">
          <%--検索結果をforEachで回して表示 --%>
			<c:forEach var="search" items="${searchList}">
            <li class="card">
              <div class="good">
                <span
                  class="iconify"
                  data-inline="false"
                  data-icon="bx:bxs-like"
                ></span
                ><span class="good-num">${fn:escapeXml(search.goodCount)}</span>
              </div>
              <a href="/recipe?recipeID=${fn:escapeXml(search.recipeId)}">
              <%-- <a href="recipeSearch">  --%>
                <div class="img-wrap">
                  <img
                    src="https://dummyimage.com/600x400/dee0ff/edeeff.png"
                    alt=""
                  />
				<%--  ここにレシピIDを渡せる方法を考える
                  <form:form action="recipeSearch" class="search-recipe">
 		            <form:input
 		              path = "recipeId"
 		              type="hidden"
 		              value= "{fn:escapeXml(search.pecipeId)}"
 		            />
 		          </form:form>
 		          --%>

                </div>
                <span class="recipe-title"
                  >${fn:escapeXml(search.recipeTitle)}</span
                ></a
              >

            </li>
            </c:forEach>

<%--
            <li class="card">
              <div class="good">
                <span
                  class="iconify"
                  data-inline="false"
                  data-icon="bx:bxs-like"
                ></span
                ><span class="good-num">1000</span>
              </div>
              <a href="">
                <div class="img-wrap">
                  <img
                    src="https://dummyimage.com/600x400/dee0ff/edeeff.png"
                    alt=""
                  />
                </div>
                <span class="recipe-title"
                  >オーツミルクで全粒粉入りパンケーキ</span
                >
              </a>
            </li>
            <li class="card">
              <div class="good">
                <span
                  class="iconify"
                  data-inline="false"
                  data-icon="bx:bxs-like"
                ></span
                ><span class="good-num">1000</span>
              </div>
              <a href="">
                <div class="img-wrap">
                  <img
                    src="https://dummyimage.com/600x400/dee0ff/edeeff.png"
                    alt=""
                  />
                </div>
                <span class="recipe-title"
                  >オーツミルクで全粒粉入りパンケーキ</span
                >
              </a>
            </li>
            <li class="card">
              <div class="good">
                <span
                  class="iconify"
                  data-inline="false"
                  data-icon="bx:bxs-like"
                ></span
                ><span class="good-num">1000</span>
              </div>
              <a href="">
                <div class="img-wrap">
                  <img
                    src="https://dummyimage.com/600x400/dee0ff/edeeff.png"
                    alt=""
                  />
                </div>
                <span class="recipe-title"
                  >オーツミルクで全粒粉入りパンケーキ</span
                >
              </a>
            </li>
            <li class="card">
              <div class="good">
                <span
                  class="iconify"
                  data-inline="false"
                  data-icon="bx:bxs-like"
                ></span
                ><span class="good-num">1000</span>
              </div>
              <a href="">
                <div class="img-wrap">
                  <img
                    src="https://dummyimage.com/600x400/dee0ff/edeeff.png"
                    alt=""
                  />
                </div>
                <span class="recipe-title"
                  >オーツミルクで全粒粉入りパンケーキ</span
                >
              </a>
            </li>
            <li class="card">
              <div class="good">
                <span
                  class="iconify"
                  data-inline="false"
                  data-icon="bx:bxs-like"
                ></span
                ><span class="good-num">1000</span>
              </div>
              <a href="">
                <div class="img-wrap">
                  <img
                    src="https://dummyimage.com/600x400/dee0ff/edeeff.png"
                    alt=""
                  />
                </div>
                <span class="recipe-title"
                  >オーツミルクで全粒粉入りパンケーキ</span
                >
              </a>
            </li>
            <li class="card">
              <div class="good">
                <span
                  class="iconify"
                  data-inline="false"
                  data-icon="bx:bxs-like"
                ></span
                ><span class="good-num">1000</span>
              </div>
              <a href="">
                <div class="img-wrap">
                  <img
                    src="https://dummyimage.com/600x400/dee0ff/edeeff.png"
                    alt=""
                  />
                </div>
                <span class="recipe-title"
                  >オーツミルクで全粒粉入りパンケーキ</span
                ></a
              >
            </li>
            <li class="card">
              <div class="good">
                <span
                  class="iconify"
                  data-inline="false"
                  data-icon="bx:bxs-like"
                ></span
                ><span class="good-num">1000</span>
              </div>
              <a href="">
                <div class="img-wrap">
                  <img
                    src="https://dummyimage.com/600x400/dee0ff/edeeff.png"
                    alt=""
                  />
                </div>
                <span class="recipe-title"
                  >オーツミルクで全粒粉入りパンケーキ</span
                >
              </a>
            </li>
            <li class="card">
              <div class="good">
                <span
                  class="iconify"
                  data-inline="false"
                  data-icon="bx:bxs-like"
                ></span
                ><span class="good-num">1000</span>
              </div>
              <a href="">
                <div class="img-wrap">
                  <img
                    src="https://dummyimage.com/600x400/dee0ff/edeeff.png"
                    alt=""
                  />
                </div>
                <span class="recipe-title"
                  >オーツミルクで全粒粉入りパンケーキ</span
                >
              </a>
            </li>
            <li class="card">
              <div class="good">
                <span
                  class="iconify"
                  data-inline="false"
                  data-icon="bx:bxs-like"
                ></span
                ><span class="good-num">1000</span>
              </div>
              <a href="">
                <div class="img-wrap">
                  <img
                    src="https://dummyimage.com/600x400/dee0ff/edeeff.png"
                    alt=""
                  />
                </div>
                <span class="recipe-title"
                  >オーツミルクで全粒粉入りパンケーキ</span
                >
              </a>
            </li>
            <li class="card">
              <div class="good">
                <span
                  class="iconify"
                  data-inline="false"
                  data-icon="bx:bxs-like"
                ></span
                ><span class="good-num">1000</span>
              </div>
              <a href="">
                <div class="img-wrap">
                  <img
                    src="https://dummyimage.com/600x400/dee0ff/edeeff.png"
                    alt=""
                  />
                </div>
                <span class="recipe-title"
                  >オーツミルクで全粒粉入りパンケーキ</span
                >
              </a>
            </li>
            <li class="card">
              <div class="good">
                <span
                  class="iconify"
                  data-inline="false"
                  data-icon="bx:bxs-like"
                ></span
                ><span class="good-num">1000</span>
              </div>
              <a href="">
                <div class="img-wrap">
                  <img
                    src="https://dummyimage.com/600x400/dee0ff/edeeff.png"
                    alt=""
                  />
                </div>
                <span class="recipe-title"
                  >オーツミルクで全粒粉入りパンケーキ</span
                >
              </a>
            </li>
 --%>
          </ul>
        </div>
        <ul class="pagenation">
          <li class="page-num"><a href="">1</a></li>
          <li class="page-num"><a href="">2</a></li>
          <li class="page-num"><a href="">3</a></li>
          <li class="page-num"><a href="">4</a></li>
          <li class="page-num"><a href="">5</a></li>
          <li class="next-page icon">
            <a href="">
              <span
                class="iconify"
                data-inline="false"
                data-icon="entypo:chevron-right"
              ></span>
            </a>
          </li>
        </ul>
        <div class="recipe-category">
          <div class="title">レシピカテゴリ</div>
          <ul class="categories">
          <c:forEach var="category" items="${categoryList}">
            <li class="category btn"><span>${fn:escapeXml(category.categoryName)}</span></li>
           <%-- <li class="category btn"><span>洋食</span></li>和食
            <li class="category btn"><span>中華</span></li>
            <li class="category btn"><span>デザート</span></li>
            <li class="category btn"><span>つけあわせ</span></li>
           --%>
          </c:forEach>
          </ul>
        </div>
      </div>
    </main>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="js/header.js"></script>
  </body>
</html>
