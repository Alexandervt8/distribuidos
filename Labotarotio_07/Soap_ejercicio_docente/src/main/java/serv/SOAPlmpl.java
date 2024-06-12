package serv;

import javax.jws.WebService;
import java.util.List;
import serv.Product;

@WebService(endpointInterface = "serv.SOAPl")
public class SOAPlmpl implements SOAPl{
	@Override
	 public List<Product> getProducts() {
		 return Product.getProducts();
	 }
	 @Override
	 public void addProduct(Product product) {
		 Product.getProducts().add(product);
	 }
}
