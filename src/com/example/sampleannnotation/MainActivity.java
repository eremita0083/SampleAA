package com.example.sampleannnotation;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.widget.TextView;

/*　https://github.com/excilys/androidannotations/wiki/Eclipse-Project-Configuration　でDL
 * manifestのactivityのname属性で.MainActivity_のように後ろに_をつける。
 * compile-libsのフォルダを作成しjarを入れる、その後libsの中にapi---.jarを入れる
 * その後、プロパティからjava compiler→annotation processingを選び,enable annotation processingをチェック。
 * その後、その配下のfactory pathを選び、add jarsを選び、compile-libsにいれたjarファイルを指定する。これでAAが使用可能になる
*/
//EAcitivity()でsetContentViewを行い、androidannotationを使うことを示すことができる
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {
	
	//findviewbyidを行う。後ろの変数名にidを指定
	@ViewById
	TextView textView;
	
	//findviewbyidを行いかつ、リスナを取り付ける。メソッド名はidと同じにしておくこと
	@Click
	void button(){
		//viewbyidを行っていれば、そのウィジェットが使えるようになる
		textView.setText("ハローワールド");
	}
	
	
}
