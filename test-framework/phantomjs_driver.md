# PhantomJs Driver

它是目前正當紅的Headless Website Testing，不需瀏覽器就可以測試，有些情況我們可能沒有GUI界面的測試機可以使用，那它就正好可派上用場了。

如果您的網站對於小視窗的適應度不好，記得要設好是固定視窗大小，還有PhantomJs對於，點擊後才能看見的選單，相容性也不是很好，實作時需要想辦法避開這個問題。

## 安裝

1. [下載安裝檔](http://phantomjs.org/download.html)
2. 開始安裝

### 在 Mac 安裝 裝 PhantomJs Driver

```bash
brew update && brew install phantomjs
```

### 在 Windows 安裝 裝 PhantomJs Driver

解壓縮後，bin資料夾的phantomjs，便可執行。

### 在 Linux 安裝 裝 PhantomJs Driver

```bash
tar jxvf  phantomjs-1.9.7-linux-x86_64.tar.bz2
mv phantomjs-1.9.7-linux-x86_64 phantomjs
ln -s /opt/phantomjs/bin/phantomjs /usr/bin/phantomjs
```
## 設定範例

### 範例一

```groovy
phantomjs {
    Capabilities caps = DesiredCapabilities.phantomjs()
    PhantomJSDriverService.createDefaultService(caps)
    driver = {new PhantomJSDriver(caps)}
}
```

### 範例二

額外設定參數

```groovy
    phantomjs {
        DesiredCapabilities caps = new DesiredCapabilities(
                'phantomjs.cli.args': [
                        '--ignore-ssl-errors=true',
                        '--ssl-protocol=any',
                ] as String[],
        )
        PhantomJSDriverService.createDefaultService(caps)
        phantomJSDriver = new PhantomJSDriver(caps)
        // 設定視窗的寬高
        phantomJSDriver.manage().window().setSize(new Dimension(1280, 800))
        driver = { phantomJSDriver }
    }
```

## 可額外設定的參數

* [phantomjs command line](http://phantomjs.org/api/command-line.html)
* capabilitie

```
    Session.negotiatedCapabilities - {
    "browserName":"phantomjs”,
    "version":"1.9.7”,
    "driverName":"ghostdriver”,
    "driverVersion":"1.1.0”,
    "platform":"mac-unknown-64bit”,"
    javascriptEnabled":true,
    "takesScreenshot":true,
    "handlesAlerts":false,
    "databaseEnabled":false,
    "locationContextEnabled":false,
    "applicationCacheEnabled":false,
    "browserConnectionEnabled":false,
    "cssSelectorsEnabled":true,
    "webStorageEnabled":false,
    "rotatable":false,
    "acceptSslCerts":false,
    "nativeEvents":true,
    "proxy":{"proxyType":”direct”}
    }
```
