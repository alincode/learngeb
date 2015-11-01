@Grab('org.gebish:geb-core:0.12.2')
@Grab('org.seleniumhq.selenium:selenium-firefox-driver:2.46.0')
import geb.Browser

def keywords = args.join(' ')

Browser.drive {
    go 'http://google.com'

    $('form#tsf').with {
      q = keywords
      btnK().click()
    }
    waitFor {
        $('h3').size() > 0
    }
    $('h3').each {
        println "* ${it.text()}"
    }
}.quit()
