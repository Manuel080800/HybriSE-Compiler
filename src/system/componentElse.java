
package system;

import java.util.ArrayList;

public class componentElse 
{
    String nivel = "";
    String condicion = "";
    ArrayList <Integer> posiciones = new ArrayList <Integer>();
    
    public void addElse (String nivel, String condicion, ArrayList <Integer> posiciones)
    {
        this.nivel = nivel;
        this.condicion = condicion;
        
        for (int i = 0; i < posiciones.size(); i++) {
            this.posiciones.add(posiciones.get(i));
        }
    }

    public String getNivel() {
        return nivel;
    }

    public String getCondicion() {
        return condicion;
    }

    public ArrayList<Integer> getPosiciones() {
        return posiciones;
    }
}
