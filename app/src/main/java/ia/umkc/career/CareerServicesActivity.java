package ia.umkc.career;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

public class CareerServicesActivity extends ListActivity {

	String[] menuName;
	ListView lv;
	WindowManager wm;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.custom_title_bar);

		int layout = R.layout.homescreen;

		menuName = getResources().getStringArray(R.array.menuName);
		List<MainMenuDTO> data = new ArrayList<MainMenuDTO>();
		int menuLength = menuName.length;
		for (int i = 0; i < menuLength; i++) {
			data.add(new MainMenuDTO(menuName[i]));
		}

		setListAdapter(new MainMenuAdapter(this, data, layout));

		lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parentView, View childView,
					int position, long id) {
				getNextPage(position);
			}
		});
	}

	private void getNextPage(int index) {
		Context context = getApplicationContext();
		if (index == 0) {
			Intent intent = new Intent().setClass(context, Location.class);
			startActivity(intent);
		} else if (index == 1) {
			Intent intent = new Intent().setClass(context, RooCareerList.class);
			intent.putExtra("rowIndex", 0);
			startActivity(intent);
		} else if (index == 3) {
			Intent intent = new Intent()
					.setClass(context, MainPageViewer.class);
			intent.putExtra("rowIndex", 0);
			startActivity(intent);
		} else if (index == 4) {
			Intent intent = new Intent()
					.setClass(context, MainPageViewer.class);
			intent.putExtra("rowIndex", 1);
			startActivity(intent);
		} else if (index == 5) {
			Intent intent = new Intent()
					.setClass(context, MainPageViewer.class);
			intent.putExtra("rowIndex", 2);
			startActivity(intent);
		} else if (index == 6) {
			Intent intent = new Intent(getApplicationContext(),
					UpcomingEvents.class);
			startActivity(intent);
		} else if (index == 7) {
			Intent intent = new Intent().setClass(context, VirtualOffice.class);
			startActivity(intent);
		} else if (index == 8) {
			//blog
			Intent intent = new Intent()
					.setClass(context, MainPageViewer.class);
			intent.putExtra("rowIndex", 4);
			startActivity(intent);
		} else if (index == 9) {
			Intent intent = new Intent()
					.setClass(context, FeedbackActivity.class);
			startActivity(intent);
		} else {
			Intent intent = new Intent().setClass(context, WebViewer.class);
			intent.putExtra("rowIndex", index);
			startActivity(intent);
		}
	}

}