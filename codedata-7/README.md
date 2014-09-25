# Groovy Tutorial（7）使用 Geb 開發 Web Test 網站自動化測試（上） #


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

