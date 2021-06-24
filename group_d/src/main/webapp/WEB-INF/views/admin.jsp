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
                <a href="./post.html" class="to-post btn">レシピを投稿する</a>
                <div class="user-icon">
                    <div class="btn">
                        <span class="iconify" data-inline="false" data-icon="carbon:user-avatar-filled"></span>
                    </div>
                    <div class="tooltip display-none">
                        <!-- 管理者ログイン時追加 -->
                        <!-- <a href="./admin.html" class="to-admin item">
                <span
                  class="iconify"
                  data-inline="false"
                  data-icon="dashicons:admin-network"
                ></span>
                管理ページ
              </a> -->
                        <!--  -->
                        <a href="./mypage.html" class="to-mypage item">
                            <span class="iconify" data-inline="false" data-icon="carbon:user-avatar-filled"></span>
                            マイページ
                        </a>
              <form:form action=logout" method="POST">
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
            <section class="user-info border">
                <h3 class="title text-bold">ユーザー情報</h3>
                <ul class="info-table">
                    <li class="th text-bold">
                        <span class="name">名前</span>
                        <span class="pass">パスワード</span>
                        <span class="role">権限</span>
                    </li>
                    <li class="td">
                        <span class="name">田中</span>
                        <span class="pass">tanakapass</span>
                        <span class="role">user</span>
                        <button class="form-btn">削除</button>
                    </li>
                    <li class="td">
                        <span class="name">田中</span>
                        <span class="pass">tanakapass</span>
                        <span class="role">user</span>
                        <button class="form-btn">削除</button>
                    </li>
                    <li class="td">
                        <span class="name">田中</span>
                        <span class="pass">tanakapass</span>
                        <span class="role">user</span>
                        <button class="form-btn">削除</button>
                    </li>
                </ul>
            </section>
            <section class="categories border">
                <h3 class="title text-bold">カテゴリ</h3>
                <div class="input">
                    <input type="text">
                    <button type="button" class="form-btn">追加</button>
                </div>
                <ul>
                    <li>
                        <div class="content-edit-wrap">
                        <input type="text" class="content" value="和食" disabled>
                        <button type="button" class="edit form-btn">編集</button>
                        <button type="button" class="save form-btn display-none" >保存</button>
                        </div>
                        <button type="button" class="form-btn delete">削除</button>
                    </li>
                    <li>
                        <div class="content-edit-wrap">
                        <input type="text" class="content" value="洋食" disabled>
                        <button type="button" class="edit form-btn">編集</button>
                        <button type="button" class="save form-btn display-none" >保存</button>
                        </div>
                        <button type="button" class="form-btn delete">削除</button>
                    </li>

                    <li>
                        <div class="content-edit-wrap">
                        <input type="text" class="content" value="中華" disabled>
                        <button type="button" class="edit form-btn">編集</button>
                        <button type="button" class="save form-btn display-none" >保存</button>
                        </div>
                        <button type="button" class="form-btn delete">削除</button>
                    </li>

                    <li>
                        <div class="content-edit-wrap">
                        <input type="text" class="content" value="デザート" disabled>
                        <button type="button" class="edit form-btn">編集</button>
                        <button type="button" class="save form-btn display-none" >保存</button>
                        </div>
                        <button type="button" class="form-btn delete">削除</button>
                    </li>

                    <li>
                        <div class="content-edit-wrap">
                        <input type="text" class="content" value="つけあわせ" disabled>
                        <button type="button" class="edit form-btn">編集</button>
                        <button type="button" class="save form-btn display-none" >保存</button>
                        </div>
                        <button type="button" class="form-btn delete">削除</button>
                    </li>
                </ul>
            </section>
            <section class="total-recipe recipes border">
                <h3 class="title">
                    レシピ：
                    <span class="recipe-num">20</span>
                </h3>
                <ul class="recipe-list">
                    <li class="card">
                        <div class="user">
                            <span class="iconify" data-inline="false" data-icon="carbon:user-avatar-filled">
                            </span>
                            <span>田中</span>
                        </div>
                        <div class="good">
                            <span class="iconify" data-inline="false" data-icon="bx:bxs-like"></span><span
                                class="good-num">1000</span>
                        </div>
                        <a href="./recipe.html">
                            <div class="img-wrap">
                                <img src="https://dummyimage.com/600x400/dee0ff/edeeff.png" alt="" />
                            </div>
                            <span class="recipe-title">オーツミルクで全粒粉入りパンケーキ</span>
                        </a>
                    </li>
                    <li class="card">
                        <div class="user">
                            <span class="iconify" data-inline="false" data-icon="carbon:user-avatar-filled">
                            </span>
                            <span>田中</span>
                        </div>
                        <div class="good">
                            <span class="iconify" data-inline="false" data-icon="bx:bxs-like"></span><span
                                class="good-num">1000</span>
                        </div>
                        <a href="./recipe.html">
                            <div class="img-wrap">
                                <img src="https://dummyimage.com/600x400/dee0ff/edeeff.png" alt="" />
                            </div>
                            <span class="recipe-title">オーツミルクで全粒粉入りパンケーキ</span>
                        </a>
                    </li>
                    <li class="card">
                        <div class="user">
                            <span class="iconify" data-inline="false" data-icon="carbon:user-avatar-filled">
                            </span>
                            <span>田中</span>
                        </div>
                        <div class="good">
                            <span class="iconify" data-inline="false" data-icon="bx:bxs-like"></span><span
                                class="good-num">1000</span>
                        </div>
                        <a href="./recipe.html">
                            <div class="img-wrap">
                                <img src="https://dummyimage.com/600x400/dee0ff/edeeff.png" alt="" />
                            </div>
                            <span class="recipe-title">オーツミルクで全粒粉入りパンケーキ</span>
                        </a>
                    </li>
                    <li class="card">
                        <div class="user">
                            <span class="iconify" data-inline="false" data-icon="carbon:user-avatar-filled">
                            </span>
                            <span>田中</span>
                        </div>
                        <div class="good">
                            <span class="iconify" data-inline="false" data-icon="bx:bxs-like"></span><span
                                class="good-num">1000</span>
                        </div>
                        <a href="./recipe.html">
                            <div class="img-wrap">
                                <img src="https://dummyimage.com/600x400/dee0ff/edeeff.png" alt="" />
                            </div>
                            <span class="recipe-title">オーツミルクで全粒粉入りパンケーキ</span>
                        </a>
                    </li>
                    <li class="card">
                        <div class="user">
                            <span class="iconify" data-inline="false" data-icon="carbon:user-avatar-filled">
                            </span>
                            <span>田中</span>
                        </div>
                        <div class="good">
                            <span class="iconify" data-inline="false" data-icon="bx:bxs-like"></span><span
                                class="good-num">1000</span>
                        </div>
                        <a href="./recipe.html">
                            <div class="img-wrap">
                                <img src="https://dummyimage.com/600x400/dee0ff/edeeff.png" alt="" />
                            </div>
                            <span class="recipe-title">オーツミルクで全粒粉入りパンケーキ</span>
                        </a>
                    </li>
                    <li class="card">
                        <div class="user">
                            <span class="iconify" data-inline="false" data-icon="carbon:user-avatar-filled">
                            </span>
                            <span>田中</span>
                        </div>
                        <div class="good">
                            <span class="iconify" data-inline="false" data-icon="bx:bxs-like"></span><span
                                class="good-num">1000</span>
                        </div>
                        <a href="./recipe.html">
                            <div class="img-wrap">
                                <img src="https://dummyimage.com/600x400/dee0ff/edeeff.png" alt="" />
                            </div>
                            <span class="recipe-title">オーツミルクで全粒粉入りパンケーキ</span>
                        </a>
                    </li>
                </ul>
                <!-- <ul class="pagenation">
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
            </section>
        </div>
    </main>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="js/header.js"></script>
    <script src="js/form.js"></script>
<<<<<<< HEAD

=======
>>>>>>> branch 'develop' of https://github.com/21nakamayuuta/D-group.git
>>>>>>> refs/heads/maekawa
</body>

</html>