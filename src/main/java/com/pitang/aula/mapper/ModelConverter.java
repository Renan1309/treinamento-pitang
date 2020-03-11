package com.pitang.aula.mapper;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;

public class ModelConverter {
	
	private static final String OUTPUT_FORMAT_FOR_DATE_HOUR = "dd/MM/yyyy hh:mm:ss";
	
	private ModelConverter() {
		throw new IllegalStateException("Utility class");
	}
	
	public static final Converter<Boolean, String> convertStatus = new AbstractConverter<Boolean, String>() {
		@Override
		protected String convert(Boolean source) {
			if(source) {
				return "Ativo";
			}else {
				return "Inativo";
			}
		}
	};
	
	public static final Converter<String, Boolean> convertStatusToBoolean = new AbstractConverter<String, Boolean>() {
		@Override
		protected Boolean convert(String source) {
			if(source.equals("Ativo")) {
				return true;
			}else {
				return false;
			}
		}
	};
	
	
	public static final Converter<Date, String> fromDateToString = new AbstractConverter<Date, String>() {
		@Override
		protected String convert(Date source) {
			if(source == null) {
				return null;
			}
			SimpleDateFormat df = new SimpleDateFormat(OUTPUT_FORMAT_FOR_DATE_HOUR);
			return df.format(source.getTime());
		}
	};


	
}