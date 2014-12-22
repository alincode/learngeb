# Frame
現在多數的網站都不用瀏覽器原生的彈跳視窗，而是使用框架提供的視窗，以 [`JQuery UI dialog`](http://jqueryui.com/dialog/) 為例，可透過瀏覽器開發者工具動態觀察原始碼，你就可以發現實際上背後是iframe元素。

語法上可以大概分為兩類：
* 直接控制元素
* 定義成Page物件的content後，在控制元素

下面例子是針對官方舉的例子做修改與延伸，其他method的使用，可以在查看 [`Frame API`](http://www.gebish.org/manual/current/api/geb/frame/FrameSupport.html)。

## 直接控制元素

**HTML Source Code (layout.html)**

```html
<html>
    <body>
    <iframe name="header" src="header_frame.html"></iframe>
    <iframe id="footer" src="footer_frame.html"></iframe>
    <iframe id="content" src="about.html"></iframe>
    <span>main</span>
    </body>
</html>
```

**Geb Sample Code**

```groovy
withFrame('header') { assert $('span').text() == 'header text' }
withFrame('footer') { assert $('span').text() == 'footer text' }
withFrame(0) { assert $('span').text() == 'header text' }
withFrame($('#footer')) { assert $('span').text() == 'footer text' }
```

## 定義成Page物件的content後，在控制元素

**HTML Source Code (about.html)**

```html
<html>
    <body>
        <span>about text</span>
    </body>
</html>
```

**Geb Sample Code**

```groovy
class FrameSpec extends GebReportingSpec{

    def setup() {
        to LayoutPage
    }

    def 'frame example 1'(){
        when:
        withFrame($('#content')) {
            assert $('span').text() == 'about text'
        }

        then:
        $('span').text() == 'main'
    }

    def 'frame example 2'(){
        when:
        withFrame(aboutFrame) {
            assert $('span').text() == 'about text'
        }

        then:
        $('span').text() == 'main'
    }

    def 'frame example 3'(){
        when:
        withFrame('header') {
            assert $('span').text() == 'header text'
        }

        then:
        $('span').text() == 'main'
    }

    def 'frame example 4'(){
        when:
        withFrame(0) {
            assert $('span').text() == 'header text'
        }

        then:
        $('span').text() == 'main'
    }

}

class LayoutPage extends Page {
    static url = 'layout.html'
    static content = {
        aboutFrame(page: AboutPage){$('#content')}
    }
}

class AboutPage extends Page {
    static url = 'about.html'
}
```
