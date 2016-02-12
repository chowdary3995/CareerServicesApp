/**
 * 
 */
package ia.umkc.career;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author rr5h4
 * 
 */
public class Location extends Activity {

	private double latitudeE61 = 39.036103;
	private double longitudeE61 = -94.577651;

	final Context context = this;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.location);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.custom_title_bar);
		TextView title= (TextView) findViewById(R.id.myTitle);
		title.setText("Location/Hours");
		TextView content0 = (TextView) findViewById(R.id.content0);
		content0.setText(Html
				.fromHtml("<u><b>MAIN OFFICE</b></u>:&nbsp; Student Success Center, 5000 Holmes, Floor 2, "
						+ "Kansas City, MO 64110<br>"));
		TextView content1 = (TextView) findViewById(R.id.content1);
		content1.setText(Html
				.fromHtml("<u><b>PHONE</b></u>:&nbsp;<font color='red'>816.235.1636 </font>"));
		content1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				call();
			}
		});

		TextView content2 = (TextView) findViewById(R.id.content2);
		content2.setText(Html
				.fromHtml("<br><u><b>EMAIL</b></u>:&nbsp;<font color='red'>"
						+ "careerservices@umkc.edu</font><br>"));
		content2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				final Intent emailIntent = new Intent(
						android.content.Intent.ACTION_SEND);
				emailIntent.setType("text/html");
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
						"subject");
				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
						Html.fromHtml("body"));
				startActivity(Intent.createChooser(emailIntent,
						"Email:careerservices@umkc.edu"));

			}
		});

		TextView content3 = (TextView) findViewById(R.id.content3);
		content3.setText(Html.fromHtml("<u><b>HOURS</b></u>:&nbsp;</u>"
				+ " Monday - Friday: 8:00 am - 5:00 pm </h4><br><br>"));

		Button button1 = (Button) findViewById(R.id.buttonLoc3);
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri
						.parse("geo:0,0?q=" + latitudeE61 + "," + longitudeE61
								+ "( Career Services)"));
				startActivity(intent);
			}
		});
		
		Button addButton = (Button) findViewById(R.id.addButton);
		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
				intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
				intent.putExtra(ContactsContract.Intents.Insert.PHONE, "8162351636");
				intent.putExtra(ContactsContract.Intents.Insert.EMAIL, "careerservices@umkc.edu");
				intent.putExtra(ContactsContract.Intents.Insert.NAME, "UMKC Career Services");
				
				intent.putExtra(ContactsContract.Data.IS_SUPER_PRIMARY, 1);
				startActivity(intent);
			}
		});
	}

	private void call() {
		try {
			Intent callIntent = new Intent(Intent.ACTION_CALL);
			callIntent.setData(Uri.parse("tel:8162351636"));
			startActivity(callIntent);
		} catch (ActivityNotFoundException e) {
			Log.e("PHONE CALL", "Call failed", e);
		}
	}

}
