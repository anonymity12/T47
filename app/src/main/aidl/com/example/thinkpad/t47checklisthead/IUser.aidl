// IUser.aidl
package com.example.thinkpad.t47checklisthead;

// Declare any non-default types here with import statements

interface IUser {
   boolean login(String userName,String userPwd);
   void logout(String userName);
}
