/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lanacion.adminsiteln.service.infografia;

import com.lanacion.adminsiteln.dao.infografiasrepository.InfografiasRepository;
import com.lanacion.adminsiteln.model.infografia.InfografiaDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;


/**
 *
 * @author Luxo
 */
@Service
public class InfografiaServiceImpl implements InfografiaService {
    
    @Autowired
    private ApplicationContext context = new ClassPathXmlApplicationContext("MySqlBean.xml");

    private InfografiasRepository infoRepo = 
      (InfografiasRepository)context.getBean("InfografiasRepositoryImpl");

    @Override
    public InfografiaDocument getInfografia(Integer idInfografia) {
        return infoRepo.getInfografia(idInfografia);
    }
    
}