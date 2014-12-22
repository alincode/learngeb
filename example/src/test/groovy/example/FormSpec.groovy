package example

import geb.Page
import geb.spock.GebReportingSpec

class FormSpec extends GebReportingSpec{

    def setup(){
        to FormPage
    }

    def 'text field example'(){
        when:
        $("input", name: "address").value("台北市XX區")

        then:
        $("input", name: "address").value() == "台北市XX區"
    }

    def 'text field example 2'(){
        when:
        $("form").address = "台北市YY區"

        then:
        $("form").address == "台北市YY區"
    }

    def 'select field example'(){
        when:
        $("form").artist = "Alin"
        println($("form").artist)

        then:
        $("form").artist == "1"
    }

    def 'select field example 2'(){
        when:
        $("form").artist = "2"

        then:
        $("form").artist == "2"
    }

    def 'select field example 3'(){
        when:
        $("form").artist = 3

        then:
        $("form").artist == "3"
    }

    def 'select field example 4'(){
        when:
        $("input", name: "artist").value("1")

        then:
        $("form").artist == "1"
    }

    def 'select multiple field example'(){

    }

    def 'unchecked checkbox example'(){
        println($('form').hobbies)

        expect:
        $("input", name: "hobbies").value() == false
    }

    def 'checkbox field example 1'(){
        when:
        $('form').hobbies = "chicken"
        println($('form').hobbies)

        then:
        waitFor { $('form').hobbies.contains("chicken")}
    }

    def 'checkbox field example 2'(){
        when:
        $("input", name: "hobbies").value("chicken")

        then:
        waitFor { $('form').hobbies.contains("chicken")}
    }

    def 'checkbox field example 3'(){
        when:
        $('form').hobbies = true
        println($('form').hobbies)

        then:
        $('form').hobbies.contains("music") && $('form').hobbies.contains("chicken")
    }

    def 'radio field example'(){
        when:
        $("form").sex = "female"
        println($("form").sex)

        then:
        $("form").sex == "female"
    }

    def 'radio field example 2'(){
        when:
        $("form").sex = "male"

        then:
        $("form").sex != "female"
    }

    def 'file field example'(){
        when:
        $("form").csvFile = "/Users/ailinliu/Desktop/markdown.pdf"

        then:
        $("form").csvFile == 'C:\\fakepath\\markdown.pdf'
    }

    def 'file field example 2'(){
        when:
        $('input', name : 'csvFile2').value('/Users/ailinliu/Desktop/markdown.pdf')

        then:
        $("form").csvFile2 == 'C:\\fakepath\\markdown.pdf'
    }

    def 'select multiple example'(){
        when:
        $("form").meaf = ["1", "2"]

        then:
        $('form').meaf.contains('1') && $('form').meaf.contains('2')
    }
}

class FormPage extends Page{
    static url = "form.html"
    static content = {
        addressInput {$("input", name: "address")}
        artistInput {$("input", name: "artist")}
        meafInput {$("input", name: "meaf")}
        hobbiesInput {$("input", name: "hobbies")}
        donateInput {$("input", name: "donate")}
        sexInput {$("input", name: "donate")}
    }
}
