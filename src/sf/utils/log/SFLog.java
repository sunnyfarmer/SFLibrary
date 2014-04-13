package sf.utils.log;

import android.util.Log;

public class SFLog {
	public static final String TAG = "SFLog";
	
	public static void i(String tag, String msg) {
		Log.i(tag, msg);
	}

	public static void e(String tag, String msg) {
		Log.e(tag, msg);
	}
	
	public static void d(String tag, String msg) {
		Log.d(tag, msg);
	}
	
	public static void w(String tag, String msg) {
		Log.w(tag, msg);
	}
}
