package com.pitang.aula.mapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;

public class ModelConverter {
	
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
}