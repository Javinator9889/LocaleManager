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
package javinator9889.localemanager.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javinator9889.localemanager.application.BaseApplication;
import javinator9889.localemanager.utils.Utils;

/**
 * Base AppCompatActivity from which your activities must inherit from.
 * <p>
 * It has the same behaviour as a simple non-modified AppCompatActivity, but it
 * changes its content language when configuration has changed. Notice that you
 * <b>must clean and restart</b> your activities when the user has changed the
 * language. If not, there will be no changes on UI.
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {
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

    /**
     * Called when the activity is starting.  This is where most initialization
     * should go: calling {@link AppCompatActivity#setContentView(int)} to
     * inflate the activity's UI, using {@link AppCompatActivity#findViewById}
     * to programmatically interact with widgets in the UI, calling {@link
     * AppCompatActivity#managedQuery(android.net.Uri, String[], String,
     * String[], String)} to retrieve cursors for data being displayed, etc.
     *
     * <p>You can call {@link AppCompatActivity#finish} from within this
     * function, in which case onDestroy() will be immediately called after
     * {@link AppCompatActivity#onCreate} without any of the rest of the
     * activity lifecycle ({@link AppCompatActivity#onStart}, {@link
     * AppCompatActivity#onResume}, {@link AppCompatActivity#onPause}, etc)
     * executing.
     *
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle
     *                           contains the data it most recently supplied in
     *                           {@link AppCompatActivity#onSaveInstanceState}.
     *                           <b><i>Note: Otherwise it is null.</i></b>
     *
     * @see AppCompatActivity#onStart
     * @see AppCompatActivity#onSaveInstanceState
     * @see AppCompatActivity#onRestoreInstanceState
     * @see AppCompatActivity#onPostCreate
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.resetActivityTitle(this);
    }
}
