/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import com.mysql.jdbc.Connection;
import db.DB;
import model.dao.DaoFabrica;
import model.dao.VendedorDao;
import model.entities.Vendedor;

/**
 *
 * @author David
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        VendedorDao vendedorDao = DaoFabrica.createVendedorDao(); /*Interface VendedorDao chamando o metodo createVendedorDao da classe DaoFabrica que deve retornar
        um objeto com base em uma consulta do mysql*/
        
        System.out.println("Teste numero 1: vendedor findById ");
        
        Vendedor vendedor = vendedorDao.findById(3); /*a consulta terá um filtro onde o id é passado como parametro (where id = ?)*/
        
        System.out.println(vendedor);
        
        
        
    }
    
}