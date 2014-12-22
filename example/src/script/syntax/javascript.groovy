@Grab('org.gebish:geb-core:0.10.0')
@Grab('org.seleniumhq.selenium:selenium-firefox-driver:2.44.0')
import geb.Browser

driver = 'firefox'

Browser.drive {
    config.reportsDir = new File('.')

    go 'http://0.0.0.0:1234/javascript.html'

    // 使用變數
    assert js.aVariable == 1

    // 呼叫方法
    assert js.add(1, 1) == 2

    // 使用原生JS
    assert js."document.title" == "Javascript Example"


}.quit()