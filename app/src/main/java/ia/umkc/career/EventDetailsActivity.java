/**
 * 
 */
package ia.umkc.career;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

/**
 * @author kcsc95
 *
 */
public class EventDetailsActivity extends Activity {
	
	String eventName, startDate, startTime, endDate, endTime, eventDesc, eventURL;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.event_details);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.custom_title_bar);
		TextView title2= (TextView) findViewById(R.id.myTitle);
		title2.setText("Event Details");
		Intent intent = getIntent();
		eventName= intent.getStringExtra("Event_name");
		startDate= intent.getStringExtra("start_date");
		startTime= intent.getStringExtra("start_time");
		endDate= intent.getStringExtra("end_date");
		endTime= intent.getStringExtra("end_time");
		eventDesc= intent.getStringExtra("Event_body");
		eventURL= intent.getStringExtra("url");
		if (!eventURL.startsWith("http://") && !eventURL.startsWith("https://"))
			eventURL = "http://" + eventURL;
		
		setTitle("UMKC Career Services");
		setTitleColor(getResources().getColor(R.color.titleColorNew));
		TextView content0 = (TextView) findViewById(R.id.content0);
		content0.setText(Html
				.fromHtml("<u><b>EVENT NAME</b></u>:&nbsp;"	+ eventName));
		TextView content1 = (TextView) findViewById(R.id.content1);
		content1.setText(Html
				.fromHtml("<u><b>Start Date</b></u>:&nbsp;"	+ startDate + " " + startTime));
		TextView content2 = (TextView) findViewById(R.id.content2);
		content2.setText(Html
				.fromHtml("<u><b>End Date</b></u>:&nbsp;"	+ endDate + " "+ endTime));
		TextView content3 = (TextView) findViewById(R.id.content3);
		content3.setText(Html
				.fromHtml("<u><b>Description</b></u>:&nbsp;"	+ eventDesc));
		TextView content4 = (TextView) findViewById(R.id.content4);
		content4.setText(Html
				.fromHtml("<u><b>Event URL</b></u>:&nbsp; <font color='blue'><i>" + eventURL + "</i></font>"));

	}
	
	public void gotoURL(View view){
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(eventURL));
		startActivity(intent);
	}
	
	public void addToCalendar(View view){
		//String[] eventDetails = url.split(",");
		Calendar beginTime = Calendar.getInstance();
		String sDay[] = startDate.split("-");
		String sTime[] = startTime.split(":");
		String eDay[] = endDate.split("-");
		String eTime[] = endTime.split(":");
		beginTime.set(Integer.parseInt(sDay[0]), Integer.parseInt(sDay[1]), Integer.parseInt(sDay[2]), Integer.parseInt(sTime[0]), Integer.parseInt(sTime[1]));
		Calendar endTime = Calendar.getInstance();
		endTime.set(Integer.parseInt(eDay[0]), Integer.parseInt(eDay[1]), Integer.parseInt(eDay[2]), Integer.parseInt(eTime[0]), Integer.parseInt(eTime[1]));           
		Intent intent = new Intent(Intent.ACTION_EDIT);
		intent.setType("vnd.android.cursor.item/event");
		intent.putExtra("beginTime", beginTime.getTimeInMillis());
		//intent.putExtra("allDay", true);
		//intent.putExtra("rrule", "FREQ=YEARLY");
		intent.putExtra("endTime", endTime.getTimeInMillis());
		intent.putExtra("title", eventName);
		intent.putExtra("description", eventDesc);
		//description, eventLocation, calendar_id
		//dtstart, dtend, eventStatus - 
		startActivity(intent);
		
	}
	
	public void share(View v){
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, eventName + " - " + eventURL);
		startActivity(Intent.createChooser(intent, "Share with"));
	}
	
}
