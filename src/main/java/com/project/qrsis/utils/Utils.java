package com.project.qrsis.utils;

import org.apache.commons.validator.routines.EmailValidator;

public class Utils {

    /**
     * Verilen parametrenin eposta adresi olup olmadığı bilgisini döner
     *
     * @param s     Kontrol edilecek string ifade
     */
    public static boolean isValidEmail(String s) {
        return EmailValidator.getInstance().isValid(s);
    }

    /**
     * Verilen string ifadenin sayı olup olmadığı bilgisini döner
     *
     * @param s     Kontrol edilecek string ifade
     */
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
