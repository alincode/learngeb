@Grab('org.gebish:geb-core:0.10.0')
@Grab('org.seleniumhq.selenium:selenium-firefox-driver:2.44.0')
import geb.Browser
import geb.Page

driver = 'firefox'

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