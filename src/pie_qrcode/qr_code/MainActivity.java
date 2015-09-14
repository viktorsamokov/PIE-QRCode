package pie_qrcode.qr_code;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
	       intent.putExtra("SCAN_MODE", "QR_CODE_MODE");//for Qr code, its "QR_CODE_MODE" instead of "PRODUCT_MODE"
	       intent.putExtra("SAVE_HISTORY", false);//this stops saving ur barcode in barcode scanner app's history
	       startActivityForResult(intent, 0);
	       WebView view = (WebView) findViewById(R.id.webView);
	       view.getSettings().setJavaScriptEnabled(true);
		   view.setWebChromeClient(new WebChromeClient());
	       
	       
	}
	
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                    String contents = data.getStringExtra("SCAN_RESULT"); //this is the result
                    finishActivity(0);
                    WebView view = (WebView) findViewById(R.id.webView);
                    view.loadUrl(contents);
                    //view.loadData("<b>asdas</b>", "text/html", "UTF-8");
                    
            } else 
            if (resultCode == RESULT_CANCELED) {
              // Handle cancel
            }
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
