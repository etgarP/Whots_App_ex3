package page.register;

import android.net.Uri;

import androidx.lifecycle.MutableLiveData;

import java.util.regex.Pattern;

public class ValidationUtils {

    public static boolean validateUsername(String name, MutableLiveData<String> setText) {
        // checks the if the input is correct
        // only letters numbers '_' and length 5 to 20
        String regex = "^[a-zA-Z0-9_]{5,20}$";
        if (Pattern.matches(regex, name)) {
            setText.setValue("");
            return true;
        } else {
            // if the input isn't correct sets error text
            setText.setValue("Only letters, numbers, '_' and length 5 to 20.");
            return false;
        }
    }

    public static boolean validatePassword(String password, MutableLiveData<String> setText) {
        // needs to have lower and higher case and number letter and be length 8-20
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,20}$";
        if (Pattern.matches(regex, password)) {
            setText.setValue("");
            return true;
        } else {
            // if the input isn't correct sets error text
            setText.setValue("Needs a lower/higher case letter and a number, be length 8-20.");
            return false;
        }
    }

    public static boolean validateConfirmPass(String password, String conPass, MutableLiveData<String> setText) {
        // checks if the confirm doesn't match or empty
        if (!(password.equals(conPass)) || conPass.isEmpty()) {
            // if the input isn't correct sets error text
            setText.setValue("Password must match.");
            return false;
        }
        setText.setValue("");
        return true;
    }

    public static boolean validateDisplayName(String name, MutableLiveData<String> setText) {
        /* needs to have only higher and lower case letters or numbers or space
        and be between length 3 to 20 */
        String regex = "^[a-zA-Z0-9 ]{3,20}$";
        if (Pattern.matches(regex, name)) {
            setText.setValue("");
            return true;
        } else {
            // if the input isn't correct sets error text
            setText.setValue("Only lower/higher case letters and numbers," +
                    " length 3 to 20.");
            return false;
        }
    }

    public static boolean validatePicture(Uri picture, MutableLiveData<String> setText) {
        if (picture != null) {
            setText.setValue("");
            return true;
        }
        setText.setValue("Please pick an image.");
        return false;
    }
}
