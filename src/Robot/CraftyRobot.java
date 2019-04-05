package Robot;

import java.util.Scanner;

/**
 * 阴险老贼 永远欺骗
 */
public class CraftyRobot extends Robot {

    public CraftyRobot() {
        this.name = "CraftyRobot";
    }

    @Override
    public int calculate(int oppoNumber) {
        int result;    //规范用result
        result=0;
        return result;
    }

    @Override
    public void play(int number) {
        Scanner in = new Scanner(System.in);
        for(int i=0;i<number;i++){

            System.out.println("*****第"+(i+1)+"轮******");
            System.out.print("请输入你的选择，1代表合作，0代表欺骗:");
            int oppoNumber = in.nextInt();  //对手选择 非此机器人
            int result =calculate(oppoNumber);
            System.out.println("机器人的选择是"+result);
            calculateScore(result,oppoNumber);

        }
    }

}
