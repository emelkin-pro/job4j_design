package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean rsl = false;
        if (row < data.length) {
            if (column < data[row].length) {
                rsl = true;
            } else {
                if (row <= data.length - 1) {
                    for (int i = row; i < data.length; i++) {
                        if (data[i].length != 0) {
                            if (column < data[i].length) {
                                rsl = true;
                                break;
                            }
                        }
                    }
                }
            }
        }

        return rsl;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        do {
            if (data[row].length == 0) {
                row++;
            }
        } while (row < data.length - 1 && data[row].length == 0);

        int rsl = data[row][column];

        if (column < data[row].length - 1) {
            column++;
        } else {
            do {
                row++;
                column = 0;
                if (row >= data.length) {
                    break;
                }
            } while (row < data.length - 1 && data[row].length == 0);
        }
        return rsl;
    }
}