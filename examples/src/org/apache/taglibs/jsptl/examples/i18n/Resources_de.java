package org.apache.taglibs.standard.examples.i18n;

import java.util.*;

public class Resources_de extends ListResourceBundle {
    private static Object[][] contents;

    static {
	contents = new Object[][] {
	    { "greetingMorning", "Guten Morgen!" },
	    { "greetingEvening", "Guten Abend!" },
	    { "currentTime", "Heutiges Datum und Uhrzeit: {0}" },
	    { "com.acme.labels.cancel", "Abbrechen" },
	    { "java.lang.ArithmeticException", "/ durch 0" }
	};
    }

    public Object[][] getContents() {
	return contents;
    }
}
