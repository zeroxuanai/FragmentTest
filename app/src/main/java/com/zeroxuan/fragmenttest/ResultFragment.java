package com.zeroxuan.fragmenttest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ResultFragment extends BaseFragment {

    private static final String ARG_EDIT_A = "arg_edit_A";
    private static final String ARG_EDIT_B = "arg_edit_b";
    private static final String ARG_EDIT_RESULT = "arg_edit_result";
    private TextView mTextResult;
    private Button mBtnCloseActivity;
    private int argEditA;
    private int argEditB;
    private int argEditResult;
    private int operatorResult;

    private ReturnResultToActivityListener mReturnResultToActivity;

    public static Fragment newInstance(int inputEditA, int inputEditB, int inputEditResult) {
        ResultFragment fragment = new ResultFragment();

        Bundle arg = new Bundle();
        arg.putInt(ARG_EDIT_A, inputEditA);
        arg.putInt(ARG_EDIT_B, inputEditB);
        arg.putInt(ARG_EDIT_RESULT, inputEditResult);
        fragment.setArguments(arg);

        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        argEditA = getArguments().getInt(ARG_EDIT_A, 0);
        argEditB = getArguments().getInt(ARG_EDIT_B, 0);
        argEditResult = getArguments().getInt(ARG_EDIT_RESULT, 0);

        if (context instanceof ReturnResultToActivityListener) {
            mReturnResultToActivity = (ReturnResultToActivityListener) context;
        }
        else {
            throw new RuntimeException(
                    context.toString() + " must implement ReturnResultToActivityListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.result_container, container, false);
        mTextResult = root.findViewById(R.id.text_result);
        mBtnCloseActivity = root.findViewById(R.id.btn_close_activity);

        operatorResult = argEditA + argEditB;
        String resultStr = getString(R.string.succeed);
        if (operatorResult != argEditResult) {
            resultStr = getString(R.string.defeat);
        }
        String getStr = String
                .format(getString(R.string.result_result), argEditA, argEditB, operatorResult,
                        argEditResult, resultStr);
        mTextResult.setText(getStr);

        mBtnCloseActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToActivity();
            }
        });
        return root;
    }

    public void setArg(int inputEditA, int inputEditB, int inputEditResult) {
        argEditA = inputEditA;
        argEditB = inputEditB;
        argEditResult = inputEditResult;
    }



    public static interface ReturnResultToActivityListener {
        void returnResult(int result);
    }


    private void sendDataToActivity(){
        mReturnResultToActivity.returnResult(operatorResult);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        sendDataToActivity();
    }
}
