@echo off
cd /d "%~dp0"
java -cp ".;GymManagementSystem.jar;../lib/mysql-connector-j-9.7.0.jar;../src" LoginPage
pause