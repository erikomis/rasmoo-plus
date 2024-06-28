package com.client.ws.rasmooplus.repository;

import com.client.ws.rasmooplus.model.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionTypeRepository extends JpaRepository<SubscriptionType, Long> {
}
