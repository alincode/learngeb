## Frame

語法上可以大概分為兩類：

* 直接控制元素

* 定義成Page物件的content後在控制元素

我就針對官方舉的例子做修改與延伸，其他method的使用，可以看 [Frame API](http://www.gebish.org/manual/current/api/geb/frame/FrameSupport.html)


### 直接控制元素

* Layout.Page

```
<html>
    <body>
        <frame name="header" src="header_frame.html"></frame>
        <frame id="footer" src="footer_frame.html"></frame>
        <iframe id="content" src="about.html"></iframe>
        <span>main</span>
    <body>
</html>
```

#### 語法範例

```
withFrame('header') { assert $('span') == 'header text' }
withFrame('footer') { assert $('span') == 'footer text' }
withFrame(0) { assert $('span') == 'header text' }
withFrame($('#footer')) { assert $('span') == 'footer text' }
```

### 定義成Page物件的content後在控制元素

* about.html

```
<html>
    <body>
        <span>about text</span>
    </body>
</html>
```

#### 語法

```
to LayoutPage
withFrame(aboutFrame) {
      assert $('span') == 'about text'
}
```

#### Page物件

```
class LayoutPage extends Page{

    static content = {
        aboutFrame(page: AboutPage){$('#content')}
    }
}
```
