package com.spring.jwt.service;

import com.spring.jwt.exception.production.ProductAlreadyExistsException;
import com.spring.jwt.exception.provider.ProviderAlreadyExistsException;
import com.spring.jwt.model.Product;
import com.spring.jwt.model.Provider;
import com.spring.jwt.payload.request.ProductCreateRequest;
import com.spring.jwt.payload.request.ProviderCreateRequest;
import com.spring.jwt.repository.ProductRepository;
import com.spring.jwt.repository.ProviderRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {

    ProviderRepository providerRepository;
    ModelMapper modelMapper;

    @Override
    public Provider createProvider(ProviderCreateRequest providerCreateRequest) {
        if (providerRepository.existsByName(providerCreateRequest.getName())) {
            throw new ProviderAlreadyExistsException();
        }

        Provider provider=modelMapper.map(providerCreateRequest, Provider.class);

        provider=providerRepository.save(provider);
        return provider;
    }
}
