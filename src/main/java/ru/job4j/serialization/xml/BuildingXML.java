package ru.job4j.serialization.xml;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.*;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "buildingxml")
@XmlAccessorType(XmlAccessType.FIELD)
public class BuildingXML {

    private boolean isFinished;
    private int floors;
    private String street;
    private Movement movement;
    @XmlElementWrapper(name = "apartments")
    @XmlElement(name = "floor")
    private int[][] apartments;

    public BuildingXML() {
    }

    public BuildingXML(boolean isFinished, int floors,
                       String street, Movement movement, int[][] apartments) {
        this.isFinished = isFinished;
        this.floors = floors;
        this.street = street;
        this.movement = movement;
        this.apartments = apartments;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public int[][] getApartments() {
        return apartments;
    }

    public void setApartments(int[][] apartments) {
        this.apartments = apartments;
    }

    @XmlRootElement(name = "movement")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Movement {

        private int numberOfElevators;
        private int numberOfStairs;
        private int numberOfFireEscapes;

        public Movement() {

        }

        public Movement(int numberOfElevators, int numberOfStairs, int numberOfFireEscapes) {
            this.numberOfElevators = numberOfElevators;
            this.numberOfStairs = numberOfStairs;
            this.numberOfFireEscapes = numberOfFireEscapes;
        }

        public int getNumberOfElevators() {
            return numberOfElevators;
        }

        public void setNumberOfElevators(int numberOfElevators) {
            this.numberOfElevators = numberOfElevators;
        }

        public int getNumberOfStairs() {
            return numberOfStairs;
        }

        public void setNumberOfStairs(int numberOfStairs) {
            this.numberOfStairs = numberOfStairs;
        }

        public int getNumberOfFireEscapes() {
            return numberOfFireEscapes;
        }

        public void setNumberOfFireEscapes(int numberOfFireEscapes) {
            this.numberOfFireEscapes = numberOfFireEscapes;
        }

        @Override
        public String toString() {
            return "Movement{"
                    + "numberOfElevators=" + numberOfElevators
                    + ", numberOfStairs=" + numberOfStairs
                    + ", numberOfFireEscapes=" + numberOfFireEscapes
                    + '}';
        }
    }

    @Override
    public String toString() {
        return "Building{"
                + "isFinished=" + isFinished
                + ", floors=" + floors
                + ", street='" + street + '\''
                + ", movement=" + movement
                + ", apartments=" + Arrays.deepToString(apartments)
                + '}';
    }

    public static void main(String[] args) throws Exception {
        int[][] apartments = new int[3][3];
        int counter = 1;
        for (int i = 0; i < apartments.length; i++) {
            for (int j = 0; j < apartments[i].length; j++) {
                apartments[i][j] = counter;
                counter++;
            }
        }
        BuildingXML buildingXML = new BuildingXML(true, 10, "Московская",
                new Movement(2, 2, 1), apartments);

        JAXBContext context = JAXBContext.newInstance(BuildingXML.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";

        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(buildingXML, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();

        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            BuildingXML result = (BuildingXML) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(buildingXML));
    }
}
