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
package javinator9889.localemanager.utils.languagesupport;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;
import static javinator9889.localemanager.utils.languagesupport.LanguagesSupport.Language.*;

/**
 * Instead of using an {@code enum}, a {@code StringDef} is used for
 * providing an easy access to the user for defining the language he wants to
 * use for defining the Locale
 */
public abstract class LanguagesSupport {
    /**
     * Notice that annotations are to be discarded by the compiler.
     */
    @Retention(SOURCE)

    /*
        Available languages
     */
    @StringDef({
            GERMAN,
            CHINESE,
            CZECH,
            DUTCH,
            FRENCH,
            ITALIAN,
            JAPANESE,
            KOREAN,
            POLISH,
            RUSSIAN,
            SPANISH,
            ARABIC,
            BULGARIAN,
            CATALAN,
            CROATIAN,
            DANISH,
            FINNISH,
            GREEK,
            HEBREW,
            HINDI,
            HUNGARIAN,
            INDONESIAN,
            LATVIAN,
            LITHUANIAN,
            NORWEGIAN,
            PORTUGUESE,
            ROMANIAN,
            SERBIAN,
            SLOVAK,
            SLOVENIAN,
            SWEDISH,
            TAGALOG,
            THAI,
            TURKISH,
            UKRAINIAN,
            VIETNAMESE,
            SYSTEM
    })

    /*
        Interface for accessing available languages
     */
    public @interface Language {
        String SYSTEM = "sys";
        String GERMAN = "de";
        String CHINESE = "zh";
        String CZECH = "cs";
        String DUTCH = "nl";
        String FRENCH = "fr";
        String ITALIAN = "it";
        String JAPANESE = "ja";
        String KOREAN = "ko";
        String POLISH = "pl";
        String RUSSIAN = "ru";
        String SPANISH = "es";
        String ARABIC = "ar";
        String BULGARIAN = "bg";
        String CATALAN = "ca";
        String CROATIAN = "hr";
        String DANISH = "da";
        String FINNISH = "fi";
        String GREEK = "el";
        String HEBREW = "iw";
        String HINDI = "hi";
        String HUNGARIAN = "hu";
        String INDONESIAN = "in";
        String LATVIAN = "lv";
        String LITHUANIAN = "lt";
        String NORWEGIAN = "nb";
        String PORTUGUESE = "pt";
        String ROMANIAN = "ro";
        String SERBIAN = "sr";
        String SLOVAK = "sk";
        String SLOVENIAN = "sl";
        String SWEDISH = "sv";
        String TAGALOG = "tl";
        String THAI = "th";
        String TURKISH = "tr";
        String UKRAINIAN = "uk";
        String VIETNAMESE = "vi";
    }
}
