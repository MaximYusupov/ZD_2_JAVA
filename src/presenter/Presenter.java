package presenter;

import model.Toy;
import view.View;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Presenter {
    public Presenter(){
        view = new View();
    }

    public void addDemoData() throws Exception {
        this.toyMap.put("id1", new Toy("ID1", "Toy1", 0.1, 100.0, 10));
        this.toyMap.put("id2", new Toy("ID2", "Toy2", 0.2, 100.0, 10));
        this.toyMap.put("id3", new Toy("ID3", "Toy3", 0.3, 100.0, 10));
        this.toyMap.put("id4", new Toy("ID4", "Toy4", 0.4, 100.0, 10));
        this.toyMap.put("id5", new Toy("ID5", "Toy5", 0.5, 100.0, 10));
        this.toyMap.put("id6", new Toy("ID6", "Toy6", 0.6, 100.0, 10));
        this.toyMap.put("id7", new Toy("ID7", "Toy7", 0.7, 100.0, 10));
        this.toyMap.put("id8", new Toy("ID8", "Toy8", 0.8, 100.0, 10));
        this.toyMap.put("id9", new Toy("ID9", "Toy9", 0.9, 100.0, 10));
        this.toyMap.put("id10", new Toy("ID10", "Toy10", 0.69, 100.0, 10));
    }
    private Map<String, Toy> toyMap = new HashMap<>();
    private PriorityQueue<Toy> toyQueue = new PriorityQueue<>();

    public Toy raffle() throws Exception {
        if (!toyMap.isEmpty()) {
            String key = Collections.max(toyMap.entrySet(), Map.Entry.comparingByValue()).getKey();
            Toy prize = toyMap.get(key).takeOne();
            toyQueue.add(prize);
            // Обновление рангов для следующего розыгрыша
            for (String vendorID : toyMap.keySet()) {
                toyMap.get(vendorID).refreshLotteryRank();
            }
            // Если игрушка кончилась, удаляем ее из базы
            toyMap.entrySet().removeIf(e -> e.getValue().getQuantity() == 0);
            return prize;
        } else return null;
    }

    public boolean getPrize() throws FileNotFoundException, UnsupportedEncodingException {
        if (toyQueue.size() > 0) {
            //return toyQueue.remove();
            Date d = new Date();
            DateFormat df = new SimpleDateFormat("HH_mm_ss-dd.MM.yyyy");
            System.out.println(d);
            PrintWriter writer = new PrintWriter(df.format(d) + ".txt", "UTF-8");
            writer.println(toyQueue.remove().toString());
            writer.close();
            return true;
        } else
            return false;
    }

    public void mainMenu() throws Exception {
        int cmd = 0;
        do {
            view.mainMenu();
            cmd = view.getIntValue("команду");
            selectCmd(cmd);
        } while (cmd != 0);
    }

    private boolean addToy(Toy toy) {
        if (toyMap.containsKey(toy.getVendorID().toLowerCase()))
            return false;
        else {
            toyMap.put(toy.getVendorID().toLowerCase(), toy);
            return true;
        }
    }

    private boolean removeToy(String vendorID) {
        vendorID = vendorID.toLowerCase();
        if (toyMap.containsKey(vendorID)) {
            toyMap.remove(vendorID);
            return true;
        } else
            return false;
    }

    private boolean changeWeight(String vendorID, Double weight) throws Exception {
        vendorID = vendorID.toLowerCase();
        if (toyMap.containsKey(vendorID) && (weight >= 0.0 && weight <= 1.0)) {
            toyMap.get(vendorID).setLotteryWeight(weight);
            return true;
        } else return false;
    }

    private void selectCmd(int command) throws Exception {
        switch (command) {
            case 1:
                boolean isFirst = true;
                StringBuilder out = new StringBuilder();
                for (String key : toyMap.keySet()) {
                    if (isFirst) isFirst = false;
                    else out.append("\n");
                    out.append(toyMap.get(key));
                }
                view.showAllData(out.toString());
                break;
            case 2:
                view.addToy(this.addToy(new Toy(view.getStrValue("артикул")
                        , view.getStrValue("имя")
                        , view.getDblValue("вес")
                        , view.getDblValue("цену")
                        , view.getIntValue("количество"))));
                break;
            case 3:
                view.removeToy(this.removeToy(view.getStrValue("артикул")));
                break;
            case 4:
                view.changeWeight(this.changeWeight(view.getStrValue("артикул"), view.getDblValue("вес")));
                break;
            case 5:
                view.toRaffle(this.raffle());
                break;
            case 6:
                view.takePrize(this.getPrize());
                break;


        }
    }

    private View view;

}
