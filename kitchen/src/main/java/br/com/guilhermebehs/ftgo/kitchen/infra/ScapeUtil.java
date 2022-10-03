package br.com.guilhermebehs.ftgo.kitchen.infra;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ScapeUtil {

    static Logger log = LoggerFactory.getLogger(ScapeUtil.class);

    public static String scapeStackTrace(Throwable e) {
        StringWriter errors = new StringWriter();

        e.printStackTrace(new PrintWriter(errors));

        String messageStackTrace = errors.toString();

        return StringEscapeUtils.escapeJava(messageStackTrace);
    }

    public static String scape(String message) {
        return StringEscapeUtils.escapeJava(message);
    }

    public static String scape(Object object) {
        return StringEscapeUtils.escapeJava(String.valueOf(object));
    }

}
