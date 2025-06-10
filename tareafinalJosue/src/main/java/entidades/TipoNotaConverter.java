/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author jferpon
 */
//Necesito hacer este metodo o clase entidad utilizando converter para solucionar los problemas que 
//tuve con el enum
@Converter(autoApply = false)
public class TipoNotaConverter implements AttributeConverter<TipoNota, String> {

    
    //Son metodos de superclase que tuve que buscar no puedo cambiar el nombre
    
    //Lo convierte a una columna en este caso segun el enum TipoNota
    @Override
    public String convertToDatabaseColumn(TipoNota tipo) {
        return tipo != null ? tipo.getValor() : null;
    }

    //Lo convierte a un atributo para la entidad NotaAromatica
    @Override
    public TipoNota convertToEntityAttribute(String dbData) {
        return TipoNota.conversorEnum(dbData);
    }
}
