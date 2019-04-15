package com.zeroxuan.fragmenttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.app.Activity.RESULT_OK;

public class MainFragment extends Fragment {

    public static final String ARG_EDIT_A = "arg_edit_a";
    public static final String ARG_EDIT_B = "arg_edit_b";
    public static final String ARG_EDIT_RESULT = "arg_edit_result";
    private static final int REQUEST_CODE_RESULT_ACTIVITY = 100;

    private EditText mEdit_a;
    private EditText mEdit_b;
    private EditText mEdit_result;
    private Button mBtnCheck;
    private TextView mTextCheckResult;


    public static Fragment newInstance() {
        MainFragment fragment = new MainFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_container, container, false);
        mEdit_a = root.findViewById(R.id.edit_a);
        mEdit_b = root.findViewById(R.id.edit_b);
        mEdit_result = root.findViewById(R.id.edit_result);
        mBtnCheck = root.findViewById(R.id.btn_check);
        mTextCheckResult = root.findViewById(R.id.text_check_result);
        mTextCheckResult.setVisibility(View.INVISIBLE);
        mBtnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int input_a = Integer.parseInt(mEdit_a.getText().toString());
                int input_b = Integer.parseInt(mEdit_b.getText().toString());
                int input_result = Integer.parseInt(mEdit_result.getText().toString());

                Intent intent = new Intent(getContext(), ResultActivity.class);
                intent.putExtra(ARG_EDIT_A, input_a);
                intent.putExtra(ARG_EDIT_B, input_b);
                intent.putExtra(ARG_EDIT_RESULT, input_result);
                startActivityForResult(intent, REQUEST_CODE_RESULT_ACTIVITY);
            }
        });
        return root;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_RESULT_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    int backResult = data.getIntExtra(ResultActivity.RESULT_FRAGMENT_RESULT, 0);
                    int operatorResult = Integer.parseInt(mEdit_result.getText().toString());

                    String operatorRes = getString(R.string.succeed);
                    if (backResult != operatorResult) {
                        operatorRes = getString(R.string.defeat);
                    }
                    String result = String
                            .format(getString(R.string.check_result), operatorResult, backResult,
                                    operatorRes);
                    mTextCheckResult.setText(result);
                    mTextCheckResult.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}
