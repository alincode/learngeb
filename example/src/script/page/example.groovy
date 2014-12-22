@Grab('org.gebish:geb-core:0.10.0')
@Grab('org.seleniumhq.selenium:selenium-firefox-driver:2.44.0')
import geb.Browser
import geb.Page

driver = 'firefox'

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