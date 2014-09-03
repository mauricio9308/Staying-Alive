package stayingalive.fmat.com.activity;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class AssetsTestActivity extends ActionBarActivity {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        text = new TextView( AssetsTestActivity.this );
        setContentView( text );

        AssetsTextTask task = new AssetsTextTask();
        task.execute( new String[]{ "myawesometext.txt" });
    }



    private class AssetsTextTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            AssetManager manager = getAssets();
            InputStream in = null;
            try{
                in = manager.open( params[0] );
                return loadTextFile( in );
            }catch( IOException e ){
                return "Couldn't find text file ";
            }finally {
                if( in != null ){
                    try{
                        in.close();
                    }catch( IOException e ){
                        return "Couldn't close file";
                    }
                }
            }
        }

        @Override
        protected void onPostExecute( String result ){
            text.setText( result );
        }

        private String loadTextFile( InputStream stream ) throws IOException{
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[4096];
            int length= 0;
            while( ( length = stream.read( bytes ) ) > 0 ){
                byteStream.write( bytes, 0, length );
            }

            return new String( byteStream.toByteArray(), "UTF-8");
        }
    }


}
