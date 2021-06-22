<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <div class="cover ${ display ? '' : 'display-none' }">
      <form action="userTop.html" class="login-form  ${ display ? '' : 'display-none' }">
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
      <!-- 新規登録 -->
      <form:form action="signUp" modelAttribute="signUp" method="post" class="singUp-form  ${ display ? '' : 'display-none' }">

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
              <form:input
              path="userId"
              id="userId"
              placeholder="ID" />
              <form:errors path="userId" class="error_msg"/>
              <span class="error_msg">${errMsgID}</span>
            </label>
          </div>
          <div class="userName">
            <label
              >名前<br />
              <form:input
                path="userName"
                id="userName"
                placeholder="名前" />
                <form:errors path="userName" class="error_msg"/>
            </label>
          </div>
          <div class="password">
            <label
              >パスワード<br />
              <form:password
                path="password"
                id="password"
                placeholder="パスワード"/>
                <form:errors path="password" class="error_msg"/>
            </label>
          </div>
          <div class="repass">
            <label
              >パスワード-確認<br />
              <form:password
                path="repass"
                id="repass"
                placeholder="パスワード"/>
                <form:errors path="repass" class="error_msg"/>
                <span class="error_msg">${errMsgPASS}</span>
            </label>
          </div>
          <form:button type="submit">新規登録</form:button>
        </div>
      </form:form>

    </div>
    <header>
      <div class="header-wrap">
        <h1><a href="./top.html" class="page-title">おさるのレシピ</a></h1>
        <form action="searchResult" class="search-recipe">
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
          <button type="button" id="singUp">新規登録</button>
          <button type="button" id="login">ログイン</button>
        </div>
        <!--  -->
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
            <li class="card">
              <div class="rank-good-wrap">
                <div class="rank"><span>1位</span></div>
                <div class="good">
                  <span
                    class="iconify"
                    data-inline="false"
                    data-icon="bx:bxs-like"
                  ></span
                  ><span class="good-num">1000</span>
                </div>
              </div>
              <a href="#">
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
              <div class="rank-good-wrap">
                <div class="rank"><span>2位</span></div>
                <div class="good">
                  <span
                    class="iconify"
                    data-inline="false"
                    data-icon="bx:bxs-like"
                  ></span
                  ><span class="good-num">500</span>
                </div>
              </div>
              <a href="#">
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
              <div class="rank-good-wrap">
                <div class="rank"><span>3位</span></div>
                <div class="good">
                  <span
                    class="iconify"
                    data-inline="false"
                    data-icon="bx:bxs-like"
                  ></span
                  ><span class="good-num">300</span>
                </div>
              </div>
              <a href="#">
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
