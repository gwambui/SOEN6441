package model;

public interface Property {

    String getType();

    String getBuildingName();

    int getId();

    int getBuildingId();
    String getStreet();

    String getCity();

    String getPostalCode();

    int getStreetNumber();
    boolean isAvailable();
}