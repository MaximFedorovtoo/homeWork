package Home.ui.workWithData;

import Home.presenter.Presenter;

import java.util.Scanner;

public class Console implements View{
    private static final String INPUT_ERROR = "Вы ввели неверное значение";
    private Presenter presenter;
    private Scanner scanner;
    private boolean work;
    private MainMenu mainMenu;

    public Console() {
        scanner = new Scanner(System.in);
        work = true;
        mainMenu = new MainMenu(this);
    }

    @Override
    public void print(String text) {
        System.out.println(text);
    }

    @Override
    public void finish(){
        System.out.println("До скорых встреч");
        work = false;
    }

    @Override
    public void getHumanInfo(){
        presenter.getHumanInfo();
    }

    @Override
    public void addHuman(){
        System.out.println("Введите имя человека");
        String firstName = scanner.nextLine();
        System.out.println("Введите возвраст человека");
        int age = scanner.nextInt();
        presenter.addHuman(firstName, age);
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void start() {
        hello();
        while (work){
            printMenu();
            execute();
        }
    }

    private void printMenu(){
        System.out.println(mainMenu.print());
    }

    private void hello(){
        System.out.println("Доброго времени суток!");
    }

    private void execute(){
        String line = scanner.nextLine();
        if (checkTextForInt(line)){
            int numCommand = Integer.parseInt(line);
            if (checkCommand(numCommand)){
                mainMenu.execute(numCommand);
            }
        }
    }

    private boolean checkTextForInt(String text){
        if (text.matches("[0-9]+")){
            return true;
        } else {
            inputError();
            return false;
        }
    }

    private boolean checkCommand(int numCommand){
        if (numCommand < mainMenu.size()){
            return true;
        } else {
            inputError();
            return false;
        }
    }

    private void inputError(){
        System.out.println(INPUT_ERROR);
    }
}
