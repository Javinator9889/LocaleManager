/*
 * Copyright © 2018 - present | LocaleManager by Javinator9889

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see https://www.gnu.org/licenses/.

 * Created by Javinator9889 on 27/12/18 - LocaleManager.
 */
package javinator9889.localemanager.example;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import javinator9889.localemanager.activity.BaseAppCompatActivity;
import javinator9889.localemanager.utils.languagesupport.LanguagesSupport;

public class MainActivity extends BaseAppCompatActivity
        implements Button.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // ALWAYS call super method - if not, there will be errors and
        // exceptions
        super.onCreate(savedInstanceState);

        // Sets the layout that will be rendered
        setContentView(R.layout.main_activity);

        // Listen for button click on this thread
        Button englishButton = findViewById(R.id.english);
        Button spanishButton = findViewById(R.id.spanish);
        Button russianButton = findViewById(R.id.russian);
        Button defaultButton = findViewById(R.id.default_language);

        englishButton.setOnClickListener(this);
        spanishButton.setOnClickListener(this);
        russianButton.setOnClickListener(this);
        defaultButton.setOnClickListener(this);

        // Sets the current language inside the "current_language" TextView
        TextView currentLanguageText = findViewById(R.id.current_language);
        String currentLanguage = ExampleApp.localeManager.getLanguage();
        currentLanguageText
                .setText(getString(R.string.current_language, currentLanguage));
    }

    /**
     * Called when a view has been clicked.
     *
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.english:
                ExampleApp.localeManager.setNewLocale(this, LanguagesSupport.Language.ENGLISH);
                recreate();
                break;
            case R.id.spanish:
                ExampleApp.localeManager.setNewLocale(this, LanguagesSupport.Language.SPANISH);
                recreate();
                break;
            case R.id.russian:
                ExampleApp.localeManager.setNewLocale(this, LanguagesSupport.Language.RUSSIAN);
                recreate();
                break;
            case R.id.default_language:
                ExampleApp.localeManager.setNewLocale(this, LanguagesSupport.Language.SYSTEM);
                recreate();
                break;
            default:
                Log.w("ExampleApp", "Unhandled case on switch - view: " + view);
                break;
        }
    }
}
