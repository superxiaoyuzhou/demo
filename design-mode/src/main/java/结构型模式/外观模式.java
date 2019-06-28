package 结构型模式;

/**
 * 外观模式是为了解决类与类之家的依赖关系的，像 spring 一样，可以将类和类之间的关系配置到配置
 * 文件中，而外观模式就是将他们的关系放在一个 Facade 类中，降低了类类之间的耦合度
 * 通过为多个复杂的子系统提供一个一致的接口，而使这些子系统更加容易被访问的模式。该模式对外有一个统一接口，
 * 外部应用程序不用关心内部子系统的具体的细节，这样会大大降低应用程序的复杂度，提高了程序的可维护性。
 *
 * 外观（Facade）模式是“迪米特法则”的典型应用，它有以下主要优点。
 * 降低了子系统与客户端之间的耦合度，使得子系统的变化不会影响调用它的客户类。
 * 对客户屏蔽了子系统组件，减少了客户处理的对象数目，并使得子系统使用起来更加容易。
 * 降低了大型软件系统中的编译依赖性，简化了系统在不同平台之间的移植过程，因为编译一个子系统不会影响其他的子系统，
 * 也不会影响外观对象。
 */
public class 外观模式 {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.startup();
        computer.shutdown();
    }
}

class CPU {
    public void startup() {
        System.out.println("cup startup");
    }

    public void shutdown() {
        System.out.println("CUP shutdown");
    }
}
class Memory {
    public void startup() {
        System.out.println("Memory startup");
    }

    public void shutdown() {
        System.out.println("Memory shutdown");
    }
}
class Disk {
    public void startup() {
        System.out.println("Disk startup");
    }

    public void shutdown() {
        System.out.println("Disk shutdown");
    }
}
class Computer {
    private CPU cpu;
    private Memory memory;
    private Disk disk;

    Computer(){
        this.cpu = new CPU();
        this.memory = new Memory();
        this.disk = new Disk();
    }

    public void startup(){
        System.out.println("start the computer");
        cpu.startup();
        memory.startup();
        disk.startup();
        System.out.println("start computer finished!");
    }

    public void shutdown(){
        System.out.println("begin to close the computer!");
        cpu.shutdown();
        memory.shutdown();
        disk.shutdown();
        System.out.println("computer closed!");
    }

}