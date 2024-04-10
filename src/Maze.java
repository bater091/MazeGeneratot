import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze {

    private final static int wall = 0;
    private final int path = 1;
    private final int trap = 2;
    private final int treasure = 3;
    private final int entrance = 4;
    private final int exit = 5;


    private int entranceX;
    private int entranceY;
    private int exitX;
    private int exitY;
    private int treasureX;
    private int treasureY;


    private final int difficult = 10;
    private int legalTrapIndex;
    private int tempIndex;
    private final int trapCount = 3;

    private final List<Integer> existingPath;

    private final int[][] maze;
    private final int width;
    private final int height;
    Random random = new Random();


    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        maze = new int[width][height];
        existingPath = new ArrayList<>();
    }

    public void generateMaze(){

        generateEntranceAndExit();
        generatePath(entranceX,entranceY,exitX,exitY);
        generateTreasure();
        boolean treasureGenerate = false;

        for (int i = 0; i < difficult; i++) {



            tempIndex = random.nextInt(existingPath.size());

            if(tempIndex % 2 == 0){
                if(!treasureGenerate){
                    generatePath(treasureX,treasureY,
                            existingPath.get(tempIndex),
                            existingPath.get(tempIndex + 1));
                    treasureGenerate = true;
                    legalTrapIndex = existingPath.size();

                    continue;
                }
                generatePath(existingPath.get(tempIndex), existingPath.get(tempIndex + 1),
                        random.nextInt(height), random.nextInt(width));



            }else {

                if(!treasureGenerate){
                    generatePath(treasureX,treasureY,
                            existingPath.get(tempIndex - 1),
                            existingPath.get(tempIndex));
                    treasureGenerate = true;
                    legalTrapIndex = existingPath.size();
                    continue;
                }

                generatePath(existingPath.get(tempIndex - 1), existingPath.get(tempIndex),
                        random.nextInt(height), random.nextInt(width));

            }






        }
        generateTraps();



    }
    public void generatePath(int fx, int fy, int sx, int sy){


        while (fx != sx || fy != sy){

            int path = random.nextInt(4);
            if(maze[fx][fy] == wall) {
                maze[fx][fy] = this.path;
                existingPath.add(fx);
                existingPath.add(fy);
            }

            switch (path) {

                case 0:



                    if(fx != 0 && sx < fx) {

                        fx--;
                    }

                    break;

                case 1:



                    if(fx + 1 != height && sx > fx ){

                        fx++;
                    }

                    break;

                case 2:



                    if(fy + 1 != width && sy > fy ){

                        fy++;

                    }

                    break;

                case 3:



                    if(fy != 0 && sy < fy ){

                        fy--;
                    }

                    break;
            }
        }
        if(maze[fx][fy] == wall) {
            maze[sx][sy] = this.path;
        }


    }
    public void generateEntranceAndExit(){

        if(random.nextBoolean()){

            this.entranceX = random.nextInt(height);
            this.entranceY = 0;


        }else {

            this.entranceX = 0;
            this.entranceY = random.nextInt(width);


        }

        maze[this.entranceX][this.entranceY] = entrance;

        if(random.nextBoolean()){


            this.exitX = random.nextInt(height);
            this.exitY = width - 1;


        }else {

            this.exitX = height - 1;
            this.exitY = random.nextInt(width);


        }

        maze[this.exitX][this.exitY] = exit;


    }
    public void generateTreasure(){

        this.treasureX = random.nextInt(height);
        this.treasureY = random.nextInt(width);

        maze[this.treasureX][this.treasureY] = treasure;

    }
    public void generateTraps(){
        boolean tempLegalTrap = false;
        int tempCount = 0;
        while ( tempCount != trapCount) {

            tempIndex = random.nextInt(existingPath.size());

            if (tempIndex <= legalTrapIndex && !tempLegalTrap){
                tempLegalTrap = true;

                if (tempIndex % 2 == 0) {

                    maze[existingPath.get(tempIndex)][existingPath.get(tempIndex + 1)] = trap;

                } else {

                    maze[existingPath.get(tempIndex - 1)][existingPath.get(tempIndex)] = trap;

                }
                tempCount++;
            }

            if (tempIndex % 2 == 0 && tempIndex > legalTrapIndex) {

                maze[existingPath.get(tempIndex)][existingPath.get(tempIndex + 1)] = trap;

            } else {


                maze[existingPath.get(tempIndex - 1)][existingPath.get(tempIndex)] = trap;

            }
            tempCount++;
        }


    }
    public void printMaze(){
        for (int[] ints : maze) {
            System.out.println('\n');
            for (int j = 0; j < maze.length; j++) {

                switch (ints[j]) {
                    case wall:
                        System.out.print("#  ");
                        break;
                    case path:
                        System.out.print(".  ");
                        break;
                    case entrance:
                        System.out.print("E ");
                        break;
                    case exit:
                        System.out.print("Q  ");
                        break;
                    case trap:
                        System.out.print("()  ");
                        break;
                    case treasure:
                        System.out.print("T  ");
                        break;

                }

            }
        }


    }

    public static void main(String[] args) {
        Maze maze = new Maze(15,15);
        maze.generateMaze();
        maze.printMaze();
    }
}

