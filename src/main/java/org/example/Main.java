package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[][] jogo = new String[3][3];
        Map<Integer, String> jogadores = new HashMap<>();
        var sc = new Scanner(System.in);

        String XO1 = "";

        while (!(XO1.equals("X") || XO1.equals("O"))) {
            System.out.println("Jogador 1 será X ou O?");
            XO1 = sc.nextLine().toUpperCase();
        }

        String XO2 = XO1.equals("X") ? "O" : "X";

        jogadores.put(1, XO1);
        jogadores.put(2, XO2);

        int vencedor;

        while (true) {
            int x = 0;
            int y = 0;
            boolean jogadorJogou = false;
            while (!jogadorJogou) {
                System.out.println("Jogador 1, escolha qual quadrante horizontal você vai desenhar (1, 2 ou 3)");
                x = sc.nextInt() - 1;
                System.out.println("Jogador 1, escolha qual quadrante vertical você vai desenhar (1, 2 ou 3)");
                y = sc.nextInt() - 1;

                if (jogo[x][y] == null) {
                    jogo[x][y] = jogadores.get(1);
                    jogadorJogou = true;
                } else {
                    System.out.println("Este quadrante já possui um desenho, escolha outro.");
                }
            }

            if (verificarSeGanhou(jogo, x, y, jogadores.get(1))) {
                vencedor = 1;
                break;
            }

            System.out.println(getJogoString(jogo));

            jogadorJogou = false;
            while (!jogadorJogou) {
                System.out.println("Jogador 2, escolha qual quadrante horizontal você vai desenhar (1, 2 ou 3)");
                x = sc.nextInt() - 1;
                System.out.println("Jogador 2, escolha qual quadrante vertical você vai desenhar (1, 2 ou 3)");
                y = sc.nextInt() - 1;
                if (jogo[x][y] == null) {
                    jogo[x][y] = jogadores.get(2);
                    jogadorJogou = true;
                } else {
                    System.out.println("Este quadrante já possui um desenho, escolha outro.");
                }
            }

            if (verificarSeGanhou(jogo, x, y, jogadores.get(2))) {
                vencedor = 2;
                break;
            }

            System.out.println(getJogoString(jogo));
        }
        System.out.println(getJogoString(jogo));
        System.out.println("O vencedor do jogo é o jogador " + vencedor + "!");
    }

    private static StringBuffer getJogoString(String[][] jogo) {
        return new StringBuffer().append(" ").append(" ").append(" ").append(jogo[0][0] != null ? jogo[0][0] : " ").append(" ").append(" ").append(" ").append("|").append(" ").append(" ").append(" ").append(jogo[0][1] != null ? jogo[0][1] : " ").append(" ").append(" ").append(" ").append("|").append(" ").append(" ").append(" ").append(jogo[0][2] != null ? jogo[0][2] : " ").append("\n")
                .append("_").append("_").append("_").append("_").append("_").append("_").append("_").append("|").append("_").append("_").append("_").append("_").append("_").append("_").append("_").append("|").append("_").append("_").append("_").append("_").append("_").append("_").append("\n")
                .append(" ").append(" ").append(" ").append(jogo[1][0] != null ? jogo[1][0] : " ").append(" ").append(" ").append(" ").append("|").append(" ").append(" ").append(" ").append(jogo[1][1] != null ? jogo[1][1] : " ").append(" ").append(" ").append(" ").append("|").append(" ").append(" ").append(" ").append(jogo[1][2] != null ? jogo[1][2] : " ").append("\n")
                .append("_").append("_").append("_").append("_").append("_").append("_").append("_").append("|").append("_").append("_").append("_").append("_").append("_").append("_").append("_").append("|").append("_").append("_").append("_").append("_").append("_").append("_").append("\n")
                .append(" ").append(" ").append(" ").append(jogo[2][0] != null ? jogo[2][0] : " ").append(" ").append(" ").append(" ").append("|").append(" ").append(" ").append(" ").append(jogo[2][1] != null ? jogo[2][1] : " ").append(" ").append(" ").append(" ").append("|").append(" ").append(" ").append(" ").append(jogo[2][2] != null ? jogo[2][2] : " ").append("\n")
                .append(" ").append(" ").append(" ").append(" ").append(" ").append(" ").append(" ").append("|").append(" ").append(" ").append(" ").append(" ").append(" ").append(" ").append(" ").append("|").append(" ").append(" ").append(" ").append(" ").append(" ").append(" ").append("\n");
    }

    private static boolean verificarSeGanhou(String[][] jogo, int x, int y, String desenho) {
        return verificarSeGanhouHorizontal(jogo, x, desenho) || verificarSeGanhouVertical(jogo, y, desenho) || verificarSeGanhouDiagonal(jogo, desenho);
    }

    private static boolean verificarSeGanhouHorizontal(String[][] jogo, int x, String desenho) {
        return jogo[x][0] != null && jogo[x][0].equals(desenho) && jogo[x][1] != null && jogo[x][1].equals(desenho) && jogo[x][2] != null && jogo[x][2].equals(desenho);
    }

    private static boolean verificarSeGanhouVertical(String[][] jogo, int y, String desenho) {
        return jogo[0][y] != null && jogo[0][y].equals(desenho) && jogo[1][y] != null && jogo[1][y].equals(desenho) && jogo[2][y] != null && jogo[2][y].equals(desenho);
    }

    private static boolean verificarSeGanhouDiagonal(String[][] jogo, String desenho) {
        return (jogo[0][0] != null && jogo[0][0].equals(desenho) && jogo[1][1] != null && jogo[1][1].equals(desenho) && jogo[2][2] != null && jogo[2][2].equals(desenho)) ||
                (jogo[2][0] != null && jogo[2][0].equals(desenho) && jogo[1][1] != null && jogo[1][1].equals(desenho) && jogo[0][2] != null && jogo[0][2].equals(desenho));
    }
}