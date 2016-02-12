/**
 * 
 */
package ia.umkc.career;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author rr5h4
 * 
 */
public class ResumeDevActivity extends ListActivity {

	String[] menuName;
	Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.resume_dev_layout);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.custom_title_bar);
		context = getApplicationContext();
		TextView tv = new TextView(this);
		tv = (TextView) findViewById(R.id.resMaintextView2);
		tv.setText(Html
				.fromHtml("<html><body>Resumesdfsdfsdf critiques are available during appointment times - schedule in Roo Career Network</body></html>"));
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
			public void onItemClick(AdapterView<?> parentView, View childView,
					int position, long id) {
				getNextPage(position);
			}
		});
	}

	private void getNextPage(int position) {
		Intent intent = new Intent()
				.setClass(this, ResumeDevListContents.class);
		intent.putExtra("rowIndex", position);
		startActivity(intent);
	}
}
