
# Driver

你要驅動哪個瀏覽器全看這個設定了，最簡單的方式就是將 Firefox 視為預設的瀏覽器，因為它是唯一不是要另外裝 Driver 的 Browser。

**Geb Sample Code**

```groovy
import org.openqa.selenium.firefox.FirefoxDriver

driver = { new FirefoxDriver() }
```

當然你也可以設 Chrome，因為執行速度比較快，我個人都用這個。但使用 Chrome 需要另外裝[ Chrome Driver ](https://code.google.com/p/selenium/wiki/ChromeDriver)。

**Geb Sample Code**

```groovy
import org.openqa.selenium.chrome.ChromeDriver

driver = { new ChromeDriver() }
```

還有其他的嗎？看看 [WebDriver API](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebDriver.html) 的 All Known Implementing Classes

## 安裝 Chrome Driver
1. 將你電腦的瀏覽器更新到最新版
2. 下載最新版的[檔案](http://chromedriver.storage.googleapis.com/index.html)
3. 解壓縮後，放置在各作業系統指定位置

### 在 Mac 安裝 裝 Chrome Driver

```bash
unzip chromedriver_mac32.zip
cp chromedriver /usr/bin/.
```

### 在Linux 64bit 安裝 裝 Chrome Driver

```bash
unzip chromedriver_linux64.zip
cp chromedriver /usr/bin/.
```

### 在Linux 32bit 安裝 裝 Chrome Driver

```bash
unzip chromedriver_linux32.zip
cp chromedriver /usr/bin/.
```

### Windowns 32bit 安裝 裝 Chrome Driver

因為每個版本的路徑不太一樣，我建議你直接搜尋 chrome.exe，然後把解壓縮的檔案，放在跟 chrome.exe 同個資料夾下。

## 設定環境變數


**Geb Sample Code ( GebConfig.groovy )**

```groovy
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
## 切換測試瀏覽器

```bash
mvn -Dgeb.env=chrome test
// or
mvn -Dgeb.env=firefox test
```
