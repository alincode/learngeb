# 自動完成文字輸入 (Autocomplete)

目前只支援有繼承 `Page` 和 `Module` 的類別的區域，可以自動辨識出 Content DSL 裡定義的屬性，其他區域不行。

例如下面的範例，searchInput 變數寫在 FaqSearchSpec 類別，IDE 就無法辨識，但不代表那樣就是寫錯了，只是 IDE 還沒辦法支援辨識而已。

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
