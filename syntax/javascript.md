# Javascript

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
