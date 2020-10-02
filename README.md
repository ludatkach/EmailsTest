# EmailsTest
## Task
1. Open browser and go to [getnada](https://getnada.com)
2. Generate new email address and save it
3. Get random API links from [RandomCat](https://aws.random.cat/meow), [RandomDog](https://random.dog/woof.json), [RandomFox](https://randomfox.ca/floof/)
4. Send email with these three links from gmail account to email from Step1 (getnada.com)
5. Wait until a letter come to [getnada](https://getnada.com) and check if it has three urls
6. Click on each link in email, make screenshot of each image and save it to some files

## Technologies
The project created using Java13, Selenium WebDriver and JUnit5 

## Launch
- Install Eclipse
- Install Java13
- Install JUnit5 modules: JUnitPlatform, JUnitJupiter
- Install Maven


## Run
- Create gmail test account
- Define the folloving environment variables:
	GMAILUSERNAME, 
	GMAILUSERPASSWORD
- To run tests: 
	-- Right-click on project => Run As > JUnit test