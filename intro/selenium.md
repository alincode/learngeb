## Selenium WebDriver

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
