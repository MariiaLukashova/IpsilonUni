�������������� �� ���������� ������ ���� �����������: JDK � Maven

1 ������. ������ ������ � ��������� ������
1. ��������� ������ �� ����������� https://github.com/MariiaLukashova/IpsilonUni.git
2. ������� ��������� ������, ������� � ����� � ����������� ��������
3. ������ ������� mvn test, ��������� ������ ������� � ��������� ���������� ������

2 ������. ������ ������ �������� � ������� Jenkins
1. ���������� Jenkins
2. ������� �������� � �������� http://localhost:8080
3. ������� � Settings Jenkins-Manage Plugins
4. ���������� �������: Git, GitHub plugin, Maven Integration plugin
5. ������� � Settings Jenkins-Configuration System
6. ��������� ���� � ������������� �� ���������� JDK � Maven
7. ������ � ������� ���� New Item, ������ ��� IpsilonUni � ������� New project maven-OK
8. � ���������� �������� ������� �������� Allow task to run in parallel
9. � ����� Source Code Management ������� Git � ������ � ���� Repository URL https://github.com/MariiaLukashova/IpsilonUni.git
10. � ����� Build Triggers ������� Run periodically � ������ 
TZ=Europe/Saratov
0 0 * * 1-5
��� �������� ���������� ������� ������ ����� ������ ���� � ������ �� ��������
11. � ����� Assembly � ���� Goals and options ������ test
12. ��������� ���������
13. ����� ������ ������ ����� � ����������� ����� ����� � ����� index.html, 
�� ������ %JENKINS_HOME_PATH%\workspace\IpsilonUni\test-output\html