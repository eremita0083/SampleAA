package com.example.sampleannnotation;

import org.androidannotations.annotations.sharedpreferences.DefaultInt;
import org.androidannotations.annotations.sharedpreferences.DefaultRes;
import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

//shared pref。スコープはユニークに設定しないと、アプリケーション全体での値共有ができない。
@SharedPref(value = SharedPref.Scope.UNIQUE)
public interface MyPrefs {

	//stringのリソースで初期化
	@DefaultRes(R.string.hello_world)
	String resourceHello();
	
	//Johnで初期化
	@DefaultString("John")
    String name();

	//42で初期化
	@DefaultInt(42)
    int age();
	
	// 何も書かない場合はデふぉが０
	long lastUpdated();
	
}
