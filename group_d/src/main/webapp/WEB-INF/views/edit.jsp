<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  <body>
    <header>
      <div class="header-wrap">
        <h1><a href="./top.html" class="page-title">おさるのレシピ</a></h1>
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
        <form class="recipe-form" enctype="multipart/form-data">
          <div class="name">
            <label for="title" class="title">レシピタイトル</label>
            <input
              type="text"
              id="title"
              name="title"
              value="さるでも作れるカレーライス"
            />
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
              <input type="file" name="image" id="file" class="display-none" />
            </label>
          </div>
          <div class="material">
            <h3 class="title">材料・分量</h3>
            <div class="input">
              <label
                >材料名<input type="text" name="material" id="material"
              /></label>
              <label>分量<input type="text" name="amount" id="amount" /></label>
              <button type="button" class="form-btn">追加</button>
            </div>

            <ul>
              <li>
                <input type="text" class="material" value="ニンジン" /><input
                  type="text"
                  class="amount"
                  value="1本"
                /><button type="button" class="form-btn">削除</button>
              </li>
              <li>
                <input type="text" class="material" value="ジャガイモ" /><input
                  type="text"
                  class="amount"
                  value="1個"
                /><button type="button" class="form-btn">削除</button>
              </li>
              <li>
                <input type="text" class="material" value="玉ねぎ" /><input
                  type="text"
                  class="amount"
                  value="1個"
                /><button type="button" class="form-btn">削除</button>
              </li>
              <li>
                <input type="text" class="material" value="鶏肉" /><input
                  type="text"
                  class="amount"
                  value="200"
                /><button type="button" class="form-btn">削除</button>
              </li>
            </ul>
          </div>
          <div class="time">
            <label class="title" for="time"> 調理時間</label>
            <div class="input">
              <input
                type="number"
                name="time"
                id="time"
                min="1"
                max="150"
                value="5"
              />分以内
            </div>
          </div>
          <div class="how-to">
            <h3 class="title">
              作り方<span class="error_msg">エラーメッセージ</span>
            </h3>
            <div class="input">
              <input type="text" />
              <button type="button" class="form-btn">追加</button>
            </div>
            <ul>
              <li>
                <input type="text" value="手順1 野菜を洗う" /><button
                  type="button"
                  class="form-btn"

                >
                  削除
                </button>
              </li>
              <li>
                <input type="text" value="手順2 野菜を切る"/><button
                  type="button"
                  class="form-btn"
                >
                  削除
                </button>
              </li>
            </ul>
          </div>
          <div class="comment">
            <h3 class="title">
              コメント<span class="error_msg">エラーメッセージ</span>
            </h3>
            <textarea name="comment" id="" cols="30" rows="20">
                サルでも作れるくらい簡単なカレーライスですね。
            </textarea>
          </div>
          <div class="category">
            <h3 class="title">カテゴリ</h3>
            <ul class="input">
              <li><input type="checkbox" value="和食" />和食</li>
              <li><input type="checkbox" value="洋食" />洋食</li>
              <li><input type="checkbox" value="中華" />中華</li>
              <li><input type="checkbox" value="デザート" />デザート</li>
              <li><input type="checkbox" value="つけあわせ" />つけあわせ</li>
            </ul>
          </div>
          <button type="submit" class="submit post-btn">レシピ投稿</button>
        </form>
      </div>
    </main>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="js/header.js"></script>
    <script src="js/post.js"></script>
  </body>
</html>
