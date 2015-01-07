# atCheckWaiting
在前面 Content DSL 小節有提到可設定參數，其中一個參數是 wait，這個參數預設值為 false，但如果設定 atCheckWaiting 的值為 true 時，預設參數就會被改為 true。

**Geb Sample Code**

```groovy
atCheckWaiting = true
```

結果像這樣

```groovy
class DynamicPage extends Page {
    static content = {
        dynamicallyAdded(wait: true) { $("p.dynamic") }
    }
}
```
