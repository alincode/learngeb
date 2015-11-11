# Gradle 介紹

提供比 Maven 更大的彈性，它是使用 Groovy 語言，可以直接在設定檔編寫 Script。

## 範例

### 遇到的問題
在這裡以官方範例簡化說明，我們每次增加一種測試的瀏覽器，
就要先配置相對應的 `Driver`，有時候因為開發環境重新安裝，或許又要在派至一次，那我們有辦法直接讓 Build Script 來幫我們解決這樣事呢？

### 解決方式
編寫一個可以自動完成下載 `Driver`，並可依照不同的作業系統，自動設定相對應環境變數？

*** [build.gradle](https://github.com/geb/geb-example-gradle/blob/master/build.gradle
) ***
```
import org.apache.tools.ant.taskdefs.condition.Os

// 宣告引入自行撰寫的 osSpecificDownloads 外掛
apply from: "gradle/osSpecificDownloads.gradle"

// 中間略...

chromeTest {
    // 執行 chromeTest 之前需要先執行 unzipChromeDriver
    dependsOn unzipChromeDriver

    // 依照作業系統不同，可自動調整不一樣的環境設定。
    def chromedriverFilename = Os.isFamily(Os.FAMILY_WINDOWS) ? "chromedriver.exe" : "chromedriver"
    systemProperty "webdriver.chrome.driver", new File(unzipChromeDriver.outputs.files.singleFile, chromedriverFilename).absolutePath
}

```

*** [osSpecificDownloads.gradle](https://github.com/geb/geb-example-gradle/blob/master/gradle/osSpecificDownloads.gradle) ***
```
import org.apache.tools.ant.taskdefs.condition.Os
import org.apache.commons.io.FileUtils

// 基本配置，並設定需要使用的外部 library
buildscript {
	repositories {
		jcenter()
	}
	dependencies {
		classpath "commons-io:commons-io:2.4"
	}
}

// 下載 Chrome 驅動程式
task downloadChromeDriver {
	def outputFile = file("$buildDir/webdriver/chromedriver.zip")
	inputs.property("chromeDriverVersion", chromeDriverVersion)
	outputs.file(outputFile)

	doLast {
		def driverOsFilenamePart

    // 依照作業系統不同，抓取 Driver 的網址也需更變。
		if (Os.isFamily(Os.FAMILY_WINDOWS)) {
			driverOsFilenamePart = "win32"
		} else if (Os.isFamily(Os.FAMILY_MAC)) {
			driverOsFilenamePart = "mac32"
		} else if (Os.isFamily(Os.FAMILY_UNIX)) {
			driverOsFilenamePart = Os.isArch("amd64") ? "linux64" : "linux32"
		}
		FileUtils.copyURLToFile(new URL("http://chromedriver.storage.googleapis.com/${chromeDriverVersion}/chromedriver_${driverOsFilenamePart}.zip"), outputFile)
	}
}

// 解壓縮 Chrome 驅動程式，並把檔案搬到指定的輸出目錄。
task unzipChromeDriver(type: Copy) {
	def outputDir = file("$buildDir/webdriver/chromedriver")

  // 執行 unzipChromeDriver 之前，須先執行 downloadChromeDriver
  dependsOn downloadChromeDriver
	outputs.dir(outputDir)

  // 解壓縮
	from(zipTree(downloadChromeDriver.outputs.files.singleFile))
	into(outputDir)
}
```

## 參考資源
* [Grale User Guide](http://www.gradle.org/docs/current/userguide/userguide.html)
