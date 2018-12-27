# LocaleManager
Android Locale Manager compatible with multiple versions - change your Android App language easily

[![Build Status](https://travis-ci.com/Javinator9889/LocaleManager.svg?branch=master)](https://travis-ci.com/Javinator9889/LocaleManager)
[![Android X - latest version](https://img.shields.io/badge/Android%20X%20-1.1X%20APK-green.svg)](https://bintray.com/javinator9889/maven/LocaleManager/1.1X)
[![Android - latest version](https://img.shields.io/badge/AppCompat%20-1.1%20APK-green.svg)](https://bintray.com/javinator9889/maven/LocaleManager/1.1)

## Index

 1. [Introduction](#introduction)
 2. [Installation](#installation)
 3. [Usage](#usage)
 4. [Contributing](#contributing)
 5. [License](#license)

### Introduction
Nowadays, creating an Android application (that aspires to be popular) needs to be multilingual.
If you are an experienced developer, you may have noticed that there is no **pre-built** Android
system for changing manually the *application language*. However, from Android central they encouragingly
recommend us to **not to change the language manually**, as some incompatibilities can appear or errors.

But there is an extra feature that it is not *so much difficult* to implement and it is an extra bonus to
our actual and future users that they can switch from any language to another with just clicking a button.
For that reason, and with the intention to save developing time, I decided to create this library
that allows you to build a **simple multilingual application**, without *losing any capability*.

With just **inheriting** from some specific required classes, your application will be *fully multilingual*.
If you want to customize that settings, directly go to the [usage step](#usage) and setup this lib as you want.

### Installation
First, you must include support with **JCenter** at your own package:

#### Gradle
At your **top-level `build.gradle`**, add `jcenter()` to your repositories section:
```groovy
repositories {
    ...
    jcenter()
}
```

Then, you should be able (if there is no error) to include the repository. At your local `build.gradle`
(application level), add the following at the `dependencies` section:
```groovy
dependencies {
    // If you are deploying an Android X based application
    implementation 'com.github.javinator9889:localemanager:1.1X'

    // If you are deploying an Android AppCompat based application
    implementation 'com.github.javinator9889:localemanager:1.1'
}
```

Sync your gradle settings for applying changes and you are done!

#### Maven
At your **top-level** pom file, include the following:
```xml
<repositories>
  <repository>
    <id>central</id>
    <name>bintray</name>
    <url>http://jcenter.bintray.com</url>
  </repository>
</repositories>
```

Finally, include at your *dependencies* section one of the following declarations, depending if
you are deploying an Android app based on *Android X* or *AppCompat*:
```xml
<!-- AppCompat dependency -->
<dependency>
  <groupId>com.github.javinator9889</groupId>
  <artifactId>localemanager</artifactId>
  <version>1.1</version>
  <type>pom</type>
</dependency>
```

```xml
<!-- Android X dependency -->
<dependency>
  <groupId>com.github.javinator9889</groupId>
  <artifactId>localemanager</artifactId>
  <version>1.1X</version>
  <type>pom</type>
</dependency>
```

### Usage
As explained at the introduction, using this module is quite simple. It is as simple that
you always must follow this steps:

1. Make your `application` class inherit from `BaseApplication`. If you do not have one, just create it.
2. Each single activity you want to have a **translated one** must inherit from `BaseActivity`.
3. Each single fragment you want to have a **translated one** must inherit from `BaseFragment`.
3. Each single service (or job) you want to have a **translated one** must inherit from `BaseService` or `BaseJobService`.
4. When applying the new language, you must call to the `localeManager` object placed on your `application` class and set
the new `Language`.

By following these steps, you should be able to make your application multilingual. You can read the
[docs](https://javinator9889.github.io/LocaleManager) for getting more information about classes and their possibilities.

Now a little example for start working on your app:
```java
import javinator9889.localemanager.application.BaseApplication;

public class YourApplication extends BaseApplication {
    @Override
    @Nullable
    protected SharedPreferences getCustomSharedPreferences(@NonNull Context base) {
        // If you are planning to store the user language in a custom shared preferences, create 
        // and initialize them here.
        // If not, you can safely return "null"
        
        return base.getSharedPreferences("myPrefs", MODE_PRIVATE);
        // OR
        return null;
    }    
}

/* -------- */

import javinator9889.localemanager.activity.BaseAppCompatActivity;
import javinator9889.localemanager.utils.languagesupport.LanguagesSupport;

public class YourActivity extends BaseAppCompatActivity implements Button.OnClickListener {
    // At this activity, we will change out language by implementing an arbitrary button selector
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        /* 
        Initialize some IDs, buttons and set content view.
        */
    }
    
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // Arbitrary ID supposedly defined above
            case idEnglishButton:
                // Here we are going to change our application language to English
                YourApplication.localeManager.setNewLocale(this, LanguagesSupport.Language.ENGLISH); 
                recreate(); // Restart the activity - from API 11
                break;
            case idSpanishButton:
                // Here we are going to change our application language to English
                YourApplication.localeManager.setNewLocale(this, LanguagesSupport.Language.SPANISH); 
                recreate(); // Restart the activity - from API 11
                break;
            default:
                // Here we are going to change our application language to the Android system 
                // default
                YourApplication.localeManager.setNewLocale(this, LanguagesSupport.Language.SYSTEM); 
                recreate(); // Restart the activity - from API 11
                break;
        }
    }
}
```

As you can see, with that simple steps you can easily change dynamically your application language.
![English](/captures/english.png)
![Spanish](/captures/spanish.png)
![Russian](/captures/russian.png)
![System](/captures/system.png)

### Contributing
As you can see, developing this application is **quite simple**: the only necessary requirement 
is to understand *how Android locales works* and then just repeat the same process over and over.

If you want to contribute, I probably need your help:

+ Share and like **this repository**, so much more people can enjoy it and its benefits.
+ Comment *any bugs* or problems you have: there will be **an entire community** wanting to help 
you.
+ Commit *your own* implementations or enhancements you consider necessary - there are only a 
*few languages* supported, so you can commit the ones that are missing.

Lets make the *Android community* stronger and help the others deploying their own applications 💪

### License
 
     Copyright © 2018 - present | Javinator9889
 
     This program is free software: you can redistribute it and/or modify
     it under the terms of the GNU General Public License as published by
     the Free Software Foundation, either version 3 of the License, or
     (at your option) any later version.
 
     This program is distributed in the hope that it will be useful,
     but WITHOUT ANY WARRANTY; without even the implied warranty of
     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
     GNU General Public License for more details.
 
     You should have received a copy of the GNU General Public License
     along with this program.  If not, see https://www.gnu.org/licenses/.