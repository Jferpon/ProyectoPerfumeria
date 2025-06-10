/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author jferpon
 */
//Clase o entidad Enum para controlar el tipo de dato dentro de la base de datos que es enum
public enum TipoNota {
    SALIDA("salida"),
    CORAZON("corazon"),
    BASE("base");

    private final String valor;

    //Constructor con parametros al ser final 
    private TipoNota(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    //Convierte el texto o String a un dato enum segun lo creado
    public static TipoNota conversorEnum(String texto) {
        if (texto == null) {
            return null;
        }
        for (TipoNota tipo : TipoNota.values()) {
            if (tipo.getValor().equalsIgnoreCase(texto)) {
                return tipo;
            }
        }
        return null;
    }
}
