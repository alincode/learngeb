# Reporter

關於報表的部分，有三個變數可以設定。
* reporter
* reportsDir
* reportOnTestFailureOnly

## reporter

如果你有其他特殊的需求，也可以自己實作[Reporter](http://www.gebish.org/manual/snapshot/api/geb/report/Reporter.html)這個interface。

```groovy
reporter = new CustomReporter()
```

## reportsDir
自訂報表產出的位置

```groovy
reportsDir = "target/geb-reports"
```

## reportOnTestFailureOnly

Geb預設簡易的報表，每個測試都會產出一個html和一個png截圖檔，圖檔其實也蠻佔空間的，尤其是當你測試案例很多的時候，所以這個變數可以讓你自行決定，是不是只產出測試失敗的資料，忽略測試成功的資料，但目前這個功能官方文件有說，現在只支援TestNG，其他框架像JU
nit、Spock還不支援，我剛剛用Spock試了一下，的確還不支援。

```groovy
reportOnTestFailureOnly = true
```
