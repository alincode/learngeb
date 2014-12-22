@Grab('org.gebish:geb-core:0.10.0')
@Grab('org.seleniumhq.selenium:selenium-firefox-driver:2.44.0')
import geb.Browser
import groovy.json.JsonSlurper

driver = 'firefox'

Browser.drive {

    go 'http://0.0.0.0:1234/about.html'

    // 值是否相同？
    assert $('span').text() == 'about text'

    // 特定元素存不存在？
    assert $('div').size() == 2

    // 直接詢問後端API
    URL apiUrl = new URL('http://0.0.0.0:1234/myname.json')
    def data = new JsonSlurper().parseText(apiUrl.text)
    assert data.info.firstName == 'Liou'

}.quit()