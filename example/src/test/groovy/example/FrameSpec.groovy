package example

import geb.Page
import geb.spock.GebReportingSpec

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
