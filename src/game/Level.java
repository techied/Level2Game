package game;

import com.sun.javafx.geom.Vec4f;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Level implements Comparable<Level> {

    private ArrayList<Wall> walls;
    private float startX, startY;
    private float order;


    Level(String fp) {

        BufferedReader br = null;
        FileReader fr = null;

        walls = new ArrayList<>();
        walls.add(new Wall(0, Game.HEIGHT, Game.WIDTH, Ball.height));
        walls.add(new Wall(-Ball.width, 0, Ball.width, Game.WIDTH));
        walls.add(new Wall(Game.WIDTH, 0, Ball.width, Game.HEIGHT));
        walls.add(new Wall(0, -Ball.height, Game.WIDTH, Ball.height));

        try {

            fr = new FileReader(fp);
            br = new BufferedReader(fr);

            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                readLevel(currentLine);

            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }
    }

    private void readLevel(String currentLine) {
        boolean goal = false;
        boolean x = false;
        boolean y = false;
        boolean movingWall = false;
        boolean _order = false;
        boolean spike = false;
        StringBuilder currentNumber = new StringBuilder();
        ArrayList<Float> input = new ArrayList<>();
        for (int i = 0; i < currentLine.length(); i++) {
            char currentChar = currentLine.charAt(i);
            if (currentChar == ' ') {
                input.add(Float.parseFloat(currentNumber.toString()));
                currentNumber = new StringBuilder();
            } else if (currentChar == 'g') {
                goal = true;
            } else if (currentChar == 'x') {
                x = true;
            } else if (currentChar == 'y') {
                y = true;
            } else if (currentChar == 'm') {
                movingWall = true;
            } else if (currentChar == 'o') {
                _order = true;
            } else if (currentChar == 's') {
                spike = true;
            } else {
                currentNumber.append(currentChar);
            }
        }

        if (goal) {
            walls.add(new Goal(input.get(0), input.get(1), input.get(2), input.get(3)));
        } else if (x) {
            startX = input.get(0);
        } else if (y) {
            startY = input.get(0);
        } else if (_order) {
            order = input.get(0);
        } else if (movingWall) {
            walls.add(new MovingWall(input.get(0), input.get(1), input.get(2), input.get(3), input.get(4), input.get(5), new Vec4f(input.get(6), input.get(7), input.get(8), input.get(9))));
        } else if (spike) {
            walls.addAll(Spike.makeSpikes(input.get(0), input.get(1), input.get(2), input.get(3), input.get(4), input.get(5)));
        } else {
            walls.add(new Wall(input.get(0), input.get(1), input.get(2), input.get(3)));
        }
    }

    ArrayList<Wall> getWalls() {
        return walls;
    }

    float getStartX() {
        return startX;
    }

    float getStartY() {
        return startY;
    }

    private float getOrder() {
        return order;
    }

    @Override
    public int compareTo(Level o) {
        return Math.round(this.order - o.getOrder());
    }

}
