package com.github.yoojia.inputs;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Default message display
 * @author 陈小锅 (yoojia.chen@gmail.com)
 */
public class AndroidMessageDisplay implements MessageDisplay {
    Context context;
    OnVerifierListener verifierListener;

    public AndroidMessageDisplay(Context context) {
        this.context = context;
    }

    public AndroidMessageDisplay(OnVerifierListener verifierListener) {
        this.verifierListener = verifierListener;
    }


    private final static String TAG = AndroidMessageDisplay.class.getSimpleName();

    @Override
    public void show(Input input, String message) {
        // try attach
        final View inputView;
        if (input instanceof TextInput) {
            inputView = ((TextInput) input).inputView;
        }else{
            Log.e(TAG, "- When use <AndroidMessageDisplay>, <TextInput> is recommend !");
            inputView = null;
        }
        if(null!=context){
            Toast.makeText(context,message,
                    Toast.LENGTH_SHORT).show();
        }

        if(null!=verifierListener&&null!=inputView){
            verifierListener.onVerifierView(inputView,message);
        }

    }

    public interface OnVerifierListener {
        void onVerifierView(View v,String msg);
    }

}