// ICustomAidlInterface.aidl
package com.srilatha.ClientSideExample;

// Declare any non-default types here with import statements
import com.srilatha.ClientSideExample.bean.UserBean;

interface ICustomAidlInterface {
    String getCurrentTime();

    void insertUser(in UserBean userBean);

    List<UserBean> getUsers();

    void clearUser();
}
