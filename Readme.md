
iOS App Demo Video: https://youtu.be/y1ai8DL8ABM

Android App Demo Video: https://youtu.be/08GGx9-gkDE


The app uses Bank of Canada's Valet Api to get real time USD to CAD rate, whcih is slightly lower than CIBC (the bank I use) rates. But I decided not to calibrate the data and use the rates received from valet api when converting the currencies as the conversion is meant to give an estimate.

The Miles to Km functionality was added as my car is American where they use miles. However I use gas buddy to keep track of my car's fuel economy and I prefer the liter/100km unit, and so the gas buddy app requires odometer reading in kms. So whenever i use the gas buddy app, i have to open up my chrome browswer and input it and wait, which is just too much work for such a simple conversion. That is why I added that functionality so that instead of going through all the hassle, i can quickly convert between units.

Similarly I use recipes that my mom sent me (with videos of how to prepare the food) and she uses kgs. However the meat shop I buy meat from uses lbs. So that is why I decided to add that conversion functionality to quickly convert between units.


The Android folder contains the android app, the iOS folder contains the iOS app, the dekstop folder contains the SysTop desktop app  and the backend folder contains the node.js app that is hosted on a digital ocean droplet. I usually use a Samsung Galaxy s20 Plus, Lg G5 and an iPhone XS Max on daily basis, so I decided to use the node.js app to sync the notes, ip addresses as well as car maintenance records among all of my devices.
