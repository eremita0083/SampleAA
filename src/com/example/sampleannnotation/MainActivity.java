package com.example.sampleannnotation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import android.app.Activity;
import android.net.http.AndroidHttpClient;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

	// findviewbyidを行う。後ろの変数名にidを指定。カッコでIDを指定することも出来る、その時は変数名を変更することができる。
	@ViewById
	TextView textView;

	// findviewbyidを行いかつ、リスナを取り付ける。メソッド名はidと同じにしておくこと。カッコでIDを指定することも出来る、その時は関数名変更可能。
	@Click
	void button() {
		// viewbyidを行っていれば、そのウィジェットが使えるようになる
		textView.setText("ハローワールド");
	}

	// クリックしたら次のアクティビティへ
	@Click
	void toNextButton() {
		// Intent .textはNextActivityのメンバ変数に@Extraをつけている。
		new NextActivity_.IntentBuilder_(this).text(
				textView.getText().toString()).start();
	}

	// @viewbyIdが読み込まれた後に呼び出される。
	@AfterViews
	void afterViews() {
		Toast.makeText(this, textView.getText().toString() + " \n起動したよ",
				Toast.LENGTH_SHORT).show();
		// spにデータがある場合には起動後、setテキストする。ない場合はデフォルトの値で初期化。
		prefAgeTextView.setText(myPrefs.age().getOr(0) + "");
		prefNameTextView.setText(myPrefs.name().getOr("No Name"));
	}

	@ViewById
	EditText prefEditText;
	@ViewById
	TextView prefAgeTextView, prefNameTextView;
	@ViewById
	RadioButton nameRadioButton, ageRadioButton;
	@ViewById
	RadioGroup myRadioGroup;

	// shared prefのAA
	@Pref
	MyPrefs_ myPrefs;

	@Click(R.id.savePrefButton)
	void savePref() {
		String str = prefEditText.getText().toString();
		if (!TextUtils.isEmpty(str)) {
			switch (myRadioGroup.getCheckedRadioButtonId()) {
			case R.id.nameRadioButton:
				// spのnameにstrを上書きする。
				myPrefs.name().put(str);
				myPrefs.lastUpdated().put(System.currentTimeMillis());
				prefNameTextView.setText(myPrefs.name().get());
				break;
			case R.id.ageRadioButton:
				if (NumberUtils.isNumber(str)) {
					// spのバッチ処理。editではじめ、関数名、put、関数名,put,,,,, .apply()でコミット。
					int i = Integer.valueOf(str);
					myPrefs.edit().age().put(i).lastUpdated()
							.put(System.currentTimeMillis()).apply();
					prefAgeTextView.setText(i + "");
				} else {
					Toast.makeText(MainActivity.this, "ageには数字を入れてください",
							Toast.LENGTH_SHORT).show();
				}
				break;
			}
			// 消去: myPrefs.clear();
			// 値が存在するか確認する: boolean nameExists = myPrefs.name().exists();
			// 値を取得する: long lastUpdated = myPrefs.lastUpdated().get();
			// 値を読み取り、その値がない場合はgetOrの引数が帰ってくる。
			// long now = System.currentTimeMillis();
			// long longlastUpdated = myPrefs.lastUpdated().getOr(now);
		} else {
			Toast.makeText(MainActivity.this, "EditTextに値を入力してください",
					Toast.LENGTH_SHORT).show();
		}
	}

	//uithread側の処理。onpreexeみたいな
	@UiThread
	void doInUiThread() {
		String query = null; //TODO ここにクエリを書く 
		doInBackGround(query);
	}

	String result;

	// これでAsynctascの代わりになる
	@Background
	void doInBackGround(String query) {
		AndroidHttpClient client = AndroidHttpClient
				.newInstance("Android UserAgent");
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		try {
			HttpResponse res = client
					.execute(new HttpGet(
							"https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=" + query ));
			// HttpResponseのEntityデータをStringへ変換
			reader = new BufferedReader(new InputStreamReader(res.getEntity()
					.getContent(), "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
			}
		}
		// resultに入れる
		result = builder.toString();
		afterBackgroundExe(result);
	}
	
	//onPostみたいな役割
	@UiThread
	void afterBackgroundExe(String result){
		//検索した文字列の結果をテキストに貼り付けるだけ。
		textView.setText(result);
	}
}
