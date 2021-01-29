package innerclass;

class Student1 {
    private String name;
    private Score score;
    public Student1(String name) {
        this.name = name;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public void showInfo(){
        System.out.println("이름: " +this.name);
        System.out.println(score);
    }

    public class Score {
        private int eng;
        private int mat;

        public void setEng(int eng) {
            this.eng = eng;
        }

        public void setMat(int mat) {
            this.mat = mat;
        }

        public void showInfo(){
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

public class Sample01 {
    public static void main(String[] args) {
        Student1 student = new Student1("시루떡");
        Student1.Score score = student.new Score();
        score.setEng(90);
        score.setMat(100);
        student.setScore(score);
        student.showInfo();
    }
}
