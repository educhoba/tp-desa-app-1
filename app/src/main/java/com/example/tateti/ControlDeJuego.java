package com.example.tateti;

public class ControlDeJuego {

    /*
    * Si el valor es 0 esta vacia
    * Si el valor es 1 cruces
    * Si el valor es 2 circulos
    * */
    int[][] tablero = new int[3][3];

    public ControlDeJuego(){
        for(int i = 0; i<3; i++)
            for(int j = 0; j < 3; j++)
                tablero[i][j] = 0;
    }

    public void asignarValorJugado(int i, int j, String usa){

    }

    public int[] proximoMovimiento(){
        int[] resultado = null;
    /*
    * La estrategia de juego de la app
    * */
        return resultado;
    }

    public boolean gano(){
        return false;
    }

}
