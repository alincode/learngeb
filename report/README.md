# 擷取測試結果

自動化測試的每個步驟完成或失敗發生後，我們可以利用 Geb 的報表功能擷取測試結果，包含網頁畫面截圖與當前 HTML 原始碼。使用 `config.reportsDir` 可以自訂檔案存放的路徑，以下是將 CodeData 首頁截圖的範例。

```
Browser.drive {
    config.reportsDir = new File('/tmp')

    go 'http://www.codedata.com.tw/'

    report 'codedata-home'
}
```

`report` 指令的參數是測試報告的名稱，圖片及原始碼檔案會依此命名，在指定的存放路徑下產生 .png 及 .html 檔案。

* /tmp/codedata-home.html
* /tmp/codedata-home.png

以下是瀏覽器網頁畫面截圖的範例，對自動化測試的任務，保留各階段測試的畫面，可以彙整成一份更詳盡的測試報告。如果要撰寫一個 Web Screen Capture 的程式，使用 Geb 也可以輕鬆完成。

![Geb Report Sample](./geb-report-sample.png)


