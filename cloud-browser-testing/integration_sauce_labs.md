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

*** build.gradle ***
```
import geb.gradle.saucelabs.SauceAccount

apply plugin: "geb-saucelabs"

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath 'org.gebish:geb-gradle:0.12.2'
    }
}

repositories {
    mavenCentral()
    maven { url "http://repository-saucelabs.forge.cloudbees.com/release" }
}

dependencies {
    // 這裡是列出新增的，以簡化範例。
    sauceConnect "com.saucelabs:ci-sauce:1.81"
}

sauceLabs {
    browsers {
        firefox_linux_39 {
            capabilities(
                    name: "FireFox Linux 39 Test",
                    browserName: "FireFox",
                    passed: "true"
            )
        }
        internet_explorer_11{
            capabilities(
                    name: "Internet Explorer 11 Test",
                    browserName: "iexplore",
                    passed: "true",
                    screenResolution: "1280x1024",
                    version: "11",
                    platform: "Windows 7"
            )
        }
    }
    task {
        testClassesDir = test.testClassesDir
        testSrcDirs = test.testSrcDirs
        classpath = test.classpath
    }
    account {
        username = System.getenv(SauceAccount.USER_ENV_VAR)
        accessKey = System.getenv(SauceAccount.ACCESS_KEY_ENV_VAR)
    }
    connect {
        port = 4445
    }
}
```
