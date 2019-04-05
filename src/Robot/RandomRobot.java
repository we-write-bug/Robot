package Robot;

import java.util.Scanner;

/**
 * 佛系出牌 random就完了
 */
public class RandomRobot extends Robot {
    double probability;

    public RandomRobot(){
        this.name="RandomRobot";
    }

    @Override
    public int calculate(int oppoNumber) {
        int result;
        probability=Math.random();
        if(probability>0.5){   //随机数大于0.5 合作； 小于0.5 欺骗
            result=1;
        }else{
            result=0;
        }
        return result;
    }


    @Override
    public void play(int number) {
        Scanner in = new Scanner(System.in);
        for(int i=0;i<number;i++){    //i是一共玩几轮

            System.out.println("*****第"+(i+1)+"轮******");
            System.out.print("请输入你的选择，1代表合作，0代表欺骗:");
            int oppoNumber = in.nextInt();  //对手选择 非此机器人
            int result =calculate(oppoNumber);
            System.out.println("机器人的选择是"+result);
            calculateScore(result,oppoNumber);

        }
    }


}
