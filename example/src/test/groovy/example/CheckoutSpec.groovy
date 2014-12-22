package example

import geb.spock.GebReportingSpec
import geb.Page;
import geb.Module;

class CheckoutSpec extends GebReportingSpec{

    def setup() {
        to CheckoutPage
    }

    def '數量都超過0'(){
        expect:
        cartItems.every { it.quantity > 0 }
    }

    def '第一本書的書名'(){
        expect:
        cartItems[0].bookName == "Geb ebook"
    }

    def '檢查第2,3本的書名'(){
        expect:
        cartItems(1..2)*.bookName == ["Spock ebook", "Groovy ebook"]
    }
}

class CartRow extends Module {
    static content = {
        cell { $("td", it) }
        bookName { cell(0).text() }
        quantity { cell(1).text().toInteger() }
        bookId { cell(2).text().toDouble() }
    }
}

class CheckoutPage extends Page {
    static url = 'checkout.html'
    static content = {
        cartItems { moduleList CartRow, $("table tr").tail() } // 忽略標題列
        cartItems { index -> moduleList CartRow, $("table tr").tail(), index }
    }
}