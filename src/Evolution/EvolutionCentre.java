package Evolution;

public class EvolutionCentre {
    public Pop[] select(Pop[] popList) {
        int length = popList.length;
        Pop[] selectedPopList = new Pop[length];
        double[] score = new double[length];

        //把每个待进化机器人的得分存到一个score数组里
        for (int i = 0; i < length; i++) {
            score[i] = (double) popList[i].getScore();
        }

        int maxIndex = 0;
        double maxScore = score[0];
        for (int i = 1; i < length; i++) {
            if (score[i] > maxScore) {
                maxScore = score[i];
                maxIndex = i;
            }
        }
        //计算总分
        double sumScore = 0;
        for (int i = 0; i < length; i++) {
            sumScore += score[i];
        }

        //转换成百分比
        for (int i = 0; i < length; i++) {
            score[i] = score[i] / sumScore;
        }

        //score转成递增百分比,准备轮盘选择
        for (int i = 1; i < length; i++) {
            score[i] = score[i] + score[i - 1];
        }
        score[length - 1] = 1;

        //选择
        for (int i = 0; i < length - 1; i++) {
            double p = Math.random();
            for (int j = 0; j < length; j++) {
                if (score[j] > p) {
                    selectedPopList[i] = popList[j];
                    break;
                }
            }
        }

        selectedPopList[length-1] = popList[maxIndex];
        return selectedPopList;
    }

    public void crossover(Pop[] popList, double pCross) {
        int length = popList.length;
        int chromLength = popList[0].chrom.length;
        for (int i = 0; i < length; i++) {
            if (Math.random() < pCross) {

                //随机生成两个整数，代表要交叉的个体
                int point1 = (int) Math.floor(Math.random() * length);
                int point2 = (int) Math.floor(Math.random() * length);
                while (point1 == point2)
                    point2 = (int) Math.floor(Math.random() * length);

                //交叉位点，且保证crosspoint1<=crosspoint2
                int crossPoint1 = (int) Math.floor(Math.random() * chromLength);
                int crossPoint2 = (int) Math.floor(Math.random() * chromLength);
                if (crossPoint1 > crossPoint2) {
                    int temp = crossPoint1;
                    crossPoint1 = crossPoint2;
                    crossPoint2 = temp;
                }

                //开始交叉辣
                int[] chrom1 = new int[chromLength];
                int[] chrom2 = new int[chromLength];
                for (int j = 0; j < crossPoint1; j++) {
                    chrom1[j] = popList[point1].chrom[j];
                    chrom2[j] = popList[point2].chrom[j];
                }
                for (int j = crossPoint1; j < crossPoint2; j++) {
                    chrom1[j] = popList[point2].chrom[j];
                    chrom2[j] = popList[point1].chrom[j];
                }
                for (int j = crossPoint2; j < chromLength; j++) {
                    chrom1[j] = popList[point1].chrom[j];
                    chrom2[j] = popList[point2].chrom[j];
                }
                popList[point1].chrom = chrom1;
                popList[point2].chrom = chrom2;

            }
        }
    }

    public void mutation(Pop[] popList, double p) {
        int chromLength = popList[0].chrom.length;

        //遍历所有Pop，按突变概率来突变
        for (Pop temp : popList) {
            if (Math.random() < p) {
                int mPoint = (int) Math.floor(Math.random() * chromLength);
                if (temp.chrom[mPoint] == 0)
                    temp.chrom[mPoint] = 1;
                else
                    temp.chrom[mPoint] = 0;
            }
        }
    }
}
