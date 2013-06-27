package br.com.estudo.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Classe com métodos utilitários. 
 * @author daniel.oliveira
 */
public abstract class Util {

	public static final String DDMMYYYY = "dd/MM/yyyy";
	public static final String DDMMYYYY_HHMMSS = "dd/MM/yyyy HH:mm:ss";
	/**
	 * String vazia.
	 */
	public static final String VAZIO = "";
	
	/**
	 * Retorna a data formatada.
	 * @param date A data a ser formatada.
	 * @param format O formato da data.
	 * @return A data formatada.
	 */
	public static String formatDate(final Date date, final String format) {
		if (format == null || VAZIO.equals(format)) {
			throw new IllegalArgumentException("Formato não suportado.");
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	
	/**
	 * Retorna a data formatada.
	 * @see Calendar
	 * @param calendar A data a ser formatada.
	 * @param format O formato da data.
	 * @return A data formatada.
	 */
	public static String formatDate(final Calendar calendar, final String format) {
		return formatDate(calendar.getTime(), format);
	}
}
