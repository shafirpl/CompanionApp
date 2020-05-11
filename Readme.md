Youtube video link that demoes the app: https://youtu.be/3ghjt1lS7r0

The app uses Bank of Canada's Valet Api to get real time USD to CAD rate, whcih is slightly lower than CIBC (the bank I use) rates. But I decided not to calibrate the data and use the rates received from valet api when converting the currencies.

The Android folder contains the android app and the backend folder contains the node.js app that is hosted on a digital ocean droplet. I usually use a Samsung Galaxy s20 Plus, Lg G5 and an iPhone XS Max on daily basis, and plan to make a native iOS app and react based progressive web app in the future, so I decided to use the node.js app to sync the notes between devices. I could have used shared preference or sql lite database on android devices but in my testing the app was really fast to fetch the data from the backend, so I decided to keep it as it is.

In future I plan to include other functionalities such as remotely monitor my room temparauture using an arduino based room temparature and humidity sensor( I plan to use DHT22 sensor for this project), remotely lock/unlock my door using an arduino and servo motor and finally turn on/off my portable air conditioner remotely if the room temp is too hot again using an arudino based claw.
