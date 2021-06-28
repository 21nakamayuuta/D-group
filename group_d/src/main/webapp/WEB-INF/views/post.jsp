<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="css/recipeForm.css" />
    <script src="https://code.iconify.design/1/1.0.6/iconify.min.js"></script>
  </head>
  <!-- task
    ・材料の追加、削除時or作り方の追加、削除時に画像ファイルがリセットさせる
    ・フォーム内のスタイル(フォントサイズなど)
    ・カテゴリのスタイル変更
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
            <!-- <a href="post" class="to-post btn">レシピを投稿する</a> -->
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
                <form:form action="logout" method="POST">
                  <button type="submit" class="logout item">
                    <span class="iconify" data-inline="false" data-icon="carbon:logout"></span>
                    ログアウト
                  </button>
                </form:form>
              </div>
          </c:otherwise>
        </c:choose>
      </div>
    </div>
    </header>
    <main>
      <div class="wrapper">
        <form:form action="postInfoCheck" modelAttribute="postInfo" method="post" class="recipe-form" enctype="multipart/form-data">
          <div class="name">
            <form:errors path="recipeTitle" class="error_msg"/>
            <label for="title" class="title">レシピタイトル</label>
            <form:input type="text" id="title" path="recipeTitle" />
          </div>

          <div class="image">
            <img src="" class="preview display-none" />
            <label for="file" class="image-wrap">
              <div class="text">
                <span
                  class="iconify"
                  data-inline="false"
                  data-icon="akar-icons:image"
                ></span
                ><br />
                <span>クリックして料理の写真を載せる</span>
              </div>
              <form:input path="completeImage" type="file" name="image" accept="image/jpeg,image/png" id="file" class="display-none"/>
            </label>
            <c:if test="${not empty imageError }">
              <span class="error_msg">${imageError }</span>
          </c:if>
          </div>
          <div class="material">
            <c:if test="${not empty foodErrorMsg }">
              <span class="error_msg">${foodErrorMsg }</span>
            </c:if>
            <h3 class="title">材料・分量</h3>
            <c:if test="${not empty nameEmpty }">
              <span class="error_msg">${nameEmpty }</span>
            </c:if>
            <c:if test="${not empty amountEmpty }">
              <span class="error_msg">${amountEmpty }</span>
            </c:if>
            <div class="input">
              <label
                >材料名<form:input type="text" name="material" id="material" path="foodName"
              /></label>
              <label>分量<form:input type="text" name="amount" id="amount" path="amount"/></label>
              <form:button type="submit" class="form-btn" name="foodAdd">追加</form:button>
            </div>
            <ul>

            <c:forEach var="f" items="${foodList }" varStatus="loop">
              <li>
                <form:input type="text" class="material" value="${fn:escapeXml(f.foodName)}" path="foodNameList" /><form:input
                  type="text"
                  class="amount"
                  path="amountList"
                  value="${fn:escapeXml(f.amount)}"
                /><form:button name="foodDel" type="submit" class="form-btn" value="${fn:escapeXml(loop.index) }" >削除</form:button>
              </li>
            </c:forEach>
            </ul>
          </div>
          <div class="time">
            <form:errors path="cookingTime" class="error_msg"/>
            <label class="title" for="time"> 調理時間</label>
            <div class="input">
              <form:input type="number" name="time" id="time" min="1" max="150" path="cookingTime"/>分以内
            </div>
          </div>
          <div class="how-to">
            <h3 class="title">
              作り方<span class="error_msg">${processErrorMsg }<c:if test="${not empty processEmpty }">${processEmpty }</c:if></span>
            </h3>
            <div class="input">
              <form:input type="text" path="processDescription"/>
              <form:button type="submit" class="form-btn" name="processAdd">追加</form:button>
            </div>
            <ul>
              <c:forEach var="p" items="${processList }" varStatus="loop">
                <li>
                  <form:input type="text" value="${fn:escapeXml(p.processDescription)}" path="processInfoList"/><form:button type="submit" class="form-btn" name="processDel" value="${fn:escapeXml(loop.index) }" >
                    削除
                  </form:button>
                </li>
              </c:forEach>
            </ul>
          </div>
          <div class="comment">
            <h3 class="title">
              コメント<span class="error_msg"><form:errors path="overview"/></span>
            </h3>
            <form:textarea name="comment" id="" cols="30" rows="10" path="overview"></form:textarea>
          </div>
          <div class="category">
            <h3 class="title">カテゴリ</h3><span class="error_msg">${categoryErrorMsg}</span>
            <ul class="input">
	          <li><form:checkboxes items="${categoryList}" itemValue="categoryId" itemLabel="categoryName" path="formCategoryId" delimiter=" " /></li>
            </ul>
          </div>
          <form:button type="submit" class="submit post-btn" name="register">レシピ投稿</form:button>
        </form:form>
      </div>
    </main>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="js/header.js"></script>
    <script src="js/post.js"></script>
  </body>
</html>
