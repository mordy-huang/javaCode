package comparator;

import com.hdljava.skycat.pojo.Product;

import java.util.Comparator;

public class ProducPriceComparator implements Comparator<Product> {
    /**
     * 比较两个产品价格
     * @param p1
     * @param p2
     * @return
     */
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getPromotePrice().compareTo( p1.getPromotePrice() );
    }
}
