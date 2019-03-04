package com.stuart.testcontroller.service;

import com.stuart.testcontroller.model.Dog;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class DogService {
    public List<Dog> findDogs(String id, int age, Boolean bySmellyIndicator) {
        List<Dog> foundDogs = new ArrayList<>();

        // assuming id is unique on your database
        if (id != null && !id.isEmpty()) {
            foundDogs.addAll(getAllDogs().stream().filter(dog -> id.equals(dog.getId())).collect(Collectors.toList()));
            if (age > 0) { //age is a bad example as it cant be null, like ever!
                foundDogs = foundDogs.stream().filter(dog -> dog.getAge() == age).collect(Collectors.toList());
            } else {
                if (bySmellyIndicator != null) {
                    return foundDogs.stream().filter(dog -> dog.isSmellyIndicator() == bySmellyIndicator).collect(Collectors.toList());
                } else {
                    // id was only used
                    return foundDogs;
                }
            }
        } else { // id wasn't included in the search params, so start with age on the full list

            if (age > 0) {
                foundDogs.addAll(getAllDogs().stream().filter(dog -> dog.getAge() == age).collect(Collectors.toList()));
                if (bySmellyIndicator != null) {
                    return foundDogs.stream().filter(dog -> dog.isSmellyIndicator() == bySmellyIndicator).collect(Collectors.toList());
                }
            } else {
                if (bySmellyIndicator != null) {
                    // id nor age where included in the search params, so search only by smelly ind
                    return getAllDogs().stream().filter(Dog::isSmellyIndicator).collect(Collectors.toList());
                } else {
                    // no search pararms - return the full list
                    return getAllDogs();
                }
            }

            foundDogs.addAll(foundDogs.stream().filter(Dog::isSmellyIndicator).collect(Collectors.toList()));
        }
        return foundDogs.stream().distinct().collect(Collectors.toList());
    }

    public List<Dog> findDogs2(String id, int age, Boolean bySmellyIndicator) {
        List<Dog> foundDogs = new ArrayList<>();

        if (id != null && !id.isEmpty()) {
            foundDogs.addAll(getAllDogs().stream().filter(dog -> id.equals(dog.getId())).collect(Collectors.toList()));
        }
        if (age > 0) {
            foundDogs.addAll(getAllDogs().stream().filter(dog -> age == dog.getAge()).collect(Collectors.toList()));
        }
        if (bySmellyIndicator != null) {
            foundDogs.addAll(getAllDogs().stream().filter(dog -> bySmellyIndicator == dog.isSmellyIndicator()).collect(Collectors.toList()));
        }

        // If none are populated, we need the whole lot
        if ((id == null || id.isEmpty()) && (age==0) && (bySmellyIndicator == null)) {
            foundDogs = getAllDogs();
        }

        if (id != null && !id.isEmpty()) {
            foundDogs = foundDogs.stream().filter(dog -> id.equals(dog.getId())).collect(Collectors.toList());
        }
        if (age > 0) {
            foundDogs = foundDogs.stream().filter(dog -> age == dog.getAge()).collect(Collectors.toList());
        }
        if (bySmellyIndicator != null) {
            foundDogs = foundDogs.stream().filter(dog -> bySmellyIndicator == dog.isSmellyIndicator()).collect(Collectors.toList());
        }

        return foundDogs.stream().filter(distinctByKey(dog -> dog.getId())).collect(Collectors.toList());

    }

    private List<Dog> getAllDogs() {
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("001", "Robert", 7, true));
        dogs.add(new Dog("002", "Buster", 11, true));
        dogs.add(new Dog("003", "Susan", 11, false));
        dogs.add(new Dog("004", "Bessie", 13, false));

        return dogs;

    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
