package tttrung43.yahoo.com.adapter;

import java.util.List;

import tttrung43.yahoo.com.object.Story;
import tttrung43.yahoo.com.vuoncotich.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StoryAdapter extends BaseAdapter{

	private ViewHolder holder;
	private LayoutInflater inflater;
	private List<Story> data;
	
	public StoryAdapter(Context context, List<Story>data){
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.data = data;
	}
	
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.story_item, null);
			holder.tvId =(TextView)convertView.findViewById(R.id.tvId);
			holder.tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
			convertView.setTag(holder);
			
		}else
			holder = (ViewHolder)convertView.getTag();
		holder.tvId.setText(""+data.get(position).getId());
		holder.tvTitle.setText(data.get(position).getTitle());
		return convertView;
	}

	static class ViewHolder{
		TextView tvId, tvTitle;
	}
}
