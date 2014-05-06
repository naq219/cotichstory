package tttrung43.yahoo.com.vuoncotich;

import tttrung43.yahoo.com.object.Connection;
import tttrung43.yahoo.com.object.Global;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class DetailStoryActivity extends Activity {
	private WebView web;
	private CheckBox chkFavorite;
	private Button btnBack;
	private int mId;
	private TextView tvTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_story_layout);
		chkFavorite = (CheckBox) findViewById(R.id.chkFavorite);
		web = (WebView) findViewById(R.id.web);
		tvTitle = (TextView)findViewById(R.id.tvTitle);
		btnBack = (Button)findViewById(R.id.btnBack);
		btnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();				
			}
		});
		
		tvTitle.setText(Global.TITLE);
		web.getSettings().setBuiltInZoomControls(true);
		web.getSettings().setDisplayZoomControls(true);
		web.getSettings().setTextZoom(130);
		Intent intent = getIntent();
		mId = intent.getIntExtra("id", 0);
		LoadContent();
		chkFavorite.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Connection conn = new Connection(DetailStoryActivity.this);
				SQLiteDatabase db = conn.getWritableDatabase();
				int fa = chkFavorite.isChecked() ? 1 : 0;
				if(fa==0){
					setResult(RESULT_OK);
				}
				db.execSQL("update truyen set yeuthich =" + fa + " where id = "
						+ mId);
				db.close();
				conn.close();
			}
		});
	}

	private void LoadContent() {
		Connection conn = new Connection(this);
		SQLiteDatabase db = conn.getReadableDatabase();
		Cursor result = db
				.rawQuery(
						"select tieude, noidung, yeuthich from truyen where id= "
								+ mId, null);
		result.moveToFirst();
		chkFavorite.setChecked(result.getInt(2) == 1 ? true : false);
		String data = "<h2><span style='color: #003399 ;'>"
				+ result.getString(0) + "</h2>" + result.getString(1);
		web.loadDataWithBaseURL(null, data, "text/html", "utf-8", null);
		result.close();
		db.close();
		conn.close();
	}
}
