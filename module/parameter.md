## 傳參數
額外傳參數給modules

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
