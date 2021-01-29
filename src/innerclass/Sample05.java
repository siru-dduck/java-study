package innerclass;

class Student2 {
    private String name;
    private Score score;

    public Student2(String name) {
        this.name = name;
    }

    public void setScore(Score score){
        this.score = score;
    }

    public void showInfo() {
        System.out.println("이름: " + name);
        System.out.println(score);
    }

    public static class Score {
        private int eng;
        private int mat;

        public void setEng(int eng) {
            this.eng = eng;
        }

        public void setMat(int mat) {
            this.mat = mat;
        }

        public void showInfo() {
            System.out.println(this);
        }

        @Override
        public String toString() {
            return "Score{" +
                    "eng=" + eng +
                    ", mat=" + mat +
                    '}';
        }
    }
}

public class Sample05 {

    public static void main(String[] args) {
        Student2 student = new Student2("시루떡");
        Student2.Score score = new Student2.Score();
        score.setMat(100);
        score.setEng(60);
        student.setScore(score);
        student.showInfo();
    }

}
