
package serv;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface SOAPl {

	@WebMethod
	public List<Product> getProducts();
	
	@WebMethod
	public void addProduct(Product product);
	
}