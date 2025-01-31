import java.util.*;

// 1
class ATM {
    private final Map<Integer, Integer> banknotes = new HashMap<>();
    private final int minWithdrawal = 10;
    private final int maxBanknotes = 40;

    public ATM() {
        int[] denominations = {1, 2, 5, 10, 20, 50, 100, 200, 500};
        for (int d : denominations) banknotes.put(d, 0);
    }

    public void loadCash(Map<Integer, Integer> cash) {
        for (var entry : cash.entrySet()) {
            banknotes.put(entry.getKey(), banknotes.get(entry.getKey()) + entry.getValue());
        }
    }

    public int getTotalCash() {
        return banknotes.entrySet().stream().mapToInt(e -> e.getKey() * e.getValue()).sum();
    }
}

class Bank {
    private final List<ATM> atms = new ArrayList<>();

    public void addATM(ATM atm) {
        atms.add(atm);
    }

    public int getTotalBankCash() {
        return atms.stream().mapToInt(ATM::getTotalCash).sum();
    }
}

// 2
interface ICipher {
    String encode(String text);
    String decode(String text);
}

class ACipher implements ICipher {
    public String encode(String text) {
        return shift(text, 1);
    }
    public String decode(String text) {
        return shift(text, -1);
    }
    private String shift(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            result.append((char) (c + shift));
        }
        return result.toString();
    }
}

class BCipher implements ICipher {
    public String encode(String text) {
        return reverseAlphabet(text);
    }
    public String decode(String text) {
        return reverseAlphabet(text);
    }
    private String reverseAlphabet(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            result.append((char) ('я' - (c - 'а')));
        }
        return result.toString();
    }
}

// 3
interface IInfo {
    void sound();
    void show();
    void desc();
    void history();
}

abstract class MusicalInstrument implements IInfo {
    protected String name;
    protected String description;
    protected String history;
    protected String sound;
}

class Violin extends MusicalInstrument {
    public Violin() {
        name = "Скрипка";
        sound = "Скрипичный звук";
        description = "Струнный инструмент";
        history = "Происходит из эпохи какой-то";
    }
    public void sound() { System.out.println(sound); }
    public void show() { System.out.println(name); }
    public void desc() { System.out.println(description); }
    public void history() { System.out.println(history); }
}

// 4
interface IPart {}
interface IWorker {
    void work(House house);
}

class House {
    boolean basement, roof;
    int walls = 0, windows = 0, doors = 0;
}

class Worker implements IWorker {
    public void work(House house) {
        if (!house.basement) house.basement = true;
        else if (house.walls < 4) house.walls++;
        else if (house.windows < 4) house.windows++;
        else if (house.doors < 1) house.doors++;
        else if (!house.roof) house.roof = true;
    }
}

class TeamLeader implements IWorker {
    public void work(House house) {
        System.out.println("Отчет: Фундамент - " + house.basement + ", Стены - " + house.walls + ", Окна - " + house.windows + ", Двери - " + house.doors + ", Крыша - " + house.roof);
    }
}

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        ATM atm1 = new ATM();
        atm1.loadCash(Map.of(50, 10, 100, 5));
        bank.addATM(atm1);
        System.out.println("Общая сумма в банке: " + bank.getTotalBankCash());

        ACipher aCipher = new ACipher();
        System.out.println("Шифр A: " + aCipher.encode("Привет"));

        Violin violin = new Violin();
        violin.show(); violin.sound(); violin.desc(); violin.history();

        House house = new House();
        Worker worker = new Worker();
        TeamLeader leader = new TeamLeader();
        for (int i = 0; i < 10; i++) worker.work(house);
        leader.work(house);
    }
}
