/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelodedatos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Roberto Ureta
 */
public class ValidacionCampo {

    public ValidacionCampo() {
    }
    /**
     * Verifica si un campo esta vacio
     * @param s cadena de texto
     * @return true si es vacio, false en caso contrario
     */
    public boolean campoVacio(String s){
        return s.isEmpty();
    }
    
    /**
     * Verifica si un correo es valido
     * @param correo cadena con correo
     * @return true si es valido, false en caso contrario
     */
    public boolean isEmail(String correo) {
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        mat = pat.matcher(correo);
        if (mat.find()) {
            System.out.println("[" + mat.group() + "]");
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * verifica si la cadena contiene solo numeros, sin espacios.
     * @param cad cadena de texto
     * @return true si es valido, false en caso contrario
     */
    public boolean isNumeros(String cad)
    {
    	boolean corr=true;
    	int tam=cad.length();
    	int i=0;
    	
    	while(corr && i<tam)
    	{
    		corr=Character.isDigit(cad.charAt(i));
    		i++;
    	}
    	return corr;
    }
    /**
     * verifica si la cadena contiene numero incluidos espacios
     * @param str cadena de texto
     * @return true si es valido, false en caso contrario.
     */
    public boolean isCadenaNumeros(String str){
        boolean respuesta = false;
        if ((str).matches("([0-9]|\\-)+")) {
            respuesta = true;
        }
        return respuesta;
    } 
    
    /**
     * verifica si una cadena contiene solo letras
     * @param str cadena de texto
     * @return true si es valido, false en caso contrario
     */
    public  boolean isAlpha(String str) {
        boolean respuesta = false;
        if ((str).matches("([a-z]|[A-Z]|\\s)+")) {
        respuesta = true;
        }
        return respuesta;
    } 
    
    
}
