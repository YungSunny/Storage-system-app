import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class UI {
    public static void showMainMenu(){
        Scanner scanner = new Scanner(System.in);
        StorageService.getData();
        while(true){
            System.out.println("--------------------------------");
            System.out.println("Main menu");
            System.out.println("(1) Check items quantity");
            System.out.println("(2) Check items expiration date");
            System.out.println("(3) Exit");
            System.out.println("--------------------------------");

            switch (scanner.nextInt()){
                case 1:
                    System.out.println("Enter the quantity to see items low in stock");
                    int quantity = scanner.nextInt();
                    checkQuantity(quantity);
                    break;
                case 2:
                    System.out.println("Enter the date to see items expiration date (Format is yyyy-MM-dd)");
                    String dateStr = scanner.next();
                    Date date = convertToDateFormat(dateStr);
                    checkExpirationDate(date);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error ! Enter number between 1-3 !");
            }
        }
    }

    public static void checkQuantity(int quantity){
        List<StorageModel> sortedList =  StorageService.getItemsByQuantity(quantity).stream()
                .sorted(Comparator.comparing(StorageModel::getItemName))
                .collect(Collectors.toList());
        sortedList.forEach(System.out::println);
    }

    public static Date convertToDateFormat(String dateStr){
        Date date = null;
        try{
            date = StorageModel.formatter.parse(dateStr);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return date;
    }


    public static void checkExpirationDate(Date date){
        List<StorageModel> sortedList =  StorageService.getItemsByDate(date).stream()
                .sorted(Comparator.comparing(StorageModel::getItemName))
                .collect(Collectors.toList());
        sortedList.forEach(System.out::println);
    }
}
