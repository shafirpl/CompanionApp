
iOS App Demo Video: https://youtu.be/y1ai8DL8ABM

Android App Demo Video: https://youtu.be/08GGx9-gkDE

SysTop Desktop app running on Mac Demo: https://youtu.be/o2Gcwq1cKbs

For packaging the SysTop desktop app, open up a terminal or command shell, cd into the Desktop_App/sys-top folder and type either "npm run package-mac" for mac os or "npm run package-win" for windows. The command should create a new folder called "release-builds" inside which the packaged app can be found.


I made the intial version of the desktop app as a code along project while undertaking Brad Traversy's Udemy Electron course (https://www.udemy.com/course/electron-from-scratch/), and then added more functionalities on top of the initial version. The added functionalities included: adding a node.js server so that the stats can be remotely monitored from the mobile app, adding more info about system memory (such as the memory usage and memory free stats, total memory etc) as well as info about the ip address of the computer running the app so that the computer can be easily added to the mobile app for remote monitoring.


The app uses Bank of Canada's Valet Api to get real time USD to CAD rate, whcih is slightly lower than CIBC (the bank I use) rates. But I decided not to calibrate the data and use the rates received from valet api when converting the currencies as the conversion is meant to give an estimate.


The Android folder contains the android app, the iOS folder contains the iOS app, the dekstop folder contains the SysTop desktop app  and the backend folder contains the node.js app that is hosted on a digital ocean droplet. I usually use a Samsung Galaxy s20 Plus, Lg G5 and an iPhone XS Max on daily basis, so I decided to use the node.js app to sync the notes, ip addresses as well as car maintenance records among all of my devices.
