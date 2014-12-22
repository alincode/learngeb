@Grab('org.gebish:geb-core:0.10.0')
@Grab('org.seleniumhq.selenium:selenium-htmlunit-driver:2.44.0')
import geb.Browser

driver = 'htmlunit'

Browser.drive {

    go 'http://0.0.0.0:1234/layout.html'

    withFrame('header') { assert $('span').text() == 'header text' }
    withFrame('footer') { assert $('span').text() == 'footer text' }
    withFrame(0) { assert $('span').text() == 'header text' }
    withFrame($('#footer')) { assert $('span').text() == 'footer text' }

}.quit()