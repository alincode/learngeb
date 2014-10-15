## 靜態屬性 at

> 這個屬性存在的目的，就是定義Page的檢查條件，確認是否為期望的那一個頁。

### 常見的判斷

* title

```
class GebHomePage extends Page {

    static url = "http://www.gebish.org/"
    static at = { title == "Geb - Very Groovy Browser Automation" }
}
```

title是哪裡來的？[Page Object API](http://www.gebish.org/manual/current/api/geb/Page.html)裡面有一個getTitle()，那為什麼是用title，而不是getTitle()，您可以去了解一下[JavaBean的由來](http://zh.wikipedia.org/wiki/JavaBeans)，Groovy語法上比Java精簡，可以直接寫title，就可以了。

* 元素

```
import geb.*

class GebHomePage extends Page {
    static url = "http://www.gebish.org/"
    static at = { $('h1').last().text() == "Build Status" }
}
```
* WaitFor

```
import geb.*

class GebHomePage extends Page {
    static url = "http://www.gebish.org/"
    static at = { waitFor { title.endsWith("Groovy Browser Automation") } }
}
```
### 如何呼叫
剛剛只有在Page中定義了at靜態屬性，但不代表你在執行to GebHomePage2時，就會一併檢查at，需要另外呼叫才行。
```
import geb.Browser

Browser.drive {
    to GebHomePage2
    at GebHomePage2
}
```

但另一種情況，它就會預設執行at檢查。
```
$('a.home2').click(GebHomePage2)
```
### 拋出異常
當條件不符合，at內所以定義時，就會跳出類似於下方的失敗訊息。

```
import geb.*

class GebHomePage2 extends Page {
    static url = "http://www.gebish.org/"
    static at = { $('h1').size() == 6 }
}
```

```
Caught: Assertion failed:

$('h1').size() == 6
|       |      |
|       5      false
[[[[[ChromeDriver: chrome on MAC] -> tag name: html]] -> css selector: h1]

    at GebHomePage2$__clinit__closure1.doCall(GebHomePage2.groovy:5)
	at GebHomePage2$__clinit__closure1.doCall(GebHomePage2.groovy)
	at geb.Page.verifyThisPageAtOnly(Page.groovy:165)
	at geb.Page.verifyAt(Page.groovy:133)
	at geb.Page$verifyAt$3.call(Unknown Source)
	at geb.Browser.doAt(Browser.groovy:358)
	at geb.Browser.this$2$doAt(Browser.groovy)
	at geb.Browser$this$2$doAt$10.callCurrent(Unknown Source)
	at geb.Browser.at(Browser.groovy:289)
	at geb.Browser.to(Browser.groovy:436)
	at geb.Browser$to$1.callCurrent(Unknown Source)
	at geb.Browser.to(Browser.groovy:412)
	at geb01$_run_closure1.doCall(geb01.groovy:9)
	at geb01$_run_closure1.doCall(geb01.groovy)
	at geb.Browser.drive(Browser.groovy:860)
	at geb.Browser$drive$0.callStatic(Unknown Source)
	at geb.Browser.drive(Browser.groovy:830)
	at geb.Browser$drive.call(Unknown Source)
	at geb01.run(geb01.groovy:8)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:134)
```

