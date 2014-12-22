@Grab('org.gebish:geb-core:0.10.0')
@Grab('org.seleniumhq.selenium:selenium-htmlunit-driver:2.44.0')
import geb.Browser
import geb.Page

driver = 'htmlunit'

Browser.drive {
    config.baseUrl = 'http://ithelp.ithome.com.tw/'
    to IronmanPage, 20092025, 'dev'
}.quit()

class IronmanPage extends Page {
    static url = "ironman7/app/profile"
}