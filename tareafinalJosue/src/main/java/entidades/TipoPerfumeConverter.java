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
@Converter(autoApply = true)
public class TipoPerfumeConverter implements AttributeConverter<TipoPerfume, String> {

    @Override
    public String convertToDatabaseColumn(TipoPerfume tipo) {
        return tipo != null ? tipo.getValor(): null;
    }

    @Override
    public TipoPerfume convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return TipoPerfume.fromString(dbData); // debe hacer la conversión correctamente
    }
}