# Content DSL

## 自定參數

除了使用預設參數，也可選擇自定參數。


| 參數 | 預設值 | 說明 |
| -- | -- | -- |
| required | true | 當required為true，但loginLink不存在時，下面的範例會拋出[RequiredPageContentNotPresent](http://www.gebish.org/manual/current/api/geb/error/RequiredPageContentNotPresent.html)，但若 required為false，則不會拋出exception。 |
| cache | false | - |
| to | null | - |
| wait | false | - |
| page | null | - |

### 使用 `required`

**Geb Sample Code**

```groovy
Browser.drive {
    config.baseUrl = 'http://getbootstrap.com/'
    to SigninPage
    println h1Title
}.quit()

class SigninPage extends Page {
    static url = 'examples/signin/'
    static content = {
        h1Title(required: false) {$('h1').text()}
    }
}
```

** 錯誤訊息 **
```
Caught: groovy.lang.MissingPropertyException: No such property: h1Title for class: required
groovy.lang.MissingPropertyException: No such property: h1Title for class: required
        at required$_run_closure1.doCall(required.groovy:11)
        at required$_run_closure1.doCall(required.groovy)
        at geb.Browser.drive(Browser.groovy:884)
        at geb.Browser$drive$0.callStatic(Unknown Source)
        at geb.Browser.drive(Browser.groovy:854)
        at geb.Browser$drive.call(Unknown Source)
        at required.run(required.groovy:8)
```

**Geb Sample Code**

```groovy
Browser.drive {
    config.baseUrl = 'http://getbootstrap.com/'
    to SigninPage
    println h1Title
}.quit()

class SigninPage extends Page {
    static url = 'examples/signin/'
    static content = {
        h1Title(required: false) {$('h1').text()}
    }
}
```

** 錯誤訊息 **
```
null
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
Browser.drive {
    config.baseUrl = 'http://www.jacklmoore.com/'
    to OutsidePage
    outsideWebpageLink.click()
    sleep(5000)

    withFrame(wikiFrame){
        assert h1Ttitle == 'Wikipedia'
    }
}.quit()

class OutsidePage extends Page {
    static url = '/colorbox/example2/'
    static content = {
        outsideWebpageLink {$('.iframe')}
        wikiFrame(page: WikiPage){$('iframe')}
    }
}

class WikiPage extends Page{
    static at = {title == 'Wikipedia'}
    static url = 'http://www.wikipedia.org/'
    static content = {
        h1Ttitle {$('h1 img').attr('title')}
    }
}
```

## 範例

### 範例一

**Geb Sample Code**

```groovy
Browser.drive {
    config.baseUrl = 'http://0.0.0.0:1234/'
    to LoginPage
    emailInput.value('aaa@bbb.com')
}.quit()

class LoginPage extends Page {
    static url = 'login.html'
    static content = {
        // «名稱» { «定義» }
        emailInput { $("input[name=account]") }
    }
}
```

### 範例二

**Geb Sample Code**

```groovy
Browser.drive {
    config.baseUrl = 'http://www.gebish.org/'
    to FrontPage
    assert menuText("crossbrowser") == "Cross Browser"
    assert menuText("content") == "jQuery-like API"
}.quit()

class FrontPage extends Page {
    static content = {
        menuText { sidemenuName -> $('.sidemenu li', class: sidemenuName).find('a').text() }
    }
}
```
