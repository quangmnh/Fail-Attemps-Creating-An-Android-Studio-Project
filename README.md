# Fail-Attemps-Creating-An-Android-Studio-Project
My struggle creating a working project in Android Studio 3.0.1. Fuk u Android Studio

# Every fuking day passes and i keep losing my mind.

#  Test 01
First created with a lot of error, dem, without C++ support.

# Test 02
Created with nothing, no activity, can build but what i should do then???

# Test 03
Created with a basic activity or something. A bunch of errors

> Error:(9, 5) error: resource android:attr/dialogCornerRadius not found.

It seems this error comes from mismatched compileSdkVersion.
as the dependencies in **build.gradle (Module: app)** also gives the error:

> com.android.support libraries must use the exact same version specification (mixing versions can lead to runtime crashes). Found versions 28.0.0, 26.1.0. Examples include com.android.support:animated-vector-drawable:28.0.0 and com.android.support:design:26.1.0

Some bros suggest changing version in **Project Structure** but another error detected

> NDK does not contain any platforms

So the thing is I clicked the install NDK as soon as i saw the pop-up and it automatically installed latest NDK version, which doesn't contain a platform folder???? Anw, installing NDK version 20.0.5594570 or around that does seem to solve the problem. I install the CMake thing too, hopefully it will help me through some other troubles.

Don't forget to change the ndk path in **File/Project Structure

Then there is this shite

> Error:No toolchains found in the NDK toolchains folder for ABI with prefix: mips64el-linux-android

Tried created a folder named **mips64el-linux-android classpath** in ndk-budle/toolchains folder. **Not working**

Change to this line in build.gradle 'com.android.tools.build:gradle:3.2.1' 
Still error.
So let's try downgrade the ndk thing to 19.xxxxxx 

**No good**

Then I got an older version of **Android NDK** (16b) and get the **mips64el-linux-android** toolchain to by pass the error. **It works**.
 
 **The thing is**, just **copy** the **mips64el-linux-android** to toolchains folder of ndk and ndk-budle to guaranteed build.
 
 One problem solved so let's move on to test 04 and tracesback to the error...
 
 # Test 04
 
 It says something like 
 > <!-- We can use the platform styles on API 28+ -->
 
 So I change compile SDK version in Project Structure to **API 28** and **build tool 30.0.3**, as well as source & target compaiblity as **1.8**

**It sucks**. I don't like this solution at all.

So let's get to test 05 and try something else.

# Test 05
I love **C++** so let give it a try.
then there is this shite
>Error:Error occurred while communicating with CMake server. 
It gets sth to do with CMake, version concern again.

Re-install it with the **3.6.4111459** does the trick.

# Test 06
Let's try Mr.Thien tutorial.

Everything seem to work as expected, except that he somehow miraculously bypasses all those errors without doing anything specific. So i still resort to solve the problems as above.

He also mentions crating a virrtual device for testing. I choose to create a **Google Pixel 2** device and **API 28**. It works and we finally reach the first stage of Programming - **Hello World**. But, **as expected**, errors come and laugh at me.

> Emulator: handleCpuAcceleration: feature check for hvf
> Emulator: added library vulkan-1.dll

The solution on developer.android.com is a **wall of text** and also a pain in the @ss to read.

# Test 07

Calculator exampler by Mr. Nhan
Set compileSDKversion in guild gradle to 28
Theme error: android:theme="@style/AppTheme"> in android manifest file
Ctrl+click it get me to 
Add Base.
>style name="AppTheme" parent="Base.Theme.AppCompat.Light.DarkActionBar"
fracment????

Intentional errror for the app to reset??
 uses permission befor application in manifest
 Java inheritence:
     + Extend: inherit only one parent #overide Ctrl+O -

XML: 
     +LinearLayout: android:orientation="vertical" 
     +Child layout: 

<<<<<<< Updated upstream
# Test 08

Lab 2 by Mr. Thien
Simple add function, nothing special.

# Test 07 Revisited

I try to work out how to do Android Studio in Ubuntu environment.

Tutorial is clear enough, nothing special happened.

**Except**, as expected, something odd with the AVD.
>grant current user access to /dev/kvm

No further instruction given, fuk. 

So, the solution. First, install qemu-kvm
>sudo apt install qemu-kvm

>ls -al /dev/kvm

Root should be the user, group kvm.
>grep kvm /etc/group

The return message should be like
>kvm: x :127:

No user in kvm group, hence the AVD begged us to grant user to the group.
>sudo adduser $USER kvm

This should do the trick.

Check again
>grep kvm /etc/group

Your user name should be there.

Log out and log in again for the change to take effect.
=======
# Test 09
UART/thingspeak communication
LeanBack UI




# Test 10

MQTT---MQTT broker check for allsubscribed clients. (for a topic/feed)

Client ID should be phone series + key word -->MD5

This is a sample code for MQTT server connection.

Till this day, everything works as expected. I feel like i can do anything now.
>>>>>>> Stashed changes
