del maWebapp.war
rd /s /q temp
md temp
xcopy /E src\main\webapp\* temp
jar cvf maWebapp.war -C temp .