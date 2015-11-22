package com.demo.viewpagerdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyFragment extends Fragment {
	private int mArgument;
	private static final String ARGUMENT="argument";
	private View view;
	
	
	public static MyFragment newInstance(int argument){
		Bundle bundle=new Bundle();
		bundle.putInt(ARGUMENT, argument);
		MyFragment myFragment=new MyFragment();
		myFragment.setArguments(bundle);
		return  myFragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle bundle=getArguments();
		mArgument=bundle.getInt(ARGUMENT);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view=inflater.inflate(R.layout.fragment_layout, container,false);
		return view;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		TextView textView=(TextView) view.findViewById(R.id.txt);
		
		textView.setText("第"+mArgument+"页");
		
	}
	
}
