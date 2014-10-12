## Content DSL
* 範例一

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
* 範例二

```groovy
import geb.*

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

#### 可自定的參數有
* required
* cache
* to
* wait
* page


#### 範例

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

