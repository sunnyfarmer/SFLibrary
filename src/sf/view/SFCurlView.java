package sf.view;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class SFCurlView extends GLSurfaceView {
	public static final String TAG = "SFCurlView";

	private Renderer mRenderer = null;
	
	public SFCurlView(Context context) {
		super(context);

		this.mRenderer = new SFCurlRender();
		this.setRenderer(mRenderer);
	}

}
