package com.toll_calculator.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.toll_calculator.dto.LocationDTO;
import com.toll_calculator.dto.RouteDTO;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Custom deserializer with which we convert json to
 * a list of locations
 *
 * @author Arthur Babaev
 */
@Component
public class LocationDeserializer extends JsonDeserializer<List<LocationDTO>> {

    @Override
    public List<LocationDTO> deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        List<LocationDTO> locations = new ArrayList<>();
        JsonNode node = jp.getCodec().readTree(jp);
        JsonNode locationsNode = node.get("locations");
        Iterator<Map.Entry<String, JsonNode>> iterator = locationsNode.fields();

        while (iterator.hasNext()) {
            Map.Entry<String, JsonNode> entry = iterator.next();
            Long id = Long.parseLong(entry.getKey());
            JsonNode locationNode = entry.getValue();
            String name = locationNode.get("name").asText();
            double lat = locationNode.get("lat").asDouble();
            double lng = locationNode.get("lng").asDouble();
            List<RouteDTO> routes = new ArrayList<>();
            JsonNode routesNode = locationNode.get("routes");
            for (JsonNode routeNode : routesNode) {
                Long toId = routeNode.get("toId").asLong();
                double distance = routeNode.get("distance").asDouble();
                String startDate = routesNode.get("startDate") != null ? routeNode.get("startDate").asText() : null;
                boolean enter = routeNode.has("enter") && routeNode.get("enter").asBoolean();
                boolean exit = routeNode.has("exit") && routeNode.get("exit").asBoolean();
                RouteDTO route = new RouteDTO(toId, distance, startDate, enter, exit);
                routes.add(route);
            }
            LocationDTO location = new LocationDTO(id, name, lat, lng, routes);
            locations.add(location);
        }

        return locations;
    }
}
