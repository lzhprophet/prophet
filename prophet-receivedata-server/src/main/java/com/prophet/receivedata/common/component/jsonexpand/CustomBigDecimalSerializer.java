package com.prophet.receivedata.common.component.jsonexpand;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


public class CustomBigDecimalSerializer extends JsonSerializer<BigDecimal> {

	@Override
	public void serialize(BigDecimal value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
			JsonProcessingException {
		String bigdecimalJson = this.process(value);
		jgen.writeString(bigdecimalJson);
	}

	public String process(Object value) {
		String str = "";
		if (null != value && value instanceof BigDecimal) {
			BigDecimal bigValue = (BigDecimal) value;
			bigValue = bigValue.setScale(2, BigDecimal.ROUND_HALF_UP);
			str = bigValue.toString();
		}
		return str;
	}

}
