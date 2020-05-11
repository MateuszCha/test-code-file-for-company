import java.util.*;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    private Stack<Tile[][]> previousStates = new Stack(); //stack
    private Stack<Integer> previousScores = new Stack(); //stack
    int score = 0;
    int maxTile = 0;
    private boolean isSaveNeeded = true;

    public Model(){
        this.gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        resetGameTiles();

    }
    public void  resetGameTiles(){
        for(int i = 0; i< gameTiles.length; i++ ){
            for(int j = 0; j< gameTiles[0].length; j++){
                gameTiles[j][i] = new Tile();
            }
        }
        addTile();
        addTile();
        this.score = 0;
        this.maxTile = 0;
    }
    public void addTile(){
        List<Tile> list = getEmptyTiles();
        if(list.size() > 0) {
            int index = (int) (Math.random() * list.size());
            list.get(index).value = Math.random() < 0.9 ? 2 : 4;
        }

    }
    private List<Tile> getEmptyTiles(){
        List<Tile> list = new ArrayList<>();
        for(int i = 0; i< gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[0].length; j++) {
                if(gameTiles[i][j].isEmpty())
                    list.add(gameTiles[i][j]);
            }
        }
        return list;
    }
    private boolean consolidateTiles(Tile[] tiles)
    {
        boolean check = false;
        int value = 0;
        for(int i = 0 ; i<tiles.length;i++){
            if(tiles[i].value != 0)
                value++;
        }
        for(int j = 0; j < value; j++) {
            for (int i = tiles.length - 1; i > 0; i--) {
                if (tiles[i].value != 0 && tiles[i - 1].value == 0) {
                    int valuePom = tiles[i].value;
                    tiles[i].value = tiles[i - 1].value;
                    tiles[i - 1].value = valuePom;
                    check = true;
                }
            }
        }
        return check;
    }
    private boolean mergeTiles(Tile[] tiles)
    {
        boolean check = false;
        for(int i = 0; i< tiles.length-1;i++)
        {
            if(tiles[i].value == tiles[i+1].value && tiles[i].value != 0)
            {
                check = true;
                tiles[i].value *=2;
                tiles[i+1].value = 0;
                this.score += tiles[i].value;
                if(this.maxTile < tiles[i].value)
                    this.maxTile = tiles[i].value;
                consolidateTiles(tiles);
            }
        }
        return check;
    }
     void left(){
        if(isSaveNeeded)
            saveState(this.gameTiles);
        boolean value1 = false;
        for(int i = 0; i<gameTiles.length; i++) {
            value1 |= consolidateTiles(gameTiles[i]);
            value1 |= mergeTiles(gameTiles[i]);
        }
         if(value1)
             addTile();
         isSaveNeeded = true;
    }
    void right() {
        this.saveState(this.gameTiles);
        rotateClockwise();
        rotateClockwise();
        left();
        rotateClockwise();
        rotateClockwise();

    }
    void up(){
        this.saveState(this.gameTiles);
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        left();
        rotateClockwise();

    }
    void down(){
        this.saveState(this.gameTiles);
        rotateClockwise();
        left();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }
    private void rotateClockwise(){
        Tile[][] pom = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for(int i =0 ; i<gameTiles.length;i++){
            for(int j =0; j< gameTiles[0].length;j++){
                pom[j][FIELD_WIDTH-1-i] = gameTiles[i][j];
            }
        }
        gameTiles = pom;
    }
    boolean canMove(){
        for(int i = 0 ; i < gameTiles.length;i++) { // -1
            for (int j = 0; j < gameTiles[0].length ; j++) { //-1
                if(gameTiles[i][j].isEmpty())
                    return true;
                if(i < gameTiles.length-1 && gameTiles[i][j].value == gameTiles[i+1][j].value)
                    return true;
                if(j < gameTiles[0].length-1 && gameTiles[i][j].value == gameTiles[i][j+1].value)
                    return true;
               // if(gameTiles[i][j].value == gameTiles[i+1][j].value || gameTiles[i+1][j].isEmpty())
                //    return true;
               // if(gameTiles[i][j].value == gameTiles[i][j+1].value || gameTiles[i][j+1].isEmpty())
              //      return true;
            }
        }
        return false;
    }
    private void saveState(Tile[][] tiles){
        Tile[][] newTab =  new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for(int i = 0 ; i < gameTiles.length;i++) {
            for (int j = 0; j < gameTiles[0].length; j++) {
                newTab[i][j] = new Tile(tiles[i][j].value);
              }
        }
        previousStates.push(newTab);
        previousScores.push(this.score);
        isSaveNeeded = false;
    }
    public void rollback() {
        if (!this.previousStates.empty() && !this.previousScores.empty()){
            this.gameTiles = previousStates.pop();
            this.score = previousScores.pop();
        }

    }
    void randomMove(){
        int n = ((int) (Math.random () * 100)) % 4;
        switch (n) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
        }

    }
    boolean hasBoardChanged(){
        if(this.previousStates.empty())
            return false;
        Tile[][] tilesPom = previousStates.peek();
        for(int i = 0; i < this.gameTiles.length; i++){
            for(int j =0; j < gameTiles[0].length; j++){
                if(gameTiles[i][j].value != tilesPom[i][j].value)
                    return true;
            }
        }
        return false;
    }
    MoveFitness getMoveFitness(Move move){
        MoveFitness moveFitness = null;
        if(!hasBoardChanged()) {
            moveFitness = new MoveFitness(-1, 0, move);

        } else  moveFitness = new MoveFitness(getEmptyTiles().size(), this.score, move);
        moveFitness.getMove().move();
        this.rollback();


        return moveFitness;
    }
    void autoMove(){
        PriorityQueue<MoveFitness> queue = new PriorityQueue<MoveFitness>(4, Collections.reverseOrder());
          queue.add(getMoveFitness(this::left));
          queue.add(getMoveFitness(this::up));
          queue.add(getMoveFitness(this::right));
          queue.add(getMoveFitness(this::down));
          queue.peek().getMove().move();
    }

/////////////////////GETTERS & SETTERS///////////////////
    public Tile[][] getGameTiles() {
        return gameTiles;
    }


    /* private static void consolidateTiles(int[] tiles)
    {
        int value = 0;
        for(int i = 0 ; i<tiles.length;i++){
            if(tiles[i] != 0)
                value++;
        }
        for(int j = 0; j< value; j++) {
            for (int i = tiles.length - 1; i > 0; i--) {
                if (tiles[i] != 0 && tiles[i - 1] == 0) {
                    int valuePom = tiles[i];
                    tiles[i] = tiles[i - 1];
                    tiles[i - 1] = valuePom;
                }
            }
        }
        System.out.println(Arrays.toString(tiles));
    }

    */
    /*
    public static void main(String[] args){
        Model model = new Model();
        Model.consolidateTiles(new int[]{0,0,2,0});
        Model.consolidateTiles(new int[]{0,2,2,2});
    }

     */
}
//2. Model - It will contain the game logic and store the game board.