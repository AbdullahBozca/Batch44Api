package utilities;

import get_http_request.day15.Customer;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class WriteToText {

    public static void saveSSNData(String fileName, Customer[] customers) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

            for (int i = 0; i < customers.length; i++) {
                writer.append(customers[i].getSsn() + ",\n");
            }
            writer.close();
        } catch (Exception e) {
        }
    }

    public static void saveEmailData(String fileName, Customer[] customers) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

            for (int i = 0; i < customers.length; i++) {
                writer.append(customers[i].getEmail() + ",\n");
            }
            writer.close();
        } catch (Exception e) {
        }
    }


    /*
    First Name,
    Last Name,
    email,
    mobilePhone,
    Adres
    city
     */

    public static void saveCustomersData(String fileName, Customer[] customers) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

            for (int i = 0; i < customers.length; i++) {
                writer.append(customers[i].getFirstName() + ",\n");
                writer.append(customers[i].getLastName() + ",\n");
                writer.append(customers[i].getEmail() + ",\n");
                writer.append(customers[i].getMobilePhoneNumber() + ",\n");
                writer.append(customers[i].getAddress() + ",\n");
                writer.append(customers[i].getCity() + ",\n");
                writer.append("-----------------------------------" + ",\n");
            }
            writer.close();
        } catch (Exception e) {
        }
    }
}