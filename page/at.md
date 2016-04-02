# 靜態屬性 at

這個屬性定義 Page 是否真的為預期的頁面的檢查條件

## 常見用法

### 比對網頁標題

**Geb Sample Code**

```groovy
class GebHomePage extends Page {
    static at = { title == 'Geb - Very Groovy Browser Automation'}
    static url = "http://www.gebish.org"
}
```

`title` 是哪裡來的？[`Page Object API`](http://www.gebish.org/manual/current/api/geb/Page.html) 裡面有一個 `getTitle()`，所以在 Groovy 語法上可以直接寫 `title`。

除此之外，如果某些頁面載入的時間會特別久，這裡也可以與 WaitFor 搭配使用。

**Geb Sample Code**

```groovy
class GebHomePage3 extends Page {
    static at = { waitFor { title.endsWith("Groovy Browser Automation") } }
    static url = "http://www.gebish.org"
}
```

### 比對元素資料

**Geb Sample Code**

```groovy
class GebHomePage2 extends Page {
    static at = { $('h1').last().text() == "Build Status" }
    static url = "http://www.gebish.org"
}
```

## 範例

### 正常測試

**Geb Sample Code**

```groovy
Browser.drive {
    to GebHomePage
    item1.click()
    assert $('#main h1').first().text() == 'Cross Browser Automation'
}.quit()

class GebHomePage extends Page {

    static at = {$('#main h1').first().text() == 'What is it?' }

    static url = "http://www.gebish.org"

    static content = {
        item1 { $('#sidebar li a').first() }
    }
}
```

### 發生錯誤的測試

**Geb Sample Code**

```groovy
Browser.drive {
    to GebHomePage
    item1.click(CrossBrowserPage)
}.quit()

class GebHomePage extends Page {

    static at = {$('#main h1').first().text() == 'What is it?' }

    static url = "http://www.gebish.org"

    static content = {
        item1 { $('#sidebar li a').first() }
    }
}

class CrossBrowserPage extends Page {

    // 故意定義錯的檢查條件
    static at = {$('#main h1').first().text() == 'Cross Browser Automation2'}

    static url = "http://www.gebish.org/crossbrowser"

    static content = {
        topic3 { $('#main h1').last().text() }
    }
}
```

**錯誤訊息**

```groovy
Caught: geb.error.UnexpectedPageException: Page verification failed for page CrossBrowserPage after clicking an element
geb.error.UnexpectedPageException: Page verification failed for page CrossBrowserPage after clicking an element
        at geb.navigator.NonEmptyNavigator.click(NonEmptyNavigator.groovy:477)
        at geb.navigator.NonEmptyNavigator.click(NonEmptyNavigator.groovy)
        at geb.content.TemplateDerivedPageContent.click(TemplateDerivedPageContent.groovy:27)
        at at3$_run_closure1.doCall(at3.groovy:10)
        at at3$_run_closure1.doCall(at3.groovy)
        at geb.Browser.drive(Browser.groovy:884)
        at geb.Browser$drive$0.callStatic(Unknown Source)
        at geb.Browser.drive(Browser.groovy:854)
        at geb.Browser$drive.call(Unknown Source)
        at at3.run(at3.groovy:8)
Caused by: Assertion failed:

$('#main h1').first().text() == 'Cross Browser Automation2'
|             |       |      |
|             [<h1>]  |      false
[<h1>, <h1>, <h1>]    Cross Browser Automation
```
