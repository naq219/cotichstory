package tttrung43.yahoo.com.vuoncotich;

import java.util.ArrayList;
import java.util.List;

import tttrung43.yahoo.com.adapter.StoryAdapter;
import tttrung43.yahoo.com.object.Connection;
import tttrung43.yahoo.com.object.Story;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class FavoriteActivity extends Activity {

	private LinearLayout layoutNotFound;
	private ListView lstStory;
	private Button btnBack;
	private List<Story> data;
	private StoryAdapter mStoryAdapter;
	private int curPos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.favorite_layout);
		layoutNotFound = (LinearLayout) findViewById(R.id.layoutNotfound);
		lstStory = (ListView) findViewById(R.id.lstStory);
		btnBack = (Button)findViewById(R.id.btnBack);
		btnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		LoadFavoriteStory();
		lstStory.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				Intent intent = new Intent(FavoriteActivity.this,
						DetailStoryActivity.class);
				intent.putExtra("id", data.get(pos).getId());
				curPos = pos;
				startActivityForResult(intent, 100);
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data1) {
		super.onActivityResult(requestCode, resultCode, data1);
		if (requestCode == 100 && resultCode == RESULT_OK) {
			data.remove(curPos);
			mStoryAdapter.notifyDataSetChanged();
			if(data.size()==0){
				layoutNotFound.setVisibility(View.VISIBLE);
				lstStory.setVisibility(View.GONE);
			}
		}
	}

	void LoadFavoriteStory() {
		data = new ArrayList<Story>();
		Story story;
		Connection conn = new Connection(this);
		SQLiteDatabase db = conn.getReadableDatabase();
		Cursor result = db.query("truyen", new String[] { "id", "tieude" },
				"yeuthich = ?", new String[] { "1" }, null, null, null);
		while (result.moveToNext()) {
			story = new Story(result.getInt(0), result.getString(1));
			data.add(story);
		}
		result.close();
		db.close();
		conn.close();
		if (data.size() > 0) {
			mStoryAdapter = new StoryAdapter(this, data);
			lstStory.setAdapter(mStoryAdapter);
			lstStory.setVisibility(View.VISIBLE);
			layoutNotFound.setVisibility(View.GONE);
		} else {
			lstStory.setVisibility(View.GONE);
			layoutNotFound.setVisibility(View.VISIBLE);
		}
	}
}
