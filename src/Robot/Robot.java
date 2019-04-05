package Robot;

abstract class Robot {
    /*
     * 游戏规则：
     *
     * 1均代表合作，0均代表欺骗
     *
     *            合作      欺骗
     *    合作   +3\+3      +4/+1
     *    欺骗   +1\+4      +2/+2
     * 若都合作，执行cooperate方法，分数+3分
     * 若被欺骗，执行beCheated方法，分数+1分
     * 若欺骗成功，执行cheat方法，分数++4分
     * 若都欺骗·，执行notCooperate方法，分数+2分*/
    public int score = 0;
    public String name ="Robot";
    /**
     * 继承出来的子类写一个构造函数，把name改掉
     */
    public void cooperate(){
        score+=3;
    }
    public void cheat(){
        score+=4;
    }
    public void beCheated(){
        score+=1;
    }
    public void notCooperate(){
        score+=2;
    }

    public void calculateScore(int myChoose,int oppoChoose){
        /**
         * 该方法用于计算成绩，传入第一个参数为本人该轮选择，第二个参数为对手选择
         * 1均代表合作，0均代表欺骗
         */
        if (myChoose==1&&oppoChoose==1){
            this.cooperate();
        }else if(myChoose==1&&oppoChoose==0){
            this.beCheated();
        }else if (myChoose==0&&oppoChoose==1){
            this.cheat();
        }else if(myChoose==0&&oppoChoose==0){
            this.notCooperate();
        }
    }
    public void printScore(){
        /**
         * 打印成绩方法
         */
        System.out.println("My name is "+name+".");
        System.out.println("My score is "+score+".");
    }
    abstract public int calculate(int oppoNumber);
    abstract public void play(int number);
}
