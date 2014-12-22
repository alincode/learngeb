@Grab('org.gebish:geb-core:0.10.0')
@Grab('org.seleniumhq.selenium:selenium-htmlunit-driver:2.44.0')
import geb.Browser
import geb.Page

driver = 'htmlunit'

Browser.drive {
    config.baseUrl = 'http://www.gebish.org/'
    to FrontPage
    assert menuText("crossbrowser") == "Cross Browser"
    assert menuText("content") == "jQuery-like API"
}.quit()

class FrontPage extends Page {
    static content = {
        menuText { sidemenuName -> $('.sidemenu li', class: sidemenuName).find('a').text() }
    }
}