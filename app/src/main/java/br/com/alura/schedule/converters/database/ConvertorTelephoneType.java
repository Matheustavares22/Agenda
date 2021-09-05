package br.com.alura.schedule.converters.database;

import androidx.annotation.NonNull;
import androidx.room.TypeConverter;

import br.com.alura.schedule.model.TelephoneType;

public class ConvertorTelephoneType {

    @TypeConverter
    @NonNull
    public String toString(TelephoneType type) {
        return type.name();
    }

    @NonNull
    public TelephoneType toTelephoneType(String value) {
        if (value != null){
            return TelephoneType.valueOf(value);
        }
        return TelephoneType.CELLPHONE;
    }
}
