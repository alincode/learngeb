## 安裝Jenkins

### 安裝步驟
* Centos

```
sudo wget -O /etc/yum.repos.d/jenkins.repo http://pkg.jenkins-ci.org/redhat-stable/jenkins.repo
sudo rpm --import http://pkg.jenkins-ci.org/redhat-stable/jenkins-ci.org.key
yum install jenkins
service jenkins start
```
### 修改port
自動安裝預設為8080，請更改port的設定 ```JENKINS_PORT=“9095”``` ，因為tomcat預設的port也是8080，會衝突。

### 安裝plugin
* [Publish Over SSH Plugin](https://wiki.jenkins-ci.org/display/JENKINS/Publish+Over+SSH+Plugin)
* [Email-ext plugin](https://wiki.jenkins-ci.org/display/JENKINS/Email-ext+plugin)
