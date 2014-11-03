# 執行 Geb Script

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
