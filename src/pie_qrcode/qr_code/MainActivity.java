package pie_qrcode.qr_code;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * @author Viktor
 * Requires permission for Internet access - android.permission.INTERNET
 **/   
public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Scan();
	    
	    // Enable Javascript execution in our webview
	    WebView view = (WebView) findViewById(R.id.webView);
	    view.getSettings().setJavaScriptEnabled(true);
		view.setWebChromeClient(new WebChromeClient());   
	}
	
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // If the scanner scanned qr code get the result which in our case is url
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
            		// This is the url from the scan
                    String contents = data.getStringExtra("SCAN_RESULT"); 
                    finishActivity(0);
                    
                    // Load the contents from the url in our webview
                    WebView view = (WebView) findViewById(R.id.webView);
                    view.loadUrl(contents);
            } else 
            if (resultCode == RESULT_CANCELED) {
              // Handle cancel
            }
        }
    }
	
	public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);                
        int landscape = newConfig.ORIENTATION_LANDSCAPE;
        
        // Reload the webview if the device screen is rotated
        if(newConfig.orientation == landscape){
        	WebView view = (WebView) findViewById(R.id.webView);
 	        view.getSettings().setJavaScriptEnabled(true);
 		    view.setWebChromeClient(new WebChromeClient());
        }
	}

	/**
	 * Handles onClick event when the user needs to do another scan
	 * @param view
	 */
	public void onClickScan(View view)
	{
		Scan();
	}
	
	/**
	 * Opens the scanner application and waits for the result from the scan
	 */
	public void Scan(){
		// Prepare the intent for the qrcode scanner
		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
				
		//To scan QR code use "QR_CODE_MODE"
	    intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
			    
	    // This stops saving uour barcode in barcode scanner app's history
	    intent.putExtra("SAVE_HISTORY", false);
			    
	    // Open the scanner app
	    startActivityForResult(intent, 0);
	}
	
}
