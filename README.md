# Black Desert Online fishbot RU
 
 Бот для РУ сервера бдо. При необходимости можно адаптировать работу и под евро.
 
 Основные функции:
- авто ловля рыбы
- автоюз слотов
- фильтрация лута
- оповещения в телеграмм
- реакция на сообщение в ПМ
- автоюз пива, миниигры 

### Requirements

- Разрешение экрана 1920x1080(При необходимости можно добавить поддержку других разрешений)
- Плата arduino pro micro(с поддежкой эмуляции клавиатуры и мыши)
- jdk >= 1.8
- Apache Maven >= 3.0.5

Если предполагается использовать нейронные сети для определения капчи(данный метод предпочтителен, так как является более точным):
- python3
- pip3
- pytorch

## Getting started

```
mkdir blackdesert-fishbot
cd blackdesert-fishbot
git clone https://github.com/Symb1OS/blackdesert-fishbot.git

```

### Install

```
mvn package
```

### Running
```
cd target/blackdesert-fishbot_0.1.9
run.bat
```

Для дальнейших действий воспользуйтесь [инструкцией по настройке и запуску бота](https://docs.google.com/document/d/1DkkaUYzsAG57zADdlMZyV0jzGTR5s-Vo13wi64Z0TC8/edit#heading=h.3ppzcxu04cdm)

### Donation
[Поддержать](https://money.yandex.ru/to/410014569437812)
