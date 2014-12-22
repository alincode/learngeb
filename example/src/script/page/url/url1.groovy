@Grab('org.gebish:geb-core:0.10.0')
@Grab('org.seleniumhq.selenium:selenium-htmlunit-driver:2.44.0')
import geb.Browser
import geb.Page

driver = 'htmlunit'

Browser.drive {
    config.baseUrl = 'http://www.gebish.org/'
    to ExamplePage
}.quit()

class ExamplePage extends Page {
    static url = "manual/current/all.html"
}