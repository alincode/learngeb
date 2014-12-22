package example

import geb.Page
import geb.spock.GebReportingSpec

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
