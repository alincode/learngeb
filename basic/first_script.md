## 撰寫 Geb 程式碼 ##

上一篇已初步介紹 Geb 工具的，對於這套瀏覽器自動化解決方案有興趣的讀者，請跟著本篇提供的範例，開始動手實作自己的程式。以強大的 `Selenium WebDriver` 為核心，使用類似 jQuery 的 DOM 選取與操作方式，及 `Groovy DSL` 易讀易寫的語法開發，使得 Geb 成為受矚目的新一代瀏覽器自動化測試工具。

Geb 測試程式本身就是 Groovy 原始碼，使用 Groovy Console 或使用個人喜愛的文字編輯器（如 Sublime Text 或 Vim），即可輕鬆撰寫、執行 Geb 程式，完全不需要依賴特定的編輯工具。

以下是一個最簡易的 Geb 程式碼範例，包含所需的 Grapes 相依套件宣告。在裝有 Groovy 與 Firefox 瀏覽器的電腦上即可直接執行。

範例：`03/first-script.groovy`

```
@Grapes([
    @Grab('org.gebish:geb-core:0.12.2'),
    @Grab('org.seleniumhq.selenium:selenium-firefox-driver:2.46.0'),
    @Grab('org.seleniumhq.selenium:selenium-support:2.46.0')
])
import geb.Browser

Browser.drive {
    go 'http://www.codedata.com.tw/'
}
```

執行方式：終端機執行指令「`groovy first-script.groovy`」。
