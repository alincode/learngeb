# Module 包含其他 Module

Module 內可在包含其他 Module ，但不是透過繼承父類別的方式，這點要注意了。官方文件例子舉得很好，淺顯易懂，就不另外舉其他例子了。

**Geb Sample Code**

```groovy
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
