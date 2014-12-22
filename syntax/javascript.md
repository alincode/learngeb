# Javascript

## 使用變數

**HTML Source Code**

```html
<html>
    <script type="text/javascript">
        var aVariable = 1;
    </script>
<body>
</body>
</html>
```

**Geb Sample Code**

```groovy
assert js.aVariable == 1
```

## 呼叫方法(method)

**HTML Source Code**

```html
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

**Geb Sample Code**

```groovy
assert js.add(1, 1) == 2

```

## 使用原生JS

**Geb Sample Code**

```groovy
assert js."document.title" == "Javascript Example"
```

## 對話視窗
一般常見的對話視窗有alert、confirm、prompt， [`AlertAndConfirmSupport`](http://www.gebish.org/manual/current/api/geb/js/AlertAndConfirmSupport.html) 這個類別涵蓋了前兩項，由於Geb不鼓勵使用prompt對話視窗，所以不支持這個功能。

常用類別 `AlertAndConfirmSupport` 的方法
<table>
    <tr>
        <th>回傳值</th><th>方法名稱</th><th>說明</th>
    </tr>
    <tr>
        <td>String</td>
        <td>withAlert(Closure actions)</td>
        <td></td>
    </tr>
    <tr>
        <td>void</td>
        <td>withNoAlert(Closure actions)</td>
        <td></td>
    </tr>
    <tr>
        <td>void</td>
        <td>withNoConfirm(Closure actions)</td>
        <td></td>
    </tr>
    <tr>
        <td>String</td>
        <td>withAlert(Closure actions)</td>
        <td>ok參數預設為true，可省略</td>
    </tr>
</table>

範例
----

**Geb Sample Code**

```groovy
class JavascriptSpec extends GebReportingSpec{

    def setup() {
        to JavascriptPage
    }

    def "withAlert"(){
        expect:
            withAlert(wait: true) { showAlert.click() } == "Hello World!"

    }

    def "withNoAlert"(){
        expect:
            withNoAlert { showAlert.click() }
//        java.lang.AssertionError: an unexpected browser alert() was raised (message: Hello World!)
    }

    def "withConfirm"(){
        expect:
            withConfirm(true) { showConfirm.click() } == "Do you like Geb?"
    }
}

class JavascriptPage extends Page{
    static url = "javascript.html"
    static content = {
        showAlert {$("input", name: "showAlert")}
        showConfirm {$("input", name: "showConfirm")}
    }
}

```

**HTML Source Code**

```html
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Javascript Example</title>
</head>
<body>
<input type="button" name="showAlert" onclick="alert('Hello World!');" value="showAlert"/>
<input type="button" name="showConfirm" onclick="confirm('Do you like Geb?');" value="showConfirm"/>
</body>
</html>
```
