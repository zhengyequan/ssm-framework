package com.yqbaba.framework.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MessageUtil {

	private final static Log log = LogFactory.getLog(MessageUtil.class);

	public static String getMessage(Enum<?> e) {
		String baseName = e.getClass().getName();
		ResourceBundle rb = ResourceBundle.getBundle(baseName);
		if (rb == null) {
			log.error("[MessageUtil] : There is no base file can find" + baseName);
			return StringUtils.EMPTY;
		}

		String message = rb.getString(e.toString());
		if (StringUtils.isBlank(message)) {
			log.error("[MessageUtil] : There is no " + e.getClass().getName() + " can be found in " + baseName
					+ ".properties");
			return StringUtils.EMPTY;
		}

		return message;
	}

	public static String getMessage(Enum<?> e, Object... args) {
		if (args == null) {
			return getMessage(e);
		}

		return MessageFormat.format(getMessage(e), args);
	}

}
