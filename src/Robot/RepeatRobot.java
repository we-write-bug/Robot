package Robot;

import java.util.Scanner;

public class RepeatRobot extends Robot {
    boolean isFirstRound = true;
    int rememberLast;

    public RepeatRobot(){
        this.name = "RepeatedRobot";
        this.score = 1;
    }
    public int calculate(int oppo){
        int result;
        if (isFirstRound){
            this.rememberLast = oppo;
            this.isFirstRound = false;
            result = 1;
        }else{
            result = rememberLast;
            this.rememberLast = oppo;
        }
        return result;
    }
    public void play(int num){
        Scanner in = new Scanner(System.in);
        for(int i=0;i<num;i++){
            System.out.println("*****第"+(i+1)+"轮******");
            System.out.print("请输入你的选择，1代表合作，2代表欺骗:");
            int oppo = in.nextInt();
            int result =calculate(oppo);
            System.out.println("机器人的选择是"+result);
            calculateScore(result,oppo);
        }
    }

//    public static void main(String[] args) {
//        RepeatRobot rr = new RepeatRobot();
//        rr.play(2);
//        rr.printScore();
//    }
}
