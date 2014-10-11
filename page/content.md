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

### Template Options
除了使用預設參數，也可選擇自定參數。像下面這個範例就有這個需求，因為首頁有未登入狀態時，不會有登出連結，反之，在已登入狀態時，也不會有登入連結。如果不使用自定參數，則會拋出[RequiredPageContentNotPresent](http://www.gebish.org/manual/current/api/geb/error/RequiredPageContentNotPresent.html)的exception。
* 範例

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


可自定的參數有
* required
* cache
* to
* wait
* page
