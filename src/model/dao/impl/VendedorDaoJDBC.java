/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.impl;

import com.mysql.jdbc.Connection;
import db.DB;
import java.sql.PreparedStatement;
import db.DbException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

/**
 *
 * @author David
 */
public class VendedorDaoJDBC implements VendedorDao{
    
    
    private Connection conn; /*Estamos criando essa dependencia com o connection para que não seja necessario ficar criando um objeto de conexão
    toda vez que precisarmos de uma consulta no banco nos metodos abaixo*/

    public VendedorDaoJDBC(Connection conn) { /*Construtor*/
        this.conn = conn;
    }
    
    @Override
    public void insert(Vendedor obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Vendedor obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public Vendedor findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
            try{
                
                st = conn.prepareStatement( 
                    "SELECT Vendedor.*, Departamento.nome as depNome " 
                    +"FROM Vendedor INNER JOIN Departamento "  
                    +"ON Vendedor.DepartamentoId = departamento.Id "  
                    +"WHERE vendedor.id = ?"
                ); /*Quando a consulta for muito grande e não tiver como fazer na mesma linha. Não esquecer dos espaço ao final de cada linha, se não da erro de sintax no mysql*/
                
                st.setInt(1, id);
                rs = st.executeQuery(); /*Inicialmente a variavel do tipo rs ResultSet inicial com zero. Quando excutamos uma consulta, ela é armazenada na variavel rs*/
                
                
                if(rs.next()){ /*Se o rs não receber nenhum registro, vai da falso. Se for verdadeiro, signfica que retornou com uma linha de registro do banco*/
                    
                    /*Vamos pegar os registros de todas a colunas que serão retornadas na consulta e armazenar nos meus objetos Departamento e Vendedor*/
                    
                    Departamento dep = instanciandoDepartamento(rs); /*Chamando o metodos que serão responsaveis por passar para as classe Departamento e Vendedor o atributos retornados na consulta do mysql*/
                    Vendedor obj = instanciandoVendedor(rs, dep);
                    
                    return obj;
                    
                }
                return null;
            
            } 
            catch (SQLException e) {
                throw new DbException (e.getMessage());
            }
            finally {
            
                DB.closeStatement(st);
                DB.closeResulSet(rs);
            }     
    }

    @Override
    public List<Vendedor> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Departamento instanciandoDepartamento(ResultSet rs) throws SQLException { 
                                                                /*Não vamos tratar a exceções que vão ocorrer abaixa, pois já está sendo
                                                                tratado acima. Vamos simplesment propagar para eleminar os erros*/
        Departamento dep = new Departamento();
        dep.setId(rs.getInt("DepartamentoId")); /*dep.setId, vai passar para a classe Departamento. getInt vai pegar da consulta um valor inteiro na coluna DepartamentoId */
        dep.setNome(rs.getString("depNome")); /*estou pegando o nome do departamento pelo apelido que dei na consulta*/
        return dep;
    }

    
    
    private Vendedor instanciandoVendedor(ResultSet rs, Departamento dep) throws SQLException {
                                                                /*Não vamos tratar a exceções que vão ocorrer abaixa, pois já está sendo
                                                                tratado acima. Vamos simplesment propagar para eleminar os erros*/
        
        
        Vendedor obj = new Vendedor();
        obj.setId(rs.getInt("id"));
        obj.setNome(rs.getString("nome"));
        obj.setEmail(rs.getString("email"));
        obj.setDataNascimento(rs.getDate("dataNascimento"));
        obj.setSalarioBase(rs.getDouble("salarioBase"));
                    
        obj.setDepartamento(dep); /*Estamos associando o objeto vendedor com o departamento. Vale lembrar que essa associação não é feita pelo id
        de chave estrangeira das tabelas, e sim pelo atributo departamento criado na tabela vendedor*/
    
        return obj;
    }
    
}
