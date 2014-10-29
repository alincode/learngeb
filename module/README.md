# Module物件

若多個頁面中，有重複的組件，我們可以將它獨立成一個module，常見的例子像是「共用底標」、「浮貼在下方的購物車」等，都適合獨立一個module，減少程式碼的重複性。

* 共用底標

![共用底標](footer.png)

* 浮貼在下方的購物車

![浮貼在下方的購物車](shoppingCart.png)

## 簡易範例

    class ShoppingCartSpec extends GebReportingSpec{

        def '檢查首頁是否存在購物車'() {
            to HomePage

            expect:
            shoppingCartModule.shoppingListTab.text() == '購物清單'
        }
    }

    class HomePage extends Page {

        static url = 'http://www.pubu.com.tw'

        static content = {
            shoppingCartModule { module ShoppingCartModule }
        }
    }

    class SubscribePage extends Page {

        static url = 'http://www.pubu.com.tw/subscribe'

        static content = {
            shoppingCartModule { module ShoppingCartModule }
        }
    }

    class ShoppingCartModule extends Module {

        static content = {
            menuTab {$('#cart-slide .ui-tabs-anchor')}
            shoppingListTab { menuTab.getAt(0) }
            shoppingLogTab { menuTab.getAt(1) }
        }
    }

## 額外傳參數給modules

### Module

在Module內，要先宣告要接受傳入的參數名稱，例如「buttonName」。

```
class ExampleModule extends Module {
    def buttonName
    static content = {
        button { $("input", type: "submit", name: buttonName) }
    }
}
```
### Page
theModule元素定義可接受一個傳入參數name，並會將此參數設定給ExampleModule的buttonName值。

```
class ExamplePage extends Page {
    static content = {
        theModule { name -> module ExampleModule, buttonName: name }
    }
}
```

### Test script
如同ExamplePage所宣告的，此時theModule就可以接受一個傳入參數。

```
Browser.drive {
    to ExamplePage
    theModule("something").button.click()
}
```

### 轉換成Java style
有好多參數傳來傳去頭都昏了，如果你跟我一樣，之前是Java背景的工程師，不仿可以先想像成下面這個例子，再去理解groovy code style。

    class ExampleModule extends Module{
        private String buttonName;

        ExampleModule(String name){
           buttonName = name;
        }

        Object button(){
          return $("input", type: "submit", name: buttonName)
        }
    }

    class ExamplePage extends Page {
        ExampleModule theModule(String name){
           return new ExampleModule(name);
        }
    }

    class Test{
        public void main(String arg[]){
           ExamplePage examplePage = new ExamplePage();
           examplePage.theModule("something").button.click();
        }
    }


## Module包含其他Module

Module內可在包含其他Module，但不是透過繼承父類別的方式，這點要注意了。官方文件例子舉得很好，淺顯易懂，就不另外舉其他例子了。

```
class ExampleModule extends Module {
    static content = {
        innerModule { module InnerModule }
    }
}

class InnerModule extends Module {
    static content = {
        button { $("input", type: "submit") }
    }
}

class ExamplePage extends Page {
    static content = {
        theModule { module ExampleModule }
    }
}

Browser.drive {
    theModule.innerModule.button.click()
}
```
