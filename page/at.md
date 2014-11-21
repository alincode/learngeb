# 靜態屬性 at

這個屬性存在的目的，就是定義Page的檢查條件，確認是否執行狀態位於預期的頁面。

## 常見語法

### title

比對網頁標題

**Geb Sample Code**

```groovy
class GebHomePage extends Page {

    static url = "http://www.gebish.org/"
    static at = { title == "Geb - Very Groovy Browser Automation" }
     static content = {
        item1 { $('#sidebar li a').first() }
    }
}
```

`title` 是哪裡來的？[`Page Object API`](http://www.gebish.org/manual/current/api/geb/Page.html)裡面有一個 `getTitle()` ，那為什麼是用 `title`，而不是 `getTitle()` ，您可以去了解一下 [`JavaBean`](http://zh.wikipedia.org/wiki/JavaBeans) 的由來 ，Groovy 語法上比 Java 精簡，可以直接寫 `title` ，就可以了。

### 元素

比對元素資料

**Geb Sample Code**

```groovy
import geb.*

class GebHomePage extends Page {
    static url = "http://www.gebish.org/"
    static at = { $('h1').last().text() == "Build Status" }
}
```

### WaitFor

某些頁面載入的時間會特別久，你就必須在這裡特別設定它。

**Geb Sample Code**

```groovy
import geb.*

class GebHomePage extends Page {
    static url = "http://www.gebish.org/"
    static at = { waitFor { title.endsWith("Groovy Browser Automation") } }
}
```

## 如何呼叫

雖然在Page中定義了at靜態屬性，但不代表有被定義的url，會自動檢查at屬性條件，需要另外呼叫才行。

**Geb Sample Code**

```groovy
class CrossBrowserPage extends Page {
    // 故意定義錯的檢查條件
    static at = {$('#main h1').first().text() == 'Cross Browser Automation2'}

    static url = "http://www.gebish.org/crossbrowser"

    static content = {
        topic3 { $('#main h1').last().text() }
    }
}
```
## 範例

### 範例一

並沒有檢查出錯誤

**Geb Sample Code**

```groovy
import geb.Browser

Browser.drive {
    to GebHomePage
    item1.click()
    assert $('#main h1').first().text() == 'Cross Browser Automation'
}
```

### 範例二

主動呼叫at檢查

**Geb Sample Code**

```groovy
import geb.Browser

Browser.drive {
    to GebHomePage
    item1.click()
    at CrossBrowserPage
}
```

** 錯誤訊息 **
```
Assertion failed:

$('#main h1').first().text() == 'Cross Browser Automation2'
|             |       |      |
|             |       |      false
|             |       Cross Browser Automation
```

### 範例三

**Geb Sample Code**

```groovy
import geb.Browser

Browser.drive {
    to GebHomePage
    item1.click(CrossBrowserPage)
}
```

**錯誤訊息**

```groovy
geb.error.UnexpectedPageException: Page verification failed for page pages.CrossBrowserPage after clicking an element
	at geb.navigator.NonEmptyNavigator.click(NonEmptyNavigator.groovy:415)
	at example.AtSpec.at example 3(AtSpec.groovy:27)
Caused by: Assertion failed:

$('#main h1').first().text() == 'Cross Browser Automation2'
|             |       |      |
|             |       |      false
|             |       Cross Browser Automation
```

### 範例四

**Geb Sample Code**

```groovy
import geb.Browser

Browser.drive {
    to CrossBrowserPage
}
```

**錯誤訊息**

```
Assertion failed:

$('#main h1').first().text() == 'Cross Browser Automation2'
|             |       |      |
|             |       |      false
|             |       Cross Browser Automation
```
