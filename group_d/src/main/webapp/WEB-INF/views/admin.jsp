<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="css/admin.css" />
    <script src="https://code.iconify.design/1/1.0.6/iconify.min.js"></script>
</head>
<body>
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
            <button type="button" id="singUp">新規登録</button>
            <button type="button" id="login">ログイン</button>
          </c:when>

          <c:otherwise>
            <a href="post" class="to-post btn">レシピを投稿する</a>
            <div class="user-icon">

              <div class="btn">
                <span class="iconify" data-inline="false" data-icon="carbon:user-avatar-filled"></span>
              </div>

              <div class="tooltip display-none">
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
            <section class="user-info border">
                <h3 class="title text-bold">ユーザー情報</h3>
                <ul class="info-table">
                	<li class="th text-bold">
                        <span class="name">名前</span>
                        <span class="pass">パスワード</span>
                        <span class="role">権限</span>
                    </li>
                <c:forEach var="admin" items="${userAdminList}">
                  <form:form action="deleteUser" method="POST" modelAttribute="adminUser" >
                    <li class="td">
                        <span class="name">${admin.getUserName()}</span>
                        <span class="pass">${admin.getPassword()}</span>
                        <span class="role">${admin.getRoleId() eq 1 ? "admin" : "user"} </span>
                        <form:button class="form-btn" value="${admin.getUserId()}" name="deleteUserId">削除</form:button>
                    </li>
                  </form:form>
                </c:forEach>
               </ul>
            </section>
            <section class="categories border">
                <h3 class="title text-bold">カテゴリ</h3>
                <form:form action="categoryInsert" modelAttribute="category" method="post" >
                <div class="input">
                    <form:input path="categoryName"/>
                    <form:button class="form-btn">追加</form:button>
                </div>
                </form:form>
                <ul>
				<c:forEach var="category" items="${categoryList}">
                    <li>
                      <div class="content-edit-wrap">
                       <form:form action="categoryEdit" modelAttribute="categoryEdit" method="post" >
                        <form:input path="categoryIdList" type="hidden" value= "${category.categoryId}" />
                        <form:input path="categoryNameList" class="content" value= "${category.categoryName}" />
                        <button type="button" value="${category.categoryName}" class="edit form-btn">編集</button>
                        <form:button name="categoryNameEdit" value="${category.categoryId}" class="edit form-btn" >保存</form:button>
                      </form:form>

                      <form:form action="categoryEditDelete"  modelAttribute="category" method="post" >
                        <form:button name="categoryNameDelete" value="${category.categoryId}" class="form-btn delete">削除</form:button>
                      </form:form>
                      </div>
                    </li>
				</c:forEach>

    </ul>
            </section>
            <section class="total-recipe recipes border">
                <h3 class="title">
                    レシピ：
                    <span class="recipe-num">${recipeAllList.size() > 0 ? recipeAllList.size() : 0 }</span>
                </h3>
                <ul class="recipe-list">
				<c:forEach var="recipe" items="${recipeAllList}">
                    <li class="card">
                        <div class="user">
                            <span class="iconify" data-inline="false" data-icon="carbon:user-avatar-filled">
                            </span>
                            <span>${recipe.getUserName()} </span>
                        </div>
                        <div class="good">
                            <span class="iconify" data-inline="false" data-icon="bx:bxs-like"></span><span
                                class="good-num">${recipe.getGoodCount()}</span>
                        </div>
                        <a href="/recipe?recipeID=${recipe.getRecipeId()}">
                            <div class="img-wrap">
                                <img src="../../imgs/${recipe.getCompleteImage()}" alt="" />
                            </div>
                            <span class="recipe-title">${recipe.getRecipeTitle()}</span>
                        </a>
                    </li>
				</c:forEach>
         
</ul>
            </section>
        </div>
    </main>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="js/auth.js"></script>
    <script src="js/form.js"></script>
</body>

</html>