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
public enum TipoPerfume {
    EAU_DE_TOILETTE("eau de toilette"),
    EAU_DE_PARFUM("eau de parfum"),
    PARFUM("parfum"),
    ELIXIR("elixir");

    private final String valor;

    TipoPerfume(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

   //Mismo metodo aplicado en TipoNota pero orientado al Perfume
    public static TipoPerfume coversorEnum(String texto) {
        if (texto == null) return null;
        for (TipoPerfume tipo : TipoPerfume.values()) {
            if (tipo.getValor().equalsIgnoreCase(texto)) {
                return tipo;
            }
        }
        return null; 
    }
}
