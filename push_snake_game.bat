@echo off
rem Ensure we are in the correct directory
cd /d "D:\MS AI\JAVA\SnakeGameRecitation"
echo Starting the push process to GitHub...

echo.
echo 1. Adding files to staging area...
git add .
if %ERRORLEVEL% NEQ 0 (
  echo Error: Failed to add files. Do you have git installed?
  pause
  exit /b %ERRORLEVEL%
)

echo.
echo 2. Committing changes...
git commit -m "Add Initial Project Files and README"
if %ERRORLEVEL% NEQ 0 (
    echo Note: If it says 'nothing to commit', that is okay. Proceeding...
)

echo.
echo 3. Pulling remote changes to merge (if any)...
git pull origin main --allow-unrelated-histories
if %ERRORLEVEL% NEQ 0 (
  echo Warning: Pull failed or had conflicts. Please check manually if needed.
)

echo.
echo 4. Pushing to GitHub...
git push -u origin main
if %ERRORLEVEL% NEQ 0 (
  echo Error: Push failed. Check your internet connection or if the remote URL is correct.
  pause
  exit /b %ERRORLEVEL%
)

echo.
echo ==========================================
echo SUCCESS! Your code is now on GitHub.
echo You can check it at: https://github.com/LuminarySam30/Snake-Game
echo ==========================================
pause
