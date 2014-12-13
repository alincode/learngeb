# Content DSL

## 範例

### 範例一

**Geb Sample Code**

```groovy
import geb.*

class LoginPage extends Page {

    static content = {
        // «名稱» { «定義» }
        emailInput { $("input[name=account]") }
    }
}
```

```groovy
Browser.drive {
    to LoginPage
    emailInput.value('aaa@bbb.com')
}
```

### 範例二

**Geb Sample Code**

```groovy
class FrontPage extends Page {

    static content = {
        menu { menuLinkName -> $('div', id: menuLinkName) }
    }
}
```

```groovy
Browser.drive {
    to FrontPage
    assert menu("menu-about").text() == "About Me"
    assert menu("menu-faq").text() == "FAQ"
}
```

## 自定參數

除了使用預設參數，也可選擇自定參數。

<table>
    <tr>
        <th>參數</th><th>預設值</th><th>說明</th>
    </tr>
    <tr>
        <td>required</td>
        <td>true</td>
        <td>當required為true，但loginLink不存在時，下面的範例會拋出[RequiredPageContentNotPresent](http://www.gebish.org/manual/current/api/geb/error/RequiredPageContentNotPresent.html)，但若
required為false，則不會拋出exception。</td>
    </tr>
   <tr>
        <td>required</td>
        <td>true</td>
        <td></td>
    </tr>
    <tr>
        <td>cache</td>
        <td>false</td>
        <td></td>
    </tr>
    <tr>
        <td>to</td>
        <td>null</td>
        <td></td>
    </tr>
    <tr>
        <td>wait</td>
        <td>false</td>
        <td></td>
    </tr>
    <tr>
        <td>page</td>
        <td>null</td>
        <td></td>
    </tr>
</table>

### 使用 `required`

**Geb Sample Code**

```groovy
Browser.drive {
    to ExamplePage
    loginLink.click()
}
```

```groovy
import geb.*

class FrontPage extends Page {

    static content = {
        // «名稱»(«參數») { «定義» }
        loginLink(required: false) { $('.login') }
        logoutLink(required: false) { $('.logout') }
    }
}
```

### 使用 `cache`

**Geb Sample Code**

```groovy
import geb.spock.GebReportingSpec
import pages.FrontPage
import spock.lang.Stepwise

@Stepwise
class CacheSpec extends GebReportingSpec{

    def "test cache true"(){
        when:
        to FrontPage
        assert theValue1 == 1
        value1 = 2
        assert theValue1 == 1

        then:
        true
    }

    def "test cache false"(){
        when:
        assert theValue2 == 1
        value2 = 2
        assert theValue2 == 2

        then:
        true
    }

}

```

```groovy
import geb.Page

class FrontPage extends Page {

    def value1 = 1
    def value2 = 1

    static content = {
        theValue1(cache: true) { value1 }
        theValue2(cache: false) { value2 }
    }
}
```

### 使用 `page`

常運用於彈跳視窗

**Geb Sample Code**

```groovy
package pages

import geb.Page

class FrontPage extends Page{

    static at = {$('h4').text() == "home"}

    static url = '/'

    static content = {
        alertButton {$('#alert')}
        alertFrame(page: AlertPage){$('iframe')}
    }
}

```

```groovy
to FrontPage
alertButton.click()

withFrame(alertFrame){
    // doSomething
}

```
