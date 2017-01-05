package com.gcit.lms;

import org.apache.commons.dbcp.BasicDataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.gcit.lms.dao.AuthorDao;
import com.gcit.lms.dao.BorrowerDao;
import com.gcit.lms.dao.BranchDao;
import com.gcit.lms.dao.LoanDao;
import com.gcit.lms.dao.CopiesDao;
import com.gcit.lms.dao.GenreDao;
import com.gcit.lms.dao.PublisherDao;
import com.gcit.lms.dao.BookDao;
import com.gcit.lms.service.AdminService;
import com.gcit.lms.service.BorrowerService;
import com.gcit.lms.service.LibrarianService;


@EnableTransactionManagement
@Configuration
public class LMSConfig {
	
	private String driverName = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost/library?useSSL=false";
	private String uname = "root";
	private String pwd = "ARde!#15";
	
	private JdbcTemplate template = null;
	
	@Bean
	public BasicDataSource dataSource(){
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driverName);
		ds.setUrl(url);
		ds.setUsername(uname);
		ds.setPassword(pwd);
		
		return ds;
	}
	
	@Bean
	public JdbcTemplate template(){
		if (template != null)
			return template;
		
		template = new JdbcTemplate();
		template.setDataSource(dataSource());
		
		return template;
	}
	
	@Bean
	public PlatformTransactionManager txManager(){
		DataSourceTransactionManager txManager = new DataSourceTransactionManager();
		txManager.setDataSource(dataSource());
		
		return txManager;
	}
	
	@Bean
	public AuthorDao adao(){
		return new AuthorDao();
	}
	
	@Bean
	public BookDao bdao(){
		return new BookDao();
	}
	@Bean
	public BorrowerDao bodao(){
		return new BorrowerDao();
	}	
	@Bean
	public BranchDao brdao(){
		System.out.println("brdao");
		//return new BranchDao(ldao(), template);
		return new BranchDao();
	}
	@Bean
	public CopiesDao cdao(){
		return new CopiesDao();
	}
	@Bean
	public GenreDao gdao(){
		return new GenreDao();
	}
	@Bean
	public LoanDao ldao(){
		return new LoanDao();
	}
	@Bean
	public PublisherDao pdao(){
		return new PublisherDao();
	}
	
	
	@Bean
	public AdminService adminService(){
		return new AdminService();
	}
	@Bean
	public BorrowerService borrowerService(){
		return new BorrowerService();
	}	
	
	@Bean
	public LibrarianService librarianService(){
		return new LibrarianService();
	}
}
