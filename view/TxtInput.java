package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TxtInput {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner read = null;
        String type,street, city, postalCode, buildingName, country;
        int id,streetNumber, numberofUnits;
        try {
           read = new Scanner(new File("ph1\\src\\data\\sampleProps.txt"));
           read.useDelimiter(",");
            while (read.hasNext())
            {
//                System.out.println(read.next());
                type = read.next().strip();
                id = Integer.parseInt(read.next());
                street = read.next();
                city = read.next();
                postalCode = read.next();
                streetNumber = Integer.parseInt(read.next());
                numberofUnits = Integer.parseInt(read.next());
                buildingName = read.next();
                country = read.next();

//
                System.out.println( type +" "+id +" "+ street +" "+city+" "+postalCode
                        +" "+streetNumber +" "+ numberofUnits+" "+ buildingName +" "+  country +"\n ");


            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        read.close();

    }
}
