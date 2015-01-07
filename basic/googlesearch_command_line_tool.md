# GoogleSearch 命令列界面

```groovy
#!/usr/bin/env groovy
@Grab('org.gebish:geb-core:0.10.0')
@Grab('org.seleniumhq.selenium:selenium-firefox-driver:2.44.0')
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
```

Usage

```bash
chmod a+x GoogleSearch
./GoogleSearch learn geb
```
