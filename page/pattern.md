## Page物件模式

Page有三個靜態屬性

* url：網址
* at：頁面的檢查條件
* content：Page包含的元素

除了這三個區塊外，你還可以額外自定物件的方法。

```groovy
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
