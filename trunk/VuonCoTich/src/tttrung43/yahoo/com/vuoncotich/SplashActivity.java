package tttrung43.yahoo.com.vuoncotich;

import java.io.File;

import tttrung43.yahoo.com.object.Connection;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashActivity extends Activity{
	private ImageView img;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_layout);
		img = (ImageView)findViewById(R.id.imgview);
		img.setBackgroundResource(R.drawable.bg_loading);
		AnimationDrawable animation = (AnimationDrawable)img.getBackground();
		animation.start();
		new Initialize(this,SplashActivity.this).execute();
	}

	class Initialize extends AsyncTask<Void, Void, Void>{
		private Context context;
		private Activity activity;		
		public Initialize(Context context,Activity activity){
			this.context = context;
			this.activity = activity;			
		}
				
		
		@Override
		protected Void doInBackground(Void... params) {
			try {							
				File dbFile = new File("/data/data/"+context.getPackageName()+"/databases/vuoncotich.db");								
				if(!dbFile.exists())
				{				
					Connection conn = new Connection(context);
					conn.createDatabase();
					conn.close();
				}											
				Thread.sleep(800);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);			
			activity.finish();
			Intent intent = new Intent(context,MainActivity.class);
			context.startActivity(intent);
		}
	}
}
