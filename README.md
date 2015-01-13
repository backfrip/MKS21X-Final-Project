# MKS21X Final Project
**_Team Force Lightning - JE & ES_**

![alt text](http://i756.photobucket.com/albums/xx204/tsuzuki31/lightning01.gif)


### Abstract
##### Super Smash Bros. for Atari 2600
A crossover fighting game type program based on the legendary Atari 2600 Video Computer System and the popular Super Smash Bros. video game series.

### Compile & Run Intructions
##### Eclipse
  1. Run Eclipse
  2. In the File menu, select `Import... > General > Existing Projects into Workspace`
  3. Set root directory to MKS21X-Final-Project repository
  4. Make sure "Copy projects into workspace" is unchecked
  5. Run project (`Run > Run`)

##### Command Line
  1. Navigate to MKS21X-Final-Project directory
  2. Compile with: `javac -d bin -cp "lib/*" -sourcepath src src/main/Main.java`
  3. Using Java 7 or later, run with: `java -cp "lib/*;bin" main.Main`  
  Using Java 6 or earlier, run with: `java -cp "lib/*:bin" main.Main`


## Changelog

### [Version 0.1.3.2.2](#) (2015-01-13)

#### Fixes
  - Fixed splash briefly flashing before fading in & out

### [Version 0.1.3.2.1](https://github.com/backfrip/MKS21X-Final-Project/commit/cb68b2582b1249c88045973ad7d267761b0dad6b) (2015-01-12)
  - Added all 4 buttons to main menu (layout still unfinished; currently looks terrible)

### [Version 0.1.3.2](https://github.com/backfrip/MKS21X-Final-Project/commit/7bd1901670f3f7925ee84adb807245380e21f75a) (2015-01-12)
  - Turned text buttons into ImageButtons

### [Version 0.1.3.1](https://github.com/backfrip/MKS21X-Final-Project/commit/dee9063deeb3b3a5f87b99e1337fa9843809ca60) (2015-01-12)
  - Adjusted Random implementation (to be readded later)
  - Added compile instructions for Java 6
  - Made splash skippable (as long as you press the SECRET key!!!! [*it's escape*])

### [Version 0.1.3](https://github.com/backfrip/MKS21X-Final-Project/commit/02f8df22b6b9563775528f14b8e7cf44a858384a) (2015-01-11)
  - Added TweenEngine to lib
  - Changed resolution to 1280x720 because that's a real aspect ratio

##### New Features
  - Splash screen now shows the Force Lightning logo and plays music
  - With the help of TweenEngine, it also fades in and out!
  - Added a secret splash (has a 1 in 200 chance of playing instead of the regular splash)

### [Version 0.1.2.4](https://github.com/backfrip/MKS21X-Final-Project/commit/b342dbae20d4e3d3c4e7ff590aba1fc631b706a6) (2015-01-10)

##### New Features
  - Added second menu theme (the new theme and the old theme each have a 50% chance of playing)

### [Version 0.1.2.3](https://github.com/backfrip/MKS21X-Final-Project/commit/824ef43d18ffd285f5f5e420447ee43fbb27fe15) (2015-01-10)

##### New Features
  - Added another button
  - Theme now static, carries between screens

### [Version 0.1.2.2](https://github.com/backfrip/MKS21X-Final-Project/commit/191670ba7a643c5a4e2c071fed24c4abee2a6d66) (2015-01-10)
  - Temporary game exit text button test 

### [Version 0.1.2.1](https://github.com/backfrip/MKS21X-Final-Project/commit/196561b86721f34f38bcc434bd2becf5fd78c69c) (2015-01-10)

##### New Features
  - Implemented blinking text on title screen (When a button is pressed, it flashes rapidly)

### [Version 0.1.2](https://github.com/backfrip/MKS21X-Final-Project/commit/d378261cf6fc71dba763e795e78cf2fd3753f043) (2015-01-10)

##### New Features
  - Implemented Splash Screen, Title Screen, Main Menu sequence at application startup

### [Version 0.1.1](https://github.com/backfrip/MKS21X-Final-Project/commit/dd943d68f564bad83cc5ebe4f8ae6b3065678292) (2015-01-10)
  - Added and cleaned up icon code
  - Switched out test_splash.jpg
  - Added compilation instructions to README

##### New Features
  - Window icon

### [Version 0.1.0](https://github.com/backfrip/MKS21X-Final-Project/commit/9135c2b299fd3b94ebcdbacf50b90805b78bc37c) (2015-01-09)
  - Wrote Main class to open Smash (Game class)
  - Began writing Smash (Game class), currently loads Splash screen
  - Wrote temporary Splash screen
  - Began writing Menu (Main menu) screen

##### New Features
  - Opens to splash screen of anticlimatic lightning JPEG

### [Version 0.0.5](https://github.com/backfrip/MKS21X-Final-Project/commit/8d0ef165126a7f37c35f1a590da2deb4f1826309) (2015-01-08)
  - Added libGDX classes in 'lib' directory

### [Version 0.0.4](https://github.com/backfrip/MKS21X-Final-Project/commit/195a567ed93dc07dfc37ddac274189eb693c5c04) (2015-01-05)
  - Revised readme to be more legit
  - That's a really bad version comment, so I'll be more specific
  - What I did was update the readme to keep it in accordance with our decided project. 

### [Version 0.0.3](https://github.com/backfrip/MKS21X-Final-Project/commit/38d7fbbf9e4014e987aaaed95ad5cd885b255948) (2014-12-23)
  - Added basic .gitignore from E's APCS directory
  - Crossed out ideas

### [Version 0.0.2.1](https://github.com/backfrip/MKS21X-Final-Project/commit/15df200cd690d200ae1cc20fe3cd80e8bdadc5b6) (2014-12-22)
  - The word "format" was bugging me and I wanted to make another commit anyways

### [Version 0.0.2](https://github.com/backfrip/MKS21X-Final-Project/commit/7b5023ac461ca9d7c9e6357520921f2d0307a9d5) (2014-12-22)
  - Changed date ~~format~~ style in Changelog
  - Began adding commit links on previous versions  
    Ideally, this should be done whevever a new official version is added  
    _(FORMATTING NOTE: Two trailing spaces allow line breaks within list elements)_

### [Version 0.0.1.1](https://github.com/backfrip/MKS21X-Final-Project/commit/50b2475dd54e154fd75b9c9cf949b935a2546642) (2014-12-22)
  - Removed 'brainstorm'

### [Version 0.0.1](https://github.com/backfrip/MKS21X-Final-Project/commit/bb974cf5a54ec30eb16460d5ee0751c9a0eb74c3) (2014-12-22)

##### New Features
  - [README](README.md) updated *(not really a feature)*

##### Bugfixes
  - No problems reported; this category just for show
