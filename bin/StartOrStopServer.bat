@echo off
rem 获取 jar 包的文件名
set JAR_NAME=..\lib\yunshu-nas.jar
rem 获取要杀掉的端口号
set PORT=8888
rem 获取占用该端口的进程 ID
for /f "tokens=5" %%i in ('netstat -ano ^| findstr :%PORT%') do set PID=%%i
rem 判断进程是否存在
if defined PID (
  rem 如果存在，杀死进程
  echo Stopping Process %PID% . . .
  taskkill /F /PID %PID%
  echo Successfully Stopped Process %PID%
) else (
  rem 如果不存在，后台运行 jar 包，并将输出重定向到日志文件
  echo Starting Web Server . . .
  javaw -jar %JAR_NAME% > output.log 2>&1
  echo Successfully Started Web Server
)
