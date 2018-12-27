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

 * Created by Javinator9889 on 20/12/2018 - LocaleManager.
 */
package javinator9889.localemanager.localemanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import java.util.Locale;

import javinator9889.localemanager.utils.Utils;
import javinator9889.localemanager.utils.languagesupport.LanguagesSupport.Language;

import static android.os.Build.VERSION_CODES.JELLY_BEAN_MR1;
import static javinator9889.localemanager.utils.Constants.KEY;

//import androidx.annotation.NonNull;

/**
 * {@code LocaleManager} provides an easy access functionality for changing
 * application language to user's preferred one.
 * <p>
 * By default, it uses the "default preferences" for ensuring persistence across
 * application sessions and instances, but it also allows the developer to use
 * custom ones, for example for simplifying and merging all shared preferences
 * files.
 * <p>
 * It uses proper Android context and configurations for managing and changing
 * locales. As this is not recommended and is not officially maintained and
 * documented by Android, it is possible to alter the locale for a custom
 * application without so much effort with this lib. But it is important that
 * when building your application, advising the user that he should not change
 * the application language but the Android device language instead.
 */
public class LocaleManager {
    /**
     * For data persistence across instances, the user language is stored inside
     * the shared preferences.
     */
    private final SharedPreferences mPreferences;

    /**
     * Constructor that takes a {@link Context} for obtaining the default shared
     * preferences for storing user data - for using custom shared preferences
     * instead, switch to {@link #LocaleManager(SharedPreferences)}
     *
     * @param context base context or activity one for taking the
     *                SharedPreferences from.
     *
     * @see PreferenceManager#getDefaultSharedPreferences(Context)
     * @see #LocaleManager(SharedPreferences)
     */
    public LocaleManager(@NonNull Context context) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Custom constructor that takes a {@link SharedPreferences} customized by
     * the user, instead of using the default one.
     * <p>
     * You can switch to {@link #LocaleManager(Context)} for generating a new
     * instance based on the default shared preferences.
     *
     * @param customSharedPreferences custom implementation and/or file for
     *                                shared preferences.
     *
     * @see #LocaleManager(Context)
     */
    public LocaleManager(@NonNull SharedPreferences customSharedPreferences) {
        mPreferences = customSharedPreferences;
    }

    /**
     * Obtains the user custom language - if this value is not set, a default
     * value is returned ({@link Language#SYSTEM} value).
     *
     * @return {@code String} with the user language.
     *
     * @see Language#SYSTEM
     */
    public String getLanguage() {
        return mPreferences.getString(KEY, Language.SYSTEM);
    }

    /**
     * Whenever the app restarts or an activity is created, this method ensures
     * that the user defined custom language is being used instead of Android
     * default's one.
     *
     * @param context the context from which the locale will be applied.
     *
     * @return {@code context} modified with the new locale.
     */
    public Context setLocale(Context context) {
        return updateLocaleResources(context, getLanguage());
    }

    /**
     * Sets a new locale language that will be used for changing the in-app
     * lang. If it success, a call to the method {@linkplain
     * android.app.Application#onConfigurationChanged(Configuration)} is done.
     *
     * @param context  the context from which the new locale will be changed.
     * @param language the new language that will have the application.
     *
     * @return {@code context} modified if version is higher than Android N.
     */
    @NonNull
    public Context setNewLocale(@NonNull Context context,
                                @Language @NonNull String language) {
        return setNewLocaleCustomLanguage(context, language);
    }

    /**
     * Sets a new locale language that will be used for changing the in-app
     * lang. If it success, a call to the method {@linkplain
     * android.app.Application#onConfigurationChanged(Configuration)} is done.
     *
     * @param context  the context from which the new locale will be changed.
     * @param language the new language that will have the application - it can
     *                 be a custom one if not available at {@link Language
     *                 languages}.
     *
     * @return {@code context} modified if version is higher than Android N.
     */
    @NonNull
    public Context setNewLocaleCustomLanguage(@NonNull Context context,
                                              @NonNull String language) {
        persistLanguage(language);
        return updateLocaleResources(context, language);
    }

    /**
     * Tries to update the resources by setting a custom locale so the display
     * language will change once the activities are reloaded.
     *
     * @param context  the context from which the new locale will be changed.
     * @param language the new language that will have the application - it can
     *                 be a custom language if not available at {@link Language
     *                 languages}.
     *
     * @return {@code context} modified with the new locale.
     */
    @NonNull
    private Context updateLocaleResources(@NonNull Context context,
                                          @NonNull String language) {
        // Check if the new language is the system one
        Configuration systemConfig = Resources.getSystem().getConfiguration();
        Locale locale = language.equals(Language.SYSTEM) ?
                Utils.getSystemLocale(systemConfig) :
                new Locale(language);
        // Sets the new default locale
        Locale.setDefault(locale);

        // Obtains resources and configuration from provided context
        Resources resources = context.getResources();
        Configuration config = new Configuration(resources.getConfiguration());

        // Updates configuration depending on Android Version
        if (Utils.isAtLeastAndroidVersion(JELLY_BEAN_MR1)) {
            config.setLocale(locale);
            context = context.createConfigurationContext(config);
        } else {
            config.locale = locale;
            resources.updateConfiguration(config, resources.getDisplayMetrics());
        }

        // The modified context
        return context;
    }

    /**
     * Saves the user language inside the shared preferences - as we care about
     * this value to be saved, we use "commit" instead of apply, just because
     * sometimes the application may be killed which prevents apply() to
     * finish.
     *
     * @param language the user custom language.
     *
     * @see SharedPreferences.Editor#apply()
     * @see SharedPreferences.Editor#commit()
     */
    @SuppressLint("ApplySharedPref")
    private void persistLanguage(@NonNull String language) {
        mPreferences.edit().putString(KEY, language).commit();
    }
}
