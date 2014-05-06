package tttrung43.yahoo.com.vuoncotich;

import java.util.ArrayList;
import java.util.List;

import tttrung43.yahoo.com.adapter.StoryAdapter;
import tttrung43.yahoo.com.object.Connection;
import tttrung43.yahoo.com.object.Global;
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
import android.widget.ListView;
import android.widget.TextView;

public class StoryActivity extends Activity {

	private ListView lstStory;
	private TextView tvTitle;
	private Button btnBack;
	private StoryAdapter mStoryAdapter;
	private List<Story> data;
	private int mId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.story_layout);		
		Intent intent = getIntent();
		mId = intent.getIntExtra("id", 0);
		lstStory = (ListView) findViewById(R.id.lstStory);
		tvTitle = (TextView) findViewById(R.id.tvTitle);
		btnBack = (Button)findViewById(R.id.btnBack);
		btnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				finish();
			}
		});
		tvTitle.setText(Global.TITLE);

		LoadStory();

		lstStory.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int pos,
					long arg3) {
				Intent intent = new Intent(StoryActivity.this,
						DetailStoryActivity.class);
				intent.putExtra("id", data.get(pos).getId());
				startActivity(intent);
			}
		});
	}

	private void LoadStory() {
		data = new ArrayList<Story>();
		Story story;
		Connection conn = new Connection(this);
		SQLiteDatabase db = conn.getReadableDatabase();
		Cursor result = db.query("truyen", new String[] { "id", "tieude" },
				"chudeid = ?", new String[] { "" + mId }, null, null, null);
		while (result.moveToNext()) {
			story = new Story(result.getInt(0), result.getString(1));
			data.add(story);
		}
		result.close();
		db.close();
		conn.close();

		mStoryAdapter = new StoryAdapter(this, data);
		lstStory.setAdapter(mStoryAdapter);
	}
}
