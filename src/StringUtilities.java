import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StringUtilities {

	public static String getDate() {
		String format = "hh:mm a 'on' EEE, MMM dd, yyyy";
		return getDate(format);
	}

	public static String getDate(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(Calendar.getInstance().getTime());
	}

}
