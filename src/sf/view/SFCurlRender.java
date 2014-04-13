package sf.view;

import static javax.microedition.khronos.opengles.GL10.GL_COLOR_BUFFER_BIT;
import static javax.microedition.khronos.opengles.GL10.GL_DEPTH_BUFFER_BIT;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import sf.utils.log.SFLog;

import android.opengl.GLSurfaceView.Renderer;

public class SFCurlRender implements Renderer {
	public static final String TAG = "SFCurlRender";

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		SFLog.d(TAG, "onSurfaceCreated");
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		SFLog.d(TAG, "onSurfaceChanged");
		gl.glViewport(0, 0, width, height);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		SFLog.d(TAG, "onDrawFrame");

		gl.glClearColor(1.0f, 0.0f, 0.0f, 0.5f);
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	}

}
