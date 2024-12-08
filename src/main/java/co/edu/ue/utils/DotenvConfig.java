package co.edu.ue.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvConfig {
    private static final Dotenv dotenv = Dotenv.configure().load();

    public static String get(String key) {
        return dotenv.get(key);
    }
}