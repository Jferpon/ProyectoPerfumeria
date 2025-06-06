/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author jferpon
 */
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

   
    public static TipoPerfume fromString(String texto) {
        if (texto == null) return null;
        for (TipoPerfume tipo : TipoPerfume.values()) {
            if (tipo.getValor().equalsIgnoreCase(texto)) {
                return tipo;
            }
        }
        return null; // o lanzar excepción si prefieres
    }
}

