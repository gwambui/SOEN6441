package model;

public interface Property {

    String getType();

    String getBuildingName();

    int getId();

    String getStreet();

    String getCity();

    String getPostalCode();

    int getStreetNumber();
    boolean isAvailable();
}