package stayingalive.fmat.com.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ExternalStorageTestActivity extends ActionBarActivity {

    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mText = new TextView( ExternalStorageTestActivity.this );
        setContentView(mText);
        String state = Environment.getExternalStorageState();
        if( !state.equals( Environment.MEDIA_MOUNTED)){
            mText.setText("No external storage mounted");
        }else{
            File externalDir = Environment.getExternalStorageDirectory();
            File textFile = new File( externalDir.getAbsolutePath() + File.separator + "text.txt");
            try{
                writeTextToFile( textFile, "This is a test");
                String text = readTextFile( textFile );
                mText.setText( text );
                if( !textFile.delete() ){
                    mText.setText("Couldn't remove temporary file");
                }
            }catch( IOException e ){
                mText.setText("something went wrong " + e.getMessage() );
            }
        }
    }

    private void writeTextToFile( File file, String text ) throws IOException {
        if( !file.exists() ){
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter( new FileWriter( file ));
        writer.write( text );
        writer.flush();
        writer.close();
    }

    private String readTextFile( File file ) throws IOException{
        BufferedReader reader = new BufferedReader( new FileReader( file ));
        String text = null;
        String line = null;
        while ((line = reader.readLine()) != null) {
            text += line;
        }

        return text;
    }



}
