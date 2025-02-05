package com.turkcell.mini_e_commere_cqrs_hw3.rules;


import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Cart;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.CartItem;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.CartRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.OrderRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.ProductRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.core.exception.type.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductBusinessRules {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    public void productIdMustExist(int productId) {
        productRepository.findById(productId).orElseThrow(() -> new BusinessException("Product not found"));
    }

    public void productNameMustExist(String name) {
        productRepository.findByName(name).ifPresent(product -> {
            throw new BusinessException("Product name already exists");
        });
    }

    public void productMustNotBeAssociatedWithAnyOrder(Integer id) {
        if (orderRepository.existsByProductId(id)) {
            throw new BusinessException("Product cannot be deleted because it is associated with orders");
        }
    }

    public void productMustBeInStock(Integer id, Integer quantity) {
        if (productRepository.findById(id).get().getStock() < quantity) {
            throw new BusinessException("Product is not in stock");
        }
    }

    public void productMustBeInStockInCart(Integer cartId, Integer productId, Integer quantity) {
        if (cartRepository.existsById(cartId)) {
            Cart cart = cartRepository.findById(cartId).get();

            Optional<CartItem> optionalCartItem = cart.getCartItems().stream()
                    .filter(cartItem -> cartItem.getProduct().equals(productId))
                    .findFirst();

            if (optionalCartItem.isPresent()) {
                CartItem cartItem = optionalCartItem.get();
                if (cartItem.getQuantity() + quantity > productRepository.findById(productId).get().getStock()) {
                    throw new BusinessException("Product is not in stock");
                }
            }
        }
    }

    public void productMustBeUnique(String name) {
        if (productRepository.existsByName(name)) {
            throw new BusinessException("Product name must be unique");
        }
    }

    public void productMustBeUniqueExceptForItself(String name, Integer id) {
        if (productRepository.existsByNameAndIdNot(name, id)) {
            throw new BusinessException("Product name must be unique");
        }
    }


}
