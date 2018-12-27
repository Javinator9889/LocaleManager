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

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import javinator9889.localemanager.application.BaseApplication;
import javinator9889.localemanager.localemanager.LocaleManager;

public class ExampleApp extends BaseApplication {
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
     * @return {@code SharedPreferences} or {@code null} if using the default
     * ones.
     */
    @Nullable
    @Override
    protected SharedPreferences getCustomSharedPreferences(@NonNull Context base) {
        // we will not use custom shared preferences, so null is safely
        // returnable-
        return null;
    }
}
