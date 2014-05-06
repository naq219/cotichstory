package tttrung43.yahoo.com.vuoncotich;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class AuthorDialog extends Dialog{

	public AuthorDialog(Context context) {
		super(context);		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.author_dialog);
		setCancelable(false);
		View v = getWindow().getDecorView();
		v.setBackgroundColor(android.R.color.transparent);
		Button btn = (Button)findViewById(R.id.btnOk);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
	}

}
