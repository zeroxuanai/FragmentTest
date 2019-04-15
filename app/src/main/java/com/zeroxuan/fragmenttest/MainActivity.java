package com.zeroxuan.fragmenttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String ARG_EDIT_A = "arg_edit_a";
    public static final String ARG_EDIT_B = "arg_edit_b";
    public static final String ARG_EDIT_RESULT = "arg_edit_result";
    private static final String TAG = "YJ MainActivity";
    private static final int REQUEST_CODE_RESULT = 100;
    private EditText mEditA;
    private EditText mEditB;
    private EditText mEditResult;
    private Button mBtnCheck;
    private TextView mTextResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mEditA = findViewById(R.id.edit_a);
        mEditB = findViewById(R.id.edit_b);
        mEditResult = findViewById(R.id.edit_result);
        mBtnCheck = findViewById(R.id.btn_check);
        mTextResult = findViewById(R.id.text_check_result);

        mTextResult.setVisibility(View.INVISIBLE);


        mBtnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);

                int edit_a = Integer.parseInt(mEditA.getText().toString());
                int edit_b = Integer.parseInt(mEditB.getText().toString());
                int edit_result = Integer.parseInt(mEditResult.getText().toString());
                intent.putExtra(ARG_EDIT_A, edit_a);
                intent.putExtra(ARG_EDIT_B, edit_b);
                intent.putExtra(ARG_EDIT_RESULT, edit_result);
                startActivityForResult(intent, REQUEST_CODE_RESULT);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_RESULT) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    int getResult = data.getIntExtra(ResultActivity.ARG_RESULT, 0);

                    int result = Integer.parseInt(mEditResult.getText().toString());

                    String resultCheck = "正确";
                    if (result != getResult) {
                        resultCheck = "错误";
                    }
                    String resultStr = String
                            .format(getResources().getString(R.string.check_result), result,
                                    getResult, resultCheck);
                    mTextResult.setText(resultStr);
                    mTextResult.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}
