## PhantomJs Driver
它是目前正當紅的Headless Website Testing，不需瀏覽器就可以測試，有些情況我們可能沒有GUI界面的測試機可以使用，那它就正好可派上用場了。

### 安裝
* [下載安裝檔](http://phantomjs.org/download.html)
* 開始安裝

#### Mac
```
brew update && brew install phantomjs
```
#### Windows
解壓縮後，bin資料夾的phantomjs，便可執行

### Linux
```
tar jxvf  phantomjs-1.9.7-linux-x86_64.tar.bz2
mv phantomjs-1.9.7-linux-x86_64 phantomjs
ln -s /opt/phantomjs/bin/phantomjs /usr/bin/phantomjs
```

### 範例一
```
    phantomjs {
        Capabilities caps = DesiredCapabilities.phantomjs()
        PhantomJSDriverService.createDefaultService(caps)
        driver = {new PhantomJSDriver(caps)}
    }
```

### 範例二：額外設定參數
```
    phantomjs {
        DesiredCapabilities caps = new DesiredCapabilities(
                'phantomjs.cli.args': [
                        '--ignore-ssl-errors=true',
                        '--ssl-protocol=any',
                ] as String[],
        )
        caps.setCapability("takesScreenshot", true);
        driver = {new PhantomJSDriver(caps)}
    }
```

### 可額外設定的變數有兩類
* [phantomjs command line](http://phantomjs.org/api/command-line.html)
* capabilitie

log:

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
