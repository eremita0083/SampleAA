package com.example.sampleannnotation;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

/* 導入
 * https://github.com/excilys/androidannotations/wiki/Eclipse-Project-Configuration　でDL
 * manifestのactivityのname属性で.MainActivity_のように後ろに_をつける。
 * compile-libsのフォルダを作成しjarを入れる、その後libsの中にapi---.jarを入れる
 * その後、プロパティからjava compiler→annotation processingを選び,enable annotation processingをチェック。
 * その後、その配下のfactory pathを選び、add jarsを選び、compile-libsにいれたjarファイルを指定する。これでAAが使用可能になる
 * 次のアクティビティを作成するときは、アクティビティのnameの最後に_をつけずに作成すること。その後に改名し、＿をつける。
*/

//EAcitivity()でsetContentViewを行い、androidannotationを使うことを示すことができる。また、IntentBuilderも生成される
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {
	
	//findviewbyidを行う。後ろの変数名にidを指定。カッコでIDを指定することも出来る、その時は変数名を変更することができる。
	@ViewById TextView textView;
	
	//findviewbyidを行いかつ、リスナを取り付ける。メソッド名はidと同じにしておくこと。カッコでIDを指定することも出来る、その時は関数名変更可能。
	@Click
	void button(){
		//viewbyidを行っていれば、そのウィジェットが使えるようになる
		textView.setText("ハローワールド");
	}
	
	//shared prefのAA
	@Pref
    MyPrefs_ myPrefs;

	
	//クリックしたら次のアクティビティへ
	@Click
	void toNextButton(){
		//Intent .textはNextActivityのメンバ変数に@Extraをつけている。
		new NextActivity_.IntentBuilder_(this).text(textView.getText().toString()).start();
		
		//spのnameにジョンを上書きする。
		myPrefs.name().put("John");
		
		//spのバッチ処理。editではじめ、関数名、put、関数名,put,,,,, .apply()で占める。
		myPrefs.edit()
		  .name()
		  .put("John")
		  .age()
		  .put(42)
		  .apply();
		
		// myPrefs.clear();　で消去
		
		// 値が存在するか確認する:
		boolean nameExists = myPrefs.name().exists();

		// 値を取得する
		long lastUpdated = myPrefs.lastUpdated().get();

		// 値を読み取り、その値がない場合はgetOrの引数が帰ってくる。
		long now = System.currentTimeMillis();
		long longlast = myPrefs.lastUpdated().getOr(now);
	}
	
	//@viewbyIdが読み込まれた後に呼び出される。
	@AfterViews
	void makeToast(){
		Toast.makeText(this,  textView.getText().toString() +" \n起動したよ", Toast.LENGTH_SHORT).show();
	}
	
}
