package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Building {

    private boolean isFinished;
    private int floors;
    private String street;
    private Movement movement;
    private int[][] apartments;

    public Building(boolean isFinished, int floors,
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

    private static class Movement {
        private int numberOfElevators;
        private int numberOfStairs;
        private int numberOfFireEscapes;

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

    public static void main(String[] args) {
        int[][] apartments = new int[10][10];
        int counter = 1;
        for (int i = 0; i < apartments.length; i++) {
            for (int j = 0; j < apartments[i].length; j++) {
                apartments[i][j] = counter;
                counter++;
            }
        }
        Building building = new Building(true, 10, "Московская",
                new Movement(2, 2, 1), apartments);

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(building));

        String buldingStr =
                "{"
                    + "\"isFinished\":true,"
                    + "\"floors\":2,"
                    + "\"street\":\"Московская\","
                    + "\"movement\":"
                        + "{"
                            + "\"numberOfElevators\":0,"
                            + "\"numberOfStairs\":1,"
                            + "\"numberOfFireEscapes\":1"
                        + "},"
                    + "\"apartments\":[[1,2,3,4,5,6,7,8,9,10],"
                        + "[11,12,13,14,15,16,17,18,19,20]]"
                + "}";
        final Building buildingFromJson = gson.fromJson(buldingStr, Building.class);
        System.out.println(buildingFromJson);
    }
}
