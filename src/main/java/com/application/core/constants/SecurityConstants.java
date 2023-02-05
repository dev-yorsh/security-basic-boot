package com.application.core.constants;

public class SecurityConstants {

        /********************************** ROLES **********************************/
        public static final String ROLE_PUBLICO = "Publico";
        public static final String ROLE_ADMINISTRADOR = "Administrador";

        /********************************* MENSAJES *********************************/
        public static final String MSJ_ERROR_SERVER = "Error de servidor, por favor contáctese con el Administrador de sistema.";
        public static final String MSJ_ERROR_DATABASE = "Error de base de datos.";
        public static final String MSJ_ERROR_BAD_REQUEST = "Por favor verifique los campos.";
        public static final String MSJ_ERROR_LOGIN = "Error de Autenticación.";
        public static final String MSJ_ERROR_PRIVILEGE = "Acceso denegado | No tiene los permisos suficientes.";
        public static final String MSJ_ERROR_NOT_FOUND = "Recurso solicitado no encotrado.";
        public static final String MSJ_ERROR_DESERIALIZE = "Por favor revise los valore ingresados";


        /******************************** PARAMETROS ********************************/
        public static final Integer SIZE_KEY_SECRET = 15;
        public static final Integer SIZE_CODE_2FA = 4;
}
