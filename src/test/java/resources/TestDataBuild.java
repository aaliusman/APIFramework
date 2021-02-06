package resources;

import pojoSerialization.AddPlace;
import pojoSerialization.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayload(String name, String language, String address, String typesOne, String typesTwo, double lat){
        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setName(name);
        addPlace.setPhone_number("215-000-0000");
        addPlace.setAddress(address);
        addPlace.setWebsite("www.google.com");
        addPlace.setLanguage(language);
        List<String> types = new ArrayList<String>();
        types.add(typesOne);
        types.add(typesTwo);
        addPlace.setTypes(types);
        Location location = new Location();
        location.setLat(lat);
        location.setLng(33.23232);
        addPlace.setLocation(location);
        return addPlace;
    }

    public String deleteAddress(String place_id) {
        return "{\n" +
                "    \"place_id\":\""+place_id+"\"\n" +
                "}";
    }
}
