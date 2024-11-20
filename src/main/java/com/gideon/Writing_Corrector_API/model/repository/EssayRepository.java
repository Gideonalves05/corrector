package com.gideon.Writing_Corrector_API.model.repository;

import com.gideon.Writing_Corrector_API.model.corrector.Essay;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface EssayRepository extends PagingAndSortingRepository< Essay , String> {
}