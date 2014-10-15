## atCheckWaiting
在前面Content DSL小節有提到可設定參數，其中一個參數是wait，這個參數預設值為false，但如果設定atCheckWaiting的值為true時，預設參數就會被改為true。
```
atCheckWaiting = true
```
結果像這樣
```
class DynamicPage extends Page {
    static content = {
        dynamicallyAdded(wait: true) { $("p.dynamic") }
    }
}
```
