import java.util.*;

public class Sudoku{

    private char[][] Board;
    private int Side = 9;

    public Sudoku() {

        Board = new char[9][9];

        for(Integer i = 0; i<Side; i++){
            for(Integer j=0; j<Side; j++){
                Board[i][j] = ' ';
            }
        }
    }

    public void set(int i, int j, char c) {

        int signal = 0;
        Boolean isNumber = Character.isDigit(c);
        if(8 < i || 8 < j)
        {
            signal = 1;
            System.out.format("Trying to access illegal cell (%d, %d)!\n", i,j);
            return;
        }

        try
        {
            if(! isNumber && c != ' ') 
            {
                System.out.format("Trying to set illegal character %c to (%d, %d)!\n", c,i,j);
                return;
            }
            Board[i][j] = c;
        }
        catch(IllegalArgumentException | ArrayIndexOutOfBoundsException ex)
        {   
            if(signal == 1) {return;}
            System.out.format("Trying to access illegal cell (%d, %d)!\n", i,j);
            return;
        }
        
        return;
    }
    
    public Boolean check() {
            // Check rows
            for(int i=0; i<Side; i++)
            {
                Set<Character> Row = new HashSet<Character>(); 
                for(int j=0; j<Side; j++)
                {
                    char x = Board[i][j];
                    if(x != ' ') 
                    {
                        // Jos matrixin rivillä on 2 samaa -> False
                        if(Row.add(x) == false)
                        {
                            System.out.format("Row %d has multiple %c's!\n", i,x);
                            return false;
                        }
                    }
                    
                }
            }
            // Check columns
            for(int j=0; j<Side; j++)
            {
                Set<Character> Column = new HashSet<Character>();
                for(int i=0; i<Side; i++)
                {
                    char x = Board[i][j]; 
                    if(x != ' ') 
                    {
                        // Jos matrixin sarakkeella on 2 samaa -> False
                        if(Column.add(x) == false)
                            {
                                System.out.format("Column %d has multiple %c's!\n", j,x);
                                return false;
                            }
                    }
                    
                }
            }
            // Check Blocks
            // Block 1
            Set<Character> Block = new HashSet<Character>();
            for(int i=0; i<3; i++)
            {
                for(int j=0;j<3;j++){
                    char x = Board[i][j];
                    if(x != ' ') {
                        if(Block.add(x) == false){
                            System.out.format("Block at (0, 0) has multiple %c's!\n", x);
                            return false;
                        }
                    }
                }
            }
            // Block 2
            Block.clear();
            for(int i=0; i<3; i++)
            {
                for(int j=3;j<6;j++){
                    char x = Board[i][j];
                    if(x != ' ') {
                        if(Block.add(x) == false){
                            System.out.format("Block at (0, 3) has multiple %c's!\n", x);
                            return false;
                        }
                    }
                }
            }
            // Block 3
            Block.clear();
            for(int i=0; i<3; i++)
            {
                for(int j=6;j<9;j++){
                    char x = Board[i][j];
                    if(x != ' ') {
                        if(Block.add(x) == false){
                            System.out.format("Block at (0, 6) has multiple %c's!\n", x);
                            return false;
                        }
                    }
                }
            }
            // Block 4
            Block.clear();
            for(int i=3; i<6; i++)
            {
                for(int j=0;j<3;j++){
                    char x = Board[i][j];
                    if(x != ' ') {
                        if(Block.add(x) == false){
                            System.out.format("Block at (3, 0) has multiple %c's!\n", x);
                            return false;
                        }
                    }
                }
            }
            // Block 5
            Block.clear();
            for(int i=3; i<6; i++)
            {
                for(int j=3;j<6;j++){
                    char x = Board[i][j];
                    if(x != ' ') {
                        if(Block.add(x) == false){
                            System.out.format("Block at (3, 3) has multiple %c's!\n", x);
                            return false;
                        }
                    }
                }
            }
            // Block 6
            Block.clear();
            for(int i=3; i<6; i++)
            {
                for(int j=6;j<9;j++){
                    char x = Board[i][j];
                    if(x != ' ') {
                        if(Block.add(x) == false){
                            System.out.format("Block at (3, 6) has multiple %c's!\n", x);
                            return false;
                        }
                    }
                }
            }
            // Block 7
            Block.clear();
            for(int i=6; i<9; i++)
            {
                for(int j=0;j<3;j++){
                    char x = Board[i][j];
                    if(x != ' ') {
                        if(Block.add(x) == false){
                            System.out.format("Block at (6, 0) has multiple %c's!\n", x);
                            return false;
                        }
                    }
                }
            }
            //Block 8
            Block.clear();
            for(int i=6; i<9; i++)
            {
                for(int j=3;j<6;j++){
                    char x = Board[i][j];
                    if(x != ' ') {
                        if(Block.add(x) == false){
                            System.out.format("Block at (6, 3) has multiple %c's!\n", x);
                            return false;
                        }
                    }
                }
            }
            //Block 9
            Block.clear();
            for(int i=6; i<9; i++)
            {
                for(int j=6;j<9;j++){
                    char x = Board[i][j];
                    if(x != ' ') {
                        if(Block.add(x) == false){
                            System.out.format("Block at (6, 6) has multiple %c's!\n", x);
                            return false;
                        }
                    }
                }
            }
            
        return true;
    }
    
    public void print() {
        int a = 0;
        int b = 0;
        for(int i=0; i<19; i++)
        {
            if(i == 0 || i==6 || i==12 || i==18)
            {
                System.out.println("#####################################");
                continue;
            }
            if(i == 2 ||i == 4 ||i == 8 ||i == 10 ||i == 14 ||i == 16){
                System.out.println("#---+---+---#---+---+---#---+---+---#");
                continue;
            }
            // 1 3 5 7 9 11 13 15 17
            for(int j=0; j<=36; j++)
            {
                if(b == 9) {b = 0;}

                if(j == 0 || j == 12 || j == 24 || j == 36) 
                {
                    System.out.format("#");
                    if(j == 36){
                        System.out.format("\n");
                        a+=1;
                    }
                }
                else if(j == 4 || j == 8 || j == 16 || j == 20 || j == 28 || j == 32)
                {
                    System.out.format("|");
                }
                else if(j == 2 || j == 6 || j == 10 || j == 14 || j == 18 || j == 22 || j == 26 || j == 30 || j == 34)
                {
                    char x = Board[a][b];
                    System.out.format(" %c ", x);
                    b += 1;
                }
                else {
                    continue;
                }
            }
        }
    }
}


