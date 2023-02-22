package com.springboot.apache.cxf.factory;

import com.springboot.apache.cxf.generated.ArrayOftCountryCodeAndNameGroupedByContinent;
import com.springboot.apache.cxf.generated.CountryNameResponse;
import com.springboot.apache.cxf.generated.ListOfCountryNamesGroupedByContinentResponse;
import org.springframework.stereotype.Component;

@Component
public class CountryInfoFactory {

    public CountryNameResponse mapCountryNameResponse(String countryName) {
        CountryNameResponse countryNameResponse = new CountryNameResponse();
        countryNameResponse.setCountryNameResult(countryName);
        return countryNameResponse;
    }

    public ListOfCountryNamesGroupedByContinentResponse mapListOfCountryNamesGroupedByContinentResponse(ArrayOftCountryCodeAndNameGroupedByContinent response) {
        ListOfCountryNamesGroupedByContinentResponse listOfCountryNamesGroupedByContinentResponse = new ListOfCountryNamesGroupedByContinentResponse();
        listOfCountryNamesGroupedByContinentResponse.setListOfCountryNamesGroupedByContinentResult(response);
        return listOfCountryNamesGroupedByContinentResponse;
    }

}
