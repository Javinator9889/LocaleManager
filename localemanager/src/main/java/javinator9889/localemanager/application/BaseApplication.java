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
package javinator9889.localemanager.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javinator9889.localemanager.localemanager.LocaleManager;

/**
 * Your application must inherits from this {@link Application} implementation
 * or either override {@link #attachBaseContext(Context)} and {@link
 * #onConfigurationChanged(Configuration)}, declaring a static field of a {@link
 * LocaleManager} instance.
 * <p>
 * This base application allows your app to use a custom language (locale)
 * without any troubles or even revert back to system default one. By using the
 * proper Android methods, and managing {@code Context} objects, it is easy to
 * implement a custom language without altering the normal application
 * execution.
 */
public abstract class BaseApplication extends Application {

    /**
     * {@link LocaleManager} static instance for managing activities, services
     * and fragments with custom locale.
     */
    public static LocaleManager localeManager;

    /**
     * Set the base context for this ContextWrapper.  All calls will then be
     * delegated to the base context.  Throws IllegalStateException if a base
     * context has already been set.
     *
     * @param base The new base context for this wrapper.
     */
    @Override
    protected void attachBaseContext(Context base) {
        SharedPreferences customSharedPreferences =
                getCustomSharedPreferences(base);
        localeManager = (customSharedPreferences != null) ?
                new LocaleManager(customSharedPreferences) :
                new LocaleManager(base);
        super.attachBaseContext(localeManager.setLocale(base));
    }

    /**
     * Called by the system when the device configuration changes while your
     * component is running.  Note that, unlike activities, other components are
     * never restarted when a configuration changes: they must always deal with
     * the results of the change, such as by re-retrieving resources.
     *
     * <p>At the time that this function has been called, your Resources
     * object will have been updated to return resource values matching the new
     * configuration.
     *
     * <p>For more information, read
     * <a href="{@docRoot}guide/topics/resources/runtime-changes.html">
     * Handling Runtime Changes</a>.
     *
     * @param newConfig The new device configuration.
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        localeManager.setLocale(this);
    }

    /**
     * You must override this method for using a custom shared preferences with
     * your locale instance. Notice that, at this point, the application
     * <b>is being created</b>, so you must use the provided {@code base} for
     * managing shared preferences and any other resources that depends on
     * {@code Context}.
     * <p>
     * You can safely return {@code null} if you are not using any custom shared
     * preferences file, so {@link LocaleManager} will use the default one.
     *
     * @param base the base context obtained when just creating the
     *             application.
     *
     * @return {@code SharedPreferences} or {@code null} if using the default
     * ones.
     */
    @Nullable
    protected abstract SharedPreferences getCustomSharedPreferences(@NonNull Context base);
}
