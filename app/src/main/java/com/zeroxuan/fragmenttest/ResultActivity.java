package com.zeroxuan.fragmenttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

public class ResultActivity extends AppCompatActivity
        implements ResultFragment.ReturnResultToActivityListener {

    public static final String RESULT_FRAGMENT_RESULT = "result_fragment_result";
    private static final String TAG = "yj ResultActivity";
    private int resultFragmentResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent data = getIntent();
        int editA = data.getIntExtra(MainFragment.ARG_EDIT_A, 0);
        int editB = data.getIntExtra(MainFragment.ARG_EDIT_B, 0);
        int editResult = data.getIntExtra(MainFragment.ARG_EDIT_RESULT, 0);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag("ResultFragment");
        if (fragment == null) {
            fragment = ResultFragment.newInstance(editA, editB, editResult);
            getSupportFragmentManager().beginTransaction()
                                       .add(R.id.result_container, fragment, "ResultFragment")
                                       .commit();
        }
        else {
            ((ResultFragment) fragment).setArg(editA, editB, editResult);
        }


    }


    @Override
    public void returnResult(int result) {
        resultFragmentResult = result;
        setResult();
        finish();
    }


    @Override
    public void onBackPressed() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();

        for (Fragment f : fragments) {
            if (f != null && f instanceof BaseFragment) {
                ((BaseFragment) f).onBackPressed();
            }
        }
        Log.e(TAG, "onBackPressed: ");
        setResult();
        super.onBackPressed();
    }

    void setResult() {
        Intent intent = new Intent();
        intent.putExtra(RESULT_FRAGMENT_RESULT, resultFragmentResult);
        setResult(RESULT_OK, intent);
    }
}
