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
```
