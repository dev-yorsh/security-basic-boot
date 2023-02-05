package com.application.core.util;


import com.application.core.constants.Constants;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class GenericUtil {

    public static String formatDate(LocalDateTime date, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return (date != null) ? date.format(dateTimeFormatter) : "Fecha no asignada";
    }

    public static LocalDateTime getCurrentDateByRegion() {
        ZonedDateTime fecha = ZonedDateTime.now(ZoneId.systemDefault());
        return fecha.withZoneSameInstant(ZoneId.of(Constants.TIME_ZONE)).toLocalDateTime();
    }

    public static boolean isEmpty(Object object) {
        return isObjectEmpty(object);
    }

    public static boolean isObjectEmpty(Object value) {
        if (value == null) {
            return true;
        } else if (value instanceof String) {
            return isEmpty((String) value);
        } else if (value instanceof CharSequence) {
            return isEmpty((CharSequence) value);
        } else if (value instanceof Collection || value instanceof Map) {
            return isCollectionEmpty(value);
        }
        return false;
    }

    public static boolean isEmpty(CharSequence character) {
        return (character == null) || (character.length() == 0);
    }

    private static boolean isCollectionEmpty(Object value) {
        if (value instanceof Collection) {
            return isEmpty((Collection<?>) value);
        } else {
            return isEmpty((Map<?, ?>) value);
        }
    }

    public static <K, E> boolean isEmpty(Map<K, E> map) {
        return (map == null) || (map.isEmpty());
    }

    public static <E> boolean isEmpty(Collection<E> collection) {
        return (collection == null) || collection.isEmpty();
    }

    // Validar si la causa de la excepcion es el mismo contenido que el mensaje.
    public static List<String> getCause(Throwable ex) {
        if (ex == null) {
            if (ex != null && ex.getCause() != null) {
                if (ex.getCause().getLocalizedMessage().equals(ex.getMessage())) {
                    return null;
                } else {
                    return Arrays.asList(ex.getCause().getCause().getLocalizedMessage());
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static Boolean onlyLetters(String character) {
        return "[ a-zA-Z]+$".matches(character);
    }

    public static Boolean onlyNumbers(String character) {
        return "[0-9]*".matches(character);
    }

    /**
     * Método para validar si una cadena de caracteres es una contraseña segura.
     *
     * @param character La cadena de caracteres a validar.
     * @return True si la cadena de caracteres cumple con los criterios de seguridad de contraseña.
     * Los criterios de seguridad de contraseña incluyen:
     * Contiene al menos: Un caracter en minúsculas, un caracter en mayúsculas, un caracter especial (!@#$&*),
     * un número y tiene una longitud entre 4 y 12 caracteres.
     **/
    public static Boolean onlyPassword(String character) {
        return "^((?=.*[az])(?=.*[AZ])(?=.*[!@#$&*])(?=.*[0-9] )){8,50}$".matches(character);
    }

    public static Boolean onlyUsername(String character) {
        return "[a-zA-Z0-9]+$".matches(character);
    }
}
