# openapi-backend
api开放平台后端


### 加密
```
- nonce生成：UUID.randomUUID()
- 密码加密：new BCryptPasswordEncoder().encode()
- apiKey生成：DigestUtils.md5DigestAsHex((username + new SecureRandom().nextInt()).getBytes());
- api签名加密：DigestUtils.md5DigestAsHex(apiKey.getBytes())
```



