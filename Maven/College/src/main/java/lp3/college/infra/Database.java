
package lp3.college.infra;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Faz a conecao com o banco de dados utilizando o arquivo db.properties
 */
public final class Database {
    private Database(){}
    private static Connection conexao = null;

    public static Connection getConexao()
    {
        if(conexao == null){
            try{
                Properties propriedades = carregaPropriedades();
                String url = propriedades.getProperty("dburl");     //usa a url no arquivo propriedades
                conexao = DriverManager.getConnection(url,propriedades);
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return conexao;
    }

    /**
     * Carrega o arquivo de propriedades db.properties
     */
    private static Properties carregaPropriedades(){
        try(FileInputStream fs = new FileInputStream("src/main/resources/db.properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
