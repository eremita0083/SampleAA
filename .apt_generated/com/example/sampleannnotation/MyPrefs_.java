//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.1.
//


package com.example.sampleannnotation;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.sampleannnotation.R.string;
import org.androidannotations.api.sharedpreferences.EditorHelper;
import org.androidannotations.api.sharedpreferences.IntPrefEditorField;
import org.androidannotations.api.sharedpreferences.IntPrefField;
import org.androidannotations.api.sharedpreferences.LongPrefEditorField;
import org.androidannotations.api.sharedpreferences.LongPrefField;
import org.androidannotations.api.sharedpreferences.SharedPreferencesHelper;
import org.androidannotations.api.sharedpreferences.StringPrefEditorField;
import org.androidannotations.api.sharedpreferences.StringPrefField;

public final class MyPrefs_
    extends SharedPreferencesHelper
{

    private Context context_;

    public MyPrefs_(Context context) {
        super(context.getSharedPreferences("MyPrefs", 0));
        this.context_ = context;
    }

    public MyPrefs_.MyPrefsEditor_ edit() {
        return new MyPrefs_.MyPrefsEditor_(getSharedPreferences());
    }

    public IntPrefField age() {
        return intField("age", 42);
    }

    public LongPrefField lastUpdated() {
        return longField("lastUpdated", 0L);
    }

    public StringPrefField name() {
        return stringField("name", "John");
    }

    public StringPrefField resourceHello() {
        return stringField("resourceHello", context_.getResources().getString(string.hello_world));
    }

    public final static class MyPrefsEditor_
        extends EditorHelper<MyPrefs_.MyPrefsEditor_>
    {


        MyPrefsEditor_(SharedPreferences sharedPreferences) {
            super(sharedPreferences);
        }

        public IntPrefEditorField<MyPrefs_.MyPrefsEditor_> age() {
            return intField("age");
        }

        public LongPrefEditorField<MyPrefs_.MyPrefsEditor_> lastUpdated() {
            return longField("lastUpdated");
        }

        public StringPrefEditorField<MyPrefs_.MyPrefsEditor_> name() {
            return stringField("name");
        }

        public StringPrefEditorField<MyPrefs_.MyPrefsEditor_> resourceHello() {
            return stringField("resourceHello");
        }

    }

}
