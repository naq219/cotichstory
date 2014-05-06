package tttrung43.yahoo.com.vuoncotich;

import java.util.ArrayList;
import java.util.List;

import com.google.ads.AdRequest;
import com.google.ads.InterstitialAd;

import tttrung43.yahoo.com.adapter.MenuAdapter;
import tttrung43.yahoo.com.object.Global;
import tttrung43.yahoo.com.object.Menu;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends Activity {
	private GridView gridMenu;
	private MenuAdapter mMenuAdapter;
	private List<Menu> data;
	private InterstitialAd interstitial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Create the interstitial
		interstitial = new InterstitialAd(this, "6cc8b4f245d743f9");
		// Create ad request
		AdRequest adRequest = new AdRequest();
		// Begin loading your interstitial
		interstitial.loadAd(adRequest);

		gridMenu = (GridView) findViewById(R.id.gridMenu);
		mMenuAdapter = new MenuAdapter(this, data);
		InitMenu();
		gridMenu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int pos,
					long arg3) {
				Intent intent;
				switch (data.get(pos).getId()) {
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
					intent = new Intent(MainActivity.this, StoryActivity.class);
					intent.putExtra("id", data.get(pos).getId());
					Global.TITLE = data.get(pos).getTitle();
					startActivity(intent);
					break;
				case 7:
					intent = new Intent(MainActivity.this,
							FavoriteActivity.class);
					Global.TITLE = data.get(pos).getTitle();
					startActivity(intent);
					break;
				case 8:/*
						 * AuthorDialog author = new
						 * AuthorDialog(MainActivity.this); author.show();
						 */
					Intent otherApps = new Intent(Intent.ACTION_VIEW);
					otherApps.setData(Uri
							.parse("https://play.google.com/store/apps/details?id=tttrung43.wordpress.com.englishstudy"));
					startActivity(otherApps);
					break;
				}
			}
		});
	}
	
	@Override
	public void onBackPressed() {	
		super.onBackPressed();
		if(interstitial!=null)
			interstitial.show();
	}

	private void InitMenu() {
		data = new ArrayList<Menu>();
		data.add(new Menu(1, "Việt Nam", getResources().getDrawable(
				R.drawable.flower)));
		data.add(new Menu(2, "Anderson", getResources().getDrawable(
				R.drawable.pig)));
		data.add(new Menu(3, "Thế giới", getResources().getDrawable(
				R.drawable.world)));
		data.add(new Menu(4, "Truyền thuyết", getResources().getDrawable(
				R.drawable.eye)));
		data.add(new Menu(5, "Grim", getResources().getDrawable(R.drawable.fox)));
		data.add(new Menu(6, "Thần thoại", getResources().getDrawable(
				R.drawable.electric)));
		data.add(new Menu(7, "Yêu thích", getResources().getDrawable(
				R.drawable.star)));
		data.add(new Menu(8, "More Apps", getResources().getDrawable(
				R.drawable.more_app)));
		mMenuAdapter = new MenuAdapter(this, data);
		gridMenu.setAdapter(mMenuAdapter);
	}

}
