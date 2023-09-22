package cl.travel.proyecto.codigos.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringConstansUtil {

	public static final String USUARIO = "Usuario=";

    public static final String PERFILES = "perfiles";

    public static final String HIJOID = "hijo-id";

    public static final String PADREID = "padre-id";

    public static final String DOMAIN = "@travel.cl";

    public static final String[] permitAll = new String[]{
            "/login",
            "/logout",
            "/error",
            "/testeo",
            "/img/**",
            "/css/**",
            "/login*",
            "/assets/**",
            "/demo/**"
    };
}
