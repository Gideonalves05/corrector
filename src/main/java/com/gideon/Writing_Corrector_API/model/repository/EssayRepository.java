package com.gideon.Writing_Corrector_API.model.repository;

import com.gideon.Writing_Corrector_API.model.corrector.Essay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EssayRepository extends JpaRepository<Essay, String> {
}
