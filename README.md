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
