# 使用 waitFor 指令

許多網站使用前端 JavaScript 技術強化網頁瀏覽效果，特別是使用 AJAX 技術或 SPA（Single-page application）架構的網站，在進行自動化測試時，需要先等待網頁的背景動作完成。例如點選送出指令後，透過 AJAX 更新某些內容，Geb 提供 `waitFor` 指令方便此類型的測試需求。

以 Google reCAPTCHA 的 AJAX Demo 為例，按下「Click Me」按鈕後，會利用 AJAX 載入 Captcha （圖片驗證碼）的區塊，我們可以利用 `waitFor` 指令等待 JavaScript 建立此區塊的 DOM 之後，再抓取驗證圖片的來源位址。

```groovy
Browser.drive {
    go 'https://www.google.com/recaptcha/demo/ajax'

    $('input', value: 'Click Me').click()

    waitFor {
        $('#recaptcha_area').size() > 0
    }

    println $('#recaptcha_challenge_image').attr('src')
}
```

在 `waitFor` 的區塊中使用條件判斷式，執行程序會一直等待判斷結果成立才繼續。

* `$("div#result").text() == "Successful"`
* `$("img.thumbnail").size() >= 20`

如果想改變 `waitFor` 等待的時間長度，以及每次測試判斷條件是否成立的時間間隔，可以利用傳入的參數改變預設值。

## 語法
| 範例 | 說明 |
| -- | -- |
| waitFor {} | 預設的等待時間 |
| waitFor(10) {} | 最長等待時間為 10 秒 |
| waitFor(10, 0.5) {} | 最長等待時間為 10 秒，每次判斷條件的間隔時間為 0.5 秒 |
| waitFor("quick") {} | 依照自定的等待時間 |
