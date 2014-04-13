package sf.view;

import sf.utils.log.SFLog;
import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewDebug.CapturedViewProperty;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

public class SFPageView extends AdapterView<BaseAdapter> {
	public static final String TAG = "SFPageView";

	private BaseAdapter mBaseAdapter = null;
	
	private int mSelectionIndex = -1;

	private SparseArray<View> mBufferedViewArray = null;

	private DataSetObserver mDataSetObserver = null;
	
	public SFPageView(Context context) {
		super(context);
		this.init();
	}
	
	public SFPageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.init();
	}

	public SFPageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.init();
	}
	
	private void init() {
		this.mBufferedViewArray = new SparseArray<View>();
	}

	@Override
	@CapturedViewProperty
	public int getCount() {
		return this.mBaseAdapter.getCount();
	}
	
	@Override
	public BaseAdapter getAdapter() {
		return this.mBaseAdapter;
	}

	@Override
	public void setAdapter(BaseAdapter adapter) {
		if (this.mDataSetObserver!=null) {
			this.mBaseAdapter.unregisterDataSetObserver(this.mDataSetObserver);
			this.mDataSetObserver = null;
		}

		this.mBaseAdapter = adapter;
		this.mDataSetObserver = new SFAdapterDataSetObserver();
		this.mBaseAdapter.registerDataSetObserver(this.mDataSetObserver);
		
		this.setSelection(2);
	}

	@Override
	public View getSelectedView() {
		if (this.mBufferedViewArray.size()>0) {
			this.mBufferedViewArray.setValueAt(0, this.mBaseAdapter.getView(this.mSelectionIndex, this.mBufferedViewArray.get(0), this));
		} else {
			this.mBufferedViewArray.put(0, this.mBaseAdapter.getView(this.mSelectionIndex, null, this));
		}
		return this.mBufferedViewArray.get(0);
	}

	@Override
	public void setSelection(int position) {
SFLog.d(TAG, "setSelection: " + position);
		this.mSelectionIndex = position;

		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		this.addViewInLayout(this.getSelectedView(), 0, params);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);

		this.getSelectedView().layout(left, top, right, bottom);
	}
	
	private void onDataChanged() {
	}

	public class SFAdapterDataSetObserver extends DataSetObserver {
		@Override
		public void onChanged() {
			super.onChanged();
			onDataChanged();
		}
		@Override
		public void onInvalidated() {
			super.onInvalidated();
			onDataChanged();
		}
	}
}
