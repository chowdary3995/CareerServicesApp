/**
 * 
 */
package ia.umkc.career;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * @author rr5h4
 * 
 */
public class RooCareerWebViewer extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		
		int rowIndex = getIntent().getExtras().getInt("rowIndex");
		WebView webview = null;
		String content = "";
		webview = new WebView(this);
		setContentView(webview);
		webview.setBackgroundResource(R.drawable.white);
		webview.setBackgroundColor(0);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.custom_title_bar);
		TextView title= (TextView) findViewById(R.id.myTitle);
		switch (rowIndex) {

		case 0:
			title.setText("Profile");
			content = "<html><body>"
					+ "<p>Complete your academic information, activate privacy settings and opt&#45;in or out of"
					+ " receiving system alerts </p></body></html>";
			break;
		case 1:
			title.setText("Documents");
			content = "<html><body><p>Upload resume(s), cover letter(s), unofficial transcripts, writing samples and other documents for "
					+ "review by Career Services and to be used in your job or internship search </p></body></html>";
			break;
		case 2:
			title.setText("Jobs");
			content = "<html><body><p>Roo Jobs are all of "
					+ "the jobs posted by employers directly in the system; Extended Job Search Tool will provide access to DirectEmployers and "
					+ "jobs across the United States; a link to the Work Study Jobs database (requires additional login); UMKC SEARCH, "
					+ "Serve 2 Learn, and Study&#45;Abroad links are available for you to utilize; Going Global provides internship"
					+ " and full&#45;time job information for more than 32 countries including the United States and Canada; "
					+ "Internships&#45;USA provides an internship directory in several for&#45;profit and non&#45;profit fields; and "
					+ "KC Nonprofit Connect to review full&#45;time, internship and volunteer opportunities in the Kansas City non&#45;profit "
					+ "community</p> </body></html>";
			break;
		case 3:
			title.setText("Events");
			content = "<html><body>"
					+ "<p>Review upcoming employer information sessions and career fairs sponsored by UMKC "
					+ "Career Services</p></body></html>";
			break;
		case 4:
			title.setText("Appointments");
			content = "<html><body>"
					+ "<p>Schedule an appointment with Career Services using the short&#45;cut option on your <font color=\"red\"><a href=\"https://umkc-csm.symplicity.com/\" >Roo Career Network</a></font> homepage </p></body></html>";
		default:
			break;
		}
		
		webview.loadData(content, "text/html", null);
	}
}
