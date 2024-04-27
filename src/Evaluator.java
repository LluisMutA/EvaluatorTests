import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Evaluator {


   public static int calculate(String expr) {
       // Convertim l'string d'entrada en una llista de tokens
       Token[] tokens = Token.getTokens(expr);
       Token[] tokenRPN = RPN(tokens); // Efectua el procediment per convertir la llista de tokens en notació RPN
       return calcRPN(tokenRPN);// Finalment, crida a calcRPN amb la nova llista de tokens i torna el resultat
   }

    // intentar multiplicar per -1 si el nombre no unaris es imparell

    // aplicr un parentesis amb el -() per fer el canvi a negatiu

//el bucle while que se encuentra después del bloque if que maneja los paréntesis, la condición while (!pilaRPN.isEmpty())
//parece estar mal colocada. Actualmente, está dentro del bloque if (token.getTtype() == Token.Toktype.PAREN),
//lo cual no es correcto. Debería estar fuera de ese bloque y después del bucle for principal que recorre los tokens.
   public static Token[] RPN(Token[] RPN){
       List<Token> transformaRPN = new ArrayList<>();
       Stack<Token> pilaRPN = new Stack<>();

       for (Token token: RPN) {
           if (token.getTtype() == Token.Toktype.NUMBER) {
               transformaRPN.add(token);
           } else if (token.getTtype() == Token.Toktype.OP) {
               while ((!pilaRPN.isEmpty()) && (prioritat(pilaRPN.peek()) >= prioritat(token))) {
                   transformaRPN.add(pilaRPN.pop());
               }
               pilaRPN.push(token);
           } else if (token.getTtype() == Token.Toktype.PAREN) {
               if (token.getTk() == '(') {
                   pilaRPN.push(token);
               } else if (token.getTk() == ')') {
                   while (!pilaRPN.isEmpty() && pilaRPN.peek().getTk() != '(') {
                       transformaRPN.add(pilaRPN.pop());
                   }
                   pilaRPN.pop();
               }
           }
       }

       while (!pilaRPN.isEmpty()) {
           transformaRPN.add(pilaRPN.pop());
       }

       return transformaRPN.toArray(new Token[0]);
   }

    public static int prioritat(Token otk){
        char c = otk.getTk();
        if (c == 'k'){
            return 1;
        }
        if (c == '+' || c == '-'){
            return 2;
        }
        if (c == '*' || c == '/'){
            return 3;
        }
        if (c == '^') {
            return 4;
        }return 0;
    }

    public static int calcRPN(Token[] list) {
        // Calcula el valor resultant d'avaluar la llista de tokens
        Stack<Integer> stack = new Stack<>();

        for (Token token : list) {
            if (token.getTtype() == Token.Toktype.NUMBER) {
                stack.push(token.getValue());
            } else if (token.getTtype() == Token.Toktype.OP) {
                int b = stack.pop();
                int a = stack.pop();
                char oper = token.getTk();
                int result = operador(a, b, oper);
                stack.push(result);
            }
        }return stack.pop();
}


    private static int operador(int a, int b, char oper) {
        switch (oper) {
            case '+':
                return a+b;
            case '-':
                return a-b;
            case '/':
                return a/b;
            case '*':
                return a*b;
            case '^':
                return (int) Math.pow(a, b);
        }
        return oper;
    }
}
