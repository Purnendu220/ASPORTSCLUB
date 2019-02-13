package com.asportsclub.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by Mobikasa on 7/25/2016.
 */
public class FontTypeFace {
    public void setRobotoThinTypeFace(Context context, View... view) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "Roboto-Light.ttf");
        for (View v : view) {
            if (v instanceof TextView) {
                ((TextView) v).setTypeface(tf);
            } else if (v instanceof EditText) {
                ((EditText) v).setTypeface(tf);
            } else if (v instanceof Button) {
                ((Button) v).setTypeface(tf);
            } else if (v instanceof CheckBox) {
                ((CheckBox) v).setTypeface(tf);
            } else if (v instanceof RadioButton) {
                ((RadioButton) v).setTypeface(tf);
            }
        }
    }

    public void setRobotoRegularTypeFace(Context context, View... view) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf");
        for (View v : view) {
            if (v instanceof TextView) {
                ((TextView) v).setTypeface(tf);
            } else if (v instanceof EditText) {
                ((EditText) v).setTypeface(tf);
            } else if (v instanceof Button) {
                ((Button) v).setTypeface(tf);
            } else if (v instanceof CheckBox) {
                ((CheckBox) v).setTypeface(tf);
            } else if (v instanceof RadioButton) {
                ((RadioButton) v).setTypeface(tf);
            }
        }
    }

    public void setRobotoMediumTypeFace(Context context, View... view) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "Roboto-Medium.ttf");
        for (View v : view) {
            if (v instanceof TextView) {
                ((TextView) v).setTypeface(tf);
            } else if (v instanceof EditText) {
                ((EditText) v).setTypeface(tf);
            } else if (v instanceof Button) {
                ((Button) v).setTypeface(tf);
            } else if (v instanceof CheckBox) {
                ((CheckBox) v).setTypeface(tf);
            } else if (v instanceof RadioButton) {
                ((RadioButton) v).setTypeface(tf);
            }
        }
    }

    public void setRobotoItalicTypeFace(Context context, View... view) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "Roboto-Italic.ttf");
        for (View v : view) {
            if (v instanceof TextView) {
                ((TextView) v).setTypeface(tf);
            } else if (v instanceof EditText) {
                ((EditText) v).setTypeface(tf);
            } else if (v instanceof Button) {
                ((Button) v).setTypeface(tf);
            } else if (v instanceof CheckBox) {
                ((CheckBox) v).setTypeface(tf);
            } else if (v instanceof RadioButton) {
                ((RadioButton) v).setTypeface(tf);
            }
        }
    }

    public void setRobotoBoldTypeFace(Context context, View... view) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "Roboto-Bold.ttf");
        for (View v : view) {
            if (v instanceof TextView) {
                ((TextView) v).setTypeface(tf);
            } else if (v instanceof EditText) {
                ((EditText) v).setTypeface(tf);
            } else if (v instanceof Button) {
                ((Button) v).setTypeface(tf);
            } else if (v instanceof CheckBox) {
                ((CheckBox) v).setTypeface(tf);
            } else if (v instanceof RadioButton) {
                ((RadioButton) v).setTypeface(tf);
            }
        }
    }

}
