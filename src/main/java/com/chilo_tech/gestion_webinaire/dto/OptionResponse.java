package com.chilo_tech.gestion_webinaire.dto;

import java.util.Map;

public record OptionResponse (
        Map<String, Object> poll,
        Map<String,Object> option

){
}
