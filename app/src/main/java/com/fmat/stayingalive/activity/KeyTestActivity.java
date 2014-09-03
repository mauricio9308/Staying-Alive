package com.fmat.stayingalive.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by mauriciolara on 9/2/14.
 */
public class KeyTestActivity extends ActionBarActivity implements View.OnKeyListener{

    private TextView mTxtKey;
    private EditText mEdtxtKey;

    @Override
    public void onCreate( Bundle savedInstanceState ){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_key_test );
        mTxtKey = ( TextView ) findViewById( R.id.txt_key_pressed );
        mTxtKey.setText("Initial state");
        mEdtxtKey = ( EditText ) findViewById( R.id.edtxt_key_input );
        mEdtxtKey.setImeOptions(EditorInfo.IME_ACTION_DONE );

        mEdtxtKey.setFocusableInTouchMode( true );
        mEdtxtKey.requestFocus();
        mEdtxtKey.setOnKeyListener( KeyTestActivity.this );

    }


    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        switch( event.getAction() ){
            case KeyEvent.ACTION_DOWN:
                mTxtKey.setText( "KeyCode" + event.getKeyCode() + " character : " + event.getUnicodeChar() );
                break;
            case KeyEvent.ACTION_UP:
                mTxtKey.setText("UP");
                break;
        }

        return false;
    }
}
