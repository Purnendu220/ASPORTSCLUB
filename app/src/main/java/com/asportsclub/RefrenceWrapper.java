package com.asportsclub;

import android.content.Context;

import com.asportsclub.utils.FontTypeFace;


/**
 * Project : Mobikasa Cardamom
 * Author : Balwinder Singh Madaan
 * Creation Date : 25-july-2016
 * Description :
 */
public class RefrenceWrapper {
  public static RefrenceWrapper refrenceWrapper;
  private Context context;


  private FontTypeFace fontTypeFace;

  private RefrenceWrapper(Context activity) {
    this.context = activity;
  }

  public static RefrenceWrapper getRefrenceWrapper(Context context) {
    if (refrenceWrapper == null) {
      refrenceWrapper = new RefrenceWrapper(context);
    }
    return refrenceWrapper;
  }

  public FontTypeFace getFontTypeFace() {
    if (fontTypeFace == null) {
      fontTypeFace = new FontTypeFace();
    }
    return fontTypeFace;
  }



}