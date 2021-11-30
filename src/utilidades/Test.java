/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Mario Zelaya
 */
public class Test {
    public static void main(String[] args) {
 // Patrón para validar el email
 //
 //^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$");

        // El email a validar
        String email = "correo@correo.com";

        Matcher mather = pattern.matcher(email);

        if (ValidarMail(email)) {
            System.out.println("El email ingresado es válido.");
        } else {
            System.out.println("El email ingresado es inválido.");
        }
        
    }
 
    public static boolean ValidarMail(String email) {
        // Patron para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
 
        Matcher mather = pattern.matcher(email);
        return mather.find();
    }
}
