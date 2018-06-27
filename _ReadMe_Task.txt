
# The Task:
---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------
We hope you are able to implement this home task and publish the source code of the app in a public GIT repo. 
Please, send the link to the GIT repo to us and Timo Kaulio (timo.kaulio@nordea.com) latest by 28th June. 
Please, include also a brief written explanation of your solution in your email.
 
Assignment details:

a) - Create Selenium Web browser tests using Java language for amazon.com with following details:
b) - Search Nikon 
c) - and sort results from highest price to slowest.
d) - Select second product 
   - - The newly implemented ( since Selenium 3.11 ) auto-wait is definitely NOT working.
   - - Greatly missing following methods:
   - - - waitForElementPresent
   - - - clickAndWait
   - - - waitForValue
   - - - waitForPageToLoad
   - - - https://www.ultimateqa.com/selenium-3-11-released/
   - - - https://github.com/SeleniumHQ/selenium-ide/issues/157
e) - and click it for details.
f) - From details check (verify with assert) that product topic contains text “Nikon D3X”
   - - It does not. My search history influences the results from amazon, so I'm assuming you're interested in seeing an assert whether it fails or not.
 
Bonus points will be given from:
- Creating cucumber scenario which is used for test execution/test step mapping.    - Check
- Implementing the webpage opening step so that Url is parameter                    - Check
- Test is implemented as Maven project                                              - Check



# Notes:
---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------
1) Download and install Java - incl. updating PATH
	- 'C:\Program Files\Java\jdk1.8.0_162'
2) Download and install Maven - incl. updating PATH
	- 'C:\Program Files\Apache\maven\apache-maven-3.5.3'
3) 	Download and Install Eclipse ( Oxygen.3 Release (4.7.3) )
4) 	Download needed Selenium files
	selenium-server-standalone-3.12.0.jar
		- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-server/3.12.0
	selenium-java-3.12.0
		- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/3.12.0
5) Create git lib for project
6) Create Maven project:
	mvn archetype:generate "-DgroupId=com.balkit.amazon" "-DartifactId=amazon-test" "-DarchetypeArtifactId=maven-archetype-quickstart" "-DinteractiveMode=false"
	- Apparently quotation marks are needed though Maven documentation says otherwise.
7) Create Eclipse project
	- import Project from File system or Archive
8) Include Maven dependencies
	- selenium-server
	- - https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-server/3.12.0
	- selenium-java-3.12.0
	- - https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/3.12.0
9)	Install Cucumber for Eclipse	
	- Market place didn't work
	- Install new software ... - from link didn't work
	- Downloaded from Github 


# Comments to solution:
---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------
Thank you for the home task here it definitely required some reading as I wasn't familiar with neither Cucumber	or initial Maven setup ( I was an ANT guy ).
I chose to use Eclipse over Intellij IDEA as I am more familiar with Eclipse though Intellij seems to have way better IntelliSense.

I have created a pure Selenium part and Cucumber version of the Selenium part.
For both solution the geckodriver needs to be changed accordingly as the path is hardcoded right now.
There is a geckodriver available in the 'Extras' folder, if you don't already have one locally.

I ran into the unexpected issue that my Amazon searches differs from PC to PC and could not find a 'Nikon D3X' unless I search for it specifically, 
so as mentioned above I am assuming you are interested in seeing an assert whether it fails or not.
In my setup:
			TEST OK				{ "http://www.amazon.com", "800mm" },
			ASSERT FAILED		{ "http://www.amazon.com", "Nikon D3X" },
			Dropdown NotFound	{ "http://www.amazon.de", "Nikon D3X" },

/AmazonTask/src/test/java/com/balkit/amazon/SeleniumAmazonTest.java
---------------------------------------------------------------------
Initially I created /AmazonTask/src/test/java/com/balkit/amazon/SeleniumAmazonTest.java which is a Selenium test without Cucumber but with multiple parameters, as I could not find the requested "Nikon D3X".
I chose to keep this file in the solution for the sake of potential discussion.

Cucumber solution
---------------------------------------------------------------------
Fortunately I managed to find some time to look into Cucumber as well and I put that solution here:
- /AmazonTask/src/test/java/features/amazon.feature
- /AmazonTask/src/test/java/runner/AmazonTest.java
- /AmazonTask/src/test/java/seleniumglue/AmazonRunSteps.java 

I have implemented the test steps to match the Assignment details:
  Scenario: Search Amazon website for Nikon D3X
    Given user goes to "amazon.com"
    When user searches for Nikon product
    And user sorts results from highest price to lowest
    And user selects second product from top
    And user clicks it for details
    Then product containing Nikon D3X is found

Overall feeling:
- I wasted a significant amount of time on adjusting the setup as 
- - 1) The combination of Selenium 3.11 deprecated the wait methods and Selenium 3.12's auto-wait didn't always work accordingly forcing use of WebDriverWait.
- - 2) Cucumber for Eclipse didn't play nicely. Multiple restarts required.
- - 3) Surprising need for cucumber-java8 is and not cucumber-java when using Java 8.
- - 4) I tried to tweak my amazon searches to find the "Nikon D3X" but eventually gave in and I expect that a failing assert show you just as much as a positive assert.



 