package stayingalive.fmat.com.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

public class LifeCycleTestsActivity extends ActionBarActivity {

	private StringBuilder mBuilder;
	private TextView mTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mBuilder = new StringBuilder();
		mTextView = new TextView(LifeCycleTestsActivity.this);
		setContentView(mTextView);
		log("created ");
	}

	@Override
	public void onResume() {
		super.onResume();
		log("resume");
	}

	@Override
	public void onPause() {
		super.onPause();
		log("pause");
		
		if( isFinishing() ){
			log("finishing"); 
		}
	}

	private void log(String text) {
		Log.d("LifeCycleTest", text);
		mBuilder.append(text);
		mBuilder.append("\n");
		mTextView.setText(mBuilder.toString());
	}

}
