/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sv.global.colas.repositories;

import org.springframework.data.repository.Repository;

import sv.global.colas.entities.EdDeclaraciones;
import sv.global.colas.pojos.security.PersonW;

/**
 *
 * 
 */
public interface PersonWRepository extends Repository<EdDeclaraciones,Long>{
   
    public static final String USERS_OU = "cn=users,dc=web,dc=gob,dc=sv";
    public static final String GROUPS_OU = "cn=apps,dc=web,dc=gob,dc=sv";
    
    public PersonW findByPrimaryKey(String name);
}
