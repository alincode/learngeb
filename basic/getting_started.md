## 第一個自動化測試程式 ##

`Browser.drive` 是開始使用 Geb 的第一步，在 `drive` 的 Closure 區塊內可以使用 Geb 的 DSL 語法，開始進行瀏覽器的各種操作。

`go` 指令用來開啓一個網頁 URL 位址。

```groovy
@Grab('org.gebish:geb-core:0.10.0')
@Grab('org.seleniumhq.selenium:selenium-firefox-driver:2.44.0')
import geb.Browser

Browser.drive {
    go 'http://www.codedata.com.tw/'
}
```

以下是自動化操作 Google 搜尋的完整範例，使用 Geb 的 DSL 語法撰寫相當簡單易懂。

```groovy
@Grab('org.gebish:geb-core:0.10.0')
@Grab('org.seleniumhq.selenium:selenium-firefox-driver:2.44.0')
import geb.Browser

Browser.drive {
    go 'https://www.google.com.tw/'

    waitFor { title.startsWith('Google') }

    $('input', name: 'q').value('CodeData')
    $('input', name: 'btnI').click()

    waitFor { title.endsWith('CodeData') }

    println $('div.article h3 a').text()
}
```

這段程式碼包含幾個常見的 Geb 語法使用範例。

* `.value()` 將資料填入表單。
* `.text()` 取得 Element 包含的文字內容。
* `.click()` 模擬滑鼠點擊 Element 的事件。
* `waitFor` 等待一個條件被滿足。

`waitFor` 是撰寫 Geb Web Test 重要的語法，它如同以往 Unix 自動化操作 Telnet 的 Expect 工具，可以讓我們等待網頁內容改變如預期。如果 `waitFor` 在限定時間內無法等到一個滿意的結果，就會拋出 `WaitTimeoutException`。

