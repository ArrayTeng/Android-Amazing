// IPassWordManager.aidl
package com.example.tengfei.client;


interface IPassWordManager {

   String encryption(String password);

   String decrypt(String password);
}
