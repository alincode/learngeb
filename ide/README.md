# IDE

目前對於 Groovy 支援度最好的編輯器，就是 [IntelliJ IDEA](http://blog.lyhdev.com/2012/12/intellij-idea-12-java.html) 不需要另外裝什麼東西，就可以開始寫了，但如果你真的不想捨棄慣用的 Eclipse，也是有 [Eclipse Plugin](http://groovy.codehaus.org/Eclipse+Plugin) 可以安裝。

我之前也是 Eclipse 的愛好者，因為 Geb 官方推薦的原因，而開始用了 IntelliJ IDEA，除了快捷鍵差異要適應一下之外，操作習慣還蠻像的。

## 使用 IntelliJ IDEA 的好處

現在的 IDE 都很聰明，會自動比對是否有打錯字，你可否跟我一樣非常依賴這個功能？但要能自動比對，前提是 IDE 要能辨識的出來才能成立，這也是 Geb 官方推薦 IntelliJ IDEA 的原因。

### 可辨別出 `Browser` 的方法

只要類別是繼承 GebSpec，例如 `GebReportingSpec` 或 `GebSpec` 都可以，都可以辨識 [`Browser`](http://www.gebish.org/manual/current/api/geb/Browser.html) 的方法，像 `to` 和 `at` 就是 `Browser` 類別的方法。

**Geb Sample Code**

```groovy
class LoginWebSiteSpec extends GebReportingSpec {

    def "go to login page"(){
        when:
        to LoginPage

        then:
        at LoginPage
    }
}
```

### 可辨識出 Content DSL

只要是繼承 `Page` 和 `Module` 的類別，在都可以自動辨識出 Content DSL 裡定義的屬性。例如下面範例的 privacyText 變數，在 IntelliJ IDEA 就可以被辨識出來。

**Geb Sample Code**

```groovy
class FrontPage extends Page{

    static content = {
        privacyText {$('.footer a[href$=privacy]').text()}
    }

    def checkLanguageZhTW() {
        waitFor { privacyText == "隱私" }
    }

}
```

## 自動完成文字輸入 (Autocomplete)

目前只支援有繼承 `Page` 和 `Module` 的類別的區域，可以自動辨識出 Content DSL 裡定義的屬性，其他區域不行。

例如下面的範例，searchInput 變數寫在 FaqSearchSpec 類別，IDE 就無法辨識，但不代表那樣寫是錯的，只是 IDE 還沒辦法支援辨識功能而已。

**Geb Sample Code**

```groovy
class FaqPage extends Page{

    static content = {
        searchInput {$('.search-query')}
        searchButton {$('button' , text: 'Search')}
        isExistSearchResult {$('#tabSearchResult h4').size() > 0}
    }

}

class FaqSearchSpec extends GebReportingSpec{

    def "Input Taiwan then click Search button"(){
        when:
        to FaqPage
        searchInput.value('Taiwan')
        searchButton.click();

        then:
        isExistSearchResult
    }

}
```
