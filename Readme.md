
Youtube video link that demoes the iOS app: https://youtu.be/y1ai8DL8ABM


The app uses Bank of Canada's Valet Api to get real time USD to CAD rate, whcih is slightly lower than CIBC (the bank I use) rates. But I decided not to calibrate the data and use the rates received from valet api when converting the currencies as the conversion is meant to give an estimate.

The Miles to Km functionality was added as my car is American where they use miles. However I use gas buddy to keep track of my car's fuel economy and I prefer the liter/100km unit, and so the gas buddy app requires odometer reading in kms. So whenever i use the gas buddy app, i have to open up my chrome browswer and input it and wait, which is just too much work for such a simple conversion. That is why I added that functionality so that instead of going through all the hassle, i can quickly convert between units.

Similarly I use recipes that my mom sent me (with videos of how to prepare the food) and she uses kgs. However the meat shop I buy meat from uses lbs. So that is why I decided to add that conversion functionality to quickly convert between units.

The CAD to BDT(Bangladesh Taka) functionality is not implemented as I am looking for a free api to get real time currency conversion that converts any unit to BDT (My plan was to use USD to BDT and then do some calculation based on USD to CAD rate to get CAD to BDT rate if I cannot find direct CAD to BDT rate but I also couldn't find any free api that converts USD to BDT as well)

The Android folder contains the android app and the backend folder contains the node.js app that is hosted on a digital ocean droplet. I usually use a Samsung Galaxy s20 Plus, Lg G5 and an iPhone XS Max on daily basis, and plan to make a native iOS app and react based progressive web app in the future, so I decided to use the node.js app to sync the notes between devices. I could have used shared preference or sql lite database on android devices but in my testing the app was really fast to fetch the data from the backend, so I decided to keep it as it is.

In future I plan to include other functionalities such as remotely monitor my room temparauture using an arduino based room temparature and humidity sensor( I plan to use DHT22 sensor for this project), remotely lock/unlock my door using an arduino and servo motor and finally turn on/off my portable air conditioner remotely if the room temp is too hot again using an arudino based claw.
