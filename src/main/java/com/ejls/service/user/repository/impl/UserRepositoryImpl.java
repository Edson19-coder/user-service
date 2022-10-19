package com.ejls.service.user.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceUnit;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;

import com.ejls.service.user.dto.request.UserRequest;
import com.ejls.service.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceUnit(unitName="base")
	EntityManagerFactory entityManagerFactory;

    @Override
    public Boolean createUser(UserRequest request) {
        log.info("Executing procedure PKG_USER.CREATE_USER");
        EntityManager eManager = entityManagerFactory.createEntityManager();
        try {
            if(eManager.isOpen()) {
                StoredProcedureQuery query = eManager.createStoredProcedureQuery("PKG_USER.CREATE_USER")
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, String.class, ParameterMode.OUT)
                .setParameter(1, request.getUserName())
                .setParameter(2, request.getEmail())
                .setParameter(3, request.getPassword());
                query.execute();
                @SuppressWarnings("unchecked")
                Boolean response = Boolean.parseBoolean(query.getOutputParameterValue(4).toString());
                return response;
            }
            return false;
        } catch(Exception error) {
            log.error(error.toString());
            return false;
        } finally {
            eManager.close();
        }
    }

    @Override
    public Boolean updateUser(UserRequest request) {
        log.info("Executing procedure PKG_USER.UPDATE_USER");
        EntityManager eManager = entityManagerFactory.createEntityManager();
        try {
            if(eManager.isOpen()) {
                StoredProcedureQuery query = eManager.createStoredProcedureQuery("PKG_USER.UPDATE_USER")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(6, String.class, ParameterMode.OUT)
                .setParameter(1, request.getUserId())
                .setParameter(2, request.getUserName())
                .setParameter(3, request.getEmail())
                .setParameter(4, request.getPassword())
                .setParameter(5, request.getActive());
                query.execute();
                @SuppressWarnings("unchecked")
                Boolean response = Boolean.parseBoolean(query.getOutputParameterValue(6).toString());
                return response;
            }
            return false;
        } catch(Exception error) {
            log.error(error.toString());
            return false;
        } finally {
            eManager.close();
        }
    }

    @Override
    public List<?> getUser(UserRequest request) {
        log.info("Executing procedure PKG_USER.GET_USER");
        EntityManager eManager = entityManagerFactory.createEntityManager();
        try {
            if(eManager.isOpen()) {
                StoredProcedureQuery query = eManager.createStoredProcedureQuery("PKG_USER.GET_USER")
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Class.class, ParameterMode.REF_CURSOR)
                .setParameter(1, request.getEmail());
                query.execute();
                @SuppressWarnings("unchecked")
                List<Object[]> response = query.getResultList();
                return response;
            }
            return null;
        } catch(Exception error) {
            log.error(error.toString());
            return null;
        } finally {
            eManager.close();
        }
    }

    @Override
    public List<?> getUsers() {
        log.info("Executing procedure PKG_USER.GET_USERS");
        EntityManager eManager = entityManagerFactory.createEntityManager();
        try {
            if(eManager.isOpen()) {
                StoredProcedureQuery query = eManager.createStoredProcedureQuery("PKG_USER.GET_USERS")
                .registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
                query.execute();
                @SuppressWarnings("unchecked")
                List<Object[]> response = query.getResultList();
                return response;
            }
            return null;
        } catch(Exception error) {
            log.error(error.toString());
            return null;
        } finally {
            eManager.close();
        }
    }
    
}
