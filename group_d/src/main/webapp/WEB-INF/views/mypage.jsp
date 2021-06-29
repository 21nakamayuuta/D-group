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
    <script src="https://code.iconify.design/1/1.0.6/iconify.min.js"></script>
  </head>
  <!-- task
    ・ユーザー情報のバリデーション処理
    ・ユーザー情報の変更のボタンの切り分け
    ・レシピ一覧表示のスタイル
    ・レシピページへの遷移
    ・レシピの削除
    ・レシピの編集ページへの遷移
  -->
  <body>
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
            <button type="button" id="singUp">新規登録</button>
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
        <div class="my-info">

          <form:form action="editName" class="edit-name"  method="POST" modelAttribute="MyPageForm" >
              <label for="name">名前</label>
              <div class="input-btn-wrap">
                <div class="input">
                <span class="error_msg myName"></span>
                  <!-- <span class="error_msg">エラーメッセージ</span> --><br>
              <form:input path="myName" value="${userName}" /><!-- disabledは一旦無し -->
              </div>
              <button type="button"  class="edit display-none" >編集</button>
              <form:button type="submit" class="save " >保存</form:button>
              </div>
            </form:form>

          <form:form action="editPass" class="edit-pass"  method="POST" modelAttribute="MyPageForm" >
              <label for="pass">パスワード</label>
              <div class="input-btn-wrap">
                <div class="input">
                  <!-- <span class="error_msg">エラーメッセージ</span><br> -->
              <form:password path="myPass" value="${password}" /><!-- disabledは一旦無し -->
              </div>
              <button type="button" class="edit display-none">編集</button>
              <form:button type="submit" class="save ">保存</form:button>
              </div>
            </form:form>
        </div>
        <div class="my-recipe recipes">
          <h3 class="title">
            <span
              class="iconify"
              data-inline="false"
              data-icon="fluent:food-24-filled"
            ></span>
            レシピの総数：
            <c:choose>
			<c:when test="${ empty sumResult }">
			<p>0</p>
			</c:when>
			<c:otherwise>
			<p>${ sumResult.recipeCount }</p>
			</c:otherwise>
			</c:choose>
            <span class="recipe-num"></span>
          </h3>


          <ul class="recipe-list">
          <c:forEach items="${RecipeResult}" var="recipe" varStatus="loop">
          <form:form action="deleteORedit" method="POST" modelAttribute="MyPageForm">
            <li class="card">
              <div class="good">
                <span
                  class="iconify"
                  data-inline="false"
                  data-icon="bx:bxs-like"
                ></span
                ><span class="good-num">${fn:escapeXml(recipe.goodCount)}</span>
              </div>
              <a href="/recipe?recipeID=${fn:escapeXml(recipe.recipeId)}">
                <div class="img-wrap">
                  <img
                    src="../../imgs/${fn:escapeXml(recipe.completeImage)}"
                    alt="${fn:escapeXml(recipe.completeImage)}"
                  />
                </div>
                <span class="recipe-title"
                  >${fn:escapeXml(recipe.recipeTitle)}</span
                ></a
              >
              <div class="btn-wrap">
                <form:button type="submit" value="${fn:escapeXml(recipe.recipeId) }" name="editRecipe" class="to-edit form-btn">編集</form:button>
                <form:button type="submit" value="${fn:escapeXml(recipe.recipeId) }" name="deleteRecipe" class="delete-recipe form-btn">削除</form:button>
              </div>
            </li>
            </form:form>
            </c:forEach>
          </ul>
        </div>
      </div>
    </main>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="js/auth.js"></script>
    <script src="js/form.js"></script>
  </body>
</html>
