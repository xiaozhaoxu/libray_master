package com.github.yoojia.inputs;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Inputs tool for Android widgets
 * @author 陈小锅 (yoojia.chen@gmail.com)
 * @since 1.0
 */
public class AndroidInputs extends Inputs{

    public static TextInput<TextView> textView(TextView textView){
        return new TextInput<TextView>(textView);
    }

    public static TextInput<EditText> editText(EditText editText) {
        return new TextInput<EditText>(editText);
    }

    public static ViewInput<CompoundButton> radioButton(final RadioButton radioButton) {
        return checkable(radioButton);
    }

    public static ViewInput<CompoundButton> checkBox(CheckBox checkBox) {
        return checkable(checkBox);
    }

    public static ViewInput<CompoundButton> toggleButton(ToggleButton toggleButton) {
        return checkable(toggleButton);
    }

    public static ViewInput<RatingBar> ratingBar(final RatingBar ratingBar) {
        return new ViewInput<RatingBar>(ratingBar) {
            @Override public String getValue() {
                if(null!=ratingBar) {
                    return String.valueOf(ratingBar.getRating());
                }else{
                    return "0";
                }
            }
        };
    }

    public static ViewInput<CompoundButton> checkable(final CompoundButton checkable) {
        return new ViewInput<CompoundButton>(checkable) {
            @Override public String getValue() {
                if(null!=checkable) {
                    return String.valueOf(checkable.isChecked());
                }else{
                    return "false";
                }
            }
        };
    }
}
