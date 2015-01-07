# 搭配單元測試框架

使用 Geb 撰寫的測試程式，可以作為專案自動化功能測試（functional test）的項目，搭配 IDE 開發工具或 Jenkins CI 持續整合，讓瀏覽器自動化測試的結果，與其它單元測試項目一併被執行與產生測試報告。Geb 可以和常見的 Java 測試框架搭配使用，例如：

* Spock
* JUnit
* TestNG

以下是搭配 Spock 測試框架的測試程式範例。

```groovy
import geb.Page
import geb.spock.GebSpec

class LoginSpec extends GebSpec {
    def "login to dashboard section"() {
        given:
        to LoginPage

        when:
        loginForm.with {
            username = "admin"
            password = "password"
        }

        and:
        loginButton.click()

        then:
        at DashboardPage
    }
}
```

在搭配 Spock 框架使用時，需要額外的 [geb-spock](http://mvnrepository.com/artifact/org.gebish/geb-spock) 套件。

```groovy
@Grab('org.gebish:geb-spock:0.10.0')
```
