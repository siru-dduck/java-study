package generic;

// 컴퓨터 내장 부품 대상 객체
class ComputerPart { }

// 그래픽 카드
class GraphicCard extends ComputerPart { }

// 메모리
class Memory extends ComputerPart { }

// 모니터 (컴퓨터의 내장 부품은 아님)
class Monitor { }

class Computer<T extends ComputerPart> {
    private T component;

    public void toInfo() {
        System.out.println(component.getClass().getName());
    }

    public Computer(T component) {
        this.component = component;
    }

    public Computer() { }

    public void setComponent(T component) {
        this.component = component;
    }
}

public class Sample04 {
    public static void main(String[] args) {
        Computer<ComputerPart> vga = new Computer<>();
        vga.setComponent(new GraphicCard());
//        vga.setComponent(new Monitor()); // ❗ 컴파일에러 -> 타입호환에러

        Computer<ComputerPart> mem = new Computer<>(new Memory());
//        Computer<ComputerPart> mo = new Computer<>(new Monitor()); // ❗ 컴파일에러 -> 타입추론에러
        vga.toInfo();
        mem.toInfo();
    }
}
