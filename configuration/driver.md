## Driver
你要驅動哪個瀏覽器，全看這個設定了。你可以將Firefox視為預設的瀏覽器，因為它是唯一不是要另外裝Driver的Browser。
```
import org.openqa.selenium.firefox.FirefoxDriver

driver = { new FirefoxDriver() }
```
當然你也可以設Chrome，因為執行速度比較快，我個人都用這個。但使用Chrome需要另外裝[ Chrome Driver ](https://code.google.com/p/selenium/wiki/ChromeDriver)。
```
import org.openqa.selenium.chrome.ChromeDriver

driver = { new ChromeDriver() }
```

還有其他的嗎？看看 [WebDriver API](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebDriver.html) 的 All Known Implementing Classes

### 安裝 Chrome Driver
1. 將你電腦的瀏覽器更新到最新版
2. 下載最新版的[檔案](http://chromedriver.storage.googleapis.com/index.html)
3. 解壓縮後，放置在各作業系統指定位置

Mac
```
unzip chromedriver_mac32.zip
cp chromedriver /usr/bin/.
```

Linux 64bit
```
unzip chromedriver_linux64.zip
cp chromedriver /usr/bin/.
```

Linux 32bit
```
unzip chromedriver_linux32.zip
cp chromedriver /usr/bin/.
```

Windowns
* 因為每個版本的路徑不太一樣，我建議你直接搜尋chrome.exe，然後把解壓縮的檔案，放在跟chrome.exe同個資料夾下。

### 設定環境變數
* GebConfig.groovy

```
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.chrome.ChromeDriver

driver = { new FirefoxDriver() }

environments {

    chrome {
        driver = { new ChromeDriver() }
    }

    firefox {
        driver = { new FirefoxDriver()}
    }
}
```
### 切換測試瀏覽器
```
mvn -Dgeb.env=chrome test
// or
mvn -Dgeb.env=firefox test
