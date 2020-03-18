/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import com.mysql.jdbc.Connection;
import db.DB;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import model.dao.DaoFabrica;
import model.dao.DepartamentoDao;
import model.dao.VendedorDao;
import model.entities.Departamento;
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
       
        Scanner leia = new Scanner(System.in);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        VendedorDao vendedorDao = DaoFabrica.createVendedorDao(); /*Interface VendedorDao chamando o metodo createVendedorDao da classe DaoFabrica que deve retornar
        um objeto com base em uma consulta do mysql*/
    
        System.out.println("Teste numero 1: vendedor findById ");
        
        Vendedor vendedor = vendedorDao.findById(3); /*a consulta terá um filtro onde o id é passado como parametro (where id = ?)*/
        
        System.out.println(vendedor);
        
        
        
        
        System.out.println("");
        
        System.out.println("Teste numero 2: vendedor findByDepartamento");
        
        Departamento departamento = new Departamento (4, null);
        
        List<Vendedor> list = vendedorDao.findByDepartamento(departamento);
        
        for(Vendedor obj : list){
        
            System.out.println(obj);
        
        }
        
        
        System.out.println("");
        
        System.out.println("Teste numero 3: vendedor findAll");
        
        
        list = vendedorDao.findAll();
        
        for(Vendedor obj : list){
        
            System.out.println(obj);
        
        }
        
        
        
        
        
        
        
        System.out.println("");
        
        System.out.println("Teste numero 4: vendedor insert");
        
        Vendedor novoVendedo = new Vendedor(null, "Davidson", "davidson@gmail.com", new Date(), 4000.0, departamento);
        
        vendedorDao.insert(novoVendedo);
        
        System.out.println("Inserido! Novo Id = " + novoVendedo.getId());
        
        
        
        
        
        
        
        System.out.println("");
        
        System.out.println("Teste numero 5: vendedor update");
        vendedor = vendedorDao.findById(1);
        vendedor.setNome("Orinamarona");
        vendedorDao.update(vendedor);
        System.out.println("Atualização completa!");
        
        
        
        
        
        System.out.println("");
        
        System.out.println("Teste numero 6: Departamento delete");
        System.out.println("Informe um id para delete");
        int id = leia.nextInt();
        vendedorDao.delete(id);
        System.out.println("Delete completo!");
    }
    
}