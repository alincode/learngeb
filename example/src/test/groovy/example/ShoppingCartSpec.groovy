package example

import geb.Module
import geb.Page
import geb.spock.GebReportingSpec

class ShoppingCartSpec extends GebReportingSpec{

    def setup() {
        to HomePage
    }

    def '檢查首頁是否存在購物車'() {
        expect:
        shoppingCartModule.shoppingListTab.text() == '購物清單'
    }

    def '設定文字欄位1'(){
        when:
        searchModule.searchInput.value('我要找東西')

        then:
        waitFor {searchModule.searchInput.value() == '我要找東西'}
    }
}

class HomePage extends Page {

    static url = 'http://www.pubu.com.tw'

    static content = {
        shoppingCartModule { module ShoppingCartModule }
        searchModule { module SearchModule }
    }
}

class ShoppingCartModule extends Module {

    static content = {
        menuTab {$('#cart-slide .ui-tabs-anchor')}
        shoppingListTab { menuTab.getAt(0) }
        shoppingLogTab { menuTab.getAt(1) }
    }
}

class SearchModule extends Module {

    static content = {
        searchForm {$('form#search-bar')}
        searchInput { searchForm.find('#global-search-input')}
    }
}

