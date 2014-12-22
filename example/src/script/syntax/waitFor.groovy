@Grab('org.gebish:geb-core:0.10.0')
@Grab('org.seleniumhq.selenium:selenium-firefox-driver:2.44.0')
import geb.Browser

driver = 'firefox'

Browser.drive {
    go 'https://www.google.com/recaptcha/demo/ajax'

    $('input', value: 'Click Me').click()

    waitFor {
        $('#recaptcha_area').size() > 0
    }

    println $('#recaptcha_challenge_image').attr('src')
}.quit()