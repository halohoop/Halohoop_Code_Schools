package com.halohoop.custom;
 
import android.annotation.NonNull;
import android.annotation.StyleRes;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
 
import com.halohoop.custom.R;
 
public class SimpleCustomDialog extends Dialog {
 
    private SimpleCustomDialog(@NonNull Context context) {
        super(context);
    }
 
    private SimpleCustomDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }
 
    private SimpleCustomDialog(@NonNull Context context, boolean cancelable, OnCancelListener
            cancelListener) {
        super(context, cancelable, cancelListener);
    }
 
    public static class Builder {
        private Context mContext;
        private String mMessage;
        private String mPositiveButtonText;
        private String mNegativeButtonText;
        private OnClickListener mPositiveButtonClickListener;
        private OnClickListener mNegativeButtonClickListener;
 
        public Builder(Context context) {
            this.mContext = context;
        }
 
        public Builder setMessage(String message) {
           this.mMessage = message;
            return this;
        }
 
        public Builder setPositiveButtonText(String positiveButtonText) {
            this.mPositiveButtonText = positiveButtonText;
            return this;
        }
 
        public Builder setNegativeButtonText(String negativeButtonText) {
            this.mNegativeButtonText = negativeButtonText;
            return this;
        }
 
        public Builder setPositiveButtonClickListener(OnClickListener positiveOnClickListener) {
            this.mPositiveButtonClickListener = positiveOnClickListener;
            return this;
        }
 
        public Builder setNegativeButtonClickListener(OnClickListener negativeOnClickListener) {
            this.mNegativeButtonClickListener = negativeOnClickListener;
            return this;
        }
 
        public SimpleCustomDialog create() {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View contentView = inflater.inflate(R.layout.editor_alert_dialog, null);
            TextView tvAlertDialogMessage = (TextView) contentView.findViewById(R.id
                    .tv_alert_dialog_message);
            TextView tvPositive = (TextView) contentView.findViewById(R.id.tv_positive);
            TextView tvNegetive = (TextView) contentView.findViewById(R.id.tv_negetive);
            setTextViewText(tvAlertDialogMessage, mMessage);
            setTextViewText(tvPositive, mPositiveButtonText);
            setTextViewText(tvNegetive, mNegativeButtonText);
            final SimpleCustomDialog simpleCustomDialog = new SimpleCustomDialog(mContext);
            simpleCustomDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            simpleCustomDialog.addContentView(contentView, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            if (mPositiveButtonClickListener != null) {
                tvPositive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPositiveButtonClickListener.onClick(simpleCustomDialog,
                                DialogInterface.BUTTON_POSITIVE);
                    }
                });
            }
            if (mNegativeButtonClickListener != null) {
                tvNegetive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mNegativeButtonClickListener.onClick(simpleCustomDialog,
                                DialogInterface.BUTTON_NEGATIVE);
                    }
                });
            }
            return simpleCustomDialog;
        }
 
        private void setTextViewText(TextView tv, String text) {
            if (!TextUtils.isEmpty(text)) {
                tv.setText(text);
            }
        }
    }
}
//--------------------------------------------------------------------------------
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              android:background="#ffffff"
              android:orientation="vertical">
 
    <TextView
        android:layout_weight="1"
        android:id="@+id/tv_alert_dialog_message"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:gravity="left|center_vertical"
        android:padding="20dp"
        android:text="@string/edited_exist_or_not"
        android:textColor="#000"/>
 
    <FrameLayout
        android:layout_weight="1"
        android:layout_width="match_parent"
        android:layout_height="0dp">
 
        <TextView
            android:id="@+id/tv_positive"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_gravity="center"
            android:padding="20dp"
            android:gravity="center"
            android:text="@string/editor_dialog_exist"
            android:textColor="#20A397"/>
 
        <TextView
            android:id="@+id/tv_negetive"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
           android:gravity="center"
            android:layout_gravity="right|center_vertical"
            android:padding="20dp"
            android:text="@string/editor_dialog_cancel"
            android:textColor="#20A397"/>
    </FrameLayout>
 
 
</LinearLayout>
//--------------------------------------------------------------------------------
    private void alertDialog() {
        SimpleCustomDialog.Builder dialog = new SimpleCustomDialog.Builder(this);
        dialog.setNegativeButtonClickListener(this)
                .setPositiveButtonClickListener(this)
                .create().show();
}
    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE) {
            finish();
        } else if (which == DialogInterface.BUTTON_NEGATIVE) {
            //eat it
        }
    }