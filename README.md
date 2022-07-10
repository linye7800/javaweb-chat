# cookie-session-jsp

### 手头上有个重要的老Java项目，写个简单的项目练练手，回顾一下以前的老技术。

## 技术栈
### 核心想回归WebSocket
### JSP
### Cookie 
### Session
### WebSocket

## 第一阶段基本 Cookie + Session + SSM + JSP
## 第二阶段     + WebSocket
## 第三阶段     + 把WebSocket改为基于Netty
## 第四阶段     + Maven
## 第五阶段     + 改为Springboot项目

### 新路历程
20220516 开启第一阶段
20220516 23:46 把项目init 成功了

### Session 与 Cookie
session 处理用户会话，与登录有关
cookie 也是处理用户会话，与登录有关

session 和cookie的区别
(1) session生成的数据在服务器
    cookie生成的数据在客户端浏览器
(2) 有效期计算方式不同
    session: 有效期从最后一次请求结束后，开始计时
    cookie: 有效期是固定的，从创建开始计时
(3) session的数据存储在服务器，客户端浏览器无法干预，非常安全
    cookie的数据存储到浏览器，用户可以篡改和删除，不安全
## 借鉴的大佬

## TODO
缺一个用户logout的功能
1.根据用户名移除用户在httpsession的信息
2.移除在websocket里面的session信息
3.刷新用户列表，但要保留他的聊天信息