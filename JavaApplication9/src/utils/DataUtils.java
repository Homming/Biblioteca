package utils;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import java.util.Calendar;
import java.util.Date;

public class DataUtils {

    //Retorna a data enviada por parametro com a adicao dos dias desejado
    public static Date adicionarDias(Date data, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(DAY_OF_MONTH, dias);
        return calendar.getTime();
    }

    /*
      Retorna a data atual com a diferenca de dias enviados por parametro
     */
    public static Date obterDataComDiferencaDias(int dias) {
        return adicionarDias(new Date(), dias);
    }

    // Retorna uma instancia de Date refletindo os valores passados por parametro
    public static Date obterData(int dia, int mes, int ano) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(DAY_OF_MONTH, dia);
        calendar.set(MONTH, mes - 1);
        calendar.set(YEAR, ano);
        return calendar.getTime();
    }

    // Verifica se uma data é igual a outra Esta comparacao considera apenas dia, mes e ano
    public static boolean isMesmaData(Date data1, Date data2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(data1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(data2);
        return (calendar1.get(DAY_OF_MONTH) == calendar2.get(DAY_OF_MONTH))
                && (calendar1.get(MONTH) == calendar2.get(MONTH))
                && (calendar1.get(YEAR) == calendar2.get(YEAR));
    }

    // Verifica se uma determinada data é o dia da semana desejado
    public static boolean verificarDiaSemana(Date data, int diaSemana) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        return calendar.get(DAY_OF_WEEK) == diaSemana;
    }

}
