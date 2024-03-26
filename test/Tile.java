package test;
import java.util.Random;
import java.util.Arrays;

public class Tile {
    private final char letter;
    private final int score;

 private Tile(char letter, int score) {
    this.letter = letter;
    this.score = score;
    }

@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + letter;
    result = prime * result + score;
    return result;
}

@Override
public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    Tile other = (Tile) obj;
    if (letter != other.letter)
        return false;
    if (score != other.score)
        return false;
    return true;
}
    public static class Bag {
        private final int[] quantity;
        private final Tile[] tile;

        private Bag() {
            this.quantity = new int[]{9, 2, 2, 4, 12,2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1,6, 4, 6, 4, 2, 2,1, 2, 1};
            this.tile = new Tile[]{
                    new Tile('A', 1),
                    new Tile('B', 3),
                    new Tile('C', 3),
                    new Tile('D', 2),
                    new Tile('E', 1),
                    new Tile('F', 4),
                    new Tile('G', 2),
                    new Tile('H', 4),
                    new Tile('I', 1),
                    new Tile('J', 8),
                    new Tile('K', 5),
                    new Tile('L', 1),
                    new Tile('M', 3),
                    new Tile('N', 1),
                    new Tile('O', 1),
                    new Tile('P', 3),
                    new Tile('Q', 10),
                    new Tile('R', 1),
                    new Tile('S', 1),
                    new Tile('T', 1),
                    new Tile('U', 1),
                    new Tile('V', 4),
                    new Tile('W', 4),
                    new Tile('X', 8),
                    new Tile('Y', 4),
                    new Tile('Z', 10)
            };
        }

        private int sum() {
            int sum = 0;
            for (int i = 0; i < quantity.length; i++) {
                sum += quantity[i];
            }
            return sum;
        }

        public Tile getRand() {
            Random rand = new Random();
            int index = rand.nextInt(25);
            this.quantity[index] -= 1;

            if (sum() == 0){
                return null;
            }
            return this.tile[index];
        }

        public Tile getTile(char letter) {
            for (int i = 0; i < tile.length; i++) {
                if (tile[i].letter == letter) {
                    return tile[i];
                }
            }
            return null;
        }

        public Tile put(Tile tile) {
            if (sum() == 98)
                return tile;
            else {
                for (int i = 0; i < this.tile.length; i++) {
                    if(this.tile[i].equals(tile)) {
                        this.quantity[i] += 1;
                    }
                }
            }
            return tile;
        }

        public int size() {
            return sum();
        }

        public int[] getQuantities() {
            return this.quantity.clone();
        }

        // Private static bag to hold the single instance of the class
        private static Bag b;
        // sigleton
        public static Bag getBag() {
            if(b == null)
                b = new Bag();
            return b;
        }
    }
}
