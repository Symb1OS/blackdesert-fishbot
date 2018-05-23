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
- python3.6
- pip3
- pytorch

### Presets
Устанавливаем Java, при необходимости добавляем переменные окружения, открываем консоль, проверяем

```
java --version

Вывод примерно следующий

C:\Users\jyk>java -version
java version "1.8.0_171"
Java(TM) SE Runtime Environment (build 1.8.0_171-b11)
Java HotSpot(TM) 64-Bit Server VM (build 25.171-b11, mixed mode)

```

Устанавливаем Maven, добавляем переменные окружения, проверяем:

```
mvn --version

C:\Users\jyk>mvn --version
Apache Maven 3.5.3 (3383c37e1f9e9b3bc3df5050c29c8aff9f295297; 2018-02-24T
5+03:00)
Maven home: C:\programms\apache-maven-3.5.3-bin\apache-maven-3.5.3\bin\..
Java version: 1.8.0_171, vendor: Oracle Corporation
Java home: C:\Program Files\Java\jdk1.8.0_171\jre
Default locale: ru_RU, platform encoding: Cp1251
OS name: "windows 7", version: "6.1", arch: "amd64", family: "windows"

```

Устанавливаем python, отмечаем при установке галочки добавить переменные окружения, проверяем:

```
python --version

Вывод примерно следующий
C:\Users\jyk>python --version
Python 3.6.5
```

Устанавливаем git, проверяем

```
C:\Users\jyk>git --version
git version 2.17.0.windows.1
```

Устанавливаем pytorch:
Переходим по [ссылке](https://pytorch.org/), отмечаем галочки ОС - windows, Manager - pip, Python - 3.6, CUDA - none. 
Запускаем предложенную команду в терминале, у меня это выглядит следующим образом:

```
pip3 install http://download.pytorch.org/whl/cpu/torch-0.4.0-cp36-cp36m-win_amd64.whl
pip3 install torchvision
```

### Installing

```
mkdir blackdesert-fishbot
cd blackdesert-fishbot
git clone https://github.com/Symb1OS/blackdesert-fishbot.git
```

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
