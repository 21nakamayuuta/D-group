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
  <body>
    <header>
      <div class="header-wrap">
        <h1><a href="./userTop" class="page-title">おさるのレシピ</a></h1>
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
          <a href="" class="to-post btn">レシピを投稿する</a>
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
              <div class="to-mypage item">
                <span
                  class="iconify"
                  data-inline="false"
                  data-icon="carbon:user-avatar-filled"
                ></span>
                マイページ
              </div>
              <button type="button" class="logout item">
                <span
                  class="iconify"
                  data-inline="false"
                  data-icon="carbon:logout"
                ></span>
                ログアウト
              </button>
            </div>
          </div>
        </div>
        <!--  -->
      </div>
    </header>
    <main>
      <div class="wrapper">
        <div class="my-info">

          <form:form action="editName" class="edit-name"  method="POST" modelAttribute="MyPageForm" >
              <label for="name">名前</label>
              <div class="input-btn-wrap">
                <div class="input">
                  <!-- <span class="error_msg">エラーメッセージ</span><br> -->
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
            <li class="card">
              <div class="good">
                <span
                  class="iconify"
                  data-inline="false"
                  data-icon="bx:bxs-like"
                ></span
                ><span class="good-num">1000</span>
              </div>
              <a href="./recipe.html">
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
              <div class="btn-wrap">
                <button type="button" class="to-edit">編集</button>
                <button type="button" class="delete-recipe">削除</button>
              </div>
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
              <a href="./recipe.html">
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
              <div class="btn-wrap">
                <button type="button" class="to-edit">編集</button>
                <button type="button" class="delete-recipe">削除</button>
              </div>
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
              <a href="./recipe.html">
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
              <div class="btn-wrap">
                <button type="button" class="to-edit">編集</button>
                <button type="button" class="delete-recipe">削除</button>
              </div>
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
              <a href="./recipe.html">
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
              <div class="btn-wrap">
                <button type="button" class="to-edit">編集</button>
                <button type="button" class="delete-recipe">削除</button>
              </div>
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
              <a href="./recipe.html">
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
              <div class="btn-wrap">
                <button type="button" class="to-edit">編集</button>
                <button type="button" class="delete-recipe">削除</button>
              </div>
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
              <a href="./recipe.html">
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
              <div class="btn-wrap">
                <button type="button" class="to-edit">編集</button>
                <button type="button" class="delete-recipe">削除</button>
              </div>
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
              <a href="./recipe.html">
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
              <div class="btn-wrap">
                <button type="button" class="to-edit">編集</button>
                <button type="button" class="delete-recipe">削除</button>
              </div>
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
              <a href="./recipe.html">
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
              <div class="btn-wrap">
                <button type="button" class="to-edit">編集</button>
                <button type="button" class="delete-recipe">削除</button>
              </div>
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
              <a href="./recipe.html">
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
              <div class="btn-wrap">
                <button type="button" class="to-edit">編集</button>
                <button type="button" class="delete-recipe">削除</button>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </main>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="js/header.js"></script>
    <script src="js/form.js"></script>
  </body>
</html>
