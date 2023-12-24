# itmo-se-web04

## Установка
### Установка на сервер ubuntu
```bash
apt-get update
apt-get upgrade
```

```bash
apt install openjdk-17-jdk openjdk-17-jre
```
Проверяем, что все установилось: `java -version`

Устанавливаем maven:
```bash
sudo apt install maven
mvn -version
```

#### Собираем backend:
Переходим в директорию `backend` и запускаем: ``mvn clean package``.

### Docker
#### Состав
- web04-backend
- web04-frontend
- db
- nginx

#### Команды
Выполняем из корневой директории проекта (там где находится docker-compose.yml). 
##### Запуск
Выполняем запуск docker-compose (запускает все необходимые для работы контейнеры):
``docker-compose up -d``. Данная команда запускает все контейнеры,

##### etc
- ``docker-compose down`` Остановка всех контейнеров.
- ``docker-compose down -v`` Остановка всех контейнеров и очистка volume.
- ``docker-compose build <container-name>`` сборка необходимого контейнера.