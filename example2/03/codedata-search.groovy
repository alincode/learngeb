@Grapes([
    @Grab('org.gebish:geb-core:0.12.2'),
    @Grab('org.seleniumhq.selenium:selenium-firefox-driver:2.46.0'),
    @Grab('org.seleniumhq.selenium:selenium-support:2.46.0')
])
import geb.Browser

Browser.drive {
    go 'http://www.codedata.com.tw/'

    $('input', name: 'keyword').value('groovy')
    $('input', name: 'btnSearch').click()

    $('.list .block h3 a').each { elem ->
        println elem.text()
    }
}
