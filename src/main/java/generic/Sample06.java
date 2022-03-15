package generic;

class Student<G extends Integer, C extends Integer> {
    G g;
    C c;

    public Student(G g, C c) {
        this.g = g;
        this.c = c;
    }

    public void printInfo(String name) {
        System.out.printf("%s(은)는 %d학년 %d반입니다.\n", name, g, c);
    }
}

public class Sample06 {
    public static void main(String[] args) {
        Student<Integer, Integer> younghee = new Student<>(1, 4);
        younghee.printInfo("영희");
    }
}
