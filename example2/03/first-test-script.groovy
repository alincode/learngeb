@Grab('org.gebish:geb-core:0.12.2')
@Grab('org.seleniumhq.selenium:selenium-firefox-driver:2.46.0')
import geb.Browser

Browser.drive {
    go 'https://www.google.com.tw/'

    waitFor { title.startsWith('Google') }

    $('input', name: 'q').value('CodeData')
    $('input', name: 'btnI').click()

    waitFor { title.endsWith('CodeData') }

    println $('div.article h3 a').text()
}

