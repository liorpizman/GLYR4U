package Model;

public class Location {

    private String Country;
    private String City;


    public Location(String country, String city) {
        Country = country;
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public String getCity() {
        return City;
    }

}
