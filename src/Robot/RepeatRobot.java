package Robot;

import java.util.Scanner;

/**
 * 复读机
 */
public class RepeatRobot extends Robot {
    boolean isFirstRound = true;
    int rememberLast;

    public RepeatRobot(){
        this.name = "RepeatedRobot";
    }

    public int calculate(int oppoNumber){   //与父类保持一致 用oppoNumber
        int result;
        if (isFirstRound){
            this.rememberLast = oppoNumber;  //第一局就跟对手一样
            this.isFirstRound = false;
            result = 1;
        }else{
            result = rememberLast;     //以后跟对手上一局一样
            this.rememberLast = oppoNumber;
        }
        return result;
    }

    public void play(int number){  //与父类保持一致 用number
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

//    public static void main(String[] args) {
//        StubbornRobot rr = new StubbornRobot();
//        rr.play(10);     //共玩10轮
//        rr.printScore();
//    }
}
