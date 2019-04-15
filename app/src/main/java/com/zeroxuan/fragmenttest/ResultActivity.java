package com.zeroxuan.fragmenttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private int data_a;
    private int data_b;
    private int data_result;
    public static final String ARG_RESULT="arg_result";
    private int operatorResult;

    private TextView mTextView;
    private Button mBtnCloseActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mTextView = findViewById(R.id.text_result);

        data_a = getIntent().getIntExtra(MainActivity.ARG_EDIT_A, 0);
        data_b = getIntent().getIntExtra(MainActivity.ARG_EDIT_B, 0);
        data_result = getIntent().getIntExtra(MainActivity.ARG_EDIT_RESULT, 0);
        operatorResult = data_a + data_b;

        String checkStr = "正确";
        if (operatorResult != data_result) {
            checkStr = "错误";
        }

        String result = String
                .format(getResources().getString(R.string.result_result), data_a, data_b,
                        operatorResult, data_result, checkStr);
        mTextView.setText(result);

        mBtnCloseActivity = findViewById(R.id.btn_close_activity);
        mBtnCloseActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResultAndCloseActivity();
            }
        });


    }

    void setResultAndCloseActivity() {
        finish();
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra(ARG_RESULT, operatorResult);
        setResult(RESULT_OK, intent);
        super.finish();
    }


}
