import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.io.*;
import java.io.BufferedReader;
import java.net.URL;

public class Enrolment {

    static Multimap<String, String> myMultimap = ArrayListMultimap.create();
    FileReader reader;

    public void readFile(String filePath){
        URL url = Enrolment.class.getClassLoader().getResource(filePath);

        File file = new File(url.getFile());
        try{
            reader = new FileReader(file);

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void verifyAndStoreEnrollment(){

        try (BufferedReader bufferReader = new BufferedReader(reader)) {

            String line;

            while((line = bufferReader.readLine())!= null) {
                String studentID = line.split(",")[0];
                String firstName = line.split(",")[1];
                String lastName = line.split(",")[2];
                String identifier = line.split(",")[3];
                System.out.println(line);
                if(!studentID.matches("[^OI]{3}[0-9]{4}")) {
                    System.out.println("Invalid Student ID format, Enrollment not stored!");
                }else {
                    System.out.println("Valid Student ID format, Enrollment Stored!");
                    myMultimap.put(identifier, studentID+","+firstName+","+lastName);
                }
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
    public void enrolledStudentList() {
        System.out.println("Enrollment List");

        for (String key : myMultimap.keySet()) {
            System.out.println(key + ":" + myMultimap.get(key).size());
        }
    }

    public static void main(String[] args) {

        Enrolment enrolment = new Enrolment();
        enrolment.readFile("StudentData.txt");
        enrolment.verifyAndStoreEnrollment();
        enrolment.enrolledStudentList();







    }
}
