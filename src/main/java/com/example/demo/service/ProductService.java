package com.example.demo.service;


import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.UnprocessableEntityException;
import com.example.demo.model.MockProductDAO;
import com.example.demo.model.Product;
import com.example.demo.parameter.ProductQueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private MockProductDAO productDAO;

    public Product createProduct(Product request) {
        boolean isIdDuplicated = productDAO.find(request.getId()).isPresent();
        if (isIdDuplicated) {
            throw new UnprocessableEntityException("The id of the product is duplicated.");
        }

        Product product = new Product();
        product.setId(request.getId());
        product.setName(request.getName());
        product.setPrice(request.getPrice());

        return productDAO.insert(product);
    }

    public Product getProduct(String id) {
        return productDAO.find(id)
                .orElseThrow(() -> new NotFoundException("Can't find product."));
    }

    public Product replaceProduct(String id, Product request) {
        Product product = getProduct(id);
        return productDAO.replace(product.getId(), request);
    }

    public void deleteProduct(String id) {
        Product product = getProduct(id);
        productDAO.delete(product.getId());
    }

    public List<Product> getProducts(ProductQueryParameter param) {
        return productDAO.find(param);
    }

}
