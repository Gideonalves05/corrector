package com.gideon.Writing_Corrector_API.model.repository;

import com.gideon.Writing_Corrector_API.model.corrector.correctorModel;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface correctorRepository extends PagingAndSortingRepository< correctorModel, String> {
}