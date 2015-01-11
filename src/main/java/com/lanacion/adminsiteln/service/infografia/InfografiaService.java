/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lanacion.adminsiteln.service.infografia;

import com.lanacion.adminsiteln.model.infografia.InfografiaDocument;
import java.util.List;

/**
 *
 * @author Luxo
 */
public interface InfografiaService {

    public InfografiaDocument getInfografia(Integer idInfografia);
    
    public List<InfografiaDocument> listInfografias();
    
    public void create(String titulo, String descripcion, Integer tipo_infografia, Integer estado);
    
    public void delete(Integer id);
    
    public void update(Integer idInfografia, String titulo, String descripcion, Integer tipo_infografia, Integer estado);
}
