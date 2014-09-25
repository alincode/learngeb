# Groovy Tutorial（7）使用 Geb 開發 Web Test 網站自動化測試（上） #

Geb 是瀏覽器自動化（browser automation）的解決方案 ，它是 Groovy 用於 Web Test 自動化測試的 DSL（Domain-Specific Language）。Geb 的內部是 Selenium WebDriver 瀏覽器自動化測試引擎，提供類似 jQuery Selector 的 DOM 操作方法，可以與多種 Java 測試框架整合搭配。使用易讀易寫的 Groovy DSL 語法撰寫 Script 可以簡化工作，Geb 是不能錯過的 Web Test 自動化測試框架。

## 關於 Web Test ##

現代 Web Application 專案愈來愈複雜，只做基本的 Unit Test 並不夠驗證軟體品質。在一個導入持續整合（Continuous Integration）開發流程的專案中，我們期望專案除了通過程式碼的單元測試，實際部署到伺服器的測試站台，也要能夠以使用者的角度進行更完整的測試。

使用瀏覽器實際進行網站功能的操作，這是最接近 End User 行為的方式，即使有很高的 Unit Test 覆蓋率，最終仍不免要實際以瀏覽器操作各項功能，才能確保呈現給使用者的網站能夠正常使用。

由於瀏覽器版本相當多，在不同的作業系統上也可能存在些許差異，因此傳統的 Web QA Test 總是相當耗費人力，並且不斷重複耗時且枯燥乏味的工作。如何讓測試的工作自動化，是許多開發者努力的方向。

撰寫程式自動化測試網站各項功能，並無法取代所有測試工作的人工作業，因為許多人腦可以很容易處理的資訊，並無法輕易用程式取代。自動化測試無法完全取代網站測試所需的人工作業，但我們仍期望 80/20 法則能實踐，至少八成枯燥乏味的重複作業，能夠撰寫自動化測試程式取代，讓測試人員專注完成更重要的工作。

## Sellenium WebDriver 簡介 ##

[Sellenium](http://docs.seleniumhq.org/) 是為瀏覽器自動化（Browser Automation）需求所設計的一組工具集，它讓我們直接用真實的瀏覽器來自動操作一個網站，將 Sellenium 應用在自動化測試時，除了檢驗網頁內容、填寫表單等基本操作，也能驗證 JavaScript 的功能是否正常執行等，因為 Sellenium 操作的網站畫面就是瀏覽器呈現給使用者的最終結果。

如果是第一次接觸 Sellenium 的讀者，可以從 Sellenium IDE 這個工具開始認識。Sellenium IDE 需要搭配 Firefox 瀏覽器使用，在安裝 [Sellenium IDE Plugins](http://docs.seleniumhq.org/projects/ide/) 之後，我們就能在 Firefox 瀏覽器打開 Sellenium IDE 視窗，進行網站操作的「錄製」，然後再「重播」一次所有被錄下來的動作。這就是 Sellenium 進行網站自動化測試的基礎，我們需要先建立一組測試案例（Test Case），定義那些需要被執行的步驟，例如填寫表單、按下送出按鈕等，然後再重複執行這些動作，檢查其結果是否符合預期。

![Sellenium IDE](images/sellenium-ide-google-search.png)

許多 Web Test Framework，都是以 Sellenium API 作為基礎，功能強大且穩固已經讓 Sellenium 成為瀏覽器自動化的基石。Sellenium 2.0 帶來 WebDriver 的實作，跨越不同瀏覽器的自動化操作，有更清楚定義的標準可循，目前 [WebDriver API](http://www.w3.org/TR/webdriver/) 規範已提交 W3C，若能夠被標準化且在各大瀏覽器實作，執行跨瀏覽器的自動化測試工作將會被簡化許多。

在 Sellenium 及開放源碼社群的努力下，已有許多 WebDriver 可供使用，包含目前佔有率最高的 Google Chrome、Firefox 與 InternetExplorer，已能滿足大多數網站自動化測試的需求。

Sellenium WebDriver API 支援 Java、C#、Ruby、Python 及 Perl 等多種語言，以下是 Java 語言的範例程式碼，示範以 Firefox 瀏覽器打開 Google 網站、搜尋關鍵字「geb」的自動化操作。

```
WebDriver driver = new FirefoxDriver();
driver.get("http://www.google.com");
WebElement element = driver.findElement(By.name("q"));
element.sendKeys("geb");
element.submit();
System.out.println("Title: " + driver.getTitle());
driver.quit();
```

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

