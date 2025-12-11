@echo off
echo Compiling Java program...
javac ScreenshotTool.java

echo Running program...
java ScreenshotTool %cd%\output %cd%\output\images.zip

echo Done.
pause
