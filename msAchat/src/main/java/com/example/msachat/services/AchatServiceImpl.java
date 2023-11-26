package com.example.msachat.services;

import com.example.msachat.dto.AchatReq;
import com.example.msachat.dto.AchatResponse;
import com.example.msachat.dto.Currency;
import com.example.msachat.dto.ProductResponse;
import com.example.msachat.entities.Achat;
import com.example.msachat.mappers.AchatMapper;
import com.example.msachat.repositories.AchatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.awt.geom.CubicCurve2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AchatServiceImpl implements AchatService {
    @Autowired
    AchatRepo achatRepo;
    @Autowired
    WebClient webClient;
    @Autowired
    AchatMapper achatMapper;
    @Override
    public AchatResponse add(AchatReq achatReq) {
        Achat achat = achatMapper.fromAchatReq(achatReq);
        //-----------------taux de change------
        Currency currency = webClient.get()
                .uri("https://v6.exchangerate-api.com/v6/7470c4bdc23040be8ef6e902/pair/EUR/"+achat.getCurrency())
                .retrieve()
                .bodyToMono(Currency.class)
                .block();

        //----------------------------------------
        double tot = 0;
        for(Long p:achat.getProducts()){
            ProductResponse productResponse = webClient.get()
                    .uri("http://localhost:8080/api/product/"+p)
                    .retrieve()
                    .bodyToMono(ProductResponse.class)
                    .block();
            tot += productResponse.getPrice();
        }
        achat.setTotal(tot*currency.getConversion_rate());
        achatRepo.save(achat);

        return achatMapper.fromAchat(achat);
    }

    @Override
    public List<AchatResponse> allAchats() {
        List<Achat> achats = achatRepo.findAll();
        List<AchatResponse> achatResponses = new ArrayList<>();
        achats.forEach(achat -> {
            List<ProductResponse> productResponses = new ArrayList<>();
            achat.getProducts().forEach(product -> {
                productResponses.add(webClient.get()
                        .uri("http://localhost:8080/api/product/"+product)
                        .retrieve()
                        .bodyToMono(ProductResponse.class)
                        .block());
            });
            achatResponses.add(new AchatResponse(achat.getId(), achat.getDate(),
                    productResponses, achat.getCurrency(), achat.getTotal()));
        });
        return achatResponses;
    }

    @Override
    public AchatResponse getAchat(Long id) throws Exception {
        Optional<Achat> achatOptional = achatRepo.findById(id);
        if(achatOptional.isPresent()) {
            Achat achat = achatOptional.get();
            List<ProductResponse> productResponses = new ArrayList<>();
            achat.getProducts().forEach(product -> {
                productResponses.add(webClient.get()
                        .uri("http://localhost:8080/api/product/"+product)
                        .retrieve()
                        .bodyToMono(ProductResponse.class)
                        .block());
            });

            return new AchatResponse(achat.getId(), achat.getDate(),
                    productResponses, achat.getCurrency(), achat.getTotal());
        } else {
            throw new Exception("The purchase with the id " + id + " not found");
        }
    }
}
