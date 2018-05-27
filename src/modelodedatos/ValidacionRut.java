/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelodedatos;


/**
 *
 * @author Josema
 */
public class ValidacionRut {

    
    ValidacionCampo validacionCampo;

    public ValidacionRut() {
    }

   /**
     * verifica que la cadena sea un rut valido, sin puntos y con digito verificador.
     * @param cad cadena de texto
     * @return true si es valido, false en caso contrario
     */

    public boolean validacion_rut(String rut) {
        

        if (validacionCampo.isCadenaNumeros(rut) == false){
        
        return false;
        }
        Boolean lDevuelve = false;
        int Ult = rut.length();
        int Largo = rut.length() - 3;
        int Constante = 2;
        int Suma = 0;
        int Digito = 0;
        
        if(Ult>=12){
        
            return false;
        }

        for (int i = Largo; i >= 0; i--) {

            Suma = Suma + Integer.parseInt(rut.substring(i, i + 1))
                          * Constante;
            Constante = Constante + 1;
            if (Constante == 8) {
                Constante = 2;
            }
        }
        String Ultimo = rut.substring(Ult - 1).toUpperCase();
        Digito = 11 - (Suma % 11);
        if (Digito == 10 && Ultimo.equals("K")) {
            lDevuelve = true;
        }
        else {
            if (Digito == 11 && Ultimo.equals("0")) {
                lDevuelve = true;
            }
            else {

                if (Digito == Integer.parseInt(Ultimo)) {

                    lDevuelve = true;

                }

            }

        }
        return lDevuelve;

    }

}
