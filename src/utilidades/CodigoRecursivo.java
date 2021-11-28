/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

/**
 *
 * @author Julio
 */
    public class CodigoRecursivo {

    public static String generarCodigo(String a, int n) {

        if (a.length() == 6) { // caso base
            return a;
        } else {
            if (a.length() == 3 && n < 10) { // MAT001 - MAT009
                return generarCodigo(a + "00" + n, 0);
            } else if (n >= 10 && n < 100) { // MAT010 - MAT099
                return generarCodigo(a + "0" + n, 0);
            } else if (n <= 100) { // MAT100 - MAT999 
                return generarCodigo(a + n, 0);
            }
        }
        return null;
    }
}
                            