# HtmlUnit Driver

首先要介紹的是 WebDriver 是 HtmlUnitDriver，如果在沒有 GUI 環境的 Server 上執行 Geb 測試程式，就無法使用一般網頁瀏覽器，HtmlUnit 是 Java 平台用於滿足瀏覽器測試需求的函式庫，它可以在沒有 GUI 環境的 console 中執行，支援許多瀏覽器行為操作的模擬，包含一些使用 JavaScript 的 AJAX 互動網頁，但不支援截圖。

若要將 WebDriver 切換為 HtmlUnitDriver，需要先使用 `@Grab` 配置相依的套件再作 `driver` 的設定。

```
@Grapes([
    @Grab('org.gebish:geb-core:0.9.3'),
    @Grab('org.seleniumhq.selenium:selenium-htmlunit-driver:2.42.0'),
    @Grab('org.seleniumhq.selenium:selenium-support:2.42.0')
])
import geb.Browser

driver = 'htmlunit'

Browser.drive {
    go 'http://www.codedata.com.tw/'
    println title
}
```

另一種設定 `driver` 的方式。

```
import org.openqa.selenium.htmlunit.HtmlUnitDriver
driver = new HtmlUnitDriver()
```
