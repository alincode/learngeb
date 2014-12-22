# 完整範例

以下是 `Page` 物件定義的範例，在`content`區塊中，我們將該網頁內容需要被自動化操作的 DOM 元件，先透過 Navigator  API 找到，就能夠方便在測試程式中使用。

**Geb Sample Code**

```groovy
Browser.drive {
    config.baseUrl = 'http://getbootstrap.com/'
    to SigninPage
    login('aaa@bbb.com', '11111111')

    assert emailInput.value() == ''
}.quit()

class SigninPage extends Page {
    static at = {$('h2').text() == 'Please sign in'}
    static url = 'examples/signin/'
    static content = {
        emailInput {$('#inputEmail')}
        passwordInput {$('#inputPassword')}
        loginButton {$('button')}
    }

    def login(email, password){
        emailInput.value email
        passwordInput.value password
        loginButton.click()
    }
}
```

**Geb Sample Code**

```groovy
import geb.Page

class DashboardPage extends Page {
    static at = { heading.text() == "My Dashboard" }
    static content = {
        heading { $("h1") }
    }
}
```

在操作 `Page` 物件時，可以搭配 `to` 與 `at` 兩個指令使用。

* 使用 `to` 開啟 `Page` 物件所在的 URL 位址。
* 使用 `at` 判斷是否為在該 `Page` 所屬頁面。

`Page` 物件讓 Geb 測試程式呈現更有趣的 DSL 風格。

**Geb Sample Code**

```
import geb.Browser

Browser.drive {
    to LoginPage
    assert at(LoginPage)
    loginForm.with {
        username = "admin"
        password = "password"
    }
    loginButton.click()
    assert at(DashboardPage)
}
```

定義好的 Page 物件可以被重複使用，依照測試案例（test case）的需求，組合成不同的測試執行流程。對於中大型的 Web Application 自動化測試，Geb 的 Page 物件讓測試程式的維護更容易。
