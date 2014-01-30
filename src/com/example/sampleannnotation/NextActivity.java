package com.example.sampleannnotation;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.widget.TextView;

@EActivity(R.layout.activity_next)
public class NextActivity extends Activity {

	@ViewById TextView text_next;
	
	//遷移先のアクティビティに@extraをつけたメンバ変数を置くと、intentの際にputExtraとして値を受け取れる。
	@Extra String text;
	
	@AfterViews
	void displayText(){
		text_next.setText(text);
	}
}
