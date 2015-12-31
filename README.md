# language-reset
This is simple and stupid Android tool that resets the OS language to EN.

It's intended to help with the awful Marshmallow bug (https://code.google.com/p/android/issues/detail?id=191068) that occurs on the Nexus Player.


After installing the app, you need to run this command: (adb needed)

``adb shell pm grant com.example.langfuck android.permission.CHANGE_CONFIGURATION``
