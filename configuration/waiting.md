# Waiting

## 覆蓋預設參數

原本初始預設只有五秒，你覺得太短的話，可以直接改掉。

**Geb Sample Code**

```groovy
waiting {
    timeout = 10
    retryInterval = 0.5
}
```

## 定義自定參數

有時候預設的時間，並不適用於所有測試需求，但你又不希望每次都自己指定時間，而且指定時間也容易產生重複，身為開發者最討厭重複了，所以這裡可以自定參數。

**Geb Sample Code**

```groovy
waiting {
    presets {
        slow {
            timeout = 20
            retryInterval = 1
        }
        quick {
            timeout = 1
        }
    }
}
```

* timeout: 最長等待時間為n秒
* retryInterval: 每次判斷條件的間隔時間為n秒

## 使用waitFor自定參數

**Geb Sample Code**

```groovy
Browser.drive {
    // 略...
    waitFor("quick"){
	    2 == 1 + 1
    }
}
```
