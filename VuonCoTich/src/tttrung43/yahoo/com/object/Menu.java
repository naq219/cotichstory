package tttrung43.yahoo.com.object;

import android.graphics.drawable.Drawable;

public class Menu {
	int id;
	String title;
	Drawable icon;
	
	public Menu(int id, String title, Drawable icon){
		this.id = id;
		this.title = title;
		this.icon = icon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Drawable getIcon() {
		return icon;
	}

	public void setIcon(Drawable icon) {
		this.icon = icon;
	}		
}
