package com.SpringBlogsLatestBE.SpringBlogsLatestBE.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FlightSearchDTO {
    public String engine;
    public String departure_id;
    public String arrival_id;
    public String outbound_date;
    public String return_date;
    public String currency;
    public String hl;
    public String adults;
    public String api_key;
}
