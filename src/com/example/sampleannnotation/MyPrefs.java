package com.example.sampleannnotation;

import org.androidannotations.annotations.sharedpreferences.DefaultInt;
import org.androidannotations.annotations.sharedpreferences.DefaultRes;
import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

//shared pref。スコープはユニークに設定しないと、アプリケーション全体での値共有ができない。
@SharedPref(value = SharedPref.Scope.UNIQUE)
public interface MyPrefs {

	@DefaultRes(R.string.hello_world)
	String resourceHello();
	
	@DefaultString("John")
    String name();

	@DefaultInt(42)
    int age();
	
	// The field lastUpdated will have default value 0
	long lastUpdated();
	
}
