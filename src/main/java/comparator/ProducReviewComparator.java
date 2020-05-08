package comparator;

import com.hdljava.skycat.pojo.Product;

import java.util.Comparator;

public class ProducReviewComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getReviewCount().compareTo( p1.getReviewCount() );
    }
}
