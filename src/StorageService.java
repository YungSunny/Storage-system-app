import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StorageService {

    public static List<StorageModel> storage = new ArrayList<StorageModel>();

    public static void getData(){
        BufferedReader bufferedReader = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            bufferedReader = new BufferedReader(new FileReader("resources/sample.csv"));
            String currentLine;
            boolean first = false;
            while ((currentLine = bufferedReader.readLine()) != null) {
                if (!first) {
                    first = true;
                } else {
                    String[] info = currentLine.split(",");
                    storage.add(new StorageModel(info[0], Long.parseLong(info[1]), Integer.parseInt(info[2]),
                            formatter.parse(info[3])));
                }
            }
        } catch (IOException | ParseException e){
            e.printStackTrace();
        } finally {
            try{
                bufferedReader.close();
                removeMatchingElements(storage);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void removeMatchingElements(List<StorageModel> storage){
        for(int i = 0; i < storage.size()-1; i++) {
            for (int j = i + 1; j < storage.size(); j++) {
                if (storage.get(i).getItemName().equals(storage.get(j).getItemName())
                        && storage.get(i).getCode() == storage.get(j).getCode()
                        && storage.get(i).getExpirationDate().equals(storage.get(j).getExpirationDate()))
                {
                    storage.get(j).setQuantity(storage.get(i).getQuantity()+storage.get(j).getQuantity());
                    storage.remove(i);
                }
            }
        }
    }

    public static List<StorageModel> getItemsByQuantity(int quantity){
        List<StorageModel> items = new ArrayList<>();
        for(StorageModel model: storage){
            if(model.getQuantity() < quantity)
                items.add(model);
        }
        return items;
    }

    public static List<StorageModel> getItemsByDate(Date date) {
        List<StorageModel> items = new ArrayList<>();
        for (StorageModel model: storage) {
            if (model.getExpirationDate().before(date))
                items.add(model);
        }
        return items;
    }
}

