package com.zeroxuan.fragmenttest;

import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment implements OnBackPressed {

    /**
     * fragment 中的返回键
     * 默认返回 false,交给Activity 处理
     * 返回 true:执行fragment中需要执行的逻辑
     * 返回 false:执行Activity中的onBackPressed
     */
    @Override
    public boolean onBackPressed() {
        return false;
    }
}
