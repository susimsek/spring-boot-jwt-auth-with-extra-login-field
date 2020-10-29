package com.spring.jwt.service;

import com.spring.jwt.model.Provider;
import com.spring.jwt.payload.request.ProductCreateRequest;
import com.spring.jwt.payload.request.ProviderCreateRequest;

public interface ProviderService {

    Provider createProvider(ProviderCreateRequest providerCreateRequest);
}
