## 定義 Page 物件 ##

學會使用 Geb 提供的 Page 物件，就可以發現 Geb 不僅讓 Selenium WebDriver 的操作更簡易，它還可以讓我們針對一個網站應用程式，撰寫更容易重複使用的測試元件。舉例來說，有一個 Web 系統包含 Login 與 Dashboard 兩個頁面，必須先在 Login 頁面填寫使用者帳號密碼，通過驗證後才能進到 Dashboard 頁面。如果有很多測試功能，都需要先進行 Login 的操作，我們就可以利用繼承 `Page` 物件來實作該頁面專屬的測試功能。

以下是 `Page` 物件定義的範例，在 `content` 區塊中，我們將該網頁內容需要被自動化操作的 DOM 元件，先透過 Navigator API 找到，就能夠方便在測試程式中使用。

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
