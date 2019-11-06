import java.text.SimpleDateFormat;
import java.util.Date;

public class StorageModel {
    private String itemName;
    private long code;
    private int quantity;
    private Date expirationDate;

    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public StorageModel(String itemName, long code, int quantity, Date expirationDate) {
        this.itemName = itemName;
        this.code = code;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Item Name: " + itemName +
                ", Code: " + code +
                ", Quantity: " + quantity +
                ", Expiration Date: " + formatter.format(expirationDate);
    }
}
