//package com.uniritter.monitor.common;
//
//import org.modelmapper.ModelMapper;
//import org.modelmapper.spi.NameTransformer;
//import org.modelmapper.spi.NameableType;
//import org.modelmapper.spi.NamingConvention;
//import org.modelmapper.spi.PropertyType;
//
//public class CustomModelMapper {
//	
//	public static ModelMapper GetModelMapper(){
//		
//		ModelMapper modelMapper = new ModelMapper();
//		
//		modelMapper.getConfiguration()
//		  .setDestinationNamingConvention(yourConvention)
//		  .setDestinationNameTransformer(yourTransformer);
//	}
//	
//	// Naming convention that matches fields and properties whose names begin with "with"
//	static NamingConvention yourConvention = new NamingConvention() {
//		
//	  public boolean applies(String propertyName, PropertyType propertyType) {
//	    return PropertyType.FIELD.equals(propertyType);
//	  }
//	  
//	};
//
//	// NameTransformer that transforms setters beginning with "with" to their property name
//	static NameTransformer yourTransformer = new NameTransformer() {
//		
//	  public String transform(String name, NameableType nameableType) {
//	    if (NameableType.METHOD.equals(nameableType) && name.startsWith("set") && name.length() > 3)
//	      return name.substring(3).toLowerCase();
//	    return name;
//	  }
//	  
//	};
//}
