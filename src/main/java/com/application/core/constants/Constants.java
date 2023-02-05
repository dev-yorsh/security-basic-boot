package com.application.core.constants;

public class Constants {

    public static final String TIME_ZONE = "America/Lima";
    public static final Integer LINK_EXPIRATION_TIME = 1;
    public static final Integer PAGE_DEFAULT = 0;
    public static final Integer LIMIT_DEFAULT = 10;


    public static class MessageUser {
        public static final String MSJ_SAVE_OK = "Usuario creado con éxito, se envió un link de activacion al correo electronico";
        public static final String MSJ_CONFIRMATION_OK = "Su cuenta ha sido verificada con éxito!, por favor revise su correo.";
        public static final String MSJ_RESET_PASSWORD_OK = "Se ha cambiado la contraseña de su cuenta correctamente.";
        public static final String USER_ENABLED = "Usuario habilitado con exito.";
        public static final String DELETE = "Se elimno correctamente";
        public static final String MSJ_REQUEST_ERROR = "Error en el 'RequestBody'";
        public static final String MSJ_INTERNAL_EXCEPTION = "Error de logica interna";
    }

    public static class MessageValidation {
        public static final String EMAIL_ERROR = "El correo debe ser valido";
        public static final String USERNAME_ERROR = "EL Username debe ser valido";
        public static final String NAME_SIZE_ERROR = "El nombre debe ser mayor a 3 digitos";
        public static final String NAME_CHARACTER_ERROR = "El nombre debe contener solo letras";
        public static final String PASSWORD_ERROR = "La contraseña no cumple con los criterios requeridos: " +
                "(minimo 8 caracteres entre ayuscula, minuscula y numeros)";
        public static final String PASSWORD_REPEAT_NULL = "Por favor repita la contraseña";
        public static final String PASSWORD_CHANGE_NULL = "Por favor ingrese su nueva contraseña";

    }

    public static class AccountStatus {
        public static final String ACTIVATED = "EU001";
        public static final String PENDING_ACTIVATION = "EU002";
        public static final String SUSPENDED = "EU003";
        public static final String CHANGED = "EU004";
    }

    public static class RegularExpressions {
        public static final String REGEXP_ONLY_LETTERS = "[ a-zA-Z]+$";
        public static final String REGEXP_ONLY_NUMBERS = "[0-9]*";
        public static final String REGEXP_ONLY_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    }

}
