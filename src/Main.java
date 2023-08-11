import presenter.Presenter;

public class Main {
    public static void main(String[] args) throws Exception {
        Presenter ps = new Presenter();
        ps.addDemoData();
        ps.mainMenu();
    }
}