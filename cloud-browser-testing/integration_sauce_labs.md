# 實戰 Sauce Labs

*** GebConfig.groovy ***
```
import geb.driver.SauceLabsDriverFactory

def sauceLabsBrowser = System.getProperty("geb.saucelabs.browser")
if (sauceLabsBrowser) {
    driver = {
       def username = System.getenv("GEB_SAUCE_LABS_USER")
       assert username
       def accessKey = System.getenv("GEB_SAUCE_LABS_ACCESS_PASSWORD")
       assert accessKey
       new SauceLabsDriverFactory().create(sauceLabsBrowser, username, accessKey)
    }
}

baseUrl = 'http://gebish.org'
```
