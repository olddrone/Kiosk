package Console;

public interface Factory <T extends Manageable>{
    T create();
}
