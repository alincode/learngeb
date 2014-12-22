# 靜態屬性 at

這個屬性存在的目的，就是定義Page的檢查條件，確認是否執行狀態位於預期的頁面。

## 常見語法

### title

比對網頁標題

**Geb Sample Code**

```groovy
class GebHomePage extends Page {

    static at = {$('#main h1').first().text() == 'What is it?' }

    static url = "http://www.gebish.org"

    static content = {
        item1 { $('#sidebar li a').first() }
    }
}
```

`title` 是哪裡來的？[`Page Object API`](http://www.gebish.org/manual/current/api/geb/Page.html)裡面有一個 `getTitle()` ，Groovy 語法上比 Java 精簡，直接寫 `title` 就可以了。

### 元素

比對元素資料

**Geb Sample Code**

```groovy
class GebHomePage2 extends Page {
    static at = { $('h1').last().text() == "Build Status" }
    static url = "http://www.gebish.org"
}
```

### WaitFor

某些頁面載入的時間會特別久，你就必須在這裡特別設定它。

**Geb Sample Code**

```groovy
class GebHomePage3 extends Page {
    static at = { waitFor { title.endsWith("Groovy Browser Automation") } }
    static url = "http://www.gebish.org"
}
}
```

## 如何呼叫

雖然在Page中定義了at靜態屬性，但不代表有被定義的url，會自動檢查at屬性條件，需要另外呼叫才行。

**Geb Sample Code**

```groovy
class CrossBrowserPage extends Page {

    // // 故意定義錯的檢查條件
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

### 範例二

主動呼叫at檢查

**Geb Sample Code**

```groovy
Browser.drive {
    to GebHomePage
    item1.click()
    at CrossBrowserPage
}.quit()

class GebHomePage extends Page {

    static at = {$('#main h1').first().text() == 'What is it?' }

    static url = "http://www.gebish.org"

    static content = {
        item1 { $('#sidebar li a').first() }
    }
}

class CrossBrowserPage extends Page {

    // // 故意定義錯的檢查條件
    static at = {$('#main h1').first().text() == 'Cross Browser Automation2'}

    static url = "http://www.gebish.org/crossbrowser"

    static content = {
        topic3 { $('#main h1').last().text() }
    }
}
```

** 錯誤訊息 **
```
Caught: Assertion failed:

$('#main h1').first().text() == 'Cross Browser Automation2'
|             |       |      |
|             [<h1>]  |      false
[<h1>, <h1>, <h1>]    Cross Browser Automation

Assertion failed:

$('#main h1').first().text() == 'Cross Browser Automation2'
|             |       |      |
|             [<h1>]  |      false
[<h1>, <h1>, <h1>]    Cross Browser Automation

        at CrossBrowserPage$__clinit__closure1.doCall(at2.groovy:28)
        at CrossBrowserPage$__clinit__closure1.doCall(at2.groovy)
        at geb.Page.verifyThisPageAtOnly(Page.groovy:187)
        at geb.Page.verifyAt(Page.groovy:146)
        at geb.Page$verifyAt$3.call(Unknown Source)
        at geb.Page$verifyAt$3.call(Unknown Source)
        at geb.Browser.doAt(Browser.groovy:367)
        at geb.Browser.this$2$doAt(Browser.groovy)
        at geb.Browser$this$2$doAt$10.callCurrent(Unknown Source)
        at geb.Browser$this$2$doAt$10.callCurrent(Unknown Source)
        at geb.Browser.at(Browser.groovy:298)
        at at2$_run_closure1.doCall(at2.groovy:11)
        at at2$_run_closure1.doCall(at2.groovy)
        at geb.Browser.drive(Browser.groovy:884)
        at geb.Browser$drive$0.callStatic(Unknown Source)
        at geb.Browser.drive(Browser.groovy:854)
        at geb.Browser$drive.call(Unknown Source)
        at at2.run(at2.groovy:8)
```

### 範例三

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

    // // 故意定義錯的檢查條件
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

### 範例四

**Geb Sample Code**

```groovy
Browser.drive {
    to CrossBrowserPage
}.quit()

class CrossBrowserPage extends Page {

    // // 故意定義錯的檢查條件
    static at = {$('#main h1').first().text() == 'Cross Browser Automation2'}

    static url = "http://www.gebish.org/crossbrowser"

    static content = {
        topic3 { $('#main h1').last().text() }
    }
}
```

**錯誤訊息**

```
Caught: Assertion failed:

$('#main h1').first().text() == 'Cross Browser Automation2'
|             |       |      |
|             [<h1>]  |      false
[<h1>, <h1>, <h1>]    Cross Browser Automation

Assertion failed:

$('#main h1').first().text() == 'Cross Browser Automation2'
|             |       |      |
|             [<h1>]  |      false
[<h1>, <h1>, <h1>]    Cross Browser Automation

        at CrossBrowserPage$__clinit__closure1.doCall(at4.groovy:15)
        at CrossBrowserPage$__clinit__closure1.doCall(at4.groovy)
        at geb.Page.verifyThisPageAtOnly(Page.groovy:187)
        at geb.Page.verifyAt(Page.groovy:146)
        at geb.Page$verifyAt$3.call(Unknown Source)
        at geb.Browser.doAt(Browser.groovy:367)
        at geb.Browser.this$2$doAt(Browser.groovy)
        at geb.Browser$this$2$doAt$10.callCurrent(Unknown Source)
        at geb.Browser.at(Browser.groovy:298)
        at geb.Browser.to(Browser.groovy:450)
        at geb.Browser$to$1.callCurrent(Unknown Source)
        at geb.Browser.to(Browser.groovy:426)
        at at4$_run_closure1.doCall(at4.groovy:9)
        at at4$_run_closure1.doCall(at4.groovy)
        at geb.Browser.drive(Browser.groovy:884)
        at geb.Browser$drive$0.callStatic(Unknown Source)
        at geb.Browser.drive(Browser.groovy:854)
        at geb.Browser$drive.call(Unknown Source)
        at at4.run(at4.groovy:8)
```
