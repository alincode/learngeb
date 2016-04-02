## Page物件模式

Page有三個靜態屬性

* url：網址
* at：頁面的檢查條件
* content DSL：Page包含的元素

除了這三個區塊外，你還可以額外自定物件的方法。

```groovy
class LoginSpec extends GebReportingSpec{

    def setup() {
        to LoginPage
    }

    def "login"(){
        when:
        login('xxx@yyy.com', '11111111')

        then:
        true
    }
}

class LoginPage extends Page {

    static at = { title == "Login Page" }

    static url = "login.html"

    static content = {
        emailInput { $("input[name=account]") }
        passwordInput { $("input[name=password]") }
        loginButton { $("input[value='Login']") }
    }

    // 自定物件方法
    void login(String account, String passowrd) {
        emailInput.value(account)
        passwordInput.value(passowrd)
        loginButton.click()
    }
}

```
