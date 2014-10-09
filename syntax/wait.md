## 等待時間

### 語法
* waitFor {} 預設的等待時間
* waitFor(10) {} 最多等待10秒
* waitFor(10, 0.5) {} 最多等待10秒，但0.5秒執行一次
* waitFor("quick") {} 依照自定的等待時間

### 官方預設等待時間

```
// GebConfig.groovy

waiting {
    timeout = 10
    retryInterval = 0.5
}
```
### 自定等待時間
```
// GebConfig.groovy

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

```
waitFor("quick"){
	text == "中斷"
}
```
