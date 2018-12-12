// ISecurityCenter.aidl
package com.example.tengfei.androidcrossprocess;


interface ISecurityCenter {

   String encrypt(String content);

   String decrypt(String password);
}
