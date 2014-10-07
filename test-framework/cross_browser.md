## 跨瀏覽器測試支援 ##

測試網站在不同瀏覽器執行的相容度，也是許多軟體專案測試的常見需求，Geb 使用 Selenium WebDriver 為基礎，所以 Selenium 支援的瀏覽器類型，就可以在 Geb 程式中使用。使用 MVNRepository.com 查詢 [org.seleniumhq.selenium](http://mvnrepository.com/artifact/org.seleniumhq.selenium) 群組，可以看到 Selenium 已經提供的 WebDriver 套件，以下是目前已經發佈的 WebDriver 專案名稱：

* selenium-chrome-driver
* selenium-firefox-driver
* selenium-ie-driver
* selenium-safari-driver
* selenium-htmlunit-driver

Selenium 早期就是搭配 Firefox 瀏覽器發展，所以在 Geb 預設也是使用 FirefoxDriver 的設定，只要電腦中已安裝 Firefox 瀏覽器，並不需要加掛其它軟體。如果沒有特別指定瀏覽器的需求，使用 FirefoxChrome 是最簡單的方式，不必多做額外的設定。

在 Geb 程式中自訂 WebDriver 很容易，只要設定「`driver`」即可指定瀏覽器類型。

* `driver = 'firefox'`
* `driver = new FirefoxDriver()`

以上兩種設定方式都可以被 Geb 接受，但是使用「`new FirefoxDriver()`」必須先引入（import）指定的類別，否則會顯示無法找到類別（ClassNotFoundException）。
