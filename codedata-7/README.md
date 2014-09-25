# Groovy Tutorial（7）使用 Geb 開發 Web Test 網站自動化測試（上） #





## 開始使用 Geb ##

儘管 Sellenium 提供的 WebDriver API 已經相當容易使用，但是在撰寫 Web Application 的自動化測試案例時，我們仍希望有更輕鬆的方式可以完成任務；過於冗長難以維護修改的測試程式碼，很容易造成自動化測試的理想在日後難以維繫。

Geb 基於 WebDriver 的良好基礎，所以 WebDriver 支援的瀏覽器，在 Geb 也同樣能操作。利用 Groovy DSL 易讀易寫的優點，Geb 提供更友善的 Web Test Script 開發環境。在 Geb 程式中，可以直接與其他 Java（Groovy）程式碼互相搭配，例如透過 JDBC 從資料庫取得資料，或是將測試結果利用 JavaMail 傳送電子郵件報告等。

在 Groovy Script 中使用 Geb 相當容易，只要利用 Grapes/Grab 設定相依的 geb-core 及相關套件。目前 Geb 最新穩定發行版本為 0.9.2，可以透過 Maven Central Repository 下載取得，因此直接加上以下的 `@Grapes` 設定與 `import` 語法，就能開始在 Groovy Script 程式中使用 Geb。

```
@Grapes([
    @Grab('org.gebish:geb-core:0.9.2'),
    @Grab('org.seleniumhq.selenium:selenium-firefox-driver:2.42.0'),
    @Grab('org.seleniumhq.selenium:selenium-support:2.42.0')
])
import geb.Browser
```

為了確保 Geb 能夠正常運作，除了盡可能使用最新 Stable 版本的 Geb 之外，也建議使用最新版本的 Firefox 瀏覽器，並搭配最新的 Selenium WebDriver 套件。在 [MVNRepository](http://mvnrepository.com/) 網站可以搜尋 `selenium-firefox-driver` 關鍵字，以獲取最新的版本編號。如果遇到 Geb 無法正常驅動瀏覽器操作的問題，通常都是版本設定在某些搭配下所產生的異常。

如果搭配 Gradle 建置 Groovy/Geb 專案，則必須在 build.gradle 加入以下 dependencies 設定。

```
compile "org.gebish:geb-core:0.9.2"
compile "org.seleniumhq.selenium:selenium-firefox-driver:2.26.0"
compile "org.seleniumhq.selenium:selenium-support:2.26.0"
```

## Geb Navigator API ##

在撰寫瀏覽器自動化程式時，需要大量的 DOM 操作，正確找到一個 Element 才能對它做進一步的存取。在 Front-end 開發框架中，jQuery 已經提供一個多數 Web 開發者都已經熟悉的 Selector 機制，我們可以透過 Id 或 Class name 輕鬆找到一個目標的 Element。

以下是一些 jQuery 常見的 Selector 程式碼使用範例。

```
// jQuery Selector
$('div.errorMessage');
$('input#firstName');
$('button.confirm');
```

Geb 提供類似 jQuery Selector 的 Navigator API，讓開發者輕鬆找到一個 DOM 的 Element 物件。

尋找一個 `<div>` 及 `<div class="main">` 的語法，`$` 函數將會傳回一個 `Navigator` 物件。

```
// Geb Navigator
$("div")
$("div.main")
```

也可以利用 ID 尋找 Element。

```
$("#iframe1")
```

如果找到條件相符的 Element 有多項，則可以在第二個參數指定 0 取出第一個 Element；使用 `.first()` 也同樣可以傳回第一個項目。

```
$("div", 0)
$("div.main", 0)
$("div").first()
```

如果要找到 `<div title="section">` 的 Element，也可以這樣做。

```
$("div", title: "section")
$("div", 0, title: "section")
```

Navigator 找到的 Element 也可以進一步找到其上層，或繼續往它包含的子 Element 做搜尋。

```
$("table.books", 0).parent()
$("div.contents").find("table", cellspacing: '0')
```

## Getting Started with Geb ##

`Browser.drive` 是開始使用 Geb 的第一步，在 `drive` 的 Closure 區塊內可以使用 Geb 的 DSL 語法，開始進行瀏覽器的各種操作。

`go` 指令用來開啓一個網頁 URL 位址。

```
import geb.Browser

Browser.drive {
    go "http://www.codedata.com.tw/"
}
```

以下是自動化操作 Google 搜尋的完整範例，使用 Geb 的 DSL 語法撰寫相當簡單易懂。

```
@Grapes([
    @Grab('org.gebish:geb-core:0.9.2'),
    @Grab('org.seleniumhq.selenium:selenium-firefox-driver:2.42.0'),
    @Grab('org.seleniumhq.selenium:selenium-support:2.42.0')
])
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

