package com.prophet.web.framework.json;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * jackson 自定义BigDecimal转化器
 * @author lvzhh
 *
 */
public class CustomBigDecimalSerializer extends JsonSerializer<BigDecimal> {

	@Override
	public void serialize(BigDecimal value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
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
