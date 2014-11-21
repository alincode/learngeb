# Page物件

使用 Page Object 讓自動化測試代碼更容易被重複使用

學會使用 Geb 提供的 Page 物件，就可以發現 Geb 不僅讓 Selenium WebDriver 的操作更簡易，它還可以讓我們針對一個網站應用程式，撰寫更容易重複使用的測試元件。

舉例來說，有一個 Web 系統包含 Login 與 Dashboard 兩個頁面，必須先在 Login 頁面填寫使用者帳號密碼，通過驗證後才能進到 Dashboard  頁面。如果有很多測試功能，都需要先進行 Login  的操作，才允許做其他事，那登入頁就應該獨立成一個繼承 `Page` 的 class ，關於這頁的所有操作都封裝在裡面。
