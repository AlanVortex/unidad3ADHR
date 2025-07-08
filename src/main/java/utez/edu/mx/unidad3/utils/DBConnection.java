package utez.edu.mx.unidad3.utils;

/*

@Configuration -> Le dice a spring que esta clase java va a generar una config durante la ejecución de la app,
pero esta anotación debe de ir con un método con la anotacion bean que le dia que va a configurar

@Bean -> Le indica a spring el metodo que retornara dicha configuracion

*/

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DBConnection {

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.username}")
    private String dbUsername;

    @Value("${db.password}")
    private String dbPassword;

    @Bean
    public DataSource getConnection(){
        try{
            DriverManagerDataSource configuration = new DriverManagerDataSource();
            configuration.setUrl(dbUrl);
            configuration.setUsername(dbUsername);
            configuration.setPassword(dbPassword);
            configuration.setDriverClassName("com.mysql.cj.jdbc.Driver");
            return configuration;
        }catch (Exception ex){
            System.out.println("Error al conectar a la BD");
            ex.printStackTrace();
            return null;
        }
    }
}
