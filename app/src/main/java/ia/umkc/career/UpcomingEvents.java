/**
 * 
 */
package ia.umkc.career;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

/**
 * @author kcsc95
 * 
 */
public class UpcomingEvents extends ExpandableListActivity {
	Map<String, List<Map<String, String>>> events;
	EditText eventSearch;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.upcoming_events);

		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.custom_title_bar);
		TextView title= (TextView) findViewById(R.id.myTitle);
		title.setText("Upcoming Events");
		events = new LinkedHashMap<String, List<Map<String, String>>>();
		ExpandableListView expandableListView = getExpandableListView();
		expandableListView.setClickable(true);
		// expandableListView.setTextFilterEnabled(true);
		// expandableListView.setFocusable(true);

		getEventsFromDB();
		SuperAdapter expListAdapter = new SuperAdapter(this,
				createGroupList(events), R.layout.group_row,
				new String[] { "GroupItem" }, new int[] { R.id.row_name },
				createChildList(events), R.layout.child_row,
				new String[] { "ChildItem" }, new int[] { R.id.grp_child });
		setListAdapter(expListAdapter);
		int groupCount = expListAdapter.getGroupCount();
		for (int i = 0; i < groupCount; i++) {
			expandableListView.expandGroup(i);
		}
		eventSearch = (EditText) findViewById(R.id.eventSearch);
		eventSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				(((SuperAdapter) getExpandableListAdapter())).getFilter()
						.filter(eventSearch.getText().toString());
			}
		});
	}

	@Override
	@SuppressWarnings(value = { "unchecked" })
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		HashMap group = (HashMap) getExpandableListAdapter().getGroup(
				groupPosition);
		String childEventName = (String) ((HashMap) getExpandableListAdapter()
				.getChild(groupPosition, childPosition)).get("ChildItem");
		List<HashMap<String, String>> childList = (ArrayList) events.get(group
				.get("GroupItem"));
		HashMap selectedChild = null;
		for (HashMap child : childList) {
			if (child.get("Event_name").equals(childEventName)) {
				selectedChild = child;
				break;
			}
		}

		Intent intent = new Intent(getApplicationContext(),
				EventDetailsActivity.class);
		intent.putExtra("Event_name", (String) selectedChild.get("Event_name"));
		intent.putExtra("start_date", (String) selectedChild.get("start_date"));
		intent.putExtra("start_time", (String) selectedChild.get("start_time"));
		intent.putExtra("end_date", (String) selectedChild.get("end_date"));
		intent.putExtra("end_time", (String) selectedChild.get("end_time"));
		intent.putExtra("Event_body", (String) selectedChild.get("Event_body"));
		intent.putExtra("url", (String) selectedChild.get("url"));
		startActivity(intent);
		return true;
	}

	private List<Map<String, String>> createGroupList(
			Map<String, List<Map<String, String>>> eventsLocal) {
		List<Map<String, String>> groupsList = new ArrayList<Map<String, String>>();
		HashMap<String, String> group;
		for (Object key : eventsLocal.keySet().toArray()) {
			group = new HashMap<String, String>();
			group.put("GroupItem", (String) key);
			groupsList.add(group);
		}
		return groupsList;
	}

	private List<? extends List<? extends Map<String, ?>>> createChildList(
			Map<String, List<Map<String, String>>> eventsLocal) {
		List<List<Map<String, String>>> childList = new ArrayList<List<Map<String, String>>>();
		List<Map<String, String>> subList;
		HashMap<String, String> child;
		for (Object key : eventsLocal.keySet().toArray()) {
			subList = new ArrayList<Map<String, String>>();

			for (Map<String, String> map : eventsLocal.get((String) key)) {
				child = new HashMap<String, String>();
				child.put("ChildItem", (String) map.get("Event_name"));
				subList.add(child);
			}
			childList.add(subList);
		}
		return childList;
	}

	private void getEventsFromDB() {
		//old http://php.umkc.edu/intapps/events.php
		//http://php.umkc.edu/intapps/json/career-services-events.php
		HttpGet httpGet = new HttpGet("http://php.umkc.edu/intapps/json/career-services-events.php");
		String json = Utils.readJSONFeed(httpGet);
		try {
			JSONObject jsonObject;
			JSONArray jsonArray = new JSONArray(json);
			Map<String, String> eventDetails;
			List<Map<String, String>> eventsList;
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				eventDetails = new HashMap<String, String>();
				jsonObject = jsonArray.getJSONObject(i);
				eventDetails.put("Event_name", jsonObject.get("Event_name")
						.toString());
				eventDetails.put("start_date", jsonObject.get("start_date")
						.toString());
				eventDetails.put("start_time", jsonObject.get("start_time")
						.toString());
				eventDetails.put("end_date", jsonObject.get("end_date")
						.toString());
				eventDetails.put("end_time", jsonObject.get("end_time")
						.toString());
				eventDetails.put("url", jsonObject.get("url").toString());
				eventDetails.put("Event_body", jsonObject.get("Event_body")
						.toString());
				eventDetails.put("Event_id", jsonObject.get("Event_id")
						.toString());
				String key = getWeek(jsonObject.get("start_date").toString());
				if (key == null)
					key = "other";
				if (events.containsKey(key)) {
					events.get(key).add(eventDetails);
				} else {
					eventsList = new ArrayList<Map<String, String>>();
					eventsList.add(eventDetails);
					events.put(key, eventsList);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private String getWeek(String startDate) {
		if (startDate == null)
			return null;
		String[] dateArr = startDate.split("-");
		Calendar cal = Calendar.getInstance();
		int sdate = Integer.parseInt(dateArr[2]);
		// cal month starts from 0, not 1.
		int smonth = Integer.parseInt(dateArr[1]) - 1;
		int syear = Integer.parseInt(dateArr[0]);
		cal.set(syear, smonth, sdate);
		System.out.println(cal.get(Calendar.DAY_OF_WEEK));
		while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
			System.out.println(cal.get(Calendar.DAY_OF_WEEK));
			cal.add(Calendar.DATE, -1);
		}
		StringBuffer weekDates = new StringBuffer("Week: "
				+ cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.DATE) + "/"
				+ cal.get(Calendar.YEAR) + " - ");
		cal.add(Calendar.DATE, 6);
		weekDates.append(cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.DATE)
				+ "/" + cal.get(Calendar.YEAR));
		return weekDates.toString();
	}

	public class EventFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence searchText) {
			Map<String, List<Map<String, String>>> searchedEvents;
			List<Map<String, String>> subList;
			searchText = eventSearch.getText().toString().toLowerCase();
			FilterResults results = new FilterResults();
			if (searchText != null && searchText.length() > 0) {
				searchedEvents = new LinkedHashMap<String, List<Map<String, String>>>();

				for (Object key : events.keySet().toArray()) {
					for (Map<String, String> map : events.get((String) key)) {
						if (map.get("Event_name").toLowerCase()
								.contains(searchText)) {
							if (searchedEvents.containsKey(map
									.get("start_date"))) {
								searchedEvents.get(map.get("start_date")).add(
										map);
							} else {
								subList = new ArrayList<Map<String, String>>();
								subList.add(map);
								searchedEvents.put(map.get("start_date"),
										subList);
							}
						}
					}
				}
			} else {
				searchedEvents = events;
			}
			results.values = searchedEvents;
			return results;
		}

		@Override
		protected void publishResults(CharSequence arg0, FilterResults results) {
			if (results == null)
				return;
			Map<String, List<Map<String, String>>> searchedEvents = (Map<String, List<Map<String, String>>>) results.values;
			SuperAdapter expListAdapter = new SuperAdapter(
					getApplicationContext(), createGroupList(searchedEvents),
					R.layout.group_row, new String[] { "GroupItem" },
					new int[] { R.id.row_name },
					createChildList(searchedEvents), R.layout.child_row,
					new String[] { "ChildItem" }, new int[] { R.id.grp_child });
			setListAdapter(expListAdapter);
			int groupCount = expListAdapter.getGroupCount();
			for (int i = 0; i < groupCount; i++) {
				getExpandableListView().expandGroup(i);
			}
		}
	}

	public class SuperAdapter extends SimpleExpandableListAdapter implements
			Filterable {

		private Filter eventFilter;

		public SuperAdapter(Context context,
				List<? extends Map<String, ?>> groupData, int groupLayout,
				String[] groupFrom, int[] groupTo,
				List<? extends List<? extends Map<String, ?>>> childData,
				int childLayout, String[] childFrom, int[] childTo) {
			super(context, groupData, groupLayout, groupFrom, groupTo,
					childData, childLayout, childFrom, childTo);
		}

		@Override
		public Filter getFilter() {
			if (eventFilter == null)
				eventFilter = new EventFilter();
			return eventFilter;
		}

	}

}
