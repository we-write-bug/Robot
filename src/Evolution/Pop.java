package Evolution;

public class Pop {
    int[] chrom;
    private double percent = 0;
    private int score = 0;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
    public void addScore(int score){
        this.score += score;
    }

    public Pop(int chromLength) {
        chrom = new int[chromLength];
        for (int i = 0; i < chromLength; i++) {
            chrom[i] = (int) Math.round(Math.random());
        }
    }

    @Override
    public Object clone() {
        Pop pop = new Pop(243);
        pop.chrom = chrom.clone();
        return pop;
    }
}
