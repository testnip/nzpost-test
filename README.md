# How to run
## Run from IntelliJ
### Run a test suite
1. Right click on `nz.co.nzpost.automation.CucumberRunner`.
2. Select `Run CucumberRunner`. 

To target the set of test suite you would like to run, you can do the following in CucumberRunner options:
* Specify tags to target features or scenarios tagged with that specified tags e.g. @ui, @api, ~@wip.
* Point feature to either a directory or a file.

### Run a feature
* Right click on a feature file under `src/main/resources/features` then select Run.
* The first run it will fail since it was probably using `cucumber.api.cli.Main`. To fix this simply open up configuration
and remove `cucumber.api.spring` in the **Glue** field.

### Run a specific scenario
Same process as running a feature.

### Run different browsers
Put this in VM Arguments field and replace <path to project> with correct path
#### Chrome
```
-Dselenide.browser=chrome -Dwebdriver.chrome.driver=<path to project>/src/test/resources/drivers/chromedriver
```

#### Firefox
```
-Dselenide.browser=firefox -Dwebdriver.gecko.driver=<path to project>/src/test/resources/drivers/geckodriver
```

#### Safari
1. Turn on Safari/Developer/Allow Remote Automation on Safari Browser.
2. Run with argument below 
```
-Dselenide.browser=safari
```

#### Saucelabs
-Dcloud.enabled=true -Dspring.profiles.active=dev

## Run from command line
```
$ mvn clean verify
```

### Run different browsers
#### Chrome
```
$ mvn clean verify
```

#### Firefox
```
$ mvn clean verify -Pfirefox
```

#### Safari
```
$ mvn clean verify -Psafari
```

#### Saucelabs
```
$ mvn clean verify -Pcloud
```

# Saucelabs 
## Test Configuration Options
https://wiki.saucelabs.com/display/DOCS/Test+Configuration+Options

## Test Configuration Console
https://wiki.saucelabs.com/display/DOCS/Platform+Configurator#/

## Sauce Connect
https://wiki.saucelabs.com/display/DOCS/Basic+Sauce+Connect+Proxy+Setup

Remember to use localhost once sauce connect is enabled

## Define cucmber options in commandline
```
-Dcucumber.options='--tags @now'
```
Ref: https://docs.cucumber.io/cucumber/api/