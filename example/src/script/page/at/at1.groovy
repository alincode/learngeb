@Grab('org.gebish:geb-core:0.10.0')
@Grab('org.seleniumhq.selenium:selenium-htmlunit-driver:2.44.0')
import geb.Browser

driver = 'htmlunit'

import geb.Page

Browser.drive {
    to GebHomePage
    item1.click()
    assert $('#main h1').first().text() == 'Cross Browser Automation'
}.quit()

class GebHomePage extends Page {

    static at = {$('#main h1').first().text() == 'What is it?' }

    static url = "http://www.gebish.org"

    static content = {
        item1 { $('#sidebar li a').first() }
    }
}