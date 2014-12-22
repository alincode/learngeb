@Grab('org.gebish:geb-core:0.10.0')
@Grab('org.seleniumhq.selenium:selenium-firefox-driver:2.44.0')
import geb.Browser
import geb.Page

driver = 'firefox'

Browser.drive {
    config.baseUrl = 'http://getbootstrap.com/'
    to SigninPage
    println h1Title
}.quit()

class SigninPage extends Page {
    static url = 'examples/signin/'
    static content = {
        h1Title(required: false) {$('h1').text()}
    }
}