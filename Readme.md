Problem Summary
---
There is no ruler in the universe of Southeros and pandemonium reigns. Shan, the gorilla king of the Space kingdom
wants to rule all Six Kingdoms in the universe of Southeros.

He needs the support of 3 more kingdoms to be the ruler.
Each kingdom has an animal emblem and Shan needs to send a message with the animal in the message to win them over.

Challenge is to have King Shan send a secret message to each kingdom and win them over.

Problem source: `GeekTrust.in` [Golden Crown/Tame of Thrones](https://www.geektrust.in/coding-problem/backend/tame-of-thrones)

Solution Description
---
Interface `KingdomService` holds the core service of deciphering secret message with that kingdom's attributes like `cipher key` which is implemented by kingdom classes.

Abstract class `Kingdom` defines basic attributes for a kingdom which has to be extended by every kingdom's sub class and implement them accordingly. 

`Controller` is the driver class which holds the core implementation methods like `validateMessage` and `buildOutput`. 

`KingdomLookup` holds the instances which is called by the controller to set the service type according to the kingdom passed.

`DecipherMessage` class holds method which is used to decode the secret message sent by a kingdom to other kingdoms and retrieve their responses.

Allied kingdoms stored in unique `ArrayList` of kingdom sending the secret message to build final output.

Test Cases written for positive and negative response and for invalid input in `GeekTrustTest` class. 

How to run
---
Run `main` method in `Geektrust` class.
Pass absolute path to `input` text file as argument containing kingdom name and secret message.
Refer `guide` from problem source for input examples.
