package comparator;

import com.hdljava.skycat.pojo.Product;

import java.util.Comparator;

public class ProductDataComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getCreateDate().compareTo( p1.getCreateDate() );
    }
}
