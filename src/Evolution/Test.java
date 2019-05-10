package Evolution;

import Robot.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        go();
    }

    public static void printPreInfo() {
        /**
         * 打印准备信息
         */
        System.out.println("*********************Game:囚徒困境**********************");
        System.out.println("-------------------------------------------------------");
        System.out.println("|  游戏规则:       合作     欺骗                        |");
        System.out.println("|             合作 +2\\+2   +0\\+3                       |");
        System.out.println("|             欺骗 +3\\+0   +1\\+1                       |");
        System.out.println("-------------------------------------------------------");
        System.out.println("|  请选择机器人:                                        |");
        System.out.println("|  [1]CheatAndRepeatRobot                              |");
        System.out.println("|  [2]CraftyRobot                                      |");
        System.out.println("|  [3]HigherRepeatRobot                                |");
        System.out.println("|  [4]KingRobot                                        |");
        System.out.println("|  [5]RegularFirstRobot                                |");
        System.out.println("|  [6]RepeatRobot                                      |");
        System.out.println("|  [7]StubbornRobot                                    |");
        System.out.println("|  [8]TentativeRobot                                   |");
        System.out.println("|  [0]结束选择                                          |");
        System.out.println("--------------------------------------------------------");
    }

    public static ArrayList<Integer> dealPre() {
        /**
         * 处理准备信息，以Arraylist形式返回
         * Arraylist中，奇数位为机器人编号，偶数位为机器人个数
         */
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> result = new ArrayList<>();
        while (true) {
            System.out.print(" 您选择的选择机器人编号：");
            int num = in.nextInt();
            if (num == 0) {
                System.out.print(" 您是否要结束选择？[Y]确认结束 [N]取消:");
                if ("Y".equals(in.next())) {
                    System.out.println(" 结束选择");
                    System.out.println("--------------------------------------------------------");
                    break;
                } else {
                    continue;
                }
            } else {
                result.add(num);
                System.out.print(" 您选择[" + num + "]号机器人的个数为：");
                result.add(in.nextInt());
            }
        }
        return result;
    }

    public static Robot[] getRobot(ArrayList<Integer> data) {
        int allsize = 0;
        for (int i = 1; i < data.size(); i += 2) {
            allsize += data.get(i);
        }
        Robot[] robots = new Robot[allsize];
        int count = 0;
        for (int i = 0; i < data.size(); i += 2) {
            switch (data.get(i)) {
                case 1:
                    for (int j = 0; j < data.get(i + 1); j++) {
                        robots[count] = new CheatAndRepeatRobot();
                        count++;
                    }
                    break;
                case 2:
                    for (int j = 0; j < data.get(i + 1); j++) {
                        robots[count] = new CraftyRobot();
                        count++;
                    }
                    break;
                case 3:
                    for (int j = 0; j < data.get(i + 1); j++) {
                        robots[count] = new HigherRepeatRobot();
                        count++;
                    }
                    break;
                case 4:
                    for (int j = 0; j < data.get(i + 1); j++) {
                        robots[count] = new KindRobot();
                        count++;
                    }
                    break;
                case 5:
                    for (int j = 0; j < data.get(i + 1); j++) {
                        robots[count] = new RegularFirstRobot();
                        count++;
                    }
                    break;
                case 6:
                    for (int j = 0; j < data.get(i + 1); j++) {
                        robots[count] = new RepeatRobot();
                        count++;
                    }
                    break;
                case 7:
                    for (int j = 0; j < data.get(i + 1); j++) {
                        robots[count] = new StubbornRobot();
                        count++;
                    }
                    break;
                case 8:
                    for (int j = 0; j < data.get(i + 1); j++) {
                        robots[count] = new TentativeRobot();
                        count++;
                    }
                    break;
            }
        }
        return robots;
    }

    public static void go() {
        printPreInfo();
        ArrayList<Integer> data = dealPre();

        Robot[] robots;
        Pop[] popList = new Pop[20];
        PlayCentre playCentre = new PlayCentre();
        EvolutionCentre evolutionCentre = new EvolutionCentre();

        for (int i = 0; i < 20; i++) {
            popList[i] = new Pop(243);
        }

        ArrayList<Integer> scoreLi = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            for (Pop temp : popList) {
                robots = getRobot(data);
//                Robot robot1 = new RepeatRobot();
//                Robot robot2 = new KindRobot();
//                Robot robot3 = new StubbornRobot();
//                Robot robot4 = new TentativeRobot();
//                Robot robot5 = new CraftyRobot();
//                Robot robot6 = new HigherRepeatRobot();
                temp.setScore(0);
                for (int j = 0; j < robots.length; j++) {
                    playCentre.playTest(temp, robots[j]);
                }
//                playCentre.playTest(temp, robot1);
//                playCentre.playTest(temp, robot2);
//                playCentre.playTest(temp, robot3);
//                playCentre.playTest(temp, robot4);
//                playCentre.playTest(temp, robot5);
//                playCentre.playTest(temp, robot6);
                scoreLi.add(temp.getScore());
            }
            System.out.println("第" + i + "轮最高分为" + scoreLi.stream().max((a, b) -> a - b));
            scoreLi.clear();
            popList = evolutionCentre.select(popList);
            Pop best = (Pop) popList[popList.length - 1].clone();
            evolutionCentre.crossover(popList, 0.6);
            evolutionCentre.mutation(popList, 0.1);
            popList[popList.length - 1] = best;
        }
    }

}
