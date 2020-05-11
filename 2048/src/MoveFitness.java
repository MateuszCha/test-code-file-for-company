public class MoveFitness implements Comparable<MoveFitness> {
    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    MoveFitness(int numberOfEmptyTiles, int score, Move move){
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }
////////////////////////////GETTERS AND SETTERS//////////////////////////////////////////
    public int getNumberOfEmptyTiles() {
        return numberOfEmptyTiles;
    }

    public int getScore() {
        return score;
    }

    public Move getMove() {
        return move;
    }
///////////////////////OVERRIDE ////////////////////////////////////
    @Override
    public int compareTo(MoveFitness o)  {
        if(this.numberOfEmptyTiles == o.numberOfEmptyTiles)
            return Integer.compare(this.score, o.score);
        else return Integer.compare(this.numberOfEmptyTiles, o.numberOfEmptyTiles);
}
}
