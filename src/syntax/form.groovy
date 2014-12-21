@Grab('org.gebish:geb-core:0.9.3')
@Grab('org.seleniumhq.selenium:selenium-htmlunit-driver:2.43.1')
import geb.Browser

driver = 'htmlunit'

Browser.drive {
    config.reportsDir = new File('.')

    go 'http://0.0.0.0:1234/form.html'


    // ### 文字欄位 ###

    // 1.
    $('input', name: 'address').value('台北市中正區重慶南路一段')

    // 2.
    $('form').address = '台北市中正區重慶南路一段'

    println $('input', name: 'address').value()
    println $('form').address

    assert $('input', name: 'address').value() == '台北市中正區重慶南路一段'
    assert $('form').address.startsWith('台北市')
    assert $('form').address == '台北市中正區重慶南路一段'


    // ### 核取方塊 ###
    $('checkbox', name: 'donate').click()
    $('form').donate().click()

    def isDonate = $('checkbox', name: 'donate').value()
    $('checkbox', name: 'donate').value(true)
    $('checkbox', name: 'donate').value(false)

    // 表單捷徑的語法範例
    $('form').donate().click()
    $('form').donate = true // Or false
    isDonate = $('form').donate


    // ### 多選核取方塊 ###
    // 全部選取
    $('form').hobbies = true

    // 全部取消
    $('form').hobbies = false

    $('checkbox', name: 'hobbies').value('chicken')
    $('form').hobbies = 'chicken'

    // 取得值
    $('form').hobbies

    // 判斷式
    // 沒有一個欄位被勾選
    assert $('input', name: 'hobbies').value() == false

    // 檢查 chicken 是否被勾選
    assert $('form').hobbies.contains('chicken')


    // ### 選項按鈕 ###
    // 設定值
    $('form').sex = "female"
    // 取得值
    $('form').sex
    // 判斷式
    assert $('form').sex == 'female'


    // ### 檔案欄位 ###
    $('form').csvFile = '/tmp/data.csv'

    // Or
    $('input', name : 'csvFile').value('/tmp/data.csv')


    // ### 下拉式選單 ###
    // 設定值
    $('input', name: 'artist').value('1') // Or 'Alin'

    // Or in shortcut style
    $('form').artist = 'Alin'
    $('form').artist = 1 // same as '1'

    // 取得值
    $('form').artist

    // 判斷式
    assert $('form').artist == '1'

}.quit()