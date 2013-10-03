package com.projetoboaviagem.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class GlobalUtil {
    
    private static GlobalUtil globalUtil;
    
    private SimpleDateFormat dateFormat;
    
    private GlobalUtil() {
    }
    
    public static final GlobalUtil getInstance() {
        if (globalUtil == null) {
            globalUtil = new GlobalUtil();
        }
        
        return globalUtil;
    }
    
    public String formatarDataMedio(Date date) {
        dateFormat = new SimpleDateFormat(Constantes.DATA_MEDIO);
        return dateFormat.format(date);
    }

    public String formatarData(Date date) {
        dateFormat = new SimpleDateFormat(Constantes.DATA_PADRAO);
        return dateFormat.format(date);
    }
    
    public Date parseStringInDateMedio(String value) throws ParseException {
        dateFormat = new SimpleDateFormat(Constantes.DATA_MEDIO);
        return dateFormat.parse(value);
    }
    
    public Date parseStringInDatePadrao(String value) throws ParseException {
        dateFormat = new SimpleDateFormat(Constantes.DATA_PADRAO);
        return dateFormat.parse(value);
    }
    
    public Calendar converterEmCalendar(int dia, int mes, int ano) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, dia);
        calendar.set(Calendar.MONTH, mes);
        calendar.set(Calendar.YEAR, ano);
        
        return calendar;
    }
    
    public Date converterEmDate(int dia, int mes, int ano) {
        return converterEmCalendar(dia, mes, ano).getTime();
    }
    
    public String formatarValor(Double valor) {
    	DecimalFormat formatoDois = new DecimalFormat("##,###,###,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));
    	formatoDois.setMinimumFractionDigits(2); 
    	formatoDois.setParseBigDecimal (true);
    	return "R$ " +  formatoDois.format(valor);
    }
}
