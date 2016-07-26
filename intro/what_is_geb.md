# Geb 是什麼？

Geb 是瀏覽器自動化（browser automation）的解決方案。

以強大的 Selenium WebDriver 作為基礎，直接控制瀏覽器進行網站操作，而非模擬 HTTP 存取的方式，即使面對 AJAX 或是複雜的 RIA（Rich Internet Application）也能輕鬆駕馭。

發揮 Groovy 程式語言作為 DSL（Domain-Specific Language）的優勢，建構出一套瀏覽器自動化專屬的語法，並提供類似 jQuery 的操作方法，存取網頁 DOM 節點更方便，撰寫測試程式更簡潔快速。

可以用簡單的 Groovy Script 方式執行，也可以配合 Maven 或 Gradle 建置專案，能夠無縫整合 JUnit、Spock 或 TestNG 等測試框架，並且搭配 Jenkins CI 進行專案開發流程整合。

## Geb 可以做什麼？

使用程式自動化操作瀏覽器，可以解決許多問題，例如滿足網站測試（web application testing）的需求。

**Web Application Testing**

包含：

* 功能測試（Functional Testing）
* 驗收測試（User Acceptance Testing）

Geb 可以搭配各種 Web 開發框架使用，滿足專案測試的完整需求。

**Web Automation Script**

配合系統排成工具（如 crontab）定期執行，自動完成指定的網站操作，例如進入某個網站的後台點選維護功能。

**網路爬蟲（Web crawler）**

亦稱網路蜘蛛（Web spider），Geb 程式可以打開不同網站，例如先從搜尋引擎找到需要的網站，分別打開瀏覽然後將頁面內容自動採集。Geb 程式除了將結果保存到檔案系統，也可以輕易地使用 JDBC 儲存至資料庫。

**快照（Webpage Screenshot）**

讓瀏覽器自動完成某些操作，然後將網站的畫面擷取保存成圖片檔。

## Geb 的優點

Based on the WebDriver

jQuery-like selector and syntax

易學易讀易寫的 Groovy DSL

實現 Page Object Pattern

高度整合測試框架，如 JUnit, Spock, TestNG 等。

開發流程整合，搭配 Grails, Gradle, Maven, Jenkins CI 等。
