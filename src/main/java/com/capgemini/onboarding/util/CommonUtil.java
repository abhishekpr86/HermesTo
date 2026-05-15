package com.capgemini.onboarding.util;

import java.text.DecimalFormat;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The Class CommonUtil.
 */
public class CommonUtil {

	private static Logger logger = Logger.getLogger(CommonUtil.class);

	/** The password encoder. */
	static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	/**
	 * Format string.
	 *
	 * @param value
	 *            the value
	 * @return the string
	 */
	public static final String formatString(double value) {
		return "" + Double.parseDouble(new DecimalFormat("##.##").format(value));
	}

	/**
	 * Zero for blank.
	 *
	 * @param value
	 *            the value
	 * @return the string
	 */
	public static final String zeroForBlank(String value) {

		if (value == null || "".equals(value)) {
			value = "0";
		}

		return value;
	}

	/**
	 * Gets the primary tech.
	 *
	 * @param technology
	 *            the technology
	 * @return the primary tech
	 */
	public static final String getPrimaryTech(String technology) {
		String primaryTech = "";

		if (technology != null && !"".equals(technology)) {
			primaryTech = (technology.split(","))[0];
		}

		if (primaryTech.equals(null) || "null".equals(primaryTech)) {
			primaryTech = "";
		}
		return primaryTech;
	}

	/**
	 * Gets the entity name.
	 *
	 * @param entityName
	 *            the entity name
	 * @return the entity name
	 */
	public static final String getEntityName(String entityName) {

		if (entityName != null && !"".equals(entityName)) {

			return entityName.substring(8);

		}

		if (entityName.equals(null) || "null".equals(entityName)) {
			entityName = "";
		}

		return entityName;
	}

	/**
	 * Gets the password bcrypt.
	 *
	 * @param password the password
	 * @return the password bcrypt
	 */
	public static String getPasswordBcrypt(String password) {
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

	/**
	 * Compare password.
	 *
	 * @param oldPassword the old password
	 * @param storedPassword the stored password
	 * @return true, if successful
	 */
	public static boolean comparePassword(String oldPassword, String storedPassword) {
		return passwordEncoder.matches(oldPassword, storedPassword);

	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[]) {
		String hashedPassword = passwordEncoder.encode("pass");

		logger.info(
				passwordEncoder.matches("pass", "$2a$10$gUXQLtrUPFi6fFxnDZPaNeslVbTTyAHN5jK2awbSyQQ2JhP/I/8AC"));

		logger.info(hashedPassword);
	}
	
}
