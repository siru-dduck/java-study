package generic;

class NewComputer {
    private ComputerPart computerPart;

    public <T extends ComputerPart> NewComputer(T computerPart){
        this.computerPart = computerPart;
    }

    public void toInfo() {
        System.out.println(computerPart.getClass().getName());
    }
}

public class Sample05 {
    public static void main(String[] args) {
        NewComputer mem = new NewComputer(new Memory());
//        NewComputer momitor = new NewComputer(new Monitor()); // ❗ 컴파일에러 -> 타입호환예외
        mem.toInfo();
    }
}
