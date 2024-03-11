package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.DTOs.FlightSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class Serpapiservices {

    @Autowired
    public RestTemplate restTemplate;
    @Value("${api_key}")
    public String api_key;

    public List<Object> getalldata(FlightSearchDTO flightSearchDTO){
        String finalurl="https://serpapi.com/" +
                "search?engine="+flightSearchDTO.getEngine()
                +"&departure_id="+flightSearchDTO.getDeparture_id()
                +"&arrival_id="+flightSearchDTO.getArrival_id()
                +"&outbound_date="+flightSearchDTO.getOutbound_date()
                +"&return_date="+flightSearchDTO.getReturn_date()
                +"&currency="+flightSearchDTO.getCurrency()
                +"&hl="+flightSearchDTO.getHl()
                +"&adults="+flightSearchDTO.getAdults()
                +"&api_key="+api_key;


       // String url="https://serpapi.com/search?&engine=google_flights&departure_id=BOM&arrival_id=AUS&outbound_date=2024-03-25&return_date=2024-03-28&currency=USD&hl=en&adults=2&api_key=f3014e8d294ba28783c9626199c333ae1e3919899c886aa4052798ae958ae19a";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>( headers);
        List<Object> objects= Collections.singletonList(restTemplate.exchange(finalurl, HttpMethod.GET, entity, Object.class));
        return objects;
    }
  //  headers.add("engine","google_flights");
//        headers.add("departure_id","BOM");
//        headers.add("arrival_id","AUS");
//        headers.add("outbound_date","2024-03-25");
//        headers.add("return_date","2024-03-28");
//        headers.add("currency","USD");
//        headers.add("hl","en");
//        headers.add("adults","2");
//        headers.add("api_key","f3014e8d294ba28783c9626199c333ae1e3919899c886aa4052798ae958ae19a");

//        ObjectMapper objectMapper=new ObjectMapper();
//        //List<Object> objects= List.of(this.restTemplate.getForObject(url, List.class));
//        JsonNode objectsData=this.restTemplate.getForObject(url, JsonNode.class);
//        List<Object> objects=objectMapper.convertValue(objectsData, new TypeReference<List<Object>>() {
//        });


    public List<Object> getalljsonData(){
        String url="https://jsonplaceholder.typicode.com/posts";
        List<Object> objects= List.of(this.restTemplate.getForObject(url, Object[].class));
        System.out.println(objects);
        return objects;
    }


}
