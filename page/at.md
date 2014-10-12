## 靜態屬性 at

> 這個屬性存在的目的，就是定義Page的檢查條件，確認是否為期望的那一頁。

###常見的判斷

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
