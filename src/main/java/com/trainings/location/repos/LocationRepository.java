package com.trainings.location.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trainings.location.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {

	//@Query("select type,count(type) from location group by type")
	//public List<Object[]> findTypeAndTypeCount();
}
