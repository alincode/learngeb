## Content DSL
* 範例一

```
import geb.*

class LoginPage extends Page {

    static content = {
        // «名稱» { «定義» }
        emailInput { $("input[name=account]") }
    }
}
```

```
Browser.drive {
    to LoginPage
    emailInput.value('aaa@bbb.com')
}
```
* 範例二

```
import geb.*

class FrontPage extends Page {

    static content = {
        menu { menuLinkName -> $('div', id: menuLinkName) }
    }
}
```

```
Browser.drive {
    to FrontPage
    assert menu("menu-about").text() == "About Me"
    assert menu("menu-faq").text() == "FAQ"
}
```

除了使用預設參數，也可選擇自定參數。

### required
* required預設true

當required為true，但loginLink不存在時，下面的範例會拋出[RequiredPageContentNotPresent](http://www.gebish.org/manual/current/api/geb/error/RequiredPageContentNotPresent.html)，但若
required為false，則不會拋出exception。

```
Browser.drive {
    to ExamplePage
    loginLink.click()
}
```
```
import geb.*

class FrontPage extends Page {

    static content = {
        // «名稱»(«參數») { «定義» }
        loginLink(required: false) { $('.login') }
        logoutLink(required: false) { $('.logout') }
    }
}
```
### cache
* cache預設false

```
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

```
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

### to
* to預設null

### wait
* wait預設false

### page
* page預設null
