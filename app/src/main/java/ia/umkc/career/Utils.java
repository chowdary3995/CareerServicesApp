/**
 * 
 */
package ia.umkc.career;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * @author kcsc95
 * 
 */
public class Utils {

	public static boolean isInternetAvailable(Context context) {
		boolean isNetworkEnabled = false;
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		if (activeNetworkInfo != null
				&& activeNetworkInfo.isConnectedOrConnecting())
			isNetworkEnabled = true;
		return isNetworkEnabled;
	}

	public static boolean isGPSEnabled(Context context) {
		LocationManager m_LocationManager;
		m_LocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		return m_LocationManager
				.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}

	public static String readJSONFeed(HttpGet httpGet) {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null)
					builder.append(line);
			} else
				Log.e(CareerServicesActivity.class.toString(),
						"Failed to download file");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
}
