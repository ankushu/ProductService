package com.vodqa.productservice;

import au.com.dius.pact.provider.junit.Consumer;
import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.target.MockMvcTarget;
import com.vodqa.productservice.web.controllers.ProductController;
import com.vodqa.productservice.web.models.Product;
import com.vodqa.productservice.web.services.ProductService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(PactRunner.class)
@Provider("product-service")
@Consumer("category-service")
@PactFolder(value = "./src/test/contract/provider/resources/pacts")
public class CategoryServiceContractTest {

    @TestTarget
    public MockMvcTarget target = new MockMvcTarget();

    @Mock
    private ProductService productService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        ProductController productController = new ProductController(productService);
        target.setControllers(productController);
    }

    @State("HasProductDetails")
    public void shouldReturnProductDetails(){
        when(productService.getProductById("prod1234")).thenReturn(new Product("prod1234", "Shoe", 345));
        System.out.println(productService.getProductById("prod1234"));
    }

    @State("add your state name")
    public void shouldReturnListOfProductDetails(){
        //add your code here
    }
}
