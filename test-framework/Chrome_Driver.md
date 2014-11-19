# Chrome Driver

使用 Geb 搭配其他瀏覽器時，需要先配置所需的執行環境，以 Google Chrome 瀏覽器為例，必須先下載安裝 ChromeDriver 並配置 `webdriver.chrome.driver` 系統環境變數。

先下載最新版本的 [ChromeDriver](http://chromedriver.storage.googleapis.com/index.html) 檔案，目前提供的作業系統支援包含 Linux（32 及 64 位元）、Mac(32 位元) 及 Windows（32 位元）。

* chromedriver_linux32.zip
* chromedriver_linux64.zip
* chromedriver_mac32.zip
* chromedriver_win32.zip

將下載的 `chromedriver*.zip` 檔案解壓縮到特定的資料夾，並配置 `webdriver.chrome.driver` 系統環境變數，其設定值為 chromedriver（或 chromedriver.exe）的完整路徑。若不想變更作業系統設定，在 Geb 程式中也可以使用 `System.setProperty(...)` 修改設定。

以下是 Geb 搭配 Chrome 瀏覽器的程式範例。

```groovy
@Grapes([
    @Grab('org.gebish:geb-core:0.9.3'),
    @Grab('org.seleniumhq.selenium:selenium-chrome-driver:2.42.0'),
    @Grab('org.seleniumhq.selenium:selenium-support:2.42.0')
])
import geb.Browser
import org.openqa.selenium.chrome.ChromeDriver

System.setProperty('webdriver.chrome.driver', '/tmp/chromedriver')

driver = {
   new ChromeDriver()
}

Browser.drive {
    go 'http://www.codedata.com.tw/'
    println title
}
```
