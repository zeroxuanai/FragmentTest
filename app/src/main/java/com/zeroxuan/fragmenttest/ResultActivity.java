package com.zeroxuan.fragmenttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity
        implements ResultFragment.ReturnResultToActivityListener {


    public static final String RESULT_FRAGMENT_RESULT = "result_fragment_result";
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
        finish();
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra(RESULT_FRAGMENT_RESULT, resultFragmentResult);
        setResult(RESULT_OK,intent);
        super.finish();
    }
}
