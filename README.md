# Book Project

## Описание

Проект **Book** — это веб-приложение на Spring Boot, реализующее базовую функциональность CRUD для управления книгами. В проекте используются:

- Spring Boot 3.5.3
- Spring Data JPA для работы с базой данных
- H2 Database — встраиваемая база данных для тестирования и разработки
- Spring Validation для проверки входящих данных
- SpringDoc OpenAPI для автоматической генерации документации API
- JUnit 5 и Mockito для тестирования

---

## Структура проекта

- Контроллеры обрабатывают HTTP-запросы
- Сервисы реализуют бизнес-логику
- Репозитории работают с базой данных через JPA
- Валидация и глобальная обработка ошибок обеспечивают корректность данных и удобство работы с API

---

## Инструкция по запуску

### Требования

- Java 17 или выше (совместимая с Spring Boot 3.x)
- Maven (или использовать встроенный wrapper `./mvnw` / `mvnw.cmd`)

### Клонирование репозитория

```bash
git clone <URL вашего репозитория>
cd Book
```

### Сборка и запуск
Сборка проекта и запуск приложения:

```bash
./mvnw clean install
./mvnw spring-boot:run
```
Либо используйте встроенный функционал IDE

### Запуск тестов

```bash
./mvnw test
```

---
## Конфигурация

### Настройки БД по умолчанию:
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
```
---
## API:
### Модель Book

| Метод  | URL                | Описание                    | Тело запроса (JSON)                                      | Ответ (JSON)                             | Статус  |
|--------|--------------------|-----------------------------|----------------------------------------------------------|-----------------------------------------|---------|
| GET    | `/api/books`        | Получить список всех книг    | —                                                        | Массив книг                             | 200 OK  |
| GET    | `/api/books/{id}`   | Получить книгу по ID         | —                                                        | Книга с указанным ID                    | 200 OK  |
| POST   | `/api/books`        | Создать новую книгу          | `{ "title": "string", "author": "string", "year": int }` | Созданная книга                        | 201 Created |
| PUT    | `/api/books/{id}`   | Обновить книгу по ID         | `{ "title": "string", "author": "string", "year": int }` | Обновлённая книга                      | 200 OK  |
| DELETE | `/api/books/{id}`   | Удалить книгу по ID          | —                                                        | —                                       | 204 No Content |

---
## Примеры запросов


* Получить все книги `curl -X GET http://localhost:8080/api/books`

* Получить книгу по ID `curl -X GET http://localhost:8080/api/books/1`

* Создать новую книгу
```bash
curl -X POST http://localhost:8080/api/books \
-H "Content-Type: application/json" \
-d '{"title": "Война и мир", "author": "Толстой", "year": 1869}
```


* Обновить книгу
```bash
curl -X PUT http://localhost:8080/api/books/1 \
-H "Content-Type: application/json" \
-d '{"title": "Анна Каренина", "author": "Толстой", "year": 1877}'
```

* Удалить книгу
`curl -X DELETE http://localhost:8080/api/books/1`
---
## Валидация и обработка ошибок
### Для полей модели настроена валидация через аннотации:
@Valid, @NotNull и т.д.

Глобальная обработка ошибок реализована с помощью @ControllerAdvice.
В случае ошибок валидации возвращается понятный JSON с описанием ошибок
---
## Контакты

Если есть вопросы или предложения — создавайте issues или пишите мне напрямую.

---