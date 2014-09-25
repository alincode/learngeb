# 未確認分類主題 #

## 直接使用javascript

### 怎麼使用變數

```
<html>
    <script type="text/javascript">
        var aVariable = 1;
    </script>
<body>
</body>
</html>
```

```
assert js.aVariable == 1
```
### 怎麼呼叫方法(method)
```
<html>
    <script type="text/javascript">
        function add(a,b) {
            return a + b;
        }
    </script>
<body>
</body>
</html>
```

```
assert js.add(1, 1) == 2

```

### 怎麼使用原生JS

```
assert js."document.title" == "Geb"
```

## 等待時間(Waiting)
* waitFor {} 預設的等待時間
* waitFor(10) {} 最多等待10秒
* waitFor(10, 0.5) {} 最多等待10秒，但0.5秒執行一次
* waitFor("quick") {} 依照自定的等待時間

### 官方預設等待時間

```
waiting {
    timeout = 10
    retryInterval = 0.5
}
```
### 自定等待時間

```
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

## 常見驗證條件

### 值
```
<html>
<body>
	<div>aaa</div>
</body>
</html>

assert $('div').text() == 'aaa'
```

### 數量
```
<html>
<body>
	<div></div>
	<div></div>
</body>
</html>

assert $('div').size() == 2
```

### JSON

```
go('/rest/instances/'+ id + '/usage?knot=1')
def result = $('pre').text().substring(1, $('pre').text().length()-1)
def info = new JsonSlurper().parseText(result)

assert info.cpu == 1
assert info.networkOut == 0
assert info.networkIn == 0


## Template Options
* required
* cache
* to
* wait
* page

```
«name»(«options map») { «definition» }

theDiv(cache: false, required: false) { $("div", id: "a") }

// TODO: 待補