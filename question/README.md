# 常見問題

這篇整理一些常見的問題與解決方法，歡迎將您遇到的問題來信：

* lyhcode 小老鼠 gmail.com

## 出現 UnresolvablePropertyException？

```
geb.error.UnresolvablePropertyException: Unable to resolve searchModule as content for Page, or as a property on its Navigator context. Is searchModule a class you forgot to import?
	at geb.content.PageContentSupport.propertyMissing(PageContentSupport.groovy:64)
	at geb.Browser.propertyMissing(Browser.groovy:210)
	at geb.spock.GebSpec.propertyMissing(GebSpec.groovy:58)
```

* 沒有 import 需要用到的其他類別
* 使用錯誤的 element 名稱

## 恐龍時代的網站如何處理？

某些學校或政府機關的網站，相容性還停留在很舊的瀏覽器版本，通常兇手就是 MSIE。遇到這類型的網站，需要先確認 Selenium WebDriver 與作業系統及瀏覽器相容性。

以 IE 為例，對應的 WebDriver 是 `InternetExplorerDriver`。

> The InternetExplorerDriver is a standalone server which implements WebDriver's wire protocol. This driver has been tested with IE 6, 7, 8, 9, and 10 on appropriate combinations of XP, Vista and Windows 7.

從 [InternetExplorerDriver](https://code.google.com/p/selenium/wiki/InternetExplorerDriver) 的說明來看，目前仍是可以相容 IE 6, 7, 8 這些老舊瀏覽器。使用 VirtualBox 等虛擬機器軟體，建立一個 Windows XP 或 Windows 7 的 VM，更新（或不要更新） IE 到需要測試的版本，接下來就停用 Windows Update，讓 IE 的版本鎖定，在 VM 執行 Selenium Server，搭配 RemoteWebDriver 使用，如此一來就能讓 Geb 測試古老架構的網站。
