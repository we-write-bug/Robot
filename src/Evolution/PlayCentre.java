package Evolution;

import Robot.*;

public class PlayCentre {
    public void playTest(Pop myRobot, Robot oppoRobot) {
        int[] status = {2, 2, 2, 2, 2};
        int myScore = 0;

        for (int j = 0; j < 5; j++) {
            //我方选择
            int myStrategy = getStrategy(myRobot.chrom, status);

            //对方选择
            int oppo = oppoRobot.calculate(myStrategy);

            //更新状态
            int length = status.length;
            for (int i = 0; i < length - 1; i++) {
                status[i] = status[i + 1];
            }
            status[length - 1] = oppo;

            //计算得分
            if (myStrategy == 1 && oppo == 1) {
                myScore += 2;
            } else if (myStrategy == 1 && oppo == 0) {
                myScore += 0;
            } else if (myStrategy == 0 && oppo == 1) {
                myScore += 3;
            } else if (myStrategy == 0 && oppo == 0) {
                myScore += 1;
            }
        }
        myRobot.addScore(myScore);
    }

    public int getStrategy(int[] chrom, int[] status) {
        int index = 0;
        for (int i = 0; i < status.length; i++) {
            index += status[i] * (Math.pow(3, i));
        }
        return chrom[index];
    }

}
