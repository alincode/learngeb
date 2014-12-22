@Grab('org.gebish:geb-core:0.10.0')
@Grab('org.seleniumhq.selenium:selenium-firefox-driver:2.44.0')
import geb.Browser
import geb.Page

driver = 'firefox'

Browser.drive {
    config.baseUrl = 'http://www.jacklmoore.com/'
    to OutsidePage
    outsideWebpageLink.click()
    sleep(5000)

    withFrame(wikiFrame){
        assert h1Ttitle == 'Wikipedia'
    }
}.quit()

class OutsidePage extends Page {
    static url = '/colorbox/example2/'
    static content = {
        outsideWebpageLink {$('.iframe')}
        wikiFrame(page: WikiPage){$('iframe')}
    }
}

class WikiPage extends Page{
    static at = {title == 'Wikipedia'}
    static url = 'http://www.wikipedia.org/'
    static content = {
        h1Ttitle {$('h1 img').attr('title')}
    }
}