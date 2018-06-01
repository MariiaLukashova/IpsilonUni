Предварительно на компьюторе должны быть установлены: JDK и Maven

1 способ. Запуск тестов с командной строки
1. Загрузить проект из репозитория https://github.com/MariiaLukashova/IpsilonUni.git
2. Открыть командную строку, перейти в папку с загруженным проектом
3. Ввести команду mvn test, подождать сборки проекта и наблюдать выполнение тестов

2 способ. Запуск тестов удаленна с помощью Jenkins
1. Установить Jenkins
2. Открыть страницу в браузере http://localhost:8080
3. Перейти в Settings Jenkins-Manage Plugins
4. Установить плагины: Git, GitHub plugin, Maven Integration plugin
5. Перейти в Settings Jenkins-Configuration System
6. Прописать пути к установленным на компьютере JDK и Maven
7. Нажать в главном меню New Item, ввести имя IpsilonUni и выбрать New project maven-OK
8. В настройках оставить галочку напротив Allow task to run in parallel
9. В блоке Source Code Management выбрать Git и ввести в поле Repository URL https://github.com/MariiaLukashova/IpsilonUni.git
10. В блоке Build Triggers выбрать Run periodically и ввести 
TZ=Europe/Saratov
0 0 * * 1-5
Это поставит расписание запуска сборки кадый будний день в полноч по Саратову
11. В блоке Assembly в поле Goals and options ввести test
12. Сохранить настройки
13. После каждой сборки отчет о результатах можно найти в файле index.html, 
по адресу %JENKINS_HOME_PATH%\workspace\IpsilonUni\test-output\html