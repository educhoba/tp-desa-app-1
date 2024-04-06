package com.example.tateti;

import java.time.LocalDateTime;
import java.util.Random;

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

    public void asignarValorJugado(int i, int j, Integer jugadorUsa){
        if(tablero[i][j] == 0 ){
            tablero[i][j] = jugadorUsa;
        }
        //no hago nada
    }

    public int proximoMovimiento(Integer maquinaUsa) {

        boolean seleccionado = false;



        int num = (int)(Math.random()*8 + 1);
        while(!seleccionado){

        num = (int)(Math.random()*8 + 1);

        switch (num){
            case 1:
                if (tablero[0][0] == 0){
                    seleccionado = true;
                    tablero[0][0] = maquinaUsa;
                }
                break;
            case 2:
                if (tablero[0][1] == 0){
                    seleccionado = true;
                    tablero[0][1] = maquinaUsa;
                }
                break;
            case 3:
                if (tablero[0][2] == 0){
                    seleccionado = true;
                    tablero[0][2] = maquinaUsa;
                }
                break;
            case 4:
                if (tablero[1][0] == 0){
                    seleccionado = true;
                    tablero[1][0] = maquinaUsa;
                }
                break;
            case 5:
                if (tablero[1][1] == 0){
                    seleccionado = true;
                    tablero[1][1] = maquinaUsa;
                }
                break;
            case 6:
                if (tablero[1][2] == 0){
                    seleccionado = true;
                    tablero[1][2] = maquinaUsa;
                }
                break;
            case 7:
                if (tablero[2][0] == 0){
                    seleccionado = true;
                    tablero[2][0] = maquinaUsa;
                }
                break;
            case 8:
                if (tablero[2][1] == 0){
                    seleccionado = true;
                    tablero[2][1] = maquinaUsa;
                }
                break;
            case 9:
                if (tablero[2][2] == 0){
                    seleccionado = true;
                    tablero[2][2] = maquinaUsa;
                }
                break;
            default:
                break;
        }

        }
        return num;
    }

    public boolean gano(){

        //quiero saber si gano cruces o circulos

        int primero = -1;
        int segundo = -1;
        int tercero = -1;

        for(int i = 0; i<3; i++) {

            primero = tablero[i][0];
            segundo = tablero[i][1];
            tercero = tablero[i][2];

            if (checkTaTeTi(primero, segundo, tercero)) return true;
            primero = -1;
            segundo = -1;
            tercero = -1;
        }

        for(int j = 0; j<3; j++) {

            primero = tablero[0][j];
            segundo = tablero[1][j];
            tercero = tablero[2][j];

            if (checkTaTeTi(primero, segundo, tercero)) return true;
            primero = -1;
            segundo = -1;
            tercero = -1;
        }

        //falta chequear las diagonales

        //diagonal izquierda a derecha
        primero = tablero[0][0];
        segundo = tablero[1][1];
        tercero = tablero[2][2];

        if (checkTaTeTi(primero, segundo, tercero)) return true;

        //diagonal derecha a izquierda
        primero = tablero[0][2];
        segundo = tablero[1][1];
        tercero = tablero[2][0];

        if (checkTaTeTi(primero, segundo, tercero)) return true;

        return false;
    }

    private static boolean checkTaTeTi(int primero, int segundo, int tercero) {
        if(primero != 0 && primero == segundo && primero == tercero){
            //hay
            return true;
        }
        return false;
    }

}
