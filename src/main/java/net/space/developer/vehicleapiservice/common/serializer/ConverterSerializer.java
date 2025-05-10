package net.space.developer.vehicleapiservice.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import net.space.developer.vehicleapiservice.enums.gasoline.GasolineType;
import net.space.developer.vehicleapiservice.model.electrical.ElectricalRegisterInfo;

import java.io.IOException;

/**
 * Converter serializer custom class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

public class ConverterSerializer extends StdSerializer<ElectricalRegisterInfo> {

    /**
     * Default constructor for ConverterSerializer
     */
    public ConverterSerializer(){
        this(null);
    }

    /**
     * Constructor for ConverterSerializer
     *
     * @param electricalRegisterInfoClass Class type of ElectricalRegisterInfo
     */
    protected ConverterSerializer(Class<ElectricalRegisterInfo> electricalRegisterInfoClass){
        super(electricalRegisterInfoClass);
    }

    /**
     * Serializes the ElectricalRegisterInfo object to JSON
     *
     * @param electricalRegisterInfo The ElectricalRegisterInfo object to serialize
     * @param jsonGenerator          The JsonGenerator used for serialization
     * @param serializerProvider     The SerializerProvider used for serialization
     * @throws IOException If an I/O error occurs during serialization
     */
    @Override
    public void serialize(
            ElectricalRegisterInfo electricalRegisterInfo,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider
    ) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("VIN", electricalRegisterInfo.getVehicleIdentificationNumber());
        jsonGenerator.writeStringField("voltage", electricalRegisterInfo.getBatteryVoltage().toString());
        jsonGenerator.writeStringField("current", electricalRegisterInfo.getBatteryCurrent().toString());
        jsonGenerator.writeStringField("batteryType", electricalRegisterInfo.getBatteryType().name());

        if(electricalRegisterInfo.isReconverted()){
            jsonGenerator.writeStringField("reconverted", "true");

            jsonGenerator.writeObjectFieldStart("conversion_info");

            jsonGenerator.writeStringField("registration", electricalRegisterInfo.getConvertedInfo().getVehicleRegistration());

            jsonGenerator.writeArrayFieldStart("fuelType");
            for( GasolineType type : electricalRegisterInfo.getConvertedInfo().getGasolineTypes()){
                jsonGenerator.writeString(type.name());
            }
            jsonGenerator.writeEndArray();

            jsonGenerator.writeEndObject();
        }

        jsonGenerator.writeEndObject();
    }
}
