#!/usr/bin/env groovy
@Grab('org.gebish:geb-core:0.9.3')
@Grab('org.seleniumhq.selenium:selenium-htmlunit-driver:2.43.1')
import geb.Browser

def keywords = args.join(' ')

driver = 'htmlunit'

Browser.drive {
    config.reportsDir = new File('.')

    go 'http://google.com'

    println title

    $('form#tsf').with {
      q = keywords
      btnK().click()
    }

    sleep(1000)

    report "test"
    println $('body').dump()
    println $('h3').size()
    //$('a').each { println it.text() }
}.quit()

