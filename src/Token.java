import java.util.ArrayList;
import java.util.List;

public class Token {
    enum Toktype {
        NUMBER, OP, PAREN
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public char getTk() {
        return tk;
    }

    public void setTk(char tk) {
        this.tk = tk;
    }

    public Toktype getTtype() {
        return ttype;
    }

    public void setTtype(Toktype ttype) {
        this.ttype = ttype;
    }

    // Pensa a implementar els "getters" d'aquests atributs
    private Toktype ttype;
    private int value;
    private char tk;

    // Constructor privat. Evita que es puguin construir objectes Token externament
    private Token(Toktype ttype, int value, char tk) {
        this.ttype = ttype;
        this.value = value;
        this.tk = tk;
    }

    // Torna un token de tipus "NUMBER"
    static Token tokNumber(int value) {
        return new Token(Toktype.NUMBER, value, '0');
    }

    // Torna un token de tipus "OP"
    static Token tokOp(char c) {
        return new Token(Toktype.OP, 0, c);
    }

    // Torna un token de tipus "PAREN"
    static Token tokParen(char c) {
        return new Token(Toktype.PAREN, 0, c);
    }

    // Mostra un token (conversió a String)
    public String toString() {
        if (ttype == Toktype.NUMBER) {
            return String.valueOf(value);
        } else {
            return String.valueOf(tk);
        }
    }

    // Mètode equals. Comprova si dos objectes Token són iguals
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return value == token.value && tk == token.tk && ttype == token.ttype;
    }

    // A partir d'un String, torna una llista de tokens
    public static Token[] getTokens(String expr) {
        List<Token> tokens = new ArrayList<>();
        StringBuilder consNum = new StringBuilder(); // enmagatzema el digits

        for (char c : expr.toCharArray()) {
            if (Character.isDigit(c)) {  // isDigit, fa aixo, mira si es u digit
                consNum.append(c);
            } else {
                if (!consNum.isEmpty()) {
                    int number = Integer.parseInt(consNum.toString());
                    tokens.add(tokNumber(number));
                    consNum.setLength(0);
                }
                if (c == '+' || c == '-' || c == '*' || c == '/') {
                    tokens.add(tokOp(c));
                } else if (c == '(' || c == ')') {
                    tokens.add(tokParen(c));
                }
            }
        }
        // Afegir darrer numero si no es parentesis o +*-/
        if (consNum.isEmpty()) {
            int number = Integer.parseInt(consNum.toString());
            tokens.add(tokNumber(number));
        }
        return tokens.toArray(new Token[0]);
    }
}
