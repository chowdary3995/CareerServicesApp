/**
 * 
 */
package ia.umkc.career;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author rr5h4
 * 
 */
public class ResumeListAdapter extends ArrayAdapter<MainMenuDTO> {
	private LayoutInflater inflater;
	private List<MainMenuDTO> data;
	public ResumeListAdapter(Context context, List<MainMenuDTO> objects,
			int layout) {
		super(context, R.layout.homescreen, objects);
		inflater = LayoutInflater.from(context);
		this.data = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.homescreen, null);
			holder = new ViewHolder();
			holder.image = (ImageView) convertView.findViewById(R.id.imgView);
			holder.name = (TextView) convertView.findViewById(R.id.textView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		//convertView.setBackgroundResource(R.drawable.swinney_cell_bg);
		holder.name.setText((CharSequence) data.get(position).getMenuName());
		holder.image.setImageResource(R.drawable.menumarker);
		return convertView;
	}

	static class ViewHolder {
		ImageView image;
		TextView name;
	}

}
