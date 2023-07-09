package com.rabbitminers.extendedgears.base.util;

public class StringHelpers {
    public static boolean containsAll(String value, CharSequence... checks) {
        for (CharSequence check : checks)
            if (!value.contains(check))
                return false;
        return true;
    }
}
