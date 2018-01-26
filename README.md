# Primitive FX MVC

![](https://img.shields.io/badge/groupId-com.erayerdin-blue.svg?style=flat-square)
![](https://img.shields.io/badge/artifactId-primitivefxmvc-blue.svg?style=flat-square)
![](https://img.shields.io/badge/version-0.2.1--alpha-red.svg?style=flat-square)
[![](https://img.shields.io/badge/license-Apache2-333333.svg?style=flat-square)](LICENSE)

PFXMVC is a library which has a primitive MVC specifications and
a couple of units which provide a quick start to a JavaFX project.

## Installing

### Maven
You can get installation guide [here](https://jitpack.io/#erayerdin/primitivefxmvc).
If you are not good with Jitpack, add below to your repositories in `pom.xml` file:

	<repositories>
	    <!-- Your other repositories if exists -->
	
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
	
And add the dependency as following:

    <dependencies>
        <!-- Your other dependencies -->
    
        <dependency>
            <groupId>com.github.User</groupId>
            <artifactId>Repo</artifactId>
            <version>LATEST</version>
        </dependency>
    </dependencies>
    
You can change `version` if you wish to use a specific one, but you
can also use `LATEST` to get the latest version.

## Documentation

In progress... There will be pieces of video tutorials or blog articles
until then and I will probably update here.

## When to Use This Library

 - When you want to make a quick start
 - When you have a personal project (suggested)

## When Not To Use This Library

 - When you aim to utilize dependency management and inversion of control approaches in your project
 - When you want to use POJOs... This library heavily contains units that is required to be implemented or extended

## Criticism

This library might not hold the sense of classical MVC pattern because
it is aimed to implement MVC pattern *in sense of* JavaFX. For instance,
*controller*, in this case, is aimed to extend the functionalities of
classical JavaFX controller.