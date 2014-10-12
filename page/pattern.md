## Page物件模式

Page有三個靜態屬性

* url：網址
* at：頁面的檢查條件
* content：Page包含的元素

除了這三個區塊外，你還可以額外自定物件的方法。

```
import geb.*

class LoginPage extends Page {
    static url = "/login"
    static at = { title == "Login Page" }
    static content = {
        emailInput { $("input[name=account]") }
        passwordInput { $("input[name=password]") }
        loginButton { $("input[value='Login']") }
    }

    // 自定物件方法
    void login(String account, String passowrd) {
        emailInput.value('xxx@yyy.com')
        passwordInput.value('xxxxxxxx')
        loginButton.click()
    }
}
```

### 完整範例
以下是 `Page` 物件定義的範例，在`content`區塊中，我們將該網頁內容需要被自動化操作的 DOM 元件，先透過 Navigator  API 找到，就能夠方便在測試程式中使用。

* LoginPage.groovy

```
import geb.Page

class LoginPage extends Page {
    static url = "http://localhost:8080/user/login"
    static at = { heading.text() == "Login Form" }
    static content = {
        heading { $("h1.page-title") }
        loginForm { $("form.login-form") }
        loginButton(to: DashboardPage) { $("button.submit") }
    }
}
```

* DashboardPage.groovy

```
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
