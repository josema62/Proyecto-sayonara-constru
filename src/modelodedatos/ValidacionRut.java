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
 * @author Josema
 */
public class ValidacionRut {

    
   

    public ValidacionRut() {
    }

   /**
     * verifica que la cadena sea un rut valido, con puntos y con digito verificador.
     * @param rut cadena de texto
     * @return true si es valido, false en caso contrario
     */
    
    
    public Boolean validaRut ( String rut ) {
		Pattern pattern = Pattern.compile("^[0-9]+-[0-9kK]{1}$");
		Matcher matcher = pattern.matcher(rut);
		if ( matcher.matches() == false ) 
			return false;
		String[] stringRut = rut.split("-");
		return stringRut[1].toLowerCase().equals(ValidacionRut.dv(stringRut[0]));
		
	}
	public static String dv ( String rut ) {
		Integer M=0,S=1,T=Integer.parseInt(rut);
		for (;T!=0;T=(int) Math.floor(T/=10))
			S=(S+T%10*(9-M++%6))%11;
		return ( S > 0 ) ? String.valueOf(S-1) : "k";		
}


}
