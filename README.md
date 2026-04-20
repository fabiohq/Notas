
    }
    
}spring:
  profiles:
    active: local
  application:
    name: bnc-bsn049-mstermdepoprmtrs
  autoconfigure:
    exclude:
      - org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
      - org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
