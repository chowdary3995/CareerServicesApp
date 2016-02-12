/**
 * 
 */
package ia.umkc.career;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

/**
 * @author rr5h4
 * 
 */
@SuppressLint("SetJavaScriptEnabled")
public class WebViewer extends Activity {
	String[] menuName;
	Context context;
	WebView webview;
	ProgressDialog mDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int rowIndex = getIntent().getExtras().getInt("rowIndex");
		// requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.custom_title_bar);

		switch (rowIndex) {
		//case 0:
			//not used
			//break;

		// case 1:
		// webview = new WebView(this);
		// setContentView(webview);
		// String summary2 =
		// "<html><body><h3><font color=\"#0F69B9\"> Roo Career Network </font></h3><p><b>Profile:</b> Complete your academic information, activate privacy settings and opt&#45;in or out of receiving system alerts </p><p> <b>Documents:</b>  Upload resume(s), cover letter(s), unofficial transcripts, writing samples and other documents for review by Career Services and to be used in your job or internship search </p><p><b>Jobs:</b>  Roo Jobs are all of the jobs posted by employers directly in the system; Extended Job Search Tool will provide access to DirectEmployers and jobs across the United States; a link to the Work Study Jobs database (requires additional login); UMKC SEARCH, Serve 2 Learn, and Study&#45;Abroad links are available for you to utilize; Going Global provides internship and full&#45;time job information for more than 32 countries including the United States and Canada; Internships&#45;USA provides an internship directory in several for&#45;profit and non&#45;profit fields; and KC Nonprofit Connect to review full&#45;time, internship and volunteer opportunities in the Kansas City non&#45;profit community</p> <p><b>Events:</b> Review upcoming employer information sessions and career fairs sponsored by UMKC Career Services</p> <p> <b>Appointments:</b>  Schedule an appointment with Career Services using the short&#45;cut option on your RCN homepage </p></body></html>";
		// webview.loadData(summary2, "text/html", null);
		// break;

		case 2:
			String secRow = "<html><body><b>Career Counseling: </b> UMKC Career Services provides confidential career counseling for students and alumni who want to clarify, explore and understand their career&#45;related themes and make better informed career decisions.<br/><br/> <b>SIGI3:</b>  Free, online career assessment you may access on our website using your UMKC email address<br/><br/><b>Career Self&#45;Assessments:</b>  Several career assessments are available for a small fee and include an appointment interpretation.  The assessments include:  Myers&#45;Briggs Type Indicator and Strong Interest Inventory.<br/><br/><b>What Can I Do With My Major?:</b>  This resource provides common career areas, typical employers, and strategies designed to maximize major exploration and is available on our website, www.career.umkc.edu.<br/><br/><b>Career Planning Courses:</b>  UMKC Career Services offers career and life planning courses: ED 160<br/><br/></body></html>";
			webview = new WebView(this);
			webview.setBackgroundResource(R.drawable.white);
			webview.setBackgroundColor(0);
			setContentView(webview);
			TextView title= (TextView) findViewById(R.id.myTitle);
			title.setText("Advising/Counseling");
			webview.loadData(secRow, "text/html", null);
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			// Upcoming Events
			// Intent intent = new
			// Intent(getApplicationContext(),UpcomingEvents.class);
			// startActivity(intent);
			break;
		case 7:
			break;
		case 8:
			// Virtual office presentations
			webview = new WebView(this);
			webview.setBackgroundResource(R.drawable.white);
			webview.setBackgroundColor(0);
			setContentView(webview);
			webview.getSettings().setJavaScriptEnabled(true);
			// webview.getSettings().setPluginsEnabled(true);
			webview.loadUrl(getIntent().getExtras().getString("uri"));
			break;
		case 9:
			// blog
			webview = new WebView(this);

			// setContentView(webview);
			mDialog = new ProgressDialog(this);
			mDialog.setMessage("Loading Post...");
			mDialog.setCancelable(false);
			mDialog.show();

			webview.getSettings().setJavaScriptEnabled(true);
			webview.setWebViewClient(new WebViewClient() {
				@Override
				public void onPageFinished(WebView view, String url) {
					String heading = getIntent().getExtras()
							.getString("heading").replace("\"", "\\\"");
					webview.loadUrl("javascript:(function() { "
							+ "var x=document.getElementsByClassName(\"field-item\"); \n"
							+ "document.write(\"<h2>" + heading
							+ "</h2>\" + x[0].innerHTML);" + "})()");
					webview.setBackgroundResource(R.drawable.white);
					webview.setBackgroundColor(0);
					setContentView(webview);
					mDialog.dismiss();
				}
			});
			webview.loadUrl(getIntent().getExtras().getString("link"));
			break;
		default:
			break;
		}
	}

}
