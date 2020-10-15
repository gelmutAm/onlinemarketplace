# Online Marketplace
Marketplace web-application.

# Requirements
- JDK 8 or higher must be installed
- maven
- WildFly

# Installation
Windows:
```
git clone https://github.com/gelmutAm/onlinemarketplace.git
cd onlinemarketplace
mvn package
[PATH_TO_WILDFLY]/bin/jboss-cli.bat -c
deploy [PATH_TO_WAR] --force
```
