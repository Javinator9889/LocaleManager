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
package javinator9889.localemanager.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import javinator9889.localemanager.application.BaseApplication;
import javinator9889.localemanager.utils.Utils;

/**
 * Base FragmentActivity from which your activities must inherit from.
 * <p>
 * It has the same behaviour as a simple non-modified FragmentActivity, but it
 * changes its content language when configuration has changed. Notice that you
 * <b>must clean and restart</b> your activities when the user has changed the
 * language. If not, there will be no changes on UI.
 */
public abstract class BaseFragmentActivity extends FragmentActivity {
    /**
     * Set the base context for this ContextWrapper.  All calls will then be
     * delegated to the base context.  Throws IllegalStateException if a base
     * context has already been set.
     *
     * @param base The new base context for this wrapper.
     */
    @Override
    protected void attachBaseContext(@NonNull Context base) {
        super.attachBaseContext(BaseApplication.localeManager.setLocale(base));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.resetActivityTitle(this);
    }
}
