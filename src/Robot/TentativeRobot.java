package Robot;

import java.util.Scanner;

/**
 * 先一直合作 直到被对手欺骗一次后 就一直欺骗
 */
public class TentativeRobot extends Robot{
    boolean whetherBeCheated = false;

    public TentativeRobot(){
        this.name="TentativeRobot";
    }

    @Override
    public int calculate(int oppoNumber) {
        int result;
        if(whetherBeCheated){
            result =0;
        }else{
            result =1;
        }
        if(oppoNumber==0){whetherBeCheated=true;}    //先让他玩完这次 然后再判断是否被骗

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
