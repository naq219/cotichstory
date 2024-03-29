package tttrung43.yahoo.com.object;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Connection extends SQLiteOpenHelper{

	private static String DB_PATH ="";
	private static String DB_NAME="vuoncotich.db";	
	private Context myContext;
	public Connection(Context context) {
		super(context, "vuoncotich.db", null, 1);
		this.myContext = context;
		DB_PATH="/data/data/"+context.getPackageName()+"/databases/";
	}

	
	public void createDatabase(){
		boolean dbExist = checkDatabase();
		if(!dbExist){
			this.getReadableDatabase();
			try{
				copyDatabase();
			}catch(Exception ex){}
		}
	}
	
	private boolean checkDatabase(){
		SQLiteDatabase checkDB = null;
		try{
			String myPath =DB_PATH+DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		}catch(Exception ex){}
		if(checkDB!=null){
			checkDB.close();
			return true;
		}else return false;
	}
	
	public void copyDatabase()
	{
		try {
			InputStream myInput = myContext.getAssets().open(DB_NAME);			
			String filename=DB_PATH+DB_NAME;
			OutputStream myOutput = new FileOutputStream(filename);
			byte[] buffer = new byte[100];
			int length;
			while((length=myInput.read(buffer))>0){
				myOutput.write(buffer,0,length);
			}
			myOutput.flush();
			myOutput.close();
			myInput.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void onCreate(SQLiteDatabase db) {		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
	}
	
	
}
