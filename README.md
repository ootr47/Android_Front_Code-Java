# 안드로이드 코드 입니다.. Firebase 파일은 신경 안쓰셔도 됩니다.

* settings.gradle
```Java
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven { url "https://jitpack.io" }
        maven {
            url 'https://naver.jfrog.io/artifactory/maven/'
        }

    }
}
rootProject.name = "naver_map_test"
include ':app'
```

* build.gradle(Module)
```Java
plugins {
    id 'com.android.application'
}

android {
    compileSdk 32

    defaultConfig {
        applicationId "com.example.naver_map_test"
        minSdk 26
        targetSdk 32
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}

dependencies {

    //Naver map api
    implementation 'com.naver.maps:map-sdk:3.14.0'

    //Naver map FusedLocationSource
    implementation 'com.google.android.gms:play-services-location:19.0.1'


    //Retrofit connection
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.8.2'

    // flexbox-layout
    implementation 'com.google.android.flexbox:flexbox:3.0.0'

    implementation 'androidx.appcompat:appcompat:1.4.1'
    implementation 'com.google.android.material:material:1.5.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.3'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    implementation 'nl.joery.animatedbottombar:library:1.1.0'
}
```

* gradle.properties 추가

```Java
android.useAndroidX=true
android.enableJetifier=true
```


* AndroidManifest.xml 추가 YOUR_CLIENT_ID에는 네이버에서 발급받은 Client_ID

```Java
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.naver_map_test">

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.Naver_map_test">

        <meta-data
            android:name="com.naver.maps.map.CLIENT_ID"
            android:value="YOUR_CLIENT_ID" />

        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```

