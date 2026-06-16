# 🔐Генератор паролей

Простой консольный генератор паролей на Java

--- 

## ❗Особенность

Использует название сервиса и число, введённое пользователем, для генерации пароля.<br>
Для одинаковых входных данных будет сгенерирован один и тот же пароль.

---

## ✨Возможности

- Создание паролей
- Сохранение данных в БД

---
## 🛠️ Технологии

- Java 21+
- Maven

---

## 📁 Структура проекта

```
project/
├──data
│   └──database.db
├──src/
│   ├──main
│   │    └──java
│   │        ├── Main.java
│   │        ├── Generator.java
│   │        ├── FileReader.java
│   │        └── UniqueGenerator.java
│   └──test
│       └──java
├──pom.xml
└──README.md
```

---

## ⚙️ Запуск проекта

```bash
git clone git@github.com:yasteo/password-generator
cd password-generator
mvn clean package
java --enable-native-access=ALL-UNNAMED -jar target/*.jar
```

---

## 📋 Чек-лист

- [x] Сохранение данных для пароля в базе данных.