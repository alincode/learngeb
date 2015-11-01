@Grapes([
    @Grab('org.gebish:geb-core:0.12.2'),
    @Grab('org.seleniumhq.selenium:selenium-firefox-driver:2.46.0'),
    @Grab('org.seleniumhq.selenium:selenium-support:2.46.0')
])
import geb.Browser

Browser.drive {
    go 'http://www.codedata.com.tw/'
}
