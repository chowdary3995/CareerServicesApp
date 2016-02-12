/**
 * 
 */
package ia.umkc.career;

/**
 * @author rr5h4
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Demonstrates expandable lists backed by a Simple Map-based adapter
 */
public class VirtualOffice extends ExpandableListActivity {
	private static final String NAME = "NAME";
	private String[] parentGroup;

	private ExpandableListAdapter mAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.virtoffice_layout);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.custom_title_bar);
		TextView title2= (TextView) findViewById(R.id.myTitle);
		title2.setText("Virtual Office/Videos");
		List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
		List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
		parentGroup = getResources().getStringArray(R.array.ParentGroup);

		for (int i = 0; i < parentGroup.length; i++) {
			Map<String, String> curGroupMap = new HashMap<String, String>();
			groupData.add(curGroupMap);
			curGroupMap.put(NAME, parentGroup[i]);

			List<Map<String, String>> children = new ArrayList<Map<String, String>>();

			switch (i) {
			case 0:
				String[] one = getResources().getStringArray(
						R.array.FreeOnlineMagazines);
				for (int j = 0; j < one.length; j++) {
					Map<String, String> curChildMap = new HashMap<String, String>();
					children.add(curChildMap);
					curChildMap.put(NAME, one[j]);
				}
				break;
			case 1:
				String[] two = getResources().getStringArray(
						R.array.FreeCareerAssessments);
				for (int j = 0; j < two.length; j++) {
					Map<String, String> curChildMap = new HashMap<String, String>();
					children.add(curChildMap);
					curChildMap.put(NAME, two[j]);
				}
				break;
			case 2:
				String[] three = getResources().getStringArray(
						R.array.HelpfulVideos);
				for (int j = 0; j < three.length; j++) {
					Map<String, String> curChildMap = new HashMap<String, String>();
					children.add(curChildMap);
					curChildMap.put(NAME, three[j]);
				}
				break;
			case 3:
				String[] four = getResources().getStringArray(
						R.array.Presentations);
				for (int j = 0; j < four.length; j++) {
					Map<String, String> curChildMap = new HashMap<String, String>();
					children.add(curChildMap);
					curChildMap.put(NAME, four[j]);
				}
				break;
			}
			childData.add(children);
		}

		// Set up our adapter
		mAdapter = new SimpleExpandableListAdapter(this, groupData,
				R.layout.group_row, new String[] { NAME },
				new int[] { R.id.row_name }, childData, R.layout.child_row,
				new String[] { NAME }, new int[] { R.id.grp_child });

		//LayoutInflater infalInflater = (LayoutInflater) getApplicationContext()
			//	.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//View convertView1 = infalInflater.inflate(R.layout.explist_one, null);
		//convertView1.setBackgroundResource(R.drawable.swinney_cell_bg);

		//View convertView2 = infalInflater.inflate(R.layout.explist_two, null);
		//convertView2.setBackgroundResource(R.drawable.swinney_cell_bg);

		setListAdapter(mAdapter);

		getExpandableListView().setOnChildClickListener(this);

	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		// use groupPosition and childPosition to locate the current item in the
		// adapter
		switch (groupPosition) {
		case 0:
			getMagazineView(childPosition);
			break;
		case 1:
			getCareerView(childPosition);
			break;
		case 2:
			getVideoView(childPosition);
			break;
		case 3:
			getPresentationView(childPosition);
			break;

		default:
			break;
		}
		return true;
	}

	private void getMagazineView(int child) {
		switch (child) {
		case 0:
			Uri uri = Uri.parse("http://www.jobchoicesonline.com/");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	private void getCareerView(int child) {

		Uri uri = null;
		Intent intent = null;
		switch (child) {
		case 0:
			uri = Uri.parse("http://www.sigi3.org/login.asp");
			intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
			break;
		case 1:
			uri = Uri.parse("http://www.keirsey.com/");
			intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
			break;
		case 2:
			uri = Uri.parse("http://www.self-directed-search.com/");
			intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
			break;
		case 3:
			uri = Uri.parse("http://www.personalitytype.com/");
			intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
			break;
		case 4:
			uri = Uri.parse("http://www.assessment.com/");
			intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	private void getVideoView(int child) {

		switch (child) {
		case 0:
			startActivity(new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://www.youtube.com/watch?v=550wGEbXhn4")));
			break;
		case 1:
			startActivity(new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://www.youtube.com/watch?v=EU7Qe4UJIsU")));
			break;
		case 2:
			startActivity(new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://www.youtube.com/watch?v=YPav3Ru7qT0")));
			break;
		case 3:
			startActivity(new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://www.youtube.com/watch?v=WJOoyUPow-A")));
			break;
		case 4:
			startActivity(new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://www.youtube.com/watch?v=550wGEbXhn4")));
			break;
		default:
			break;
		}
	}

	private void getPresentationView(int child) {
		String url = null;
		boolean isInternetAvailable = false;
		if (child == 0) {
			url = "http://www.umkc.edu/intapps/json/pdf/DRESS%20TO%20IMPRESS.pdf";
		} else if (child == 1) {
			url = "http://www.umkc.edu/intapps/json/pdf/Dining%20Etiquette.pdf";
		}
		isInternetAvailable = Utils.isInternetAvailable(getApplicationContext());
		if (isInternetAvailable) {
			// use Google Docs Viewer
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

	}

	public void onFacebookClick(View view) {
		Uri uri = Uri
				.parse("http://m.facebook.com/pages/UMKC-Career-Services-Center/12616294316");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}

	public void onTwitterClick(View view) {
		Uri uri = Uri.parse("https://mobile.twitter.com/umkcroocareers");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}

	public void onFeedClick(View view) {
		Uri uri = Uri.parse("http://careersumkc.blogspot.com/");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}
}