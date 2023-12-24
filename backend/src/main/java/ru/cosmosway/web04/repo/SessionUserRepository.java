package ru.cosmosway.web04.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cosmosway.web04.entities.SesssionUser;

public interface SessionUserRepository extends JpaRepository<SesssionUser, String> {}

