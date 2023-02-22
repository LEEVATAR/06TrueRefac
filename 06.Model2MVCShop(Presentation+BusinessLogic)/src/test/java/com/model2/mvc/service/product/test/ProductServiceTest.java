package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


/*
 *	FileName :  UserServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {	"classpath:config/context-common.xml",
									"classpath:config/context-aspect.xml",
									"classpath:config/context-mybatis.xml",
									"classpath:config/context-transaction.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	//clear
	public void testAddProduct() throws Exception {
		
		Product product = new Product();
		product.setProdName("05testProdName");
		product.setProdDetail("testProdDetail");
		product.setManuDate("20000102");
		product.setPrice(111111);
		product.setFileName("blahblah.jpg");		
		
		productService.addProduct(product);		
//		product = productService.getProduct(10076);

		//==> console Ȯ��
		System.out.println(product+":: let me show u product");
//		System.out.println(product+":: let me show u product");
		System.out.println(product.getProdName()+" �˾ƾ߰ھ�");
		//==> API Ȯ��
		Assert.assertEquals("05testProdName", product.getProdName());
		Assert.assertEquals("testProdDetail", product.getProdDetail());
		Assert.assertEquals("20000102", product.getManuDate());
		Assert.assertEquals(111111, product.getPrice());
		Assert.assertEquals("blahblah.jpg", product.getFileName());
	}
	
	//@Test
	//clear
	public void testGetProduct() throws Exception {
		
		Product product = new Product();
		//==> �ʿ��ϴٸ�...
//		product.setProdName("testProdName");
//		product.setProdDetail("testProdDetail");
//		product.setManuDate("test date202-020");
//		product.setPrice("testPrice 102039w");
//		product.setFileName("blahblah.jpg");
		
		product = productService.getProduct(10030);

		//==> console Ȯ��
		System.out.println(product);
		System.out.println("�˾ƾ߰ھ� "+product.getFileName());
		
		//==> API Ȯ��
//		Assert.assertEquals(12345, product.getProdNo());
//		Assert.assertEquals("testProdName", product.getProdName());
//		Assert.assertEquals("testProdDetail", product.getProdDetail());
//		Assert.assertEquals("20000102", product.getManuDate());
//		Assert.assertEquals(111111, product.getPrice());
//		Assert.assertEquals("blahblah.jpg", product.getFileName());
		
		Assert.assertNotNull(productService.getProduct(10000));
		Assert.assertNotNull(productService.getProduct(10001));
	}
	
	//@Test
	 public void testUpdateProduct() throws Exception{
		 
		Product product = productService.getProduct(10006);
		Assert.assertNotNull(product);
		
//		Assert.assertEquals("testProdName", product.getProdName());
//		Assert.assertEquals("testProdDetail", product.getProdDetail());
//		Assert.assertEquals("20000102", product.getManuDate());
//		Assert.assertEquals(111111, product.getPrice());
//		Assert.assertEquals("blahblah.jpg", product.getFileName());

		product.setProdName("changeTestName");
		product.setProdDetail("CH.ProdDetail");
		product.setManuDate("20020403");
		product.setPrice(222222);
		product.setFileName("CHblahblah.jpg");
		
		productService.updateProduct(product);
		
		product = productService.getProduct(10006);
		Assert.assertNotNull(product);
		
		//==> console Ȯ��
		//System.out.println(user);
		//==> API Ȯ��
		Assert.assertEquals("changeTestName", product.getProdName());
		Assert.assertEquals("CH.ProdDetail", product.getProdDetail());
		Assert.assertEquals("20020403", product.getManuDate());
		Assert.assertEquals(222222, product.getPrice());
		Assert.assertEquals("CHblahblah.jpg", product.getFileName());
	 }
	
	 //==>  �ּ��� Ǯ�� �����ϸ�....
	//@Test
	//clear!
	 public void testGetProductListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list+"im UPPER list in ListAll");
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("������");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
	 	//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
//	 @Test
	 //clear!
	 public void testGetProductListByProdNo() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("10005");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list+"im UPPER list in TGPLBPN");
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list+"im BELOW list in TGPLBPN");
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
//	 @Test
	 //clear!
	 public void testGetProductListByProdName() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("��");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list+"im UPPER list in TG @ ProdName");
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }	 
}