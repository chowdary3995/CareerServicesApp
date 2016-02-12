/**
 * 
 */
package ia.umkc.career;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author rr5h4
 * 
 */
@SuppressLint("CutPasteId")
public class MainPageViewer extends ListActivity {

	String[] menuName;
	Context context;
	Map<String, Map<String, String>> blogPosts;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		final int rowPointer = getIntent().getExtras().getInt("rowIndex");
		switch (rowPointer) {

		case 0:
			setContentView(R.layout.resume_dev_layout);
			getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
					R.layout.custom_title_bar);
			TextView titleR= (TextView) findViewById(R.id.myTitle);
			titleR.setText("Resume Development Tips");
			TextView textView = (TextView) findViewById(R.id.resMaintextView2);
			textView.setText(Html
					.fromHtml("<html><body>Resume critiques are available during appointment times - schedule in <font color=\"red\"><a href=\"https://umkc-csm.symplicity.com/\" >Roo Career Network</a></font>. Access the Resume Builder tool to start your first resume or give an old one a lift. Access it through <font color=\"red\"><a href=\"https://umkc-csm.symplicity.com/\" >Roo Career Network homepage</a></font></body></html>"));
			textView.setMovementMethod(LinkMovementMethod.getInstance());
			menuName = getResources().getStringArray(R.array.ResDevmenuName);
			List<MainMenuDTO> data = new ArrayList<MainMenuDTO>();
			for (int i = 0; i < menuName.length; i++) {
				data.add(new MainMenuDTO(menuName[i]));
			}
			int layout = R.layout.homescreen;
			setListAdapter(new ResumeListAdapter(this, data, layout));
			ListView lv = getListView();
			lv.setTextFilterEnabled(true);
			lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parentView,
						View childView, int position, long id) {
					getNextPage(position, rowPointer);
				}
			});
			break;
		case 1:
			setContentView(R.layout.main);
			getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
					R.layout.custom_title_bar);
			TextView title2= (TextView) findViewById(R.id.myTitle);
			title2.setText("Cover Letter Tips");
			
			menuName = getResources().getStringArray(R.array.CoverLetterTips);
			List<MainMenuDTO> coverData = new ArrayList<MainMenuDTO>();
			for (int i = 0; i < menuName.length; i++) {
				coverData.add(new MainMenuDTO(menuName[i]));
			}
			int cvrLayout = R.layout.homescreen;
			setListAdapter(new ResumeListAdapter(this, coverData, cvrLayout));
			ListView lv1 = getListView();
			lv1.setTextFilterEnabled(true);
			lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parentView,
						View childView, int position, long id) {
					getNextPage(position, rowPointer);
				}
			});
			break;
		case 2:
			setContentView(R.layout.main);
			getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
					R.layout.custom_title_bar);
			TextView title3= (TextView) findViewById(R.id.myTitle);
			title3.setText("Interviewing Tips");
			menuName = getResources().getStringArray(R.array.interviewTips);
			List<MainMenuDTO> interviewData = new ArrayList<MainMenuDTO>();
			for (int i = 0; i < menuName.length; i++) {
				interviewData.add(new MainMenuDTO(menuName[i]));
			}
			int intrvLayout = R.layout.homescreen;
			setListAdapter(new ResumeListAdapter(this, interviewData,
					intrvLayout));
			ListView intrvLV = getListView();
			intrvLV.setTextFilterEnabled(true);
			intrvLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parentView,
						View childView, int position, long id) {
					getNextPage(position, rowPointer);
				}
			});
			break;
		case 3:
			setContentView(R.layout.virtoffice_layout);
			getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
					R.layout.custom_title_bar);
			menuName = getResources().getStringArray(R.array.interviewTips);
			List<MainMenuDTO> virtOfficeData = new ArrayList<MainMenuDTO>();
			for (int i = 0; i < menuName.length; i++) {
				virtOfficeData.add(new MainMenuDTO(menuName[i]));
			}
			int vrtLayout = R.layout.homescreen;
			setListAdapter(new ResumeListAdapter(this, virtOfficeData,
					vrtLayout));
			ListView vrtOffice = getListView();
			vrtOffice.setTextFilterEnabled(true);
			vrtOffice
					.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parentView,
								View childView, int position, long id) {
							getNextPage(position, rowPointer);
						}
					});
			break;
		case 4:
			//blog posts
			setContentView(R.layout.main);
			getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
					R.layout.custom_title_bar);
			TextView title= (TextView) findViewById(R.id.myTitle);
			title.setText("Blog");
			blogPosts = getBlogPosts();
			
			menuName = blogPosts.keySet().toArray(new String[blogPosts.size()]);
			List<MainMenuDTO> blogData = new ArrayList<MainMenuDTO>();
			int length = menuName.length;
			for (int i = 0; i < length; i++) {
				blogData.add(new MainMenuDTO(menuName[i]));
			}
			int blogLayout = R.layout.homescreen;
			ResumeListAdapter adapter = new ResumeListAdapter(this, blogData,
					blogLayout);
			setListAdapter(adapter);
			ListView blogLV = getListView();
			blogLV.setTextFilterEnabled(true);
			blogLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parentView,
						View childView, int position, long id) {
					String item = ((MainMenuDTO)getListAdapter().getItem(position)).getMenuName();
					String link = blogPosts.get(item).get("link");
					Intent intent = new Intent(getApplicationContext(),WebViewer.class);
					intent.putExtra("rowIndex", 9);
					intent.putExtra("link", link);
					intent.putExtra("heading", item);
					startActivity(intent);
					
				}
			});
			break;
		case 5:
			break;
		default:
			break;
		}
	}

	private Map<String, Map<String, String>> getBlogPosts() {
		HttpGet httpGet = new HttpGet(
				"http://www.umkc.edu/intapps/json/career-umkc-feed-json.cfm");
		String json = Utils.readJSONFeed(httpGet);
		Map<String, Map<String, String>> blogPosts = new LinkedHashMap<String, Map<String, String>>();
		try {
			JSONArray jsonArray = new JSONArray(json);
			JSONObject jsonObj;
			
			Map<String, String> postDetails;// = new HashMap<String,String>();
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				jsonObj = jsonArray.getJSONObject(i);
				postDetails = new HashMap<String, String>();
				postDetails.put("link", jsonObj.getString("link"));
				postDetails.put("date", jsonObj.getString("date"));
				postDetails.put("title", jsonObj.getString("title"));
				blogPosts.put(jsonObj.getString("title"), postDetails);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return blogPosts;
	}

	private void getNextPage(int position, int rowPointer) {
		boolean isInternetAvailable = false;
		if (position == 1 && rowPointer == 1) {
			isInternetAvailable = Utils
					.isInternetAvailable(getApplicationContext());
			if (isInternetAvailable) {
				String url = "http://www.career.umkc.edu/career/sites/default/files/pdfs/cover_letter_samples.pdf";
				String uri = "https://docs.google.com/gview?embedded=true&url="
						+ url;
				Intent intent = new Intent().setClass(getApplicationContext(),
						WebViewer.class);
				intent.putExtra("rowIndex", 8);
				intent.putExtra("uri", uri);
				startActivity(intent);
			} else {
				Toast toast = Toast.makeText(getApplicationContext(),
						"Internet not available", Toast.LENGTH_SHORT);
				toast.show();
			}

		} else if (position == 9 && rowPointer == 0) {
			isInternetAvailable = Utils
					.isInternetAvailable(getApplicationContext());
			if (isInternetAvailable) {
				String url = "http://www.career.umkc.edu/career/sites/default/files/pdfs/resume_samples.pdf";
				String uri = "https://docs.google.com/gview?embedded=true&url="
						+ url;
				Intent intent = new Intent().setClass(getApplicationContext(),
						WebViewer.class);
				intent.putExtra("rowIndex", 8);
				intent.putExtra("uri", uri);
				startActivity(intent);
			} else {
				Toast toast = Toast.makeText(getApplicationContext(),
						"Internet not available", Toast.LENGTH_SHORT);
				toast.show();
			}
		} else {

			Intent intent = new Intent().setClass(this,
					ResumeDevListContents.class);
			intent.putExtra("rowIndex", position);
			intent.putExtra("rowPointer", rowPointer);
			startActivity(intent);
		}
	}

}
