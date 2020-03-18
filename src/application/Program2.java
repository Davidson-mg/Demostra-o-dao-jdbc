/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.dao.DaoFabrica;
import model.dao.DepartamentoDao;
import model.entities.Departamento;

/**
 *
 * @author David
 */
public class Program2 {
    
    public static void main(String[] args) {
        
        
        Scanner leia = new Scanner(System.in);
    
        DepartamentoDao departamentoDao = DaoFabrica.createDepartamentoDao();
        
        System.out.println("Teste numero 1: Departamento findById ");
        
        Departamento departamento = departamentoDao.findById(3);
        
        System.out.println(departamento);
        
        
        
        
        
        System.out.println("");
        
        System.out.println("Teste numero 2: Departamento findAll");
        
        List<Departamento> list = departamentoDao.findAll();
         
        
        for(Departamento d : list){
        
            System.out.println(d);
        
        }
        
        
        System.out.println("");
        
        System.out.println("Teste numero 3: Departamento insert");
        
        Departamento novoDepartamento = new Departamento (null, "Novo DB");
        
        departamentoDao.insert(novoDepartamento);
        
        
        
        
        System.out.println("");
        
        System.out.println("Teste numero 4: Departamento update");
        
        departamento = departamentoDao.findById(5);
        departamento.setNome("Novo DB3");
        
        departamentoDao.update(departamento);
        
        
        
        
        System.out.println("");
        
        System.out.println("Teste numero 5: Departamento delete");
        
        System.out.print("Informe um id para delete: ");
        int id = leia.nextInt();
        
        departamentoDao.delete(id);
        
                
    }
    
}
