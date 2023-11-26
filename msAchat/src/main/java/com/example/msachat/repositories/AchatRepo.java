package com.example.msachat.repositories;

import com.example.msachat.entities.Achat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchatRepo extends JpaRepository<Achat,Long> {
}
