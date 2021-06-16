package com.srilatha.ClientSideExample.base;

import android.os.Bundle;

public interface IBaseView {
    int getContentLayout();

    void initView();

    void initData(Bundle bundle);
}
