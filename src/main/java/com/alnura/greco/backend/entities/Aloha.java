/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alnura.greco.backend.entities;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 *
 * @author armena
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aloha implements Serializable{

    private static final long serialVersionUID = 98996283192432100L;
    
    private String message;
    
}
