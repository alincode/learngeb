# 測試報告
> Geb提供了簡易的報表功能，每個測試步驟都會產出一個當前 HTML 原始碼和一個網頁畫面截圖png檔

## 設定

* report：設定報告標簽

```
report 'codedata-home'
```

* reportGroup：設定報告群組

```
reportGroup "codedata"
```

* cleanReportGroupDir：清除群組報告資料夾的檔案

```
cleanReportGroupDir()
```

## 實作 Reporter Interface
目前有[PageSourceReporter](http://www.gebish.org/manual/snapshot/api/geb/report/PageSourceReporter.html) 和 [ScreenshotAndPageSourceReporter](http://www.gebish.org/manual/snapshot/api/geb/report/ScreenshotReporter.html) 類別，如果有需要也可自行擴充。第一個是只產html，第二個是產html和png截圖。

## API 和 範例
這些是[ Browser ](http://www.gebish.org/manual/snapshot/api/geb/Browser.html)類別內的method
* void report(String label)
* void reportGroup(String path)
* cleanReportGroupDir()

以下是將 CodeData 首頁截圖的範例

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

對自動化測試的任務，保留各階段測試的畫面，可以彙整成一份更詳盡的測試報告。如果要撰寫一個 Web Screen Capture 的程式，使用 Geb 也可以輕鬆完成。

![Geb Report Sample](./geb-report-sample.png)

