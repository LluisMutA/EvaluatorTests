public class Token {
    enum Toktype {
        NUMBER, OP, PAREN
    }

    // Pensa a implementar els "getters" d'aquests atributs
    private Toktype ttype;
    private int value;
    private char tk;

    // Constructor privat. Evita que es puguin construir objectes Token externament
    private Token() {
    }

    // Torna un token de tipus "NUMBER"
    static Token tokNumber(int value) {
        return null;
    }

    // Torna un token de tipus "OP"
    static Token tokOp(char c) {
        return null;
    }

    // Torna un token de tipus "PAREN"
    static Token tokParen(char c) {
        return null;
    }

    // Mostra un token (conversió a String)
    public String toString() {
        return "";
    }

    // Mètode equals. Comprova si dos objectes Token són iguals
    public boolean equals(Object o) {
        return false;
    }

    // A partir d'un String, torna una llista de tokens
    public static Token[] getTokens(String expr) {
        return null;
    }
}
