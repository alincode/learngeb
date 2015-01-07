# Geb + Groovy + Selenium

使用的程式語言 Groovy

```
def element = WaitFor{$('#dynamicContent')}
assert element.text() == '動態內容'
```

# Java + Selenium

使用的程式語言 Java

```
WebDriverWait wait = new WebDriverWait(driver, 10);
WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dynamicContent")))
ssertEquals(element.getText(), "動態內容")
```

# Robot + Selenium

RobotFramework是python-based的自動化測試框架，用於驗收測試，所有的測試案例都是透過**關鍵字**寫成。

**具備下列特性：**
* 使用python語言
* 寫法就像一個一個的Keyword串成command似的
* 提供基本的 library API，可另外撰寫Python 和 Java 程式來擴充
* 提供HTML、TSV、純文字等格式，產出測試報表

```
*** Settings ***
Documentation     A test suite with a single test for valid login.
...
...               This test has a workflow that is created using keywords in
...               the imported resource file.
Resource          resource.txt

*** Test Cases ***
Valid Login
    Open Browser To Login Page
    Input Username    demo
    Input Password    mode
    Submit Credentials
    Welcome Page Should Be Open
    [Teardown]    Close Browser
```

