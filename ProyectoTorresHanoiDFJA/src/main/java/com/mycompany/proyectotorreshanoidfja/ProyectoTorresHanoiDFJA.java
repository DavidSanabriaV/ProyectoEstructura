package com.mycompany.proyectotorreshanoidfja;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class ProyectoTorresHanoiDFJA {

    public static void main(String[] args) {

        //Creacion de objeto para llamar los metodos para ejecutar el juego 
        HanoiController juego = new HanoiController(3);
        juego.juego();

    }
}
