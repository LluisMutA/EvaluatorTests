import java.util.ArrayList;
import java.util.List;

public class Token {
    enum Toktype {
        NUMBER, OP, PAREN, UNARI
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
        return new Token(Toktype.OP,0, c);
    }

    // Torna un token de tipus "PAREN"
    static Token tokParen(char c) {
        return new Token(Toktype.PAREN, 0, c);
    }

    static Token tokUnari () {
        return new Token(Toktype.UNARI, 0, 'k');
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
        List<Token> llistaTokens = new ArrayList<>(); // enmagatzema el que no son numeros
        StringBuilder consNum = new StringBuilder(); // enmagatzema caracters en valor decimal (49 = 1, 51 = 3...)

        for (char c : expr.toCharArray()) { // Bucle per cada char del String
            if (Character.isDigit(c)) {  // isDigit, fa aixo, mira si es u digit
                consNum.append(c);       // aqui l'afegim al consNum
            } else {
                if (consNum.length() > 0) {   // Es comprova quin numero es el que ve i el guarda al StringBuilder quan torba un valor no numeric "+/()-*"
                    int num = Integer.parseInt(consNum.toString());
                    llistaTokens.add(tokNumber(num));
                    consNum.setLength(0);
                }
                if(esUnari(c, llistaTokens)){
                    llistaTokens.add(tokUnari());
                    continue;
                }
                if (c == '+' || c == '-' || c == '*' || c == '/') {  // Comprovam quin caracter es, un operador o un parentesis, cridam al seu token i afegim a la llista
                    llistaTokens.add(tokOp(c));
                } else if (c == '(' || c == ')') {
                    llistaTokens.add(tokParen(c));
                }
            }
       }
       // Afegir darrer numero si no es parentesis o +*-/
       if (consNum.length() > 0) {
           int number = Integer.parseInt(consNum.toString());
           llistaTokens.add(tokNumber(number));
        }
        return llistaTokens.toArray(new Token[0]);
    }

    private static boolean esUnari(char c, List<Token> llistaTokens) {
        // comprovar els - a l'inici
        // despres de () i
        // despres de cada operador.
       // return (c == '-' && llistaTokens.isEmpty() || llistaTokens.getLast().getTtype() == Toktype.PAREN || llistaTokens.getLast().getTtype() == Toktype.OP);
        return (c == '-' && (llistaTokens.isEmpty() || llistaTokens.get(llistaTokens.size() - 1).getTtype() == Toktype.PAREN || llistaTokens.get(llistaTokens.size() - 1).getTtype() == Toktype.OP));

    }
}

