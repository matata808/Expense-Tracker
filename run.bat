@echo off
cd /d "C:\Users\fardy\Expense-Tracker"

for %%f in ("target\expense-tracker-*-SNAPSHOT.jar") do (
    java -jar "%%f" %*
    exit /b %errorlevel%
)

echo No expense-tracker jar found in target.
exit /b 1