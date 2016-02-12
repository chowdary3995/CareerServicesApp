package ia.umkc.career;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class RooCareerList extends ListActivity {
	String[] menuName;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.custom_title_bar);
		TextView title= (TextView) findViewById(R.id.myTitle);
		title.setText("Career Network");
		menuName = getResources().getStringArray(R.array.rooCareerMenuName);
		List<MainMenuDTO> coverData = new ArrayList<MainMenuDTO>();
		for (int i = 0; i < menuName.length; i++) {
			coverData.add(new MainMenuDTO(menuName[i]));
		}
		int cvrLayout = R.layout.homescreen;
		/*TextView tv = (TextView) findViewById(R.id.mainTxt1);
		tv.setText(Html.fromHtml("<font color=\"#FABE00\"><a href=\"https://umkc-csm.symplicity.com/\" >Roo Career Network</a></font>"));
		tv.setMovementMethod(LinkMovementMethod.getInstance());*/
		ImageView iv = (ImageView) findViewById(R.id.imageView1);
		iv.setImageResource(R.drawable.rcn_logo_small);
		iv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String url = "https://umkc-csm.symplicity.com/";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
			}
		});
		setListAdapter(new ResumeListAdapter(this, coverData, cvrLayout));
		ListView lv1 = getListView();
		lv1.setTextFilterEnabled(true);
		lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parentView, View childView,
					int position, long id) {
				getNextPage(position);
			}

			private void getNextPage(int position) {
				Intent intent = new Intent().setClass(getApplicationContext(), RooCareerWebViewer.class);
				intent.putExtra("rowIndex", position);
				startActivity(intent);
			}
		});
	}
}