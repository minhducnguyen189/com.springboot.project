## EXCEPTION HANDLER WITH MESSAGE SOURCE

- You can simply clone this source code then using `mvn clean package -DskipTests` to build.
- Then start service and call api GET http://localhost:8080/v1/customers?email=abcdef@gmail.com to test the error messages.

```
curl --location --request GET 'http://localhost:8080/v1/customers?email=abcdef@gmail.com \
--header 'Content-Type: application/json'

```

- If you want to change the language you can go to and edit locale. For example: 

```.properties

  web:
    locale: ja_JP
    locale-resolver: fixed

```

- Note: this source only supports Vietnamese and Japanese languages, if you want to use with different languages, you can define more in `messages` of `resources`.
- [View this post](https://minhducnguyen189.github.io/java/2021/11/28/java-springboot-exception-handler-message-source.html) for more details. Thanks.