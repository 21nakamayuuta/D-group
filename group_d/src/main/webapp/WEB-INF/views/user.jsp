<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <%-- type="text" name="searchKeyword" --%>
        <form:button>レシピ検索</form:button>
      </form:form>
      <!-- 権限ごとに切り替える部分 -->
      <div class="btn-wrap">
        <c:choose>
          <%-- 未ログイン時 --%>
          <c:when test="${empty user}">
            <button type="button" id="signUp">新規登録</button>
            <button type="button" id="login">ログイン</button>
          </c:when>

          <%-- ログイン時 --%>
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
        <div class="user-recipe recipes">
          <div class="user">
            <span
              class="iconify"
              data-inline="false"
              data-icon="carbon:user-avatar-filled"
            ></span>
            ${fn:escapeXml(recipeList[0].userName)}
          </div>
          <h3 class="title">
            <span
              class="iconify"
              data-inline="false"
              data-icon="fluent:food-24-filled"
            ></span>
<!-- <%--             ${fn:escapeXml(recipeList[0].userName)} --%> -->
            <span class="recipe-num">レシピの総数：${fn:escapeXml(recipeList.size())}</span>
          </h3>

          <ul class="recipe-list">
          <!-- <%--検索結果をforEachで回して表示 --%> -->
			<c:forEach var="search" items="${recipeList}">
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
              <!-- <%-- <a href="recipeSearch">  --%> -->
                <div class="img-wrap">
                  <img
                    src="../../imgs/${fn:escapeXml(search.completeImage)}"
                    alt="${fn:escapeXml(search.completeImage)}"
                  />
				<!-- <%--  ここにレシピIDを渡せる方法を考える
                  <form:form action="recipeSearch" class="search-recipe">
 		            <form:input
 		              path = "recipeId"
 		              type="hidden"
 		              value= "{fn:escapeXml(search.pecipeId)}"
 		            />
 		          </form:form>
 		          --%> -->

                </div>
                <span class="recipe-title"
                  >${fn:escapeXml(search.recipeTitle)}</span
                ></a
              >
            </li>
            </c:forEach>

          <!-- <%--
          <li class="card">
            <div class="good">
              <span class="iconify" data-inline="false" data-icon="bx:bxs-like"></span><span
                class="good-num">1000</span>
            </div>
            <a href="">
              <div class="img-wrap">
                <img src="https://dummyimage.com/600x400/dee0ff/edeeff.png" alt="" />
              </div>
              <span class="recipe-title">オーツミルクで全粒粉入りパンケーキ</span>
            </a>
          </li>
          <li class="card">
            <div class="good">
              <span class="iconify" data-inline="false" data-icon="bx:bxs-like"></span><span
                class="good-num">1000</span>
            </div>
            <a href="">
              <div class="img-wrap">
                <img src="https://dummyimage.com/600x400/dee0ff/edeeff.png" alt="" />
              </div>
              <span class="recipe-title">オーツミルクで全粒粉入りパンケーキ</span>
            </a>
          </li>
          <li class="card">
            <div class="good">
              <span class="iconify" data-inline="false" data-icon="bx:bxs-like"></span><span
                class="good-num">1000</span>
            </div>
            <a href="">
              <div class="img-wrap">
                <img src="https://dummyimage.com/600x400/dee0ff/edeeff.png" alt="" />
              </div>
              <span class="recipe-title">オーツミルクで全粒粉入りパンケーキ</span>
            </a>
          </li>
          <li class="card">
            <div class="good">
              <span class="iconify" data-inline="false" data-icon="bx:bxs-like"></span><span
                class="good-num">1000</span>
            </div>
            <a href="">
              <div class="img-wrap">
                <img src="https://dummyimage.com/600x400/dee0ff/edeeff.png" alt="" />
              </div>
              <span class="recipe-title">オーツミルクで全粒粉入りパンケーキ</span>
            </a>
          </li>
          <li class="card">
            <div class="good">
              <span class="iconify" data-inline="false" data-icon="bx:bxs-like"></span><span
                class="good-num">1000</span>
            </div>
            <a href="">
              <div class="img-wrap">
                <img src="https://dummyimage.com/600x400/dee0ff/edeeff.png" alt="" />
              </div>
              <span class="recipe-title">オーツミルクで全粒粉入りパンケーキ</span>
            </a>
          </li>
          <li class="card">
            <div class="good">
              <span class="iconify" data-inline="false" data-icon="bx:bxs-like"></span><span
                class="good-num">1000</span>
            </div>
            <a href="">
              <div class="img-wrap">
                <img src="https://dummyimage.com/600x400/dee0ff/edeeff.png" alt="" />
              </div>
              <span class="recipe-title">オーツミルクで全粒粉入りパンケーキ</span>
            </a>
          </li>
          <li class="card">
            <div class="good">
              <span class="iconify" data-inline="false" data-icon="bx:bxs-like"></span><span
                class="good-num">1000</span>
            </div>
            <a href="">
              <div class="img-wrap">
                <img src="https://dummyimage.com/600x400/dee0ff/edeeff.png" alt="" />
              </div>
              <span class="recipe-title">オーツミルクで全粒粉入りパンケーキ</span>
            </a>
          </li>
          <li class="card">
            <div class="good">
              <span class="iconify" data-inline="false" data-icon="bx:bxs-like"></span><span
                class="good-num">1000</span>
            </div>
            <a href="">
              <div class="img-wrap">
                <img src="https://dummyimage.com/600x400/dee0ff/edeeff.png" alt="" />
              </div>
              <span class="recipe-title">オーツミルクで全粒粉入りパンケーキ</span>
            </a>
          </li>
          <li class="card">
            <div class="good">
              <span class="iconify" data-inline="false" data-icon="bx:bxs-like"></span><span
                class="good-num">1000</span>
            </div>
            <a href="">
              <div class="img-wrap">
                <img src="https://dummyimage.com/600x400/dee0ff/edeeff.png" alt="" />
              </div>
              <span class="recipe-title">オーツミルクで全粒粉入りパンケーキ</span>
            </a>
          </li>
          <li class="card">
            <div class="good">
              <span class="iconify" data-inline="false" data-icon="bx:bxs-like"></span><span
                class="good-num">1000</span>
            </div>
            <a href="">
              <div class="img-wrap">
                <img src="https://dummyimage.com/600x400/dee0ff/edeeff.png" alt="" />
              </div>
              <span class="recipe-title">オーツミルクで全粒粉入りパンケーキ</span>
            </a>
          </li>
          <li class="card">
            <div class="good">
              <span class="iconify" data-inline="false" data-icon="bx:bxs-like"></span><span
                class="good-num">1000</span>
            </div>
            <a href="">
              <div class="img-wrap">
                <img src="https://dummyimage.com/600x400/dee0ff/edeeff.png" alt="" />
              </div>
              <span class="recipe-title">オーツミルクで全粒粉入りパンケーキ</span>
            </a>
          </li>

           --%> -->


        <!-- </ul>
        <ul class="pagenation">
          <li class="page-num"><a href="">1</a></li>
          <li class="page-num"><a href="">2</a></li>
          <li class="page-num"><a href="">3</a></li>
          <li class="page-num"><a href="">4</a></li>
          <li class="page-num"><a href="">5</a></li>
          <li class="next-page icon">
            <a href="">
              <span class="iconify" data-inline="false" data-icon="entypo:chevron-right"></span>
            </a>
          </li>
        </ul> -->
      </div>
    </div>
  </main>
  <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
  <script src="js/auth.js"></script>
</body>

</html>